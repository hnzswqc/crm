/*
 * @项目名称: crm
 * @文件名称: Dic.java
 * @日期: 2015-8-26 下午05:23:45  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：Dic.java   <br/>
 * 类描述：数据字典实体对象   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-26 下午05:23:45   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-26 下午05:23:45   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Dic {
	
	/**
	 * 主键
	 */
	private String uuid;
	
	/**
	 * 参数标识
	 */
	private String key;
	
	/**
	 * 标识值
	 */
	private String id;
	
	/**
	 * 名称
	 */
	private String text;
	
	/**
	 * 所属父级uuid
	 */
	private String parentUuid;
	
	/**
	 * 所属父级别名称
	 */
	private String parentName;
	
	/**
	 * 排序
	 */
	private Integer orderBy;
	
	/**
	 * 字典类别 
	 */
	private String type;
	
	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 说明
	 */
	private String createTime;
	
	/**
	 * 子级数量
	 */
	private Integer child;
	
	/**
	 * 显示样式值
	 */
	private String label;

	/**
	 * 主键
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 主键
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 标识
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 标识
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 名称
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * 名称
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 所属父级uuid
	 * @return the parentUuid
	 */
	public String getParentUuid() {
		return parentUuid;
	}

	/**
	 * 所属父级uuid
	 * @param parentUuid the parentUuid to set
	 */
	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}

	/**
	 * 所属父级别名称
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * 所属父级别名称
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * 排序
	 * @return the orderBy
	 */
	public Integer getOrderBy() {
		return orderBy;
	}

	/**
	 * 排序
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 备注
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 备注
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 说明
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 说明
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 字典类别 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 字典类别 
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * 参数标识
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 参数标识
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 显示样式值
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * 显示样式值
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	
	
}
