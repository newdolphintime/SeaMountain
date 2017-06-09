package com.play.zv.seamountain.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.AVViewPagerAdapter;
import com.play.zv.seamountain.adapter.AvStarRecyAdapter;
import com.play.zv.seamountain.api.AvjsoupApi.Magnet;
import com.play.zv.seamountain.api.AvjsoupApi.MovieInfo;
import com.play.zv.seamountain.api.AvjsoupApi.Star;
import com.play.zv.seamountain.db.AvDataHelper;
import com.play.zv.seamountain.db.JavbusDBOpenHelper;
import com.play.zv.seamountain.presenter.JavPresenter;
import com.play.zv.seamountain.view.IviewBind.IJavFragment;
import com.play.zv.seamountain.widget.ToastUtils;


import java.util.Arrays;
import java.util.List;

/**
 * Created by Zv on 2017/06/04.
 */

public class FindAvActivity extends AppCompatActivity implements IJavFragment {
    private MovieInfo movieInfo;
    private Button serch;
    private ImageView avcover;
    private EditText avnum;
    private JavbusDBOpenHelper javbusDBOpenHelper;
    private JavPresenter javPresenter = new JavPresenter(this);
    private Button but_db2sd;
    private Button but_sd2db;
    private TextView tvpro;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findav);
//        serch = (Button) findViewById(R.id.serch);
        avcover = (ImageView) findViewById(R.id.avcover);

        but_db2sd = (Button) findViewById(R.id.but_db2sd);
        but_sd2db= (Button) findViewById(R.id.but_sd2db);
        tvpro = (TextView) findViewById(R.id.tvpro);
        but_db2sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvpro.setText("正在导出");
                AvDataHelper.copyDataBaseToSD(FindAvActivity.this);
                tvpro.setText("导出成功");
            }
        });
        but_sd2db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvpro.setText("正在导出");
                AvDataHelper.copySDToDataBase(FindAvActivity.this);
                tvpro.setText("导出成功");
            }
        });


        avcover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mavnum = avnum.getText().toString().trim().toUpperCase();
                Logger.d(FindAvActivity.this.getFilesDir().getAbsolutePath());
                Logger.d(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
                Logger.d(Environment.getExternalStorageState());
                if (findMovie(mavnum, "stars") != null) {
                    ToastUtils.showToast(FindAvActivity.this, findMovie(mavnum, "stars"));
                }
                //下载代码
//                SimpleNotification notification = new
//                        SimpleNotification(mActivity,
//                        findMovie(avnum.getText().toString().trim().toUpperCase(), "cover"),
//                        avnum.getText().toString().trim().toUpperCase() + ".jpg");
//                notification.start();
                startAvDetileActivity(null, mavnum);

            }
        });
        avnum = (EditText) findViewById(R.id.avnum);
