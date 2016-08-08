/*
 * @项目名称: crm
 * @文件名称: PurchaseController.java
 * @日期: 2015-11-27 下午06:36:02  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.officegoods.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.oa.common.controller.MfBaseController;
import com.hnzskj.oa.common.util.MfConstant;
import com.hnzskj.oa.officegoods.bean.Purchase;
import com.hnzskj.oa.officegoods.service.IPurchaseService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：PurchaseController.java   <br/>
 * 类描述：   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-27 下午06:36:02   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-27 下午06:36:02   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@RequestMapping(value="purchase")
@Controller
public class PurchaseController extends MfBaseController {


	/**进入物品采购模块*/
	protected static String GET_PURCHASE_TITLE="进入物品采购模块";
	
	/**添加采购信息*/
	protected static String GET_PURCHASE_ADD="添加采购信息";
	
	/**修改采购信息*/
	protected static String GET_PURCHASE_UPD="修改采购信息";
	
	/**删除采购信息*/
	protected static String GET_PURCHASE_DEL="删除采购信息";
	
	/**导出采购信息*/
	protected static String GET_PURCHASE_EXPORT="导出采购信息";
	
	/**
	 * 日志信息业务层注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IPurchaseService purchaseService = null;
	
	
	/**
	 * 
	 * 方法描述：进入物品采购模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="purchase")
	public ModelAndView purchase(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/oa/purchase/index");
		addLogs(LOG_TYPE_OPERATE, GET_PURCHASE_TITLE, GET_PURCHASE_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		modelAndView.addObject("DIC_PURCHASE_TYPE",MfConstant.DIC_PURCHASE_TYPE);
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：查询物品采购信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getPurchasePage")
	public String getPurchasePage(@ModelAttribute()Purchase purchase,@ModelAttribute()PageBean<Purchase> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = purchaseService.getPurchaseList(pageBean, purchase);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：添加物品采购信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 下午02:36:34<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addPurchase")
	public String addPurchase(@ModelAttribute()Purchase purchase,HttpServletRequest request,HttpServletResponse response){
		
		boolean result = false;
		String msg = GET_PURCHASE_ADD;
		if(null!=purchase.getPurchaseUuid()&&!"".equals(purchase.getPurchaseUuid())){
			msg = GET_PURCHASE_UPD;
			result = purchaseService.updPurchase(purchase);
		}else{
			User user = getUserFromSession(request);
			if(null!=user){
				purchase.setCreateUserName(user.getUserName());
				purchase.setCreateUserNumber(user.getUserNumber());
				purchase.setCreateUserUuid(user.getUserUuid());
			}
			result = purchaseService.addPurchase(purchase);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(purchase, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(purchase, true), LOG_STATE_ERROR,request);
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
	@RequestMapping(value="delPurchase")
	public String delPurchase(@ModelAttribute()Purchase purchase,HttpServletRequest request,HttpServletResponse response){
		boolean result =purchaseService.delPurchase(purchase);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_PURCHASE_DEL, JSONUtil.toJSONString(purchase, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_PURCHASE_DEL, JSONUtil.toJSONString(purchase, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
