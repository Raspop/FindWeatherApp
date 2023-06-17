package com.example.findweatherapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetDataFromInternet extends AsyncTask<URL, Void, String> {
    private static final String TAG = "GetDataFromInternet";

    public interface AsyncResponse{
        void processFinish(String output);
    }
    public AsyncResponse delegate;

    public GetDataFromInternet(AsyncResponse delegate){
        this.delegate = delegate;
    }

    protected String getResponseFromHttpGetUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasInputStream = scanner.hasNext();
            String result = null;
            if (hasInputStream) {
                result = scanner.next();
            }
            return result;
        } finally {
            urlConnection.disconnect();
        }
    }

    @Override
    protected String doInBackground(URL[] urls) {
        String result = null;
        URL urlQuerry =  urls[0];
        try {
            result = getResponseFromHttpGetUrl(urlQuerry);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: called");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(TAG, "onPostExecute: called");
        Log.d(TAG, "onPostExecute: " + result);
        delegate.processFinish(result);

    }

}
