/*
 * @项目名称: crm
 * @文件名称: IUserDao.java
 * @日期: 2015-8-25 下午04:48:25  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Org;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.bean.User;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IUserDao.java   <br/>
 * 类描述：人员信息dao层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-25 下午04:48:25   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-25 下午04:48:25   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IUserDao {

	/**
	 * 
	 * 方法描述：添加人员信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午04:55:58<br/>         
	 * @param user 人员实体对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addUser(User user);
	
	/**
	 * 
	 * 方法描述：删除人员信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午04:56:24<br/>         
	 * @param user人员实体对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delUser(User user);
	
	/**
	 * 
	 * 方法描述：修改人员信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午04:56:50<br/>         
	 * @param user 人员实体对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updUser(User user);
	
	
	/**
	 * 
	 * 方法描述：获取人员分页信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return Page<User><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<User> getUserList(PageBean<User> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	/**
	 * 
	 * 方法描述：获取组织人员关系<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 上午10:33:02<br/>         
	 * @param userUuid 人员uuid<br/>
	 * @param orgUuid 组织uuid<br/>  
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int getOrgUser(String userUuid,String orgUuid);
	
	/**
	 * 
	 * 方法描述：变更部门<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午02:25:42<br/>         
	 * @param orgUuids 变更的部门<br/>
	 * @param userUuid 变更人员<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addOrgUser(String[]orgUuids,String userUuid);
	
	
	/**
	 * 
	 * 方法描述：根据用户删除其所在所有部门<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午02:56:20<br/>         
	 * @param userUuid 用户uuid<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delOrgUser(String userUuid);
	
	/**
	 * 
	 * 方法描述：通过用户userId获取用户信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午03:20:55<br/>         
	 * @param <br/>   
	 * @return User<br/>   
	 * @version   1.0<br/>
	 */
	public User getUserByUserId(String userId);
	
	/**
	 * 
	 * 方法描述：通过用户userNumber员工号获取用户信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午03:20:55<br/>         
	 * @param userNumber 员工号 <br/>   
	 * @return User<br/>   
	 * @version   1.0<br/>
	 */
	public User getUserByUserNumber(String userNumber);
	
	/**
	 * 
	 * 方法描述：通过用户获取其角色信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 上午08:41:58<br/>         
	 * @param <br/>   
	 * @return List<Role><br/>   
	 * @version   1.0<br/>
	 */
	public List<Role> getUserRoleList(User user);
	
	/**
	 * 
	 * 方法描述：通过部门获取其角色信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 上午08:41:58<br/>         
	 * @param <br/>   
	 * @return List<Role><br/>   
	 * @version   1.0<br/>
	 */
	public List<Role> getOrgRoleList(Org org);
	
	/**
	 * 
	 * 方法描述：批量添加用户角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 下午03:10:18<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addUserRole(List<User> userList,String[]roleUuids);
	
	/**
	 * 
	 * 方法描述：批量添加用户角色<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 下午03:10:18<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addOrgRole(String orgUuid,String[]roleUuids);
	
	
	
	/**
	 * 
	 * 方法描述：添加前先删除该用户所有的角色再进行添加<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 下午03:11:05<br/>         
	 * @param userUuid 用户uuid<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delUserRoleByUserUuid(String userUuid);
	
	

	/**
	 * 
	 * 方法描述：添加前先删除该用户所有的角色再进行添加<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 下午03:11:05<br/>         
	 * @param orgUuid 组织uuid<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delOrgRoleByOrgUuid(String orgUuid);
	
	/**
	 * 
	 * 方法描述：用户登录<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 下午06:15:11<br/>         
	 * @param userId 用户userId<br/>
	 * @param userPassword 用户密码<br/>   
	 * @return User<br/>   
	 * @version   1.0<br/>
	 */
	public User login(String userId,String userPassword);
	
	/**
	 * 
	 * 方法描述：修改密码<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-6 上午10:57:33<br/>         
	 * @param user 条件实体<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updPassword(User user);
	
	/**
	 * 
	 * 方法描述：通过用户uuid获取用户信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-22 下午02:03:11<br/>         
	 * @param <br/>   
	 * @return User<br/>   
	 * @version   1.0<br/>
	 */
	public User getUserByUuid(String uuid);
	
	
	/**
	 * 
	 * 方法描述：通过用户获取其角色信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-31 上午08:41:58<br/>         
	 * @param <br/>   
	 * @return List<Role><br/>   
	 * @version   1.0<br/>
	 */
	public List<Role> getUserRoleList(String userUuid);
	
	/**
	 * 
	 * 方法描述：自动分配员工工号<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-1 下午03:30:22<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String getUserNumber(String orgUuid);
	
	
	
}
