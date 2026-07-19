package controller;

import java.util.ArrayList;

import model.Vehicle;
import service.CsvManager;
import service.FleetManager;


public class VehicleController {


    private FleetManager fleetManager;


    public VehicleController() {

        fleetManager = new FleetManager();

        loadVehicles();

    }



    private void loadVehicles() {


        try {

            CsvManager csvManager =
                    new CsvManager();


            ArrayList<Vehicle> vehicles =
                    csvManager.loadVehicles(
                            "data/vehicles.csv"
                    );


            fleetManager.loadFleet(
                    vehicles
            );


        }
        catch(Exception e) {

            System.out.println(
                    "Error loading vehicles : "
                    + e.getMessage()
            );

        }

    }



    public ArrayList<Vehicle> getVehicles() {

        return fleetManager.getVehicles();

    }


}