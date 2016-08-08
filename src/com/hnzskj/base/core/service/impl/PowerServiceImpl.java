/*
 * @项目名称: crm
 * @文件名称: PowerServiceImpl.java
 * @日期: 2015-8-11 上午11:40:30  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Power;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.base.core.dao.IPowerDao;
import com.hnzskj.base.core.service.IPowerService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：PowerServiceImpl.java   <br/>
 * 类描述：功能权限业务层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-11 上午11:40:30   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-11 上午11:40:30   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class PowerServiceImpl implements IPowerService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IPowerDao powerDao = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IPowerService#addPower(com.hnzskj.mainFrame.core.bean.Power)
	 */
	public boolean addPower(Power power) {
		int result = powerDao.addPower(power);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IPowerService#delPower(java.lang.String)
	 */
	public boolean delPower(String powerUuid) {
		int result = powerDao.delPower(powerUuid);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IPowerService#getPower(java.lang.String)
	 */
	public Power getPower(String powerUuid) {
		Power power = powerDao.getPower(powerUuid);
		return power;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IPowerService#getPowerList(com.hnzskj.common.bean.Page, com.hnzskj.mainFrame.core.bean.Power)
	 */
	public PageBean<Power> getPowerList(PageBean<Power> pageBean, Power power) {
		String fields=" POWER_UUID AS POWERUUID,POWER_UUID AS ID,POWER_NAME AS POWERNAME,POWER_NAME AS TEXT,POWER_ICON AS POWERICON,POWER_LOGO AS POWERLOGO,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_POWER_STATE+"' AND ID = POWER_STATE) AS POWERSTATETEXT,POWER_STATE as POWERSTATE,POWER_URL AS POWERURL,POWER_PARENT_UUID AS POWERPARENTUUID,POWER_MODEL_UUID AS POWERMODELUUID,POWER_NOTE AS POWERNOTE,POWER_ORDERBY AS POWERORDERBY,CREATE_TIME AS CREATETIME,(SELECT POWER_NAME FROM APP_SYSTEM_POWER P WHERE P.POWER_UUID = SP.POWER_PARENT_UUID) AS POWERPARENTNAME,(SELECT MODEL_TITLE FROM APP_SYSTEM_MODEL M WHERE M.MODEL_UUID = SP.POWER_MODEL_UUID) AS POWERMODELNAME,(SELECT COUNT(1) FROM APP_SYSTEM_POWER M WHERE M.POWER_PARENT_UUID = SP.POWER_UUID) AS NUM ";
		StringBuffer sqlCondition = new StringBuffer(" WHERE 1=1 ");
		if(null!=power){
			//所属父级id
			if(null!=power.getPowerParentUuid()&&!"".equals(power.getPowerParentUuid())){
				sqlCondition.append(" AND POWER_PARENT_UUID = '").append(power.getPowerParentUuid()).append("' ");
			}
			//功能名称
			if(null!=power.getPowerName()&&!"".equals(power.getPowerName())){
				sqlCondition.append(" AND POWER_NAME LIKE '%").append(power.getPowerName()).append("%' ");
			}
			//所示模块
			if(null!=power.getPowerModelUuid()&&!"".equals(power.getPowerModelUuid())){
				sqlCondition.append(" AND POWER_MODEL_UUID ='").append(power.getPowerModelUuid()).append("'");
			}
			//状态
			if(null!=power.getPowerState()&&!"".equals(power.getPowerState())){
				sqlCondition.append(" AND POWER_STATE ='").append(power.getPowerState()).append("'");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("POWER_ORDERBY", "ASC");
		pageBean = powerDao.getPowerList(pageBean, fields, sqlCondition.toString(), null, orderby, " APP_SYSTEM_POWER SP ", " POWER_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IPowerService#updPower(com.hnzskj.mainFrame.core.bean.Power)
	 */
	public boolean updPower(Power power) {
		int result = powerDao.updPower(power);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IPowerService#getPowerList(java.lang.String,java.lang.String,java.lang.String)
	 */
	public List<TreeNode> getPowerList(String modelUuid,String powerParentUuid,String userUuid) {
		List<TreeNode> list = powerDao.getPowerList(modelUuid,powerParentUuid,userUuid);
		return list;
	}
	
}
