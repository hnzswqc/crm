/*
 * @项目名称: crm
 * @文件名称: ResignedServiceImpl.java
 * @日期: 2015-12-2 下午08:43:38  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.resigned.service.impl;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.common.util.MfConstant;
import com.hnzskj.oa.resigned.bean.Resigned;
import com.hnzskj.oa.resigned.dao.IResignedDao;
import com.hnzskj.oa.resigned.service.IResignedService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ResignedServiceImpl.java   <br/>
 * 类描述：  辞职员工业务层实现类 <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-2 下午08:43:38   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-2 下午08:43:38   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class ResignedServiceImpl implements IResignedService {

	/**
	 * dao层接口
	 */
	@Autowired
	private IResignedDao resignedDao = null;
	
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedService#addResigned(com.hnzskj.oa.resigned.bean.Resigned)
	 */
	public boolean addResigned(Resigned resigned) {
		int result = resignedDao.addResigned(resigned);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedService#delResigned(com.hnzskj.oa.resigned.bean.Resigned)
	 */
	public boolean delResigned(Resigned resigned) {
		int result = resignedDao.delResigned(resigned);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedService#getResignedByParams(com.hnzskj.oa.resigned.bean.Resigned)
	 */
	public Resigned getResignedByParams(Resigned resigned) {
		Resigned r = resignedDao.getResignedByParams(getSqlCondition(resigned));
		return r;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedService#searchResignedList(com.hnzskj.common.bean.PageBean, com.hnzskj.oa.resigned.bean.Resigned)
	 */
	public PageBean<Resigned> searchResignedList(PageBean<Resigned> pageBean,
			Resigned resigned) {
		String fields="RES_UUID as RESUUID,USER_UUID as USERUUID,USER_NAME as USERNAME,USER_NUMBER as USERNUMBER,ROLE_NAME as ROLENAME,USER_GENDER as USERGENDERTEXT,USER_GENDER as USERGENDER,RES_APPLY_DATE as RESAPPLYDATE,RES_APPROVE_DATE as RESAPPROVEDATE,RES_TYPE as RESTYPE,RES_MONEY_TYPE as RESMONEYTYPE,USER_MOBILE as USERMOBILE,RES_RESON as RESRESON,RES_HANDOVER as RESHANDOVER,RES_TOOL_RETURN as RESTOOLRETURN,RES_OFFICE_GOODS as RESOFFICEGOODS,RES_CHECK_WORK as RESCHECKWORK,RES_FINANCIAL_LOAN as RESFINANCIALLOAN,RES_DEDUCT_WAGES_ITEM AS RESDEDUCTWAGESITEM,RES_REALY_WAGES as RESREALYWAGES,CREATE_TIME as CREATETIME,CREATE_USER_UUID as CREATEUSERUUID,CREATE_USER_NAME as CREATEUSERNAME,RES_NOTE AS RESNOTE,FILE_NAME AS FILENAME,RES_STATE as RESSTATE,FILE_TYPE AS FILETYPE,USER_BANK_CARD AS USERBANKCARD, " ;
				
		   if(StringUtils.isNotEmpty(resigned.getMonthNum())){
			   fields+="ISNULL((SELECT top 1 WC_CHECK_DAY FROM APP_SYSTEM_WORK_CHECK WC WHERE WC.USER_UUID = USER_UUID AND WC_MONTH = '"+resigned.getMonthNum()+"' ),RES_CHECK_WORK_NUM) as RESCHECKWORKNUM," +
			   		  "CASE WHEN  CONVERT(INT,SUBSTRING(CONVERT(VARCHAR(20),RES_APPLY_DATE),6,2)) = "+(Integer.valueOf(resigned.getMonthNum())+1)+" then RES_DEDUCT_WAGES " +
			   		  "ELSE 0 END AS RESDEDUCTWAGES,";
		   }else{
			   fields+="RES_CHECK_WORK_NUM AS RESCHECKWORKNUM,RES_DEDUCT_WAGES as RESDEDUCTWAGES,";
		   }
		   
			  
		if(null!=pageBean){
			fields+="(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_RESIGNED_STATE+"' AND ID = RES_STATE) AS RESSTATETEXT, "+
		      "(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_RESIGNED_TYPE+"' AND ID = RES_TYPE) AS RESTYPETEXT,"+
              "(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_RESIGNED_MONEY_TYPE+"' AND ID = RES_MONEY_TYPE) AS RESMONEYTYPETEXT, "+
			  "(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_BANK_CARD_STATE+"' AND ID = USER_BANK_CARD) AS USERBANKCARDTEXT," +
			  "(SELECT COUNT(1) FROM APP_SYSTEM_USER U WHERE U.USER_UUID = RES.USER_UUID) AS COUNT ";
		}else{
			fields+="(SELECT [TEXT] FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_RESIGNED_STATE+"' AND ID = RES_STATE) AS RESSTATETEXT, "+
		      "(SELECT [TEXT] FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_RESIGNED_TYPE+"' AND ID = RES_TYPE) AS RESTYPETEXT,"+
              "(SELECT [TEXT] FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_RESIGNED_MONEY_TYPE+"' AND ID = RES_MONEY_TYPE) AS RESMONEYTYPETEXT, "+
              "(SELECT [TEXT] FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_BANK_CARD_STATE+"' AND ID = USER_BANK_CARD) AS USERBANKCARDTEXT," +
              "(SELECT COUNT(1) FROM APP_SYSTEM_USER U WHERE U.USER_UUID = APP_SYSTEM_RESIGNED.USER_UUID) AS COUNT ";
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("CREATE_TIME", "DESC");
		pageBean = resignedDao.searchResignedList(pageBean, fields, getSqlCondition(resigned), null, orderby, " APP_SYSTEM_RESIGNED ", " RES_UUID ");
		return pageBean;
	}
	
	private String getSqlCondition(Resigned resigned){
		StringBuffer sb = new StringBuffer(" WHERE 1=1 ");
		if(null!=resigned){
			//主键
			if(null!=resigned.getResUuid()&&!"".equals(resigned.getResUuid())){
				sb.append(" AND RES_UUID = '").append(resigned.getResUuid()).append("'");
			}
			//辞职员工姓名
			if(null!=resigned.getUserName()&&!"".equals(resigned.getUserName())){
				sb.append(" AND USER_NAME LIKE '%").append(resigned.getUserName()).append("%'");
			}
			//员工工号
			if(null!=resigned.getUserNumber()&&!"".equals(resigned.getUserNumber())){
				sb.append(" AND USER_NUMBER ='").append(resigned.getUserNumber()).append("'");
			}
			//查询开始时间
			if(null!=resigned.getStartDate()&&!"".equals(resigned.getStartDate())){
				sb.append(" AND RES_APPROVE_DATE >='").append(resigned.getStartDate()).append("'");
			}
			//结束开始时间
			if(null!=resigned.getEndDate()&&!"".equals(resigned.getEndDate())){
				sb.append(" AND RES_APPROVE_DATE <='").append(resigned.getEndDate()).append("'");
			}
			//是否有银行卡
			if(null!=resigned.getUserBankCard()&&!"".equals(resigned.getUserBankCard())){
				sb.append(" AND USER_BANK_CARD = '").append(resigned.getUserBankCard()).append("'");
			}
			//结算状态
			if(null!=resigned.getResState()&&!"".equals(resigned.getResState())){
				if(resigned.getResState().equals(MfConstant.DIC_RESIGNED_STATE_NO)){
					sb.append(" AND RES_STATE in ('").append(resigned.getResState()).append("','"+MfConstant.DIC_RESIGNED_STATE_PART+"')");
				}else{
					sb.append(" AND RES_STATE = '").append(resigned.getResState()).append("'");
				}
			}
			//查询月份未结算的信息
			if(null!=resigned.getMonthNum()&&!"".equals(resigned.getMonthNum())){
				sb.append(" AND USER_UUID IN (SELECT USER_UUID FROM APP_SYSTEM_RESIGNED_MONTH WHERE MONTH_NUM = '"+resigned.getMonthNum()+"' AND MONTH_STATE = '"+MfConstant.DIC_RESIGNED_STATE_NO+"')");
			}
			//年度
			if(StringUtils.isNotEmpty(resigned.getYearNum())){
				sb.append(" AND RES_APPROVE_DATE >= '").append(resigned.getYearNum()+"-01-01").append("'");
				sb.append(" AND RES_APPROVE_DATE <='").append(resigned.getYearNum()+"-12-31").append("'");
			}
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedService#updResigned(com.hnzskj.oa.resigned.bean.Resigned)
	 */
	public boolean updResigned(Resigned resigned) {
		int result = resignedDao.updResigned(resigned);
		return result>0?true:false;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedService#updResigned(java.lang.String, java.lang.String)
	 */
	public boolean updResigned(String resUuid,String userUuid) {
		int result = resignedDao.updResigned(resUuid,userUuid);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedService#updUserState(com.hnzskj.oa.resigned.bean.Resigned)
	 */
	public boolean updUserState(Resigned resigned) {
		int result = resignedDao.updUserState(resigned.getUserUuid());
		return result>0?true:false;
	}
}
