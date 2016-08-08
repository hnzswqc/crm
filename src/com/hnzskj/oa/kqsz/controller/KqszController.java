/*
 * @项目名称: crm
 * @文件名称: KqszController.java
 * @日期: 2016-3-11 下午07:33:08  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.kqsz.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.DataUtil;
import com.hnzskj.common.util.ExcelUtil;
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.oa.kqsz.bean.Kqsz;
import com.hnzskj.oa.kqsz.service.IKqszService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：KqszController.java   <br/>
 * 类描述： 考勤设置逻辑层  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2016-3-11 下午07:33:08   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2016-3-11 下午07:33:08   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="kqsz")
public class KqszController extends MfBaseController {

	/**进入考勤设置模块*/
	protected static String GET_KQSZ_TITLE="进入考勤设置模块";
	
	/**添加考勤设置信息*/
	protected static String GET_KQSZ_ADD="添加考勤设置信息";
	
	/**修改考勤设置信息*/
	protected static String GET_KQSZ_UPD="修改考勤设置信息";
	
	/**删除考勤设置信息*/
	protected static String GET_KQSZ_DEL="删除考勤设置信息";
	
	/**导出考勤设置信息*/
	protected static String GET_KQSZ_EXPORT="导出考勤设置信息";
	
	/**
	 * 考勤设置业务层接口注入
	 */
	@Autowired
	private IKqszService kqszService = null;
	
	/**
	 * 日志信息dao层注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 
	 * 方法描述：进入包装工资模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="kqsz")
	public ModelAndView kqsz(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/oa/kqsz/index");
		addLogs(LOG_TYPE_OPERATE, GET_KQSZ_TITLE, GET_KQSZ_TITLE, LOG_STATE_SUCCESS,request);
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
		//初始化参数信息
	}
	
	/**
	 * 
	 * 方法描述：获取考勤设置基础信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2016-3-15 下午04:53:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getKqszPage")
	public String getKqszPage(@ModelAttribute()Kqsz kqsz,@ModelAttribute()PageBean<Kqsz> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = kqszService.getKqszList(pageBean, kqsz);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：添加或修改数据信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 下午02:36:34<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addKqsz")
	public String addKqsz(@ModelAttribute()Kqsz kqsz,HttpServletRequest request,HttpServletResponse response){
		
		boolean result = false;
		String msg = GET_KQSZ_ADD;
		if(null!=kqsz.getUuid()&&!"".equals(kqsz.getUuid())){
			msg = GET_KQSZ_UPD;
			result = kqszService.updKqsz(kqsz);
		}else{
			result = kqszService.addKqsz(kqsz);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(kqsz, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(kqsz, true), LOG_STATE_ERROR,request);
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
	@RequestMapping(value="delKqsz")
	public String delKqsz(@ModelAttribute()Kqsz kqsz,HttpServletRequest request,HttpServletResponse response){
		boolean result = kqszService.delKqsz(kqsz);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_KQSZ_DEL, JSONUtil.toJSONString(kqsz, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_KQSZ_DEL, JSONUtil.toJSONString(kqsz, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
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
	@RequestMapping("getDownKqszList")
	public String getDownKqszList(@ModelAttribute()Kqsz kqsz,@ModelAttribute()PageBean<Kqsz> pageBean,HttpServletRequest request,HttpServletResponse response){
		String name="";
		if(null!=kqsz.getYear()&&!"".equals(kqsz.getYear())){
			name=kqsz.getYear()+"年考勤天数设置表";
		}else{
			name="考勤天数设置表";
		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();//创建爱你一个新的excel
		HSSFSheet sheet = workbook.createSheet();//创建一个sheet页面
		workbook.setSheetName(0, name);//设置sheet页面名称

		
		
		//标题字体
		HSSFFont headfont = workbook.createFont();   
		headfont.setFontName("宋体");   
		headfont.setFontHeightInPoints((short) 20);// 字体大小   
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
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
		CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 6);	
		sheet.addMergedRegion(cra);
		
		
		pageBean = kqszService.getKqszList(null, kqsz);
		
		String[]title = new String[]{"年度","月份","满勤天数","考勤开始时间","考勤结束时间","备注信息"};
		String[]key = new String[]{"year","month","mqts","kqStartTime","kqEndTime","note"};
		
		sheet.setColumnWidth(1, (short)150*20);
		sheet.setColumnWidth(2, (short)150*20);
		sheet.setColumnWidth(3, (short)150*20);
		sheet.setColumnWidth(4, (short)200*20);
		sheet.setColumnWidth(5, (short)200*20);
		sheet.setColumnWidth(6, (short)300*20);
		
		try {
			sheet = ExcelUtil.getSheet(sheet,1,datastyle,datastyle,title,key , pageBean.getList());
			
	        //通过Response把数据以Excel格式保存         
			response.reset();
			response.setContentType("application/msexcel;charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ new String((name+DataUtil.formateDate()+".xls").getBytes("GBK"),
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
	
	

}
