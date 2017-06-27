package com.pb.core;

import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.framework.exceptions.ServiceException;
import com.pb.services.common.CommService;


/**
 * 
 * File name：SessionCheckAop
 * Date: 2012-4-25
 * Author: Administrator
 * Description：service中任意方法执行前检测session是否失效
 * Modify History:
 */
public class SessionCheckAop {

	private static final Logger log = LoggerFactory.getLogger(SessionCheckAop.class);

	private CommService commService;

	public CommService getCommService() {
		return commService;
	}

	public void setCommService(CommService commService) {
		this.commService = commService;
	}

	public void callCheck(JoinPoint j) throws ServiceException {
		String name = j.getSignature().getName();
		try {
			String client = RemoteServer.getClientHost();
			List list = (List)WebApplication.ctx.getBean("rmiPolicy");
			if( VersionReaderUtil.isProdMode() && (null==client||!list.contains(client)) ){
				log.error("client from "+client +",request "+name+",reject");
				throw new ServiceException("非法访问");
			}
			else
			{
				if(!name.equals("setEhCache"))
					log.info("client from "+client +",request "+name+",allow");
			}
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}
	}

}
