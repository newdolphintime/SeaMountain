package com.play.zv.seamountain.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.api.AvjsoupApi.Magnet;
import com.play.zv.seamountain.api.AvjsoupApi.MovieInfo;
import com.play.zv.seamountain.api.AvjsoupApi.Star;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static List<Magnet> findmagnet(String id, Context context) {
        mContext = context;
        List<Magnet> magnetList = new ArrayList<Magnet>();
        Magnet magnet;
        javbusDBOpenHelper = new JavbusDBOpenHelper(mContext, "javbus.db", null, 2);
        //String name = "";
        SQLiteDatabase db = javbusDBOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM magnetinfo where magnetNum = ? ", new String[]{id});
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
            String isCC = cursor.getString(cursor.getColumnIndex("isCC"));
            String magnetData = cursor.getString(cursor.getColumnIndex("magnetData"));
            String magnetNum = cursor.getString(cursor.getColumnIndex("magnetNum"));
            String magnetSize = cursor.getString(cursor.getColumnIndex("magnetSize"));
            String magnetTitle = cursor.getString(cursor.getColumnIndex("magnetTitle"));
            String magnetUrl = cursor.getString(cursor.getColumnIndex("magnetUrl"));
            magnet.setIsHD(Boolean.parseBoolean(isHD));
            magnet.setIsCC(Boolean.parseBoolean(isCC));
            magnet.setMagnetData(magnetData);
            magnet.setMagnetNum(magnetNum);
            magnet.setMagnetSize(magnetSize);
            magnet.setMagnetTitle(magnetTitle);
            magnet.setMagnetUrl(magnetUrl);

            magnetList.add(magnet);

        }
        cursor.close();
        return magnetList;
    }

    public static Star findstar(String starname, Context context) {
        Star star = null;
        mContext = context;
        javbusDBOpenHelper = new JavbusDBOpenHelper(mContext, "javbus.db", null, 2);

        SQLiteDatabase db = javbusDBOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from avstars where avstarname = ?", new String[]{starname});
        //存在数据才返回true
//        CREATE TABLE avstars(avstarname varchar(100) PRIMARY KEY,age varchar(10),birthday varchar(20),bust varchar(10),
//                cup varchar(10),height varchar(10),hips varchar(10),hometown varchar(100),
//                image varchar(500),waist varchar(10));
        if (cursor.moveToFirst()) {

            star = new Star();
            String avstarname = cursor.getString(cursor.getColumnIndex("avstarname"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            String bust = cursor.getString(cursor.getColumnIndex("bust"));
            String cup = cursor.getString(cursor.getColumnIndex("cup"));
            String height = cursor.getString(cursor.getColumnIndex("height"));
            String hips = cursor.getString(cursor.getColumnIndex("hips"));
            String hometown = cursor.getString(cursor.getColumnIndex("hometown"));
            String waist = cursor.getString(cursor.getColumnIndex("waist"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            Logger.d(image);


            star.setName(avstarname);
            star.setAge(age);
            star.setBirthday(birthday);
            star.setBust(bust);
            star.setCup(cup);
            star.setHeight(height);
            star.setHips(hips);
            star.setHometown(hometown);
            star.setWaist(waist);
            star.setImage(image);
        }
        cursor.close();
        return star;

    }

    public static List<MovieInfo> findmovieinfos(Context context) {
        MovieInfo movieInfo = null;
        Star star;
        List<MovieInfo> movieInfos = new ArrayList<MovieInfo>();

        mContext = context;
        javbusDBOpenHelper = new JavbusDBOpenHelper(mContext, "javbus.db", null, 2);

        SQLiteDatabase db = javbusDBOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movieinfo ", new String[]{});
        //存在数据才返回true
//        CREATE TABLE avstars(avstarname varchar(100) PRIMARY KEY,age varchar(10),birthday varchar(20),bust varchar(10),
//                cup varchar(10),height varchar(10),hips varchar(10),hometown varchar(100),
//                image varchar(500),waist varchar(10));
        while (cursor.moveToNext()) {
            List<Star> starList = new ArrayList<Star>();
            movieInfo = new MovieInfo();
            star = new Star();
            String avnum = cursor.getString(cursor.getColumnIndex("avnum"));
            String cover = cursor.getString(cursor.getColumnIndex("cover"));
            String runtime = cursor.getString(cursor.getColumnIndex("runtime"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String stars = cursor.getString(cursor.getColumnIndex("stars"));


            movieInfo.setNum(avnum);
            movieInfo.setCover(cover);
            movieInfo.setRunTime(runtime);
            movieInfo.setTitle(title);
            for (String starname : Arrays.asList(stars.split(","))) {

                star.setName(starname);
                starList.add(star);
            }
            movieInfo.setStars(starList);

            movieInfos.add(movieInfo);

        }
        cursor.close();
        return movieInfos;

    }

    public static List<Star> findstars( Context context) {
        Star star = null;
        List<Star> starList = new ArrayList<>();
        mContext = context;
        javbusDBOpenHelper = new JavbusDBOpenHelper(mContext, "javbus.db", null, 2);

        SQLiteDatabase db = javbusDBOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from avstars ", new String[]{});
        //存在数据才返回true
//        CREATE TABLE avstars(avstarname varchar(100) PRIMARY KEY,age varchar(10),birthday varchar(20),bust varchar(10),
//                cup varchar(10),height varchar(10),hips varchar(10),hometown varchar(100),
//                image varchar(500),waist varchar(10));
        while (cursor.moveToNext()) {

            star = new Star();
            String avstarname = cursor.getString(cursor.getColumnIndex("avstarname"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            String bust = cursor.getString(cursor.getColumnIndex("bust"));
            String cup = cursor.getString(cursor.getColumnIndex("cup"));
            String height = cursor.getString(cursor.getColumnIndex("height"));
            String hips = cursor.getString(cursor.getColumnIndex("hips"));
            String hometown = cursor.getString(cursor.getColumnIndex("hometown"));
            String waist = cursor.getString(cursor.getColumnIndex("waist"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            Logger.d(image);


            star.setName(avstarname);
            star.setAge(age);
            star.setBirthday(birthday);
            star.setBust(bust);
            star.setCup(cup);
            star.setHeight(height);
            star.setHips(hips);
            star.setHometown(hometown);
            star.setWaist(waist);
            star.setImage(image);
            starList.add(star);
        }
        cursor.close();
        return starList;

    }

    //"previews"
    public String findMovie(String id, String cloumn) {
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
