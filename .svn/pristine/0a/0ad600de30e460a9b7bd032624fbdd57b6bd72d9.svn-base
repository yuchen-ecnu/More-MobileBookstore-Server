/**
 * File name:      ClearFieldErrorInterceptor.java
 * Date:           2011-9-5
 * Description:    // 用于详细说明此程序文件完成的主要功
 *                 // 能与其他模块或函数的接口，输出值、
 *                 // 取值范围、含义及参数间的关系
 * Modify History:     Date             Programmer       Notes
 **********************************************************************
 */
package com.framework.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Created on 2011-9-5
 * <p>Title:       汇环_[子系统统名]_[模块名]/p>
 * <p>Description: [描述该类概要功能介绍]</p>
 * @version        1.0
 */
public class ClearFieldErrorInterceptor extends AbstractInterceptor{
  public String intercept(ActionInvocation invocation) 
  throws Exception
{
    ActionSupport actionSupport = (ActionSupport)invocation.getAction();      
    actionSupport.clearErrorsAndMessages();
    String resultCode = invocation.invoke();
    return resultCode;
}
}