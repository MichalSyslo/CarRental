package pl.edu.wszib.car.rental.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.car.rental.dao.IVehicleDAO;
import pl.edu.wszib.car.rental.model.Reservation;
import pl.edu.wszib.car.rental.model.Vehicle;
import pl.edu.wszib.car.rental.services.IVehicleService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class VehicleServiceImpl implements IVehicleService {

    @Autowired
    IVehicleDAO vehicleDAO;

    @Resource
    SessionObject sessionObject;


    @Override
    public Vehicle getVehicleByID(int id) {
        return this.vehicleDAO.getVehicleByID(id);
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        this.vehicleDAO.updateVehicle(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return this.vehicleDAO.getAllVehicles();
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> vehicles = this.vehicleDAO.getAvailableVehicles(this.sessionObject.getStartDate(), this.sessionObject.getEndDate());

        return vehicles;
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
