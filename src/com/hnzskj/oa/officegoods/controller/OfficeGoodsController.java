/*
 * @项目名称: crm
 * @文件名称: OfficeGoodsController.java
 * @日期: 2015-11-28 下午07:58:02  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.officegoods.controller;

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

import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.oa.common.controller.MfBaseController;
import com.hnzskj.oa.common.util.MfConstant;
import com.hnzskj.oa.officegoods.bean.OfficeGoods;
import com.hnzskj.oa.officegoods.service.IOfficeGoodsService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：OfficeGoodsController.java   <br/>
 * 类描述：   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-28 下午07:58:02   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-28 下午07:58:02   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="ogs")
public class OfficeGoodsController extends MfBaseController {

		/**进入办公物品领用模块*/
		protected static String GET_OFFICEGOODS_TITLE="进入办公物品领用模块";
		
		/**添加办公物品领用信息*/
		protected static String GET_OFFICEGOODS_ADD="添加办公物品领用信息";
		
		/**修改办公物品领用信息*/
		protected static String GET_OFFICEGOODS_UPD="修改办公物品领用信息";
		
		/**删除办公物品领用信息*/
		protected static String GET_OFFICEGOODS_DEL="删除办公物品领用信息";
		
		/**导出办公物品领用信息*/
		protected static String GET_OFFICEGOODS_EXPORT="导出办公物品领用信息";
		
		/**
		 * 日志信息业务层注入
		 */
		@Autowired
		private IOperateService operateService = null;
		
		/**
		 * 业务注入
		 */
		@Autowired
		private IOfficeGoodsService officeGoodsService = null;
		
		/**
		 * 
		 * 方法描述：进入办公物品领用信息模块<br/>
		 * 创建人：King   <br/>
		 * 创建时间：2015-8-10 上午10:55:38<br/>         
		 * @param <br/>   
		 * @return String<br/>   
		 * @version   1.0<br/>
		 */
		@RequestMapping(value="officeGoods")
		public ModelAndView officeGoods(String id,HttpServletRequest request,HttpServletResponse response){
			ModelAndView modelAndView = new ModelAndView("/oa/officeGoods/index");
			addLogs(LOG_TYPE_OPERATE, GET_OFFICEGOODS_TITLE, GET_OFFICEGOODS_TITLE, LOG_STATE_SUCCESS,request);
			modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
			modelAndView.addObject("DIC_PURCHASE_TYPE",MfConstant.DIC_PURCHASE_TYPE);
			modelAndView.addObject("DIC_OFFICEGOODS_STATE",MfConstant.DIC_OFFICEGOODS_STATE);
			return modelAndView;
		}
	
		/**
		 * 
		 * 方法描述：查询物品领取信息<br/>
		 * 创建人：King   <br/>
		 * 创建时间：2015-8-25 下午05:41:23<br/>         
		 * @param type 用于查询变身人员<br/>   
		 * @return String<br/>   
		 * @version   1.0<br/>
		 */
		@RequestMapping("getOfficeGoodsPage")
		public String getOfficeGoodsPage(@ModelAttribute()OfficeGoods officeGoods,@ModelAttribute()PageBean<OfficeGoods> pageBean,HttpServletRequest request,HttpServletResponse response){
			pageBean = officeGoodsService.searchOfficeGoodsList(pageBean, officeGoods);
			writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
			return null;
		}
		
		/**
		 * 
		 * 方法描述：添加物品领取信息<br/>
		 * 创建人：开发部笔记本   <br/>
		 * 创建时间：2015-11-16 下午02:36:34<br/>         
		 * @param <br/>   
		 * @return <br/>   
		 * @version   1.0<br/>
		 */
		@RequestMapping(value="addOfficeGoods")
		public String addOfficeGoods(@RequestParam()MultipartFile file,@ModelAttribute()OfficeGoods officeGoods,HttpServletRequest request,HttpServletResponse response){
			
			try {
				if(null!=file&&0!=file.getSize()){
					officeGoods.setOgsFileContent(file.getBytes());
					officeGoods.setOgsFileName(file.getOriginalFilename());
					officeGoods.setOgsFileType(file.getContentType());
					officeGoods.setOgsFileSize(file.getSize());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			boolean result = false;
			String msg = GET_OFFICEGOODS_ADD;
			if(null!=officeGoods.getOgsUuid()&&!"".equals(officeGoods.getOgsUuid())){
				msg = GET_OFFICEGOODS_UPD;
				result = officeGoodsService.updOfficeGoods(officeGoods);
			}else{
				User u= getUserFromSession(request);
				if(null!=u){
					officeGoods.setCreateUserName(u.getUserName());
					officeGoods.setCreateUserNumber(u.getUserNumber());
					officeGoods.setCreateUserUuid(u.getUserUuid());
				}
				result = officeGoodsService.addOfficeGoods(officeGoods);
			}
			JsonBean jsonBean = null;
			try {
				officeGoods.setOgsFileContent(null);
				if(result){
					jsonBean = new JsonBean(RESULT_STATE_OK, true);
					addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(officeGoods, true), LOG_STATE_SUCCESS,request);
				}else{
					jsonBean = new JsonBean(RESULT_STATE_NULL, false);
					addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(officeGoods, true), LOG_STATE_ERROR,request);
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
		@RequestMapping(value="delOfficeGoods")
		public String delOfficeGoods(@ModelAttribute()OfficeGoods officeGoods,HttpServletRequest request,HttpServletResponse response){
			boolean result =officeGoodsService.delOfficeGoods(officeGoods);
			JsonBean jsonBean = null;
			try {
				if(result){
					jsonBean = new JsonBean(RESULT_STATE_OK, true);
					addLogs(LOG_TYPE_OPERATE, GET_OFFICEGOODS_DEL, JSONUtil.toJSONString(officeGoods, true), LOG_STATE_SUCCESS,request);
				}else{
					jsonBean = new JsonBean(RESULT_STATE_NULL, false);
					addLogs(LOG_TYPE_OPERATE, GET_OFFICEGOODS_DEL, JSONUtil.toJSONString(officeGoods, true), LOG_STATE_ERROR,request);
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
		@RequestMapping(value="showOfficeGoods")
		public String showOfficeGoods(@ModelAttribute()OfficeGoods officeGoods,HttpServletRequest request,HttpServletResponse response){
			officeGoods = officeGoodsService.getOfficeGoodsByParam(officeGoods);
			InputStream in = null;
			OutputStream os = null;
			if(null!=officeGoods&&null!=officeGoods.getOgsFileName()&!"".equals(officeGoods.getOgsFileName())){
				in =officeGoods.getFileContent();
			}
			try {
				// 下载文件时显示的文件保存名称
				String s = java.net.URLEncoder.encode(officeGoods.getOgsFileName(), "utf-8");
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
