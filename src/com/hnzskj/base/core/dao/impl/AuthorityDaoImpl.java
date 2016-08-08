/*
 * @项目名称: crm
 * @文件名称: AuthorityDaoImpl.java
 * @日期: 2015-8-29 上午09:52:30  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Authority;
import com.hnzskj.base.core.dao.IAuthorityDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：AuthorityDaoImpl.java   <br/>
 * 类描述：功能权限dao层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-29 上午09:52:30   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-29 上午09:52:30   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class AuthorityDaoImpl extends BaseDao implements IAuthorityDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IAuthorityDao#addAuthority(java.util.List)
	 */
	public int addAuthority(List<Authority> list) {
		String sql="INSERT INTO APP_SYSTEM_ROLE_AUTHORITY(UUID,ROLEUUID,FOREIGNKEY,TYPE) VALUES(?,?,?,?)";
		int i=0;
		Object[][]params = new Object[list.size()][];
		for (Authority authority : list) {
			Object[]param = new Object[]{
					null!=authority.getUuid()&&!"".equals(authority.getUuid())?authority.getUuid():UUID.randomUUID().toString(),
					authority.getRoleUuid(),
					authority.getForeignKey(),
					authority.getType()
			};
			params[i]=param;
			i++;
		}
		int result = updateBatch(sql, params, list.size());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IAuthorityDao#delAuthorityByRoleUuid(java.lang.String)
	 */
	public int delAuthorityByRoleUuid(String roleUuid) {
		String sql="DELETE FROM APP_SYSTEM_ROLE_AUTHORITY WHERE ROLEUUID = ? ";
		int result = update(sql, new Object[]{roleUuid});
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IAuthorityDao#getSubsytemList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Authority> getSubsytemList(String roleUuid) {
		String sql="SELECT S.SUB_UUID AS FOREIGNKEY,S.SUB_TITLE AS FOREIGNKEYTEXT,S.SUB_ICON AS ICON, " +
				   "(SELECT UUID FROM APP_SYSTEM_ROLE_AUTHORITY WHERE FOREIGNKEY = S.SUB_UUID  AND ROLEUUID = ? AND TYPE='"+MfConstant.AUTHORITY_SUBSYSTEM+"' ) AS UUID,'"+MfConstant.AUTHORITY_SUBSYSTEM+"' AS TYPE  FROM APP_SYSTEM_SUBSYSTEM S  " +
				   "WHERE S.SUB_STATE = "+MfConstant.STATE_ACTIVATED+" ORDER BY SUB_ORDERBY ASC ";
		List<Authority> list = (List<Authority>)query(sql, Authority.class, new Object[]{roleUuid});
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IAuthorityDao#getModelList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Authority> getModelList(String roleUuid,String modelSubsystemUuid) {
		String sql="SELECT M.MODEL_UUID AS FOREIGNKEY,M.MODEL_TITLE AS FOREIGNKEYTEXT,M.MODEL_ICON AS ICON, " +
				   "(SELECT UUID FROM APP_SYSTEM_ROLE_AUTHORITY WHERE FOREIGNKEY = M.MODEL_UUID AND ROLEUUID = ? AND TYPE='"+MfConstant.AUTHORITY_MODEL+"' ) AS UUID,'"+MfConstant.AUTHORITY_MODEL+"' AS TYPE  FROM APP_SYSTEM_MODEL M  " +
				   "WHERE M.MODEL_STATE = "+MfConstant.STATE_ACTIVATED+" AND MODEL_SUBSYSTEM_UUID = ? ORDER BY MODEL_ORDERBY ASC ";
		List<Authority> list = (List<Authority>)query(sql, Authority.class, new Object[]{roleUuid,modelSubsystemUuid});
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IAuthorityDao#getOperateList(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Authority> getOperateList(String roleUuid, String powerUuid) {
		String sql="select O.OPERATE_UUID AS FOREIGNKEY,O.OPERATE_NAME AS FOREIGNKEYTEXT,O.OPERATE_ICON AS ICON, " +
				   "(SELECT UUID FROM APP_SYSTEM_ROLE_AUTHORITY WHERE FOREIGNKEY = O.OPERATE_UUID AND ROLEUUID = ? AND TYPE='"+MfConstant.AUTHORITY_OPERATE+"' ) AS UUID,'"+MfConstant.AUTHORITY_OPERATE+"' AS TYPE  FROM APP_SYSTEM_OPERATE O  " +
				   "WHERE O.POWER_UUID = ? ORDER BY OPERATE_ORDERBY ASC ";
		List<Authority> list = (List<Authority>)query(sql, Authority.class, new Object[]{roleUuid,powerUuid});
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IAuthorityDao#getPowerList(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Authority> getPowerList(String roleUuid, String modelUuid,
			String powerUuid) {
		String sql="SELECT P.POWER_UUID AS FOREIGNKEY,P.POWER_NAME AS FOREIGNKEYTEXT,P.POWER_ICON AS ICON,  " +
	               "(SELECT UUID FROM APP_SYSTEM_ROLE_AUTHORITY WHERE FOREIGNKEY = P.POWER_UUID  AND ROLEUUID = ? AND TYPE='"+MfConstant.AUTHORITY_POWER+"' ) AS UUID,'"+MfConstant.AUTHORITY_POWER+"' AS TYPE  FROM APP_SYSTEM_POWER P  " +
				   "WHERE P.POWER_STATE = "+MfConstant.STATE_ACTIVATED+" AND POWER_MODEL_UUID  = ? AND POWER_PARENT_UUID  = ? ORDER BY POWER_ORDERBY ASC ";
		List<Authority> list = (List<Authority>)query(sql, Authority.class, new Object[]{roleUuid,modelUuid,powerUuid});
		return list;
	}

	
	
	

}
