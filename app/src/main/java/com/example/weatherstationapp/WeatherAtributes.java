package com.example.weatherstationapp;

import com.example.weatherstationapp.ui.Weather;

import java.util.List;

public abstract class WeatherAtributes {
    private float _value;
    private String _dateTime;
    public Weather weather;

    public void SetWeather(Weather weather) {
        this.weather = weather;
    }

    public void SetValue(float value) {
        _value = value;
    }

    public void SetDate(String date) {
        _dateTime = date;
    }

    public float GetValue() {
        return _value;
    }

    public String GetDateTime() {
        return _dateTime;
    }

    public abstract void GetLatestValue();
    public abstract String GetValueByDate(String date);
}
