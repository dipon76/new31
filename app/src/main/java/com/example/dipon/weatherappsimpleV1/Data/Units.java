package com.example.dipon.weatherappsimpleV1.Data;

import org.json.JSONObject;

/**
 * Created by Dipon on 3/28/2017.
 */

public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}
