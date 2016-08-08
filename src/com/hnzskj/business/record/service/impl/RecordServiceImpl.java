/*
 * @项目名称: crm
 * @文件名称: RecordServiceImpl.java
 * @日期: 2015-11-16 上午11:26:10  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.business.record.service.impl;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.business.record.bean.Record;
import com.hnzskj.business.record.dao.IRecordDao;
import com.hnzskj.business.record.service.IRecordService;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：RecordServiceImpl.java   <br/>
 * 类描述：员工档案业务层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-16 上午11:26:10   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-16 上午11:26:10   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class RecordServiceImpl implements IRecordService{
	
	/**
	 * dao层注入
	 */
	@Autowired
	private IRecordDao recordDao = null;

	/* (non-Javadoc)
	 * @see com.hnzskj.business.record.service.IRecordService#addRecord(com.hnzskj.business.record.bean.Record)
	 */
	public boolean addRecord(Record record) {
		int result = recordDao.addRecord(record);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.record.service.IRecordService#delRecord(com.hnzskj.business.record.bean.Record)
	 */
	public boolean delRecord(Record record) {
		int result = recordDao.delRecord(record);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.record.service.IRecordService#getRecordByParam(com.hnzskj.business.record.bean.Record)
	 */
	public Record getRecordByParam(Record record) {
		StringBuffer sb = new StringBuffer(200);
		if(null!=record){
			//查询开始时间
			if(null!=record.getStartDate()&&!"".equals(record.getStartDate())){
				sb.append(" AND RECORD_DATE >= '").append(record.getStartDate()).append("'");
			}
			//查询结束时间
			if(null!=record.getEndDate()&&!"".equals(record.getEndDate())){
				sb.append(" AND RECORD_DATE <= '").append(record.getEndDate()).append("'");
			}
			//员工工号
			if(null!=record.getRecordUserNumber()&&!"".equals(record.getRecordUserNumber())){
				sb.append(" AND RECORD_USER_NUMBER LIKE '%").append(record.getRecordUserNumber()).append("%'");
			}
			//员工姓名
			if(null!=record.getRecordUserName()&&!"".equals(record.getRecordUserName())){
				sb.append(" AND RECORD_USER_NAME LIKE '%").append(record.getRecordUserName()).append("%'");
			}
			//档案类别
			if(null!=record.getRecordType()&&!"".equals(record.getRecordType())){
				sb.append(" AND RECORD_TYPE= '").append(record.getRecordType()).append("'");
			}
			//附件名称
			if(null!=record.getRecordAttName()&&!"".equals(record.getRecordAttName())){
				sb.append(" AND RECORD_ATT_NAME LIKE '%").append(record.getRecordAttName()).append("%'");
			}
			//档案Uuid
			if(null!=record.getRecordUuid()&&!"".equals(record.getRecordUuid())){
				sb.append(" AND RECORD_UUID = '").append(record.getRecordUuid()).append("'");
				
			}
		}
		sb.append(" AND DELETE_TYPE =  '").append(com.hnzskj.business.common.util.MfConstant.DELETE_TYPE_NO).append("'");
		record = recordDao.getRecordByParam(sb.toString());		
		return record;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.record.service.IRecordService#searchRecordPage(com.hnzskj.common.bean.PageBean, com.hnzskj.business.record.bean.Record)
	 */
	public PageBean<Record> searchRecordPage(PageBean<Record> pageBean,
			Record record) {
		String fields="RECORD_UUID AS RECORDUUID,RECORD_DATE AS RECORDDATE,RECORD_USER_UUID AS RECORDUSERUUID,RECORD_USER_NUMBER AS RECORDUSERNUMBER,RECORD_USER_NAME AS RECORDUSERNAME,RECORD_USER_GENDER AS RECORDUSERGENDER,RECORD_TYPE AS RECORDTYPE," +
				      "RECORD_ATT_NAME AS RECORDATTNAME,RECORD_ATT_FILE_NAME AS RECORDATTFILENAME,RECORD_ATT_TYPE AS RECORDATTTYPE,RECORD_ATT_LENGTH AS RECORDATTLENGTH,RECORD_NOTE AS RECORDNOTE,DELETE_TYPE AS DELETETYPE,CREATE_TIME AS CREATETIME," +
				      "CREATE_USER_UUID AS CREATEUSERUUID,CREATE_USER_NAME AS CREATEUSERNAME,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_GENDER+"' AND ID = RECORD_USER_GENDER) AS RECORDUSERGENDERTEXT,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_RECORD_TYPE+"' AND ID = RECORD_TYPE) AS RECORDTYPETEXT, ";
		StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=record){
			//查询开始时间
			if(null!=record.getStartDate()&&!"".equals(record.getStartDate())){
				sb.append(" AND RECORD_DATE >= '").append(record.getStartDate()).append("'");
			}
			//查询结束时间
			if(null!=record.getEndDate()&&!"".equals(record.getEndDate())){
				sb.append(" AND RECORD_DATE <= '").append(record.getEndDate()).append("'");
			}
			//员工工号
			if(null!=record.getRecordUserNumber()&&!"".equals(record.getRecordUserNumber())){
				sb.append(" AND RECORD_USER_NUMBER LIKE '%").append(record.getRecordUserNumber()).append("%'");
			}
			//员工姓名
			if(null!=record.getRecordUserName()&&!"".equals(record.getRecordUserName())){
				sb.append(" AND RECORD_USER_NAME LIKE '%").append(record.getRecordUserName()).append("%'");
			}
			//档案类别
			if(null!=record.getRecordType()&&!"".equals(record.getRecordType())){
				sb.append(" AND RECORD_TYPE= '").append(record.getRecordType()).append("'");
			}
			//附件名称
			if(null!=record.getRecordAttName()&&!"".equals(record.getRecordAttName())){
				sb.append(" AND RECORD_ATT_NAME LIKE '%").append(record.getRecordAttName()).append("%'");
			}
			//所属部门
			if(StringUtils.isNotEmpty(record.getOrgUuid())){
				sb.append(" AND RECORD_USER_UUID IN (SELECT USER_UUID  FROM APP_SYSTEM_ORG_USER WHERE ORG_UUID = '"+record.getOrgUuid()+"' )");
			}
		}
		sb.append(" AND DELETE_TYPE =  '").append(com.hnzskj.business.common.util.MfConstant.DELETE_TYPE_NO).append("'");
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("RECORD_DATE", "DESC");
		orderby.put("CREATE_TIME", "DESC");
		pageBean = recordDao.searchRecordPage(pageBean, fields, sb.toString(), null, orderby, " APP_BUSINESS_RECORD ", " RECORD_UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.business.record.service.IRecordService#updRecord(com.hnzskj.business.record.bean.Record)
	 */
	public boolean updRecord(Record record) {
		int result = recordDao.updRecord(record);
		return result>0?true:false;
	}

}
