package com.asen.android.demo.sqlite;

import com.asen.android.demo.sqlite.bean.PersonInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao接口
 *
 * @author Asen
 * @version v1.0
 * @date 2016/9/21 11:27
 */
public interface PersonDao {

    /**
     * 新增或插入数据
     *
     * @param info 数据
     * @return 成功与否
     */
    public boolean createOrUpdatePerson(PersonInfo info) throws SQLException;

    /**
     * 新增或插入多条数据（启用事务）
     *
     * @param infoList 数据
     * @return 成功与否
     */
    public boolean createOrUpdatePerson(List<PersonInfo> infoList) throws SQLException;

    /**
     * 删除单条记录
     *
     * @param info 数据
     * @return 成功与否
     */
    public boolean deletePerson(PersonInfo info) throws SQLException;

    /**
     * 根据姓名删除记录
     *
     * @param name 姓名
     * @return 成功与否
     */
    public boolean deletePersonByName(String name) throws SQLException;

    /**
     * 查询所有记录
     *
     * @return 数据集合
     */
    public List<PersonInfo> queryAll() throws SQLException;

    /**
     * 根据姓名查询记录
     *
     * @param name 姓名
     * @return 数据集合
     */
    public PersonInfo queryByName(String name) throws SQLException;

}
