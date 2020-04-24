package com.example.weatherstationapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherstationapp.Humidity;
import com.example.weatherstationapp.R;
import com.example.weatherstationapp.Temperature;
import com.example.weatherstationapp.WeatherAtributes;
import com.example.weatherstationapp.ui.Weather;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static androidx.lifecycle.ViewModelProviders.*;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button refreshButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);


        WeatherAtributes temperature = new Temperature();
        WeatherAtributes humidity = new Humidity();
        //Get data


        final TextView tempValue = root.findViewById(R.id.temperatureValue);
        final TextView dateValue = root.findViewById(R.id.actualizare_id);
        final TextView humidityValue = root.findViewById(R.id.humidityValue);

        temperature.GetLatestValue(tempValue,dateValue);
        humidity.GetLatestValue(humidityValue,dateValue);

        refreshButton = root.findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherAtributes temperature = new Temperature();
                WeatherAtributes humidity = new Humidity();
                final TextView tempValue = root.findViewById(R.id.temperatureValue);
                final TextView dateValue = root.findViewById(R.id.actualizare_id);
                final TextView humidityValue = root.findViewById(R.id.humidityValue);

                temperature.GetLatestValue(tempValue,dateValue);
                humidity.GetLatestValue(humidityValue,dateValue);
            }
        });
        return root;
    }

}