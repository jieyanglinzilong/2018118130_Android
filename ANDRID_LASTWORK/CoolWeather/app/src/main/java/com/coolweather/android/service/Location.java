package com.coolweather.android.service;

import android.app.Activity;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

public class Location extends Activity implements TencentLocationListener {
     TencentLocationRequest request;
     TencentLocationManager locationManager;



    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        request= TencentLocationRequest.create();
        request. setRequestLevel(TencentLocationRequest. REQUEST_LEVEL_ADMIN_AREA);
        //是否允许使用GPS
        request.setAllowGPS(true);
        //是否需要获取传感器方向
        request. setAllowDirection(true);
        //是否需要开启室内定位
        request.setIndoorLocationMode(true);
        //mLocationManager.removeUpdates(this);
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }
}
