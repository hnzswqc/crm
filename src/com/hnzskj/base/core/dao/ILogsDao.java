/*
 * @项目名称: crm
 * @文件名称: ILogs.java
 * @日期: 2015-8-11 下午05:53:32  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao;

import java.util.LinkedHashMap;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Logs;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ILogs.java   <br/>
 * 类描述：系统日志dao层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-11 下午05:53:33   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-11 下午05:53:33   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface ILogsDao {

	/**
	 * 
	 * 方法描述：添加日志<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 下午06:00:22<br/>         
	 * @param logs 实体条件 <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addLogs(Logs logs);
	
	/**
	 * 
	 * 方法描述：批量删除一条日志信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 下午06:01:34<br/>         
	 * @param logUuids 日志uuids。形如'','','',<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delLogs(String logUuids);
	
	/**
	 * 
	 * 方法描述：修改日志信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 下午06:05:56<br/>         
	 * @param logs 日志实体条件<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updLogs(Logs logs);
	
	/**
	 * 
	 * 方法描述：获取日志详细信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 下午06:06:27<br/>         
	 * @param logUuid 日志uuid<br/>   
	 * @return Logs<br/>   
	 * @version   1.0<br/>
	 */
	public Logs getLogs(String logUuid);
	
	/**
	 * 
	 * 方法描述：分页查询日志信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 下午06:06:46<br/>         
	 * @param page 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return PageBean<Logs><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Logs> getLogsList(PageBean<Logs> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	
}
