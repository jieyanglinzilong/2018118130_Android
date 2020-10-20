package com.example.a05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseAcitity{
    private EditText account_editText;
    private EditText password_editText;
    private Button login;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        account_editText=(EditText)findViewById(R.id.account);
        password_editText=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        checkBox=(CheckBox)findViewById(R.id.checkbox);
        boolean isRemeber= sharedPreferences.getBoolean("checkbox",false);
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
                if(account.equals("lin")&&password.equals("123456")){
                    editor =sharedPreferences.edit();
                    if(checkBox.isChecked()){
                    editor.putBoolean("checkbox",true);
                    editor.putString("account",account);
                    editor.putString("password",password);}
                    else{
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(LoginActivity.this,Aftetlogin.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this,"密码或用户名错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}