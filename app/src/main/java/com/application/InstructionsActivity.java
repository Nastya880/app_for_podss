package com.application;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class InstructionsActivity extends ParentNavigationActivity{
    /**
     * Создание активити
     * Экран с инструкциями по использованию приложения
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        onCreateOption(savedInstanceState);

//
//        ScrollView scrollView = new ScrollView(this);
//
//        TextView textView = findViewById(R.id.textInsruction);
//        textView.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry...like Aldus PageMaker including versions of Lorem Ipsum.");
//        textView.setLayoutParams(new ViewGroup.LayoutParams
//                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        textView.setTextSize(26);
//        scrollView.addView(textView);
//        setContentView(scrollView);
    }
}
