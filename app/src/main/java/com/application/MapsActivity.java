package com.application;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.application.databinding.ActivityMapsBinding;

public class MapsActivity extends ParentNavigationActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    //Текущая сущность для получения координат
    public static Pojo currentPojo;

    /**
     * Получаем доступ к фрагменту Google-карты
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Получение фрагмента и уведомления, когда карта готова к использованию
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //Загрузка карты в асинхронном режиме
        mapFragment.getMapAsync(this);
    }

    /**
     * Добавление маркера на карту, когда она доступна
     * Если на устройсте не установлены сервисы Google play, будет предложено их установить
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng marker;
        try {
            marker = new LatLng(((StudioPojo) currentPojo).getLat(), ((StudioPojo) currentPojo).getLng());
            //Установка наименования маркера при его нажатии
            mMap.addMarker(new MarkerOptions().position(marker).title("Студия \"" + ((StudioPojo) currentPojo).getName() + "\""));
        } catch (ClassCastException e) {
            marker = new LatLng(((EventPojo) currentPojo).getLat(), ((EventPojo) currentPojo).getLng());
            //Установка наименования маркера при его нажатии
            mMap.addMarker(new MarkerOptions().position(marker).title("Мероприятие \"" + ((EventPojo) currentPojo).getName() + "\""));
        }
        //Перемещение карты в нужную позицию (туда, где находится маркер)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }

}