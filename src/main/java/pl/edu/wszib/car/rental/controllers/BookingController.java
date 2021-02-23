package pl.edu.wszib.car.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.car.rental.model.view.BookingPeriodModel;
import pl.edu.wszib.car.rental.services.IBookingService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
