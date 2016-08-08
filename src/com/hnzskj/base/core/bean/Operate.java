/*
 * @项目名称: crm
 * @文件名称: Operate.java
 * @日期: 2015-8-28 下午03:38:35  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Operate.java   <br/>
 * 类描述：操作功能实体对象   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 下午03:38:35   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 下午03:38:35   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Operate {

	/**
	 * 操作uuid
	 */
	private String operateUuid;
	
	/**
	 * 唯一标识
	 */
	private String operateKey;
	
	/**
	 * 操作名称
	 */
	private String operateName;
	
	/**
	 * 图标
	 */
	private String operateIcon;
	
	/**
	 * 说明信息
	 */
	private String operateNote;
	
	/**
	 * 排序号
	 */
	private Integer operateOrderBy;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 所属功能连接uuid
	 */
	private String powerUuid;
	
	/**
	 * 用户信息注入
	 */
	private User user = null;

	/**
	 * 操作uuid
	 * @return the operateUuid
	 */
	public String getOperateUuid() {
		return operateUuid;
	}

	/**
	 * 操作uuid
	 * @param operateUuid the operateUuid to set
	 */
	public void setOperateUuid(String operateUuid) {
		this.operateUuid = operateUuid;
	}

	/**
	 * 唯一标识
	 * @return the operateKey
	 */
	public String getOperateKey() {
		return operateKey;
	}

	/**
	 * 唯一标识
	 * @param operateKey the operateKey to set
	 */
	public void setOperateKey(String operateKey) {
		this.operateKey = operateKey;
	}

	/**
	 * 操作名称
	 * @return the operateName
	 */
	public String getOperateName() {
		return operateName;
	}

	/**
	 * 操作名称
	 * @param operateName the operateName to set
	 */
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	/**
	 * 图标
	 * @return the operateIcon
	 */
	public String getOperateIcon() {
		return operateIcon;
	}

	/**
	 * 图标
	 * @param operateIcon the operateIcon to set
	 */
	public void setOperateIcon(String operateIcon) {
		this.operateIcon = operateIcon;
	}

	/**
	 * 说明信息
	 * @return the operateNote
	 */
	public String getOperateNote() {
		return operateNote;
	}

	/**
	 * 说明信息
	 * @param operateNote the operateNote to set
	 */
	public void setOperateNote(String operateNote) {
		this.operateNote = operateNote;
	}

	/**
	 * 排序号
	 * @return the operateOrderBy
	 */
	public Integer getOperateOrderBy() {
		return operateOrderBy;
	}

	/**
	 * 排序号
	 * @param operateOrderBy the operateOrderBy to set
	 */
	public void setOperateOrderBy(Integer operateOrderBy) {
		this.operateOrderBy = operateOrderBy;
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
	 * 所属功能连接uuid
	 * @return the powerUuid
	 */
	public String getPowerUuid() {
		return powerUuid;
	}

	/**
	 * 所属功能连接uuid
	 * @param powerUuid the powerUuid to set
	 */
	public void setPowerUuid(String powerUuid) {
		this.powerUuid = powerUuid;
	}

	/**
	 * 用户信息注入
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 用户信息注入
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
