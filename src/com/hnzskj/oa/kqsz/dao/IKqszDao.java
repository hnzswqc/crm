/*
 * @项目名称: crm
 * @文件名称: KqszDao.java
 * @日期: 2016-3-4 下午07:59:17  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.kqsz.dao;

import java.util.LinkedHashMap;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.kqsz.bean.Kqsz;

/**    
 * 项目名称：crm   <br/>
 * 类名称：KqszDao.java   <br/>
 * 类描述：考勤设置dao层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2016-3-4 下午07:59:17   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2016-3-4 下午07:59:17   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IKqszDao {

	/**
	 * 
	 * 方法描述：添加一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2016-3-4 下午08:01:33<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int addKqsz(Kqsz kqsz);
	
	/**
	 * 
	 * 方法描述：删除一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2016-3-4 下午08:01:20<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int delKqsz(Kqsz kqsz);
	
	/**
	 * 
	 * 方法描述：修改一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2016-3-4 下午08:01:05<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int updKqsz(Kqsz kqsz);
	
	/**
	 * 
	 * 方法描述：查询考勤设置信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return Page<Kqsz><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Kqsz> getKqszList(PageBean<Kqsz> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	/**
	 * 
	 * 方法描述：根据条件查询一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2016-3-4 下午08:02:09<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public Kqsz getKqsz(String sqlCondition);
	
	
	
}
