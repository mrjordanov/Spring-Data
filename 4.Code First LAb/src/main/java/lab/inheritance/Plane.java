package lab.inheritance;

import javax.persistence.Entity;


@Entity(name = "planes")
public class Plane extends Vehicle {

    private int passengerCapacity;

    public Plane() {
    }

    public Plane(String type, String model, double price, String fuelType, int passengerCapacity) {
        super(type, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
