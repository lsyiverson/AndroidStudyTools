
package com.study.tools.items;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.study.tools.R;
import com.study.tools.R.layout;

public class TabActivityTest extends TabActivity {

    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity_test);
        mTabHost = getTabHost();

        addTab("One", SetErrorActivity.class);
        addTab("Two", GetDeviceInfoActivity.class);
        addTab("Three", ListProgressActivity.class);
        addTab("Four", SpeechRecognizeActivity.class);
    }

    private void addTab(String tag, Class clazz){
        Intent intent = new Intent();
        intent.setClass(TabActivityTest.this, clazz);

        TabSpec spec = mTabHost.newTabSpec(tag);
        spec.setIndicator(tag, null);
        spec.setContent(intent);
        mTabHost.addTab(spec);
    }
}
