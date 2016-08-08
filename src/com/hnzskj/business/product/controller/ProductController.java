/*
 * @项目名称: crm
 * @文件名称: ProductController.java
 * @日期: 2015-10-27 下午03:58:00  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.business.common.controller.MfBaseController;
import com.hnzskj.business.product.bean.Product;
import com.hnzskj.business.product.service.IProductService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.JSONUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ProductController.java   <br/>
 * 类描述：   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-10-27 下午03:58:00   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-10-27 下午03:58:00   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@RequestMapping(value="product")
@Controller
public class ProductController extends MfBaseController {

	/**进入人员管理系统*/
	protected static String GET_PRODUCT_TITLE="进入产品管理模块";
	
	/**添加人员信息*/
	protected static String GET_PRODUCT_ADD="添加产品信息";
	
	/**修改人员信息*/
	protected static String GET_PRODUCT_UPD="修改产品信息";
	
	/**删除人员信息*/
	protected static String GET_PRODUCT_DEL="删除产品信息";
	
	/**
	 * 日志信息dao层注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 产品信息dao层注入
	 */
	@Autowired
	private IProductService productService = null;
	
	
	/**
	 * 
	 * 方法描述：进入人员管理模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="product")
	public ModelAndView product(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/business/product/index");
		addLogs(LOG_TYPE_OPERATE, GET_PRODUCT_TITLE, GET_PRODUCT_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(Constant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		return modelAndView;
	}
	

	/**
	 * 
	 * 方法描述：查询产品信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getProductPage")
	public String getProductPage(@ModelAttribute()Product product,@ModelAttribute()PageBean<Product> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = productService.searchProductList(pageBean, product);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
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
	@RequestMapping(value="addProduct")
	public String addProduct(@ModelAttribute()Product product,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_PRODUCT_ADD;
		if(null!=product.getProductUuid()&&!"".equals(product.getProductUuid())){
			msg = GET_PRODUCT_UPD;
			result = productService.updProduct(product);
		}else{
			result =productService.addProduct(product);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(product, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(product, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：删除一条产品信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-10-29 下午05:59:13<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("delProduct")
	public String delProduct(@ModelAttribute()Product product,HttpServletRequest request,HttpServletResponse response){
		boolean result = productService.delProduct(product);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_PRODUCT_DEL, JSONUtil.toJSONString(product, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_PRODUCT_DEL, JSONUtil.toJSONString(product, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：通过编号获取产品信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 上午08:38:16<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getProductByParam")
	public String getProductByParam(@ModelAttribute()Product product,HttpServletRequest request,HttpServletResponse response){
		Product  p = productService.getProductByParam(product);
		if(null!=p){
			writeToJson(new JsonBean(RESULT_STATE_OK, true,  p, null), response);
		}else{
			writeToJson(new JsonBean(RESULT_STATE_NULL, false,  null, null), response);
		}
		return null;
	}
	
	
}
