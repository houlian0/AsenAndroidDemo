package com.asen.android.demo.ui.error;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.asen.android.demo.R;
import com.asen.android.lib.base.global.AppPath;
import com.asen.android.lib.base.module.activity.BaseActivity;
import com.asen.android.lib.base.tool.util.FileUtil;
import com.asen.android.lib.base.ui.quick.findview.AFindView;
import com.asen.android.lib.base.ui.quick.findview.FindViewUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 异常测试页面
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 14:51
 */
public class ErrorTestActivity extends BaseActivity {

    @AFindView
    private Button btnError;
    @AFindView(onClick = "clickOpen")
    private Button btnOpen;
    @AFindView(onClick = "clickClear")
    private Button btnClear;
    @AFindView
    private ListView lvShow;

    private List<File> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataList = new ArrayList<>();

        setContentView(R.layout.ui_error);
        // 快速FindView
        FindViewUtil.getInstance(mContext).findViews(this, this);

        // 注册点击事件的监听
        registerListener();
    }

    // 注册点击事件的监听
    private void registerListener() {
        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickError(view);
            }
        });
    }

    public void clickError(View v) {
        // 制造程序崩溃的条件，即让程序崩溃
        String content = null;
        content.isEmpty();
    }

    public void clickOpen(View v) {
        // 获取程序崩溃时，异常文件保存的文件夹位置
        File errorFile = AppPath.getAppErrorFile(mContext);
        // 获取文件夹中的所有文件
        File[] files = errorFile.listFiles();
        // 填充数据
        mDataList.clear();
        Collections.addAll(mDataList, files);
        // 刷新适配器
        fillAdapter();
    }

    public void clickClear(View v) {
        // 获取程序崩溃时，异常文件保存的文件夹位置
        File errorFile = AppPath.getAppErrorFile(mContext);
        // 删除所有异常文件
        FileUtil.deleteFolder(errorFile);
        // 重新获取列表信息，并刷新适配器
        clickOpen(null);
    }

    private ErrorTestAdapter adapter = null;

    private void fillAdapter() {
        if (null == adapter) {
            adapter = new ErrorTestAdapter(mContext, mDataList);
            lvShow.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

}
