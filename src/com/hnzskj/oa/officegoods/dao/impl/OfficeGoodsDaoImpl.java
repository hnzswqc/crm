/*
 * @项目名称: crm
 * @文件名称: OfficeGoodsDaoImpl.java
 * @日期: 2015-11-28 下午08:24:35  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.officegoods.dao.impl;

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
import com.hnzskj.oa.officegoods.bean.OfficeGoods;
import com.hnzskj.oa.officegoods.dao.IOfficeGoodsDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：OfficeGoodsDaoImpl.java   <br/>
 * 类描述：办公物品领用dao层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-11-28 下午08:24:35   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-11-28 下午08:24:35   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class OfficeGoodsDaoImpl extends BaseDao implements IOfficeGoodsDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.dao.IOfficeGoodsDao#addOfficeGoods(com.hnzskj.oa.officegoods.bean.OfficeGoods)
	 */
	public int addOfficeGoods(OfficeGoods officeGoods) {
		String sql="INSERT INTO APP_SYSTEM_OFFICE_GOODS(OGS_UUID,OGS_USER_NUMBER,OGS_USER_NAME,OGS_USER_UUID,OGS_DATE,OGS_TYPE,OGS_NUMBER,OGS_STATE,OGS_BACK_DATE,OGS_NOTE,OGS_FILE_NAME,OGS_FILE_CONTENT,OGS_FILE_SIZE,OGS_FILE_TYPE,CREATE_TIME,CREATE_USER_UUID,CREATE_USER_NAME,CREATE_USER_NUMBER)" +
				   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[]params = new  Object[]{
				null!=officeGoods.getOgsUuid()&&!"".equals(officeGoods.getOgsUuid())?officeGoods.getOgsUuid():UUID.randomUUID().toString(),
				officeGoods.getOgsUserNumber(),
				officeGoods.getOgsUserName(),
				officeGoods.getOgsUserUuid(),
				officeGoods.getOgsDate(),
				officeGoods.getOgsType(),
				officeGoods.getOgsNumber(),
				officeGoods.getOgsState(),
				officeGoods.getOgsBackDate(),
				officeGoods.getOgsNote(),
				officeGoods.getOgsFileName(),
				officeGoods.getOgsFileContent(),
				officeGoods.getOgsFileSize(),
				officeGoods.getOgsFileType(),
				DataUtil.formateDefaultDate(),
				officeGoods.getCreateUserUuid(),
				officeGoods.getCreateUserName(),
				officeGoods.getCreateUserNumber()
		};
		int result = update(sql, params);
		if(result>0){	
			if(officeGoods.getOgsState().equals(MfConstant.DIC_OGS_STATE_YES)){
				sql="UPDATE dbo.APP_SYSTEM_PURCHASE SET PURCHASE_ONHAND=PURCHASE_ONHAND+"+officeGoods.getOgsNumber()+"  WHERE PURCHASE_UUID = ? ";
			}else{
				sql="UPDATE dbo.APP_SYSTEM_PURCHASE SET PURCHASE_ONHAND=PURCHASE_ONHAND-"+officeGoods.getOgsNumber()+"  WHERE PURCHASE_UUID = ? ";
			}
			result = update(sql, new Object[]{officeGoods.getOgsType()});
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.dao.IOfficeGoodsDao#delOfficeGoods(com.hnzskj.oa.officegoods.bean.OfficeGoods)
	 */
	public int delOfficeGoods(OfficeGoods officeGoods) {
		String sql="DELETE FROM APP_SYSTEM_OFFICE_GOODS WHERE OGS_UUID = ? ";
		int result = update(sql, new Object[]{officeGoods.getOgsUuid()});
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.dao.IOfficeGoodsDao#getOfficeGoodsByParam(java.lang.String)
	 */
	public OfficeGoods getOfficeGoodsByParam(String sqlCondition) {
		String sql="SELECT OGS_FILE_NAME AS OGSFILENAME,OGS_FILE_CONTENT AS OGSFILECONTENT,OGS_FILE_SIZE AS OGSFILESIZE,OGS_FILE_TYPE AS OGSFILETYPE FROM APP_SYSTEM_OFFICE_GOODS "+sqlCondition;
		OfficeGoods ogs = new OfficeGoods();
		try {
			conn = new BaseDao().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ogs.setFileContent(rs.getBinaryStream("OGSFILECONTENT"));
				ogs.setOgsFileName(rs.getString("OGSFILENAME"));
				ogs.setOgsFileType(rs.getString("OGSFILETYPE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ogs;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.dao.IOfficeGoodsDao#searchOfficeGoodsList(com.hnzskj.common.bean.PageBean, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PageBean<OfficeGoods> searchOfficeGoodsList(
			PageBean<OfficeGoods> pageBean, String fields, String sqlCondition,
			Object[] queryParams, LinkedHashMap<String, String> orderby,
			String tableName, String key) {
		List<OfficeGoods> empls = new ArrayList<OfficeGoods>();//封装查询结果集
		int totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		PageBean<OfficeGoods> epage = new PageBean<OfficeGoods>();//临时变量，如果在page为null的情况下使用
		
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
			empls = query(sql, OfficeGoods.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from "+tableName+" " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, OfficeGoods.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.officegoods.dao.IOfficeGoodsDao#updOfficeGoods(com.hnzskj.oa.officegoods.bean.OfficeGoods)
	 */
	public int updOfficeGoods(OfficeGoods officeGoods) {
		String sql = "";
		Object[] params = null;
		if (null != officeGoods.getOgsFileName()&&!"".equals(officeGoods.getOgsFileName())) {
			sql = "UPDATE APP_SYSTEM_OFFICE_GOODS SET OGS_USER_NUMBER = ? ,OGS_USER_NAME = ? ,OGS_USER_UUID = ? ,OGS_DATE = ? ,OGS_TYPE = ? ,OGS_NUMBER = ? ,OGS_STATE = ? ,OGS_BACK_DATE = ? ,OGS_NOTE = ? ,OGS_FILE_NAME = ? ,OGS_FILE_CONTENT = ? ,OGS_FILE_SIZE = ? ,OGS_FILE_TYPE = ? "
					+ "WHERE OGS_UUID = ?";
			params = new Object[] { officeGoods.getOgsUserNumber(),
					officeGoods.getOgsUserName(), officeGoods.getOgsUserUuid(),
					officeGoods.getOgsDate(), officeGoods.getOgsType(),
					officeGoods.getOgsNumber(), officeGoods.getOgsState(),
					officeGoods.getOgsBackDate(), officeGoods.getOgsNote(),
					officeGoods.getOgsFileName(),
					officeGoods.getOgsFileContent(),
					officeGoods.getOgsFileSize(), officeGoods.getOgsFileType(),
					officeGoods.getOgsUuid() };

		} else {
			sql = "UPDATE APP_SYSTEM_OFFICE_GOODS SET OGS_USER_NUMBER = ? ,OGS_USER_NAME = ? ,OGS_USER_UUID = ? ,OGS_DATE = ? ,OGS_TYPE = ? ,OGS_NUMBER = ? ,OGS_STATE = ? ,OGS_BACK_DATE = ? ,OGS_NOTE = ? "
					+ "WHERE OGS_UUID = ?";
			params = new Object[] { officeGoods.getOgsUserNumber(),
					officeGoods.getOgsUserName(), officeGoods.getOgsUserUuid(),
					officeGoods.getOgsDate(), officeGoods.getOgsType(),
					officeGoods.getOgsNumber(), officeGoods.getOgsState(),
					officeGoods.getOgsBackDate(), officeGoods.getOgsNote(),
					officeGoods.getOgsUuid() };
		}
		int result = update(sql, params);
		return result;
	}

}
