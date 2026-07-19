package service;

import model.Vehicle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ReportManager {

    public void generateReport(
            ArrayList<Vehicle> vehicles,
            StatisticsManager statisticsManager,
            String filePath) {

        try (FileWriter writer = new FileWriter(filePath)) {

            writer.write("========== VEHICLE FLEET REPORT ==========\n\n");

            writer.write("Total vehicles : "
                    + vehicles.size() + "\n");

            writer.write("Average mileage : "
                    + String.format("%.2f",
                    statisticsManager.calculateAverageMileage(vehicles))
                    + " km\n");

            writer.write("Estimated revenue (5 days) : "
                    + String.format("%.2f",
                    statisticsManager.calculateTotalRevenue(vehicles, 5))
                    + " $\n\n");

            writer.write("Vehicles by type\n");
            writer.write("----------------\n");

            for (Map.Entry<String, Integer> entry :
                    statisticsManager.countByType(vehicles).entrySet()) {

                writer.write(entry.getKey()
                        + " : "
                        + entry.getValue()
                        + "\n");
            }

            writer.write("\nVehicles requiring maintenance\n");
            writer.write("------------------------------\n");

            for (Vehicle vehicle :
                    statisticsManager.vehiclesNeedingMaintenance(vehicles)) {

                writer.write(vehicle.getId()
                        + " - "
                        + vehicle.getBrand()
                        + " "
                        + vehicle.getModel()
                        + "\n");
            }

            writer.write("\nReport generated successfully.\n");

            System.out.println(
                    "Report generated : " + filePath);

        } catch (IOException e) {

            System.out.println(
                    "Error generating report : "
                    + e.getMessage());
        }
    }
}