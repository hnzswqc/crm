/*
 * @项目名称: crm
 * @文件名称: ModelController.java
 * @日期: 2015-8-12 下午07:02:57  
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

import com.hnzskj.common.bean.JsonBean;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Model;
import com.hnzskj.base.core.bean.Subsystem;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.service.IModelService;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.base.core.service.ISubsystemService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ModelController.java   <br/>
 * 类描述：模块信息逻辑控制层   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-12 下午07:02:57   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-12 下午07:02:57   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="model")
public class ModelController extends MfBaseController {
	
	/**
	 * 获取权限菜单
	 */
	protected static String GET_MODEL_TITLE="获取权限菜单"; 
	
	/**
	 * 进入模块管理系统
	 */
	protected static String GET_MODEL_LOGIN="进入模块管理系统";
	
	/**
	 * 添加模块信息
	 */
	protected static String MODEL_TITLE_ADD="添加模块信息"; 
	
	/**
	 * 修改模块信息
	 */
	protected static String MODEL_TITLE_UPD="修改模块信息"; 
	
	/**
	 * 修改模块信息
	 */
	protected static String MODEL_TITLE_DEL="删除模块信息"; 
	

	/**
	 * 业务注入
	 */
	@Autowired
	private IModelService modelService = null;
	
	/**
	 * 操作注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 子系统注入
	 */
	@Autowired
	private ISubsystemService subsystemService = null;
	
	
	/**
	 * 
	 * 方法描述：功能权限模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="model")
	public ModelAndView power(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/base/model/index");
		addLogs(LOG_TYPE_OPERATE, GET_MODEL_LOGIN, GET_MODEL_LOGIN, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(MfConstant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：获取子系统菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 下午03:19:09<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getModelList")
	public String getModelList(@ModelAttribute()Model model,@ModelAttribute()PageBean<Model> pageBean,HttpServletRequest request,HttpServletResponse response){
		User user = getUserFromSession(request);
		if(!Constant.SUPER_USER_ID.equals(user.getUserId())){
			model.setUser(user);
		}
		pageBean = modelService.getModelList(null, model);
		writeToJson(new JsonBean(RESULT_STATE_OK,true,pageBean.getList(),pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取模块菜单数<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-12 下午07:34:43<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getModelTree")
	public String getModelTree(HttpServletRequest request,HttpServletResponse response){
		List<Subsystem> list = getSubsystemList();
		StringBuffer sb = new StringBuffer("[");
		for (int j = 0; j < list.size(); j++) {
			Subsystem subsystem=list.get(j);
			if(j>0){
				sb.append(",");
			}
			sb.append("{");
			sb.append("id:'").append(subsystem.getSubUuid()).append("',text:'").append(subsystem.getSubTitle()).append("',");
			sb.append("expanded:").append(true).append(",");
			sb.append("icon:'").append(subsystem.getSubIcon()).append("',leaf:false,children:[");
			Model m = new Model();
			m.setModelSubsystemUuid(subsystem.getSubUuid());
			PageBean<Model>  pageBean = modelService.getModelList(null, m);
			if(null!=pageBean.getList()&&pageBean.getList().size()>0){
				int i=0;
				for (Model model : pageBean.getList()) {
					if(i>0){
						sb.append(",");
					}
					sb.append("{");
					sb.append("id:'").append(model.getModelUuid()).append("',text:'").append(model.getModelTitle()).append("',");
					sb.append("icon:'").append(model.getModelIcon()).append("',leaf:true");
					sb.append("}");
					i++;
				}
			
			}
			sb.append("]");
			sb.append("}");
		}
		sb.append("]");
		writeToStr(sb.toString(),response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取所有的子系统<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-23 下午07:17:31<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Subsystem> getSubsystemList(){
		List<Subsystem> list = subsystemService.getSubsystemList(null,null).getList();
		return list;
	}
	
	/**
	 * 
	 * 方法描述：模块管理<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-20 下午05:33:03<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getModelPage")
	public String getModelPage(@ModelAttribute()Model model,@ModelAttribute() PageBean<Model> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = modelService.getModelList(pageBean, model);
		writeToJson(new JsonBean(RESULT_STATE_OK,true,pageBean.getList(),pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：添加or修改模块信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-20 下午06:27:43<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addModel")
	public String addModel(@ModelAttribute()Model model,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg=MODEL_TITLE_ADD;
		if(null!=model.getModelUuid()&&!"".equals(model.getModelUuid())){
			result =modelService.updModel(model);
			msg = MODEL_TITLE_UPD;
		}else{
			result = modelService.addModel(model);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(model, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(model, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：删除<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-21 上午09:58:31<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delModel")
	public String delModel(@ModelAttribute()Model model,HttpServletRequest request,HttpServletResponse response){
		boolean result = modelService.delModel(model.getModelUuid());
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, MODEL_TITLE_DEL, JSONUtil.toJSONString(model, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, MODEL_TITLE_DEL, JSONUtil.toJSONString(model, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
