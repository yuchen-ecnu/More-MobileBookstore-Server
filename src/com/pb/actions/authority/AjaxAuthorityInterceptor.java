package com.pb.actions.authority;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import com.common.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.pb.cst.Constants;

/**
 * File name：SessionAuthorityInterceptor
 * Date: 2012-4-25
 * Author: Administrator
 * Description：AJAX请求拦截器
 * Modify History:
 */
public class AjaxAuthorityInterceptor extends AbstractInterceptor {

	private static final Log log = LogFactory.getLog(AjaxAuthorityInterceptor.class);
	
	/**
	 * Interrcept拦截器
	 */
	public String intercept(ActionInvocation invocation){
		
//		long before = System.currentTimeMillis();
//		BaseJson commBaseJsonError = new BaseJson();
//		
//		String reqUrl = invocation.getProxy().getActionName();
//		String method = invocation.getProxy().getMethod();
//		String nameSpace = invocation.getProxy().getNamespace();
//		if (nameSpace.equals("/")) //不包含namepsace的不做校验，比如登录类action，公共服务类action等
//			return invocation.invoke();
//		
//		String action = reqUrl.substring(0,reqUrl.indexOf("_"));
//		
//		ActionContext ctx = ActionContext.getContext();
//		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
//		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
//		
//		String idhash = request.getParameter("userindex_userIdHash");
//		log.info("request id:"+before+",from:"+AppUtil.getClientIpAddr() + ",url:"+reqUrl+",action:"+action+",method:"+ method+",idhash:"+idhash);
//		
//		response.addHeader("Access-Control-Allow-Origin", CacheUtil.getAllowOriginForCometd());
//		
//		response.addHeader("X-UA-Compatible", "IE=8");//强制设置IE兼容性
//		
//		boolean isAjax = isAjaxRequest(request);
//		
//		if (!method.equals("caCheckStep1") && !method.equals("caCheckStep2")){ //CA认证时也走ajax请求，不做拦截
//			try {
//				Object obj = ctx.getSession().get(Constants.USER_KEY);
//				
//				//session丢失处理
//				if(null==obj)
//				{
//					log.warn("用户session为空，session:"+request.getSession().getId());
//					if(isAjax){
//						commBaseJsonError.setRetcode("SESSION_INVALIDATE");
//						dealSessionInvalid(response, commBaseJsonError);
//						return null;
//					}
//					else{
//						return "userinvalid";
//					}
//				}
//				else
//				{
//					LoginUserVO user = (LoginUserVO)obj;
//					//请求中hashid判断
//					if(StringUtil.isEmpty(idhash)||!user.getUserIdHash().equals(idhash)){
//						log.warn("请求hashid为空或非法,hashid from request:"+idhash+",hash id in server:"+user.getUserIdHash()+" session:"+request.getSession().getId()+",userid:"+user.getUserId());
//						if(isAjax){
//							commBaseJsonError.setRetcode("SESSION_INVALIDATE");
//							dealSessionInvalid(response, commBaseJsonError);
//							return null;
//						}
//						else{
//							return "userinvalid";
//						}
//					}
//					
//					//用户在线状态校验，场务端代理登陆时，不校验
//					if(!user.isProxyLogin())
//					{
//						HttpSession session = ServletActionContext.getRequest().getSession();
//						Boolean usrStatus = ((ICommService)WebApplication.ctx.getBean("commonService")).userStatusCheck(user.getUserId(), session.getId());
//						if (usrStatus) {
//							log.info("用户非在线状态，session：" + ServletActionContext.getRequest().getSession().getId()+",userid:"+user.getUserId());
//							session.invalidate();
//							if(isAjax){
//								commBaseJsonError.setRetcode("SESSION_INVALIDATE");
//								dealSessionInvalid(response, commBaseJsonError);
//								return null;
//							}
//							else{
//								return "userinvalid";
//							}
//						}
//					}
//					
//					//场务模拟登录时，禁止部分权限
//					if( user.isProxyLogin() && CWProxyLgnFbdMethod.getMap().containsKey(method) )
//					{
//						log.error("userid:"+user.getUserId()+" name:"+user.getUserName()+" 为场务代理登陆，禁止此类操作");
//						
//						if(isAjax){
//							commBaseJsonError.setRetcode("E-9996");
//							dealSessionInvalid(response, commBaseJsonError);
//							return null;
//						}
//						else{
//							request.setAttribute("errorContext", AppUtil.transLocaleMessage("E-9996", request.getLocale()));
//							return "business";
//						}
//					}
//					
//					Object rsrcObj = CacheUtil.getSysAcsCtrlMap().get(action);
//					List rsrcIdList = (List)rsrcObj;
//					if(null!=rsrcIdList && rsrcIdList.size()>0){
//						//若rsrcid为-1，则暂时不校验
//						if(rsrcIdList.size()==1 && ((Long)rsrcIdList.get(0))==-1L ){
//							log.info("userid:"+user.getUserId()+" name:"+user.getUserName()+" not need to check,allow");
//						}
//						else{
//							for(int i=0;i<rsrcIdList.size();i++)
//							{
//								Long rsrcId = (Long)rsrcIdList.get(i);
//								if(!user.getUsrMenuMap().containsKey(rsrcId)&&!user.getUsrTradeMarketMap().containsKey(rsrcId)){
//									log.error("userid:"+user.getUserId()+" name:"+user.getUserName()+" rsrcId:"+rsrcId+" reject");
//									
//									if(isAjax){
//										commBaseJsonError.setRetcode("E-9997");
//										dealSessionInvalid(response, commBaseJsonError);
//										return null;
//									}
//									else{
//										request.setAttribute("errorContext", AppUtil.transLocaleMessage("E-9997", request.getLocale()));
//										return "business";
//									}
//								}
//							}
//							//所有规则校验通过，允许访问
//							log.info("userid:"+user.getUserId()+" name:"+user.getUserName()+" allow");
//						}
//					}
//					else{
//						log.warn("userid:"+user.getUserId()+" name:"+user.getUserName()+" access undefined service");
//						if(isAjax){
//							commBaseJsonError.setRetcode("E-9997");
//							dealSessionInvalid(response, commBaseJsonError);
//							return null;
//						}
//						else{
//							request.setAttribute("errorContext", AppUtil.transLocaleMessage("E-9997", request.getLocale()));
//							return "business";
//						}
//					}
//				}
//			} catch (Exception e) {
//				if(isAjax){
//					commBaseJsonError.setRetcode("SESSION_INVALIDATE");
//					dealSessionInvalid(response, commBaseJsonError);
//					return null;
//				}
//				else{
//					return "error";
//				}
//			}
//		}
//		String ret = invocation.invoke();
//		
//		long interval = System.currentTimeMillis()-before;
//		if(interval>4000)
//			log.warn("request "+before+" end,cost:"+interval+"ms");
//		else
//			log.info("request "+before+" end,cost:"+interval+"ms");
//		
//		return ret;
		
		
		
		String result="";
		String reqUrl = invocation.getProxy().getActionName();
		String method = invocation.getProxy().getMethod();
		String action = reqUrl.substring(0,reqUrl.indexOf("_"));
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession();
		// 判断session是否存在及session中的user信息是否存在，如果存在不用拦截
		String userId = session.getAttribute(Constants.USER_Id)+"";
		try {
			if (!StringUtil.isBlank(userId)&&!"null".equals(userId)||"loginAction".equals(action)) {
				result= invocation.invoke();
			} else {
				if (isAjaxRequest(request)) {
					if (action.contains("loginAction")) {
						result= invocation.invoke();
					}
					response.setHeader("sessionstatus", "timeout");
					response.sendError(518, "session timeout.");
					return null;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean isAjaxRequest(HttpServletRequest request){
		String header = request.getHeader("X-Requested-With");
		if(header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
//	
//	private void dealSessionInvalid(HttpServletResponse response,BaseJson commBaseJsonError){
//		commBaseJsonError.setErrorMsg(AppUtil.transLocaleMessage(commBaseJsonError.getRetcode(), null));
//		JSONObject jsonObject = JSONObject.fromObject(commBaseJsonError);
//		PrintWriter writer;
//		try {
//			writer = response.getWriter();
//			writer.print(jsonObject);
//			writer.flush();
//			writer.close();
//		} catch (IOException e) {
//			log.error(e);
//		}
//	}
//	
//	private void addParamLog(ActionInvocation invocation){
//		log.info("=============param print===============");
//		try{
//			Map param = invocation.getInvocationContext().getParameters();
//			Iterator iter = param.keySet().iterator();
//			while(iter.hasNext()){
//				Object key = iter.next();
//				Object value = param.get(key);
//				
//				log.info("key:"+key+" value:"+((String[])value)[0]);
//			}
//		}catch (Exception e) {
//			log.error("ajax param print error", e);
//		}
//		log.info("=============param print end===============");
//	}

}
