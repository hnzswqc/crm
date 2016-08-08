/*
 * @项目名称: crm
 * @文件名称: UserController.java
 * @日期: 2015-8-25 下午03:48:41  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Attachment;
import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.base.core.bean.Org;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.service.IAttachmentService;
import com.hnzskj.base.core.service.IDicService;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.base.core.service.IOrgService;
import com.hnzskj.base.core.service.IUserService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.JSONUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：UserController.java   <br/>
 * 类描述：   人员管理模块<br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-25 下午03:48:41   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-25 下午03:48:41   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="user")
public class UserController extends MfBaseController{

	/**进入人员管理系统*/
	protected static String GET_USER_TITLE="进入人员管理系统";
	
	/**添加人员信息*/
	protected static String GET_USER_ADD="添加人员信息";
	
	/**修改人员信息*/
	protected static String GET_USER_UPD="修改人员信息";
	
	/**删除人员信息*/
	protected static String GET_USER_DEL="删除人员信息";
	
	/**人员部门调离*/
	protected static String GET_USER_CHANGE_ORG="人员部门调离";
	
	/**密码重置*/
	protected static String GET_USER_DEFAULT_PASSWORD="密码重置";
	
	/**为用户分配权限*/
	protected static String GET_USER_ROLE_ADD="为用户分配权限";
	
	/**为部门分配权限*/
	protected static String GET_ORG_ROLE_ADD="为部门分配权限";
	
	/**添加附件*/
	private static String GET_ATTACHMENT_ADD="添加附件";
	
	/**删除*/
	private static String GET_ATTACHMENT_DEL="删除";
	
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IUserService userService = null;
	
	/**
	 * 组织机构注入
	 */
	@Autowired
	private IOrgService orgService = null;
	

	/**
	 * 操作注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	
	/**
	 * 数据字典业务注入
	 */
	@Autowired
	private IDicService dicService = null;
	
	/**
	 * 附件业务注入
	 */
	@Autowired
	private IAttachmentService attcAttachmentService = null;
	
	
	/**
	 * 
	 * 方法描述：进入人员管理模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="user")
	public ModelAndView user(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/base/user/index");
		addLogs(LOG_TYPE_OPERATE, GET_USER_TITLE, GET_USER_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(MfConstant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		setInitParam(request,modelAndView);
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
	private void setInitParam(HttpServletRequest request,ModelAndView modelAndView){
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
		
		modelAndView.addObject("DIC_RESIGNED_TYPE",com.hnzskj.oa.common.util.MfConstant.DIC_RESIGNED_TYPE);
		modelAndView.addObject("DIC_RESIGNED_MONEY_TYPE",com.hnzskj.oa.common.util.MfConstant.DIC_RESIGNED_MONEY_TYPE);
		modelAndView.addObject("DIC_BANK_CARD_STATE",com.hnzskj.oa.common.util.MfConstant.DIC_BANK_CARD_STATE);
		modelAndView.addObject("DIC_USER_STATE_ACTIVITY",com.hnzskj.oa.common.util.MfConstant.DIC_USER_STATE_ACTIVITY);
		modelAndView.addObject("DIC_RECORD_ID_CARD",com.hnzskj.oa.common.util.MfConstant.DIC_RECORD_ID_CARD);
		modelAndView.addObject("DIC_RECORD_JOIN_MSG",com.hnzskj.oa.common.util.MfConstant.DIC_RECORD_JOIN_MSG);
		modelAndView.addObject("DIC_RECORD_JOIN_TJ_MSG",com.hnzskj.oa.common.util.MfConstant.DIC_RECORD_JOIN_TJ_MSG);
		modelAndView.addObject("DIC_RECORD_JOIN_SQZZB",com.hnzskj.oa.common.util.MfConstant.DIC_RECORD_JOIN_SQZZB);
		
	}
	
	
	/**
	 * 
	 * 方法描述：查询人员信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getUserPage")
	public String getUserPage(@ModelAttribute()User user,String type,@ModelAttribute()PageBean<User> pageBean,HttpServletRequest request,HttpServletResponse response){
		if(null!=type&&!"".equals(type)){
			user.setUserUuid(getUserFromSession(request).getUserUuid());
			pageBean = userService.getUserList(pageBean, user,type);	
		}else{
			pageBean = userService.getUserList(pageBean, user);
		}
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：查询人员数量信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param type 用于查询变身人员<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getUserCount")
	public String getUserCount(@ModelAttribute()PageBean<User> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = userService.getUserList(null, new User());
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
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
		String msg = GET_USER_ADD;
		if(null!=user.getUserUuid()&&!"".equals(user.getUserUuid())){
			result = userService.updUser(user);
			msg = GET_USER_UPD;
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
	 * 方法描述：添加用户信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午06:00:28<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addNormalUser")
	public String addNormalUser(@ModelAttribute()User user,@RequestParam()MultipartFile file1,@RequestParam()MultipartFile file2,@RequestParam()MultipartFile file3,@RequestParam()MultipartFile file4,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_USER_ADD;
		//默认正式员工
		user.setUserState(com.hnzskj.oa.common.util.MfConstant.DIC_USER_STATE_ACTIVITY);
		if(null!=user.getUserUuid()&&!"".equals(user.getUserUuid())){
			result = userService.updUser(user);
			msg = GET_USER_UPD;
		}else{
			result = userService.addUser(user);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				attcAttachmentService.addAttachment(setAttachmentList(user, file1, file2, file3, file4, request));
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
	 * 方法描述：上传的附件信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-11 下午09:41:08<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private List<Attachment> setAttachmentList(User u,MultipartFile file1,MultipartFile file2,MultipartFile file3,MultipartFile file4,HttpServletRequest request){
		List<Attachment> list = new ArrayList<Attachment>();
		try {
			User user = getUserFromSession(request);
			
			//身份证复印件
			if(StringUtils.isNotEmpty(file1.getOriginalFilename())){
				Attachment att= new Attachment();
				att.setFileContent(file1.getBytes());
				att.setFileName(file1.getOriginalFilename());
				att.setFileType(file1.getContentType());
				att.setForeignUUid(u.getUserUuid());
				att.setAttType(MfConstant.DIC_RECORD_ID_CARD);
				att.setCreateUserUuid(null!=user?user.getUserUuid():null);
				list.add(att);
			}
			//体检报告
			if(StringUtils.isNotEmpty(file2.getOriginalFilename())){
				Attachment att= new Attachment();
				att.setFileContent(file2.getBytes());
				att.setFileName(file2.getOriginalFilename());
				att.setFileType(file2.getContentType());
				att.setForeignUUid(u.getUserUuid());
				att.setAttType(MfConstant.DIC_RECORD_JOIN_TJ_MSG);
				att.setCreateUserUuid(null!=user?user.getUserUuid():null);
				list.add(att);
			}
			//入职应聘表
			if(StringUtils.isNotEmpty(file3.getOriginalFilename())){
				Attachment att= new Attachment();
				att.setFileContent(file3.getBytes());
				att.setFileName(file3.getOriginalFilename());
				att.setFileType(file3.getContentType());
				att.setForeignUUid(u.getUserUuid());
				att.setAttType(MfConstant.DIC_RECORD_JOIN_MSG);
				att.setCreateUserUuid(null!=user?user.getUserUuid():null);
				list.add(att);
			}
			//申请转正表
			if(StringUtils.isNotEmpty(file4.getOriginalFilename())){
				Attachment att= new Attachment();
				att.setFileContent(file4.getBytes());
				att.setFileName(file4.getOriginalFilename());
				att.setFileType(file4.getContentType());
				att.setForeignUUid(u.getUserUuid());
				att.setAttType(MfConstant.DIC_RECORD_JOIN_SQZZB);
				att.setCreateUserUuid(null!=user?user.getUserUuid():null);
				list.add(att);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
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
	@RequestMapping(value="delUser")
	public String delUser(@ModelAttribute()User user,HttpServletRequest request,HttpServletResponse response){
		boolean result = userService.delUser(user);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_USER_DEL, JSONUtil.toJSONString(user, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_USER_DEL, JSONUtil.toJSONString(user, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：组织机构树形菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午10:33:04<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getOrgTreeList")
	public String getOrgTreeList(String orgParentUuid,@ModelAttribute()User user,HttpServletRequest request,HttpServletResponse response){
		if(null==orgParentUuid||"".equals(orgParentUuid)){
			orgParentUuid = MfConstant.DEFAULT_ROOT_UUID;
		}
		String json = getOrgList(orgParentUuid,user.getUserUuid());
		writeToStr(json, response);
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：封装树形菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-20 下午02:14:51<br/>         
	 * @param <br/>   
	 * @return List<TreeNode><br/>   
	 * @version   1.0<br/>
	 */
	private String getOrgList(String orgParentUuid,String userUuid){
		    List<TreeNode> list = orgService.getTreeNodeList(orgParentUuid);
			StringBuffer sb = new StringBuffer(200);
			if(null!=list&&list.size()>0){
				sb.append("[");
				for (TreeNode treeNode : list) {
					sb.append("{id:'").append(treeNode.getId()).append("',");
					sb.append("text:'").append(treeNode.getText()).append("',");
					sb.append("expanded:").append(true).append(",");
					sb.append("icon:'").append(treeNode.getIcon()).append("',");
					sb.append("checked:").append(userService.getOrgUser(userUuid, treeNode.getId())).append(",");
					String children =this.getOrgList(treeNode.getId(),userUuid);
					if(null!=children&&!"".equals(children)){
						sb.append("children:").append(children).append("},");
					}else{
						sb.append("leaf:").append(true).append("},");
					}
				}
				if(sb.length()>1){
					sb.delete(sb.length()-1, sb.length());
				}
				sb.append("]");
			}
		return sb.toString();
	}
	
	/**
	 * 
	 * 方法描述：部门变更，可以设置一个人员多个部门<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午02:21:14<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="changeUserOrg")
	public String changeUserOrg(@RequestParam()String orgUuid,String orgName,@RequestParam()String userUuid,String userName,HttpServletRequest request,HttpServletResponse response){
		boolean result = userService.addOrgUser(orgUuid, userUuid);
		JsonBean jsonBean = null;
		if(result){
			jsonBean = new JsonBean(RESULT_STATE_OK, true);
			addLogs(LOG_TYPE_OPERATE, GET_USER_DEL,userName+"部门变更为"+orgName, LOG_STATE_SUCCESS,request);
		}else{
			jsonBean = new JsonBean(RESULT_STATE_NULL, false);
			addLogs(LOG_TYPE_OPERATE, GET_USER_DEL, userName+"部门变更为"+orgName, LOG_STATE_ERROR,request);
		}
		writeToJson(jsonBean, response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：验证用户userId是否已经被占用<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午03:19:58<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="validateUserId")
	public String validateUserId(@RequestParam()String userId,HttpServletRequest request,HttpServletResponse response){
		User user = userService.getUserByUserId(userId);
		if(null!=user){
			writeToJson(new JsonBean(RESULT_STATE_OK,true,user,0), response);
		}else{
			writeToJson(new JsonBean(RESULT_STATE_NULL,false), response);
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：验证用户userId是否已经被占用<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午03:19:58<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="validateUserNumber")
	public String validateUserNumber(@RequestParam()String userNumber,HttpServletRequest request,HttpServletResponse response){
		User user = userService.getUserByUserNumber(userNumber);
		if(null!=user){
			writeToJson(new JsonBean(RESULT_STATE_OK,true,user,0), response);
		}else{
			writeToJson(new JsonBean(RESULT_STATE_NULL,false), response);
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：为用户分配角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 上午08:38:16<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getUserRoleList")
	public String getUserRoleList(@ModelAttribute()User user,HttpServletRequest request,HttpServletResponse response){
		List<Role> list = userService.getUserRoleList(user);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, list, list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：为用户分配角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 上午08:38:16<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getUserRoleListByUserUuid")
	public String getUserRoleListByUserUuid(@ModelAttribute()User user,HttpServletRequest request,HttpServletResponse response){
		List<Role> list = userService.getUserRoleList(user.getUserUuid());
		writeToJson(new JsonBean(RESULT_STATE_OK, true, list, list.size()), response);
		return null;
	}
	
	

	/**
	 * 
	 * 方法描述：为部门分配角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 上午08:38:16<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getOrgRoleList")
	public String getOrgRoleList(@ModelAttribute()Org org,HttpServletRequest request,HttpServletResponse response){
		List<Role> list = userService.getOrgRoleList(org);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, list, list.size()), response);
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：为人员分配角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 下午03:00:13<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addUserRole")
	public String addUserRole(@RequestParam()String userUuid,@RequestParam()String userName,@RequestParam()String roleUuids,@RequestParam()String roleNames,HttpServletRequest request,HttpServletResponse response){
		boolean result = userService.addUserRole(userUuid, roleUuids);
		if(result){
			writeToJson(new JsonBean(RESULT_STATE_OK,true), response);
			addLogs(LOG_TYPE_OPERATE, GET_USER_ROLE_ADD,userName+"角色变更为"+roleNames, LOG_STATE_SUCCESS,request);
		}else{
			writeToJson(new JsonBean(RESULT_STATE_NULL,false), response);
			addLogs(LOG_TYPE_OPERATE, GET_USER_ROLE_ADD,userName+"角色变更为"+roleNames, LOG_STATE_ERROR,request);
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：为人员分配角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 下午03:00:13<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addOrgRole")
	public String addOrgRole(@RequestParam()String orgUuid,@RequestParam()String orgName,@RequestParam()String roleUuids,@RequestParam()String roleNames,HttpServletRequest request,HttpServletResponse response){
		boolean result = userService.addOrgRole(orgUuid, roleUuids);
		if(result){
			writeToJson(new JsonBean(RESULT_STATE_OK,true), response);
			addLogs(LOG_TYPE_OPERATE, GET_USER_ROLE_ADD,orgName+"角色变更为"+roleNames, LOG_STATE_SUCCESS,request);
		}else{
			writeToJson(new JsonBean(RESULT_STATE_NULL,false), response);
			addLogs(LOG_TYPE_OPERATE, GET_USER_ROLE_ADD,orgName+"角色变更为"+roleNames, LOG_STATE_ERROR,request);
		}
		return null;
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
	private String getDefaultPassword(){
		Dic dic= dicService.getDicByKey(Constant.INIT_PARAM, Constant.USER_DEFAULT_PASSWORD);
		if(null!=dic){
			return dic.getId();
		}
		return "1";
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
		String defaultPassword=getDefaultPassword();
		user.setUserPassword(getDefaultPassword());
		boolean result = userService.updPassword(user);
		if(result){
			addLogs(LOG_TYPE_OPERATE, GET_USER_DEFAULT_PASSWORD, user.getUserName()+"密码重置为"+defaultPassword, LOG_STATE_SUCCESS,request);
			writeToJson(new JsonBean(RESULT_STATE_OK,true, "密码重置成功", 0), response);
		}else{
			addLogs(LOG_TYPE_OPERATE, GET_USER_DEFAULT_PASSWORD,user.getUserName()+"密码重置为"+defaultPassword, LOG_STATE_ERROR,request);
			writeToJson(new JsonBean(RESULT_STATE_NULL,false, "密码重置失败", 0), response);
		}
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：为人员分配角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 下午03:00:13<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getUserNumber")
	public String getUserNumber(@RequestParam()String orgUuid,HttpServletRequest request,HttpServletResponse response){
		String result = userService.getUserNumber(orgUuid);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, result, 1), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：单个添加附件<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-13 下午03:27:52<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addAttachment")
	public String addAttachment(@ModelAttribute() Attachment att,@RequestParam()MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		try {
			
			//申请转正表
			if(StringUtils.isNotEmpty(file.getOriginalFilename())){
				User user = getUserFromSession(request);
				att.setFileContent(file.getBytes());
				att.setFileName(file.getOriginalFilename());
				att.setFileType(file.getContentType());
				att.setCreateUserUuid(null!=user?user.getUserUuid():null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean result = attcAttachmentService.addAttachment(att);
		
		try {
			if(result){
				addLogs(LOG_TYPE_OPERATE, GET_ATTACHMENT_ADD, JSONUtil.toJSONString(att), LOG_STATE_SUCCESS,request);
				writeToJson(new JsonBean(RESULT_STATE_OK,true, "添加附件成功", 0), response);
			}else{
				addLogs(LOG_TYPE_OPERATE, GET_ATTACHMENT_ADD,JSONUtil.toJSONString(att), LOG_STATE_ERROR,request);
				writeToJson(new JsonBean(RESULT_STATE_NULL,false, "添加附件成功", 0), response);
			}
		} catch (JSONException e) {
			
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：删除附件<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-13 下午04:45:27<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delAttachment")
	public String delAttachment(@ModelAttribute() Attachment attachment,HttpServletRequest request,HttpServletResponse response){
		boolean result = attcAttachmentService.delAttachment(attachment);
		try {
			if(result){
				addLogs(LOG_TYPE_OPERATE, GET_ATTACHMENT_DEL, JSONUtil.toJSONString(attachment), LOG_STATE_SUCCESS,request);
				writeToJson(new JsonBean(RESULT_STATE_OK,true, "添加附件成功", 0), response);
			}else{
				addLogs(LOG_TYPE_OPERATE, GET_ATTACHMENT_DEL,JSONUtil.toJSONString(attachment), LOG_STATE_ERROR,request);
				writeToJson(new JsonBean(RESULT_STATE_NULL,false, "添加附件成功", 0), response);
			}
		} catch (JSONException e) {
			
		}
		return null;
	}
}
