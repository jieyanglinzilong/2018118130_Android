package com.example.a05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a05.entiy.ActivityCollter;

public class BaseAcitity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_acitity);
        ActivityCollter.addActivity(this);
    }
    @Override
    public  void onDestroy() {

        super.onDestroy();
        ActivityCollter.removeActivity(this);
    }
}