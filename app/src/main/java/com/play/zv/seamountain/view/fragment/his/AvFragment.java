package com.play.zv.seamountain.view.fragment.his;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.AVViewPagerAdapter;
import com.play.zv.seamountain.api.his.AVInfo;
import com.play.zv.seamountain.db.JavbusDBOpenHelper;
import com.play.zv.seamountain.presenter.his.AvPresenter;
import com.play.zv.seamountain.view.IviewBind.his.IavFragment;
import com.play.zv.seamountain.view.fragment.BaseFragment;
import com.play.zv.seamountain.widget.ToastUtils;

import java.util.List;

/**
 * Created by Zv on 2017/05/22.
 */
//
public class AvFragment extends BaseFragment implements IavFragment{
    private Button serch;

    private ImageView avcover;
    private EditText avnum;
    private ViewPager avvp;
    private AVViewPagerAdapter avViewPagerAdapter;
    private JavbusDBOpenHelper javbusDBOpenHelper;
    private AvPresenter javPresenter = new AvPresenter(this);
    private View view;


    @Override
    public View initViews() {
        view = View.inflate(mActivity, R.layout.fargment_av, null);
        //textView= (TextView) view.findViewById(R.id.av);
        serch = (Button) view.findViewById(R.id.serch);
        avcover = (ImageView) view.findViewById(R.id.avcover);
        avcover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logger.d();
                ToastUtils.showToast(mActivity,find(avnum.getText().toString().trim().toUpperCase()));
                //ToastUtils.showToast(mActivity, find(avnum.getText().toString().trim().toUpperCase()));
            }
        });
        avnum = (EditText) view.findViewById(R.id.avnum);
        serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!avnum.getText().toString().trim().isEmpty()) {
                    ToastUtils.showToast(mActivity, "开始给你翻网页");
                    Logger.d(avnum.getText().toString().trim());
                    loadData(avnum.getText().toString().trim());

                }
            }
        });
        avvp = (ViewPager) view.findViewById(R.id.avvp);
        javbusDBOpenHelper = new JavbusDBOpenHelper(mActivity, "javbus.db", null, 2);
        return view;

    }

    @Override
    public void initData() {
        //new Thread(runnable).start();
        //loadData("abp-120");
    }
//    String avjson;
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            /**
//             * 要执行的操作
//             */
//            try {
//                avjson=MyOkHttp.get("http://45.78.1.234:8080/MyAvLibrary/servlet/FindAVHtmlByName?avnum=cead-222");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            // 执行完毕后给handler发送一个空消息
//            handler.sendEmptyMessage(0);
//        }
//    };
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            /**
//             * 处理UI
//             */
//            Gson gson = new GsonBuilder().create();
//            AVInfo p = gson.fromJson(avjson, AVInfo.class);
//            Logger.d(p.getCover());
//            Glide.with(mActivity).load(p.getCover()).into(avcover);
//
//            //textView.setText(movieInfo.toString());
//            // 当收到消息时就会执行这个方法
//        }
//    };


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hidProgressBar() {

    }

    @Override
    public void loadData(String avnum) {
        javPresenter.loadAVdata(avnum);
    }

    @Override
    public void getDataSuccess(AVInfo avInfo) {
        //textView.setText(movieInfo.toString());
        //Toast.makeText(mActivity,"得到网页数据开始加载",Toast.LENGTH_LONG).show();
        avvp.setAdapter(avViewPagerAdapter = new AVViewPagerAdapter(avInfo.getPreviews(), mActivity));
        Logger.d(avInfo.toString());
        ToastUtils.showToast(mActivity, "得到网页数据开始加载");
        Glide.with(mActivity).load(avInfo.getCover()).into(avcover);

    }

    @Override
    public void getDataFail(String errCode, String errMsg) {

    }

    @Override
    public void unSubcription() {

    }

    public String find(String id) {
        String name =null;
        SQLiteDatabase db = javbusDBOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movieinfo where avnum = ?", new String[]{id});
        //存在数据才返回true
        if (cursor.moveToFirst()) {
            if (name == null) {
                name = cursor.getString(cursor.getColumnIndex("stars"));
            }
            else{
                name = name + cursor.getString(cursor.getColumnIndex("stars"));
            }

        }
        cursor.close();
        return name;

    }

    @Override
    public void writeDatabase(AVInfo avInfo) {

        SQLiteDatabase javbusDB = javbusDBOpenHelper.getWritableDatabase();
        if (avInfo != null) {
            javbusDB.execSQL("replace INTO movieinfo(avnum,censored,cover,director,genres,lable,release,runtime,series,studio,title,stars,previews) " +
                            "values ( ?,?,?,?,?,?,?,?,?,?,?,?,?)",

                    new String[]{
                            avInfo.getNum(),
                            avInfo.getCensored(),
                            avInfo.getCover(),
                            avInfo.getDirector(),
                            avInfo.getGenres().toString(),
                            avInfo.getLabel(),
                            avInfo.getRelease(),
                            avInfo.getRunTime(),
                            avInfo.getSeries(),
                            avInfo.getStudio(),
                            avInfo.getTitle(),
                            getStarsName(avInfo.getStars()),
                            avInfo.getPreviews().toString()});
            //ToastUtils.showToast(mActivity,"得到网页数据存入数据库中");
            for (AVInfo.StarsBean star : avInfo.getStars()) {
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

        }
    }

    public String getStarsName(List<AVInfo.StarsBean> stars) {
        String starName = null;
        for (AVInfo.StarsBean star : stars) {
            if (starName == null) {
                starName = star.getName();
            } else {
                starName = starName + "," + star.getName();
            }

        }
        return starName;
    }


}
