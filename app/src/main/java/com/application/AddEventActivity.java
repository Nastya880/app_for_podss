package com.application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddEventActivity extends ParentNavigationActivity{

    EditText eventTitlePlainText;
    EditText eventPlacePlainText;
    EditText latEditTextNumberDecimal;
    EditText lngEditTextNumberDecimal;

    EditText dateText;
    final Calendar calendar = Calendar.getInstance();

    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        onCreateOption(savedInstanceState);
        eventTitlePlainText = findViewById(R.id.eventTitlePlainText);
        eventPlacePlainText = findViewById(R.id.eventPlacePlainText);
        latEditTextNumberDecimal = findViewById(R.id.latEditTextNumberDecimal);
        lngEditTextNumberDecimal = findViewById(R.id.lngEditTextNumberDecimal);
        tvTime = (TextView) findViewById(R.id.timeText);

        TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
               if (hourOfDay < 10 & minute < 10)
                   tvTime.setText("0" + hourOfDay + ":0" + minute);
               else if (hourOfDay < 10)
                   tvTime.setText("0" + hourOfDay + ":" + minute);
               else if (minute < 10)
                   tvTime.setText(hourOfDay + ":0" + minute);
               else
                   tvTime.setText(hourOfDay + ":" + minute);
            }
        };
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(AddEventActivity.this, myCallBack, calendar.getTime().getHours(),calendar.getTime().getMinutes(), true);
                dialog.show();
            }
        });

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

    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void addEvent(View view) {
        if (eventTitlePlainText.getText().toString().length() <= 0)
            showAlertDialog("Введите название мероприятия");
        else if (eventTitlePlainText.getText().toString().length() >= 50)
            showAlertDialog("Название мероприятия слишком большое");
        else if (eventPlacePlainText.getText().toString().length() <= 0)
            showAlertDialog("Введите описания мероприятия");
        else if (eventPlacePlainText.getText().toString().length() >= 255)
            showAlertDialog("Описание мероприятия слишком большое");

        else if (latEditTextNumberDecimal.getText().toString().length() <= 0)
            showAlertDialog("Введите координату широты");
        else if (lngEditTextNumberDecimal.getText().toString().length() <= 0)
            showAlertDialog("Введите координату долготы");
        else if (!checkAddressString(Double.parseDouble(latEditTextNumberDecimal.getText().toString()), Double.parseDouble(lngEditTextNumberDecimal.getText().toString())))
            showAlertDialog("Введенные координаты не удовлетворяют существующему местоположению. Введите корректные координаты");
        else if (dateText.getText().toString().length() <= 0)
            showAlertDialog("Выберите дату проведения мероприятия");
        else if (tvTime.getText().toString().length() <= 0)
            showAlertDialog("Выберите время проведения мероприятия");
        else if (!isOnline())
            showAlertDialog("Проверьте подключение к интернету");
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