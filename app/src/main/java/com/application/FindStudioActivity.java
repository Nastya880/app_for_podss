package com.application;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FindStudioActivity extends  ParentNavigationActivity {

    final String LOG_TAG = "myLogs for Studio";
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<String>();

    SeekBar costSeekBar;
    TextView costTextView;
    ListView list;

    boolean flag = true;

    public static ArrayList<StudioPojo> studios = new ArrayList<>();

    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_studio);
        onCreateOption(savedInstanceState);
        list = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        list.setAdapter(adapter);
        costSeekBar = findViewById(R.id.costSeekBar);
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
        refreshList();
    }



    public void showMaps(int index) {
        MapsActivity.currentPojo = studios.get(index);

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

//    public void setStudioFindFlag(View view) {
//        flag = true;
//        costSeekBar.setEnabled(true);
//        costSeekBar.setVisibility(View.VISIBLE);
//
//        refreshList();
//    }

    public void fillData() {

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

        }
    }

}