package com.application;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AboutDeveloperActivity extends ParentNavigationActivity {

    /**
     * Создание активити
     * Экран с информацией о разработчике
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);
        //Боковое меню-гамбургер
        onCreateOption(savedInstanceState);
    }
}