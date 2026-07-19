package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import model.*;

public class CsvManager {


    public ArrayList<Vehicle> loadVehicles(String filePath)
            throws Exception {


        ArrayList<Vehicle> vehicles =
                new ArrayList<>();


        BufferedReader reader =
                new BufferedReader(
                        new FileReader(filePath)
                );


        String line;

        reader.readLine(); // ignorer header


        while ((line = reader.readLine()) != null) {


            String[] data = line.split(",");


            String id = data[0];
            String type = data[1];
            String brand = data[2];
            String model = data[3];

            int year =
                    Integer.parseInt(data[4]);

            double mileage =
                    Double.parseDouble(data[5]);


            if (mileage < 0) {
                System.out.println("Invalid mileage for vehicle " + id);

                continue;
            }


            Vehicle vehicle = null;


            switch(type) {

                case "Car":

                    vehicle = new Car(
                            id,
                            brand,
                            model,
                            year,
                            mileage,
                            Integer.parseInt(data[7])
                    );

                    break;


                case "SUV":

                    vehicle = new SUV(
                            id,
                            brand,
                            model,
                            year,
                            mileage,
                            Boolean.parseBoolean(data[7])
                    );

                    break;


                case "Truck":

                    vehicle = new Truck(
                            id,
                            brand,
                            model,
                            year,
                            mileage,
                            Double.parseDouble(data[7])
                    );

                    break;
            }


            if(vehicle != null) {

                vehicles.add(vehicle);

            }

        }


        reader.close();


        return vehicles;
    }
}