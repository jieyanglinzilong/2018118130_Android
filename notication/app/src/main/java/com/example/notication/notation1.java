package com.example.notication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class notation1 extends AppCompatActivity {
    private NotificationManager manager;
    private NotificationCompat.Builder notification;
    private static final String REPLY_KEY = "key_text_reply";
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notication1);
        Button button=(Button)findViewById(R.id.button_first);
        Button button1=(Button)findViewById(R.id.button_2);
        String CHANNEL_ID = "chat";
        Intent intent=new Intent(notation1.this,Mainui.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
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
                       setAutoCancel(true).
                       setContentIntent(pi).
                       setPriority(NotificationCompat.PRIORITY_DEFAULT);

                manager.notify(1,notification.build());

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification.setContentText("快点");
                manager.notify(1,notification.build());

            }
        });
        Button button2=(Button)findViewById(R.id.button_2);

    }


}


