/*
 * @项目名称: crm
 * @文件名称: PowerDaoImpl.java
 * @日期: 2015-8-11 上午11:24:22  
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
import com.hnzskj.common.util.Constant;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Power;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.base.core.dao.IPowerDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：PowerDaoImpl.java   <br/>
 * 类描述： 权限dao层实现类  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-11 上午11:24:22   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-11 上午11:24:22   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class PowerDaoImpl extends BaseDao implements IPowerDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IPower#addPower(com.hnzskj.mainFrame.core.bean.Power)
	 */
	public int addPower(Power power) {
		String sql="INSERT INTO APP_SYSTEM_POWER(POWER_UUID,POWER_NAME,POWER_ICON,POWER_LOGO,POWER_STATE,POWER_URL,POWER_PARENT_UUID,POWER_MODEL_UUID,POWER_NOTE,POWER_ORDERBY,CREATE_TIME) " +
				   "VALUES(?,?,?,?,?,?,?,?,?,?,GETDATE())";
		Object[]params = new Object[]{
				null!=power.getPowerUuid()&&!"".equals(power.getPowerUuid())?power.getPowerUuid():UUID.randomUUID().toString(),
				power.getPowerName(),
				power.getPowerIcon(),
				power.getPowerLogo(),
				power.getPowerState(),
				power.getPowerUrl(),
				power.getPowerParentUuid(),
				power.getPowerModelUuid(),
				power.getPowerNote(),
				power.getPowerOrderby()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IPower#delPower(java.lang.String)
	 */
	public int delPower(String powerUuid) {
		String sql="DELETE FROM APP_SYSTEM_POWER WHERE POWER_UUID = ? ";
		int result = update(sql, new Object[]{powerUuid});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IPower#getPower(java.lang.String)
	 */
	public Power getPower(String powerUuid) {
		String sql="SELECT POWER_UUID AS POWERUUID,POWER_UUID AS ID,POWER_NAME AS POWERNAME,POWER_NAME AS TEXT,POWER_ICON AS POWERICON,POWER_LOGO AS POWERLOGO,POWER_STATE AS POWERSTATE,POWER_URL AS POWERURL,POWER_PARENT_UUID AS POWERPARENTUUID,POWER_MODEL_UUID AS POWERMODELUUID,POWER_NOTE AS POWERNOTE,POWER_ORDERBY AS POWERORDERBY,CREATE_TIME AS CREATETIME  FROM APP_SYSTEM_POWER " +
				   "WHERE POWER_UUID = ? ";
		Power power = (Power)get(sql, Power.class,new Object[]{powerUuid});
		return power;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IPower#getPowerList(com.hnzskj.common.bean.Page, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Power> getPowerList(PageBean<Power> pageBean, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Power> empls = new ArrayList<Power>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<Power> epage = new PageBean<Power>();// 临时变量，如果在page为null的情况下使用
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
			empls = query(sql,Power.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, Power.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IPower#updPower(com.hnzskj.mainFrame.core.bean.Power)
	 */
	public int updPower(Power power) {
		String sql="UPDATE APP_SYSTEM_POWER SET POWER_NAME = ?,POWER_ICON = ?,POWER_LOGO = ?,POWER_STATE = ?,POWER_URL = ?,POWER_PARENT_UUID = ?,POWER_MODEL_UUID = ? ,POWER_NOTE = ?,POWER_ORDERBY = ? " +
				   "WHERE POWER_UUID = ? ";
		Object[]params = new Object[]{
				power.getPowerName(),
				power.getPowerIcon(),
				power.getPowerLogo(),
				power.getPowerState(),
				power.getPowerUrl(),
				power.getPowerParentUuid(),
				power.getPowerModelUuid(),
				power.getPowerNote(),
				power.getPowerOrderby(),
				power.getPowerUuid()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IPowerDao#getPowerList(java.lang.String,java.lang.String,java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<TreeNode> getPowerList(String modelUuid,String powerParentUuid,String userUuid) {
		String sqlcondition =" WHERE 1=1 ";
		if(null!=userUuid&&!"".equals(userUuid)){
			if(!Constant.SUPER_USER_UUID.equals(userUuid)){
				sqlcondition+=" AND POWER_UUID IN(SELECT FOREIGNKEY FROM DBO.APP_SYSTEM_ROLE_AUTHORITY WHERE ROLEUUID IN (SELECT ROLE_UUID  FROM  APP_SYSTEM_ROLE_USER WHERE USER_UUID = '"+userUuid+"') AND TYPE = '"+MfConstant.AUTHORITY_POWER+"')";
			}
		}
		String sql="SELECT POWER_UUID AS ID,POWER_NAME AS TEXT,POWER_ICON AS ICON,POWER_LOGO AS POWERLOGO,POWER_URL AS URL,(SELECT COUNT(1) FROM APP_SYSTEM_POWER P WHERE POWER_PARENT_UUID = SP.POWER_UUID) AS CHILDSNUM FROM APP_SYSTEM_POWER SP " +
					sqlcondition+" AND POWER_MODEL_UUID = ? AND POWER_PARENT_UUID = ? AND POWER_STATE = ? ORDER BY POWER_ORDERBY ASC ";
		List<TreeNode> list = queryMax(sql, TreeNode.class, new Object[]{modelUuid,powerParentUuid,MfConstant.STATE_ACTIVATED});
		return list;
	}
	
	

}
