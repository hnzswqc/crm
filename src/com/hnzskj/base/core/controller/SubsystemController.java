/*
 * @项目名称: crm
 * @文件名称: SubsystemController.java
 * @日期: 2015-9-2 下午02:03:55  
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
import org.springframework.web.servlet.ModelAndView;

import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Subsystem;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.base.core.service.ISubsystemService;
import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.JSONUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：SubsystemController.java   <br/>
 * 类描述：子系统controller层   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-2 下午02:03:55   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-2 下午02:03:55   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="sub")
public class SubsystemController extends MfBaseController {

	/**进入子系统管理*/
	protected static String GET_SUB_TITLE="进入子系统管理";
	/**添加子系统*/
	protected static String GET_SUB_ADD="添加子系统";
	/**修改子系统*/
	protected static String GET_SUB_UPD="修改子系统";
	/**删除子系统*/
	protected static String GET_SUB_DEL="删除子系统";
	
	/**
	 * 业务注入
	 */
	@Autowired
	private ISubsystemService subsystemService = null;
	

	/**
	 * 操作注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	
	/**
	 * 
	 * 方法描述：进入子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 下午02:08:06<br/>         
	 * @param <br/>   
	 * @return ModelAndView<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="subsystem")
	public ModelAndView subsystem(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/base/subsystem/index");
		addLogs(LOG_TYPE_OPERATE, GET_SUB_TITLE, GET_SUB_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(MfConstant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：查询子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 下午02:29:57<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getSubsystemList")
	public String getSubsystemList(@ModelAttribute()Subsystem subsystem,@ModelAttribute()PageBean<Subsystem> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = subsystemService.getSubsystemList(pageBean, subsystem);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	
	/**
	 * 
	 * 方法描述：保存子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 下午02:10:44<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addSubsystem")
	public String addSubsystem(@ModelAttribute()Subsystem subsystem,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_SUB_ADD;
		if(null!=subsystem.getSubUuid()&&!"".equals(subsystem.getSubUuid())){
			result = subsystemService.updSubsystem(subsystem);
			msg = GET_SUB_UPD;
		}else{
			result = subsystemService.addSubsystem(subsystem);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(subsystem, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(subsystem, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：删除子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 下午02:11:21<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delSubsystem")
	public String delSubsystem(@ModelAttribute()Subsystem subsystem,HttpServletRequest request,HttpServletResponse response){
		boolean result = subsystemService.delSubsystem(subsystem);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_SUB_DEL, JSONUtil.toJSONString(subsystem, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_SUB_DEL, JSONUtil.toJSONString(subsystem, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：验证唯一标识key是否存在<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 下午02:19:08<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="validateSubKey")
	public String validateSubKey(@ModelAttribute()Subsystem subsystem,HttpServletRequest request,HttpServletResponse response){
		PageBean<Subsystem> pageBean = subsystemService.getSubsystemList(null, subsystem);
		if(null!=pageBean.getList()&&pageBean.getList().size()>0){
			writeToJson(new JsonBean(RESULT_STATE_OK,true,pageBean.getList().get(0),0), response);
		}else{
			writeToJson(new JsonBean(RESULT_STATE_NULL,false), response);
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 下午03:34:00<br/>         
	 * @param <br/>
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getSubsystemTreeList")
	public String getSubsystemTreeList(HttpServletRequest request,HttpServletResponse response){
		String json = getSubsystemList();
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
	private String getSubsystemList(){
		Subsystem subsystem = new Subsystem();
		subsystem.setSubState(MfConstant.STATE_ACTIVATED.toString());
		PageBean<Subsystem> pageBean = subsystemService.getSubsystemList(null, subsystem);
			StringBuffer sb = new StringBuffer(200);
			if(null!=pageBean.getList()&&pageBean.getList().size()>0){
				sb.append("[");
				for (Subsystem sub:pageBean.getList()) {
					sb.append("{id:'").append(sub.getSubUuid()).append("',");
					sb.append("text:'").append(sub.getSubTitle()).append("',");
					sb.append("expanded:").append(true).append(",");
					sb.append("icon:'").append(sub.getSubIcon()).append("',");
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
	 * 方法描述：查询权限子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 下午02:29:57<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getUserSubsystemList")
	public String getUserSubsystemList(@ModelAttribute()Subsystem subsystem,HttpServletRequest request,HttpServletResponse response){
		User user = getUserFromSession(request);
		subsystem.setUser(user);
		PageBean<Subsystem> pageBean = subsystemService.getSubsystemList(null, subsystem);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalPage()), response);
		return null;
	}
	
}
