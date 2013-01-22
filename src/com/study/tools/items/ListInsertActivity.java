
package com.study.tools.items;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.study.tools.R;

public class ListInsertActivity extends Activity {

    private ArrayList<String> mList1 = new ArrayList<String>();

    private ArrayList<String> mList2 = new ArrayList<String>();

    private ArrayList<String> mList3 = new ArrayList<String>();

    private ListView mLvInsertList;

    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_insert);
        init();
        View headerView = View.inflate(this, R.layout.list_insert_header, null);
        View footerView = View.inflate(this, R.layout.list_insert_footer, null);
        headerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mList2.addAll(0, mList1);
                mAdapter.notifyDataSetChanged();
            }
        });
        footerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mList2.addAll(mList3);
                mAdapter.notifyDataSetChanged();
            }
        });
        mLvInsertList = (ListView)findViewById(R.id.list_insert);
        mLvInsertList.addHeaderView(headerView);
        mLvInsertList.addFooterView(footerView);
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, mList2);
        mLvInsertList.setAdapter(mAdapter);
    }

    private void init() {
        String[] list1 = {
                "1", "2", "3", "4"
        };
        for (String str1 : list1) {
            mList1.add(str1);
        }
        String[] list2 = {
                "5", "6", "7", "8"
        };
        for (String str2 : list2) {
            mList2.add(str2);
        }
        String[] list3 = {
                "9", "10", "11", "12"
        };
        for (String str3 : list3) {
            mList3.add(str3);
        }

    }

}
