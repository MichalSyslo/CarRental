package pl.edu.wszib.car.rental.services;

import pl.edu.wszib.car.rental.model.User;
import pl.edu.wszib.car.rental.model.view.RegistrationModel;

public interface IUserService {
    void authenticate(User user);
    void logout();
    boolean register(RegistrationModel registrationModel);
}
