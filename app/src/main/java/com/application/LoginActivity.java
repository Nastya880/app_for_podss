package com.application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends ParentNavigationActivity {

    TextView loginInfoText;
    static  EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onCreateOption(savedInstanceState);
        loginInfoText = findViewById(R.id.loginInfoText);
        phoneEditText = findViewById(R.id.editTextPhone);
    }

    private boolean checkCorrect(String phoneString) {
        //String phoneNumber = "+7(9##) ###-##-##";
        // String text = String.valueOf( android.telephony.PhoneNumberUtils.formatNumber(phoneNumber) );
        //formatted: 123-456-7890
        //phoneEditText.setText(text);
        //set editText text equal to new formatted number
        //phoneEditText.setSelection(text.length());
        //move cursor to end of editText view
        if (phoneString.length() != 12) {
            return false;
        }
        if (phoneString.toCharArray()[0] != '+' || phoneString.toCharArray()[1] != '7' || phoneString.toCharArray()[2] != '9') {
            return false;
        }
        return true;
    }

    public void click(View view) {
        if (checkCorrect(phoneEditText.getText().toString())) {
            //MainActivity.authFlag = true;
            Intent intent = new Intent(this, AddEventActivity.class);
            startActivity(intent);
        } else {
            final int DIALOG_EXIT = 1;
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.error);
            adb.setMessage("Введите ваш номер телефона в формате: +7 (9ХХ) ХХХ-ХХ-ХХ");
            adb.setNeutralButton(R.string.ok, null);
            adb.create();
            showDialog(DIALOG_EXIT);
            adb.show();
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