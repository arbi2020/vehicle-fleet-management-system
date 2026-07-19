package service;

import java.util.ArrayList;
import model.Vehicle;

public class FleetManager {

    private ArrayList<Vehicle> vehicles;


    public FleetManager() {

        vehicles = new ArrayList<>();

    }


    // Ajouter un véhicule

    public void addVehicle(Vehicle vehicle) {

        vehicles.add(vehicle);

    }


    // Supprimer un véhicule

    public boolean removeVehicle(String id) {

        Vehicle vehicle = findVehicleById(id);

        if (vehicle != null) {

            vehicles.remove(vehicle);
            return true;
        }

        return false;
    }


    // Rechercher un véhicule

    public Vehicle findVehicleById(String id) {


        for (Vehicle vehicle : vehicles) {

            if (vehicle.getId().equals(id)) {

                return vehicle;
            }
        }

        return null;
    }


    // Afficher tous les véhicules

    public void displayVehicles() {


        for (Vehicle vehicle : vehicles) {

            System.out.println(vehicle);

        }

    }


    // Retourner la liste

    public ArrayList<Vehicle> getVehicles() {

        return vehicles;

    }


    // Nombre total de véhicules

    public int getVehicleCount() {

        return vehicles.size();

    }

    public void loadFleet(ArrayList<Vehicle> vehicles) {

    this.vehicles.addAll(vehicles);

    }
}