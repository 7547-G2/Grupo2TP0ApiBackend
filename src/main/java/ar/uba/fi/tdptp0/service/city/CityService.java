package ar.uba.fi.tdptp0.service.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class CityService {

    private static final String CITIES_KEY = "cities";
    private static final String COUNTRY_KEY = "country";

    private static String cityJson;
    private static List<Object> citiesList;

    public CityService(String fileName) throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream(fileName);
        cityJson = IOUtils.toString(inputStream, "UTF-8");
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

    public String getCity() {
        return cityJson;
    }
}
