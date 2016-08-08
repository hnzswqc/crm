/*
 * @项目名称: crm
 * @文件名称: LoginSessionListener.java
 * @日期: 2015-10-22 下午04:19:48  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.common.util;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hnzskj.base.core.bean.User;
import com.hnzskj.common.dao.LoginUserDaoImpl;

/**    
 * 项目名称：crm   <br/>
 * 类名称：LoginSessionListener.java   <br/>
 * 类描述：用户登录session   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-10-22 下午04:19:48   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-10-22 下午04:19:48   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class LoginSessionListener implements HttpSessionListener,
HttpSessionAttributeListener {
	
	/**
	 * dao注入
	 */
	private LoginUserDaoImpl  loginUserDaoImpl = new LoginUserDaoImpl();
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("创建sessionId："+arg0.getSession().getId());
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("销毁sessionId："+arg0.getSession().getId());
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		if(arg0.getName().equals(Constant.SESSION_USER)){
			loginUserDaoImpl.addLoginUser((User)arg0.getValue(), arg0.getSession().getId());
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		if(arg0.getName().equals(Constant.SESSION_USER)){
			loginUserDaoImpl.delLoginUser(arg0.getSession().getId());
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("替换sessionId："+arg0.getSession().getId());
		if(arg0.getName().equals(Constant.SESSION_USER)){
			loginUserDaoImpl.delLoginUser(arg0.getSession().getId());
			loginUserDaoImpl.addLoginUser((User)arg0.getValue(), arg0.getSession().getId());
		}
		
	}

	
}
