/*
 * @项目名称: crm
 * @文件名称: ProductDaoImpl.java
 * @日期: 2015-10-27 下午05:15:50  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.product.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.business.common.util.MfConstant;
import com.hnzskj.business.product.bean.Product;
import com.hnzskj.business.product.dao.IProductDao;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.DataUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ProductDaoImpl.java   <br/>
 * 类描述：产品dao层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-10-27 下午05:15:50   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-10-27 下午05:15:50   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class ProductDaoImpl extends BaseDao implements IProductDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.business.product.dao.ProductDao#addProduct(com.hnzskj.business.product.bean.Product)
	 */
	public int addProduct(Product product) {
		String sql="INSERT INTO APP_BUSINESS_PRODUCT (PRODUCT_UUID,PRODUCT_NAME,PRODUCT_NUM,PRODUCT_SPECIFICATIONS,PRODUCT_BZ_PRICE,PRODUCT_PG_PRICE,PRODUCT_ZG_PRICE,PRODUCT_WEIGHT,DELETE_TYPE,PRODUCT_ORDERBY,PRODUCT_NOTE,CREATE_TIME,PRODUCT_STATE,PRODUCT_YEILD)" +
				   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[]{
				null!=product.getProductUuid()&&!"".equals(product.getProductUuid())?product.getProductUuid():UUID.randomUUID().toString(),
				product.getProductName(),
				product.getProductNum(),
				product.getProductSpecifications(),
				product.getProductBzPrice(),
				product.getProductPgPrice(),
				product.getProductZgPrice(),
				product.getProductWeight(),
				MfConstant.DELETE_TYPE_NO,
				product.getProductOrderBy(),
				product.getProductNote(),
				DataUtil.formateDate(new Date()),
				product.getProductState(),
				product.getProductYeild()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.product.dao.ProductDao#delProduct(com.hnzskj.business.product.bean.Product)
	 */
	public int delProduct(Product product) {
		String sql="UPDATE APP_BUSINESS_PRODUCT SET DELETE_TYPE = ? WHERE PRODUCT_UUID  = ? ";
		int result = update(sql, new Object[]{MfConstant.DELETE_TYPE_YES,product.getProductUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.product.dao.ProductDao#getProductByParam(java.lang.String)
	 */
	public Product getProductByParam(String sqlCondition) {
		String sql="SELECT PRODUCT_UUID AS PRODUCTUUID,PRODUCT_NAME AS PRODUCTNAME,PRODUCT_NUM AS PRODUCTNUM,PRODUCT_SPECIFICATIONS AS PRODUCTSPECIFICATIONS,PRODUCT_BZ_PRICE AS PRODUCTBZPRICE,PRODUCT_PG_PRICE AS PRODUCTPGPRICE,PRODUCT_ZG_PRICE AS PRODUCTZGPRICE,PRODUCT_WEIGHT AS PRODUCTWEIGHT,DELETE_TYPE AS DELETETYPE,PRODUCT_ORDERBY AS PRODUCTORDERBY,PRODUCT_NOTE AS PRODUCETNOTE ,CREATE_TIME AS CREATETIME,PRODUCT_STATE AS PRODUCTSTATE  FROM APP_BUSINESS_PRODUCT " +
				   "WHERE 1=1 "+sqlCondition;
		Product product = (Product)get(sql, Product.class, null);
		return product;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.product.dao.ProductDao#searchProductList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Product> searchProductList(PageBean<Product> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
			List<Product> empls = new ArrayList<Product>();// 封装查询结果集
			Integer totalRecords = 0;// 记录查询的总记录数
			String sql = "";// 查询的sql语句
			String countSql = "";// 查询总记录数的sql语句
			PageBean<Product> epage = new PageBean<Product>();// 临时变量，如果在page为null的情况下使用
			sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " ": sqlCondition;
			countSql = "select count(" + key + ") from " + tableName + " " + sqlCondition;
			totalRecords = (Integer) getSingleValue(countSql, queryParams);
			if (pageBean != null) {// 如果需要分页
				if (null != queryParams && queryParams.length > 0) {
					// 将数组的长度扩充为原来的2倍,并使得数组的第n个元素和第n+数组长度的元素的值相等,如此是为了分页查询时参数设置的需要
					Object[] newParamsArray = Arrays.copyOf(queryParams,
							queryParams.length * 2);
					for (int i = 0; i < queryParams.length; i++) {
						newParamsArray[queryParams.length + i] = queryParams[i];
					}
					queryParams = newParamsArray;
				}
				// 如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
				// 如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
				sql = "select top " + pageBean.getLimit() + " " + fields
						+ " from  " + tableName + " " + sqlCondition;
				if (" ".equals(sqlCondition) == true) {
					sql += " where " + key + " not in (select top "
							+ (pageBean.getPage() - 1) * pageBean.getLimit() + " "
							+ key + "  from " + tableName + " " + sqlCondition
							+ BaseDao.buildOrderBy(orderby) + ")"
							+ BaseDao.buildOrderBy(orderby);
				} else {
					sql += " and " + key + " not in (select top "
							+ (pageBean.getPage() - 1) * pageBean.getLimit() + " "
							+ key + " from " + tableName + sqlCondition
							+ BaseDao.buildOrderBy(orderby) + ")"
							+ BaseDao.buildOrderBy(orderby);
				}
				epage = pageBean;
				// 查询结果集
				empls = query(sql,Product.class, queryParams);
			} else {// 如果不需要分页
				sql = "select " + fields + " from " + tableName + " "
						+ sqlCondition +  BaseDao.buildOrderBy(orderby);
				// 查询结果集
				empls = query(sql, Product.class, queryParams);
			}
			// 设置总记录数
			epage.setList(empls);
			// 设置结果集
			epage.setTotalRecords(totalRecords);
			return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.product.dao.ProductDao#updProduct(com.hnzskj.business.product.bean.Product)
	 */
	public int updProduct(Product product) {
		String sql="UPDATE APP_BUSINESS_PRODUCT SET PRODUCT_NAME = ? ,PRODUCT_NUM = ? ,PRODUCT_SPECIFICATIONS = ? ,PRODUCT_BZ_PRICE = ? ,PRODUCT_PG_PRICE = ? ,PRODUCT_ZG_PRICE = ? ,PRODUCT_WEIGHT = ? ,PRODUCT_ORDERBY = ? ,PRODUCT_NOTE = ?,PRODUCT_STATE = ? ,PRODUCT_YEILD = ? " +
				  "WHERE PRODUCT_UUID = ? ";
		Object[]params = new Object[]{
				product.getProductName(),
				product.getProductNum(),
				product.getProductSpecifications(),
				product.getProductBzPrice(),
				product.getProductPgPrice(),
				product.getProductZgPrice(),
				product.getProductWeight(),
				product.getProductOrderBy(),
				product.getProductNote(),
				product.getProductState(),
				product.getProductYeild(),
				product.getProductUuid()
		};
		int reuslt = update(sql, params);
		return reuslt;
	}

}
