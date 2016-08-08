/*
 * @项目名称: crm
 * @文件名称: WorkLoadChartDaoImpl.java
 * @日期: 2015-11-19 上午09:00:13  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.chart.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hnzskj.base.core.bean.Chart;
import com.hnzskj.business.chart.dao.IWorkLoadChartDao;
import com.hnzskj.common.dao.BaseDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：WorkLoadChartDaoImpl.java   <br/>
 * 类描述：工作量分析dao层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-19 上午09:00:13   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-19 上午09:00:13   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class WorkLoadChartDaoImpl extends BaseDao implements IWorkLoadChartDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.business.chart.dao.IWorkLoadChartDao#getChartList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Chart> getChartList(String sql) {
		List<Chart> list = queryMax(sql, Chart.class, null);
		return list;
	}

}
