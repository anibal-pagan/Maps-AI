import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Agent {
	
	ConsistentAdmissible check;

    public Agent(){

        askForInfo();
    }
    // prompts the user for csv file path, algorithm to run, or verifications (admissible, inconsistent)
    private void askForInfo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Quarantine Maps! ");
        System.out.println("Enter the path of the csv file containing the map");

        String csvFile = scan.nextLine();
        HashMap<String, Location> map = new HashMap<>();
        readFile(csvFile, map);

        System.out.println("Set the start location:");
        String start = scan.nextLine();
        System.out.println("Set destination:");
        String destination = scan.nextLine();

        map.get(start).setIsStart();
        map.get(destination).setIsGoal();
        check = new ConsistentAdmissible(map.get(destination));
        fillH_n(map);

        System.out.println("\nChoose the algorithm you want to run:");
        System.out.println("(0)A*   (1)Simulated Annealing  (2)Admissible   (3)Consistent");
        switch (scan.nextInt()){
            case 0: {
                A_star s = new A_star(map.get(start), map.get(destination));
                System.out.println("\nETA = " + s.getETA());
                System.out.println("\nPath:");
                for(Location l : s.getPath()) {
                    System.out.println(l.getName());
                }
                break;
            }
            case 1: {
                SimulatedAnnealing s = new SimulatedAnnealing(map.get(start), map.get(destination), 1000);
                System.out.println("\nETA = " + s.getETA());
                System.out.println("\nPath:");
                for(Location l : s.getPath()) {
                    System.out.println(l.getName());
                }
                break;
            }
            case 2: {
            	boolean isAdmissible = check.isAdmissible();
            	if(isAdmissible) {
            		System.out.println("Graph is admissible!");
            	}
            	else {
            		System.out.println("Graph isn`t admissible!");
            	}
            	break;
            }
            case 3: {
            	boolean isConsistent = check.isConsistent(new ArrayList<Location>(map.values()));
            	if(isConsistent) {
            		System.out.println("Graph is consistent!");
            	}
            	else {
            		System.out.println("Graph isn`t consistent!");
            	}
            	break;
            }
            default: {
            	System.out.println("Error: Option not available.");
            }
        }



        scan.close();
    }
    //assembles map with the read csv file lines
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

