package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    EditText eventTitlePlainText;
    EditText eventPlacePlainText;
    DatePicker datePicker;
    EditText latEditTextNumberDecimal;
    EditText lngEditTextNumberDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        eventTitlePlainText = findViewById(R.id.eventTitlePlainText);
        eventPlacePlainText = findViewById(R.id.eventPlacePlainText);
        datePicker = findViewById(R.id.datePicker);
        latEditTextNumberDecimal = findViewById(R.id.latEditTextNumberDecimal);
        lngEditTextNumberDecimal = findViewById(R.id.lngEditTextNumberDecimal);
        setCurrentDateOnView();
    }


    public void close(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // устанавливаем текущую дату
    public void setCurrentDateOnView() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Устанавливаем текущую дату для DatePicker
        datePicker.init(year, month, day, null);
    }

    public void addEvent(View view) {
        if (eventTitlePlainText.getText().toString().length() <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Название мероприятие не может быть пустым", Toast.LENGTH_SHORT);
            toast.show();
        } else if (eventPlacePlainText.getText().toString().length() <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Описание мероприятия не может быть пустым", Toast.LENGTH_SHORT);
            toast.show();
        } else if (latEditTextNumberDecimal.getText().toString().length() <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Координата широты не может быть пустой", Toast.LENGTH_SHORT);
            toast.show();
        } else if (lngEditTextNumberDecimal.getText().toString().length() <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Кооордината долготы не может быть пустой", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            String month = String.valueOf(datePicker.getMonth());
            String day = String.valueOf(datePicker.getDayOfMonth());
            String year = String.valueOf(datePicker.getYear());
            if (day.length() == 1) {
                day = "0" + day;
            }
            if (month.length() == 1) {
                month = "0" + month;
            }
            String stringDate = String.format("%s-%s-%s", year, month, day);
            APIHandler.addEvent(new EventPojo(-1, eventTitlePlainText.getText().toString(), eventPlacePlainText.getText().toString(),
                    stringDate, Double.parseDouble(latEditTextNumberDecimal.getText().toString()), Double.parseDouble(lngEditTextNumberDecimal.getText().toString())));
            eventTitlePlainText.setText("");
            eventPlacePlainText.setText("");
            latEditTextNumberDecimal.setText("");

            Intent intent = new Intent(this, MessageAddEventActivity.class);
            startActivity(intent);
        }
    }
}