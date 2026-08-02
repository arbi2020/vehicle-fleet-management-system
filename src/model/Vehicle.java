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

    // Rental statistics
    private int rentalCount;
    private double totalRevenue;

    public Vehicle(
            String id,
            String brand,
            String model,
            int year,
            double mileage
    ) {


        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.available = true;
        this.rentalCount = 0;
        this.totalRevenue = 0.0;

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


    public int getYear() {

        return year;

    }


    public double getMileage() {

        return mileage;

    }


    public boolean isAvailable() {

        return available;

    }

    public int getRentalCount() {
        return rentalCount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    // ===============================
    // Location
    // ===============================


    @Override
    public void rent() {

        available = false;

    }


    @Override
    public void returnVehicle() {

        available = true;

    }

    // ===============================
    // Restaurer état depuis CSV
    // ===============================

    public void setAvailable(boolean available) {

        this.available = available;

    }

    public void addMileage(double distance) {

        mileage += distance;

    }

    // ===============================
    // Maintenance
    // ===============================


    @Override
    public void performMaintenance() {

        System.out.println(
                "Maintenance completed for vehicle "
                + id
        );


    }

    @Override
    public boolean needsMaintenance() {

        return mileage > 50000;

    }

    public abstract String getVehicleType();

    public abstract double calculateRentalCost(
            int days
    );


    @Override
    public String toString() {

        return

        "ID: " + id +

        ", Type: " + getVehicleType() +

        ", Brand: " + brand +

        ", Model: " + model +

        ", Year: " + year +

        ", Mileage: " + mileage +

        ", Available: " + available;

    }

    public void registerRental(int days) {
        rentalCount++;

        totalRevenue += calculateRentalCost(days);

    }

}