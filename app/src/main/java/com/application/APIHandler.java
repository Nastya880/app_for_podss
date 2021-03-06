package com.application;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class APIHandler {

    /**
     * Конвертация данных в JSON для отправки НА сервер списка мероприятий - метод POST
     * @param eventPojo
     * @return
     */
    public static boolean addEvent(EventPojo eventPojo) {
        try {
            //AssyncTask нужен для синхронизации обработчика потока с UI
            //AsyncTask<[Input_Parameter Type], [Progress_Report Type], [Result Type]>
            AsyncTask<String, Void, JSONArray> jsonArray = new POSTHandler().execute("https://nastya2022.pythonanywhere.com/api/event/add", eventPojo.toJSON().toString());
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Получение списка мероприятий в формате JSON с сервера
     * @return
     */
    public static ArrayList<EventPojo> getEvents() {
        ArrayList<EventPojo> events = new ArrayList<>();
        AsyncTask<String, Void, JSONArray> jsonArray = new
                JSONHandler().execute("https://nastya2022.pythonanywhere.com/api/event/list");
        try {
            for (int i = 0; i < jsonArray.get().length(); i++) {
                JSONObject exploreObject = jsonArray.get().getJSONObject(i);
                events.add(new EventPojo(exploreObject.getInt("id"),
                        exploreObject.getString("name"), exploreObject.getString("description"),
                        exploreObject.getString("datetime"), exploreObject.getDouble("lat"),
                        exploreObject.getDouble("lng"), exploreObject.getString("phone")));
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return events;
    }

    /**
     * Получение списка студий в формате JSON с сервера
     * @return
     */
    public static ArrayList<StudioPojo> getStudios() {
        ArrayList<StudioPojo> studios = new ArrayList<>();
        AsyncTask<String, Void, JSONArray> jsonArray = new
                JSONHandler().execute("https://nastya2022.pythonanywhere.com/api/studio/list");
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
