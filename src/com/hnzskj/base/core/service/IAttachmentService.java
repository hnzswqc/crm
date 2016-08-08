/*
 * @项目名称: crm
 * @文件名称: IAttachmentService.java
 * @日期: 2015-12-11 下午09:13:16  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.service;

import java.util.List;

import com.hnzskj.base.core.bean.Attachment;

/**    
 * 项目名称：crm   <br/>
 * 类名称：IAttachmentService.java   <br/>
 * 类描述：附件service层接口   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-11 下午09:13:16   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-11 下午09:13:16   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface IAttachmentService {

	/**
	 * 
	 * 方法描述：添加一条信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-11 下午08:54:45<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean addAttachment(Attachment attachment);
	
	/**
	 * 
	 * 方法描述：批量添加信息<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-11 下午08:54:54<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean addAttachment(List<Attachment> list);
	
	/**
	 * 
	 * 方法描述：通过条件删除附件<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-11 下午08:55:30<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean delAttachment(Attachment attachment);
	
	/**
	 * 
	 * 方法描述：下载附件<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-11 下午08:55:53<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public Attachment getAttachmentByParam(Attachment attachment);
	
}
