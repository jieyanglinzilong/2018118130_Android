package com.example.a04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Uichat extends AppCompatActivity {
    private List<Msg> msgList=new ArrayList<>();
    private EditText editText;
    private Button send;
    private RecyclerView recyclerView;
    private WechatAdapter wechatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uichat);
      //  init_msg();
       // editText=(EditText)findViewById(R.id.)

    }

}