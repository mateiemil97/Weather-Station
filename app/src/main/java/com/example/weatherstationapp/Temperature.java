package com.example.weatherstationapp;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.weatherstationapp.ui.Weather;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Temperature extends WeatherAtributes {

    public Temperature(Weather weather){
        SetWeather(weather);
        SetDate("");
        SetValue(0);
    }
    @Override
    public void GetLatestValue() {

        String contentTemperature;
        // MainActivity.Weather weather = new MainActivity.Weather();
        try {
            //get temperature
            contentTemperature = weather.execute("https://weatherstation.conveyor.cloud/api/measurements/measurement/latest?type=temperature").get();

            //check if data is was retrived succefully or not

            JSONObject jsonTemperature = new JSONObject(contentTemperature);
            String temperatureValue = jsonTemperature.getString("value");
            String temperatureDateTime = jsonTemperature.getString("dateTime");

            SetValue(Float.parseFloat(temperatureValue));
            SetDate(temperatureDateTime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String GetValueByDate(String date) {
        String measurementList="";
       // Volley.newRequestQueue(getActivity().getApplicationContext());
        return measurementList;
    }
}
