package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class UseService extends AppCompatActivity implements View.OnClickListener {
   private MyService.DowloadBinder dowloadBinder;
   private ServiceConnection serviceConnection=new ServiceConnection() {
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
           dowloadBinder=(MyService.DowloadBinder)service;
           dowloadBinder.startDowload();
           dowloadBinder.getPorogress();
       }

       @Override
       public void onServiceDisconnected(ComponentName name) {

       }
   };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_service);
        Button start=findViewById(R.id.Start);
        Button stop = findViewById(R.id.Stop);
        Button bind=findViewById(R.id.bind);
        Button unbind=findViewById(R.id.Unbind);

        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.Start:
               Intent startIntent =new Intent(this,MyService.class);
               startService(startIntent);
               break;
           case R.id.Stop:
               Intent stopIntent = new Intent(this, MyService.class);
               stopService(stopIntent);
               break;
           case R.id.bind:
               Intent bindIntent=new Intent(this, MyService.class);
               bindService(bindIntent,serviceConnection,BIND_AUTO_CREATE);
               break;
               //bindService(bindIntent);
           case R.id.Unbind:
               unbindService(serviceConnection);
               break;
           default:
               break;
       }

    }
    
}