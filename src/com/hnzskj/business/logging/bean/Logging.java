/*
 * @项目名称: crm
 * @文件名称: Logging.java
 * @日期: 2015-11-2 下午02:27:05  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.logging.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Logging.java   <br/>
 * 类描述：员工日结工资实体对象，记录员工每天的工作内容以及每天的工资数量等   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-2 下午02:27:05   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-2 下午02:27:05   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Logging {
	
	/**
	 * 主键
	 */
	private String loggingUuid;
	
	
	/**
	 * 开始查询时间
	 */
	private String startDate;
	/**
	 * 记录日期
	 */
	private String loggingDate;
	
	/**
	 * 结束查询时间
	 */
	private String endDate;
	
	/**
	 * 员工Uuid
	 */
	private String loggingUserUuid;
	
	/**
	 * 员工号
	 */
	private String loggingUserNumber;
	
	/**
	 * 员工名称
	 */
	private String loggingUserName;
	
	/**
	 * 员工性别
	 */
	private String loggingUserSex;
	
	/**
	 * 员工性别名称
	 */
	private String loggingUserSexText;
	
	/**
	 * 产品uuid
	 */
	private String loggingProductUuid;
	
	/**
	 * 产品规格
	 */
	private Integer loggingProductNum;
	
	/**
	 * 产品数量
	 */
	private Integer loggingNumber;
	
	/**
	 * 单价，随type变化而改变
	 */
	private Float loggingPrice;
	
	/**
	 * 支重
	 */
	private Float loggingWeight;
	
	/**
	 * 日产总重量
	 */
	private Float loggingAllWeight;
	/**
	 * 抛光次数
	 */
	private Integer loggingPgNum;
	
	/**
	 * 抛光比例。
	 */
	private Float loggingPgRatio;
	
	/**
	 * 工作性质。制管、包装、抛光、推管等。key
	 */
	private String loggingType;
	
	/**
	 * 工作性质。制管、包装、抛光、推管等。text
	 */
	private String loggingTypeText;
	
	/**
	 * 每日工资
	 */
	private Float loggingWages;
	
	/**
	 * 记录人员Uuid
	 */
	private String createUserUuid;
	
	/**
	 * 记录人员姓名
	 */
	private String createUserName;
	
	/**
	 * 记录人员id
	 */
	private String createUserId;
	
	/**
	 * 记录人员编号
	 */
	private String createUserNumber;
	
	
	/**
	 * 备注信息
	 */
	private String note; 
	
	/**
	 * 入库时间
	 */
	private String createTime;

	/**
	 * 员工所属部门
	 */
	private String orgName;
	
	/**
	 * 不合格数量
	 */
	private Integer loggingErrNum;
	
	/**
	 * 不合格系数
	 */
	private Float loggingErrRatio;
	
	/**
	 * 应扣工资
	 */
	private Float loggingErrWages;
	
	/**
	 * 检查人uuid
	 */
	private String loggingCheckUserUuid;
	
	/**
	 * 检查人名称
	 */
	private String loggingCheckUserName;
	
	/**
	 * 检查人工号
	 */
	private String loggingCheckUserNumber;
	
	/**
	 * 实际到手工资
	 */
	private Float loggingRealityWages;
	/**
	 * 主键
	 * @return the loggingUuid
	 */
	public String getLoggingUuid() {
		return loggingUuid;
	}

	/**
	 * 主键
	 * @param loggingUuid the loggingUuid to set
	 */
	public void setLoggingUuid(String loggingUuid) {
		this.loggingUuid = loggingUuid;
	}

	/**
	 * 记录日期
	 * @return the loggingDate
	 */
	public String getLoggingDate() {
		return loggingDate;
	}

	/**
	 * 记录日期
	 * @param loggingDate the loggingDate to set
	 */
	public void setLoggingDate(String loggingDate) {
		this.loggingDate = loggingDate;
	}

	/**
	 * 
	 * 员工Uuid
	 * @return the loggingUserUuid
	 */
	public String getLoggingUserUuid() {
		return loggingUserUuid;
	}

	/**
	 * 员工Uuid
	 * @param loggingUserUuid the loggingUserUuid to set
	 */
	public void setLoggingUserUuid(String loggingUserUuid) {
		this.loggingUserUuid = loggingUserUuid;
	}

	/**
	 * 员工号
	 * @return the loggingUserNumber
	 */
	public String getLoggingUserNumber() {
		return loggingUserNumber;
	}

	/**
	 * 员工号
	 * @param loggingUserNumber the loggingUserNumber to set
	 */
	public void setLoggingUserNumber(String loggingUserNumber) {
		this.loggingUserNumber = loggingUserNumber;
	}

	/**
	 * 员工名称
	 * @return the loggingUserName
	 */
	public String getLoggingUserName() {
		return loggingUserName;
	}

	/**
	 * 员工名称
	 * @param loggingUserName the loggingUserName to set
	 */
	public void setLoggingUserName(String loggingUserName) {
		this.loggingUserName = loggingUserName;
	}

	/**
	 * 产品uuid
	 * @return the loggingProductUuid
	 */
	public String getLoggingProductUuid() {
		return loggingProductUuid;
	}

	/**
	 * 产品uuid
	 * @param loggingProductUuid the loggingProductUuid to set
	 */
	public void setLoggingProductUuid(String loggingProductUuid) {
		this.loggingProductUuid = loggingProductUuid;
	}

	/**
	 * 产品规格
	 * @return the loggingProductNum
	 */
	public Integer getLoggingProductNum() {
		return loggingProductNum;
	}

	/**
	 * 产品规格
	 * @param loggingProductNum the loggingProductNum to set
	 */
	public void setLoggingProductNum(Integer loggingProductNum) {
		this.loggingProductNum = loggingProductNum;
	}

	/**
	 * 产品数量
	 * @return the loggingNumber
	 */
	public Integer getLoggingNumber() {
		return loggingNumber;
	}

	/**
	 * 产品数量
	 * @param loggingNumber the loggingNumber to set
	 */
	public void setLoggingNumber(Integer loggingNumber) {
		this.loggingNumber = loggingNumber;
	}

	/**
	 * 日产重量
	 * @return the loggingWeight
	 */
	public Float getLoggingWeight() {
		return loggingWeight;
	}

	/**
	 * 日产重量
	 * @param loggingWeight the loggingWeight to set
	 */
	public void setLoggingWeight(Float loggingWeight) {
		this.loggingWeight = loggingWeight;
	}

	/**
	 * 抛光次数
	 * @return the loggingPgNum
	 */
	public Integer getLoggingPgNum() {
		return loggingPgNum;
	}

	/**
	 * 抛光次数
	 * @param loggingPgNum the loggingPgNum to set
	 */
	public void setLoggingPgNum(Integer loggingPgNum) {
		this.loggingPgNum = loggingPgNum;
	}

	/**
	 * 工作性质。制管、包装、抛光、推管等。
	 * @return the loggingType
	 */
	public String getLoggingType() {
		return loggingType;
	}

	/**
	 * 工作性质。制管、包装、抛光、推管等。
	 * @param loggingType the loggingType to set
	 */
	public void setLoggingType(String loggingType) {
		this.loggingType = loggingType;
	}

	/**
	 * 每日工资
	 * @return the loggingWages
	 */
	public Float getLoggingWages() {
		return loggingWages;
	}

	/**
	 * 每日工资
	 * @param loggingWages the loggingWages to set
	 */
	public void setLoggingWages(Float loggingWages) {
		this.loggingWages = loggingWages;
	}

	/**
	 * 记录人员Uuid
	 * @return the createUserUuid
	 */
	public String getCreateUserUuid() {
		return createUserUuid;
	}

	/**
	 * 记录人员Uuid
	 * @param createUserUuid the createUserUuid to set
	 */
	public void setCreateUserUuid(String createUserUuid) {
		this.createUserUuid = createUserUuid;
	}

	/**
	 * 记录人员姓名
	 * @return the createUserName
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * 记录人员姓名
	 * @param createUserName the createUserName to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * 记录人员id
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * 记录人员id
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 记录人员编号
	 * @return the createUserNumber
	 */
	public String getCreateUserNumber() {
		return createUserNumber;
	}

	/**
	 * 记录人员编号
	 * @param createUserNumber the createUserNumber to set
	 */
	public void setCreateUserNumber(String createUserNumber) {
		this.createUserNumber = createUserNumber;
	}

	/**
	 * 备注信息
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 备注信息
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 入库时间
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 入库时间
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 员工性别
	 * @return the loggingUserSex
	 */
	public String getLoggingUserSex() {
		return loggingUserSex;
	}

	/**
	 * 员工性别
	 * @param loggingUserSex the loggingUserSex to set
	 */
	public void setLoggingUserSex(String loggingUserSex) {
		this.loggingUserSex = loggingUserSex;
	}

	/**
	 * 单价，随type变化而改变
	 * @return the loggingPrice
	 */
	public Float getLoggingPrice() {
		return loggingPrice;
	}

	/**
	 * 单价，随type变化而改变
	 * @param loggingPrice the loggingPrice to set
	 */
	public void setLoggingPrice(Float loggingPrice) {
		this.loggingPrice = loggingPrice;
	}

	/**
	 * 日产总重量
	 * @return the loggingAllWeight
	 */
	public Float getLoggingAllWeight() {
		return loggingAllWeight;
	}

	/**
	 * 日产总重量
	 * @param loggingAllWeight the loggingAllWeight to set
	 */
	public void setLoggingAllWeight(Float loggingAllWeight) {
		this.loggingAllWeight = loggingAllWeight;
	}

	/**
	 * 开始查询时间
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 开始查询时间
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 结束查询时间
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 结束查询时间
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 员工性别名称
	 * @return the loggingUserSexText
	 */
	public String getLoggingUserSexText() {
		return loggingUserSexText;
	}

	/**
	 * 员工性别名称
	 * @param loggingUserSexText the loggingUserSexText to set
	 */
	public void setLoggingUserSexText(String loggingUserSexText) {
		this.loggingUserSexText = loggingUserSexText;
	}

	/**
	 * 工作性质。制管、包装、抛光、推管等。text
	 * @return the loggingTypeText
	 */
	public String getLoggingTypeText() {
		return loggingTypeText;
	}

	/**
	 * 工作性质。制管、包装、抛光、推管等。text
	 * @param loggingTypeText the loggingTypeText to set
	 */
	public void setLoggingTypeText(String loggingTypeText) {
		this.loggingTypeText = loggingTypeText;
	}

	/**
	 * 员工所属部门
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 员工所属部门
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 不合格数量
	 * @return the loggingErrNum
	 */
	public Integer getLoggingErrNum() {
		return loggingErrNum;
	}

	/**
	 * 不合格数量
	 * @param loggingErrNum the loggingErrNum to set
	 */
	public void setLoggingErrNum(Integer loggingErrNum) {
		this.loggingErrNum = loggingErrNum;
	}

	/**
	 * 不合格系数
	 * @return the loggingErrRatio
	 */
	public Float getLoggingErrRatio() {
		return loggingErrRatio;
	}

	/**
	 * 不合格系数
	 * @param loggingErrRatio the loggingErrRatio to set
	 */
	public void setLoggingErrRatio(Float loggingErrRatio) {
		this.loggingErrRatio = loggingErrRatio;
	}

	/**
	 * 应扣工资
	 * @return the loggingErrWages
	 */
	public Float getLoggingErrWages() {
		return loggingErrWages;
	}

	/**
	 * 应扣工资
	 * @param loggingErrWages the loggingErrWages to set
	 */
	public void setLoggingErrWages(Float loggingErrWages) {
		this.loggingErrWages = loggingErrWages;
	}

	/**
	 * 检查人uuid
	 * @return the loggingCheckUserUuid
	 */
	public String getLoggingCheckUserUuid() {
		return loggingCheckUserUuid;
	}

	/**
	 * 检查人uuid
	 * @param loggingCheckUserUuid the loggingCheckUserUuid to set
	 */
	public void setLoggingCheckUserUuid(String loggingCheckUserUuid) {
		this.loggingCheckUserUuid = loggingCheckUserUuid;
	}

	/**
	 * 检查人名称
	 * @return the loggingCheckUserName
	 */
	public String getLoggingCheckUserName() {
		return loggingCheckUserName;
	}

	/**
	 * 检查人名称
	 * @param loggingCheckUserName the loggingCheckUserName to set
	 */
	public void setLoggingCheckUserName(String loggingCheckUserName) {
		this.loggingCheckUserName = loggingCheckUserName;
	}

	/**
	 * 检查人工号
	 * @return the loggingCheckUserNumber
	 */
	public String getLoggingCheckUserNumber() {
		return loggingCheckUserNumber;
	}

	/**
	 * 检查人工号
	 * @param loggingCheckUserNumber the loggingCheckUserNumber to set
	 */
	public void setLoggingCheckUserNumber(String loggingCheckUserNumber) {
		this.loggingCheckUserNumber = loggingCheckUserNumber;
	}

	/**
	 * 实际到手工资
	 * @return the loggingRealityWages
	 */
	public Float getLoggingRealityWages() {
		return loggingRealityWages;
	}

	/**
	 * 实际到手工资
	 * @param loggingRealityWages the loggingRealityWages to set
	 */
	public void setLoggingRealityWages(Float loggingRealityWages) {
		this.loggingRealityWages = loggingRealityWages;
	}

	/**
	 * 抛光比例。
	 * @return the loggingPgRatio
	 */
	public Float getLoggingPgRatio() {
		return loggingPgRatio;
	}

	/**
	 * 抛光比例。
	 * @param loggingPgRatio the loggingPgRatio to set
	 */
	public void setLoggingPgRatio(Float loggingPgRatio) {
		this.loggingPgRatio = loggingPgRatio;
	}

		
	
}
