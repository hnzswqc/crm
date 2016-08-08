/*
 * @项目名称: crm
 * @文件名称: IOperateService.java
 * @日期: 2015-8-28 下午04:00:13  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service;

import javax.servlet.http.HttpServletRequest;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Operate;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IOperateService.java   <br/>
 * 类描述：功能操作业务层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 下午04:00:13   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 下午04:00:13   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IOperateService {


	/**
	 * 
	 * 方法描述：添加操作功能<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 下午03:47:38<br/>         
	 * @param operate 实体参数<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addOperate(Operate operate);
	
	/**
	 * 
	 * 方法描述：修改操作功能<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 下午03:47:38<br/>         
	 * @param operate 实体参数<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean updOperate(Operate operate);
	
	/**
	 * 
	 * 方法描述：删除操作功能<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 下午03:47:38<br/>         
	 * @param operate 实体参数<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delOperate(Operate operate);
	
	/**
	 * 
	 * 方法描述：获取唯一key操作<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 下午03:47:38<br/>         
	 * @param operate 实体参数<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public Operate getOperateByKey(Operate operate);
	
	/**
	 * 
	 * 方法描述：获取功能权限分页信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return Page<Operate><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Operate> getOperateList(PageBean<Operate> pageBean,Operate operate);
	
	/**
	 * 
	 * 方法描述：获取操作权限<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-1 下午03:44:09<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	public String getOperateList(String id,HttpServletRequest request);
}
