package com.asen.android.demo.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asen.android.demo.R;
import com.asen.android.lib.base.ui.quick.findview.AFindView;
import com.asen.android.lib.base.ui.quick.findview.FindViewUtil;

import java.util.List;

/**
 * Demo程序主界面的适配器
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 13:46
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Holder> {

    private Context mContext;

    private List<MainItem> mDataList;

    private int mResId;

    private OnMainItemClickListener onMainItemClickListener;

    public MainAdapter(Context context, List<MainItem> dataList, OnMainItemClickListener onMainItemClickListener) {
        mContext = context;
        mDataList = dataList;
        mResId = R.layout.main_item;
        this.onMainItemClickListener = onMainItemClickListener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mResId, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final MainItem mainItem = mDataList.get(position);
        holder.tvShow.setText(mainItem.getName());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMainItemClickListener != null) {
                    onMainItemClickListener.onClick(mainItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @AFindView
        ImageView imgShow;
        @AFindView
        TextView tvShow;
        @AFindView
        RelativeLayout layoutItem;

        public Holder(View itemView) {
            super(itemView);
            // 快速FindView，可在示例Demo中看到更详细的介绍
            FindViewUtil.getInstance(mContext).findViews(itemView, this);
        }
    }

    /**
     * 自定义点击事件接口
     */
    public interface OnMainItemClickListener {

        /**
         * 点击事件执行的方法
         */
        public void onClick(MainItem mainItem);
    }

}
