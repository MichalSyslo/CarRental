package pl.edu.wszib.car.rental.dao;

import pl.edu.wszib.car.rental.model.Vehicle;

import java.sql.Timestamp;
import java.util.List;

public interface IVehicleDAO  {
    Vehicle getVehicleByID(int id);
    void updateVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAvailableVehicles(Timestamp start, Timestamp end);
    void persistVehicle(Vehicle vehicle);
    void removeVehicle(Vehicle vehicle);
}
