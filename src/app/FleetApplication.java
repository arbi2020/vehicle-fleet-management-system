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
        // TABLE
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




        TableColumn<Vehicle,String> statusColumn =
                new TableColumn<>("Status");



        statusColumn.setCellValueFactory(data -> {


            Vehicle vehicle =
                    data.getValue();



            if(!vehicle.isAvailable()) {

                return new SimpleStringProperty(
                        "Rented"
                );

            }


            else if(vehicle.needsMaintenance()) {


                return new SimpleStringProperty(
                        "Maintenance"
                );

            }


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
        // DATA
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




        table.setColumnResizePolicy(

                TableView.CONSTRAINED_RESIZE_POLICY

        );


                // ===============================
        // ROW COLOR BY STATUS
        // ===============================


        table.setRowFactory(tv -> {


            TableRow<Vehicle> row =
                    new TableRow<>();


            row.itemProperty().addListener(

                    (observable, oldVehicle, vehicle) -> {


                        row.getStyleClass()
                                .removeAll(
                                        "available-row",
                                        "rented-row",
                                        "maintenance-row"
                                );



                        if(vehicle == null) {

                            return;

                        }



                        if(!vehicle.isAvailable()) {


                            row.getStyleClass()
                                    .add("rented-row");


                        }

                        else if(vehicle.needsMaintenance()) {


                            row.getStyleClass()
                                    .add("maintenance-row");


                        }

                        else {


                            row.getStyleClass()
                                    .add("available-row");


                        }


                    }

            );


            return row;


        });






        // ===============================
        // DASHBOARD
        // ===============================


        dashboard =

                new Label();



        dashboard.setId(

                "dashboard"

        );



        dashboard.getStyleClass()

                .add(

                        "card"

                );



        updateDashboard(

                statisticsManager,

                controller

        );







        // ===============================
        // SEARCH
        // ===============================


        TextField searchField =

                new TextField();



        searchField.setPromptText(

                "Search vehicle..."

        );



        searchField.getStyleClass()

                .add(

                        "search-field"

                );






        searchField.textProperty()

                .addListener(

                        (observable, oldValue, newValue) -> {



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


                        }

                );







        // ===============================
        // BUTTONS
        // ===============================


        Button rentButton =

                new Button("Rent");



        Button returnButton =

                new Button("Return");



        Button refreshButton =

                new Button("Refresh");





        rentButton.getStyleClass()

                .add(

                        "add-button"

                );



        returnButton.getStyleClass()

                .add(

                        "return-button"

                );



        refreshButton.getStyleClass()

                .add(

                        "refresh-button"

                );







        // ===============================
        // RENT VEHICLE
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
        // RETURN VEHICLE
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
        // REFRESH
        // ===============================


        refreshButton.setOnAction(event -> {



            table.refresh();



            updateDashboard(

                    statisticsManager,

                    controller

            );


        });






        // ===============================
        // DOUBLE CLICK DETAILS
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
        // TOOLBAR
        // ===============================


        HBox menu =

                new HBox(

                        15,

                        searchField,

                        rentButton,

                        returnButton,

                        refreshButton

                );



        menu.setPadding(

                new Insets(10)

        );



        menu.getStyleClass()

                .add(

                        "toolbar"

                );

                                // ===============================
        // TITLE
        // ===============================


        Label title =

                new Label(
                        "🚗 Vehicle Fleet Management System"
                );


        title.getStyleClass()

                .add(

                        "title-label"

                );






        // ===============================
        // MAIN LAYOUT
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

                new Insets(20)

        );


        root.getStyleClass()

                .add(

                        "main-container"

                );







        // ===============================
        // SCENE
        // ===============================


        Scene scene =

                new Scene(

                        root,

                        1100,

                        650

                );







        // ===============================
        // LOAD CSS
        // src/resources/style.css
        // ===============================


        var css =

                getClass()

                .getResource(
                        "/resources/style.css"
                );



        if(css != null) {


            scene.getStylesheets()

                    .add(

                            css.toExternalForm()

                    );


        }

        else {


            System.out.println(

                    "WARNING : style.css not found"

            );


        }






        stage.setTitle(

                "Vehicle Fleet Management System"

        );



        stage.setScene(scene);



        stage.show();


    }









    // ==================================================
    // VEHICLE DETAILS
    // ==================================================


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









    // ==================================================
    // DASHBOARD UPDATE
    // ==================================================


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



                "🚗 Total Vehicles : "

                + total



                + "\n\n🟢 Available : "

                + available



                + "\n🔴 Rented : "

                + rented



                + "\n🟠 Maintenance : "

                + maintenance



                + "\n\nAverage Mileage : "

                + String.format(

                        "%.2f",

                        averageMileage

                )

                + " km"



        );



    }









    // ==================================================
    // WARNING MESSAGE
    // ==================================================


    private void showWarning(String message) {



        Alert alert =

                new Alert(

                        Alert.AlertType.WARNING

                );



        alert.setTitle(

                "Warning"

        );



        alert.setHeaderText(null);



        alert.setContentText(

                message

        );



        alert.showAndWait();


    }









    // ==================================================
    // MAIN
    // ==================================================


    public static void main(String[] args) {


        launch(args);


    }


}