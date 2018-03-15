package domutadarius.galactic.cargo.management.Pojos;

public class Planet {

    int id;
    String name;
    int distance;

    public Planet() {

    }
    public Planet(int id, String name, int distance) {
        this.id = id;
        this.name = name;
        this.distance = distance;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
