package pl.edu.wszib.car.rental.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.car.rental.model.User;
import pl.edu.wszib.car.rental.services.IVehicleService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;


@Controller
public class CommonController {

    @Autowired
    IVehicleService vehicleService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String landingPage(){

        return "redirect:/home";
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        return "/home";
    }

    @RequestMapping(value="/contact", method = RequestMethod.GET)
    public String contact(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        return "/contact";
    }

    @RequestMapping(value="/cars", method = RequestMethod.GET)
    public String cars(Model model){

        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        model.addAttribute("vehicles", (this.sessionObject.isLogged() && this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN)
                ? this.vehicleService.getAvailableVehicles() : this.vehicleService.getAllVehicles());

        if((this.sessionObject.getStartDate() == null || this.sessionObject.getEndDate() == null)  && this.sessionObject.isLogged()
                && this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN){
            return "redirect:/bookingPeriod";
        }

        return "/cars";
    }
}
