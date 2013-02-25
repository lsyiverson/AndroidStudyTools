
package com.study.tools.items;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.study.tools.R;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
    }

    public void onButtonClicked(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://3g.189store.com/appinfo/474355"));
        //        Intent i = new Intent();
        //        i.setAction(Intent.ACTION_VIEW);
        //        i.setClassName("com.eshore.ezone", "com.eshore.ezone.ui.EstoreMain");
        //        i.setData(Uri.parse("http://3g.189store.com/appinfo/318014"));
        startActivity(i);
    }

}
