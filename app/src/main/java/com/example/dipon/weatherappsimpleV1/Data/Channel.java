package com.example.dipon.weatherappsimpleV1.Data;

import org.json.JSONObject;

/**
 * Created by Dipon on 3/28/2017.
 */

public class Channel implements JSONPopulator {
    private Units units;
    private Items items;

    public Units getUnits() {
        return units;
    }

    public Items getItems() {
        return items;
    }

    @Override
    public void populate(JSONObject data) {
       units = new Units();
        units.populate(data.optJSONObject("units"));

        items = new Items();
        items.populate(data.optJSONObject("item"));
    }
}
