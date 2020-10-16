package com.example.a05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MysqlTest extends AppCompatActivity {
    private String datas="你好，我是调用者，mainApp.会返回给你数据，请查收！！！";

    Mybroadcast receiver=new Mybroadcast();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql_test);
        Button button=(Button)findViewById(R.id.button_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentFilter intentFilter=new IntentFilter();
                intentFilter.addAction("com.example.a05.mymin");
                Log.i("Tag", "发送数据");
                registerReceiver(receiver, intentFilter);//注册接收者
                Intent intent = new Intent("com.example.a05.mymin");
                intent.putExtra("zhh", "123456");
                sendBroadcast(intent);

            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //取消注册
        unregisterReceiver(receiver);
    }

}