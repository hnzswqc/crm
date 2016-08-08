/*
 * @项目名称: crm
 * @文件名称: IPower.java
 * @日期: 2015-8-11 上午11:04:23  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Power;
import com.hnzskj.base.core.bean.TreeNode;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IPower.java   <br/>
 * 类描述： 功能权限dao层接口  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-11 上午11:04:23   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-11 上午11:04:23   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IPowerDao {

	/**
	 * 
	 * 方法描述：添加功能权限<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:00<br/>         
	 * @param power 条件实体<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addPower(Power power);
	
	/**
	 * 
	 * 方法描述：删除功能权限<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:08<br/>         
	 * @param powerUuid 主键uuid<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delPower(String powerUuid);
	
	/**
	 * 
	 * 方法描述：修改功能权限<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:17<br/>         
	 * @param power 条件实体<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updPower(Power power);
	
	/**
	 * 
	 * 方法描述：获取功能权限<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:24<br/>         
	 * @param powerUuid 主键id<br/>   
	 * @return Power<br/>   
	 * @version   1.0<br/>
	 */
	public Power getPower(String powerUuid);
		
	/**
	 * 
	 * 方法描述：获取功能权限分页信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return Page<Power><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Power> getPowerList(PageBean<Power> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	/**
	 * 
	 * 方法描述：获取当前功能菜单子级菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-20 上午10:54:13<br/>
	 * @param modelUuid 模块uuid<br/>            
	 * @param powerParentUuid 父级uuid<br/>
	 * @param userUuid 用户uuid<br/>     
	 * @return List<TreeNode><br/>   
	 * @version   1.0<br/>
	 */
	public List<TreeNode> getPowerList(String modelUuid,String powerParentUuid,String userUuid);
	
	
}
