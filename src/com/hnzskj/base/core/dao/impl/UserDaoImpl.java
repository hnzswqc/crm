/*
 * @项目名称: crm
 * @文件名称: UserDaoImpl.java
 * @日期: 2015-8-25 下午05:00:43  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.Encrypt;
import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Org;
import com.hnzskj.base.core.bean.Role;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.dao.IUserDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：UserDaoImpl.java   <br/>
 * 类描述：人员信息dao层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-25 下午05:00:43   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-25 下午05:00:43   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#addUser(com.hnzskj.mainFrame.core.bean.User)
	 */
	public int addUser(User user) {
		String sql="INSERT INTO APP_SYSTEM_USER (USER_UUID,USER_ID,USER_NUMBER,USER_NAME,USER_PASSWORD,USER_PHONE,USER_STATE,USER_BIRTYDAY,USER_GENDER,USER_MOBILE,USER_MAIL,USER_OTHER,USER_UNVERSITY,USER_DEGREES,USER_ADDRESS,USER_ORDERBY,USER_NOTE,CREATE_TIME,JOIN_TIME,FORMAL_TIME,ID_CARD," +
				   "USER_BANK_CARD,USER_AGE,PBF_DATE,PBF_LEADER,PBF_NORMAL_PAY,PBF_MERIT_PAY,PBF_OTHER_PAY,USER_NORMAL_PAY,USER_MERIT_PAY,USER_OTHER_PAY)" +
				   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE(),?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String uuid =null!=user.getUserUuid()&&!"".equals(user.getUserUuid())?user.getUserUuid():UUID.randomUUID().toString(); 
		Object[]params = new Object[]{
				uuid,
				user.getUserId(),
				user.getUserNumber(),
				user.getUserName(), 
				Encrypt.digest(MfConstant.DEFAULT_PASSWORD),//加密
				user.getUserPhone(),
				user.getUserState(),
				null!=user.getUserBirthday()&&!"".equals(user.getUserBirthday())?user.getUserBirthday():null,
				user.getUserGender(),
				user.getUserMobile(),
				user.getUserMail(),
				user.getUserOther(),
				user.getUserUnversity(),
				user.getUserDegrees(),
				user.getUserAddress(),
				user.getUserOrderBy(),
				user.getUserNote(),
				null!=user.getJoinTime()&&!"".equals(user.getJoinTime())?user.getJoinTime():null,
				null!=user.getFormalTime()&&!"".equals(user.getFormalTime())?user.getFormalTime():null,
				user.getIdCard(),
				user.getUserBankCard(),
				user.getUserAge(),
				null!=user.getPbfDate()&&!"".equals(user.getPbfDate())?user.getPbfDate():null,
				user.getPbfLeader(),
				user.getProbationNormalPay(),
				user.getProbationMeritPay(),
				user.getProbationOtherPay(),
				user.getUserNormalPay(),
				user.getUserMeritPay(),
				user.getUserOtherPay()
		};
		//添加部门 
		String sql1="INSERT INTO APP_SYSTEM_ORG_USER(UUID,USER_UUID,ORG_UUID,CREATE_TIME) VALUES(?,?,?,GETDATE())";
		Object[]params1 = new Object[]{
				UUID.randomUUID().toString(),
				uuid,
				user.getOrgUuid()
		};
		//添加角色职务
		String sql2="INSERT INTO APP_SYSTEM_ROLE_USER(UUID,USER_UUID,ROLE_UUID) VALUES(?,?,?)";
		Object[]params2 = new Object[]{
				UUID.randomUUID().toString(),
				uuid,
				user.getRoleUuid()
		};
		int result = update(new String[]{sql,sql1,sql2},new Object[][]{params,params1,params2});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#delUser(com.hnzskj.mainFrame.core.bean.User)
	 */
	public int delUser(User user) {
		String sql="DELETE FROM APP_SYSTEM_USER WHERE USER_UUID = ? ";//用户表
		String sql1="DELETE FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = ? ";//用户部门表
		String sql2="DELETE FROM APP_SYSTEM_ROLE_USER WHERE USER_UUID = ? ";//用户角色表
		int result = update(new String[]{sql,sql1,sql2}, new Object[][]{new Object[]{user.getUserUuid()},new Object[]{user.getUserUuid()},new Object[]{user.getUserUuid()}});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#getUserList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<User> getUserList(PageBean<User> pageBean, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<User> empls = new ArrayList<User>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<User> epage = new PageBean<User>();// 临时变量，如果在page为null的情况下使用
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " ": sqlCondition;
		countSql = "select count(" + key + ") from " + tableName + " " + sqlCondition;
		totalRecords = (Integer) getSingleValue(countSql, queryParams);
		if (pageBean != null) {// 如果需要分页
			if (null != queryParams && queryParams.length > 0) {
				// 将数组的长度扩充为原来的2倍,并使得数组的第n个元素和第n+数组长度的元素的值相等,如此是为了分页查询时参数设置的需要
				Object[] newParamsArray = Arrays.copyOf(queryParams,
						queryParams.length * 2);
				for (int i = 0; i < queryParams.length; i++) {
					newParamsArray[queryParams.length + i] = queryParams[i];
				}
				queryParams = newParamsArray;
			}
			// 如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			// 如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			sql = "select top " + pageBean.getLimit() + " " + fields
					+ " from  " + tableName + " " + sqlCondition;
			if (" ".equals(sqlCondition) == true) {
				sql += " where " + key + " not in (select top "
						+ (pageBean.getPage() - 1) * pageBean.getLimit() + " "
						+ key + "  from " + tableName + " " + sqlCondition
						+ BaseDao.buildOrderBy(orderby) + ")"
						+ BaseDao.buildOrderBy(orderby);
			} else {
				sql += " and " + key + " not in (select top "
						+ (pageBean.getPage() - 1) * pageBean.getLimit() + " "
						+ key + " from " + tableName + sqlCondition
						+ BaseDao.buildOrderBy(orderby) + ")"
						+ BaseDao.buildOrderBy(orderby);
			}
			epage = pageBean;
			// 查询结果集
			empls = query(sql,User.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, User.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#updUser(com.hnzskj.mainFrame.core.bean.User)
	 */
	public int updUser(User user) {
		String sql="UPDATE APP_SYSTEM_USER SET  USER_ID = ?, USER_NAME = ?,USER_NUMBER = ?,USER_PHONE = ?,USER_STATE = ?,USER_BIRTYDAY = ?,USER_GENDER = ?,USER_MOBILE = ?,USER_MAIL = ?,USER_OTHER = ?,USER_UNVERSITY = ?,USER_DEGREES = ?,USER_ADDRESS = ?,USER_ORDERBY = ?,USER_NOTE = ?,JOIN_TIME = ?,FORMAL_TIME = ? , ID_CARD = ? ," +
				   "USER_BANK_CARD = ? ,USER_AGE = ? ,PBF_DATE = ? ,PBF_LEADER = ? ,PBF_NORMAL_PAY = ? ,PBF_MERIT_PAY = ? ,PBF_OTHER_PAY = ? ,USER_NORMAL_PAY = ? ,USER_MERIT_PAY = ? ,USER_OTHER_PAY = ? " +
				   "WHERE USER_UUID = ? ";
		Object[]params = new Object[]{
				user.getUserId(),
				user.getUserName(),
				user.getUserNumber(),
				user.getUserPhone(),
				user.getUserState(),
				null!=user.getUserBirthday()&&!"".equals(user.getUserBirthday())?user.getUserBirthday():null,
				user.getUserGender(),
				user.getUserMobile(),
				user.getUserMail(),
				user.getUserOther(),
				user.getUserUnversity(),
				user.getUserDegrees(),
				user.getUserAddress(),
				user.getUserOrderBy(),
				user.getUserNote(),
				null!=user.getJoinTime()&&!"".equals(user.getJoinTime())?user.getJoinTime():null,
				null!=user.getFormalTime()&&!"".equals(user.getFormalTime())?user.getFormalTime():null,
				user.getIdCard(),
				user.getUserBankCard(),
				user.getUserAge(),
				null!=user.getPbfDate()&&!"".equals(user.getPbfDate())?user.getPbfDate():null,
				user.getPbfLeader(),
				user.getProbationNormalPay(),
				user.getProbationMeritPay(),
				user.getProbationOtherPay(),
				user.getUserNormalPay(),
				user.getUserMeritPay(),
				user.getUserOtherPay(),
				user.getUserUuid()
		};
		String sql1="DELETE FROM APP_SYSTEM_ROLE_USER WHERE USER_UUID = ? ";
		
		int result = update(new String[]{sql,sql1}, new Object[][]{params,new Object[]{user.getUserUuid()}});
		if(result>0){
			//添加角色职务
			String sql2="INSERT INTO APP_SYSTEM_ROLE_USER(UUID,USER_UUID,ROLE_UUID) VALUES(?,?,?)";
			Object[]params2 = new Object[]{
					UUID.randomUUID().toString(),
					user.getUserUuid(),
					user.getRoleUuid()
			};
			result = update(sql2, params2);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#getOrgUser(java.lang.String, java.lang.String)
	 */
	public int getOrgUser(String userUuid, String orgUuid) {
		String sql="SELECT COUNT(1) AS COUNT FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = ? AND ORG_UUID = ? ";
		Integer result =(Integer) getSingleValue(sql, new Object[]{userUuid,orgUuid});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#addOrgUser(java.lang.String[], java.lang.String)
	 */
	public int addOrgUser(String[] orgUuids, String userUuid) {
		String sql="INSERT INTO APP_SYSTEM_ORG_USER (UUID,USER_UUID,ORG_UUID,CREATE_TIME) VALUES(?,?,?,getdate())";
		String[]sqls=new String[orgUuids.length];
		Object[][]params = new Object[orgUuids.length][];
		for (int i = 0; i < orgUuids.length; i++) {
			Object[]param = new Object[]{
					UUID.randomUUID().toString(),
					userUuid,
					orgUuids[i]
			};
			sqls[i] = sql;
			params[i]=param;
		}
		int result = update(sqls, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#delOrgUser(java.lang.String)
	 */
	public int delOrgUser(String userUuid) {
		String sql="DELETE FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = ? ";
		int result = update(sql, new Object[]{userUuid});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#getUserByUserId(java.lang.String)
	 */
	public User getUserByUserId(String userId) {
		String sql="SELECT USER_UUID AS USERUUID,USER_ID AS USERID,USER_NUMBER AS USERNUMBER,USER_NAME AS USERNAME,USER_PHONE AS USERPHONE,USER_STATE AS USERSTATE,USER_BIRTYDAY AS USERBIRTHDAY,USER_GENDER AS USERGENDER,USER_MOBILE AS USERMOBILE,USER_MAIL AS USERMAIL,USER_OTHER AS USEROTHER,USER_UNVERSITY AS USERUNVERSITY,USER_DEGREES USERDEGREES ,USER_ADDRESS AS USERADDRESS,USER_ORDERBY AS USERORDERBY,USER_NOTE AS USERNOTE,CREATE_TIME AS CREATETIME,JOIN_TIME AS JOINTIME,FORMAL_TIME AS FORMALTIME FROM APP_SYSTEM_USER " +
				   "WHERE USER_ID = ? ";
		User user = (User)get(sql, User.class,new Object[]{userId});
		return user;
	}

	
	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IUserDao#getUserByUserNumber(java.lang.String)
	 */
	public User getUserByUserNumber(String userNumber) {
		String sql="SELECT USER_UUID AS USERUUID,USER_ID AS USERID,USER_NUMBER AS USERNUMBER,USER_NAME AS USERNAME,USER_PHONE AS USERPHONE,USER_STATE AS USERSTATE,USER_BIRTYDAY AS USERBIRTHDAY,USER_GENDER AS USERGENDER,USER_MOBILE AS USERMOBILE,USER_MAIL AS USERMAIL,USER_OTHER AS USEROTHER,USER_UNVERSITY AS USERUNVERSITY,USER_DEGREES USERDEGREES ,USER_ADDRESS AS USERADDRESS,USER_ORDERBY AS USERORDERBY,USER_NOTE AS USERNOTE,CREATE_TIME AS CREATETIME,PBF_DATE AS JOINTIME,FORMAL_TIME AS FORMALTIME,ID_CARD AS IDCARD, " +
		"(SELECT TOP 1 ORG_UUID AS ORGUUID FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = U.USER_UUID) AS ORGUUID,"+
		"(SELECT ORG_NAME FROM APP_SYSTEM_ORG WHERE ORG_UUID = ((SELECT TOP 1 ORG_UUID AS ORGUUID FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = U.USER_UUID))) AS ORGNAME,"+
		"(SELECT TOP 1 ROLE_UUID FROM APP_SYSTEM_ROLE_USER WHERE USER_UUID = U.USER_UUID) AS ROLEUUID, "+
		"(SELECT TOP 1 ROLE_NAME FROM APP_SYSTEM_ROLE WHERE ROLE_UUID IN (SELECT ROLE_UUID FROM APP_SYSTEM_ROLE_USER WHERE USER_UUID = U.USER_UUID) ) AS ROLENAME " +
	   "FROM APP_SYSTEM_USER U " +
	   "WHERE USER_NUMBER = ? ";
		User user = (User)get(sql, User.class,new Object[]{userNumber});
		return user;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#getOrgRoleList(com.hnzskj.mainFrame.core.bean.Org)
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getOrgRoleList(Org org) {
		String sql="SELECT ROLE_UUID AS ROLEUUID,ROLE_KEY AS ROLEKEY,ROLE_NAME AS ROLENAME,ROLE_NOTE AS ROLENOTE,ROLE_ORDERBY AS ROLEORDERBY,CREATE_TIME AS CREATETIME,(SELECT COUNT(1) FROM APP_SYSTEM_ROLE_ORG RO WHERE RO.ROLE_UUID = R.ROLE_UUID AND ORG_UUID = ? ) AS COUNT FROM dbo.APP_SYSTEM_ROLE R WHERE R.ROLE_STATE = ? ORDER BY ROLE_ORDERBY ASC ";
		Object[]params = new Object[]{
				org.getOrgUuid(),
				MfConstant.STATE_ACTIVATED
		};
		List<Role> list = query(sql, Role.class, params);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#getUserRoleList(com.hnzskj.mainFrame.core.bean.User)
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getUserRoleList(User user) {
		String sql="SELECT ROLE_UUID AS ROLEUUID,ROLE_KEY AS ROLEKEY,ROLE_NAME AS ROLENAME,ROLE_NOTE AS ROLENOTE,ROLE_ORDERBY AS ROLEORDERBY,CREATE_TIME AS CREATETIME,(SELECT COUNT(1) FROM APP_SYSTEM_ROLE_USER RU WHERE RU.ROLE_UUID = R.ROLE_UUID AND USER_UUID = ? ) AS COUNT FROM dbo.APP_SYSTEM_ROLE R WHERE R.ROLE_STATE = ? ORDER BY ROLE_ORDERBY ASC ";
		Object[]params = new Object[]{
				user.getUserUuid(),
				MfConstant.STATE_ACTIVATED
		};
		List<Role> list = query(sql, Role.class, params);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#addUserRole(java.util.List, java.lang.String[])
	 */
	public int addUserRole(List<User> userList, String[] roleUuids) {
		String sql="INSERT INTO APP_SYSTEM_ROLE_USER (UUID,USER_UUID,ROLE_UUID)VALUES(?,?,?)";
		Object[][]params = new Object[userList.size()*roleUuids.length][];
		int i =0;
		for (User user:userList) {
			//先删除
			delUserRoleByUserUuid(user.getUserUuid());
			for (String string : roleUuids) {
				Object[]param = new Object[]{
						UUID.randomUUID().toString(),
						user.getUserUuid(),
						string
				};
				params[i] = param;
				i++;
			}
		}
		int result = updateBatch(sql, params, userList.size()*roleUuids.length);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#delUserRoleByUserUuid(java.lang.String)
	 */
	public int delUserRoleByUserUuid(String userUuid) {
		String sql="DELETE FROM APP_SYSTEM_ROLE_USER WHERE USER_UUID = ? ";
		int result = update(sql,new Object[]{userUuid});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#addOrgRole(java.lang.String, java.lang.String[])
	 */
	public int addOrgRole(String orgUuid, String[] roleUuids) {
		String sql="INSERT INTO APP_SYSTEM_ROLE_ORG (UUID,ORG_UUID,ROLE_UUID)VALUES(?,?,?)";
		Object[][]params = new Object[roleUuids.length][];
		int i =0;
		//先删除
		delOrgUser(orgUuid);
		for (String string : roleUuids) {
			Object[]param = new Object[]{
					UUID.randomUUID().toString(),
					orgUuid,
					string
			};
			params[i] = param;
			i++;
		}
		int result = updateBatch(sql, params, roleUuids.length);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#delOrgRoleByOrgUuid(java.lang.String)
	 */
	public int delOrgRoleByOrgUuid(String orgUuid) {
		String sql="DELETE FROM APP_SYSTEM_ROLE_ORG WHERE ORG_UUID = ? ";
		int result = update(sql,new Object[]{orgUuid});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IUserDao#login(java.lang.String, java.lang.String)
	 */
	public User login(String userId, String userPassword) {
		String sql="SELECT USER_UUID AS USERUUID, USER_NUMBER AS USERNUMBER,USER_ID AS USERID,USER_NAME AS USERNAME,USER_PHONE AS USERPHONE,USER_STATE AS USERSTATE,USER_BIRTYDAY AS USERBIRTHDAY,USER_GENDER as USERGENDER,USER_DEGREES AS USERDEGREES,USER_MOBILE AS USERMOBILE,USER_MAIL AS USERMAIL,USER_OTHER AS USEROTHER,USER_UNVERSITY AS USERUNVERSITY,USER_ADDRESS AS USERADDRESS,USER_ORDERBY AS USERORDERBY,USER_NOTE AS USERNOTE,CREATE_TIME AS CREATETIME,JOIN_TIME AS JOINTIME,FORMAL_TIME AS FORMALTIME,ID_CARD AS IDCARD FROM APP_SYSTEM_USER " +
				   "WHERE USER_ID = ? AND USER_PASSWORD = ?";
		User user = (User)get(sql,User.class,new Object[]{userId,Encrypt.digest(userPassword)});
		return user;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IUserDao#updPassword(com.hnzskj.base.core.bean.User)
	 */
	public int updPassword(User user) {
		String sql="UPDATE APP_SYSTEM_USER SET USER_PASSWORD = ?  WHERE USER_ID = ? AND USER_UUID = ? ";
		int result = update(sql, new Object[]{Encrypt.digest(user.getUserPassword()),user.getUserId(),user.getUserUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IUserDao#getUserByUuid(java.lang.String)
	 */
	public User getUserByUuid(String uuid) {
		String sql="SELECT USER_UUID AS USERUUID,USER_NUMBER AS USERNUMBER,USER_ID AS USERID,USER_NAME AS USERNAME,USER_PHONE AS USERPHONE,USER_STATE AS USERSTATE,USER_BIRTYDAY AS USERBIRTHDAY,USER_GENDER as USERGENDER,USER_DEGREES AS USERDEGREES,USER_MOBILE AS USERMOBILE,USER_MAIL AS USERMAIL,USER_OTHER AS USEROTHER,USER_UNVERSITY AS USERUNVERSITY,USER_ADDRESS AS USERADDRESS,USER_ORDERBY AS USERORDERBY,USER_NOTE AS USERNOTE,CREATE_TIME AS CREATETIME,JOIN_TIME AS JOINTIME,FORMAL_TIME AS FORMALTIME,ID_CARD AS IDCARD FROM APP_SYSTEM_USER " +
				   "WHERE USER_UUID = ? ";
		User user = (User)get(sql,User.class,new Object[]{uuid});
		return user;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IUserDao#getUserRoleList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getUserRoleList(String userUuid) {
		String sql="SELECT ROLE_UUID AS ROLEUUID,ROLE_KEY AS ROLEKEY,ROLE_NAME AS ROLENAME,ROLE_NOTE AS ROLENOTE,ROLE_ORDERBY AS ROLEORDERBY,CREATE_TIME AS CREATETIME FROM APP_SYSTEM_ROLE ORDER BY ROLE_ORDERBY ASC " +
				   "WHERE ROLE_UUID IN (SELECT ROLE_UUID FROM  DBO.APP_SYSTEM_ROLE_USER WHERE USER_UUID = ? ) AND ROLE_STATE = ?  ";
		Object[]params = new Object[]{
				userUuid,
				MfConstant.STATE_ACTIVATED
		};
		List<Role> list = query(sql, Role.class, params);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IUserDao#getUserNumber(java.lang.String)
	 */
	public String getUserNumber(String orgUuid) {
		String sql="DECLARE @RETURN_VALUE VARCHAR(10);EXEC GETUSERNUMBER '"+orgUuid+"',@RETURN_VALUE OUTPUT;SELECT @RETURN_VALUE AS USERNUMBER";
		String userNumber =String.valueOf(getSingleStringValue(sql, null));
		return userNumber;
	}
}

