package com.iub.openweatherforecast;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Weather extends AsyncTask<String, Void, String> {

    String result;
    @Override
    protected String doInBackground(String... urls) {
        result = "";
        URL link;
        HttpURLConnection myConnection;

        try{
            link = new URL(urls[0]);
            myConnection = (HttpURLConnection) link.openConnection();
            InputStream in = myConnection.getInputStream();
            InputStreamReader myStreamReader = new InputStreamReader(in);
            int data = myStreamReader.read();
            while (data != -1){
                char current = (char) data;
                result += current;
                data = myStreamReader.read();
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        ArrayList<String> tempsList = new ArrayList<>();
        ArrayList<String> feelsLikeTempsList = new ArrayList<>();
        ArrayList<String> humiditiesList = new ArrayList<>();
        ArrayList<String> weatherList = new ArrayList<>();
        ArrayList<String> descriptionList = new ArrayList<>();
        ArrayList<String> dtTextList = new ArrayList<>();

        try {
            JSONObject myObject = new JSONObject(result);
            JSONArray listArray = myObject.getJSONArray("list");

            for(int i = 0; i < listArray.length(); i++) {
                JSONObject listObject = listArray.getJSONObject(i);
                JSONObject main = new JSONObject(listObject.getString("main"));
                JSONArray weatherArray = listObject.getJSONArray("weather");
                JSONObject weather = weatherArray.getJSONObject(0);

                tempsList.add(main.getString("temp"));
                feelsLikeTempsList.add(main.getString("feels_like"));
                humiditiesList.add(main.getString("humidity"));
                weatherList.add(weather.getString("main"));
                descriptionList.add(weather.getString("description"));
                dtTextList.add(listObject.getString("dt_txt"));
            }
            JSONObject city = new JSONObject(myObject.getString("city"));
            String cityName = city.getString("name");
            String countryName = city.getString("country");

            String resultText = cityName + ", " + countryName + "\nDay 1 : \n"
                    + "Temp : " + tempsList.get(0) + "\u2103 \n"
                    + "Feels like : " + feelsLikeTempsList.get(0) + "\u2103 \n"
                    + weatherList.get(0) + "\n"
                    + descriptionList.get(0) + "\n"
                    + "Humidity : " + humiditiesList.get(0) + "%\n"
                    + dtTextList.get(0) + "\n"
                    + "\nDay 2 : \n"
                    + "Temp : " + tempsList.get(8) + "\u2103 \n"
                    + "Feels like : " + feelsLikeTempsList.get(8) + "\u2103 \n"
                    + weatherList.get(8) + "\n"
                    + descriptionList.get(8) + "\n"
                    + "Humidity : " + humiditiesList.get(8) + "%\n"
                    + dtTextList.get(8) + "\n"
                    + "\nDay 3 : \n"
                    + "Temp : " + tempsList.get(16) + "\u2103 \n"
                    + "Feels like : " + feelsLikeTempsList.get(16) + "\u2103 \n"
                    + weatherList.get(16) + "\n"
                    + descriptionList.get(16) + "\n"
                    + "Humidity : " + humiditiesList.get(16) + "%\n"
                    + dtTextList.get(16) + "\n"
                    + "\nDay 4 : \n"
                    + "Temp : " + tempsList.get(24) + "\u2103 \n"
                    + "Feels like : " + feelsLikeTempsList.get(24) + "\u2103 \n"
                    + weatherList.get(24) + "\n"
                    + descriptionList.get(24) + "\n"
                    + "Humidity : " + humiditiesList.get(24) + "%\n"
                    + dtTextList.get(24) + "\n"
                    + "\nDay 5 : \n"
                    + "Temp : " + tempsList.get(32) + "\u2103 \n"
                    + "Feels like : " + feelsLikeTempsList.get(32) + "\u2103 \n"
                    + weatherList.get(32) + "\n"
                    + descriptionList.get(32) + "\n"
                    + "Humidity : " + humiditiesList.get(32) + "%\n"
                    + dtTextList.get(32) + "\n";


            switch (weatherList.get(0)) {
                case "Rain":
                    MainActivity.iconView.setBackgroundResource(R.drawable.rain);
                    break;
                case "Clear":
                    MainActivity.iconView.setBackgroundResource(R.drawable.clear);
                    break;
                case "Snow":
                    MainActivity.iconView.setBackgroundResource(R.drawable.snow);
                    break;
                case "Clouds":
                    MainActivity.iconView.setBackgroundResource(R.drawable.clouds);
                    break;
                case "Thunderstorm":
                    MainActivity.iconView.setBackgroundResource(R.drawable.thunderstorm);
                    break;
                case "Drizzle":
                    MainActivity.iconView.setBackgroundResource(R.drawable.drizzle);
                    break;
                case "Mist":
                case "Haze":
                case "Fog":
                    MainActivity.iconView.setBackgroundResource(R.drawable.mist);
                    break;
            }
            MainActivity.weatherMain.setText(weatherList.get(0));
            MainActivity.forecastText.setText(resultText);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
