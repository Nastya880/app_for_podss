package com.application;

import org.json.JSONException;
import org.json.JSONObject;

public class EventPojo extends Pojo {
    private Integer id;
    private String name;
    private String place;
    private String dateTime;
    private Double lat;
    private Double lng;

    public EventPojo(Integer id, String name, String place, String dateTime, Double lat, Double lng) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.dateTime = dateTime;
        this.lat = lat;
        this.lng = lng;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("id", id);
        jo.put("name", name);
        jo.put("place", place);
        jo.put("datetime", dateTime);
        jo.put("lat", lat);
        jo.put("lng", lng);
        return jo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "название:" + name +
                ",\nвремя:" + dateTime;
    }
}
