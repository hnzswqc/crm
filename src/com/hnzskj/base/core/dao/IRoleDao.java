/*
 * @项目名称: crm
 * @文件名称: IRoleDao.java
 * @日期: 2015-8-28 上午10:36:19  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao;

import java.util.LinkedHashMap;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Role;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IRoleDao.java   <br/>
 * 类描述：角色管理dao层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 上午10:36:19   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 上午10:36:19   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IRoleDao {

	/**
	 * 
	 * 方法描述：添加角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 上午10:38:33<br/>         
	 * @param role 条件实体<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addRole(Role role);
	
	/**
	 * 
	 * 方法描述：修改角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 上午10:38:48<br/>         
	 * @param role 角色对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delRole(Role role);
	
	/**
	 * 
	 * 方法描述：修改角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 上午10:38:48<br/>         
	 * @param role 角色对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updRole(Role role);
	
	/**
	 * 
	 * 方法描述：通过key获取角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 上午10:38:48<br/>         
	 * @param role 角色对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public Role getRoleKey(Role role);
	
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
	 * @return Page<Role><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Role> getRoleList(PageBean<Role> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
}
