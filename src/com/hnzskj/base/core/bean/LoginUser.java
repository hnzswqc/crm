/*
 * @项目名称: crm
 * @文件名称: LoginUser.java
 * @日期: 2015-10-21 下午05:11:14  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：LoginUser.java   <br/>
 * 类描述：   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-10-21 下午05:11:14   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-10-21 下午05:11:14   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class LoginUser {

	/**
	 * 主键uuid
	 */
	private String loginUuid;
	
	/**
	 * 用户登录sessionId
	 */
	private String loginSessionId;
	
	/**
	 * 登录人员uuid
	 */
	private String loginUserUuid;
	
	/**
	 * 登录人员id
	 */
	private String loginUserId;
	
	/**
	 * 登录人员名称
	 */
	private String loginUserName;
	
	/**
	 * 登录人员部门uuid
	 */
	private String loginOrgUuid;
	
	/**
	 * 登录人员部门名称
	 */
	private String loginOrgName;
	
	/**
	 * 登录人员部门编码
	 */
	private String loginOrgCode;
	
	/**
	 * 登录人员部门上级
	 */
	private String loginOrgParentUuid;
	
	/**
	 * 登录时间
	 */
	private String createTime;

	/**
	 * 主键uuid
	 * @return the loginUuid
	 */
	public String getLoginUuid() {
		return loginUuid;
	}

	/**
	 * 主键uuid
	 * @param loginUuid the loginUuid to set
	 */
	public void setLoginUuid(String loginUuid) {
		this.loginUuid = loginUuid;
	}
	
	/**
	 * 用户登录sessionId
	 * @return the loginSessionId
	 */
	public String getLoginSessionId() {
		return loginSessionId;
	}

	/**
	 * 用户登录sessionId
	 * @param loginSessionId the loginSessionId to set
	 */
	public void setLoginSessionId(String loginSessionId) {
		this.loginSessionId = loginSessionId;
	}

	/**
	 * 登录人员uuid
	 * @return the loginUserUuid
	 */
	public String getLoginUserUuid() {
		return loginUserUuid;
	}

	/**
	 * 登录人员uuid
	 * @param loginUserUuid the loginUserUuid to set
	 */
	public void setLoginUserUuid(String loginUserUuid) {
		this.loginUserUuid = loginUserUuid;
	}

	/**
	 * 登录人员id
	 * @return the loginUserId
	 */
	public String getLoginUserId() {
		return loginUserId;
	}

	/**
	 * 登录人员id
	 * @param loginUserId the loginUserId to set
	 */
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	/**
	 * 登录人员名称
	 * @return the loginUserName
	 */
	public String getLoginUserName() {
		return loginUserName;
	}

	/**
	 * 登录人员名称
	 * @param loginUserName the loginUserName to set
	 */
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	/**
	 * 登录人员部门uuid
	 * @return the loginOrgUuid
	 */
	public String getLoginOrgUuid() {
		return loginOrgUuid;
	}

	/**
	 * 登录人员部门uuid
	 * @param loginOrgUuid the loginOrgUuid to set
	 */
	public void setLoginOrgUuid(String loginOrgUuid) {
		this.loginOrgUuid = loginOrgUuid;
	}

	/**
	 * 登录人员部门名称
	 * @return the loginOrgName
	 */
	public String getLoginOrgName() {
		return loginOrgName;
	}

	/**
	 * 登录人员部门名称
	 * @param loginOrgName the loginOrgName to set
	 */
	public void setLoginOrgName(String loginOrgName) {
		this.loginOrgName = loginOrgName;
	}

	/**
	 * 登录人员部门编码
	 * @return the loginOrgCode
	 */
	public String getLoginOrgCode() {
		return loginOrgCode;
	}

	/**
	 * 登录人员部门编码
	 * @param loginOrgCode the loginOrgCode to set
	 */
	public void setLoginOrgCode(String loginOrgCode) {
		this.loginOrgCode = loginOrgCode;
	}

	/**
	 * 登录人员部门上级
	 * @return the loginOrgParentUuid
	 */
	public String getLoginOrgParentUuid() {
		return loginOrgParentUuid;
	}

	/**
	 * 登录人员部门上级
	 * @param loginOrgParentUuid the loginOrgParentUuid to set
	 */
	public void setLoginOrgParentUuid(String loginOrgParentUuid) {
		this.loginOrgParentUuid = loginOrgParentUuid;
	}

	/**
	 * 登录时间
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 登录时间
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	
}
