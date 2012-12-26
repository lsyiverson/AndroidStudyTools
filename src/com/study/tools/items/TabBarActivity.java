
package com.study.tools.items;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;

import com.study.tools.R;

public class TabBarActivity extends ActivityGroup {
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tab_bar);

        mContainer = (LinearLayout)findViewById(R.id.container);
    }

    public void onFirstClick(View v){
        mContainer.removeAllViews();
        mContainer.addView(getLocalActivityManager().startActivity(
                "Tab1",
                new Intent(TabBarActivity.this, FirstActivity.class))
                .getDecorView(),new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
    }

    public void onSecondClick(View v){
        mContainer.removeAllViews();
        mContainer.addView(getLocalActivityManager().startActivity(
                "Tab2",
                new Intent(TabBarActivity.this, SecondActivity.class))
                .getDecorView(),new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
    }
}
