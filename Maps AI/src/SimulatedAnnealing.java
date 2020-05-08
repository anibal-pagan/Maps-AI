import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {

    double temperature;
    int x;
    private Location current;
    private Location start;
    private Location goal;
    private Location next;
    private ArrayList<Location> path;
    private int bestPathETA;



    public SimulatedAnnealing(Location start, Location goal, int iterations){
        this.start = start;
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
            if (temperature == 0) break;
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
            int delta_E = current.h_n() - next.h_n(); //VALUE
            if(delta_E > 0) {
                next.addPrevious(current);
                current = next;
            }
            else {
                float randomFloat = rand.nextFloat();
                if(Math.exp(delta_E/temperature) >= randomFloat){
                    next.addPrevious(current);
                    current = next;
                }
            }


        }

        fillPath(current);

    }

    private double schedule(double t){
        int k = 1000;
        double lam = 0.005;
        int limit = 100;
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
            if(foundPath) {
                path.add(l);
                return;
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
