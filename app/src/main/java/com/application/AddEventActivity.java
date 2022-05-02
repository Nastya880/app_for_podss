package com.application;

import androidx.appcompat.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddEventActivity extends ParentNavigationActivity{

    //Переменная для редактирования поля названия мероприятия
    EditText eventTitlePlainText;
    //Переменная для редактирования поля описания мероприятия
    EditText eventPlacePlainText;
    //Переменная для редактирования поля координаты широты
    EditText latEditTextNumberDecimal;
    //Переменная для редактирования поля координаты долготы
    EditText lngEditTextNumberDecimal;
    //Переменная для редактирования поля даты проведения мероприятия
    EditText dateText;
    //Переменная для выбора времени проведения мероприятия
    TextView tvTime;
    //Дата и время
    final Calendar calendar = Calendar.getInstance();

    /**
     * Создание активити
     * экран добавления информации о мероприятии
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        //Боковое меню-гамбургер
        onCreateOption(savedInstanceState);
        eventTitlePlainText = findViewById(R.id.eventTitlePlainText);
        eventPlacePlainText = findViewById(R.id.eventPlacePlainText);
        latEditTextNumberDecimal = findViewById(R.id.latEditTextNumberDecimal);
        lngEditTextNumberDecimal = findViewById(R.id.lngEditTextNumberDecimal);
        tvTime = (TextView) findViewById(R.id.timeText);

        /**
         * Обработка нажатия поля выбора времени - отображение часов
         * установка времени в формате HH:mm в поле выбора времени
         */
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

        /**
         * Обаботка отображения календаря с ограничением текущей датой
         */
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

                //Установка текущей даты в DatePicker (в календаре)
                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();
            }
        });
    }

    /**
     * Обновление даты в формате yyyy-MM-dd
     */
    private void updateLabel(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(dateFormat.format(calendar.getTime()));
    }

    /**
     * Проверка существования координат
     * @param LATITUDE координата широты
     * @param LONGITUDE координата долготы
     * @return
     */
    private boolean checkAddressString(double LATITUDE, double LONGITUDE) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
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

    /**
     * Отображение диалога
     * @param message
     * @return
     */
    public boolean showAlertDialog(String message)
    {
      //  final int DIALOG_EXIT = 1;
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.error);
        adb.setMessage(message);
        adb.setNeutralButton(R.string.ok, null);
        adb.create();
       // showDialog(DIALOG_EXIT);
        adb.show();
        return false;
    }

    /**
     * Проверка интернет-соединения
     * @return
     */
    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Добавление мероприятия и обработка возможных ошибок
     * @param view
     * @return
     */
    public boolean addEvent(View view) {
        if (eventTitlePlainText.getText().toString().length() <= 0)
            return (showAlertDialog("Введите название мероприятия"));
        else if (eventTitlePlainText.getText().toString().length() >= 50)
            return (showAlertDialog("Название мероприятия слишком большое"));
        else if (eventPlacePlainText.getText().toString().length() <= 0)
            return (showAlertDialog("Введите описания мероприятия"));
        else if (eventPlacePlainText.getText().toString().length() >= 255)
            return (showAlertDialog("Описание мероприятия слишком большое"));

        else if (latEditTextNumberDecimal.getText().toString().length() <= 0)
            return (showAlertDialog("Введите координату широты"));
        else if (lngEditTextNumberDecimal.getText().toString().length() <= 0)
            return (showAlertDialog("Введите координату долготы"));
        else if (!checkAddressString(Double.parseDouble(latEditTextNumberDecimal.getText().toString()), Double.parseDouble(lngEditTextNumberDecimal.getText().toString())))
            return (showAlertDialog("Введенные координаты не удовлетворяют существующему местоположению. Введите корректные координаты"));
        else if (dateText.getText().toString().length() <= 0)
            return (showAlertDialog("Выберите дату проведения мероприятия"));
        else if (tvTime.getText().toString().length() <= 0)
            return (showAlertDialog("Выберите время проведения мероприятия"));
        else if (!isOnline())
            return (showAlertDialog("Проверьте подключение к интернету"));
        else {
            String stringDate = String.format("%s %s:00",dateText.getText().toString(), tvTime.getText().toString());

            APIHandler.addEvent(new EventPojo(-1, eventTitlePlainText.getText().toString(), eventPlacePlainText.getText().toString(),
                    stringDate, Double.parseDouble(latEditTextNumberDecimal.getText().toString()), Double.parseDouble(lngEditTextNumberDecimal.getText().toString()), LoginActivity.phoneEditText.getText().toString()));

            Intent intent = new Intent(this, MessageAddEventActivity.class);
            startActivity(intent);
            return true;
        }
    }
}