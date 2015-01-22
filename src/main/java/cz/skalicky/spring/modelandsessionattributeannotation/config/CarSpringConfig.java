package cz.skalicky.spring.modelandsessionattributeannotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.skalicky.spring.modelandsessionattributeannotation.controller.CarController;

@Configuration
@Import(HttpMessageConverterConfig.class)
public class CarSpringConfig {

    @Bean
    public CarController carController() {
        return new CarController();
    }

}
