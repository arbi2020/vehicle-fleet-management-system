package service;

import model.Vehicle;

import java.util.ArrayList;

public class MaintenanceManager {


    private ArrayList<Vehicle> maintenanceList;


    public MaintenanceManager() {

        maintenanceList = new ArrayList<>();

    }


    // Ajouter un véhicule à la liste de maintenance

    public void reportMaintenance(Vehicle vehicle) {

        maintenanceList.add(vehicle);

        System.out.println(
                "Maintenance reported for vehicle "
                + vehicle.getId()
        );

    }



    // Effectuer la maintenance

    public void performMaintenance(Vehicle vehicle) {

        vehicle.performMaintenance();

        maintenanceList.remove(vehicle);

        System.out.println(
                "Vehicle "
                + vehicle.getId()
                + " removed from maintenance list."
        );

    }



    // Afficher les véhicules en maintenance

    public void displayMaintenanceList() {


        System.out.println(
                "===== MAINTENANCE LIST ====="
        );


        for (Vehicle vehicle : maintenanceList) {

            System.out.println(vehicle);

        }

    }



    // Nombre de véhicules nécessitant une maintenance

    public int getMaintenanceCount() {

        return maintenanceList.size();

    }

}