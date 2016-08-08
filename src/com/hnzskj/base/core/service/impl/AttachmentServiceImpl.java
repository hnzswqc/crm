/*
 * @项目名称: crm
 * @文件名称: AttachmentServiceImpl.java
 * @日期: 2015-12-11 下午09:14:20  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzskj.base.core.bean.Attachment;
import com.hnzskj.base.core.dao.IAttachmentDao;
import com.hnzskj.base.core.service.IAttachmentService;

/**    
 * 项目名称：crm   <br/>
 * 类名称：AttachmentServiceImpl.java   <br/>
 * 类描述：附件service层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-11 下午09:14:20   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-11 下午09:14:20   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Service
public class AttachmentServiceImpl implements IAttachmentService {

	
	/**
	 * dao层注入
	 */
	@Autowired
	private IAttachmentDao attachmentDao = null;
	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IAttachmentService#addAttachment(com.hnzskj.base.core.bean.Attachment)
	 */
	public boolean addAttachment(Attachment attachment) {
		int result =attachmentDao.addAttachment(attachment);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IAttachmentService#addAttachment(java.util.List)
	 */
	public boolean addAttachment(List<Attachment> list) {
		int result = attachmentDao.addAttachment(list);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IAttachmentService#delAttachment(com.hnzskj.base.core.bean.Attachment)
	 */
	public boolean delAttachment(Attachment attachment) {
		StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=attachment){
			//主键
			if(StringUtils.isNotEmpty(attachment.getAttUuid())){
				sb.append(" AND ATT_UUID = '").append(attachment.getAttUuid()).append("'");
			}
			//关联外键
			if(StringUtils.isNotEmpty(attachment.getForeignUUid())){
				sb.append(" AND FOREIGN_UUID = '").append(attachment.getForeignUUid()).append("'");
			}
			//删除类别
			if(StringUtils.isNotEmpty(attachment.getAttType())){
				sb.append(" AND ATT_TYPE = '").append(attachment.getAttType()).append("'");
			}
		}else{
			sb.append(" AND ATT_UUID = NULL ");
		}
		int result = attachmentDao.delAttachment(sb.toString());
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.service.IAttachmentService#getAttachmentByParam(com.hnzskj.base.core.bean.Attachment)
	 */
	public Attachment getAttachmentByParam(Attachment attachment) {
		StringBuffer sb = new StringBuffer("WHERE 1=1 ");
		if(null!=attachment){
			//主键
			if(StringUtils.isNotEmpty(attachment.getAttUuid())){
				sb.append(" AND ATT_UUID = '").append(attachment.getAttUuid()).append("'");
			}
			//关联外键
			if(StringUtils.isNotEmpty(attachment.getForeignUUid())){
				sb.append(" AND FOREIGN_UUID = '").append(attachment.getForeignUUid()).append("'");
			}
			//删除类别
			if(StringUtils.isNotEmpty(attachment.getAttType())){
				sb.append(" AND ATT_TYPE = '").append(attachment.getAttType()).append("'");
			}
		}else{
			sb.append(" AND ATT_UUID = NULL ");
		}
		attachment = attachmentDao.getAttachmentByParam(sb.toString());
		return attachment;
	}

}
