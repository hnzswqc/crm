/*
 * @项目名称: crm
 * @文件名称: LoggingServiceImpl.java
 * @日期: 2015-11-2 下午05:27:14  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.logging.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.business.logging.bean.Logging;
import com.hnzskj.business.logging.dao.ILoggingDao;
import com.hnzskj.business.logging.service.ILoggingService;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.Constant;

/**    
 * 项目名称：crm   <br/>
 * 类名称：LoggingServiceImpl.java   <br/>
 * 类描述： 日常工作记录service层实现类  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-2 下午05:27:14   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-2 下午05:27:14   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class LoggingServiceImpl implements ILoggingService {

	/**
	 * dao层注入
	 */
	@Autowired
	private ILoggingDao loggingDao = null;
	
	
	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.service.ILoggingService#addLogging(com.hnzskj.business.logging.bean.Logging)
	 */
	public boolean addLogging(Logging logging) {
		int result = loggingDao.addLogging(logging);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.service.ILoggingService#delLogging(com.hnzskj.business.logging.bean.Logging)
	 */
	public boolean delLogging(Logging logging) {
		int result = loggingDao.delLogging(logging);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.service.ILoggingService#getLoggingByParam(com.hnzskj.business.logging.bean.Logging)
	 */
	public Logging getLoggingByParam(Logging logging) {
		StringBuffer sb = new StringBuffer(200);
		if(null!=logging){
			//产品规格，模糊查询
			if(null!=logging.getLoggingProductNum()&&!"".equals(logging.getLoggingProductNum())){
				sb.append(" AND LOGGING_PRODUCT_NUM LIKE '").append(logging.getLoggingProductNum()).append("%'");
			}
			//员工编号
			if(null!=logging.getLoggingUserNumber()&&!"".equals(logging.getLoggingUserNumber())){
				sb.append(" AND LOGGING_USER_NUMBER ='").append(logging.getLoggingUserNumber()).append("'");
			}
			//员工姓名
			if(null!=logging.getLoggingUserName()&&!"".equals(logging.getLoggingUserName())){
				sb.append(" AND LOGGING_USER_NAME LIKE '%").append(logging.getLoggingUserName()).append("%'");
			}
			//大于等于查询开始时间
			if(null!=logging.getStartDate()&&!"".equals(logging.getStartDate())){
				sb.append(" AND LOGGING_DATE >='").append(logging.getStartDate()).append("'");
			}
			//小于等于查询结束时间
			if(null!=logging.getEndDate()&&!"".equals(logging.getEndDate())){
				sb.append(" AND LOGGING_DATE <='").append(logging.getEndDate()).append("'");
			}
			//根据工作性质查询
			if(null!=logging.getLoggingType()&&!"".equals(logging.getLoggingType())){
				sb.append(" AND LOGGING_TYPE = '").append(logging.getLoggingType()).append("'");
			}
		}
		Logging l = loggingDao.getLoggingByParam(sb.toString());
		return l;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.service.ILoggingService#searchLoggingList(com.hnzskj.common.bean.PageBean, com.hnzskj.business.logging.bean.Logging)
	 */
	public PageBean<Logging> searchLoggingList(PageBean<Logging> pageBean,
			Logging logging) {
		String fields="(SELECT ORG_NAME FROM APP_SYSTEM_ORG WHERE ORG_UUID IN (SELECT ORG_UUID FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = LOGGING_USER_UUID)) AS ORGNAME,LOGGING_UUID AS LOGGINGUUID,LOGGING_DATE AS LOGGINGDATE,LOGGING_USER_UUID AS LOGGINGUSERUUID,LOGGING_USER_NUMBER AS LOGGINGUSERNUMBER," +
				      "LOGGING_USER_NAME AS LOGGINGUSERNAME,LOGGING_USER_SEX AS LOGGINGUSERSEX,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+Constant.DIC_GENDER+"' AND ID = LOGGING_USER_SEX) AS LOGGINGUSERSEXTEXT," +
		      		  "LOGGING_PRODUCT_UUID AS LOGGINGPRODUCTUUID,LOGGING_PRODUCT_NUM AS LOGGINGPRODUCTNUM," +
				      "LOGGING_PRICE AS LOGGINGPRICE,LOGGING_NUMBER AS LOGGINGNUMBER,LOGGING_WEIGHT AS LOGGINGWEIGHT,LOGGING_ALL_WEIGHT AS LOGGINGALLWEIGHT,LOGGING_PG_NUM AS LOGGINGPGNUM," +
				      "LOGGING_TYPE AS LOGGINGTYPE,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+Constant.DIC_LOGGING_TYPE+"' AND ID = LOGGING_TYPE) AS LOGGINGTYPETEXT,LOGGING_WAGES AS LOGGINGWAGES,CREATE_USER_UUID AS CREATEUSERUUID,CREATE_USER_NAME AS CREATEUSERNAME,CREATE_USER_ID AS CREATEUSERID," +
				      "CREATE_USER_NUMBER AS CREATEUSERNUMBER,NOTE,CREATE_TIME AS CREATETIME,LOGGING_ERR_NUM AS LOGGINGERRNUM,LOGGING_ERR_RATIO AS LOGGINGERRRATIO,LOGGING_ERR_WAGES AS LOGGINGERRWAGES,LOGGING_REALITY_WAGES AS LOGGINGREALITYWAGES,LOGGING_CHECK_USER_UUID AS LOGGINGCHECKUSERUUID,LOGGING_CHECK_USER_NAME AS LOGGINGCHECKUSERNAME," +
				      "LOGGING_CHECK_USER_NUMBER AS LOGGINGCHECKUSERNUMBER,LOGGING_PG_RATIO AS LOGGINGPGRATIO  ";
		StringBuffer sb = new StringBuffer(" WHERE 1=1 ");
		if(null!=logging){
			//产品规格，模糊查询
			if(null!=logging.getLoggingProductNum()&&!"".equals(logging.getLoggingProductNum())){
				sb.append(" AND LOGGING_PRODUCT_NUM LIKE '").append(logging.getLoggingProductNum()).append("%'");
			}
			//员工编号
			if(null!=logging.getLoggingUserNumber()&&!"".equals(logging.getLoggingUserNumber())){
				sb.append(" AND LOGGING_USER_NUMBER LIKE '").append(logging.getLoggingUserNumber()).append("%'");
			}
			//员工姓名
			if(null!=logging.getLoggingUserName()&&!"".equals(logging.getLoggingUserName())){
				sb.append(" AND LOGGING_USER_NAME LIKE '%").append(logging.getLoggingUserName()).append("%'");
			}
			//大于等于查询开始时间
			if(null!=logging.getStartDate()&&!"".equals(logging.getStartDate())){
				sb.append(" AND LOGGING_DATE >='").append(logging.getStartDate()).append("'");
			}
			//小于等于查询结束时间
			if(null!=logging.getEndDate()&&!"".equals(logging.getEndDate())){
				sb.append(" AND LOGGING_DATE <='").append(logging.getEndDate()).append("'");
			}
			//根据工作性质查询
			if(null!=logging.getLoggingType()&&!"".equals(logging.getLoggingType())){
				sb.append(" AND LOGGING_TYPE = '").append(logging.getLoggingType()).append("'");
			}
		}
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("LOGGING_DATE ", "DESC");
		orderby.put("CREATE_TIME ", "DESC");
		pageBean = loggingDao.searchLoggingList(pageBean, fields, sb.toString(), null, orderby, " APP_BUSINESS_LOGGING ", " LOGGING_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.service.ILoggingService#updLogging(com.hnzskj.business.logging.bean.Logging)
	 */
	public boolean updLogging(Logging logging) {
		int result = loggingDao.updLogging(logging);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.service.ILoggingService#getLeaderWages(com.hnzskj.business.logging.bean.Logging)
	 */
	public Logging getLeaderWages(Logging logging) {
		Logging l = loggingDao.getLeaderWages(logging);
		return l;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.service.ILoggingService#searchLoggingList(com.hnzskj.business.logging.bean.Logging)
	 */
	public List<Logging> searchLoggingList(Logging logging) {
		StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=logging){
			//员工编号
			if(null!=logging.getLoggingUserNumber()&&!"".equals(logging.getLoggingUserNumber())){
				sb.append(" AND LOGGING_USER_NUMBER LIKE '").append(logging.getLoggingUserNumber()).append("%'");
			}
			//员工姓名
			if(null!=logging.getLoggingUserName()&&!"".equals(logging.getLoggingUserName())){
				sb.append(" AND LOGGING_USER_NAME LIKE '%").append(logging.getLoggingUserName()).append("%'");
			}
			//大于等于查询开始时间
			if(null!=logging.getStartDate()&&!"".equals(logging.getStartDate())){
				sb.append(" AND LOGGING_DATE >='").append(logging.getStartDate()).append("'");
			}
			//小于等于查询结束时间
			if(null!=logging.getEndDate()&&!"".equals(logging.getEndDate())){
				sb.append(" AND LOGGING_DATE <='").append(logging.getEndDate()).append("'");
			}
			//根据工作性质查询
			if(null!=logging.getLoggingType()&&!"".equals(logging.getLoggingType())){
				sb.append(" AND LOGGING_TYPE = '").append(logging.getLoggingType()).append("'");
			}
		}
		List<Logging> list = loggingDao.searchLoggingList(sb.toString());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.service.ILoggingService#getYear()
	 */
	public List<Logging> getYear() {
		List<Logging> list = loggingDao.getYear();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.logging.service.ILoggingService#getYearDetailReport(com.hnzskj.business.logging.bean.Logging)
	 */
	public List<Logging> getYearDetailReport(Logging logging) {
		StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=logging){
			//员工编号
			if(null!=logging.getLoggingUserNumber()&&!"".equals(logging.getLoggingUserNumber())){
				sb.append(" AND LOGGING_USER_NUMBER LIKE '").append(logging.getLoggingUserNumber()).append("%'");
			}
			//员工姓名
			if(null!=logging.getLoggingUserName()&&!"".equals(logging.getLoggingUserName())){
				sb.append(" AND LOGGING_USER_NAME LIKE '%").append(logging.getLoggingUserName()).append("%'");
			}
			//大于等于查询开始时间
			if(null!=logging.getStartDate()&&!"".equals(logging.getStartDate())){
				sb.append(" AND LOGGING_DATE >='").append(logging.getStartDate()).append("'");
			}
			//小于等于查询结束时间
			if(null!=logging.getEndDate()&&!"".equals(logging.getEndDate())){
				sb.append(" AND LOGGING_DATE <='").append(logging.getEndDate()).append("'");
			}
			//根据工作性质查询
			if(null!=logging.getLoggingType()&&!"".equals(logging.getLoggingType())){
				sb.append(" AND LOGGING_TYPE = '").append(logging.getLoggingType()).append("'");
			}
		}
		List<Logging> list = loggingDao.getYearDetailReport(sb.toString());
		return  list;
	}

	
	
	

}
