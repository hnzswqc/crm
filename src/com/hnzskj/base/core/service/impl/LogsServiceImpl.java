/*
 * @项目名称: crm
 * @文件名称: LogsServiceImpl.java
 * @日期: 2015-8-11 下午06:40:17  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Logs;
import com.hnzskj.base.core.dao.ILogsDao;
import com.hnzskj.base.core.service.ILogsService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：LogsServiceImpl.java   <br/>
 * 类描述：操作日志service层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-11 下午06:40:17   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-11 下午06:40:17   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class LogsServiceImpl implements ILogsService {

	/**
	 * dao注入
	 */
	@Autowired
	private ILogsDao logsDao = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.ILogsService#addLogs(com.hnzskj.mainFrame.core.bean.Logs)
	 */
	public boolean addLogs(Logs logs) {
		int result = logsDao.addLogs(logs);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.ILogsService#delLogs(java.lang.String)
	 */
	public boolean delLogs(String logUuid) {
		if(null!=logUuid&&!"".equals(logUuid)){
			if(logUuid.indexOf(",")>-1){
				logUuid = "'"+logUuid.replaceAll(",", "','")+"'";
			}else{
				logUuid = "'"+logUuid+"'";
			}
			int result = logsDao.delLogs(logUuid);
			return result>0?true:false;
		}
		return true;
		
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.ILogsService#getLogs(java.lang.String)
	 */
	public Logs getLogs(String logUuid) {
		Logs logs = logsDao.getLogs(logUuid);
		return logs;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.ILogsService#getLogsList(com.hnzskj.common.bean.PageBean, com.hnzskj.mainFrame.core.bean.Logs)
	 */
	public PageBean<Logs> getLogsList(PageBean<Logs> pageBean, Logs logs) {
		String fields = " LOG_UUID AS LOGUUID,LOG_TYPE AS LOGTYPE,LOG_TITLE AS LOGTITLE,LOG_CONTENT AS LOGCONTENT,LOG_STATE AS LOGSTATE,LOG_USER_UUID AS LOGUSERUUID,LOG_USER_NAME AS LOGUSERNAME,CREATE_TIME AS CREATETIME,LOG_IP AS LOGIP ";
		StringBuffer sqlCondition = new StringBuffer(" WHERE 1=1 ");
		if(null!=logs){
			//日志类别
			if(null!=logs.getLogType()&&logs.getLogType()!=0){
				sqlCondition.append(" AND LOG_TYPE = '").append(logs.getLogType()).append("' ");
			}
			//日志操作状态
			if(null!=logs.getLogState()&&logs.getLogState()!=0){
				sqlCondition.append(" AND LOG_STATE = '").append(logs.getLogState()).append("' ");
			}
			//日志操作人名称
			if(null!=logs.getLogUserName()&&!"".equals(logs.getLogUserName())){
				sqlCondition.append(" AND LOG_USER_NAME LIKE '%").append(logs.getLogUserName()).append("%'");
			}
			//查询开始时间
			if(null!=logs.getStartTime()&&!"".equals(logs.getStartTime())){
				sqlCondition.append(" AND CREATE_TIME >= '").append(logs.getStartTime()).append("'");
			}
			//查询结束时间
			if(null!=logs.getEndTime()&&!"".equals(logs.getEndTime())){
				sqlCondition.append(" AND CREATE_TIME <= '").append(logs.getEndTime()).append("'");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("CREATE_TIME","DESC");
		pageBean = logsDao.getLogsList(pageBean, fields, sqlCondition.toString(), null, orderby, " APP_SYSTEM_LOGS ", " LOG_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.ILogsService#updLogs(com.hnzskj.mainFrame.core.bean.Logs)
	 */
	public boolean updLogs(Logs logs) {
		int result = logsDao.updLogs(logs);
		return result>0?true:false;
	}

}
