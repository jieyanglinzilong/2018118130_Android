package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static String num="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button_1);
        Log.d("MainActivity",this.toString());
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Button button1=(Button)findViewById(R.id.button_2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button_2:Intent intent2=new Intent(MainActivity.this,singleinstance.class);
                        //intent2.setData();
                        startActivity(intent2);break;
                    case R.id.button_1:
                        Toast.makeText(MainActivity.this,"点击",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent); break;
                }

            }
        });
    }
}