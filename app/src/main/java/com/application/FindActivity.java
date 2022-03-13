package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class FindActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<String>();

    Button findStudioButton;
    Button findEventButton;

    boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ListView list = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        list.setAdapter(adapter);
        findStudioButton = findViewById(R.id.findStudioButton);
        findEventButton = findViewById(R.id.findEventButton);
        changeSelect();
        refreshList();
    }

    public void changeSelect() {
        if (flag) {
            findStudioButton.setEnabled(false);
            findEventButton.setEnabled(true);
        } else {
            findStudioButton.setEnabled(true);
            findEventButton.setEnabled(false);
        }
    }

    public void setStudioFindFlag(View view) {
        flag = true;
        changeSelect();
        refreshList();
    }

    public void setEventFindFlag(View view)  {
        flag = false;
        changeSelect();
        refreshList();
    }

    public void refreshList()  {
        adapter.clear();
        if (flag) {
            for (StudioPojo studioPojo : APIHandler.getStudios()) {
                adapter.add(studioPojo.toString());
            }
        } else {
            for (EventPojo event : APIHandler.getEvents()) {
                adapter.add(event.toString());
            }
        }
    }

}