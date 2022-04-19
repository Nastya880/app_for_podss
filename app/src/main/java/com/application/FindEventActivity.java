package com.application;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;

public class FindEventActivity extends ParentNavigationActivity {
    final String LOG_TAG = "myLogs";
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<String>();

    TextView costTextView;
    ListView list;

    boolean flag = true;
    public static ArrayList<EventPojo> events = new ArrayList<>();

    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_event);
        onCreateOption(savedInstanceState);
        list = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        list.setAdapter(adapter);
        costTextView = findViewById(R.id.costTextView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                showMaps(position);
            }
        });

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d(LOG_TAG, "scrollState = " + scrollState);
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
                        + ", visibleItemCount" + visibleItemCount
                        + ", totalItemCount" + totalItemCount);
            }
        });

        EditText searchDataEditText = (EditText) findViewById(R.id.searchDataNameEventEditText);
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
        refreshList();
    }

    public void showMaps(int index) {

        MapsActivity.currentPojo = events.get(index);
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

//    public void setEventFindFlag(View view) {
//        flag = true;
//        refreshList();
//    }

    public void fillData() {
        if (events.size() == 0) {
            events = APIHandler.getEvents();
        }
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
            for (EventPojo event : events) {
                if (searchFilter(event)) {
                    adapter.add(event.toString());
                }
            }
        }
    }

}


