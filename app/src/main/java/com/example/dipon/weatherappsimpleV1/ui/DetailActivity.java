package com.example.dipon.weatherappsimpleV1.ui;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dipon.weatherappsimpleV1.R;

public class DetailActivity extends AppCompatActivity {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_DAY = "EXTRA_DAY";
    private static final String EXTRA_CONDITION = "EXTRA_CONDITION";
    private static final String EXTRA_CODE = "EXTRA_CODE";
    private static final String EXTRA_DATE = "EXTRA_DATE";
    private static final String EXTRA_HIGH = "EXTRA_HIGH";
    private static final String EXTRA_LOW = "EXTRA_LOW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        ImageView  weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageDetail);

        int resourceId = getResources().getIdentifier("drawable/icon" + extras.getString(EXTRA_CODE),null,getPackageName());

        Drawable weatherIcon =  getResources().getDrawable(resourceId);
        weatherIconImageView.setImageDrawable(weatherIcon);

        ((TextView)findViewById(R.id.dayTextView)).setText(extras.getString(EXTRA_DAY));
        ((TextView)findViewById(R.id.conditionTextView)).setText(extras.getString(EXTRA_CONDITION));
        ((TextView)findViewById(R.id.dateTextView)).setText(extras.getString(EXTRA_DATE));
        ((TextView)findViewById(R.id.highTextView)).setText("High : "+extras.getString(EXTRA_HIGH) +"\u00B0"+"C");
        ((TextView)findViewById(R.id.lowTextView)).setText("Low : " +extras.getString(EXTRA_LOW)+"\u00B0"+"C");
    }
}
