package com.application;

import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends ParentNavigationActivity{

    public static boolean authFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onCreateOption(savedInstanceState);
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

        public void showLoginActivity(View view) {
        Intent intent;
        if (!authFlag) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, AddEventActivity.class);
        }
        startActivity(intent);
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
        public void showFindStudioActivity(View view) {
        if (!isOnline())
            showAlertDialog("Проверьте подключение к интернету", "ОШИБКА");
        else {
            Intent intent = new Intent(this, FindStudioActivity.class);
            startActivity(intent);
        }
    }
}