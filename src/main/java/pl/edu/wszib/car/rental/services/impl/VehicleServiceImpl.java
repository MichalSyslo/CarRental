package pl.edu.wszib.car.rental.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.car.rental.dao.IVehicleDAO;
import pl.edu.wszib.car.rental.model.Vehicle;
import pl.edu.wszib.car.rental.services.IVehicleService;

import java.util.List;

@Service
public class VehicleServiceImpl implements IVehicleService {

    @Autowired
    IVehicleDAO vehicleDAO;

    @Override
    public Vehicle getVehicleByID(int id) {
        return this.vehicleDAO.getVehicleByID(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return this.vehicleDAO.getAllVehicles();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        this.vehicleDAO.persistVehicle(vehicle);
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        this.vehicleDAO.removeVehicle(vehicle);
    }
}
