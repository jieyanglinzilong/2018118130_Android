package com.coolweather.android.dbsupport;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;



public class SqliteSupport  extends SQLiteOpenHelper {
    public static final String crete="create table user("+"id integer primary key autoincrement,"+"name text,"
            +"password text)";

    private Context mcontext;
    public SqliteSupport(@org.jetbrains.annotations.Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crete);
        Toast.makeText(mcontext,"创建成功",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists Category");
        onCreate(db);
        System.out.println("删除数据库user");

    }
    public void add(String name,String password){




    }
}
