package com.study.tools.items;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import com.study.tools.R;

public class GetDeviceInfoActivity extends Activity {
    private TextView mModel;
    private TextView mBrand;
    private TextView mName;
    private TextView mDevice;
    private TextView mManufacturer;
    private TextView mResolution;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_device_info);
        setupView();
    }

    private void setupView(){
        mModel = (TextView)findViewById(R.id.model);
        mBrand = (TextView)findViewById(R.id.brand);
        mName = (TextView)findViewById(R.id.name);
        mDevice = (TextView)findViewById(R.id.device);
        mManufacturer = (TextView)findViewById(R.id.manufacturer);
        mResolution = (TextView)findViewById(R.id.resolution);
        getDeviceInfo();
        getScreenResolution();
    }

    private void getDeviceInfo(){
        Class<?> SystemPropertiesCls;
        Method methodGet;
        String temp;
        String model = "";
        String brand = "";
        String name = "";
        String device = "";
        String manufacturer = "";
        try {
            SystemPropertiesCls = Class.forName("android.os.SystemProperties");
            methodGet = SystemPropertiesCls.getMethod("get", String.class);
            model = (String)methodGet.invoke(SystemPropertiesCls, "ro.product.model");
            brand = (String)methodGet.invoke(SystemPropertiesCls, "ro.product.brand");
            name = (String)methodGet.invoke(SystemPropertiesCls, "ro.product.name");
            device = (String)methodGet.invoke(SystemPropertiesCls, "ro.product.device");
            manufacturer = (String)methodGet.invoke(SystemPropertiesCls, "ro.product.manufacturer");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        temp = getResources().getString(R.string.model);
        model = String.format(temp, model);
        mModel.setText(model);

        temp = getResources().getString(R.string.brand);
        brand = String.format(temp, brand);
        mBrand.setText(brand);

        temp = getResources().getString(R.string.name);
        name = String.format(temp, name);
        mName.setText(name);

        temp = getResources().getString(R.string.device);
        device = String.format(temp, device);
        mDevice.setText(device);

        temp = getResources().getString(R.string.manufacturer);
        manufacturer = String.format(temp, manufacturer);
        mManufacturer.setText(manufacturer);
    }

    private void getScreenResolution(){
        int width, height;
        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();
        String resolution = getResources().getString(R.string.resolution);
        resolution = String.format(resolution, width, height);
        mResolution.setText(resolution);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        getScreenResolution();
        super.onConfigurationChanged(newConfig);
    }
}
