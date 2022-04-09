package com.application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

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

    public void showFindActivity(View view) {
        Intent intent = new Intent(this, FindActivity.class);
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


    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            Log.d(LOG_TAG, "Create");
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Информация");
            adb.setMessage("Приложение разработано в рамках выпускной работы в МИЭТ студенткой группы ПИН-41 Макеевой Анастасией");
            adb.setPositiveButton("OK", null);
            dialog = adb.create();

            // обработчик отображения
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialog) {
                    Log.d(LOG_TAG, "Show");
                }
            });

            // обработчик отмены
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    Log.d(LOG_TAG, "Cancel");
                }
            });

            // обработчик закрытия
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    Log.d(LOG_TAG, "Dismiss");
                }
            });
            return dialog;
        }
        return super.onCreateDialog(id);
    }

    public void info(View view) {
        showDialog(DIALOG);
    }
}