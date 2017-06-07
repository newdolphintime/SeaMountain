package com.play.zv.seamountain.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.blankj.utilcode.util.ClipboardUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.PreviewAdapter;
import com.play.zv.seamountain.api.AvjsoupApi.Magnet;
import com.play.zv.seamountain.api.AvjsoupApi.Star;
import com.play.zv.seamountain.db.AvDataHelper;
import com.play.zv.seamountain.widget.CustomerTextView;
import com.play.zv.seamountain.widget.FontCache;
import com.play.zv.seamountain.widget.SnackbarUtil;
import com.play.zv.seamountain.widget.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.R.attr.duration;

/**
 * Created by Zv on 2017/05/27.
 */

public class AvDetilsActivity extends AppCompatActivity {
    private TextView mcensored;
    private TextView mruntime;
    private TextView avname;
    private ImageView avcover;
    private TextView avnum;
    private GridView gridView;
    private Context mContext;

    public String mAvnum;
    public static final String AVNUM = "av_num";
    public static final String STARNAME = "star_num";
    private LinearLayout linearLayout;
    private LinearLayout megnetlinearLayout;
    private PreviewAdapter previewAdapter;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private HorizontalScrollView nestedScrollView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.av_detail_activity);
        postponeEnterTransition();
        mContext = getApplicationContext();
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        avcover = (ImageView) findViewById(R.id.avcover);
        avnum = (TextView) findViewById(R.id.avnum);
