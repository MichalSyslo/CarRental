package pl.edu.wszib.car.rental.model.view;

public class RegistrationModel {
    private String login;
    private String password;
    private String password2;
    private String name;
    private String surname;

    public RegistrationModel(String login, String password, String password2, String name, String surname) {
        this.login = login;
        this.password = password;
        this.password2 = password2;
        this.name = name;
        this.surname = surname;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}


