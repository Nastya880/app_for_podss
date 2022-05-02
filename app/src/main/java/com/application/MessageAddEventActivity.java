package com.application;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MessageAddEventActivity extends ParentNavigationActivity {

    /**
     * Создание активити
     * Экран после успешного добавления мероприятия
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_add_event);
        onCreateOption(savedInstanceState);
    }

    /**
     * Обработка нажатия на кнопку "Добавить новое мероприятие"
     * @param view
     */
    public void addNewButton(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }
}
