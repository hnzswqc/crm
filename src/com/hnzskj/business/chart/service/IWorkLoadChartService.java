/*
 * @项目名称: crm
 * @文件名称: IWorkLoadChartService.java
 * @日期: 2015-11-19 上午09:01:12  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.chart.service;

import java.util.List;

import com.hnzskj.base.core.bean.Chart;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IWorkLoadChartService.java   <br/>
 * 类描述：工作量分析service层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-19 上午09:01:12   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-19 上午09:01:12   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IWorkLoadChartService {

	
	/**
	 * 
	 * 方法描述：查询制管工作量<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-19 上午09:03:53<br/>         
	 * @param startDate 开始查询时间<br/>  
	 * @param endDate 结束查询时间<br/>  
	 * @param orderby 排序方式<br/>  
	 * @param topNum 每页显示数量<br/>
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Chart> getZgListChart(String startDate,String endDate,String orderby,Integer topNum);
	
	/**
	 * 
	 * 方法描述：查询包装工作量<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-19 上午09:03:53<br/>         
	 * @param startDate 开始查询时间<br/>  
	 * @param endDate 结束查询时间<br/>  
	 * @param orderby 排序方式<br/>  
	 * @param topNum 每页显示数量<br/>
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Chart> getBzListChart(String startDate,String endDate,String orderby,Integer topNum);
	
	
	/**
	 * 
	 * 方法描述：查询抛光工作量<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-19 上午09:03:53<br/>         
	 * @param startDate 开始查询时间<br/>  
	 * @param endDate 结束查询时间<br/>  
	 * @param orderby 排序方式<br/>  
	 * @param topNum 每页显示数量<br/>
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Chart> getPgListChart(String startDate,String endDate,String orderby,Integer topNum);
	
	/**
	 * 
	 * 方法描述：查询推管工作量<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-19 上午09:03:53<br/>         
	 * @param startDate 开始查询时间<br/>  
	 * @param endDate 结束查询时间<br/>  
	 * @param orderby 排序方式<br/>  
	 * @param topNum 每页显示数量<br/>
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Chart> getTgListChart(String startDate,String endDate,String orderby,Integer topNum);
	
}
