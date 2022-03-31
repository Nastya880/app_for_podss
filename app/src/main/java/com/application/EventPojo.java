package com.application;

public class EventPojo extends Pojo{
    private Integer id;
    private String name;
    private String description;

    public EventPojo(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "EventPojo{" +
                "id=" + id +
                ", name='" + name + '\'' + "description=" + description +
                '}';
    }
}
