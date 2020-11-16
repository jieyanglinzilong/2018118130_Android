package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private  DowloadBinder mbinder=new DowloadBinder();
    class DowloadBinder extends Binder {
        public void startDowload(){
            Log.d("Myservice","开始下载");

        }
        public int getPorogress(){
            Log.d("Myservice","进度查询");
            return 0;
        }



    }
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mbinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Stop","服务销毁");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Create","服务创建");

    }
}