/*
 * @项目名称: crm
 * @文件名称: IOrg.java
 * @日期: 2015-8-24 上午09:01:22  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.base.core.bean.Org;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IOrg.java   <br/>
 * 类描述：组织机构dao层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-24 上午09:01:22   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-24 上午09:01:22   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IOrgDao {

	/**
	 * 
	 * 方法描述：添加组织机构<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午09:02:27<br/>         
	 * @param org 组织机构实体对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addOrg(Org org);
	
	/**
	 * 
	 * 方法描述：修改组织机构<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午09:03:07<br/>         
	 * @param org 组织机构<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updOrg(Org org);
	
	/**
	 * 
	 * 方法描述：删除组织机构<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午09:04:22<br/>         
	 * @param org 组织机构实体信息<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delOrg(Org org);
	
	/**
	 * 
	 * 方法描述：获取组织机构信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午09:05:27<br/>         
	 * @param org 组织机构实体<br/>   
	 * @return Org<br/>   
	 * @version   1.0<br/>
	 */
	public Org getOrg(Org org);
	
	/**
	 * 
	 * 方法描述：分页查询模型信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 下午06:06:46<br/>         
	 * @param page 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return PageBean<Org><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Org> getOrgList(PageBean<Org> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	/**
	 * 
	 * 方法描述：获取子级树形列表<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-24 上午10:35:50<br/>         
	 * @param orgParentUuid 父级Uuid<br/>   
	 * @return List<TreeNode><br/>   
	 * @version   1.0<br/>
	 */
	public List<TreeNode> getTreeNodeList(String orgParentUuid);
	
	/**
	 * 
	 * 方法描述：通过部门uuid获取其部门下的职务<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-9 下午09:49:19<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Role> getRoleList(String orgUUid);
	
	/**
	 * 
	 * 方法描述：保存部门职务信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-9 下午10:25:19<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int saveOrgRole(String[] roleUuid,String orgUuid);
	
	
	
}
