package com.xdh.everything;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.xdh.everything.utils.FileOperator;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText content;
    private Context mContext;
    private Button read;
    private Button write;
    FileOperator mfileOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        mContext = getApplicationContext();
        bindViews();
        mfileOperator = new FileOperator(this);
    }

    private void bindViews() {
    content = (EditText) findViewById(R.id.edit_content);
    read = (Button) findViewById(R.id.button_read);
    write = (Button) findViewById(R.id.button_write);

    read.setOnClickListener(this);
    write.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_read:
                try {
                    String s = mfileOperator.read("demo.txt");
                    Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_write:
                try {
                    mfileOperator.save("demo.txt",content.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(mContext,content.getText(),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}