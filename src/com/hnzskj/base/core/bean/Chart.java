/*
 * @项目名称: crm
 * @文件名称: Chart.java
 * @日期: 2015-11-14 下午02:58:46  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Chart.java   <br/>
 * 类描述：统计图实体对象   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-14 下午02:58:46   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-14 下午02:58:46   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Chart {

	/**
	 * x轴
	 */
	private String labelField;
	
	/**
	 * y轴
	 */
	private Float dataField;
	
	/**
	 * 条件参数
	 */
	private String paramField;

	/**
	 * x轴
	 * @return the labelField
	 */
	public String getLabelField() {
		return labelField;
	}

	/**
	 * x轴
	 * @param labelField the labelField to set
	 */
	public void setLabelField(String labelField) {
		this.labelField = labelField;
	}

	/**
	 * y轴
	 * @return the dataField
	 */
	public Float getDataField() {
		return dataField;
	}

	/**
	 * y轴
	 * @param dataField the dataField to set
	 */
	public void setDataField(Float dataField) {
		this.dataField = dataField;
	}

	/**
	 * 条件参数
	 * @return the paramField
	 */
	public String getParamField() {
		return paramField;
	}

	/**
	 * 条件参数
	 * @param paramField the paramField to set
	 */
	public void setParamField(String paramField) {
		this.paramField = paramField;
	}
	
	
	
	
}
