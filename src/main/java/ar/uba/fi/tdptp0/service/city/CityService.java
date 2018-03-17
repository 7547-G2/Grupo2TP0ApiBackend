package ar.uba.fi.tdptp0.service.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class CityService {

    private static final String CITIES_KEY = "cities";
    private static final String COUNTRY_KEY = "country";

    private static String cityJson;
    private static List<Object> citiesList;

    public CityService(String fileName) throws IOException {
        File file = new File(ClassLoader.getSystemResource(fileName).getPath());
        cityJson = new String(Files.readAllBytes(file.toPath()));
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
