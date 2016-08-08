/*
 * @项目名称: crm
 * @文件名称: KqszDaoImpl.java
 * @日期: 2016-3-4 下午08:02:56  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.kqsz.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.DataUtil;
import com.hnzskj.oa.kqsz.bean.Kqsz;
import com.hnzskj.oa.kqsz.dao.IKqszDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：KqszDaoImpl.java   <br/>
 * 类描述： 考勤设置dao层实现类  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2016-3-4 下午08:02:56   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2016-3-4 下午08:02:56   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class KqszDaoImpl extends BaseDao implements IKqszDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.kqsz.dao.KqszDao#addKqsz(com.hnzskj.oa.kqsz.bean.Kqsz)
	 */
	public int addKqsz(Kqsz kqsz) {
		String sql="INSERT INTO APP_SYSTEM_KQSZ(UUID,YEAR,MONTH,MQTS,KQSTARTTIME,KQENDTIME,NOTE,CREATETIME) VALUES(?,?,?,?,?,?,?,?)";
		Object[]params = new Object[]{
				null!=kqsz.getUuid()&&!"".equals(kqsz.getUuid())?kqsz.getUuid():UUID.randomUUID().toString(),
				kqsz.getYear(),
				kqsz.getMonth(),
				kqsz.getMqts(),
				kqsz.getKqStartTime(),
				kqsz.getKqEndTime(),
				kqsz.getNote(),
				DataUtil.formateDate(new Date())
				
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.kqsz.dao.KqszDao#delKqsz(com.hnzskj.oa.kqsz.bean.Kqsz)
	 */
	public int delKqsz(Kqsz kqsz) {
		String sql="DELETE FROM APP_SYSTEM_KQSZ WHERE UUID = ? ";
		int result = update(sql, new Object[]{kqsz.getUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.kqsz.dao.KqszDao#getKqsz(java.lang.String)
	 */
	public Kqsz getKqsz(String sqlCondition) {
		String sql="SELECT UUID,YEAR,MONTH,MQTS,KQSTARTTIME,KQENDTIME,NOTE,CREATETIME FROM APP_SYSTEM_KQSZ "+sqlCondition;
		Kqsz kqsz = (Kqsz)get(sql, Kqsz.class,null);
		return kqsz;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.kqsz.dao.KqszDao#getKqszList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Kqsz> getKqszList(PageBean<Kqsz> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		
		List<Kqsz> empls = new ArrayList<Kqsz>();//封装查询结果集
		int totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		PageBean<Kqsz> epage = new PageBean<Kqsz>();//临时变量，如果在page为null的情况下使用
		
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
			empls = query(sql, Kqsz.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from "+tableName+" " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, Kqsz.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.kqsz.dao.KqszDao#updKqsz(com.hnzskj.oa.kqsz.bean.Kqsz)
	 */
	public int updKqsz(Kqsz kqsz) {
		String sql="UPDATE APP_SYSTEM_KQSZ SET YEAR = ? ,MONTH = ? ,MQTS = ? ,KQSTARTTIME = ? ,KQENDTIME = ?,NOTE = ?  WHERE UUID = ? ";
		Object[]params = new Object[]{
				kqsz.getYear(),
				kqsz.getMonth(),
				kqsz.getMqts(),
				kqsz.getKqStartTime(),
				kqsz.getKqEndTime(),
				kqsz.getNote(),
				kqsz.getUuid()
		};
		int result = update(sql, params);
		return result;
	}

}
