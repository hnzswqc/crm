/*
 * @项目名称: crm
 * @文件名称: IDicDao.java
 * @日期: 2015-8-26 下午05:57:38  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.base.core.bean.TreeNode;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IDicDao.java   <br/>
 * 类描述：数据字典dao层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-26 下午05:57:38   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-26 下午05:57:38   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IDicDao {

	/**
	 * 
	 * 方法描述：添加字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:04:54<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addDicType(Dic dic);
	
	/**
	 * 
	 * 方法描述：添加数据字典<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:05:03<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addDic(Dic dic);
	
	/**
	 * 
	 * 方法描述：删除字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:05:13<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delDicType(Dic dic);
	
	/**
	 * 
	 * 方法描述：删除数据字典<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:05:22<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delDic(Dic dic);
	
	/**
	 * 
	 * 方法描述：修改字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:05:32<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updDicType(Dic dic);
	
	/**
	 * 
	 * 方法描述：修改字典<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:05:41<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int updDic(Dic dic);
	
	/**
	 * 
	 * 方法描述：查询字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-26 下午06:05:49<br/>         
	 * @param <br/>   
	 * @return List<Dic><br/>   
	 * @version   1.0<br/>
	 */
	public List<Dic> getDicTypeList();
	
	/**
	 * 
	 * 方法描述：查询数据字典<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return Page<Dic><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Dic> getDicList(PageBean<Dic> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	
	/**
	 * 
	 * 方法描述：通过id查询数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 上午09:53:00<br/>         
	 * @param id 数据字典类别id<br/>   
	 * @return Dic<br/>   
	 * @version   1.0<br/>
	 */
	public Dic getDicType(String id);
	
	/**
	 * 
	 * 方法描述：通过id查询数据字典类别<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 上午09:53:00<br/>         
	 * @param type 数据字典类别<br/>
	 * @param id 数据字典id<br/>   
	 * @return Dic<br/>   
	 * @version   1.0<br/>
	 */
	public Dic getDic(String type,String id);
	
	/**
	 * 
	 * 方法描述：封装属性菜单数据字典<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 上午11:21:29<br/>         
	 * @param type 类别<br/>
	 * @param parentUuid 父级别字典<br/>   
	 * @return List<TreeNode><br/>   
	 * @version   1.0<br/>
	 */
	public List<TreeNode> getTreeList(String type,String parentUuid);
	
	/**
	 * 
	 * 方法描述：通过type获取数据字典信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 下午02:49:46<br/>         
	 * @param type 数据字典<br/>   
	 * @return List<Dic><br/>   
	 * @version   1.0<br/>
	 */
	public List<Dic> getParamList(String type);
	
	/**
	 * 
	 * 方法描述：通过key和type获取数据字典信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-27 下午04:24:25<br/>         
	 * @param type 类别<br/>
	 * @param key 标识key<br/>   
	 * @return Dic<br/>   
	 * @version   1.0<br/>
	 */
	public Dic getDicByKey(String type,String key);
	
	
}
