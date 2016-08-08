/*
 * @项目名称: crm
 * @文件名称: AuthorityService.java
 * @日期: 2015-8-29 上午10:39:17  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.base.core.bean.Authority;
import com.hnzskj.base.core.dao.IAuthorityDao;
import com.hnzskj.base.core.service.IAuthorityService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：AuthorityService.java   <br/>
 * 类描述： 功能权限业务层实现类  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-29 上午10:39:17   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-29 上午10:39:17   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class AuthorityService implements IAuthorityService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IAuthorityDao authorityDao = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IAuthorityService#addAuthority(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean addAuthority(String foreignKeys, String types,
			String roleUuid) {
		//先删除在添加。如果没有选择权限信息，则就默认全部删除。
		authorityDao.delAuthorityByRoleUuid(roleUuid);
		if(null!=foreignKeys&&!"".equals(foreignKeys)){
			String[]foreignKey=null;
			String[]type=null;
			if(foreignKeys.indexOf(",")>-1){
				foreignKey = foreignKeys.split(",");
				type = types.split(",");
			}else{
				foreignKey = new String[]{foreignKeys};
				type = new String[]{types};
			}
			List<Authority> list = new ArrayList<Authority>();
			Authority authority = null;
			for (int i = 0; i < foreignKey.length; i++) {
				authority = new Authority();
				authority.setForeignKey(foreignKey[i]);
				authority.setType(type[i]);
				authority.setRoleUuid(roleUuid);
				list.add(authority);
			}
			int result = authorityDao.addAuthority(list);
			return result>0?true:false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IAuthorityService#delAuthorityByRoleUuid(java.lang.String)
	 */
	public boolean delAuthorityByRoleUuid(String roleUuid) {
		int result = authorityDao.delAuthorityByRoleUuid(roleUuid);
		return result>=0?true:false;
	}
	
	

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IAuthorityService#getSubsytemList(java.lang.String)
	 */
	public List<Authority> getSubsytemList(String roleUuid) {
		List<Authority> list = authorityDao.getSubsytemList(roleUuid);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IAuthorityService#getModelList(java.lang.String)
	 */
	public List<Authority> getModelList(String roleUuid,String modelSubsystemUuid) {
		List<Authority> list = authorityDao.getModelList(roleUuid,modelSubsystemUuid);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IAuthorityService#getOperateList(java.lang.String, java.lang.String)
	 */
	public List<Authority> getOperateList(String roleUuid, String powerUuid) {
		List<Authority> list = authorityDao.getOperateList(roleUuid,powerUuid);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IAuthorityService#getPowerList(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<Authority> getPowerList(String roleUuid, String modelUuid,
			String powerUuid) {
		List<Authority> list = authorityDao.getPowerList(roleUuid,modelUuid,powerUuid);
		return list;
	}

}
