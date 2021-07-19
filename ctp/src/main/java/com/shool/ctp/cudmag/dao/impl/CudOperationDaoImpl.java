package com.shool.ctp.cudmag.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.shool.ctp.cudmag.dao.ICudOperationDao;

/**
 * 增删改接口实现类
 * @author luolin
 * @date 2019年1月14日
 */
@Repository
public class CudOperationDaoImpl implements ICudOperationDao {

	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void addByHibernate(Object object) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(object);
	}

	@Override
	public void updateByHibernate(Object object) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(object);
	}

	@Override
	public void deleteByHibernate(Object object) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(object);
	}

	@Override
	public Object getObjectById(Long id,Class<?> clas) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(clas, id);
	}

	@Override
	public Object getObjectById(Integer id, Class<?> clas) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(clas, id);
	}
	
}
