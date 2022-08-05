package com.example.putrabuwana.diagnosawal.User;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.putrabuwana.diagnosawal.R;

public class webvcari extends AppCompatActivity {
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewcari);
        webview = findViewById(R.id.webview_test);
        webview.loadUrl("https://www.google.com/search?source=hp&ei=pcIXX9-yMvC-3LUP45GM8A0&q=psikolog+psikater+sekitar&btnK=Penelusuran+Google");
    }
}
