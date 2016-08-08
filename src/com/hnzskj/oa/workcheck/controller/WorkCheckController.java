/*
 * @项目名称: crm
 * @文件名称: WorkCheckController.java
 * @日期: 2015-12-14 下午06:42:11  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.workcheck.controller;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.base.core.service.IDicService;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.controller.BaseController;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.ExcelUtil;
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.oa.kqsz.bean.Kqsz;
import com.hnzskj.oa.kqsz.service.IKqszService;
import com.hnzskj.oa.workcheck.bean.WorkCheck;
import com.hnzskj.oa.workcheck.service.IWorkCheckService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：WorkCheckController.java   <br/>
 * 类描述： 考勤管理逻辑层  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-14 下午06:42:11   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-14 下午06:42:11   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="wc")
public class WorkCheckController extends BaseController {

	/**进入考勤管理模块*/
	protected static String GET_WC_TITLE="进入考勤管理模块";
	
	/**添加员工考勤信息*/
	protected static String GET_WC_ADD="添加员工考勤信息";
	
	/**批量添加员工考勤信息*/
	protected static String GET_WC_ADD_BATCH="批量添加员工考勤信息";
	
	/**修改员工考勤信息*/
	protected static String GET_WC_UPD="修改员工考勤信息";
	
	/**删除员工考勤信息*/
	protected static String GET_WC_DEL="删除员工考勤信息";
	
	
	/**
	 * 日志信息业务层注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 业务层注入
	 */
	@Autowired
	private IWorkCheckService workCheckService = null;
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IDicService dicService = null;
	
	/**
	 * 考勤信息设置注入
	 */
	@Autowired
	private IKqszService kqszService = null;
	
	
	/**
	 * 
	 * 方法描述：进入考勤管理模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="workCheck")
	public ModelAndView workCheck(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/oa/workCheck/index");
		addLogs(LOG_TYPE_OPERATE, GET_WC_TITLE, GET_WC_TITLE, LOG_STATE_SUCCESS,request);
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
		//其他
		Dic DIC_SHOW_YEAR_COUNT= dicService.getDicByKey(Constant.DIC_SHOW_YEAR_COUNT, Constant.DIC_YEAR_COUNT);
		modelAndView.addObject("DIC_SHOW_YEAR_COUNT", DIC_SHOW_YEAR_COUNT.getText());
		
	}
	
	/**
	 * 
	 * 方法描述：查询员工考勤<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getWorkCheckPage")
	public String getWorkCheckPage(@ModelAttribute()WorkCheck workCheck,@ModelAttribute()PageBean<WorkCheck> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = workCheckService.searchWorkCheckList(pageBean, workCheck);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	

	/**
	 * 
	 * 方法描述：添加用户考勤信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-10-28 上午11:09:09<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addWorkCheck")
	public String addWorkCheck(@ModelAttribute()WorkCheck workCheck,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_WC_ADD;
		if(StringUtils.isNotEmpty(workCheck.getUuid())){
			msg = GET_WC_UPD;
			result = workCheckService.updWorkCheck(workCheck);
		}else{
			result =workCheckService.addWorkCheck(workCheck);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(workCheck, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(workCheck, true), LOG_STATE_ERROR,request);
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
	@RequestMapping(value="delWorkCheck")
	public String delWorkCheck(@ModelAttribute()WorkCheck workCheck,HttpServletRequest request,HttpServletResponse response){
		boolean result =workCheckService.delWorkCheck(workCheck);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_WC_DEL, JSONUtil.toJSONString(workCheck, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_WC_DEL, JSONUtil.toJSONString(workCheck, true), LOG_STATE_ERROR,request);
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
	 * @throws Exception 
	 */
	@RequestMapping("getDownWorkCheckList")
	public String getDownWorkCheckList(@ModelAttribute()WorkCheck workCheck,@ModelAttribute()PageBean<WorkCheck> pageBean,HttpServletRequest request,HttpServletResponse response){
		String name="";
		if(StringUtils.isNotEmpty(workCheck.getWcYear())){
			name=workCheck.getWcYear()+"年";
		}
		if(StringUtils.isNotEmpty(workCheck.getWcMonth())){
			name+=workCheck.getWcMonth()+"月份";
		}
		name+="考勤统计";
		
		//最后添加一行说明信息列表。
		Kqsz kqsz = new Kqsz();
		kqsz.setYear(workCheck.getWcYear());
		kqsz.setMonth(workCheck.getWcMonth());
		kqsz = kqszService.getKqsz(kqsz);
		if(kqsz==null){
			kqsz = new  Kqsz();
		}
		String start = null;
		String end = null;
		try {
			start= new SimpleDateFormat("yyyy年MM月dd日").format(new SimpleDateFormat("yyyy-MM-dd").parse(kqsz.getKqStartTime()));
			end= new SimpleDateFormat("yyyy年MM月dd日").format(new SimpleDateFormat("yyyy-MM-dd").parse(kqsz.getKqEndTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}   
		String note="考勤时间段："+start+"--"+end;
		
		
		HSSFWorkbook workbook = new HSSFWorkbook();//创建爱你一个新的excel
		HSSFSheet sheet = workbook.createSheet();//创建一个sheet页面
		workbook.setSheetName(0, name);//设置sheet页面名称

		
		
		//标题字体
		HSSFFont headfont = workbook.createFont();   
		headfont.setFontName("宋体");   
		headfont.setFontHeightInPoints((short) 20);// 字体大小   
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		// 标题样式   
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
		
		// 内容样式 1
		HSSFCellStyle datastyle = workbook.createCellStyle();   
		datastyle.setFont(dataFont);   
		datastyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		datastyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		datastyle.setLocked(true);   
		datastyle.setWrapText(true);// 自动换行   
		
		// 内容样式 
		HSSFCellStyle datastyle2 = workbook.createCellStyle();   
		datastyle2.setFont(dataFont);   
		datastyle2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 左右居中   
		datastyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		datastyle2.setLocked(true);   
		datastyle2.setWrapText(true);// 自动换行   
		
		
		
		HSSFHeader header = sheet.getHeader();
		header.setCenter(name);//设置标题名称
		
		//设置标题样式
		HSSFRow titleRow = sheet.createRow(0);//设置第一行为header
		titleRow.setHeight((short)(50*20));
		HSSFCell cellTitle = titleRow.createCell(0);
		cellTitle.setCellValue(name);
		cellTitle.setCellStyle(headstyle);
		titleRow.setRowStyle(headstyle);
		CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 8);	
		sheet.addMergedRegion(cra);
		
		
		//设置副标题标题样式
		HSSFRow noteRow = sheet.createRow(1);//设置第一行为header
		titleRow.setHeight((short)(50*20));
		HSSFCell cellNote = noteRow.createCell(0);
		cellNote.setCellValue(note);
		cellNote.setCellStyle(datastyle2);
		titleRow.setRowStyle(datastyle2);
		CellRangeAddress cra2=new CellRangeAddress(1, 1, 0, 8);	
		sheet.addMergedRegion(cra2);
		noteRow.setHeight((short)(30*20));//第二行说明行列表
		
		
		pageBean = workCheckService.searchWorkCheckList(null, workCheck);
		
		String[]title = new String[]{"部门","姓名","工号","职务","考勤天数","加班天数","加班小时","入职日期","其他"};
		String[]key = new String[]{"orgName","userName","userNumber","roleName","wcCheckDay","wcAddDay","wcAddHour","joinTime","wcNote"};
		
		HSSFRow row = sheet.createRow(2);//设置第一行为header
		//遍历标题信息
		for (int i = 0; i < title.length; i++) {
			HSSFCell cell = row.createCell(i);//序号
			cell.setCellValue(title[i]);
			cell.setCellStyle(datastyle);
		}
		row.setHeight((short)(30*20));//第一行标题高度
		
		sheet.setColumnWidth(0, (short)100*30);
		sheet.setColumnWidth(1, (short)100*30);
		sheet.setColumnWidth(2, (short)100*30);
		sheet.setColumnWidth(3, (short)150*30);
		sheet.setColumnWidth(4, (short)100*30);
		sheet.setColumnWidth(5, (short)100*30);
		sheet.setColumnWidth(6, (short)100*30);
		sheet.setColumnWidth(7, (short)150*30);
		sheet.setColumnWidth(8, (short)150*30);
		try {
			int i=1;
			int rowNum = 3;
			for (Object object : pageBean.getList()) {
				HSSFRow rowData = sheet.createRow(rowNum);//创建第n行数据
				Class<? extends Object> clazz = object.getClass();
				PropertyDescriptor pd = null;
				i++;
				for (int j = 0; j < key.length; j++) {
					HSSFCell cell = rowData.createCell(j);
					pd = new PropertyDescriptor(key[j], clazz);
					if(null!=pd){
						Method getMethod = pd.getReadMethod();
						Object o = getMethod.invoke(object);
						cell.setCellValue(null!=o?o.toString():"");
					}else{
						cell.setCellValue("");
					}
					cell.setCellStyle(datastyle);
				}
				rowData.setHeight((short)(30*20));//第一行标题高度
				rowNum++;
			}
			
			
			//最后一行字体样式
			HSSFFont dataFontLast = workbook.createFont();
			dataFontLast.setFontName("宋体");
			dataFontLast.setFontHeightInPoints((short) 12);// 字体大小   
			dataFontLast.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			//最后一行样式
			HSSFCellStyle datastyleLast = workbook.createCellStyle();   
			datastyleLast.setFont(dataFontLast);   
			datastyleLast.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中   
			datastyleLast.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
			datastyleLast.setLocked(true);   
			datastyleLast.setWrapText(true);// 自动换行   
			
			
			int lastRowNum=3+pageBean.getList().size();
			//设置副标题标题样式
			HSSFRow lastRow = sheet.createRow(lastRowNum);//设置第一行为header
			lastRow.setHeight((short)(50*20));
			HSSFCell cellLast = lastRow.createCell(0);
			cellLast.setCellValue("注："+workCheck.getWcMonth()+"月份满勤天数"+kqsz.getMqts()+"，除法定节假日外加班天数只计算基本工资。考勤如有疑问，请及时到行政部核实。");
			cellLast.setCellStyle(datastyleLast);
			lastRow.setRowStyle(datastyleLast);
			CellRangeAddress craLast=new CellRangeAddress(lastRowNum, lastRowNum, 0, 8);	
			sheet.addMergedRegion(craLast);
			lastRow.setHeight((short)(30*20));//第二行说明行列表
			
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
		}  catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * 
	 * 方法描述：验证当前人员考勤信息是否已经添加过<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-16 下午09:56:12<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addValidate")
	public String addValidate(@ModelAttribute()WorkCheck workCheck,HttpServletRequest request,HttpServletResponse response){
		boolean result = true;
		if(StringUtils.isNotEmpty(workCheck.getUuid())){
			result = false;
		}else{
			result = workCheckService.addValidate(workCheck);
		}
		JsonBean jsonBean = null;
		if(result){
			jsonBean = new JsonBean(RESULT_STATE_OK, true);
		}else{
			jsonBean = new JsonBean(RESULT_STATE_NULL, false);
		}
		writeToJson(jsonBean, response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：批量添加用户考勤信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2016-3-22 下午02:38:16<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="beatchAddWorkCheck")
	public String beatchAddWorkCheck(@ModelAttribute()WorkCheck workCheck,HttpServletRequest request,HttpServletResponse response){
		boolean result = true;
		result = workCheckService.addWorkCheck(workCheck.getList(), workCheck);
		JsonBean jsonBean = null;
		if(result){
			jsonBean = new JsonBean(RESULT_STATE_OK, true);
		}else{
			jsonBean = new JsonBean(RESULT_STATE_NULL, false);
		}
		writeToJson(jsonBean, response);
		return null;
	}
	
	
}
