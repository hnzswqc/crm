/*
 * @项目名称: crm
 * @文件名称: ResignedMonthServiceImpl.java
 * @日期: 2015-12-6 下午01:51:40  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.resigned.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.oa.resigned.bean.Resigned;
import com.hnzskj.oa.resigned.bean.ResignedMonth;
import com.hnzskj.oa.resigned.dao.IResignedMonthDao;
import com.hnzskj.oa.resigned.service.IResignedMonthService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ResignedMonthServiceImpl.java   <br/>
 * 类描述：计发月份业务层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-6 下午01:51:40   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-6 下午01:51:40   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class ResignedMonthServiceImpl implements IResignedMonthService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IResignedMonthDao resignedMonthDao = null;
	
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedMonthService#addResignedMonth(com.hnzskj.oa.resigned.bean.Resigned, java.lang.String)
	 */
	public boolean addResignedMonth(Resigned resigned, String months) {
		if(null!=months&&!"".equals(months)){
			String[]month = null;
			if(months.indexOf(",")>-1){
				month = months.split(",");
			}else{
				month = new String[]{months};
			}
			int result = resignedMonthDao.addResignedMonth(resigned, month);
			return result>0?true:false;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedMonthService#delResignedMonth(com.hnzskj.oa.resigned.bean.ResignedMonth)
	 */
	public boolean delResignedMonth(ResignedMonth resignedMonth) {
		int result = resignedMonthDao.delResignedMonth(getSqlCondition(resignedMonth));
		return result>=0?true:false;
	}

	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedMonthService#searchResignedMonthList(com.hnzskj.oa.resigned.bean.ResignedMonth)
	 */
	public List<ResignedMonth> searchResignedMonthList(
			ResignedMonth resignedMonth) {
		List<ResignedMonth> list = resignedMonthDao.searchResignedMonthList(getSqlCondition(resignedMonth));
		return list;
	}
	
	/**
	 * 
	 * 方法描述：根据条件查询<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午02:01:38<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private String getSqlCondition(ResignedMonth resignedMonth){
		StringBuffer sb = new StringBuffer(" WHERE 1=1 ");
		if(null!=resignedMonth){
			//根据用户查询
			if(null!=resignedMonth.getUserUuid()&&!"".equals(resignedMonth.getUserUuid())){
				sb.append(" AND USER_UUID = '").append(resignedMonth.getUserUuid()).append("'");
			}
			//结算状态
			if(null!=resignedMonth.getMonthState()&&!"".equals(resignedMonth.getMonthState())){
				sb.append(" AND MONTH_STATE = '").append(resignedMonth.getMonthState()).append("'");
			}
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedMonthService#updResignedMonth(java.lang.String, java.lang.String)
	 */
	public boolean updResignedMonth(String monthUuids,String state) {
		if(null!=monthUuids&&!"".equals(monthUuids)){
			if(monthUuids.indexOf(",")>-1){
				monthUuids = "'"+monthUuids.replace(",", "','")+"'";
			}else{
				monthUuids ="'"+monthUuids+"'";
			}
			int result = resignedMonthDao.updResignedMonth(monthUuids,state);
			return result>0?true:false;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.service.IResignedMonthService#updateResigned(java.lang.String)
	 */
	public boolean updateResigned(String userUuid) {
		int result = resignedMonthDao.updateResigned(userUuid);
		return result>0?true:false;
	}
	

}
