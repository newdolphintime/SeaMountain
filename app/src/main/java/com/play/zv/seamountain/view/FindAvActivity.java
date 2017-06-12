package com.play.zv.seamountain.view;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.cocosw.bottomsheet.BottomSheet;
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
import com.play.zv.seamountain.widget.SnackbarUtil;
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
    private View view;

    private FloatingActionButton fab_setting;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findav);
//        serch = (Button) findViewById(R.id.serch);
        view = findViewById(R.id.fabsetting);
        avcover = (ImageView) findViewById(R.id.avcover);
        fab_setting = (FloatingActionButton) findViewById(R.id.fabsetting);
        fab_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View mView = view;
                new BottomSheet.Builder(FindAvActivity.this).title("设置").sheet(R.menu.sd_database_mine).
                        listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case R.id.database:
                                        AvDataHelper.copyDataBaseToSD(FindAvActivity.this);
                                        SnackbarUtil.ShortSnackbar(mView, "备份数据库成功！", Color.BLACK, Color.WHITE).show();
                                        break;
                                    case R.id.sdcard:
                                        AvDataHelper.copySDToDataBase(FindAvActivity.this);
                                        SnackbarUtil.ShortSnackbar(mView, "恢复数据库成功！", Color.BLACK, Color.WHITE).show();
                                        break;
                                    case R.id.aboutme:
                                        break;
                                }
                            }
                        }).show();
            }
        });


        avcover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mavnum = avnum.getText().toString().trim().toUpperCase();
                Logger.d(FindAvActivity.this.getFilesDir().getAbsolutePath());
                Logger.d(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
                Logger.d(Environment.getExternalStorageState());
//                if (findMovie(mavnum, "stars") != null) {
//                    ToastUtils.showToast(FindAvActivity.this, findMovie(mavnum, "stars"));
//                }
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
        //onkeylistener点击搜索会点击搜索出现两次点击时间 解决 用setOnEditorActionListener 事件用EditorInfo.IME_ACTION_SEARCH
        avnum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(FindAvActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    if (!avnum.getText().toString().trim().isEmpty()) {
                        SnackbarUtil.ShortSnackbar(view, "搜索数据中！", Color.BLACK, Color.WHITE).show();
                        Logger.d(avnum.getText().toString().trim());
                        loadData(avnum.getText().toString().toUpperCase().trim());

                    }
                    return true;
                }
                return false;
            }
        });
        //javbusDBOpenHelper = new JavbusDBOpenHelper(FindAvActivity.this, "javbus.db", null, 2);


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
            SnackbarUtil.ShortSnackbar(view, "数据库中有数据！", Color.BLACK, Color.WHITE).show();
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

        SnackbarUtil.ShortSnackbar(view, "得到网页数据开始加载！", Color.BLACK, Color.WHITE).show();
        Glide.with(FindAvActivity.this).load(movieInfo.getCover()).
                diskCacheStrategy(DiskCacheStrategy.SOURCE).into(avcover);
    }

    @Override
    public void getDataFail(String errCode, String errMsg) {
        //ToastUtils.showToast(FindAvActivity.this, errMsg);
        SnackbarUtil.ShortSnackbar(view, errMsg, Color.BLACK, Color.WHITE).show();
    }

    @Override
    public void unSubcription() {

    }

    @Override
    public void writeDatabase(MovieInfo movieInfo) {
        SQLiteDatabase javbusDB = javbusDBOpenHelper.openDatabase(getApplicationContext());
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
        SQLiteDatabase db = javbusDBOpenHelper.openDatabase(getApplicationContext());
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
