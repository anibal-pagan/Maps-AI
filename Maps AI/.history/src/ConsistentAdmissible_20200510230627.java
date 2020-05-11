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
                
                if (!visited.contains(current)) {
                    System.out.println("Estoy en " + vertex.getName() );
                    System.out.println("My vecino es " + current.getName() );
                    System.out.println(vertex.h_n()+ " <= "+current.h_n() + vertex.getG_n());
                    System.out.println("  ");
                    
                    if (!(vertex.h_n() <= current.h_n() + vertex.h_n())) {
                        return false;
                    }
                    visited.add(current);
                    queue.add(current);
                }
            }
        }
        return true;
    }

    public boolean isAdmissible(Location start, Location goal, int pathSum) {
        // System.out.println("Estoy en " + start.getName() + " pathSum " + pathSum);
        // System.out.println("start: " + start.getName() + " goal: " + goal.getName());
        // if (start.getName().equals(goal.getName())) {
        //     System.out.println(start.getName().equals(goal.getName()));
        //     return start.h_n() <= pathSum;
        // }
        // Set<Location> visited = new LinkedHashSet<>();
        // Queue<Location> queue = new LinkedList<>();
        // queue.add(start);
        // visited.add(start);

        // while (!queue.isEmpty()) {
        //     Location vertex = queue.poll();

        //     for (Location current : vertex.getNeighbors().keySet()) {
        //         isAdmissible(current, goal, 0); // probar con todos los hijos del parent
        //         if (!visited.contains(current)) {
        //             System.out.println("Visite a " + current.getName());
        //             visited.add(current);
        //             queue.add(current);
        //             isAdmissible(current, goal, pathSum + current.getG_n()); // sumando el path
        //         }
        //     }
        // }
        return true;

    }

    //                          /Users/davidcarrionbeniquez/Desktop/west_map.csv
}