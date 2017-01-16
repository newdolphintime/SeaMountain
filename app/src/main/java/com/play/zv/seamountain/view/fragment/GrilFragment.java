package com.play.zv.seamountain.view.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.R;
import com.play.zv.seamountain.adapter.GrilAdapter;
import com.play.zv.seamountain.api.GrilInfo;
import com.play.zv.seamountain.presenter.GrilPresenter;
import com.play.zv.seamountain.view.IviewBind.IGrilFragment;
import com.play.zv.seamountain.view.PictureActivity;


import java.util.List;

/**
 * Created by Zv on 2016/12/01.
 */
//public class GrilFragment extends BaseFragment implements IGrilFragment {
public class GrilFragment extends BaseFragment implements IGrilFragment {
    private int lastVisibleItem;
    private int page = 1;


    private static RecyclerView recyclerview;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;
    private StaggeredGridLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GrilAdapter mAdapter;
    private GrilInfo grilInfo;
    private GrilPresenter grilPresenter = new GrilPresenter(this);

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_gril, null);

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.grid_coordinatorLayout);

        recyclerview = (RecyclerView) view.findViewById(R.id.grid_recycler);
        mLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
               // new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(mLayoutManager);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.grid_swipe_refresh);
        //调整SwipeRefreshLayout的位置
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        fab = (FloatingActionButton) view.findViewById(R.id.fab);


        return view;
    }

    @Override
    public void initData() {
        showProgressBar();
        loadData();

        //prepareFetchData(true);
    }

    @Override
    public void setListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerview.smoothScrollToPosition(0);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //page=1;
                loadData();
            }
        });


        //recyclerview滚动监听
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                // 滑动状态停止并且剩余少于两个item时，自动加载下一页
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 2 >= mLayoutManager.getItemCount()) {
                    loadMore("福利", 10, ++page);

                    System.out.println("当前页是" + page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                获取加载的最后一个可见视图在适配器的位置。
                int[] positions = mLayoutManager.findLastCompletelyVisibleItemPositions(null);
                //lastVisibleItem = Math.max(positions[0],positions[1]);
                lastVisibleItem = positions[0];

            }
        });
    }

    @Override
    public void showProgressBar() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hidProgressBar() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadData() {
        grilPresenter.loadGrilData("福利", 10, 1);
    }

    @Override
    public void loadMore(String type, int count, int page) {
        grilPresenter.loadGrilData(type, count, page);
    }

    @Override
    public void getDataSuccess(List<GrilInfo.GrilsEntity> grilsEntities) {
        Logger.d("getDataSuccess");
        if (grilInfo == null) {
            grilInfo = new GrilInfo();
            grilInfo.setResults(grilsEntities);
        } else {
            if (!grilInfo.getResults().containsAll(grilsEntities))//會調用Person的equal方法
                grilInfo.getResults().addAll(grilsEntities);
        }
        if (mAdapter == null) {
            recyclerview.setAdapter(mAdapter = new GrilAdapter(mActivity, grilInfo));

            mAdapter.setOnItemClickListener(new GrilAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, ImageView grilview, String url) {
                    int position = recyclerview.getChildAdapterPosition(view);
                    //SnackbarUtil.ShortSnackbar(coordinatorLayout,"点击第"+position+"个",SnackbarUtil.Info).show();
                    startPictureActivity(grilview, url);
                    Toast.makeText(mActivity, page + "", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onItemLongClick(View view) {

                }


            });


        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDataFail(String errCode, String errMsg) {

    }

    @Override
    public void unSubcription() {
        grilPresenter.unsubcription();
    }

    private void startPictureActivity(View meizhiView, String url) {
        Intent i = new Intent(mActivity, PictureActivity.class);
        i.putExtra(PictureActivity.EXTRA_IMAGE_URL, url);
        startActivity(i);
//        ActivityOptionsCompat optionsCompat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, meizhiView,
//                        PictureActivity.TRANSIT_PIC);
//        ActivityCompat.startActivity(mActivity, i, optionsCompat.toBundle());
       // ActivityCompat.startActivity(mActivity,i, ActivityOptions.makeSceneTransitionAnimation(mActivity, meizhiView, "sharedView").toBundle());
    }

//    private void init() {
//        ivHead.setImageResource(info.imageRes);
//        tvTitle.setText(info.title);
//        // 这里指定了被共享的视图元素
//        ViewCompat.setTransitionName(ivHead, OPTION_IMAGE);
//    }

}
