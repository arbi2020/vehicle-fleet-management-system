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


        VehicleController controller =
                new VehicleController();


        StatisticsManager statisticsManager =
                new StatisticsManager();



        // ===============================
        // Table
        // ===============================


        TableView<Vehicle> table =
                new TableView<>();



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
        // Status column
        // ===============================


        TableColumn<Vehicle,String> statusColumn =
                new TableColumn<>("Status");


        statusColumn.setCellValueFactory(data -> {


            Vehicle vehicle =
                    data.getValue();



            if(vehicle.needsMaintenance()) {


                return new SimpleStringProperty(
                        "Maintenance"
                );

            }


            else if(vehicle.isAvailable()) {


                return new SimpleStringProperty(
                        "Available"
                );

            }


            else {


                return new SimpleStringProperty(
                        "Rented"
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
        // Data
        // ===============================


        ObservableList<Vehicle> data =
                FXCollections.observableArrayList(
                        controller.getVehicles()
                );



        FilteredList<Vehicle> filteredData =
                new FilteredList<>(data, v -> true);



        table.setItems(filteredData);





        // ===============================
        // Row colors
        // ===============================


        table.setRowFactory(tv -> {


            TableRow<Vehicle> row =
                    new TableRow<>();


            row.itemProperty()
                    .addListener((obs, oldVehicle, vehicle) -> {


                        if(vehicle == null) {


                            row.setStyle("");

                        }


                        else if(vehicle.needsMaintenance()) {


                            row.setStyle(
                                    "-fx-background-color:#FFD580;"
                            );

                        }


                        else if(vehicle.isAvailable()) {


                            row.setStyle(
                                    "-fx-background-color:#D8F3DC;"
                            );

                        }


                        else {


                            row.setStyle(
                                    "-fx-background-color:#FFCCCC;"
                            );

                        }


                    });


            return row;

        });






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
        // Search
        // ===============================


        TextField search =
                new TextField();


        search.setPromptText(
                "Search vehicle..."
        );



        search.textProperty()
                .addListener((obs, oldValue, newValue) -> {


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
        // Rent
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




            // Seulement cette règle :
            // impossible si déjà loué


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
        // Return
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







        refreshButton.setOnAction(event -> {


            table.refresh();


            updateDashboard(
                    statisticsManager,
                    controller
            );


        });






        // ===============================
        // Details double click
        // ===============================


        table.setOnMouseClicked(event -> {


            if(event.getClickCount() == 2) {


                Vehicle selected =
                        table.getSelectionModel()
                                .getSelectedItem();



                if(selected != null) {


                    showDetails(selected);

                }


            }


        });






        HBox menu =
                new HBox(
                        10,
                        search,
                        rentButton,
                        returnButton,
                        refreshButton
                );


        menu.setPadding(
                new Insets(10)
        );




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



        dashboard.setText(

                "Total : "
                + total

                + " | Available : "
                + available

                + " | Rented : "
                + rented

                + " | Maintenance : "
                + maintenance

        );


    }






    private void showDetails(Vehicle vehicle) {


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


        alert.setContentText(

                "ID : "
                + vehicle.getId()

                + "\nType : "
                + vehicle.getVehicleType()

                + "\nYear : "
                + vehicle.getYear()

                + "\nMileage : "
                + vehicle.getMileage()

                + "\nStatus : "
                + (vehicle.isAvailable()
                ? "Available"
                : "Rented")

        );


        alert.show();


    }







    private void showWarning(String message) {


        Alert alert =
                new Alert(
                        Alert.AlertType.WARNING
                );


        alert.setTitle(
                "Warning"
        );


        alert.setHeaderText(null);


        alert.setContentText(message);


        alert.show();


    }







    public static void main(String[] args) {


        launch(args);


    }



}