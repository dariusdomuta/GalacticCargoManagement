package domutadarius.galactic.cargo.management.Pojos;

import java.util.ArrayList;

public class GalacticCharacter {

    private int id;
    private String name;
    private ArrayList<String> shipsType;

    public GalacticCharacter() {

    }

    public GalacticCharacter(int id, String name, ArrayList<String> shipsType) {
        this.id = id;
        this.name = name;
        this.shipsType = shipsType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getShipsType() {
        return shipsType;
    }

    public void setShipsType(ArrayList<String> shipsType) {
        this.shipsType = shipsType;
    }
}
