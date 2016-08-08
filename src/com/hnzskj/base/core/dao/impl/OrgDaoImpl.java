/*
 * @项目名称: crm
 * @文件名称: OrgDaoImpl.java
 * @日期: 2015-8-24 上午09:14:37  
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

import com.hnzskj.base.core.bean.Org;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.base.core.dao.IOrgDao;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：OrgDaoImpl.java   <br/>
 * 类描述：组织机构dao层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-24 上午09:14:37   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-24 上午09:14:37   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class OrgDaoImpl extends BaseDao implements IOrgDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOrgDao#addOrg(com.hnzskj.mainFrame.core.bean.Org)
	 */
	public int addOrg(Org org) {
		String sql="INSERT INTO APP_SYSTEM_ORG (ORG_UUID,ORG_NAME,ORG_CODE,ORG_GRADE_UUID,ORG_PARENT_UUID,ORG_STATE,ORG_ORDERBY,ORG_NOTE,ORG_ICON,CREATE_TIME,START_NUMBER,END_NUMBER)" +
				   "VALUES(?,?,?,?,?,?,?,?,?,GETDATE(),?,?)";
		Object[]params = new Object[]{
			null!=org.getOrgUuid()&&!"".equals(org.getOrgUuid())?org.getOrgUuid():UUID.randomUUID().toString(),
			org.getOrgName(),
			org.getOrgCode(),
			org.getOrgGradeUuid(),
			org.getOrgParentUuid(),
			org.getOrgState(),
			org.getOrgOrderBy(),
			org.getOrgNote(),
			org.getOrgIcon(),
			org.getStartNumber(),
			org.getEndNumber()
		};
		int result =update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOrgDao#delOrg(com.hnzskj.mainFrame.core.bean.Org)
	 */
	public int delOrg(Org org) {
		String sql="DELETE FROM APP_SYSTEM_ORG WHERE ORG_UUID = ? ";
		int result = update(sql, new Object[]{org.getOrgUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOrgDao#getOrg(com.hnzskj.mainFrame.core.bean.Org)
	 */
	public Org getOrg(Org org) {
		String sql="SELECT ORG_UUID AS ORGUUID,ORG_NAME AS ORGNAME,ORG_CODE AS ORGCODE,ORG_GRADE_UUID AS ORGGRADEUUID,ORG_PARENT_UUID AS ORGPARENTUUID,ORG_STATE AS ORGSTATE,ORG_ORDERBY AS ORGORDERBY,ORG_NOTE AS ORGNOTE,CREATE_TIME AS CREATETIME,ORG_ICON AS ORGICON,START_NUMBER AS STARTNUMBER,END_NUMBER AS ENDNUMBER FROM APP_SYSTEM_ORG " +
				   "WHERE ORG_UUID = ? ";
		Org o = (Org)get(sql, Org.class, new Object[]{org.getOrgUuid()});
		return o;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOrgDao#getOrgList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Org> getOrgList(PageBean<Org> pageBean, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Org> empls = new ArrayList<Org>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<Org> epage = new PageBean<Org>();// 临时变量，如果在page为null的情况下使用
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
			empls = query(sql,Org.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, Org.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOrgDao#updOrg(com.hnzskj.mainFrame.core.bean.Org)
	 */
	public int updOrg(Org org) {
		String sql="UPDATE APP_SYSTEM_ORG SET ORG_NAME = ?,ORG_CODE = ?,ORG_GRADE_UUID = ?,ORG_PARENT_UUID = ?,ORG_STATE = ?,ORG_ORDERBY = ?,ORG_NOTE = ?,ORG_ICON = ?,START_NUMBER = ? ,END_NUMBER = ?  " +
				   "WHERE ORG_UUID = ? ";
		Object[]params = new Object[]{
			org.getOrgName(),
			org.getOrgCode(),
			org.getOrgGradeUuid(),
			org.getOrgParentUuid(),
			org.getOrgState(),
			org.getOrgOrderBy(),
			org.getOrgNote(),
			org.getOrgIcon(),
			org.getStartNumber(),
			org.getEndNumber(),
			org.getOrgUuid()
		};
		int result =update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOrgDao#getTreeNodeList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<TreeNode> getTreeNodeList(String orgParentUuid) {
		String sql="SELECT ORG_UUID AS ID,ORG_NAME AS TEXT,ORG_ICON AS ICON FROM APP_SYSTEM_ORG SP " +
			   	   "WHERE ORG_PARENT_UUID = ? ";
		List<TreeNode> list = query(sql, TreeNode.class, new Object[]{orgParentUuid});
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IOrgDao#getRoleList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getRoleList(String orgUUid) {
		String sql="SELECT ROLE_UUID AS ROLEUUID,ROLE_KEY AS ROLEKEY,ROLE_NAME AS ROLENAME,ROLE_NOTE AS ROLENOTE,ROLE_ORDERBY AS ROLEORDERBY,CREATE_TIME AS CREATETIME , " +
					"(SELECT COUNT(1) FROM APP_SYSTEM_ORG_ROLE WHERE ROLE_UUID = R.ROLE_UUID AND ORG_UUID = '"+orgUUid+"') AS COUNT "+
				   " FROM APP_SYSTEM_ROLE R ORDER BY ROLE_ORDERBY ASC ";
		List<Role> list = query(sql, Role.class, null);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IOrgDao#saveOrgRole(java.lang.String[], java.lang.String)
	 */
	public int saveOrgRole(String[] roleUuid, String orgUuid) {
		//先删除
		update("DELETE FROM APP_SYSTEM_ORG_ROLE WHERE ORG_UUID = ? ", new Object[]{orgUuid});
		String sql="INSERT INTO APP_SYSTEM_ORG_ROLE(UUID,ORG_UUID,ROLE_UUID)VALUES(?,?,?)";
		Object[][]params =new Object[roleUuid.length][];
		for (int i = 0; i < roleUuid.length; i++) {
			Object[]param = new Object[]{
					UUID.randomUUID().toString(),
					orgUuid,
					roleUuid[i]
			};
			params[i] = param;
		}
		int result =updateBatch(sql, params, roleUuid.length);
		return result;
	}

	
	

}
