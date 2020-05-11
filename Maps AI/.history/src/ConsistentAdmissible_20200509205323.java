import java.util.HashSet;
import java.util.Set;

public class ConsistentAdmissible {
    private Set<Location> exploredSet;

    public ConsistentAdmissible() {
        this.exploredSet = new HashSet<>();
    }

    public boolean isConsistent(Location current, Location goal, int pathSum) {
        System.out.println("current: " + current.getName() + " pathsum: " + pathSum);
        if (current.equals(goal))
            return current.h_n() <= pathSum;
            
            exploredSet.add(current);
        for (Location loc : current.getNeighbors().keySet()) {
            if (!exploredSet.contains(loc)) {
                if (!(isConsistent(loc, goal, pathSum + loc.getG_n()) && isConsistent(loc, goal, 0)));
                // break;
            }
        }
        return false;
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

}