/*
 * @项目名称: crm
 * @文件名称: BzController.java
 * @日期: 2015-11-12 下午02:24:05  
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.base.core.service.IDicService;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.business.common.controller.MfBaseController;
import com.hnzskj.business.logging.bean.Logging;
import com.hnzskj.business.logging.service.ILoggingService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.DataUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：PgController.java   <br/>
 * 类描述：抛光工资   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-12 下午02:24:05   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-12 下午02:24:05   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="pg")
public class PgController extends MfBaseController{

	/**进入抛光工资模块*/
	protected static String GET_PG_TITLE="进入抛光工资模块";
	
	/**添加抛光工资*/
	protected static String GET_PG_ADD="添加抛光工资";
	
	/**修改抛光工资*/
	protected static String GET_PG_UPD="修改抛光工资";
	
	/**删除抛光工资*/
	protected static String GET_PG_DEL="删除抛光工资";
	
	/**导出抛光工资*/
	protected static String GET_PG_EXPORT="导出抛光工资";
	
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
	 * 方法描述：进入抛光工资模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="pg")
	public ModelAndView pg(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/business/pg/index");
		addLogs(LOG_TYPE_OPERATE, GET_PG_TITLE, GET_PG_TITLE, LOG_STATE_SUCCESS,request);
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
	 * 方法描述：查询抛光数据<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getPgPage")
	public String getPgPage(@ModelAttribute()Logging logging,@ModelAttribute()PageBean<Logging> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = getPgPageBean(logging,pageBean);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取后台数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-13 上午10:42:50<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private PageBean<Logging> getPgPageBean(Logging logging,PageBean<Logging> pageBean){
		//默认导出当月的信息
		if(null==logging.getStartDate()||"".equals(logging.getStartDate())){
			logging.setStartDate(DataUtil.getMonthFirstDay());//获取本月第一天
		}
		//默认导出当月的信息
		if(null==logging.getEndDate()||"".equals(logging.getEndDate())){
			logging.setEndDate(DataUtil.getMonthLastDay());//获取本月最后一天
		}
		//包装
		Dic LOGGING_TYPE_PG= dicService.getDicByKey(Constant.DIC_LOGGING_TYPE, Constant.LOGGING_TYPE_PG);
		if(null!=LOGGING_TYPE_PG){
			logging.setLoggingType(LOGGING_TYPE_PG.getId());//包装工作
		}
		if(null==pageBean){
			pageBean = new PageBean<Logging>();
		}
		pageBean = loggingService.searchLoggingList(null, logging);
		return pageBean;
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
	@RequestMapping("getDownPgList")
	public String getDownPgList(@ModelAttribute()Logging logging,@ModelAttribute()PageBean<Logging> pageBean,HttpServletRequest request,HttpServletResponse response){
		HSSFWorkbook workbook = new HSSFWorkbook();//创建爱你一个新的excel
		HSSFSheet sheet = workbook.createSheet();//创建一个sheet页面
		workbook.setSheetName(0, "抛光工资");//设置sheet页面名称
		
		HSSFHeader header = sheet.getHeader();//设置标题
		header.setCenter("抛光工资");//设置标题名称
		
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
		HSSFCell cell6 = row.createCell(6);//应得工资
		HSSFCell cell7 = row.createCell(7);//产品规格代码
		HSSFCell cell8 = row.createCell(8);//单价
		HSSFCell cell9 = row.createCell(9);//支重
		HSSFCell cell10 = row.createCell(10);//支重
		HSSFCell cell11 = row.createCell(11);//总重
		HSSFCell cell12 = row.createCell(12);//次数
		HSSFCell cell13 = row.createCell(13);//说明
		HSSFCell cell14 = row.createCell(14);//扣除工资
		HSSFCell cell15 = row.createCell(15);//扣除工资
		HSSFCell cell16 = row.createCell(16);//时间工资
		HSSFCell cell17 = row.createCell(17);//次品数量
		HSSFCell cell18 = row.createCell(18);//次品率
		
		cell0.setCellValue("序号");
		cell1.setCellValue("日期");
		cell2.setCellValue("员工姓名");
		cell3.setCellValue("员工号");
		cell4.setCellValue("性别");
		cell5.setCellValue("工作性质");
		cell6.setCellValue("应得工资");
		cell7.setCellValue("扣除工资");
		cell8.setCellValue("实际工资");
		cell9.setCellValue("产品规格代码");
		cell10.setCellValue("单价");
		cell11.setCellValue("支重");
		cell12.setCellValue("数量");
		cell13.setCellValue("总重");
		cell14.setCellValue("次数");
		cell15.setCellValue("抛光比例");
		cell16.setCellValue("次品数量");
		cell17.setCellValue("次品系数");
		cell18.setCellValue("说明");
		
		pageBean = getPgPageBean(logging,pageBean);
		
		List<Logging> list = pageBean.getList();
		int i = 0;
		float loggingWages=0f;
		float loggingErrWages=0f;
		float loggingRealityWages=0f;
		float loggingNumber=0f;
		float loggingAllWeight=0f;
		float loggingErrNum=0f;
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
			 cell14 = row.createCell(14);//说明
			 cell15 = row.createCell(15);//说明
			 cell16 = row.createCell(16);//说明
			 cell17 = row.createCell(17);//说明
			 
		 	 cell0.setCellValue(i+1);
			 cell1.setCellValue(l.getLoggingDate());
			 cell2.setCellValue(l.getLoggingUserName());
			 cell3.setCellValue(l.getLoggingUserNumber());
			 cell4.setCellValue(l.getLoggingUserSexText());
			 cell5.setCellValue(l.getLoggingTypeText());
			 cell6.setCellValue(null==l.getLoggingWages()?"":String.valueOf(l.getLoggingWages()));
			 cell7.setCellValue(null==l.getLoggingErrWages()?"":String.valueOf(l.getLoggingErrWages()));
			 cell8.setCellValue(null==l.getLoggingRealityWages()?"":String.valueOf(l.getLoggingRealityWages()));
			 cell9.setCellValue(null==l.getLoggingProductNum()?"":String.valueOf(l.getLoggingProductNum()));
			 cell10.setCellValue(null==l.getLoggingPrice()?"":String.valueOf(l.getLoggingPrice()));
			 cell11.setCellValue(null==l.getLoggingWeight()?"":String.valueOf(l.getLoggingWeight()));
			 cell12.setCellValue(null==l.getLoggingNumber()?"":String.valueOf(l.getLoggingNumber()));
			 cell13.setCellValue(null==l.getLoggingAllWeight()?"":String.valueOf(l.getLoggingAllWeight()));
			 cell14.setCellValue(null==l.getLoggingPgNum()?"":String.valueOf(l.getLoggingPgNum()));
			 cell15.setCellValue(null==l.getLoggingPgRatio()?"":String.valueOf(l.getLoggingPgRatio()));
			 cell16.setCellValue(null==l.getLoggingErrNum()?"":String.valueOf(l.getLoggingErrNum()));
			 cell17.setCellValue(null==l.getLoggingErrRatio()?"":String.valueOf(l.getLoggingErrRatio()));
			 cell18.setCellValue(l.getNote());
			 i++;
			 loggingWages+=null==l.getLoggingWages()?0:l.getLoggingWages();
		     loggingErrWages+=null==l.getLoggingErrWages()?0:l.getLoggingErrWages();
		     loggingRealityWages+=null==l.getLoggingRealityWages()?0:l.getLoggingRealityWages();
		     loggingNumber+=null==l.getLoggingNumber()?0:l.getLoggingNumber();
			 loggingAllWeight+=null==l.getLoggingAllWeight()?0:l.getLoggingAllWeight();
			 loggingErrNum+=null==l.getLoggingErrNum()?0:l.getLoggingErrNum();
		}
		
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
		 cell14 = row.createCell(14);//说明
		 cell15 = row.createCell(15);//说明
		 cell16 = row.createCell(16);//说明
		 cell17 = row.createCell(17);//说明
		 cell18 = row.createCell(18);//说明
		 
	 	 cell0.setCellValue("合计");
		 cell6.setCellValue(loggingWages);
		 cell7.setCellValue(loggingErrWages);
		 cell8.setCellValue(loggingRealityWages);
		 cell12.setCellValue(loggingNumber);
		 cell13.setCellValue(loggingAllWeight);
		 cell15.setCellValue(loggingErrNum);
		 
        //通过Response把数据以Excel格式保存         
		response.reset();
		response.setContentType("application/msexcel;charset=UTF-8");
		try {
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ new String(("抛光工资"+DataUtil.formateDate() + ".xls").getBytes("GBK"),
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
