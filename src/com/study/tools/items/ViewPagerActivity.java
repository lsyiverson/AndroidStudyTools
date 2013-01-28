
package com.study.tools.items;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;

import com.study.tools.R;

public class ViewPagerActivity extends Activity {

    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private Gallery mGallery;
    private GalleryAdapter mGalleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        initView();
    }

    private void initView(){
        mViewPagerAdapter = new ViewPagerAdapter();
        mGalleryAdapter = new GalleryAdapter();
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mGallery = (Gallery)findViewById(R.id.viewpager_tab);

        LayoutInflater inflater = getLayoutInflater();
        addItem(inflater,R.layout.set_error);
        addItem(inflater,R.layout.speech_recognize);
        addItem(inflater,R.layout.set_error);

        mGallery.setAdapter(mGalleryAdapter);
        mViewPager.setAdapter(mViewPagerAdapter);

        mGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewPager.setCurrentItem(position, true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            /* (non-Javadoc)
             * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrollStateChanged(int)
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO Auto-generated method stub

            }

            /* (non-Javadoc)
             * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled(int, float, int)
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // TODO Auto-generated method stub

            }

            /* (non-Javadoc)
             * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected(int)
             */
            @Override
            public void onPageSelected(int position) {
                mGallery.setSelection(position);
            }

        });
    }

    private void addItem(LayoutInflater inflater,int resId){
        mGalleryAdapter.addItem(resId);
        mViewPagerAdapter.addItem(inflater.inflate(resId, null));
    }

    private class ViewPagerAdapter extends PagerAdapter {

        private ArrayList<View> mViewList;

        public ViewPagerAdapter() {
            mViewList = new ArrayList<View>();
        }

        public ViewPagerAdapter(ArrayList<View> viewList) {
            mViewList = viewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(mViewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(mViewList.get(position));
            return mViewList.get(position);
        }

        public void addItem(View view){
            mViewList.add(view);
        }

        public View getItemByPosition(int position){
            return mViewList.get(position);
        }

    }

    private class GalleryAdapter extends BaseAdapter {

        private ArrayList<Integer> mTabList;

        public GalleryAdapter(){
            mTabList = new ArrayList<Integer>();
        }

        @Override
        public int getCount() {
            return mTabList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return mTabList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.gallery_tab_item, null);
                TextView tvTabName = (TextView)convertView.findViewById(R.id.gallery_tabname);
                tvTabName.setText(mTabList.get(position));
            }
            return convertView;
        }

        public void addItem(Integer name){
            mTabList.add(name);
        }

    }

}
