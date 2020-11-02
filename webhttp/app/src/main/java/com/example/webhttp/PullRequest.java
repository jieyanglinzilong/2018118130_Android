package com.example.webhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PullRequest extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    private static final int update_text=1;
    Handler handler;
    static int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        Button button=findViewById(R.id.SendRequest);
        textView=findViewById(R.id.TextView);
        button.setOnClickListener(this);
        handler=new Handler(){
            @Override
            public void handleMessage(Message message){
                switch (message.what){
                    case update_text:
                        String msg= message.getData().getString("4");
                        System.out.println("*****"+msg);
                        textView.setText(msg);
                        break;
                }
            }
        };

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.SendRequest){
            count+=1;
            sendRequestConnection();
        }
    }
    private void sendRequestConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request request=new Request.Builder().url("http://192.168.3.220:8001/payment/get/"+count).build();
                    Call call=okHttpClient.newCall(request);
                    Response response=call.execute();
                    String string = response.body().string();

                    Message message = new Message();
                    message.what=update_text;

                    Bundle bundle=new Bundle();
                    bundle.putString("4",string);
                    message.setData(bundle);
                    handler.sendMessage(message);


                    



                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

}
    private void showRespones(String string){
        Message message = new Message();
        message.what=update_text;

        Bundle bundle=new Bundle();
        bundle.putString("4",string);
        message.setData(bundle);
        handler.sendMessage(message);

        textView.setText(string);

    }}