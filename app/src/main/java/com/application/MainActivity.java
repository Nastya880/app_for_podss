package com.application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.application.databinding.ActivityFindStudioBinding;

public class MainActivity extends AppCompatActivity {

    public static boolean authFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    public void showLoginActivity(View view) {
        Intent intent;
        if (!authFlag) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, AddEventActivity.class);
        }
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

    final String LOG_TAG = "myLogs";
    final int DIALOG = 1;

    Dialog dialog;

    public void info(View view) {
        showAlertDialog("Приложение разработано в рамках выпускной работы в МИЭТ студенткой группы ПИН-41 Макеевой Анастасией", "Информация");
    }

    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null)
            return false;
        else
            return true;
    }

    public void showFindEventActivity(View view) {
        if (!isOnline())
            showAlertDialog("Проверьте подключение к интернету", "ОШИБКА");
        else {
            Intent intent = new Intent(this, FindEventActivity.class);
            startActivity(intent);
        }
    }
    public void showAlertDialog(String message, String title)
    {
        final int DIALOG_EXIT = 1;
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle(title);
        adb.setMessage(message);
        adb.setNeutralButton(R.string.ok, null);
        adb.create();
        showDialog(DIALOG_EXIT);
        adb.show();
    }

    public void showFindStudioActivity(View view) {
        if (!isOnline())
            showAlertDialog("Проверьте подключение к интернету", "ОШИБКА");
        else {
            Intent intent = new Intent(this, FindStudioActivity.class);
            startActivity(intent);
        }
    }
}