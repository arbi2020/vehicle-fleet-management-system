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






    // ==================================
    // Find vehicle by ID
    // ==================================


    public Vehicle findVehicle(String id) {



        for(Vehicle vehicle :
                fleetManager.getVehicles()) {



            if(vehicle.getId().equals(id)) {


                return vehicle;


            }


        }



        return null;


    }






    // ==================================
    // Rent vehicle
    // ==================================


    public boolean rentVehicle(String id) {



        Vehicle vehicle =
                findVehicle(id);




        if(vehicle != null
                && vehicle.isAvailable()) {



            vehicle.rent();


            return true;


        }



        return false;


    }






    // ==================================
    // Return vehicle
    // ==================================


    public boolean returnVehicle(String id) {



        Vehicle vehicle =
                findVehicle(id);




        if(vehicle != null
                && !vehicle.isAvailable()) {



            vehicle.returnVehicle();


            return true;


        }



        return false;


    }



}