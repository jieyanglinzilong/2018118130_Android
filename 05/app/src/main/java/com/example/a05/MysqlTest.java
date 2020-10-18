package com.example.a05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MysqlTest extends AppCompatActivity {


    Mybroadcast receiver=new Mybroadcast();
    private LocalBroadcastManager  localBroadCast;

    private LocalReceiver   localReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql_test);
        Button button1=(Button)findViewById(R.id.button_1);
        localBroadCast=LocalBroadcastManager.getInstance(this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent("com.example.a05.localbrocast");
                localBroadCast.sendBroadcast(intent);




            }
        });
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
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.a05.localbrocast");
        localReceiver=new LocalReceiver();
        localBroadCast.registerReceiver(localReceiver,intentFilter);
        Button button2=(Button)findViewById(R.id.button_3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.a05.fore_offline");
                sendBroadcast(intent);
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //取消注册
        unregisterReceiver(receiver);
        localBroadCast.unregisterReceiver(localReceiver);

    }
    class  LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"收到本地广播",Toast.LENGTH_SHORT).show();

        }
    }
}