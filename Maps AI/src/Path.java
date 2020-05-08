import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

public class Path {

    ArrayList<Location> myPath;
    int pathCost = 0;

    public Path(){
        myPath = new ArrayList<>();
    }

    public void setMyPath(ArrayList<Location> myPath) {
        this.myPath = (ArrayList<Location>) myPath.clone();
    }

    public Location getFirstLocation(){
        return myPath.get(0);
    }

    public Location getLastLocation(){
        return myPath.get(myPath.size()-1);
    }

    public void addLocation(Location location, int stepCost){
        myPath.add(location);
        pathCost += stepCost;
    }

    public int getPathCost(){
        return pathCost;
    }

    public ArrayList<Location> getMyPath(){
        return myPath;
    }

}
