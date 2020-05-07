import java.util.HashMap;

public class Location {


    private boolean isGoal = false;
    private boolean isStart = false;
    private boolean isVisited = false;
    private int etaFromHereToGoal = Integer.MAX_VALUE;    //h(n)
    private String name;
    private HashMap<Location, int[]> neighbors;           //g(n) = int[3]

    public Location(String name){
        this.neighbors = new HashMap<>();
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setIsStart(){
        isStart = true;
    }

    public void setIsGoal(){
        isGoal = true;
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

    public boolean isVisited(){
        return  isVisited;
    }

    public void visit(){
        isVisited = true;
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
}
