/*
 * @项目名称: crm
 * @文件名称: ResignedDao.java
 * @日期: 2015-12-2 下午08:06:34  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.resigned.dao;

import java.util.LinkedHashMap;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.resigned.bean.Resigned;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ResignedDao.java   <br/>
 * 类描述：  离职员工dao层接口 <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-2 下午08:06:34   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-2 下午08:06:34   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IResignedDao {

	/**
	 * 
	 * 方法描述：添加一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-2 下午08:08:56<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int addResigned(Resigned resigned);
	
	/**
	 * 
	 * 方法描述：删除一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-2 下午08:09:05<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int delResigned(Resigned resigned);
	
	/**
	 * 
	 * 方法描述：修改一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-2 下午08:09:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int updResigned(Resigned resigned);
	
	/**
	 * 
	 * 方法描述：修改一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-2 下午08:09:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int updResigned(String resUuid,String userUuid);
	
	/**
	 * 
	 * 方法描述：分页查询辞职员工<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-2 下午08:09:31<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Resigned> searchResignedList(PageBean<Resigned> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	/**
	 * 
	 * 方法描述：通过条件查询一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-2 下午08:09:50<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public Resigned getResignedByParams(String sqlCondition);
	
	/**
	 * 
	 * 方法描述：辞职员工恢复，更改员工状态<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-9 下午06:34:09<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int updUserState(String userUuid);
}
