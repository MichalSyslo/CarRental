package pl.edu.wszib.car.rental.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.car.rental.model.User;
import pl.edu.wszib.car.rental.model.Vehicle;
import pl.edu.wszib.car.rental.services.IVehicleService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired
    IVehicleService vehicleService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addVehicle", method = RequestMethod.GET)
    public String addCarForm(Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN){
            return "redirect:/login";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("vehicleModel", new Vehicle());

        return "/addVehicle";
    }

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public String addCarSubmit(@ModelAttribute Vehicle vehicle){
        this.vehicleService.addVehicle(vehicle);

        return "redirect:/cars";
    }

    @RequestMapping(value = "/removeVehicle/{id}", method = RequestMethod.GET)
    public String removeCarForm(@PathVariable int id){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN){
            return "redirect:/login";
        }
        this.vehicleService.removeVehicle(this.vehicleService.getVehicleByID(id));

        return "redirect:/cars";
    }

    @RequestMapping(value = "/editVehicle/{id}", method = RequestMethod.GET)
    public String editVehicleForm(@PathVariable int id, Model model){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN){
            return "redirect:/login";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("vehicleModel", this.vehicleService.getVehicleByID(id));

        return "/editVehicle";
    }

    @RequestMapping(value = "/editVehicle/{id}", method = RequestMethod.POST)
    public String editVehicleForm(@ModelAttribute Vehicle vehicle){
        this.vehicleService.updateVehicle(vehicle);

        return "redirect:/cars";
    }
}
