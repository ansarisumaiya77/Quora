package com.example.quora.RequestDataClasses;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.quora.JsonConvert.EarthquakeConvert;
import com.example.quora.Models.Earthquake;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class EarthquakeData {

    ArrayList<Earthquake> earthquakes;
    private Context context;
    String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2019-01-02&minmagnitude=7&limit=25";

    public EarthquakeData(Context _context)
    {
        this.context = _context;
    }

    public ArrayList<Earthquake> getData()
    {
        earthquakes = new ArrayList<>();
        //Volley Request for Earthquake
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try
                {
                    JSONArray array = response.getJSONArray("features");
                    for(int i=0; i<array.length();i++)
                    {
                        JSONObject singleObj = array.getJSONObject(i);
                        JSONObject property = singleObj.getJSONObject("properties");
                        double magnitude = property.getDouble("mag");
                        String  place = property.getString("place");
                        String  alert = property.getString("alert");
                        String  status = property.getString("status");
                        String  code = property.getString("code");
                        //Earthquake earth = new Earthquake(magnitude, place,alert,status,code);
                        earthquakes.add(new Earthquake(magnitude, place,alert,status,code));
                    }
                    Log.i("Error","Size:"+earthquakes.size());
                }
                catch (Exception e)
                {
                    Log.v("Error", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Information", ""+error.getMessage());
            }
        });
        queue.add(request);
        return earthquakes;
    }
}
