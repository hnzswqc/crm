/*
 * @项目名称: crm
 * @文件名称: Kqsz.java
 * @日期: 2016-3-4 下午07:53:44  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.kqsz.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Kqsz.java   <br/>
 * 类描述：考勤天数基础数据设置   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2016-3-4 下午07:53:44   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2016-3-4 下午07:53:44   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Kqsz {

	/**
	 * 主键
	 */
	private String uuid;
	
	/**
	 * 考勤年度
	 */
	private String year;
	
	/**
	 * 考勤月份
	 */
	private String month;
	
	/**
	 * 满勤天数
	 */
	private String mqts;
	
	/**
	 * 考勤开始时间
	 */
	private String kqStartTime;
	
	/**
	 * 考勤结束时间
	 */
	private String kqEndTime;
	
	/**
	 * 创建日期
	 */
	private String createTime;
	
	/**
	 * 备注说明
	 */
	private String note;

	/**
	 * 主键
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 主键
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 考勤年度
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * 考勤年度
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * 考勤月份
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * 考勤月份
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * 满勤天数
	 * @return the mqts
	 */
	public String getMqts() {
		return mqts;
	}

	/**
	 * 满勤天数
	 * @param mqts the mqts to set
	 */
	public void setMqts(String mqts) {
		this.mqts = mqts;
	}

	/**
	 * 考勤开始时间
	 * @return the kqStartTime
	 */
	public String getKqStartTime() {
		return kqStartTime;
	}

	/**
	 * 考勤开始时间
	 * @param kqStartTime the kqStartTime to set
	 */
	public void setKqStartTime(String kqStartTime) {
		this.kqStartTime = kqStartTime;
	}

	/**
	 * 考勤结束时间
	 * @return the kqEndTime
	 */
	public String getKqEndTime() {
		return kqEndTime;
	}

	/**
	 * 考勤结束时间
	 * @param kqEndTime the kqEndTime to set
	 */
	public void setKqEndTime(String kqEndTime) {
		this.kqEndTime = kqEndTime;
	}

	/**
	 * 创建日期
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建日期
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 备注说明
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 备注说明
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
	
}
