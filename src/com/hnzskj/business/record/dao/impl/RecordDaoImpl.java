/*
 * @项目名称: crm
 * @文件名称: RecordDaoImpl.java
 * @日期: 2015-11-16 上午10:56:05  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.record.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.business.common.util.MfConstant;
import com.hnzskj.business.record.bean.Record;
import com.hnzskj.business.record.dao.IRecordDao;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.DataUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：RecordDaoImpl.java   <br/>
 * 类描述：员工档案记录dao层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-16 上午10:56:05   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-16 上午10:56:05   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class RecordDaoImpl extends BaseDao implements IRecordDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.business.record.dao.IRecordDao#addRecord(com.hnzskj.business.record.bean.Record)
	 */
	public int addRecord(Record record) {
		String sql="INSERT INTO APP_BUSINESS_RECORD(RECORD_UUID,RECORD_DATE,RECORD_USER_UUID,RECORD_USER_NUMBER,RECORD_USER_NAME,RECORD_USER_GENDER,RECORD_TYPE,RECORD_ATT_NAME,RECORD_ATT_FILE_NAME,RECORD_ATT_TYPE,RECORD_ATT_CONTENT,RECORD_ATT_LENGTH,RECORD_NOTE,DELETE_TYPE,CREATE_TIME,CREATE_USER_UUID,CREATE_USER_NAME)" +
				   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[]params = new Object[]{
			(null!=record.getRecordUuid()&&!"".equals(record.getRecordUuid()))?record.getRecordUuid():UUID.randomUUID().toString(),
			record.getRecordDate(),
			record.getRecordUserUuid(),
			record.getRecordUserNumber(),
			record.getRecordUserName(),
			record.getRecordUserGender(),
			record.getRecordType(),
			record.getRecordAttName(),
			record.getRecordAttFileName(),
			record.getRecordAttType(),
			record.getRecordAttContent(),
			record.getRecordAttLength(),
			record.getRecordNote(),
			MfConstant.DELETE_TYPE_NO,
			DataUtil.formateDate(new Date()),
			record.getCreateUserUuid(),
			record.getCreateUserName()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.record.dao.IRecordDao#delRecord(com.hnzskj.business.record.bean.Record)
	 */
	public int delRecord(Record record) {
		String sql="UPDATE APP_BUSINESS_RECORD SET DELETE_TYPE = ? WHERE RECORD_UUID = ? ";
		int result = update(sql, new Object[]{MfConstant.DELETE_TYPE_YES,record.getRecordUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.record.dao.IRecordDao#getRecordByParam(java.lang.String)
	 */
	public Record getRecordByParam(String sqlCondition) {
		String sql="SELECT RECORD_UUID AS RECORDUUID,RECORD_DATE AS RECORDDATE,RECORD_USER_UUID AS RECORDUSERUUID,RECORD_USER_NUMBER AS RECORDUSERNUMBER,RECORD_USER_NAME AS RECORDUSERNAME,RECORD_USER_GENDER AS RECORDUSERGENDER,RECORD_TYPE AS RECORDTYPE," +
				   "RECORD_ATT_NAME AS RECORDATTNAME,RECORD_ATT_FILE_NAME AS RECORDATTFILENAME,RECORD_ATT_TYPE AS RECORDATTTYPE,RECORD_ATT_CONTENT AS FILECONTENT,RECORD_ATT_LENGTH AS RECORDATTLENGTH,RECORD_NOTE AS RECORDNOTE,DELETE_TYPE AS DELETETYPE,CREATE_TIME AS CREATETIME," +
				   "CREATE_USER_UUID AS CREATEUSERUUID,CREATE_USER_NAME AS CREATEUSERNAME FROM APP_BUSINESS_RECORD " +
				   "WHERE 1=1 "+sqlCondition;
		
		Record record = new Record();
		try {
			conn = new BaseDao().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				record.setFileContent(rs.getBinaryStream("FILECONTENT"));
				record.setRecordAttFileName(rs.getString("RECORDATTFILENAME"));
				record.setRecordType(rs.getString("RECORDTYPE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.record.dao.IRecordDao#searchRecordPage(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Record> searchRecordPage(PageBean<Record> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Record> empls = new ArrayList<Record>();//封装查询结果集
		int totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		PageBean<Record> epage = new PageBean<Record>();//临时变量，如果在page为null的情况下使用
		
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count("+key+") from  "+tableName+" " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.parseInt(getSingleStringValue(countSql, queryParams).toString());
		if ( pageBean != null) {//如果需要分页
			int startNum=(pageBean.getPage() - 1) * pageBean.getLimit();
			//sql="select "+fields+" from "+tableName+" "+sqlCondition + buildOrderBy(orderby)
				//+" limit " +page.getMaxResult()+" offset " + startNum ;
			sql="SELECT SUB.* FROM ( select "+fields+" row_number() over ("+buildOrderBy(orderby)+") rnk from  "+tableName
				+" RES "+sqlCondition+" ) SUB where  SUB.rnk > "+startNum+" AND SUB.rnk <= "+pageBean.getPage()*pageBean.getLimit();
			epage = pageBean;
			//查询结果集
			empls = query(sql, Record.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from "+tableName+" " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, Record.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.record.dao.IRecordDao#updRecord(com.hnzskj.business.record.bean.Record)
	 */
	public int updRecord(Record record) {
		String sql="";
		Object[]params = null;
		if(null!=record&&null!=record.getRecordAttContent()){
			sql="UPDATE APP_BUSINESS_RECORD SET RECORD_DATE = ?,RECORD_USER_UUID = ?,RECORD_USER_NUMBER = ?,RECORD_USER_NAME = ?,RECORD_USER_GENDER = ?,RECORD_TYPE = ?," +
			   "RECORD_ATT_NAME = ?,RECORD_ATT_FILE_NAME = ? ,RECORD_ATT_TYPE = ?,RECORD_ATT_CONTENT = ?,RECORD_ATT_LENGTH = ?,RECORD_NOTE = ? WHERE RECORD_UUID = ? ";
			params = new Object[]{
					record.getRecordDate(),
					record.getRecordUserUuid(),
					record.getRecordUserNumber(),
					record.getRecordUserName(),
					record.getRecordUserGender(),
					record.getRecordType(),
					record.getRecordAttName(),
					record.getRecordAttFileName(),
					record.getRecordAttType(),
					record.getRecordAttContent(),
					record.getRecordAttLength(),
					record.getRecordNote(),
					record.getRecordUuid()
			};
		}else{
			sql="UPDATE APP_BUSINESS_RECORD SET RECORD_DATE = ?,RECORD_USER_UUID = ?,RECORD_USER_NUMBER = ?,RECORD_USER_NAME = ?,RECORD_USER_GENDER = ?,RECORD_TYPE = ?," +
			   "RECORD_ATT_NAME = ?,RECORD_NOTE = ? WHERE RECORD_UUID = ? ";
			params = new Object[]{
					record.getRecordDate(),
					record.getRecordUserUuid(),
					record.getRecordUserNumber(),
					record.getRecordUserName(),
					record.getRecordUserGender(),
					record.getRecordType(),
					record.getRecordAttName(),
					record.getRecordNote(),
					record.getRecordUuid()
			};	
		}
		int result = update(sql, params);
		return result;
	}

}
