package ar.uba.fi.tdptp0.service.city;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CityService {


    private static final String FILE_NAME = "shortCityList.json";
    private static String cityJson;

    public CityService() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());
        cityJson = new String(Files.readAllBytes(file.toPath()));
    }

/*    public String getCity(String countryName) {
        ObjectMapper mapper = new ObjectMapper();
        Map cityMap = null;
        try {
            cityMap = mapper.readValue(cityJson, Map.class);
        } catch (IOException e) {
            //TODO Do something here
            return "error";
        }

        List<Object> cities = (List<Object>) cityMap.get("Students");
        Object[] delhiStudents = cities
                .stream()
                .filter(student -> ((Map)student).get("City").equals("Delhi"))
                .toArray();

        return "ac";
    }*/

    public String getCity(String countryName) {
        JSONArray jsonArray = new JSONArray(cityJson);
        Object[] abc = StreamSupport.stream(jsonArray.spliterator(), false)
                .map(JSONObject.class::cast)
                .filter(o -> o.get("country").equals(countryName))
                .toArray();
        return abc.toString();
    }
}
