/*
 * @项目名称: crm
 * @文件名称: ModelServiceImpl.java
 * @日期: 2015-8-10 下午03:14:11  
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
import com.hnzskj.base.core.bean.Model;
import com.hnzskj.base.core.dao.IModelDao;
import com.hnzskj.base.core.service.IModelService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ModelServiceImpl.java   <br/>
 * 类描述：模块菜单service层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-10 下午03:14:11   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-10 下午03:14:11   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class ModelServiceImpl implements IModelService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IModelDao modelDao = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.ModelService#getModelList(java.lang.String)
	 */
	public List<Model> getModelList(String subsystemUuid) {
		List<Model> list = modelDao.getModelList(subsystemUuid);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IModelService#addModel(com.hnzskj.mainFrame.core.bean.Model)
	 */
	public boolean addModel(Model model) {
		int result = modelDao.addModel(model);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IModelService#delModel(java.lang.String)
	 */
	public boolean delModel(String modelUuid) {
		int result = modelDao.delModel(modelUuid);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IModelService#getModel(java.lang.String)
	 */
	public Model getModel(String modelUuid) {
		Model model = modelDao.getModel(modelUuid);
		return model;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IModelService#getModelList(com.hnzskj.common.bean.PageBean, com.hnzskj.mainFrame.core.bean.Model)
	 */
	public PageBean<Model> getModelList(PageBean<Model> pageBean, Model model) {
		String fields=" MODEL_UUID AS MODELUUID,MODEL_TITLE AS MODELTITLE,MODEL_ICON AS MODELICON,MODEL_LOGO AS MODELLOGO,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_STATE+"' AND ID = MODEL_STATE) AS MODELSTATETEXT,MODEL_STATE AS MODELSTATE,MODEL_SUBSYSTEM_UUID AS MODELSUBSYSTEMUUID,(SELECT SUB_TITLE FROM APP_SYSTEM_SUBSYSTEM WHERE SUB_UUID = MODEL_SUBSYSTEM_UUID) AS MODELSUBSYSTEMNAME,MODEL_NOTE AS MODELNOTE,CREATE_TIME AS CREATETIME,MODEL_ORDERBY AS MODELORDERBY,(SELECT COUNT(1) FROM APP_SYSTEM_POWER WHERE POWER_MODEL_UUID =MODEL_UUID ) AS CHILD ";
		StringBuffer sqlCondition = new StringBuffer("WHERE 1=1 ");
		if(null!=model){
			//模型标题
			if(null!=model.getModelTitle()&&!"".equals(model.getModelTitle())){
				sqlCondition.append(" AND MODEL_TITLE LIKE '%").append(model.getModelTitle()).append("%'");
			}
			//模型状态
			if(null!=model.getModelState()&&!"".equals(model.getModelState())){
				sqlCondition.append(" AND MODEL_STATE = '").append(model.getModelState()).append("'");
			}
			//所属子系统
			if(null!=model.getModelSubsystemUuid()&&!"".equals(model.getModelSubsystemUuid())){
				sqlCondition.append(" AND MODEL_SUBSYSTEM_UUID ='").append(model.getModelSubsystemUuid()).append("'");
			}
			//备注说明
			if(null!=model.getModelNote()&&!"".equals(model.getModelNote())){
				sqlCondition.append(" AND MODEL_NOTE LIKE '%").append(model.getModelNote()).append("%'");
			}
			//用户权限模块
			if(null!=model.getUser()&&!"".equals(model.getUser().getUserUuid())&&null!=model.getUser().getUserUuid()&&!MfConstant.SUPER_USER_ID.equals(model.getUser().getUserId())){
				sqlCondition.append(" AND MODEL_UUID IN(SELECT FOREIGNKEY FROM DBO.APP_SYSTEM_ROLE_AUTHORITY WHERE ROLEUUID IN (SELECT ROLE_UUID  FROM  APP_SYSTEM_ROLE_USER WHERE USER_UUID = '"+model.getUser().getUserUuid()+"') AND TYPE = '"+MfConstant.AUTHORITY_MODEL+"')");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("MODEL_ORDERBY", "ASC");
		pageBean = modelDao.getModelList(pageBean, fields, sqlCondition.toString(), null, orderby, " APP_SYSTEM_MODEL ", " MODEL_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IModelService#updModel(com.hnzskj.mainFrame.core.bean.Model)
	 */
	public boolean updModel(Model model) {
		int result = modelDao.updModel(model);
		return result>0?true:false;
	}
	
	
	

}
