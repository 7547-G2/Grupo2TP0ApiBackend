package ar.uba.fi.tdptp0.service.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);
    private static final String CITIES_KEY = "cities";
    private static final String COUNTRY_KEY = "country";

    private static String cityJson;
    private static List<Object> citiesList;

    public CityService(String fileName) throws IOException {
        LOGGER.info("Trying to load CityService with filename: {}", fileName);
        File file = new File(ClassLoader.getSystemResource(fileName).getPath());
        cityJson = new String(Files.readAllBytes(file.toPath()));
        LOGGER.info("City Json: {}", cityJson);
        ObjectMapper mapper = new ObjectMapper();
        Map cityMap = mapper.readValue(cityJson, Map.class);
        citiesList = (List<Object>) cityMap.get(CITIES_KEY);
    }

    public String getCity(String countryName) {
        Object[] filteredCities = citiesList
                .stream()
                .filter(student -> ((Map)student).get(COUNTRY_KEY).equals(countryName))
                .toArray();

        Gson gson = new Gson();
        String result = gson.toJson(filteredCities);

        return result;
    }
}
