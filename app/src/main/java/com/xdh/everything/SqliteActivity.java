package com.xdh.everything;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.xdh.everything.utils.MyDBOpenHelper;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        MyDBOpenHelper myDBOpenHelper = new MyDBOpenHelper(this,"mydb",null,1);
        db = myDBOpenHelper.getWritableDatabase();
        bindView();
    }

    private void bindView() {
        Button add = (Button) findViewById(R.id.button_add);
        Button mod = (Button) findViewById(R.id.button_mod);
        Button del = (Button) findViewById(R.id.button_del);
        Button que = (Button) findViewById(R.id.button_que);
        Button clr = (Button) findViewById(R.id.button_clr);

        add.setOnClickListener(this);
        mod.setOnClickListener(this);
        del.setOnClickListener(this);
        que.setOnClickListener(this);
        clr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(this,"aa",Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.button_add:
                ContentValues values1 = new ContentValues();
                values1.put("name", "呵呵~" + i++);
                //参数依次是：表名，强行插入null值得数据列的列名，一行记录的数据
                db.insert("person", null, values1);
                Toast.makeText(this, "插入完毕~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_mod:
                ContentValues values2 = new ContentValues();
                values2.put("name", "嘻嘻~");
                //参数依次是表名，修改后的值，where条件，以及约束，如果不指定三四两个参数，会更改所有行
                db.update("person", values2, "name = ?", new String[]{"呵呵~2"});
                break;
            case R.id.button_del:
                //参数依次是表名，以及where条件与约束
                db.delete("person", "name = ?", new String[]{"呵呵~4"});
                break;
            case R.id.button_que:
                StringBuilder sb = new StringBuilder();
                //参数依次是:表名，列名，where约束条件，where中占位符提供具体的值，指定group by的列，进一步约束
                //指定查询结果的排序方式
                Cursor cursor = db.query("person", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        int pid = cursor.getInt(cursor.getColumnIndex("personid"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        sb.append("id：" + pid + "\t\tname：" + name + "\n");
                    } while (cursor.moveToNext());
                }
                cursor.close();
                Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_clr:
//                SQLiteDatabase 中提供了一个 delete()方法专门用于删除数据，这个方法接收三个参数，
//                第一 个参数仍然是表名，这个已经没什么好说的了，第二、第三个参数又是用于去约束删除某一 行或某几行的数据，
//                不指定的话默认就是删除所有行。
                    db.delete("person",null,null);
                break;
        }
    }
}