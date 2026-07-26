package controller;



import java.util.ArrayList;
import model.Vehicle;
import service.CsvManager;
import service.FleetManager;




public class VehicleController {



    private FleetManager fleetManager;



    private final String CSV_FILE =
            "data/vehicles.csv";







    // ===============================
    // Constructor
    // ===============================


    public VehicleController() {



        fleetManager =
                new FleetManager();



        loadVehicles();



    }







    // ===============================
    // Load CSV
    // ===============================


    private void loadVehicles() {



        try {



            CsvManager csvManager =

                    new CsvManager();




            ArrayList<Vehicle> vehicles =

                    csvManager.loadVehicles(
                            CSV_FILE
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








    // ===============================
    // Get vehicles
    // ===============================


    public ArrayList<Vehicle> getVehicles() {


        return fleetManager.getVehicles();


    }








    // ===============================
    // Add vehicle
    // ===============================


    public void addVehicle(
            Vehicle vehicle
    ) {



        try {



            fleetManager.addVehicle(
                    vehicle
            );



            CsvManager csvManager =

                    new CsvManager();




            csvManager.saveAllVehicles(

                    CSV_FILE,

                    fleetManager.getVehicles()

            );



        }
        catch(Exception e) {



            System.out.println(

                    "Error adding vehicle : "

                    + e.getMessage()

            );



        }


    }








    // ===============================
    // Rent vehicle
    // ===============================


    public void rentVehicle(
            String id
    ) {



        Vehicle vehicle =

                fleetManager.findVehicleById(
                        id
                );





        if(vehicle != null) {



            vehicle.rent();




            saveChanges();



        }



    }









    // ===============================
    // Return vehicle
    // ===============================


    public void returnVehicle(
            String id
    ) {



        Vehicle vehicle =

                fleetManager.findVehicleById(
                        id
                );





        if(vehicle != null) {



            vehicle.returnVehicle();




            saveChanges();



        }



    }









    // ===============================
    // Save CSV
    // ===============================


    private void saveChanges() {



        try {



            CsvManager csvManager =

                    new CsvManager();




            csvManager.updateVehicleStatus(

                    CSV_FILE,

                    fleetManager.getVehicles()

            );



        }
        catch(Exception e) {



            System.out.println(

                    "Error saving changes : "

                    + e.getMessage()

            );



        }



    }





}