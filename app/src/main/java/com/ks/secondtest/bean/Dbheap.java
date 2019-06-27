package com.ks.secondtest.bean;

import com.ks.secondtest.dao.BeanDao;
import com.ks.secondtest.dao.DaoMaster;
import com.ks.secondtest.dao.DaoSession;

import java.util.List;

/**
 * Created by F0519 on 2019/6/26.
 */

public class Dbheap {
    private static Dbheap dbheap;
    private final BeanDao mBeanDao;

    private Dbheap() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(App.getApp(), "ss.db");
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        DaoSession session = master.newSession();
        mBeanDao = session.getBeanDao();
    }

    public static Dbheap getDbheap() {
        if (dbheap == null) {
            synchronized (Dbheap.class) {
                if (dbheap == null) {
                    dbheap=new Dbheap();
                }
            }
        }
        return dbheap;
    }
    public void insert(Bean bean){
        if(has(bean)){
            return;
        }
        mBeanDao.insertOrReplaceInTx(bean);
    }
    public void delete(Bean bean){
        if(has(bean)){
        mBeanDao.delete(bean);
        }
    }
    public boolean has(Bean bean){
        List<Bean> list = mBeanDao.queryBuilder().where(BeanDao.Properties.Title.eq(bean.getTitle())).list();
        if(list!=null&&list.size()>0){
            return true;
        }else {
            return false;
        }
    }
    public List<Bean> queryall(){
        List<Bean> list = mBeanDao.queryBuilder().list();
        return list;
    }
    public Bean queryone(Bean bean){
        Bean bean1 = mBeanDao.queryBuilder().unique();
       return bean1;
    }
}
