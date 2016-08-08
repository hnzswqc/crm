/*
 * @项目名称: oaapi
 * @文件名称: InitServlet.java
 * @日期: 2015-2-2 下午02:46:15  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.common.util;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**    
 * 项目名称：oaapi   <br/>
 * 类名称：InitServlet.java   <br/>
 * 类描述：系统初始化   <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2015-2-2 下午02:46:15   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2015-2-2 下午02:46:15   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8572731646003606239L;
	
	/**
	 * 日志
	 */
	Logger log = Logger.getLogger(InitServlet.class);


	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		String strPrefix = getServletContext().getRealPath("/"); 
		System.setProperty("system.root", strPrefix);
		String strFile = getInitParameter("log4j");
		if (strFile != null)
			PropertyConfigurator
					.configure(strPrefix + File.separator + strFile);
		else {
			System.exit(-1);
		}
		log.info("定期清除日志信息开始......");
		//用于处理定期删除日志文件
		String path = this.getServletContext().getRealPath("WEB-INF\\logs");
		FileDeleter deleter = new FileDeleter();
		deleter.setTargetDirectory(path);
		deleter.start();
		super.init();
	}
	
	

	
}
