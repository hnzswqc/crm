/*
 * @项目名称: crm
 * @文件名称: ResignedController.java
 * @日期: 2015-12-2 下午09:31:01  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.resigned.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.DataUtil;
import com.hnzskj.common.util.ExcelUtil;
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.oa.common.controller.MfBaseController;
import com.hnzskj.oa.resigned.bean.Resigned;
import com.hnzskj.oa.resigned.bean.ResignedMonth;
import com.hnzskj.oa.resigned.service.IResignedMonthService;
import com.hnzskj.oa.resigned.service.IResignedService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ResignedController.java   <br/>
 * 类描述：员工离职管理   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-2 下午09:31:01   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-2 下午09:31:01   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="resigned")
public class ResignedController extends MfBaseController {

	/**进入辞职员工管理模块*/
	protected static String GET_RESIGNED_TITLE="进入辞职员工管理模块";
	
	/**添加辞职员工信息*/
	protected static String GET_RESIGNED_ADD="添加辞职员工信息";
	
	/**修改辞职员工信息*/
	protected static String GET_RESIGNED_UPD="修改辞职员工信息";
	
	/**删除辞职员工信息*/
	protected static String GET_RESIGNED_DEL="删除辞职员工信息";
	
	/**恢复辞职员工信息*/
	protected static String GET_RESIGNED_BACK="恢复辞职员工信息";
	
	
	/**工资结算完结*/
	protected static String GET_RESIGNED_OVER="工资结算结算";
	
	
	
	/**
	 * 日志信息业务层注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IResignedService resignedService = null;
	
	/**
	 * 计发月份业务层注入
	 */
	@Autowired
	private IResignedMonthService resignedMonthService = null;
	
	
	
	/**
	 * 
	 * 方法描述：进入辞职员工信息模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="resigned")
	public ModelAndView resigned(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/oa/resigned/index");
		addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_TITLE, GET_RESIGNED_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		intParam(modelAndView);
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：进入辞职员工信息模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="resigned_over")
	public ModelAndView resigned_over(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/oa/resigned_over/index");
		addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_TITLE, GET_RESIGNED_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		intParam(modelAndView);
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：进入辞职员工信息模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="resigned_have")
	public ModelAndView resigned_have(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/oa/resigned_have/index");
		addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_TITLE, GET_RESIGNED_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		intParam(modelAndView);
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：进入辞职员工信息模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="resigned_no")
	public ModelAndView resigned_no(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/oa/resigned_no/index");
		addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_TITLE, GET_RESIGNED_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		intParam(modelAndView);
		return modelAndView;
	}
	
	
	
	/**
	 * 
	 * 方法描述：初始化参数<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-8 下午07:22:28<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private void intParam(ModelAndView modelAndView){
		modelAndView.addObject("DIC_RESIGNED_TYPE",com.hnzskj.oa.common.util.MfConstant.DIC_RESIGNED_TYPE);
		modelAndView.addObject("DIC_RESIGNED_MONEY_TYPE",com.hnzskj.oa.common.util.MfConstant.DIC_RESIGNED_MONEY_TYPE);
		modelAndView.addObject("DIC_RESIGNED_STATE",com.hnzskj.oa.common.util.MfConstant.DIC_RESIGNED_STATE);
		modelAndView.addObject("DIC_RESIGNED_STATE_YES",com.hnzskj.oa.common.util.MfConstant.DIC_RESIGNED_STATE_YES);
		modelAndView.addObject("DIC_RESIGNED_STATE_NO",com.hnzskj.oa.common.util.MfConstant.DIC_RESIGNED_STATE_NO);
		modelAndView.addObject("DIC_BANK_CARD_STATE",com.hnzskj.oa.common.util.MfConstant.DIC_BANK_CARD_STATE);
		modelAndView.addObject("DIC_BANK_CARD_STATE_HAVE",com.hnzskj.oa.common.util.MfConstant.DIC_BANK_CARD_STATE_HAVE);
		modelAndView.addObject("DIC_BANK_CARD_STATE_NO",com.hnzskj.oa.common.util.MfConstant.DIC_BANK_CARD_STATE_NO);
		
	}
	
	/**
	 * 
	 * 方法描述：查询离职员工<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getResignedPage")
	public String getResignedPage(@ModelAttribute()Resigned resigned,@ModelAttribute()PageBean<Resigned> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = resignedService.searchResignedList(pageBean, resigned);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：添加辞职员工信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 下午02:36:34<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addResigned")
	public String addResigned(@RequestParam()MultipartFile file,@ModelAttribute()Resigned resigned,String month,HttpServletRequest request,HttpServletResponse response){
		
		try {
			if(null!=file&&0!=file.getSize()){
				resigned.setFileContent(file.getBytes());
				resigned.setFileName(file.getOriginalFilename());
				resigned.setFileType(file.getContentType());
				resigned.setFileSize(file.getSize());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean result = false;
		String msg = GET_RESIGNED_ADD;
		if(null!=resigned.getResUuid()&&!"".equals(resigned.getResUuid())){
			msg = GET_RESIGNED_UPD;
			result = resignedService.updResigned(resigned);
		}else{
			User u= getUserFromSession(request);
			if(null!=u){
				resigned.setCreateUserName(u.getUserName());
				resigned.setCreateUserUuid(u.getUserUuid());
			}
			result = resignedService.addResigned(resigned);
		}
		JsonBean jsonBean = null;
		try {
			resigned.setFileContent(null);
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				//保存计发月份
				resignedMonthService.addResignedMonth(resigned, month);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(resigned, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(resigned, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：删除领取信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 下午05:36:40<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="updResigned")
	public String updResigned(@ModelAttribute()Resigned resigned,HttpServletRequest request,HttpServletResponse response){
		boolean result = resignedService.updResigned(resigned.getResUuid(),resigned.getUserUuid());
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_OVER, JSONUtil.toJSONString(resigned, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_OVER, JSONUtil.toJSONString(resigned, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：恢复正式员工身份<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 下午05:36:40<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="backResigned")
	public String backResigned(@ModelAttribute()Resigned resigned,HttpServletRequest request,HttpServletResponse response){
		boolean result = resignedService.delResigned(resigned);
		JsonBean jsonBean = null;
		try {
			if(result){
				resignedService.updUserState(resigned);
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_BACK, JSONUtil.toJSONString(resigned, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_BACK, JSONUtil.toJSONString(resigned, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：删除领取信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 下午05:36:40<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delResigned")
	public String delResigned(@ModelAttribute()Resigned resigned,HttpServletRequest request,HttpServletResponse response){
		boolean result = resignedService.delResigned(resigned);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_DEL, JSONUtil.toJSONString(resigned, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_DEL, JSONUtil.toJSONString(resigned, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：查看附件<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 下午06:03:07<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="showResigned")
	public String showResigned(@ModelAttribute()Resigned  resigned,HttpServletRequest request,HttpServletResponse response){
		resigned =resignedService.getResignedByParams(resigned);
		InputStream in = null;
		OutputStream os = null;
		if(null!=resigned&&null!=resigned.getFileName()&!"".equals(resigned.getFileName())){
			in =resigned.getInputStream();
		}
		try {
			// 下载文件时显示的文件保存名称
			String s = java.net.URLEncoder.encode(resigned.getFileName(), "utf-8");
			//response.setContentType(record.getRecordAttType()+";charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment;filename="+s);
			os = response.getOutputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) > 0) {
				os.write(b, 0, i);
			}
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}finally{
			try {
				if (null != os) {
				os.close();
				}
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
			}
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
	@RequestMapping("getDownResignedList")
	public String getDownResignedList(@ModelAttribute()Resigned resigned,@ModelAttribute()PageBean<Resigned> pageBean,HttpServletRequest request,HttpServletResponse response){
		String name="";
		String m=resigned.getMonthNum();
		Integer year = Integer.valueOf(StringUtils.isNotEmpty(resigned.getYearNum())?resigned.getYearNum():DataUtil.getYear());
		if(null!=m&&!"".equals(m)){
			if(m.equals("1")){
				name=(year-1)+"年12月26日至"+DataUtil.getYear()+"年01月25日辞职花名册";
			}else{
				
				name=year+"年"+((Integer.valueOf(m)-1)<10?"0"+(Integer.valueOf(m)-1):(Integer.valueOf(m)-1))+"月26日至"+DataUtil.getYear()+"年"+(Integer.valueOf(m)<10?"0"+m:m)+"月25日辞职花名册";
			}
		}else{
			name="辞职花名册";
		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();//创建爱你一个新的excel
		HSSFSheet sheet = workbook.createSheet();//创建一个sheet页面
		workbook.setSheetName(0, name);//设置sheet页面名称

		
		
		//标题字体
		HSSFFont headfont = workbook.createFont();   
		headfont.setFontName("宋体");   
		headfont.setFontHeightInPoints((short) 20);// 字体大小   
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		
		// 另一个样式   
		HSSFCellStyle headstyle = workbook.createCellStyle();   
		headstyle.setFont(headfont);   
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		headstyle.setLocked(true);   
		headstyle.setWrapText(true);// 自动换行   
		
		//内容字体
		HSSFFont dataFont = workbook.createFont();
		dataFont.setFontName("宋体");
		headfont.setFontHeightInPoints((short) 20);// 字体大小   
		dataFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		
		// 内容样式 
		HSSFCellStyle datastyle = workbook.createCellStyle();   
		datastyle.setFont(dataFont);   
		datastyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		datastyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		datastyle.setLocked(true);   
		datastyle.setWrapText(true);// 自动换行   
		
		
		HSSFHeader header = sheet.getHeader();
		header.setCenter(name);//设置标题名称
		
		//设置标题样式
		HSSFRow row = sheet.createRow(0);//设置第一行为header
		row.setHeight((short)(50*20));
		HSSFCell cellTitle = row.createCell(0);
		cellTitle.setCellValue(name);
		cellTitle.setCellStyle(headstyle);
		row.setRowStyle(headstyle);
		CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 11);	
		sheet.addMergedRegion(cra);
		
		
		pageBean = resignedService.searchResignedList(null, resigned);
		
		String[]title = new String[]{"员工姓名","员工工号","性别","工资结算状态","辞职类别","工资结算方式","联系方式","批准辞职日期","考勤天数","应扣金额"};
		String[]key = new String[]{"userName","userNumber","userGender","resStateText","resTypeText","resMoneyTypeText","userMobile","resApproveDate","resCheckWorkNum","resDeductWages"};
		
		sheet.setColumnWidth(2, (short)70*20);
		sheet.setColumnWidth(3, (short)100*20);
		sheet.setColumnWidth(4, (short)70*20);
		sheet.setColumnWidth(5, (short)80*20);
		sheet.setColumnWidth(6, (short)130*20);
		sheet.setColumnWidth(7, (short)150*20);
		sheet.setColumnWidth(8, (short)150*20);
		sheet.setColumnWidth(9, (short)160*20);
		sheet.setColumnWidth(10, (short)170*20);
		sheet.setColumnWidth(11, (short)180*20);
		
		try {
			sheet = ExcelUtil.getSheet(sheet,1,datastyle,datastyle,title,key , pageBean.getList());
			
	        //通过Response把数据以Excel格式保存         
			response.reset();
			response.setContentType("application/msexcel;charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ new String((name+".xls").getBytes("GBK"),
							"ISO8859_1") + "\"");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：获取计发月份<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午03:43:18<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getResignedMonthList")
	public String getResignedMonthList(@ModelAttribute()ResignedMonth resignedMonth,HttpServletRequest request,HttpServletResponse response){
		List<ResignedMonth> list = resignedMonthService.searchResignedMonthList(resignedMonth);
		writeToJson(new JsonBean(RESULT_STATE_OK, true,list, list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：逐月完结工资<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午04:44:14<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="updResignedMonth")
	public String updResignedMonth(@ModelAttribute()ResignedMonth resignedMonth,String monthUuids,HttpServletRequest request,HttpServletResponse response){
		boolean result = resignedMonthService.updResignedMonth(monthUuids,resignedMonth.getMonthState());
		if(result){
			//修改辞职状态
			result = resignedMonthService.updateResigned(resignedMonth.getUserUuid());
		}
		JsonBean jsonBean = null;
		if(result){
			jsonBean = new JsonBean(RESULT_STATE_OK, true);
			addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_OVER, monthUuids, LOG_STATE_SUCCESS,request);
		}else{
			jsonBean = new JsonBean(RESULT_STATE_NULL, false);
			addLogs(LOG_TYPE_OPERATE, GET_RESIGNED_OVER, monthUuids, LOG_STATE_ERROR,request);
		}
		writeToJson(jsonBean, response);
		return null;
	}
	
	
}
