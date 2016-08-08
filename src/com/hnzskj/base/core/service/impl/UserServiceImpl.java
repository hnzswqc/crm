/*
 * @项目名称: crm
 * @文件名称: UserServiceImpl.java
 * @日期: 2015-8-25 下午05:18:10  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Org;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.dao.IUserDao;
import com.hnzskj.base.core.service.IUserService;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：UserServiceImpl.java   <br/>
 * 类描述： 人员信息业务层实现类  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-25 下午05:18:10   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-25 下午05:18:10   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class UserServiceImpl implements IUserService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IUserDao userDao =null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#addUser(com.hnzskj.mainFrame.core.bean.User)
	 */
	public boolean addUser(User user) {
		int result = userDao.addUser(user);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#delUser(com.hnzskj.mainFrame.core.bean.User)
	 */
	public boolean delUser(User user) {
		int result = userDao.delUser(user);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#getUserList(com.hnzskj.common.bean.PageBean, com.hnzskj.mainFrame.core.bean.User)
	 */
	public PageBean<User> getUserList(PageBean<User> pageBean, User user) {
		String fields = "USER_UUID AS USERUUID,USER_ID AS USERID,USER_NUMBER AS USERNUMBER,USER_NAME AS USERNAME,USER_PASSWORD AS USERPASSWORD,USER_PHONE AS USERPHONE," +
				        "USER_STATE AS USERSTATE,USER_BIRTYDAY AS USERBIRTYDAY,USER_GENDER AS USERGENDER,USER_MOBILE AS USERMOBILE,USER_MAIL AS USERMAIL,USER_OTHER AS USEROTHER," +
				        "USER_UNVERSITY AS USERUNVERSITY,USER_ADDRESS AS USERADDRESS,USER_ORDERBY AS USERORDERBY,USER_NOTE AS USERNOTE,JOIN_TIME AS JOINTIME,FORMAL_TIME AS FORMALTIME," +
				        "ID_CARD AS IDCARD,USER_BANK_CARD AS USERBANKCARD,CREATE_TIME AS CREATETIME,USER_AGE AS USERAGE,PBF_DATE AS PBFDATE,PBF_LEADER AS PBFLEADER," +
				        "PBF_NORMAL_PAY AS PROBATIONNORMALPAY,PBF_MERIT_PAY AS PROBATIONMERITPAY,PBF_OTHER_PAY AS PROBATIONOTHERPAY,USER_NORMAL_PAY AS USERNORMALPAY," +
				        "USER_MERIT_PAY AS USERMERITPAY,USER_OTHER_PAY AS USEROTHERPAY,USER_DEGREES as USERDEGREES,USER_BIRTYDAY AS USERBIRTHDAY," +
						"(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_GENDER+"' AND ID = USER_GENDER) AS USERGENDERTEXT,"+
						"(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_USER_DEGREES+"' AND ID = USER_DEGREES) AS USERDEGREESTEXT,"+
						"(SELECT TOP 1 ORG_UUID AS ORGUUID FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = U.USER_UUID) AS ORGUUID,"+
						"(SELECT TOP 1 ORG_NAME FROM APP_SYSTEM_ORG WHERE ORG_UUID IN (SELECT ORG_UUID FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = U.USER_UUID) )AS ORGNAME,"+
						"(SELECT TOP 1 ROLE_UUID FROM APP_SYSTEM_ROLE_USER WHERE USER_UUID = U.USER_UUID) AS ROLEUUID, "+
						"(SELECT TOP 1 ROLE_NAME FROM APP_SYSTEM_ROLE WHERE ROLE_UUID IN (SELECT ROLE_UUID FROM APP_SYSTEM_ROLE_USER WHERE USER_UUID = U.USER_UUID) ) AS ROLENAME, " +
						"(SELECT TOP 1 FILE_TYPE FROM APP_SYSTEM_ATTACHMENT WHERE FOREIGN_UUID =USER_UUID AND ATT_TYPE = '"+com.hnzskj.oa.common.util.MfConstant.DIC_RECORD_ID_CARD+"'  ) AS sfz, " +
						"(SELECT TOP 1 FILE_TYPE FROM APP_SYSTEM_ATTACHMENT WHERE FOREIGN_UUID =USER_UUID AND ATT_TYPE = '"+com.hnzskj.oa.common.util.MfConstant.DIC_RECORD_JOIN_MSG+"'  ) AS rzbg, " +
						"(SELECT TOP 1 FILE_TYPE FROM APP_SYSTEM_ATTACHMENT WHERE FOREIGN_UUID =USER_UUID AND ATT_TYPE = '"+com.hnzskj.oa.common.util.MfConstant.DIC_RECORD_JOIN_TJ_MSG+"'  ) AS tjzm, " +
						"(SELECT TOP 1 FILE_TYPE FROM APP_SYSTEM_ATTACHMENT WHERE FOREIGN_UUID =USER_UUID AND ATT_TYPE = '"+com.hnzskj.oa.common.util.MfConstant.DIC_RECORD_JOIN_SQZZB+"'  ) AS sqzzb " +
						"";
				StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=user){
			//用户uuid
			if(null!=user.getUserUuid()&&!"".equals(user.getUserUuid())){
				sb.append(" AND USER_UUID = '").append(user.getUserUuid()).append("'");
			}
			//用户帐号
			if(null!=user.getUserId()&&!"".equals(user.getUserId())){
				sb.append(" AND USER_ID = '").append(user.getUserId()).append("'");
			}
			//员工号
			if(null!=user.getUserNumber()&&!"".equals(user.getUserNumber())){
				sb.append(" AND USER_NUMBER = '").append(user.getUserNumber()).append("'");
			}
			//用户名称
			if(null!=user.getUserName()&&!"".equals(user.getUserName())){
				sb.append(" AND USER_NAME LIKE '%").append(user.getUserName()).append("%'");
			}
			//人员状态
			if(null!=user.getUserState()&&!"".equals(user.getUserState())){
				sb.append(" AND USER_STATE = ").append(user.getUserState());
			}else{
				//默认查询在职状态
				sb.append(" AND USER_STATE = ").append(MfConstant.STATE_ACTIVATED);
			}
			//所属组织机构
			if(null!=user.getOrgUuid()&&!"".equals(user.getOrgUuid())){
				sb.append(" AND USER_UUID IN(").append("SELECT USER_UUID FROM APP_SYSTEM_ORG_USER WHERE ORG_UUID = '"+user.getOrgUuid()+"'").append(")");
			}
			
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("USER_NUMBER", "ASC");
		//orderby.put("USER_ORDERBY", "ASC");
		//orderby.put("CREATE_TIME", "DESC");
		pageBean = userDao.getUserList(pageBean, fields, sb.toString(),null, orderby, " APP_SYSTEM_USER U "," USER_UUID ");
		return pageBean;
	}
	
	

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IUserService#getUserList(com.hnzskj.common.bean.PageBean, com.hnzskj.base.core.bean.User, java.lang.String)
	 */
	public PageBean<User> getUserList(PageBean<User> pageBean, User user,
			String type) {
		String fields=" U.USER_UUID AS USERUUID,USER_ID AS USERID,USER_NUMBER AS USERNUMBER,USER_NAME AS USERNAME,USER_PHONE AS USERPHONE,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_USER_STATE+"' AND ID = USER_STATE) AS USERSTATETEXT,USER_STATE AS USERSTATE,USER_BIRTYDAY AS USERBIRTHDAY,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_GENDER+"' AND ID = USER_GENDER) AS USERGENDERTEXT,USER_GENDER as USERGENDER,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_USER_DEGREES+"' AND ID = USER_DEGREES) AS USERDEGREESTEXT,USER_DEGREES AS USERDEGREES,USER_MOBILE AS USERMOBILE,USER_MAIL AS USERMAIL,USER_OTHER AS USEROTHER,USER_UNVERSITY AS USERUNVERSITY,USER_ADDRESS AS USERADDRESS,USER_ORDERBY AS USERORDERBY,USER_NOTE AS USERNOTE,U.CREATE_TIME AS CREATETIME,JOIN_TIME AS JOINTIME,FORMAL_TIME AS FORMALTIME,ID_CARD AS IDCARD ";
		if(MfConstant.USER_APPLY.equals(type)){
			fields+=",(SELECT COUNT(1) FROM APP_SYSTEM_USER_AUTHORITY WHERE AUTHORITY_USER_UUID = U.USER_UUID AND TYPE = '"+type+"' AND USER_UUID = '"+user.getUserUuid()+"') AS COUNT ";
		}else{
			fields+=",(SELECT COUNT(1) FROM APP_SYSTEM_USER_AUTHORITY WHERE USER_UUID = U.USER_UUID AND TYPE = '"+type+"' AND AUTHORITY_USER_UUID='"+user.getUserUuid()+"') AS COUNT ";
		}
		StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=user){
			//用户帐号
			if(null!=user.getUserId()&&!"".equals(user.getUserId())){
				sb.append(" AND USER_ID = '").append(user.getUserId()).append("'");
			}
			//员工号
			if(null!=user.getUserNumber()&&!"".equals(user.getUserNumber())){
				sb.append(" AND USER_NUMBER = '").append(user.getUserNumber()).append("'");
			}
			//用户名称
			if(null!=user.getUserName()&&!"".equals(user.getUserName())){
				sb.append(" AND USER_NAME LIKE '%").append(user.getUserName()).append("%'");
			}
			//人员状态
			if(null!=user.getUserState()&&!"".equals(user.getUserState())){
				sb.append(" AND USER_STATE = ").append(user.getUserState());
			}else{
				//默认查询在职状态
				sb.append(" AND USER_STATE = ").append(MfConstant.STATE_ACTIVATED);
			}
			//所属组织机构
			if(null!=user.getOrgUuid()&&!"".equals(user.getOrgUuid())){
				sb.append(" AND USER_UUID IN(").append("SELECT USER_UUID FROM APP_SYSTEM_ORG_USER WHERE ORG_UUID = '"+user.getOrgUuid()+"'").append(")");
			}
			
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("USER_NUMBER", "ASC");
		//orderby.put("USER_ORDERBY", "ASC");
		//orderby.put("CREATE_TIME", "DESC");
		pageBean = userDao.getUserList(pageBean, fields, sb.toString(),null, orderby, " APP_SYSTEM_USER U "," U.USER_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#updUser(com.hnzskj.mainFrame.core.bean.User)
	 */
	public boolean updUser(User user) {
		int result = userDao.updUser(user);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#getOrgUser(java.lang.String, java.lang.String)
	 */
	public boolean getOrgUser(String userUuid, String orgUuid) {
		int result = userDao.getOrgUser(userUuid, orgUuid);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#addOrgUser(java.lang.String, java.lang.String)
	 */
	public boolean addOrgUser(String orgUuid, String userUuid) {
		userDao.delOrgUser(userUuid);//先删除
		if(null!=orgUuid&&!"".equals(orgUuid)){
			String[]strs=null;
			if(orgUuid.indexOf(",")>-1){
				strs = orgUuid.split(",");
			}else{
				strs = new String[]{orgUuid};
			}
			int result = userDao.addOrgUser(strs, userUuid);
			return result>0?true:false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#getUserByUserId(java.lang.String)
	 */
	public User getUserByUserId(String userId) {
		User user = userDao.getUserByUserId(userId);
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IUserService#getUserByUserNumber(java.lang.String)
	 */
	public User getUserByUserNumber(String userNumber) {
		User user = userDao.getUserByUserNumber(userNumber);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#getOrgRoleList(com.hnzskj.mainFrame.core.bean.Org)
	 */
	public List<Role> getOrgRoleList(Org org) {
		List<Role> list = userDao.getOrgRoleList(org);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#getUserRoleList(com.hnzskj.mainFrame.core.bean.User)
	 */
	public List<Role> getUserRoleList(User user) {
		List<Role> list = userDao.getUserRoleList(user);
		return list;
	}
	
	

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IUserService#getUserRoleList(java.lang.String)
	 */
	public List<Role> getUserRoleList(String userUuid) {
		List<Role> list = userDao.getUserRoleList(userUuid);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#addOrgRole(java.lang.String, java.lang.String)
	 */
	public boolean addOrgRole(String orgUuid, String roleUuid) {
		if(null!=roleUuid&&!"".equals(roleUuid)){
			String[]roleUuids=null;
			if(roleUuid.indexOf(",")>-1){
				roleUuids = roleUuid.split(",");
			}else{
				roleUuids=new String[]{roleUuid};
			}
			int result = userDao.addOrgRole(orgUuid, roleUuids);
			if(result>0){//添加部门下用户
				User user = new User();
				user.setOrgUuid(orgUuid);
				PageBean<User> pageBean = this.getUserList(null, user);
				if(null!=pageBean&&pageBean.getList().size()>0){
					result= userDao.addUserRole(pageBean.getList(), roleUuids);
				}
			}
			return result >0?true:false;
		}
		userDao.delOrgRoleByOrgUuid(orgUuid);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#addUserRole(java.lang.String, java.lang.String)
	 */
	public boolean addUserRole(String userUuid, String roleUuid) {
		if(null!=roleUuid&&!"".equals(roleUuid)){
			String[]roleUuids=null;
			if(roleUuid.indexOf(",")>-1){
				roleUuids = roleUuid.split(",");
			}else{
				roleUuids=new String[]{roleUuid};
			}
			User user = new User();
			user.setUserUuid(userUuid);
			PageBean<User> pageBean = this.getUserList(null, user);
			int result =0;
			if(null!=pageBean&&pageBean.getList().size()>0){
				result= userDao.addUserRole(pageBean.getList(), roleUuids);
			}
			return result >0?true:false;
		}
		userDao.delUserRoleByUserUuid(userUuid);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IUserService#login(java.lang.String, java.lang.String)
	 */
	public User login(String userId, String userPassword) {
		User user = userDao.login(userId, userPassword);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IUserService#updPassword(com.hnzskj.base.core.bean.User)
	 */
	public boolean updPassword(User user) {
		int result = userDao.updPassword(user);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IUserService#getUserByUuid(java.lang.String)
	 */
	public User getUserByUuid(String uuid) {
		User user = userDao.getUserByUuid(uuid);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IUserService#getUserNumber(java.lang.String)
	 */
	public String getUserNumber(String orgUuid) {
		String userNumber = userDao.getUserNumber(orgUuid);
		if("-1".equals(userNumber)){
			return "";
		}
		if(userNumber.length()==1){
			return "00"+userNumber;
		}else if(userNumber.length()==2){
			return "0"+userNumber;
		}
		return userNumber;
	}
	
	
	
}

