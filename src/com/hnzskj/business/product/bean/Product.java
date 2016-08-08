/*
 * @项目名称: crm
 * @文件名称: Product.java
 * @日期: 2015-10-27 下午03:56:24  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.product.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Product.java   <br/>
 * 类描述：产品实体对象   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-10-27 下午03:56:24   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-10-27 下午03:56:24   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Product {

	/**
	 * 产品uuid
	 */
	private String  productUuid;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 产品规格代码
	 */
	private String productNum;
	
	/**
	 * 产品规格信息
	 */
	private String productSpecifications;
	
	/**
	 * 包装单价
	 */
	private double productBzPrice;
	
	/**
	 * 抛光单价
	 */
	private double productPgPrice;
	
	/**
	 * 制管单价
	 */
	private double productZgPrice;
	
	/**
	 * 支重
	 */
	private double productWeight;
	
	/**
	 * 删除标记
	 */
	private Integer deleteType;
	
	/**
	 * 排序号
	 */
	private Integer productOrderBy;
	
	/**
	 * 备注说明
	 */
	private String productNote;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 生产状态
	 */
	private String productState;

	/**
	 * 生产状态 显示内容
	 */
	private String productStateText;
	
	/**
	 * 产品成品率
	 */
	private double productYeild;
	
	
	
	/**
	 * 产品uuid
	 * @return the productUuid
	 */
	public String getProductUuid() {
		return productUuid;
	}

	/**
	 * 产品uuid
	 * @param productUuid the productUuid to set
	 */
	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	/**
	 * 产品名称
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 产品名称
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 产品规格代码
	 * @return the productNum
	 */
	public String getProductNum() {
		return productNum;
	}

	/**
	 * 产品规格代码
	 * @param producNum the producNum to set
	 */
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	/**
	 * 产品规格信息
	 * @return the productSpecifications
	 */
	public String getProductSpecifications() {
		return productSpecifications;
	}

	/**
	 * 产品规格信息
	 * @param productSpecifications the productSpecifications to set
	 */
	public void setProductSpecifications(String productSpecifications) {
		this.productSpecifications = productSpecifications;
	}

	/**
	 * 包装单价
	 * @return the productBzPrice
	 */
	public double getProductBzPrice() {
		return productBzPrice;
	}

	/**
	 * 包装单价
	 * @param productBzPrice the productBzPrice to set
	 */
	public void setProductBzPrice(double productBzPrice) {
		this.productBzPrice = productBzPrice;
	}

	/**
	 * 抛光单价
	 * @return the productPgPrice
	 */
	public double getProductPgPrice() {
		return productPgPrice;
	}

	/**
	 * 抛光单价
	 * @param productPgPrice the productPgPrice to set
	 */
	public void setProductPgPrice(double productPgPrice) {
		this.productPgPrice = productPgPrice;
	}

	/**
	 * 制管单价
	 * @return the productZgPrice
	 */
	public double getProductZgPrice() {
		return productZgPrice;
	}

	/**
	 * 制管单价
	 * @param productZgPrice the productZgPrice to set
	 */
	public void setProductZgPrice(double productZgPrice) {
		this.productZgPrice = productZgPrice;
	}

	/**
	 * 支重
	 * @return the productWeight
	 */
	public double getProductWeight() {
		return productWeight;
	}

	/**
	 * 支重
	 * @param productWeight the productWeight to set
	 */
	public void setProductWeight(double productWeight) {
		this.productWeight = productWeight;
	}

	/**
	 * 删除标记
	 * @return the deleteType
	 */
	public Integer getDeleteType() {
		return deleteType;
	}

	/**
	 * 删除标记
	 * @param deleteType the deleteType to set
	 */
	public void setDeleteType(Integer deleteType) {
		this.deleteType = deleteType;
	}

	/**
	 * 排序号
	 * @return the productOrderBy
	 */
	public Integer getProductOrderBy() {
		return productOrderBy;
	}

	/**
	 * 排序号
	 * @param productOrderBy the productOrderBy to set
	 */
	public void setProductOrderBy(Integer productOrderBy) {
		this.productOrderBy = productOrderBy;
	}

	/**
	 * 备注说明
	 * @return the productNote
	 */
	public String getProductNote() {
		return productNote;
	}

	/**
	 * 备注说明
	 * @param productNote the productNote to set
	 */
	public void setProductNote(String productNote) {
		this.productNote = productNote;
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
	 * 生产状态
	 * @return the productState
	 */
	public String getProductState() {
		return productState;
	}

	/**
	 * 生产状态
	 * @param productState the productState to set
	 */
	public void setProductState(String productState) {
		this.productState = productState;
	}

	/**
	 * 生产状态 显示内容
	 * @return the productStateText
	 */
	public String getProductStateText() {
		return productStateText;
	}

	/**
	 * 生产状态 显示内容
	 * @param productStateText the productStateText to set
	 */
	public void setProductStateText(String productStateText) {
		this.productStateText = productStateText;
	}

	/**
	 * 产品成品率
	 * @return the productYeild
	 */
	public double getProductYeild() {
		return productYeild;
	}

	/**
	 * 产品成品率
	 * @param productYeild the productYeild to set
	 */
	public void setProductYeild(double productYeild) {
		this.productYeild = productYeild;
	}

	
	
	
}
