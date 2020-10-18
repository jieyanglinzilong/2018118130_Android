package com.example.a05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.a05.entiy.ActivityCollter;

public class BaseAcitity extends AppCompatActivity {
    private ForceofflineReceiver forceofflineReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_acitity);
        ActivityCollter.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.a05.fore_offline");
        forceofflineReceiver=new ForceofflineReceiver();
        registerReceiver(forceofflineReceiver,intentFilter);

    }

    @Override
    public  void onDestroy() {

        super.onDestroy();
        ActivityCollter.removeActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(forceofflineReceiver!=null){
            unregisterReceiver(forceofflineReceiver);
            forceofflineReceiver=null;
        }
    }


    class ForceofflineReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);
            alertDialog.setTitle("Waring");
            alertDialog.setMessage("多台设备登陆，本设备将被强制下线");
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollter.finishAll();

                    Intent i=new Intent(context,LoginActivity.class);
                    context.startActivity(i);
                }
            });
            alertDialog.show();
        }
    }
}