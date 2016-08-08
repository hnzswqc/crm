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
    <script type="text/javascript" src="<%=basePath%>js/oa/resigned_no/app.js"></script>
    <script type="text/javascript">
    	//常量信息
    	/**根节点*/
    	var DEFAULT_ROOT_UUID = "${DEFAULT_ROOT_UUID}";
    	//物品采购类别
    	var DIC_PURCHASE_TYPE="${DIC_PURCHASE_TYPE}";
    	/**辞职类别*/
    	var DIC_RESIGNED_TYPE ="${DIC_RESIGNED_TYPE}";
   		/**工作发放方式*/
       	var DIC_RESIGNED_MONEY_TYPE ="${DIC_RESIGNED_MONEY_TYPE}";
       	/**工资结算状态*/
       	var DIC_RESIGNED_STATE="${DIC_RESIGNED_STATE}";
		/**已完结*/
       	var DIC_RESIGNED_STATE_YES = "${DIC_RESIGNED_STATE_YES}";
       	/**未完结*/
       	var DIC_RESIGNED_STATE_NO = "${DIC_RESIGNED_STATE_NO}";
    	/***/
       	var DIC_RESIGNED_MONEY_TYPE_LJFF = "${DIC_RESIGNED_MONEY_TYPE_LJFF}";
    	/**是否存在工资卡*/
       	var DIC_BANK_CARD_STATE ="${DIC_BANK_CARD_STATE}";
    	/**存在工资卡*/
       	var DIC_BANK_CARD_STATE_HAVE = "${DIC_BANK_CARD_STATE_HAVE}";
       	/**没有工资卡*/
       	var DIC_BANK_CARD_STATE_NO = "${DIC_BANK_CARD_STATE_NO}";
       	
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
