package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dbsupport.DBsupport;
import com.example.myapplication.dbsupport.SqliteSupport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {
    private EditText account_editText;
    private EditText password_editText;
    private Button login;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CheckBox checkBox;
    private SqliteSupport sqliteSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        account_editText=(EditText)findViewById(R.id.account);
        password_editText=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        checkBox=(CheckBox)findViewById(R.id.checkbox);
        sqliteSupport=new SqliteSupport(this,"user.db",null ,3);
        boolean isRemeber= sharedPreferences.getBoolean("checkbox",false);
        if(!isRemeber){
            AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
            dialog.setTitle("登陆提示");
            dialog.setMessage("检查到您当前未登录是否前往登陆");
            dialog.setPositiveButton("前往登陆", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.setNegativeButton("前往注册", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                 Intent intent=new Intent(LoginActivity.this,Register.class);
                 startActivity(intent);
                }
            });


         dialog.show();

        }
        System.out.println(isRemeber);
        if(isRemeber){
            String account = sharedPreferences.getString("account","");
            String password= sharedPreferences.getString("password","");
            password_editText.setText(password);
            account_editText.setText(account);
            checkBox.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = account_editText.getText().toString();
                String password = password_editText.getText().toString();
                SQLiteDatabase sqLiteDatabase=sqliteSupport.getWritableDatabase();
                Cursor cursor = sqLiteDatabase.rawQuery("select * from user where name=? and password=?", new String[]{account,password});
                System.out.println(account+"haha"+ password);
                if(cursor.getCount()>0){
                    Intent intent=new Intent(LoginActivity.this,Register.class);
                    startActivity(intent);
                }
                else{

                  int n =addMysql(account,password);
                  if(n!=1){
                      Intent intent=new Intent(LoginActivity.this,LoginActivity.class);
                      startActivity(intent);
                  }
                  if(n==1){
                      find();
                  }
                }








            }
        });


    }
    public int  addMysql( String name, String password){
        final int[] n = {0};
        new Thread(new Runnable() {

            @Override
            public void run() {
                DBsupport dBsupport=new DBsupport();




                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Connection connection;

                        connection= dBsupport.mymysql();
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

                        PreparedStatement preparedStatement;
                        try {
                            Connection conn =  DriverManager.getConnection(url,user,password);

                            String sql = "select * from user where name =? and password = ?";
                            preparedStatement = conn.prepareStatement(sql);
                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, password);
                            System.out.println(name+""+ password);

                            ResultSet resultSet= preparedStatement.executeQuery();
                            if(resultSet.next()){
                                System.out.println("查询有数据");
                                n[0] =3;
                                return  ;
                            }
                            else{
                                System.out.println("无数据");
                            }


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

    return n[0];
    }
     public void find(){
        Toast.makeText(this,"此用户尚未注册请前往注册",Toast.LENGTH_SHORT).show();
         Intent intent = new Intent(LoginActivity.this, Register.class);
         startActivity(intent);

     }

    }






