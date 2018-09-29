package com.example.dli.androiddemo.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dli.androiddemo.R;
import com.example.dli.androiddemo.bean.News;
import com.example.dli.androiddemo.common.base.BaseMvpActivity;
import com.example.dli.androiddemo.common.constants.CRecyclerView;
import com.example.dli.androiddemo.component.DaggerNewsComponent;
import com.example.dli.androiddemo.contract.NewsContract;
import com.example.dli.androiddemo.module.NewsModule;
import com.example.dli.androiddemo.presenter.NewsPresenter;
import com.example.dli.androiddemo.view.adapter.NewsAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseMvpActivity<NewsPresenter> implements NewsAdapter.OnItemClickListener, NewsContract.INewsView, NewsContract.OnLoadDataListener {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.xrc)
    XRecyclerView xRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        DaggerNewsComponent.builder().newsModule(new NewsModule(this)).build().inject(this);
        mPresenter.getList(CRecyclerView.REFRESH);
        initView();
    }

    private void initView() {
        super.initToolBar(this);
        // 设置为一个纵向网格布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setFootViewText("正在加载中，请稍等", "没有更多了呦");
    }

    private void initData(List<News> data) {
        // 设置数据
        xRecyclerView.setAdapter(new NewsAdapter(this, data, this));
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.getList(CRecyclerView.REFRESH);
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mPresenter.getList(CRecyclerView.LOAD_MORE);
                xRecyclerView.loadMoreComplete();
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
    public void onImageClick(View view) {
        SimpleDraweeView image = (SimpleDraweeView) view;
        image.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    public void onTextClick(View view) {
        Toast.makeText(this, "点击的内容 = " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public NewsContract.OnLoadDataListener getOnLoadDataListener() {
        return this;
    }

    @Override
    public void loadData(List<News> data) {
        initData(data);
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

