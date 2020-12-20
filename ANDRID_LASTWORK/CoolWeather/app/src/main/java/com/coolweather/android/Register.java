package com.coolweather.android;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.coolweather.android.dbsupport.SnowflakeIdWorkerV1;
import com.coolweather.android.dbsupport.SqliteSupport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends AppCompatActivity {
    SqliteSupport sqliteSupport;
    private EditText account_editText;
    private EditText password_editText;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SnowflakeIdWorkerV1 snowflakeIdWorkerV1=new SnowflakeIdWorkerV1(2,4);
        final long id = snowflakeIdWorkerV1.nextId();
        sqliteSupport=new SqliteSupport(this,"user.db",null ,3);
        account_editText= (EditText) findViewById(R.id.account);
        password_editText= (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = account_editText.getText().toString();
                String password= password_editText.getText().toString();
                addMysql(id,name,password);
                addSqlite(id,name,password);


            }
        });


    }
    public void addSqlite(long id, String name, String password){
        SQLiteDatabase sqLiteDatabase=sqliteSupport.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("password",password);

        sqLiteDatabase.insert("user",null, contentValues);
        Toast.makeText(this,"注册成功", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(Register.this,LoginActivity.class);
        startActivity(intent);
    }
    public void addMysql(final long id, final String name, String password){
        new Thread(new Runnable() {
            @Override
            public void run() {





                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Connection connection;
                        System.out.println("插入mysql数据库成功");

                        System.out.println("连接成功");
                        String ip = "121.37.212.47";
                        int port = 3306;
                        String dbName = "user";
                        String url = "jdbc:mysql://" + ip + ":" + port
                                + "/" + dbName+"?useSSL=false";
                        // 构建连接mysql的字符串
                        String user = "root";
                        String password = "root";


                        //
                        String sql = "insert into  user(id,name,password) values(?,?,?)";
                        PreparedStatement preparedStatement;
                        try {
                            Connection conn =  DriverManager.getConnection(url,user,password);
                            if(conn!=null){
                                System.out.println("连接不为空");
                            }
                            preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setLong(1,id);
                            preparedStatement.setString(2,name);
                            preparedStatement.setString(3,password);
                            preparedStatement.executeUpdate();
                            if(conn!=null){

                                conn.close();
                            }
                            //
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }


                    }
                }).start();


            }
        }).start();


    }
}