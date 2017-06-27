package com.pb.core;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionListener implements HttpSessionListener {
	
	static Log log = LogFactory.getLog(SessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		try{
			HttpSession session = arg0.getSession();
			if(null!=session)
			{
				log.info("start to destroy session,session id:"+session.getId());
				
//				LoginUserVO o = (LoginUserVO)session.getAttribute(Constants.USER_KEY);
//				if(null!=o)
//				{
//					log.info("exist session, name:"+o.getUserLoginId());
//					saveLogoutHstry(o);
//					
//					if(!o.isProxyLogin()) //场务模拟登录时，不需要删除在线表
//					{
//						ICommService commService = (ICommService)WebApplication.ctx.getBean("commonService");
//						commService.userOnlnDel(o.getUserId(), session.getId());
//					}
//					
//					if(!o.isProxyLogin()) //场务模拟登录时，不需要删除ehcache数据
//					{
//						LoginUserVO ehcacheUserVO = new EhcacheSessionUtil().getUserSession(o.getUserId());
//						if(null!=ehcacheUserVO && ehcacheUserVO.getSessionId().equals(session.getId()))
//						{
//							new EhcacheSessionUtil().removeUserSession(o.getUserId());
//						}
//					}
//				}
				
				log.info("clear all session attribute data...");
				clearAllSessionData(session);
				log.info("session destroy success,session id:"+session.getId());
			}
		}catch (Exception e) {
			log.error("session destroy error!",e);
		}
	}
	
	private void clearAllSessionData(HttpSession session)
	{
		Enumeration en =  session.getAttributeNames();
		while(en.hasMoreElements())
		{
			String sessionKey = en.nextElement().toString();
			//System.out.println("!!-"+sessionKey);
			if(!sessionKey.equalsIgnoreCase("WW_TRANS_I18N_LOCALE")){
				session.removeAttribute(sessionKey);
			}
		}
	}

//	private void saveLogoutHstry(LoginUserVO o) {
//		UsrLgnHstry usrLgnHstry = new UsrLgnHstry();
//		usrLgnHstry.setUsrId(o.getUserId());
//		usrLgnHstry.setOprtTpEnum("1");
//		usrLgnHstry.setOprtTmst(DateUtil.getCurrentTime());
//		usrLgnHstry.setLgnIpCd(o.getIpAddress());
//		ICommService service = (ICommService)WebApplication.ctx.getBean("commonService");
//		service.logoutHstry(usrLgnHstry);
//	}

}
