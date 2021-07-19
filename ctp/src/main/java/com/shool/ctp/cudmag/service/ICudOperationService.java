package com.shool.ctp.cudmag.service;
/**
 * 增删改操作业务接口
 * @author luolin
 * @date 2019年1月14日
 */
public interface ICudOperationService {
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
