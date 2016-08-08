/*
 * @项目名称: crm
 * @文件名称: ProbationStaffDaoImpl.java
 * @日期: 2015-12-7 下午09:11:15  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.probationstaff.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.DataUtil;
import com.hnzskj.oa.common.util.MfConstant;
import com.hnzskj.oa.probationstaff.bean.ProbationStaff;
import com.hnzskj.oa.probationstaff.dao.IProbationStaffDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ProbationStaffDaoImpl.java   <br/>
 * 类描述：   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-7 下午09:11:15   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-7 下午09:11:15   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class ProbationStaffDaoImpl extends BaseDao implements IProbationStaffDao{

	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.probationstaff.dao.IProbationStaffDao#addProbationStaff(com.hnzskj.oa.probationstaff.bean.ProbationStaff)
	 */
	public int addProbationStaff(ProbationStaff probationStaff) {
		String sql="INSERT INTO APP_SYSTEM_USER(USER_UUID,USER_NAME,USER_GENDER,USER_AGE,PBF_DATE,PBF_LEADER,USER_NOTE,USER_STATE,USER_BIRTYDAY,CREATE_TIME,id_card)" +
				   "VALUES(?,?,?,?,?,?,?,?,?)";
		Object[]params = new Object[]{
				null!=probationStaff.getUserUuid()&&!"".equals(probationStaff.getUserUuid())?probationStaff.getUserUuid():UUID.randomUUID().toString(),
				probationStaff.getUserName(),
				probationStaff.getUserGender(),
				probationStaff.getUserAge(),
				null!=probationStaff.getPbfDate()&&!"".equals(probationStaff.getPbfDate())?probationStaff.getPbfDate():null,
				probationStaff.getPbfLeader(),
				probationStaff.getUserNote(),
				MfConstant.DIC_USER_STATE_SYQYG,
				null!=probationStaff.getUserBirthday()&&!"".equals(probationStaff.getUserBirthday())?probationStaff.getUserBirthday():null,
				DataUtil.formateDefaultDate(),
				probationStaff.getIdCard()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.probationstaff.dao.IProbationStaffDao#delProbationStaff(com.hnzskj.oa.probationstaff.bean.ProbationStaff)
	 */
	public int delProbationStaff(ProbationStaff probationStaff) {
		String sql="DELETE FROM APP_SYSTEM_USER WHERE USER_UUID = ? ";
		int result = update(sql, new Object[]{probationStaff.getUserUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.probationstaff.dao.IProbationStaffDao#searchProbationStaffList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<ProbationStaff> searchProbationStaffList(
			PageBean<ProbationStaff> pageBean, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<ProbationStaff> empls = new ArrayList<ProbationStaff>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<ProbationStaff> epage = new PageBean<ProbationStaff>();// 临时变量，如果在page为null的情况下使用
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
			empls = query(sql,ProbationStaff.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, ProbationStaff.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.probationstaff.dao.IProbationStaffDao#updProbationStaff(com.hnzskj.oa.probationstaff.bean.ProbationStaff)
	 */
	public int updProbationStaff(ProbationStaff probationStaff) {
		String sql="UPDATE APP_SYSTEM_USER SET USER_NAME = ? ,USER_GENDER = ? ,USER_AGE = ? ,PBF_DATE = ? ,PBF_LEADER = ? ,USER_NOTE = ?,USER_BIRTYDAY = ?,ID_CARD = ?  " +
				   "WHERE USER_UUID = ? ";
		Object[]params = new Object[]{
				probationStaff.getUserName(),
				probationStaff.getUserGender(),
				probationStaff.getUserAge(),
				null!=probationStaff.getPbfDate()&&!"".equals(probationStaff.getPbfDate())?probationStaff.getPbfDate():null,
				probationStaff.getPbfLeader(),
				probationStaff.getUserNote(),
				null!=probationStaff.getUserBirthday()&&!"".equals(probationStaff.getUserBirthday())?probationStaff.getUserBirthday():null,
				probationStaff.getIdCard(),
				probationStaff.getUserUuid()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.probationstaff.dao.IProbationStaffDao#searchOrgRoleList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Dic> searchOrgRoleList(String orgUuid) {
		String sql="SELECT ROLE_UUID AS ID,ROLE_NAME AS [TEXT] FROM APP_SYSTEM_ROLE WHERE ROLE_UUID IN ( " +
				"SELECT ROLE_UUID FROM APP_SYSTEM_ORG_ROLE WHERE ORG_UUID = ? " +
				") ORDER BY ROLE_ORDERBY ASC ";
		List<Dic> list = query(sql, Dic.class, new Object[]{orgUuid});
		return list;
	}
	
	
}
