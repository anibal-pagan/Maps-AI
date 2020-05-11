import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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

    public boolean isConsistent(Location start, Location goal, int pathSum) {
        System.out.println("Estoy en " + start.getName() + " pathSum " + pathSum);
        System.out.println("start: " + start.getName() + " goal: " + goal.getName());
        if (start.equals(goal))
            return start.h_n() <= pathSum;
        Set<Location> visited = new LinkedHashSet<>();
        Queue<Location> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            Location vertex = queue.poll();
            for (Location current : vertex.getNeighbors().keySet()) {
                isConsistent(current, goal, 0); // probar con todos los hijos del parent
                if (!visited.contains(current)) {
                    System.out.println("Visite a " + current.getName());
                    visited.add(current);
                    queue.add(current);
                    isConsistent(current, goal, pathSum + start.getG_n()); // sumando el path
                }
            }
        }
        return true;

    }

    

    // /Users/davidcarrionbeniquez/Desktop/west_map.csv
}