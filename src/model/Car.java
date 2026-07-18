package model;

public class Car extends Vehicle {

    private int numberOfDoors;


    public Car(String id,
               String brand,
               String model,
               int year,
               double mileage,
               int numberOfDoors) {

        super(id, brand, model, year, mileage);

        this.numberOfDoors = numberOfDoors;
    }


    public int getNumberOfDoors() {
        return numberOfDoors;
    }


    @Override
    public String getVehicleType() {
        return "Car";
    }


    @Override
    public double calculateRentalCost(int days) {

        return days * 50.0;
    }


    @Override
    public String toString() {

        return super.toString()
                + ", Doors: " + numberOfDoors;
    }
}
