package com.xdh.everything;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
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
    List<String> list = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    private Button button;
    private Button button_j;
    private ListView listView1;
    private TextView textView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.add("init String 0");


         button = (Button) findViewById(R.id.button);
         button_j = (Button) findViewById(R.id.button2) ;
         Button button_f = (Button) findViewById(R.id.button3);
         Button button_s = (Button) findViewById(R.id.button6);
         Button button_l = (Button) findViewById(R.id.button7);
         listView1 = (ListView) findViewById(R.id.listview1);
         textView = (TextView) findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("add "+Math.random());
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"added"+this.getClass().getName(),Toast.LENGTH_SHORT).show();

            }
        });

        button_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FundActivity.class);
                startActivity(intent);
            }
        });

        button_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FileActivity.class);
                startActivity(intent);
            }
        });

        button_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SqliteActivity.class);
                startActivity(intent);
            }
        });
        button_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LayoutInflaterActivity.class);
                startActivity(intent);
            }
        });



        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        for(int i=0;i<3;i++) {
            Map<String, Object> item= new HashMap<String,Object>();
            item.put("name",roles[i]);
            item.put("eyes",disc[i]);
            mapList.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,mapList,R.layout.item_list1,
                new String[]{"name","eyes"},new int[]{R.id.name,R.id.eyes});

        arrayAdapter = new ArrayAdapter(this,R.layout.item,list);
      //  listView1.setAdapter(simpleAdapter);
        listView1.setAdapter(arrayAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("xdh","onClick");

                Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_SHORT).show();
            }
        });
//        listView1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,"you clickd "+roles[position],Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(MainActivity.this,"nothing selected",Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        textView.setText("正在充电："+isCharging());
    }

    public boolean isCharging() {
        Intent batteryBroadcast = this.registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        // 0 means we are discharging, anything else means charging
        boolean isCharging = batteryBroadcast.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) != 0;
        return isCharging;
    }


//    //判断应用是否在前台运行
//    public boolean isRunningForeground(Context context){
//        String packageName=getPackageName(context);
//        String topActivityClassName=getTopActivityName(context);
//        System.out.println("packageName="+packageName+",topActivityClassName="+topActivityClassName);
//        if (packageName!=null&&topActivityClassName!=null&&topActivityClassName.startsWith(packageName)) {
//            System.out.println("应用在前台执行");
//            return true;
//        } else {
//            System.out.println("应用在后台执行");
//            return false;
//        }
//    }
//
//    // 判断应用是否在运行
//    public boolean isRun(Context context,String mPackageName){
//        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
//        List<RunningTaskInfo> list = am.getRunningTasks(100);
//        boolean isAppRunning = false;
//        //100表示取的最大的任务数，info.topActivity表示当前正在运行的Activity，info.baseActivity表示系统后台有此进程在运行
//        for (RunningTaskInfo info : list) {
//            if (info.topActivity.getPackageName().equals(mPackageName) || info.baseActivity.getPackageName().equals(mPackageName)) {
//                isAppRunning = true;
//                Log.i("ActivityService",info.topActivity.getPackageName() + " info.baseActivity.getPackageName()="+info.baseActivity.getPackageName());
//                break;
//            }
//        }
//        if(isAppRunning){
//            Log.i("ActivityService", "该程序正在运行");
//        }else{
//            Log.i("ActivityService", "该程序没有运行");
//        }
//        return isAppRunning;
//    }
//    //获取栈顶ActivityName
//    public  String getTopActivityName(Context context){
//        String topActivityClassName=null;
//        ActivityManager activityManager =
//                (ActivityManager)(context.getSystemService(android.content.Context.ACTIVITY_SERVICE )) ;
//        List<runningtaskinfo> runningTaskInfos = activityManager.getRunningTasks(1) ;
//        if(runningTaskInfos != null){
//            ComponentName f=runningTaskInfos.get(0).topActivity;
//            topActivityClassName=f.getClassName();
//        }
//        return topActivityClassName;
//    }

    public String getPackageName(Context context){
        String packageName = context.getPackageName();
        return packageName;
    }
}