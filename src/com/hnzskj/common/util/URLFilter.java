/*
 * @项目名称: WebGIS
 * @文件名称: URLFilter.java
 * @日期: 2014-8-22 上午10:58:55  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.hnzskj.base.core.bean.User;

/**    
 * 项目名称：WebGIS   <br/>
 * 类名称：URLFilter.java   <br/>
 * 类描述：   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2014-8-22 上午10:58:55   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2014-8-22 上午10:58:55   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class URLFilter extends OncePerRequestFilter {

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
			User user = (User)request.getSession().getAttribute(Constant.SESSION_USER);
			if(null!=user){
				filterChain.doFilter(request, response);
			}else {
				String uri=request.getRequestURI();
				if(notFilterUrl(uri)){
					filterChain.doFilter(request, response);
				}else{
					String path = request.getContextPath();
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
					request.setAttribute("loginError", "登录超时");
					response.sendRedirect(basePath+"index.do");
				}
			}
		
	}
	
	/**
	 * 
	 * 方法描述：不需要过滤的url。<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:30:21<br/>         
	 * @param <br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	protected boolean notFilterUrl(String uri){
		//不需要进行过滤的uri
		String[] uris=new String[]{
				"index.do",
				"login.do"
		};
		
		for (int i = 0; i < uris.length; i++) {
			if(uri.indexOf(uris[i])>-1){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * 方法描述：获取输出信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2014-2-10 下午02:43:23<br/>         
	 * @param <br/>   
	 * @return PrintWriter<br/>   
	 * @version   1.0<br/>
	 */
	public PrintWriter getPrintWriter(HttpServletResponse response) throws IOException{
		PrintWriter out = null;
		response.setContentType("text/html;chartset=utf-8");
		response.setCharacterEncoding("utf-8");
		out = response.getWriter();
		return out;
	}
	
	
}
