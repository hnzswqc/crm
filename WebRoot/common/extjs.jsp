<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hnzskj.base.common.util.MfConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("SUPER_USER_ID",MfConstant.SUPER_USER_ID);
%>
<!-- ExtJS样式文件 -->
<link rel="stylesheet" href="js/lib/ext4/resources/ext-theme-${empty CSS_STYLE?'classic':CSS_STYLE }/ext-theme-${empty CSS_STYLE?'classic':CSS_STYLE }-all.css">

<!-- Grid引擎JS文件 -->
<script type="text/javascript">
	//域路径
	var extPath = '<%=path%>';
	//域名访问路径
	var extBasePath = '<%=basePath%>';

	//生成全球唯一主键
	function guidGenerator() {
	    var S4 = function() {
	       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
	    };
	    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
	}
	//错误信息提示
	var onActionFailureTypeShow=function(action){
		switch (action.failureType) {
		    case Ext.form.action.Action.CLIENT_INVALID:
		        Ext.Msg.show({title : '提示信息',msg :'from表达参数验证失败！',buttons : Ext.Msg.OK,icon : Ext.MessageBox.ERROR});
		        break;
		    case Ext.form.action.Action.CONNECT_FAILURE:
		        Ext.Msg.show({title : '提示信息',msg :'请求出现错误！',buttons : Ext.Msg.OK,icon : Ext.MessageBox.ERROR});
		        break;
		    case Ext.form.action.Action.SERVER_INVALID:
		       Ext.Msg.show({title : '提示信息',msg :action.result.msg,buttons : Ext.Msg.OK,icon : Ext.MessageBox.ERROR});
		    default:
		    	Ext.Msg.show({title : '提示信息',msg :'未知错误',buttons : Ext.Msg.OK,icon : Ext.MessageBox.ERROR});
		}    
	}
	//用户登录id
	var SESSION_USER_ID="${SESSION_USER.userId}";
	//用户登录Uuid
	var SESSION_USER_UUID="${SESSION_USER.userUuid}";
	//用户登录名称
	var SESSION_USER_NAME="${SESSION_USER.userName}[${SESSION_USER.orgName}]&nbsp;角色[${SESSION_USER_ROLE}]";
	//超级管理员
	var SUPER_USER_ID = "${SUPER_USER_ID}";
	//用户角色
	var SESSION_USER_ROLE="${SESSION_USER_ROLE}";
	//屏幕宽度
	var WIDTH=window.screen.width;
	//屏幕高度
	var HEIGHT=window.screen.height;
	
	
</script>

<script src="js/lib/ext4/ext-all.js"></script>
<script src="js/lib/ext4/ext-lang-zh_CN.js"></script>
<script src="js/lib/ext4/json2.js"></script>
