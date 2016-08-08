/*
 * @项目名称: crm
 * @文件名称: Authority.java
 * @日期: 2015-8-29 上午09:30:46  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Authority.java   <br/>
 * 类描述： 权限表  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-29 上午09:30:46   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-29 上午09:30:46   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Authority {

	/**
	 * 主键uuid
	 */
	private String uuid;
	
	/**
	 * 角色uuid
	 */
	private String roleUuid;
	
	/**
	 * 外键uuid。包括模块、功能、操作、后续可能包括系统
	 */
	private String foreignKey;
	
	/**
	 * 类别。包括模块、功能、操作、后续可能包括系统
	 */
	private String type;
	
	/**
	 * 外键名称
	 */
	private String foreignKeyText;
	
	/**
	 * 类别名称
	 */
	private String typeText;
	
	/**
	 * 是否已经存在该权限
	 */
	private String checked;
	
	/**
	 * 图标
	 */
	private String icon;
	
	

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
	 * 角色uuid
	 * @return the roleUuid
	 */
	public String getRoleUuid() {
		return roleUuid;
	}

	/**
	 * 角色uuid
	 * @param roleUuid the roleUuid to set
	 */
	public void setRoleUuid(String roleUuid) {
		this.roleUuid = roleUuid;
	}

	/**
	 * 外键uuid。包括模块、功能、操作、后续可能包括系统
	 * @return the foreignKey
	 */
	public String getForeignKey() {
		return foreignKey;
	}

	/**
	 * 外键uuid。包括模块、功能、操作、后续可能包括系统
	 * @param foreignKey the foreignKey to set
	 */
	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	/**
	 * 类别。包括模块、功能、操作、后续可能包括系统
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 类别。包括模块、功能、操作、后续可能包括系统
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 外键名称
	 * @return the foreignKeyText
	 */
	public String getForeignKeyText() {
		return foreignKeyText;
	}

	/**
	 * 外键名称
	 * @param foreignKeyText the foreignKeyText to set
	 */
	public void setForeignKeyText(String foreignKeyText) {
		this.foreignKeyText = foreignKeyText;
	}

	/**
	 * 类别名称
	 * @return the typeText
	 */
	public String getTypeText() {
		return typeText;
	}

	/**
	 * 类别名称
	 * @param typeText the typeText to set
	 */
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

	/**
	 * 是否已经存在该权限
	 * @return the checked
	 */
	public String getChecked() {
		return checked;
	}

	/**
	 * 是否已经存在该权限
	 * @param checked the checked to set
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}

	/**
	 * 图标
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 图标
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
}
