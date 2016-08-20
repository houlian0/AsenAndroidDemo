package com.asen.android.demo.ui.error;

import android.content.Context;
import android.widget.TextView;

import com.asen.android.demo.R;
import com.asen.android.lib.base.ui.quick.adapter.QuickHolderBaseAdapter;
import com.asen.android.lib.base.ui.quick.findview.AFindView;

import java.io.File;
import java.util.List;

/**
 * 异常文件的列表适配器
 * 此处用到了基于BaseAdapter的快速适配器，在快速适配器部分会详细介绍
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 15:22
 */
public class ErrorTestAdapter extends QuickHolderBaseAdapter<File, ErrorTestAdapter.Holder> {

    /**
     * 构造函数
     *
     * @param context Android上下文
     * @param data    数据源
     */
    public ErrorTestAdapter(Context context, List<File> data) {
        super(context, R.layout.ui_error_item, data);
    }

    @Override
    public Holder getInstance() {
        return new Holder();
    }

    @Override
    public void convert(Holder holder, File info, int position) {
        holder.tvShow.setText(info.getName());
    }

    class Holder {
        @AFindView
        TextView tvShow;
    }
}
