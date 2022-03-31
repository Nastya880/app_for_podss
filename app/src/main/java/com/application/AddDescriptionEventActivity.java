package com.application;
import static androidx.core.content.ContextCompat.startActivity;

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

public class AddDescriptionEventActivity extends AppCompatActivity {
    TextView eventDescriptionPlainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description_event);
        eventDescriptionPlainText = findViewById(R.id.eventDescriptionPlainText);
    }

    public void addDescriptionEvent(View view) {
        if (eventDescriptionPlainText.getText().toString().length() > 0) {
            FindActivity.events.add(new EventPojo(FindActivity.events.size(), eventDescriptionPlainText.getText().toString()));
            eventDescriptionPlainText.setText("");
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Описание мероприятия не может быть пустым", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void closeInDesc(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
