/*
 * @项目名称: crm
 * @文件名称: IProbationStaff.java
 * @日期: 2015-12-7 下午09:08:08  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.probationstaff.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.probationstaff.bean.ProbationStaff;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IProbationStaff.java   <br/>
 * 类描述：试用期员工dao层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-7 下午09:08:08   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-7 下午09:08:08   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IProbationStaffDao {
	
	/**
	 * 
	 * 方法描述：添加一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-7 下午09:10:03<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int addProbationStaff(ProbationStaff probationStaff);
	
	/**
	 * 
	 * 方法描述：删除一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-7 下午09:10:15<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int delProbationStaff(ProbationStaff probationStaff);
	
	/**
	 * 
	 * 方法描述：修改一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-7 下午09:10:27<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int updProbationStaff(ProbationStaff probationStaff);
	
	/**
	 * 
	 * 方法描述：获取人员分页信息<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-11 上午11:14:47<br/>         
	 * @param page 分页实体<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>
	 * @param tableName 查询表明<br/>
	 * @param key 查询主键<br/>
	 * @return Page<User><br/>   
	 * @version   1.0<br/>
	 */
	public PageBean<ProbationStaff> searchProbationStaffList(PageBean<ProbationStaff> pageBean,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String tableName, String key);
	
	/**
	 * 
	 * 方法描述：根据部门uuid获取部门内的职务信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-10 下午05:09:23<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Dic> searchOrgRoleList(String orgUuid);
	
	

}
