/*
 * @项目名称: crm
 * @文件名称: ResignedMonth.java
 * @日期: 2015-12-6 下午01:22:18  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.resigned.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ResignedMonth.java   <br/>
 * 类描述： 辞职后工资发放月份实体对象  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-6 下午01:22:18   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-6 下午01:22:18   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class ResignedMonth {

	/**
	 * 主键Uuid
	 */
	private String monthUuid;
	
	/**
	 * 关联用户uuid
	 */
	private String userUuid;
	
	/**
	 * 月份
	 */
	private String monthNum;
	
	/**
	 * 结算状态
	 */
	private String monthState;
	
	/**
	 * 结算状态
	 */
	private String monthStateText;
	
	
	/**
	 * 完结时间
	 */
	private String clearTime;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 
	 */
	private boolean checked =true;
	
	

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	

	/**
	 * 主键Uuid
	 * @return the monthUuid
	 */
	public String getMonthUuid() {
		return monthUuid;
	}

	/**
	 * 主键Uuid
	 * @param monthUuid the monthUuid to set
	 */
	public void setMonthUuid(String monthUuid) {
		this.monthUuid = monthUuid;
	}

	/**
	 * 关联用户uuid
	 * @return the userUuid
	 */
	public String getUserUuid() {
		return userUuid;
	}

	/**
	 * 关联用户uuid
	 * @param userUuid the userUuid to set
	 */
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	/**
	 * 月份
	 * @return the monthNum
	 */
	public String getMonthNum() {
		return monthNum;
	}

	/**
	 * 月份
	 * @param monthNum the monthNum to set
	 */
	public void setMonthNum(String monthNum) {
		this.monthNum = monthNum;
	}

	/**
	 * 结算状态
	 * @return the monthState
	 */
	public String getMonthState() {
		return monthState;
	}

	/**
	 * 结算状态
	 * @param monthState the monthState to set
	 */
	public void setMonthState(String monthState) {
		this.monthState = monthState;
	}

	/**
	 * 完结时间
	 * @return the clearTime
	 */
	public String getClearTime() {
		return clearTime;
	}

	/**
	 * 完结时间
	 * @param clearTime the clearTime to set
	 */
	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
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
	 * 结算状态
	 * @return the monthStateText
	 */
	public String getMonthStateText() {
		return monthStateText;
	}

	/**
	 * 结算状态
	 * @param monthStateText the monthStateText to set
	 */
	public void setMonthStateText(String monthStateText) {
		this.monthStateText = monthStateText;
	}
	
	
	
}
