/*
 * @项目名称: crm
 * @文件名称: ModelDao.java
 * @日期: 2015-8-10 下午02:54:41  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Model;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ModelDao.java   <br/>
 * 类描述： 模块菜单dao层接口  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-10 下午02:54:41   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-10 下午02:54:41   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IModelDao {
	
	/**
	 * 
	 * 方法描述：添加模型信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-12 下午06:47:50<br/>         
	 * @param model <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addModel(Model model);
	
	/**
	 * 
	 * 方法描述：删除模型信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-12 下午06:48:32<br/>         
	 * @param modelUuid 模型uuid<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delModel(String modelUuid);
	
	/**
	 * 
	 * 方法描述：修改模型信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-12 下午06:49:04<br/>         
	 * @param model 模型信息<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updModel(Model model);
	
	/**
	 * 
	 * 方法描述：获取模型信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-12 下午06:49:36<br/>         
	 * @param modelUuid 模型uuid<br/>   
	 * @return Model<br/>   
	 * @version   1.0<br/>
	 */
	public Model getModel(String modelUuid);

	/**
	 * 
	 * 方法描述：查询子系统模块菜单<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-10 下午02:58:20<br/>         
	 * @param subsystemUuid 子系统模块Id<br/>   
	 * @return List<Model><br/>   
	 * @version   1.0<br/>
	 */
	public List<Model> getModelList(String subsystemUuid);
	
	/**
	 * 
	 * 方法描述：分页查询模型信息<br/>
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
	public PageBean<Model> getModelList(PageBean<Model> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	
	
	
	
	
}
