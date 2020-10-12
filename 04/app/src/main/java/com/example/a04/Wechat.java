package com.example.a04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Wechat extends AppCompatActivity {
    private List<Msg> msgList=new ArrayList<Msg>();
    private EditText editText;
    private Button send;
    private RecyclerView recyclerView;
    private WechatAdapter wechatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        editText=(EditText)findViewById(R.id.input_text);
        send=(Button)findViewById(R.id.send);
        init_msg();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view2);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        wechatAdapter=new WechatAdapter(msgList);
        recyclerView.setAdapter(wechatAdapter);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String content = editText.getText().toString();

                if(!"".equals(content)){
                    Msg msg=new Msg(content,Msg.Type_send);
                    msgList.add(msg);
                    System.out.println(msgList.toString());
                    wechatAdapter.notifyItemChanged(msgList.size()-1);
                    recyclerView.scrollToPosition(msgList.size()-1);
                    editText.setText("");

                }
            }
        });
    }
    private void init_msg(){
        Msg msg1=new Msg("hello",Msg.Type_Received);
        msgList.add(msg1);
        Msg msg2=new Msg("hello guy",Msg.Type_Received);
        msgList.add(msg2);
        Msg msg3=new Msg("how are you",Msg.Type_send);
        System.out.println(Msg.Type_send);
        msgList.add(msg3);

    }
}