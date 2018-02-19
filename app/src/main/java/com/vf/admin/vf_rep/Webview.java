package com.vf.admin.vf_rep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mWebView = (WebView) findViewById(R.id.webview1);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView.setWebViewClient(new MyWebViewClient());

        Intent me = getIntent();
        String urlstring = me.getStringExtra("StoreInfoURL");
        mWebView.loadUrl(urlstring);

    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedHttpAuthRequest(WebView view,
                                              HttpAuthHandler handler, String host, String realm) {

            handler.proceed("me@test.com", "mypassword");

        }
    }

}
