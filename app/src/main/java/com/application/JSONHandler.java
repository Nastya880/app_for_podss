package com.application;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * AsyncTask<[Input_Parameter Type], [Progress_Report Type], [Result Type]>
 * Установка соединения по протоколу HTTP
 * запрос GET использован для получения данных с сервера
 */
public class JSONHandler extends AsyncTask<String, Void, JSONArray> {
    @Override
    protected JSONArray doInBackground(String... strings) {
        try {
            HttpURLConnection urlConnection = null;
            URL url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            String jsonString = sb.toString();
            return new JSONArray(jsonString);
        } catch (IOException | JSONException e) {
            Log.e("Error Json", e.getMessage());
        }
        return null;
    }
}
