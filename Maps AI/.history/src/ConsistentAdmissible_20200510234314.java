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
                
                // if (!visited.contains(current)) {
                    int suma = current.h_n() + vertex.getG_n();
                    System.out.println("Estoy en " + vertex.getName() );
                    System.out.println("My vecino es " + current.getName() );
                    System.out.println(vertex.h_n()+ " <= "+ suma);
                    System.out.println("vertex h(n) " +vertex.h_n());
                    System.out.println("current h(n) " +current.h_n());
                    System.out.println("vertex g(n) " +vertex.getG_n());
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
                    int suma = current.h_n() + vertex.getG_n();
                    System.out.println("Estoy en " + vertex.getName() );
                    System.out.println("My vecino es " + current.getName() );
                    System.out.println(vertex.h_n()+ " <= "+ suma);
                    System.out.println("vertex h(n) " +vertex.h_n());
                    System.out.println("current h(n) " +current.h_n());
                    System.out.println("vertex g(n) " +vertex.getG_n());
                    System.out.println("  ");
                    
                    if (!(vertex.h_n() <= suma)) {
                        return false;
                    }
                    visited.add(current);
                    queue.add(current);
                }
            }
        }
        return true;

    }

    //                          /Users/davidcarrionbeniquez/Desktop/west_map.csv
}