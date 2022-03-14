package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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


}