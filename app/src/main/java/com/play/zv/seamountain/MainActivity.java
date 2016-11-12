package com.play.zv.seamountain;


import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.google.gson.Gson;
import GrilInfo.GrilInfo;



public class MainActivity extends AppCompatActivity {
    private static RecyclerView recyclerview;
    private CoordinatorLayout coordinatorLayout;
    private GridLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GrilAdapter mAdapter;
    private GrilInfo grilInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        new GetData().execute("http://gank.io/api/data/福利/10/1");
    }

    private void initView(){
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.grid_coordinatorLayout);

        recyclerview=(RecyclerView)findViewById(R.id.grid_recycler);
        mLayoutManager=new GridLayoutManager(MainActivity.this,3,GridLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(mLayoutManager);

        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.grid_swipe_refresh) ;
        //调整SwipeRefreshLayout的位置
        swipeRefreshLayout.setProgressViewOffset(false, 0,  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));


    }
    private class GetData extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            return MyOkHttp.get(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)) {

                Gson gson = new Gson();

                grilInfo = gson.fromJson(s, GrilInfo.class);

            }
            if(mAdapter==null){

                recyclerview.setAdapter(mAdapter = new GrilAdapter(MainActivity.this,grilInfo));

                mAdapter.setOnItemClickListener(new GrilAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view) {
                        int position=recyclerview.getChildAdapterPosition(view);
                        //SnackbarUtil.ShortSnackbar(coordinatorLayout,"点击第"+position+"个",SnackbarUtil.Info).show();
                    }

                    @Override
                    public void onItemLongClick(View view) {
                        //itemTouchHelper.startDrag(recyclerview.getChildViewHolder(view));
                    }
                });

                //itemTouchHelper.attachToRecyclerView(recyclerview);
            }else{
                mAdapter.notifyDataSetChanged();
            }

        }
    }

}
