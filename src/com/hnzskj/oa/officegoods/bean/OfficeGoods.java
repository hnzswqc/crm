/*
 * @项目名称: crm
 * @文件名称: OfficeGoods.java
 * @日期: 2015-11-27 下午04:57:11  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.officegoods.bean;

import java.io.InputStream;

/**    
 * 项目名称：crm   <br/>
 * 类名称：OfficeGoods.java   <br/>
 * 类描述：办公物品发放实体对象   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-27 下午04:57:11   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-27 下午04:57:11   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class OfficeGoods {

	/**
	 * 主键uuid
	 */
	private String ogsUuid;
	
	/**
	 * 员工工号
	 */
	private String ogsUserNumber;
	
	/**
	 * 员工姓名
	 */
	private String ogsUserName;
	
	/**
	 * 员工uuid
	 */
	private String ogsUserUuid;
	
	/**
	 * 领取时间
	 */
	private String ogsDate;
	
	/**
	 * 领取物品类别
	 */
	private String ogsType;
	
	/**
	 * 领取物品名称
	 */
	private String ogsTypeText;
	
	/**
	 * 领取数量
	 */
	private Integer ogsNumber;
	
	/**
	 * 当前状态
	 */
	private String ogsState;
	
	/**
	 * 当前状态
	 */
	private String ogsStateText;
	
	/**
	 * 归还时间
	 */
	private String ogsBackDate;
	
	/**
	 * 领取说明信息
	 */
	private String ogsNote;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 创建人员uuid
	 */
	private String createUserUuid;
	
	/**
	 * 创建人名称
	 */
	private String createUserName;
	
	/**
	 * 员工编号
	 */
	private String createUserNumber;
	
	/**
	 * 附件名称
	 */
	private String ogsFileName;
	
	/**
	 * 附件类别
	 */
	private String ogsFileType;
	
	/**
	 * 附件大小
	 */
	private Long ogsFileSize;
	
	/**
	 * 附件内容
	 */
	private byte[]ogsFileContent;
	
	/**
	 * 文件流
	 */
	private InputStream fileContent;
	

	/**
	 * 主键uuid
	 * @return the ogsUuid
	 */
	public String getOgsUuid() {
		return ogsUuid;
	}

	/**
	 * 主键uuid
	 * @param ogsUuid the ogsUuid to set
	 */
	public void setOgsUuid(String ogsUuid) {
		this.ogsUuid = ogsUuid;
	}

	/**
	 * 员工工号
	 * @return the ogsUserNumber
	 */
	public String getOgsUserNumber() {
		return ogsUserNumber;
	}

	/**
	 * 员工工号
	 * @param ogsUserNumber the ogsUserNumber to set
	 */
	public void setOgsUserNumber(String ogsUserNumber) {
		this.ogsUserNumber = ogsUserNumber;
	}

	/**
	 * 员工姓名
	 * @return the ogsUserName
	 */
	public String getOgsUserName() {
		return ogsUserName;
	}

	/**
	 * 员工姓名
	 * @param ogsUserName the ogsUserName to set
	 */
	public void setOgsUserName(String ogsUserName) {
		this.ogsUserName = ogsUserName;
	}

	/**
	 * 员工uuid
	 * @return the ogsUserUuid
	 */
	public String getOgsUserUuid() {
		return ogsUserUuid;
	}

	/**
	 * 员工uuid
	 * @param ogsUserUuid the ogsUserUuid to set
	 */
	public void setOgsUserUuid(String ogsUserUuid) {
		this.ogsUserUuid = ogsUserUuid;
	}

	/**
	 * 领取时间
	 * @return the ogsDate
	 */
	public String getOgsDate() {
		return ogsDate;
	}

	/**
	 * 领取时间
	 * @param ogsDate the ogsDate to set
	 */
	public void setOgsDate(String ogsDate) {
		this.ogsDate = ogsDate;
	}

	/**
	 * 领取物品类别
	 * @return the ogsType
	 */
	public String getOgsType() {
		return ogsType;
	}

	/**
	 * 领取物品类别
	 * @param ogsType the ogsType to set
	 */
	public void setOgsType(String ogsType) {
		this.ogsType = ogsType;
	}

	/**
	 * 领取物品名称
	 * @return the ogsTypeText
	 */
	public String getOgsTypeText() {
		return ogsTypeText;
	}

	/**
	 * 领取物品名称
	 * @param ogsTypeText the ogsTypeText to set
	 */
	public void setOgsTypeText(String ogsTypeText) {
		this.ogsTypeText = ogsTypeText;
	}

	/**
	 * 领取数量
	 * @return the ogsNumber
	 */
	public Integer getOgsNumber() {
		return ogsNumber;
	}

	/**
	 * 领取数量
	 * @param ogsNumber the ogsNumber to set
	 */
	public void setOgsNumber(Integer ogsNumber) {
		this.ogsNumber = ogsNumber;
	}

	/**
	 * 当前状态
	 * @return the ogsState
	 */
	public String getOgsState() {
		return ogsState;
	}

	/**
	 * @param ogsState the ogsState to set
	 */
	public void setOgsState(String ogsState) {
		this.ogsState = ogsState;
	}

	/**
	 * @return the ogsBackDate
	 */
	public String getOgsBackDate() {
		return ogsBackDate;
	}

	/**
	 * @param ogsBackDate the ogsBackDate to set
	 */
	public void setOgsBackDate(String ogsBackDate) {
		this.ogsBackDate = ogsBackDate;
	}

	/**
	 * @return the ogsNote
	 */
	public String getOgsNote() {
		return ogsNote;
	}

	/**
	 * 当前状态
	 * @param ogsNote the ogsNote to set
	 */
	public void setOgsNote(String ogsNote) {
		this.ogsNote = ogsNote;
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
	 * 创建人名称
	 * @return the createUserName
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * 创建人名称
	 * @param createUserName the createUserName to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * 员工编号
	 * @return the createUserNumber
	 */
	public String getCreateUserNumber() {
		return createUserNumber;
	}

	/**
	 * 员工编号
	 * @param createUserNumber the createUserNumber to set
	 */
	public void setCreateUserNumber(String createUserNumber) {
		this.createUserNumber = createUserNumber;
	}

	/**
	 * 附件名称
	 * @return the ogsFileName
	 */
	public String getOgsFileName() {
		return ogsFileName;
	}

	/**
	 * 附件名称
	 * @param ogsFileName the ogsFileName to set
	 */
	public void setOgsFileName(String ogsFileName) {
		this.ogsFileName = ogsFileName;
	}

	/**
	 * 附件类别
	 * @return the ogsFileType
	 */
	public String getOgsFileType() {
		return ogsFileType;
	}

	/**
	 * 附件类别
	 * @param ogsFileType the ogsFileType to set
	 */
	public void setOgsFileType(String ogsFileType) {
		this.ogsFileType = ogsFileType;
	}

	/**
	 * 附件大小
	 * @return the ogsFileSize
	 */
	public Long getOgsFileSize() {
		return ogsFileSize;
	}

	/**
	 * 附件大小
	 * @param ogsFileSize the ogsFileSize to set
	 */
	public void setOgsFileSize(Long ogsFileSize) {
		this.ogsFileSize = ogsFileSize;
	}

	/**
	 * 附件内容
	 * @return the ogsFileContent
	 */
	public byte[] getOgsFileContent() {
		return ogsFileContent;
	}

	/**
	 * 附件内容
	 * @param ogsFileContent the ogsFileContent to set
	 */
	public void setOgsFileContent(byte[] ogsFileContent) {
		this.ogsFileContent = ogsFileContent;
	}

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
	 * 当前状态
	 * @return the ogsStateText
	 */
	public String getOgsStateText() {
		return ogsStateText;
	}

	/**
	 * 当前状态
	 * @param ogsStateText the ogsStateText to set
	 */
	public void setOgsStateText(String ogsStateText) {
		this.ogsStateText = ogsStateText;
	}
	
	
	
	
}
