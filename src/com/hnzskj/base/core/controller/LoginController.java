/*
 * @项目名称: crm
 * @文件名称: LoginController.java
 * @日期: 2015-8-10 上午10:54:17  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.base.core.bean.Org;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.bean.Subsystem;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.bean.UserAuthority;
import com.hnzskj.base.core.service.IDicService;
import com.hnzskj.base.core.service.IOrgService;
import com.hnzskj.base.core.service.ISubsystemService;
import com.hnzskj.base.core.service.IUserService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：LoginController.java   <br/>
 * 类描述： 用户登录  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-10 上午10:54:17   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-10 上午10:54:17   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="/")
public class LoginController extends MfBaseController {

	
	/**
	 * 日志描述-登录信息
	 */
	public static String LOG_TITLE_LOGIN="登录主系统";
	
	/**
	 * 修改密码
	 */
	protected static String LOG_UPD_PASSWORD="修改密码";
	
	/**
	 * 使用变身功能
	 */
	protected static String LOG_CHANGE_USER="变身";
	
	/**
	 * 数据字典业务注入
	 */
	@Autowired
	private IDicService dicService = null;
	
	/**
	 * 用户业务信息注入
	 */
	@Autowired
	private IUserService userService = null;
	
	/**
	 * 业务注入
	 */
	@Autowired
	private ISubsystemService subsystemService = null;
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IOrgService orgService = null;
	
	
	
	
	/**
	 * 
	 * 方法描述：用户登录<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="login")
	public ModelAndView login(String userId,String password,HttpServletRequest request,HttpServletResponse response){
		
		//如果已经登录，则不需要再进行登录
		User sessionUser = getUserFromSession(request);
		if(null!=sessionUser){
			ModelAndView modelAndView = new ModelAndView("/base/mainFrame/index");
			//系统参数初始化
			setInitParam(getUserFromSession(request),request);
			//获取第一个子系统信息。
			modelAndView.addObject(MfConstant.REQUEST_SUBSYTEM, getSubsystem(sessionUser));
			return modelAndView;
		}
		
		
		//超管。
		if(Constant.SUPER_USER_ID.equals(userId)&&Constant.SUPER_USER_PASSWORD.equals(password)){
			User user = new User();
			user.setUserUuid(Constant.SUPER_USER_UUID);
			user.setUserId(userId);
			user.setUserPassword(password);
			user.setUserName(Constant.SUPER_USER_NAME);
			//系统参数初始化
			setInitParam(user,request);
			ModelAndView modelAndView =  new ModelAndView("/base/mainFrame/index");
			//获取第一个子系统信息。
			modelAndView.addObject(MfConstant.REQUEST_SUBSYTEM, getSubsystem(user));
			return modelAndView;
		}
		
		User user = userService.login(userId, password);
		ModelAndView modelAndView =new ModelAndView("/login");;
		if(null!=user){
			modelAndView = new ModelAndView("/base/mainFrame/index");
			//系统参数初始化
			setInitParam(user,request);
			addLogs(LOG_TYPE_OPERATE, LOG_TITLE_LOGIN, user.getUserName()+"成功登录系统", LOG_STATE_SUCCESS,request);
		}else{
			modelAndView.addObject("loginError", "用户名或密码错误");
		}
		//获取第一个子系统信息。
		modelAndView.addObject(MfConstant.REQUEST_SUBSYTEM, getSubsystem(user));
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：获取第一个子系统信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-6 下午05:19:36<br/>         
	 * @param <br/>   
	 * @return Subsystem<br/>   
	 * @version   1.0<br/>
	 */
	private Subsystem getSubsystem(User user){
		Subsystem subsystem = new Subsystem();
		subsystem.setUser(user);
		PageBean<Subsystem> pageBean = subsystemService.getSubsystemList(null, subsystem);
		if(null!=pageBean.getList()&&pageBean.getList().size()>0){
			subsystem = pageBean.getList().get(0);
			return subsystem;
		}
		return null;
	}
	/**
	 * 
	 * 方法描述：用户登录<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		//如果已经登录，则不需要再进行登录
		if(null!=getUserFromSession(request)){
			ModelAndView modelAndView = new ModelAndView("/base/mainFrame/index");
			//系统参数初始化
			setInitParam(getUserFromSession(request),request);
			//获取第一个子系统信息。
			modelAndView.addObject(MfConstant.REQUEST_SUBSYTEM, getSubsystem(getUserFromSession(request)));
			return modelAndView;
		}
		ModelAndView modelAndView =new ModelAndView("/login");;
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
	private void setInitParam(User user,HttpServletRequest request){
		Dic dic_title= dicService.getDicByKey(Constant.INIT_PARAM, Constant.PRJ_TITLE);
		if(null!=dic_title){
			setSession(Constant.PRJ_TITLE, dic_title.getId(), request);
		}
		Dic dic_css= dicService.getDicByKey(Constant.INIT_PARAM, Constant.CSS_STYLE);
		if(null!=dic_css){
			setSession(Constant.CSS_STYLE, dic_css.getId(), request);
		}
		//审核通过状态
		Dic dic_authority_state= dicService.getDicByKey(MfConstant.DIC_AUTHORITY_STATE, MfConstant.DIC_AUTHORITY_STATE_AUDIT_THROUGH);
		if(null!=dic_authority_state){
			setSession(MfConstant.DIC_AUTHORITY_STATE_AUDIT_THROUGH, dic_authority_state.getId(), request);
		}
		//获取用户角色
		List<Role> list = userService.getUserRoleList(user);
		user.setRoleList(list);
		String roles="";
		int i=0;
		for (Role role : list) {
			if(role.getCount()>0){
				if(i>0){
					roles+=",";	
				}
				roles+=role.getRoleName();
				i++;
			}
		}
		//存储用户角色
		setSession(Constant.SESSION_USER_ROLE, roles, request);
		//获取当前人员所属组织
		Org org = new Org();
		org.setUserUuid(user.getUserUuid());
		PageBean<Org> pageBean = orgService.getOrgList(null, org);
		if(null!=pageBean.getList()&&pageBean.getList().size()>0){
			user.setOrgList(pageBean.getList());
		}
		setSession(Constant.SESSION_USER, user, request);
	}
	
	/**
	 * 
	 * 方法描述：退出系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-1 上午10:11:35<br/>         
	 * @param <br/>   
	 * @return ModelAndView<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="exit")
	public String exit(HttpServletRequest request,HttpServletResponse response){
		setSession(Constant.SESSION_USER, null, request);
		removeSession(Constant.SESSION_USER, request);
		writeToJson(new JsonBean(RESULT_STATE_OK,true), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：修改密码<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-6 上午09:55:30<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="updPassword")
	public String updPassword(@ModelAttribute()User user,HttpServletRequest request,HttpServletResponse response){
		User u = userService.login(user.getUserId(), user.getUserPassword());
		if(null!=u){
			u.setUserPassword(user.getNewPassword());
			boolean result = userService.updPassword(u);
			if(result){
				addLogs(LOG_TYPE_OPERATE, LOG_UPD_PASSWORD, user.getUserName()+"执行修改密码操作", LOG_STATE_SUCCESS,request);
				writeToJson(new JsonBean(RESULT_STATE_OK,true, "密码修改成功", 0), response);
			}else{
				addLogs(LOG_TYPE_OPERATE, LOG_UPD_PASSWORD, user.getUserName()+"执行修改密码操作", LOG_STATE_ERROR,request);
				writeToJson(new JsonBean(RESULT_STATE_NULL,false, "密码修改失败", 0), response);
			}
		}else{
			writeToJson(new JsonBean(RESULT_STATE_NULL,false, "原密码不正确", 0), response);
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：变身功能<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-22 下午01:58:53<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="changeUser")
	public String changeUser(@ModelAttribute()UserAuthority userAuthority,HttpServletRequest request,HttpServletResponse response){
		User user = userService.getUserByUuid(userAuthority.getAuthorityUserUuid());
		setInitParam(user, request);
		setSession(Constant.SESSION_USERAUTHORITY, userAuthority, request);
		addLogs(LOG_TYPE_OPERATE, LOG_CHANGE_USER, userAuthority.getUserName()+"变身为"+userAuthority.getAuthorityUserName(), LOG_STATE_SUCCESS,request);
		writeToJson(new JsonBean(RESULT_STATE_OK,true, "变身成功", 0), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：变身功能<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-22 下午01:58:53<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="changeBackUser")
	public String changeBackUser(HttpServletRequest request,HttpServletResponse response){
		UserAuthority userAuthority = (UserAuthority) getFromSession(Constant.SESSION_USERAUTHORITY,request);
		if(null!=userAuthority){
			User user = userService.getUserByUuid(userAuthority.getUserUuid());
			setInitParam(user, request);
			removeSession(Constant.SESSION_USERAUTHORITY, request);
			addLogs(LOG_TYPE_OPERATE, LOG_CHANGE_USER, userAuthority.getAuthorityUserName()+"还原为"+userAuthority.getUserName(), LOG_STATE_SUCCESS,request);
			writeToJson(new JsonBean(RESULT_STATE_OK,true, "还原成功", 0), response);	
		}else{
			writeToJson(new JsonBean(RESULT_STATE_OK,true, "还原成功", 0), response);	
		}
		return null;
	}
	
	
	
}
