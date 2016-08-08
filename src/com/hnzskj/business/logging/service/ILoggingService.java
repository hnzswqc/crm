/*
 * @项目名称: crm
 * @文件名称: ILoggingService.java
 * @日期: 2015-11-2 下午05:23:28  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.logging.service;

import java.util.List;

import com.hnzskj.business.logging.bean.Logging;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ILoggingService.java   <br/>
 * 类描述：日常工作业务层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-2 下午05:23:28   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-2 下午05:23:28   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface ILoggingService {
	/**
	 * 
	 * 方法描述：添加一条记录<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-2 下午05:24:05<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean addLogging(Logging logging);
	
	/**
	 * 
	 * 方法描述：修改一条记录<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-2 下午05:24:34<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean delLogging(Logging logging);
	
	/**
	 * 
	 * 方法描述：修改一条记录<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-2 下午05:24:48<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean updLogging(Logging logging);
	
	/**
	 * 
	 * 方法描述：分页查询日常工作内容<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-27 下午05:13:40<br/>         
	 * @param pageBean 分页实体<br/>
	 * @param logging 查询字段<br/>
	 * @return PageBean<Logging><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Logging> searchLoggingList(PageBean<Logging> pageBean,Logging logging);
	
	
	/**
	 * 
	 * 方法描述：通过条件查询一条信息。<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-27 下午05:14:38<br/>         
	 * @param logging <br/>   
	 * @return Logging<br/>   
	 * @version   1.0<br/>
	 */
	public Logging getLoggingByParam(Logging logging);
	
	
	/**
	 * 
	 * 方法描述：计算班长工资。其班下所以员工包装工资的平均工资算作班长当天的工资<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-6 下午03:47:23<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public Logging getLeaderWages(Logging logging);
	
	/**
	 * 
	 * 方法描述：根据不同的时间查询员工工资<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 下午03:30:43<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Logging> searchLoggingList(Logging logging);
	
	
	/**
	 * 
	 * 方法描述：获取当前库中已经有的年度数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 下午03:30:43<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Logging> getYear();
	
	/**
	 * 
	 * 方法描述：根据年度和人员获取人员月度明细报表<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-11 下午05:33:51<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Logging> getYearDetailReport(Logging logging);
	

}
