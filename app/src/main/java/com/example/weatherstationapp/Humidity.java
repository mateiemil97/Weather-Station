package com.example.weatherstationapp.ui;

import com.example.weatherstationapp.MainActivity;
import com.example.weatherstationapp.WeatherAtributes;
import com.example.weatherstationapp.ui.home.Weather;

import org.json.JSONObject;

public class Humidity extends WeatherAtributes {

    public Humidity(Weather weather){
        SetWeather(weather);
    }

    @Override
    public void GetLatestValue() {

        String contentHumidity;
        // MainActivity.Weather weather = new MainActivity.Weather();
        try {
            //get temperature
            contentHumidity = weather.execute("https://weatherstation.conveyor.cloud/api/measurements/measurement/latest?type=temperature").get();

            //check if data is was retrived succefully or not

            JSONObject jsonHumidity = new JSONObject(contentHumidity);
            String humidityValue = jsonHumidity.getString("value");
            String humidityDateTime = jsonHumidity.getString("dateTime");

            SetValue(Float.parseFloat(humidityValue));
            SetDate(humidityDateTime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}