
package com.study.tools.items;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.study.tools.R;

public class WebViewActivity extends Activity {

    private EditText mEtUrl;

    private Button mBtnEnter;

    private WebView mWvWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Log.d("WebViewActivity", "lisy===========onCreate");
        mEtUrl = (EditText)findViewById(R.id.web_url);
        mBtnEnter = (Button)findViewById(R.id.submit);
        mWvWeb = (WebView)findViewById(R.id.webpage);

        mBtnEnter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mEtUrl.getText().toString().trim();
                if (!TextUtils.isEmpty(url)) {
                    mWvWeb.loadUrl(url);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d("WebViewActivity", "lisy===========onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("WebViewActivity", "lisy===========onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("WebViewActivity", "lisy===========onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d("WebViewActivity", "lisy===========onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("WebViewActivity", "lisy===========onStop");
        super.onStop();
    }

}
