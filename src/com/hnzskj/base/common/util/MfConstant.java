/*
 * @项目名称: crm
 * @文件名称: MfConstant.java
 * @日期: 2015-8-19 上午09:42:22  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.common.util;

import com.hnzskj.common.util.Constant;

/**    
 * 项目名称：crm   <br/>
 * 类名称：MfConstant.java   <br/>
 * 类描述：主界面常量实体   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-19 上午09:42:22   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-19 上午09:42:22   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class MfConstant extends Constant {

	/**
	 * 默认根节点uuid
	 */
	public static  String DEFAULT_ROOT_UUID="0000-0000-0000-0000";
	
	/**
	 * 启用状态
	 */
	public static Integer STATE_ACTIVATED = 1;
	
	/**
	 * 挂起状态
	 */
	public static Integer STATE_SUSPENDED = 2;
	
	
	/**
	 * 默认密码
	 */
	public static String DEFAULT_PASSWORD="1";
	
	/**
	 * 数据字典-状态
	 */
	public static String DIC_STATE="STATE"; 
	
	/**
	 * 数据字典-人员状态
	 */
	public static String DIC_USER_STATE="USER_STATE";
	
	/**
	 * 数据字典-人员学历
	 */
	public static String DIC_USER_DEGREES="USER_DEGREES";
	
	/**
	 * 数据字典-性别
	 */
	public static String DIC_GENDER="GENDER";
	
	/**
	 * 数据字典-功能连接状态
	 */
	public static String DIC_POWER_STATE="POWER_STATE";
	
	/**
	 * 数据字典-组织机构状态
	 */
	public static String DIC_ORG_STATE="ORG_STATE";
	
	/**
	 * 数据字典-组织机构级别
	 */
	public static String DIC_ORG_GRADE_LEVEL="ORG_GRADE_LEVEL";
	
	/**
	 * 数据字典-角色信息
	 */
	public static String DIC_ROLE_STATE="ROLE_STATE";
	
	/**
	 * 子系统数据字典
	 */
	public static String DIC_SUB_STATE="SUB_STATE";
	
	/**
	 * 子系统权限模块
	 */
	public static String AUTHORITY_SUBSYSTEM="AUTHORITY_SUBSYSTEM";
	
	/**
	 * 功能权限模块
	 */
	public static String AUTHORITY_MODEL="AUTHORITY_MODEL";
	
	/**
	 * 功能权限链接
	 */
	public static String AUTHORITY_POWER="AUTHORITY_POWER";
	
	/**
	 * 功能权限操作
	 */
	public static String AUTHORITY_OPERATE="AUTHORITY_OPERATE";
	
	/**
	 * 个人申请
	 */
	public static String USER_APPLY="USER_APPLY";
	
	/**
	 * 个人审批
	 */
	public static String USER_APPROVE="USER_APPROVE";
	
	
	/**
	 * 授权
	 */
	public static String USER_AUTHORITY="USER_AUTHORITY";
	
	/**
	 * 他人授权
	 */
	public static String OTHER_AUTHORITY="OTHER_AUTHORITY";
	
	
	
	/**
	 * 管理员授权
	 */
	public static String ADMIN_AUTHORITY="ADMIN_AUTHORITY";
	
	
	/**
	 * 授权申请类别数据字典类别
	 */
	public static String DIC_AUTHORITY_STATE = "AUTHORITY_STATE";
	
	/**
	 * 授权待审核
	 */
	public static String DIC_AUTHORITY_STATE_PENDING_AUDIT="PENDING_AUDIT";
	
	/**
	 * 接受申请
	 */
	public static String DIC_AUTHORITY_STATE_AUDIT_THROUGH="AUDIT_THROUGH";
	
	/**
	 * 不接受申请
	 */
	public static String DIC_AUTHORITY_STATE_AUDIT_NOT_THROUGH="AUDIT_NOT_THROUGH";
	
	
	
	
}
