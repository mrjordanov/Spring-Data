package lab.inheritance;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
public class Car extends Vehicle {

    private int seats;

    public Car() {
    }

    public Car(String type, String model, double price, String fuelType, int seats) {
        super(type, model, price, fuelType);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
