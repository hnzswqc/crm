/*
 * @项目名称: crm
 * @文件名称: TreeNode.java
 * @日期: 2015-8-20 上午11:13:38  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;



/**    
 * 项目名称：crm   <br/>
 * 类名称：TreeNode.java   <br/>
 * 类描述：功能菜单树实体对象   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-20 上午11:13:38   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-20 上午11:13:38   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class TreeNode {

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 内容
	 */
	private String text;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * Url
	 */
	private String url;
	
	/**
	 * 是否选中
	 */
	private Integer check;
	
	/**
	 * 是否末级别
	 */
	private boolean leaf;
	
	/**
	 * 子级别数量
	 */
	private Integer childsNum;
	

	/**
	 * id
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * id
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 内容
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * 内容
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 图标
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 图标
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * Url
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Url
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 是否选中
	 * @return the check
	 */
	public Integer getCheck() {
		return check;
	}

	/**
	 * 是否选中
	 * @param check the check to set
	 */
	public void setCheck(Integer check) {
		this.check = check;
	}

	/**
	 * 是否末级别
	 * @return the leaf
	 */
	public boolean isLeaf() {
		if(null!=childsNum){
			if(childsNum>0){
				return false;
			}else{
				return true;
			}
		}
		return leaf;
	}

	/**
	 * 是否末级别
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * 子级别数量
	 * @return the childsNum
	 */
	public Integer getChildsNum() {
		return childsNum;
	}

	/**
	 * 子级别数量
	 * @param childsNum the childsNum to set
	 */
	public void setChildsNum(Integer childsNum) {
		this.childsNum = childsNum;
	}

	
	
	
}
