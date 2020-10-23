package com.example.file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.file.Entiy.People;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class db extends AppCompatActivity {
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        book=new Book(this,"bookstore.db",null,1);
        Button button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.getWritableDatabase();
            }
        });
        Button button1=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();

            }
        });
        Button button2=(Button)findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("开始添加数据");
                People people=new People();
                people.setAge(18);
                people.setId(1001);
                people.setName("叶琳");
                people.setRecoard(false);

                people.save();
                List<People> peopleList=LitePal.findAll(People.class);
                for (People people1:peopleList){
                    System.out.println(people1);
                }
            }
        });
        Button button3=(Button)findViewById(R.id.button4);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                People people=new People();
                people.setAge(18);
                people.setId(1002);
                people.setName("李菲菲");
                people.setRecoard(false);
                people.updateAll("name =? ","叶琳");




            }
        });

        Button button4=(Button)findViewById(R.id.button5);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              LitePal.deleteAll(People.class);




            }
        });
    }
}