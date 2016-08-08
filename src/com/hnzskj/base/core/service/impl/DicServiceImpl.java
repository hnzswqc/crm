/*
 * @项目名称: crm
 * @文件名称: DicServiceImpl.java
 * @日期: 2015-8-26 下午06:17:33  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.common.bean.PageBean;
import com.hnzskj.base.core.bean.Dic;
import com.hnzskj.base.core.bean.TreeNode;
import com.hnzskj.base.core.dao.IDicDao;
import com.hnzskj.base.core.service.IDicService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：DicServiceImpl.java   <br/>
 * 类描述：   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-8-26 下午06:17:33   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-8-26 下午06:17:33   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class DicServiceImpl implements IDicService {
	
	/**
	 * dao层注入
	 */
	@Autowired
	private IDicDao dicDao = null;

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#addDic(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public boolean addDic(Dic dic) {
		int result = dicDao.addDic(dic);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#addDicType(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public boolean addDicType(Dic dic) {
		int result = dicDao.addDicType(dic);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#delDic(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public boolean delDic(Dic dic) {
		int result = dicDao.delDic(dic);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#delDicType(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public boolean delDicType(Dic dic) {
		int result = dicDao.delDicType(dic);
		return result>=0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#getDicList(com.hnzskj.common.bean.PageBean, com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public PageBean<Dic> getDicList(PageBean<Dic> pageBean, Dic dic) {
		String fields=" UUID,ID,[KEY],TEXT,LABEL,NOTE,PARENTUUID,ORDERBY,TYPE,CREATETIME,(SELECT TEXT FROM APP_SYSTEM_DIC WHERE UUID = ASD.PARENTUUID) AS PARENTNAME, (select COUNT(1) FROM APP_SYSTEM_DIC WHERE PARENTUUID = ASD.UUID) AS CHILD ";
		StringBuffer sb = new StringBuffer(" WHERE 1=1 ");
		if(null!=dic){
			//类别
			if(null!=dic.getType()&&!"".equals(dic.getType())){
				sb.append(" AND TYPE ='").append(dic.getType()).append("'");
			}
			//所属父级菜单
			if(null!=dic.getParentUuid()&&!"".equals(dic.getParentUuid())){
				sb.append(" AND PARENTUUID = '").append(dic.getParentUuid()).append("'");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("ORDERBY", "ASC");
		pageBean = dicDao.getDicList(pageBean, fields, sb.toString(), null, orderby, " APP_SYSTEM_DIC ASD ", " UUID ");
		return pageBean;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#getDicTypeList()
	 */
	public List<Dic> getDicTypeList() {
		List<Dic> list = dicDao.getDicTypeList();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#updDic(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public boolean updDic(Dic dic) {
		int result = dicDao.updDic(dic);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#updDicType(com.hnzskj.mainFrame.core.bean.Dic)
	 */
	public boolean updDicType(Dic dic) {
		int result = dicDao.updDicType(dic);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#getDicType(java.lang.String)
	 */
	public Dic getDicType(String id) {
		Dic dic = dicDao.getDicType(id);
		return dic;
	}
	
	

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#getDic(java.lang.String, java.lang.String)
	 */
	public Dic getDic(String type, String id) {
		Dic dic = dicDao.getDic(type,id);
		return dic;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#getTreeList(java.lang.String, java.lang.String)
	 */
	public List<TreeNode> getTreeList(String type, String parentUuid) {
		List<TreeNode> list = dicDao.getTreeList(type, parentUuid);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#getParamList(java.lang.String)
	 */
	public List<Dic> getParamList(String type) {
		List<Dic> list = dicDao.getParamList(type);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.mainFrame.core.service.IDicService#getDicByKey(java.lang.String, java.lang.String)
	 */
	public Dic getDicByKey(String type, String key) {
		Dic dic = dicDao.getDicByKey(type, key);
		return dic;
	}
	
	
	

}
