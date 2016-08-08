/*
 * @项目名称: crm
 * @文件名称: Attachment.java
 * @日期: 2015-12-11 下午08:39:02  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

import java.io.InputStream;

import org.codehaus.jackson.JsonProcessingException;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Attachment.java   <br/>
 * 类描述：  附件实体对象 <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-11 下午08:39:02   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-11 下午08:39:02   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Attachment {
	
	/**
	 * 附件Uuid
	 */
	private String attUuid;
	
	/**
	 * 对应外键
	 */
	private String foreignUUid;
	
	/**
	 * 附件大类
	 */
	private String attType;
	
	/**
	 * 附件大类类别
	 */
	private String attTypeText;
	
	/**
	 * 文件类别
	 */
	private String fileType;
	
	/**
	 * 文件名称
	 */
	private String fileName;
	
	/**
	 * 文件内容
	 */
	private byte[] fileContent;
	
	/**
	 * 文件流
	 */
	private InputStream inputStream;
	
	/**
	 * 创建着
	 */
	private String createUserUuid;
	
	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 附件Uuid
	 * @return the attUuid
	 */
	public String getAttUuid() {
		return attUuid;
	}

	/**
	 * 附件Uuid
	 * @param attUuid the attUuid to set
	 */
	public void setAttUuid(String attUuid) {
		this.attUuid = attUuid;
	}

	/**
	 * 对应外键
	 * @return the foreignUUid
	 */
	public String getForeignUUid() {
		return foreignUUid;
	}

	/**
	 * 对应外键
	 * @param foreignUUid the foreignUUid to set
	 */
	public void setForeignUUid(String foreignUUid) {
		this.foreignUUid = foreignUUid;
	}

	/**
	 * 附件大类类别
	 * @return the attType
	 */
	public String getAttType() {
		return attType;
	}

	/**
	 * 附件大类
	 * @param attType the attType to set
	 */
	public void setAttType(String attType) {
		this.attType = attType;
	}

	/**
	 * 附件大类类别
	 * @return the attTypeText
	 */
	public String getAttTypeText() {
		return attTypeText;
	}

	/**
	 * 附件大类类别
	 * @param attTypeText the attTypeText to set
	 */
	public void setAttTypeText(String attTypeText) {
		this.attTypeText = attTypeText;
	}

	/**
	 * 文件类别
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 文件类别
	 * @return the fileContent
	 */
	public byte[] getFileContent() {
		return fileContent;
	}

	/**
	 * @param fileContent the fileContent to set
	 */
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 创建人uuid
	 * @return the createUserUuid
	 */
	public String getCreateUserUuid() {
		return createUserUuid;
	}

	/**
	 * 创建人uuid
	 * @param createUserUuid the createUserUuid to set
	 */
	public void setCreateUserUuid(String createUserUuid) {
		this.createUserUuid = createUserUuid;
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
	
	
	

}
