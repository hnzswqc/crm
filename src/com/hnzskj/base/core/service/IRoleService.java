/*
 * @项目名称: crm
 * @文件名称: IRoleService.java
 * @日期: 2015-8-28 上午10:52:30  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Role;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IRoleService.java   <br/>
 * 类描述： 角色service接口  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 上午10:52:30   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 上午10:52:30   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IRoleService {

	/**
	 * 
	 * 方法描述：添加角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 上午10:38:33<br/>         
	 * @param role 条件实体<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addRole(Role role);
	
	/**
	 * 
	 * 方法描述：修改角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 上午10:38:48<br/>         
	 * @param role 角色对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delRole(Role role);
	
	/**
	 * 
	 * 方法描述：修改角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 上午10:38:48<br/>         
	 * @param role 角色对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public boolean updRole(Role role);
	
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
	 * @param role 角色实体对象<br/>
	 * @return Page<Role><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Role> getRoleList(PageBean<Role> pageBean,Role role);
}
