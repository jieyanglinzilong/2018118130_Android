package com.example.myapplication.dbsupport;

import android.content.Intent;
import android.util.Log;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.Register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBsupport  {
    private static final String TAG = "mysql";

    public static Connection mymysql(){
        Connection conn=null;
        final Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {

                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(100);  // 每隔0.1秒尝试连接
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.toString());
                    }

// 1.加载JDBC驱动
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Log.v(TAG, "加载JDBC驱动成功");
                    } catch (ClassNotFoundException e) {
                        Log.e(TAG, "加载JDBC驱动失败");
                        return;
                    }

                    // 2.设置好IP/端口/数据库名/用户名/密码等必要的连接信息
                    String ip = "121.37.212.47";
                    int port = 3306;
                    String dbName = "user";
                    String url = "jdbc:mysql://" + ip + ":" + port
                            + "/" + dbName;
                    String url1 = "jdbc:mysql://"+ip+":3306?zeroDateTimeBehavior=convertToNull&amp;"
                            + "user=root&amp;password=root&amp;useUnicode=true&amp;characterEncoding=UTF8";
                    try {
                    // 构建连接mysql的字符串
                    String user = "root";
                    String password = "root";

                    // 3.连接JDBC
                    try {
                        Connection conn = DriverManager.getConnection(url,user,password);
                        //Statement stmt = conn.createStatement();

                        //切换库。
                        //String sqlusedb="use user";
                       // int result = stmt.executeUpdate(sqlusedb);
                        //Log.d(TAG, "数据库连接成功"+result);

                    }
                    catch (SQLException e) {
                        Log.e(TAG, e.getMessage());
                    }

                }catch (Exception e){
                    e.printStackTrace();}
                }
            }
        });



        return conn;
    }


}
