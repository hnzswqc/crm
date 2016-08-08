/*
 * @项目名称: crm
 * @文件名称: WorkChechServiceImpl.java
 * @日期: 2015-12-14 下午05:02:28  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.workcheck.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.util.DataUtil;
import com.hnzskj.oa.workcheck.bean.WorkCheck;
import com.hnzskj.oa.workcheck.dao.IWorkCheckDao;
import com.hnzskj.oa.workcheck.service.IWorkCheckService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：WorkChechServiceImpl.java   <br/>
 * 类描述：   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-14 下午05:02:28   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-14 下午05:02:28   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class WorkChechServiceImpl implements IWorkCheckService {

	/**
	 * dao层接口
	 */
	@Autowired
	private IWorkCheckDao workCheckDao = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.service.IWorkCheckService#addWorkDao(java.util.List, com.hnzskj.oa.workcheck.bean.WorkCheck)
	 */
	public boolean addWorkCheck(List<WorkCheck> list, WorkCheck workCheck) {
		List<WorkCheck> l =  getWorkCheckList(list);
		Object[][]params = new Object[l.size()][];
		int i = 0;
		for (WorkCheck wc :l) {
				Object[]param = new Object[]{
					StringUtils.isNotEmpty(wc.getUuid())?wc.getUuid():UUID.randomUUID().toString(),
					workCheck.getWcYear(),
					workCheck.getWcMonth(),
					workCheck.getWcStartDate(),
					workCheck.getWcEndDate(),
					workCheck.getWcAllDay(),
					wc.getUserNumber(),
					wc.getUserName(),
					wc.getRoleName(),
					wc.getWcCheckDay(),
					wc.getWcAddDay(),
					wc.getWcAddHour(),
					StringUtils.isNotEmpty(wc.getJoinTime())?wc.getJoinTime():null,
					wc.getWcNote(),
					wc.getOrgUuid(),
					wc.getOrgName(),
					DataUtil.formateDefaultDate()
				};
				params[i] = param;
				i++;
		}
		int result =  workCheckDao.addWorkCheck(params);
		return result>0?true:false;
	}

	/**
	 * 
	 * 方法描述：处理为空的 数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2016-3-23 下午04:49:53<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<WorkCheck> getWorkCheckList(List<WorkCheck> list){
		List<WorkCheck> l = new ArrayList<WorkCheck>();
		for (WorkCheck wc : list) {
			if(StringUtils.isNotEmpty(wc.getUserUuid())){
				l.add(wc);
			}
		}
		return l;
	}
	
	public boolean delWorkCheck(WorkCheck workCheck) {
		int result = workCheckDao.delWorkCheck(getSqlCondition(workCheck));
		return result>=0?true:false;
	}

	/**
	 * 
	 * 方法描述：获取查询条件<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-14 下午05:18:53<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private String getSqlCondition(WorkCheck workCheck){
		StringBuffer sb = new StringBuffer(" WHERE 1=1 ");
		if(null!=workCheck){
			//年度
			if(StringUtils.isNotEmpty(workCheck.getWcYear())){
				sb.append(" AND WC_YEAR = '").append(workCheck.getWcYear()).append("'");
			}else{
				sb.append(" AND WC_YEAR = '").append(DataUtil.getYear()).append("'");
			}
			//月份
			if(StringUtils.isNotEmpty(workCheck.getWcMonth())){
				sb.append(" AND WC_MONTH = '").append(workCheck.getWcMonth()).append("'");
			}
			//工号
			if(StringUtils.isNotEmpty(workCheck.getUserNumber())){
				sb.append(" AND USER_NUMBER = '").append(workCheck.getUserNumber()).append("'");
			}
			//姓名
			if(StringUtils.isNotEmpty(workCheck.getUserName())){
				sb.append(" AND USER_NAME like '%").append(workCheck.getUserName()).append("%'");
			}
			//所属部门
			if(StringUtils.isNotEmpty(workCheck.getOrgUuid())){
				sb.append(" AND ORG_UUID = '").append(workCheck.getOrgUuid()).append("'");
			}
			//主键
			if(StringUtils.isNotEmpty(workCheck.getUuid())){
				sb.append(" AND UUID = '").append(workCheck.getUuid()).append("'");
			}
		}
		return sb.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.service.IWorkCheckService#searchWorkCheckList(com.hnzskj.common.bean.PageBean, com.hnzskj.oa.workcheck.bean.WorkCheck)
	 */
	public PageBean<WorkCheck> searchWorkCheckList(
			PageBean<WorkCheck> pageBean, WorkCheck workCheck) {
		String fields=" UUID,WC_YEAR AS WCYEAR,WC_MONTH AS WCMONTH,WC_START_DATE AS WCSTARTDATE,WC_END_DATE AS WCENDDATE,WC_ALL_DAY AS WCALLDAY,USER_NUMBER AS USERNUMBER,USER_NAME AS USERNAME,ROLE_NAME AS ROLENAME,WC_CHECK_DAY AS WCCHECKDAY,WC_ADD_DAY AS WCADDDAY,WC_ADD_HOUR AS WCADDHOUR,JOIN_TIME AS JOINTIME,WC_NOTE AS WCNOTE,ORG_UUID AS ORGUUID,ORG_NAME AS ORGNAME,CREATE_TIME AS CREATETIME,USER_UUID AS USERUUID ";
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("WC_YEAR", "DESC");
		orderby.put("WC_MONTH", "DESC");
		orderby.put("ORG_NAME", "ASC");
		orderby.put("USER_NUMBER", "ASC");
		pageBean = workCheckDao.searchWorkCheckList(pageBean, fields, getSqlCondition(workCheck), null, orderby, " APP_SYSTEM_WORK_CHECK ", " UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.service.IWorkCheckService#updWorkCheck(com.hnzskj.oa.workcheck.bean.WorkCheck)
	 */
	public boolean updWorkCheck(WorkCheck workCheck) {
		int result = workCheckDao.updWorkCheck(workCheck);
		return result>0?true:false;
	}


	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.service.IWorkCheckService#addWorkCheck(com.hnzskj.oa.workcheck.bean.WorkCheck)
	 */
	public boolean addWorkCheck(WorkCheck workCheck) {
		int result = workCheckDao.addWorkCheck(workCheck);
		return result>0?true:false;
	}


	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.service.IWorkCheckService#addValidate(com.hnzskj.oa.workcheck.bean.WorkCheck)
	 */
	public boolean addValidate(WorkCheck workCheck) {
		if(null!=workCheck){
			int result = workCheckDao.addValidate(workCheck.getWcYear(), workCheck.getWcMonth(), workCheck.getUserNumber());
			return result>0?true:false;
		}
		return false;
	}
	
		

}
