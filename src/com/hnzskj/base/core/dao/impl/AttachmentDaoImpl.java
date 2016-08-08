/*
 * @项目名称: crm
 * @文件名称: AttachmentDaoImpl.java
 * @日期: 2015-12-11 下午08:57:11  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.hnzskj.base.core.bean.Attachment;
import com.hnzskj.base.core.dao.IAttachmentDao;
import com.hnzskj.common.dao.BaseDao;
import com.hnzskj.common.util.DataUtil;

/**    
 * 项目名称：crm   <br/>
 * 类名称：AttachmentDaoImpl.java   <br/>
 * 类描述：附件dao层接口  <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-11 下午08:57:11   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-11 下午08:57:11   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@Repository
public class AttachmentDaoImpl extends BaseDao implements IAttachmentDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IAttachmentDao#addAttachment(com.hnzskj.base.core.bean.Attachment)
	 */
	public int addAttachment(Attachment attachment) {
		//先删除
		String sql="DELETE FROM APP_SYSTEM_ATTACHMENT WHERE FOREIGN_UUID = ? AND ATT_TYPE = ? ";
		update(sql, new Object[]{attachment.getForeignUUid(),attachment.getAttType()});
		//再添加
		String addSql="INSERT INTO APP_SYSTEM_ATTACHMENT(ATT_UUID,FOREIGN_UUID,ATT_TYPE,FILE_TYPE,FILE_NAME,FILE_CONTENT,CREATE_USER_UUID,CREATE_TIME) " +
				      "VALUES(?,?,?,?,?,?,?,?)";
		Object[]params = new Object[]{
				null!=attachment.getAttUuid()&&!"".equals(attachment.getAttUuid())?attachment.getAttUuid():UUID.randomUUID().toString(),
				attachment.getForeignUUid(),
				attachment.getAttType(),
				attachment.getFileType(),
				attachment.getFileName(),
				attachment.getFileContent(),
				attachment.getCreateUserUuid(),
				DataUtil.formateDefaultDate()
		};
		int result = update(addSql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IAttachmentDao#addAttachment(java.util.List)
	 */
	public int addAttachment(List<Attachment> list) {
		String delSql="DELETE FROM APP_SYSTEM_ATTACHMENT WHERE FOREIGN_UUID = ? AND ATT_TYPE = ? ";
		//再添加
		String addSql="INSERT INTO APP_SYSTEM_ATTACHMENT(ATT_UUID,FOREIGN_UUID,ATT_TYPE,FILE_TYPE,FILE_NAME,FILE_CONTENT,CREATE_USER_UUID,CREATE_TIME) " +
				      "VALUES(?,?,?,?,?,?,?,?)";
		Object[][]delParams = new Object[list.size()][];
		Object[][]addParams = new Object[list.size()][];
		int i = 0;
		for (Attachment attachment : list) {
			Object[]delParam = new Object[]{attachment.getForeignUUid(),attachment.getAttType()};
			Object[]addParam = new Object[]{
					null!=attachment.getAttUuid()&&!"".equals(attachment.getAttUuid())?attachment.getAttUuid():UUID.randomUUID().toString(),
					attachment.getForeignUUid(),
					attachment.getAttType(),
					attachment.getFileType(),
					attachment.getFileName(),
					attachment.getFileContent(),
					attachment.getCreateUserUuid(),
					DataUtil.formateDefaultDate()	
			};
			delParams[i] = delParam;
			addParams[i] = addParam;
			i++;
		}
		//先删除
		updateBatch(delSql, delParams, list.size());
		//再添加
		int result = updateBatch(addSql, addParams, list.size());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IAttachmentDao#delAttachment(java.lang.String)
	 */
	public int delAttachment(String sqlCondition) {
		//先删除
		String sql="DELETE FROM APP_SYSTEM_ATTACHMENT "+sqlCondition;
		int result = update(sql, new Object[]{});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.base.core.dao.IAttachmentDao#getAttachmentByParam(java.lang.String)
	 */
	public Attachment getAttachmentByParam(String sqlCondition) {
		String sql="SELECT FILE_NAME ,FILE_CONTENT ,FILE_TYPE FROM APP_SYSTEM_ATTACHMENT "+sqlCondition;
		Attachment attachment = new Attachment();
		try {
			conn = new BaseDao().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				attachment.setInputStream(rs.getBinaryStream("FILE_CONTENT"));
				attachment.setFileName(rs.getString("FILE_NAME"));
				attachment.setFileType(rs.getString("FILE_TYPE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attachment;
	}

}
