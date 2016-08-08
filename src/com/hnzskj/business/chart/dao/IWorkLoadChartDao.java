/*
 * @项目名称: crm
 * @文件名称: IWorkLoadChartDao.java
 * @日期: 2015-11-19 上午08:58:08  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.chart.dao;

import java.util.List;

import com.hnzskj.base.core.bean.Chart;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IWorkLoadChartDao.java   <br/>
 * 类描述：员工工作量分析dao层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-19 上午08:58:08   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-19 上午08:58:08   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public  interface IWorkLoadChartDao {
	
	/**
	 * 
	 * 方法描述：获取展现图数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-14 下午03:38:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Chart> getChartList(String sql);

}
