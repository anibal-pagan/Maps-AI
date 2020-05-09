import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the path of the csv file containing the map");

        String csvFile = scan.nextLine();
        HashMap<String, Location> map = new HashMap<>();

        readFile(csvFile, map);
        map.get("Vega Baja").setIsStart();
        map.get("Mayaguez").setIsGoal();

        System.out.println(map.size());
        
        fillH_n(map);

        for(String area: map.keySet()){

            System.out.println(area +" -> "+ map.get(area).getNeighborsString());
        }
        System.out.println("\nh(n)");
        for(String area: map.keySet()) {
        	System.out.println(area + " = " + map.get(area).h_n());
        }
        
        A_star s = new A_star(map.get("Vega Baja"), map.get("Mayaguez"));
//        SimulatedAnnealing s = new SimulatedAnnealing(map.get("Vega Baja"), map.get("Mayaguez"), 1000);

        System.out.println("\nETA = " + s.getETA());
        System.out.println("\nPath:");
        for(Location l : s.getPath()) {
            System.out.println(l.getName());
        }

        scan.close();

    }

    private static void readFile(String filePath, HashMap<String, Location> map){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line=br.readLine()) != null){

                String[] fromTo = line.split(",");
                String fromPin = fromTo[0];
                String toPin = fromTo[1];
                int distance = Integer.parseInt(fromTo[2]);
                int maxSpeed = Integer.parseInt(fromTo[3]);
                int averageSpeed = Integer.parseInt(fromTo[4]);


                if(map.size()>0 && map.containsKey(fromPin) && map.containsKey(toPin)){
                    map.get(fromPin).addNeighbor(map.get(toPin),distance, maxSpeed, averageSpeed);
                    map.get(toPin).addNeighbor(map.get(fromPin), distance, maxSpeed, averageSpeed);
                }else if(map.size()>0 && map.containsKey(fromPin) && !map.containsKey(toPin)){
                    Location newLocation= new Location(toPin);
                    map.put(toPin.trim(), newLocation);
                    map.get(fromPin).addNeighbor(newLocation, distance, maxSpeed, averageSpeed);
                    map.get(toPin).addNeighbor(map.get(fromPin), distance, maxSpeed, averageSpeed);

                } else if (map.size()>0 && map.containsKey(toPin) && !map.containsKey(fromPin)){
                    Location newLocation= new Location(fromPin);
                    map.put(fromPin.trim(), newLocation);
                    map.get(toPin).addNeighbor(newLocation, distance, maxSpeed, averageSpeed);
                    newLocation.addNeighbor(map.get(toPin), distance, maxSpeed, averageSpeed);
                } else {
                    Location from = new Location(fromPin);
                    map.put(fromPin, from);
                    Location to = new Location(toPin);
                    map.put(toPin, to);
                    map.get(fromPin).addNeighbor(to, distance, maxSpeed, averageSpeed);
                    map.get(toPin).addNeighbor(from,distance, maxSpeed,averageSpeed);
                }

            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void fillH_n(HashMap<String, Location> map) {
    	for(String location : map.keySet()) {
    		if(map.get(location).isGoal()) {
    			fillH_nHelper(map, location, 0);
    			break;
    		}
    	}
    }
    
    private static void fillH_nHelper(HashMap<String, Location> map, String currLocation, int travelTime) {
    	Queue<Location> frontier = new LinkedList<>();
    	if(map.get(currLocation).h_n() < travelTime) return;
    	
    	map.get(currLocation).setH_n(travelTime);
    	
    	for(Location l : map.get(currLocation).getNeighbors().keySet()) {
    		frontier.add(l);
    	}
    	
    	while(!frontier.isEmpty()) {
    		Location location = frontier.remove();
    		int[] travelInfo = map.get(currLocation).getNeighbors().get(location);
    		int addedTime = (travelInfo[0]*3600)/travelInfo[1];
    		fillH_nHelper(map, location.getName(), travelTime + addedTime);
    	}
    }
}
