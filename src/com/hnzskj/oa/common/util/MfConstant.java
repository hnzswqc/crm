/*
 * @项目名称: crm
 * @文件名称: MfConstant.java
 * @日期: 2015-8-19 上午09:42:22  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.common.util;

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
	 * 物品类别
	 */
	public static String DIC_PURCHASE_TYPE="PURCHASE_TYPE";
	
	/**
	 * 办公物品领取状态
	 */
	public static String DIC_OFFICEGOODS_STATE="OFFICEGOODS_STATE";
	
	/**
	 * 已经归还
	 */
	public static String DIC_OGS_STATE_YES = "OGS_STATE_YES";
	
	/**
	 * 正式员工状态
	 */
	public static String DIC_USER_STATE_ACTIVITY="1";
	
	/**
	 * 离职员工状态
	 */
	public static String DIC_USER_STATE_SUSPENDED="2";
	
	/**
	 * 员工辞职类别
	 */
	public static String DIC_RESIGNED_TYPE="RESIGNED_TYPE";
	
	/**
	 * 工资发放方式
	 */
	public static String DIC_RESIGNED_MONEY_TYPE="RESIGNED_MONEY_TYPE";
	
	/**
	 * 立即结算
	 */
	public static String DIC_RESIGNED_MONEY_TYPE_LJFF ="RESIGNED_MONEY_TYPE_LJFF";
	
	/**
	 * 工资结算状态
	 */
	public static String DIC_RESIGNED_STATE="RESIGNED_STATE";
	
	/**
	 * 工资结算状态，已结算
	 */
	public static String DIC_RESIGNED_STATE_YES="RESIGNED_STATE_YES";
	
	/**
	 * 工资结算状态，部分结算
	 */
	public static String DIC_RESIGNED_STATE_PART="RESIGNED_STATE_PART";
	
	/**
	 * 工资结算状态，未结算
	 */
	public static String DIC_RESIGNED_STATE_NO="RESIGNED_STATE_NO";
	
	/**
	 * 试用期员工状态
	 */
	public static String DIC_USER_STATE_SYQYG="4";
	
	/**
	 * 数据字典-是否存在工资卡
	 */
	public static String DIC_BANK_CARD_STATE="BANK_CARD_STATE";
	
	/**
	 * 数据字典-存在工资卡
	 */
	public static String DIC_BANK_CARD_STATE_HAVE = "BANK_CARD_STATE_HAVE";
	
	/**
	 * 数据字典-没有工资卡
	 */
	public static String DIC_BANK_CARD_STATE_NO = "BANK_CARD_STATE_NO";
	
	
	
	
}
