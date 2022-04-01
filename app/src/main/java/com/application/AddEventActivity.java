package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddEventActivity extends AppCompatActivity {

    TextView eventTitlePlainText;
    TextView eventDescriptionPlainText;
    TextView eventPlaceLatPlainText;
    TextView eventPlaceLngPlainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        eventTitlePlainText = findViewById(R.id.eventTitlePlainText);
        eventDescriptionPlainText = findViewById(R.id.eventDescriptionPlainText);
        eventPlaceLatPlainText = findViewById(R.id.eventPlaceLatPlainText);
        eventPlaceLngPlainText = findViewById(R.id.eventPlaceLngPlainText);
    }


    public void close(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addEvent(View view) {
        if (eventTitlePlainText.getText().toString().length() < 1) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Введите название мероприятия", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (eventDescriptionPlainText.getText().toString().length() < 1) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Введите описание мероприятия", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (eventPlaceLatPlainText.length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Введите координату широты мероприятия", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (eventPlaceLngPlainText.length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Введите координату долготы мероприятия", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
       // if (eventTitlePlainText.getText().toString().length() > 0 && eventDescriptionPlainText.getText().toString().length() > 0) {
            FindActivity.events.add(new EventPojo(FindActivity.events.size(), eventTitlePlainText.getText().toString(), eventDescriptionPlainText.getText().toString(), eventPlaceLatPlainText.getX(), eventPlaceLngPlainText.getY()));
            eventTitlePlainText.setText("");
            eventDescriptionPlainText.setText("");
            eventPlaceLatPlainText.setText("");
            eventPlaceLngPlainText.setText("");
            Intent intent = new Intent(this, MessageAddEventActivity.class);
            startActivity(intent);
        }
    }
}
