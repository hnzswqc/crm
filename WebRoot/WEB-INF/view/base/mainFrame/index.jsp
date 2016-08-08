<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.base.common.util.MfConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("USER_APPLY",MfConstant.USER_APPLY);
request.setAttribute("USER_APPROVE",MfConstant.USER_APPROVE);
request.setAttribute("USER_AUTHORITY",MfConstant.USER_AUTHORITY);
request.setAttribute("OTHER_AUTHORITY",MfConstant.OTHER_AUTHORITY);
request.setAttribute("DIC_AUTHORITY_STATE",MfConstant.DIC_AUTHORITY_STATE);



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
    <script type="text/javascript" src="<%=basePath%>js/base/mainFrame/app.js"></script>
    <script type="text/javascript">
    	//默认第一个子系统
    	var REQUEST_SUBSYTEM_UUID ="${REQUEST_SUBSYTEM.subUuid}" ;
    	/**申请*/
    	var USER_APPLY = "${USER_APPLY}";
    	/**审批*/
    	var USER_APPROVE = "${USER_APPROVE}";
    	/**本人授权*/
    	var USER_AUTHORITY = "${USER_AUTHORITY}";
		/**他人授权*/
    	var OTHER_AUTHORITY = "${OTHER_AUTHORITY}";
		/**审核类别数据字典*/
    	var DIC_AUTHORITY_STATE="${DIC_AUTHORITY_STATE}";
    	/**接受申请*/
    	var DIC_AUTHORITY_STATE_AUDIT_THROUGH="${AUDIT_THROUGH}";
		/**变身标识*/
    	var CHANGE_USER_STATE="${SESSION_USERAUTHORITY.uuid}";
		/**标题*/
    	var PRJ_TITLE = "${PRJ_TITLE }";
    </script>
  </head>
  
  <body>
  </body>
</html>
