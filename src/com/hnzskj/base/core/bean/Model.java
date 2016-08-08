/*
 * @项目名称: crm
 * @文件名称: Model.java
 * @日期: 2015-8-10 下午02:46:35  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Model.java   <br/>
 * 类描述： 系统菜单模块实体对象  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-10 下午02:46:35   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-10 下午02:46:35   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Model {
	
	/**
	 * 模块uuid
	 */
	private String modelUuid;
	
	/**
	 * 模块标题
	 */
	private String modelTitle;
	
	/**
	 * 模块图标
	 */
	private String modelIcon;
	
	/**
	 * 模块状态。默认启用。activate 启用。suspend挂起
	 */
	private String modelState;
	
	/**
	 * 状态显示值
	 */
	private String modelStateText;
	
	/**
	 * 备注说明
	 */
	private String modelNote;
	
	/**
	 * 所属子系统
	 */
	private String modelSubsystemUuid;
	
	/**
	 * 所属子系统名称
	 */
	private String modelSubsystemName;
	
	/**
	 * 排序号
	 */
	private Integer modelOrderby;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 子级数量
	 */
	private Integer child;
	
	/**
	 * 模块logo
	 */
	private String modelLogo;

	
	/**
	 * 模块uuid
	 * @return the modelUuid
	 */
	public String getModelUuid() {
		return modelUuid;
	}

	/**
	 * 模块uuid
	 * @param modelUuid the modelUuid to set
	 */
	public void setModelUuid(String modelUuid) {
		this.modelUuid = modelUuid;
	}

	/**
	 * 模块标题
	 * @return the modelTitle
	 */
	public String getModelTitle() {
		return modelTitle;
	}

	/**
	 * 模块标题
	 * @param modelTitle the modelTitle to set
	 */
	public void setModelTitle(String modelTitle) {
		this.modelTitle = modelTitle;
	}

	/**
	 * 模块图标
	 * @return the modelIcon
	 */
	public String getModelIcon() {
		return modelIcon;
	}

	/**
	 * 模块图标
	 * @param modelIcon the modelIcon to set
	 */
	public void setModelIcon(String modelIcon) {
		this.modelIcon = modelIcon;
	}

	/**
	 * 模块状态。默认启用。activate 启用。suspend挂起
	 * @return the modelState
	 */
	public String getModelState() {
		return modelState;
	}

	/**
	 * 模块状态。默认启用。activate 启用。suspend挂起
	 * @param modelState the modelState to set
	 */
	public void setModelState(String modelState) {
		this.modelState = modelState;
	}

	/**
	 * 备注说明
	 * @return the modelNote
	 */
	public String getModelNote() {
		return modelNote;
	}

	/**
	 * 备注说明
	 * @param modelNote the modelNote to set
	 */
	public void setModelNote(String modelNote) {
		this.modelNote = modelNote;
	}


	/**
	 * 所属子系统
	 * @return the modelSubsystemUuid
	 */
	public String getModelSubsystemUuid() {
		return modelSubsystemUuid;
	}

	/**
	 * 所属子系统
	 * @param modelSubsystemUuid the modelSubsystemUuid to set
	 */
	public void setModelSubsystemUuid(String modelSubsystemUuid) {
		this.modelSubsystemUuid = modelSubsystemUuid;
	}

	/**
	 * 所属子系统名称
	 * @return the modelSubsystemName
	 */
	public String getModelSubsystemName() {
		return modelSubsystemName;
	}

	/**
	 * 所属子系统名称
	 * @param modelSubsystemName the modelSubsystemName to set
	 */
	public void setModelSubsystemName(String modelSubsystemName) {
		this.modelSubsystemName = modelSubsystemName;
	}

	
	/**
	 * 排序号
	 * @return the modelOrderby
	 */
	public Integer getModelOrderby() {
		return modelOrderby;
	}

	/**
	 * 排序号
	 * @param modelOrderby the modelOrderby to set
	 */
	public void setModelOrderby(Integer modelOrderby) {
		this.modelOrderby = modelOrderby;
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
	 * 状态显示值
	 * @return the modelStateText
	 */
	public String getModelStateText() {
		return modelStateText;
	}

	/**
	 * 状态显示值
	 * @param modelStateText the modelStateText to set
	 */
	public void setModelStateText(String modelStateText) {
		this.modelStateText = modelStateText;
	}

	/**
	 * 子级数量
	 * @return the child
	 */
	public Integer getChild() {
		return child;
	}

	/**
	 * 子级数量
	 * @param child the child to set
	 */
	public void setChild(Integer child) {
		this.child = child;
	}
	
	/**
	 * 权限用户
	 */
	private User user = null;


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 模块logo
	 * @return the modelLogo
	 */
	public String getModelLogo() {
		return modelLogo;
	}

	/**
	 * 模块logo
	 * @param modelLogo the modelLogo to set
	 */
	public void setModelLogo(String modelLogo) {
		this.modelLogo = modelLogo;
	}
	
	
	
	
}
