/*
 * @项目名称: crm
 * @文件名称: SubsystemService.java
 * @日期: 2015-9-2 下午01:53:57  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Subsystem;
import com.hnzskj.base.core.dao.ISubsystemDao;
import com.hnzskj.base.core.service.ISubsystemService;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：SubsystemService.java   <br/>
 * 类描述：子系统业务层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-2 下午01:53:57   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-2 下午01:53:57   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class SubsystemService implements ISubsystemService {

	/**
	 * dao注入
	 */
	@Autowired
	private ISubsystemDao subsystemDao = null;
	
	
	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.ISubsystemService#addSubsystem(com.hnzskj.base.core.bean.Subsystem)
	 */
	public boolean addSubsystem(Subsystem subsystem) {
		int reuslt = subsystemDao.addSubsystem(subsystem);
		return reuslt>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.ISubsystemService#delSubsystem(com.hnzskj.base.core.bean.Subsystem)
	 */
	public boolean delSubsystem(Subsystem subsystem) {
		int reuslt = subsystemDao.delSubsystem(subsystem);
		return reuslt>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.ISubsystemService#getSubsystemList(com.hnzskj.common.bean.PageBean, com.hnzskj.base.core.bean.Subsystem)
	 */
	public PageBean<Subsystem> getSubsystemList(PageBean<Subsystem> pageBean,
			Subsystem subsystem) {
		String fields=" SUB_UUID AS SUBUUID,SUB_KEY AS SUBKEY,SUB_TITLE AS SUBTITLE,SUB_STATE AS SUBSTATE,SUB_ICON AS SUBICON ,SUB_LOGO AS SUBLOGO,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_SUB_STATE+"' AND ID = SUB_STATE) AS SUBSTATETEXT,SUB_ORDERBY AS SUBORDERBY,SUB_NOTE AS SUBNOTE,CREATE_TIME AS CREATETIME,(select COUNT(1) FROM APP_SYSTEM_MODEL WHERE MODEL_SUBSYSTEM_UUID = SUB_UUID) AS CHILD ";
		StringBuffer sb = new StringBuffer(" WHERE 1=1 ");
		if(null!=subsystem){
			//主键uuid
			if(null!=subsystem.getSubUuid()&&!"".equals(subsystem.getSubUuid())){
				sb.append(" AND SUB_UUID = '").append(subsystem.getSubUuid()).append("' ");
			}
			//唯一标识
			if(null!=subsystem.getSubKey()&&!"".equals(subsystem.getSubKey())){
				sb.append(" AND SUB_KEY = '").append(subsystem.getSubKey()).append("'");
			}
			//状态
			if(null!=subsystem.getSubTitle()&&!"".equals(subsystem.getSubTitle())){
				sb.append(" AND SUB_TITLE LIKE '%").append(subsystem.getSubTitle()).append("%'");
			}
			//标题
			if(null!=subsystem.getSubState()&&!"".equals(subsystem.getSubState())){
				sb.append(" AND SUB_STATE = '").append(subsystem.getSubState()).append("'");
			}
			//用户权限模块
			if(null!=subsystem.getUser()&&!"".equals(subsystem.getUser().getUserUuid())&&null!=subsystem.getUser().getUserUuid()&&!MfConstant.SUPER_USER_ID.equals(subsystem.getUser().getUserId())){
				sb.append(" AND SUB_UUID IN(SELECT FOREIGNKEY FROM DBO.APP_SYSTEM_ROLE_AUTHORITY WHERE ROLEUUID IN (SELECT ROLE_UUID  FROM  APP_SYSTEM_ROLE_USER WHERE USER_UUID = '"+subsystem.getUser().getUserUuid()+"') AND TYPE = '"+MfConstant.AUTHORITY_SUBSYSTEM+"')");
			}
				
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("SUB_ORDERBY", "ASC");
		pageBean = subsystemDao.getSubsystemList(pageBean, fields, sb.toString(), null, orderby, " APP_SYSTEM_SUBSYSTEM", " SUB_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.ISubsystemService#updSubsystem(com.hnzskj.base.core.bean.Subsystem)
	 */
	public boolean updSubsystem(Subsystem subsystem) {
		int reuslt = subsystemDao.updSubsystem(subsystem);
		return reuslt>0?true:false;
	}

}
