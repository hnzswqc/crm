/*
 * @项目名称: crm
 * @文件名称: LoggingDaoImpl.java
 * @日期: 2015-11-2 下午04:59:43  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.logging.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.business.logging.bean.Logging;
import com.hnzskj.business.logging.dao.ILoggingDao;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.Constant;
import com.hnzskj.common.util.DataUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：LoggingDaoImpl.java   <br/>
 * 类描述：日常工作dao层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-2 下午04:59:43   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-2 下午04:59:43   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class LoggingDaoImpl extends BaseDao implements ILoggingDao{

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.dao.ILoggingDao#addLogging(com.hnzskj.business.logging.bean.Logging)
	 */
	public int addLogging(Logging logging) {
		String sql="INSERT INTO APP_BUSINESS_LOGGING(LOGGING_UUID,LOGGING_DATE,LOGGING_USER_UUID,LOGGING_USER_NUMBER,LOGGING_USER_NAME,LOGGING_USER_SEX,LOGGING_PRODUCT_UUID,LOGGING_PRODUCT_NUM,LOGGING_PRICE,LOGGING_NUMBER,LOGGING_WEIGHT,LOGGING_ALL_WEIGHT,LOGGING_PG_NUM,LOGGING_TYPE,LOGGING_WAGES,CREATE_USER_UUID,CREATE_USER_NAME,CREATE_USER_ID,CREATE_USER_NUMBER,NOTE,CREATE_TIME," +
				"LOGGING_ERR_NUM,LOGGING_ERR_RATIO,LOGGING_ERR_WAGES,LOGGING_REALITY_WAGES,LOGGING_CHECK_USER_UUID,LOGGING_CHECK_USER_NAME,LOGGING_CHECK_USER_NUMBER,LOGGING_PG_RATIO)" +
				   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[]params = new Object[]{
			null!=logging.getLoggingUuid()&&!"".equals(logging.getLoggingUuid())?logging.getLoggingUuid():UUID.randomUUID().toString(),
			logging.getLoggingDate(),
			logging.getLoggingUserUuid(),
			logging.getLoggingUserNumber(),
			logging.getLoggingUserName(),
			logging.getLoggingUserSex(),
			logging.getLoggingProductUuid(),
			logging.getLoggingProductNum(),
			logging.getLoggingPrice(),
			logging.getLoggingNumber(),
			logging.getLoggingWeight(),
			logging.getLoggingAllWeight(),
			logging.getLoggingPgNum(),
			logging.getLoggingType(),
			logging.getLoggingWages(),
			logging.getCreateUserUuid(),
			logging.getCreateUserName(),
			logging.getCreateUserId(),
			logging.getCreateUserNumber(),
			logging.getNote(),
			DataUtil.formateDate(new Date()),
			logging.getLoggingErrNum(),
			logging.getLoggingErrRatio(),
			logging.getLoggingErrWages(),
			logging.getLoggingRealityWages(),
			logging.getLoggingCheckUserUuid(),
			logging.getLoggingCheckUserName(),
			logging.getLoggingCheckUserNumber(),
			logging.getLoggingPgRatio()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.dao.ILoggingDao#delLogging(com.hnzskj.business.logging.bean.Logging)
	 */
	public int delLogging(Logging logging) {
		String sql="DELETE FROM APP_BUSINESS_LOGGING WHERE LOGGING_UUID = ? ";
		int result = update(sql, new Object[]{null!=logging?logging.getLoggingUuid():""});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.dao.ILoggingDao#getLoggingByParam(java.lang.String)
	 */
	public Logging getLoggingByParam(String sqlCondition) {
		String sql="SELECT LOGGING_UUID AS LOGGINGUUID,LOGGING_DATE AS LOGGINGDAGTE,LOGGING_USER_UUID AS LOGGINGUSERUUID,LOGGING_USER_NUMBER AS LOGGINGUSERNUMBER," +
				   "LOGGING_USER_NAME AS LOGGINGUSERNAME,LOGGING_USER_SEX AS LOGGINGUSERSEX,LOGGING_PRODUCT_UUID AS LOGGINGPRODUCTUUID,LOGGING_PRODUCT_NUM AS LOGGINGPRODUCTNUM," +
				   "LOGGING_PRICE AS LOGGINGPRICE,LOGGING_NUMBER AS LOGGINGNUMBER,LOGGING_WEIGHT AS LOGGINGWEIGHT,LOGGING_ALL_WEIGHT AS LOGGINGALLWEIGHT,LOGGING_PG_NUM AS LOGGINGPGNUM," +
				   "LOGGING_TYPE AS LOGGINGTYPE,LOGGING_WAGES AS LOGGINGWAGES,CREATE_USER_UUID AS CREATEUSERUUID,CREATE_USER_NAME AS CREATEUSERNAME,CREATE_USER_ID AS CREATEUSERID," +
				   "CREATE_USER_NUMBER AS CREATEUSERNUMBER,NOTE,CREATE_TIME AS CREATETIME,LOGGING_PG_RATIO AS LOGGINGPGRATIO FROM APP_BUSINESS_LOGGING WHERE 1=1 "+sqlCondition;
		Logging logging = (Logging)get(sql, Logging.class,null);
		return logging;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.dao.ILoggingDao#searchLoggingList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Logging> searchLoggingList(PageBean<Logging> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Logging> empls = new ArrayList<Logging>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<Logging> epage = new PageBean<Logging>();// 临时变量，如果在page为null的情况下使用
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
			empls = query(sql,Logging.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, Logging.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.dao.ILoggingDao#updLogging(com.hnzskj.business.logging.bean.Logging)
	 */
	public int updLogging(Logging logging) {
		String sql="UPDATE APP_BUSINESS_LOGGING SET LOGGING_DATE = ? ,LOGGING_USER_UUID = ? ,LOGGING_USER_NUMBER = ? ,LOGGING_USER_NAME = ? ,LOGGING_USER_SEX = ? ,LOGGING_PRODUCT_UUID = ? ,LOGGING_PRODUCT_NUM = ? ," +
				   "LOGGING_PRICE = ? ,LOGGING_NUMBER = ? ,LOGGING_WEIGHT = ? ,LOGGING_ALL_WEIGHT = ? ,LOGGING_PG_NUM = ? ,LOGGING_TYPE = ? ,LOGGING_WAGES = ? ,CREATE_USER_UUID = ? ,CREATE_USER_NAME = ? ," +
				   "CREATE_USER_ID = ? ,CREATE_USER_NUMBER = ? ,NOTE = ? ,LOGGING_ERR_NUM = ? ,LOGGING_ERR_RATIO = ? ,LOGGING_ERR_WAGES = ? ,LOGGING_REALITY_WAGES = ? ,LOGGING_CHECK_USER_UUID = ? ," +
				   "LOGGING_CHECK_USER_NAME = ? ,LOGGING_CHECK_USER_NUMBER  = ?,LOGGING_PG_RATIO = ? " +
				   "WHERE LOGGING_UUID = ? ";
			Object[]params = new Object[]{
				logging.getLoggingDate(),
				logging.getLoggingUserUuid(),
				logging.getLoggingUserNumber(),
			    logging.getLoggingUserName(),
			    logging.getLoggingUserSex(),
				logging.getLoggingProductUuid(),
			    logging.getLoggingProductNum(),
				logging.getLoggingPrice(),
				logging.getLoggingNumber(),
				logging.getLoggingWeight(),
				logging.getLoggingAllWeight(),
				logging.getLoggingPgNum(),
				logging.getLoggingType(),
				logging.getLoggingWages(),
				logging.getCreateUserUuid(),
				logging.getCreateUserName(),
				logging.getCreateUserId(),
				logging.getCreateUserNumber(),
				logging.getNote(),
				logging.getLoggingErrNum(),
				logging.getLoggingErrRatio(),
				logging.getLoggingErrWages(),
				logging.getLoggingRealityWages(),
				logging.getLoggingCheckUserUuid(),
				logging.getLoggingCheckUserName(),
				logging.getLoggingCheckUserNumber(),
				logging.getLoggingPgRatio(),
				logging.getLoggingUuid()
			};
			int result = update(sql, params);
			return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.dao.ILoggingDao#getLeaderWages(com.hnzskj.business.logging.bean.Logging)
	 */
	public Logging getLeaderWages(Logging logging) {
		String sql="SELECT ISNULL(AVG(A.LOGGINGREALITYWAGES),0) AS LOGGINGREALITYWAGES FROM ( "+
				   "SELECT ISNULL(SUM(LOGGING_REALITY_WAGES),0) AS LOGGINGREALITYWAGES FROM APP_BUSINESS_LOGGING " +
				   "WHERE LOGGING_USER_UUID IN(SELECT USER_UUID FROM APP_SYSTEM_ORG_USER " +
				   "WHERE ORG_UUID IN(SELECT ORG_UUID FROM  APP_SYSTEM_ORG_USER " +
				   "WHERE USER_UUID =( SELECT USER_UUID FROM APP_SYSTEM_USER WHERE USER_NUMBER = ? ))) " +
				   "AND LOGGING_USER_UUID NOT IN (SELECT USER_UUID FROM APP_SYSTEM_USER WHERE FORMAL_TIME IS NOT NULL AND FORMAL_TIME !='')"+
				   "AND LOGGING_TYPE = '"+Constant.LOGGING_TYPE_BZ+"' AND LOGGING_DATE = ? GROUP BY LOGGING_USER_NUMBER ) A";
		Object[]params = new Object[]{
				logging.getLoggingUserNumber(),
				logging.getLoggingDate()
		};
		Logging l = (Logging)get(sql, Logging.class, params);
		return l;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.dao.ILoggingDao#searchLoggingList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Logging> searchLoggingList(String sqlCondition) {
		String sql="SELECT ISNULL(SUM(LOGGING_WAGES),0) AS LOGGINGWAGES,ISNULL(SUM(LOGGING_ALL_WEIGHT),0) AS LOGGINGALLWEIGHT," +
				   "(SELECT ORG_NAME FROM APP_SYSTEM_ORG WHERE ORG_UUID IN (SELECT ORG_UUID FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = LOGGING_USER_UUID)) AS ORGNAME, " +
				   "LOGGING_USER_UUID AS LOGGINGUSERUUID,LOGGING_USER_NAME AS LOGGINGUSERNAME,LOGGING_USER_NUMBER AS LOGGINGUSERNUMBER,LOGGING_USER_SEX AS LOGGINGUSERSEX," +
				   "LOGGING_TYPE AS LOGGINGTYPE,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+Constant.DIC_LOGGING_TYPE+"' AND ID = LOGGING_TYPE) AS LOGGINGTYPETEXT," +
				   "(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+Constant.DIC_GENDER+"' AND ID = LOGGING_USER_SEX) AS LOGGINGUSERSEXTEXT FROM APP_BUSINESS_LOGGING " +sqlCondition+
				   "GROUP BY LOGGING_USER_NUMBER,LOGGING_USER_NAME,LOGGING_USER_SEX,LOGGING_USER_UUID,LOGGING_TYPE ORDER BY LOGGING_USER_NUMBER ASC ";
		List<Logging> list = (List<Logging>)query(sql, Logging.class, null);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.dao.ILoggingDao#getYear()
	 */
	@SuppressWarnings("unchecked")
	public List<Logging> getYear() {
		String sql="SELECT DISTINCT SUBSTRING(CONVERT(VARCHAR(20),LOGGING_DATE),0,5) AS LOGGINGDATE FROM APP_BUSINESS_LOGGING";
		List<Logging> list = (List<Logging>)query(sql, Logging.class, null);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.dao.ILoggingDao#getYearDetailReport(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Logging> getYearDetailReport(String sqlCondition) {
		String sql="SELECT SUBSTRING(CONVERT(VARCHAR(20),LOGGING_DATE),6,2) AS LOGGINGDATE,ISNULL(SUM(LOGGING_WAGES),0) AS LOGGINGWAGES,ISNULL(SUM(LOGGING_ALL_WEIGHT),0) AS LOGGINGALLWEIGHT," +
				   "(SELECT ORG_NAME FROM APP_SYSTEM_ORG WHERE ORG_UUID IN (SELECT ORG_UUID FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = LOGGING_USER_UUID)) AS ORGNAME, " +
				   "LOGGING_USER_UUID AS LOGGINGUSERUUID,LOGGING_USER_NAME AS LOGGINGUSERNAME,LOGGING_USER_NUMBER AS LOGGINGUSERNUMBER,LOGGING_USER_SEX AS LOGGINGUSERSEX," +
				   "LOGGING_TYPE AS LOGGINGTYPE,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+Constant.DIC_LOGGING_TYPE+"' AND ID = LOGGING_TYPE) AS LOGGINGTYPETEXT," +
				   "(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+Constant.DIC_GENDER+"' AND ID = LOGGING_USER_SEX) AS LOGGINGUSERSEXTEXT FROM APP_BUSINESS_LOGGING " +sqlCondition+
				   "GROUP BY LOGGING_USER_NUMBER,LOGGING_USER_NAME,LOGGING_USER_SEX,LOGGING_USER_UUID,LOGGING_TYPE , SUBSTRING(CONVERT(VARCHAR(20),LOGGING_DATE),6,2) ORDER BY  SUBSTRING(CONVERT(VARCHAR(20),LOGGING_DATE),6,2)  ASC ";
		List<Logging> list = (List<Logging>)query(sql, Logging.class, null);
		return list;
	}

	
	
	
	
	
	
	
}
