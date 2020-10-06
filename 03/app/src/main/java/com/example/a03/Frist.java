package com.example.a03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Frist extends AppCompatActivity  implements View.OnClickListener{
    private ProgressBar progressBar;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        Button button=(Button)findViewById(R.id.button1);
        //progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        imageView=(ImageView)findViewById(R.id.image2);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                imageView.setImageResource(R.drawable.ab);
                break;
            case R.id.button2:

                break;
            default:
                break;


        }
    }
}