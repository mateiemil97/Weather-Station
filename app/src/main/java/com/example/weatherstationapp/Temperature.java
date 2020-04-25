package com.example.weatherstationapp;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.weatherstationapp.ui.Weather;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Temperature extends WeatherAtributes {

    public Temperature(){
        SetDate("");

    }
    @Override
    public void GetLatestValue(final TextView temperature, final TextView dateTime) {

        String contentTemperature;

            AsyncHttpClient asyncClient = new AsyncHttpClient();

            asyncClient.get("https://weatherstation.conveyor.cloud/api/measurements/measurement/latest?type=temperature", new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                    try {
                        String temperatureValue = response.getString("value");
                        String temperatureDateTime = response.getString("dateTime");

                        SetValue(Float.parseFloat(temperatureValue));
                        SetDate(temperatureDateTime);

                        temperature.setText("Temperatura: " + temperatureValue + "°C");
                        Log.e("date",temperatureDateTime);
                        if(!temperatureDateTime.isEmpty()) {
                            String[] arrOfDate = temperatureDateTime.split("T", 2);
                            Log.e("date",arrOfDate[0]);
                            String[] time = arrOfDate[1].split(":", 3);
                            dateTime.setText("Ultima actualizare:  " + arrOfDate[0] + " - " + time[0] + ":" + time[1]);
                        }

                    } catch (JSONException e) {
                       // e.printStackTrace();
                    }
                }
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.d("Failed: ", ""+statusCode);
                    Log.d("Error : ", "" + throwable);
                }
            });



    }
    @Override
    public String GetValueByDate(String date) {
        String measurementList="";
       // Volley.newRequestQueue(getActivity().getApplicationContext());
        return measurementList;
    }
}
