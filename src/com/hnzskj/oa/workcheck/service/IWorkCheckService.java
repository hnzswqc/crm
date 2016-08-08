/*
 * @项目名称: crm
 * @文件名称: IWorkCheckService.java
 * @日期: 2015-12-14 下午05:00:08  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.workcheck.service;

import java.util.List;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.workcheck.bean.WorkCheck;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IWorkCheckService.java   <br/>
 * 类描述：考勤管理业务层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-14 下午05:00:08   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-14 下午05:00:08   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IWorkCheckService {


	/**
	 * 
	 * 方法描述：批量添加员工考勤信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-14 下午04:28:14<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean addWorkCheck(List<WorkCheck> list,WorkCheck workCheck);
	
	/**
	 * 
	 * 方法描述：添加考勤信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-16 下午05:52:50<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean addWorkCheck(WorkCheck workCheck);
	
	/**
	 * 
	 * 方法描述：删除一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-14 下午04:29:47<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean delWorkCheck(WorkCheck workCheck);
	
	/**
	 * 
	 * 方法描述：修改一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-14 下午04:29:55<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean updWorkCheck(WorkCheck workCheck);
	
	/**
	 * 
	 * 方法描述：分页查询考勤信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-14 下午04:30:02<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<WorkCheck> searchWorkCheckList(PageBean<WorkCheck> pageBean,WorkCheck workCheck);
	
	
	/**
	 * 
	 * 方法描述：判断当前考勤信息是否存在<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-16 下午09:58:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean addValidate(WorkCheck workCheck);
}
