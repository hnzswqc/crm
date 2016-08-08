<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>登录界面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <link rel="stylesheet" href="<%=basePath%>css/login.css" type="text/css" />
 

</head>
<body>
	<div class="login">
		<form action="<%=basePath%>login.do" method="post">
			<label>用户名:</label>
			<input type="text" name="userId"  required/>
			
			<label>密码:</label>
			<input type="password" name="password"  required/>
			<span></span>
			
			<input type="checkbox" name="save" id="save" />
			<label for="save">记住密码</label>
			<input type="submit" value="登录" />
		</form>
	</div>
		<c:if test="${!empty loginError}">
			<br/>
			<span style="color: red;font-size: 15px;">${loginError }</span>
		</c:if>
		
  </body>
</html>
