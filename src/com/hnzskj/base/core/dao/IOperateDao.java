/*
 * @项目名称: crm
 * @文件名称: IOperateDao.java
 * @日期: 2015-8-28 下午03:42:44  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao;

import java.util.LinkedHashMap;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Operate;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IOperateDao.java   <br/>
 * 类描述：操作功能权限dao层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 下午03:42:44   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 下午03:42:44   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IOperateDao {

	/**
	 * 
	 * 方法描述：添加操作功能<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 下午03:47:38<br/>         
	 * @param operate 实体参数<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addOperate(Operate operate);
	
	/**
	 * 
	 * 方法描述：修改操作功能<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 下午03:47:38<br/>         
	 * @param operate 实体参数<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updOperate(Operate operate);
	
	/**
	 * 
	 * 方法描述：删除操作功能<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-28 下午03:47:38<br/>         
	 * @param operate 实体参数<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delOperate(Operate operate);
	
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
	public PageBean<Operate> getOperateList(PageBean<Operate> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
}
