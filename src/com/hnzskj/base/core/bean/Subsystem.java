/*
 * @项目名称: crm
 * @文件名称: Subsytem.java
 * @日期: 2015-9-2 上午11:48:59  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Subsytem.java   <br/>
 * 类描述：子系统实体对象   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-2 上午11:48:59   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-2 上午11:48:59   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Subsystem {

	/**
	 * 子系统uuid
	 */
	private String subUuid;
	
	/**
	 * 子系统标识
	 */
	private String subKey;
	
	/**
	 * 子系统标题
	 */
	private String subTitle;
	
	/**
	 * 子系统图标
	 */
	private String subIcon;
	
	/**
	 * 子系统状态
	 */
	private String subState;
	
	/**
	 * 状态名称
	 */
	private String subStateText;
	
	/**
	 * 排序
	 */
	private Integer subOrderBy;
	
	/**
	 * 备注信息
	 */
	private String subNote;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 子模块数量
	 */
	private Integer child;
	
	/**
	 * 子系统logo
	 */
	private String subLogo;
	
	/**
	 * 权限子系统
	 */
	private User user = new User();

	/**
	 * 子系统uuid
	 * @return the subUuid
	 */
	public String getSubUuid() {
		return subUuid;
	}

	/**
	 * 子系统uuid
	 * @param subUuid the subUuid to set
	 */
	public void setSubUuid(String subUuid) {
		this.subUuid = subUuid;
	}

	/**
	 * 子系统标识
	 * @return the subKey
	 */
	public String getSubKey() {
		return subKey;
	}

	/**
	 * 子系统标识
	 * @param subKey the subKey to set
	 */
	public void setSubKey(String subKey) {
		this.subKey = subKey;
	}

	/**
	 * 子系统标题
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * 子系统标题
	 * @param subTitle the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * 子系统图标
	 * @return the subIcon
	 */
	public String getSubIcon() {
		return subIcon;
	}

	/**
	 * 子系统图标
	 * @param subIcon the subIcon to set
	 */
	public void setSubIcon(String subIcon) {
		this.subIcon = subIcon;
	}

	/**
	 * 子系统状态
	 * @return the subState
	 */
	public String getSubState() {
		return subState;
	}

	/**
	 * 子系统状态
	 * @param subState the subState to set
	 */
	public void setSubState(String subState) {
		this.subState = subState;
	}

	/**
	 * 状态名称
	 * @return the subStateText
	 */
	public String getSubStateText() {
		return subStateText;
	}

	/**
	 * 状态名称
	 * @param subStateText the subStateText to set
	 */
	public void setSubStateText(String subStateText) {
		this.subStateText = subStateText;
	}

	/**
	 * 排序
	 * @return the subOrderBy
	 */
	public Integer getSubOrderBy() {
		return subOrderBy;
	}

	/**
	 * 排序
	 * @param subOrderBy the subOrderBy to set
	 */
	public void setSubOrderBy(Integer subOrderBy) {
		this.subOrderBy = subOrderBy;
	}

	/**
	 * 备注信息
	 * @return the subNote
	 */
	public String getSubNote() {
		return subNote;
	}

	/**
	 * 备注信息
	 * @param subNote the subNote to set
	 */
	public void setSubNote(String subNote) {
		this.subNote = subNote;
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
	 * 子模块数量
	 * @return the child
	 */
	public Integer getChild() {
		return child;
	}

	/**
	 * 子模块数量
	 * @param child the child to set
	 */
	public void setChild(Integer child) {
		this.child = child;
	}

	/**
	 * 子系统logo
	 * @return the subLogo
	 */
	public String getSubLogo() {
		return subLogo;
	}

	/**
	 * 子系统logo
	 * @param subLogo the subLogo to set
	 */
	public void setSubLogo(String subLogo) {
		this.subLogo = subLogo;
	}

	/**
	 * 权限子系统
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 权限子系统
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
