/*
 * @项目名称: crm
 * @文件名称: UserAuthority.java
 * @日期: 2015-9-7 上午10:14:05  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：UserAuthority.java   <br/>
 * 类描述：授权申请实体。   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-7 上午10:14:05   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-7 上午10:14:05   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class UserAuthority {

	/**
	 * 主键uuid
	 */
	private String uuid;

	/**
	 * 授权人a。a授权b，b可以变身为a
	 */
	private String userUuid;
	
	/**
	 * 授权人a。a授权b，b可以变身为a
	 */
	private String userName;
	
	/**
	 * 被授权人b。a授权b，b可以变身为a
	 */
	private String authorityUserUuid;

	/**
	 * 被授权人b。a授权b，b可以变身为a
	 */
	private String authorityUserName;
	
	
	/**
	 * 类别。个人申请、个人授权、管理员授权
	 */
	private String type;
	
	/**
	 * 状态。待审核、审核通过
	 */
	private String state;
	
	/**
	 * 状态。待审核、审核通过。显示内容
	 */
	private String stateText;
	
	/**
	 * 备注说明
	 */
	private String note;
	
	/**
	 * 创建时间
	 */
	private String createTime;

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
	 * 授权人a。a授权b，b可以变身为a
	 * @return the userUuid
	 */
	public String getUserUuid() {
		return userUuid;
	}

	/**
	 * 授权人a。a授权b，b可以变身为a
	 * @param userUuid the userUuid to set
	 */
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	/**
	 * 被授权人b。a授权b，b可以变身为a
	 * @return the authorityUserUuid
	 */
	public String getAuthorityUserUuid() {
		return authorityUserUuid;
	}

	/**
	 * 被授权人b。a授权b，b可以变身为a
	 * @param authorityUserUuid the authorityUserUuid to set
	 */
	public void setAuthorityUserUuid(String authorityUserUuid) {
		this.authorityUserUuid = authorityUserUuid;
	}

	/**
	 * 类别。个人申请、个人授权、管理员授权
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 类别。个人申请、个人授权、管理员授权
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 状态。待审核、审核通过
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 状态。待审核、审核通过
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
	 * 状态。待审核、审核通过。显示内容
	 * @return the stateText
	 */
	public String getStateText() {
		return stateText;
	}

	/**
	 * 状态。待审核、审核通过。显示内容
	 * @param stateText the stateText to set
	 */
	public void setStateText(String stateText) {
		this.stateText = stateText;
	}

	/**
	 * 授权人a。a授权b，b可以变身为a
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 授权人a。a授权b，b可以变身为a
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 被授权人b。a授权b，b可以变身为a
	 * @return the authorityUserName
	 */
	public String getAuthorityUserName() {
		return authorityUserName;
	}

	/**
	 * 被授权人b。a授权b，b可以变身为a
	 * @param authorityUserName the authorityUserName to set
	 */
	public void setAuthorityUserName(String authorityUserName) {
		this.authorityUserName = authorityUserName;
	}
	
	
	
	
	
}
