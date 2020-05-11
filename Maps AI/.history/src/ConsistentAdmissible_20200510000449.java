import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ConsistentAdmissible {

    private HashMap<String, Location> map = new HashMap<>();

    

    public boolean isConsistent(Location current, Location goal, int pathSum) {
       
        System.out.println("current: " + current.getName() + " pathsum: " + pathSum);
        this.traversal(current);
        
    }

    public boolean isAdmissible(Location current, Location goal) {
        // System.out.println("current: "+ current.getName());
        // if(current.equals(goal))
        // return true;
        // for(Location loc : current.getNeighbors().keySet()){
        // if (!(current.h_n()<=loc.h_n()+current.getG_n()))
        // isAdmissible(loc, goal);
        // break;
        // }
        return false;
    }

    public void traversal(Location start){
        Set<Location> visited = new LinkedHashSet<>();
        Queue <Location> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
                    Location vertex = queue.poll();
                    for (Location current : start.getNeighbors().keySet()) {
                       if (!visited.contains(current)){
                        System.out.println("Visite a " + current.getName());   
                        visited.add(current);
                           queue.add(current);
                      
                        }
                                }
                                        }
                                        
    }

    


    
    
    

}