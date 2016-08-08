/*
 * @项目名称: crm
 * @文件名称: ProbationStaffController.java
 * @日期: 2015-12-7 下午09:39:32  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.probationstaff.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
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
import org.apache.poi.ss.util.CellRangeAddress;
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
import com.hnzskj.base.core.service.IUserService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.controller.BaseController;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.ExcelUtil;
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.oa.common.util.MfConstant;
import com.hnzskj.oa.probationstaff.bean.ProbationStaff;
import com.hnzskj.oa.probationstaff.service.IProbationStaffService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ProbationStaffController.java   <br/>
 * 类描述：试用期逻辑控制层   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-7 下午09:39:32   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-7 下午09:39:32   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@RequestMapping(value="pbf")
@Controller
public class ProbationStaffController extends BaseController {


	/**进入试用期员工管理模块*/
	protected static String GET_PROBATIONSTAFF_TITLE="进入试用期员工管理模块";
	
	/**添加试用期员工*/
	protected static String GET_PROBATIONSTAFF_ADD="添加试用期员工";
	
	/**修改试用期员工*/
	protected static String GET_PROBATIONSTAFF_UPD="修改试用期员工";
	
	/**删除试用期员工*/
	protected static String GET_PROBATIONSTAFF_DEL="删除试用期员工";
	
	/**导出试用期员工*/
	protected static String GET_PROBATIONSTAFF_EXPORT="导出试用期员工";
	
	/**
	 * 日志信息业务层注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IProbationStaffService probationStaffService = null;
	
	
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IUserService userService = null;
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IDicService dicService = null;
	
	
	/**
	 * 
	 * 方法描述：进入试用期员工管理模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="probationStaff")
	public ModelAndView probationStaff(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/oa/probationStaff/index");
		addLogs(LOG_TYPE_OPERATE, GET_PROBATIONSTAFF_TITLE, GET_PROBATIONSTAFF_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		initParam(modelAndView);
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
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：设置初始化参数<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-10 下午04:54:21<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private void initParam(ModelAndView modelAndView){
		modelAndView.addObject("DIC_GENDER",MfConstant.DIC_GENDER);
		modelAndView.addObject("DIC_USER_STATE_SYQYG",MfConstant.DIC_USER_STATE_SYQYG);
		modelAndView.addObject("DIC_USER_DEGREES",com.hnzskj.base.common.util.MfConstant.DIC_USER_DEGREES);
	}
	
	/**
	 * 
	 * 方法描述：查询试用期员工<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getProbationStaffPage")
	public String getProbationStaffPage(@ModelAttribute()ProbationStaff probationStaff,@ModelAttribute()PageBean<ProbationStaff> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = probationStaffService.searchProbationStaffList(pageBean, probationStaff);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取部门职务信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 下午02:54:26<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="searchOrgRoleList")
	public String searchOrgRoleList(String orgUuid,HttpServletRequest request,HttpServletResponse response){
		List<Dic> list =probationStaffService.searchOrgRoleList(orgUuid);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, list, list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：添加用户信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午06:00:28<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addUser")
	public String addUser(@ModelAttribute()User user,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_PROBATIONSTAFF_ADD;
		if(null!=user.getUserUuid()&&!"".equals(user.getUserUuid())){
			result = userService.updUser(user);
			msg = GET_PROBATIONSTAFF_UPD;
		}else{
			result = userService.addUser(user);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(user, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(user, true), LOG_STATE_ERROR,request);
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
	@RequestMapping("getDownProbationStaffList")
	public String getDownProbationStaffList(@ModelAttribute()ProbationStaff probationStaff,@ModelAttribute()PageBean<ProbationStaff> pageBean,HttpServletRequest request,HttpServletResponse response){
		String name="试用期员工明细表";
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
		CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 7);	
		sheet.addMergedRegion(cra);
		
		
		pageBean = probationStaffService.searchProbationStaffList(null, probationStaff);
		
		String[]title = new String[]{"员工姓名","性别","年龄","身份证号","试工岗位","试工日期","部门主管","备注"};
		String[]key = new String[]{"userName","userGenderText","userAge","idCard","roleName","pbfDate","pbfLeader","userNote"};
		
		
		/*sheet.setColumnWidth(2, (short)70*20);
		sheet.setColumnWidth(3, (short)100*20);
		sheet.setColumnWidth(4, (short)70*20);
		sheet.setColumnWidth(5, (short)80*20);
		sheet.setColumnWidth(6, (short)130*20);
		sheet.setColumnWidth(7, (short)150*20);*/
		
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
}
