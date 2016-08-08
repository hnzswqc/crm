/*
 * @项目名称: crm
 * @文件名称: ISubsystemService.java
 * @日期: 2015-9-2 下午01:51:45  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service;

import com.hnzskj.base.core.bean.Subsystem;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ISubsystemService.java   <br/>
 * 类描述： 子系统业务层接口  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-2 下午01:51:45   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-2 下午01:51:45   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface ISubsystemService {

	/**
	 * 
	 * 方法描述：添加子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 上午11:57:36<br/>         
	 * @param subsystem 子系统实体对象<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addSubsystem(Subsystem subsystem);
	
	/**
	 * 
	 * 方法描述：删除子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 上午11:57:36<br/>         
	 * @param subsystem 子系统实体对象<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delSubsystem(Subsystem subsystem);
	
	/**
	 * 
	 * 方法描述：修改子系统<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-2 上午11:57:36<br/>         
	 * @param subsystem 子系统实体对象<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean updSubsystem(Subsystem subsystem);
	
	/**
	 * 
	 * 方法描述：分页查询<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param subsystem 实体条件<br/>
	 * @return Page<Subsystem><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Subsystem> getSubsystemList(PageBean<Subsystem> pageBean,Subsystem subsystem);
	
}
