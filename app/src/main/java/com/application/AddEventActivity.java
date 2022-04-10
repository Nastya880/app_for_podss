package com.application;

import static android.graphics.Color.BLUE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    EditText eventTitlePlainText;
    EditText eventPlacePlainText;
    DatePicker datePicker;
    EditText latEditTextNumberDecimal;
    EditText lngEditTextNumberDecimal;
    //TextView time;
    TimePicker timePicker;

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

    // устанавливаем текущую дату
    public void setCurrentDateOnView() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Устанавливаем текущую дату для DatePicker
        datePicker.init(year, month, day, null);

        datePicker.setMinDate(System.currentTimeMillis());
    }

    public void addEvent(View view) {
        if (eventTitlePlainText.getText().toString().length() <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Название мероприятие не может быть пустым", Toast.LENGTH_SHORT);
            toast.show();
        } else if (eventPlacePlainText.getText().toString().length() <= 0) {
            //     Toast toast = Toast.makeText(getApplicationContext(),
            //               "Описание мероприятия не может быть пустым", Toast.LENGTH_SHORT);
            Toast toast = new Toast(getApplicationContext());

            toast.setGravity(Gravity.AXIS_PULL_AFTER, 100, 0);
            TextView tv = new TextView(AddEventActivity.this);
            // tv.setBackground(color.BLUE);
            tv.setTextColor(Color.RED);
            tv.setTextSize(25);

            Typeface t = Typeface.create("serif", Typeface.BOLD_ITALIC);
            tv.setTypeface(t);
            tv.setPadding(10,10,10,10);
            tv.setText("\"Поле нем ожет быть пустым\"");
            toast.setView(tv);


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
           // time = (TextView) findViewById(R.id.time);
            timePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
            timePicker.setIs24HourView(true); // used to display 24 mode

            String month = String.valueOf(datePicker.getMonth() + 1);
            String day = String.valueOf(datePicker.getDayOfMonth());
            String year = String.valueOf(datePicker.getYear());

            String hour = String.valueOf(timePicker.getHour());
            String minute = String.valueOf(timePicker.getMinute());
            if (day.length() == 1) {
                day = "0" + day;
            }
            if (month.length() == 1) {
                month = "0" + month;
            }

            String stringDate = String.format("%s-%s-%s %s:%s:%s", year, month, day, hour, minute, "00");

            APIHandler.addEvent(new EventPojo(-1, eventTitlePlainText.getText().toString(), eventPlacePlainText.getText().toString(),
                    stringDate, Double.parseDouble(latEditTextNumberDecimal.getText().toString()), Double.parseDouble(lngEditTextNumberDecimal.getText().toString())));
            eventTitlePlainText.setText("");
            eventPlacePlainText.setText("");
            latEditTextNumberDecimal.setText("");

            Intent intent = new Intent(this, MessageAddEventActivity.class);
            startActivity(intent);
        }
    }

    public void mainHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void exitApp(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Вы действительно хотите выйти из приложения?")
                .setCancelable(false)
                .setPositiveButton("да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(Intent.ACTION_MAIN);
                        i.addCategory(Intent.CATEGORY_HOME);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Нет", null).show();
    }


}