package com.example.weatherstationapp;

import android.util.Log;
import android.widget.TextView;

import com.example.weatherstationapp.ui.Weather;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Humidity extends WeatherAtributes {

    public Humidity(){
       // SetWeather(weather);
        SetDate("");
        SetValue(0);
    }

    @Override
    public void GetLatestValue(final TextView humidity, final TextView dateTime) {

        String contentHumidity;

        AsyncHttpClient asyncClient = new AsyncHttpClient();

        asyncClient.get("https://weatherstation.conveyor.cloud/api/measurements/measurement/latest?type=humidity", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    String humidityValue = response.getString("value");
                    String humidityDateTime = response.getString("dateTime");

                    SetValue(Float.parseFloat(humidityValue));
                    SetDate(humidityDateTime);

                    humidity.setText("Umiditatea aerului: " + humidityValue + "%");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("Failed: ", ""+statusCode);
                Log.d("Error : ", "" + throwable);
            }
        });

        // MainActivity.Weather weather = new MainActivity.Weather();
//        try {
//            //get temperature
//            contentHumidity = weather.execute("https://weatherstation.conveyor.cloud/api/measurements/measurement/latest?type=humidity").get();
//
//            //check if data is was retrived succefully or not
//
//            JSONObject jsonHumidity = new JSONObject(contentHumidity);
//            String humidityValue = jsonHumidity.getString("value");
//            String humidityDateTime = jsonHumidity.getString("dateTime");
//
//            SetValue(Float.parseFloat(humidityValue));
//            SetDate(humidityDateTime);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public String GetValueByDate(String date) {
        return null;
    }
}