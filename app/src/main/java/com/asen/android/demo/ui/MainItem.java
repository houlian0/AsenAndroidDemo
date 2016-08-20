package com.asen.android.demo.ui;

/**
 * 主页面布局内容对象
 *
 * @author Asen
 * @version v1.0
 * @date 2016/8/20 13:45
 */
public class MainItem {

    private String name; // 功能名称

    private Class<?> cls; // Activity类

    public MainItem(String name, Class<?> cls) {
        this.name = name;
        this.cls = cls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

}
