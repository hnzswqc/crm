/*
 * @项目名称: crm
 * @文件名称: UserAuthorityDaoImpl.java
 * @日期: 2015-9-7 上午10:38:01  
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

import com.hnzskj.base.core.bean.UserAuthority;
import com.hnzskj.base.core.dao.IUserAuthorityDao;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：UserAuthorityDaoImpl.java   <br/>
 * 类描述：用户授权实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-7 上午10:38:01   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-7 上午10:38:01   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class UserAuthorityDaoImpl extends BaseDao implements IUserAuthorityDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IUserAuthorityDao#addUserAuthority(java.util.List)
	 */
	public int addUserAuthority(List<UserAuthority> list) {
		String sql="INSERT INTO APP_SYSTEM_USER_AUTHORITY (UUID,USER_UUID,AUTHORITY_USER_UUID,TYPE,STATE,NOTE,CREATE_TIME)" +
				   "VALUES(?,?,?,?,?,?,GETDATE())";
		Object[][]params = new Object[list.size()][];
		int i = 0;
		for (UserAuthority userAuthority : list) {
			Object[]param = new Object[]{
					null!=userAuthority.getUuid()&&!"".equals(userAuthority.getUuid())?userAuthority.getUuid():UUID.randomUUID().toString(),
					userAuthority.getUserUuid(),
					userAuthority.getAuthorityUserUuid(),
					userAuthority.getType(),
					userAuthority.getState(),
					userAuthority.getNote()
			};
			params[i] = param;
			i++;
		}
		int result = updateBatch(sql, params, list.size());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IUserAuthorityDao#delUserAuthority(com.hnzskj.base.core.bean.UserAuthority)
	 */
	public int delUserAuthority(UserAuthority userAuthority) {
		String sql="DELETE FROM APP_SYSTEM_USER_AUTHORITY WHERE UUID = ? ";
		int result = update(sql, new Object[]{userAuthority.getUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IUserAuthorityDao#getUserAuthorityList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<UserAuthority> getUserAuthorityList(
			PageBean<UserAuthority> pageBean, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<UserAuthority> empls = new ArrayList<UserAuthority>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<UserAuthority> epage = new PageBean<UserAuthority>();// 临时变量，如果在page为null的情况下使用
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
			empls = query(sql,UserAuthority.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, UserAuthority.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IUserAuthorityDao#updUserAuthority(com.hnzskj.base.core.bean.UserAuthority)
	 */
	public int updUserAuthority(UserAuthority userAuthority) {
		String sql="UPDATE APP_SYSTEM_USER_AUTHORITY SET STATE = ? WHERE UUID = ? ";
		int result = update(sql, new Object[]{userAuthority.getState(),userAuthority.getUuid()});
		return result;
	}

}
