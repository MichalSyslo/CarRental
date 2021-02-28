package pl.edu.wszib.car.rental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.car.rental.model.User;
import pl.edu.wszib.car.rental.services.IHistoryService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class HistoryController {

    @Autowired
    IHistoryService historyService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String history(Model model){
        if(!this.sessionObject.isLogged()){
            return "redirect:/login";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("historyInfo", this.sessionObject.getLoggedUser().getRole() == User.Role.ADMIN
                ? this.historyService.getAllFullHistoryInfo()
                : this.historyService.getFullHistoryInfoByLogin(this.sessionObject.getLoggedUser().getLogin()));

        return "/history";
    }

    @RequestMapping(value = "/history-search", method = RequestMethod.GET)
    public String reservationsSearch(Model model, @RequestParam String name, @RequestParam String surname){
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN){
            return "redirect:/history";
        }
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        model.addAttribute("info", this.sessionObject.getInfo());
        model.addAttribute("historyInfo", this.historyService.getFullHistoryInfoByName(name, surname));

        return "/history";
    }
}
