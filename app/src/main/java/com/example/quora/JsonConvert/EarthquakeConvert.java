package com.example.quora.JsonConvert;

import android.util.Log;

import com.example.quora.Models.Earthquake;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class EarthquakeConvert {

    public EarthquakeConvert() {

    }
    public ArrayList<Earthquake> getJson(JSONObject mainObj)
    {
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        try
        {
            JSONArray array = mainObj.getJSONArray("features");
            for(int i=0; i<array.length();i++)
            {
                JSONObject singleObj = array.getJSONObject(i);
                JSONObject property = singleObj.getJSONObject("properties");
                double magnitude = property.getDouble("mag");
                String  place = property.getString("place");
                String  alert = property.getString("alert");
                String  status = property.getString("status");
                String  code = property.getString("code");
                Earthquake earth = new Earthquake(magnitude, place,alert,status,code);
                earthquakes.add(earth);
            }
        }
        catch (Exception e)
        {
            Log.v("Error", e.getMessage());
        }
        return earthquakes;
    }
}
