/*
 * @项目名称: crm
 * @文件名称: WorkLoadChartController.java
 * @日期: 2015-11-19 上午08:28:05  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.chart.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.core.bean.Chart;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.business.chart.service.IWorkLoadChartService;
import com.hnzskj.business.common.controller.MfBaseController;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.DataUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：WorkLoadChartController.java   <br/>
 * 类描述：员工工作量排行分析   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-19 上午08:28:05   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-19 上午08:28:05   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="workLoadChart")
public class WorkLoadChartController extends MfBaseController {


	/**查看员工工作量排行*/
	protected static String GET_SHOW_TITLE="查看员工工作量排行";
	
	/**导出制管量排行*/
	protected static String GET_EXPORT_ZG_TITLE="导出制管量排行";
	
	/**导出包装量排行*/
	protected static String GET_EXPORT_BZ_TITLE="导出制包装量排行";
	
	/**导出抛光量排行*/
	protected static String GET_EXPORT_PG_TITLE="导出抛光量排行";
	
	/**导出推管量排行*/
	protected static String GET_EXPORT_TG_TITLE="导出推管量排行";
	
	/**
	 * 日志信息业务层注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IWorkLoadChartService workLoadChartService = null;
	
	/**
	 * 默认开始时间
	 */
	protected String STARTDATE=DataUtil.getYear()+"-01-01";
	
	/**
	 * 默认结束时间
	 */
	protected String ENDDATE=DataUtil.getYear()+"-12-31";
	
	/**
	 * 默认排序方式倒序
	 */
	protected String ORDERBY=" DESC ";
	
	/**
	 * 默认查询记录数据。10条
	 */
	protected Integer TOPNUM=5;
	
	/**
	 * 根据年度查询
	 */
	protected String TYPE_YEAR = "YEAR";
	/**
	 * 根据月份查询
	 */
	protected String TYPE_MONTH = "MONTH";
	/**
	 * 查询某天
	 */
	protected String TYPE_DATE = "DATE";
	/**
	 * 自定义查询
	 */
	protected String TYPE_NORMAL = "NORMAL";
	
	/**
	 * 
	 * 方法描述：进入工作量分析模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="workLoadChart")
	public ModelAndView workLoadChart(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/business/workLoadChart/index");
		addLogs(LOG_TYPE_OPERATE, GET_SHOW_TITLE, GET_SHOW_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		return modelAndView;
	}

	/**
	 * 
	 * 方法描述：获取数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-20 下午03:24:29<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@SuppressWarnings("unchecked")
	private List<Chart> getChartList(String startDate,String endDate,String orderby,Integer topNum,String type,String method){
		if(null!=type&&!"".equals(type)){
			if(TYPE_YEAR.equals(type.toUpperCase())){
				endDate = null!=startDate&&!"".equals(startDate)?startDate+"-12-31":ENDDATE;
				startDate = null!=startDate&&!"".equals(startDate)?startDate+"-01-01":STARTDATE;
			}else if(TYPE_MONTH.equals(type.toUpperCase())){
				startDate=null!=startDate&&!"".equals(startDate)?startDate:DataUtil.getMonthFirstDay();
				String str[]=startDate.split("-");
				Integer year = Integer.valueOf(str[0]);
				Integer m =Integer.valueOf(str[1]);
				int days = DataUtil.getMonthLastDay(year,m);
				startDate = year+"-"+(m<10?"0"+m:m+"")+"-01";
				endDate = year+"-"+(m<10?"0"+m:m+"")+"-"+days;
			}else if(TYPE_DATE.equals(type.toUpperCase())){
				startDate = null!=startDate&&!"".equals(startDate)?startDate:DataUtil.formateDate(new Date());
				endDate = startDate;
			}else{
				startDate = null!=startDate&&!"".equals(startDate)?startDate:STARTDATE;
				endDate = null!=endDate&&!"".equals(endDate)?endDate:ENDDATE;
			}
			orderby = null!=orderby&&!"".equals(orderby)?orderby:ORDERBY;
			topNum = null!=topNum&&0!=topNum?topNum:TOPNUM;
		}else{
			startDate = null!=startDate&&!"".equals(startDate)?startDate:STARTDATE;
			endDate = null!=endDate&&!"".equals(endDate)?endDate:ENDDATE;
			orderby = null!=orderby&&!"".equals(orderby)?orderby:ORDERBY;
			topNum = null!=topNum&&0!=topNum?topNum:TOPNUM;
		}
			
		Class<? extends IWorkLoadChartService> clazz = workLoadChartService.getClass();
		Method m1  = null;
		List<Chart> list = null;
		try {
			m1 = clazz.getDeclaredMethod(method, String.class,String.class,String.class,Integer.class);
			list = (List<Chart>) m1.invoke(workLoadChartService, startDate,endDate,orderby,topNum);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * 方法描述：查询制管工作量<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-19 上午08:54:50<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="zg")
	public String zg(String startDate,String endDate,String orderby,Integer topNum,String type,HttpServletRequest request,HttpServletResponse response){
		List<Chart> list = getChartList(startDate, endDate, orderby, topNum,type,"getZgListChart");
		writeToJson(new JsonBean(RESULT_STATE_OK,true,list,list.size()), response);
		return null;
	}
	/**
	 * 
	 * 方法描述：查询包装工作量<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-19 上午08:54:50<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="bz")
	public String bz(String startDate,String endDate,String orderby,Integer topNum,String type,HttpServletRequest request,HttpServletResponse response){
		List<Chart> list = getChartList(startDate, endDate, orderby, topNum,type,"getBzListChart");
		writeToJson(new JsonBean(RESULT_STATE_OK,true,list,list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：查询抛光工作量<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-19 上午08:54:50<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="pg")
	public String pg(String startDate,String endDate,String orderby,Integer topNum,String type,HttpServletRequest request,HttpServletResponse response){
		List<Chart> list = getChartList(startDate, endDate, orderby, topNum,type,"getPgListChart");
		writeToJson(new JsonBean(RESULT_STATE_OK,true,list,list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：查询抛光工作量<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-19 上午08:54:50<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="tg")
	public String tg(String startDate,String endDate,String orderby,Integer topNum,String type,HttpServletRequest request,HttpServletResponse response){
		List<Chart> list = getChartList(startDate, endDate, orderby, topNum,type,"getTgListChart");
		writeToJson(new JsonBean(RESULT_STATE_OK,true,list,list.size()), response);
		return null;
	}


}
