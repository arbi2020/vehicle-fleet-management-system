package app;

import exceptions.VehicleNotAvailableException;
import java.util.ArrayList;
import model.Vehicle;
import service.CsvManager;
import service.FleetManager;
import service.MaintenanceManager;
import service.RentalManager;


public class Main {


    public static void main(String[] args) {


        System.out.println(
                "===== VEHICLE FLEET MANAGEMENT SYSTEM ====="
        );


        // ===============================
        // Load vehicles from CSV
        // ===============================


        CsvManager csvManager =
                new CsvManager();


        FleetManager fleetManager =
                new FleetManager();



        try {


            ArrayList<Vehicle> vehicles =
                    csvManager.loadVehicles(
                            "data/vehicles.csv"
                    );


            fleetManager.loadFleet(
                    vehicles
            );


            System.out.println(
                    "\nVehicles loaded : "
                    + fleetManager.getVehicleCount()
            );



            fleetManager.displayVehicles();



        }
        catch (Exception e) {


            System.out.println(
                    "CSV Loading Error : "
                    + e.getMessage()
            );

        }



        // ===============================
        // Rental management
        // ===============================


        System.out.println(
                "\n===== RENTAL MANAGEMENT ====="
        );


        RentalManager rentalManager =
                new RentalManager();



        Vehicle vehicle =
                fleetManager.findVehicleById("V001");



        if (vehicle != null) {


            try {


                rentalManager.rentVehicle(
                        vehicle
                );


                double price =
                        rentalManager.calculateRentalCost(
                                vehicle,
                                5
                        );


                System.out.println(
                        "Rental price : "
                        + price
                        + "$"
                );


                rentalManager.returnVehicle(
                        vehicle
                );


            }
            catch (VehicleNotAvailableException e) {


                System.out.println(
                        e.getMessage()
                );

            }

        }



        // ===============================
        // Maintenance management
        // ===============================


        System.out.println(
                "\n===== MAINTENANCE MANAGEMENT ====="
        );


        MaintenanceManager maintenanceManager =
                new MaintenanceManager();



        for (Vehicle v :
                fleetManager.getVehicles()) {


            if (v.needsMaintenance()) {


                maintenanceManager.reportMaintenance(
                        v
                );

            }

        }


        maintenanceManager.displayMaintenanceList();


        System.out.println(
                "Vehicles requiring maintenance : "
                + maintenanceManager.getMaintenanceCount()
        );

    }

}