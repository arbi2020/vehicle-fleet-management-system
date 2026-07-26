package app;


import controller.VehicleController;
import service.StatisticsManager;

import javafx.application.Application;

import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;

import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import model.Vehicle;



public class FleetApplication extends Application {



    private Label dashboard;





    @Override
    public void start(Stage stage) {



        // ===============================
        // Controller
        // ===============================


        VehicleController controller =

                new VehicleController();




        StatisticsManager statisticsManager =

                new StatisticsManager();







        // ===============================
        // TableView
        // ===============================


        TableView<Vehicle> table =

                new TableView<>();







        // ===============================
        // Columns
        // ===============================



        TableColumn<Vehicle,String> idColumn =

                new TableColumn<>("ID");



        idColumn.setCellValueFactory(

                new PropertyValueFactory<>("id")

        );






        TableColumn<Vehicle,String> typeColumn =

                new TableColumn<>("Type");



        typeColumn.setCellValueFactory(

                new PropertyValueFactory<>("vehicleType")

        );






        TableColumn<Vehicle,String> brandColumn =

                new TableColumn<>("Brand");



        brandColumn.setCellValueFactory(

                new PropertyValueFactory<>("brand")

        );






        TableColumn<Vehicle,String> modelColumn =

                new TableColumn<>("Model");



        modelColumn.setCellValueFactory(

                new PropertyValueFactory<>("model")

        );






        TableColumn<Vehicle,Integer> yearColumn =

                new TableColumn<>("Year");



        yearColumn.setCellValueFactory(

                new PropertyValueFactory<>("year")

        );






        TableColumn<Vehicle,Double> mileageColumn =

                new TableColumn<>("Mileage");



        mileageColumn.setCellValueFactory(

                new PropertyValueFactory<>("mileage")

        );









        // ===============================
        // Status column (CORRIGÉ)
        // ===============================


        TableColumn<Vehicle,String> statusColumn =

                new TableColumn<>("Status");





        statusColumn.setCellValueFactory(data -> {



            Vehicle vehicle =

                    data.getValue();






            // Priorité 1 : véhicule loué


            if(!vehicle.isAvailable()) {



                return new SimpleStringProperty(

                        "Rented"

                );


            }






            // Priorité 2 : maintenance


            else if(vehicle.needsMaintenance()) {



                return new SimpleStringProperty(

                        "Maintenance"

                );


            }







            // Priorité 3 : disponible


            else {



                return new SimpleStringProperty(

                        "Available"

                );


            }



        });








        table.getColumns().addAll(


                idColumn,

                typeColumn,

                brandColumn,

                modelColumn,

                yearColumn,

                mileageColumn,

                statusColumn


        );









        // ===============================
        // Load vehicles
        // ===============================


        ObservableList<Vehicle> data =


                FXCollections.observableArrayList(

                        controller.getVehicles()

                );







        FilteredList<Vehicle> filteredData =

                new FilteredList<>(

                        data,

                        vehicle -> true

                );







        table.setItems(filteredData);









        // ===============================
        // Row colors (CORRIGÉ)
        // ===============================


        table.setRowFactory(tv -> {



            TableRow<Vehicle> row =

                    new TableRow<>();






            row.itemProperty()

                    .addListener(

                            (observable,
                             oldVehicle,
                             vehicle) -> {



                        if(vehicle == null) {



                            row.setStyle("");

                        }






                        // Loué = priorité


                        else if(!vehicle.isAvailable()) {



                            row.setStyle(

                                    "-fx-background-color:#FFCCCC;"

                            );


                        }







                        // Maintenance disponible


                        else if(vehicle.needsMaintenance()) {



                            row.setStyle(

                                    "-fx-background-color:#FFD580;"

                            );


                        }







                        // Disponible


                        else {



                            row.setStyle(

                                    "-fx-background-color:#D8F3DC;"

                            );


                        }



                    });





            return row;



        });






        table.setColumnResizePolicy(

                TableView.CONSTRAINED_RESIZE_POLICY

        );

                // ===============================
        // Dashboard
        // ===============================


        dashboard =

                new Label();




        updateDashboard(

                statisticsManager,

                controller

        );









        // ===============================
        // Search field
        // ===============================


        TextField searchField =

                new TextField();




        searchField.setPromptText(

                "Search vehicle..."

        );






        searchField.textProperty()

                .addListener((observable, oldValue, newValue) -> {



                    filteredData.setPredicate(vehicle -> {



                        if(newValue == null ||

                                newValue.isEmpty()) {



                            return true;

                        }






                        String keyword =

                                newValue.toLowerCase();






                        return vehicle.getId()

                                .toLowerCase()

                                .contains(keyword)



                                ||

                                vehicle.getBrand()

                                .toLowerCase()

                                .contains(keyword)



                                ||

                                vehicle.getModel()

                                .toLowerCase()

                                .contains(keyword)



                                ||

                                vehicle.getVehicleType()

                                .toLowerCase()

                                .contains(keyword);



                    });



                });









        // ===============================
        // Buttons
        // ===============================


        Button rentButton =

                new Button("Rent");




        Button returnButton =

                new Button("Return");




        Button refreshButton =

                new Button("Refresh");









        // ===============================
        // Rent vehicle
        // ===============================


        rentButton.setOnAction(event -> {



            Vehicle selected =

                    table.getSelectionModel()

                            .getSelectedItem();






            if(selected == null) {



                showWarning(

                        "Please select a vehicle"

                );



                return;

            }









            // ===============================
            // Rental rule
            //
            // Maintenance does NOT block rental
            // Only already rented vehicles
            // cannot be rented again
            // ===============================



            if(!selected.isAvailable()) {



                showWarning(

                        "Vehicle is already rented"

                );



                return;

            }









            controller.rentVehicle(

                    selected.getId()

            );








            table.refresh();







            updateDashboard(

                    statisticsManager,

                    controller

            );



        });









        // ===============================
        // Return vehicle
        // ===============================


        returnButton.setOnAction(event -> {



            Vehicle selected =

                    table.getSelectionModel()

                            .getSelectedItem();







            if(selected == null) {



                showWarning(

                        "Please select a vehicle"

                );



                return;

            }








            controller.returnVehicle(

                    selected.getId()

            );








            table.refresh();







            updateDashboard(

                    statisticsManager,

                    controller

            );



        });









        // ===============================
        // Refresh
        // ===============================


        refreshButton.setOnAction(event -> {



            table.refresh();




            updateDashboard(

                    statisticsManager,

                    controller

            );



        });









        // ===============================
        // Double click details
        // ===============================


        table.setOnMouseClicked(event -> {



            if(event.getClickCount() == 2) {



                Vehicle selected =

                        table.getSelectionModel()

                                .getSelectedItem();






                if(selected != null) {



                    showVehicleDetails(

                            selected

                    );


                }


            }


        });









        // ===============================
        // Menu
        // ===============================


        HBox menu =

                new HBox(

                        10,

                        searchField,

                        rentButton,

                        returnButton,

                        refreshButton

                );



        menu.setPadding(

                new Insets(10)

        );









        // ===============================
        // Layout
        // ===============================


        VBox root =

                new VBox(

                        15,

                        new Label(

                                "Vehicle Fleet Management System"

                        ),

                        dashboard,

                        menu,

                        table

                );




        root.setPadding(

                new Insets(15)

        );









        Scene scene =

                new Scene(

                        root,

                        1000,

                        600

                );








        stage.setTitle(

                "Vehicle Fleet Management System"

        );






        stage.setScene(scene);



        stage.show();



    }


