package service;


import java.util.ArrayList;
import java.util.HashMap;
import model.Vehicle;



public class StatisticsManager {



    // ===============================
    // Total vehicles
    // ===============================


    public int getTotalVehicles(
            ArrayList<Vehicle> vehicles) {


        return vehicles.size();

    }






    // ===============================
    // Available vehicles
    // ===============================


    public int getAvailableVehicles(
            ArrayList<Vehicle> vehicles) {


        int count = 0;



        for(Vehicle vehicle : vehicles) {


            if(vehicle.isAvailable()) {


                count++;

            }

        }


        return count;

    }







    // ===============================
    // Rented vehicles
    // ===============================


    public int getRentedVehicles(
            ArrayList<Vehicle> vehicles) {


        int count = 0;



        for(Vehicle vehicle : vehicles) {


            if(!vehicle.isAvailable()) {


                count++;

            }

        }


        return count;

    }








    // ===============================
    // Average mileage
    // ===============================


    public double calculateAverageMileage(
            ArrayList<Vehicle> vehicles) {



        if(vehicles.isEmpty()) {


            return 0;

        }



        double totalMileage = 0;



        for(Vehicle vehicle : vehicles) {


            totalMileage +=
                    vehicle.getMileage();


        }



        return totalMileage / vehicles.size();


    }



    // ===============================
    // Total generated revenue
    // ===============================

    public double calculateTotalRevenue(
            ArrayList<Vehicle> vehicles) {


        double revenue = 0;


        for(Vehicle vehicle : vehicles) {


            revenue += vehicle.getTotalRevenue();


        }


        return revenue;

    }


    // ===============================
    // Count by type
    // ===============================


    public HashMap<String,Integer> countByType(
            ArrayList<Vehicle> vehicles) {



        HashMap<String,Integer> statistics =
                new HashMap<>();




        for(Vehicle vehicle : vehicles) {



            String type =
                    vehicle.getVehicleType();




            statistics.put(

                    type,

                    statistics.getOrDefault(
                            type,
                            0
                    ) + 1

            );


        }




        return statistics;


    }







    // ===============================
    // Maintenance vehicles
    // ===============================


    public ArrayList<Vehicle> vehiclesNeedingMaintenance(
            ArrayList<Vehicle> vehicles) {



        ArrayList<Vehicle> result =
                new ArrayList<>();




        for(Vehicle vehicle : vehicles) {



            if(vehicle.needsMaintenance()) {



                result.add(vehicle);


            }


        }




        return result;


    }


    // ===============================
    // Most rented vehicle
    // ===============================

    public Vehicle getMostRentedVehicle(
           ArrayList<Vehicle> vehicles) {


        if(vehicles.isEmpty()) {

            return null;

        }


        Vehicle best = vehicles.get(0);


        for(Vehicle vehicle : vehicles) {


            if(vehicle.getRentalCount() >
                best.getRentalCount()) {
                    best = vehicle;

                }

       }


        return best;

    }



}