/*
 * @项目名称: crm
 * @文件名称: IUserAuthority.java
 * @日期: 2015-9-7 上午10:26:51  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.base.core.bean.UserAuthority;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IUserAuthority.java   <br/>
 * 类描述：用户变身dao层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-7 上午10:26:51   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-7 上午10:26:51   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IUserAuthorityDao {

	/**
	 * 
	 * 方法描述：添加<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-7 上午10:28:07<br/>         
	 * @param list 批量添加集合<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addUserAuthority(List<UserAuthority> list);
	
	/**
	 * 
	 * 方法描述：删除授权用户<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-7 上午10:29:04<br/>         
	 * @param userAuthority 实体条件<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delUserAuthority(UserAuthority userAuthority);
	
	
	/**
	 * 
	 * 方法描述：修改，审核通过<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-7 上午10:31:21<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updUserAuthority(UserAuthority userAuthority);
	
	

	/**
	 * 
	 * 方法描述：分页查询<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return Page<UserAuthority><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<UserAuthority> getUserAuthorityList(PageBean<UserAuthority> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	
	
}
