public class ConsistentAdmissible {

    public boolean isConsistent(Location current, Location goal, int pathSum ){
    if(current.equals(goal))
        return current.h_n()<=pathSum;
    boolean consistente = true;
    for(Location loc: current.getNeighbors().keySet()){
    if (!(isConsistent(loc, goal, pathSum + loc.getG_n()) && isConsistent(loc, goal, pathSum)))
    consistente=false;
    }
    return consistente;
    }
    
    }