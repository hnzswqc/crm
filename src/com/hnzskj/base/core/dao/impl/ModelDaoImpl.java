/*
 * @项目名称: crm
 * @文件名称: ModelDaoImpl.java
 * @日期: 2015-8-10 下午03:05:17  
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
import com.hnzskj.base.core.bean.Model;
import com.hnzskj.base.core.dao.IModelDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ModelDaoImpl.java   <br/>
 * 类描述：模型菜单dao层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-10 下午03:05:17   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-10 下午03:05:17   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class ModelDaoImpl extends BaseDao implements IModelDao {

	
	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.ModelDao#getModelList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Model> getModelList(String subsystemUuid) {
		String sql="SELECT MODEL_UUID AS MODELUUID,MODEL_TITLE AS MODELTITLE,MODEL_ICON AS MODELICON,MODEL_LOGO AS MODELLOGO,MODEL_STATE AS MODELSTATE,MODEL_SUBSYSTEM_UUID AS MODELSUBSYSTEMUUID,MODEL_NOTE AS MODELNOTE,CREATE_TIME AS CREATETIME,MODEL_ORDERBY AS MODELORDERBY FROM APP_SYSTEM_MODEL ";
		List<Model> list = (List<Model>)query(sql, Model.class,null);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IModelDao#addModel(com.hnzskj.mainFrame.core.bean.Model)
	 */
	public int addModel(Model model) {
		String sql="INSERT INTO APP_SYSTEM_MODEL (MODEL_UUID,MODEL_TITLE,MODEL_ICON,MODEL_LOGO,MODEL_STATE,MODEL_SUBSYSTEM_UUID,MODEL_NOTE,MODEL_ORDERBY,CREATE_TIME) " +
				   "VALUES(?,?,?,?,?,?,?,?,GETDATE())";
		Object[]params = new Object[]{
			null!=model.getModelUuid()&&!"".equals(model.getModelUuid())?model.getModelUuid():UUID.randomUUID().toString(),
			model.getModelTitle(),
			model.getModelIcon(),
			model.getModelLogo(),
			model.getModelState(),
			model.getModelSubsystemUuid(),
			model.getModelNote(),
			model.getModelOrderby()
		};
		int result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IModelDao#delModel(java.lang.String)
	 */
	public int delModel(String modelUuid) {
		String sql="DELETE FROM APP_SYSTEM_MODEL WHERE MODEL_UUID = ? ";
		int result = update(sql, new Object[]{modelUuid});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IModelDao#getModel(java.lang.String)
	 */
	public Model getModel(String modelUuid) {
		String sql="SELECT MODEL_UUID AS MODELUID,MODEL_TITLE AS MODELTITLE,MODEL_ICON AS MODELICON,MODEL_LOGO AS MODELLOGO,MODEL_STATE AS MODELSTATE,MODEL_SUBSYSTEM_UUID AS MODELSUBSYSTEMUUID,MODEL_NOTE AS MODELNOTE,MODEL_ORDERBY AS MODELORDERBY,CREATE_TIME AS CREATETIME FROM APP_SYSTEM_MODEL " +
				   "WHERE MODEL_UUID = ? ";
		Model model = (Model)get(sql,Model.class,new Object[]{modelUuid});
		return model;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IModelDao#getModelLost(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<Model> getModelList(PageBean<Model> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key) {
		List<Model> empls = new ArrayList<Model>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String sql = "";// 查询的sql语句
		String countSql = "";// 查询总记录数的sql语句
		PageBean<Model> epage = new PageBean<Model>();// 临时变量，如果在page为null的情况下使用
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
			empls = query(sql,Model.class, queryParams);
		} else {// 如果不需要分页
			sql = "select " + fields + " from " + tableName + " "
					+ sqlCondition +  BaseDao.buildOrderBy(orderby);
			// 查询结果集
			empls = query(sql, Model.class, queryParams);
		}
		// 设置总记录数
		epage.setList(empls);
		// 设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.dao.IModelDao#updModel(com.hnzskj.mainFrame.core.bean.Model)
	 */
	public int updModel(Model model) {
		String sql="UPDATE APP_SYSTEM_MODEL SET MODEL_TITLE = ?,MODEL_ICON = ?,MODEL_LOGO = ? ,MODEL_STATE = ?,MODEL_SUBSYSTEM_UUID = ?,MODEL_NOTE = ?,MODEL_ORDERBY = ? " +
				   "WHERE MODEL_UUID = ? ";
		Object[]params = new Object[]{
				model.getModelTitle(),
				model.getModelIcon(),
				model.getModelLogo(),
				model.getModelState(),
				model.getModelSubsystemUuid(),
				model.getModelNote(),
				model.getModelOrderby(),
				model.getModelUuid()
			};
			int result = update(sql, params);
			return result;
	}

	
	
}
