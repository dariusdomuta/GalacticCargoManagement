package domutadarius.galactic.cargo.management.Main;

import domutadarius.galactic.cargo.management.Pojos.GalacticCharacter;
import domutadarius.galactic.cargo.management.Pojos.Planet;
import domutadarius.galactic.cargo.management.Pojos.Result;
import domutadarius.galactic.cargo.management.Pojos.Ship;
import domutadarius.galactic.cargo.management.Utils.CustomComparator;
import domutadarius.galactic.cargo.management.Utils.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String [] args) {
        //we create an instance of the class utils in order to use the functions for reading the JSON input
        Utils utils = new Utils();

        String jsonString;

        //reads the characters.json file and extracts the characters by parsing the JSON data
        jsonString = utils.getJSONString("characters.json");
        List<GalacticCharacter> characters = new ArrayList<GalacticCharacter>();
        characters = utils.extractGalacticCharactersFromJson(jsonString);

        //reads the ships.json file and extracts the ships by parsing the JSON data
        jsonString = utils.getJSONString("ships.json");
        List<Ship> ships = new ArrayList<Ship>();
        ships = utils.extractShipsFromJson(jsonString);

        //reads the planets.json file and extracts the planets by parsing the JSON data
        jsonString = utils.getJSONString("planets.json");
        List<Planet> planets = new ArrayList<Planet>();
        planets = utils.extractPlanetFromJson(jsonString);

        //data is being collected from the user input
        //the user must select the character, cargo size and the planet he wants to ship to
        System.out.println("List of characters:");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(i+1 + ". " + characters.get(i).getName());
        }
        System.out.println("\n");
        System.out.println("Choose the number of the preffered character:");

        Scanner sc = new Scanner(System.in);
        int charNumber = sc.nextInt()-1;

        System.out.println("List of planets:");
        for (int i = 0; i < planets.size(); i++) {
            System.out.println(i+1 + ". " + planets.get(i).getName());
        }
        System.out.println("\n");
        System.out.println("Choose the destination planet of the cargo:");
        int planetNumber = sc.nextInt()-1;
        int distanceToCurrentPlanet = planets.get(planetNumber).getDistance();

        System.out.println("Input the cargo weight:");
        int cargoWeight = sc.nextInt();
        System.out.println("\n");

        //we create a list of results in order to store them and SORT them
        //we are interested in sorting according to the delivery time but we also save
        //the number of trips and the ship name
        List<Result> results = new ArrayList<>();

        DecimalFormat numberFormat = new DecimalFormat("#.00");
        int numberOfTrips = 0;
        double deliveryTime = 0;

        //we take a look through all the ships in order to select the ships the chosen character can drive
        for (int i = 0; i < ships.size(); i++) {
            String currentShipType = ships.get(i).getType();
            if (characters.get(charNumber).getShipsType().contains(currentShipType)) {
                //if the condition is true it means that the chosen character can drive that kind of ship
                int currentShipSpeed = ships.get(i).getSpeed();

                //compute the number of trips rounded to the smallest integer closest to the calculated ratio
                double doubleNumberOfTrips = (double) cargoWeight/ships.get(i).getMaxCargoWeight();
                numberOfTrips = (int) cargoWeight/ships.get(i).getMaxCargoWeight();
                if (doubleNumberOfTrips > (double) numberOfTrips) {
                    numberOfTrips++;
                }

                //compute the delivery time which is doubled since the ship has to deliver the cargo and come back
                //to the start point for every trip
                deliveryTime = (double) 2 * numberOfTrips * distanceToCurrentPlanet / currentShipSpeed;


                //since we've computed the results we create a new result and add it to the list
                Result result = new Result(ships.get(i).getName(), numberOfTrips, deliveryTime);
                results.add(result);

            }
        }

        //the results are sorted by the delivery time
        Collections.sort(results, new CustomComparator());

        System.out.println("Avialable shippments: \n \n");
        for (int i = 0; i < results.size(); i++) {

            System.out.println("Shipment summary:");
            System.out.println("Character: " + characters.get(charNumber).getName());
            System.out.println("Ship: " + results.get(i).getShipName());
            System.out.println("Cargo weight: " + cargoWeight + "KG");
            System.out.println("Destination planet: " + planets.get(planetNumber).getName());
            System.out.println("Number of trips: " + results.get(i).getTrips());
            System.out.println("Delivery time: " + numberFormat.format(results.get(i).getDeliveryTime()));
            System.out.println("\n \n");
        }
    }
}
