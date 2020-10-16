package com.example.a05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private String return_caller_data;
    private String parser_caller_data;
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("tag", "接收到调用者的数据");
        System.out.println("hello2");
        if("com.example.return.data.action".equals(intent.getAction())){
            Log.i("tag", "接收到调用者的action=="+intent.getAction());
            String mainData=intent.getStringExtra("mainApp_send_data");

            Log.i("tag", "接收到调用者的data=="+ parserCallerData(mainData));
            Toast.makeText(context,"收到静态广播",Toast.LENGTH_LONG).show();

            //接收到调用者的广播，回复调用者数据
            Intent return_data_intent=new Intent("com.example.return.data.action");
            return_data_intent.putExtra("return_data", returnCallerData());
            context.sendBroadcast(return_data_intent);
        }

    }

    //解析调用者传过来的数据
    private String parserCallerData(String param){
        parser_caller_data=param;
        return parser_caller_data;
    }

    //返回给调用者数据。
    private String returnCallerData(){
        return_caller_data="我是被调用者，返回给调用者数据，ybsybsybs。";
        return return_caller_data;
    }
}
