package com.example.dipon.weatherappsimpleV1.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dipon.weatherappsimpleV1.Data.Channel;
import com.example.dipon.weatherappsimpleV1.Data.Day;
import com.example.dipon.weatherappsimpleV1.Data.Forecast;
import com.example.dipon.weatherappsimpleV1.Data.Items;
import com.example.dipon.weatherappsimpleV1.R;
import com.example.dipon.weatherappsimpleV1.services.MainAdapter;
import com.example.dipon.weatherappsimpleV1.services.WeatherServiceCallBack;
import com.example.dipon.weatherappsimpleV1.services.YahooWeatherService;

import java.util.List;

public class BaseActivity extends AppCompatActivity implements WeatherServiceCallBack , MainAdapter.ItemClickCallback {
    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    List<Day> dayList;

    private TextView test;
    public YahooWeatherService service;
    public ProgressDialog dialog;
    public RecyclerView recyclerView;
    public MainAdapter adapter;

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_DAY = "EXTRA_DAY";
    private static final String EXTRA_CONDITION = "EXTRA_CONDITION";
    private static final String EXTRA_CODE = "EXTRA_CODE";
    private static final String EXTRA_DATE = "EXTRA_DATE";
    private static final String EXTRA_HIGH = "EXTRA_HIGH";
    private static final String EXTRA_LOW = "EXTRA_LOW";
    private static final String EXTRA_CITY = "EXTRA_CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageDetail);
        temperatureTextView = (TextView) findViewById(R.id.dayTextView);
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);

        Bundle extras1 = getIntent().getBundleExtra(BUNDLE_EXTRAS);
        String cityName= extras1.getString(EXTRA_CITY);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather(cityName);
    }
    @Override
    public void serviceSuccess(Channel channel){
        dialog.hide();

        Items item = channel.getItems();

        int resourceId = getResources().getIdentifier("drawable/icon" + item.getConditions().getCode(),null,getPackageName());

        Drawable weatherIcon = getResources().getDrawable(resourceId);
        weatherIconImageView.setImageDrawable(weatherIcon);

        temperatureTextView.setText(item.getConditions().getTemperature() + "\u00B0" + channel.getUnits().getTemperature() );
        conditionTextView.setText(item.getConditions().getDescription());
        locationTextView.setText(service.getLocation());

        Forecast f1 = item.getForecast();
        if (f1 == null) {
            System.out.println("ase nai");


        }

        dayList = f1.getDayList();


        recyclerView = (RecyclerView) findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MainAdapter(dayList,this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter.setItemClickCallback(this);
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(View v, int p) {

        Day item = (Day) dayList.get(p);

        Intent i = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_DAY, item.day);
        extras.putString(EXTRA_CONDITION, item.text);
        extras.putString(EXTRA_CODE, Integer.toString(item.code));
        extras.putString(EXTRA_DATE, item.date);
        extras.putString(EXTRA_HIGH, item.high);
        extras.putString(EXTRA_LOW, item.low);
        i.putExtra(BUNDLE_EXTRAS, extras);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade(Fade.IN));
            getWindow().setExitTransition(new Fade(Fade.OUT));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    new Pair <View, String>(v.findViewById(R.id.weatherIconImageDetail),
                            getString(R.string.transition_image)),
                    new Pair <View, String>(v.findViewById(R.id.day),
                            getString(R.string.transition_day)),
                    new Pair<View, String>(v.findViewById(R.id.text),
                            getString(R.string.transition_condition))
            );

            ActivityCompat.startActivity(this, i, options.toBundle());
        } else {
            startActivity(i);
        }
    }

}
