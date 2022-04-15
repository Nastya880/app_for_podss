package com.application;

public class StudioPojo extends Pojo {
    private Integer id;
    private String place;
    private String name;
    private String description;
    private String type;
    private Integer avgPrice;
    private Double lat;
    private Double lng;

    public StudioPojo(Integer id, String place, String name, String description, String type, Integer avgPrice, Double lat, Double lng) {
        this.id = id;
        this.place = place;
        this.name = name;
        this.description = description;
        this.type = type;
        this.avgPrice = avgPrice;
        this.lat = lat;
        this.lng = lng;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ",\nместо = " + place  +
                ",\nназвание = " + name +
                ",\nописание = " + description  +
                ",\nтип = " + type  +
                ",\nстоимость = " + avgPrice;
    }
}
//    @Override
//    public String toString() {
//        return "StudioPojo{" +
//                "id=" + id +
//                ", place='" + place + '\'' +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", type='" + type + '\'' +
//                ", avgPrice=" + avgPrice +
//                '}';
//    }
//}