import java.util.ArrayList;
import java.util.HashMap;

public class Location {


    private boolean isGoal = false;
    private boolean isStart = false;
    private int etaFromHereToGoal = Integer.MAX_VALUE;    //h(n)
    private int pathFromStart = 999999999;                        //g(n)
    private String name;
    boolean isVisited = false;
    private HashMap<Location, int[]> neighbors;           //step cost = int[3]
    private ArrayList<Location> previous;

    public Location(String name){
        this.neighbors = new HashMap<>();
        this.name = name;
        this.previous = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setIsStart(){
        isStart = true;
        this.pathFromStart = 0;
    }

    public void setIsGoal(){
        isGoal = true;
    }
    
    public void addPrevious(Location l) {
    	if(!previous.contains(l)) {
    		this.previous.add(l);
    	}
    }
    
    public void removePrevious(Location l) {
    	this.previous.remove(l);
    }
    
    public void setH_n(int eta) {
    	etaFromHereToGoal = eta;
    }
    
    public boolean isStart() {
    	return isStart;
    }
    
    public boolean isGoal() {
    	return isGoal;
    }
    
    public int h_n() {
    	return etaFromHereToGoal;
    }

    public void setG_n(int g_n){
        this.pathFromStart = g_n;
    }

    public int getG_n(){
        return pathFromStart;
    }
    
    public ArrayList<Location> getAllPrevious() {
    	return this.previous;
    }

    public void addNeighbor(Location location, int distance, int maxSpeed, int averageSpeed){

        int[] ETAvariables = {distance, maxSpeed, averageSpeed, (distance*3600)/averageSpeed};
        neighbors.put(location, ETAvariables);
    }


    public HashMap<Location, int[]> getNeighbors(){
        return neighbors;
    }

    public String getNeighborsString(){
        StringBuilder str = new StringBuilder();
        for(Location l: neighbors.keySet()){
            str.append(l.getName()+"  ");
        }
        return str.toString();
    }
    
    public int value(Location other) {
    	if(this.isVisited || (!this.isVisited && other.h_n() > this.h_n())) {
    		return this.h_n()/60;
    	}
    	return this.h_n()/30;
    }
}
