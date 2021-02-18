package pl.edu.wszib.car.rental.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

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

        return "/cars";
    }

    @RequestMapping(value="/cars", method = RequestMethod.POST)
    public String carsSubmit(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        System.out.println("1111111111111111");
        return "/home";
    }
}
