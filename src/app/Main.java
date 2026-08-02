package app;


import exceptions.VehicleNotAvailableException;
import java.util.ArrayList;
import model.Vehicle;
import service.CsvManager;
import service.FleetManager;
import service.MaintenanceManager;
import service.RentalManager;
import service.ReportManager;
import service.StatisticsManager;



public class Main {


    public static void main(String[] args) {


        System.out.println(
                "===== VEHICLE FLEET MANAGEMENT SYSTEM ====="
        );



        // ===============================
        // LOAD VEHICLES FROM CSV
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
        catch(Exception e) {


            System.out.println(
                    "CSV Loading Error : "
                    + e.getMessage()
            );


        }




        // ===============================
        // RENTAL MANAGEMENT
        // ===============================


        System.out.println(
                "\n===== RENTAL MANAGEMENT ====="
        );



        RentalManager rentalManager =
                new RentalManager();




        Vehicle vehicle1 =
                fleetManager.findVehicleById("V001");



        Vehicle vehicle2 =
                fleetManager.findVehicleById("V002");






        // ===============================
        // RENT FIRST VEHICLE
        // ===============================


        if(vehicle1 != null) {


            try {


                int days = 5;



                rentalManager.rentVehicle(
                        vehicle1,
                        days
                );



                double price =
                        rentalManager.calculateRentalCost(
                                vehicle1,
                                days
                        );



                System.out.println(

                        vehicle1.getBrand()
                        + " "
                        + vehicle1.getModel()
                        + " rented for "
                        + days
                        + " days : "
                        + price
                        + "$"

                );



                rentalManager.returnVehicle(
                        vehicle1
                );



                // Second rental for statistics test

                rentalManager.rentVehicle(
                        vehicle1,
                        2
                );



                double secondPrice =
                        rentalManager.calculateRentalCost(
                                vehicle1,
                                2
                        );



                System.out.println(

                        vehicle1.getBrand()
                        + " "
                        + vehicle1.getModel()
                        + " rented again for 2 days : "
                        + secondPrice
                        + "$"

                );



                rentalManager.returnVehicle(
                        vehicle1
                );



            }
            catch(VehicleNotAvailableException e) {


                System.out.println(
                        e.getMessage()
                );


            }


        }








        // ===============================
        // RENT SECOND VEHICLE
        // ===============================


        if(vehicle2 != null) {


            try {


                int days = 3;



                rentalManager.rentVehicle(
                        vehicle2,
                        days
                );



                double price =
                        rentalManager.calculateRentalCost(
                                vehicle2,
                                days
                        );



                System.out.println(

                        vehicle2.getBrand()
                        + " "
                        + vehicle2.getModel()
                        + " rented for "
                        + days
                        + " days : "
                        + price
                        + "$"

                );



                rentalManager.returnVehicle(
                        vehicle2
                );



            }
            catch(VehicleNotAvailableException e) {


                System.out.println(
                        e.getMessage()
                );


            }


        }

                // ===============================
        // MAINTENANCE MANAGEMENT
        // ===============================


        System.out.println(
                "\n===== MAINTENANCE MANAGEMENT ====="
        );



        MaintenanceManager maintenanceManager =
                new MaintenanceManager();




        for(Vehicle vehicle :
                fleetManager.getVehicles()) {



            if(vehicle.needsMaintenance()) {


                maintenanceManager.reportMaintenance(
                        vehicle
                );


            }


        }



        maintenanceManager.displayMaintenanceList();



        System.out.println(

                "Vehicles requiring maintenance : "
                + maintenanceManager.getMaintenanceCount()

        );







        // ===============================
        // STATISTICS
        // ===============================


        System.out.println(
                "\n===== STATISTICS ====="
        );



        StatisticsManager statisticsManager =
                new StatisticsManager();





        System.out.println(

                "Total vehicles : "
                + statisticsManager.getTotalVehicles(
                        fleetManager.getVehicles()
                )

        );



        System.out.println(

                "Available vehicles : "
                + statisticsManager.getAvailableVehicles(
                        fleetManager.getVehicles()
                )

        );



        System.out.println(

                "Rented vehicles : "
                + statisticsManager.getRentedVehicles(
                        fleetManager.getVehicles()
                )

        );



        System.out.println(

                "Average mileage : "
                + statisticsManager.calculateAverageMileage(
                        fleetManager.getVehicles()
                )

        );



        System.out.println(

                "Total revenue : "
                + statisticsManager.calculateTotalRevenue(
                        fleetManager.getVehicles()
                )
                + "$"

        );



        System.out.println(

                "Vehicles by type : "
                + statisticsManager.countByType(
                        fleetManager.getVehicles()
                )

        );






        // ===============================
        // MOST RENTED VEHICLE
        // ===============================


        Vehicle mostUsed =
                statisticsManager.getMostRentedVehicle(
                        fleetManager.getVehicles()
                );



        if(mostUsed != null) {


            System.out.println(

                    "Most rented vehicle : "
                    + mostUsed.getId()
                    + " - "
                    + mostUsed.getBrand()
                    + " "
                    + mostUsed.getModel()
                    + " ("
                    + mostUsed.getRentalCount()
                    + " rentals)"

            );


        }






        // ===============================
        // VEHICLES NEEDING MAINTENANCE
        // ===============================


        System.out.println(
                "\nVehicles needing maintenance :"
        );



        for(Vehicle vehicle :
                statisticsManager.vehiclesNeedingMaintenance(
                        fleetManager.getVehicles()
                )) {


            System.out.println(
                    vehicle
            );


        }








        // ===============================
        // REPORT GENERATION
        // ===============================


        System.out.println(
                "\n===== REPORT GENERATION ====="
        );



        ReportManager reportManager =
                new ReportManager();




        reportManager.generateReport(

                fleetManager.getVehicles(),

                statisticsManager,

                "reports/fleet_report.txt"

        );



        System.out.println(
                "Application finished successfully."
        );


    }

}