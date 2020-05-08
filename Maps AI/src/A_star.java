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
		this.exploredSet = new HashSet<>();
		LocationComparator locationComparator = new LocationComparator();
		this.frontier = new PriorityQueue<>(locationComparator);

		traversePath();
	}

	private class LocationComparator implements Comparator<Location>{
		@Override
		public int compare(Location o1, Location o2) {
			return getHnPlusGn(current,o1) - getHnPlusGn(current,o2);
		}
	}

	private int getHnPlusGn(Location from, Location to){
		int hn = to.h_n();
		int gn = from.getG_n()+ from.getNeighbors().get(to)[3];   //step cost = int[3]
		if(to.getG_n() > gn) {	
			to.setG_n(gn);
		}
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
			if(foundPath) {
				path.add(l);
				return;
			}
		}
	}
	
	public ArrayList<Location> getPath() {
		return this.path;
	}
	
	public int getETA() {
		return this.bestPathETA;
	}


}
