<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.base.common.util.MfConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("DEFAULT_ROOT_UUID",MfConstant.DEFAULT_ROOT_UUID);
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
    <script type="text/javascript" src="<%=basePath%>js/oa/officeGoods/app.js"></script>
    <script type="text/javascript">
    	//常量信息
    	/**根节点*/
    	var DEFAULT_ROOT_UUID = "${DEFAULT_ROOT_UUID}";
    	//物品采购类别
    	var DIC_PURCHASE_TYPE="${DIC_PURCHASE_TYPE}";
    	//办公物品领取状态
    	var DIC_OFFICEGOODS_STATE="${DIC_OFFICEGOODS_STATE}";
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
