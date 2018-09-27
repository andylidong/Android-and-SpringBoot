package com.example.dli.androiddemo.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dli.androiddemo.R;
import com.example.dli.androiddemo.bean.News;
import com.example.dli.androiddemo.common.base.BaseMvpActivity;
import com.example.dli.androiddemo.component.DaggerNewsComponent;
import com.example.dli.androiddemo.contract.NewsContract;
import com.example.dli.androiddemo.module.NewsModule;
import com.example.dli.androiddemo.presenter.NewsPresenter;
import com.example.dli.androiddemo.view.adapter.NewsAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseMvpActivity<NewsPresenter> implements NewsAdapter.OnItemClickListener, NewsContract.INewsView, NewsContract.OnLoadDataListener {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.rc)
    RecyclerView recyclerView;

    @BindView(R.id.xrc)
    XRecyclerView xRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        DaggerNewsComponent.builder().newsModule(new NewsModule(this)).build().inject(this);
        mPresenter.getList();
        initView();
    }

    private void initView() {
        // 设置为一个1列的纵向网格布局
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        // 设置为一个纵向网格布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
    }

    private void initData(List<News> data) {
        // 设置数据
        // recyclerView.setAdapter(new NewsAdapter(this, data, this));
        xRecyclerView.setAdapter(new NewsAdapter(this, data, this));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.getList();
            }

            @Override
            public void onLoadMore() {
                mPresenter.getList();
            }
        });
    }

    public void back(View v) {
        Snackbar.make(v, "返回列表头部", Snackbar.LENGTH_LONG)
                .setAction("撤销", v1 -> {
                    Toast.makeText(this, "撤销成功!", Toast.LENGTH_SHORT).show();
                }).show();
    }

    @Override
    public void onItemClick(View view) {
        Toast.makeText(this, "点击的内容 = " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view) {
        Toast.makeText(this, "点击的内容 = " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public NewsContract.OnLoadDataListener getOnLoadDataListener() {
        return this;
    }

    @Override
    public void loadData(List<News> data) {
        System.out.println("data == " + data);
        initData(data);
        xRecyclerView.refreshComplete();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (xRecyclerView != null) {
            xRecyclerView.destroy();
            xRecyclerView = null;
        }
    }
}

