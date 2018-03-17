package ar.uba.fi.tdptp0.spring.controller;

import ar.uba.fi.tdptp0.service.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/weather/{id}", produces = {"application/json"})
    public String getWeather(@PathVariable("id") Long id) {
        return weatherService.forecastData(id);
    }
}
