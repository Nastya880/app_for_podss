package com.application;

import android.os.Bundle;

public class InstructionsActivity extends ParentNavigationActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        onCreateOption(savedInstanceState);
    }
}
