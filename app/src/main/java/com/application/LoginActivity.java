package com.application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView loginInfoText;
    EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginInfoText = findViewById(R.id.loginInfoText);
        phoneEditText = findViewById(R.id.editTextPhone);
    }

    private boolean checkCorrect(String phoneString) {
        if (phoneString.length() != 12) {
            return false;
        }
        if (phoneString.toCharArray()[0] != '+' || phoneString.toCharArray()[1] != '7') {
            return false;
        }
        return true;
    }

    //public void close(View view) {
//        //эмулируем нажатие на HOME, сворачивая приложение
//        Intent i = new Intent(Intent.ACTION_MAIN);
//        i.addCategory(Intent.CATEGORY_HOME);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(i);
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    //}


    public void click(View view) {
        if (checkCorrect(phoneEditText.getText().toString())) {
            MainActivity.authFlag = true;
            Intent intent = new Intent(this, AddEventActivity.class);
            startActivity(intent);
        } else {
            loginInfoText.setText("Неверный формат! Введите номер телефона в формате +7..");
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