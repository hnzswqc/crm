/*
 * @项目名称: crm
 * @文件名称: IPurchaseService.java
 * @日期: 2015-11-27 下午07:17:13  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.officegoods.service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.officegoods.bean.Purchase;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IPurchaseService.java   <br/>
 * 类描述： 办公物品业务层接口  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-27 下午07:17:13   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-27 下午07:17:13   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IPurchaseService {
	
	/**
	 * 
	 * 方法描述：添加采购信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-27 下午07:02:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean addPurchase(Purchase purchase);
	
	/**
	 * 
	 * 方法描述：删除采购信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-27 下午07:02:26<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean delPurchase(Purchase purchase);
	
	/**
	 * 
	 * 方法描述：修改采购信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-27 下午07:02:38<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean updPurchase(Purchase purchase);
	
	/**
	 * 
	 * 方法描述：分页查询物品采购信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param purchase 条件实体<br/>
	 * @return Page<User><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Purchase> getPurchaseList(PageBean<Purchase> pageBean,Purchase purchase);

}
