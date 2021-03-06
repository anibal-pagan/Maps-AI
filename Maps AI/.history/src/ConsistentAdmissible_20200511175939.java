import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class ConsistentAdmissible {
    private Location startLocation;

    public ConsistentAdmissible(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getStartLocation() {
        return this.startLocation;
    }

    public boolean isConsistent() {

        return true;
    }

    public boolean isAdmissible() {
        return isAdmissibleHelper(this.startLocation, new HashSet<Location>());

    }

    private boolean isAdmissibleHelper(Location loc, Set<Location> visited){
        if(loc.getG_n()< loc.h_n())
            return false;
        for(Location neighbor : loc.getNeighbors().keySet()){
            if(!visited.contains(neighbor)){
                visited.add(neighbor);
                isAdmissibleHelper(neighbor, visited);
            }
        }
        return true;
    }

  
 
        public void computeShortestPaths(){
     
            // sourceVertex;
            PriorityQueue<Location> priorityQueue = new PriorityQueue<>( new LocationComparator());
            Set<Location> visited = new LinkedHashSet<>();
            priorityQueue.add(this.startLocation);
           
     
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
     
            //for(Location vertex=targetVertex; vertex!=null; vertex = vertex.getAllPrevious().get(0)){
                Location vertex= targetVertex;
                while(!vertex.getAllPrevious().isEmpty()){
                    
                    path.add(vertex);
                    vertex= vertex.getAllPrevious().get(0);
                }
                path.add(vertex);
           // }
     
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