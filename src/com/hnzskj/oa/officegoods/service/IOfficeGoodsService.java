/*
 * @项目名称: crm
 * @文件名称: IOfficeGoodsService.java
 * @日期: 2015-11-28 下午08:39:45  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.officegoods.service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.officegoods.bean.OfficeGoods;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IOfficeGoodsService.java   <br/>
 * 类描述：办公物品领用service层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-28 下午08:39:45   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-28 下午08:39:45   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IOfficeGoodsService {

	/**
	 * 
	 * 方法描述：添加一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-28 下午08:23:43<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean addOfficeGoods(OfficeGoods officeGoods);
	
	/**
	 * 
	 * 方法描述：删除一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-28 下午08:23:53<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean delOfficeGoods(OfficeGoods officeGoods);
	
	/**
	 * 
	 * 方法描述：修改一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-28 下午08:24:04<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean updOfficeGoods(OfficeGoods officeGoods);
	/**
	 * 
	 * 方法描述：分页查询产品信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-27 下午05:13:40<br/>         
	 * @param pageBean 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return PageBean<OfficeGoods><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<OfficeGoods> searchOfficeGoodsList(PageBean<OfficeGoods> pageBean,OfficeGoods officeGoods);
	
	
	/**
	 * 
	 * 方法描述：通过条件查询一条信息。<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-27 下午05:14:38<br/>         
	 * @param <br/>   
	 * @return Product<br/>   
	 * @version   1.0<br/>
	 */
	public OfficeGoods getOfficeGoodsByParam(OfficeGoods officeGoods);
	
}
