package entities.vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("car")
public class Car extends Vehicle {

    private static final String CAR_TYPE = "Car";

    private int doorCount;

    public Car(int doorCount) {
        super(CAR_TYPE, 1000);
        this.doorCount = doorCount;
    }

    public Car() {
    }
}
