package pl.edu.wszib.car.rental.session;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.car.rental.model.User;

@Component
@SessionScope
public class SessionObject {
    private User loggedUser = null;
    private String info = null;

    public boolean isLogged(){
        return this.loggedUser != null;
    }

    public String getInfo() {
        String tmp = info;
        this.info = null;
        return tmp;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
