<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.base.common.util.MfConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("DEFAULT_ROOT_UUID",MfConstant.DEFAULT_ROOT_UUID);
request.setAttribute("DIC_USER_STATE",MfConstant.DIC_USER_STATE);
request.setAttribute("DIC_GENDER",MfConstant.DIC_GENDER);
request.setAttribute("DIC_USER_DEGREES",MfConstant.DIC_USER_DEGREES);



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
    <script type="text/javascript" src="<%=basePath%>js/base/user/app.js"></script>
    <script type="text/javascript">
    	//常量信息
    	/**根节点*/
    	var DEFAULT_ROOT_UUID = "${DEFAULT_ROOT_UUID}";
    	/**人员状态*/
    	var DIC_USER_STATE="${DIC_USER_STATE}";
    	/**正式员工*/
       	var DIC_USER_STATE_ACTIVITY = "${DIC_USER_STATE_ACTIVITY}";
		/**性别*/
    	var DIC_GENDER = "${DIC_GENDER}";
    	//男
    	var DIC_GENDER_MAN="${MAN}";
    	//女
    	var DIC_GENDER_FEMALE="${FEMALE}";
    	//其他
    	var DIC_GENDER_OTHER="${OTHER}";
		/**学历*/
    	var DIC_USER_DEGREES = "${DIC_USER_DEGREES}";
		/**辞职类别*/
    	var DIC_RESIGNED_TYPE ="${DIC_RESIGNED_TYPE}";
   		/**工作发放方式*/
       	var DIC_RESIGNED_MONEY_TYPE ="${DIC_RESIGNED_MONEY_TYPE}";
       	/**是否存在工资卡*/
       	var DIC_BANK_CARD_STATE ="${DIC_BANK_CARD_STATE}";

       	/**身份证复印件*/
       	var DIC_RECORD_ID_CARD ="${DIC_RECORD_ID_CARD}";
       	/**体检报告*/
       	var DIC_RECORD_JOIN_TJ_MSG = "${DIC_RECORD_JOIN_TJ_MSG}";
       	/**入职应聘表*/
       	var DIC_RECORD_JOIN_MSG = "${DIC_RECORD_JOIN_MSG}";
       	/**申请转正表*/
       	var DIC_RECORD_JOIN_SQZZB = "${DIC_RECORD_JOIN_SQZZB}";
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
