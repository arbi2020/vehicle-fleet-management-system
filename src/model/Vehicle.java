package model;

import interfaces.Maintainable;
import interfaces.Rentable;

public abstract class Vehicle implements Rentable, Maintainable {

    protected String id;
    protected String brand;
    protected String model;
    protected int year;
    protected double mileage;
    protected boolean available;


    public Vehicle(String id, String brand, String model,
                   int year, double mileage) {

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.available = true;
    }


    public String getId() {
        return id;
    }


    public String getBrand() {
        return brand;
    }


    public String getModel() {
        return model;
    }


    public double getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }


    @Override
    public void rent() {
        available = false;
    }


    @Override
    public void returnVehicle() {
        available = true;
    }


    public void addMileage(double distance) {
        mileage += distance;
    }


    @Override
    public void performMaintenance() {
        System.out.println(
            "Maintenance completed for vehicle " + id
        );
    }


    @Override
    public boolean needsMaintenance() {
        return mileage > 50000;
    }


    public abstract String getVehicleType();


    public abstract double calculateRentalCost(int days);


    @Override
    public String toString() {

        return "ID: " + id +
                ", Type: " + getVehicleType() +
                ", Brand: " + brand +
                ", Model: " + model +
                ", Year: " + year +
                ", Mileage: " + mileage +
                ", Available: " + available;
    }
}