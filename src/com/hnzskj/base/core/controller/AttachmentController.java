/*
 * @项目名称: crm
 * @文件名称: AttachmentController.java
 * @日期: 2015-12-12 下午10:06:14  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.base.core.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzskj.base.core.bean.Attachment;
import com.hnzskj.base.core.service.IAttachmentService;
import com.hnzskj.common.controller.BaseController;

/**    
 * 项目名称：crm   <br/>
 * 类名称：AttachmentController.java   <br/>
 * 类描述：   <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-12 下午10:06:14   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-12 下午10:06:14   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@RequestMapping(value="att")
@Controller
public class AttachmentController extends BaseController {

	
	/**
	 * 业务注入
	 */
	@Autowired
	private IAttachmentService attachmentService = null;
	
	
	/**
	 * 
	 * 方法描述：查看附件<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-12 下午10:09:53<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	@RequestMapping(value="showAttachment")
	public String showAttachment(@ModelAttribute()Attachment attachment,HttpServletRequest request,HttpServletResponse response){
		attachment = attachmentService.getAttachmentByParam(attachment);
		InputStream in = null;
		OutputStream os = null;
		if(StringUtils.isNotEmpty(attachment.getFileName())){
			in = attachment.getInputStream();
		}
		try {
			// 下载文件时显示的文件保存名称
			String s = java.net.URLEncoder.encode(attachment.getFileName(), "utf-8");
			//response.setContentType(record.getRecordAttType()+";charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment;filename="+s);
			os = response.getOutputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) > 0) {
				os.write(b, 0, i);
			}
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}finally{
			try {
				if (null != os) {
				os.close();
				}
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
			}
		}
		return null;
	}
	
}
