package com.example.webhttp;


import android.os.Bundle;
import android.webkit.WebViewClient;


import androidx.appcompat.app.AppCompatActivity;

public class WebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        android.webkit.WebView webView=findViewById(R.id.WebView);
        setContentView(webView);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.example.com");

    }
}