package com.example.a04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity3 extends AppCompatActivity {
    String[] data={"sister_d","sister_b","sister_a","sister_c"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                MainActivity3.this,android.R.layout.simple_list_item_1,data);
        ListView listView=(ListView)findViewById(R.id.list_item);
        listView.setAdapter(adapter);

    }
}