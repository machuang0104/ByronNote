package com.byron.library.db.client.dao;

import android.util.Log;

import com.byron.library.App;
import com.byron.library.db.SQLiteDBManager;
import com.byron.library.db.exception.DBNotOpenException;
import com.byron.library.bean.db.TypeVo;

import java.util.List;

/**
 * Dao Example 使用时不通过Dao直接操作，通过对应的Manager来操作数据库
 *
 * @author machuang
 */
public final class TypeDao {

    private final String TAG = "TypeDao";

    public TypeDao() {
        createDatabase();
    }

    /**
     * 创建表
     */
    private synchronized void createDatabase() {
        SQLiteDBManager manager = App.getApp().getSQLiteDatabasePool()
                .getSQLiteDatabase();
        if (manager != null) {
            if (!manager.hasTable(TypeVo.class)) {
                manager.creatTable(TypeVo.class);
            }
        }
        App.getApp().getSQLiteDatabasePool().releaseSQLiteDatabase(manager);
    }

    /**
     * 增
     *
     * @param entity
     * @return
     */
    public synchronized boolean insert(TypeVo entity) {
        SQLiteDBManager manager = App.getApp().getSQLiteDatabasePool()
                .getSQLiteDatabase();
        boolean result = manager.insert(entity);
        App.getApp().getSQLiteDatabasePool().releaseSQLiteDatabase(manager);
        if (!result) {
            Log.e(TAG, "添加失败");
        }

        return result;
    }

    /**
     * 删1
     *
     * @param entity
     * @return
     */
    public synchronized boolean delete(TypeVo entity) {
        SQLiteDBManager manager = App.getApp().getSQLiteDatabasePool()
                .getSQLiteDatabase();
        boolean result = manager.delete(entity);
        App.getApp().getSQLiteDatabasePool().releaseSQLiteDatabase(manager);
        if (!result) {
            Log.e(TAG, "修改失败");
        }

        return result;
    }

    /**
     * 删除2
     *
     * @param where
     * @return
     */
    public synchronized boolean delete(String where) {
        SQLiteDBManager manager = App.getApp().getSQLiteDatabasePool()
                .getSQLiteDatabase();
        boolean result = manager.delete(TypeVo.class, where);
        App.getApp().getSQLiteDatabasePool().releaseSQLiteDatabase(manager);
        if (!result) {
            Log.e(TAG, "修改失败");
        }

        return result;
    }

    public synchronized boolean deleteAll() {
        SQLiteDBManager manager = App.getApp().getSQLiteDatabasePool()
                .getSQLiteDatabase();
        boolean result = manager.dropTable(TypeVo.class);
        App.getApp().getSQLiteDatabasePool().releaseSQLiteDatabase(manager);
        if (!result) {
            Log.e(TAG, "delete table fail");
        }

        return result;
    }

    /**
     * 改
     *
     * @param entity
     * @return
     */
    public synchronized boolean update(TypeVo entity) {
        SQLiteDBManager manager = App.getApp().getSQLiteDatabasePool()
                .getSQLiteDatabase();
        boolean result = manager.update(entity);
        App.getApp().getSQLiteDatabasePool().releaseSQLiteDatabase(manager);
        if (!result) {
            Log.e(TAG, "修改失败");
        }

        return result;
    }

    /**
     * 查1
     *
     * @return
     */
    public synchronized List<TypeVo> findAll() {
        SQLiteDBManager manager = App.getApp().getSQLiteDatabasePool()
                .getSQLiteDatabase();
        List<TypeVo> list = manager.query(TypeVo.class, null, null, null,
                "_createtime ASC", null, null);
        App.getApp().getSQLiteDatabasePool().releaseSQLiteDatabase(manager);

        return list;
    }

    public synchronized void insertUpdate(TypeVo entity) {
        SQLiteDBManager manager = App.getApp().getSQLiteDatabasePool()
                .getSQLiteDatabase();
        manager.InsertUpdate(entity);
        App.getApp().getSQLiteDatabasePool().releaseSQLiteDatabase(manager);
    }

    /**
     * 执行sql
     *
     * @param sql
     * @return
     */
    public synchronized void execute(String sql) {
        SQLiteDBManager manager = App.getApp().getSQLiteDatabasePool()
                .getSQLiteDatabase();
        try {
            manager.execute(sql, null);
        } catch (DBNotOpenException e) {
            e.printStackTrace();
        } finally {
            App.getApp().getSQLiteDatabasePool().releaseSQLiteDatabase(manager);
        }
    }
}