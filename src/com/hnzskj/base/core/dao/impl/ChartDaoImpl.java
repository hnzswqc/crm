/*
 * @项目名称: crm
 * @文件名称: ChartDaoImpl.java
 * @日期: 2015-11-14 下午03:38:59  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hnzskj.base.core.bean.Chart;
import com.hnzskj.base.core.dao.IChartDao;
import com.hnzskj.common.dao.BaseDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ChartDaoImpl.java   <br/>
 * 类描述：首页展示图dao层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-14 下午03:38:59   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-14 下午03:38:59   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class ChartDaoImpl extends BaseDao implements IChartDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IChartDao#getChartList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Chart> getChartList(String sql) {
		List<Chart> list = queryMax(sql, Chart.class, null);
		return list;
	}
	

}
