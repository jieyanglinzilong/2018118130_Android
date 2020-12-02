package com.example.myapplication;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RecyleTask extends AsyncTask<Void, Void, String > {
    String api="http://hadoop103:8080/20200509/";
    private String mEnd;
    private List<Fruit> fruitList;
    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url=new URL(api);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            //请求方法
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Accept-Language", "zh-CN");
            //conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            //支持火狐浏览器
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                //得到原始的输入流
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes) ) != -1) {
                    os.write(bytes, 0, len);
                }
                byte[] array = os.toByteArray();
                mEnd = new String(array, StandardCharsets.UTF_8);
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mEnd;
        }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
}

