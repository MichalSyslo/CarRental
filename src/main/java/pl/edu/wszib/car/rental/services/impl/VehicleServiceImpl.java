package pl.edu.wszib.car.rental.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.car.rental.dao.IVehicleDAO;
import pl.edu.wszib.car.rental.model.Vehicle;
import pl.edu.wszib.car.rental.services.IVehicleService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;
import java.util.Base64;
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

        return this.vehicleDAO.getAvailableVehicles(this.sessionObject.getStartDate(), this.sessionObject.getEndDate());
    }

    @Override
    public boolean addVehicle(Vehicle vehicle, MultipartFile multipartFile) {
        if(vehicle.getName().equals("") || vehicle.getSeats()==0 || vehicle.getBootCapacity() == 0 || vehicle.getMileage()==0){
            this.sessionObject.setInfo("Please, provide all necessary information");
            return false;
        }
        try {
            vehicle.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.vehicleDAO.persistVehicle(vehicle);
        return true;
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        this.vehicleDAO.removeVehicle(vehicle);
    }
}
