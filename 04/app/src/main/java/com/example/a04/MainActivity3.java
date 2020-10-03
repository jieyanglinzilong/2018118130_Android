package com.example.a04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    private List<Grils> grilsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager  linearLayout=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        //linearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        GrilsAdapter grilsAdapter=new GrilsAdapter(grilsList);
        recyclerView.setAdapter(grilsAdapter);

    }
    private void init(){
        String[] data={"apple","grape","banana","cherry_pic","orange","watermelon","pear","grape","mango"};
        for(int i=0;i<10;i++){
            Grils gril1=new Grils(getRandomLengthName(data[0]),R.drawable.apple_pic);

            grilsList.add(gril1);
            Grils gril2=new Grils(getRandomLengthName(data[1]),R.drawable.grape_pic);
            grilsList.add(gril2);
            Grils gril3=new Grils(getRandomLengthName(data[2]),R.drawable.banana_pic);
            grilsList.add(gril3);
            Grils gril4=new Grils(getRandomLengthName(data[3]),R.drawable.cherry_pic);
            grilsList.add(gril4);
            Grils gril5=new Grils(getRandomLengthName(data[4]),R.drawable.orange_pic);
            grilsList.add(gril3);
            Grils gril6=new Grils(getRandomLengthName(data[5]),R.drawable.watermelon_pic);
            grilsList.add(gril6);
            Grils gril7=new Grils(getRandomLengthName(data[6]),R.drawable.pear_pic);
            grilsList.add(gril7);
            Grils gril8=new Grils(getRandomLengthName(data[7]),R.drawable.grape_pic);
            grilsList.add(gril8);
            Grils gril9=new Grils(getRandomLengthName(data[8]),R.drawable.mango_pic);
            grilsList.add(gril9);


        }
}
private String getRandomLengthName(String name){
    Random random=new Random();
    int length=random.nextInt(20)+1;
    StringBuilder builder=new StringBuilder();
    for(int i=0;i<length;i++){
        builder.append(name);
    }

    return  builder.toString();







}



}