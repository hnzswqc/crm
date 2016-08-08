/*
 * @项目名称: crm
 * @文件名称: ExcelUtil.java
 * @日期: 2015-12-4 下午04:53:00  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;


/**    
 * 项目名称：crm   <br/>
 * 类名称：ExcelUtil.java   <br/>
 * 类描述：  导出excel工具类 <br/>
 * 创建人：开发部笔记本   <br/>
 * 创建时间：2015-12-4 下午04:53:00   <br/>
 * 修改人：开发部笔记本   <br/>
 * 修改时间：2015-12-4 下午04:53:00   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class ExcelUtil {
	
	/**
	 * 
	 * 方法描述：导出execel生成sheet工具类<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-12-4 下午04:59:31<br/>         
	 * @param sheet 工作表<br/>  
	 * @param rowNum 第几行开始<br/>   
	 * @param title 标题<br/>   
	 * @param key 对应反射字段<br/>   
	 * @param data 导出数据<br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static HSSFSheet getSheet(HSSFSheet sheet,Integer rowNum,HSSFCellStyle headerStyle,HSSFCellStyle dataStyle, String[] title, String[] key, List<?> data) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		HSSFRow row = sheet.createRow(rowNum);//设置第一行为header
		
		HSSFCell cell1 = row.createCell(0);//序号
		cell1.setCellValue("序号");
		cell1.setCellStyle(headerStyle);
		
		for (int i = 0; i < title.length; i++) {
			HSSFCell cell = row.createCell(i+1);//序号
			cell.setCellValue(title[i]);
			cell.setCellStyle(headerStyle);
		}
		row.setHeight((short)(30*20));//第一行标题高度
		int i=1;
		for (Object object : data) {
			HSSFRow rowData = sheet.createRow(rowNum+1);//创建第n行数据
			Class<? extends Object> clazz = object.getClass();
			PropertyDescriptor pd = null;
			
			HSSFCell cell2 = rowData.createCell(0);//序号
			cell2.setCellValue(i);
			cell2.setCellStyle(dataStyle);
			i++;
			for (int j = 0; j < key.length; j++) {
				HSSFCell cell = rowData.createCell(j+1);
				pd = new PropertyDescriptor(key[j], clazz);
				if(null!=pd){
					Method getMethod = pd.getReadMethod();
					Object o = getMethod.invoke(object);
					cell.setCellValue(null!=o?o.toString():"");
				}else{
					cell.setCellValue("");
				}
				cell.setCellStyle(dataStyle);
			}
			rowData.setHeight((short)(30*20));//第一行标题高度
			rowNum++;
		}
		return sheet;
	}

}
