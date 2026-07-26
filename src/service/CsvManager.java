package service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import model.*;



public class CsvManager {



    // ===============================
    // Load vehicles from CSV
    // ===============================


    public ArrayList<Vehicle> loadVehicles(String filePath)
            throws Exception {



        ArrayList<Vehicle> vehicles =
                new ArrayList<>();



        BufferedReader reader =
                new BufferedReader(
                        new FileReader(filePath)
                );



        String line;


        // Ignore header
        reader.readLine();




        while ((line = reader.readLine()) != null) {



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



            if(mileage < 0) {


                System.out.println(
                        "Invalid mileage for vehicle "
                        + id
                );


                continue;

            }




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


                vehicles.add(vehicle);


            }



        }



        reader.close();



        return vehicles;



    }







    // ===============================
    // Save one vehicle in CSV
    // ===============================


    public void saveVehicle(
            String filePath,
            Vehicle vehicle
    )
            throws Exception {



        File file =
                new File(filePath);




        FileWriter fileWriter =
                new FileWriter(
                        file,
                        true
                );



        BufferedWriter writer =
                new BufferedWriter(
                        fileWriter
                );





        // Add new line before new vehicle
        // if file already contains data

        if(file.length() > 0) {


            writer.newLine();


        }






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








        writer.write(

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
                + vehicle.isAvailable()
                + ","
                + extra

        );




        writer.close();



    }



}