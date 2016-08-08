/*
 * @项目名称: crm
 * @文件名称: IAuthorityService.java
 * @日期: 2015-8-29 上午10:34:58  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service;

import java.util.List;

import com.hnzskj.base.core.bean.Authority;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IAuthorityService.java   <br/>
 * 类描述：功能权限业务层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-29 上午10:34:58   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-29 上午10:34:58   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IAuthorityService {
	/**
	 * 
	 * 方法描述：保存功能权限<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-29 上午09:44:28<br/>         
	 * @param foreignKeys 选中的功能模块<br/>
	 * @param types 选中的功能模块类别<br/>
	 * @param roleUuid 角色Uuid<br/>      
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addAuthority(String foreignKeys,String types,String roleUuid);
	
	/**
	 * 
	 * 方法描述：删除功能角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-29 上午09:44:57<br/>         
	 * @param roleUuid 角色Uuid<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delAuthorityByRoleUuid(String roleUuid);
	
	
	/**
	 * 
	 * 方法描述：获取所有模块信息以及是否已经选中<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-29 上午09:45:24<br/>         
	 * @param roleUuid 角色Uuid<br/>   
	 * @return List<Authority><br/>   
	 * @version   1.0<br/>
	 */
	public List<Authority> getSubsytemList(String roleUuid);
	
	/**
	 * 
	 * 方法描述：获取所有模块信息以及是否已经选中<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-29 上午09:45:24<br/>         
	 * @param roleUuid 角色Uuid<br/>
	 * @param modelSubsystemUuid 子系统<br/>     
	 * @return List<Authority><br/>   
	 * @version   1.0<br/>
	 */
	public List<Authority> getModelList(String roleUuid,String modelSubsystemUuid);
	
	/**
	 * 
	 * 方法描述：获取所有功能信息以及是否已经选择<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-29 上午09:45:58<br/>         
	 * @param roleUuid 角色uuid<br/>
	 * @param modelUuid 所属模块Uuid<br/>
	 * @param powerUuid 父级模块Uuid<br/>   
	 * @return List<Authority><br/>   
	 * @version   1.0<br/>
	 */
	public List<Authority> getPowerList(String roleUuid,String modelUuid,String powerUuid);
	
	/**
	 * 
	 * 方法描述：获取所有操作功能<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-29 上午09:47:06<br/>         
	 * @param roleUuid 角色Uuid<br/>
	 * @param powerUuid 角色Uuid<br/>     
	 * @return List<Authority><br/>   
	 * @version   1.0<br/>
	 */
	public List<Authority> getOperateList(String roleUuid,String powerUuid);
	
	
}
