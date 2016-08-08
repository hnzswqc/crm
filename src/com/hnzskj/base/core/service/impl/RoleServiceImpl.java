/*
 * @项目名称: crm
 * @文件名称: RoleServiceImpl.java
 * @日期: 2015-8-28 上午10:54:10  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.dao.IRoleDao;
import com.hnzskj.base.core.service.IRoleService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：RoleServiceImpl.java   <br/>
 * 类描述： 业务层接口  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-28 上午10:54:10   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-28 上午10:54:10   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class RoleServiceImpl implements IRoleService{

	/**
	 * dao层注入
	 */
	@Autowired
	private IRoleDao roleDao = null;
	
	
	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IRoleService#addRole(com.hnzskj.mainFrame.core.bean.Role)
	 */
	public boolean addRole(Role role) {
		int result = roleDao.addRole(role);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IRoleService#delRole(com.hnzskj.mainFrame.core.bean.Role)
	 */
	public boolean delRole(Role role) {
		int result = roleDao.delRole(role);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IRoleService#getRoleKey(com.hnzskj.mainFrame.core.bean.Role)
	 */
	public Role getRoleKey(Role role) {
		role = roleDao.getRoleKey(role);
		return role;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IRoleService#getRoleList(com.hnzskj.common.bean.PageBean, com.hnzskj.mainFrame.core.bean.Role)
	 */
	public PageBean<Role> getRoleList(PageBean<Role> pageBean, Role role) {
		String fields=" ROLE_UUID AS ROLEUUID,ROLE_KEY AS ROLEKEY,ROLE_NAME AS ROLENAME,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_ROLE_STATE+"' AND ID = ROLE_STATE) AS ROLESTATETEXT,ROLE_STATE AS ROLESTATE,ROLE_NOTE AS ROLENOTE,ROLE_ORDERBY AS ROLEORDERBY,CREATE_TIME AS CREATETIME ";
		StringBuffer sb = new StringBuffer(" WHERE 1=1 ");
		if(null!=role){
			//角色key
			if(null!=role.getRoleKey()&&!"".equals(role.getRoleKey())){
				sb.append(" AND ROLE_KEY = '").append(role.getRoleKey()).append("' ");
			}
			//角色状态
			if(null!=role.getRoleState()&&!"".equals(role.getRoleState())){
				sb.append(" AND ROLE_STATE = ").append(role.getRoleState()).append("' ");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("ROLE_ORDERBY", "ASC");
		pageBean = roleDao.getRoleList(pageBean, fields, sb.toString(), null, orderby, "APP_SYSTEM_ROLE", "ROLE_UUID");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IRoleService#updRole(com.hnzskj.mainFrame.core.bean.Role)
	 */
	public boolean updRole(Role role) {
		int result = roleDao.updRole(role);
		return result>0?true:false;
	}

}
