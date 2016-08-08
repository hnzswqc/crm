/*
 * @项目名称: crm
 * @文件名称: IUserAuthorityService.java
 * @日期: 2015-9-7 上午10:54:08  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service;

import com.hnzskj.base.core.bean.User;
import com.hnzskj.base.core.bean.UserAuthority;
import com.hnzskj.common.bean.PageBean;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IUserAuthorityService.java   <br/>
 * 类描述：用户授权业务层接口   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-9-7 上午10:54:08   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-9-7 上午10:54:08   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IUserAuthorityService {

	/**
	 * 
	 * 方法描述：添加<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-7 上午10:28:07<br/>         
	 * @param list 批量添加集合<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addUserAuthority(User user,String authorityUserUuids,String type,String note);
	
	/**
	 * 
	 * 方法描述：删除授权用户<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-7 上午10:29:04<br/>         
	 * @param userAuthority 实体条件<br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delUserAuthority(UserAuthority userAuthority);
	
	
	/**
	 * 
	 * 方法描述：修改，审核通过<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-9-7 上午10:31:21<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public boolean updUserAuthority(UserAuthority userAuthority);
	
	

	/**
	 * 
	 * 方法描述：分页查询<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param userAuthority 条件参数<br/>
	 * @return Page<UserAuthority><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<UserAuthority> getUserAuthorityList(PageBean<UserAuthority> pageBean,UserAuthority userAuthority);
	
	
}
