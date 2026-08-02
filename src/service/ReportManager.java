package service;


import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Vehicle;



public class ReportManager {


    public void generateReport(

            ArrayList<Vehicle> vehicles,

            StatisticsManager statisticsManager,

            String filePath

    ) {



        try {


            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(filePath)
                    );



            // ===============================
            // HEADER
            // ===============================


            writer.println(
                    "======================================"
            );

            writer.println(
                    " VEHICLE FLEET MANAGEMENT REPORT"
            );

            writer.println(
                    "======================================"
            );


            writer.println();



            String date =
                    LocalDateTime.now()
                    .format(
                            DateTimeFormatter.ofPattern(
                                    "yyyy-MM-dd HH:mm:ss"
                            )
                    );



            writer.println(
                    "Generated date : "
                    + date
            );



            writer.println();





            // ===============================
            // GENERAL STATISTICS
            // ===============================


            writer.println(
                    "========== GENERAL STATISTICS =========="
            );


            writer.println(
                    "Total vehicles : "
                    + statisticsManager.getTotalVehicles(
                            vehicles
                    )
            );


            writer.println(
                    "Available vehicles : "
                    + statisticsManager.getAvailableVehicles(
                            vehicles
                    )
            );



            writer.println(
                    "Rented vehicles : "
                    + statisticsManager.getRentedVehicles(
                            vehicles
                    )
            );



            writer.println(
                    "Average mileage : "
                    + statisticsManager.calculateAverageMileage(
                            vehicles
                    )
                    + " km"
            );



            writer.println(
                    "Total revenue : "
                    + statisticsManager.calculateTotalRevenue(
                            vehicles
                    )
                    + " $"
            );



            writer.println();





            // ===============================
            // VEHICLES BY TYPE
            // ===============================


            writer.println(
                    "========== VEHICLES BY TYPE =========="
            );


            writer.println(

                    statisticsManager.countByType(
                            vehicles
                    )

            );



            writer.println();





            // ===============================
            // MOST RENTED VEHICLE
            // ===============================


            writer.println(
                    "========== MOST RENTED VEHICLE =========="
            );



            Vehicle mostUsed =
                    statisticsManager.getMostRentedVehicle(
                            vehicles
                    );



            if(mostUsed != null) {


                writer.println(

                        mostUsed.getId()
                        + " - "
                        + mostUsed.getBrand()
                        + " "
                        + mostUsed.getModel()
                        + " | Rentals : "
                        + mostUsed.getRentalCount()

                );


            }
            else {


                writer.println(
                        "No rental history available."
                );


            }



            writer.println();






            // ===============================
            // MAINTENANCE
            // ===============================


            writer.println(
                    "========== MAINTENANCE REQUIRED =========="
            );



            ArrayList<Vehicle> maintenanceList =
                    statisticsManager
                    .vehiclesNeedingMaintenance(
                            vehicles
                    );



            if(maintenanceList.isEmpty()) {


                writer.println(
                        "No vehicle requires maintenance."
                );


            }
            else {


                for(Vehicle vehicle :
                        maintenanceList) {


                    writer.println(
                            vehicle
                    );


                }


            }



            writer.println();


            writer.println(
                    "======================================"
            );


            writer.println(
                    "END OF REPORT"
            );


            writer.println(
                    "======================================"
            );



            writer.close();



            System.out.println(
                    "Report generated successfully."
            );



        }
        catch(Exception e) {


            System.out.println(
                    "Report generation error : "
                    + e.getMessage()
            );


        }


    }


}