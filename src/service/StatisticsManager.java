package service;

import model.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;


public class StatisticsManager {


    // Calcul du kilométrage moyen

    public double calculateAverageMileage(
            ArrayList<Vehicle> vehicles) {


        if (vehicles.isEmpty()) {

            return 0;

        }


        double totalMileage = 0;


        for (Vehicle vehicle : vehicles) {

            totalMileage += vehicle.getMileage();

        }


        return totalMileage / vehicles.size();

    }



    // Calcul du revenu total estimé

    public double calculateTotalRevenue(
            ArrayList<Vehicle> vehicles,
            int days) {


        double revenue = 0;


        for (Vehicle vehicle : vehicles) {


            revenue +=
                    vehicle.calculateRentalCost(days);

        }


        return revenue;

    }



    // Nombre de véhicules par type

    public HashMap<String, Integer> countByType(
            ArrayList<Vehicle> vehicles) {


        HashMap<String, Integer> statistics =
                new HashMap<>();


        for (Vehicle vehicle : vehicles) {


            String type =
                    vehicle.getVehicleType();


            statistics.put(
                    type,
                    statistics.getOrDefault(type, 0) + 1
            );

        }


        return statistics;

    }



    // Véhicules nécessitant une maintenance

    public ArrayList<Vehicle> vehiclesNeedingMaintenance(
            ArrayList<Vehicle> vehicles) {


        ArrayList<Vehicle> result =
                new ArrayList<>();


        for (Vehicle vehicle : vehicles) {


            if (vehicle.needsMaintenance()) {


                result.add(vehicle);

            }

        }


        return result;

    }

}