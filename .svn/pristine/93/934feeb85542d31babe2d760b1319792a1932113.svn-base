package com.pb.actions.authority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.pb.services.common.CommService;

/**
 * 
 * File name：SessionAuthorityInterceptor
 * Date: 2012-4-25
 * Author: Administrator
 * Description：Session拦截器，用户切换菜单时拦截
 * Modify History:
 */
public class SessionAuthorityInterceptor extends AbstractInterceptor {

	private static final Logger log = LoggerFactory.getLogger(SessionAuthorityInterceptor.class);

	private CommService commService;

	public CommService getCommService() {
		return commService;
	}

	public void setCommService(CommService commService) {
		this.commService = commService;
	}

	/**
	 * Interrcept拦截器
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		// 创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		// 获取HttpSession中的user判断用户是否有效
//		try {
//			LoginUserVO user = (LoginUserVO) ctx.getSession().get(Constants.USER_KEY);
//			if (SysInfo.getSingleUser() == SINGLE_USR.SINGLE) { //控制唯一性登陆，默认打开
//				if (user != null) {
//					//用户访问控制，防止用户直接访问未经授权的菜单
//					String actionName = invocation.getProxy().getActionName();
//					if(actionName.equals("sysPage_goPage"))
//					{
//						Map map = ServletActionContext.getRequest().getParameterMap();
//						if(map.containsKey("sysUrl"))
//						{
//							String sysUrl = ((String[])map.get("sysUrl"))[0];
//							log.info("user access menu:"+actionName+" "+sysUrl);
//						}
//					}
//					
//					if(!user.isProxyLogin()) //场务端代理登陆时，不校验session有效性
//					{
//						HttpSession session = ServletActionContext.getRequest().getSession();
//						Boolean usrStatus = commService.userStatusCheck(user.getUserId(), session.getId());
//						if (usrStatus) {
//							//用户被踢出
//							//log.info("用户被踢出:" + ServletActionContext.getRequest().getSession().getId());
//							session.invalidate();
//							return "userinvalid";
//						}
//					}
//				} else {
//					//用户信息不存在
//					//log.info("用户信息不存在:" + ServletActionContext.getRequest().getSession().getId());
//					ServletActionContext.getRequest().getSession().invalidate();
//					return "userinvalid";
//				}
//			}
//		} catch (Exception e) {
//			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
//			actionSupport.addActionError(actionSupport.getText("E-0013"));
//			return "userinvalid";// Action.LOGIN;
//		}
		
		return invocation.invoke();

	}
}
