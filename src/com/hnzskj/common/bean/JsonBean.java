/*
 * @项目名称: OACharts
 * @文件名称: JsonBean.java
 * @日期: 2015-2-3 下午04:21:10  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.common.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**    
 * 项目名称：OACharts   <br/>
 * 类名称：JsonBean.java   <br/>
 * 类描述：   <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2015-2-3 下午04:21:10   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2015-2-3 下午04:21:10   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class JsonBean {

	/**
	 * 默认构造函数
	 */
	public JsonBean(){}
	/**
	 * 构造函数
	 * @param result,状态值
	 * @param success,成功状态
	 * @param object,数据
	 * @param dataSize,数据量
	 */
	public JsonBean(String result,boolean success,Object data,Integer dataSize){
		this.result = result;
		this.success = success;
		this.data = data;
		this.dataSize = dataSize;
	}
	/**
	 * 构造函数
	 * @param result,状态值
	 * @param success,成功状态
	 */
	public JsonBean(String result,boolean success){
		this.result = result;
		this.success = success;
	}

	/**
	 * 返回结果状态值。三种状态<br/>
	 * RESULT_STATE_OK ok，成功<br/>
	 * RESULT_STATE_NULL null，成功，但是没有数据<br/>
	 * RESULT_STATE_ERROR error，失败<br/>
	 */
	public String result;
	
	/**
	 * 返回实体对象，可以是任何类型
	 */
	private Object data;
	
	/**
	 * 时间戳
	 */
	private String createTime;
	
	/**
	 * 数据多少
	 */
	private Integer dataSize;
	
	/**
	 * 成功状态
	 */
	private boolean success;
	
	

	/**
	 * 返回结果状态值。三种状态<br/>
	 * RESULT_STATE_OK ok，成功<br/>
	 * RESULT_STATE_NULL null，成功，但是没有数据<br/>
	 * RESULT_STATE_ERROR error，失败<br/>
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 返回结果状态值。三种状态<br/>
	 * RESULT_STATE_OK ok，成功<br/>
	 * RESULT_STATE_NULL null，成功，但是没有数据<br/>
	 * RESULT_STATE_ERROR error，失败<br/>
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 返回实体对象，可以是任何类型
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 返回实体对象，可以是任何类型
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 时间戳
	 * @return the createTime
	 */
	public String getCreateTime() {
		if(null!=createTime&&!"".equals(createTime)){
			return createTime;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
		String data = format.format(new Date());
		return data;
	}

	/**
	 * 时间戳
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 数据多少
	 * @return the dataSize
	 */
	public Integer getDataSize() {
		return dataSize;
	}
	/**
	 * 数据多少
	 * @param dataSize the dataSize to set
	 */
	public void setDataSize(Integer dataSize) {
		this.dataSize = dataSize;
	}
	/**
	 * 成功状态
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * 成功状态
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
