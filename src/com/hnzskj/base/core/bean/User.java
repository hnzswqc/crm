/*
 * @项目名称: crm
 * @文件名称: User.java
 * @日期: 2015-8-25 下午02:09:26  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.bean;

import java.util.ArrayList;
import java.util.List;

/**    
 * 项目名称：crm   <br/>
 * 类名称：User.java   <br/>
 * 类描述：用户实体对象   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-25 下午02:09:26   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-25 下午02:09:26   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class User {

	/**
	 * 人员uuid
	 */
	private String userUuid;
	
	/**
	 * 人员登录名称
	 */
	private String userId;
	
	/**
	 * 员工号
	 */
	private String userNumber;
	
	/**
	 * 人员名称
	 */
	private String userName;
	
	/**
	 * 人员密码
	 */
	private String userPassword;
	
	/**
	 * 人员性别
	 */
	private String userGender;
	
	/**
	 * 显示值
	 */
	private String userGenderText;
	
	/**
	 * 办公电话
	 */
	private String userPhone;
	
	/**
	 * 人员状态
	 */
	private String userState;
	
	/**
	 * 人员状态显示值
	 */
	private String userStateText;
	
	/**
	 * 年龄
	 */
	private Integer userAge;
	
	/**
	 * 生日
	 */
	private String userBirthday;
	
	/**
	 * 手机
	 */
	private String userMobile;
	
	/**
	 * 邮箱
	 */
	private String userMail;
	
	/**
	 * 其他联系方式
	 */
	private String userOther;
	
	/**
	 * 毕业院校
	 */
	private String userUnversity;
	
	/**
	 * 学历
	 */
	private String userDegrees;
	
	/**
	 * 学历
	 */
	private String userDegreesText;
	
	/**
	 * 籍贯、住址
	 */
	private String userAddress;
	
	/**
	 * 排序号
	 */
	private Integer userOrderBy;
	
	/**
	 * 备注说明
	 */
	private String userNote;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 入职时间
	 */
	private String joinTime;
	
	/**
	 * 转正时间
	 */
	private String formalTime;
	
	/**
	 * 组织机构Uuid
	 */
	private String orgUuid;
	
	/**
	 * 新密码
	 */
	private String newPassword;
	
	/**
	 * 确认密码
	 */
	private String confirmPassword;
	
	/**
	 * 身份证号
	 */
	private String idCard;
	
	/**
	 * 角色信息Uuid
	 */
	private String roleUuid;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 体检证明
	 */
	private String tjzm;
	
	/**
	 * 入职报告
	 */
	private String rzbg;
	
	/**
	 * 身份证复印件
	 */
	private String sfz;
	
	/**
	 * 申请转正表
	 */
	private String sqzzb;
	
	/**
	 * 银行卡号
	 */
	private String userBankCard;
	
	/**
	 * 银行卡号
	 */
	private String userBankCardText;
	
	/**
	 * 用户角色集合信息
	 */
	private List<Role> roleList = new ArrayList<Role>();
	
	/**
	 * 当前人员所属组织
	 */
	private List<Org> orgList  = new ArrayList<Org>();
	
	/**
	 * 获取组织名称
	 */
	private String orgName;
	
	/**
	 * 数量信息 
	 */
	private int count;
	
	
	/**
	 * 试工日期
	 */
	private String pbfDate;
	
	/**
	 * 部门主管
	 */
	private String pbfLeader;
	
	/**
	 * 试用期基本工资
	 */
	private Float probationNormalPay;
	
	/**
	 * 试用期绩效工资
	 */
	private Float probationMeritPay;
	
	/**
	 * 试用期其他工资
	 */
	private Float probationOtherPay;
	
	/**
	 * 转正基本工资
	 */
	private Float userNormalPay;
	
	/**
	 * 转正绩效工资
	 */
	private Float userMeritPay;
	
	/**
	 * 转正其他工资
	 */
	private Float userOtherPay;		


	/**
	 * 试工日期
	 * @return the pbfDate
	 */
	public String getPbfDate() {
		return pbfDate;
	}

	/**
	 * 试工日期
	 * @param pbfDate the pbfDate to set
	 */
	public void setPbfDate(String pbfDate) {
		this.pbfDate = pbfDate;
	}

	/**
	 * 部门主管
	 * @return the pbfLeader
	 */
	public String getPbfLeader() {
		return pbfLeader;
	}

	/**
	 * 部门主管
	 * @param pbfLeader the pbfLeader to set
	 */
	public void setPbfLeader(String pbfLeader) {
		this.pbfLeader = pbfLeader;
	}
	

	/**
	 * 人员uuid
	 * @return the userUuid
	 */
	public String getUserUuid() {
		return userUuid;
	}

	/**
	 * 人员uuid
	 * @param userUuid the userUuid to set
	 */
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	/**
	 * 人员登录名称
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 人员登录名称
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 人员名称
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 人员名称
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 人员密码
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * 人员密码
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * 办公电话
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * 办公电话
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * 人员状态
	 * @return the userState
	 */
	public String getUserState() {
		return userState;
	}

	/**
	 * 人员状态
	 * @param userState the userState to set
	 */
	public void setUserState(String userState) {
		this.userState = userState;
	}

	/**
	 * 年龄
	 * @return the userAge
	 */
	public Integer getUserAge() {
		return userAge;
	}

	/**
	 * 年龄
	 * @param userAge the userAge to set
	 */
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	/**
	 * 手机
	 * @return the userMobile
	 */
	public String getUserMobile() {
		return userMobile;
	}

	/**
	 * 手机
	 * @param userMobile the userMobile to set
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	/**
	 * 邮箱
	 * @return the userMail
	 */
	public String getUserMail() {
		return userMail;
	}

	/**
	 * 邮箱
	 * @param userMail the userMail to set
	 */
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	/**
	 * 其他联系方式
	 * @return the userOther
	 */
	public String getUserOther() {
		return userOther;
	}

	/**
	 * 其他联系方式
	 * @param userOther the userOther to set
	 */
	public void setUserOther(String userOther) {
		this.userOther = userOther;
	}

	/**
	 * 毕业院校
	 * @return the userUnversity
	 */
	public String getUserUnversity() {
		return userUnversity;
	}

	/**
	 * 毕业院校
	 * @param userUnversity the userUnversity to set
	 */
	public void setUserUnversity(String userUnversity) {
		this.userUnversity = userUnversity;
	}

	/**
	 * 专业
	 * @return the userDegrees
	 */
	public String getUserDegrees() {
		return userDegrees;
	}

	/**
	 * 专业
	 * @param userDegrees the userDegrees to set
	 */
	public void setUserDegrees(String userDegrees) {
		this.userDegrees = userDegrees;
	}

	/**
	 * 籍贯、住址
	 * @return the userAddress
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * 籍贯、住址
	 * @param userAddress the userAddress to set
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	/**
	 * 排序号
	 * @return the userOrderBy
	 */
	public Integer getUserOrderBy() {
		return userOrderBy;
	}

	/**
	 * 排序号
	 * @param userOrderBy the userOrderBy to set
	 */
	public void setUserOrderBy(Integer userOrderBy) {
		this.userOrderBy = userOrderBy;
	}

	/**
	 * 备注说明
	 * @return the userNote
	 */
	public String getUserNote() {
		return userNote;
	}

	/**
	 * 备注说明
	 * @param userNote the userNote to set
	 */
	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}

	/**
	 * 创建时间
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 生日
	 * @return the userBirthday
	 */
	public String getUserBirthday() {
		return userBirthday;
	}

	/**
	 * 生日
	 * @param userBirthday the userBirthday to set
	 */
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	/**
	 * 人员性别
	 * @return the userGender
	 */
	public String getUserGender() {
		return userGender;
	}

	/**
	 * 人员性别
	 * @param userGender the userGender to set
	 */
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	/**
	 * 入职时间
	 * @return the joinTime
	 */
	public String getJoinTime() {
		return joinTime;
	}

	/**
	 * 入职时间
	 * @param joinTime the joinTime to set
	 */
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	/**
	 * 组织机构Uuid
	 * @return the orgUuid
	 */
	public String getOrgUuid() {
		return orgUuid;
	}

	/**
	 * 组织机构Uuid
	 * @param orgUuid the orgUuid to set
	 */
	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}

	/**
	 * 显示值
	 * @return the userGenderText
	 */
	public String getUserGenderText() {
		return userGenderText;
	}

	/**
	 * 显示值
	 * @param userGenderText the userGenderText to set
	 */
	public void setUserGenderText(String userGenderText) {
		this.userGenderText = userGenderText;
	}

	/**
	 * 人员状态显示值
	 * @return the userStateText
	 */
	public String getUserStateText() {
		return userStateText;
	}

	/**
	 * 人员状态显示值
	 * @param userStateText the userStateText to set
	 */
	public void setUserStateText(String userStateText) {
		this.userStateText = userStateText;
	}

	/**
	 * 学历
	 * @return the userDegreesText
	 */
	public String getUserDegreesText() {
		return userDegreesText;
	}

	/**
	 * 学历
	 * @param userDegreesText the userDegreesText to set
	 */
	public void setUserDegreesText(String userDegreesText) {
		this.userDegreesText = userDegreesText;
	}

	/**
	 * 用户角色集合信息
	 * @return the roleList
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * 用户角色集合信息
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 新密码
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * 新密码
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * 确认密码
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * 确认密码
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * 当前人员所属组织
	 * @return the orgList
	 */
	public List<Org> getOrgList() {
		return orgList;
	}

	/**
	 * 当前人员所属组织
	 * @param orgList the orgList to set
	 */
	public void setOrgList(List<Org> orgList) {
		this.orgList = orgList;
	}
	
	/**
	 * 获取组织名称
	 * @return the orgName
	 */
	public String getOrgName() {
		if(null!=orgList&&orgList.size()>0){
			StringBuffer sb = new StringBuffer(200);
			int i = 0;
			for (Org org:orgList) {
				if(i>0){
					sb.append(",");
				}
				sb.append(org.getOrgName());
				i++;
			}
			return sb.toString();
		}
		return orgName;
	}

	/**
	 * 获取组织名称
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 数量信息 
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 数量信息 
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 员工号
	 * @return the userNumber
	 */
	public String getUserNumber() {
		return userNumber;
	}

	/**
	 * 员工号
	 * @param userNumber the userNumber to set
	 */
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	/**
	 * 转正时间
	 * @return the formalTime
	 */
	public String getFormalTime() {
		return formalTime;
	}

	/**
	 * 转正时间
	 * @param formalTime the formalTime to set
	 */
	public void setFormalTime(String formalTime) {
		this.formalTime = formalTime;
	}

	/**
	 * 身份证号
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * 身份证号
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * 角色名称
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 角色名称
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 体检证明
	 * @return the tjzm
	 */
	public String getTjzm() {
		return tjzm;
	}

	/**
	 * 体检证明
	 * @param tjzm the tjzm to set
	 */
	public void setTjzm(String tjzm) {
		this.tjzm = tjzm;
	}

	/**
	 * 入职报告
	 * @return the rzbg
	 */
	public String getRzbg() {
		return rzbg;
	}

	/**
	 * 入职报告
	 * @param rzbg the rzbg to set
	 */
	public void setRzbg(String rzbg) {
		this.rzbg = rzbg;
	}

	/**
	 * 身份证复印件
	 * @return the sfz
	 */
	public String getSfz() {
		return sfz;
	}

	/**
	 * 身份证复印件
	 * @param sfz the sfz to set
	 */
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	

	/**
	 * @return the sqzzb
	 */
	public String getSqzzb() {
		return sqzzb;
	}

	/**
	 * @param sqzzb the sqzzb to set
	 */
	public void setSqzzb(String sqzzb) {
		this.sqzzb = sqzzb;
	}

	/**
	 * 银行卡号
	 * @return the userBankCard
	 */
	public String getUserBankCard() {
		return userBankCard;
	}

	/**
	 * 银行卡号
	 * @param userBankCard the userBankCard to set
	 */
	public void setUserBankCard(String userBankCard) {
		this.userBankCard = userBankCard;
	}

	/**
	 * 银行卡号
	 * @return the userBankCardText
	 */
	public String getUserBankCardText() {
		return userBankCardText;
	}

	/**
	 * 银行卡号
	 * @param userBankCardText the userBankCardText to set
	 */
	public void setUserBankCardText(String userBankCardText) {
		this.userBankCardText = userBankCardText;
	}

	/**
	 * 试用期基本工资
	 * @return the probationNormalPay
	 */
	public Float getProbationNormalPay() {
		return probationNormalPay;
	}

	/**
	 * 试用期基本工资
	 * @param probationNormalPay the probationNormalPay to set
	 */
	public void setProbationNormalPay(Float probationNormalPay) {
		this.probationNormalPay = probationNormalPay;
	}

	/**
	 * 试用期绩效工资
	 * @return the probationMeritPay
	 */
	public Float getProbationMeritPay() {
		return probationMeritPay;
	}

	/**
	 * 试用期绩效工资
	 * @param probationMeritPay the probationMeritPay to set
	 */
	public void setProbationMeritPay(Float probationMeritPay) {
		this.probationMeritPay = probationMeritPay;
	}

	/**
	 * 试用期其他工资
	 * @return the probationOtherPay
	 */
	public Float getProbationOtherPay() {
		return probationOtherPay;
	}

	/**
	 * 试用期其他工资
	 * @param probationOtherPay the probationOtherPay to set
	 */
	public void setProbationOtherPay(Float probationOtherPay) {
		this.probationOtherPay = probationOtherPay;
	}

	/**
	 * 转正基本工资
	 * @return the userNormalPay
	 */
	public Float getUserNormalPay() {
		return userNormalPay;
	}

	/**
	 * 转正基本工资
	 * @param userNormalPay the userNormalPay to set
	 */
	public void setUserNormalPay(Float userNormalPay) {
		this.userNormalPay = userNormalPay;
	}

	/**
	 * 转正绩效工资
	 * @return the userMeritPay
	 */
	public Float getUserMeritPay() {
		return userMeritPay;
	}

	/**
	 * 转正绩效工资
	 * @param userMeritPay the userMeritPay to set
	 */
	public void setUserMeritPay(Float userMeritPay) {
		this.userMeritPay = userMeritPay;
	}

	/**
	 * 转正其他工资
	 * @return the userOtherPay
	 */
	public Float getUserOtherPay() {
		return userOtherPay;
	}

	/**
	 * 转正其他工资
	 * @param userOtherPay the userOtherPay to set
	 */
	public void setUserOtherPay(Float userOtherPay) {
		this.userOtherPay = userOtherPay;
	}

	/**
	 * 角色信息Uuid
	 * @return the roleUuid
	 */
	public String getRoleUuid() {
		return roleUuid;
	}

	/**
	 * 角色信息Uuid
	 * @param roleUuid the roleUuid to set
	 */
	public void setRoleUuid(String roleUuid) {
		this.roleUuid = roleUuid;
	}
	
	
	
}
