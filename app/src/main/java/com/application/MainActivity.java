package com.application;

import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends ParentNavigationActivity{

    /* Флаг для определения: авторизован пользователь или нет
       Используется, чтобы не проходить уже авторизованному пользователю вновь
       авторизацию при добавлении нового мероприятия */
    public static boolean authFlag = false;

    /**
     * Создание активити
     * главный экран
     * @param savedInstanceState Текущее состояние
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onCreateOption(savedInstanceState);
    }

    /**
     * Создание диалогового окна
     * @param message Сообщение в окне
     * @param title Заголовок окна
     */
    public void showAlertDialog(String message, String title)
    {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle(title);
        adb.setMessage(message);
        adb.setNeutralButton(R.string.ok, null);
        adb.create();
        adb.show();
    }

    /**
     * Обработка нажатия кнопки "Добавить мероприятие"
     * переход на активити с авторизацией
     * @param view
     */
        public void showLoginActivity(View view) {
        Intent intent;
        if (!authFlag) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, AddEventActivity.class);
        }
        startActivity(intent);
    }

    /**
     * Проверка интернет-соединения
     * @return
     */
    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null)
            return false;
        else
            return true;
    }

    /**
     * Обработка нажатия кнопки "Найти мероприятие"
     * переход на активити поиска мероприятия
     * @param view
     */
    public void showFindEventActivity(View view) {
        if (!isOnline())
            showAlertDialog("Проверьте подключение к интернету", "ОШИБКА");
        else {
            Intent intent = new Intent(this, FindEventActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Обработка нажатия кнопки "Найти студию"
     * переход на активити поиска студии
     * @param view
     */
    public void showFindStudioActivity(View view) {
        if (!isOnline())
            showAlertDialog("Проверьте подключение к интернету", "ОШИБКА");
        else {
            Intent intent = new Intent(this, FindStudioActivity.class);
            startActivity(intent);
        }
    }
}