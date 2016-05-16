package com.example.anhlee.myapplication.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.anhlee.myapplication.R;
import com.example.anhlee.myapplication.constant.Constant;
import com.example.anhlee.myapplication.model.Video;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by anhlee on 5/6/16.
 */
public class ConnectTVActivity extends Activity implements View.OnClickListener{
    WebView webView;
    String url1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connecttv);
        webView = (WebView) findViewById(R.id.webConnect);
        Button btnAddVideo = (Button) findViewById(R.id.btnAddVideo);
        btnAddVideo.setOnClickListener(this);

        String pairingCode = getIntent().getExtras().getString(Constant.PAIRING_CODE);
        UUID uuid = UUID.randomUUID();

          url1 = "http://www.yokara.com/tv/candy2/mobile/controller.jsp?nickname=controller-" + uuid + "&roomName=" + pairingCode + "@yokara.data3.ikara.co&showInputBox=false";

        WebSettings settings = webView.getSettings();
        // Enable Javascript
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
        }

        // Enable remote debugging via chrome://inspect
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(url1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddVideo:
                Video video = new Video("icmPALjUpqI", "[Karaoke HD] Mien cat trang - Quang Vinh (Beat goc)", 0, 0);
                String videoJson = video.toJSON();
                byte[] data = null;
                try {
                    data = videoJson.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                String base64 = Base64.encodeToString(data, Base64.NO_WRAP);
                String script = "javascript:sendBase64Message(\"" + base64 + "\")";
                if (android.os.Build.VERSION.SDK_INT >= 19) {
                    webView.evaluateJavascript(script, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            Log.d("LogName", s); // Log is written, but s is always null
                        }
                    });
                } else {
                  webView.loadUrl(script);
                }
                break;
        }
    }
}
