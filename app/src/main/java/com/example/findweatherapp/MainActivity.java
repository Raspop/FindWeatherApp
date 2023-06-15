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
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather/?q=Kazan&appid=4a0c12f2644c638207a1f1cfa680aed6");
            new GetDataFromInternet().execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new GetDataFromInternet().execute();
    }
}