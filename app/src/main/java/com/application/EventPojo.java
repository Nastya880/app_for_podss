package com.application;

import org.json.JSONException;
import org.json.JSONObject;

// Класс, выступающий сущностью мероприятий в БД

public class EventPojo extends Pojo {
    private Integer id;
    private String name;
    private String description;
    private String dateTime;
    private Double lat;
    private Double lng;
    private String phone;

    /**
     * конструктор класса EventPojo
     * @param id уникальный порядковый номер мероприятия
     * @param name название мероприятия
     * @param description описание мероприятия
     * @param dateTime дата и время проведения мероприятия
     * @param lat координата широты
     * @param lng координата долготы
     * @param phone номер телефона пользователя, добавляющего мероприятие
     */
    public EventPojo(Integer id, String name, String description, String dateTime, Double lat, Double lng, String phone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.lat = lat;
        this.lng = lng;
        this.phone = phone;
    }

    /**
     * Коверитирование объекта Java-класса в формат JSON
     * @return
     * @throws JSONException стандартное исключение, если ключ не найден
     */
    public JSONObject toJSON() throws JSONException {
        // Хранение неупорядоченных пар ключ-значение
        JSONObject jo = new JSONObject();
        // Добавление элементов
        jo.put("id", id);
        jo.put("name", name);
        jo.put("description", description);
        jo.put("datetime", dateTime);
        jo.put("lat", lat);
        jo.put("lng", lng);
        jo.put("phone", phone);
        return jo;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    /**
     * Данные о мероприятии для отображения на экране
     * @return
     */
    @Override
    public String toString() {
        return "название: " + name +
                ",\nописание: " + description +
                ",\nвремя: " + dateTime;
    }
}
