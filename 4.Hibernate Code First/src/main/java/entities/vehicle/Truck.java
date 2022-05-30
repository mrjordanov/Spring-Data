package entities.vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("truck")
public class Truck extends TransportationVehicle {

    private final static String TRUCK_TYPE = "Truck";

    public Truck(double price, int loadCapacity) {
        super(TRUCK_TYPE, price, loadCapacity);
    }

    public Truck() {

    }
}
