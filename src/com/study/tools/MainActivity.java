
package com.study.tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.study.tools.items.ActionBarActivity;
import com.study.tools.items.GetDeviceInfoActivity;
import com.study.tools.items.ListInsertActivity;
import com.study.tools.items.ListProgressActivity;
import com.study.tools.items.NfcReaderActivity;
import com.study.tools.items.SetErrorActivity;
import com.study.tools.items.SpeechRecognizeActivity;
import com.study.tools.items.TabActivityTest;
import com.study.tools.items.TabBarActivity;
import com.study.tools.items.ViewPagerActivity;
import com.study.tools.items.WSActivity;
import com.study.tools.items.WebViewActivity;

public class MainActivity extends Activity {
    private Button mBtnSetErrorTool;

    private Button mBtnGetDeviceInfo;

    private Button mBtnListProgressItem;

    private Button mBtnSpeechRecognize;

    private Button mBtnNfcReader;

    private Button mBtnTabBar;

    private Button mBtnTabActivityTest;

    private Button mBtnWorkspace;

    private Button mBtnListInsert;

    private Button mBtnViewPager;

    private Button mBtnActionBar;

    private Button mBtnWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupView();
    }

    private void setupView() {
        mBtnSetErrorTool = (Button)findViewById(R.id.set_error_item);
        mBtnSetErrorTool.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SetErrorActivity.class));
            }
        });
        mBtnGetDeviceInfo = (Button)findViewById(R.id.get_device_info_item);
        mBtnGetDeviceInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GetDeviceInfoActivity.class));
            }
        });
        mBtnListProgressItem = (Button)findViewById(R.id.list_progress_item);
        mBtnListProgressItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(MainActivity.this, ListProgressActivity.class));
            }
        });
        mBtnSpeechRecognize = (Button)findViewById(R.id.speech_recognize_item);
        mBtnSpeechRecognize.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(MainActivity.this, SpeechRecognizeActivity.class));
            }
        });
        mBtnNfcReader = (Button)findViewById(R.id.nfc_reader_item);
        mBtnNfcReader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(MainActivity.this, NfcReaderActivity.class));
            }
        });
        mBtnTabBar = (Button)findViewById(R.id.tab_item);
        mBtnTabBar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabBarActivity.class));
            }
        });
        mBtnTabActivityTest = (Button)findViewById(R.id.tab_activity_item);
        mBtnTabActivityTest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabActivityTest.class));
            }
        });
        mBtnWorkspace = (Button)findViewById(R.id.workspace_item);
        mBtnWorkspace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WSActivity.class));
            }
        });
        mBtnListInsert = (Button)findViewById(R.id.list_item);
        mBtnListInsert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListInsertActivity.class));
            }
        });
        mBtnViewPager = (Button)findViewById(R.id.viewpager_item);
        mBtnViewPager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
            }
        });
        mBtnActionBar = (Button)findViewById(R.id.actionbar_item);
        mBtnActionBar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActionBarActivity.class));
            }
        });
        mBtnWebView = (Button)findViewById(R.id.webview_item);
        mBtnWebView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
