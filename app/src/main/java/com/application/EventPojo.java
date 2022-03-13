package com.application;

public class EventPojo {
    private Integer id;
    private String name;

    public EventPojo(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        // тут можем настроить как "мероприятие" должно в списке отображаться
        return "EventPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
