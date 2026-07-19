package app;

import model.Car;
import model.SUV;
import model.Truck;
import model.Vehicle;

public class Main {

    public static void main(String[] args) {


        // Création des véhicules

        Vehicle car = new Car(
                "C001",
                "Toyota",
                "Corolla",
                2022,
                15000,
                4
        );


        Vehicle suv = new SUV(
                "S001",
                "BMW",
                "X5",
                2021,
                35000,
                true
        );


        Vehicle truck = new Truck(
                "T001",
                "Ford",
                "F150",
                2020,
                70000,
                2.5
        );


        // Affichage initial

        System.out.println("===== VEHICLES =====");

        System.out.println(car);
        System.out.println(suv);
        System.out.println(truck);


        // Test location

        System.out.println("\n===== RENTAL TEST =====");

        car.rent();

        System.out.println(
                "Car available : " + car.isAvailable()
        );


        double cost = car.calculateRentalCost(5);

        System.out.println(
                "Rental cost for 5 days : " + cost + "$"
        );


        // Retour du véhicule

        car.returnVehicle();

        System.out.println(
                "Car available after return : "
                + car.isAvailable()
        );


        // Test maintenance

        System.out.println("\n===== MAINTENANCE TEST =====");


        System.out.println(
                "Truck needs maintenance : "
                + truck.needsMaintenance()
        );


        truck.performMaintenance();

    }
}