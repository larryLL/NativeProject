package com.shool.ctp.cudmag.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shool.ctp.cudmag.dao.ICudOperationDao;
import com.shool.ctp.cudmag.service.ICudOperationService;
/**
 * 增删改业务层接口实现
 * @author luolin
 * @date 2019年1月14日
 */
@Service
public class CudOperationServiceImpl implements ICudOperationService {

	@Resource
	private ICudOperationDao cudOperationDaoImpl;
	
	@Override
	public void addByHibernate(Object object) {
		// TODO Auto-generated method stub
		cudOperationDaoImpl.addByHibernate(object);
	}

	@Override
	public void updateByHibernate(Object object) {
		// TODO Auto-generated method stub
		cudOperationDaoImpl.updateByHibernate(object);
	}

	@Override
	public void deleteByHibernate(Object object) {
		// TODO Auto-generated method stub
		cudOperationDaoImpl.deleteByHibernate(object);
	}

	@Override
	public Object getObjectById(Long id, Class<?> clas) {
		// TODO Auto-generated method stub
		return cudOperationDaoImpl.getObjectById(id, clas);
	}

	@Override
	public Object getObjectById(Integer id, Class<?> clas) {
		// TODO Auto-generated method stub
		return cudOperationDaoImpl.getObjectById(id, clas);
	}

}
