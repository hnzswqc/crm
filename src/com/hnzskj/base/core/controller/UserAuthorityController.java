/*
 * @项目名称: crm
 * @文件名称: UserAuthorityController.java
 * @日期: 2015-9-8 上午11:06:28  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.bean.UserAuthority;
import com.hnzskj.base.core.service.IUserAuthorityService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.controller.BaseController;

/**    
 * 项目名称：crm   <br/>
 * 类名称：UserAuthorityController.java   <br/>
 * 类描述：授权申请controller   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-8 上午11:06:28   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-8 上午11:06:28   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="userAuthority")
public class UserAuthorityController extends BaseController {

	/**
	 * service层注入
	 */
	@Autowired
	private IUserAuthorityService userAuthorityService = null;
	
	/**
	 * 申请变身功能
	 */
	private static String GET_USER_APPLY = "申请变身功能";
	
	/**
	 * 审批变身功能
	 */
	private static String GET_USER_APPROVE = "审批变身功能";
	
	/**
	 * 授权变身功能
	 */
	private static String GET_USER_AUTHORITY = "授权变身功能";
	
	/**
	 * 删除自己授权
	 */
	private static String GET_USER_AUTHORITY_DEL="删除个人申请（授权）变身";
	
	/**
	 * 管理员授权变身
	 */
	private static String GET_ADMIN_USER_AUTHORITY = "管理员授权变身功能";
	
	
	/**
	 * 
	 * 方法描述：分页查询授权信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-8 上午11:11:37<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getUserAuthorityList")
	public String getUserAuthorityList(@ModelAttribute()PageBean<UserAuthority> pageBean,@ModelAttribute()UserAuthority userAuthority,HttpServletRequest request,HttpServletResponse response){
		User user = getUserFromSession(request);
		if(null!=userAuthority.getType()&&!"".equals(userAuthority.getType())){
			if(MfConstant.USER_APPLY.equals(userAuthority.getType())){//本人申请
				userAuthority.setUserUuid(user.getUserUuid());
			}else if(MfConstant.USER_APPROVE.equals(userAuthority.getType())){//本人审核
				userAuthority.setType(MfConstant.USER_APPLY);//强制转换类别
				userAuthority.setAuthorityUserUuid(user.getUserUuid());
			}else if(MfConstant.OTHER_AUTHORITY.equals(userAuthority.getType())){
				userAuthority.setType(MfConstant.USER_AUTHORITY);//强制转换类别
				userAuthority.setUserUuid(user.getUserUuid());
			}else{
				userAuthority.setAuthorityUserUuid(user.getUserUuid());
			}
		}
		pageBean = userAuthorityService.getUserAuthorityList(pageBean, userAuthority);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：保存申请、授权<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-8 下午03:33:04<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addUserAuthority")
	public String addUserAuthority(String type,String userUuids,String userNames,String note,HttpServletRequest request,HttpServletResponse response){
		User user = getUserFromSession(request);
		boolean result = userAuthorityService.addUserAuthority(user, userUuids, type, note);
		String msg = GET_USER_APPLY;
		String content="";
		if(MfConstant.USER_APPLY.equals(type)){
			msg = GET_USER_APPLY;
			content=user.getUserName()+"申请变身为"+userNames;
		}else if(MfConstant.USER_AUTHORITY.equals(type)){
			msg = GET_USER_AUTHORITY;
			content=user.getUserName()+"授权变身为"+userNames;
		}else if(MfConstant.ADMIN_AUTHORITY.equals(type)){
			msg = GET_ADMIN_USER_AUTHORITY;
			content=user.getUserName()+"强制授权变身为"+userNames;
		}
		JsonBean jsonBean = null;
		if(result){
			jsonBean = new JsonBean(RESULT_STATE_OK, true);
			addLogs(LOG_TYPE_OPERATE, msg, content, LOG_STATE_SUCCESS,request);
		}else{
			jsonBean = new JsonBean(RESULT_STATE_NULL, false);
			addLogs(LOG_TYPE_OPERATE, msg, content, LOG_STATE_ERROR,request);
		}
		writeToJson(jsonBean, response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：更改审核状态<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-22 上午11:23:30<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="updUserAuthorityState")
	public String updUserAuthorityState(@ModelAttribute()UserAuthority userAuthority,HttpServletRequest request,HttpServletResponse response){
		boolean result = userAuthorityService.updUserAuthority(userAuthority);
		JsonBean jsonBean = null;
		String content="更新变身审核状态";
		if(result){
			jsonBean = new JsonBean(RESULT_STATE_OK, true);
			addLogs(LOG_TYPE_OPERATE, GET_USER_APPROVE, content, LOG_STATE_SUCCESS,request);
		}else{
			jsonBean = new JsonBean(RESULT_STATE_NULL, false);
			addLogs(LOG_TYPE_OPERATE, GET_USER_APPROVE, content, LOG_STATE_ERROR,request);
		}
		writeToJson(jsonBean, response);
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：取消个人申请或个人授权<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-22 下午02:43:06<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delUserAuthorityState")
	public String delUserAuthorityState(@ModelAttribute()UserAuthority userAuthority,HttpServletRequest request,HttpServletResponse response){
		boolean result = userAuthorityService.delUserAuthority(userAuthority);
		JsonBean jsonBean = null;
		if(result){
			jsonBean = new JsonBean(RESULT_STATE_OK, true);
			addLogs(LOG_TYPE_OPERATE, GET_USER_AUTHORITY_DEL, GET_USER_AUTHORITY_DEL, LOG_STATE_SUCCESS,request);
		}else{
			jsonBean = new JsonBean(RESULT_STATE_NULL, false);
			addLogs(LOG_TYPE_OPERATE, GET_USER_AUTHORITY_DEL, GET_USER_AUTHORITY_DEL, LOG_STATE_ERROR,request);
		}
		writeToJson(jsonBean, response);
		return null;
	}
	
	
	
}
