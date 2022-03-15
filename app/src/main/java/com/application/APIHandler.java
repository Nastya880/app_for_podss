package com.application;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class APIHandler {
    public static ArrayList<EventPojo> getEvents() {
        ArrayList<EventPojo> events = new ArrayList<>();
        AsyncTask<String, Void, JSONArray> jsonArray = new JSONHandler().execute("https://nastya2022.pythonanywhere.com/api/event/list");
        try {
            for (int i = 0; i < jsonArray.get().length(); i++) {
                JSONObject exploreObject = jsonArray.get().getJSONObject(i);
                events.add(new EventPojo(exploreObject.getInt("id"), exploreObject.getString("name")));
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return events;
    }

    public static ArrayList<StudioPojo> getStudios() {
        ArrayList<StudioPojo> studios = new ArrayList<>();
        AsyncTask<String, Void, JSONArray> jsonArray = new JSONHandler().execute("https://nastya2022.pythonanywhere.com/api/studio/list");
        try {
            for (int i = 0; i < jsonArray.get().length(); i++) {
                JSONObject exploreObject = jsonArray.get().getJSONObject(i);
                studios.add(new StudioPojo(exploreObject.getInt("id"), exploreObject.getString("place"),
                        exploreObject.getString("name"), exploreObject.getString("description"), exploreObject.getString("type"),
                        exploreObject.getInt("avg_price"), exploreObject.getDouble("lat"), exploreObject.getDouble("lng")));
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return studios;
    }


}
