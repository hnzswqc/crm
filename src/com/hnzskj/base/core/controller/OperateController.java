/*
 * @项目名称: crm
 * @文件名称: OperateController.java
 * @日期: 2015-8-28 下午04:10:09  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.core.bean.Operate;
import com.hnzskj.base.core.service.IOperateService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：OperateController.java   <br/>
 * 类描述：功能操作逻辑控制层   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 下午04:10:09   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 下午04:10:09   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="operate")
public class OperateController extends MfBaseController {

	/**添加操作功能*/
	private static String GET_OPERATE_ADD="添加操作功能";
	/**修改操作功能*/
	private static String GET_OPERATE_UPD="修改操作功能";
	/**删除操作功能*/
	private static String GET_OPERATE_DEL="删除操作功能";
	
	/**
	 * 业务层注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 
	 * 方法描述：查询列表信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getOperatePage")
	public String getOperatePage(@ModelAttribute()Operate operate,@ModelAttribute()PageBean<Operate> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = operateService.getOperateList(pageBean, operate);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：添加信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午06:00:28<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addOperate")
	public String addOperate(@ModelAttribute()Operate operate,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_OPERATE_ADD;
		if(null!=operate.getOperateUuid()&&!"".equals(operate.getOperateUuid())){
			result = operateService.updOperate(operate);
			msg = GET_OPERATE_UPD;
		}else{
			result =  operateService.addOperate(operate);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(operate, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(operate, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：删除用户<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 上午08:56:53<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delOperate")
	public String delOperate(@ModelAttribute()Operate operate,HttpServletRequest request,HttpServletResponse response){
		boolean result = operateService.delOperate(operate);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_OPERATE_DEL, JSONUtil.toJSONString(operate, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_OPERATE_DEL, JSONUtil.toJSONString(operate, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：验证Key否已经被占用<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午03:19:58<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="validateOperateKey")
	public String validateOperateKey(@ModelAttribute()Operate operate,HttpServletRequest request,HttpServletResponse response){
		operate =operateService.getOperateByKey(operate);
		if(null!=operate){
			writeToJson(new JsonBean(RESULT_STATE_OK,true,operate,0), response);
		}else{
			writeToJson(new JsonBean(RESULT_STATE_NULL,false), response);
		}
		return null;
	}
	
}
