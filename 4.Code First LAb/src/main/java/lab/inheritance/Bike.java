package lab.inheritance;

import javax.persistence.Entity;


@Entity(name = "bikes")
public class Bike extends Vehicle {

    public Bike(String type, String model, double price, String fuelType) {
        super(type, model, price, fuelType);
    }

    public Bike() {
    }
}
