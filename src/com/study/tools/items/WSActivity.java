
package com.study.tools.items;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.study.tools.R;
import com.study.tools.Workspace;
import com.study.tools.Workspace.OnScrollListener;

public class WSActivity extends Activity {

    private static final int MODE_SCROLL = 0x01;

    private Workspace mWorkspace;

    private int mScreenWidth;

    private HorizontalScrollView mScrollView;

    private LinearLayout[] mPage = new LinearLayout[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws);

        mWorkspace = (Workspace)findViewById(R.id.workspace);
        mScrollView = (HorizontalScrollView)findViewById(R.id.scroll_view);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view1 = inflater.inflate(R.layout.set_error, null);
        mWorkspace.addView(view1);
        final View view2 = inflater.inflate(R.layout.speech_recognize, null);
        mWorkspace.addView(view2);
        final View view3 = inflater.inflate(R.layout.set_error, null);
        mWorkspace.addView(view3);
        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        initTab();

        mWorkspace.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScroll(float screenFraction, int mode) {
                final int index = Math.round(screenFraction);
                if (mode != MODE_SCROLL) {
                    final float curLeftDistance = mScreenWidth * index / 3;
                    mScrollView.smoothScrollTo((int)curLeftDistance, 0);
                    for (int i = 0; i < mPage.length; i++) {
                        if (i != index){
                            mPage[i].setBackgroundColor(Color.TRANSPARENT);
                        } else {
                            mPage[index].setBackgroundColor(Color.WHITE);
                        }
                    }
                }
            }
        }, true);
    }

    private void initTab(){
        mPage[0] = (LinearLayout)findViewById(R.id.page1);
        mPage[1] = (LinearLayout)findViewById(R.id.page2);
        mPage[2] = (LinearLayout)findViewById(R.id.page3);

        for(int i=0;i<mPage.length;i++){
            mPage[i].setId(i);
            mPage[i].setLayoutParams(new LinearLayout.LayoutParams(mScreenWidth/3, LayoutParams.FILL_PARENT));
            mPage[i].setOnClickListener(mTabListener);
        }
    }

    private View.OnClickListener mTabListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            for (int i = 0; i < mPage.length; i++) {
                if (id != i) {
                    mPage[i].setBackgroundColor(Color.TRANSPARENT);
                } else {
                    v.setBackgroundColor(Color.WHITE);
                    mWorkspace.setCurrentScreen(id, MODE_SCROLL);
                }
            }
        }

    };

}
