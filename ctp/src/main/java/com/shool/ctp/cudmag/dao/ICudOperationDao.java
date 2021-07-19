package com.shool.ctp.cudmag.dao;

public interface ICudOperationDao {
	/**
	 * 使用hibernate添加
	 * @param object
	 */
	void addByHibernate(Object object);
	
	/**
	 * 使用hibernate修改
	 * @param object
	 */
	void updateByHibernate(Object object);
	
	/**
	 * 使用hibernate删除
	 * @param object
	 */
	void deleteByHibernate(Object object);
	
	/**
	 * 通过id查询类（Long）
	 * @param id
	 * @return
	 */
	Object getObjectById(Long id,Class<?> clas);
	/**
	 * 通过id查询类（Integer）
	 * @param id
	 * @param clas
	 * @return
	 */
	Object getObjectById(Integer id,Class<?> clas);
}
