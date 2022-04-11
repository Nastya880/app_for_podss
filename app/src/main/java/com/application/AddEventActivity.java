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

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {

    EditText eventTitlePlainText;
    EditText eventPlacePlainText;
    EditText latEditTextNumberDecimal;
    EditText lngEditTextNumberDecimal;
    TimePicker timePicker;

    EditText dateText;
    final Calendar calendar = Calendar.getInstance();


    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 35;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        eventTitlePlainText = findViewById(R.id.eventTitlePlainText);
        eventPlacePlainText = findViewById(R.id.eventPlacePlainText);
        latEditTextNumberDecimal = findViewById(R.id.latEditTextNumberDecimal);
        lngEditTextNumberDecimal = findViewById(R.id.lngEditTextNumberDecimal);
        tvTime = (TextView) findViewById(R.id.timeText);

        dateText=(EditText) findViewById(R.id.dateText);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,day);

                updateLabel();
            }
        };
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new  DatePickerDialog(AddEventActivity.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

                // Устанавливаем текущую дату для DatePicker
                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();
            }
        });
    }

    private void updateLabel(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(dateFormat.format(calendar.getTime()));
    }


    public void onclicktime(View view) {
        showDialog(DIALOG_TIME);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            tvTime.setText(hourOfDay + ":" + minute);
        }
    };



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
                //Log.w("My Current loction address", "No Address returned!");
                return false;
            }
        } catch (Exception e) {
            //Log.w("My Current loction address", "Canont get Address!");
            return false;
        }
        return true;
    }

    public void showAlertDialog(String message)
    {
        final int DIALOG_EXIT = 1;
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle(R.string.error);
        adb.setMessage(message);
        adb.setNeutralButton(R.string.ok, null);
        adb.create();
        showDialog(DIALOG_EXIT);
        adb.show();

    }

    public void addEvent(View view) {
        if (eventTitlePlainText.getText().toString().length() <= 0)
            showAlertDialog("Название мероприятия не может быть пустым");
        else if (eventTitlePlainText.getText().toString().length() >= 50)
            showAlertDialog("Название мероприятия слишком большое");
        else if (eventPlacePlainText.getText().toString().length() <= 0)
            showAlertDialog("Описание мероприятия не может быть пустым");
        else if (eventPlacePlainText.getText().toString().length() >= 255)
            showAlertDialog("Описание мероприятия слишком большое");

        else if (latEditTextNumberDecimal.getText().toString().length() <= 0)
            showAlertDialog("Координата широты не может быть пустой");
        else if (lngEditTextNumberDecimal.getText().toString().length() <= 0)
            showAlertDialog("Координата долготы не может быть пустой");
        else if (!checkAddressString(Double.parseDouble(latEditTextNumberDecimal.getText().toString()), Double.parseDouble(lngEditTextNumberDecimal.getText().toString())))
            showAlertDialog("Введенные координаты не удовлетворяют существующему местоположению. Введите корректные координаты");
        else if (dateText.getText().toString().length() <= 0)
            showAlertDialog("Дата не может быть невыбранной");
        else {
            String stringDate = String.format("%s %s:00",dateText.getText().toString(), tvTime.getText().toString());

            APIHandler.addEvent(new EventPojo(-1, eventTitlePlainText.getText().toString(), eventPlacePlainText.getText().toString(),
                    stringDate, Double.parseDouble(latEditTextNumberDecimal.getText().toString()), Double.parseDouble(lngEditTextNumberDecimal.getText().toString())));

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