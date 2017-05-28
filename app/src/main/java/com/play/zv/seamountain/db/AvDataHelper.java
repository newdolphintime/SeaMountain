package com.play.zv.seamountain.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.play.zv.seamountain.api.AvjsoupApi.Magnet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zv on 2017/05/27.
 */

public class AvDataHelper {
    private static Context mContext;
    private static JavbusDBOpenHelper javbusDBOpenHelper;


    public static String findMovie(String id, String cloumn, Context context) {
        mContext = context;
        javbusDBOpenHelper = new JavbusDBOpenHelper(mContext, "javbus.db", null, 2);
        String name = "";
        SQLiteDatabase db = javbusDBOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movieinfo where avnum = ?", new String[]{id});
        //存在数据才返回true

        if (cursor.moveToFirst()) {
            if (name.trim().isEmpty()) {
                name = cursor.getString(cursor.getColumnIndex(cloumn));
            } else {
                name = name + cursor.getString(cursor.getColumnIndex(cloumn));
            }

        }
        cursor.close();
        return name;

    }

    public static List<Magnet> findmagnet(String id, String cloumn, Context context) {
        List<Magnet> magnetList = new ArrayList<Magnet>();
        Magnet magnet;
        javbusDBOpenHelper = new JavbusDBOpenHelper(mContext, "javbus.db", null, 2);
        //String name = "";
        SQLiteDatabase db = javbusDBOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM avstars ", new String[]{});
        //存在数据才返回true

        while (cursor.moveToNext()) {
            magnet = new Magnet();
            /*
            * replace into magnetinfo (
              isHD ,
              magnetData ,
              magnetNum ,
              magnetSize ,
              magnetTitle ,
              magnetUrl
              )values (?,?,?,?,?,?)
            * */
            String isHD = cursor.getString(cursor.getColumnIndex("isHD"));
            String magnetData = cursor.getString(cursor.getColumnIndex("magnetData"));
            String magnetNum = cursor.getString(cursor.getColumnIndex("magnetNum"));
            String magnetSize = cursor.getString(cursor.getColumnIndex("magnetSize"));
            String magnetTitle = cursor.getString(cursor.getColumnIndex("magnetTitle"));
            String magnetUrl = cursor.getString(cursor.getColumnIndex("magnetUrl"));
            magnet.setIsHD(Boolean.parseBoolean(isHD));
            magnet.setMagnetData(magnetData);
            magnet.setMagnetNum(magnetNum);
            magnet.setMagnetSize(magnetSize);
            magnet.setMagnetTitle(magnetTitle);
            magnet.setMagnetUrl(magnetUrl);



        }
        cursor.close();
        return magnetList;
    }

}
