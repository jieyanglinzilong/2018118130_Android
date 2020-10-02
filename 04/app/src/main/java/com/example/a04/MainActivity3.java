package com.example.a04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    String[] data={"sister_d","sister_b","sister_a","sister_c"};
    private List<Grils> grilsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayout=new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayout);
        GrilsAdapter grilsAdapter=new GrilsAdapter(grilsList);
        recyclerView.setAdapter(grilsAdapter);

    }
    private void init(){
        String[] data={"apple","grape","banana","cherry_pic","orange","watermelon","pear","grape","mango"};
        for(int i=0;i<2;i++){
            Grils gril1=new Grils(data[0],R.drawable.apple_pic);

            grilsList.add(gril1);
            Grils gril2=new Grils(data[1],R.drawable.grape_pic);
            grilsList.add(gril2);
            Grils gril3=new Grils(data[2],R.drawable.banana_pic);
            grilsList.add(gril3);
            Grils gril4=new Grils(data[3],R.drawable.cherry_pic);
            grilsList.add(gril4);
            Grils gril5=new Grils(data[4],R.drawable.orange_pic);
            grilsList.add(gril3);
            Grils gril6=new Grils(data[5],R.drawable.watermelon_pic);
            grilsList.add(gril6);
            Grils gril7=new Grils(data[6],R.drawable.pear_pic);
            grilsList.add(gril7);
            Grils gril8=new Grils(data[7],R.drawable.grape_pic);
            grilsList.add(gril8);
            Grils gril9=new Grils(data[8],R.drawable.mango_pic);
            grilsList.add(gril9);


        }
}}