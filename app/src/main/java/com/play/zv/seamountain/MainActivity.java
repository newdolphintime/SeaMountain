package com.play.zv.seamountain;


import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.play.zv.seamountain.adapter.GrilAdapter;


import com.play.zv.seamountain.api.GrilInfo;
import com.play.zv.seamountain.presenter.GrilPresenter;
import com.play.zv.seamountain.view.IviewBind.IGrilActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity implements IGrilActivity {
    private int lastVisibleItem;
    private int page=1;


    private static RecyclerView recyclerview;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;
    private GridLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GrilAdapter mAdapter;
    private GrilInfo grilInfo;
    private GrilPresenter grilPresenter = new GrilPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //设置为空 就可以使titile居中
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        initView();
        setListener();
        loadData();
    }
    private void setListener(){
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
                        && lastVisibleItem +2>=mLayoutManager.getItemCount()) {
                    loadMore("福利", 10, ++page);

                    System.out.println("当前页是"+page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                获取加载的最后一个可见视图在适配器的位置。
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

            }
        });
    }
    private void initView(){
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.grid_coordinatorLayout);

        recyclerview=(RecyclerView)findViewById(R.id.grid_recycler);
        mLayoutManager=new GridLayoutManager(MainActivity.this,3,GridLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(mLayoutManager);

        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.grid_swipe_refresh) ;
        //调整SwipeRefreshLayout的位置
        swipeRefreshLayout.setProgressViewOffset(false, 0,  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        fab = (FloatingActionButton) findViewById(R.id.fab);


    }

    @Override
    public void showProgressBar() {

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
        if (grilInfo == null) {
            grilInfo = new GrilInfo();
            grilInfo.setResults(grilsEntities);
        } else {
            if (!grilInfo.getResults().containsAll(grilsEntities))//會調用Person的equal方法
                grilInfo.getResults().addAll(grilsEntities);
        }
        if (mAdapter == null) {
            recyclerview.setAdapter(mAdapter = new GrilAdapter(MainActivity.this, grilInfo));

            mAdapter.setOnItemClickListener(new GrilAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view) {
                    int position = recyclerview.getChildAdapterPosition(view);
                    //SnackbarUtil.ShortSnackbar(coordinatorLayout,"点击第"+position+"个",SnackbarUtil.Info).show();
                    Toast.makeText(MainActivity.this, page + "", Toast.LENGTH_SHORT).show();

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




}
