/*
 * @项目名称: crm
 * @文件名称: DicDaoImpl.java
 * @日期: 2015-8-26 下午06:06:36  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.base.core.dao.IDicDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：DicDaoImpl.java   <br/>
 * 类描述： 数据字典dao层接口  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-26 下午06:06:36   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-26 下午06:06:36   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class DicDaoImpl extends BaseDao implements IDicDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#addDic(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public int addDic(Dic dic) {
		
		String sql="INSERT INTO APP_SYSTEM_DIC(UUID,ID,TEXT,LABEL,NOTE,PARENTUUID,ORDERBY,TYPE,[KEY],CREATETIME)" +
				   "VALUES(?,?,?,?,?,?,?,?,?,GETDATE())";
		Object[]params = new Object[]{
				null!=dic.getUuid()&&!"".equals(dic.getUuid())?dic.getUuid():UUID.randomUUID().toString(),
				dic.getId(),
				dic.getText(),
				dic.getLabel(),
				dic.getNote(),
				dic.getParentUuid(),
				dic.getOrderBy(),
				dic.getType(),
				dic.getKey()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#addDicType(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public int addDicType(Dic dic) {
		String sql="INSERT INTO APP_SYSTEM_DIC_TYPE(UUID,ID,TEXT,NOTE,ORDERBY,CREATETIME)" +
				   "VALUES(?,?,?,?,?,GETDATE())";
		Object[]params = new Object[]{
				null!=dic.getUuid()&&!"".equals(dic.getUuid())?dic.getUuid():UUID.randomUUID().toString(),
				dic.getId(),
				dic.getText(),
				dic.getNote(),
				dic.getOrderBy()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#delDic(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public int delDic(Dic dic) {
		String sql="DELETE FROM APP_SYSTEM_DIC WHERE UUID = ? ";
		int result = update(sql, new Object[]{dic.getUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#delDicType(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public int delDicType(Dic dic) {
		String sql="DELETE FROM APP_SYSTEM_DIC_TYPE WHERE UUID = ? ";
		int result = update(sql, new Object[]{dic.getUuid()});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#getDicList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Dic> getDicList(PageBean<Dic> pageBean, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Dic> empls = new ArrayList<Dic>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<Dic> epage = new PageBean<Dic>();// 临时变量，如果在page为null的情况下使用
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " ": sqlCondition;
		countSql = "select count(" + key + ") from " + tableName + " " + sqlCondition;
		totalRecords = (Integer) getSingleValue(countSql, queryParams);
		if (pageBean != null) {// 如果需要分页
			if (null != queryParams && queryParams.length > 0) {
				// 将数组的长度扩充为原来的2倍,并使得数组的第n个元素和第n+数组长度的元素的值相等,如此是为了分页查询时参数设置的需要
				Object[] newParamsArray = Arrays.copyOf(queryParams,
						queryParams.length * 2);
				for (int i = 0; i < queryParams.length; i++) {
					newParamsArray[queryParams.length + i] = queryParams[i];
				}
				queryParams = newParamsArray;
			}
			// 如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			// 如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			sql = "select top " + pageBean.getLimit() + " " + fields
					+ " from  " + tableName + " " + sqlCondition;
			if (" ".equals(sqlCondition) == true) {
				sql += " where " + key + " not in (select top "
						+ (pageBean.getPage() - 1) * pageBean.getLimit() + " "
						+ key + "  from " + tableName + " " + sqlCondition
						+ BaseDao.buildOrderBy(orderby) + ")"
						+ BaseDao.buildOrderBy(orderby);
			} else {
				sql += " and " + key + " not in (select top "
						+ (pageBean.getPage() - 1) * pageBean.getLimit() + " "
						+ key + " from " + tableName + sqlCondition
						+ BaseDao.buildOrderBy(orderby) + ")"
						+ BaseDao.buildOrderBy(orderby);
			}
			epage = pageBean;
			// 查询结果集
			empls = query(sql,Dic.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, Dic.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#getDicTypeList()
	 */
	@SuppressWarnings("unchecked")
	public List<Dic> getDicTypeList() {
		String sql=" SELECT UUID,ID,TEXT,NOTE,ORDERBY,CREATETIME FROM APP_SYSTEM_DIC_TYPE ORDER BY ORDERBY ASC ";
		List<Dic> list = query(sql,Dic.class,null);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#updDic(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public int updDic(Dic dic) {
		String sql="UPDATE APP_SYSTEM_DIC SET ID=?,TEXT = ? ,LABEL = ? ,NOTE = ? ,ORDERBY = ? ,parentUuid = ? ,[KEY] = ? " +
				   "WHERE UUID = ? ";
		Object[]params= new Object[]{
				dic.getId(),
				dic.getText(),
				dic.getLabel(),
				dic.getNote(),
				dic.getOrderBy(),
				dic.getParentUuid(),
				dic.getKey(),
				dic.getUuid()
		};
		int reuslt = update(sql, params);
		return reuslt;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#updDicType(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public int updDicType(Dic dic) {
		String sql="UPDATE APP_SYSTEM_DIC_TYPE SET  TEXT = ? ,NOTE = ? ,ORDERBY = ? " +
				   "WHERE UUID = ? ";
		Object[]params= new Object[]{
				dic.getText(),
				dic.getNote(),
				dic.getOrderBy(),
				dic.getUuid()
		};
		int reuslt = update(sql, params);
		return reuslt;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#getDicType(java.lang.String)
	 */
	public Dic getDicType(String id) {
		String sql=" SELECT UUID,ID,TEXT,NOTE,ORDERBY,CREATETIME FROM APP_SYSTEM_DIC_TYPE WHERE ID = ? ";
		Dic dic = (Dic)get(sql, Dic.class,new Object[]{id});
		return dic;
	}
	
	

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#getDic(java.lang.String, java.lang.String)
	 */
	public Dic getDic(String type, String id) {
		String sql=" SELECT UUID,ID,[KEY],TEXT,NOTE,TYPE,LABEL,PARENTUUID,ORDERBY,CREATETIME FROM APP_SYSTEM_DIC WHERE TYPE= ? AND ID = ?  ";
		Dic dic = (Dic)get(sql, Dic.class,new Object[]{type,id});
		return dic;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#getTreeList(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<TreeNode> getTreeList(String type, String parentUuid) {
		String sql="SELECT UUID AS ID,TEXT FROM APP_SYSTEM_DIC WHERE type= ? and  parentUuid = ? order by orderby asc ";
		List<TreeNode> list = query(sql, TreeNode.class,new Object[]{type,parentUuid});
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#getParamList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Dic> getParamList(String type) {
		String sql="SELECT ID,TEXT FROM APP_SYSTEM_DIC WHERE TYPE= ? ORDER BY ORDERBY ASC ";
		List<Dic> list = queryMax(sql, Dic.class, new Object[]{type});
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IDicDao#getDicByKey(java.lang.String, java.lang.String)
	 */
	public Dic getDicByKey(String type, String key) {
		String sql=" SELECT UUID,ID,[KEY],TEXT,LABEL,NOTE,TYPE,PARENTUUID,ORDERBY,CREATETIME FROM APP_SYSTEM_DIC WHERE TYPE= ? AND [KEY] = ?  ";
		Dic dic = (Dic)get(sql, Dic.class,new Object[]{type,key});
		return dic;
	}
	
}
