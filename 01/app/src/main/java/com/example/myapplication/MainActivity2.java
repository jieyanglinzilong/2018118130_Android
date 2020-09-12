package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    static int m=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        m=m+1;
        System.out.println("创建活动   "+m);
        setContentView(R.layout.frist_layout);
        Button button=(Button)findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this,"点击按钮",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {


        super.onStart();
        m=m+1;
        System.out.println("启动"+m);
    }

    @Override
    protected void onPause() {

        super.onPause();
        m=m+1;
        System.out.println("暂停活动回调 "+m);
    }

    @Override
    protected void onResume() {

        m=m+1;
        super.onResume();
        System.out.println("恢复活动回调"+m);
    }

    @Override
    protected void onStop() {

        super.onStop();
        m=m+1;
        System.out.println("停止活动回调 "+m);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        m=m+1;
        System.out.println("销毁活动回调 "+m);
    }
    @Override
    protected  void onRestart() {

        super.onRestart();
        m=m+1;
        System.out.println("重启活动回调 "+m);
    }
}