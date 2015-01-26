package cz.skalicky.spring.modelandsessionattributeannotation.config;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.support.GenericConversionService;

import cz.skalicky.spring.modelandsessionattributeannotation.controller.CarController;
import cz.skalicky.spring.modelandsessionattributeannotation.convert.EnumWithIdConverterFactory;

@Configuration
@Import(HttpMessageConverterConfig.class)
public class CarSpringConfig {

    @Bean
    public CarController carController() {
        return new CarController();
    }

    @Inject
    private GenericConversionService conversionService;

    @PostConstruct
    private void addConverterFactory() {

        conversionService.addConverterFactory(new EnumWithIdConverterFactory());
    }

}
