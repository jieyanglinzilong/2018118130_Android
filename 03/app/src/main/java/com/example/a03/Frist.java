package com.example.a03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Frist extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                break;
            case R.id.button2:
                break;
            default:
                break;


        }
    }
}