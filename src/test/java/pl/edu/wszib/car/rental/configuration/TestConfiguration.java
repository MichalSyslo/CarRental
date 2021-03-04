package pl.edu.wszib.car.rental.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"pl.edu.wszib.car.rental.controllers",
        "pl.edu.wszib.car.rental.services",
        "pl.edu.wszib.car.rental.session"}
)
public class TestConfiguration {


}
