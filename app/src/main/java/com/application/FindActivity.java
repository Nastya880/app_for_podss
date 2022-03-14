package com.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class FindActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<String>();

    Button findStudioButton;
    Button findEventButton;

    SeekBar costSeekBar;
    TextView costTextView;

    boolean flag = true;

    public static ArrayList<EventPojo> events = new ArrayList<>();
    public static ArrayList<StudioPojo> studios = new ArrayList<>();

    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ListView list = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        list.setAdapter(adapter);
        findStudioButton = findViewById(R.id.findStudioButton);
        findEventButton = findViewById(R.id.findEventButton);
        costSeekBar = findViewById(R.id.costSeekBar);
        costTextView = findViewById(R.id.costTextView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                showMaps(position);
            }
        });



        costSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                costTextView.setText("Цена (макс.) " + progress);
                refreshList();
            }
        });

        EditText searchDataEditText = (EditText) findViewById(R.id.searchDataEditText);
        searchDataEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                filter = searchDataEditText.getText().toString();
                refreshList();
            }
        });

        changeSelect();
        refreshList();
    }

    public void showMaps(int index){
        MapsActivity.currentStudioPojo = studios.get(index);
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
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
        costSeekBar.setEnabled(true);
        changeSelect();
        refreshList();
    }

    public void setEventFindFlag(View view) {
        flag = false;
        costSeekBar.setEnabled(false);
        changeSelect();
        refreshList();
    }

    public void fillData() {
        if (events.size() == 0) {
            events = APIHandler.getEvents();
        }
        if (studios.size() == 0) {
            studios = APIHandler.getStudios();
        }
    }

    public boolean costFilter(StudioPojo studioPojo) {
        if (costSeekBar.getProgress() > 0) {
            if (studioPojo.getAvgPrice() <= costSeekBar.getProgress()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean searchFilter(Pojo pojo) {
        if (filter.length() > 0) {
            if (pojo.toString().contains(filter)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public void refreshList() {
        fillData();
        adapter.clear();
        if (flag) {
            for (StudioPojo studioPojo : studios) {
                if (costFilter(studioPojo) && searchFilter(studioPojo)) {
                    adapter.add(studioPojo.toString());
                }
            }
        } else {
            for (EventPojo event : events) {
                if (searchFilter(event)) {
                    adapter.add(event.toString());
                }
            }
        }
    }

}