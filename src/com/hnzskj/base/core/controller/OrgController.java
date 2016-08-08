/*
 * @项目名称: crm
 * @文件名称: OrgController.java
 * @日期: 2015-8-24 上午09:59:18  
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
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Org;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.base.core.service.IOrgService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.JSONUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：OrgController.java   <br/>
 * 类描述： 组织机构控制层  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-24 上午09:59:18   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-24 上午09:59:18   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="org")
public class OrgController extends MfBaseController {

	/**
	 * 日志操作,进入组织管理系统
	 */
	protected static String GET_ORG_TITLE="进入组织管理系统";
	
	protected static String GET_ORG_ADD="添加组织信息";
	
	protected static String GET_ORG_UPD="修改组织信息";
	
	protected static String GET_ORG_DEL="删除组织信息";
	
	protected static String GET_ORG_ROLE="保存部门职务信息";
	
	/**
	 * 业务层注入
	 */
	@Autowired
	private IOrgService orgService = null;
	
	/**
	 * 操作注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	
	/**
	 * 
	 * 方法描述：进入组织机构系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="org")
	public ModelAndView org(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/base/org/index");
		addLogs(LOG_TYPE_OPERATE, GET_ORG_TITLE, GET_ORG_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(MfConstant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：分页查询组织机构信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午10:03:59<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getOrgPage")
	public String getOrgPage(@ModelAttribute()Org org,@ModelAttribute()PageBean<Org> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = orgService.getOrgList(pageBean, org);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：添加者修改组织机构<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午10:14:13<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addOrg")
	public String addOrg(@ModelAttribute()Org org,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_ORG_ADD;
		if(null!=org.getOrgUuid()&&!"".equals(org.getOrgUuid())){
			result = orgService.updOrg(org);
		}else{
			result = orgService.addOrg(org);
			msg = GET_ORG_UPD;
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(org, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(org, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：删除组织机构<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午10:26:39<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delOrg")
	public String delOrg(@ModelAttribute()Org org,HttpServletRequest request,HttpServletResponse response){
		boolean result = orgService.delOrg(org);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_ORG_DEL, JSONUtil.toJSONString(org, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, true);
				addLogs(LOG_TYPE_OPERATE, GET_ORG_DEL, JSONUtil.toJSONString(org, true), LOG_STATE_ERROR,request);
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
	public String getOrgTreeList(String orgParentUuid,HttpServletRequest request,HttpServletResponse response){
		if(null==orgParentUuid||"".equals(orgParentUuid)){
			orgParentUuid = MfConstant.DEFAULT_ROOT_UUID;
		}
		String json = getOrgList(orgParentUuid);
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
	private String getOrgList(String orgParentUuid){
		    List<TreeNode> list = orgService.getTreeNodeList(orgParentUuid);
			StringBuffer sb = new StringBuffer(200);
			if(null!=list&&list.size()>0){
				sb.append("[");
				for (TreeNode treeNode : list) {
					sb.append("{id:'").append(treeNode.getId()).append("',");
					sb.append("text:'").append(treeNode.getText()).append("',");
					sb.append("expanded:").append(true).append(",");
					sb.append("icon:'").append(treeNode.getIcon()).append("',");
					String children =this.getOrgList(treeNode.getId());
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
	 * 方法描述：获取部门内的角色信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-9 下午09:56:09<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getRoleList")
	public String getRoleList(@ModelAttribute()Org org,HttpServletRequest request,HttpServletResponse response){
		List<Role> list = orgService.getRoleList(org);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, list, list.size()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：保存部门和职务关系<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-9 下午10:19:09<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="saveOrgRole")
	public String saveOrgRole(String roleUuids,String orgUuid,HttpServletRequest request,HttpServletResponse response){
		boolean result = orgService.saveOrgRole(roleUuids,orgUuid);
		JsonBean jsonBean = null;
		if(result){
			jsonBean = new JsonBean(RESULT_STATE_OK, true);
			addLogs(LOG_TYPE_OPERATE, GET_ORG_ROLE, GET_ORG_ROLE, LOG_STATE_SUCCESS,request);
		}else{
			jsonBean = new JsonBean(RESULT_STATE_NULL, true);
			addLogs(LOG_TYPE_OPERATE, GET_ORG_ROLE, GET_ORG_ROLE, LOG_STATE_ERROR,request);
		}
		writeToJson(jsonBean, response);
		return null;
	}
	
}
