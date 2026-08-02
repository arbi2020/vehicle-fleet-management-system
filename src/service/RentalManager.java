package service;

import exceptions.VehicleNotAvailableException;
import model.Vehicle;

public class RentalManager {


    public void rentVehicle(Vehicle vehicle, int days)
        throws VehicleNotAvailableException {


        if (!vehicle.isAvailable()) {

            throw new VehicleNotAvailableException(
                "Vehicle " + vehicle.getId()
                + " is already rented."
            );
        }


        vehicle.rent();

        // Mise à jour des statistiques
        vehicle.registerRental(days);

        System.out.println(
            "Vehicle " + vehicle.getId()
            + " rented successfully."
        );
    }



    public void returnVehicle(Vehicle vehicle) {

        vehicle.returnVehicle();

        System.out.println(
                "Vehicle " + vehicle.getId()
                + " returned successfully."
        );
    }



    public double calculateRentalCost(
            Vehicle vehicle,
            int days) {


        return vehicle.calculateRentalCost(days);

    }
}