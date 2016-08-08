/*
 * @项目名称: workFlow
 * @文件名称: DataUtil.java
 * @日期: 2015-4-8 上午09:14:36  
 * @版权: 2015 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**    
 * 项目名称：workFlow   <br/>
 * 类名称：DataUtil.java   <br/>
 * 类描述：数据格式化类   <br/>
 * 创建人：King   <br/>
 * 创建时间：2015-4-8 上午09:14:36   <br/>
 * 修改人：King   <br/>
 * 修改时间：2015-4-8 上午09:14:36   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class DataUtil {

	/**
	 * 
	 * 方法描述：格式化事件，返回yyyy-MM-dd HH:mm:ss<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-4-8 上午09:15:04<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	public static String formateDate(Date date){
		String dateStr = null;
		if (null != date) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			dateStr = format.format(date);
		}
		return dateStr;
	}
	
	/**
	 * 
	 * 方法描述：格式化事件，返回yyyy-MM-dd HH:mm:ss<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-4-8 上午09:15:04<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	public static String formateDefaultDate(){
		String dateStr = null;
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			dateStr = format.format(new Date());
		return dateStr;
	}
	
	
	/**
	 * 
	 * 方法描述：格式化事件，返回yyyyMMddHHmmss<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-4-8 上午09:15:04<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	public static String formateDate(){
		String dateStr = null;
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		dateStr = format.format(new Date());
		return dateStr;
	}
	
	/**
	 * 
	 * 方法描述：格式化事件，返回yyyy<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-4-8 上午09:15:04<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	public static String getYear(){
		String dateStr = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		dateStr = format.format(new Date());
		return dateStr;
	}
	
	
	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date formateStrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 
	 * 方法描述：计算年龄<br/>
	 * 创建人：King   <br/>
	 * 创建时间：2015-8-25 下午02:36:51<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public static int getAgeByBirthday(String birthday) {
		java.util.Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		
		int yearBirth = Integer.valueOf(birthday.substring(0,4));
		int monthBirth =  Integer.valueOf(birthday.substring(6,7));
		int dayOfMonthBirth =Integer.valueOf(birthday.substring(8,10));
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return age;
	}
	
	/**
	 * 
	 * 方法描述：获取当月第一天<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 上午11:28:23<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public static String getMonthFirstDay(){
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String first = format.format(c.getTime());
		return first;
	}
	
	/**
	 * 
	 * 方法描述：获取当月最后一天<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 上午11:28:23<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public static String getMonthLastDay(){
		Calendar ca = Calendar.getInstance();    
	    ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastDay = format.format(ca.getTime());
		return lastDay;
	}
	
	/**
	 * 
	 * 方法描述：根据年度和月份获取当月天数<br/>
	 * 创建人：开发部笔记本   <br/>
	 * 创建时间：2015-11-9 下午03:50:02<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public static int getMonthLastDay(int year,int month){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONDAY, month-1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int lastDay = a.get(Calendar.DATE);
		return lastDay;
	}
	
	public static void main(String[] args) {
		System.out.println(getMonthLastDay(2015, 11));
		System.out.println(getMonthLastDay(2016, 2));
	}

}
