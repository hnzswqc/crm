/*
 * @项目名称: crm
 * @文件名称: OperateServiceImpl.java
 * @日期: 2015-8-28 下午04:01:43  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.controller.BaseController;
import com.hnzskj.common.util.Constant;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Operate;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.dao.IOperateDao;
import com.hnzskj.base.core.service.IOperateService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：OperateServiceImpl.java   <br/>
 * 类描述： 功能操作业务层实现类  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 下午04:01:43   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 下午04:01:43   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class OperateServiceImpl implements IOperateService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IOperateDao operateDao = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOperateService#addOperate(com.hnzskj.mainFrame.core.bean.Operate)
	 */
	public boolean addOperate(Operate operate) {
		int result = operateDao.addOperate(operate);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOperateService#delOperate(com.hnzskj.mainFrame.core.bean.Operate)
	 */
	public boolean delOperate(Operate operate) {
		int result = operateDao.delOperate(operate);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOperateService#getOperateByKey(com.hnzskj.mainFrame.core.bean.Operate)
	 */
	public Operate getOperateByKey(Operate operate) {
		operate = operateDao.getOperateByKey(operate);
		return operate;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOperateService#getOperateList(com.hnzskj.common.bean.PageBean, com.hnzskj.mainFrame.core.bean.Operate)
	 */
	public PageBean<Operate> getOperateList(PageBean<Operate> pageBean,
			Operate operate) {
		String fields=" OPERATE_UUID AS OPERATEUUID,OPERATE_KEY AS OPERATEKEY,OPERATE_NAME AS OPERATENAME,OPERATE_ICON AS OPERATEICON,OPERATE_NOTE AS OPERATENOTE,OPERATE_ORDERBY AS OPERATEORDERBY,POWER_UUID AS POWERUUID,CREATE_TIME AS CREATETIME ";
		StringBuffer sb = new StringBuffer(" WHERE 1=1 ");
		if(null!=operate){
			//所属功能连接
			if(null!=operate.getPowerUuid()&&!"".equals(operate.getPowerUuid())){
				sb.append(" AND POWER_UUID = '").append(operate.getPowerUuid()).append("'");
			}
			//唯一标识key
			if(null!=operate.getOperateKey()&&!"".equals(operate.getOperateKey())){
				sb.append(" AND OPERATE_KEY ='").append(operate.getOperateKey()).append("'");
			}
			//名称
			if(null!=operate.getOperateName()&&!"".equals(operate.getOperateName())){
				sb.append(" AND OPERATE_NAME LIKE '%").append(operate.getOperateName()).append("%'");
			}
			//用户权限
			if(null!=operate.getUser()&&!"".equals(operate.getUser().getUserUuid())){
				if(!Constant.SUPER_USER_ID.equals(operate.getUser().getUserId())){
					sb.append(" AND OPERATE_UUID IN (SELECT FOREIGNKEY FROM DBO.APP_SYSTEM_ROLE_AUTHORITY WHERE ROLEUUID IN (SELECT ROLE_UUID  FROM  APP_SYSTEM_ROLE_USER WHERE USER_UUID = '"+operate.getUser().getUserUuid()+"') AND TYPE = '"+MfConstant.AUTHORITY_OPERATE+"')");
				}
			}
		}
		LinkedHashMap<String, String> orderby  = new LinkedHashMap<String, String>();
		orderby.put("OPERATE_ORDERBY", "ASC");
		pageBean = operateDao.getOperateList(pageBean, fields, sb.toString(), null, orderby, " APP_SYSTEM_OPERATE ", " OPERATE_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOperateService#updOperate(com.hnzskj.mainFrame.core.bean.Operate)
	 */
	public boolean updOperate(Operate operate) {
		int result = operateDao.updOperate(operate);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOperateService#getOperateList(java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	public String getOperateList(String id, HttpServletRequest request) {
		User user =new BaseController().getUserFromSession(request);
		Operate operate = new Operate();
		operate.setUser(user);
		operate.setPowerUuid(id);
		PageBean<Operate> pageBean = getOperateList(null, operate);
		StringBuffer sb = new StringBuffer(200);
		if(null!=pageBean&&pageBean.getList().size()>0){
			for (Operate op : pageBean.getList()) {
				sb.append(op.getOperateKey()).append("|");
			}
		}
		return sb.toString();
	}
	
	

}
