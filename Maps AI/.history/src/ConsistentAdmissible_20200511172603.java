import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class ConsistentAdmissible {

    private HashMap<String, Location> map = new HashMap<>();

    // public boolean isConsistent(Location current, Location goal, int pathSum) {

    // // System.out.println("current: " + current.getName() + " pathsum: " +
    // pathSum);
    // this.traversal(current);
    // return true;
    // }

    public boolean isConsistent(Location start, Location goal) {

        if (start.equals(goal))
            return true;

        Set<Location> visited = new LinkedHashSet<>();
        Queue<Location> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Location vertex = queue.poll();

            for (Location current : vertex.getNeighbors().keySet()) {

                // if (!visited.contains(current)) {
                int suma = current.h_n() + vertex.getG_n();
                System.out.println("Estoy en " + vertex.getName());
                System.out.println("My vecino es " + current.getName());
                System.out.println(vertex.h_n() + " <= " + suma);
                System.out.println("vertex h(n) " + vertex.h_n());
                System.out.println("current h(n) " + current.h_n());
                System.out.println("vertex g(n) " + vertex.getG_n());
                System.out.println("  ");

                if (!(vertex.h_n() <= suma)) {
                    return false;
                }
                visited.add(current);
                queue.add(current);
                // }
            }
        }
        return true;
    }

    public boolean isAdmissible(Location start, Location goal, int pathSum) {

        Set<Location> visited = new LinkedHashSet<>();
        Queue<Location> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Location vertex = queue.poll();

            for (Location current : vertex.getNeighbors().keySet()) {

                if (!visited.contains(current)) {

                    System.out.println("  ");

                    if (!(vertex.h_n() <= pathSum)) {
                        return false;
                    }
                    visited.add(current);
                    queue.add(current);
                }
            }
        }
        return true;

    }

  
 
        public void computeShortestPaths(Location sourceVertex){
     
            // sourceVertex;
            PriorityQueue<Location> priorityQueue = new PriorityQueue<>( new LocationComparator());
            Set<Location> visited = new LinkedHashSet<>();
            priorityQueue.add(sourceVertex);
           
     
            while( !priorityQueue.isEmpty() ){
                // Getting the minimum distance vertex from priority queue
                Location actualVertex = priorityQueue.poll();
     
                for(Location.Edge edge : actualVertex.getEdges()){
                    Location v = edge.getTargetLocation();
                    if(!visited.contains(v))
                    {
                        int newDistance = actualVertex.getG_n() + edge.getWeight();
     
                        if( newDistance < v.getG_n() ){
                            priorityQueue.remove(v);
                            v.setG_n(newDistance);
                            if(v.getAllPrevious().isEmpty()){
                                v.addPrevious(actualVertex);
                            }
                            else{
                                v.removePrevious();
                                v.addPrevious(actualVertex);
                            }
                            priorityQueue.add(v);
                        }
                    }
                }
                visited.add(actualVertex);
            }
        }
     
        public List<Location> getShortestPathTo(Location targetVertex){
            
            List<Location> path = new ArrayList<>();
     
            for(Location vertex=targetVertex; vertex!=null; vertex = vertex.getAllPrevious().get(0)){
                path.add(vertex);
            }
     
            Collections.reverse(path);
            return path;
        }

        private class LocationComparator implements Comparator<Location>{
            @Override
            public int compare(Location o1, Location o2) {
                return o1.getG_n() - o2.getG_n();
            }
        }
     


    

    // /Users/davidcarrionbeniquez/Desktop/west_map.csv
}