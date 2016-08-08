<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.business.common.util.MfConstant"%>
<%@page import="com.hnzskj.common.util.Constant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("DIC_LOGGING_TYPE",Constant.DIC_LOGGING_TYPE);

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
 	<script type="text/javascript" src="<%=basePath%>js/lib/ext4/ux/TreePicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/business/monthReport/app.js"></script>
    <script type="text/javascript">
    	var exists=function(btnId){
    		var btns="${AUTHORITY_OPERATE}";
    		if(btns.indexOf(btnId)>-1){
    			return false;
    		}
    		return true;
    	}
    	var DIC_LOGGING_TYPE = "${DIC_LOGGING_TYPE}";
    	//制管
    	var LOGGING_TYPE_ZG = "${LOGGING_TYPE_ZG}";
    	//包装
    	var LOGGING_TYPE_BZ = "${LOGGING_TYPE_BZ}";
    	//抛光
    	var LOGGING_TYPE_PG = "${LOGGING_TYPE_PG}";
    	//推管
    	var LOGGING_TYPE_TG = "${LOGGING_TYPE_TG}";
    	//班长
    	var LOGGING_TYPE_LEADER = "${LOGGING_TYPE_LEADER}";
    	//抛光比例
    	var LOGGING_PG_NUM_WEIGHT ="${LOGGING_PG_NUM_WEIGHT}";
    	//男
    	var DIC_GENDER_MAN="${MAN}";
    	//女
    	var DIC_GENDER_FEMALE="${FEMALE}";
    	//其他
    	var DIC_GENDER_OTHER="${OTHER}";
		//男工推管单价
    	var LOGGING_TG_MAN_PRICE="${LOGGING_TG_MAN_PRICE}";
    	//女工推管单价
    	var LOGGING_TG_WUMEN_PRICE="${LOGGING_TG_WUMEN_PRICE}";
    	
    </script>
    
  </head>
  
  <body>
  </body>
</html>
