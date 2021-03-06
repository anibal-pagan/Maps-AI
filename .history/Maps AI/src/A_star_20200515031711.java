import java.util.*;

public class A_star {

	private Set<Location> exploredSet;
	private PriorityQueue<Location> frontier;
	private Location start;
	private Location goal;
	private Location current;
	private ArrayList<Location> path = new ArrayList<>();
	private int bestPathETA = 0;

	public A_star(Location start, Location goal){
		this.start = start;
		this.goal = goal;
		this.current = start;
		this.start.setG_n(0);
		this.exploredSet = new HashSet<>();
		LocationComparator locationComparator = new LocationComparator();
		this.frontier = new PriorityQueue<>(locationComparator);

		traversePath();
	}

	private class LocationComparator implements Comparator<Location>{
		@Override
		public int compare(Location o1, Location o2) {
			return getHnPlusGn(o1) - getHnPlusGn(o2);
		}
	}

	//h(n) + g(n)
	private int getHnPlusGn(Location to){
		int hn = to.h_n();
		int gn = to.getG_n();
		return hn+gn;
	}

	private void traversePath(){

		if(exploredSet.isEmpty()){
			exploredSet.add(start);
		}

		while(current != goal){

			for(Location neighbor : current.getNeighbors().keySet()){
				if(!exploredSet.contains(neighbor)) {
					neighbor.addPrevious(current);
					if(neighbor.getG_n() > current.getG_n()+current.getNeighbors().get(neighbor)[3]) {
						neighbor.setG_n(current.getG_n()+current.getNeighbors().get(neighbor)[3]);
					}
					frontier.add(neighbor);
				}
			}
			current = frontier.poll();
			exploredSet.add(current);
		}

		this.bestPathETA = current.getG_n();
		fillPath(current);


	}
	
	private boolean foundPath = false;
	
	private void fillPath(Location l) {
		if(l == start) {
			foundPath = true;
			path.add(l);
			return;
		}
		for(Location prev : l.getAllPrevious()) {
			fillPath(prev);
			if(foundPath && l.getG_n() == prev.getG_n() + l.getNeighbors().get(prev)[3]) {
                path.add(l);
                return;
            }
			else if(foundPath && l.getG_n() != prev.getG_n() + l.getNeighbors().get(prev)[3]) {
            	foundPath = false;
            	path = new ArrayList<>();
            }
		}
	}
	
	public ArrayList<Location> getPath() {
		return this.path;
	}
	
	//Estimated Time of Arrival
	public int getETA() {
		return this.bestPathETA;
	}


}
