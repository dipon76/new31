package com.example.dipon.weatherappsimpleV1.Data;

import org.json.JSONObject;

/**
 * Created by Dipon on 3/28/2017.
 */

public class Items implements JSONPopulator {
    private Conditions conditions;
    private Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }

    public Conditions getConditions() {
        return conditions;
    }

    @Override
    public void populate(JSONObject data) {
        conditions = new Conditions();
        conditions.populate(data.optJSONObject("condition"));

        forecast = new Forecast();
        forecast.populate(data);
    }
}
