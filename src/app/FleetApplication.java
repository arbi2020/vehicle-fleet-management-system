package app;


import controller.VehicleController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import model.Vehicle;



public class FleetApplication extends Application {


    @Override
    public void start(Stage stage) {


        // ===============================
        // Load vehicles from CSV
        // ===============================

        VehicleController controller =
                new VehicleController();



        // ===============================
        // Create TableView
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



        // ===============================
        // Column sizes
        // ===============================


        idColumn.setPrefWidth(80);

        typeColumn.setPrefWidth(100);

        brandColumn.setPrefWidth(120);

        modelColumn.setPrefWidth(120);

        yearColumn.setPrefWidth(80);

        mileageColumn.setPrefWidth(120);

        availableColumn.setPrefWidth(100);



        // ===============================
        // Add columns
        // ===============================


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


        table.setItems(data);



        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );



        // ===============================
        // Layout
        // ===============================


        VBox root =
                new VBox(table);


        Scene scene =
                new Scene(root, 1000, 600);



        // ===============================
        // Window
        // ===============================


        stage.setTitle(
                "Vehicle Fleet Management System"
        );


        stage.setScene(scene);


        stage.show();

    }



    public static void main(String[] args) {

        launch(args);

    }

}