package com.example.dipon.weatherappsimpleV1.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dipon on 3/28/2017.
 */

public class Forecast implements JSONPopulator {
    private String forecast = new String("");

    public String getForecast() {
        return forecast;
    }

    public int sizeTest;
    public List<Day> getDayList() {
        return dayList;
    }

    public List<Day> dayList = new ArrayList<>();
    @Override
    public void populate(JSONObject data) {
        forecast = data.optString("forecast");
        try {
            JSONArray jsonarray = new JSONArray(forecast);
            sizeTest = jsonarray.length();
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                Day day = new Day();

                day.code = jsonobject.optInt("code");
                day.day = jsonobject.optString("day");
                day.text = jsonobject.optString("text");
                day.date = jsonobject.optString("date");
                day.high = jsonobject.optString("high");
                day.low = jsonobject.optString("low");
                dayList.add(day);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
