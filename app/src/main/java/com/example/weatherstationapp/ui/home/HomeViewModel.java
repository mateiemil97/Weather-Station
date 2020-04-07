package com.example.weatherstationapp.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherstationapp.Temperature;
import com.example.weatherstationapp.WeatherAtributes;
import com.example.weatherstationapp.Humidity;
import com.example.weatherstationapp.ui.Weather;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {

        Weather weather = new Weather();

        WeatherAtributes temperature = new Temperature(weather);

        //Get data
        temperature.GetLatestValue();
        float temp = temperature.GetValue();
        String dateTimeTemperature = temperature.GetDateTime();

        WeatherAtributes humidity = new Humidity(weather);

        humidity.GetLatestValue();
        float hum = humidity.GetValue();
        String dateTimeHumidity = humidity.GetDateTime();

        //Set data to ui

      //  TextView myAwesomeTextView = (TextView)findViewById(R.id.temperatureValue);

//in your OnCreate() method
//
        //myAwesomeTextView.setText(String.valueOf(temp));
    }

}