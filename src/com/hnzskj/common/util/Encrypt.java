package com.hnzskj.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 加密类 实现加密算法
 * 
 * @author 常利召
 * 
 */
public class Encrypt {
	/**
	 * md5加密算法
	 * 
	 * @param source
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String source) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			// 开始使用算法
			messageDigest.update(source.getBytes("UTF-8"));
			// 输出算法运算结果
			byte[] retValue = messageDigest.digest();
			return new String(retValue,"UTF-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * base64加密
	 * 
	 * @param source
	 * @return
	 */
	public static String base64(String source) {
		BASE64Encoder encoder = new BASE64Encoder();
		try {
			return encoder.encode(source.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 默认加密算法，先用md5加密，然后转换成64进制编码
	 * 
	 * @param source
	 * @return
	 */
	public static String digest(String source) {
		if(null!=source&&!"".equals(source)){
			return base64(md5(source));
		}else{
			return  "";
		}
	}

	/**
	 * 方法描述：Base64解密<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-2-15 上午10:15:35<br/>         
	 * @param strMi
	 * @return
	 * @version   1.0  
	 */
	public static String getDecoderBase64(String strMi) {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes = null;
        String str = "";
		try {
			decodedBytes = decoder.decodeBuffer(strMi);
			str = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Encrypt();
		/*String source = "654321";
		System.out.println("md5(" + source + ")=" + md5(source));
		System.out.println("base64(" + source + ")=" + base64(source));
		System.out.println("digest(" + source + ")=" + digest(source));
		System.out.println(digest("admin"));*/
//		System.out.println(digest("hnzskj"));
//		String string = base64("test*577e577e-128395c26fa-87b60dd922bbbd6df19d0642588f3b8f*227*String");
//		string=string.replace("\r\n", "");
//		System.out.println(string);
//		
		//dGVzdCo0N2VjNDdlYy0xM2MxZWIxOTMyYS03MjEwNWIxNmMwYzYzYTI3NWI0NmFkYTUwMzk4NTZjMCo4KlN0cmluZypiYWY1YjUzLTExNzZiYzk5ZTU5LWIzNDExYzUzYTRlYjE1YTJhMThhOTE5MjcyMDA3MDRi
		String str=getDecoderBase64("dGVzdCo0N2VjNDdlYy0xM2MxZWIxOTMyYS03MjEwNWIxNmMwYzYzYTI3NWI0NmFkYTUwMzk4NTZjMCo4KlN0cmluZypiYWY1YjUzLTExNzZiYzk5ZTU5LWIzNDExYzUzYTRlYjE1YTJhMThhOTE5MjcyMDA3MDRi");
		System.out.println(str);
		String[]r = str.split("\\*");
		//System.out.println(r[1]);
		for (int i = 0; i < r.length; i++) {
			System.out.println(r[i]);
		}
		System.out.println(Encrypt.digest("爱尔爱国"));
		
		
		
		
	}
}
