package com.example.a03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
        Button button1=(Button)findViewById(R.id.button2);
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
                System.out.println("hello");
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Frist.this);
                alertDialog.setTitle("对话框弹出");
                alertDialog.setMessage("是否前往百度");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                                 
                    }
                });
                alertDialog.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();


                break;
            case R.id.button3:
                /**
                ProgressDialog progressDialog=new ProgressDialog(Frist.this);
                progressDialog.setTitle("This is dialog");
                progressDialog.setMessage("loading......");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;**/
                if(progressBar.getVisibility()== View.GONE){
                    progressBar.setVisibility(View.VISIBLE);

                }else{
                    progressBar.setVisibility(View.GONE);
                }
                break;
            default:
                break;


        }
    }
}