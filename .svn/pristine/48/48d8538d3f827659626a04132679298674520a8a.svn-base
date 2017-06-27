package com.pb.core;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class MyStrutsFilter extends StrutsPrepareAndExecuteFilter {
    private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(MyStrutsFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;
            String path = req.getRequestURI();
            if(path.contains("/restful/")) {
                    log.debug("跳过struts");
                    chain.doFilter(request, response);
            } else {
                    log.debug("进入struts");
                    super.doFilter(request, response, chain);
            }
    }
}