//        serch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!avnum.getText().toString().trim().isEmpty()) {
//                    ToastUtils.showToast(FindAvActivity.this, "开始给你翻网页");
//                    Logger.d(avnum.getText().toString().trim());
//                    loadData(avnum.getText().toString().toUpperCase().trim());
//
//                }
//
//
//            }
//        });
        avnum.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(FindAvActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    if (!avnum.getText().toString().trim().isEmpty()) {
                        ToastUtils.showToast(FindAvActivity.this, "开始给你翻网页");
                        Logger.d(avnum.getText().toString().trim());
                        loadData(avnum.getText().toString().toUpperCase().trim());

                    }
                }
                return false;
            }

        });
        javbusDBOpenHelper = new JavbusDBOpenHelper(FindAvActivity.this, "javbus.db", null, 2);





    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hidProgressBar() {

    }

    public void loadData(String avnum) {
        if (findMovie(avnum, "cover").trim().isEmpty()) {
            javPresenter.loadAVdata(avnum);
        } else {
            ToastUtils.showToast(FindAvActivity.this, "数据库里面有!");
            Glide.with(FindAvActivity.this).load(findMovie(avnum, "cover").trim()).
                    diskCacheStrategy(DiskCacheStrategy.SOURCE).into(avcover);
            List<String> previews = Arrays.asList(findMovie(avnum, "previews").split(","));
            //avvp.setAdapter(avViewPagerAdapter = new AVViewPagerAdapter(previews, mActivity));
        }


    }

    @Override
    public void getDataSuccess(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
        //textView.setText(movieInfo.toString());
        //Toast.makeText(mActivity,"得到网页数据开始加载",Toast.LENGTH_LONG).show();
        //avvp.setAdapter(avViewPagerAdapter = new AVViewPagerAdapter(movieInfo.getPreviews(), mActivity));
        Logger.d(movieInfo);
        ToastUtils.showToast(FindAvActivity.this, "得到网页数据开始加载");
        Glide.with(FindAvActivity.this).load(movieInfo.getCover()).
                diskCacheStrategy(DiskCacheStrategy.SOURCE).into(avcover);
    }

    @Override
    public void getDataFail(String errCode, String errMsg) {
        ToastUtils.showToast(FindAvActivity.this, errMsg);
    }

    @Override
    public void unSubcription() {

    }

    @Override
    public void writeDatabase(MovieInfo movieInfo) {
        SQLiteDatabase javbusDB = javbusDBOpenHelper.getWritableDatabase();
        if (movieInfo != null) {
            javbusDB.execSQL("replace INTO movieinfo(avnum,censored,cover,director,genres,lable,release,runtime,series,studio,title,stars,previews) " +
                            "values ( ?,?,?,?,?,?,?,?,?,?,?,?,?)",

                    new String[]{
                            movieInfo.getNum(),
                            movieInfo.getCensored(),
                            movieInfo.getCover(),
                            movieInfo.getDirector(),
                            movieInfo.getGenres().toString(),
                            movieInfo.getLabel(),
                            movieInfo.getRelease(),
                            movieInfo.getRunTime(),
                            movieInfo.getSeries(),
                            movieInfo.getStudio(),
                            movieInfo.getTitle(),
                            getStarsName(movieInfo.getStars()),
                            getListname(movieInfo.getPreviews())});
            //ToastUtils.showToast(mActivity,"得到网页数据存入数据库中");
            for (Star star : movieInfo.getStars()) {
                if (star != null) {
                    javbusDB.execSQL(
                            "replace into avstars(" +
                                    "avstarname," +
                                    "age," +
                                    "birthday," +
                                    "bust," +
                                    "cup," +
                                    "height," +
                                    "hips," +
                                    "hometown," +
                                    "image," +
                                    "waist" +
                                    ")" + "values ( ?,?,?,?,?,?,?,?,?,?)",
                            new String[]{
                                    star.getName(),
                                    star.getAge(),
                                    star.getBirthday(),
                                    star.getBust(),
                                    star.getCup(),
                                    star.getHeight(),
                                    star.getHips(),
                                    star.getHometown(),
                                    star.getImage(),
                                    star.getWaist()
                            });
                }
            }
            for (Magnet magnet : movieInfo.getMagnet()) {
                if (magnet != null) {
                    javbusDB.execSQL(
                            "replace into magnetinfo (" +
                                    "isCC ," +
                                    "isHD ," +
                                    "magnetData ," +
                                    "magnetNum ," +
                                    "magnetSize ," +
                                    "magnetTitle ," +
                                    "magnetUrl " +
                                    ")values (?,?,?,?,?,?,?)",
                            new String[]{
                                    String.valueOf(

                                            magnet.getIsCC()),
                                    String.valueOf(

                                            magnet.getIsHD()),
                                    magnet.getMagnetData(),
                                    magnet.getMagnetNum(),
                                    magnet.getMagnetSize(),
                                    magnet.getMagnetTitle(),
                                    magnet.getMagnetUrl()

                            });
                }

            }

        }
    }
    public String getStarsName(List<Star> stars) {
        String starName = null;
        for (Star star : stars) {
            if (starName == null) {
                starName = star.getName();
            } else {
                starName = starName + "," + star.getName();
            }

        }
        return starName;
    }

    public String getListname(List<String> strs) {
        String name = "";
        for (String str : strs) {
            if (name.trim().isEmpty()) {
                name = str;
            } else {
                name = name + "," + str;
            }

        }
        return name;
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
    private void startAvDetileActivity(View meizhiView, String avnum) {

        //startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, view1, "sharedView").toBundle());


        Intent i = new Intent(FindAvActivity.this, AvDetilsActivity.class);
        i.putExtra(AvDetilsActivity.AVNUM, avnum);
        //共享元素转场动画
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(FindAvActivity.this, avcover, "avcover").toBundle());
//        }
    }
}
