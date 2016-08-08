/*
 * @项目名称: crm
 * @文件名称: RoleController.java
 * @日期: 2015-8-28 上午09:41:56  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Authority;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.service.IAuthorityService;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.base.core.service.IRoleService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：RoleController.java   <br/>
 * 类描述：角色信息逻辑控制层   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 上午09:41:56   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 上午09:41:56   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@RequestMapping(value="role")
@Controller
public class RoleController extends MfBaseController {

	/**进入角色管理系统*/
	private static String GET_ROLE_TITLE="进入角色管理系统";
	/**添加角色信息*/
	private static String GET_ROLE_ADD="添加角色信息";
	/**修改角色信息*/
	private static String GET_ROLE_UPD="修改角色信息";
	/**删除角色信息*/
	private static String GET_ROLE_DEL="删除角色信息";
	/**为角色分配权限*/
	private static String GET_ROLE_POWER_ADD="为角色分配权限";
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IRoleService roleService = null;
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IAuthorityService authorityService = null;
	

	/**
	 * 操作注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	
	
	/**
	 * 
	 * 方法描述：进入人员管理模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="role")
	public ModelAndView role(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/base/role/index");
		addLogs(LOG_TYPE_OPERATE, GET_ROLE_TITLE, GET_ROLE_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(MfConstant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：查询角色信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午05:41:23<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping("getRolePage")
	public String getRolePage(@ModelAttribute()Role role,@ModelAttribute()PageBean<Role> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = roleService.getRoleList(pageBean, role);
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
	@RequestMapping(value="addRole")
	public String addRole(@ModelAttribute()Role role,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_ROLE_ADD;
		if(null!=role.getRoleUuid()&&!"".equals(role.getRoleUuid())){
			result = roleService.updRole(role);
			msg = GET_ROLE_UPD;
		}else{
			result = roleService.addRole(role);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(role, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(role, true), LOG_STATE_ERROR,request);
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
	@RequestMapping(value="delRole")
	public String delRole(@ModelAttribute()Role role,HttpServletRequest request,HttpServletResponse response){
		boolean result = roleService.delRole(role);
		JsonBean jsonBean = null;
		try {
			if(result){
				authorityService.delAuthorityByRoleUuid(role.getRoleUuid());
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_ROLE_DEL, JSONUtil.toJSONString(role, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_ROLE_DEL, JSONUtil.toJSONString(role, true), LOG_STATE_ERROR,request);
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
	@RequestMapping(value="validateRoleKey")
	public String validateRoleKey(@ModelAttribute()Role role,HttpServletRequest request,HttpServletResponse response){
		role = roleService.getRoleKey(role);
		if(null!=role){
			writeToJson(new JsonBean(RESULT_STATE_OK,true,role,0), response);
		}else{
			writeToJson(new JsonBean(RESULT_STATE_NULL,false), response);
		}
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
	private String getSubsystemList(String roleUuid){
		List<Authority> list = authorityService.getSubsytemList(roleUuid);
			StringBuffer sb = new StringBuffer(200);
			if(null!=list&&list.size()>0){
				sb.append("[");
				for (Authority authority : list) {
					sb.append("{id:'").append(authority.getForeignKey()).append("',");
					sb.append("text:'").append(authority.getForeignKeyText()+"[<span style=\"color:green\">子系统</span>]").append("',");
					sb.append("icon:'").append(authority.getIcon()).append("',");
					sb.append("expanded:").append(true).append(",");
					sb.append("type:'").append(authority.getType()).append("',");
					if(null!=authority.getUuid()&&!"".equals(authority.getUuid())){
						sb.append("checked:").append(true).append(",");
					}else{
						sb.append("checked:").append(false).append(",");
					}
					//获取模块下功能连接
					String children =this.getModelList(roleUuid,authority.getForeignKey());
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
	 * 方法描述：封装树形菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-20 下午02:14:51<br/>         
	 * @param <br/>   
	 * @return List<TreeNode><br/>   
	 * @version   1.0<br/>
	 */
	private String getModelList(String roleUuid,String modelSubsystemUuid){
		List<Authority> list = authorityService.getModelList(roleUuid,modelSubsystemUuid);
			StringBuffer sb = new StringBuffer(200);
			if(null!=list&&list.size()>0){
				sb.append("[");
				for (Authority authority : list) {
					sb.append("{id:'").append(authority.getForeignKey()).append("',");
					sb.append("text:'").append(authority.getForeignKeyText()+"[<span style=\"color:#05C1F5\">模块</span>]").append("',");
					sb.append("icon:'").append(authority.getIcon()).append("',");
					sb.append("expanded:").append(false).append(",");
					sb.append("type:'").append(authority.getType()).append("',");
					if(null!=authority.getUuid()&&!"".equals(authority.getUuid())){
						sb.append("checked:").append(true).append(",");
					}else{
						sb.append("checked:").append(false).append(",");
					}
					//获取模块下功能连接
					String children =this.getPowerList(roleUuid,authority.getForeignKey(),MfConstant.DEFAULT_ROOT_UUID);
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
	 * 方法描述：封装树形菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-20 下午02:14:51<br/>         
	 * @param <br/>   
	 * @return List<TreeNode><br/>   
	 * @version   1.0<br/>
	 */
	private String getPowerList(String roleUuid,String modelUuid,String powerUuid){
		if(null==powerUuid||"".equals(powerUuid)){
			powerUuid = MfConstant.DEFAULT_ROOT_UUID;
		}
		List<Authority> list = authorityService.getPowerList(roleUuid, modelUuid, powerUuid);
			StringBuffer sb = new StringBuffer(200);
			if(null!=list&&list.size()>0){
				sb.append("[");
				for (Authority authority : list) {
					sb.append("{id:'").append(authority.getForeignKey()).append("',");
					sb.append("text:'").append(authority.getForeignKeyText()+"[<span style=\"color:blue\">功能</span>]").append("',");
					sb.append("icon:'").append(authority.getIcon()).append("',");
					sb.append("expanded:").append(false).append(",");
					sb.append("type:'").append(authority.getType()).append("',");
					if(null!=authority.getUuid()&&!"".equals(authority.getUuid())){
						sb.append("checked:").append(true).append(",");
					}else{
						sb.append("checked:").append(false).append(",");
					}
					String children =this.getPowerList(roleUuid,modelUuid,authority.getForeignKey());
					if(null!=children&&!"".equals(children)){
						sb.append("children:").append(children).append("},");
					}else{
						//获取操作功能
						String operateChildren =this.getOperateList(roleUuid,authority.getForeignKey());
						if(null!=operateChildren&&!"".equals(operateChildren)){
							sb.append("children:").append(operateChildren).append("},");
						}else{
							//获取操作功能
							sb.append("leaf:").append(true).append("},");
						}
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
	 * 方法描述：封装树形菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-20 下午02:14:51<br/>         
	 * @param <br/>   
	 * @return List<TreeNode><br/>   
	 * @version   1.0<br/>
	 */
	private String getOperateList(String roleUuid,String powerUuid){
		List<Authority> list = authorityService.getOperateList(roleUuid, powerUuid);
			StringBuffer sb = new StringBuffer(200);
			if(null!=list&&list.size()>0){
				sb.append("[");
				for (Authority authority : list) {
					sb.append("{id:'").append(authority.getForeignKey()).append("',");
					sb.append("text:'").append(authority.getForeignKeyText()+"[<span style=\"color:red\">操作</span>]").append("',");
					sb.append("icon:'").append(authority.getIcon()).append("',");
					sb.append("expanded:").append(false).append(",");
					sb.append("type:'").append(authority.getType()).append("',");
					if(null!=authority.getUuid()&&!"".equals(authority.getUuid())){
						sb.append("checked:").append(true).append(",");
					}else{
						sb.append("checked:").append(false).append(",");
					}
					sb.append("leaf:").append(true).append("},");
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
	 * 方法描述：获取功能权限字符窜<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-29 上午11:02:51<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getAuthority")
	public String getAuthority(@ModelAttribute()Role role,HttpServletRequest request,HttpServletResponse response){
		if(null==role.getRoleUuid()||"".equals(role.getRoleUuid()))return null;
		String json = getSubsystemList(role.getRoleUuid());
		writeToStr(json, response);
		return null;
	}
	

	/**
	 * 
	 * 方法描述：获取功能权限字符窜<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-29 上午11:02:51<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addAuthority")
	public String addAuthority(@RequestParam()String foreignKeys,@RequestParam()String foreignKeyTexts,@RequestParam()String types,@RequestParam()String roleUuid,HttpServletRequest request,HttpServletResponse response){
		boolean result = authorityService.addAuthority(foreignKeys, types, roleUuid);
		JsonBean jsonBean = null;
		if(result){
			jsonBean = new JsonBean(RESULT_STATE_OK, true);
			addLogs(LOG_TYPE_OPERATE, GET_ROLE_POWER_ADD, foreignKeyTexts, LOG_STATE_ERROR,request);
		}
		writeToJson(jsonBean, response);
		return null;
	}
	
	
}
