/*
 * @项目名称: crm
 * @文件名称: ProductServiceImpl.java
 * @日期: 2015-10-27 下午05:38:17  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.product.service.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.business.common.util.MfConstant;
import com.hnzskj.business.product.bean.Product;
import com.hnzskj.business.product.dao.IProductDao;
import com.hnzskj.business.product.service.IProductService;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ProductServiceImpl.java   <br/>
 * 类描述： 产品信息dao层接口  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-10-27 下午05:38:17   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-10-27 下午05:38:17   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class ProductServiceImpl implements IProductService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IProductDao productDao  = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.business.product.service.ProductService#addProduct(com.hnzskj.business.product.bean.Product)
	 */
	public boolean addProduct(Product product) {
		int result = productDao.addProduct(product);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.product.service.ProductService#delProduct(com.hnzskj.business.product.bean.Product)
	 */
	public boolean delProduct(Product product) {
		int result = productDao.delProduct(product);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.product.service.IProductService#getProductByParam(com.hnzskj.business.product.bean.Product)
	 */
	public Product getProductByParam(Product product) {
		StringBuffer sb = new StringBuffer();
		if(null!=product){
			//uuid
			if(null!=product.getProductUuid()&&!"".equals(product.getProductUuid())){
				sb.append(" AND PRODUCT_UUID = '").append(product.getProductUuid()).append("'");
			}
			//产品规格
			if(null!=product.getProductNum()&&!"".equals(product.getProductNum())){
				sb.append(" AND PRODUCT_NUM = '").append(product.getProductNum()).append("'");
			}
		}
		Product p = productDao.getProductByParam(sb.toString());
		return p;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.product.service.ProductService#searchProductList(com.hnzskj.common.bean.PageBean, com.hnzskj.business.product.bean.Product)
	 */
	public PageBean<Product> searchProductList(PageBean<Product> pageBean,
			Product product) {
		String fields = " PRODUCT_UUID AS PRODUCTUUID,PRODUCT_NAME AS PRODUCTNAME,PRODUCT_NUM AS PRODUCTNUM,PRODUCT_SPECIFICATIONS AS PRODUCTSPECIFICATIONS,PRODUCT_BZ_PRICE AS PRODUCTBZPRICE,PRODUCT_PG_PRICE AS PRODUCTPGPRICE,PRODUCT_ZG_PRICE AS PRODUCTZGPRICE,PRODUCT_WEIGHT AS PRODUCTWEIGHT,DELETE_TYPE AS DELETETYPE,PRODUCT_ORDERBY AS PRODUCTORDERBY,PRODUCT_NOTE AS PRODUCTNOTE ,CREATE_TIME AS CREATETIME,PRODUCT_STATE AS PRODUCTSTATE,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_PRODUCT_STATE+"' AND ID = PRODUCT_STATE) AS PRODUCTSTATETEXT,PRODUCT_YEILD AS PRODUCTYEILD ";
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		if(null!=product){
			//产品名称
			if(null!=product.getProductName()&&!"".equals(product.getProductName())){
				sb.append(" AND PRODUCT_NAME LIKE '%").append(product.getProductName()).append("%'");
			}
			//产品编号
			if(null!=product.getProductNum()&&!"".equals(product.getProductNum())){
				sb.append(" AND PRODUCT_NUM LIKE '%").append(product.getProductNum()).append("%'");
			}
			//产品生产状态
			if(null!=product.getProductState()&&!"".equals(product.getProductState())){
				sb.append(" AND PRODUCT_STATE = ").append(product.getProductState());
			}
			//产品删除状态
			if(null!=product.getDeleteType()&&0!=product.getDeleteType()){
				sb.append(" AND DELETE_TYPE = ").append(product.getDeleteType());
			}
		}
		sb.append(" AND DELETE_TYPE = "+MfConstant.DELETE_TYPE_NO+" ");
		LinkedHashMap<String,String> orderby = new LinkedHashMap<String, String>();
		orderby.put("PRODUCT_ORDERBY", "ASC");
		pageBean = productDao.searchProductList(pageBean, fields, sb.toString(), null, orderby, " APP_BUSINESS_PRODUCT ", " PRODUCT_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.product.service.ProductService#updProduct(com.hnzskj.business.product.bean.Product)
	 */
	public boolean updProduct(Product product) {
		int result = productDao.updProduct(product);
		return result>0?true:false;
	}

}
