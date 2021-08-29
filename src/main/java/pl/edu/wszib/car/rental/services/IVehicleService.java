package pl.edu.wszib.car.rental.services;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.car.rental.model.Vehicle;

import java.util.List;

public interface IVehicleService {
    Vehicle getVehicleByID(int id);
    void updateVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAvailableVehicles();
    void removeVehicle(Vehicle vehicle);
    boolean addVehicle(Vehicle vehicle, MultipartFile multipartFile);
}
