package com.example.a03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;

public class Framelayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);
        ActionBar actionBar=getActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }
}