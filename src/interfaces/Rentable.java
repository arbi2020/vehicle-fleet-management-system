package interfaces;

public interface Rentable {

    void rent();

    void returnVehicle();

    double calculateRentalCost(int days);
}