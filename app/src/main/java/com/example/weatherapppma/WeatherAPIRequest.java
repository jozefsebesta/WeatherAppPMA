package com.example.weatherapppma;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WeatherAPIRequest {
    static final String API_KEY = "e288a157aca3f5b09c6a8fbf1e0fdb4f";
    static final String URL_Base = "http://api.weatherstack.com/";
    static final String current = "current";

    static final String URL = URL_Base + current + "?access_key=" + API_KEY;

    //http://api.weatherstack.com/current
    //    ? access_key = YOUR_ACCESS_KEY


  public  static String GetRequest(String input)
    {
        AsyncTask<String,String,String> task = new RequestTask(MainActivity.mainActivity).execute(URL + "&query=" + input);
        String res = null;
        try {
            String requestResult = task.get();
            JSONObject reader = new JSONObject(requestResult);

            JSONObject location  = reader.getJSONObject("location");
            String cityName = location.getString("name");
            JSONObject current  = reader.getJSONObject("current");
            String temperature = current.getString("temperature");

            res = cityName + "," + temperature;


        }
        catch (Exception e) {
            Log.println(Log.ERROR, "REQUEST_TASK",e.getMessage());
        }

        return res;
    }


    static class RequestTask extends AsyncTask<String, String, String> {

        private MainActivity activity;
        RequestTask(MainActivity activity)
        {
            this.activity = activity;
        }


        @Override
        protected String doInBackground(String... uri) {
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                java.net.URL url = new URL(uri[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                }
                else {
                    // response = "FAILED"; // See documentation for more info on response handling
                }
            } catch (Exception e){
                Log.println(Log.ERROR, "REQUEST_TASK",e.getMessage());
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

}