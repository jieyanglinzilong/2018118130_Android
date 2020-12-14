package com.coolweather.android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.coolweather.android.db.City;
import com.coolweather.android.util.HttpUtil;
import com.coolweather.android.util.Utility;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Location extends AppCompatActivity implements TencentLocationListener {
    private TextView mStatus;
    private TencentLocation mLocation;
    private TencentLocationManager mLocationManager;
    private static String city;
    private static String provinces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mStatus = (TextView) findViewById(R.id.Textview);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            if (checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, 0);
            }
            initTencentLocationRequest();

            //if(city!=null)
          }

            /*Intent intent=new Intent(this,WeatherActivity.class);
            intent.putExtra("city",city);
            startActivity(intent);*/

        mLocationManager = TencentLocationManager.getInstance(this);
        mLocationManager.requestLocationUpdates(TencentLocationRequest.create().setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_POI).setInterval(500).setAllowDirection(true), this);

//TencentLocationRequest.REQUEST_LEVEL_POI这是请求级别，最高的，不懂的可以看官方文档很详细哦
    }
    public void find(String province,String city){
        if(city!=null){
        city=city.replaceAll("市","");
        province=province.replaceAll("省","");


        int id=0;

        //System.out.println(province+"++++haha+++"+city);
        List<City> cityList= DataSupport.select("cityCode","cityName").where("cityname=?",city).find(City.class);

        if(cityList!=null){
            System.out.println(province+"+++++++"+city);

            for(City city1:cityList){
                id = city1.getCityCode();

                System.out.println("id"+id);
            }}
         else{
            System.out.println(province+"++++haha+++"+city);
            String address = "http://guolin.tech/api/china"+21;
            query(address,21);





            }}
        }
     private void query(String address, final int province){
         HttpUtil.sendOkHttpRequest(address, new Callback() {

             @Override
             public void onFailure(Call call, IOException e) {

             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 String responsebody= response.body().string();

                 boolean flag=Utility.handleCityResponse(responsebody,province);


                     System.out.println(flag);


                     find(provinces,city);}



         });

     }


    /**
     * 开启定位监听器
     */
    private void initTencentLocationRequest() {
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setInterval(30000).setRequestLevel(4).setAllowGPS(true);
        TencentLocationManager locationManager = TencentLocationManager.getInstance(this);
        int error = locationManager.requestLocationUpdates(request, this);
        System.out.println("" + error);
        if (error == 0)
            System.out.println("注册位置监听器成功！");

        else
            System.out.println("注册位置监听器失败！");
    }



    @Override
    public void onLocationChanged(TencentLocation location, int error, String reason) {

        String msg = null;
        if (TencentLocation.ERROR_OK == 0) {
            System.out.println("定位成功");
            city = location.getCity();
            provinces = location.getProvince();

            find(provinces, city);
            /* 定位成功
            if (location != null) {
                StringBuilder sb = new StringBuilder();
                System.out.println(location.getProvince());
                sb.append("来源=").append(location.getProvider())
                        .append("，纬度=").append(location.getLatitude())
                        .append(",经度=").append(location.getLongitude())
                        .append(",海拔=").append(location.getAltitude())
                        .append(",精度=").append(location.getAccuracy())
                        .append(",国家=").append(location.getNation())
                        .append(",省=").append(location.getProvince())
                        .append(",市=").append(location.getCity())
                        .append(",区=").append(location.getDistrict())
                        .append(",镇=").append(location.getTown())
                        .append(",村=").append(location.getVillage())
                        .append(", 街道").append(location.getStreet())
                        .append(", 门号").append(location.getStreetNo())
                        .append(", POI列表").append(location.getPoiList())
                        // 注意, 根据国家相关法规, wgs84坐标下无法提供地址信息
                        .append("{84坐标下不提供地址!}");
                msg = sb.toString();
                System.out.println(msg + "---" );
                mStatus.setText(msg);
                System.out.println(msg + "---***********" );

            }
        } else{
            // 定位失败
        }*/
        }
    }



    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationManager.removeUpdates(this);
    }
}