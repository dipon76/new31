package com.example.dipon.weatherappsimpleV1.services;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.dipon.weatherappsimpleV1.Data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Dipon on 3/28/2017.
 */

public class YahooWeatherService {

    public WeatherServiceCallBack callBack;
    public String location;
    public Exception error;

    public YahooWeatherService(WeatherServiceCallBack weatherServiceCallBack) {
        this.callBack = weatherServiceCallBack;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(final String location){
        this.location = location;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'", params[0]);

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream  inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine())!= null){
                        result.append(line);
                    }

                    return result.toString();

                } catch (Exception e) {
                   error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
               if(s == null && error !=null)
               {
                   callBack.serviceFailure(error);
                   return;
               }

                try {
                    JSONObject data = new JSONObject(s);
                    JSONObject queryResult = data.optJSONObject("query");
                    int count = queryResult.optInt("count");

                    if(count == 0){
                        callBack.serviceFailure(new LocationError("No Info are found for the city :"+location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResult.optJSONObject("results").optJSONObject("channel"));
                    callBack.serviceSuccess(channel);

                } catch (JSONException e) {
                   callBack.serviceFailure(e);
                }
            }
        }.execute(location);
    }

    public class LocationError extends Exception {
        public LocationError(String message) {
            super(message);
        }
    }
}
