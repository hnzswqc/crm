/*
 * @项目名称: crm
 * @文件名称: OperateDaoImpl.java
 * @日期: 2015-8-28 下午03:48:45  
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
import com.hnzskj.base.core.bean.Operate;
import com.hnzskj.base.core.dao.IOperateDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：OperateDaoImpl.java   <br/>
 * 类描述： 功能操作dao层实现类  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 下午03:48:45   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 下午03:48:45   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class OperateDaoImpl extends BaseDao implements IOperateDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOperateDao#addOperate(com.hnzskj.mainFrame.core.bean.Operate)
	 */
	public int addOperate(Operate operate) {
		String sql="INSERT INTO APP_SYSTEM_OPERATE (OPERATE_UUID,OPERATE_KEY,OPERATE_NAME,OPERATE_ICON,OPERATE_NOTE,OPERATE_ORDERBY,POWER_UUID,CREATE_TIME)" +
				   "VALUES(?,?,?,?,?,?,?,GETDATE())";
		Object[]params = new Object[]{
				null!=operate.getOperateUuid()&&!"".equals(operate.getOperateUuid())?operate.getOperateUuid():UUID.randomUUID().toString(),
				operate.getOperateKey(),
				operate.getOperateName(),
				operate.getOperateIcon(),
				operate.getOperateNote(),
				operate.getOperateOrderBy(),
				operate.getPowerUuid()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOperateDao#delOperate(com.hnzskj.mainFrame.core.bean.Operate)
	 */
	public int delOperate(Operate operate) {
		String sql="DELETE FROM APP_SYSTEM_OPERATE WHERE OPERATE_UUID = ? ";
		int result = update(sql, new Object[]{operate.getOperateUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOperateDao#getOperateByKey(com.hnzskj.mainFrame.core.bean.Operate)
	 */
	public Operate getOperateByKey(Operate operate) {
		String sql="SELECT OPERATE_UUID AS OPERATEUUID,OPERATE_KEY AS OPERATEKEY,OPERATE_NAME AS OPERATENAME,OPERATE_ICON AS OPERATEICON,OPERATE_NOTE AS OPERATENOTE,OPERATE_ORDERBY AS OPERATEORDERBY,POWER_UUID AS POWERUUID,CREATE_TIME AS CREATETIME FROM APP_SYSTEM_OPERATE " +
				   "WHERE OPERATE_KEY = ? ";
		operate = (Operate)get(sql, Operate.class,new Object[]{operate.getOperateKey()});
		return operate;
	}
	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOperateDao#getOperateList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Operate> getOperateList(PageBean<Operate> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Operate> empls = new ArrayList<Operate>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<Operate> epage = new PageBean<Operate>();// 临时变量，如果在page为null的情况下使用
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
			empls = query(sql,Operate.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, Operate.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IOperateDao#updOperate(com.hnzskj.mainFrame.core.bean.Operate)
	 */
	public int updOperate(Operate operate) {
		String sql="UPDATE APP_SYSTEM_OPERATE SET OPERATE_KEY = ? ,OPERATE_NAME = ? ,OPERATE_ICON = ? ,OPERATE_NOTE = ?,OPERATE_ORDERBY = ? " +
				   "WHERE OPERATE_UUID = ? ";
		Object[]params = new Object[]{
				operate.getOperateKey(),
				operate.getOperateName(),
				operate.getOperateIcon(),
				operate.getOperateNote(),
				operate.getOperateOrderBy(),
				operate.getOperateUuid()
		};
		int result = update(sql, params);
		return result;
	}

}
