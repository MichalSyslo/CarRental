package pl.edu.wszib.car.rental.services.impl;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.car.rental.configuration.TestConfiguration;
import pl.edu.wszib.car.rental.dao.IBookingDAO;
import pl.edu.wszib.car.rental.dao.IHistoryDAO;
import pl.edu.wszib.car.rental.dao.IUserDAO;
import pl.edu.wszib.car.rental.dao.IVehicleDAO;
import pl.edu.wszib.car.rental.model.User;
import pl.edu.wszib.car.rental.model.view.RegistrationModel;
import pl.edu.wszib.car.rental.services.IUserService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBookingDAO bookingDAO;

    @MockBean
    IHistoryDAO historyDAO;

    @MockBean
    IVehicleDAO vehicleDAO;

    @Resource
    SessionObject sessionObject;


    @Test
    public void correctRegistrationTest(){
        RegistrationModel registrationModel = new RegistrationModel("jimmy123", "jimmy123", "jimmy123", "jimmy", "jim");
        Mockito.when(this.userDAO.getUserByLogin("jimmy123")).thenReturn(null);
        Mockito.when(this.userDAO.persistUser(ArgumentMatchers.any())).thenReturn(true);

        boolean result = this.userService.register(registrationModel);

        Assert.assertTrue(result);
    }

    @Test
    public void incorrectRegistrationTest(){
        RegistrationModel registrationModel = new RegistrationModel("jimmy123", "jimmy123", "jimmy123", "jimmy", "jim");
        Mockito.when(this.userDAO.getUserByLogin("jimmy123")).thenReturn(new User());

        boolean result = this.userService.register(registrationModel);

        Assert.assertFalse(result);
    }

    @Test
    public void correctAuthenticationTest(){
        User user = new User("iza123", "iza123", "Izabella", "Izabella", User.Role.USER );

        Mockito.when(this.userDAO.getUserByLogin("iza123")).thenReturn(new User("iza123", "iza123", "Izabella", "Izabella", User.Role.USER ));

        this.userService.authenticate(user);

        Assert.assertNotNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void incorrectLoginAuthenticationTest() {
        User user = new User("tomek123", "tomek123", "tomasz", "tomicki", User.Role.USER);
        Mockito.when(this.userDAO.getUserByLogin("tomek123")).thenReturn(null);

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

    @Test
    public void incorrectPasswordAuthenticationTest() {
        User user = new User("tomek123", "tomek123", "tomasz", "tomicki", User.Role.USER);
        Mockito.when(this.userDAO.getUserByLogin("tomek123")).thenReturn(new User("tomek123", "tomek567", "tomasz", "tomicki", User.Role.USER));

        this.userService.authenticate(user);

        Assert.assertNull(this.sessionObject.getLoggedUser());
    }

}
