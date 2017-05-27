package com.play.zv.seamountain.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Zv on 2017/05/27.
 */

public class AvDataHelper {
    private static   Context mContext;
    private static JavbusDBOpenHelper  javbusDBOpenHelper;



    public static String findMovie(String id, String cloumn,Context context) {
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
}
