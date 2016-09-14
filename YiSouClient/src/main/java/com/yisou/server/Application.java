package com.yisou.server;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 程序主入口
 * @author yes
 *
 */
public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	public static ApplicationContext appContext;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) {
		try {
			logger.info("------>>>>>>>>>>正在启动服务器");
			File dir = new ClassPathResource("").getFile();
			logger.info(dir.toString());

			appContext = new ClassPathXmlApplicationContext("app-*.xml");
			Server jettyServer = (Server) appContext.getBean("jettyServer");
			WebAppContext webAppContext = (WebAppContext) jettyServer.getHandler();
			// webAppContext.setClassLoader(appContext.getClassLoader());

			// 使用JSTL
			webAppContext.setClassLoader(new WebAppClassLoader(Application.class.getClassLoader(), webAppContext));

			XmlWebApplicationContext xmlWebAppContext = new XmlWebApplicationContext();
			xmlWebAppContext.setParent(appContext);
			xmlWebAppContext.setConfigLocation("");
			xmlWebAppContext.setServletContext(webAppContext.getServletContext());
			xmlWebAppContext.refresh();
			webAppContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, xmlWebAppContext);
			jettyServer.start();
			logger.info("------>>>>>>服务器启动成功");
		} catch (Exception e) {
			logger.error("主程序出错:", e);
		}

	}

}
