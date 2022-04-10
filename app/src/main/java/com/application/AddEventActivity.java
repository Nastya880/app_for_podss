package com.application;

import static android.graphics.Color.BLUE;

import static java.util.Locale.*;

import androidx.annotation.RequiresApi;
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
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
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
import java.util.List;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {

    EditText eventTitlePlainText;
    EditText eventPlacePlainText;
    DatePicker datePicker;
    EditText latEditTextNumberDecimal;
    EditText lngEditTextNumberDecimal;
    //TextView time;
    TimePicker timePicker;
    Dialog dialog;

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

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // негативная кнопка
                case Dialog.BUTTON_NEGATIVE:
                    break;
                // нейтральная кнопка
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };

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

    private boolean checkAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                //Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                //Toast toast = Toast.makeText(getApplicationContext(), "noo", Toast.LENGTH_SHORT);
                //toast.show();
                //Log.w("My Current loction address", "No Address returned!");
                return false;
            }
        } catch (Exception e) {
            //Log.w("My Current loction address", "Canont get Address!");
            return false;
        }
        return true;
    }

    public void addEvent(View view) {

        final int DIALOG_EXIT = 1;
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        if (eventTitlePlainText.getText().toString().length() <= 0) {
            adb.setTitle(R.string.error);
            adb.setMessage("Название мероприятия не может быть пустым");
            adb.setNeutralButton(R.string.ok, myClickListener);
            adb.create();
            showDialog(DIALOG_EXIT);
            adb.show();
        }   else if (eventTitlePlainText.getText().toString().length() >= 50) {
            adb.setTitle(R.string.error);
            adb.setMessage("Название мероприятия слишком большое");
            adb.setNeutralButton(R.string.ok, myClickListener);
            adb.create();
            showDialog(DIALOG_EXIT);
            adb.show();
        }
        else if (eventPlacePlainText.getText().toString().length() <= 0) {
            adb.setTitle(R.string.error);
            adb.setMessage("Описание мероприятия не может быть пустым");
            adb.setNeutralButton(R.string.ok, myClickListener);
            adb.create();
            showDialog(DIALOG_EXIT);
            adb.show();
        } else if (eventPlacePlainText.getText().toString().length() >= 255) {
            adb.setTitle(R.string.error);
            adb.setMessage("Описание мероприятия слишком большое");
            adb.setNeutralButton(R.string.ok, myClickListener);
            adb.create();
            showDialog(DIALOG_EXIT);
            adb.show();
        }
        else if (latEditTextNumberDecimal.getText().toString().length() <= 0) {
            adb.setTitle(R.string.error);
            adb.setMessage("Координата широты не может быть пустой");
            adb.setNeutralButton(R.string.ok, myClickListener);
            adb.create();
            showDialog(DIALOG_EXIT);
            adb.show();
        } else if (lngEditTextNumberDecimal.getText().toString().length() <= 0) {
            adb.setTitle(R.string.error);
            adb.setMessage("Координата долготы  не может быть пустой");
            adb.setNeutralButton(R.string.ok, myClickListener);
            adb.create();
            showDialog(DIALOG_EXIT);
            adb.show();
        } else if (!checkAddressString(Double.parseDouble(latEditTextNumberDecimal.getText().toString()), Double.parseDouble(lngEditTextNumberDecimal.getText().toString())))
        {
            adb.setTitle(R.string.error);
            adb.setMessage("Введенные координаты не удовлетворяют существующему местоположению. Введите корректные координаты");
            adb.setNeutralButton(R.string.ok, myClickListener);
            adb.create();
            showDialog(DIALOG_EXIT);
            adb.show();
        } else {

            timePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
            timePicker.setIs24HourView(true); // used to display 24 mode

            String month = String.valueOf(datePicker.getMonth() + 1);
            String day = String.valueOf(datePicker.getDayOfMonth());
            String year = String.valueOf(datePicker.getYear());

            //timePicker.setCurrentMinute();
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