package com.asen.android.demo.ui.afindview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.asen.android.demo.R;
import com.asen.android.lib.base.module.activity.BaseFragment;
import com.asen.android.lib.base.tool.util.ToastUtil;
import com.asen.android.lib.base.ui.quick.findview.AFindView;
import com.asen.android.lib.base.ui.quick.findview.FindViewUtil;

/**
 * Fragment中使用快速FindView
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 14:43
 */
public class AFindViewFragment extends BaseFragment {

    @AFindView
    private TextView tvShowTest; // 直接加AFindView注解获得，注：需要xml中所设的id名称与属性名称一致
    @AFindView(onClick = "clickTest")
    private Button btnShowTest; // 可以为当前控件增加onclick的点击事件，事件触发的方法会调用到当前Activity中的“clickTest”方法，且有且需要一个View作为其参数

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_afindview_fragment, null);
        // 此处为在Fragment中快速FindView的关键，区别在于第一个参数为view（PS：只要有view的地方，都能用此方法快速FindView）
        FindViewUtil.getInstance(mContext).findViews(view, this);
        return view;
    }

    // 点击事件触发此方法
    public void clickTest(View v) {
        ToastUtil.showToast(mContext, "测试点击事件");
        tvShowTest.setText("测试点击事件");
    }

}
