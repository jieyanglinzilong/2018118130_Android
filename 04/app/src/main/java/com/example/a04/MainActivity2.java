package com.example.a04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
   private List<Grils> grilsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayout=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        GrilsAdapter grilsAdapter=new GrilsAdapter(grilsList);
        recyclerView.setAdapter(grilsAdapter);

    }
    private void init(){
         String[] data={"apple","grape","banana"};
         for(int i=0;i<2;i++){
             Grils gril1=new Grils(data[0],R.drawable.apple_pic);

             grilsList.add(gril1);
             Grils gril2=new Grils(data[1],R.drawable.grape_pic);
             grilsList.add(gril2);
             Grils gril3=new Grils(data[2],R.drawable.banana_pic);
             grilsList.add(gril3);


        }

    }

}