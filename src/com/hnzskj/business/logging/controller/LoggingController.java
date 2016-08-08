/*
 * @项目名称: crm
 * @文件名称: LoggingController.java
 * @日期: 2015-11-2 下午04:19:28  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.logging.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.service.IDicService;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.business.common.controller.MfBaseController;
import com.hnzskj.business.logging.bean.Logging;
import com.hnzskj.business.logging.service.ILoggingService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.DataUtil;
import com.hnzskj.common.util.JSONUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：LoggingController.java   <br/>
 * 类描述：员工日产量控制层   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-2 下午04:19:28   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-2 下午04:19:28   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@RequestMapping(value="logging")
@Controller
public class LoggingController extends MfBaseController{
	

	/**进入员工日常工作管理模块*/
	protected static String GET_LOGGING_TITLE="进入员工日常工作管理模块";
	
	/**添加员工日常工作信息*/
	protected static String GET_LOGGING_ADD="添加员工日常工作信息";
	
	/**修改员工日常工作信息*/
	protected static String GET_LOGGING_UPD="修改员工日常工作信息";
	
	/**删除员工日常工作信息*/
	protected static String GET_LOGGING_DEL="删除员工日常工作信息";
	
	/**删除员工日常工作信息*/
	protected static String GET_LOGGING_EXPORT="导出员工日常工作信息";
	
	/**进入月度报表查询模块*/
	protected static String  GET_MONTHREPORT_TITLE="进入月度报表查询模块";
	
	/**进入自定义报表查询模块*/
	protected static String  GET_DATAREPORT_TITLE="进入自定义报表查询模块";
	
	/**进入年度报表查询模块*/
	protected static String  GET_YEARREPORT_TITLE="进入年度报表查询模块";
	
	/**进入自定义报表查询模块*/
	protected static String  GET_NORMALREPORT_TITLE="进入自定义报表查询模块";
	
	
	/**
	 * 日志信息dao层注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 业务注入
	 */
	@Autowired
	private ILoggingService loggingService = null;
	
	/**
	 * 数据字典业务注入
	 */
	@Autowired
	private IDicService dicService = null;
	
	
	/**
	 * 
	 * 方法描述：进入员工日常工作管理模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="logging")
	public ModelAndView logging(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/business/logging/index");
		addLogs(LOG_TYPE_OPERATE, GET_LOGGING_TITLE, GET_LOGGING_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		setInitParam(request);
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：初始化参数<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 下午06:45:04<br/>         
	 * @param <br/>   
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	private void setInitParam(HttpServletRequest request){
		//男
		Dic DIC_GENDER_MAN= dicService.getDicByKey(Constant.DIC_GENDER, Constant.DIC_GENDER_MAN);
		if(null!=DIC_GENDER_MAN){
			request.setAttribute(Constant.DIC_GENDER_MAN, DIC_GENDER_MAN.getId());
		}
		//女
		Dic DIC_GENDER_FEMALE= dicService.getDicByKey(Constant.DIC_GENDER, Constant.DIC_GENDER_FEMALE);
		if(null!=DIC_GENDER_FEMALE){
			request.setAttribute(Constant.DIC_GENDER_FEMALE, DIC_GENDER_FEMALE.getId());
		}
		//其他
		Dic DIC_GENDER_OTHER= dicService.getDicByKey(Constant.DIC_GENDER, Constant.DIC_GENDER_OTHER);
		if(null!=DIC_GENDER_OTHER){
			request.setAttribute(Constant.DIC_GENDER_OTHER, DIC_GENDER_OTHER.getId());
		}
		//抛光比例。
		Dic LOGGING_PG_NUM_WEIGHT= dicService.getDicByKey(Constant.DIC_LOGGING_NUM, Constant.LOGGING_PG_NUM_WEIGHT);
		if(null!=LOGGING_PG_NUM_WEIGHT){
			request.setAttribute(Constant.LOGGING_PG_NUM_WEIGHT, LOGGING_PG_NUM_WEIGHT.getId());
		}
		//男性推管单价
		Dic LOGGING_TG_MAN_PRICE= dicService.getDicByKey(Constant.DIC_LOGGING_NUM, Constant.LOGGING_TG_MAN_PRICE);
		if(null!=LOGGING_TG_MAN_PRICE){
			request.setAttribute(Constant.LOGGING_TG_MAN_PRICE, LOGGING_TG_MAN_PRICE.getId());
		}
		//女性推管单价
		Dic LOGGING_TG_WUMEN_PRICE= dicService.getDicByKey(Constant.DIC_LOGGING_NUM, Constant.LOGGING_TG_WUMEN_PRICE);
		if(null!=LOGGING_PG_NUM_WEIGHT){
			request.setAttribute(Constant.LOGGING_TG_WUMEN_PRICE, LOGGING_TG_WUMEN_PRICE.getId());
		}
		
		//制管
		Dic LOGGING_TYPE_ZG= dicService.getDicByKey(Constant.DIC_LOGGING_TYPE, Constant.LOGGING_TYPE_ZG);
		if(null!=LOGGING_TYPE_ZG){
			request.setAttribute(Constant.LOGGING_TYPE_ZG, LOGGING_TYPE_ZG.getId());
		}
		
		//包装
		Dic LOGGING_TYPE_BZ= dicService.getDicByKey(Constant.DIC_LOGGING_TYPE, Constant.LOGGING_TYPE_BZ);
		if(null!=LOGGING_TYPE_BZ){
			request.setAttribute(Constant.LOGGING_TYPE_BZ, LOGGING_TYPE_BZ.getId());
		}
		
		//抛光
		Dic LOGGING_TYPE_PG= dicService.getDicByKey(Constant.DIC_LOGGING_TYPE, Constant.LOGGING_TYPE_PG);
		if(null!=LOGGING_TYPE_PG){
			request.setAttribute(Constant.LOGGING_TYPE_PG, LOGGING_TYPE_PG.getId());
		}
		
		//推管
		Dic LOGGING_TYPE_TG= dicService.getDicByKey(Constant.DIC_LOGGING_TYPE, Constant.LOGGING_TYPE_TG);
		if(null!=LOGGING_TYPE_TG){
			request.setAttribute(Constant.LOGGING_TYPE_TG, LOGGING_TYPE_TG.getId());
		}
		
		//班长
		Dic LOGGING_TYPE_LEADER= dicService.getDicByKey(Constant.DIC_LOGGING_TYPE, Constant.LOGGING_TYPE_LEADER);
		if(null!=LOGGING_TYPE_LEADER){
			request.setAttribute(Constant.LOGGING_TYPE_LEADER, LOGGING_TYPE_LEADER.getId());
		}
		
	}
	
	/**
	 * 
	 * 方法描述：查询日常工作记录<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getLoggingPage")
	public String getLoggingPage(@ModelAttribute()Logging logging,String month,@ModelAttribute()PageBean<Logging> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = getPageBean(logging,month,pageBean);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取后台数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 下午06:25:17<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private PageBean<Logging> getPageBean(Logging logging,String month,PageBean<Logging> pageBean){
		if(null!=month&&!"".equals(month)){
			String str[]=month.split("-");
			Integer year = Integer.valueOf(str[0]);
			Integer m =Integer.valueOf(str[1]);
			int days = DataUtil.getMonthLastDay(year,m);
			logging.setStartDate(year+"-"+m+"-01");
			logging.setEndDate(year+"-"+m+"-"+days);
		}
		pageBean = loggingService.searchLoggingList(pageBean, logging);
		return pageBean;
	}
	
	/**
	 * 
	 * 方法描述：添加产品信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-10-28 上午11:09:09<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addLogging")
	public String addLogging(@ModelAttribute()Logging logging,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_LOGGING_ADD;
		if(null!=logging.getLoggingUuid()&&!"".equals(logging.getLoggingUuid())){
			msg = GET_LOGGING_UPD;
			result = loggingService.updLogging(logging);
		}else{
			//获取当前登录人员
			User user = getUserFromSession(request);
			if(null!=user){
				logging.setCreateUserId(user.getUserId());
				logging.setCreateUserName(user.getUserName());
				logging.setCreateUserNumber(user.getUserNumber());
				logging.setCreateUserUuid(user.getUserUuid());
			}
			result =loggingService.addLogging(logging);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(logging, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(logging, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：删除一条日常工作信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-10-29 下午05:59:13<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("delLogging")
	public String delLogging(@ModelAttribute()Logging logging,HttpServletRequest request,HttpServletResponse response){
		boolean result = loggingService.delLogging(logging);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_LOGGING_DEL, JSONUtil.toJSONString(logging, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_LOGGING_DEL, JSONUtil.toJSONString(logging, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：计算班长工资。该班下所有包装人员工资平均数。<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-6 下午03:17:28<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getLeaderWages")
	public String getLeaderWages(@ModelAttribute()Logging logging,HttpServletRequest request,HttpServletResponse response){
		Logging l = loggingService.getLeaderWages(logging);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, l,1), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：导出excel<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 上午10:04:33<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getDownLoggingList")
	public String getDownLoggingList(@ModelAttribute()Logging logging,@ModelAttribute()PageBean<Logging> pageBean,HttpServletRequest request,HttpServletResponse response){
		HSSFWorkbook workbook = new HSSFWorkbook();//创建爱你一个新的excel
		HSSFSheet sheet = workbook.createSheet();//创建一个sheet页面
		workbook.setSheetName(0, "工作记录");//设置sheet页面名称
		
		HSSFHeader header = sheet.getHeader();//设置标题
		header.setCenter("工作记录明细");//设置标题名称
		
		//字体
		HSSFFont headfont = workbook.createFont();   
		headfont.setFontName("黑体");   
		headfont.setFontHeightInPoints((short) 10);// 字体大小   
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗   
		
		// 另一个样式   
		HSSFCellStyle headstyle = workbook.createCellStyle();   
		headstyle.setFont(headfont);   
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		headstyle.setLocked(true);   
		headstyle.setWrapText(true);// 自动换行   
			
		for (int i = 0; i <= 12; i++) {
			sheet.setDefaultColumnStyle(i, headstyle);
		}
		 
		
		HSSFRow row = sheet.createRow(0);//设置第一行为header
		
		HSSFCell cell0 = row.createCell(0);//序号
		HSSFCell cell1 = row.createCell(1);//日期
		HSSFCell cell2 = row.createCell(2);//员工姓名
		HSSFCell cell3 = row.createCell(3);//员工号
		HSSFCell cell4 = row.createCell(4);//性别
		HSSFCell cell5 = row.createCell(5);//工作性质
		HSSFCell cell6 = row.createCell(6);//日工资额
		HSSFCell cell7 = row.createCell(7);//产品规格代码
		HSSFCell cell8 = row.createCell(8);//单价
		HSSFCell cell9 = row.createCell(9);//支重
		HSSFCell cell10 = row.createCell(10);//支重
		HSSFCell cell11 = row.createCell(11);//总重
		HSSFCell cell12 = row.createCell(12);//次数
		HSSFCell cell13 = row.createCell(13);//说明
		
		
		cell0.setCellValue("序号");
		cell1.setCellValue("日期");
		cell2.setCellValue("员工姓名");
		cell3.setCellValue("员工号");
		cell4.setCellValue("性别");
		cell5.setCellValue("工作性质");
		cell6.setCellValue("日工资额");
		cell7.setCellValue("产品规格代码");
		cell8.setCellValue("单价");
		cell9.setCellValue("支重");
		cell10.setCellValue("数量");
		cell11.setCellValue("总重");
		cell12.setCellValue("次数");
		cell13.setCellValue("说明");
		
		//默认导出当月的信息
		if(null==logging.getStartDate()||"".equals(logging.getStartDate())){
			logging.setStartDate(DataUtil.getMonthFirstDay());//获取本月第一天
		}
		//默认导出当月的信息
		if(null==logging.getEndDate()||"".equals(logging.getEndDate())){
			logging.setEndDate(DataUtil.getMonthLastDay());//获取本月最后一天
		}
		pageBean = loggingService.searchLoggingList(null, logging);
		List<Logging> list = pageBean.getList();
		int i = 0;
		for (Logging l : list) {
			 row = sheet.createRow(i+1);
			 row = sheet.createRow(i+1);
			 cell0 = row.createCell(0);//序号
			 cell1 = row.createCell(1);//日期
			 cell2 = row.createCell(2);//员工姓名
			 cell3 = row.createCell(3);//员工号
			 cell4 = row.createCell(4);//性别
			 cell5 = row.createCell(5);//工作性质
			 cell6 = row.createCell(6);//日工资额
			 cell7 = row.createCell(7);//产品规格代码
			 cell8 = row.createCell(8);//单价
			 cell9 = row.createCell(9);//支重
			 cell10 = row.createCell(10);//支重
			 cell11 = row.createCell(11);//总重
			 cell12 = row.createCell(12);//次数
			 cell13 = row.createCell(13);//说明
			 
		 	 cell0.setCellValue(i+1);
			 cell1.setCellValue(l.getLoggingDate());
			 cell2.setCellValue(l.getLoggingUserName());
			 cell3.setCellValue(l.getLoggingUserNumber());
			 cell4.setCellValue(l.getLoggingUserSexText());
			 cell5.setCellValue(l.getLoggingTypeText());
			 cell6.setCellValue(null==l.getLoggingWages()?"":String.valueOf(l.getLoggingWages()));
			 cell7.setCellValue(null==l.getLoggingProductNum()?"":String.valueOf(l.getLoggingProductNum()));
			 cell8.setCellValue(null==l.getLoggingPrice()?"":String.valueOf(l.getLoggingPrice()));
			 cell9.setCellValue(null==l.getLoggingWeight()?"":String.valueOf(l.getLoggingWeight()));
			 cell10.setCellValue(null==l.getLoggingNumber()?"":String.valueOf(l.getLoggingNumber()));
			 cell11.setCellValue(null==l.getLoggingAllWeight()?"":String.valueOf(l.getLoggingAllWeight()));
			 cell12.setCellValue(null==l.getLoggingPgNum()?"":String.valueOf(l.getLoggingPgNum()));
			 cell13.setCellValue(l.getNote());
			 i++;
		}
		
        //通过Response把数据以Excel格式保存         
		response.reset();
		response.setContentType("application/msexcel;charset=UTF-8");
		try {
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ new String(("工作记录"+DataUtil.formateDate() + ".xls").getBytes("GBK"),
							"ISO8859_1") + "\"");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/*********************************************月度查询开始****************************************************/
	
	
	/**
	 * 
	 * 方法描述：进入月度报表查询<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="monthReport")
	public ModelAndView monthReport(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/business/monthReport/index");
		addLogs(LOG_TYPE_OPERATE, GET_MONTHREPORT_TITLE, GET_MONTHREPORT_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		setInitParam(request);
		return modelAndView;
	}
	
	
	
	/**
	 * 
	 * 方法描述：月度报表查询<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getMonthReport")
	public String getMonthReport(@ModelAttribute()Logging logging,HttpServletRequest request,HttpServletResponse response){
		List<Logging> list = getLoggingList(logging);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, list, list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取后台数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 下午06:05:54<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private List<Logging> getLoggingList(Logging logging){
		if(null!=logging.getStartDate()&&!"".equals(logging.getStartDate())){
			String str[]=logging.getStartDate().split("-");
			Integer year = Integer.valueOf(str[0]);
			Integer month =Integer.valueOf(str[1]);
			int days = DataUtil.getMonthLastDay(year,month);
			logging.setStartDate(year+"-"+month+"-01");
			logging.setEndDate(year+"-"+month+"-"+days);
		}else{
			//默认查询当月工资
			logging.setStartDate(DataUtil.getMonthFirstDay());
			logging.setEndDate(DataUtil.getMonthLastDay());
		}
		
		List<Logging> list = loggingService.searchLoggingList(logging);
		return list;
	}
	
	/**
	 * 
	 * 方法描述：导出当前数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 下午06:06:19<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getDownMonthReport")
	public String getDownMonthReport(@ModelAttribute()Logging logging,HttpServletRequest request,HttpServletResponse response){
		HSSFWorkbook workbook = new HSSFWorkbook();//创建爱你一个新的excel
		HSSFSheet sheet = workbook.createSheet();//创建一个sheet页面
		workbook.setSheetName(0, "工资统计表");//设置sheet页面名称
		
		HSSFHeader header = sheet.getHeader();//设置标题
		header.setCenter("工资统计表");//设置标题名称
		
		//字体
		HSSFFont headfont = workbook.createFont();   
		headfont.setFontName("黑体");   
		headfont.setFontHeightInPoints((short) 10);// 字体大小   
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗   
		
		// 另一个样式   
		HSSFCellStyle headstyle = workbook.createCellStyle();   
		headstyle.setFont(headfont);   
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		headstyle.setLocked(true);   
		headstyle.setWrapText(true);// 自动换行   
			
		for (int i = 0; i <= 12; i++) {
			sheet.setDefaultColumnStyle(i, headstyle);
		}
		 
		
		HSSFRow row = sheet.createRow(0);//设置第一行为header
		
		HSSFCell cell0 = row.createCell(0);//序号
		HSSFCell cell1 = row.createCell(1);//部门
		HSSFCell cell2 = row.createCell(2);//员工姓名
		HSSFCell cell3 = row.createCell(3);//员工号
		HSSFCell cell4 = row.createCell(4);//性别
		HSSFCell cell5 = row.createCell(5);//工作性质
		HSSFCell cell6 = row.createCell(6);//工资
		HSSFCell cell7 = row.createCell(7);//总重
		
		
		cell0.setCellValue("序号");
		cell1.setCellValue("部门");
		cell2.setCellValue("员工姓名");
		cell3.setCellValue("员工号");
		cell4.setCellValue("性别");
		cell5.setCellValue("工作性质");
		cell6.setCellValue("工资");
		cell7.setCellValue("总重");
		
		List<Logging> list = getLoggingList(logging);
		
		int i = 0;
		float loggingWages=0.0f;
		float loggingAllWeight=0.0f;
		for (Logging l : list) {
			 row = sheet.createRow(i+1);
			 cell0 = row.createCell(0);//序号
			 cell1 = row.createCell(1);//部门
			 cell2 = row.createCell(2);//员工姓名
			 cell3 = row.createCell(3);//员工号
			 cell4 = row.createCell(4);//性别
			 cell5 = row.createCell(5);//工作性质
			 cell6 = row.createCell(6);//工资
			 cell7 = row.createCell(7);//总重
			 
		 	 cell0.setCellValue(i+1);
			 cell1.setCellValue(l.getOrgName());
			 cell2.setCellValue(l.getLoggingUserName());
			 cell3.setCellValue(l.getLoggingUserNumber());
			 cell4.setCellValue(l.getLoggingUserSexText());
			 cell5.setCellValue(l.getLoggingTypeText());
			 cell6.setCellValue(null==l.getLoggingWages()?"":String.valueOf(l.getLoggingWages()));
			 cell7.setCellValue(null==l.getLoggingAllWeight()?"":String.valueOf(l.getLoggingAllWeight()));
			 i++;
			 loggingWages+=null==l.getLoggingWages()?0f:l.getLoggingWages();
			 loggingAllWeight+=null==l.getLoggingAllWeight()?0f:l.getLoggingAllWeight();
		}
		
		 row = sheet.createRow(list.size()+1);
		 cell0 = row.createCell(0);//序号
		 cell1 = row.createCell(1);//部门
		 cell2 = row.createCell(2);//员工姓名
		 cell3 = row.createCell(3);//员工号
		 cell4 = row.createCell(4);//性别
		 cell5 = row.createCell(5);//工作性质
		 cell6 = row.createCell(6);//工资
		 cell7 = row.createCell(7);//总重
		 
	 	 cell0.setCellValue("合计");
		 cell6.setCellValue(loggingWages);
		 cell7.setCellValue(loggingAllWeight);
		 
		
        //通过Response把数据以Excel格式保存         
		response.reset();
		response.setContentType("application/msexcel;charset=UTF-8");
		try {
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ new String(("工资统计表"+DataUtil.formateDate() + ".xls").getBytes("GBK"),
							"ISO8859_1") + "\"");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：导出excel<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 上午10:04:33<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getDownDetailList")
	public String getDownDetailList(@ModelAttribute()Logging logging,String month,@ModelAttribute()PageBean<Logging> pageBean,HttpServletRequest request,HttpServletResponse response){
		HSSFWorkbook workbook = new HSSFWorkbook();//创建爱你一个新的excel
		HSSFSheet sheet = workbook.createSheet();//创建一个sheet页面
		workbook.setSheetName(0, "工资明细");//设置sheet页面名称
		
		HSSFHeader header = sheet.getHeader();//设置标题
		header.setCenter("工资明细");//设置标题名称
		
		//字体
		HSSFFont headfont = workbook.createFont();   
		headfont.setFontName("黑体");   
		headfont.setFontHeightInPoints((short) 10);// 字体大小   
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗   
		
		// 另一个样式   
		HSSFCellStyle headstyle = workbook.createCellStyle();   
		headstyle.setFont(headfont);   
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		headstyle.setLocked(true);   
		headstyle.setWrapText(true);// 自动换行   
			
		for (int i = 0; i <= 12; i++) {
			sheet.setDefaultColumnStyle(i, headstyle);
		}
		 
		
		HSSFRow row = sheet.createRow(0);//设置第一行为header
		
		HSSFCell cell0 = row.createCell(0);//序号
		HSSFCell cell1 = row.createCell(1);//日期
		HSSFCell cell2 = row.createCell(2);//员工姓名
		HSSFCell cell3 = row.createCell(3);//员工号
		HSSFCell cell4 = row.createCell(4);//性别
		HSSFCell cell5 = row.createCell(5);//工作性质
		HSSFCell cell6 = row.createCell(6);//日工资额
		HSSFCell cell7 = row.createCell(7);//产品规格代码
		HSSFCell cell8 = row.createCell(8);//单价
		HSSFCell cell9 = row.createCell(9);//支重
		HSSFCell cell10 = row.createCell(10);//支重
		HSSFCell cell11 = row.createCell(11);//总重
		HSSFCell cell12 = row.createCell(12);//次数
		HSSFCell cell13 = row.createCell(13);//说明
		
		
		cell0.setCellValue("序号");
		cell1.setCellValue("日期");
		cell2.setCellValue("员工姓名");
		cell3.setCellValue("员工号");
		cell4.setCellValue("性别");
		cell5.setCellValue("工作性质");
		cell6.setCellValue("日工资额");
		cell7.setCellValue("产品规格代码");
		cell8.setCellValue("单价");
		cell9.setCellValue("支重");
		cell10.setCellValue("数量");
		cell11.setCellValue("总重");
		cell12.setCellValue("次数");
		cell13.setCellValue("说明");
		
		//默认导出当月的信息
		if(null==logging.getStartDate()||"".equals(logging.getStartDate())){
			logging.setStartDate(DataUtil.getMonthFirstDay());//获取本月第一天
		}
		//默认导出当月的信息
		if(null==logging.getEndDate()||"".equals(logging.getEndDate())){
			logging.setEndDate(DataUtil.getMonthLastDay());//获取本月最后一天
		}
		pageBean = getPageBean(logging, month,pageBean);
		List<Logging> list = pageBean.getList();
		int i = 0;
		float loggingWages=0.0f;
		float loggingAllWeight=0.0f;
		float loggingNumber=0.0f;
		for (Logging l : list) {
			 row = sheet.createRow(i+1);
			 cell0 = row.createCell(0);//序号
			 cell1 = row.createCell(1);//日期
			 cell2 = row.createCell(2);//员工姓名
			 cell3 = row.createCell(3);//员工号
			 cell4 = row.createCell(4);//性别
			 cell5 = row.createCell(5);//工作性质
			 cell6 = row.createCell(6);//日工资额
			 cell7 = row.createCell(7);//产品规格代码
			 cell8 = row.createCell(8);//单价
			 cell9 = row.createCell(9);//支重
			 cell10 = row.createCell(10);//支重
			 cell11 = row.createCell(11);//总重
			 cell12 = row.createCell(12);//次数
			 cell13 = row.createCell(13);//说明
			 
		 	 cell0.setCellValue(i+1);
			 cell1.setCellValue(l.getLoggingDate());
			 cell2.setCellValue(l.getLoggingUserName());
			 cell3.setCellValue(l.getLoggingUserNumber());
			 cell4.setCellValue(l.getLoggingUserSexText());
			 cell5.setCellValue(l.getLoggingTypeText());
			 cell6.setCellValue(null==l.getLoggingWages()?"":String.valueOf(l.getLoggingWages()));
			 cell7.setCellValue(null==l.getLoggingProductNum()?"":String.valueOf(l.getLoggingProductNum()));
			 cell8.setCellValue(null==l.getLoggingPrice()?"":String.valueOf(l.getLoggingPrice()));
			 cell9.setCellValue(null==l.getLoggingWeight()?"":String.valueOf(l.getLoggingWeight()));
			 cell10.setCellValue(null==l.getLoggingNumber()?"":String.valueOf(l.getLoggingNumber()));
			 cell11.setCellValue(null==l.getLoggingAllWeight()?"":String.valueOf(l.getLoggingAllWeight()));
			 cell12.setCellValue(null==l.getLoggingPgNum()?"":String.valueOf(l.getLoggingPgNum()));
			 cell13.setCellValue(l.getNote());
			 i++;
			 loggingWages+=null==l.getLoggingWeight()?0f:l.getLoggingWeight();
			 loggingAllWeight+=null==l.getLoggingAllWeight()?0f:l.getLoggingAllWeight();
			 loggingNumber+=null==l.getLoggingNumber()?0f:l.getLoggingNumber();
		}
		 row = sheet.createRow(list.size()+1);
		 cell0 = row.createCell(0);//序号
		 cell1 = row.createCell(1);//日期
		 cell2 = row.createCell(2);//员工姓名
		 cell3 = row.createCell(3);//员工号
		 cell4 = row.createCell(4);//性别
		 cell5 = row.createCell(5);//工作性质
		 cell6 = row.createCell(6);//日工资额
		 cell7 = row.createCell(7);//产品规格代码
		 cell8 = row.createCell(8);//单价
		 cell9 = row.createCell(9);//支重
		 cell10 = row.createCell(10);//数量
		 cell11 = row.createCell(11);//总重
		 cell12 = row.createCell(12);//次数
		 cell13 = row.createCell(13);//说明
		 
	 	 cell0.setCellValue("合计");
	 	 cell6.setCellValue(loggingWages);
		 cell10.setCellValue(loggingNumber);
		 cell11.setCellValue(loggingAllWeight);
		
        //通过Response把数据以Excel格式保存         
		response.reset();
		response.setContentType("application/msexcel;charset=UTF-8");
		try {
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ new String(("工资明细"+DataUtil.formateDate() + ".xls").getBytes("GBK"),
							"ISO8859_1") + "\"");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
/*********************************************年度报表查询开始****************************************************/
	
	
	/**
	 * 
	 * 方法描述：进入年度报表查询<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="yearReport")
	public ModelAndView yearReport(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/business/yearReport/index");
		addLogs(LOG_TYPE_OPERATE, GET_YEARREPORT_TITLE, GET_YEARREPORT_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		setInitParam(request);
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：获取后台数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 下午06:05:54<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private List<Logging> getYearLoggingList(Logging logging){
		if(null!=logging.getStartDate()&&!"".equals(logging.getStartDate())){
			String year = logging.getStartDate();
			logging.setStartDate(year+"-01-01");
			logging.setEndDate(year+"-12-31");
		}else{
			//默认查询当年
			logging.setStartDate(DataUtil.getYear()+"-01-01");
			logging.setEndDate(DataUtil.getYear()+"-12-31");
		}
		List<Logging> list = loggingService.searchLoggingList(logging);
		return list;
	}
	
	/**
	 * 
	 * 方法描述：年度报表查询<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getYearReport")
	public String getYearReport(@ModelAttribute()Logging logging,HttpServletRequest request,HttpServletResponse response){
		List<Logging> list = getYearLoggingList(logging);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, list, list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：导出当前数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 下午06:06:19<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getDownYearReport")
	public String getDownYearReport(@ModelAttribute()Logging logging,HttpServletRequest request,HttpServletResponse response){
		String year = null!=logging.getStartDate()&&!"".equals(logging.getStartDate())?logging.getStartDate():DataUtil.getYear();
		HSSFWorkbook workbook = new HSSFWorkbook();//创建爱你一个新的excel
		HSSFSheet sheet = workbook.createSheet();//创建一个sheet页面
		workbook.setSheetName(0, year+"年工资统计表");//设置sheet页面名称
		
		HSSFHeader header = sheet.getHeader();//设置标题
		header.setCenter(year+"年度工资统计表");//设置标题名称
		
		//字体
		HSSFFont headfont = workbook.createFont();   
		headfont.setFontName("黑体");   
		headfont.setFontHeightInPoints((short) 10);// 字体大小   
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗   
		
		// 另一个样式   
		HSSFCellStyle headstyle = workbook.createCellStyle();   
		headstyle.setFont(headfont);   
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		headstyle.setLocked(true);   
		headstyle.setWrapText(true);// 自动换行   
			
		for (int i = 0; i <= 12; i++) {
			sheet.setDefaultColumnStyle(i, headstyle);
		}
		 
		
		HSSFRow row = sheet.createRow(0);//设置第一行为header
		
		HSSFCell cell0 = row.createCell(0);//序号
		HSSFCell cell1 = row.createCell(1);//部门
		HSSFCell cell2 = row.createCell(2);//员工姓名
		HSSFCell cell3 = row.createCell(3);//员工号
		HSSFCell cell4 = row.createCell(4);//性别
		HSSFCell cell5 = row.createCell(5);//工作性质
		HSSFCell cell6 = row.createCell(6);//工资
		HSSFCell cell7 = row.createCell(7);//总重
		
		
		cell0.setCellValue("序号");
		cell1.setCellValue("部门");
		cell2.setCellValue("员工姓名");
		cell3.setCellValue("员工号");
		cell4.setCellValue("性别");
		cell5.setCellValue("工作性质");
		cell6.setCellValue("工资");
		cell7.setCellValue("总重");
		
		List<Logging> list = getYearLoggingList(logging);
		
		int i = 0;
		float loggingWages=0.0f;
		float loggingAllWeight=0.0f;
		for (Logging l : list) {
			 row = sheet.createRow(i+1);
			 cell0 = row.createCell(0);//序号
			 cell1 = row.createCell(1);//部门
			 cell2 = row.createCell(2);//员工姓名
			 cell3 = row.createCell(3);//员工号
			 cell4 = row.createCell(4);//性别
			 cell5 = row.createCell(5);//工作性质
			 cell6 = row.createCell(6);//工资
			 cell7 = row.createCell(7);//总重
			 
		 	 cell0.setCellValue(i+1);
			 cell1.setCellValue(l.getOrgName());
			 cell2.setCellValue(l.getLoggingUserName());
			 cell3.setCellValue(l.getLoggingUserNumber());
			 cell4.setCellValue(l.getLoggingUserSexText());
			 cell5.setCellValue(l.getLoggingTypeText());
			 cell6.setCellValue(null==l.getLoggingWages()?"":String.valueOf(l.getLoggingWages()));
			 cell7.setCellValue(null==l.getLoggingAllWeight()?"":String.valueOf(l.getLoggingAllWeight()));
			 i++;
			 loggingWages+=null==l.getLoggingWages()?0f:l.getLoggingWages();
			 loggingAllWeight+=null==l.getLoggingAllWeight()?0f:l.getLoggingAllWeight();
		}
		
		 row = sheet.createRow(list.size()+1);
		 cell0 = row.createCell(0);//序号
		 cell1 = row.createCell(1);//部门
		 cell2 = row.createCell(2);//员工姓名
		 cell3 = row.createCell(3);//员工号
		 cell4 = row.createCell(4);//性别
		 cell5 = row.createCell(5);//工作性质
		 cell6 = row.createCell(6);//工资
		 cell7 = row.createCell(7);//总重
		 
	 	 cell0.setCellValue("合计");
		 cell6.setCellValue(loggingWages);
		 cell7.setCellValue(loggingAllWeight);
		 
		
        //通过Response把数据以Excel格式保存         
		response.reset();
		response.setContentType("application/msexcel;charset=UTF-8");
		try {
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ new String((year+"年度工资统计表"+DataUtil.formateDate() + ".xls").getBytes("GBK"),
							"ISO8859_1") + "\"");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：年度报表查询<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getYear")
	public String getYear(HttpServletRequest request,HttpServletResponse response){
		List<Logging> list = loggingService.getYear();
		writeToJson(new JsonBean(RESULT_STATE_OK, true, list, list.size()), response);
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：根据年度报表获取月度报表<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-11 下午05:32:48<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getYearDetailReport")
	public String getYearDetailReport(@ModelAttribute()Logging logging,HttpServletRequest request,HttpServletResponse response){
		List<Logging> list = getYearDetailReport(logging);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, list, list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取后台组织数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-12 下午01:52:17<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private List<Logging> getYearDetailReport(Logging logging){
		if(null!=logging.getStartDate()&&!"".equals(logging.getStartDate())){
			String year = logging.getStartDate();
			logging.setStartDate(year+"-01-01");
			logging.setEndDate(year+"-12-31");
		}else{
			//默认查询当年
			logging.setStartDate(DataUtil.getYear()+"-01-01");
			logging.setEndDate(DataUtil.getYear()+"-12-31");
		}
		List<Logging> list = loggingService.getYearDetailReport(logging);
		return list;
	}
	
	/**
	 * 
	 * 方法描述：导出人员当年月度报表数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 下午06:06:19<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getDownYearDetailReport")
	public String getDownYearDetailReport(@ModelAttribute()Logging logging,HttpServletRequest request,HttpServletResponse response){
		String year = null!=logging.getStartDate()&&!"".equals(logging.getStartDate())?logging.getStartDate():DataUtil.getYear();
		HSSFWorkbook workbook = new HSSFWorkbook();//创建爱你一个新的excel
		HSSFSheet sheet = workbook.createSheet();//创建一个sheet页面
		workbook.setSheetName(0, year+"年工资统计表");//设置sheet页面名称
		
		HSSFHeader header = sheet.getHeader();//设置标题
		header.setCenter(year+"年度工资统计表");//设置标题名称
		
		//字体
		HSSFFont headfont = workbook.createFont();   
		headfont.setFontName("黑体");   
		headfont.setFontHeightInPoints((short) 10);// 字体大小   
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 加粗   
		
		// 另一个样式   
		HSSFCellStyle headstyle = workbook.createCellStyle();   
		headstyle.setFont(headfont);   
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		headstyle.setLocked(true);   
		headstyle.setWrapText(true);// 自动换行   
			
		for (int i = 0; i <= 12; i++) {
			sheet.setDefaultColumnStyle(i, headstyle);
		}
		 
		
		HSSFRow row = sheet.createRow(0);//设置第一行为header
		
		HSSFCell cell0 = row.createCell(0);//序号
		HSSFCell cell1 = row.createCell(1);//部门
		HSSFCell cell2 = row.createCell(2);//员工姓名
		HSSFCell cell3 = row.createCell(3);//员工号
		HSSFCell cell4 = row.createCell(4);//性别
		HSSFCell cell5 = row.createCell(5);//工作性质
		HSSFCell cell6 = row.createCell(6);//工资
		HSSFCell cell7 = row.createCell(7);//总重
		HSSFCell cell8 = row.createCell(8);//月份
		
		
		cell0.setCellValue("序号");
		cell1.setCellValue("月份");
		cell2.setCellValue("部门");
		cell3.setCellValue("员工姓名");
		cell4.setCellValue("员工号");
		cell5.setCellValue("性别");
		cell6.setCellValue("工作性质");
		cell7.setCellValue("工资");
		cell8.setCellValue("总重");
		List<Logging> list = getYearDetailReport(logging);
		int i = 0;
		float loggingWages=0.0f;
		float loggingAllWeight=0.0f;
		for (Logging l : list) {
			 row = sheet.createRow(i+1);
			 cell0 = row.createCell(0);//序号
			 cell1 = row.createCell(1);//部门
			 cell2 = row.createCell(2);//员工姓名
			 cell3 = row.createCell(3);//员工号
			 cell4 = row.createCell(4);//性别
			 cell5 = row.createCell(5);//工作性质
			 cell6 = row.createCell(6);//工资
			 cell7 = row.createCell(7);//总重
			 cell8 = row.createCell(8);//总重
			 
		 	 cell0.setCellValue(i+1);
		 	 cell1.setCellValue(l.getLoggingDate());
			 cell2.setCellValue(l.getOrgName());
			 cell3.setCellValue(l.getLoggingUserName());
			 cell4.setCellValue(l.getLoggingUserNumber());
			 cell5.setCellValue(l.getLoggingUserSexText());
			 cell6.setCellValue(l.getLoggingTypeText());
			 cell7.setCellValue(null==l.getLoggingWages()?"":String.valueOf(l.getLoggingWages()));
			 cell8.setCellValue(null==l.getLoggingAllWeight()?"":String.valueOf(l.getLoggingAllWeight()));
			 i++;
			 loggingWages+=null==l.getLoggingWages()?0f:l.getLoggingWages();
			 loggingAllWeight+=null==l.getLoggingAllWeight()?0f:l.getLoggingAllWeight();
		}
		
		 row = sheet.createRow(list.size()+1);
		 cell0 = row.createCell(0);//序号
		 cell1 = row.createCell(1);//部门
		 cell2 = row.createCell(2);//员工姓名
		 cell3 = row.createCell(3);//员工号
		 cell4 = row.createCell(4);//性别
		 cell5 = row.createCell(5);//工作性质
		 cell6 = row.createCell(6);//工资
		 cell7 = row.createCell(7);//总重
		 cell8 = row.createCell(8);//总重
		 
	 	 cell0.setCellValue("合计");
		 cell7.setCellValue(loggingWages);
		 cell8.setCellValue(loggingAllWeight);
		 
		
        //通过Response把数据以Excel格式保存         
		response.reset();
		response.setContentType("application/msexcel;charset=UTF-8");
		try {
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ new String((year+"年度工资统计表"+DataUtil.formateDate() + ".xls").getBytes("GBK"),
							"ISO8859_1") + "\"");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
