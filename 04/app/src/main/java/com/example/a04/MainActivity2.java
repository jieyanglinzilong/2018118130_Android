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
         String[] data={"sister_d","sister_b","sister_a","sister_c"};
         for(int i=0;i<2;i++){
             Grils gril1=new Grils(data[0],R.drawable.ab);
             grilsList.add(gril1);
             Grils gril2=new Grils(data[1],R.drawable.al);
             grilsList.add(gril2);
             Grils gril3=new Grils(data[2],R.drawable.bd);
             grilsList.add(gril3);
             Grils gril4=new Grils(data[3],R.drawable.h);
             grilsList.add(gril4);

        }

    }

}