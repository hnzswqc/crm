/*
 * @项目名称: crm
 * @文件名称: PurchaseDaoImpl.java
 * @日期: 2015-11-27 下午07:03:13  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.officegoods.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.DataUtil;
import com.hnzskj.oa.officegoods.bean.Purchase;
import com.hnzskj.oa.officegoods.dao.IPurchaseDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：PurchaseDaoImpl.java   <br/>
 * 类描述：物品采购dao层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-27 下午07:03:13   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-27 下午07:03:13   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class PurchaseDaoImpl extends BaseDao implements IPurchaseDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.dao.IPurchaseDao#addPurchase(com.hnzskj.oa.officegoods.bean.Purchase)
	 */
	public int addPurchase(Purchase purchase) {
		String sql="INSERT INTO APP_SYSTEM_PURCHASE(PURCHASE_UUID,PURCHASE_DATE,PURCHASE_TYPE,PURCHASE_NAME,PURCHASE_MODEL,PURCHASE_UNIT,PURCHASE_PRICE,PURCHASE_NUMBER,PURCHASE_TOTAL_PRICES,PURCHASE_ONHAND,PURCHASE_NOTE,CREATE_TIME,CREATE_USER_UUID,CREATE_USER_NAME,CREATE_USER_NUMBER,PURCHASE_PERSON_NAME)" +
				   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[]params = new Object[]{
				null!=purchase.getPurchaseUuid()&&!"".equals(purchase.getPurchaseUuid())?purchase.getPurchaseUuid():UUID.randomUUID().toString(),
				purchase.getPurchaseDate(),
				purchase.getPurchaseType(),
				purchase.getPurchaseName(),
				purchase.getPurchaseModel(),
				purchase.getPurchaseUnit(),
				purchase.getPurchasePrice(),
				purchase.getPurchaseNumber(),
				purchase.getPurchaseTotalPrices(),
				purchase.getPurchaseOnHand(),
				purchase.getPurchaseNote(),
				DataUtil.formateDate(new Date()),
				purchase.getCreateUserUuid(),
				purchase.getCreateUserName(),
				purchase.getCreateUserNumber(),
				purchase.getPurchasePersonName()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.dao.IPurchaseDao#delPurchase(com.hnzskj.oa.officegoods.bean.Purchase)
	 */
	public int delPurchase(Purchase purchase) {
		String sql="DELETE FROM APP_SYSTEM_PURCHASE WHERE PURCHASE_UUID = ? ";
		int result = update(sql, new Object[]{purchase.getPurchaseUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.dao.IPurchaseDao#getPurchaseList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Purchase> getPurchaseList(PageBean<Purchase> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Purchase> empls = new ArrayList<Purchase>();//封装查询结果集
		int totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		PageBean<Purchase> epage = new PageBean<Purchase>();//临时变量，如果在page为null的情况下使用
		
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count("+key+") from  "+tableName+" " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.parseInt(getSingleStringValue(countSql, queryParams).toString());
		if ( pageBean != null) {//如果需要分页
			int startNum=(pageBean.getPage() - 1) * pageBean.getLimit();
			//sql="select "+fields+" from "+tableName+" "+sqlCondition + buildOrderBy(orderby)
				//+" limit " +page.getMaxResult()+" offset " + startNum ;
			sql="SELECT SUB.* FROM ( select "+fields+" row_number() over ("+buildOrderBy(orderby)+") rnk from  "+tableName
				+" RES "+sqlCondition+" ) SUB where  SUB.rnk > "+startNum+" AND SUB.rnk <= "+pageBean.getPage()*pageBean.getLimit();
			epage = pageBean;
			//查询结果集
			empls = query(sql, Purchase.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from "+tableName+" " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, Purchase.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.dao.IPurchaseDao#updPurchase(com.hnzskj.oa.officegoods.bean.Purchase)
	 */
	public int updPurchase(Purchase purchase) {
		String sql="UPDATE APP_SYSTEM_PURCHASE SET PURCHASE_DATE = ?,PURCHASE_TYPE = ?,PURCHASE_NAME = ?,PURCHASE_MODEL = ?,PURCHASE_UNIT = ?,PURCHASE_PRICE = ?,PURCHASE_NUMBER = ?,PURCHASE_TOTAL_PRICES = ?,PURCHASE_ONHAND = ?,PURCHASE_NOTE = ? ,PURCHASE_PERSON_NAME = ? " +
				   "WHERE PURCHASE_UUID = ? ";
		Object[]params = new Object[]{
				purchase.getPurchaseDate(),
				purchase.getPurchaseType(),
				purchase.getPurchaseName(),
				purchase.getPurchaseModel(),
				purchase.getPurchaseUnit(),
				purchase.getPurchasePrice(),
				purchase.getPurchaseNumber(),
				purchase.getPurchaseTotalPrices(),
				purchase.getPurchaseOnHand(),
				purchase.getPurchaseNote(),
				purchase.getPurchasePersonName(),
				purchase.getPurchaseUuid()
		};
		int result = update(sql, params);
		return result;
	}

}
