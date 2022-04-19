package com.application;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AboutDeveloperActivity extends ParentNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);
        onCreateOption(savedInstanceState);
    }
}