/*
 * @项目名称: crm
 * @文件名称: PurchaseServiceImpl.java
 * @日期: 2015-11-27 下午07:19:11  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.officegoods.service.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.common.util.MfConstant;
import com.hnzskj.oa.officegoods.bean.Purchase;
import com.hnzskj.oa.officegoods.dao.IPurchaseDao;
import com.hnzskj.oa.officegoods.service.IPurchaseService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：PurchaseServiceImpl.java   <br/>
 * 类描述：办公物品采购业务层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-27 下午07:19:11   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-27 下午07:19:11   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class PurchaseServiceImpl implements IPurchaseService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IPurchaseDao purchaseDao = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.service.IPurchaseService#addPurchase(com.hnzskj.oa.officegoods.bean.Purchase)
	 */
	public boolean addPurchase(Purchase purchase) {
		int result = purchaseDao.addPurchase(purchase);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.service.IPurchaseService#delPurchase(com.hnzskj.oa.officegoods.bean.Purchase)
	 */
	public boolean delPurchase(Purchase purchase) {
		int result = purchaseDao.delPurchase(purchase);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.service.IPurchaseService#getPurchaseList(com.hnzskj.common.bean.PageBean, com.hnzskj.oa.officegoods.bean.Purchase)
	 */
	public PageBean<Purchase> getPurchaseList(PageBean<Purchase> pageBean,
			Purchase purchase) {
		String fields=" PURCHASE_UUID as PURCHASEUUID,PURCHASE_DATE as PURCHASEDATE,PURCHASE_TYPE as PURCHASETYPE,PURCHASE_NAME as PURCHASENAME,PURCHASE_MODEL as PURCHASEMODEL,PURCHASE_UNIT as PURCHASEUNIT,PURCHASE_PRICE as PURCHASEPRICE,PURCHASE_NUMBER as PURCHASENUMBER,PURCHASE_TOTAL_PRICES as PURCHASETOTALPRICES,PURCHASE_ONHAND as PURCHASEONHAND,PURCHASE_NOTE as PURCHASENOTE,CREATE_TIME as CREATETIME,CREATE_USER_UUID as CREATEUSERUUID,CREATE_USER_NAME as CREATEUSERNAME,CREATE_USER_NUMBER as CREATEUSERNUMBER ,PURCHASE_PERSON_NAME AS PURCHASEPERSONNAME," +
				   "((SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_PURCHASE_TYPE+"' AND ID = PURCHASE_TYPE) ) AS PURCHASETYPETEXT,";
		StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=purchase){
			//主键
			if(null!=purchase.getPurchaseUuid()&&!"".equals(purchase.getPurchaseUuid())){
				sb.append(" AND PURCHASE_UUID = '").append(purchase.getPurchaseUuid()).append("'");
			}
			//物品名称
			if(null!=purchase.getPurchaseName()&&!"".equals(purchase.getPurchaseName())){
				sb.append(" AND PURCHASE_NAME LIKE '%").append(purchase.getPurchaseName()).append("%'");
			}
			//物品类别
			if(null!=purchase.getPurchaseType()&&!"".equals(purchase.getPurchaseType())){
				sb.append(" AND PURCHASE_TYPE = '").append(purchase.getPurchaseType()).append("'");
			}
			//采购人员
			if(null!=purchase.getPurchasePersonName()&&!"".equals(purchase.getPurchasePersonName())){
				sb.append(" AND PURCHASE_PERSON_NAME LIKE '%").append(purchase.getPurchasePersonName()).append("%'");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put(" CREATE_TIME ", "DESC");
		pageBean = purchaseDao.getPurchaseList(pageBean, fields, sb.toString(), null, orderby, " APP_SYSTEM_PURCHASE ", " PURCHASE_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.service.IPurchaseService#updPurchase(com.hnzskj.oa.officegoods.bean.Purchase)
	 */
	public boolean updPurchase(Purchase purchase) {
		int result = purchaseDao.updPurchase(purchase);
		return result>0?true:false;
	}

}
