package pl.edu.wszib.car.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wszib.car.rental.configuration.AppConfiguration;


@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class);
    }
}
