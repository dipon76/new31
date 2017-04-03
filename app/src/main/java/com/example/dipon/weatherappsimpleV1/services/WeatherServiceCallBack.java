package com.example.dipon.weatherappsimpleV1.services;

import com.example.dipon.weatherappsimpleV1.Data.Channel;

/**
 * Created by Dipon on 3/28/2017.
 */

public interface WeatherServiceCallBack {

    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}
