package com.play.zv.seamountain.view.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.TextView;

import com.play.zv.seamountain.R;
import com.play.zv.seamountain.api.MovieInfo;
import com.play.zv.seamountain.api.jsoupApi.GetJavbus;
import com.play.zv.seamountain.presenter.JavPresenter;
import com.play.zv.seamountain.view.IviewBind.IJavFragment;

/**
 * Created by Zv on 2016/12/01.
 */

public class NewsFragment extends BaseFragment implements IJavFragment{
    private TextView textView;
    private  MovieInfo movieInfo;
    private JavPresenter javPresenter = new JavPresenter(this);

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_news, null);
        textView= (TextView) view.findViewById(R.id.av);
        return view;
    }

    @Override
    public void initData() {
        //new Thread(runnable).start();
        loadData();
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
            textView.setText(movieInfo.toString());
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
    public void loadData() {
        javPresenter.loadAVdata("abp-120");
    }

    @Override
    public void getDataSuccess(MovieInfo movieInfo) {
        textView.setText(movieInfo.toString());
    }

    @Override
    public void getDataFail(String errCode, String errMsg) {

    }

    @Override
    public void unSubcription() {

    }
}
