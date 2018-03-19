package ar.uba.fi.tdptp0.service.weather;

import ar.uba.fi.tdptp0.spring.client.OpenWeatherMapClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.json.simple.JSONObject;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        JSONObject responseJson = new JSONObject();
        JSONObject json = new JSONObject();
        try {
            json = (JSONObject) parser.parse(jsonString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JSONArray listOfDays = (JSONArray) json.get("list");
        responseJson.put("id",((JSONObject)json.get("city")).get("id"));
        responseJson.put("name",((JSONObject)json.get("city")).get("name"));
        responseJson.put("country",((JSONObject)json.get("city")).get("country"));
        int cantidad = 0;
        List<Double> valoresTemperatura = new ArrayList<Double>();
        int dia = 0;
        String lastDate = "";
        boolean primero = true;
        JSONObject newDay = new JSONObject();
        for(int index = 0; (dia < 5) && (index<listOfDays.size());index++){
            JSONObject oneTemp = (JSONObject) listOfDays.get(index);

            if (((String)oneTemp.get("dt_txt")).contains("03:00:00")){
                if (primero){
                    Date date = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    lastDate = modifiedDate;
                    primero = false;
                }
                JSONObject oneMain = (JSONObject) oneTemp.get("main");
                Double value = new Double(Double.parseDouble(oneMain.get("temp").toString())-273.15);
                Double cantidadIteracion = 1.0;
                for (Double s : valoresTemperatura) {
                    value += s;
                    cantidadIteracion++;
                }
                value = value / cantidadIteracion;
                valoresTemperatura = new ArrayList<Double>();
                newDay.put("nightTemp",value);
                String date = ((String)oneTemp.get("dt_txt")).substring(0,10);
                newDay.put("date",lastDate);
                JSONArray oneWeather = (JSONArray) oneTemp.get("weather");
                JSONObject weather = (JSONObject) oneWeather.get(0);
                String icon = ((String)weather.get("icon"));
                newDay.put("nightIcon",icon);
                listOf5Days.add(newDay);
                newDay = new JSONObject();
                dia++;
                cantidad++;
            } else if (((String)oneTemp.get("dt_txt")).contains("15:00:00")){
                JSONObject oneMain = (JSONObject) oneTemp.get("main");
                Double value = new Double(Double.parseDouble(oneMain.get("temp").toString())-273.15);
                Double cantidadIteracion = 1.0;
                for (Double s : valoresTemperatura) {
                    value += s;
                    cantidadIteracion++;
                }
                value = value / cantidadIteracion;
                valoresTemperatura = new ArrayList<Double>();
                newDay.put("dayTemp",value);
                String date = ((String)oneTemp.get("dt_txt")).substring(0,10);
                newDay.put("date",date);
                JSONArray oneWeather = (JSONArray) oneTemp.get("weather");
                JSONObject weather = (JSONObject) oneWeather.get(0);
                String icon = ((String)weather.get("icon"));
                newDay.put("dayIcon",icon);
                lastDate = date;
                cantidad++;
            } else {
                JSONObject oneMain = (JSONObject) oneTemp.get("main");
                Double value = new Double(Double.parseDouble(oneMain.get("temp").toString())-273.15);
                valoresTemperatura.add(value);
            }
        }
        if (!newDay.toString().equals("{}") ){
            listOf5Days.add(newDay);
        }
        responseJson.put("info",listOf5Days);    

        return responseJson.toString();
    }

    // public String forecastData(Long id) {
    //     return openWeatherMapClient.getWeather(id);
    // }
}
