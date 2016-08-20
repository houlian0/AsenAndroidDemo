package com.asen.android.demo.ui.quickadapter;

import android.content.Context;
import android.widget.TextView;

import com.asen.android.lib.base.ui.quick.adapter.QuickHolderBaseAdapter;
import com.asen.android.lib.base.ui.quick.findview.AFindView;

import java.util.List;

/**
 * 快速适配器，通过继承{@link QuickHolderBaseAdapter}来实现，基于list的对象集合
 * 其中有两个泛型。第一个为数据类型对象；第二个为需要自己写的Holder类，用于存放控件
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 16:31
 */
public class QuickAdapter extends QuickHolderBaseAdapter<String, QuickAdapter.Holder> {

    /**
     * 构造函数
     *
     * @param context Android上下文
     * @param data    数据源
     */
    public QuickAdapter(Context context, List<String> data) {
        // 第二个参数：放入对应的布局文件
        super(context, android.R.layout.simple_list_item_1, data);
    }

    @Override
    public Holder getInstance() {
        // 获取Holder对象的实例
        return new Holder();
    }

    @Override
    public void convert(Holder holder, String info, int position) {
        // 向控件上添加数据
        holder.text1.setText(info);
    }

    // 存放控件的类，与AFindView共用，能实现快速FindView
    class Holder {
        @AFindView
        TextView text1;
    }
}
