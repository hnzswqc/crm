/*
 * @项目名称: crm
 * @文件名称: Role.java
 * @日期: 2015-8-28 上午10:22:32  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Role.java   <br/>
 * 类描述： 角色实体对象  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 上午10:22:32   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 上午10:22:32   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Role {

	/**
	 * 角色uuid
	 */
	private String roleUuid;
	
	/**
	 * 角色key
	 */
	private String roleKey;
	
	/**
	 * 角色姓名
	 */
	private String roleName;
	
	/**
	 * 角色状态
	 */
	private String roleState;
	
	/**
	 * 状态名称
	 */
	private String roleStateText;
	
	/**
	 * 排序号
	 */
	private Integer roleOrderBy;
	
	/**
	 * 说明信息
	 */
	private String roleNote;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 是否选中标识
	 */
	private Integer count;
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
	 * 角色key
	 * @return the roleKey
	 */
	public String getRoleKey() {
		return roleKey;
	}

	/**
	 * 角色key
	 * @param roleKey the roleKey to set
	 */
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	/**
	 * 角色姓名
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 角色姓名
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 角色状态
	 * @return the roleState
	 */
	public String getRoleState() {
		return roleState;
	}

	/**
	 * 角色状态
	 * @param roleState the roleState to set
	 */
	public void setRoleState(String roleState) {
		this.roleState = roleState;
	}

	/**
	 * 状态名称
	 * @return the roleStateText
	 */
	public String getRoleStateText() {
		return roleStateText;
	}

	/**
	 * 状态名称
	 * @param roleStateText the roleStateText to set
	 */
	public void setRoleStateText(String roleStateText) {
		this.roleStateText = roleStateText;
	}

	/**
	 * 排序号
	 * @return the roleOrderBy
	 */
	public Integer getRoleOrderBy() {
		return roleOrderBy;
	}

	/**
	 * 排序号
	 * @param roleOrderBy the roleOrderBy to set
	 */
	public void setRoleOrderBy(Integer roleOrderBy) {
		this.roleOrderBy = roleOrderBy;
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
	 * 说明信息
	 * @return the roleNote
	 */
	public String getRoleNote() {
		return roleNote;
	}

	/**
	 * 说明信息
	 * @param roleNote the roleNote to set
	 */
	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}

	/**
	 * 是否选中标识
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 是否选中标识
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
}
