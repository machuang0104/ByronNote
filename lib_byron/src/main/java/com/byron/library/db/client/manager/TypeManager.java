package com.byron.library.db.client.manager;

import com.byron.library.db.client.dao.TypeDao;
import com.byron.library.bean.db.TypeVo;

import java.util.ArrayList;

public class TypeManager {
	private TypeDao mTestDao;

	private static TypeManager mTestManager;

	public static TypeManager getInstance() {
		if (mTestManager == null) {
			mTestManager = new TypeManager();
		}

		return mTestManager;
	}

	private TypeManager() {
		mTestDao = new TypeDao();
	}

	/**
	 * 新增
	 * 
	 * @param entity
	 * @return
	 */
	public void insert(TypeVo entity) {
		mTestDao.insert(entity);
	}

	public void insertUpdate(TypeVo v) {
		mTestDao.insertUpdate(v);
	}

	/**
	 * 执行特定的sql1
	 * 
	 */
	public void setBoxStatus(int id, int status, int isOpen, int isEmpty) {
		String sql = "update table_box set _status = " + status
				+ ", _is_open = " + isOpen + ", _is_empty = " + isEmpty
				+ " where _id = " + id;
		mTestDao.execute(sql);
	}

	/**
	 * 改
	 * 
	 * @param entity
	 * @return
	 */
	public boolean update(TypeVo entity) {
		return mTestDao.update(entity);
	}

	/**
	 * 查询：所有
	 * 
	 * @return
	 */
	public ArrayList<TypeVo> findAll() {
		return (ArrayList<TypeVo>) mTestDao.findAll();
	}

}