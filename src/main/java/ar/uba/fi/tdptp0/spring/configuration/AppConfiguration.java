package ar.uba.fi.tdptp0.spring.configuration;

import ar.uba.fi.tdptp0.service.city.CityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfiguration {

    @Bean
    public CityService cityService(@Value("${app.files.cities}") String fileName) throws IOException {
        return new CityService(fileName);
    }
}
