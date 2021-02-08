package com.xdh.everything;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LayoutInflaterActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_layout_inflater);
        final RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setId(111);
        Button button = new Button(this);
        button.setText("按钮1");
        Button button1 = new Button(this);
        button1.setText("按钮2");
        button.setId(123);
        // 设置按钮1的位置,在父容器中居中
        RelativeLayout.LayoutParams rlp1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlp1.addRule(RelativeLayout.CENTER_IN_PARENT);
        // 设置按钮2的位置,在按钮1的下方,并且对齐父容器右面
        RelativeLayout.LayoutParams rlp2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlp2.addRule(RelativeLayout.BELOW, 123);
        rlp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        // 将组件添加到外部容器中
        relativeLayout.addView(button1, rlp2);
        relativeLayout.addView(button, rlp1);
        // 调用setContentView( )方法加载布局对象即可! 另外，如果你想移除某个容器中的View，可以调用容器.removeView(要移除的组件);
        setContentView(relativeLayout);
//再演示一个
        Button btnOne = new Button(this);
        btnOne.setText("都是动态添加的按钮");
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.CENTER_IN_PARENT);
        lp2.addRule(RelativeLayout.ABOVE, 123);

        relativeLayout.addView(btnOne,lp2);

        //再演示一种方式，按钮控制加载
        final LayoutInflater inflater = LayoutInflater.from(this);
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              获得Inflater对象,同时加载被添加的布局的xml,通过findViewById找到最外层的根节点
                LinearLayout ly = (LinearLayout) inflater.inflate(
                        R.layout.activity_layout_inflater, null, false).findViewById(R.id.linlay);
//              为这个容器设置大小与位置信息:
                RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp3.addRule(RelativeLayout.ALIGN_PARENT_END);
                lp3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//              添加到外层容器中:
                relativeLayout.addView(ly,lp3);

                Toast.makeText(LayoutInflaterActivity.this,"ya!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}