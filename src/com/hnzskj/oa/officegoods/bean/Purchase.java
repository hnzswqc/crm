/*
 * @项目名称: crm
 * @文件名称: Purchase.java
 * @日期: 2015-11-27 下午05:54:15  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.officegoods.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Purchase.java   <br/>
 * 类描述：物品采购 实体对象  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-27 下午05:54:15   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-27 下午05:54:15   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Purchase {

	/**
	 * 物品采购uuid
	 */
	private String purchaseUuid;
	
	/**
	 * 物品采购时间
	 */
	private String purchaseDate;
	
	/**
	 * 物品类别
	 */
	private String purchaseType;
	
	/**
	 * 物品类别名称
	 */
	private String purchaseTypeText;
	
	/**
	 * 物品名称
	 */
	private String purchaseName;
	
	/**
	 * 物品规格
	 */
	private String purchaseModel;
	
	/**
	 * 规格单位
	 */
	private String purchaseUnit;
	
	/**
	 * 物品采购单价
	 */
	private Float purchasePrice;
	
	/**
	 * 物品采购数量
	 */
	private Float purchaseNumber;
	
	/**
	 * 采购总价
	 */
	private Float purchaseTotalPrices;
	
	/**
	 * 库存数量
	 */
	private Float purchaseOnHand;
	
	/**
	 * 备注信息
	 */
	private String purchaseNote;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 入库人员uuid
	 */
	private String createUserUuid;
	
	/**
	 * 入库人员名称
	 */
	private String createUserName;
	
	/**
	 * 入库人员编号
	 */
	private String createUserNumber;
	
	/**
	 * 采购人员
	 */
	private String purchasePersonName;

	/**
	 * 物品采购uuid
	 * @return the purchaseUuid
	 */
	public String getPurchaseUuid() {
		return purchaseUuid;
	}

	/**
	 * 物品采购uuid
	 * @param purchaseUuid the purchaseUuid to set
	 */
	public void setPurchaseUuid(String purchaseUuid) {
		this.purchaseUuid = purchaseUuid;
	}

	/**
	 * 物品采购时间
	 * @return the purchaseDate
	 */
	public String getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * 物品采购时间
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * 物品类别
	 * @return the purchaseType
	 */
	public String getPurchaseType() {
		return purchaseType;
	}

	/**
	 * @param purchaseType the purchaseType to set
	 */
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	/**
	 * @return the purchaseTypeText
	 */
	public String getPurchaseTypeText() {
		return purchaseTypeText;
	}

	/**
	 * @param purchaseTypeText the purchaseTypeText to set
	 */
	public void setPurchaseTypeText(String purchaseTypeText) {
		this.purchaseTypeText = purchaseTypeText;
	}

	/**
	 * 物品类别
	 * @return the purchaseName
	 */
	public String getPurchaseName() {
		return purchaseName;
	}

	/**
	 * 物品名称
	 * @param purchaseName the purchaseName to set
	 */
	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	/**
	 * 物品名称
	 * @return the purchaseModel
	 */
	public String getPurchaseModel() {
		return purchaseModel;
	}

	/**
	 * 物品规格
	 * @param purchaseModel the purchaseModel to set
	 */
	public void setPurchaseModel(String purchaseModel) {
		this.purchaseModel = purchaseModel;
	}

	/**
	 * 物品规格
	 * @return the purchaseUnit
	 */
	public String getPurchaseUnit() {
		return purchaseUnit;
	}

	/**
	 * 规格单位
	 * @param purchaseUnit the purchaseUnit to set
	 */
	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}

	/**
	 * 规格单位
	 * @return the purchasePrice
	 */
	public Float getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * 物品采购单价
	 * @param purchasePrice the purchasePrice to set
	 */
	public void setPurchasePrice(Float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * 物品采购单价
	 * @return the purchaseNumber
	 */
	public Float getPurchaseNumber() {
		return purchaseNumber;
	}

	/**
	 * 物品采购数量
	 * @param purchaseNumber the purchaseNumber to set
	 */
	public void setPurchaseNumber(Float purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	/**
	 * 物品采购数量
	 * @return the purchaseTotalPrices
	 */
	public Float getPurchaseTotalPrices() {
		return purchaseTotalPrices;
	}

	/**
	 * 采购总价
	 * @param purchaseTotalPrices the purchaseTotalPrices to set
	 */
	public void setPurchaseTotalPrices(Float purchaseTotalPrices) {
		this.purchaseTotalPrices = purchaseTotalPrices;
	}

	/**
	 * @return the purchaseNote
	 */
	public String getPurchaseNote() {
		return purchaseNote;
	}

	/**
	 * @param purchaseNote the purchaseNote to set
	 */
	public void setPurchaseNote(String purchaseNote) {
		this.purchaseNote = purchaseNote;
	}

	/**
	 * @return the createUserUuid
	 */
	public String getCreateUserUuid() {
		return createUserUuid;
	}

	/**
	 * 库存数量
	 * @param createUserUuid the createUserUuid to set
	 */
	public void setCreateUserUuid(String createUserUuid) {
		this.createUserUuid = createUserUuid;
	}

	/**
	 * 入库人员名称
	 * @return the createUserName
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * 入库人员名称
	 * @param createUserName the createUserName to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * 入库人员编号
	 * @return the createUserNumber
	 */
	public String getCreateUserNumber() {
		return createUserNumber;
	}

	/**
	 * 入库人员编号
	 * @param createUserNumber the createUserNumber to set
	 */
	public void setCreateUserNumber(String createUserNumber) {
		this.createUserNumber = createUserNumber;
	}

	/**
	 * 库存数量
	 * @return the purchaseOnHand
	 */
	public Float getPurchaseOnHand() {
		return purchaseOnHand;
	}

	/**
	 * 库存数量
	 * @param purchaseOnHand the purchaseOnHand to set
	 */
	public void setPurchaseOnHand(Float purchaseOnHand) {
		this.purchaseOnHand = purchaseOnHand;
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
	 * 采购人员
	 * @return the purchasePersonName
	 */
	public String getPurchasePersonName() {
		return purchasePersonName;
	}

	/**
	 * 采购人员
	 * @param purchasePersonName the purchasePersonName to set
	 */
	public void setPurchasePersonName(String purchasePersonName) {
		this.purchasePersonName = purchasePersonName;
	}
	
	
	
}
