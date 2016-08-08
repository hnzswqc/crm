/*
 * @项目名称: crm
 * @文件名称: Resigned.java
 * @日期: 2015-12-2 下午07:41:00  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.resigned.bean;

import java.io.InputStream;

import com.hnzskj.base.core.bean.User;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Resigned.java   <br/>
 * 类描述：辞职员工实体对象   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-2 下午07:41:00   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-2 下午07:41:00   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Resigned extends User{

	/**
	 * 辞职信息uuid
	 */
	private String resUuid;
	
	/**
	 * 申请离职日期
	 */
	private String resApplyDate;
	
	/**
	 * 批准离职日期
	 */
	private String resApproveDate;
	
	/**
	 * 离职类别key。正常离职、开除
	 */
	private String resType;
	
	/**
	 * 离职类别。正常离职、开除
	 */
	private String resTypeText;
	
	/**
	 * 工资如何发放。立即发放和正常发放。
	 */
	private String resMoneyType;
	
	/**
	 * 工资如何发放。立即发放和正常发放。
	 */
	private String resMoneyTypeText;
	
	/**
	 * 离职原因
	 */
	private String resReson;
	
	/**
	 * 工作交接情况
	 */
	private String resHandOver;
	
	/**
	 * 工具归还情况
	 */
	private String resToolReturn;
	
	/**
	 * 办公物品归还情况
	 */
	private String resOfficeGoods;
	
	/**
	 * 考勤情况
	 */
	private String resCheckWork;
	
	/**
	 * 考勤天数
	 */
	private Float resCheckWorkNum;
	
	/**
	 * 财务借款情况
	 */
	private String resFinancialLoan;
	
	/**
	 * 员工扣款项目
	 */
	private String resDeductWagesItem;
	
	/**
	 * 应扣除金额
	 */
	private Float resDeductWages;
	
	/**
	 * 实际领取金额
	 */
	private Float resRealyWages;
	
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
	 * 文件大小
	 */
	private Long fileSize;
	
	/**
	 * 文件类别
	 */
	private String fileType;
	
	/**
	 * 备注信息
	 */
	private String resNote;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 创建人员
	 */
	private String createUserUuid;
	
	/**
	 * 创建人员名称
	 */
	private String createUserName;
	
	/**
	 * 查询开始时间
	 */
	private String startDate;
	
	/**
	 * 查询结束时间
	 */
	private String endDate;
	
	/**
	 * 有工资卡
	 */
	private String userBankCardState;
	
	/**
	 * 结算状态
	 */
	private String resState;
	
	/**
	 * 工资结算状态
	 */
	private String resStateText;

	/**
	 * 查询月份
	 */
	private String monthNum;
	
	/**
	 * 年度查询
	 */
	private String yearNum;
	
	/**
	 * 工号使用状态
	 */
	private int count;
	
	/**
	 * 工号使用状态
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 工号使用状态
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 辞职信息uuid
	 * @return the resUuid
	 */
	public String getResUuid() {
		return resUuid;
	}

	/**
	 * 辞职信息uuid
	 * @param resUuid the resUuid to set
	 */
	public void setResUuid(String resUuid) {
		this.resUuid = resUuid;
	}

	/**
	 * 申请离职日期
	 * @return the resApplyDate
	 */
	public String getResApplyDate() {
		return resApplyDate;
	}

	/**
	 * 申请离职日期
	 * @param resApplyDate the resApplyDate to set
	 */
	public void setResApplyDate(String resApplyDate) {
		this.resApplyDate = resApplyDate;
	}

	/**
	 * 批准离职日期
	 * @return the resApproveDate
	 */
	public String getResApproveDate() {
		return resApproveDate;
	}

	/**
	 * 批准离职日期
	 * @param resApproveDate the resApproveDate to set
	 */
	public void setResApproveDate(String resApproveDate) {
		this.resApproveDate = resApproveDate;
	}

	/**
	 * 离职类别key。正常离职、开除
	 * @return the resType
	 */
	public String getResType() {
		return resType;
	}

	/**
	 * 离职类别key。正常离职、开除
	 * @param resType the resType to set
	 */
	public void setResType(String resType) {
		this.resType = resType;
	}

	/**
	 * 离职类别。正常离职、开除
	 * @return the resTypeText
	 */
	public String getResTypeText() {
		return resTypeText;
	}

	/**
	 * 离职类别。正常离职、开除
	 * @param resTypeText the resTypeText to set
	 */
	public void setResTypeText(String resTypeText) {
		this.resTypeText = resTypeText;
	}

	/**
	 * 工资如何发放。立即发放和正常发放。
	 * @return the resMoneyType
	 */
	public String getResMoneyType() {
		return resMoneyType;
	}

	/**
	 * 工资如何发放。立即发放和正常发放。
	 * @param resMoneyType the resMoneyType to set
	 */
	public void setResMoneyType(String resMoneyType) {
		this.resMoneyType = resMoneyType;
	}

	/**
	 * 工资如何发放。立即发放和正常发放。
	 * @return the resMoneyTypeText
	 */
	public String getResMoneyTypeText() {
		return resMoneyTypeText;
	}

	/**
	 * 工资如何发放。立即发放和正常发放。
	 * @param resMoneyTypeText the resMoneyTypeText to set
	 */
	public void setResMoneyTypeText(String resMoneyTypeText) {
		this.resMoneyTypeText = resMoneyTypeText;
	}

	/**
	 * 离职原因
	 * @return the resReson
	 */
	public String getResReson() {
		return resReson;
	}

	/**
	 * 离职原因
	 * @param resReson the resReson to set
	 */
	public void setResReson(String resReson) {
		this.resReson = resReson;
	}

	/**
	 * 工作交接情况
	 * @return the resHandOver
	 */
	public String getResHandOver() {
		return resHandOver;
	}

	/**
	 * 工作交接情况
	 * @param resHandOver the resHandOver to set
	 */
	public void setResHandOver(String resHandOver) {
		this.resHandOver = resHandOver;
	}

	/**
	 * 工具归还情况
	 * @return the resToolReturn
	 */
	public String getResToolReturn() {
		return resToolReturn;
	}

	/**
	 * 工具归还情况
	 * @param resToolReturn the resToolReturn to set
	 */
	public void setResToolReturn(String resToolReturn) {
		this.resToolReturn = resToolReturn;
	}

	/**
	 * 办公物品归还情况
	 * @return the resOfficeGoods
	 */
	public String getResOfficeGoods() {
		return resOfficeGoods;
	}

	/**
	 * 办公物品归还情况
	 * @param resOfficeGoods the resOfficeGoods to set
	 */
	public void setResOfficeGoods(String resOfficeGoods) {
		this.resOfficeGoods = resOfficeGoods;
	}

	/**
	 * 考情情况
	 * @return the resCheckWork
	 */
	public String getResCheckWork() {
		return resCheckWork;
	}

	/**
	 * 考情情况
	 * @param resCheckWork the resCheckWork to set
	 */
	public void setResCheckWork(String resCheckWork) {
		this.resCheckWork = resCheckWork;
	}

	/**
	 * 财务借款情况
	 * @return the resFinancialLoan
	 */
	public String getResFinancialLoan() {
		return resFinancialLoan;
	}

	/**
	 * 财务借款情况
	 * @param resFinancialLoan the resFinancialLoan to set
	 */
	public void setResFinancialLoan(String resFinancialLoan) {
		this.resFinancialLoan = resFinancialLoan;
	}

	/**
	 * 应扣除金额
	 * @return the resDeductWages
	 */
	public Float getResDeductWages() {
		return resDeductWages;
	}

	/**
	 * 应扣除金额
	 * @param resDeductWages the resDeductWages to set
	 */
	public void setResDeductWages(Float resDeductWages) {
		this.resDeductWages = resDeductWages;
	}

	/**
	 * 实际领取金额
	 * @return the resRealyWages
	 */
	public Float getResRealyWages() {
		return resRealyWages;
	}

	/**
	 * 实际领取金额
	 * @param resRealyWages the resRealyWages to set
	 */
	public void setResRealyWages(Float resRealyWages) {
		this.resRealyWages = resRealyWages;
	}

	/**
	 * 文件名称
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 文件名称
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 文件内容
	 * @return the fileContent
	 */
	public byte[] getFileContent() {
		return fileContent;
	}

	/**
	 * 文件内容
	 * @param fileContent the fileContent to set
	 */
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	/**
	 * 文件流
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * 文件流
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 文件大小
	 * @return the fileSize
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * 文件大小
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * 文件类别
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * 文件类别
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
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
	 * 创建人员
	 * @return the createUserUuid
	 */
	public String getCreateUserUuid() {
		return createUserUuid;
	}

	/**
	 * 创建人员
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
	 * 备注信息
	 * @return the resNote
	 */
	public String getResNote() {
		return resNote;
	}

	/**
	 * 备注信息
	 * @param resNote the resNote to set
	 */
	public void setResNote(String resNote) {
		this.resNote = resNote;
	}

	/**
	 * 员工扣款项目
	 * @return the resDeductWagesItem
	 */
	public String getResDeductWagesItem() {
		return resDeductWagesItem;
	}

	/**
	 * 员工扣款项目
	 * @param resDeductWagesItem the resDeductWagesItem to set
	 */
	public void setResDeductWagesItem(String resDeductWagesItem) {
		this.resDeductWagesItem = resDeductWagesItem;
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
	 * 查询结束时间
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 查询结束时间
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 有工资卡
	 * @return the userBankCardState
	 */
	public String getUserBankCardState() {
		return userBankCardState;
	}

	/**
	 * 有工资卡
	 * @param userBankCardState the userBankCardState to set
	 */
	public void setUserBankCardState(String userBankCardState) {
		this.userBankCardState = userBankCardState;
	}

	/**
	 * 结算状态
	 * @return the resState
	 */
	public String getResState() {
		return resState;
	}

	/**
	 * 结算状态
	 * @param resState the resState to set
	 */
	public void setResState(String resState) {
		this.resState = resState;
	}

	/**
	 * 工资结算状态
	 * @return the resStateText
	 */
	public String getResStateText() {
		return resStateText;
	}

	/**
	 * 工资结算状态
	 * @param resStateText the resStateText to set
	 */
	public void setResStateText(String resStateText) {
		this.resStateText = resStateText;
	}

	/**
	 * 考勤天数
	 * @return the resCheckWorkNum
	 */
	public Float getResCheckWorkNum() {
		return resCheckWorkNum;
	}

	/**
	 * 考勤天数
	 * @param resCheckWorkNum the resCheckWorkNum to set
	 */
	public void setResCheckWorkNum(Float resCheckWorkNum) {
		this.resCheckWorkNum = resCheckWorkNum;
	}

	/**
	 * 查询月份
	 * @return the monthNum
	 */
	public String getMonthNum() {
		return monthNum;
	}

	/**
	 * 查询月份
	 * @param monthNum the monthNum to set
	 */
	public void setMonthNum(String monthNum) {
		this.monthNum = monthNum;
	}

	/**
	 * @return the yearNum
	 */
	public String getYearNum() {
		return yearNum;
	}

	/**
	 * @param yearNum the yearNum to set
	 */
	public void setYearNum(String yearNum) {
		this.yearNum = yearNum;
	}

	
	
}
