/*
 * @项目名称: crm
 * @文件名称: Power.java
 * @日期: 2015-8-11 上午10:04:09  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Power.java   <br/>
 * 类描述：功能权限实体对象   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-11 上午10:04:09   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-11 上午10:04:09   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Power {

	/**
	 * 树形菜单id
	 */
	private String id;
	
	/**
	 * 树形菜单名称
	 */
	private String text;
	
	/**
	 * 属性菜单图标
	 */
	private String icon;
	
	/**
	 * 功能权限uuid
	 */
	private String powerUuid;
	
	/**
	 * 功能权限名称
	 */
	private String powerName;
	
	/**
	 * 图标
	 */
	private String powerIcon;
	
	/**
	 * 状态
	 */
	private String powerState;
	/**
	 * 状态值
	 */
	private String powerStateText;
	
	/**
	 * 组件外部连接
	 */
	private String powerUrl;
	
	/**
	 * 所属模型名称
	 */
	private String powerModelName;
	
	/**
	 * 所属模型uuid
	 */
	private String powerModelUuid;
	/**
	 * 所属父级uuid
	 */
	private String powerParentUuid;
	
	/**
	 * 所属父级名称
	 */
	private String powerParentName;
	
	/**
	 * 备注信息
	 */
	private String powerNote;
	
	/**
	 * 排序字段
	 */
	private Integer powerOrderby;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 子级数量 
	 */
	private Integer num;
	
	/**
	 * 功能logo
	 */
	private String powerLogo;

	/**
	 * 树形菜单id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 树形菜单id
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 树形菜单名称
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * 树形菜单名称
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 属性菜单图标
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 属性菜单图标
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 功能权限uuid
	 * @return the powerUuid
	 */
	public String getPowerUuid() {
		return powerUuid;
	}

	/**
	 * 功能权限uuid
	 * @param powerUuid the powerUuid to set
	 */
	public void setPowerUuid(String powerUuid) {
		this.powerUuid = powerUuid;
	}

	/**
	 * 功能权限名称
	 * @return the powerName
	 */
	public String getPowerName() {
		return powerName;
	}

	/**
	 * 功能权限名称
	 * @param powerName the powerName to set
	 */
	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	/**
	 * 图标
	 * @return the powerIcon
	 */
	public String getPowerIcon() {
		return powerIcon;
	}

	/**
	 * 图标
	 * @param powerIcon the powerIcon to set
	 */
	public void setPowerIcon(String powerIcon) {
		this.powerIcon = powerIcon;
	}

	/**
	 * 状态
	 * @return the powerState
	 */
	public String getPowerState() {
		return powerState;
	}

	/**
	 * 状态
	 * @param powerState the powerState to set
	 */
	public void setPowerState(String powerState) {
		this.powerState = powerState;
	}


	/**
	 * 组件外部连接
	 * @return the powerUrl
	 */
	public String getPowerUrl() {
		return powerUrl;
	}

	/**
	 * 组件外部连接
	 * @param powerUrl the powerUrl to set
	 */
	public void setPowerUrl(String powerUrl) {
		this.powerUrl = powerUrl;
	}

	/**
	 * 所属父级uuid
	 * @return the powerParentUuid
	 */
	public String getPowerParentUuid() {
		return powerParentUuid;
	}

	/**
	 * 所属父级uuid
	 * @param powerParentUuid the powerParentUuid to set
	 */
	public void setPowerParentUuid(String powerParentUuid) {
		this.powerParentUuid = powerParentUuid;
	}

	/**
	 * 所属父级名称
	 * @return the powerParentName
	 */
	public String getPowerParentName() {
		return powerParentName;
	}

	/**
	 * 所属父级名称
	 * @param powerParentName the powerParentName to set
	 */
	public void setPowerParentName(String powerParentName) {
		this.powerParentName = powerParentName;
	}

	/**
	 * 备注信息
	 * @return the powerNote
	 */
	public String getPowerNote() {
		return powerNote;
	}

	/**
	 * 备注信息
	 * @param powerNote the powerNote to set
	 */
	public void setPowerNote(String powerNote) {
		this.powerNote = powerNote;
	}
	
	

	/**
	 * 排序字段
	 * @return the powerOrderby
	 */
	public Integer getPowerOrderby() {
		return powerOrderby;
	}

	/**
	 * 排序字段
	 * @param powerOrderby the powerOrderby to set
	 */
	public void setPowerOrderby(Integer powerOrderby) {
		this.powerOrderby = powerOrderby;
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
	 * 所属模型名称
	 * @return the powerModelName
	 */
	public String getPowerModelName() {
		return powerModelName;
	}

	/**
	 * 所属模型名称
	 * @param powerModelName the powerModelName to set
	 */
	public void setPowerModelName(String powerModelName) {
		this.powerModelName = powerModelName;
	}

	/**
	 * 所属模型uuid
	 * @return the powerModelUuid
	 */
	public String getPowerModelUuid() {
		return powerModelUuid;
	}

	/**
	 * 所属模型uuid
	 * @param powerModelUuid the powerModelUuid to set
	 */
	public void setPowerModelUuid(String powerModelUuid) {
		this.powerModelUuid = powerModelUuid;
	}

	/**
	 * 子级数量 
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * 子级数量 
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * 状态值
	 * @return the powerStateText
	 */
	public String getPowerStateText() {
		return powerStateText;
	}

	/**
	 * 状态值
	 * @param powerStateText the powerStateText to set
	 */
	public void setPowerStateText(String powerStateText) {
		this.powerStateText = powerStateText;
	}

	/**
	 * 功能logo
	 * @return the powerLogo
	 */
	public String getPowerLogo() {
		return powerLogo;
	}

	/**
	 * 功能logo
	 * @param powerLogo the powerLogo to set
	 */
	public void setPowerLogo(String powerLogo) {
		this.powerLogo = powerLogo;
	}
	
	
	
}
