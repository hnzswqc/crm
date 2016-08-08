/*
 * @项目名称: roeeeapi
 * @文件名称: BaseController.java
 * @日期: 2014-12-1 下午02:03:49  
 * @版权: 2014 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.common.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Logs;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.dao.impl.LogsDaoImpl;

/**    
 * 项目名称：roeeeapi   <br/>
 * 类名称：BaseController.java   <br/>
 * 类描述：公共业务控制层   <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2014-12-1 下午02:03:49   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2014-12-1 下午02:03:49   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class BaseController{
	
	/**
	 * 日志
	 */
	private Logger log = Logger.getLogger(BaseController.class);

	
	/**
	 * 正常状态，且有数据返回。
	 */
	public static String RESULT_STATE_OK="OK";
	
	/**
	 * 正常状态，但无数据返回
	 */
	public static String RESULT_STATE_NULL="NULL";
	
	/**
	 * 返回失败
	 */
	public static String RESULT_STATE_ERROR="ERROR";
	
	/**
	 * 返回json类型
	 */
	public static String RETURN_TYPE_JSON="JSON";
	
	/**
	 * 返回文本类型
	 */
	public static String RETURN_TYPE_TEXT="TEXT";
	
	/**
	 * 系统日志
	 */
	public static Integer LOG_TYPE_SYSTEM = 1;
	
	/**
	 * 错误日志
	 */
	public static Integer LOG_TYPE_ERROR = 2;
	
	/**
	 * 操作日志
	 */
	public static Integer LOG_TYPE_OPERATE = 3; 
	
	/**
	 * 执行结果。成功
	 */
	public static Integer LOG_STATE_SUCCESS=1;
	
	/**
	 * 执行结果。失败
	 */
	public static Integer LOG_STATE_ERROR=2;

	
	/**
	 * 
	 * 方法描述：保存session信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午10:20:51<br/>         
	 * @param <br/>   
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	public void setSession(String key,Object object,HttpServletRequest request){
		request.getSession().setAttribute(key, object);
	}
	
	/**
	 * 
	 * 方法描述：移除session<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-1 上午10:13:30<br/>         
	 * @param <br/>   
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	public void removeSession(String key,HttpServletRequest request){
		request.getSession().removeAttribute(key);
	}
	
	/**
	 * 
	 * 方法描述：获取用户信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午10:21:55<br/>         
	 * @param <br/>   
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	public User getUserFromSession(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(MfConstant.SESSION_USER);
		return user;
	}
	/**
	 * 
	 * 方法描述：获取用户信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午10:21:55<br/>         
	 * @param <br/>   
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	public Object getFromSession(String key,HttpServletRequest request){
		return request.getSession().getAttribute(key);
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
	public PrintWriter getPrintWriter(HttpServletResponse response){
		PrintWriter out = null;
		response.setContentType("text/html;chartset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	
	/**
	 * 
	 * 方法描述：获取输出信息,以json形式<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2014-2-10 下午02:43:23<br/>         
	 * @param <br/>   
	 * @return PrintWriter<br/>   
	 * @version   1.0<br/>
	 */
	public PrintWriter getPrintJsonWriter(HttpServletResponse response){
		PrintWriter out = null;
		response.setContentType("text/plain;chartset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	
	/**
	 * 
	 * 方法描述：输出json字符窜<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 下午03:25:08<br/>         
	 * @param jsonBean 输出的json字符串<br/>   
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	public void writeToJson(JsonBean jsonBean,HttpServletResponse response){
		PrintWriter out = getPrintJsonWriter(response);
		String json;
		try {
			json = JSONUtil.toJSONString(jsonBean, true);
			log.info("查询数据："+json);
			out.print(json);
			out.flush();
			out.close();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 
	 * 方法描述：输出字符窜<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 下午03:25:08<br/>         
	 * @param jsonBean 输出的json字符串<br/>   
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	public void writeToStr(String str,HttpServletResponse response){
		PrintWriter out = getPrintJsonWriter(response);
		log.info("查询数据："+str);
		out.print(str);
		out.flush();
		out.close();
	}
	
	
	
	/**
	 * 
	 * 方法描述：输出html字符窜<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 下午03:25:08<br/>         
	 * @param jsonBean 输出的json字符串<br/>   
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	public void writeToHtml(JsonBean jsonBean,HttpServletResponse response){
		PrintWriter out = getPrintWriter(response);
		try {
			String json = JSONUtil.toJSONString(jsonBean, true);
			log.info("查询数据："+json);
			out.print(json);
			out.flush();
			out.close();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法描述：获得客户端真实的IP地址,用户在使用代理时也可获得真实IP地址<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-17 下午04:48:48<br/>         
	 * @return	当前用户的IP地址
	 * @version   1.0
	 */
	public String getIpAddr(HttpServletRequest request) {		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 
	 * 方法描述：<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 下午06:59:02<br/>         
	 * @param logType 日志类别<br/>
	 * @param logTitle 日志标题<br/>
	 * @param logContent 日志内容<br/>
	 * @param logState 执行状态<br/>
	 * @param logUserUuid 执行人uuid<br/>   
	 * @param logUserName 执行人名称<br/>      
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	public void addLogs(Integer logType, String logTitle,
			String logContent, Integer logState,HttpServletRequest request){
			User user = getUserFromSession(request);//用户信息
			Logs logs = new Logs(logType, logTitle, logContent, logState, user.getUserUuid(),user.getUserName(),getIpAddr(request));
			new LogsDaoImpl().addLogs(logs);
	}
	
	
	/**
	 * 
	 * 方法描述：<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 下午06:59:02<br/>         
	 * @param logType 日志类别<br/>
	 * @param logTitle 日志标题<br/>
	 * @param logContent 日志内容<br/>
	 * @param logState 执行状态<br/>
	 * @param logUserUuid 执行人uuid<br/>   
	 * @param logUserName 执行人名称<br/>      
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	public void addLogs(Integer logType, String logTitle,
			String logContent, Integer logState){
			Logs logs = new Logs(logType, logTitle, logContent, logState, null,null,"127.0.0.1");
			new LogsDaoImpl().addLogs(logs);
	}
	
}
