/*
 * @项目名称: crm
 * @文件名称: ISubsystemDao.java
 * @日期: 2015-9-2 上午11:53:06  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao;

import java.util.LinkedHashMap;

import com.hnzskj.base.core.bean.Subsystem;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ISubsystemDao.java   <br/>
 * 类描述：子系统dao层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-2 上午11:53:06   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-2 上午11:53:06   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface ISubsystemDao {

	/**
	 * 
	 * 方法描述：添加子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 上午11:57:36<br/>         
	 * @param subsystem 子系统实体对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addSubsystem(Subsystem subsystem);
	
	/**
	 * 
	 * 方法描述：删除子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 上午11:57:36<br/>         
	 * @param subsystem 子系统实体对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delSubsystem(Subsystem subsystem);
	
	/**
	 * 
	 * 方法描述：修改子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 上午11:57:36<br/>         
	 * @param subsystem 子系统实体对象<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updSubsystem(Subsystem subsystem);
	
	/**
	 * 
	 * 方法描述：分页查询<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return Page<Subsystem><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Subsystem> getSubsystemList(PageBean<Subsystem> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
}
