package com.easy.cloud.core.basic.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.common.thread.factory.EcExecutors;

@WebListener
public class EcServletContextListener implements ServletContextListener{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		EcLogUtils.info("执行contextInitialize方法", sce.getServletContext().getClass(), logger);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		EcLogUtils.info("执行contextDestroyed方法", sce.getServletContext().getClass(), logger);
		EcExecutors.shutDownAllExecutorService();
	}

}
