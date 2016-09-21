package com.asen.android.demo.ui.sqlite;

import android.content.Context;
import android.widget.TextView;

import com.asen.android.demo.R;
import com.asen.android.demo.sqlite.bean.PersonInfo;
import com.asen.android.lib.base.tool.util.HexUtil;
import com.asen.android.lib.base.ui.quick.adapter.QuickHolderBaseAdapter;
import com.asen.android.lib.base.ui.quick.findview.AFindView;

import java.util.List;

/**
 * 快速适配器
 *
 * @author Asen
 * @version v1.0
 * @date 2016/9/21 14:38
 */
public class SqliteTestAdapter extends QuickHolderBaseAdapter<PersonInfo, SqliteTestAdapter.Holder> {

    /**
     * 构造函数
     *
     * @param context Android上下文
     * @param data    数据源
     */
    public SqliteTestAdapter(Context context, List<PersonInfo> data) {
        super(context, R.layout.ui_quickadapter_item, data);
    }

    @Override
    public Holder getInstance() {
        return new Holder();
    }

    @Override
    public void convert(Holder holder, PersonInfo info, int position) {
        holder.tvShow.setText("【姓名】" + info.getName() + "，【年龄】" + info.getAge() + "，【身高】" + info.getHeight() + "，【BOLB】" + HexUtil.encodeHexStr(info.getPictures()));
    }

    // 存放控件的类，与AFindView共用，能实现快速FindView
    class Holder {
        @AFindView
        TextView tvShow;
    }

}
