package com.application;

public class StudioPojo extends Pojo{
    private Integer id;
    private String place;
    private String name;
    private String description;
    private String type;
    private Integer avgPrice;

    public StudioPojo(Integer id, String place, String name, String description, String type, Integer avgPrice) {
        this.id = id;
        this.place = place;
        this.name = name;
        this.description = description;
        this.type = type;
        this.avgPrice = avgPrice;
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
        //отображение студии в виде строки
        return "StudioPojo{" +
                "id=" + id +
                ", place='" + place + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", avgPrice=" + avgPrice +
                '}';
    }
}
