/*
 * @项目名称: crm
 * @文件名称: IWorkCheckDao.java
 * @日期: 2015-12-14 下午04:27:04  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.workcheck.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.workcheck.bean.WorkCheck;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IWorkCheckDao.java   <br/>
 * 类描述：考勤dao层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-14 下午04:27:04   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-14 下午04:27:04   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IWorkCheckDao {

	
	/**
	 * 
	 * 方法描述：批量添加员工考勤信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-14 下午04:28:14<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int addWorkCheck(List<WorkCheck> list,WorkCheck workCheck);
	
	/**
	 * 
	 * 方法描述：批量添加用户考勤信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2016-3-22 下午03:32:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int addWorkCheck(Object[][]params);
	
	/**
	 * 
	 * 方法描述：添加考勤信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-16 下午05:52:50<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int addWorkCheck(WorkCheck workCheck);
	
	
	/**
	 * 
	 * 方法描述：删除一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-14 下午04:29:47<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int delWorkCheck(String sqlCondition);
	
	/**
	 * 
	 * 方法描述：修改一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-14 下午04:29:55<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int updWorkCheck(WorkCheck workCheck);
	
	/**
	 * 
	 * 方法描述：分页查询考勤信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-14 下午04:30:02<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<WorkCheck> searchWorkCheckList(PageBean<WorkCheck> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	/**
	 * 
	 * 方法描述：判断当前考勤信息是否存在<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-16 下午09:58:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int addValidate(String wcYear,String wcMonth,String userNumber);
	
	
	
}
