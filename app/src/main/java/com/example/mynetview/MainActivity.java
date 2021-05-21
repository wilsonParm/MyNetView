package com.example.mynetview;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText urlBox;
    WebView webView;
    Button refresh,cancel;
    ImageView webBack, webForward;
    ProgressBar progress;

    private final int webView_zoomIn = 0;
    private final int webView_zoomOut = 1;

    private int isZoom = 1;  //是否缩放

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlBox = (EditText) findViewById(R.id.et_urlBox);
        webView = (WebView) findViewById(R.id.webView);
        webBack = (ImageView) findViewById(R.id.web_back);
        webForward = (ImageView) findViewById(R.id.web_forward);
//        progress = (ProgressBar)findViewById(R.id.progress);
        setListeners();

        webView.setWebViewClient(new CustomWebViewClient());
        webView.setWebChromeClient(new CustomWebChromeClient());

        //在文本框输入网址，回车访问
        urlBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //when enter is pressed in editText, start loading the page
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                    String url = urlBox.getText().toString();
                    String url = "http://www.baidu.com";
                    webView.loadUrl( url);

//                    webView.loadUrl( urlBox.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    private void setListeners() {
        onClick on_click = new onClick();
        webBack.setOnClickListener(on_click);
        webForward.setOnClickListener(on_click);
    }

    private class onClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.web_back:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    }
                    break;
                case R.id.web_forward:
                    if (webView.canGoForward()) {
                        webView.goForward();
                    }
                    break;
                case R.id.bar_btn_allWebView:

                    break;
            }
        }
    }

    public class CustomWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    public class CustomWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            progress.setProgress(newProgress);
            urlBox.setText(view.getUrl());

//
//            if (newProgress == 100) {
//                cancel.setVisibility(View.GONE);
//                progress.setVisibility(View.GONE);
//            } else {
//                cancel.setVisibility(View.VISIBLE);
//                progress.setVisibility(View.VISIBLE);
//            }
        }
    }
}

