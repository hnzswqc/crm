/*
 * @项目名称: crm
 * @文件名称: WorkLoadChartServiceImpl.java
 * @日期: 2015-11-19 上午09:09:17  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.chart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.base.core.bean.Chart;
import com.hnzskj.business.chart.dao.IWorkLoadChartDao;
import com.hnzskj.business.chart.service.IWorkLoadChartService;
import com.hnzskj.common.util.Constant;

/**    
 * 项目名称：crm   <br/>
 * 类名称：WorkLoadChartServiceImpl.java   <br/>
 * 类描述：工作量service层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-19 上午09:09:17   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-19 上午09:09:17   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class WorkLoadChartServiceImpl implements IWorkLoadChartService {
	
	/**
	 * dao层注入
	 */
	@Autowired
	private IWorkLoadChartDao workLoadChartDao  = null;

	/* (non-Javadoc)
	 * @see com.hnzskj.business.chart.service.IWorkLoadChartService#getBzListChart(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public List<Chart> getBzListChart(String startDate, String endDate,
			String orderby, Integer topNum) {
		String sql="SELECT TOP "+topNum+" * FROM (SELECT  LOGGING_USER_NUMBER AS PARAMFIELD, LOGGING_USER_NAME AS LABELFIELD,ISNULL(SUM(LOGGING_ALL_WEIGHT),0) AS DATAFIELD FROM APP_BUSINESS_LOGGING WHERE LOGGING_TYPE='"+Constant.LOGGING_TYPE_BZ+"' AND LOGGING_DATE>='"+startDate+"' and LOGGING_DATE<='"+endDate+"' GROUP BY LOGGING_USER_NAME,LOGGING_USER_NUMBER) A  ORDER BY A.DATAFIELD "+orderby;
		List<Chart> list = workLoadChartDao.getChartList(sql);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.chart.service.IWorkLoadChartService#getZgListChart(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public List<Chart> getZgListChart(String startDate, String endDate,
			String orderby, Integer topNum) {
		String sql="SELECT TOP "+topNum+" * FROM (SELECT  LOGGING_USER_NUMBER AS PARAMFIELD, LOGGING_USER_NAME AS LABELFIELD,ISNULL(SUM(LOGGING_ALL_WEIGHT),0) AS DATAFIELD FROM APP_BUSINESS_LOGGING WHERE LOGGING_TYPE='"+Constant.LOGGING_TYPE_ZG+"' AND LOGGING_DATE>='"+startDate+"' and LOGGING_DATE<='"+endDate+"' GROUP BY LOGGING_USER_NAME,LOGGING_USER_NUMBER) A  ORDER BY A.DATAFIELD "+orderby;
		List<Chart> list = workLoadChartDao.getChartList(sql);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.chart.service.IWorkLoadChartService#getPgListChart(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public List<Chart> getPgListChart(String startDate, String endDate,
			String orderby, Integer topNum) {
		String sql="SELECT TOP "+topNum+" * FROM (SELECT  LOGGING_USER_NUMBER AS PARAMFIELD, LOGGING_USER_NAME AS LABELFIELD,ISNULL(SUM(LOGGING_ALL_WEIGHT),0) AS DATAFIELD FROM APP_BUSINESS_LOGGING WHERE LOGGING_TYPE='"+Constant.LOGGING_TYPE_PG+"' AND LOGGING_DATE>='"+startDate+"' and LOGGING_DATE<='"+endDate+"' GROUP BY LOGGING_USER_NAME,LOGGING_USER_NUMBER) A  ORDER BY A.DATAFIELD "+orderby;
		List<Chart> list = workLoadChartDao.getChartList(sql);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.chart.service.IWorkLoadChartService#getTgListChart(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public List<Chart> getTgListChart(String startDate, String endDate,
			String orderby, Integer topNum) {
		String sql="SELECT TOP "+topNum+" * FROM (SELECT  LOGGING_USER_NUMBER AS PARAMFIELD, LOGGING_USER_NAME AS LABELFIELD,ISNULL(SUM(LOGGING_NUMBER),0) AS DATAFIELD FROM APP_BUSINESS_LOGGING WHERE LOGGING_TYPE='"+Constant.LOGGING_TYPE_TG+"' AND LOGGING_DATE>='"+startDate+"' and LOGGING_DATE<='"+endDate+"' GROUP BY LOGGING_USER_NAME,LOGGING_USER_NUMBER) A  ORDER BY A.DATAFIELD "+orderby;
		List<Chart> list = workLoadChartDao.getChartList(sql);
		return list;
	}
	
	
	

}
