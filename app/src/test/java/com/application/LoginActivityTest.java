package com.application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import android.widget.EditText;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginActivityTest {

//    @Before
//    public void setUp() throws Exception {
//        LoginActivity l = new LoginActivity();
//    }
//
//    @Test
//    public void onCreate() {
//    }

    @Test
    public void checkCorrect() {
        Assert.assertThat(String.format("Phone Test failed, so few numbers,  for %s ", "+7999999"), LoginActivity.checkCorrect("+7999999"), is(false));
        Assert.assertThat(String.format("Phone Test failed, so more numbers, for %s ", "89032223344"), LoginActivity.checkCorrect("89032223344"), is(false));
        Assert.assertThat(String.format("Phone Test failed, so invalid format, for %s ", "908967655656"), LoginActivity.checkCorrect("908967655656"), is(false));
        Assert.assertThat(String.format("Phone Test failed, so invalid format, for %s ", "79009998877"), LoginActivity.checkCorrect("79009998877"), is(false));
        Assert.assertThat(String.format("Phone Test failed, so more numbers, for %s ", "+78885556655"), LoginActivity.checkCorrect("+78885556655"), is(false));
        Assert.assertThat(String.format("Phone Test true for %s ", "+79885556655"), LoginActivity.checkCorrect("+79885556655"), is(true));
    }
}