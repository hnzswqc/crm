/*
 * @项目名称: crm
 * @文件名称: IChartServiceImpl.java
 * @日期: 2015-11-14 下午03:41:59  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Chart;
import com.hnzskj.base.core.dao.IChartDao;
import com.hnzskj.base.core.service.IChartService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IChartServiceImpl.java   <br/>
 * 类描述：首页统计图业务层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-14 下午03:41:59   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-14 下午03:41:59   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class IChartServiceImpl implements IChartService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IChartDao chartDao = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IChartService#getOrgUserList()
	 */
	public List<Chart> getOrgUserList() {
		String sql="SELECT O.ORG_UUID AS PARAMFIELD,ORG_NAME AS LABELFIELD,COUNT(1) AS DATAFIELD FROM APP_SYSTEM_ORG O " +
				   "LEFT JOIN APP_SYSTEM_ORG_USER OU ON O.ORG_UUID = OU.ORG_UUID " +
				   "LEFT JOIN APP_SYSTEM_USER U ON U.USER_UUID = OU.USER_UUID " +
				   "WHERE O.ORG_STATE = '"+MfConstant.STATE_ACTIVATED+"' AND U.USER_STATE ='"+MfConstant.STATE_ACTIVATED+"' AND O.ORG_PARENT_UUID !='"+MfConstant.DEFAULT_ROOT_UUID+"' " +
				   "GROUP BY ORG_NAME,ORG_ORDERBY,O.ORG_UUID ORDER BY ORG_ORDERBY";
		List<Chart> list = chartDao.getChartList(sql);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IChartService#getUserDegreesList()
	 */
	public List<Chart> getUserDegreesList() {
		String sql="SELECT LABELFIELD,DATAFIELD,PARAMFIELD FROM (" +
				   "SELECT USER_DEGREES AS PARAMFIELD,(SELECT LABEL FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_USER_DEGREES+"' AND ID = USER_DEGREES) AS LABELFIELD,COUNT(1) AS DATAFIELD," +
				   "(SELECT ORDERBY FROM DBO.APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_USER_DEGREES+"' AND ID = USER_DEGREES) AS ORDERBY FROM APP_SYSTEM_USER " +
				   "WHERE USER_STATE = "+MfConstant.STATE_ACTIVATED+" AND USER_DEGREES IS NOT NULL AND USER_DEGREES!='' GROUP BY USER_DEGREES) A ORDER BY A.ORDERBY";
		List<Chart> list = chartDao.getChartList(sql);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IChartService#getUserJoinYearList()
	 */
	public List<Chart> getUserJoinYearList() {
		String sql="SELECT LABELFIELD,DATAFIELD FROM VIEW_USER_JOIN_YEAR";
		List<Chart> list = chartDao.getChartList(sql);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IChartService#getUserAgeList()
	 */
	public List<Chart> getUserAgeList() {
		String sql="SELECT LABELFIELD,DATAFIELD FROM VIEW_USER_AGE";
		List<Chart> list = chartDao.getChartList(sql);
		return list;
	}
	
	
	
}
