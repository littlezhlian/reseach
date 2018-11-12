package com.changan.lib_baseutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.changan.lib_baseutil.greendao.DaoMaster;
import com.changan.lib_baseutil.greendao.DaoSession;

import java.util.List;

/**
 * Created by 64553 on 2018-11-12.
 * Power By ChangnAutoMobile RCLink Team
 */

public class GreenDaoUtil {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private volatile static GreenDaoUtil sGreenDaoUtil;

    private GreenDaoUtil() {
    }
    public static final synchronized GreenDaoUtil getInstance() {
        if (null == sGreenDaoUtil) {
            synchronized (GreenDaoUtil.class) {
                if (null == sGreenDaoUtil) {
                    sGreenDaoUtil = new GreenDaoUtil();
                }
            }
        }
        return sGreenDaoUtil;
    }

    public void init(Context context) {

        mHelper = new DaoMaster.DevOpenHelper(context, "test-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

    }


    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public final void insertData(TestBean bean){
        mDaoSession.getTestBeanDao().insert(bean);

    }

    public final void deleteData(Long id){
        mDaoSession.getTestBeanDao().deleteByKey(id);
    }

    public final List<TestBean>getAll(){
        return mDaoSession.getTestBeanDao().loadAll();
    }

}
