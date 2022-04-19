package com.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ParentNavigationActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    protected void onCreateOption(Bundle savedInstanceState) {
        setUpToolbar();

        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_add_event:
                        Intent intentaddEvent = new Intent(ParentNavigationActivity.this, AddEventActivity.class);
                        startActivity(intentaddEvent);
                        break;
                    case R.id.nav_find_event:

                        Intent intentfindEvent = new Intent(ParentNavigationActivity.this, FindEventActivity.class);
                        startActivity(intentfindEvent);
                        break;
                    case  R.id.nav_home:

                        Intent intentHome = new Intent(ParentNavigationActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        break;
                    case  R.id.nav_find_studio:

                        Intent intentfindStudio = new Intent(ParentNavigationActivity.this, FindStudioActivity.class);
                        startActivity(intentfindStudio);
                        break;
                    case R.id.nav_instruction:
                        Intent intentInstructions = new Intent(ParentNavigationActivity.this , InstructionsActivity.class);
                        startActivity(intentInstructions);
                        break;
                    case R.id.nav_about_developer:
                        Intent intentAboutDeveloper = new Intent(ParentNavigationActivity.this , AboutDeveloperActivity.class);
                        startActivity(intentAboutDeveloper);
                        break;


//Paste your privacy policy link

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                    //       break;
                    case  R.id.nav_share:{

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                    }
                    break;
                }
                return false;
            }
        });
    }

    public void lightbutton(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }

    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        actionBarDrawerToggle.syncState();

    }}