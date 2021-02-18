package pl.edu.wszib.car.rental.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.car.rental.dao.IUserDAO;
import pl.edu.wszib.car.rental.model.User;
import pl.edu.wszib.car.rental.model.view.RegistrationModel;
import pl.edu.wszib.car.rental.services.IUserService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(User user) {
        User userFromDB = this.userDAO.getUserByLogin(user.getLogin());
        if(userFromDB == null){ return; }
        if(user.getPassword().equals(userFromDB.getPassword())){
            this.sessionObject.setLoggedUser(userFromDB);
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }

    @Override
    public boolean register(RegistrationModel registrationModel) {
        if(this.userDAO.getUserByLogin(registrationModel.getLogin()) != null){
            return false;
        }
        User user = new User(0, registrationModel.getLogin(), registrationModel.getPassword(), User.Role.USER);

        return this.userDAO.persistUser(user);
    }

}
