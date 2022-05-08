package com.application;

import android.os.Bundle;

public class AddStudioActivity extends ParentNavigationActivity{
    /**
     * Создание активити
     * Экран с информацией о разработчике
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_studio);
        //Боковое меню-гамбургер
        onCreateOption(savedInstanceState);
    }
}
