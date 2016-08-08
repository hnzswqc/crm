/*
 * @项目名称: crm
 * @文件名称: IRecordService.java
 * @日期: 2015-11-16 上午11:23:43  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.record.service;

import com.hnzskj.business.record.bean.Record;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IRecordService.java   <br/>
 * 类描述：员工档案业务层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-16 上午11:23:43   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-16 上午11:23:43   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IRecordService {

	/**
	 * 
	 * 方法描述：添加一条记录<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 上午10:54:45<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean addRecord(Record record);
	
	/**
	 * 
	 * 方法描述：删除一条记录<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 上午10:54:53<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean delRecord(Record record);
	
	/**
	 * 
	 * 方法描述：修改一条记录<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 上午10:55:02<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean updRecord(Record record);
	
	/**
	 * 
	 * 方法描述：分页查询员工档案记录<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-16 上午10:55:13<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Record> searchRecordPage(PageBean<Record> pageBean,Record record);
	
	/**
	 * 
	 * 方法描述：通过条件查询一条信息。<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-27 下午05:14:38<br/>         
	 * @param <br/>   
	 * @return Product<br/>   
	 * @version   1.0<br/>
	 */
	public Record getRecordByParam(Record record);
}
