package com.xdh.everything;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView1 = (ListView) findViewById(R.id.listview1);

        String[] roles = {"Wenti","Dilic","babara"};
        String[] disc = {"wind","fire","water"};
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        for(int i=0;i<3;i++) {
            Map<String, Object> item= new HashMap<String,Object>();
            item.put("name",roles[i]);
            item.put("eyes",disc[i]);
            mapList.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,mapList,R.layout.item_list1,
                new String[]{"name","eyes"},new int[]{R.id.button,R.id.textView});
        listView1.setAdapter(simpleAdapter);

    }
}