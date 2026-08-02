package app;

import javafx.scene.control.TextInputDialog;
import controller.VehicleController;
import service.StatisticsManager;
import model.Vehicle;
import javafx.scene.layout.Region;

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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;



public class FleetApplication extends Application {


    private Label generalDashboard;
    private Label analysisDashboard;



    @Override
    public void start(Stage stage) {


        VehicleController controller =
                new VehicleController();


        StatisticsManager statisticsManager =
                new StatisticsManager();





        // ==================================================
        // TABLE
        // ==================================================


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







        // ==================================================
        // DATA
        // ==================================================


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







        // ==================================================
        // ROW COLOR BY STATUS
        // ==================================================


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
                                    .add(
                                            "rented-row"
                                    );


                        }


                        else if(vehicle.needsMaintenance()) {


                            row.getStyleClass()
                                    .add(
                                            "maintenance-row"
                                    );


                        }


                        else {


                            row.getStyleClass()
                                    .add(
                                            "available-row"
                                    );

                        }


                    }

            );


            return row;


        });

                // ==================================================
        // DASHBOARD
        // ==================================================


        generalDashboard = new Label();

        generalDashboard.setId(
             "dashboard"
        );

        generalDashboard.getStyleClass()
             .add(
                "card"
        );



        analysisDashboard = new Label();

        analysisDashboard.setId(
             "dashboard"
        );

        analysisDashboard.getStyleClass()
             .add(
                "card"
        );

        generalDashboard.setWrapText(true);
        analysisDashboard.setWrapText(true);


        generalDashboard.setMinHeight(Region.USE_PREF_SIZE);
        analysisDashboard.setMinHeight(Region.USE_PREF_SIZE);

        updateDashboard(

                statisticsManager,

                controller

        );

        HBox dashboardBox = new HBox(
                20,
                generalDashboard,
                analysisDashboard
        );

        dashboardBox.setPadding(
                new Insets(10)
        );

        HBox.setHgrow(
                generalDashboard,
                javafx.scene.layout.Priority.ALWAYS
        );

        HBox.setHgrow(
                analysisDashboard,
                javafx.scene.layout.Priority.ALWAYS
        );


        generalDashboard.setMaxWidth(Double.MAX_VALUE);

        analysisDashboard.setMaxWidth(Double.MAX_VALUE);



        // ==================================================
        // SEARCH
        // ==================================================


        TextField searchField =

                new TextField();


        TextField daysField = new TextField();

        daysField.setPromptText(
                "Days"
        );

        daysField.setPrefWidth(80);
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









        // ==================================================
        // BUTTONS
        // ==================================================


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









        // ==================================================
        // RENT VEHICLE
        // ==================================================


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




        int days;


        try {
                days = Integer.parseInt(
                        daysField.getText()
          );


                if(days <= 0) {
                        
                        showWarning(
                        "Number of days must be positive"
                );

                       return;
                }


        }
        
        catch(NumberFormatException e) {
                showWarning(
            "Please enter a valid number of days"
        );

                return;

       }



        controller.rentVehicle(
                selected.getId(),

                days

        ); 


            table.refresh();





            updateDashboard(

                    statisticsManager,

                    controller

            );



        });










        // ==================================================
        // RETURN VEHICLE
        // ==================================================


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









        // ==================================================
        // REFRESH
        // ==================================================


        refreshButton.setOnAction(event -> {



            table.refresh();



            updateDashboard(

                    statisticsManager,

                    controller

            );


        });









        // ==================================================
        // DOUBLE CLICK DETAILS
        // ==================================================


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








        // ==================================================
        // TOOLBAR
        // ==================================================


        HBox menu =

                new HBox(

                        25,

                        searchField,

                        daysField,

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

                // ==================================================
        // BANNER IMAGE
        // ==================================================


        Image bannerImage =

                new Image(

                        getClass()

                                .getResourceAsStream(

                                        "/resources/images/fleet_banner.png"

                                )

                );




        ImageView bannerView =

                new ImageView(

                        bannerImage

                );




        bannerView.setFitWidth(1000);

        bannerView.setFitHeight(220);

        bannerView.setPreserveRatio(false);   



        bannerView.getStyleClass()

                .add(

                        "banner-image"

                );



        // ==================================================
        // MAIN LAYOUT
        // ==================================================


        VBox root =
             
                new VBox(
                        15,

                        bannerView,

                        dashboardBox,

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









        // ==================================================
        // SCENE
        // ==================================================


        Scene scene =

                new Scene(

                        root,

                        1100,

                        800

                );









        // ==================================================
        // LOAD CSS
        // ==================================================


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









        // ==================================================
        // STAGE
        // ==================================================


        stage.setTitle(

                "Vehicle Fleet Management System"

        );



        stage.setScene(

                scene

        );



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
            statisticsManager.getTotalVehicles(
                    controller.getVehicles()
            );


    int available =
            statisticsManager.getAvailableVehicles(
                    controller.getVehicles()
            );


    int rented =
            statisticsManager.getRentedVehicles(
                    controller.getVehicles()
            );


    int maintenance =
            statisticsManager
                    .vehiclesNeedingMaintenance(
                            controller.getVehicles()
                    )
                    .size();



    double mileage =
            statisticsManager.calculateAverageMileage(
                    controller.getVehicles()
            );



    double revenue =
            statisticsManager.calculateTotalRevenue(
                    controller.getVehicles()
            );



    Vehicle mostUsed =
            statisticsManager.getMostRentedVehicle(
                    controller.getVehicles()
            );


    String vehicleName = "None";


    if(mostUsed != null){

        vehicleName =
                mostUsed.getBrand()
                + " "
                + mostUsed.getModel();

    }



    generalDashboard.setText(

            "🚗 FLEET OVERVIEW\n\n"

            + "Total Vehicles : "
            + total

            + "\n\n🟢 Available : "
            + available

            + "\n\n🔴 Rented : "
            + rented

            + "\n\n🟠 Maintenance : "
            + maintenance

    );



    analysisDashboard.setText(

            "📊 FLEET ANALYSIS\n\n"

            + "Average Mileage : "
            + String.format("%.2f", mileage)
            + " km"

            + "\n\n💰 Total Revenue : "
            + String.format("%.2f", revenue)
            + " $"

            + "\n\n🏆 Most Used : "
            + vehicleName

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

