/*
 * @项目名称: crm
 * @文件名称: ResignedMonthDaoImpl.java
 * @日期: 2015-12-6 下午01:36:57  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.resigned.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.DataUtil;
import com.hnzskj.oa.common.util.MfConstant;
import com.hnzskj.oa.resigned.bean.Resigned;
import com.hnzskj.oa.resigned.bean.ResignedMonth;
import com.hnzskj.oa.resigned.dao.IResignedMonthDao;

/**    
 * 项目名称：crm   <br/>
 * 类名称：ResignedMonthDaoImpl.java   <br/>
 * 类描述：辞职员工计发月份dao层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-6 下午01:36:57   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-6 下午01:36:57   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class ResignedMonthDaoImpl extends BaseDao implements IResignedMonthDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedMonthDao#addResignedMonth(com.hnzskj.oa.resigned.bean.Resigned, java.lang.String[])
	 */
	public int addResignedMonth(Resigned resigned, String[] months) {
		/*String sql="INSERT INTO APP_SYSTEM_RESIGNED_MONTH(MONTH_UUID,USER_UUID,MONTH_NUM,MONTH_STATE,CLEAR_TIME,CREATE_TIME)" +
				   "VALUES(?,?,?,?,?,?)";
		Object[][]params = new Object[][]{};
		for (int i = 0; i < months.length; i++) {
			Object[]param = new Object[]{
					UUID.randomUUID().toString(),
					resigned.getUserUuid(),
					months[i],
					MfConstant.DIC_RESIGNED_STATE_NO,
					null,
					DataUtil.formateDefaultDate()
			};
			params[i] = param;
		}
		int result = updateBatch(sql, params, months.length);
		return result;*/
		ResignedMonth resignedMonth = null;
		for (int i = 0; i < months.length; i++) {
			resignedMonth = getResignedMonth(resigned.getUserUuid(),months[i]);
			if(null==resignedMonth){
				addResignedMonth(resigned.getUserUuid(), months[i]);
			}
		}
		int result = delResignedMonth(resigned.getUserUuid(), months);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：删除多余的数据<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午02:53:27<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private int delResignedMonth(String userUuid,String []months){
		String month= "";
		for (int i = 0; i < months.length; i++) {
			if(month!=""){
				month+=","+months[i];
			}else{
				month+=months[i];
			}
		}
		String sql="DELETE FROM APP_SYSTEM_RESIGNED_MONTH WHERE USER_UUID = ? AND MONTH_NUM NOT IN("+month+")";
		Object[]params = new Object[]{userUuid};
		int result = update(sql, params);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：添加<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午02:49:25<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int addResignedMonth(String userUuid,String month){
		String sql="INSERT INTO APP_SYSTEM_RESIGNED_MONTH(MONTH_UUID,USER_UUID,MONTH_NUM,MONTH_STATE,CLEAR_TIME,CREATE_TIME)" +
		   "VALUES(?,?,?,?,?,?)";
			Object[]param = new Object[]{
				UUID.randomUUID().toString(),
				userUuid,
				month,
				MfConstant.DIC_RESIGNED_STATE_NO,
				null,
				DataUtil.formateDefaultDate()
		};
		int result =update(sql, param);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：判断是否存在<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午02:45:51<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private ResignedMonth getResignedMonth(String userUuid,String month){
		String sql="SELECT MONTH_UUID AS MONTHUUID,USER_UUID AS USERUUID,MONTH_NUM AS MONTHNUM,MONTH_STATE AS MONTHSTATE,CLEAR_TIME AS CLEARTIME,CREATE_TIME AS CREATETIME FROM APP_SYSTEM_RESIGNED_MONTH WHERE USER_UUID = ? AND MONTH_NUM = ? ";
		ResignedMonth resignedMonth = (ResignedMonth)get(sql, ResignedMonth.class, new Object[]{userUuid,month});
		return resignedMonth;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedMonthDao#delResignedMonth(java.lang.String)
	 */
	public int delResignedMonth(String sqlCondition) {
		String sql="DELETE FROM APP_SYSTEM_RESIGNED_MONTH "+sqlCondition;
		int result = update(sql, null);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedMonthDao#searchResignedMonthList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResignedMonth> searchResignedMonthList(String sqlCondition) {
		String sql="SELECT MONTH_UUID AS MONTHUUID,USER_UUID AS USERUUID,MONTH_NUM AS MONTHNUM,MONTH_STATE AS MONTHSTATE,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_RESIGNED_STATE+"' AND ID = MONTH_STATE) AS MONTHSTATETEXT,CLEAR_TIME AS CLEARTIME,CREATE_TIME AS CREATETIME FROM APP_SYSTEM_RESIGNED_MONTH "+sqlCondition;
		List<ResignedMonth> list = query(sql, ResignedMonth.class, null);
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedMonthDao#updResignedMonth(java.lang.String, java.lang.String)
	 */
	public int updResignedMonth(String monthUuids,String state) {
		String sql="UPDATE APP_SYSTEM_RESIGNED_MONTH SET MONTH_STATE = ?,CLEAR_TIME = ? WHERE MONTH_UUID IN("+monthUuids+")";
		int result = update(sql, new Object[]{state,MfConstant.DIC_RESIGNED_STATE_YES.equals(state)?DataUtil.formateDefaultDate():null});
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.resigned.dao.IResignedMonthDao#updateResigned(java.lang.String)
	 */
	public int updateResigned(String userUuid){
		int  result = getNotClearCount(userUuid);//未完成
		String state = MfConstant.DIC_RESIGNED_STATE_PART;
		String sql="UPDATE APP_SYSTEM_RESIGNED SET RES_STATE =? WHERE USER_UUID = ? ";
		if(result<=0){
			state = MfConstant.DIC_RESIGNED_STATE_YES;
			//sql+=";DELETE FROM APP_SYSTEM_USER WHERE USER_UUID ='"+userUuid+"'";
		}else{
			  result = getClearCount(userUuid);//有完成
			  if(result<=0){
				  state = MfConstant.DIC_RESIGNED_STATE_NO;
			  }
			  
		}
		result = update(sql, new Object[]{state,userUuid});
		return result;
	}
	
	/**
	 * 
	 * 方法描述：获取未结算的月份数量<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午04:51:52<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private int getNotClearCount(String userUuid){
		String sql="select count(1) from APP_SYSTEM_RESIGNED_MONTH where user_uuid = ? and MONTH_STATE = ?  ";
		Integer result =  Integer.parseInt(getSingleStringValue(sql, new Object[]{userUuid,MfConstant.DIC_RESIGNED_STATE_NO}).toString());
		return result;
	}

	/**
	 * 
	 * 方法描述：获取未结算的月份数量<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-6 下午04:51:52<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private int getClearCount(String userUuid){
		String sql="select count(1) from APP_SYSTEM_RESIGNED_MONTH where user_uuid = ? and MONTH_STATE = ?  ";
		Integer result =  Integer.parseInt(getSingleStringValue(sql, new Object[]{userUuid,MfConstant.DIC_RESIGNED_STATE_YES}).toString());
		return result;
	}

}
