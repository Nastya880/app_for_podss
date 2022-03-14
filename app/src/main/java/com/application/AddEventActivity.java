package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddEventActivity extends AppCompatActivity {

    TextView eventTitlePlainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        eventTitlePlainText = findViewById(R.id.eventTitlePlainText);
    }


    public void close(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addEvent(View view) {
        if (eventTitlePlainText.getText().toString().length() > 0) {
            FindActivity.events.add(new EventPojo(FindActivity.events.size(), eventTitlePlainText.getText().toString()));
            eventTitlePlainText.setText("");
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Название мероприятия не может быть пустым", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}