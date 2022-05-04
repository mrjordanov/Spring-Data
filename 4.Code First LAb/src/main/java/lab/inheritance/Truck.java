package lab.inheritance;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "trucks")
public class Truck extends Vehicle {

    private double loadCapacity;

    public Truck() {
    }

    public Truck(String type, String model, double price, String fuelType, double loadCapacity) {
        super(type, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
