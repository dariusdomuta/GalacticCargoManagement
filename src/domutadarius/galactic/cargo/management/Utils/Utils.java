package domutadarius.galactic.cargo.management.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domutadarius.galactic.cargo.management.Pojos.GalacticCharacter;
import domutadarius.galactic.cargo.management.Pojos.Planet;
import domutadarius.galactic.cargo.management.Pojos.Ship;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
    //read the json file and put its content into a string
    public String getJSONString(String fileName){

        String line = "";
        String jsonString = "";

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                jsonString+=line+'\n';
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch (IOException ex) {
            System.out.println( "Error reading file '" + fileName +"'");
        }

        return jsonString;
    }

    //parse the planets.json data
    public static List<Planet> extractPlanetFromJson(String jsonString) {
        List<Planet> planets = new ArrayList<>();

        try {
            JSONArray planetsArray = new JSONArray(jsonString);

            for (int i = 0; i < planetsArray.length(); i++){
                JSONObject currentPlanet = planetsArray.getJSONObject(i);


                int id = currentPlanet.getInt("id");
                String name = currentPlanet.getString("name");
                int distance = currentPlanet.getInt("distance");

                Planet planet = new Planet(id, name, distance);
                planets.add(planet);
            }

        } catch (JSONException e) {
            System.out.println("Problem parsing the character JSON results");
        }
        return planets;
    }

    //parse the characters.json data
    public static List<GalacticCharacter> extractGalacticCharactersFromJson(String jsonString) {
        List<GalacticCharacter> galacticCharacters = new ArrayList<>();

        try {
            JSONArray galacticCharacterArray = new JSONArray(jsonString);

            for (int i = 0; i < galacticCharacterArray.length(); i++){
                JSONObject currentGalacticCharacter = galacticCharacterArray.getJSONObject(i);

                int id = currentGalacticCharacter.getInt("id");
                String name = currentGalacticCharacter.getString("name");
                ArrayList<String> shipsType = new ArrayList<>();

                JSONArray shipsTypeJsonArray = currentGalacticCharacter.getJSONArray("shipsType");

                for (int j = 0; j < shipsTypeJsonArray.length(); j++) {
                    String singleShipType = shipsTypeJsonArray.getString(j);
                    shipsType.add(singleShipType);
                }

                GalacticCharacter galacticCharacter = new GalacticCharacter(id, name, shipsType);
                galacticCharacters.add(galacticCharacter);
            }

        } catch (JSONException e) {
            System.out.println("Problem parsing the character JSON results");
        }
        return galacticCharacters;
    }

    //parse the ships.json data
    public static List<Ship> extractShipsFromJson(String jsonString) {
        List<Ship> ships = new ArrayList<>();

        try {
            JSONArray shipsArray = new JSONArray(jsonString);

            for (int i = 0; i < shipsArray.length(); i++){
                JSONObject currentShip = shipsArray.getJSONObject(i);


                int id = currentShip.getInt("id");
                String name = currentShip.getString("name");
                int speed = currentShip.getInt("speed");
                String type = currentShip.getString("type");
                int maxCargoWeight = currentShip.getInt("maxCargoWeight");

                Ship ship = new Ship(id, name, speed, type, maxCargoWeight);
                ships.add(ship);
            }

        } catch (JSONException e) {
            System.out.println("Problem parsing the character JSON results");
        }
        return ships;
    }
}

