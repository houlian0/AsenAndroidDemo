package com.asen.android.demo.ui.error;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.asen.android.lib.base.module.activity.BaseActivity;
import com.asen.android.lib.base.tool.util.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * 异常文件打开页面
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 15:40
 */
public class ErrorReadFileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(mContext);
        TextView textView = new TextView(mContext);
        scrollView.addView(textView);
        setContentView(scrollView);

        try {
            String file = FileUtil.readTxtFile((File) getIntent().getSerializableExtra("file"));
            textView.setText(file);
        } catch (IOException e) {
            e.printStackTrace();
            textView.setText(e.getMessage());
            textView.setTextColor(Color.RED);
        }
    }
}
