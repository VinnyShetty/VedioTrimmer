package com.marolix.videotrimmer;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class ListOfTrimVideos extends AppCompatActivity {
ListView lv;
CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv=findViewById(R.id.listview);
        ArrayList<String> al=new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().getPath()+"/trim videos/";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            al.add(files[i].getName());
            Log.d("Files", "FileName:" + files[i].getName());
        }
//        aa = new ArrayAdapter<String>(  this,
//                android.R.layout.simple_list_item_1,
//                al
//        );
//        lv.setAdapter(aa);

        customAdapter=new CustomAdapter(this,al);
        lv.setAdapter(customAdapter);

    }
    }