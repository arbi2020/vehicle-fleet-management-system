package model;

public class SUV extends Vehicle {

    private boolean fourWheelDrive;


    public SUV(String id,
               String brand,
               String model,
               int year,
               double mileage,
               boolean fourWheelDrive) {

        super(id, brand, model, year, mileage);

        this.fourWheelDrive = fourWheelDrive;
    }


    public boolean hasFourWheelDrive() {
        return fourWheelDrive;
    }


    @Override
    public String getVehicleType() {
        return "SUV";
    }


    @Override
    public double calculateRentalCost(int days) {

        return days * 80.0;
    }


    @Override
    public String toString() {

        return super.toString()
                + ", 4WD: " + fourWheelDrive;
    }
}