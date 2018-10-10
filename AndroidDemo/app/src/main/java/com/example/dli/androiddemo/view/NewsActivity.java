package com.example.dli.androiddemo.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.example.dli.androiddemo.R;
import com.example.dli.androiddemo.model.bean.News;
import com.example.dli.androiddemo.common.base.BaseMvpActivity;
import com.example.dli.androiddemo.common.constants.CRecyclerView;
import com.example.dli.androiddemo.component.DaggerNewsComponent;
import com.example.dli.androiddemo.contract.NewsContract;
import com.example.dli.androiddemo.module.NewsModule;
import com.example.dli.androiddemo.presenter.NewsPresenter;
import com.example.dli.androiddemo.view.adapter.NewsAdapter;
import com.example.dli.androiddemo.view.event.DefaultItemTouchHelpCallback;
import com.example.dli.androiddemo.view.event.DefaultItemTouchHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseMvpActivity<NewsPresenter> implements NewsAdapter.OnItemClickListener, NewsContract.INewsView, NewsContract.OnLoadDataListener {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.xrc)
    XRecyclerView xRecyclerView;

    @BindView(R.id.pv_Image)
    PhotoView photoView;

    private NewsAdapter newsAdapter;

    private List<News> data;

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
        this.data = data;
        newsAdapter = new NewsAdapter(this, data, this);
        xRecyclerView.setAdapter(newsAdapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingListener(loadingListener);
        DefaultItemTouchHelper itemTouchHelper = new DefaultItemTouchHelper(onItemTouchCallbackListener);
        itemTouchHelper.attachToRecyclerView(xRecyclerView);
    }

    private DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener = new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
        @Override
        public void onSwiped(int adapterPosition) {
            // 滑动删除的时候，从数据源移除，并刷新这个Item。
            if (data != null) {
                data.remove(adapterPosition);
                newsAdapter.notifyItemRemoved(adapterPosition);
            }
        }

        @Override
        public boolean onMove(int srcPosition, int targetPosition) {
            if (data != null) {
                // 更换数据源中的数据Item的位置
                Collections.swap(data, srcPosition, targetPosition);
                // 更新UI中的Item的位置，主要是给用户看到交互效果
                newsAdapter.notifyItemMoved(srcPosition, targetPosition);
                return true;
            }
            return false;
        }
    };


    private XRecyclerView.LoadingListener loadingListener = new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            mPresenter.getList(CRecyclerView.REFRESH);
            xRecyclerView.refreshComplete();
            newsAdapter.notifyDataSetChanged();
        }

        @Override
        public void onLoadMore() {
            mPresenter.getList(CRecyclerView.LOAD_MORE);
            xRecyclerView.loadMoreComplete();
            newsAdapter.notifyDataSetChanged();
        }
    };

    public void back(View v) {
        Snackbar.make(v, "返回列表头部", Snackbar.LENGTH_LONG)
                .setAction("撤销", v1 -> {
                    Toast.makeText(this, "撤销成功!", Toast.LENGTH_SHORT).show();
                }).show();
    }

    @Override
    public void onImageClick(News item, SimpleDraweeView iv) {
        // 从普通的ImageView中获取Info
        Info info = PhotoView.getImageViewInfo(iv);
        photoView.setVisibility(View.VISIBLE);
        photoView.setImageDrawable(iv.getDrawable());
        photoView.setOnClickListener(v -> photoView.animaTo(info, () -> photoView.setVisibility(View.GONE)));
        // 启用图片缩放功能
        photoView.enable();
        // 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
        photoView.animaFrom(info);
        // 获取/设置 动画持续时间
        photoView.setAnimaDuring(500);
        // 获取/设置 最大缩放倍数
        photoView.setMaxScale(2);
    }

    @Override
    public void onTextClick(News item) {
        Toast.makeText(this, "点击的内容 = " + item.getDesc() + "\t" + item.getWho(), Toast.LENGTH_SHORT).show();
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

