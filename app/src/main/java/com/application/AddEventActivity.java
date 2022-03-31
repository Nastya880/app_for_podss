package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ResourceBundle;

public class AddEventActivity extends AppCompatActivity {

    TextView eventTitlePlainText;
    TextView eventDescriptionPlainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        eventTitlePlainText = findViewById(R.id.eventTitlePlainText);
        eventDescriptionPlainText = findViewById(R.id.eventDescriptionPlainText);
    }


    public void close(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addEvent(View view) {
        if (eventTitlePlainText.getText().toString().length() > 0 && eventDescriptionPlainText.getText().toString().length() > 0) {
            FindActivity.events.add(new EventPojo(FindActivity.events.size(), eventTitlePlainText.getText().toString(), eventDescriptionPlainText.getText().toString()));
            eventTitlePlainText.setText("");
       //     Intent intent = new Intent(this, AddDescriptionEventActivity.class);
         //   startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Название и описание мероприятия не могут быть пустыми", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
