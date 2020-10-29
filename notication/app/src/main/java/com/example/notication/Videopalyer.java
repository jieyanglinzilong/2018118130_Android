package com.example.notication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Videopalyer extends AppCompatActivity implements View.OnClickListener {
    private VideoView videoView;

    private Button start;
    private Button pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initVideoView();
    }

    private void initVideoView() {
        videoView.setVideoPath("http://ivi.bupt.edu.cn/player.html?channel=hebhd");
    }

    private void init() {
        videoView = findViewById(R.id.videoView);
        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }
}

/**
 * 亲测以下直播均可用
 * CCTV1高清：http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8
 * CCTV3高清：http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8
 * CCTV5+高清：http://ivi.bupt.edu.cn/hls/cctv5phd.m3u8
 * CCTV6高清：http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8
 */
