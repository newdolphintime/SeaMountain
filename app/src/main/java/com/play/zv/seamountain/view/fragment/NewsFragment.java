package com.play.zv.seamountain.view.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.TextView;

import com.play.zv.seamountain.R;
import com.play.zv.seamountain.api.MovieInfo;
import com.play.zv.seamountain.api.jsoupApi.GetJavbus;

/**
 * Created by Zv on 2016/12/01.
 */

public class NewsFragment extends BaseFragment {
    private TextView textView;
    private  MovieInfo movieInfo;

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_news, null);
        textView= (TextView) view.findViewById(R.id.av);
        return view;
    }

    @Override
    public void initData() {
        new Thread(runnable).start();
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


}
