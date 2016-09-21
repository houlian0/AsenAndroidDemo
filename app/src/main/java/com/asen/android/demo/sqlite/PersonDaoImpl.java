package com.asen.android.demo.sqlite;

import android.content.Context;

import com.asen.android.demo.sqlite.bean.PersonInfo;
import com.asen.android.lib.base.core.sqlite.builder.impl.BuilderMaker;
import com.asen.android.lib.base.core.sqlite.dao.CallTransaction;
import com.asen.android.lib.base.core.sqlite.dao.ISqliteDao;
import com.asen.android.lib.base.core.sqlite.dao.impl.SqliteDaoImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao的实现（此处只实现的部分内容，未全部实现）
 *
 * @author Asen
 * @version v1.0
 * @date 2016/9/21 11:27
 */
public class PersonDaoImpl implements PersonDao {

    private ISqliteDao sqliteDao;

    public PersonDaoImpl(Context context) {
        sqliteDao = new SqliteDaoImpl(AsenSqliteOpenHelper.getInstance(context));
    }

    @Override
    public boolean createOrUpdatePerson(PersonInfo info) throws SQLException {
        return sqliteDao.executeCreateOrUpdateBuilder(BuilderMaker.getInstance().createCreateOrUpdateBuilder(info)) != -1;
    }

    @Override
    public boolean createOrUpdatePerson(final List<PersonInfo> infoList) throws SQLException {
        return sqliteDao.executeTransaction(new CallTransaction<Boolean>() {
            @Override
            public Boolean call() throws SQLException {
                for (PersonInfo info : infoList) {
                    long result = sqliteDao.executeCreateOrUpdateBuilder(BuilderMaker.getInstance().createCreateOrUpdateBuilder(info));
                    if (result == -1) return false;
                }
                return true;
            }
        });
    }

    @Override
    public boolean deletePerson(PersonInfo info) throws SQLException {
        return sqliteDao.executeDeleteBuilder(BuilderMaker.getInstance().createDeleteBuilder(info)) != -1;
    }

    @Override
    public boolean deletePersonByName(String name) throws SQLException {
        return sqliteDao.executeDeleteBuilder(BuilderMaker.getInstance().createDeleteBuilder("T_PERSON", "NAME = ?", new String[]{name})) != -1;
    }

    @Override
    public List<PersonInfo> queryAll() throws SQLException {
        return sqliteDao.executeQueryBuilder(PersonInfo.class, BuilderMaker.getInstance().createQueryBuilder("select * from T_PERSON order by REGISTER_DATE desc"));
    }

    @Override
    public PersonInfo queryByName(String name) throws SQLException {
        List<PersonInfo> infoList = sqliteDao.executeQueryBuilder(PersonInfo.class, BuilderMaker.getInstance().createQueryBuilder("T_PERSON", new String[]{"*"}, "NAME = ?", new String[]{name}, null, null, "REGISTER_DATE desc", null));
        return infoList.size() > 0 ? infoList.get(0) : null;
    }

}
