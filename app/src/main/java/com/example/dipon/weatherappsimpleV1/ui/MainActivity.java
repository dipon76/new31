package com.example.dipon.weatherappsimpleV1.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dipon.weatherappsimpleV1.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_City = "EXTRA_CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input = (EditText) findViewById(R.id.cityName);
        Button Load = (Button) findViewById(R.id.load);
        Load.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        EditText input = (EditText) findViewById(R.id.cityName);
        String city = input.getText().toString();
        Intent i = new Intent (this, BaseActivity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_City, city);
        i.putExtra(BUNDLE_EXTRAS, extras);
        startActivity(i);
    }
}