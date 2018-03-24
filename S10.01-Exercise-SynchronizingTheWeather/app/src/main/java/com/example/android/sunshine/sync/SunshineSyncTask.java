
package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import com.example.android.sunshine.utilities.SunshineWeatherUtils;

public class SunshineSyncTask {

    synchronized public static void syncWeather(Context context) {
        
        try {

            String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.getUrl(context));

            ContentValues[] contentValuesWeatherResponse = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonWeatherResponse);

            if(contentValuesWeatherResponse != null && contentValuesWeatherResponse.length > 0) {

                ContentResolver weatherResolver = context.getContentResolver();

                //remove old weather data
                weatherResolver.delete(WeatherContract.WeatherEntry.CONTENT_URI, null, null);

                //insert new weather data
                weatherResolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI, contentValuesWeatherResponse);

            }


        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
