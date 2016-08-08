/*
 * @项目名称: crm
 * @文件名称: LogsDaoImpl.java
 * @日期: 2015-8-11 下午06:11:29  
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
import com.hnzskj.base.core.bean.Logs;
import com.hnzskj.base.core.dao.ILogsDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：LogsDaoImpl.java   <br/>
 * 类描述：日志操作信息dao层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-11 下午06:11:29   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-11 下午06:11:29   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class LogsDaoImpl extends BaseDao implements ILogsDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.ILogsDao#addLogs(com.hnzskj.mainFrame.core.bean.Logs)
	 */
	public int addLogs(Logs logs) {
		String sql="INSERT INTO APP_SYSTEM_LOGS (LOG_UUID,LOG_TYPE,LOG_TITLE,LOG_CONTENT,LOG_STATE,LOG_USER_UUID,LOG_USER_NAME,LOG_IP,CREATE_TIME)" +
				   "VALUES(?,?,?,?,?,?,?,?,GETDATE())";
		Object[]params = new Object[]{
				null!=logs.getLogUuid()&&!"".equals(logs.getLogUuid())?logs.getLogUuid():UUID.randomUUID().toString(),
				logs.getLogType(),
				logs.getLogTitle(),
				logs.getLogContent(),
				logs.getLogState(),
				logs.getLogUserUuid(),
				logs.getLogUserName(),
				logs.getLogIp()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.ILogsDao#delLogs(java.lang.String)
	 */
	public int delLogs(String logUuids) {
		String sql="DELETE FROM APP_SYSTEM_LOGS WHERE LOG_UUID IN("+logUuids+")";
		int result = update(sql, null);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.ILogsDao#getLogs(java.lang.String)
	 */
	public Logs getLogs(String logUuid) {
		String sql="SELECT LOG_UUID,LOG_TYPE,LOG_TITLE,LOG_CONTENT,LOG_STATE,LOG_USER_UUID,LOG_USER_NAME,CREATE_TIME,LOG_IP FROM  APP_SYSTEM_LOGS " +
				   "WHERE LOGUUID = ? ";
		Logs logs = (Logs)get(sql, Logs.class,new Object[]{logUuid});
		return logs;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.ILogsDao#getLogsList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Logs> getLogsList(PageBean<Logs> pageBean, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Logs> empls = new ArrayList<Logs>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<Logs> epage = new PageBean<Logs>();// 临时变量，如果在page为null的情况下使用
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
			empls = query(sql,Logs.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, Logs.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.ILogsDao#updLogs(com.hnzskj.mainFrame.core.bean.Logs)
	 */
	public int updLogs(Logs logs) {
		String sql="UPDATE APP_SYSTEM_LOGS LOG_TYPE = ?,LOG_TITLE = ?,LOG_CONTENT = ?,LOG_STATE = ?,LOG_USER_UUID = ?,LOG_USER_NAME = ?,LOG_IP = ?  " +
				   "WHERE LOG_UUID = ? ";
		Object[]params = new Object[]{
				logs.getLogType(),
				logs.getLogTitle(),
				logs.getLogContent(),
				logs.getLogState(),
				logs.getLogUserUuid(),
				logs.getLogUserName(),
				logs.getLogIp(),
				logs.getLogUuid()
		};
		int result = update(sql, params);
		return result;
	}

}
