package service;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.*;



public class CsvManager {

    private final String FILE_PATH =
            "data/vehicles.csv";


    // ===============================
    // Load vehicles
    // ===============================


    public ArrayList<Vehicle> loadVehicles(
            String filePath
    )
            throws Exception {


        ArrayList<Vehicle> vehicles =
                new ArrayList<>();


        BufferedReader reader =

                new BufferedReader(

                        new FileReader(filePath)

                );


        String line;


        // Skip header

        reader.readLine();


        while(
                (line = reader.readLine()) != null
        ) {

            if(line.trim().isEmpty()) {

                continue;

            }


            String[] data =

                    line.split(",");


            String id =
                    data[0];



            String type =
                    data[1];



            String brand =
                    data[2];



            String model =
                    data[3];



            int year =

                    Integer.parseInt(
                            data[4]
                    );



            double mileage =

                    Double.parseDouble(
                            data[5]
                    );




            boolean rented =

                    Boolean.parseBoolean(
                            data[6]
                    );



            Vehicle vehicle = null;


            switch(type) {


                case "Car":


                    vehicle =
                            new Car(

                                    id,

                                    brand,

                                    model,

                                    year,

                                    mileage,

                                    Integer.parseInt(
                                            data[7]
                                    )

                            );


                    break;


                case "SUV":


                    vehicle =
                            new SUV(

                                    id,

                                    brand,

                                    model,

                                    year,

                                    mileage,

                                    Boolean.parseBoolean(
                                            data[7]
                                    )

                            );


                    break;


                case "Truck":


                    vehicle =
                            new Truck(

                                    id,

                                    brand,

                                    model,

                                    year,

                                    mileage,

                                    Double.parseDouble(
                                            data[7]
                                    )

                            );


                    break;



            }


            if(vehicle != null) {



                // Restaurer état location


                vehicle.setAvailable(
                        !rented
                );



                vehicles.add(
                        vehicle
                );


            }



        }


        reader.close();


        return vehicles;


    }


    // ===============================
    // Save complete CSV
    // ===============================


    public void saveAllVehicles(
            String filePath,
            ArrayList<Vehicle> vehicles
    )
            throws Exception {


        PrintWriter writer =

                new PrintWriter(

                        new FileWriter(
                                filePath,
                                false
                        )

                );



        // Header


        writer.println(

                "id,type,brand,model,year,mileage,rented,extra"

        );


        for(Vehicle vehicle : vehicles) {



            String extra = "";


            if(vehicle instanceof Car) {


                Car car =
                        (Car) vehicle;


                extra =
                        String.valueOf(
                                car.getNumberOfDoors()
                        );

            }


            else if(vehicle instanceof SUV) {


                SUV suv =
                        (SUV) vehicle;


                extra =
                        String.valueOf(
                                suv.hasFourWheelDrive()
                        );



            }


            else if(vehicle instanceof Truck) {


                Truck truck =
                        (Truck) vehicle;


                extra =
                        String.valueOf(
                                truck.getLoadCapacity()
                        );

            }


            writer.println(

                    vehicle.getId()
                    + ","
                    + vehicle.getVehicleType()
                    + ","
                    + vehicle.getBrand()
                    + ","
                    + vehicle.getModel()
                    + ","
                    + vehicle.getYear()
                    + ","
                    + vehicle.getMileage()
                    + ","
                    + !vehicle.isAvailable()
                    + ","
                    + extra

            );



        }


        writer.close();



    }

    // ===============================
    // Update rental status
    // ===============================


    public void updateVehicleStatus(
            String filePath,
            ArrayList<Vehicle> vehicles
    )
            throws Exception {



        saveAllVehicles(

                filePath,

                vehicles

        );


    }




}