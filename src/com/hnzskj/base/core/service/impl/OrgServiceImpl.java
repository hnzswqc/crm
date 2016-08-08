/*
 * @项目名称: crm
 * @文件名称: OrgServiceImpl.java
 * @日期: 2015-8-24 上午09:33:48  
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
import com.hnzskj.base.core.bean.Org;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.base.core.dao.IOrgDao;
import com.hnzskj.base.core.service.IOrgService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：OrgServiceImpl.java   <br/>
 * 类描述：组织机构业务层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-24 上午09:33:48   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-24 上午09:33:48   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class OrgServiceImpl implements IOrgService {
	
	/**
	 * dao层注入
	 */
	@Autowired
	private IOrgDao orgDao = null;
	

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOrgService#addOrg(com.hnzskj.mainFrame.core.bean.Org)
	 */
	public boolean addOrg(Org org) {
		int result = orgDao.addOrg(org);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOrgService#delOrg(com.hnzskj.mainFrame.core.bean.Org)
	 */
	public boolean delOrg(Org org) {
		int result = orgDao.delOrg(org);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOrgService#getOrg(com.hnzskj.mainFrame.core.bean.Org)
	 */
	public Org getOrg(Org org) {
		org = orgDao.getOrg(org);
		return org;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOrgService#getOrgList(com.hnzskj.common.bean.PageBean, com.hnzskj.mainFrame.core.bean.Org)
	 */
	public PageBean<Org> getOrgList(PageBean<Org> pageBean, Org org) {
		String fields=" ORG_UUID AS ORGUUID,ORG_NAME AS ORGNAME,ORG_CODE AS ORGCODE,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_ORG_GRADE_LEVEL+"' AND ID = ORG_GRADE_UUID)  AS ORGGRADETEXT,ORG_GRADE_UUID AS ORGGRADEUUID,ORG_PARENT_UUID AS ORGPARENTUUID,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_ORG_STATE+"' AND ID = ORG_STATE) AS ORGSTATETEXT,ORG_STATE AS ORGSTATE,ORG_ORDERBY AS ORGORDERBY,ORG_NOTE AS ORGNOTE,CREATE_TIME AS CREATETIME,ORG_ICON AS ORGICON,(SELECT ORG_NAME FROM APP_SYSTEM_ORG WHERE ORG_UUID = SP.ORG_PARENT_UUID) AS ORGPARENTNAME,(SELECT COUNT(1) FROM APP_SYSTEM_ORG  WHERE ORG_PARENT_UUID = SP.ORG_UUID) AS CHILD,START_NUMBER AS STARTNUMBER,END_NUMBER AS ENDNUMBER  ";
		StringBuffer sb = new StringBuffer(" WHERE 1=1 ");
		if(null!=org){
			//父级uuid
			if(null!=org.getOrgParentUuid()&&!"".equals(org.getOrgParentUuid())){
				sb.append(" AND ORG_PARENT_UUID = '").append(org.getOrgParentUuid()).append("' ");
			}
			//状态
			if(null!=org.getOrgState()&&!"".equals(org.getOrgState())){
				sb.append(" AND ORG_STATE = ").append(org.getOrgState());
			}
			//组织机构code
			if(null!=org.getOrgCode()&&!"".equals(org.getOrgCode())){
				sb.append(" AND ORG_CODE LIKE '%").append(org.getOrgCode()).append("%'");
			}
			//组织机构名称
			if(null!=org.getOrgName()&&!"".equals(org.getOrgName())){
				sb.append(" AND ORG_NAME LIKE '%").append(org.getOrgName()).append("%'");
			}
			//用户uuid
			if(null!=org.getUserUuid()&&!"".equals(org.getUserUuid())){
				sb.append(" AND ORG_UUID IN (").append("SELECT ORG_UUID FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = '"+org.getUserUuid()+"'").append(")");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("ORG_ORDERBY", "ASC");
		pageBean = orgDao.getOrgList(pageBean, fields, sb.toString(), null, orderby, " APP_SYSTEM_ORG SP ", " ORG_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOrgService#updOrg(com.hnzskj.mainFrame.core.bean.Org)
	 */
	public boolean updOrg(Org org) {
		int result = orgDao.updOrg(org);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IOrgService#getTreeNodeList(java.lang.String)
	 */
	public List<TreeNode> getTreeNodeList(String orgParentUuid) {
		List<TreeNode> list = orgDao.getTreeNodeList(orgParentUuid);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IOrgService#getRoleList(com.hnzskj.base.core.bean.Org)
	 */
	public List<Role> getRoleList(Org org) {
		List<Role> list = null;
		if(null!=org){
			list = orgDao.getRoleList(org.getOrgUuid());
		}else{
			org = new Org();
			list = orgDao.getRoleList(org.getOrgUuid());
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IOrgService#saveOrgRole(java.lang.String, java.lang.String)
	 */
	public boolean saveOrgRole(String roleUuids, String orgUuid) {
		if(null!=roleUuids&&!"".equals(roleUuids)){
			String[]roleUuid = null;
			if(roleUuids.indexOf(",")>-1){
				roleUuid = roleUuids.split(",");
			}else{
				roleUuid = new String[]{roleUuids};
			}
			int result = orgDao.saveOrgRole(roleUuid,orgUuid);
			return result>0?true:false;
		}
		return false;
	}
	
	
	
}
