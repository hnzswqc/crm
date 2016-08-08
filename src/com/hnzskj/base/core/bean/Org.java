/*
 * @项目名称: crm
 * @文件名称: Org.java
 * @日期: 2015-8-24 上午08:54:48  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Org.java   <br/>
 * 类描述：组织机构实体对象   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-24 上午08:54:48   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-24 上午08:54:48   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Org {

	/**
	 * 组织机构uuid
	 */
	private String orgUuid;
	
	/**
	 * 组织机构名称
	 */
	private String orgName;
	
	/**
	 * 组织机构编码
	 */
	private String orgCode;
	
	/**
	 * 图标 
	 */
	private String orgIcon;
	
	/**
	 * 组织机构级别uuid
	 */
	private String orgGradeUuid;
	
	/**
	 * 组织机构级别名称
	 */
	private String orgGradeText;
	
	/**
	 * 组织机构名称
	 */
	private String orgGradeName;
	
	/**
	 * 父级uuid
	 */
	private String orgParentUuid;
	
	/**
	 * 父级名称
	 */
	private String orgParentName;
	
	/**
	 * 当前组织状态
	 */
	private String orgState;
	
	/**
	 * 当前组织状态
	 */
	private String orgStateText;
	
	/**
	 * 组织机构排序
	 */
	private Integer orgOrderBy;
	
	/**
	 * 组织机构备注
	 */
	private String orgNote;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 子级数量
	 */
	private Integer child;

	/**
	 * 组织人员Uuid
	 */
	private String userUuid;
	
	/**
	 * 编号开始编码
	 */
	private String startNumber;
	
	/**
	 * 编号结束编码
	 */
	private String endNumber;
	
	/**
	 * 组织机构uuid
	 * @return the orgUuid
	 */
	public String getOrgUuid() {
		return orgUuid;
	}

	/**
	 * 组织机构uuid
	 * @param orgUuid the orgUuid to set
	 */
	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}

	/**
	 * 组织机构名称
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 组织机构名称
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 组织机构编码
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * 组织机构编码
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 组织机构级别uuid
	 * @return the orgGradeUuid
	 */
	public String getOrgGradeUuid() {
		return orgGradeUuid;
	}

	/**
	 * 组织机构级别uuid
	 * @param orgGradeUuid the orgGradeUuid to set
	 */
	public void setOrgGradeUuid(String orgGradeUuid) {
		this.orgGradeUuid = orgGradeUuid;
	}

	/**
	 * 组织机构名称
	 * @return the orgGradeName
	 */
	public String getOrgGradeName() {
		return orgGradeName;
	}

	/**
	 * 组织机构名称
	 * @param orgGradeName the orgGradeName to set
	 */
	public void setOrgGradeName(String orgGradeName) {
		this.orgGradeName = orgGradeName;
	}
	
	

	/**
	 * 父级uuid
	 * @return the orgParentUuid
	 */
	public String getOrgParentUuid() {
		return orgParentUuid;
	}

	/**
	 * 父级uuid
	 * @param orgParentUuid the orgParentUuid to set
	 */
	public void setOrgParentUuid(String orgParentUuid) {
		this.orgParentUuid = orgParentUuid;
	}

	/**
	 * 父级名称
	 * @return the orgParentName
	 */
	public String getOrgParentName() {
		return orgParentName;
	}

	/**
	 * 父级名称
	 * @param orgParentName the orgParentName to set
	 */
	public void setOrgParentName(String orgParentName) {
		this.orgParentName = orgParentName;
	}

	/**
	 * 当前组织状态
	 * @return the orgState
	 */
	public String getOrgState() {
		return orgState;
	}

	/**
	 * 当前组织状态
	 * @param orgState the orgState to set
	 */
	public void setOrgState(String orgState) {
		this.orgState = orgState;
	}

	/**
	 * 组织机构排序
	 * @return the orgOrderBy
	 */
	public Integer getOrgOrderBy() {
		return orgOrderBy;
	}

	/**
	 * 组织机构排序
	 * @param orgOrderBy the orgOrderBy to set
	 */
	public void setOrgOrderBy(Integer orgOrderBy) {
		this.orgOrderBy = orgOrderBy;
	}

	/**
	 * 组织机构备注
	 * @return the orgNote
	 */
	public String getOrgNote() {
		return orgNote;
	}

	/**
	 * 组织机构备注
	 * @param orgNote the orgNote to set
	 */
	public void setOrgNote(String orgNote) {
		this.orgNote = orgNote;
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
	 * 图标 
	 * @return the orgIcon
	 */
	public String getOrgIcon() {
		return orgIcon;
	}

	/**
	 * 图标 
	 * @param orgIcon the orgIcon to set
	 */
	public void setOrgIcon(String orgIcon) {
		this.orgIcon = orgIcon;
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
	 * 组织机构级别名称
	 * @return the orgGradeText
	 */
	public String getOrgGradeText() {
		return orgGradeText;
	}

	/**
	 * 组织机构级别名称
	 * @param orgGradeText the orgGradeText to set
	 */
	public void setOrgGradeText(String orgGradeText) {
		this.orgGradeText = orgGradeText;
	}

	/**
	 * 当前组织状态
	 * @return the orgStateText
	 */
	public String getOrgStateText() {
		return orgStateText;
	}

	/**
	 * 当前组织状态
	 * @param orgStateText the orgStateText to set
	 */
	public void setOrgStateText(String orgStateText) {
		this.orgStateText = orgStateText;
	}

	/**
	 * 组织人员Uuid
	 * @return the userUuid
	 */
	public String getUserUuid() {
		return userUuid;
	}

	/**
	 * 组织人员Uuid
	 * @param userUuid the userUuid to set
	 */
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	
	/**
	 * 编号开始编码
	 * @return the startNumber
	 */
	public String getStartNumber() {
		return startNumber;
	}

	/**
	 * 编号开始编码
	 * @param startNumber the startNumber to set
	 */
	public void setStartNumber(String startNumber) {
		this.startNumber = startNumber;
	}

	/**
	 * 编号结束编码
	 * @return the endNumber
	 */
	public String getEndNumber() {
		return endNumber;
	}

	/**
	 * 编号结束编码
	 * @param endNumber the endNumber to set
	 */
	public void setEndNumber(String endNumber) {
		this.endNumber = endNumber;
	}
	
	
}
