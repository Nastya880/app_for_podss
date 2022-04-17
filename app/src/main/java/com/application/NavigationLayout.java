package com.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class NavigationLayout extends RelativeLayout
{
    Button ok;

    public NavigationLayout(Context context, RelativeLayout parent)
    {
        super(context);
        initView(context,parent);
    }


    public void initView(final Context context,RelativeLayout parent)
    {
        // надуваем любой xml файл разметки
        View view= LayoutInflater.from(context).inflate(R.layout.view_drawer_layout,parent,true);

        ok=(Button)view.findViewById(R.id.ok);

        ok.setOnClickListener(new OnClickListener() {
//            Intent intent = new Intent(String.valueOf(NavigationLayoutTest.class));
//            //Intent intent = new Intent(this, FindStudioActivity.class);
//          //  startActivity(intent);
//            @Override
            public void onClick(View v) {
         //       Intent intent = new Intent(context, FindStudioActivity.class);
           //     context.startActivity(intent);
                Toast.makeText(context,"Ok", Toast.LENGTH_SHORT).show();
            }
        });

//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, FindEventActivity.class);
//                context.startActivity(intent);}
//        });

    }
}