package com.application;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

//Используется для соединения с сервером и отправки данных
public class POSTHandler extends AsyncTask<String, Void, JSONArray> {

    /**
     * Инициализация HTTP запроса
     * @param strings
     * @return
     */
    @Override
    protected JSONArray doInBackground(String... strings) {
        try {
            //Log.i("myLog",strings[1]);
            HttpURLConnection urlConnection = null;
            URL url = new URL(strings[0]);
            //Получение объекта urlConnection
            urlConnection = (HttpURLConnection) url.openConnection();
            //Загрузка тела запроса
            //Метод POST для отправки данных на сервер
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            urlConnection.setRequestProperty("Accept", "application/json");
            //Передача данных, записанных в поток
            //Подготовка перед чтением запроса
            try (OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = strings[1].getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            urlConnection.connect();
            //Буферизация данных запроса
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                //Чтение входного потока
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                //Log.i("responseLog",response.toString());
            }
            //Итог - конвертированный код в JSONArray
            return new JSONArray();
        } catch (IOException e) {
            e.printStackTrace();
            //Log.e("Error Json", e.getMessage());
        }
        return null;
    }
}
