package com.pb.services.common;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.framework.exceptions.ServiceException;

import com.pb.daos.BaseDAO;


@Service(value="commService")
public class CommService{

	private Log log = LogFactory.getLog(CommService.class);

	@Resource
	protected BaseDAO baseDAO;

	
	public <T> T getEntity(String id, Class<T> cls) throws ServiceException {
		// TODO Auto-generated method stub
		T t=baseDAO.findById(Long.parseLong(id), cls);
		return t;
	}
}
