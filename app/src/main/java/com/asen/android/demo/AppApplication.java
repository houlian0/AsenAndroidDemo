package com.asen.android.demo;

import com.asen.android.demo.ui.MainActivity;
import com.asen.android.lib.base.BaseApplication;
import com.asen.android.lib.base.global.AppData;
import com.asen.android.lib.base.global.AppPath;
import com.asen.android.lib.base.tool.util.AppUtil;
import com.asen.android.lib.base.tool.util.FileUtil;

import java.io.File;

/**
 * Android程序入口类，需要在清单文件中进行配置
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 12:47
 */
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // 设置程序主文件夹名称，通过AppPath获得的文件夹路径，会包含此文件夹名称
        AppData.APP_PROJECT = "AsenTest";
        // 设置获取程序文件路径的类型，此处采用默认的类型处理
        AppPath.setType(AppPath.TYPE_DEFAULT);
        // 获取程序主文件夹File
        File projectFile = AppPath.getAppProjectFile(mContext);
        // 主文件夹不存在时，创建主文件夹
        if (!projectFile.exists()) FileUtil.createFolder(projectFile);
    }

    @Override
    protected boolean abnormalExit() { // 不是每次都能成功的重新打开页面，不过此方法在崩溃时确实会被执行
        // 程序崩溃时调用，此处重新打开Demo程序主界面
        AppUtil.exitAndRestart(mContext, MainActivity.class);
        return true; // 返回：false，则以系统默认的方式处理异常；true，则不执行系统默认处理异常的方法
    }

}
