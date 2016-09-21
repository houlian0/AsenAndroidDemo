package com.asen.android.demo.sqlite.bean;

import com.asen.android.lib.base.core.sqlite.field.AField;
import com.asen.android.lib.base.core.sqlite.field.DataType;
import com.asen.android.lib.base.core.sqlite.field.DefaultType;
import com.asen.android.lib.base.core.sqlite.table.ATable;
import com.asen.android.lib.base.tool.util.DateUtil;

import java.util.Date;

/**
 * 测试数据库操作的实例对象（快速操作sqlite数据库，优点：支持多主键、支持不含空构造函数、更好的支持sql语句的操作等等）
 *
 * @author Asen
 * @version v1.0
 * @date 2016/9/21 10:35
 */
@ATable(tableName = "T_PERSON")
public class PersonInfo {

    @AField(fieldName = "ID", id = true, canBeNull = false, defaultType = DefaultType.SYS_UUID)
    private String id; // 唯一编码

    @AField(fieldName = "SECOND_ID", id = true, canBeNull = false)
    private String secondId; // 第二个主键（测试双主键）

    @AField(fieldName = "NAME", length = 20)
    private String name; // 姓名，支持设置字段长度上限，超过上限的字段内容会被砍掉

    @AField(fieldName = "AGE", defaultValue = "1")
    private int age; // 年龄，支持设置默认值

    @AField(fieldName = "HEIGHT", dataType = DataType.NUMBER_FORM, form = "0.00")
    private double height; // 身高，支持格式化之后保存数据库

    @AField(fieldName = "REGISTER_DATE", dataType = DataType.DATE_STRING, form = DateUtil.dateFormatYMDHMS, defaultType = DefaultType.SYS_DATE)
    private Date registerDate; // 注册时间

    @AField(fieldName = "PICTURES", dataType = DataType.DEFAULT)
    private byte[] pictures; // 头像图片的字节数据（测试存入blob字段）

    public PersonInfo(String id, String secondId, String name, int age, double height, Date registerDate, byte[] pictures) {
        this.id = id;
        this.secondId = secondId;
        this.name = name;
        this.age = age;
        this.height = height;
        this.registerDate = registerDate;
        this.pictures = pictures;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecondId() {
        return secondId;
    }

    public void setSecondId(String secondId) {
        this.secondId = secondId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public byte[] getPictures() {
        return pictures;
    }

    public void setPictures(byte[] pictures) {
        this.pictures = pictures;
    }

}
