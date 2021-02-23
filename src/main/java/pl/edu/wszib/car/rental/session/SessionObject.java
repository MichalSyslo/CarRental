package pl.edu.wszib.car.rental.session;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.car.rental.model.User;

import java.sql.Timestamp;

@Component
@SessionScope
public class SessionObject {
    private User loggedUser = null;
    private String info = null;
    private Timestamp startDate;
    private Timestamp endDate;

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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
