package com.asen.android.demo.ui.quickadapter;

import android.os.Bundle;
import android.widget.ListView;

import com.asen.android.demo.R;
import com.asen.android.lib.base.module.activity.BaseActivity;
import com.asen.android.lib.base.ui.quick.findview.AFindView;
import com.asen.android.lib.base.ui.quick.findview.FindViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 快速设配器页面
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 15:54
 */
public class QuickAdapterActivity extends BaseActivity {

    @AFindView
    private ListView lvShow;

    private List<String> mDataList; // 数据源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataList = new ArrayList<>();
        setContentView(R.layout.ui_quickadapter);
        FindViewUtil.getInstance(mContext).findViews(this, this);
        initAdapter();
        fillAdapter();
    }

    // 初始化适配器
    private void initAdapter() {
        mDataList.clear();
        mDataList.add("C++");
        mDataList.add("C#");
        mDataList.add("JAVA");
        mDataList.add("C");
        mDataList.add("Android");
        mDataList.add("IOS");
        mDataList.add("WINDOWS");
        mDataList.add("LINUX");
        mDataList.add("...");
    }

    private QuickAdapter adapter = null;

    private void fillAdapter() {
        if (adapter == null) {
            adapter = new QuickAdapter(mContext, mDataList);
            lvShow.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

}
