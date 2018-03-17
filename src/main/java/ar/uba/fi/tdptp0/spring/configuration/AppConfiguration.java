package ar.uba.fi.tdptp0.spring.configuration;

import ar.uba.fi.tdptp0.service.city.CityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfiguration {

    @Bean
    public CityService weatherService() throws IOException {
        return new CityService();
    }
}
