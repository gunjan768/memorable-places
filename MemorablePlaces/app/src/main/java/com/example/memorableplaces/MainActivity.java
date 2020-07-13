package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    static MyAdapter myAdapter;

    static ArrayList<String> places = new ArrayList<String>();
    static ArrayList<LatLng> locations = new ArrayList<LatLng>();
    ArrayList<String> latitudes, longitudes;

    public void init()
    {
        places.clear();
        locations.clear();

        latitudes.clear();
        longitudes.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Shared preferences allow you to store small amounts of primitive data as key/value pairs in a file on the device. To get a handle to a preference file,
        //  and to read, write, and manage preference data, use the SharedPreferences class. The Android framework manages the shared preferences file itself.
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.memorableplaces", Context.MODE_PRIVATE);

        latitudes = new ArrayList<String>();
        longitudes = new ArrayList<String>();

        init();

        try
        {
            // getString() is used to get out the string by passing key as first argument and any temporary value as a second argument if the value is not
            // found whose key is passed as a first argument ( either key doesn't exist or due to any error ).

            // ObjectSerializer.serialize() will convert an array of strings into a long single string.

            String PLACES = sharedPreferences.getString("places", ObjectSerializer.serialize(new ArrayList<String>()));
            String LATITUDES = sharedPreferences.getString("latitudes", ObjectSerializer.serialize(new ArrayList<String>()));
            String LONGITUDES = sharedPreferences.getString("longitudes", ObjectSerializer.serialize(new ArrayList<String>()));

            // ObjectSerializer.deserialize() will convert back the serialized string into an array of strings.

            places = (ArrayList<String>) ObjectSerializer.deserialize(PLACES);
            latitudes = (ArrayList<String>) ObjectSerializer.deserialize(LATITUDES);
            longitudes = (ArrayList<String>) ObjectSerializer.deserialize(LONGITUDES);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        if(places.size() > 0 && latitudes.size() > 0 && longitudes.size() > 0)
        {
            if(places.size() == latitudes.size() && places.size() == longitudes.size())
            {
                for(int i=0; i < latitudes.size(); i++)
                {
                    locations.add(new LatLng(Double.parseDouble(latitudes.get(i)), Double.parseDouble(longitudes.get(i))));
                }
            }
        }
        else
        {
            places.add("Add a new place...");
            locations.add(new LatLng(0,0));
        }

        recyclerView = findViewById(R.id.recyclerView);

        myAdapter = new MyAdapter(this);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}