        // ===============================
    // Vehicle details
    // ===============================


    private void showVehicleDetails(Vehicle vehicle) {



        Alert alert =

                new Alert(

                        Alert.AlertType.INFORMATION

                );



        alert.setTitle(

                "Vehicle Details"

        );



        alert.setHeaderText(

                vehicle.getBrand()

                + " "

                + vehicle.getModel()

        );






        String status;




        if(!vehicle.isAvailable()) {



            status = "Rented";


        }

        else if(vehicle.needsMaintenance()) {



            status = "Maintenance";


        }

        else {



            status = "Available";


        }







        alert.setContentText(



                "ID : "

                + vehicle.getId()



                + "\nType : "

                + vehicle.getVehicleType()



                + "\nBrand : "

                + vehicle.getBrand()



                + "\nModel : "

                + vehicle.getModel()



                + "\nYear : "

                + vehicle.getYear()



                + "\nMileage : "

                + vehicle.getMileage()

                + " km"



                + "\nStatus : "

                + status



                + "\nRental cost (1 day) : "

                + vehicle.calculateRentalCost(1)

                + " $"




        );




        alert.showAndWait();



    }









    // ===============================
    // Update dashboard
    // ===============================


    private void updateDashboard(

            StatisticsManager statisticsManager,

            VehicleController controller

    ) {



        int total =

                controller.getVehicles()

                        .size();




        int available = 0;

        int rented = 0;






        for(Vehicle vehicle :

                controller.getVehicles()) {




            if(vehicle.isAvailable()) {



                available++;


            }

            else {



                rented++;


            }


        }







        int maintenance =


                statisticsManager

                        .vehiclesNeedingMaintenance(

                                controller.getVehicles()

                        )

                        .size();








        double averageMileage =


                statisticsManager

                        .calculateAverageMileage(

                                controller.getVehicles()

                        );








        dashboard.setText(



                "Total Vehicles : "

                + total



                + " | Available : "

                + available



                + " | Rented : "

                + rented



                + " | Maintenance : "

                + maintenance



                + "\nAverage Mileage : "

                + String.format(

                        "%.2f",

                        averageMileage

                )

                + " km"




        );



    }









    // ===============================
    // Warning message
    // ===============================


    private void showWarning(String message) {



        Alert alert =

                new Alert(

                        Alert.AlertType.WARNING

                );




        alert.setTitle(

                "Warning"

        );




        alert.setHeaderText(

                null

        );




        alert.setContentText(

                message

        );




        alert.showAndWait();



    }









    // ===============================
    // Main
    // ===============================


    public static void main(String[] args) {



        launch(args);


    }



}