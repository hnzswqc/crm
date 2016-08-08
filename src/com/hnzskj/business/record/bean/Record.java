/*
 * @项目名称: crm
 * @文件名称: Record.java
 * @日期: 2015-11-16 上午10:15:17  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.record.bean;

import java.io.InputStream;


/**    
 * 项目名称：crm   <br/>
 * 类名称：Record.java   <br/>
 * 类描述：员工档案记录实体对象   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-16 上午10:15:17   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-16 上午10:15:17   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Record {

	/**
	 * 档案记录
	 */
	private String recordUuid;
	
	/**
	 * 记录时间
	 */
	private String recordDate;
	
	/**
	 * 查询开始时间
	 */
	private String startDate;
	
	/**
	 * 查询结束时间
	 */
	private String endDate;
	
	/**
	 * 员工uuid
	 */
	private String recordUserUuid;
	
	/**
	 * 员工编号
	 */
	private String recordUserNumber;
	
	/**
	 * 员工名称
	 */
	private String recordUserName;
	
	/**
	 * 员工性别
	 */
	private String recordUserGender;
	
	/**
	 * 员工性别名称
	 */
	private String recordUserGenderText;
	
	/**
	 * 档案类别
	 */
	private String recordType;
	
	/**
	 * 档案类别名称
	 */
	private String recordTypeText;
	
	/**
	 * 档案附件名称
	 */
	private String recordAttName;
	
	/**
	 * 档案附件类别
	 */
	private String recordAttType;
	
	/**
	 * 附件名称
	 */
	private String recordAttFileName;
	
	/**
	 * 档案附件名称
	 */
	private byte[] recordAttContent;
	
	/**
	 * 文件流
	 */
	private InputStream fileContent;
	
	
	/**
	 * 档案附件大小
	 */
	private Long recordAttLength;
	
	/**
	 * 档案备注说明
	 */
	private String recordNote;
	
	/**
	 * 删除标记。0未删除1删除
	 */
	private Integer deleteType;
	
	/**
	 * 数据录入时间
	 */
	private String createTime;
	
	/**
	 * 创建人员uuid
	 */
	private String createUserUuid;
	
	/**
	 * 创建人员名称
	 */
	private String createUserName;
	
	/**
	 * 所属部门
	 */
	private String orgUuid;
	

	/**
	 * 文件流
	 * @return the fileContent
	 */
	public InputStream getFileContent() {
		return fileContent;
	}

	/**
	 * 文件流
	 * @param fileContent the fileContent to set
	 */
	public void setFileContent(InputStream fileContent) {
		this.fileContent = fileContent;
	}

	/**
	 * 档案记录
	 * @return the recordUuid
	 */
	public String getRecordUuid() {
		return recordUuid;
	}

	/**
	 * 档案记录
	 * @param recordUuid the recordUuid to set
	 */
	public void setRecordUuid(String recordUuid) {
		this.recordUuid = recordUuid;
	}

	/**
	 * 记录时间
	 * @return the recordDate
	 */
	public String getRecordDate() {
		return recordDate;
	}

	/**
	 * 记录时间
	 * @param recordDate the recordDate to set
	 */
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	/**
	 * 员工uuid
	 * @return the recordUserUuid
	 */
	public String getRecordUserUuid() {
		return recordUserUuid;
	}

	/**
	 * 员工uuid
	 * @param recordUserUuid the recordUserUuid to set
	 */
	public void setRecordUserUuid(String recordUserUuid) {
		this.recordUserUuid = recordUserUuid;
	}

	/**
	 * 员工编号
	 * @return the recordUserNumber
	 */
	public String getRecordUserNumber() {
		return recordUserNumber;
	}

	/**
	 * 员工编号
	 * @param recordUserNumber the recordUserNumber to set
	 */
	public void setRecordUserNumber(String recordUserNumber) {
		this.recordUserNumber = recordUserNumber;
	}

	/**
	 * 员工名称
	 * @return the recordUserName
	 */
	public String getRecordUserName() {
		return recordUserName;
	}

	/**
	 * 员工名称
	 * @param recordUserName the recordUserName to set
	 */
	public void setRecordUserName(String recordUserName) {
		this.recordUserName = recordUserName;
	}

	/**
	 * 员工性别
	 * @return the recordUserGender
	 */
	public String getRecordUserGender() {
		return recordUserGender;
	}

	/**
	 * 员工性别
	 * @param recordUserGender the recordUserGender to set
	 */
	public void setRecordUserGender(String recordUserGender) {
		this.recordUserGender = recordUserGender;
	}

	/**
	 * 员工性别名称
	 * @return the recordUserGenderText
	 */
	public String getRecordUserGenderText() {
		return recordUserGenderText;
	}

	/**
	 * 员工性别名称
	 * @param recordUserGenderText the recordUserGenderText to set
	 */
	public void setRecordUserGenderText(String recordUserGenderText) {
		this.recordUserGenderText = recordUserGenderText;
	}

	/**
	 * 档案类别
	 * @return the recordType
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * 档案类别
	 * @param recordType the recordType to set
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * 档案类别名称
	 * @return the recordTypeText
	 */
	public String getRecordTypeText() {
		return recordTypeText;
	}

	/**
	 * 档案类别名称
	 * @param recordTypeText the recordTypeText to set
	 */
	public void setRecordTypeText(String recordTypeText) {
		this.recordTypeText = recordTypeText;
	}

	/**
	 * 档案附件名称
	 * @return the recordAttName
	 */
	public String getRecordAttName() {
		return recordAttName;
	}

	/**
	 * 档案附件名称
	 * @param recordAttName the recordAttName to set
	 */
	public void setRecordAttName(String recordAttName) {
		this.recordAttName = recordAttName;
	}

	/**
	 * 档案附件类别。后缀名
	 * @return the recordAttType
	 */
	public String getRecordAttType() {
		return recordAttType;
	}

	/**
	 * 档案附件类别。后缀名
	 * @param recordAttType the recordAttType to set
	 */
	public void setRecordAttType(String recordAttType) {
		this.recordAttType = recordAttType;
	}

	/**
	 * 档案附件名称
	 * @return the recordAttContent
	 */
	public byte[] getRecordAttContent() {
		return recordAttContent;
	}

	/**
	 * 档案附件名称
	 * @param recordAttContent the recordAttContent to set
	 */
	public void setRecordAttContent(byte[] recordAttContent) {
		this.recordAttContent = recordAttContent;
	}

	/**
	 * 档案附件大小
	 * @return the recordAttLength
	 */
	public Long getRecordAttLength() {
		return recordAttLength;
	}

	/**
	 * 档案附件大小
	 * @param recordAttLength the recordAttLength to set
	 */
	public void setRecordAttLength(Long recordAttLength) {
		this.recordAttLength = recordAttLength;
	}

	/**
	 * 档案备注说明
	 * @return the recordNote
	 */
	public String getRecordNote() {
		return recordNote;
	}

	/**
	 * 档案备注说明
	 * @param recordNote the recordNote to set
	 */
	public void setRecordNote(String recordNote) {
		this.recordNote = recordNote;
	}

	/**
	 * 数据录入时间
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 数据录入时间
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 创建人员uuid
	 * @return the createUserUuid
	 */
	public String getCreateUserUuid() {
		return createUserUuid;
	}

	/**
	 * 创建人员uuid
	 * @param createUserUuid the createUserUuid to set
	 */
	public void setCreateUserUuid(String createUserUuid) {
		this.createUserUuid = createUserUuid;
	}

	/**
	 * 创建人员名称
	 * @return the createUserName
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * 创建人员名称
	 * @param createUserName the createUserName to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * 删除标记。0未删除1删除
	 * @return the deleteType
	 */
	public Integer getDeleteType() {
		return deleteType;
	}

	/**
	 * 删除标记。0未删除1删除
	 * @param deleteType the deleteType to set
	 */
	public void setDeleteType(Integer deleteType) {
		this.deleteType = deleteType;
	}

	/**
	 * 查询开始时间
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 查询开始时间
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 查询开始时间
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 查询开始时间
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 附件名称
	 * @return the recordAttFileName
	 */
	public String getRecordAttFileName() {
		return recordAttFileName;
	}

	/**
	 * 附件名称
	 * @param recordAttFileName the recordAttFileName to set
	 */
	public void setRecordAttFileName(String recordAttFileName) {
		this.recordAttFileName = recordAttFileName;
	}

	/**
	 * 所属部门
	 * @return the orgUuid
	 */
	public String getOrgUuid() {
		return orgUuid;
	}

	/**
	 * 所属部门
	 * @param orgUuid the orgUuid to set
	 */
	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}
	
	
	
	
	
	
	
}
