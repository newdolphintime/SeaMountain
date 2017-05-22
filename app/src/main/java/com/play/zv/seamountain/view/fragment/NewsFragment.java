package com.play.zv.seamountain.view.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.AVViewPagerAdapter;
import com.play.zv.seamountain.api.MovieInfo;
import com.play.zv.seamountain.api.jsoupApi.GetJavbus;
import com.play.zv.seamountain.presenter.JavPresenter;
import com.play.zv.seamountain.view.IviewBind.IJavFragment;
import com.play.zv.seamountain.widget.ToastUtils;

/**
 * Created by Zv on 2016/12/01.
 */

public class NewsFragment extends BaseFragment implements IJavFragment{
    private Button serch;
    private  MovieInfo movieInfo;
    private ImageView avcover;
    private EditText avnum;
    private ViewPager avvp;
    private AVViewPagerAdapter avViewPagerAdapter;
    private JavPresenter javPresenter = new JavPresenter(this);

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_news, null);
        //textView= (TextView) view.findViewById(R.id.av);
        serch = (Button) view.findViewById(R.id.serch);
        avcover = (ImageView) view.findViewById(R.id.avcover);
        avnum = (EditText) view.findViewById(R.id.avnum);
        serch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!avnum.getText().toString().trim().isEmpty()){
                    ToastUtils.showToast(mActivity,"开始给你翻网页");
                    Logger.d(avnum.getText().toString().trim());
                    loadData(avnum.getText().toString().trim());
                }
            }
        });
        avvp = (ViewPager) view.findViewById(R.id.avvp);
        return view;

    }

    @Override
    public void initData() {
        //new Thread(runnable).start();
        //loadData("abp-120");
    }
    Runnable runnable = new Runnable(){
        @Override
        public void run() {
            /**
             * 要执行的操作
             */
            try {
                movieInfo=GetJavbus.getInfo("ABP-038");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 执行完毕后给handler发送一个空消息
            handler.sendEmptyMessage(0);
        }
    };
    Handler handler = new Handler(){
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
        javPresenter.loadAVdata(avnum);
    }

    @Override
    public void getDataSuccess(MovieInfo movieInfo) {
        //textView.setText(movieInfo.toString());
        //Toast.makeText(mActivity,"得到网页数据开始加载",Toast.LENGTH_LONG).show();
        avvp.setAdapter(avViewPagerAdapter=new AVViewPagerAdapter(movieInfo.getPreviews(),mActivity));
        Logger.d(movieInfo);
        ToastUtils.showToast(mActivity,"得到网页数据开始加载");
        Glide.with(mActivity).load(movieInfo.getCover()).into(avcover);
    }

    @Override
    public void getDataFail(String errCode, String errMsg) {

    }

    @Override
    public void unSubcription() {

    }
}
