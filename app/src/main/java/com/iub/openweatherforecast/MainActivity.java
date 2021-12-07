package com.iub.openweatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static EditText CityEditText;
    public static TextView forecastText;
    public static ImageView iconView;

    public final String openweatherUrl = "https://api.openweathermap.org/data/2.5/forecast";
    public final String appid = "6a74d0568f1e312900c750047ab804ef";
    public final String units = "metric";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CityEditText = findViewById(R.id.CityEditText);
        forecastText = findViewById(R.id.forecastText);
        iconView = findViewById(R.id.iconView);
        forecastText.setMovementMethod(new ScrollingMovementMethod());
    }

    public void getForecastDetails(View view) {
        String cityName = CityEditText.getText().toString();
        /*https://api.openweathermap.org/data/2.5/forecast?q=Dhaka&appid=6a74d0568f1e312900c750047ab804ef&units=metric */
        String executeUrl = openweatherUrl + "?q=" + cityName + "&appid=" + appid + "&units=" + units;
        if(!cityName.isEmpty()) {
            Weather getData = new Weather();
            getData.execute(executeUrl);
        } else {
            Toast.makeText(this, "Enter a City name!",Toast.LENGTH_LONG).show();
        }
    }
}