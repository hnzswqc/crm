/*
 * @项目名称: crm
 * @文件名称: KqszServiceImpl.java
 * @日期: 2016-3-4 下午08:14:57  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.oa.kqsz.service.impl;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.oa.kqsz.bean.Kqsz;
import com.hnzskj.oa.kqsz.dao.IKqszDao;
import com.hnzskj.oa.kqsz.service.IKqszService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：KqszServiceImpl.java   <br/>
 * 类描述：考勤设置业务层实现类   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2016-3-4 下午08:14:57   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2016-3-4 下午08:14:57   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class KqszServiceImpl implements IKqszService {

	/**
	 * dao层注入
	 */
	@Autowired
	private IKqszDao kqszDao = null;
	
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.kqsz.service.KqszService#addKqsz(com.hnzskj.oa.kqsz.bean.Kqsz)
	 */
	public boolean addKqsz(Kqsz kqsz) {
		int result = kqszDao.addKqsz(kqsz);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.kqsz.service.KqszService#delKqsz(com.hnzskj.oa.kqsz.bean.Kqsz)
	 */
	public boolean delKqsz(Kqsz kqsz) {
		int result = kqszDao.delKqsz(kqsz);
		return result>=0?true:false;
	}

	/**
	 * 
	 * 方法描述：获取实体对象查询条件<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2016-3-11 下午07:19:42<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	private String getSqlCondition(Kqsz kqsz){
		StringBuffer sb = new StringBuffer(" where 1=1  ");
		if(null!=kqsz){
			//考勤年度
			if(null!=kqsz.getYear()&&!"".equals(kqsz.getYear())){
				sb.append(" AND YEAR = '").append(kqsz.getYear()).append("'");
			}
			//考勤月份
			if(null!=kqsz.getMonth()&&!"".equals(kqsz.getMonth())){
				sb.append(" AND MONTH = '").append(kqsz.getMonth()).append("'");
			}
			//考勤主键uuid
			if(null!=kqsz.getUuid()&&!"".equals(kqsz.getUuid())){
				sb.append(" AND UUID = '").append(kqsz.getUuid()).append("'");
			}
			//考勤开始时间
			if(null!=kqsz.getKqStartTime()&&!"".equals(kqsz.getKqEndTime())){
				sb.append(" AND KSSTARTTINE = '").append(kqsz.getKqEndTime()).append("'");
			}
			//考勤结束时间
			if(null!=kqsz.getKqEndTime()&&!"".equals(kqsz.getKqEndTime())){
				sb.append(" AND KQENDTIME = '").append(kqsz.getKqEndTime()).append("'");
			}
			//满勤天数
			if(null!=kqsz.getMqts()&&!"".equals(kqsz.getMqts())){
				sb.append(" AND MQTS = '").append(kqsz.getMqts()).append("'");
			}
		}
		return sb.toString();
	}
	/* (non-Javadoc)
	 * @see com.hnzskj.oa.kqsz.service.KqszService#getKqsz(com.hnzskj.oa.kqsz.bean.Kqsz)
	 */
	public Kqsz getKqsz(Kqsz kqsz) {
		return kqszDao.getKqsz(getSqlCondition(kqsz));
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.kqsz.service.KqszService#getKqszList(com.hnzskj.common.bean.PageBean, com.hnzskj.oa.kqsz.bean.Kqsz)
	 */
	public PageBean<Kqsz> getKqszList(PageBean<Kqsz> pageBean, Kqsz kqsz) {
		String fields=" UUID,YEAR,MONTH,MQTS,KQSTARTTIME,KQENDTIME,NOTE,CREATETIME ";
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("YEAR", "DESC");
		orderby.put("MONTH","DESC");
		pageBean = kqszDao.getKqszList(pageBean, fields, getSqlCondition(kqsz), null, orderby, " APP_SYSTEM_KQSZ ", " UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.oa.kqsz.service.KqszService#updKqsz(com.hnzskj.oa.kqsz.bean.Kqsz)
	 */
	public boolean updKqsz(Kqsz kqsz) {
		int result = kqszDao.updKqsz(kqsz);
		return result>0?true:false;
	}

}
