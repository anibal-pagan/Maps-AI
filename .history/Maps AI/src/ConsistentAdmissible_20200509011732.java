public class ConsistentAdmissible {

    public boolean isConsistent(Location current, Location goal, int pathSum ){
    System.out.println("current: "+ current.getName()+" pathsum: " + pathSum);
        if(current.equals(goal))
        return current.h_n()<=pathSum;
    
    for(Location loc: current.getNeighbors().keySet()){
    if (!(isConsistent(loc, goal, pathSum + loc.getG_n()) && isConsistent(loc, goal, 0)))
    break;
    }
    return false;
    }

    public boolean isAdmissible(Location current, Location goal){
        if(current.equals(goal))
            return true;
        for(Location loc : current.getNeighbors().keySet()){
        if (!(current.h_n()<=loc.h_n()+current.getG_n()))
        isAdmissible(loc, goal);    
        break;     
        }
        return false;
    }

    }