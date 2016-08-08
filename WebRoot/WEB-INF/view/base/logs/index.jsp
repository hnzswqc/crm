<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${PRJ_TITLE }</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 	<jsp:include page="/common/extjs.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>js/base/logs/app.js"></script>
    <script type="text/javascript">
    	var LOG_TYPE_SYSTEM = "${LOG_TYPE_SYSTEM}";
    	var LOG_TYPE_ERROR = "${LOG_TYPE_ERROR}";
    	var LOG_TYPE_OPERATE = "${LOG_TYPE_OPERATE}";
    	var LOG_STATE_SUCCESS = "${LOG_STATE_SUCCESS}";
    	var LOG_STATE_ERROR = "${LOG_STATE_ERROR}";
    	var exists=function(btnId){
    		var btns="${AUTHORITY_OPERATE}";
    		if(btns.indexOf(btnId)>-1){
    			return false;
    		}
    		return true;
    	}
    </script>
  </head>
  
  <body>
  </body>
</html>
