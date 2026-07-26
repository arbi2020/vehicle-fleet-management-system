package app;


import controller.VehicleController;

import service.StatisticsManager;


import javafx.application.Application;

import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;


import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import javafx.scene.control.cell.PropertyValueFactory;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import javafx.geometry.Insets;


import javafx.stage.Stage;


import model.Vehicle;
import model.Car;
import model.SUV;
import model.Truck;




public class FleetApplication extends Application {



    // ===============================
    // Global variables
    // ===============================


    private Label dashboard;






    @Override
    public void start(Stage stage) {




        // ===============================
        // Load vehicles
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







        TableColumn<Vehicle,Boolean> availableColumn =

                new TableColumn<>("Available");



        availableColumn.setCellValueFactory(

                new PropertyValueFactory<>("available")

        );








        table.getColumns().addAll(

                idColumn,

                typeColumn,

                brandColumn,

                modelColumn,

                yearColumn,

                mileageColumn,

                availableColumn

        );








        // ===============================
        // Load data
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




        dashboard.setStyle(

                "-fx-font-size:14px;"
                +
                "-fx-padding:10px;"

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

                .addListener(

                (

                ObservableValue<? extends String> observable,

                String oldValue,

                String newValue

                ) -> {



                    filteredData.setPredicate(

                            vehicle -> {



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



                            }

                    );



                });








        // ===============================
        // Buttons
        // ===============================


        Button addButton =

                new Button(

                        "Add Vehicle"

                );



        Button rentButton =

                new Button(

                        "Rent Vehicle"

                );



        Button returnButton =

                new Button(

                        "Return Vehicle"

                );









        // ===============================
        // Rent vehicle
        // ===============================


        rentButton.setOnAction(event -> {



            Vehicle selectedVehicle =


                    table.getSelectionModel()

                            .getSelectedItem();





            if(selectedVehicle != null) {



                controller.rentVehicle(

                        selectedVehicle.getId()

                );



                table.refresh();



                updateDashboard(

                        statisticsManager,

                        controller

                );



            }
            else {


                showWarning(

                        "Please select a vehicle first"

                );


            }


        });










        // ===============================
        // Return vehicle
        // ===============================


        returnButton.setOnAction(event -> {



            Vehicle selectedVehicle =


                    table.getSelectionModel()

                            .getSelectedItem();





            if(selectedVehicle != null) {



                controller.returnVehicle(

                        selectedVehicle.getId()

                );



                table.refresh();



                updateDashboard(

                        statisticsManager,

                        controller

                );



            }
            else {


                showWarning(

                        "Please select a vehicle first"

                );


            }


        });









        // ===============================
        // Add Vehicle
        // ===============================


        addButton.setOnAction(event -> {



            Stage addStage =

                    new Stage();




            addStage.setTitle(

                    "Add New Vehicle"

            );






            VBox form =

                    new VBox(10);



            form.setPadding(

                    new Insets(15)

            );






            TextField idField =

                    new TextField();


            idField.setPromptText(

                    "ID"

            );






            TextField brandField =

                    new TextField();


            brandField.setPromptText(

                    "Brand"

            );






            TextField modelField =

                    new TextField();


            modelField.setPromptText(

                    "Model"

            );






            TextField yearField =

                    new TextField();


            yearField.setPromptText(

                    "Year"

            );






            TextField mileageField =

                    new TextField();


            mileageField.setPromptText(

                    "Mileage"

            );






            ComboBox<String> typeBox =

                    new ComboBox<>();



            typeBox.getItems().addAll(

                    "Car",

                    "SUV",

                    "Truck"

            );



            typeBox.setValue(

                    "Car"

            );






            TextField extraField =

                    new TextField();



            extraField.setPromptText(

                    "Doors / 4WD / Capacity"

            );








            Button saveButton =

                    new Button(

                            "Save"

                    );









            saveButton.setOnAction(e -> {



                try {



                    Vehicle vehicle = null;





                    String id =

                            idField.getText();



                    String brand =

                            brandField.getText();



                    String model =

                            modelField.getText();



                    int year =

                            Integer.parseInt(

                                    yearField.getText()

                            );



                    double mileage =

                            Double.parseDouble(

                                    mileageField.getText()

                            );







                    switch(typeBox.getValue()) {



                        case "Car":


                            vehicle =

                                    new Car(

                                            id,

                                            brand,

                                            model,

                                            year,

                                            mileage,

                                            Integer.parseInt(

                                                    extraField.getText()

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

                                                    extraField.getText()

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

                                                    extraField.getText()

                                            )

                                    );


                            break;


                    }







                    controller.addVehicle(

                            vehicle

                    );






                    data.add(

                            vehicle

                    );






                    table.refresh();






                    updateDashboard(

                            statisticsManager,

                            controller

                    );







                    addStage.close();




                }

                catch(Exception ex) {



                    showWarning(

                            "Invalid vehicle information"

                    );



                }



            });







            form.getChildren().addAll(

                    idField,

                    brandField,

                    modelField,

                    yearField,

                    mileageField,

                    typeBox,

                    extraField,

                    saveButton

            );







            Scene addScene =

                    new Scene(

                            form,

                            350,

                            450

                    );






            addStage.setScene(

                    addScene

            );



            addStage.show();



        });


                // ===============================
        // Double click vehicle details
        // ===============================


        table.setOnMouseClicked(event -> {



            if(event.getClickCount() == 2) {



                Vehicle selectedVehicle =


                        table.getSelectionModel()

                                .getSelectedItem();





                if(selectedVehicle != null) {


                    showVehicleDetails(

                            selectedVehicle

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

                        addButton,

                        rentButton,

                        returnButton

                );







        // ===============================
        // Main Layout
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







        // ===============================
        // Scene
        // ===============================


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
    // Vehicle details
    // ===============================


    private void showVehicleDetails(

            Vehicle vehicle

    ) {



        Stage detailsStage =

                new Stage();




        detailsStage.setTitle(

                "Vehicle Details"

        );







        VBox details =

                new VBox(10);



        details.setPadding(

                new Insets(15)

        );







        Label idLabel =

                new Label(

                        "ID : "

                        + vehicle.getId()

                );





        Label typeLabel =

                new Label(

                        "Type : "

                        + vehicle.getVehicleType()

                );






        Label brandLabel =

                new Label(

                        "Brand : "

                        + vehicle.getBrand()

                );






        Label modelLabel =

                new Label(

                        "Model : "

                        + vehicle.getModel()

                );






        Label yearLabel =

                new Label(

                        "Year : "

                        + vehicle.getYear()

                );






        Label mileageLabel =

                new Label(

                        "Mileage : "

                        + vehicle.getMileage()

                        + " km"

                );






        Label availableLabel =

                new Label(

                        "Available : "

                        + vehicle.isAvailable()

                );






        Label costLabel =

                new Label(

                        "Rental cost/day : "

                        + vehicle.calculateRentalCost(1)

                        + " $"

                );







        String maintenanceText;



        if(vehicle.needsMaintenance()) {



            maintenanceText =

                    "Maintenance required";


        }
        else {


            maintenanceText =

                    "No maintenance required";


        }





        Label maintenanceLabel =

                new Label(

                        "Maintenance : "

                        + maintenanceText

                );







        Button closeButton =

                new Button(

                        "Close"

                );





        closeButton.setOnAction(e ->

                detailsStage.close()

        );







        details.getChildren().addAll(

                idLabel,

                typeLabel,

                brandLabel,

                modelLabel,

                yearLabel,

                mileageLabel,

                availableLabel,

                costLabel,

                maintenanceLabel,

                closeButton

        );







        Scene detailsScene =

                new Scene(

                        details,

                        350,

                        350

                );






        detailsStage.setScene(

                detailsScene

        );



        detailsStage.show();



    }









    // ===============================
    // Refresh dashboard
    // ===============================


    private void updateDashboard(

            StatisticsManager statisticsManager,

            VehicleController controller

    ) {



        int totalVehicles =

                statisticsManager.getTotalVehicles(

                        controller.getVehicles()

                );





        int availableVehicles =

                statisticsManager.getAvailableVehicles(

                        controller.getVehicles()

                );





        int rentedVehicles =

                statisticsManager.getRentedVehicles(

                        controller.getVehicles()

                );





        int maintenanceVehicles =

                statisticsManager

                        .vehiclesNeedingMaintenance(

                                controller.getVehicles()

                        )

                        .size();





        double averageMileage =

                statisticsManager.calculateAverageMileage(

                        controller.getVehicles()

                );







        dashboard.setText(

                "Total Vehicles : "

                + totalVehicles


                + " | Available : "

                + availableVehicles


                + " | Rented : "

                + rentedVehicles


                + " | Maintenance : "

                + maintenanceVehicles


                + "\nAverage Mileage : "

                + String.format(

                        "%.2f",

                        averageMileage

                  )

                + " km"

        );



    }









    // ===============================
    // Warning
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


    public static void main(String[] args) {


        launch(args);


    }



}