/*
 * @项目名称: crm
 * @文件名称: IRoleDaoImpl.java
 * @日期: 2015-8-28 上午10:39:46  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.dao.IRoleDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IRoleDaoImpl.java   <br/>
 * 类描述：dao层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 上午10:39:46   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 上午10:39:46   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class RoleDaoImpl extends BaseDao implements IRoleDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IRoleDao#addRole(com.hnzskj.mainFrame.core.bean.Role)
	 */
	public int addRole(Role role) {
		String sql="INSERT INTO APP_SYSTEM_ROLE(ROLE_UUID,ROLE_KEY,ROLE_NAME,ROLE_STATE,ROLE_NOTE,ROLE_ORDERBY,CREATE_TIME)" +
				   "VALUES(?,?,?,?,?,?,GETDATE())";
		Object[]params = new Object[]{
				null!=role.getRoleUuid()&&!"".equals(role.getRoleUuid())?role.getRoleUuid():UUID.randomUUID().toString(),
				role.getRoleKey(),
				role.getRoleName(),
				role.getRoleState(),
				role.getRoleNote(),
				role.getRoleOrderBy()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IRoleDao#delRole(com.hnzskj.mainFrame.core.bean.Role)
	 */
	public int delRole(Role role) {
		String sql="DELETE FROM APP_SYSTEM_ROLE WHERE ROLE_UUID = ? ";
		int result = update(sql, new Object[]{role.getRoleUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IRoleDao#getRoleKey(com.hnzskj.mainFrame.core.bean.Role)
	 */
	public Role getRoleKey(Role role) {
		String sql="SELECT ROLE_UUID AS ROLEUUID,ROLE_KEY AS ROLEKEY,ROLE_NAME AS ROLENAME,ROLE_STATE AS ROLESTATE,ROLE_NOTE AS ROLENOTE,ROLE_ORDERBY AS ROLEORDERBY,CREATE_TIME AS CREATETIME FROM APP_SYSTEM_ROLE " +
				   "WHERE ROLE_KEY = ? ";
		role = (Role)get(sql, Role.class,new Object[]{role.getRoleKey()});
		return role;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IRoleDao#getRoleList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Role> getRoleList(PageBean<Role> pageBean, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Role> empls = new ArrayList<Role>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<Role> epage = new PageBean<Role>();// 临时变量，如果在page为null的情况下使用
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " ": sqlCondition;
		countSql = "select count(" + key + ") from " + tableName + " " + sqlCondition;
		totalRecords = (Integer) getSingleValue(countSql, queryParams);
		if (pageBean != null) {// 如果需要分页
			if (null != queryParams && queryParams.length > 0) {
				// 将数组的长度扩充为原来的2倍,并使得数组的第n个元素和第n+数组长度的元素的值相等,如此是为了分页查询时参数设置的需要
				Object[] newParamsArray = Arrays.copyOf(queryParams,
						queryParams.length * 2);
				for (int i = 0; i < queryParams.length; i++) {
					newParamsArray[queryParams.length + i] = queryParams[i];
				}
				queryParams = newParamsArray;
			}
			// 如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			// 如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			sql = "select top " + pageBean.getLimit() + " " + fields
					+ " from  " + tableName + " " + sqlCondition;
			if (" ".equals(sqlCondition) == true) {
				sql += " where " + key + " not in (select top "
						+ (pageBean.getPage() - 1) * pageBean.getLimit() + " "
						+ key + "  from " + tableName + " " + sqlCondition
						+ BaseDao.buildOrderBy(orderby) + ")"
						+ BaseDao.buildOrderBy(orderby);
			} else {
				sql += " and " + key + " not in (select top "
						+ (pageBean.getPage() - 1) * pageBean.getLimit() + " "
						+ key + " from " + tableName + sqlCondition
						+ BaseDao.buildOrderBy(orderby) + ")"
						+ BaseDao.buildOrderBy(orderby);
			}
			epage = pageBean;
			// 查询结果集
			empls = query(sql,Role.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, Role.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IRoleDao#updRole(com.hnzskj.mainFrame.core.bean.Role)
	 */
	public int updRole(Role role) {
		String sql="UPDATE APP_SYSTEM_ROLE SET ROLE_KEY =?,ROLE_NAME = ?,ROLE_STATE =?,ROLE_NOTE =?,ROLE_ORDERBY =? " +
				   "WHERE ROLE_UUID =?";
		Object[]params = new Object[]{
				role.getRoleKey(),
				role.getRoleName(),
				role.getRoleState(),
				role.getRoleNote(),
				role.getRoleOrderBy(),
				role.getRoleUuid()
		};
		int result = update(sql, params);
		return result;
	}

}
