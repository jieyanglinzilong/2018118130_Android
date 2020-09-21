package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "ManActivity2" ;
    static int m=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d(TAG,"onCreate");
        setContentView(R.layout.frist_layout);
        Button button=(Button)findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this,"点击按钮",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {


        super.onStart();
        Log.d(TAG,"onStart");

    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.d(TAG,"onPause");

    }

    @Override
    protected void onResume() {


        super.onResume();
        Log.d(TAG,"onResume");

    }

    @Override
    protected void onStop() {

        super.onStop();
        Log.d(TAG,"onStop");

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.d(TAG,"onDestroy");

    }
    @Override
    protected  void onRestart() {

        super.onRestart();
        Log.d(TAG,"onRestart");

    }
}