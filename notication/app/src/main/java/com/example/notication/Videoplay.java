package com.example.notication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class Videoplay extends AppCompatActivity implements  View.OnClickListener{
    private VideoView videoplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);
        videoplay=findViewById(R.id.video);
        Button play=findViewById(R.id.play);
        Button replay=findViewById(R.id.Replay);
        Button pause=findViewById(R.id.pause);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);
        if(ContextCompat.checkSelfPermission(Videoplay.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Videoplay.this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);

        }else{
            initVideopath();
        }
       // Toast.makeText(this,"加载完毕",Toast.LENGTH_SHORT).show();

    }

    private void initVideopath() {
        File file=new File(Environment.getExternalStorageDirectory(),"lf.mp4");
        if(!file.exists()){
            Toast.makeText(this,"不存在此文件",Toast.LENGTH_SHORT).show();
        }
        else{
        videoplay.setVideoPath(file.getPath());
            Toast.makeText(this,"正在加载视频",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                   initVideopath();
                }else{
                    Toast.makeText(this,"No right to open the video",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.play:
                if(!videoplay.isPlaying()){
                    videoplay.start();
                }
                break;
            case R.id.Replay:
                if(videoplay.isPlaying()){
                    videoplay.resume();
                }
                break;
            case R.id.pause:
                if(videoplay.isPlaying()){
                    videoplay.pause();
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoplay!=null){
            videoplay.suspend();
        }
    }
}