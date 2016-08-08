/*
 * @项目名称: crm
 * @文件名称: WorkCheck.java
 * @日期: 2015-12-14 下午03:36:59  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.workcheck.bean;

import java.util.ArrayList;
import java.util.List;

import com.hnzskj.base.core.bean.User;

/**    
 * 项目名称：crm   <br/>
 * 类名称：WorkCheck.java   <br/>
 * 类描述： 员工考勤实体对象  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-14 下午03:36:59   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-14 下午03:36:59   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class WorkCheck extends User {

	/**
	 * 主键uuid
	 */
	private String uuid;
	
	/**
	 * 考勤年度
	 */
	private String wcYear;
	
	/**
	 * 考勤月份
	 */
	private String wcMonth;
	
	/**
	 * 考勤月份开始时间
	 */
	private String wcStartDate;
	
	/**
	 * 考勤月份结束时间
	 */
	private String wcEndDate;
	
	/**
	 * 满勤天数
	 */
	private String wcAllDay;
	
	/**
	 * 考勤天数
	 */
	private String wcCheckDay;
	
	/**
	 * 加班天数
	 */
	private String wcAddDay;
	
	/**
	 * 加班小时
	 */
	private String wcAddHour;
	
	/**
	 * 备注信息
	 */
	private String wcNote;

	/**
	 * 批量添加数据集合信息
	 */
	private List<WorkCheck> list = new ArrayList<WorkCheck>();

	/**
	 * 主键uuid
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 主键uuid
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 考勤年度
	 * @return the wcYear
	 */
	public String getWcYear() {
		return wcYear;
	}

	/**
	 * 考勤年度
	 * @param wcYear the wcYear to set
	 */
	public void setWcYear(String wcYear) {
		this.wcYear = wcYear;
	}

	/**
	 * 考勤月份
	 * @return the wcMonth
	 */
	public String getWcMonth() {
		return wcMonth;
	}

	/**
	 * 考勤月份
	 * @param wcMonth the wcMonth to set
	 */
	public void setWcMonth(String wcMonth) {
		this.wcMonth = wcMonth;
	}

	/**
	 * 考勤月份开始时间
	 * @return the wcStartDate
	 */
	public String getWcStartDate() {
		return wcStartDate;
	}

	/**
	 * 考勤月份开始时间
	 * @param wcStartDate the wcStartDate to set
	 */
	public void setWcStartDate(String wcStartDate) {
		this.wcStartDate = wcStartDate;
	}

	/**
	 * 考勤月份结束时间
	 * @return the wcEndDate
	 */
	public String getWcEndDate() {
		return wcEndDate;
	}

	/**
	 * 考勤月份结束时间
	 * @param wcEndDate the wcEndDate to set
	 */
	public void setWcEndDate(String wcEndDate) {
		this.wcEndDate = wcEndDate;
	}

	/**
	 * 满勤天数
	 * @return the wcAllDay
	 */
	public String getWcAllDay() {
		return wcAllDay;
	}

	/**
	 * 满勤天数
	 * @param wcAllDay the wcAllDay to set
	 */
	public void setWcAllDay(String wcAllDay) {
		this.wcAllDay = wcAllDay;
	}

	/**
	 * 考勤天数
	 * @return the wcCheckDay
	 */
	public String getWcCheckDay() {
		return wcCheckDay;
	}

	/**
	 * 考勤天数
	 * @param wcCheckDay the wcCheckDay to set
	 */
	public void setWcCheckDay(String wcCheckDay) {
		this.wcCheckDay = wcCheckDay;
	}

	/**
	 * 加班时间
	 * @return the wcAddDay
	 */
	public String getWcAddDay() {
		return wcAddDay;
	}

	/**
	 * 加班时间
	 * @param wcAddDay the wcAddDay to set
	 */
	public void setWcAddDay(String wcAddDay) {
		this.wcAddDay = wcAddDay;
	}

	/**
	 * 备注信息
	 * @return the wcNote
	 */
	public String getWcNote() {
		return wcNote;
	}

	/**
	 * 备注信息
	 * @param wcNote the wcNote to set
	 */
	public void setWcNote(String wcNote) {
		this.wcNote = wcNote;
	}

	/**
	 * 批量添加数据集合信息
	 * @return the list
	 */
	public List<WorkCheck> getList() {
		return list;
	}

	/**
	 * 批量添加数据集合信息
	 * @param list the list to set
	 */
	public void setList(List<WorkCheck> list) {
		this.list = list;
	}

	/**
	 * 加班小时
	 * @return the wcAddHour
	 */
	public String getWcAddHour() {
		return wcAddHour;
	}

	/**
	 * 加班小时
	 * @param wcAddHour the wcAddHour to set
	 */
	public void setWcAddHour(String wcAddHour) {
		this.wcAddHour = wcAddHour;
	}
}
