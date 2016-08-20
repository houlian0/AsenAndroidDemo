package com.asen.android.demo.ui.afindview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.asen.android.demo.R;
import com.asen.android.lib.base.module.activity.BaseActivity;
import com.asen.android.lib.base.tool.manage.fragment.FragmentManager;
import com.asen.android.lib.base.tool.util.ToastUtil;
import com.asen.android.lib.base.ui.quick.findview.AFindView;
import com.asen.android.lib.base.ui.quick.findview.FindViewUtil;

/**
 * 快速FindView的示例页面
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 14:06
 */
public class AFindViewActivity extends BaseActivity {

    @AFindView
    private TextView tvShowTest; // 直接加AFindView注解获得，注：需要xml中所设的id名称与属性名称一致
    @AFindView(onClick = "clickTest")
    private Button btnShowTest; // 可以为当前控件增加onclick的点击事件，事件触发的方法会调用到当前Activity中的“clickTest”方法，且有且需要一个View作为其参数
    @AFindView(id = R.id.imgShow)
    private ImageView imgShowTest; // 如果属性参数与xml中的id名称不一致时，注解中需要增加id的赋值
    @AFindView(onClick = "clickTwo")
    private Button btnShowClick, btnShowClick2; // 可以同时将多个控件的onclick点击事件，触发到同一个方法上
    @AFindView(onClick = "clickOpenFragment")
    private Button btnFragment;

    private FragmentManager fragmentManager; // 对Fragment的切换封装，在其对应的功能模块里会详细介绍

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_afindview);
        // 调用如下方法，可以快速FindView
        FindViewUtil.getInstance(mContext).findViews(this, this);
        // 初始化一个基于Activity的Fragment管理类
        fragmentManager = FragmentManager.createFragmentManager(this);

        // 为图片控件填充示例图片
        imgShowTest.setImageResource(R.mipmap.ic_launcher);
    }

    // 点击事件触发此方法
    public void clickTest(View v) {
        ToastUtil.showToast(mContext, "测试点击事件");
        tvShowTest.setText("测试点击事件");
    }

    // btnShowClick, btnShowClick2 共用的事件触发方法
    public void clickTwo(View v) {
        switch (v.getId()) {
            case R.id.btnShowClick:
                ToastUtil.showToast(mContext, "测试点击");
                tvShowTest.setText("测试点击");
                break;
            case R.id.btnShowClick2:
                ToastUtil.showToast(mContext, "测试点击2");
                tvShowTest.setText("测试点击2");
                break;
            default:
                break;
        }
    }

    // 打开Fragment
    public void clickOpenFragment(View v) {
        // 替换Fragment，此处为示例代码，每次点击后都重新替换Fragment
        fragmentManager.replace(new AFindViewFragment(), R.id.frameLayout, null);
    }

}
