package com.hnzskj.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 项目名称：crm     <br/>
 * 类名称：Page     <br/>
 * 创建人：king   <br/>
 * 创建时间：2011-3-7 上午09:14:55   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-3-7 上午09:14:55   <br/>  
 * 修改备注：     <br/>
 * @version   1.0
 */
public class PageBean<E> {
	/**
	 * 当前页面的索引
	 */
	private int page = 1;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 总记录数
	 */
	private int totalRecords;
	
	/**
	 * 每页显示的记录数
	 */
	private int limit = 10;
	
	/**
	 * 封装结果集的集合
	 */
	private List<E> list = new ArrayList<E>();
	
	/**
	 * 封装对象数组对象
	 */
	private List<String[]> objArray = new ArrayList<String[]>();

	/**
	 * 当前页面的索引
	 * @return the page
	 */
	public int getPage() {
		if(0==page) page = 1;
		return page;
	}

	/**
	 * 当前页面的索引
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 总页数
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 总页数
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 总记录数
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		this.totalPage = (0 == this.totalRecords % this.limit)  
			? this.totalRecords / this.limit 
			: this.totalRecords / this.limit + 1;
		return totalRecords;
	}

	/**
	 * 总记录数
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * 每页显示的记录数
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * 每页显示的记录数
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * 封装结果集的集合
	 * @return the list
	 */
	public List<E> getList() {
		return list;
	}

	/**
	 * 封装结果集的集合
	 * @param list the list to set
	 */
	public void setList(List<E> list) {
		this.list = list;
	}

	/**
	 * 封装对象数组对象
	 * @return the objArray
	 */
	public List<String[]> getObjArray() {
		return objArray;
	}

	/**
	 * 封装对象数组对象
	 * @param objArray the objArray to set
	 */
	public void setObjArray(List<String[]> objArray) {
		this.objArray = objArray;
	}

	
	
	
}