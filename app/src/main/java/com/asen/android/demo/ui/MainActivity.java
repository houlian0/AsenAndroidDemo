package com.asen.android.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.asen.android.demo.R;
import com.asen.android.demo.ui.afindview.AFindViewActivity;
import com.asen.android.demo.ui.error.ErrorTestActivity;
import com.asen.android.demo.ui.quickadapter.QuickAdapterActivity;
import com.asen.android.demo.ui.sqlite.SqliteTestActivity;
import com.asen.android.lib.base.module.activity.BaseActivity;
import com.asen.android.lib.base.ui.quick.findview.AFindView;
import com.asen.android.lib.base.ui.quick.findview.FindViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo程序主界面
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 12:20
 */
public class MainActivity extends BaseActivity implements MainAdapter.OnMainItemClickListener {

    @AFindView
    private RecyclerView recyclerView;

    private List<MainItem> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 快速FindView，可在示例Demo中看到更详细的介绍
        FindViewUtil.getInstance(mContext).findViews(this, this);

        // 设置LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // 设置ItemAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        recyclerView.setHasFixedSize(true);

        // 初始化数据
        initMainDataList();
        // 绑定适配器
        fillAdapter();
    }

    @Override
    public void onClick(MainItem mainItem) {
        // 跳转到指定的Activity
        Intent intent = new Intent(mContext, mainItem.getCls());
        startActivity(intent);
    }

    private MainAdapter adapter = null;

    // 刷新适配器
    private void fillAdapter() {
        if (adapter == null) {
            adapter = new MainAdapter(mContext, mDataList, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    // 初始化数据
    private void initMainDataList() {
        mDataList = new ArrayList<>();
        mDataList.add(new MainItem("快速FindView介绍", AFindViewActivity.class));
        mDataList.add(new MainItem("测试异常", ErrorTestActivity.class));
        mDataList.add(new MainItem("快速适配器", QuickAdapterActivity.class));
        mDataList.add(new MainItem(getString(R.string.sqliteTest), SqliteTestActivity.class));
    }


}
