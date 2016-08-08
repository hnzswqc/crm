/*
 * @项目名称: crm
 * @文件名称: OfficeGoodsServiceImpl.java
 * @日期: 2015-11-28 下午08:41:18  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.officegoods.service.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.common.util.MfConstant;
import com.hnzskj.oa.officegoods.bean.OfficeGoods;
import com.hnzskj.oa.officegoods.dao.IOfficeGoodsDao;
import com.hnzskj.oa.officegoods.service.IOfficeGoodsService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：OfficeGoodsServiceImpl.java   <br/>
 * 类描述： 办公物品领导dao层实现类  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-28 下午08:41:18   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-28 下午08:41:18   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class OfficeGoodsServiceImpl implements IOfficeGoodsService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IOfficeGoodsDao officeGoodsDao = null;
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.service.IOfficeGoodsService#addOfficeGoods(com.hnzskj.oa.officegoods.bean.OfficeGoods)
	 */
	public boolean addOfficeGoods(OfficeGoods officeGoods) {
		int result = officeGoodsDao.addOfficeGoods(officeGoods);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.service.IOfficeGoodsService#delOfficeGoods(com.hnzskj.oa.officegoods.bean.OfficeGoods)
	 */
	public boolean delOfficeGoods(OfficeGoods officeGoods) {
		int result = officeGoodsDao.delOfficeGoods(officeGoods);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.service.IOfficeGoodsService#getOfficeGoodsByParam(com.hnzskj.oa.officegoods.bean.OfficeGoods)
	 */
	public OfficeGoods getOfficeGoodsByParam(OfficeGoods officeGoods) {
		OfficeGoods ogs = officeGoodsDao.getOfficeGoodsByParam(getSqlCondition(officeGoods));
		return ogs;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.service.IOfficeGoodsService#searchOfficeGoodsList(com.hnzskj.common.bean.PageBean, com.hnzskj.oa.officegoods.bean.OfficeGoods)
	 */
	public PageBean<OfficeGoods> searchOfficeGoodsList(
			PageBean<OfficeGoods> pageBean, OfficeGoods officeGoods) {
		String fields=" OGS_UUID as OGSUUID,OGS_USER_NUMBER as OGSUSERNUMBER,OGS_USER_NAME as OGSUSERNAME,OGS_USER_UUID as OGSUSERUUID,OGS_DATE as OGSDATE,OGS_TYPE as OGSTYPE,OGS_NUMBER as OGSNUMBER,OGS_STATE as OGSSTATE,OGS_BACK_DATE as OGSBACKDATE,OGS_NOTE as OGSNOTE,OGS_FILE_NAME as OGSFILENAME,OGS_FILE_SIZE as OGSFILESIZE,OGS_FILE_TYPE as OGSFILETYPE,CREATE_TIME as CREATETIME,CREATE_USER_UUID as CREATEUSERUUID,CREATE_USER_NAME as CREATEUSERNAME,CREATE_USER_NUMBER as CREATEUSERNUMBER," +
				"(SELECT PURCHASE_NAME AS OGSTYPETEXT FROM APP_SYSTEM_PURCHASE WHERE PURCHASE_UUID =OGS_TYPE) AS OGSTYPETEXT," +
				"((SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_OFFICEGOODS_STATE+"' AND ID = convert(varchar(100),OGS_STATE)) ) AS OGSSTATETEXT,";
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("create_time", "desc");
		pageBean = officeGoodsDao.searchOfficeGoodsList(pageBean, fields, getSqlCondition(officeGoods), null, orderby, "APP_SYSTEM_OFFICE_GOODS", "OGS_UUID");
		return pageBean;
	}
	
	/**
	 * 
	 * 方法描述：获取查询条件<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-28 下午08:51:24<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private String getSqlCondition(OfficeGoods officeGoods){
		StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=officeGoods){
			//主键
			if(null!=officeGoods.getOgsUuid()&&!"".equals(officeGoods.getOgsUuid())){
				sb.append(" AND OGS_UUID = '").append(officeGoods.getOgsUuid()).append("'");
			}
			//领取类别
			if(null!=officeGoods.getOgsType()&&!"".equals(officeGoods.getOgsType())){
				sb.append(" AND OGS_TYPE ='").append(officeGoods.getOgsType()).append("'");
			}
			//员工工号
			if(null!=officeGoods.getOgsUserNumber()&&!"".equals(officeGoods.getOgsUserNumber())){
				sb.append(" AND OGS_USER_NUMBER = '").append(officeGoods.getOgsUserNumber()).append("'");
			}
			//领取时间
			if(null!=officeGoods.getOgsDate()&&!"".equals(officeGoods.getOgsDate())){
				sb.append(" AND OGS_DATE >='").append(officeGoods.getOgsDate()).append("'");
			}
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.service.IOfficeGoodsService#updOfficeGoods(com.hnzskj.oa.officegoods.bean.OfficeGoods)
	 */
	public boolean updOfficeGoods(OfficeGoods officeGoods) {
		int result = officeGoodsDao.updOfficeGoods(officeGoods);
		return result>=0?true:false;
	}

}
