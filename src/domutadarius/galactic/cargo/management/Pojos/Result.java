package domutadarius.galactic.cargo.management.Pojos;

public class Result{
    String shipName;
    int trips;
    double deliveryTime;

    public Result(String shipName, int trips, double deliveryTime) {
        this.shipName = shipName;
        this.trips = trips;
        this.deliveryTime = deliveryTime;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getTrips() {
        return trips;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }

    public double getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(double deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
