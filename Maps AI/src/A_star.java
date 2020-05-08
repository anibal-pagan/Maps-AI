import java.util.*;

public class A_star {

    Set<Location> exploredSet;
    PriorityQueue<Location> frontier;
    Location start;
    Location goal;
    Location current;
    ArrayList<Location> path = new ArrayList<>();
    int bestPathETA = 0;

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
        int gn = from.getG_n()+ from.getNeighbors().get(to)[3];   //g(n) = int[3]
        to.setG_n(gn);

        return hn+gn;
    }

    private void traversePath(){

        if(exploredSet.isEmpty()){
            exploredSet.add(start);
        }

        while(current != goal){

            for(Location neighbor : current.getNeighbors().keySet()){
                if(!neighbor.isVisited()) frontier.add(neighbor);
            }
            current = frontier.poll();
            exploredSet.add(current);
        }

        //FALTA DEVOLVER PATH Y ETA
    }
}
