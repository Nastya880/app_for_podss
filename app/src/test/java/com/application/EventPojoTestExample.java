package com.application;

import static org.junit.Assert.*;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EventPojoTestExample {


    EventPojo obj;

//    @Test
//    public void toJSON() throws JSONException {
//        String expected = "+79032295555";
//        JSONObject joTest = obj.toJSON();
//        String actual = joTest.get("Phone").toString();
//        Assert.assertEquals(expected, actual);
//    }

    @Before
    public void add()
    {
        obj = new EventPojo(1, "12", "desc",
                "2023-01-01 13:00:00", 55.0, 55.0, "+79032295555");
    }
    @Test
    public void getPhone() {
        String expected = "+79032295555";
        String actual = obj.getPhone();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setPhone() {
        String expected = "+79032295566";
        obj.setPhone("+79032295566");
        String actual = obj.getPhone();
        Assert.assertEquals(expected, actual);
    }

    
}
