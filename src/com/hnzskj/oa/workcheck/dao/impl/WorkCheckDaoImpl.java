/*
 * @项目名称: crm
 * @文件名称: WorkCheckDaoImpl.java
 * @日期: 2015-12-14 下午04:33:52  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.workcheck.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.DataUtil;
import com.hnzskj.oa.workcheck.bean.WorkCheck;
import com.hnzskj.oa.workcheck.dao.IWorkCheckDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：WorkCheckDaoImpl.java   <br/>
 * 类描述：考勤信息dao层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-14 下午04:33:52   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-14 下午04:33:52   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class WorkCheckDaoImpl extends BaseDao implements IWorkCheckDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.dao.IWorkCheckDao#addWorkCheck(java.util.List, com.hnzskj.oa.workcheck.bean.WorkCheck)
	 */
	public int addWorkCheck(List<WorkCheck> list, WorkCheck workCheck) {
		String sql="DELETE FROM APP_SYSTEM_WORK_CHECK WHERE ORG_UUID = ? ";//删除整个部门再进行添加
		update(sql, new Object[]{workCheck.getOrgUuid()});
		
		String addSql="INSERT INTO APP_SYSTEM_WORK_CHECK(UUID,WC_YEAR,WC_MONTH,WC_START_DATE,WC_END_DATE,WC_ALL_DAY,USER_NUMBER,USER_NAME,ROLE_NAME,WC_CHECK_DAY,WC_ADD_DAY,JOIN_TIME,WC_NOTE,ORG_UUID,ORG_NAME,CREATE_TIME)" +
				      "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[][]params = new Object[list.size()][];
		int i = 0;
		for (WorkCheck wc : list) {
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
					StringUtils.isNotEmpty(wc.getJoinTime())?wc.getJoinTime():null,
					wc.getWcNote(),
					wc.getOrgUuid(),
					wc.getOrgName(),
					DataUtil.formateDefaultDate()
			};
			params[i] = param;
			i++;
		}
		int ressult = updateBatch(addSql, params, list.size());
		return ressult;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.dao.IWorkCheckDao#addWorkCheck(java.lang.Object[][])
	 */
	public int addWorkCheck(Object[][] params) {
		String addSql="INSERT INTO APP_SYSTEM_WORK_CHECK(UUID,WC_YEAR,WC_MONTH,WC_START_DATE,WC_END_DATE,WC_ALL_DAY,USER_NUMBER,USER_NAME,ROLE_NAME,WC_CHECK_DAY,WC_ADD_DAY,WC_ADD_HOUR,JOIN_TIME,WC_NOTE,ORG_UUID,ORG_NAME,CREATE_TIME)" +
					  "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int result = updateBatch(addSql, params, params.length);
		return result;
	}



	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.dao.IWorkCheckDao#addWorkCheck(com.hnzskj.oa.workcheck.bean.WorkCheck)
	 */
	public int addWorkCheck(WorkCheck workCheck) {
		String sql="INSERT INTO APP_SYSTEM_WORK_CHECK(UUID,WC_YEAR,WC_MONTH,WC_START_DATE,WC_END_DATE,WC_ALL_DAY,USER_NUMBER,USER_NAME,ROLE_NAME,WC_CHECK_DAY,WC_ADD_DAY,WC_ADD_HOUR,JOIN_TIME,WC_NOTE,ORG_UUID,ORG_NAME,CREATE_TIME,USER_UUID)" +
					  "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[]param = new Object[]{
				StringUtils.isNotEmpty(workCheck.getUuid())?workCheck.getUuid():UUID.randomUUID().toString(),
				workCheck.getWcYear(),
				workCheck.getWcMonth(),
				workCheck.getWcStartDate(),
				workCheck.getWcEndDate(),
				workCheck.getWcAllDay(),
				workCheck.getUserNumber(),
				workCheck.getUserName(),
				workCheck.getRoleName(),
				workCheck.getWcCheckDay(),
				workCheck.getWcAddDay(),
				workCheck.getWcAddHour(),
				StringUtils.isNotEmpty(workCheck.getJoinTime())?workCheck.getJoinTime():null,
				workCheck.getWcNote(),
				workCheck.getOrgUuid(),
				workCheck.getOrgName(),
				DataUtil.formateDefaultDate(),
				workCheck.getUserUuid()
		};
		int result = update(sql, param);
		return result;
	}
	

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.dao.IWorkCheckDao#delWorkCheck(java.lang.String)
	 */
	public int delWorkCheck(String sqlCondition) {
		String sql="DELETE FROM APP_SYSTEM_WORK_CHECK "+sqlCondition;
		int result = update(sql, null);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.dao.IWorkCheckDao#searchWorkCheckList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<WorkCheck> searchWorkCheckList(
			PageBean<WorkCheck> pageBean, String fields, String sqlCondition,
			Object[] queryParams, LinkedHashMap<String, String> orderby,
			String tableName, String key) {
		List<WorkCheck> empls = new ArrayList<WorkCheck>();//封装查询结果集
		int totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		PageBean<WorkCheck> epage = new PageBean<WorkCheck>();//临时变量，如果在page为null的情况下使用
		
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count("+key+") from  "+tableName+" " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.parseInt(getSingleStringValue(countSql, queryParams).toString());
		if ( pageBean != null) {//如果需要分页
			int startNum=(pageBean.getPage() - 1) * pageBean.getLimit();
			//sql="select "+fields+" from "+tableName+" "+sqlCondition + buildOrderBy(orderby)
				//+" limit " +page.getMaxResult()+" offset " + startNum ;
			sql="SELECT SUB.* FROM ( select "+fields+",row_number() over ("+buildOrderBy(orderby)+") rnk from  "+tableName
				+" RES "+sqlCondition+" ) SUB where  SUB.rnk > "+startNum+" AND SUB.rnk <= "+pageBean.getPage()*pageBean.getLimit();
			epage = pageBean;
			//查询结果集
			empls = query(sql, WorkCheck.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from "+tableName+" " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, WorkCheck.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.dao.IWorkCheckDao#updWorkCheck(com.hnzskj.oa.workcheck.bean.WorkCheck)
	 */
	public int updWorkCheck(WorkCheck workCheck) {
		String sql="UPDATE APP_SYSTEM_WORK_CHECK SET WC_YEAR = ?,WC_MONTH = ?,WC_START_DATE = ?,WC_END_DATE = ?,WC_ALL_DAY = ?,WC_ALL_HOUR = ?,USER_NUMBER = ?,USER_NAME = ?,ROLE_NAME = ?,WC_CHECK_DAY = ?,WC_ADD_DAY = ?,JOIN_TIME = ?,WC_NOTE = ?,ORG_UUID = ?,ORG_NAME = ?,USER_UUID = ? " +
				   "WHERE UUID = ? ";
		Object[]param = new Object[]{
				workCheck.getWcYear(),
				workCheck.getWcMonth(),
				workCheck.getWcStartDate(),
				workCheck.getWcEndDate(),
				workCheck.getWcAllDay(),
				workCheck.getUserNumber(),
				workCheck.getUserName(),
				workCheck.getRoleName(),
				workCheck.getWcCheckDay(),
				workCheck.getWcAddDay(),
				workCheck.getWcAddHour(),
				StringUtils.isNotEmpty(workCheck.getJoinTime())?workCheck.getJoinTime():null,
				workCheck.getWcNote(),
				workCheck.getOrgUuid(),
				workCheck.getOrgName(),
				workCheck.getUserUuid(),
				workCheck.getUuid()
		};
		int result = update(sql, param);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.workcheck.dao.IWorkCheckDao#addValidate(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int addValidate(String wcYear, String wcMonth, String userNumber) {
		String sql="SELECT COUNT(1) AS COUNT FROM APP_SYSTEM_WORK_CHECK WHERE WC_YEAR = ? AND WC_MONTH = ? AND USER_NUMBER = ? ";
		int result = Integer.parseInt(getSingleStringValue(sql, new Object[]{wcYear,wcMonth,userNumber}).toString());
		return result;
	}

	
	
	

}
