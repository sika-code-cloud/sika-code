package com.sika.code.basic.listener;

import com.sika.code.common.log.util.LogUtil;
import com.sika.code.common.thread.factory.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BaseServletContextListener implements ServletContextListener {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LogUtil.info("执行contextInitialize方法", sce.getServletContext().getClass(), logger);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LogUtil.info("执行contextDestroyed方法", sce.getServletContext().getClass(), logger);
		Executors.shutDownAllExecutorService();
	}

}
