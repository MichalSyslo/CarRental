package pl.edu.wszib.car.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.car.rental.model.User;
import pl.edu.wszib.car.rental.model.view.BookingPeriodModel;
import pl.edu.wszib.car.rental.services.IBookingService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BookingController {

    @Autowired
    IBookingService bookingService;

    @Resource
    SessionObject sessionObject;



    @RequestMapping(value = "/bookingPeriod", method = RequestMethod.GET)
    public String bookingPeriodForm(Model model){
        if(!this.sessionObject.isLogged()){
            return "redirect:/login";
        }
        model.addAttribute("sessionObject", this.sessionObject);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("bookingPeriodModel", new BookingPeriodModel());
        model.addAttribute("info", this.sessionObject.getInfo());

        return "/bookingPeriod";
    }

    @RequestMapping(value = "/bookingPeriod", method = RequestMethod.POST)
    public String bookingPeriodSubmit(@ModelAttribute BookingPeriodModel bookingPeriodModel) {
        this.bookingService.setBookingPeriod(bookingPeriodModel);

        return "redirect:/cars";
    }

    @RequestMapping(value = "/book/{id}", method=RequestMethod.GET)
    public String bookCar(@PathVariable int id){
        this.bookingService.bookCarByID(id);

        return "redirect:/cars";
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String reservations(Model model){
        if(!this.sessionObject.isLogged()){
            return "redirect:/login";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("reservationsInfo", this.sessionObject.getLoggedUser().getRole() == User.Role.ADMIN
                ? this.bookingService.getAllFullReservationsInfo()
                : this.bookingService.getFullReservationsInfoByLogin(this.sessionObject.getLoggedUser().getLogin()));

        return "/reservations";
    }

    @RequestMapping(value = "/reservations-search", method = RequestMethod.GET)
    public String reservationsSearch(Model model, @RequestParam String name, @RequestParam String surname){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN){
            return "redirect:/reservations";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("reservationsInfo", this.bookingService.getFullReservationsInfoByName(name, surname));

        return "/reservations";
    }

    @RequestMapping(value = "/cancelReservation/{id}", method = RequestMethod.GET)
    public String cancelReservation(@PathVariable int id){
        this.bookingService.cancelReservationById(id);

        return "redirect:/reservations";
    }
}
