/*
 * @项目名称: crm
 * @文件名称: ProbationStaffServiceImpl.java
 * @日期: 2015-12-7 下午09:30:53  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.probationstaff.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.common.util.MfConstant;
import com.hnzskj.oa.probationstaff.bean.ProbationStaff;
import com.hnzskj.oa.probationstaff.dao.IProbationStaffDao;
import com.hnzskj.oa.probationstaff.service.IProbationStaffService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ProbationStaffServiceImpl.java   <br/>
 * 类描述：   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-7 下午09:30:53   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-7 下午09:30:53   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class ProbationStaffServiceImpl implements IProbationStaffService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IProbationStaffDao probationStaffDao = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.probationstaff.service.IProbationStaffService#addProbationStaff(com.hnzskj.oa.probationstaff.bean.ProbationStaff)
	 */
	public boolean addProbationStaff(ProbationStaff probationStaff) {
		int result = probationStaffDao.addProbationStaff(probationStaff);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.probationstaff.service.IProbationStaffService#delProbationStaff(com.hnzskj.oa.probationstaff.bean.ProbationStaff)
	 */
	public boolean delProbationStaff(ProbationStaff probationStaff) {
		int result = probationStaffDao.delProbationStaff(probationStaff);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.probationstaff.service.IProbationStaffService#searchProbationStaffList(com.hnzskj.common.bean.PageBean, com.hnzskj.oa.probationstaff.bean.ProbationStaff)
	 */
	public PageBean<ProbationStaff> searchProbationStaffList(
			PageBean<ProbationStaff> pageBean, ProbationStaff probationStaff) {
		String fields = "USER_UUID AS USERUUID ,USER_NAME AS USERNAME,USER_GENDER AS USERGENDER,USER_BIRTYDAY AS USERBIRTHDAY,USER_AGE AS USERAGE,PBF_DATE AS PBFDATE,PBF_LEADER AS PBFLEADER,USER_NOTE AS USERNOTE,USER_STATE AS USERSTATE,USER_BIRTYDAY AS USERBIRTYDAY,CREATE_TIME AS CREATETIME, "+
						"PBF_NORMAL_PAY AS PROBATIONNORMALPAY,PBF_MERIT_PAY AS PROBATIONMERITPAY,PBF_OTHER_PAY AS PROBATIONOTHERPAY,USER_NORMAL_PAY AS USERNORMALPAY,USER_MERIT_PAY AS USERMERITPAY,USER_OTHER_PAY AS USEROTHERPAY,ID_CARD AS IDCARD," +
						"(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_GENDER+"' AND ID = USER_GENDER) AS USERGENDERTEXT,"+
						"(SELECT TOP 1 ORG_UUID AS ORGUUID FROM APP_SYSTEM_ORG_USER WHERE USER_UUID = U.USER_UUID) AS ORGUUID,"+
						"(SELECT TOP 1 ROLE_UUID FROM APP_SYSTEM_ROLE_USER WHERE USER_UUID = U.USER_UUID) AS ROLEUUID, "+
						"(SELECT TOP 1 ROLE_NAME FROM APP_SYSTEM_ROLE WHERE ROLE_UUID IN (SELECT ROLE_UUID FROM APP_SYSTEM_ROLE_USER WHERE USER_UUID = U.USER_UUID) ) AS ROLENAME ";
		StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=probationStaff){
			//员工姓名
			if(null!=probationStaff.getUserName()&&!"".equals(probationStaff.getUserName())){
				sb.append(" AND USER_NAME LIKE '%").append(probationStaff.getUserName()).append("%'");
			}
			//所属部门
			if(null!=probationStaff.getOrgUuid()&&!"".equals(probationStaff.getOrgUuid())){
				sb.append(" AND USER_UUID IN(").append("SELECT USER_UUID FROM APP_SYSTEM_ORG_USER WHERE ORG_UUID = '"+probationStaff.getOrgUuid()+"'").append(")");
			}
		}
		//试用期员工
		sb.append(" AND USER_STATE = '").append(MfConstant.DIC_USER_STATE_SYQYG).append("'");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("CREATE_TIME", "DESC");
		pageBean = probationStaffDao.searchProbationStaffList(pageBean, fields, sb.toString(),null, orderby, " APP_SYSTEM_USER U "," USER_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.probationstaff.service.IProbationStaffService#updProbationStaff(com.hnzskj.oa.probationstaff.bean.ProbationStaff)
	 */
	public boolean updProbationStaff(ProbationStaff probationStaff) {
		int result =probationStaffDao.updProbationStaff(probationStaff);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.probationstaff.service.IProbationStaffService#searchOrgRoleList(java.lang.String)
	 */
	public List<Dic> searchOrgRoleList(String orgUuid) {
		List<Dic> list = probationStaffDao.searchOrgRoleList(orgUuid);
		return list;
	}
	
	

}
