package com.example.findweatherapp;

import android.os.AsyncTask;
import android.util.Log;

public class GetDataFromInternet extends AsyncTask {
    private static final String TAG = "GetDataFromInternet";
    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: called");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        Log.d(TAG, "onPostExecute: called");
        super.onPostExecute(o);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.d(TAG, "doInBackground: called");
        return null;
    }
}
