package com.example.a05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Mybroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("111", "接收成功"+intent.getStringExtra("zhh"));
        Toast.makeText(context,"接收成功",Toast.LENGTH_SHORT).show();
    }
}