package com.example.notication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class notation1 extends AppCompatActivity {
    private NotificationManager manager;
    private NotificationCompat.Builder notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notication1);
        Button button=(Button)findViewById(R.id.button_first);
        Button button1=(Button)findViewById(R.id.button_2);
        String CHANNEL_ID = "chat";
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "您收到一条短信",
                    NotificationManager.IMPORTANCE_DEFAULT);
            manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               notification=new NotificationCompat.Builder(notation1.this,CHANNEL_ID).
                       setSmallIcon(R.drawable.orange_pic).
                       setContentTitle("微信通知").
                       setContentText("下来吃饭").
                       setPriority(NotificationCompat.PRIORITY_DEFAULT);
                manager.notify(1,notification.build());

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification.setContentText("我更新了内容");
                manager.notify(1,notification.build());

            }
        });
    }

}