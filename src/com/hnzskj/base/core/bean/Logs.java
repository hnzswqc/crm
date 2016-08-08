/*
 * @项目名称: crm
 * @文件名称: Logs.java
 * @日期: 2015-8-11 下午02:51:13  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Logs.java   <br/>
 * 类描述：日志实体对象   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-11 下午02:51:13   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-11 下午02:51:13   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Logs {
	
	public Logs(){}
	
	/**
	 * @param logType
	 * @param logTitle
	 * @param logContent
	 * @param logState
	 * @param logUserUuid
	 * @param logUserName
	 * @param logIp
	 */
	public Logs( Integer logType, String logTitle,
			String logContent, Integer logState, String logUserUuid,
			String logUserName,String logIp) {
		super();
		this.logType = logType;
		this.logTitle = logTitle;
		this.logContent = logContent;
		this.logState = logState;
		this.logUserUuid = logUserUuid;
		this.logUserName = logUserName;
		this.logIp = logIp;
	}




	/**
	 * 日志主键
	 */
	private String logUuid;
	
	/**
	 * 日志类别。分为系统执行日志，人员操作日志，以及系统错误日志
	 */
	private Integer logType;
	
	/**
	 * 日志标题
	 */
	private String logTitle;
	
	/**
	 * 日志内容
	 */
	private String logContent;
	
	/**
	 * 日志执行状态
	 */
	private Integer logState;
	
	/**
	 * 日志操作人id
	 */
	private String logUserUuid;
	
	/**
	 * 日志操作人名称
	 */
	private String logUserName;
	
	/**
	 * 查询开始时间
	 */
	private String startTime;
	
	/**
	 * 查询结束时间
	 */
	private String endTime;
	
	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 客户端ip
	 */
	private String logIp;
	
	/**
	 * 日志主键
	 * @return the logUuid
	 */
	public String getLogUuid() {
		return logUuid;
	}

	/**
	 * 日志主键
	 * @param logUuid the logUuid to set
	 */
	public void setLogUuid(String logUuid) {
		this.logUuid = logUuid;
	}

	/**
	 * 日志类别。分为系统执行日志，人员操作日志，以及系统错误日志
	 * @return the logType
	 */
	public Integer getLogType() {
		return logType;
	}

	/**
	 * 日志类别。分为系统执行日志，人员操作日志，以及系统错误日志
	 * @param logType the logType to set
	 */
	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	/**
	 * 日志标题
	 * @return the logTitle
	 */
	public String getLogTitle() {
		return logTitle;
	}

	/**
	 * 日志标题
	 * @param logTitle the logTitle to set
	 */
	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	/**
	 * 日志内容
	 * @return the logContent
	 */
	public String getLogContent() {
		return logContent;
	}

	/**
	 * 日志内容
	 * @param logContent the logContent to set
	 */
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	/**
	 * 日志执行状态
	 * @return the logState
	 */
	public Integer getLogState() {
		return logState;
	}

	/**
	 * 日志执行状态
	 * @param logState the logState to set
	 */
	public void setLogState(Integer logState) {
		this.logState = logState;
	}

	/**
	 * 日志操作人id
	 * @return the logUserUuid
	 */
	public String getLogUserUuid() {
		return logUserUuid;
	}

	/**
	 * 日志操作人id
	 * @param logUserUuid the logUserUuid to set
	 */
	public void setLogUserUuid(String logUserUuid) {
		this.logUserUuid = logUserUuid;
	}

	/**
	 * 日志操作人名称
	 * @return the logUserName
	 */
	public String getLogUserName() {
		return logUserName;
	}

	/**
	 * 日志操作人名称
	 * @param logUserName the logUserName to set
	 */
	public void setLogUserName(String logUserName) {
		this.logUserName = logUserName;
	}

	/**
	 * 创建时间
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 查询开始时间
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 查询开始时间
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 查询结束时间
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 查询结束时间
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 客户端ip
	 * @return the logIp
	 */
	public String getLogIp() {
		return logIp;
	}

	/**
	 * 客户端ip
	 * @param logIp the logIp to set
	 */
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
	
	
	
}
