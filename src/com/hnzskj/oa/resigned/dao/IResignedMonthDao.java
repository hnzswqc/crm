/*
 * @项目名称: crm
 * @文件名称: ResignedMonthDao.java
 * @日期: 2015-12-6 下午01:26:21  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.resigned.dao;

import java.util.List;

import com.hnzskj.oa.resigned.bean.Resigned;
import com.hnzskj.oa.resigned.bean.ResignedMonth;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ResignedMonthDao.java   <br/>
 * 类描述：   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-6 下午01:26:21   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-6 下午01:26:21   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IResignedMonthDao {

	/**
	 * 
	 * 方法描述：添加计发月份<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午01:31:39<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int addResignedMonth(Resigned resigned,String[]months);
	
	/**
	 * 
	 * 方法描述：根据条件删除计发月份<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午01:32:34<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int delResignedMonth(String sqlCondition);
	
	/**
	 * 
	 * 方法描述：批量修改计发月份状态<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午01:33:42<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int updResignedMonth(String monthUuids,String state);
	
	/**
	 * 
	 * 方法描述：通过用户uuid获取辞职人员计发月份<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午01:35:02<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<ResignedMonth> searchResignedMonthList(String sqlCondition);
	
	/**
	 * 
	 * 方法描述：修改辞职表信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午04:48:59<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int updateResigned(String userUuid);
	
}
