package ar.uba.fi.tdptp0.spring.controller;

import ar.uba.fi.tdptp0.service.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CitiesController {

    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "/city/country/{countryName}", produces = {"application/json"})
    public String getCities(@PathVariable("countryName") String countryName) {
        return cityService.getCity(countryName);
    }
}
