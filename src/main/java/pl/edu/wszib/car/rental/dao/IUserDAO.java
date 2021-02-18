package pl.edu.wszib.car.rental.dao;

import pl.edu.wszib.car.rental.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    boolean persistUser(User user);
}
