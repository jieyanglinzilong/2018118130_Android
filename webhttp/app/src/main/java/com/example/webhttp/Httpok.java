package com.example.webhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Httpok extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpok);
        Button button=findViewById(R.id.SendRequest);
        textView=findViewById(R.id.TextView);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.SendRequest){
            sendRequestConnection();
        }
    }

    private void sendRequestConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request request=new Request.Builder().url("https://www.example.com").build();
                    Call call=okHttpClient.newCall(request);
                    Response response=call.execute();
                    String string = response.body().string();
                    showRespones(string);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showRespones(String string) {
        textView.setText(string);
    }

}