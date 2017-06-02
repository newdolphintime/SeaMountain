package com.play.zv.seamountain.view.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.AVViewPagerAdapter;
import com.play.zv.seamountain.adapter.AvStarRecyAdapter;
import com.play.zv.seamountain.api.AvjsoupApi.Magnet;
import com.play.zv.seamountain.api.AvjsoupApi.MovieInfo;
import com.play.zv.seamountain.api.AvjsoupApi.Star;
import com.play.zv.seamountain.api.AvjsoupApi.GetJavbus;
import com.play.zv.seamountain.db.JavbusDBOpenHelper;
import com.play.zv.seamountain.download.SimpleNotification;
import com.play.zv.seamountain.presenter.JavPresenter;
import com.play.zv.seamountain.view.AvDetilsActivity;
import com.play.zv.seamountain.view.IviewBind.IJavFragment;
import com.play.zv.seamountain.widget.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zv on 2016/12/01.
 */

public class NewsFragment extends BaseFragment implements IJavFragment {
    private AvStarRecyAdapter avStarRecyAdapter;
    private RecyclerView avcardList;
    private Button serch;
    private MovieInfo movieInfo;
    private ImageView avcover;
    private EditText avnum;
    private ViewPager avvp;
    private AVViewPagerAdapter avViewPagerAdapter;
    private JavbusDBOpenHelper javbusDBOpenHelper;
    private JavPresenter javPresenter = new JavPresenter(this);
    private View view;


    @Override
    public View initViews() {
        view = View.inflate(mActivity, R.layout.fragment_news, null);
        //avcardList = (RecyclerView) view.findViewById(R.id.avcardList);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        // new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false);
        //avcardList.setLayoutManager(mLayoutManager);
        serch = (Button) view.findViewById(R.id.serch);
        avcover = (ImageView) view.findViewById(R.id.avcover);

        avcover.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String mavnum = avnum.getText().toString().trim().toUpperCase();
                Logger.d(mActivity.getFilesDir().getAbsolutePath());
                Logger.d(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
                Logger.d(Environment.getExternalStorageState());
                if (findMovie(mavnum, "stars") != null) {
                    ToastUtils.showToast(mActivity, findMovie(mavnum, "stars"));
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
        avnum = (EditText) view.findViewById(R.id.avnum);
        serch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!avnum.getText().toString().trim().isEmpty()) {
                    ToastUtils.showToast(mActivity, "开始给你翻网页");
                    Logger.d(avnum.getText().toString().trim());
                    loadData(avnum.getText().toString().toUpperCase().trim());
                    initData();
                }
            }
        });
        avvp = (ViewPager) view.findViewById(R.id.avvp);
        javbusDBOpenHelper = new JavbusDBOpenHelper(mActivity, "javbus.db", null, 2);
        return view;

    }

    @Override
    public void initData() {
        Logger.d(findStar());
        if (avStarRecyAdapter == null) {
            //avcardList.setAdapter(avStarRecyAdapter = new AvStarRecyAdapter(mActivity, findStar()));
        } else {
            //avStarRecyAdapter.notifyDataSetChanged();
        }
        //new Thread(runnable).start();
        //loadData("abp-120");
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            /**
             * 要执行的操作
             */
            try {

                movieInfo = GetJavbus.getInfo("ABP-038");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 执行完毕后给handler发送一个空消息
            handler.sendEmptyMessage(0);
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /**
             * 处理UI
             */
            //textView.setText(movieInfo.toString());
            // 当收到消息时就会执行这个方法
        }
    };


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hidProgressBar() {

    }

    @Override
    public void loadData(String avnum) {
        if (findMovie(avnum, "cover").trim().isEmpty()) {
            javPresenter.loadAVdata(avnum);
        } else {
            ToastUtils.showToast(mActivity, "数据库里面有!");
            Glide.with(mActivity).load(findMovie(avnum, "cover").trim()).
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
        ToastUtils.showToast(mActivity, "得到网页数据开始加载");
        Glide.with(mActivity).load(movieInfo.getCover()).
                diskCacheStrategy(DiskCacheStrategy.SOURCE).into(avcover);

    }

    @Override
    public void getDataFail(String errCode, String errMsg) {
        ToastUtils.showToast(mActivity, errMsg);
    }

    @Override
    public void unSubcription() {

    }

    public List<Star> findStar() {
        List<Star> starList = new ArrayList<Star>();
        Star star;
        //String name = "";
        SQLiteDatabase db = javbusDBOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM avstars ", new String[]{});
        //存在数据才返回true

        while (cursor.moveToNext()) {
            star = new Star();

            String avstarname = cursor.getString(cursor.getColumnIndex("avstarname"));
            star.setName(avstarname);
            String image = cursor.getString(cursor.getColumnIndex("image"));
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

    private void startAvDetileActivity(View meizhiView, String avnum) {

        //startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, view1, "sharedView").toBundle());


        Intent i = new Intent(mActivity, AvDetilsActivity.class);
        i.putExtra(AvDetilsActivity.AVNUM, avnum);
        //共享元素转场动画
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(mActivity, avcover, "avcover").toBundle());
//        }
    }
}