//        mcensored = (TextView) findViewById(R.id.censored);
//        mruntime = (TextView) findViewById(R.id.runtime);
        avname = (TextView) findViewById(R.id.avname);
        linearLayout = (LinearLayout) findViewById(R.id.starlayout);
        nestedScrollView = (HorizontalScrollView) findViewById(R.id.av_star_scroll);
        megnetlinearLayout = (LinearLayout) findViewById(R.id.megnetLayout);
        gridView = (GridView) findViewById(R.id.gridpreview);

        //设置透明状态栏
        ImmersionBar.
                with(this)
                .statusBarDarkFont(true)
                .init();

        parseIntent();

        String avCover = AvDataHelper.findMovie(mAvnum, "cover", mContext);
        /*
        * 20170606
        * 共享元素动画一直出现一条缝隙  我加了一个asBitmap()   奇迹的好了  。。待看
        *
        * */
        Glide.with(mContext).load(avCover).asBitmap().fitCenter().
                diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(avcover);
        Logger.d(avCover);
        scheduleStartPostponedTransition(avcover);
        avnum.setText(mAvnum);

        String avName = AvDataHelper.findMovie(mAvnum, "title", mContext);
        avname.setText(avName);
        //mcensored.setText(AvDataHelper.findMovie(mAvnum, "censored", mContext));
        //mruntime.setText(AvDataHelper.findMovie(mAvnum, "runtime", mContext));

        //多个starlist
        String starsname = AvDataHelper.findMovie(mAvnum, "stars", mContext);
        if (starsname != null) {
            List<String> starsnames = Arrays.asList(starsname.split(","));

            List<Star> stars = new ArrayList<>();
            if (starsnames != null) {
                for (String avstarname : starsnames) {
                    Star star = AvDataHelper.findstar(avstarname.trim(), mContext);
                    Logger.d(avstarname.trim());
                    stars.add(star);
                }
            }
            if (stars.size() != 0) {
                for (int i = 0; i < stars.size(); i++) {
                    int height = dip2px(mContext, 50);
                    int width = dip2px(mContext, 50);
                    final ImageView imageView = new ImageView(this);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(height, width);
                    layoutParams.setMargins(10, 10, 10, 10);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setTransitionName("starphoto");
                    final String starnametest = stars.get(i).getName();
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(mContext, StarDetailActivity.class);
                            i.putExtra(AvDetilsActivity.STARNAME, starnametest);
                            //共享元素转场动画
                            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(AvDetilsActivity.this, imageView, "starphoto").toBundle());
                        }
                    });
                    Glide.with(mContext).load(stars.get(i).getImage()).centerCrop().
                            diskCacheStrategy(DiskCacheStrategy.SOURCE).
                            bitmapTransform(new CropCircleTransformation(mContext)).
                            into(imageView);
                    linearLayout.addView(imageView);

                }
            }
        }
        List<Magnet> magnetList = AvDataHelper.findmagnet(mAvnum, mContext);
        //多个磁力list
        if (magnetList != null) {
            //addLinearLayout(magnetList);
            addCard(magnetList);
        }

        List<String> previews = Arrays.asList(AvDataHelper.findMovie(mAvnum, "previews", mContext).split(","));
        Logger.d(previews.get(0));
        previewAdapter = new PreviewAdapter(mContext, previews);
        if (!previews.get(0).isEmpty()) {
            gridView.setVisibility(View.VISIBLE);
            gridView.setAdapter(previewAdapter);
        }

    }

    private void parseIntent() {
        mAvnum = getIntent().getStringExtra(AVNUM);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 动态添加线性布局
     */
    private void addLinearLayout(List<Magnet> magnets) {
        //initMissionList：存储几条测试数据
        for (int i = 0; i < magnets.size(); i++) {
            //实例化一个LinearLayout
            LinearLayout linearLayout = new LinearLayout(this);
            //设置LinearLayout属性(宽和高)
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dip2px(mContext, 50));

            //将以上的属性赋给LinearLayout
            linearLayout.setLayoutParams(layoutParams);
            //实例化一个TextView
            TextView tv = new TextView(this);
            //设置宽高以及权重
            LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //设置textview垂直居中
            tvParams.gravity = Gravity.CENTER_VERTICAL;
            tv.setLayoutParams(tvParams);
            tv.setTextSize(14);
            tv.setText(magnets.get(i).getMagnetUrl().toString().trim());


            linearLayout.addView(tv);


            megnetlinearLayout.addView(linearLayout);
        }

    }

    /**
     * 动态添加线性布局
     */
    private void addCard(List<Magnet> magnets) {
        @android.support.annotation.IdRes
        int TAG1401 = 1000;
        int TAG1402 = 1001;
        int TAG1403 = 1002;
        int TAG1404 = 1003;
        int TAG1405 = 1004;
        int TAG1406 = 1005;
        int TAG1407 = 1006;
        for (int i = 0; i < magnets.size(); i++) {

            CardView cardView = new CardView(mContext);
            RelativeLayout myrelativeLayout = new RelativeLayout(mContext);
            myrelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

            TextView customerTextView = new TextView(mContext);
            RelativeLayout.LayoutParams cuslayoutParams =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            cuslayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

            cuslayoutParams.leftMargin = dip2px(mContext, 10);
            customerTextView.setLayoutParams(cuslayoutParams);
            customerTextView.setId(TAG1401);
            customerTextView.setText(magnets.get(i).getMagnetNum());
            customerTextView.setTextSize(dip2px(mContext, 10));
            Typeface typeFace = FontCache.getTypeface("FZJHJW.ttf", mContext);
            customerTextView.setTextColor(Color.BLACK);
            customerTextView.setTypeface(typeFace);

            TextView customerTextView2 = new TextView(mContext);

            RelativeLayout.LayoutParams cuslayoutParams2 =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            cuslayoutParams2.addRule(RelativeLayout.CENTER_VERTICAL);
            cuslayoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            cuslayoutParams2.rightMargin = dip2px(mContext, 10);

            customerTextView2.setLayoutParams(cuslayoutParams2);
            customerTextView2.setText(magnets.get(i).getMagnetSize());
            customerTextView2.setTextSize(dip2px(mContext, 8));

            customerTextView2.setTextColor(Color.BLACK);
            customerTextView2.setTypeface(typeFace);
            myrelativeLayout.addView(customerTextView);
            myrelativeLayout.addView(customerTextView2);
            Logger.d(magnets.get(i).getIsHD());
            if (magnets.get(i).getIsHD()) {
                RelativeLayout.LayoutParams cuslayoutParams3 =
                        new RelativeLayout.LayoutParams(dip2px(mContext, 24), dip2px(mContext, 24));
                ImageView HDimageView = new ImageView(mContext);
                cuslayoutParams3.addRule(RelativeLayout.CENTER_VERTICAL);
                cuslayoutParams3.addRule(RelativeLayout.RIGHT_OF, TAG1401);
                cuslayoutParams3.leftMargin = dip2px(mContext, 10);
                HDimageView.setImageResource(R.drawable.hd_icon);
                HDimageView.setLayoutParams(cuslayoutParams3);

                myrelativeLayout.addView(HDimageView);
            }


            ///myrelativeLayout.setBackground(getSelectedItemDrawable());


            CardView.LayoutParams layoutParams = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(mContext, 60));
            layoutParams.setMargins(dip2px(mContext, 5), dip2px(mContext, 5), dip2px(mContext, 5), dip2px(mContext, 5));


            cardView.setRadius(dip2px(mContext, 3));
            cardView.setForeground(getSelectedItemDrawable());

            cardView.setLayoutParams(layoutParams);
            cardView.addView(myrelativeLayout);
            cardView.setCardElevation(dip2px(mContext, 1));
            cardView.setClickable(true);

            final String magnetUrl = magnets.get(i).getMagnetUrl();
            megnetlinearLayout.addView(cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //ToastUtils.showToast(mContext, magnetUrl);

                    myClip = ClipData.newPlainText("text", magnetUrl);
                    myClipboard.setPrimaryClip(myClip);

//                  Snackbar.make(view, "磁力链接已经复制到剪贴板", duration)
//
//                           .show();
                    SnackbarUtil.ShortSnackbar(view, "磁力链接已经复制到剪贴板", Color.BLACK, Color.WHITE).show();
                }
            });

        }


    }

    public Drawable getSelectedItemDrawable() {
        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray ta = mContext.obtainStyledAttributes(attrs);
        Drawable selectedItemDrawable = ta.getDrawable(0);
        ta.recycle();
        return selectedItemDrawable;
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }


}
