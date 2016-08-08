/*
 * @项目名称: crm
 * @文件名称: ProductService.java
 * @日期: 2015-10-27 下午05:36:31  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.product.service;

import com.hnzskj.business.product.bean.Product;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ProductService.java   <br/>
 * 类描述： 产品信息业务层接口  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-10-27 下午05:36:31   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-10-27 下午05:36:31   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IProductService {

	/**
	 * 
	 * 方法描述：添加一条信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-27 下午05:13:12<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addProduct(Product product);
	
	/**
	 * 
	 * 方法描述：删除一条信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-27 下午05:13:25<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delProduct(Product product);
	
	/**
	 * 
	 * 方法描述：修改一条信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-27 下午05:13:32<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public boolean updProduct(Product product);
	
	/**
	 * 
	 * 方法描述：分页查询产品信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-27 下午05:13:40<br/>         
	 * @param pageBean 分页实体<br/>
	 * @param product 条件实体对象<br/>
	 * @return PageBean<Product><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<Product> searchProductList(PageBean<Product> pageBean,Product product);
	
	
	/**
	 * 
	 * 方法描述：通过条件查询一条信息。<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-27 下午05:14:38<br/>         
	 * @param <br/>   
	 * @return Product<br/>   
	 * @version   1.0<br/>
	 */
	public Product getProductByParam(Product product);
	
}
