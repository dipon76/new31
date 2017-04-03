package com.example.dipon.weatherappsimpleV1.Data;

import org.json.JSONObject;

/**
 * Created by Dipon on 3/28/2017.
 */

public class Conditions implements JSONPopulator {
    private int code;
    private int temperature;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        code= data.optInt("code");
        temperature = data.optInt("temp");
        description = data.optString("text");
    }
}
