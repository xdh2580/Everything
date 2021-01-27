package com.xdh.everything;

import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    String[] roles = {"Wenti","Dilic","babara"};
    String[] disc = {"wind","fire","water"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView1 = (ListView) findViewById(R.id.listview1);

        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        for(int i=0;i<3;i++) {
            Map<String, Object> item= new HashMap<String,Object>();
            item.put("name",roles[i]);
            item.put("eyes",disc[i]);
            mapList.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,mapList,R.layout.item_list1,
                new String[]{"name","eyes"},new int[]{R.id.button,R.id.textView});

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.item,roles);
        listView1.setAdapter(simpleAdapter);
    //    listView1.setAdapter(arrayAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("xdh","onClick");
                Toast.makeText(MainActivity.this,"you clickd "+roles[position],Toast.LENGTH_SHORT).show();
            }
        });
        listView1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"you clickd "+roles[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this,"nothing selected",Toast.LENGTH_SHORT).show();
            }
        });


    }
}