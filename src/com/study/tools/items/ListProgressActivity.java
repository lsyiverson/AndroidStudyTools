
package com.study.tools.items;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.study.tools.R;

public class ListProgressActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_progress);
        ListView list = (ListView)findViewById(R.id.loading_list);
        View loadingView = View.inflate(this, R.layout.loading_header, null);
        loadingView.setFocusable(false);
        list.addHeaderView(loadingView);
        list.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, getData()));
    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("test1");
        data.add("test2");
        data.add("test3");
        data.add("test4");
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_progress, menu);
        return true;
    }

}
