package com.example.file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SharedPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        Button button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.SharedPreferences.Editor editor=getSharedPreferences("lg",MODE_PRIVATE).edit();
                editor.putString("name","李菲菲");
                editor.putInt("age",25);
                editor.putString("account","lin");
                editor.putString("password","123456");
                editor.apply();


            }
        });
        Button button1=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.SharedPreferences sharedPreferences=getSharedPreferences("lg",MODE_PRIVATE);
                String name=sharedPreferences.getString("name","");
                int age=sharedPreferences.getInt("age",0);

                String people = name.toString()+
                Log.d("SharedPreferences","name is "+name+"age is "+age);
            }
        });
    }
}