package com.pb.actions.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.pb.cst.Constants;
import com.pb.json.BaseJson;

import com.common.util.StringUtil;
import com.framework.exceptions.ServiceException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 
 * 文件名稱：CommonBaseAction
 * 描述：Action基类,提供session等公共操作
 * @author Zhang Xiaofeng
 * @create 2012-4-10
 * 汇环
 */
public class BaseAction extends ActionSupport implements RequestAware  {
	
	private static Log log = LogFactory.getLog(BaseAction.class);
	//validation提示
	private String tip;
	
	private Map<String, Object> request;
	
	/**
	 * 取得session当前登录用户的信息
	 * @return TODO
	 * @return LoginUserVO
	 */
	protected String getLoginUserId() throws ServiceException {
		String userId = getHttpRequest().getSession().getAttribute(Constants.USER_Id)+"";
		if(StringUtil.isBlank(userId))
			throw new ServiceException("E-0013");
		else
			return userId;
	}
	
	/**
	 * 获取httprequest请求
	 * Function Name: getHttpRequest
	 * @return
	 * description:
	 * Modification History:
	 */
	protected HttpServletRequest getHttpRequest()
	{
		ActionContext ac = ActionContext.getContext();
		return (HttpServletRequest)ac.get(ServletActionContext.HTTP_REQUEST);
	}
	
	/**
	 * 获取httpresponse请求
	 * Function Name: getHttpRequest
	 * @return
	 * description:
	 * Modification History:
	 */
	protected HttpServletResponse getHttpResponse()
	{
		ActionContext ac = ActionContext.getContext();
		return (HttpServletResponse)ac.get(ServletActionContext.HTTP_RESPONSE);
	}
	
	/**
	 * 保存session数据
	 * @param key
	 * @param value
	 */
	protected void writeSession(String key,Object value) {
		getHttpRequest().getSession().setAttribute(key, value);
	}
	
	/**
	 * 获取session数据
	 * @param key
	 * @param value
	 */
	protected Object getSession(String key) {
		return getHttpRequest().getSession().getAttribute(key);
	}
	
	/**
	 * 保存ServletContext数据
	 * @param key
	 * @param value
	 */
	protected void writeServletContext(String key,Object value) {
		getHttpRequest().getSession().getServletContext().setAttribute(key, value);
	}
	
	/**
	 * 获取ServletContext数据
	 * @param key
	 * @param value
	 */
	protected Object getServletContext(String key) {
		return getHttpRequest().getSession().getServletContext().getAttribute(key);
	}
	
	/**
	 * 
	 * Function Name: deleteSession
	 * @param key
	 * @return
	 * description:移除session指定key数据
	 * Modification History:
	 */
	protected void deleteSession(String key){
		getHttpRequest().getSession().removeAttribute(key);
	}
	
	/**
	 * 获取session Id
	 * @return
	 */
	protected String getSessionId(){
		return getHttpRequest().getSession().getId();
	}
	
	/**
	 * 异常处理，用于页面跳转或重定向时
	 * Function Name :
	 * @param e
	 * @return
	 * Description : 
	 * Modification History :
	 */
	public String processException(Throwable e) {
		String errMsg = "未知异常";
		if(e instanceof ServiceException){
			errMsg =getText(e.getMessage());
		}else if(e instanceof Exception && e.getMessage().startsWith("E-"))	{
			errMsg =getText(e.getMessage());
		}
		
		getHttpRequest().setAttribute("errorContext", errMsg);
		return "business";
	}

	/**
	 * 异常处理，转换异常代码并写入json对象
	 * @param e 异常
	 * @param target 绑定的json对象
	 */
	public void processException(Throwable e,BaseJson target) {
		if(e instanceof ServiceException)
		{
			//封装DB访问异常，转换为“系统忙”输出
			if(!StringUtils.isEmpty(e.getMessage()) && e.getMessage().equals("DB_ACCESS_ERROR"))
			{
				target.setRetcode("E-9998");
				target.setErrorMsg(getText(target.getRetcode()));
				log.error("DB_ACCESS_ERROR", e);
			}
			//正常业务异常
			else
			{
				if(e.getMessage().equals("E-0013")){//为兼容前台旧代码.E-0013也是为session失效异常,统一为SESSION_INVALIDATE
					target.setRetcode("SESSION_INVALIDATE");
					target.setErrorMsg(getText("SESSION_INVALIDATE"));
				}
				else{
					target.setRetcode(e.getMessage());
					target.setErrorMsg(getText(e.getMessage()));
				}
//				if(e.getMessage().equals("E-0001") || e.getMessage().equals("E-0013"))//查询无结果时,不打印明细日志
//					log.error(e.getMessage()+":"+getText(e.getMessage()));
//				else
				log.error(e.getMessage()+":"+getText(e.getMessage()));
			}
		}
		//其他业务异常，防止部分开发未将异常转换为serviceexception
		else if(e instanceof Exception && !StringUtils.isEmpty(e.getMessage()) && e.getMessage().startsWith("E-"))
		{
			target.setRetcode(e.getMessage());
			target.setErrorMsg(getText(e.getMessage()));
			log.error(e.getMessage()+":"+getText(e.getMessage()));
		}
		//其他系统异常，统一转换为系统忙输出
		else
		{
			target.setRetcode("E-9999");
			target.setErrorMsg(getText(target.getRetcode()));
			log.error("未知异常", e);
		}
	}
	
//	/**
//	 * 根据常量类型初始化下拉框的list
//	 * @param types type定义见@Constants 
//	 * @return
//	 */
//	protected List initSelectDataByType(String []types)
//	{
//		List list = new ArrayList();
//		for(int i=0;i<types.length;i++)
//		{
//			List<BaseSelectVO> curr = CacheUtil.getConstList(types[i]);
//			list.add(curr);
//		}
//		return list;
//	}
	
//	/**
//	 * 根据代码移除baseselect中指定值
//	 * Function Name :
//	 * @param list
//	 * @param codes
//	 * Description : 
//	 * Modification History :
//	 */
//	protected void removeBaseSelectByCode(List list,String []codes)
//	{
//		BaseSelectVO removeVO;
//		List removelist = Arrays.asList(codes);
//		for(Iterator it = list.iterator();it.hasNext();){
//			removeVO = (BaseSelectVO)it.next();
//			if(removelist.contains(removeVO.getCode())){
//				it.remove();
//			}
//		}
//	}
	
	/**
	 * 获取客户端IP地址信息
	 * @return
	 */
	protected String getIpAddr(){
		String ip = ServletActionContext.getRequest().getHeader("x-forwarded-for");
		if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
			ip=ServletActionContext.getRequest().getHeader("Proxy-Client-IP");
		}
		if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
			ip=ServletActionContext.getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
			ip=ServletActionContext.getRequest().getRemoteAddr();
			if("0:0:0:0:0:0:0:1".equals(ip)){
				ip="127.0.0.1";
			}
		}
		return ip;
	}
	
	/**
	 * 公用页面跳转
	 * Function Name: pageRedirect
	 * @return
	 */
	public String pageRedirect() {
		String url = getHttpRequest().getParameter("urlType");
		if(!StringUtil.isBlank(url)){
			return url;
		}else{
			return SUCCESS;
		}
	}

	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}
	
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public String getTip() {
		return tip;
	}
	
}
