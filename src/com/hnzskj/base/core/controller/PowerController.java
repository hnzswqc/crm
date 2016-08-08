/*
 * @项目名称: crm
 * @文件名称: PowerController.java
 * @日期: 2015-8-11 上午11:58:58  
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
import com.hnzskj.common.util.JSONUtil;
import com.hnzskj.base.common.controller.MfBaseController;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Power;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.service.IOperateService;
import com.hnzskj.base.core.service.IPowerService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：PowerController.java   <br/>
 * 类描述：功能权限管理   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-11 上午11:58:58   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-11 上午11:58:58   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="power")
public class PowerController extends MfBaseController {

	
	/**
	 * 日志描述-登录信息
	 */
	public static String POWER_TITLE_LOGIN="登录权限系统";
	
	/**
	 * 添加功能权限
	 */
	public static String POWER_TITLE_ADD="添加功能权限";
	
	/**
	 * 删除功能权限
	 */
	public static String POWER_TITLE_DEL="删除功能权限";
	
	/**
	 * 修改功能权限
	 */
	public static String POWER_TITLE_UPD="修改功能权限";
	
	/**
	 * service层注入
	 */
	@Autowired
	private IPowerService powerService = null;
	

	/**
	 * 操作注入
	 */
	@Autowired
	private IOperateService operateService = null;
	
	/**
	 * 
	 * 方法描述：功能权限模块<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 上午10:55:38<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="power")
	public ModelAndView power(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/base/power/index");
		addLogs(LOG_TYPE_OPERATE, POWER_TITLE_LOGIN, POWER_TITLE_LOGIN, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(MfConstant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：查询功能列表信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 下午12:03:11<br/>         
	 * @param 获取功能连接信息<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getPowerList")
	public String getPowerList(@ModelAttribute()Power power,@ModelAttribute() PageBean<Power> pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = powerService.getPowerList(pageBean, power);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：添加功能权限<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-18 下午05:59:01<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addPower")
	public String addPower(@ModelAttribute()Power power,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg=POWER_TITLE_ADD;
		if(null!=power.getPowerUuid()&&!"".equals(power.getPowerUuid())){
			result = powerService.updPower(power);
			msg = POWER_TITLE_UPD;
		}else{
			result = powerService.addPower(power);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(power, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(power, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取权限菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-12 下午07:34:43<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getPowerTreeList")
	public String getPowerTreeList(@ModelAttribute()Power power,HttpServletRequest request,HttpServletResponse response){
		PageBean<Power> pageBean = powerService.getPowerList(null, power);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, pageBean.getList(), pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：删除功能权限<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-19 下午02:41:22<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delPower")
	public String delPower(@ModelAttribute()Power power,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		if(null!=power){
			result =  powerService.delPower(power.getPowerUuid());
		}else{
			result = true;
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, POWER_TITLE_DEL, JSONUtil.toJSONString(power, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, POWER_TITLE_DEL, JSONUtil.toJSONString(power, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
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
	public String getPowerList(String modelUuid,String powerParentUuid,HttpServletRequest request){
		User user = getUserFromSession(request);
		if(null==user){
			return "";
		}
		List<TreeNode> list = powerService.getPowerList(modelUuid,powerParentUuid,user.getUserUuid());
			StringBuffer sb = new StringBuffer(200);
			if(null!=list&&list.size()>0){
				sb.append("[");
				for (TreeNode treeNode : list) {
					sb.append("{id:'").append(treeNode.getId()).append("',");
					sb.append("text:'").append(treeNode.getText()).append("',");
					sb.append("icon:'").append(treeNode.getIcon()).append("',");
					sb.append("url:'").append(treeNode.getUrl()).append("',");
					sb.append("expanded:").append(true).append(",");
					String children =this.getPowerList(modelUuid,treeNode.getId(),request);
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
	 * 方法描述：拼装菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-20 上午11:35:25<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getUserPowerTreeList")
	public String getUserPowerTreeList(String modelUuid,HttpServletRequest request,HttpServletResponse response){
		String json = getPowerList(modelUuid,MfConstant.DEFAULT_ROOT_UUID,request);
		writeToStr(json, response);
		return null;
	}
	
}
