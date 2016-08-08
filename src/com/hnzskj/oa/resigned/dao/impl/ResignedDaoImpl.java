/*
 * @项目名称: crm
 * @文件名称: ResignedDaoImpl.java
 * @日期: 2015-12-2 下午08:10:22  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.resigned.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.DataUtil;
import com.hnzskj.oa.common.util.MfConstant;
import com.hnzskj.oa.resigned.bean.Resigned;
import com.hnzskj.oa.resigned.dao.IResignedDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ResignedDaoImpl.java   <br/>
 * 类描述： 辞职员工dao层实现类别  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-2 下午08:10:22   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-2 下午08:10:22   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class ResignedDaoImpl extends BaseDao implements IResignedDao{

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedDao#addResigned(com.hnzskj.oa.resigned.bean.Resigned)
	 */
	public int addResigned(Resigned resigned) {
		String sql="INSERT INTO APP_SYSTEM_RESIGNED(RES_UUID,USER_UUID,USER_NAME,USER_NUMBER,ROLE_NAME,USER_GENDER,RES_APPLY_DATE,RES_APPROVE_DATE,RES_TYPE,RES_MONEY_TYPE,USER_MOBILE,USER_BANK_CARD,RES_RESON,RES_HANDOVER,RES_TOOL_RETURN,RES_OFFICE_GOODS,RES_CHECK_WORK,RES_FINANCIAL_LOAN,RES_DEDUCT_WAGES,RES_REALY_WAGES,FILE_NAME,FILE_CONTENT,FILE_SIZE,FILE_TYPE,CREATE_TIME,CREATE_USER_UUID,CREATE_USER_NAME,RES_NOTE,RES_DEDUCT_WAGES_ITEM,RES_STATE,RES_CHECK_WORK_NUM)" +
				   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[]params = new Object[]{
				null!=resigned.getResUuid()&&!"".equals(resigned.getResUuid())?resigned.getResUuid():UUID.randomUUID().toString(),
				resigned.getUserUuid(),
				resigned.getUserName(),
				resigned.getUserNumber(),
				resigned.getRoleName(),
				resigned.getUserGenderText(),
				null!=resigned.getResApplyDate()&&!"".equals(resigned.getResApplyDate())?resigned.getResApplyDate():null,
				resigned.getResApproveDate(),
				resigned.getResType(),
				resigned.getResMoneyType(),
				resigned.getUserMobile(),
				resigned.getUserBankCard(),
				resigned.getResReson(),
				resigned.getResHandOver(),
				resigned.getResToolReturn(),
				resigned.getResOfficeGoods(),
				resigned.getResCheckWork(),
				resigned.getResFinancialLoan(),
				resigned.getResDeductWages(),
				resigned.getResRealyWages(),
				resigned.getFileName(),
				resigned.getFileContent(),
				resigned.getFileSize(),
				resigned.getFileType(),
				DataUtil.formateDefaultDate(),
				resigned.getCreateUserUuid(),
				resigned.getCreateUserName(),
				resigned.getResNote(),
				resigned.getResDeductWagesItem(),
				MfConstant.DIC_RESIGNED_STATE_NO,
				resigned.getResCheckWorkNum()
		};
		String sql2 = "update APP_SYSTEM_USER set user_state = ? where user_uuid = ? ";
		Object[]params2 = new Object[]{MfConstant.DIC_USER_STATE_SUSPENDED,resigned.getUserUuid()};
		int result = update(new String[]{sql,sql2}, new Object[][]{params,params2});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedDao#delResigned(com.hnzskj.oa.resigned.bean.Resigned)
	 */
	public int delResigned(Resigned resigned) {
		String sql="DELETE FROM APP_SYSTEM_RESIGNED WHERE RES_UUID = ? ";
		String sql2="DELETE FROM APP_SYSTEM_RESIGNED_MONTH WHERE USER_UUID = ? ";
		String sql3="DELETE FROM APP_SYSTEM_USER WHERE USER_UUID = ? ";
		int result = update(new String[]{sql,sql2,sql3}, new Object[][]{new Object[]{resigned.getResUuid()},new Object[]{resigned.getUserUuid()},new Object[]{resigned.getUserUuid()}});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedDao#getResignedByParams(java.lang.String)
	 */
	public Resigned getResignedByParams(String sqlCondition) {
		String sql="SELECT FILE_NAME AS FILENAME,FILE_CONTENT AS INPUTSTREAM,FILE_SIZE AS FILESIZE,FILE_TYPE AS FILETYPE FROM APP_SYSTEM_RESIGNED "+sqlCondition;
		Resigned resigned = new Resigned();
		try {
			conn = new BaseDao().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resigned.setInputStream(rs.getBinaryStream("INPUTSTREAM"));
				resigned.setFileName(rs.getString("FILENAME"));
				resigned.setFileType(rs.getString("FILETYPE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resigned;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedDao#searchResignedList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Resigned> searchResignedList(PageBean<Resigned> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Resigned> empls = new ArrayList<Resigned>();//封装查询结果集
		int totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		PageBean<Resigned> epage = new PageBean<Resigned>();//临时变量，如果在page为null的情况下使用
		
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
			empls = query(sql, Resigned.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from "+tableName+" " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, Resigned.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedDao#updResigned(com.hnzskj.oa.resigned.bean.Resigned)
	 */
	public int updResigned(Resigned resigned) {
		String sql="";
		Object[]params = null;
		if(null!=resigned.getFileName()&&!"".equals(resigned.getFileName())){
			 sql="UPDATE APP_SYSTEM_RESIGNED SET USER_UUID = ?,USER_NAME = ?,USER_NUMBER = ?,ROLE_NAME = ?,USER_GENDER = ?,RES_APPLY_DATE = ?,RES_APPROVE_DATE = ?,RES_TYPE = ?,RES_MONEY_TYPE = ?,USER_MOBILE = ?,USER_BANK_CARD = ?,RES_RESON = ?,RES_HANDOVER = ?,RES_TOOL_RETURN = ?,RES_OFFICE_GOODS = ?,RES_CHECK_WORK = ?,RES_FINANCIAL_LOAN = ?," +
			 	 "RES_DEDUCT_WAGES = ?,RES_REALY_WAGES = ?,FILE_NAME = ?,FILE_CONTENT = ?,FILE_SIZE = ?,FILE_TYPE = ?,RES_NOTE = ?,RES_DEDUCT_WAGES_ITEM = ?,RES_CHECK_WORK_NUM = ?  WHERE RES_UUID = ?";
			 params = new Object[]{
				resigned.getUserUuid(),
				resigned.getUserName(),
				resigned.getUserNumber(),
				resigned.getRoleName(),
				resigned.getUserGenderText(),
				null!=resigned.getResApplyDate()&&!"".equals(resigned.getResApplyDate())?resigned.getResApplyDate():null,
				resigned.getResApproveDate(),
				resigned.getResType(),
				resigned.getResMoneyType(),
				resigned.getUserMobile(),
				resigned.getUserBankCard(),
				resigned.getResReson(),
				resigned.getResHandOver(),
				resigned.getResToolReturn(),
				resigned.getResOfficeGoods(),
				resigned.getResCheckWork(),
				resigned.getResFinancialLoan(),
				resigned.getResDeductWages(),
				resigned.getResRealyWages(),
				resigned.getFileName(),
				resigned.getFileContent(),
				resigned.getFileSize(),
				resigned.getFileType(),
				resigned.getResNote(),
				resigned.getResDeductWagesItem(),
				resigned.getResCheckWorkNum(),
				resigned.getResUuid()
			 };
		}else{
			 sql="UPDATE APP_SYSTEM_RESIGNED SET USER_UUID = ?,USER_NAME = ?,USER_NUMBER = ?,ROLE_NAME = ?,USER_GENDER = ?,RES_APPLY_DATE = ?,RES_APPROVE_DATE = ?,RES_TYPE = ?,RES_MONEY_TYPE = ?,USER_MOBILE = ?,USER_BANK_CARD = ?,RES_RESON = ?,RES_HANDOVER = ?,RES_TOOL_RETURN = ?,RES_OFFICE_GOODS = ?,RES_CHECK_WORK = ?,RES_FINANCIAL_LOAN = ?," +
			 	 "RES_DEDUCT_WAGES = ?,RES_REALY_WAGES = ?,RES_NOTE = ? ,RES_DEDUCT_WAGES_ITEM = ?,RES_CHECK_WORK_NUM = ? WHERE RES_UUID = ? ";
				 params = new Object[]{
					resigned.getUserUuid(),
					resigned.getUserName(),
					resigned.getUserNumber(),
					resigned.getRoleName(),
					resigned.getUserGenderText(),
					null!=resigned.getResApplyDate()&&!"".equals(resigned.getResApplyDate())?resigned.getResApplyDate():null,
					resigned.getResApproveDate(),
					resigned.getResType(),
					resigned.getResMoneyType(),
					resigned.getUserMobile(),
					resigned.getUserBankCard(),
					resigned.getResReson(),
					resigned.getResHandOver(),
					resigned.getResToolReturn(),
					resigned.getResOfficeGoods(),
					resigned.getResCheckWork(),
					resigned.getResFinancialLoan(),
					resigned.getResDeductWages(),
					resigned.getResRealyWages(),
					resigned.getResNote(),
					resigned.getResDeductWagesItem(),
					resigned.getResCheckWorkNum(),
					resigned.getResUuid()
				 };
		}
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedDao#updResigned(java.lang.String, java.lang.String)
	 */
	public int updResigned(String resUuid,String userUuid) {
		String sql="UPDATE APP_SYSTEM_RESIGNED SET RES_STATE = ? WHERE RES_UUID = ?";
		String delSql="DELETE FROM APP_SYSTEM_USER WHERE USER_UUID = ? ";
		int result = update(new String[]{sql,delSql}, new Object[][]{new Object[]{MfConstant.DIC_RESIGNED_STATE_YES,resUuid},new Object[]{userUuid}});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedDao#updUserState(java.lang.String)
	 */
	public int updUserState(String userUuid) {
		String sql="UPDATE APP_SYSTEM_USER SET USER_STATE = ? WHERE USER_UUID = ? ";
		int result = update(sql, new Object[]{1,userUuid});
		return result;
	}

	
	
}
