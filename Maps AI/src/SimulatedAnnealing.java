import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {

    double temperature;
    double initialTemp = schedule(0);
    int x;
    private Location current;
    private Location start;
    private Location goal;
    private Location next;
    private ArrayList<Location> path;
    private int bestPathETA;



    public SimulatedAnnealing(Location start, Location goal, int iterations){
        this.start = start;
        start.setG_n(0);
        this.goal = goal;
        this.current = start;
        this.x = iterations;
        path = new ArrayList<>();

        traversePath();
    }


    public void traversePath(){

        for(int i=0; i<x; i++ ){
            temperature = schedule(i); //schedule[t]
//            System.out.println("Iteration "+i+ ": Temperature: "+temperature+" Current: "+ current.getName());
            if (temperature == 0 || (current == goal && temperature < 0.05 * initialTemp)) break;
            Random rand= new Random();
            int nextInt = rand.nextInt(current.getNeighbors().size());
            int counter = 0;
            for(Location l: current.getNeighbors().keySet()){
                if(counter == nextInt) {
                    next = l;
                    break;
                }
                counter++;
            }
            
//            int delta_E = current.h_n()/60 - next.h_n()/60; //VALUE (time to travel in minutes)
            int delta_E = current.h_n()/60 - next.value(current);
            float randomFloat = rand.nextFloat();
            if(delta_E > 0 || Math.exp(delta_E/temperature) >= randomFloat) {
//                next.addPrevious(current);
                if(next.getG_n() > current.getG_n() + current.getNeighbors().get(next)[3]) {
                	next.setG_n(current.getG_n() + current.getNeighbors().get(next)[3]);
                	next.addPrevious(current);
                	if(current.getNeighbors().containsKey(next)) {
                		current.removePrevious(next);
                	}
                }
                if(current.getG_n() > next.getG_n() + next.getNeighbors().get(current)[3]) {
                	current.setG_n(next.getG_n() + next.getNeighbors().get(current)[3]);
                	current.addPrevious(next);
                	if(next.getNeighbors().containsKey(current)) {
                		next.removePrevious(current);
                	}
                }
                current.isVisited = true;
                current = next;
            }
//            else {
//                float randomFloat = rand.nextFloat();
//                if(Math.exp(delta_E/temperature) >= randomFloat){
//                    next.addPrevious(current);
//                    if(next.getG_n() > current.getG_n() + current.getNeighbors().get(next)[3]) {
//                    	next.setG_n(current.getG_n() + current.getNeighbors().get(next)[3]);
//                    }
//                    if(current.getG_n() > next.getG_n() + next.getNeighbors().get(current)[3]) {
//                    	current.setG_n(next.getG_n() + next.getNeighbors().get(current)[3]);
//                    }
//                    current.isVisited = true;
//                    current = next;
//                }
//            }

        }

        fillPath(current);

    }

    private double schedule(double t){
        int k = 20;
        double lam = 0.005;
        int limit = 5000;
        double nT = 0;
        if(t < limit) {
            nT = (k* Math.exp(-lam * t));
        }
        return nT;
    }

    private boolean foundPath = false;

    private void fillPath(Location l) {
        if(l == start) {
            foundPath = true;
            path.add(l);
            return;
        }
        for(Location prev : l.getAllPrevious()) {
        		fillPath(prev);
            if(foundPath && l.getG_n() == prev.getG_n() + l.getNeighbors().get(prev)[3]) {
            	this.bestPathETA += l.getNeighbors().get(prev)[3];
                path.add(l);
                return;
            }
            else if(foundPath && l.getG_n() != prev.getG_n() + l.getNeighbors().get(prev)[3]) {
            	foundPath = false;
            	bestPathETA = 0;
            	path = new ArrayList<>();
            }
        }
    }

    public ArrayList<Location> getPath() {
        return this.path;
    }

    public int getETA() {
        return this.bestPathETA;
    }


}
