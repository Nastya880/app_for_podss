package com.application;

import android.content.Intent;
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
import android.widget.TextView;
import java.util.ArrayList;

public class FindEventActivity extends ParentNavigationActivity {

    //final String LOG_TAG = "myLogs";
    /* ArrayAdapter нужен для представления данных  в виде
       массива ListView, элементы которого размещаются в отдельных элементах TextView
     */
    ArrayAdapter<String> adapter;
    //Динамический массив
    ArrayList<String> listItems = new ArrayList<String>();
    TextView costTextView;
    //Список мероприятий для отображения
    ListView list;
    boolean flag = true;
    //Список с характеристиками мероприятий
    public static ArrayList<EventPojo> events = new ArrayList<>();
    private String filter = "";

    /**
     * Создание активити
     * Экран поиска мероприятий
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_event);
        //Боковое меню-гамбургер
        onCreateOption(savedInstanceState);
        list = (ListView) findViewById(R.id.list);
        //Переходник между данными в массиве и списком ListView
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        list.setAdapter(adapter);
        costTextView = findViewById(R.id.costTextView);

        //Обработка взаимодействия со списком
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * При нажатии на элемент списка отображается метка на карте
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                showMaps(position);
            }
        });

        //Обработка прокрутки списка
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /* Log.d(LOG_TAG, "scrollState = " + scrollState); */
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
              /*  Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
                        + ", visibleItemCount" + visibleItemCount
                        + ", totalItemCount" + totalItemCount); */
            }
        });

        //Переменная, хранящая введенное название для поиска мероприятия
        EditText searchDataEditText = (EditText) findViewById(R.id.searchDataNameEventEditText);
        /* Методы интерфейса TextWatcher
           Обработка поискового запроса по названию */
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

    /**
     * Отображение маркера местоположения мероприятия на Google-карте
     * @param index
     */
    public void showMaps(int index) {

        MapsActivity.currentPojo = events.get(index);
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /**
     * Получение данных о мероприятиях с сервера
     */
    public void fillData() {
        if (events.size() == 0) {
            events = APIHandler.getEvents();
        }
    }

    /**
     * Проверка использования поискового запроса по названию
     * @param pojo
     * @return
     */
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

    /**
     * Обновление отображения списка мероприятий после использования поиска
     */
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


