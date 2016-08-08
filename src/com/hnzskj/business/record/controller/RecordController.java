/*
 * @项目名称: crm
 * @文件名称: BzController.java
 * @日期: 2015-11-12 下午02:24:05  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.record.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.business.common.controller.MfBaseController;
import com.hnzskj.business.record.bean.Record;
import com.hnzskj.business.record.service.IRecordService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.JSONUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：BzController.java   <br/>
 * 类描述：员工档案记录   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-12 下午02:24:05   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-12 下午02:24:05   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="record")
public class RecordController extends MfBaseController{

	/**进入档案管理模块*/
	protected static String GET_RECORD_TITLE="进入档案管理模块";
	
	/**添加员工档案*/
	protected static String GET_RECORD_ADD="添加员工档案";
	
	/**修改员工档案*/
	protected static String GET_RECORD_UPD="修改员工档案";
	
	/**删除员工档案*/
	protected static String GET_RECORD_DEL="删除员工档案";
	
	/**导出员工档案*/
	protected static String GET_RECORD_EXPORT="导出员工档案";
	
	/**
	 * 日志信息dao层注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * service层注入
	 */
	@Autowired
	private IRecordService recordService= null;
	
	
	/**
	 * 
	 * 方法描述：进入档案管理模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="record")
	public ModelAndView record(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/business/record/index");
		addLogs(LOG_TYPE_OPERATE, GET_RECORD_TITLE, GET_RECORD_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：查询档案信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getRecordPage")
	public String getRecordPage(@ModelAttribute()Record record,@ModelAttribute()PageBean<Record> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = recordService.searchRecordPage(pageBean, record);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}

	
	
	/**
	 * 
	 * 方法描述：添加员工档案<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 下午02:36:34<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addRecord")
	public String addRecord(@RequestParam()MultipartFile file,@ModelAttribute()Record record,HttpServletRequest request,HttpServletResponse response){
		
		try {
			if(null!=file&&null!=file.getInputStream()){
				record.setRecordAttFileName(file.getOriginalFilename());
				record.setRecordAttType(file.getContentType());
				record.setRecordAttLength(file.getSize());
				record.setRecordAttContent(file.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean result = false;
		String msg = GET_RECORD_ADD;
		if(null!=record.getRecordUuid()&&!"".equals(record.getRecordUuid())){
			msg = GET_RECORD_UPD;
			result = recordService.updRecord(record);
		}else{
			result = recordService.addRecord(record);
		}
		JsonBean jsonBean = null;
		try {
			record.setRecordAttContent(null);
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(record, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(record, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：删除档案信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 下午05:36:40<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delRecord")
	public String delRecord(@ModelAttribute()Record record,HttpServletRequest request,HttpServletResponse response){
		boolean result =recordService.delRecord(record);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_RECORD_DEL, JSONUtil.toJSONString(record, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_RECORD_DEL, JSONUtil.toJSONString(record, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：导出档案信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 下午06:03:07<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="exportRecord")
	public String exportRecord(@ModelAttribute()Record record,HttpServletRequest request,HttpServletResponse response){
		record = recordService.getRecordByParam(record);
		InputStream in = null;
		OutputStream os = null;
		if(null!=record&&null!=record.getRecordAttFileName()&!"".equals(record.getRecordAttFileName())){
			in =record.getFileContent();
		}
		try {
			// 下载文件时显示的文件保存名称
			String s = java.net.URLEncoder.encode(record.getRecordAttFileName(), "utf-8");
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
}
