package com.play.zv.seamountain.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hspc on 2017/5/24.
 */

public class JavbusDBOpenHelper extends SQLiteOpenHelper {
    public JavbusDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"javbus.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table movieinfo(" +
                "num varchar(20) PRIMARY KEY," +
                "censored varchar(10)," +
                "cover varchar(1000)," +
                "director varchar(100)," +
                "genres varchar(1000)," +
                "lable varchar(500)," +
                "release varchar(20)," +
                "runtime varchar(100)," +
                "series varchar(1000)," +
                "studio varchar(500)," +
                "title varchar(1000)," +
                "stars varchar(500),"+
                "previews varchar(2000))"

        );
        db.execSQL("create table avstars(" +
                   "avstarname varchar(100) PRIMARY KEY," +
                   "age varchar(10)," +
                   "birthday varchar(20)," +
                   "bust varchar(10)," +
                   "cup varchar(10)," +
                   "height varchar(10)," +
                   "hips varchar(10)," +
                   "hometown varchar(100)," +
                   "image varchar(500)," +
                   "waist varchar(10)" +
                ")");
    }
    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
