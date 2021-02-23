package pl.edu.wszib.car.rental.dao;

import pl.edu.wszib.car.rental.model.Vehicle;

import java.util.List;

public interface IVehicleDAO {
    Vehicle getVehicleByID(int id);
    List<Vehicle> getAllVehicles();
    void persistVehicle(Vehicle vehicle);
    void removeVehicle(Vehicle vehicle);
}
