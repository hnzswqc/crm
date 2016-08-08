/*
 * @项目名称: crm
 * @文件名称: MainController.java
 * @日期: 2015-11-14 下午02:49:52  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.core.bean.Chart;
import com.hnzskj.base.core.service.IChartService;
import com.hnzskj.common.bean.JsonBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：MainController.java   <br/>
 * 类描述： 首页统计图  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-14 下午02:49:52   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-14 下午02:49:52   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="main")
public class MainController extends MfBaseController {

	
	/**
	 * 业务注入
	 */
	@Autowired
	private IChartService chartService = null;
	
	/**
	 * 
	 * 方法描述：获取部门人员数量<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:28:41<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getOrgUserList")
	public String getOrgUserList(HttpServletRequest request,HttpServletResponse response){
		List<Chart> list = chartService.getOrgUserList();
		writeToJson(new JsonBean(RESULT_STATE_OK,true,list,list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取人员学历<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:28:41<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getUserDegreesList")
	public String getUserDegreesList(HttpServletRequest request,HttpServletResponse response){
		List<Chart> list = chartService.getUserDegreesList();
		writeToJson(new JsonBean(RESULT_STATE_OK,true,list,list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取员工入职年限<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:28:41<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getUserJoinYearList")
	public String getUserJoinYearList(HttpServletRequest request,HttpServletResponse response){
		List<Chart> list = chartService.getUserJoinYearList();
		writeToJson(new JsonBean(RESULT_STATE_OK,true,list,list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取员工入职年限<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:28:41<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getUserAgeList")
	public String getUserAgeList(HttpServletRequest request,HttpServletResponse response){
		List<Chart> list = chartService.getUserAgeList();
		writeToJson(new JsonBean(RESULT_STATE_OK,true,list,list.size()), response);
		return null;
	}
	
}
