    package com.example.weatherstationapp.ui.notifications;

    import android.app.DatePickerDialog;
    import android.graphics.Color;
    import android.graphics.drawable.ColorDrawable;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.DatePicker;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import android.widget.TextView;
    import androidx.annotation.Nullable;
    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.Observer;
    import androidx.lifecycle.ViewModelProviders;

    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.JsonArrayRequest;
    import com.android.volley.toolbox.JsonObjectRequest;
    import com.android.volley.toolbox.Volley;
    import com.example.weatherstationapp.MainActivity;
    import com.example.weatherstationapp.Measurement;
    import com.example.weatherstationapp.R;
    import com.example.weatherstationapp.ui.Weather;
    import com.github.mikephil.charting.charts.BarChart;
    import com.github.mikephil.charting.charts.LineChart;
    import com.github.mikephil.charting.components.AxisBase;
    import com.github.mikephil.charting.components.Description;
    import com.github.mikephil.charting.components.XAxis;
    import com.github.mikephil.charting.components.YAxis;
    import com.github.mikephil.charting.data.BarData;
    import com.github.mikephil.charting.data.BarDataSet;
    import com.github.mikephil.charting.data.BarEntry;
    import com.github.mikephil.charting.data.Entry;
    import com.github.mikephil.charting.data.LineData;
    import com.github.mikephil.charting.data.LineDataSet;
    import com.github.mikephil.charting.formatter.IAxisValueFormatter;
    import com.github.mikephil.charting.formatter.ValueFormatter;
    import com.google.android.material.snackbar.Snackbar;
    import com.google.gson.JsonArray;
    import com.google.gson.JsonObject;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Collections;
    import java.util.Date;
    import java.util.List;
    import java.util.TimeZone;
    //import com.github.mikephil.charting.charts.BarChart;

    public class NotificationsFragment extends Fragment {

    //  private BarChart barChart;
    private NotificationsViewModel notificationsViewModel;
    private LineChart chart;
        private TextView mDisplayDate;
        private DatePickerDialog.OnDateSetListener mDateSetListener;
        final List<Entry> measurements = new ArrayList<>();
        final List<Measurement> measurementsSorted = new ArrayList<>();
        String dateToSend;
        private Button showData;

        public View onCreateView(@NonNull LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        chart = view.findViewById(R.id.chartId);
    RadioGroup radioGroup = (RadioGroup) view .findViewById(R.id.radioGroupId);
        mDisplayDate = (TextView) view.findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.i("TAG", "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

                String date = month + "/" + day + "/" + year;
                String dateToSHow = day + "/" + month + "/" + year;
                dateToSend = date;
                mDisplayDate.setText(dateToSHow);


            }
        };

        showData = view.findViewById(R.id.buttonShowId);
        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton temp = view.findViewById(R.id.radioButtonTemp);
                RadioButton hum = view.findViewById(R.id.radioButtonHum);
                if(temp.isChecked())
                {
                    GetDataFromBackend(view,"temperature");
                }
                else if(hum.isChecked())
                {
                    GetDataFromBackend(view,"humidity");
                }
            }
        });

    chart = (LineChart) view.findViewById(R.id.chartId);
    return view;
    }
        public void GetDataFromBackend(final  View view, final String type)
        {
            measurements.clear();
            measurementsSorted.clear();
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            // Initialize a new JsonArrayRequest instance
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    "https://weatherstation.conveyor.cloud/api/measurements?type="+type+"&date="+dateToSend,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            // Process the JSON
                            try{
                                // Loop through the array elements
                                for(int i=0;i<response.length();i++){
                                    // Get current json object
                                    JSONObject measurement = response.getJSONObject(i);
                                    int value = Integer.parseInt(measurement.getString("value"));
                                    String dateTime =  measurement.getString("dateTime");

                                    String hour="";
                                    if(!dateTime.isEmpty()) {
                                        String[] arrOfDate = dateTime.split("T", 2);
                                        String[] time = arrOfDate[1].split(":", 3);
                                        hour = time[0];
                                    }
                                    measurementsSorted.add(new Measurement(value,hour));

                                    Log.i("obj",hour);

                                    // Display the formatted json data in text view
                                }

                                Collections.sort(measurementsSorted);

                                for (Measurement meas: measurementsSorted) {
                                    Log.i("Date",meas.GetDateTime());
                                    Log.i("Val",Float.toString(meas.GetValue()));
                                    measurements.add(new Entry(Integer.parseInt(meas.GetDateTime()),meas.GetValue()));
                                }

                                    LineDataSet lineDataSet = new LineDataSet(measurements, type == "temperature"?"temp(°C) ^   ora(h) >" : "umid(%) ^   ora(h) > ");
                                    LineData lineData = new LineData(lineDataSet);
                                    // lineDataSet.setCubicIntensity(0.5f);
                                    lineData.setValueTextSize(15);
                                    lineData.setValueTextColor(Color.parseColor("#FFA000"));
                                        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
                                    chart.setData(lineData);
                                    chart.setVisibility(view.VISIBLE);
                                    lineDataSet.setColor(Color.parseColor("#FFA000"));
                                    lineDataSet.setCircleColor(Color.parseColor("#FFA000"));
                                    //graph fill params
                                    lineDataSet.setDrawFilled(true);

                                    //        circle params
                                    lineDataSet.setCircleRadius(2.5f);
                                    //lineDataSet.setCircleColor(primaryColor);
                                    lineDataSet.setDrawCircleHole(false);
                                    LineData lineDat = new LineData(lineDataSet);
                                    lineData.setDrawValues(false);
                                    chart.setData(lineData);

                                    XAxis xAxis = chart.getXAxis();
                                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

                                    xAxis.setCenterAxisLabels(true);

                                    YAxis yAxisRight = chart.getAxisRight();
                                    yAxisRight.setDrawLabels(false);
                                    yAxisRight.setDrawGridLines(false);

                                    YAxis yAxisLeft = chart.getAxisLeft();
                                    yAxisLeft.setDrawGridLines(false);
                                    yAxisLeft.setSpaceBottom(0.1f);

                                    Description description = new Description();
                                    description.setText(type == "temperature"?"Evolutia temperaturii pe zi":"Evolutia umiditatii pe zi");
                                    description.setTextSize(15);
                                    description.setTextColor(Color.parseColor("#FFA000"));
                                    chart.setDescription(description);
                                    lineDataSet.setDrawValues(false);
                                    lineDataSet.setDrawCircles(false);
                                    chart.invalidate();
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                        }
                    }
            );
            requestQueue.add(jsonArrayRequest);
        }
    }