package com.example.a05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

public class file extends AppCompatActivity {
    private EditText editText;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        editText=(EditText)findViewById(R.id.edit);
        button=(Button)findViewById(R.id.button_1);
        final String[] line = {null};
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                     line[0] =Read();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Toast.makeText(file.this, line[0],Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String input=editText.getText().toString();
        try {
            save(input);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public void save(String input) throws IOException {
        FileOutputStream fileOutputStream=null;
        BufferedWriter writer=null;
        fileOutputStream = openFileOutput("data",Context.MODE_APPEND);
        writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        writer.write(input);
        writer.close();


    }
    public  String  Read() throws IOException {
        FileInputStream inputStream=null;
        BufferedReader bufferedReader=null;
        StringBuilder builder=new StringBuilder();

        inputStream=openFileInput("data");
        bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String line="";
        while((line = bufferedReader.readLine())!=null){
                 builder.append(line);
        }
        bufferedReader.close();
        return  builder.toString();

    }
}