package model;

public class Truck extends Vehicle {

    private double loadCapacity;


    public Truck(String id,
                 String brand,
                 String model,
                 int year,
                 double mileage,
                 double loadCapacity) {

        super(id, brand, model, year, mileage);

        this.loadCapacity = loadCapacity;
    }


    public double getLoadCapacity() {
        return loadCapacity;
    }


    @Override
    public String getVehicleType() {
        return "Truck";
    }


    @Override
    public double calculateRentalCost(int days) {

        return days * 120.0;
    }


    @Override
    public String toString() {

        return super.toString()
                + ", Load Capacity: " + loadCapacity + " tons";
    }
}