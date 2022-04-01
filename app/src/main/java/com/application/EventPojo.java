package com.application;

public class EventPojo extends Pojo{
    private Integer id;
    private String name;
    private String description;
    private Double lat;
    private Double lng;

    public EventPojo(Integer id, String name, String description, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lat = this.lat;
        this.lng = this.lng;
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

    public String getDescription() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double Lat) {
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
        return "EventPojo{" +
                "id=" + id +
                ", name='" + name + '\'' + ", description=" + description +
                '}';
    }
}
