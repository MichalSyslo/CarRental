package pl.edu.wszib.car.rental.services;

import pl.edu.wszib.car.rental.model.Vehicle;

import java.util.List;

public interface IVehicleService {
    Vehicle getVehicleByID(int id);
    void updateVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAvailableVehicles();
    void addVehicle(Vehicle vehicle);
    void removeVehicle(Vehicle vehicle);
}
