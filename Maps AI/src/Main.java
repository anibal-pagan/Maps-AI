import jdk.internal.cmm.SystemResourcePressureImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the path of the csv file containing the map");

        String csvFile = scan.nextLine();
        HashMap<String, Location> map = new HashMap<>();

        readFile(csvFile, map);

        System.out.println(map.size());

        for(String area: map.keySet()){

            System.out.println(area +" -> "+ map.get(area).getNeighborsString());
        }



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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
