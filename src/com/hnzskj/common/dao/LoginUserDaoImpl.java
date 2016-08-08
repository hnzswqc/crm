/*
 * @项目名称: crm
 * @文件名称: LoginUserDaoImpl.java
 * @日期: 2015-10-22 下午04:33:41  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.common.dao;

import java.util.Date;
import java.util.List;

import com.hnzskj.base.core.bean.Org;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.common.util.DataUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：LoginUserDaoImpl.java   <br/>
 * 类描述：当前登录用户dao层实现类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-10-22 下午04:33:41   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-10-22 下午04:33:41   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class LoginUserDaoImpl extends BaseDao{

	/**
	 * 
	 * 方法描述：把当前登录用户添加到临时在线表中<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-22 下午04:35:21<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int addLoginUser(User user,String loginSessionId){
		String sql="INSERT INTO APP_SYSTEM_LOGIN_USER(LOGIN_UUID,LOGIN_SESSION_ID,LOGIN_USER_UUID,LOGIN_USER_ID,LOGIN_USER_NAME,LOGIN_ORG_UUID,LOGIN_ORG_NAME,LOGIN_ORG_CODE,LOGIN_ORG_PARENT_UUID,CREATE_TIME) " +
				   "VALUES(?,?,?,?,?,?,?,?,?,?)";
		List<Org> list = user.getOrgList();
		Org org = new Org();
		if(null!=list&&list.size()>0){
			//默认第一个
			org = list.get(0);
		}
		Object[]params = new Object[]{
				getUUID(),
				loginSessionId,
				user.getUserUuid(),
				user.getUserId(),
				user.getUserName(),
				org.getOrgUuid(),
				org.getOrgName(),
				org.getOrgCode(),
				org.getOrgParentUuid(),
				DataUtil.formateDate(new Date())
		};
		int result = update(sql, params);
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：移除临时表中的数据信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-10-22 下午04:45:15<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int delLoginUser(String loginSessionId){
		String sql="DELETE FROM APP_SYSTEM_LOGIN_USER WHERE LOGIN_SESSION_ID = ? ";
		Object[]params = new Object[]{loginSessionId};
		int result = update(sql, params);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
