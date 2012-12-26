
package com.study.tools.items;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.study.tools.R;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
    }

}
