package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Standard extends AppCompatActivity {
    static String num="Standard";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        Button button=(Button)findViewById(R.id.button_1);
        Log.d("Standard",this.toString());
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(Standard.this,"点击",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Standard.this,Standard.class);
                startActivity(intent);
            }
        });
        Button button1=(Button)findViewById(R.id.button_2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button_2:Intent intent2=new Intent(Intent.ACTION_VIEW);
                    intent2.setData(Uri.parse("http://www.baidu.com"));
                    startActivity(intent2);break;
                    case R.id.button_1:
                        Toast.makeText(Standard.this,"点击",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Standard.this,MainActivity.class);
                        startActivity(intent); break;
                }

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
       getMenuInflater().inflate(R.menu.main,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"打开",Toast.LENGTH_SHORT).show();
            case R.id.new_item:
                Toast.makeText(this,"创建文件",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}