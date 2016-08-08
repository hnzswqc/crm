/*
 * @项目名称: crm
 * @文件名称: UserAuthorityServiceImpl.java
 * @日期: 2015-9-7 上午11:00:15  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.base.common.util.MfConstant;
import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.bean.UserAuthority;
import com.hnzskj.base.core.dao.IUserAuthorityDao;
import com.hnzskj.base.core.service.IDicService;
import com.hnzskj.base.core.service.IUserAuthorityService;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：UserAuthorityServiceImpl.java   <br/>
 * 类描述： 用户授权业务层实现类  <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-7 上午11:00:15   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-7 上午11:00:15   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class UserAuthorityServiceImpl implements IUserAuthorityService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IUserAuthorityDao userAuthorityDao = null;
	
	/**
	 * 字典注入
	 */
	@Autowired
	private IDicService dicService = null;

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IUserAuthorityService#addUserAuthority(com.hnzskj.base.core.bean.User, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean addUserAuthority(User user, String authorityUserUuids,
			String type,String note) {
		if(null!=authorityUserUuids&&!"".equals(authorityUserUuids)){
			String[]uuids = null;
			if(authorityUserUuids.indexOf(",")>-1){
				uuids = authorityUserUuids.split(",");
			}else{
				uuids=new String[]{authorityUserUuids};
			}
			List<UserAuthority> list = new ArrayList<UserAuthority>();
			String state=getDicText(MfConstant.DIC_AUTHORITY_STATE_PENDING_AUDIT);
			if(MfConstant.USER_APPLY.equals(type)||MfConstant.USER_APPROVE.equals(type)){
				for (int i = 0; i < uuids.length; i++) {
					UserAuthority userAuthority = new UserAuthority();
					userAuthority.setUserUuid(user.getUserUuid());
					userAuthority.setAuthorityUserUuid(uuids[i]);
					userAuthority.setType(type);
					userAuthority.setNote(note);
					userAuthority.setState(state);
					list.add(userAuthority);
				}
			}else{
				state=getDicText(MfConstant.DIC_AUTHORITY_STATE_AUDIT_THROUGH);
				for (int i = 0; i < uuids.length; i++) {
					UserAuthority userAuthority = new UserAuthority();
					userAuthority.setUserUuid(uuids[i]);
					userAuthority.setAuthorityUserUuid(user.getUserUuid());
					userAuthority.setType(type);
					userAuthority.setNote(note);
					userAuthority.setState(state);
					list.add(userAuthority);
				}
			}
			
			int result = userAuthorityDao.addUserAuthority(list);
			return result>0?true:false;
		}
		return true;
	}

	/**
	 * 
	 * 方法描述：获取字典对应值<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-7 下午02:22:03<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	private String getDicText(String key){
		Dic dic= dicService.getDicByKey(MfConstant.DIC_AUTHORITY_STATE,key);
		if(null!=dic){
			return dic.getId();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IUserAuthorityService#delUserAuthority(com.hnzskj.base.core.bean.UserAuthority)
	 */
	public boolean delUserAuthority(UserAuthority userAuthority) {
		int result = userAuthorityDao.delUserAuthority(userAuthority);
		return result>=0?true:false;
	}
   
	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IUserAuthorityService#getUserAuthorityList(com.hnzskj.common.bean.PageBean, com.hnzskj.base.core.bean.UserAuthority)
	 */
	public PageBean<UserAuthority> getUserAuthorityList(
			PageBean<UserAuthority> pageBean, UserAuthority userAuthority) {
		String fields=" UUID,USER_UUID AS USERUUID,(SELECT USER_NAME FROM APP_SYSTEM_USER WHERE USER_UUID = APP_SYSTEM_USER_AUTHORITY.USER_UUID) AS USERNAME,AUTHORITY_USER_UUID AS AUTHORITYUSERUUID,(SELECT USER_NAME FROM APP_SYSTEM_USER WHERE APP_SYSTEM_USER.USER_UUID = AUTHORITY_USER_UUID) as AUTHORITYUSERNAME,TYPE, STATE,(SELECT ISNULL(LABEL,[TEXT]) FROM APP_SYSTEM_DIC WHERE TYPE = '"+MfConstant.DIC_AUTHORITY_STATE+"' AND ID = STATE) AS STATETEXT ,NOTE,CREATE_TIME AS CREATETIME ";
		StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=userAuthority){
			//类别
			if(null!=userAuthority.getType()&&!"".equals(userAuthority.getType())){
				sb.append(" AND TYPE = '").append(userAuthority.getType()).append("'");
			}
			//申请人
			if(null!=userAuthority.getUserUuid()&&!"".equals(userAuthority.getUserUuid())){
				sb.append(" AND USER_UUID = '").append(userAuthority.getUserUuid()).append("'");
			}
			//被授权人
			if(null!=userAuthority.getAuthorityUserUuid()&&!"".equals(userAuthority.getAuthorityUserUuid())){
				sb.append(" AND AUTHORITY_USER_UUID = '").append(userAuthority.getAuthorityUserUuid()).append("'");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("STATE", "DESC");
		orderby.put("CREATE_TIME", "DESC");
		pageBean = userAuthorityDao.getUserAuthorityList(pageBean, fields, sb.toString(), null, orderby, " APP_SYSTEM_USER_AUTHORITY ", " UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IUserAuthorityService#updUserAuthority(com.hnzskj.base.core.bean.UserAuthority)
	 */
	public boolean updUserAuthority(UserAuthority userAuthority) {
		int result = userAuthorityDao.updUserAuthority(userAuthority);
		return result>0?true:false;
	}

}
