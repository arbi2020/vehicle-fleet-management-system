package app;


import controller.VehicleController;

import service.StatisticsManager;


import javafx.application.Application;

import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;


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


import javafx.geometry.Insets;


import javafx.stage.Stage;


import model.Vehicle;



public class FleetApplication extends Application {



    private Label dashboard;




    @Override
    public void start(Stage stage) {



        // ===============================
        // Controllers
        // ===============================


        VehicleController controller =

                new VehicleController();



        StatisticsManager statisticsManager =

                new StatisticsManager();





        // ===============================
        // Table
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

                new FilteredList<>(

                        data,

                        v -> true

                );



        table.setItems(filteredData);






        // ===============================
        // Row colors
        // ===============================


        table.setRowFactory(tv -> {



            TableRow<Vehicle> row =

                    new TableRow<>();




            row.itemProperty()

                    .addListener(

                    (obs, oldValue, vehicle) -> {



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





        table.setColumnResizePolicy(

                TableView.CONSTRAINED_RESIZE_POLICY

        );





        // ===============================
        // Title
        // ===============================


        Label title =

                new Label(

                "Vehicle Fleet Management System"

                );



        title.setStyle(

                "-fx-font-size:22px;"
                +
                "-fx-font-weight:bold;"

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
        // Search
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






                        return

                                vehicle.getId()

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

                new Button(

                        "Rent"

                );




        Button returnButton =

                new Button(

                        "Return"

                );





        Button refreshButton =

                new Button(

                        "Refresh"

                );









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





            if(!selected.isAvailable()) {



                showWarning(

                        "Vehicle already rented"

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






            if(selected.isAvailable()) {



                showWarning(

                        "Vehicle is already available"

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
        // Main layout
        // ===============================


        VBox root =

                new VBox(

                        15,

                        title,

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



        stage.setScene(

                scene

        );



        stage.show();


    }

        // ===============================
    // Vehicle details window
    // ===============================


    private void showVehicleDetails(

            Vehicle vehicle

    ) {



        Stage detailsStage =

                new Stage();




        detailsStage.setTitle(

                "Vehicle Details"

        );







        VBox box =

                new VBox(10);



        box.setPadding(

                new Insets(15)

        );








        Label id =

                new Label(

                        "ID : "
                        + vehicle.getId()

                );




        Label type =

                new Label(

                        "Type : "
                        + vehicle.getVehicleType()

                );




        Label brand =

                new Label(

                        "Brand : "
                        + vehicle.getBrand()

                );




        Label model =

                new Label(

                        "Model : "
                        + vehicle.getModel()

                );




        Label year =

                new Label(

                        "Year : "
                        + vehicle.getYear()

                );




        Label mileage =

                new Label(

                        "Mileage : "
                        + vehicle.getMileage()
                        + " km"

                );





        Label status =

                new Label();



        if(vehicle.needsMaintenance()) {



            status.setText(

                    "Status : Maintenance required"

            );


        }

        else if(vehicle.isAvailable()) {



            status.setText(

                    "Status : Available"

            );


        }

        else {



            status.setText(

                    "Status : Rented"

            );


        }






        Label rentalCost =

                new Label(

                        "Rental cost/day : "

                        +

                        vehicle.calculateRentalCost(1)

                        +

                        " $"

                );







        Button close =

                new Button(

                        "Close"

                );




        close.setOnAction(e ->

                detailsStage.close()

        );







        box.getChildren().addAll(

                id,

                type,

                brand,

                model,

                year,

                mileage,

                status,

                rentalCost,

                close

        );







        Scene scene =

                new Scene(

                        box,

                        350,

                        350

                );




        detailsStage.setScene(

                scene

        );



        detailsStage.show();



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



                "Total : "
                + total



                + " | Available : "
                + available



                + " | Rented : "
                + rented



                + " | Maintenance : "
                + maintenance



                + "\nAverage mileage : "

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


    private void showWarning(

            String message

    ) {



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



        alert.show();



    }









    // ===============================
    // Main
    // ===============================


    public static void main(

            String[] args

    ) {



        launch(args);


    }



}

