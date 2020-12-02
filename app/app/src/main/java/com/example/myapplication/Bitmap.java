package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Bitmap extends AppCompatActivity {
    Button button;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        String url="http://121.37.212.47:8080/20200509/37.jpg";
        button=findViewById(R.id.button_first);
        imageView=findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                new Thread(new Runnable() {

                    @Override

                    public void run() {

                        android.graphics.Bitmap bmp = getURLimage(url);

                        Message msg = new Message();

                        msg.what = 0;

                        msg.obj = bmp;

                        System.out.println("000");

                        handle.sendMessage(msg);

                    }

                }).start();

            }

        });
    }
    //在消息队列中实现对控件的更改

    private Handler handle = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {

                case 0:

                    System.out.println("111");

                    android.graphics.Bitmap  bmp=(android.graphics.Bitmap)msg.obj;

                    imageView.setImageBitmap(bmp);

                    break;

            }

        };

    };



    //加载图片

    public android.graphics.Bitmap getURLimage(String url) {

        android.graphics.Bitmap bmp = null;

        try {

            URL myurl = new URL(url);

            // 获得连接

            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();

            conn.setConnectTimeout(6000);//设置超时

            conn.setDoInput(true);

            conn.setUseCaches(false);//不缓存

            conn.connect();

            InputStream is = conn.getInputStream();//获得图片的数据流

            bmp =BitmapFactory.decodeStream(is);//读取图像数据

            //读取文本数据

            //byte[] buffer = new byte[100];

            //inputStream.read(buffer);

            //text = new String(buffer);

            is.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return bmp;

    }
}