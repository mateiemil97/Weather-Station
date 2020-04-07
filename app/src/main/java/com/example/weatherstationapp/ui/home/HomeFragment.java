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

import static androidx.lifecycle.ViewModelProviders.*;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button refreshButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);


        Weather weather1 = new Weather();
        Weather weather2 = new Weather();

        WeatherAtributes temperature = new Temperature(weather1);
        WeatherAtributes humidity = new Humidity(weather2);
        //Get data
        temperature.GetLatestValue();
        float temp = temperature.GetValue();
        String dateTimeTemperature = temperature.GetDateTime();
        final TextView tempValue = root.findViewById(R.id.temperatureValue);
        tempValue.setText("Temperatura: " + Float.toString(temp) + "°C");

        final TextView dateValue = root.findViewById(R.id.actualizare_id);
        if(!temperature.GetDateTime().isEmpty()) {
            String[] arrOfDate = temperature.GetDateTime().split("T", 2);
            String[] time = arrOfDate[1].split(":", 3);
            dateValue.setText("Ultima actualizare:  " + arrOfDate[0] + " - " + time[0] + ":" + time[1]);
        }
        humidity.GetLatestValue();
        float hum = humidity.GetValue();
        final TextView humidityValue = root.findViewById(R.id.humidityValue);
        humidityValue.setText("Umiditatea aerului: " + Float.toString(hum) + "%");
        Log.i("humidity",Float.toString(humidity.GetValue()));


        refreshButton = root.findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Weather weatherTemp = new Weather();
                Weather weatherHum = new Weather();


                WeatherAtributes temperature = new Temperature(weatherTemp);
                WeatherAtributes humidity = new Humidity(weatherHum);
                //Get data

                humidity.GetLatestValue();
                float hum = humidity.GetValue();
                TextView humidityValue =(TextView) root.findViewById(R.id.humidityValue);
                humidityValue.setText("Umiditatea aerului: " + Float.toString(hum) + "%");

                temperature.GetLatestValue();
                float temp = temperature.GetValue();
                if(!temperature.GetDateTime().isEmpty()) {
                    String[] arrOfDate = temperature.GetDateTime().split("T", 2);
                    String[] time = arrOfDate[1].split(":", 3);
                    dateValue.setText("Ultima actualizare:  " + arrOfDate[0] + " - " + time[0] + ":" + time[1]);
                }
                String dateTimeTemperature = temperature.GetDateTime();
                TextView tempValue = root.findViewById(R.id.temperatureValue);
                tempValue.setText("Temperatura: " + Float.toString(temp) + "°C");

                final TextView dateValue = root.findViewById(R.id.actualizare_id);
                String[] arrOfDate = temperature.GetDateTime().split("T", 2);
                String[] time = arrOfDate[1].split(":",3);
                dateValue.setText("Ultima actualizare:  " + arrOfDate[0] +" - " + time[0] + ":" + time[1]);
            }
        });
        return root;
    }
}