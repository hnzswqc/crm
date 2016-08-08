/*
 * @项目名称: crm
 * @文件名称: LogsController.java
 * @日期: 2015-8-12 下午02:23:05  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Logs;
import com.hnzskj.base.core.service.ILogsService;
import com.hnzskj.base.core.service.IOperateService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：LogsController.java   <br/>
 * 类描述： 日志信息管理  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-12 下午02:23:05   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-12 下午02:23:05   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="logs")
public class LogsController extends MfBaseController {
	
	/**
	 * 登录日志系统
	 */
	public static String LOG_LOG_LOGIN="登录日志系统";
	
	
	/**
	 * 业务注入
	 */
	@Autowired
	private ILogsService logsService = null;
	
	/**
	 * 操作注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	
	/**
	 * 
	 * 方法描述：日志管理模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="log")
	public ModelAndView log(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/base/logs/index");
		addLogs(LOG_TYPE_OPERATE, LOG_LOG_LOGIN, "登录日志系统", LOG_STATE_SUCCESS,request);
		modelAndView.addObject("LOG_TYPE_SYSTEM", LOG_TYPE_SYSTEM);
		modelAndView.addObject("LOG_TYPE_ERROR", LOG_TYPE_ERROR);
		modelAndView.addObject("LOG_TYPE_OPERATE", LOG_TYPE_OPERATE);
		modelAndView.addObject("LOG_STATE_SUCCESS", LOG_STATE_SUCCESS);
		modelAndView.addObject("LOG_STATE_ERROR", LOG_STATE_ERROR);
		modelAndView.addObject(MfConstant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：分页查看日志信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-12 下午02:25:09<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getLogsList")
	public String getLogsList(@ModelAttribute()Logs logs,@ModelAttribute()PageBean<Logs> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = logsService.getLogsList(pageBean, logs);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：删除日志信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-12 下午04:19:04<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delLogs")
	public String delLogs(@ModelAttribute()Logs logs,HttpServletRequest request,HttpServletResponse response){
		boolean result =  logsService.delLogs(logs.getLogUuid());
		if(result){
			writeToJson(new JsonBean(RESULT_STATE_OK, true, null, 0), response);
		}else{
			writeToJson(new JsonBean(RESULT_STATE_NULL, false, null, 0), response);
		}
		return null;
	}
}
