package pl.edu.wszib.car.rental.model.view;

public class RegistrationModel {
    String login;
    String password;
    String password2;

    public RegistrationModel(String login, String password, String password2) {
        this.login = login;
        this.password = password;
        this.password2 = password2;
    }

    public RegistrationModel() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}


