package pl.edu.wszib.car.rental.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired


    @Resource
    SessionObject sessionObject;
}
