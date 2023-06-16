package com.example.findweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Ekaterinburg,ru&APPID=579d66ae4b212f4bb525d7928ef3f71f");
            new GetDataFromInternet().execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new GetDataFromInternet().execute();
    }
}