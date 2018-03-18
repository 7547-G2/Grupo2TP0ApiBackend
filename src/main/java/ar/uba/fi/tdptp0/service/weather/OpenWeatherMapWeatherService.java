package ar.uba.fi.tdptp0.service.weather;

import ar.uba.fi.tdptp0.spring.client.OpenWeatherMapClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.json.simple.JSONObject;

import com.google.gson.JsonArray;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class OpenWeatherMapWeatherService implements WeatherService {

    private OpenWeatherMapClient openWeatherMapClient;

    @Autowired
    public OpenWeatherMapWeatherService(OpenWeatherMapClient openWeatherMapClient) {
        this.openWeatherMapClient = openWeatherMapClient;
    }

    @Override
    public String forecastData(Long id) {
        String jsonString = openWeatherMapClient.getWeather(id);
        JSONParser parser = new JSONParser();
        JSONArray listOf5Days = new JSONArray();
        try {
            JSONObject json = (JSONObject) parser.parse(jsonString);
            JSONArray listOfDays = (JSONArray) json.get("list");
            int cantidad = 0;
            for(int index = 0; (cantidad < 10) && (index<=listOfDays.size());index++){
                JSONObject oneTemp = (JSONObject) listOfDays.get(index);
                if (((String)oneTemp.get("dt_txt")).contains("12:00:00") 
                    || ((String)oneTemp.get("dt_txt")).contains("00:00:00")){
                    JSONObject oneMain = (JSONObject) oneTemp.get("main");
                    double value = Double.parseDouble(oneMain.get("temp").toString());
                    oneTemp.put("temp_celsius",new Double(value -273.15));
                    value = Double.parseDouble(oneMain.get("temp_min").toString());                    
                    oneTemp.put("temp_min_celsius",new Double(value -273.15));
                    value = Double.parseDouble(oneMain.get("temp_max").toString());                    
                    oneTemp.put("temp_max_celsius",new Double(value -273.15));
                    listOf5Days.add(oneTemp);
                    cantidad++;
                }
            }    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listOf5Days.toString();
    }
}
