/*
 * @项目名称: crm
 * @文件名称: IChartService.java
 * @日期: 2015-11-14 下午03:40:31  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service;

import java.util.List;

import com.hnzskj.base.core.bean.Chart;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IChartService.java   <br/>
 * 类描述：首页展示图业务层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-14 下午03:40:31   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-14 下午03:40:31   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IChartService {
	
	/**
	 * 
	 * 方法描述：获取部门人员信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-14 下午03:38:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Chart> getOrgUserList();
	
	/**
	 * 
	 * 方法描述：获取单位员工学历信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-14 下午03:38:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Chart> getUserDegreesList();
	
	
	/**
	 * 
	 * 方法描述：获取员工入职年限<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-14 下午03:38:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Chart> getUserJoinYearList();
	
	
	/**
	 * 
	 * 方法描述：获取员工年龄占比分析<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-14 下午03:38:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Chart> getUserAgeList();
	
	
	

}
