package com.asen.android.demo.ui.sqlite;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.asen.android.demo.R;
import com.asen.android.demo.sqlite.PersonDao;
import com.asen.android.demo.sqlite.PersonDaoImpl;
import com.asen.android.demo.sqlite.bean.PersonInfo;
import com.asen.android.lib.base.module.activity.BaseActivity;
import com.asen.android.lib.base.tool.util.AppUtil;
import com.asen.android.lib.base.tool.util.ConvertUtil;
import com.asen.android.lib.base.tool.util.ToastUtil;
import com.asen.android.lib.base.ui.quick.findview.AFindView;
import com.asen.android.lib.base.ui.quick.findview.FindViewUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作示例
 *
 * @author Asen
 * @version v1.0
 * @date 2016/9/21 11:51
 */
public class SqliteTestActivity extends BaseActivity {

    @AFindView
    private TextView etName, etAge;
    @AFindView
    private ListView lvShow;
    @AFindView(onClick = "clickAdd")
    private Button btnAdd;
    @AFindView(onClick = "clickDel")
    private Button btnDel;
    @AFindView(onClick = "clickQuery")
    private Button btnQuery;
    @AFindView(onClick = "clickQueryAll")
    private Button btnQueryAll;

    private PersonDao personDao;

    private List<PersonInfo> personInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personInfoList = new ArrayList<>();
        personDao = new PersonDaoImpl(mContext);

        setContentView(R.layout.ui_sqlite_test);
        FindViewUtil.getInstance(mContext).findViews(this, this);

        try {
            // 默认先查询全部
            clickQueryAll(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 新增
    public void clickAdd(View v) throws SQLException {
        String name = etName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showToast(mContext, "请输入姓名");
            return;
        }

        PersonInfo info = personDao.queryByName(name);

        if (info == null) {
            info = new PersonInfo(null, AppUtil.getUUid(), name, ConvertUtil.stringToInt(etAge.getText().toString(), 1), 10.2233, null, new byte[]{1, 1, 1, 12});
        }

        personDao.createOrUpdatePerson(info);
        clickQueryAll(null);
    }

    // 删除
    public void clickDel(View v) throws SQLException {
        if (personInfoList.size() == 0) {
            ToastUtil.showToast(mContext, "请先查询出结果");
            return;
        }
        // 选择列表中的最后一条记录进行删除
        personDao.deletePerson(personInfoList.get(personInfoList.size() - 1));

        if (isQueryAll) {
            clickQueryAll(null);
        } else {
            clickQuery(null);
        }
    }

    private boolean isQueryAll = true;

    public void clickQuery(View v) throws SQLException {
        isQueryAll = false;
        personInfoList.clear();
        PersonInfo personInfo = personDao.queryByName(etName.getText().toString());
        if (personInfo == null) {
            ToastUtil.showToast(mContext, "当前姓名不存在记录");
            return;
        }
        personInfoList.add(personInfo);
        fillAdapter();

        ToastUtil.showToast(mContext, "根据姓名查询成功");
    }

    public void clickQueryAll(View v) throws SQLException {
        isQueryAll = true;
        personInfoList.clear();
        personInfoList.addAll(personDao.queryAll());
        fillAdapter();
        ToastUtil.showToast(mContext, "全部查询成功");
    }

    private SqliteTestAdapter adapter = null;

    private void fillAdapter() {
        if (null == adapter) {
            adapter = new SqliteTestAdapter(mContext, personInfoList);
            lvShow.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

}
