package app;


import controller.VehicleController;


import javafx.application.Application;

import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;


import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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




public class FleetApplication extends Application {



    @Override
    public void start(Stage stage) {



        // ===============================
        // Load vehicles
        // ===============================


        VehicleController controller =
                new VehicleController();




        // ===============================
        // TableView
        // ===============================


        TableView<Vehicle> table =
                new TableView<>();





        // ===============================
        // Columns
        // ===============================


        TableColumn<Vehicle, String> idColumn =
                new TableColumn<>("ID");


        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );




        TableColumn<Vehicle, String> typeColumn =
                new TableColumn<>("Type");


        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("vehicleType")
        );




        TableColumn<Vehicle, String> brandColumn =
                new TableColumn<>("Brand");


        brandColumn.setCellValueFactory(
                new PropertyValueFactory<>("brand")
        );




        TableColumn<Vehicle, String> modelColumn =
                new TableColumn<>("Model");


        modelColumn.setCellValueFactory(
                new PropertyValueFactory<>("model")
        );




        TableColumn<Vehicle, Integer> yearColumn =
                new TableColumn<>("Year");


        yearColumn.setCellValueFactory(
                new PropertyValueFactory<>("year")
        );




        TableColumn<Vehicle, Double> mileageColumn =
                new TableColumn<>("Mileage");


        mileageColumn.setCellValueFactory(
                new PropertyValueFactory<>("mileage")
        );




        TableColumn<Vehicle, Boolean> availableColumn =
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
        // Search
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



                                if(newValue == null
                                        ||
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


        Button rentButton =
                new Button(
                        "Rent Vehicle"
                );



        Button returnButton =
                new Button(
                        "Return Vehicle"
                );








        // ===============================
        // Rent Action
        // ===============================


        rentButton.setOnAction(event -> {



            Vehicle selectedVehicle =
                    table.getSelectionModel()
                    .getSelectedItem();




            if(selectedVehicle != null) {



                boolean result =
                        controller.rentVehicle(
                                selectedVehicle.getId()
                        );



                if(result) {


                    table.refresh();


                }


            }
            else {


                showWarning(
                        "Please select a vehicle first."
                );


            }



        });








        // ===============================
        // Return Action
        // ===============================


        returnButton.setOnAction(event -> {



            Vehicle selectedVehicle =
                    table.getSelectionModel()
                    .getSelectedItem();




            if(selectedVehicle != null) {



                boolean result =
                        controller.returnVehicle(
                                selectedVehicle.getId()
                        );



                if(result) {


                    table.refresh();


                }


            }
            else {


                showWarning(
                        "Please select a vehicle first."
                );


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
                        returnButton
                );






        // ===============================
        // Layout
        // ===============================


        VBox root =
                new VBox(
                        15,
                        title,
                        menu,
                        table
                );


        root.setPadding(
                new Insets(15)
        );







        // ===============================
        // Window
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


        stage.setScene(scene);


        stage.show();



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


        alert.setContentText(
                message
        );


        alert.show();



    }







    public static void main(String[] args) {


        launch(args);


    }



}