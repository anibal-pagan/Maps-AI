import java.util.*;

public class A_star {

    Stack<Location> exploredSet;
    PriorityQueue<Location> frontier;
    Location start;
    Location goal;
    ArrayList<Location> path = new ArrayList<>();
    int bestPathETA = 0;

    public A_star(Location start, Location goal){
        this.start = start;
        this.goal = goal;
        this.exploredSet = new Stack<>();
        LocationComparator locationComparator = new LocationComparator();
        this.frontier = new PriorityQueue<>(locationComparator);

        traversePath();
    }

    private class LocationComparator implements Comparator<Location>{
        @Override
        public int compare(Location o1, Location o2) {
            return getHnPlusGn(exploredSet.peek(),o1) - getHnPlusGn(exploredSet.peek(),o2);
        }
    }

    private int getHnPlusGn(Location from, Location to){
        int hn = to.h_n();
        int gn = from.getNeighbors().get(to)[3];   //g(n) = int[3]

        return hn+gn;
    }

    private void traversePath(){

        if(exploredSet.isEmpty()){
            exploredSet.push(start);
        }

        Location current = exploredSet.peek();
        while(current != goal){

            for(Location neighbor : current.getNeighbors().keySet()){
                frontier.add(neighbor);
            }
            exploredSet.push(frontier.poll());
        }

        //FALTA DEVOLVER PATH Y ETA
    }
}
