/*
 * @项目名称: crm
 * @文件名称: DicController.java
 * @日期: 2015-8-26 下午05:28:25  
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
import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.base.core.service.IDicService;
import com.hnzskj.base.core.service.IOperateService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：DicController.java   <br/>
 * 类描述：数据字典controller   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-26 下午05:28:25   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-26 下午05:28:25   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Controller
@RequestMapping(value="dic")
public class DicController extends MfBaseController {

	/**进入数据字典系统*/
	protected static final String GET_DIC_TITLE="进入数据字典系统";
	/**添加数据字典类别*/
	protected static final String GET_DIC_TYPE_ADD="添加数据字典类别";
	/**修改数据字典类别*/
	protected static final String GET_DIC_TYPE_UPD="修改数据字典类别";
	/**删除数据字典类别*/
	protected static final String GET_DIC_TYPE_DEL="删除数据字典类别";
	/**添加数据字典信息*/
	protected static final String GET_DIC_DATA_ADD="添加数据字典信息";
	/**修改数据字典信息*/
	protected static final String GET_DIC_DATA_UPD="修改数据字典信息";
	/**删除数据字典信息*/
	protected static final String GET_DIC_DATA_DEL="删除数据字典信息";
	
	/**
	 * 业务注入
	 */
	@Autowired
	private IDicService dicService = null;
	

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
	@RequestMapping(value="dic")
	public ModelAndView dic(String id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/base/dic/index");
		addLogs(LOG_TYPE_OPERATE, GET_DIC_TITLE, GET_DIC_TITLE, LOG_STATE_SUCCESS,request);
		modelAndView.addObject(MfConstant.AUTHORITY_OPERATE, operateService.getOperateList(id,request));
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：获取数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:28:41<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getDicTypeList")
	public String getDicTypeList(HttpServletRequest request,HttpServletResponse response){
		writeToStr(getDicTypeListStr(), response);
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
	private String getDicTypeListStr(){
			List<Dic> list = dicService.getDicTypeList();
			StringBuffer sb = new StringBuffer(200);
			if(null!=list&&list.size()>0){
				sb.append("[");
				for (Dic dic : list) {
					sb.append("{id:'").append(dic.getId()).append("',");
					sb.append("text:'").append(dic.getText()).append("',");
					sb.append("expanded:").append(true).append(",");
					sb.append("uuid:'").append(dic.getUuid()).append("',");
					sb.append("orderBy:'").append(dic.getOrderBy()).append("',");
					sb.append("note:'").append(dic.getNote()).append("',");
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
	 * 方法描述：添加数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:31:15<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addDicType")
	public String addDicType(@ModelAttribute()Dic dic,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_DIC_TYPE_ADD;
		if(null!=dic.getUuid()&&!"".equals(dic.getUuid())){
			result =dicService.updDicType(dic);
			msg = GET_DIC_TYPE_UPD;
		}else{
			result = dicService.addDicType(dic);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(dic, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(dic, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：删除数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:32:23<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delDicType")
	public String delDicType(@ModelAttribute()Dic dic,HttpServletRequest request,HttpServletResponse response){
		boolean result = dicService.delDicType(dic);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_DIC_TYPE_DEL, JSONUtil.toJSONString(dic, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_DIC_TYPE_DEL, JSONUtil.toJSONString(dic, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:28:41<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getDicList")
	public String getDicList(@ModelAttribute()Dic dic,@ModelAttribute()PageBean<Dic>pageBean,HttpServletRequest request,HttpServletResponse response){
		pageBean = dicService.getDicList(pageBean, dic);
		writeToJson(new JsonBean(RESULT_STATE_OK,true,pageBean.getList(),pageBean.getTotalRecords()), response);
		return null;
	}
	
	/**
	 * 
	 * 方法描述：添加数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:31:15<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="addDic")
	public String addDic(@ModelAttribute()Dic dic,HttpServletRequest request,HttpServletResponse response){
		boolean result = false;
		String msg = GET_DIC_TYPE_ADD;
		if(null!=dic.getUuid()&&!"".equals(dic.getUuid())){
			result =dicService.updDic(dic);
			msg = GET_DIC_TYPE_UPD;
		}else{
			result = dicService.addDic(dic);
		}
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(dic, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, msg, JSONUtil.toJSONString(dic, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：删除数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:32:23<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="delDic")
	public String delDic(@ModelAttribute()Dic dic,HttpServletRequest request,HttpServletResponse response){
		boolean result = dicService.delDic(dic);
		JsonBean jsonBean = null;
		try {
			if(result){
				jsonBean = new JsonBean(RESULT_STATE_OK, true);
				addLogs(LOG_TYPE_OPERATE, GET_DIC_TYPE_DEL, JSONUtil.toJSONString(dic, true), LOG_STATE_SUCCESS,request);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
				addLogs(LOG_TYPE_OPERATE, GET_DIC_TYPE_DEL, JSONUtil.toJSONString(dic, true), LOG_STATE_ERROR,request);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：验证数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 上午09:52:05<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="validateDicType")
	public String validateDicType(@ModelAttribute()Dic dic,HttpServletRequest request,HttpServletResponse response){
		dic = dicService.getDicType(dic.getId());
		JsonBean jsonBean = null;
		try {
			if(null!=dic){
				jsonBean = new JsonBean(RESULT_STATE_OK, true,JSONUtil.toJSONString(dic),0);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取数据字典树形菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 上午11:27:16<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getDicTreeList")
	public String getDicTreeList(@ModelAttribute()Dic dic,HttpServletRequest request,HttpServletResponse response){
		String json = getDicList(dic.getType(),MfConstant.DEFAULT_ROOT_UUID);
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
	public String getDicList(String type,String parentUuid){
		List<TreeNode> list =dicService.getTreeList(type, parentUuid);
			StringBuffer sb = new StringBuffer(200);
			if(null!=list&&list.size()>0){
				sb.append("[");
				for (TreeNode treeNode : list) {
					sb.append("{id:'").append(treeNode.getId()).append("',");
					sb.append("text:'").append(treeNode.getText()).append("',");
					sb.append("expanded:").append(true).append(",");
					String children =this.getDicList(type,treeNode.getId());
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
	 * 方法描述：验证数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 上午09:52:05<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="validateDic")
	public String validateDic(@ModelAttribute()Dic dic,HttpServletRequest request,HttpServletResponse response){
		dic = dicService.getDic(dic.getType(),dic.getId());
		JsonBean jsonBean = null;
		try {
			if(null!=dic){
				jsonBean = new JsonBean(RESULT_STATE_OK, true,JSONUtil.toJSONString(dic),0);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：验证数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 上午09:52:05<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="validateDicKey")
	public String validateDicKey(@ModelAttribute()Dic dic,HttpServletRequest request,HttpServletResponse response){
		dic = dicService.getDicByKey(dic.getType(),dic.getKey());
		JsonBean jsonBean = null;
		try {
			if(null!=dic){
				jsonBean = new JsonBean(RESULT_STATE_OK, true,JSONUtil.toJSONString(dic),0);
			}else{
				jsonBean = new JsonBean(RESULT_STATE_NULL, false);
			}
			writeToJson(jsonBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：获取数据字典信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 下午02:54:26<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="getParamList")
	public String getParamList(String type,HttpServletRequest request,HttpServletResponse response){
		List<Dic> list = dicService.getParamList(type);
		writeToJson(new JsonBean(RESULT_STATE_OK, true, list, list.size()), response);
		return null;
	}
	
}
