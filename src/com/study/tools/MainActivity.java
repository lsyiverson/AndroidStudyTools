
package com.study.tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.study.tools.items.GetDeviceInfoActivity;
import com.study.tools.items.ListProgressActivity;
import com.study.tools.items.SetErrorActivity;

public class MainActivity extends Activity {
    private Button mBtnSetErrorTool;

    private Button mBtnGetDeviceInfo;

    private Button mBtnListProgressItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupView();
    }

    private void setupView() {
        mBtnSetErrorTool = (Button)findViewById(R.id.set_error_item);
        mBtnSetErrorTool.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SetErrorActivity.class));
            }
        });
        mBtnGetDeviceInfo = (Button)findViewById(R.id.get_device_info_item);
        mBtnGetDeviceInfo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GetDeviceInfoActivity.class));
            }
        });
        mBtnListProgressItem = (Button)findViewById(R.id.list_progress_item);
        mBtnListProgressItem.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                startActivity(new Intent(MainActivity.this, ListProgressActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}