package com.example.findweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements GetDataFromInternet.AsyncResponse{
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Ekaterinburg,ru&APPID=579d66ae4b212f4bb525d7928ef3f71f");
            new GetDataFromInternet(this).execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void processFinish(String output) {
        Log.d(TAG, "processFinish: " + output);
        try {
            JSONObject resultJSON = new JSONObject(output);
            JSONObject weather = resultJSON.getJSONObject("main");
            JSONObject sys = weather.getJSONObject("sys");

            TextView temp = findViewById(R.id.tempValue);
            String temp_C = Float.toString(Float.parseFloat(weather.getString("temp")) - 273.15f);
            temp.setText(temp_C);

            TextView pressure = findViewById(R.id.pressureValue);
            pressure.setText(weather.getString("pressure"));

            TextView sunrise = findViewById(R.id.timeSunrise);
            String timeSunrise = sys.getString("sunrise");
            Locale myLocale = new Locale("ru", "RU");
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", myLocale);
            String dateString = formatter.format(new Date(Long.parseLong(timeSunrise)*1000+(60*60*1000)*3));
            sunrise.setText(dateString);

            TextView sunset = findViewById(R.id.timeSunset);
            String timeSunset = sys.getString("sunset");
            dateString = formatter.format(new Date(Long.parseLong(timeSunset)*1000+(60*60*1000)*3));
            sunset.setText(dateString);

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}