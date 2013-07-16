
package com.study.tools.items;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
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

}
