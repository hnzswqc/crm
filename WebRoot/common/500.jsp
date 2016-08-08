<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="500.jsp" isErrorPage = "true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>500</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     500错误，服务器内部出现错误。请刷新页面重新访问。<br/>
     <%=exception %>
     
     <%
     	StackTraceElement[]stack =  exception.getStackTrace();
     	for(int i =0;i<stack.length;i++){
     		System.out.print(stack[i]);
     		System.out.print(stack[i].getFileName());
     		System.out.print(stack[i].getClassName());
     		System.out.print(stack[i].getLineNumber());
     		System.out.print(stack[i].getMethodName());
     		
     	}
     %>
  </body>
</html>
