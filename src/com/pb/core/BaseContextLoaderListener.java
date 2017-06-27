package com.pb.core;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletContextEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import agent.core.rmi.impl.RmiBeans;

public class BaseContextLoaderListener extends ContextLoaderListener {

	static Log log = LogFactory.getLog(BaseContextLoaderListener.class);
	private static RmiBeans rmiBeans = null;
	
	public void contextInitialized(ServletContextEvent event) {
		log.info("PTRD Server ready to starting...");
		log.info("prepare to init all config files...");
		WebApplication.contextPath = event.getServletContext().getRealPath("");
		log.info("server run path:"+WebApplication.contextPath);
		ContextLoader loader = new ContextLoader();
		WebApplicationContext wc = loader.initWebApplicationContext(event.getServletContext());
		WebApplication.ctx = wc;
		WebApplication.sctx = event.getServletContext();
		log.info("PTRD Web Server start success!");
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		WebApplication.isDestroy = true;
	}
}