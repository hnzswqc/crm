/*
 * File: mainFrame/controller/TreeMenuController.js
 *
 * This file was generated by Sencha Architect version 2.2.3.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('mainFrame.controller.TreeMenuController', {
    extend: 'Ext.app.Controller',

    id: 'TreeMenuController',
    models: [
        'TreeMenuModel'
    ],
    stores: [
        'TreeMenuStore'
    ],
    views: [
        'MainViewport'
    ],
    //左侧菜单加载事件
    onLeftPanelRender: function(component, eOpts) {
		this.loadLeftPanelStore({modelSubsystemUuid: REQUEST_SUBSYTEM_UUID},component);
    },
    //加载左侧菜单
    loadLeftPanelStore:function(data,component){
    	Ext.Msg.wait('正在加载数据，请稍候...','提示');
    	component.removeAll();
		Ext.Ajax.request({
		    url: 'model/getModelList.do',
		    params: data,
		    success: function(response){
		    	Ext.Msg.hide();
		        var jsonBean = JSON.parse(response.responseText);
		        if(jsonBean.success){
		        	Ext.each(jsonBean.data,function(bean){
		        		this.buildPanel(component,bean);
		        	},this);
		        	component.doLayout();//刷新显示  
		        }
		    },
		    failure: function(form,action){
		    	Ext.Msg.hide();
		    	onActionFailureTypeShow(action);
		    },
 		    scope:this
		});
    },
    //拼装菜单menu
    buildPanel:function(component,bean){
    	var panel=Ext.create("Ext.tree.Panel",{
	    		 title: bean.modelTitle,
	    		 icon: bean.modelIcon,
	    		 id:'treepanel_menu_'+bean.modelUuid,
	    		 rootVisible: false,
	    		 modelUuid:bean.modelUuid,
	    		 store:Ext.create("mainFrame.store.TreeMenuStore",{autoLoad:false})
	         });
    	   component.add(panel);
    },
    //左侧功能菜单
    onTreeMenuPanelRender: function(component, eOpts) {
    	component.store.setProxy({
				 type: 'ajax', 
				 url: 'power/getUserPowerTreeList.do',
				 autoLoad:false,
				 storeId:'treepanel_menu_stroreId_'+component.modelUuid,
				 extraParams:{
		   	 	 	'modelUuid':component.modelUuid
			 	 },
			 	 reader: {
					 type: 'json'
		     	  	 //数据格式为json
			 	}
		}); 
    	component.store.load(); 
    },
    //菜单展开事件
    onMenuExpand: function(p, eOpts) {
    	
    },
    //菜单双击事件
    onTreeMenuPanelItemClick: function(dataview, record, item, index, e, eOpts) {
        if(!record.data.leaf){
            return;
        }
        if(!Ext.getCmp(record.data.id)){
        	var url = record.data.url;
        	if(url.indexOf('?')>-1){
        		url+="&id="+record.data.id;
        	}else{
        		url+="?id="+record.data.id;
        	}
        	 Ext.getCmp("center").add({
                 animCollapse: false,
                 border:0,
                 closable: true,
                 icon:record.data.icon,
                 title:record.data.text,
                 id:record.data.id,
                 scripts:true,  
                 html:"<iframe scrolling='auto' frameborder='0' border='0' width='100%' height='100%' src='"+url+"'>正在加载，请稍后</iframe>"
             });
        	// Ext.getCmp("center").doLayout();//刷新显示  
        }
        Ext.getCmp("center").setActiveTab(Ext.getCmp(record.data.id));
    },
    //退出按钮
    onExitClick: function(button, e, eOpts) {
    	Ext.MessageBox.confirm("提示信息", "确定要退出吗？", function (btnId) {
 			if(btnId=='yes'){
 			   Ext.Msg.wait('正在处理数据，请稍候...','提示');
 			   Ext.Ajax.request({
            		url:"exit.do",
            		method:"POST",
            		success:function(responst){
 				   		Ext.Msg.hide();
 				   		window.location.href=extBasePath;
            		},
        		    failure: function(from,action){
            			 Ext.Msg.hide();
            			 onActionFailureTypeShow(action);
            		},
            		scope:this
            	});
 			}
 		 },this);
    },
    //左侧功能菜单
    onToolbarRender: function(component, eOpts) {
    	//定时器
		var task = {                 
				run: function () {             
					//Ext.fly('clock').update(Ext.Date.format(new Date(), 'Y-m-d g:i:s A'));  
					var date = Ext.Date.format(new Date(), 'Y-m-d G:i:s A l');
					Ext.getCmp("clock").setValue(date);
				},                  
				interval: 1000 //1 second         
			}             
		Ext.TaskManager.start(task); 
		Ext.getCmp("userName").setValue(SESSION_USER_NAME);
    },
    //修改密码按钮
    onUpdPasswordBtnClick:function(button, e, eOpts) {
    	if(SESSION_USER_ID==SUPER_USER_ID){
    		Ext.Msg.show({title : '提示信息',msg :'超级管理员不允许修改密码',buttons : Ext.Msg.OK,icon : Ext.MessageBox.WARNING});
    		return;
    	}
    	Ext.create("mainFrame.view.UpdPasswordWindow",{title:'修改密码',icon:'img/key.png'}).show();
    	Ext.getCmp("userId").setValue(SESSION_USER_ID);
    },
    //修改密码按钮
    onUpd_password_save_btnClick:function(button, e, eOpts) {
    	 var formPanel =  Ext.getCmp("UpdPasswordForm");
       	 if(!formPanel.isDirty()){
       		 Ext.getCmp("UpdPasswordWindow").close();
    		 return;
       	 }
       	 if(formPanel.isValid()){
       		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
        			if(btnId=='yes'){
        				Ext.Msg.wait('正在处理数据，请稍候...','提示');
        				Ext.Ajax.request({
    					    url:extBasePath+'updPassword.do',
            	  		    params: formPanel.getValues(),
            	  		    success: function(response){
            	  		    	Ext.Msg.hide();
            	  		        var jsonBean = JSON.parse(response.responseText);
            	  		        if(jsonBean.success){
            	  		          Ext.Msg.show({
            	  	  				title : '提示信息',
            	  	  				msg :'修改成功！',
            	  	  				buttons : Ext.Msg.OK,
            	  	  				icon : Ext.MessageBox.INFO
            	  		          });
            	  		        Ext.getCmp("UpdPasswordWindow").close();
            	  		        }else{
            	  		        	Ext.Msg.show({
            	  	  	  				title : '提示信息',
            	  	  	  				msg :jsonBean.data,
            	  	  	  				buttons : Ext.Msg.OK,
            	  	  	  				icon : Ext.MessageBox.WARNING
            	  	  		          });
            	  		        }
            	  		    },
            	  		    failure: function(){
            	  		    	Ext.Msg.hide();
            	  		    	onActionFailureTypeShow(action);
            	  		    },
            	   		    scope:this
            	  		});
        			}
        		 },this);
    	 	}
    },
    //关闭密码按钮
    onUpd_password_close_btnClick:function(button, e, eOpts) {
    	Ext.getCmp("UpdPasswordWindow").close();
    },
    //刷新按钮
    onRefreshBtnClick:function(button, e, eOpts) {
    	top.window.location.reload(true);
    },
    //切换子系统按钮
    onChangeSubsystemBtnClick:function(button, e, eOpts) {
    	Ext.create("mainFrame.view.SubsystemWindow",{title:'切换子系统',icon:'img/subsystem.png'}).show();
    	this.loadSubsystem();
    },
    //获取子系统信息
    loadSubsystem:function(){
    	Ext.Msg.wait('正在处理数据，请稍候...','提示');
		Ext.Ajax.request({
		    url:'sub/getUserSubsystemList.do',
  		    success: function(response){
  		    	Ext.Msg.hide();
  		        var jsonBean = JSON.parse(response.responseText);
  		        Ext.each(jsonBean.data,function(bean){
	        		this.loadSubsystemPanel(bean);
	        	},this);
  		    },
  		    failure: function(){
  		    	Ext.Msg.hide();
  		    	onActionFailureTypeShow(action);
  		    },
   		    scope:this
  		});
    },
    //封装panel
    loadSubsystemPanel:function(data){
    	var logo = data.subLogo;
    	if(null==logo||""==logo){
    		logo="img/default_logo.png";
    	}
    	Ext.getCmp("SubsystemContainer").add({
            xtype: 'panel',
            frame: true,
            height: 150,
            margin: 2,
            width: 150,
            id:'panel_'+data.subKey,
            data:data,
            layout: {
                align: 'middle',
                pack: 'center',
                type: 'hbox'
            },
            collapsed: false,
            collapsible: false,
            headerPosition: 'bottom',
            title: data.subTitle,
            titleAlign: 'center',
            bodyStyle: 'cursor:hand',
            items: [
                {
                    xtype: 'image',
                    height: 100,
                    width: 100,
                    src:logo
                }
            ]

    	});
    	Ext.get('panel_'+data.subKey).on({ 
    	    click: function(){
    			Ext.getCmp("subsystemWindow").close();
    			this.loadLeftPanelStore({modelSubsystemUuid: data.subUuid},Ext.getCmp("left"));
    	    },
    	    scope:this
    	});  
    	//鼠标悬停信息
//    	var tip = Ext.create('Ext.tip.ToolTip', {
//    	    target: 'panel_'+data.subKey,
//    	    title:data.subTitle,
//    	    html: data.subNote
//    	});
    },
    //变身按钮
    onUserChangeBtnClick:function(button, e, eOpts) {
    	if(button.text=="还原"){
    		 Ext.MessageBox.confirm("提示信息", "确定要还原吗？", function (btnId) {
    	            if (btnId == "yes") {
    	            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
    	            	 Ext.Ajax.request({
    	            		url:"changeBackUser.do",
    	            		method:"POST",
    	            		success:function(responst){
    	                	 	Ext.Msg.hide();
    	            			var result = JSON.parse(responst.responseText); 
    	            			if(result.success){
    	            				 Ext.Msg.show({
    	                  				title : '提示信息',
    	                  				msg :'还原成功!',
    	                  				buttons : Ext.Msg.OK,
    	                  				icon : Ext.MessageBox.INFO
    	                  			 });
    	            				 window.location.reload(true);
    	            			}else{
    	            				 Ext.Msg.show({
    	                   				title : '提示信息',
    	                   				msg :'还原失败!',
    	                   				buttons : Ext.Msg.OK,
    	                   				icon : Ext.MessageBox.WARNING
    	                   			 });
    	            			}
    	            		},
    	        		    failure: function(from,action){
    	            			 Ext.Msg.hide();
    	            			 onActionFailureTypeShow(action);
    	            		},
    	            		scope:this
    	            	});
    	            }
    	        },this);
    	        
    	}else{
    		Ext.create("mainFrame.view.ChangUserWindow",{title:'选择变身人员',icon:'img/user_change.png'}).show();
    	}
    },
    //关闭变身窗口
    onChange_user_close_btnClick:function(button, e, eOpts) {
    	Ext.getCmp("changUserWindow").close();
    },
    //根据id刷新不同的面吧
    onChange_user_refresh_btnClick:function(button,e,eOpts){
    	var id = Ext.getCmp("userAuthorityTab").getActiveTab().id;
	    if(id==USER_APPLY){
	    	this.onUSER_APPLYActivate();
	    }else if(id==USER_APPROVE){
	    	this.onUSER_APPROVEActivate();
	    }else if(id==USER_AUTHORITY){
	    	this.onUSER_AUTHORITYActivate();
	    }else{
	    	this.onOTHER_AUTHORITYActivate();
	    }
    },
    //用户申请
    onUSER_APPLYActivate:function(component,eOpts){
    	this.reloadUserAuthorityGridPanelStore("applyGridPanel",{"type":USER_APPLY});
    	Ext.getCmp("Change_user_cancl_btn").enable(true);
    	Ext.getCmp("Change_user_apply_btn").enable(true);
    	Ext.getCmp("Change_user_pass_btn").setDisabled(true);
    	Ext.getCmp("Change_user_authorize_btn").setDisabled(true);
    	Ext.getCmp("Change_user_change_btn").setDisabled(true);
    },
    //本人待审
    onUSER_APPROVEActivate:function(component,eOpts){
    	this.reloadUserAuthorityGridPanelStore("approveGridPanel",{"type":USER_APPROVE});
    	Ext.getCmp("Change_user_pass_btn").enable(true);
    	Ext.getCmp("Change_user_cancl_btn").setDisabled(true);
    	Ext.getCmp("Change_user_apply_btn").setDisabled(true);
    	Ext.getCmp("Change_user_authorize_btn").setDisabled(true);
    	Ext.getCmp("Change_user_change_btn").setDisabled(true);
    },
    //本人授权
    onUSER_AUTHORITYActivate:function(component,eOpts){
    	this.reloadUserAuthorityGridPanelStore("authorizeGridPanel",{"type":USER_AUTHORITY});
    	Ext.getCmp("Change_user_cancl_btn").enable(true);
    	Ext.getCmp("Change_user_pass_btn").setDisabled(true);
    	Ext.getCmp("Change_user_apply_btn").setDisabled(true);
    	Ext.getCmp("Change_user_authorize_btn").enable(true);
    	Ext.getCmp("Change_user_change_btn").setDisabled(true);
    },
    //他人授权
    onOTHER_AUTHORITYActivate:function(component,eOpts){
    	this.reloadUserAuthorityGridPanelStore("authorizeGridPanel",{"type":OTHER_AUTHORITY});
    	Ext.getCmp("Change_user_cancl_btn").setDisabled(true);
    	Ext.getCmp("Change_user_pass_btn").setDisabled(true);
    	Ext.getCmp("Change_user_apply_btn").setDisabled(true);
    	Ext.getCmp("Change_user_authorize_btn").setDisabled(true);
    	Ext.getCmp("Change_user_change_btn").setDisabled(false);//他人授权，直接变身
    	
    },
    //加载数据方法
    reloadUserAuthorityGridPanelStore:function(tabGridPanel,param){
    	var gridPanel = Ext.getCmp(tabGridPanel);
    	gridPanel.store.setProxy({
	    		type: 'ajax', 
	    		url: 'userAuthority/getUserAuthorityList.do',
	    		extraParams:param,
    		 	reader: {
	    			 type: 'json',
	          	  	 //数据格式为json
	                 root: 'data',   
	                 //获取数据总数 
	                 totalProperty: 'dataSize'
    		 	}
		 }); 
    	gridPanel.store.load(); 
    },
    //申请按钮
    onChange_user_apply_btnClick:function(){
    	Ext.create("mainFrame.view.ChoseUserWindow",{title:'申请-选择人员[红色为已申请授权过的人员]',icon:'img/user.png'}).show();
    	Ext.getCmp("user_authority_type").setValue(USER_APPLY);
    	this.reloadChoseGridPanelStore({"type":USER_APPLY},"人员管理");
    },
    //授权按钮
    onChange_user_authorize_btnClick:function(){
    	Ext.create("mainFrame.view.ChoseUserWindow",{title:'授权-选择人员[红色为已申请授权过的人员]',icon:'img/user_business_boss.png'}).show();
    	Ext.getCmp("user_authority_type").setValue(USER_AUTHORITY);
    	this.reloadChoseGridPanelStore({"type":USER_AUTHORITY},"人员管理");
    },
    //部门点击事件
    onOrgTreePanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	var type= Ext.getCmp("user_authority_type").value;
    	this.reloadChoseGridPanelStore({'type':type,'orgUuid':record.data.id},record.data.text+"-人员管理");
    },
    //加载选择人员数据
    reloadChoseGridPanelStore:function(params,title){
    	var gridPanel = Ext.getCmp("choseGridPanel");
    	gridPanel.store.setProxy({
	    		type: 'ajax', 
	    		url: 'user/getUserPage.do',
	    		extraParams:params,
    		 	reader: {
	    			 type: 'json',
	          	  	 //数据格式为json
	                 root: 'data',   
	                 //获取数据总数 
	                 totalProperty: 'dataSize'
    		 	}
		 }); 
    	gridPanel.store.load(); 
    	gridPanel.setTitle(title);
    },
    //选择人员刷新按钮
    onChose_user_refresh_btnClick:function(button,e,eOpts){
    	this.reloadChoseGridPanelStore(null,"人员管理");
    },
    //选择人员关闭按钮
    onChose_user_close_btnClick:function(button,e,eOpts){
    	Ext.getCmp("ChoseUserWindow").close();
    },
    //选择人员保存按钮
    onChose_user_save_btnClick:function(button,e,eOpts){
    	var gridPanel = Ext.getCmp("choseGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请至少选择一条人员信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var type= Ext.getCmp("user_authority_type").value;
        if(type==USER_APPLY){
        	Ext.create("mainFrame.view.UserAuthorityNoteWindow",{title:'申请说明',icon:'img/note.png'}).show();
        }else{
        	Ext.create("mainFrame.view.UserAuthorityNoteWindow",{title:'授权说明',icon:'img/note.png'}).show();
        }
    },
    //关闭申请说明按钮
    onUser_authority_close_btnClick:function(button,e,eOpts){
    	Ext.getCmp("UserAuthorityNoteWindow").close();
    },
    //保存申请说明按钮
    onUser_authority_save_btnClick:function(button,e,eOpts){
    	var type =  Ext.getCmp("user_authority_type").value;
    	var note = Ext.getCmp("UserAuthorityNote").value;
        var userUuids="";
        var userNames="";
    	Ext.each(Ext.getCmp("choseGridPanel").getSelectionModel().getSelection(),function(record,i){
    		if(i>0){
    			userUuids+=",";
    			userNames+=",";
    		}
    		userUuids+=record.data.userUuid;
    		userNames+=record.data.userName;
    	});
    	var data={"type":type,"note":note,"userUuids":userUuids,"userNames":userNames};
    	Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
            if (btnId == "yes") {
            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
            	 Ext.Ajax.request({
            		url:"userAuthority/addUserAuthority.do",
            		method:"POST",
            		params:data,
            		success:function(responst){
                	 	Ext.Msg.hide();
            			var result = JSON.parse(responst.responseText); 
            			if(result.success){
            				 Ext.Msg.show({
                  				title : '提示信息',
                  				msg :'保存成功!',
                  				buttons : Ext.Msg.OK,
                  				icon : Ext.MessageBox.INFO
                  			 });
            				 Ext.getCmp("UserAuthorityNoteWindow").close();//备注
            				 Ext.getCmp("ChoseUserWindow").close();//选择人员
            				 this.onChange_user_refresh_btnClick();
            			}else{
            				 Ext.Msg.show({
                   				title : '提示信息',
                   				msg :'保存失败!',
                   				buttons : Ext.Msg.OK,
                   				icon : Ext.MessageBox.WARNING
                   			 });
            			}
            		},
        		    failure: function(from,action){
            			 Ext.Msg.hide();
            			 onActionFailureTypeShow(action);
            		},
            		scope:this
            	});
            }
        },this);
    },
    //审核按钮
    onChange_user_pass_btnClick:function(button,e,eOpts){
    	var gridPanel = Ext.getCmp("approveGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请至少选择一条信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
    	Ext.create("mainFrame.view.UserAuthorityInfoWindow",{title:'本人待审基本信息',icon:'img/note.png'}).show();
	    var record = selectionModel.getSelection()[0];
        Ext.getCmp("UserAuthorityForm").loadRecord(record);
    },
    //审核界面关闭按钮
    onUser_authority_info_close_btnClick:function(button,e,eOpts){
    	Ext.getCmp("UserAuthorityInfoWindow").close();
    },
    //审核界面保存按钮
    onUser_authority_info_save_btnClick:function(button,e,eOpts){
    	var jsonData = Ext.getCmp("UserAuthorityForm").getValues(true);
    	Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
            if (btnId == "yes") {
            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
            	 Ext.Ajax.request({
            		url:"userAuthority/updUserAuthorityState.do",
            		method:"POST",
            		params:jsonData,
            		success:function(responst){
                	 	Ext.Msg.hide();
            			var result = JSON.parse(responst.responseText); 
            			if(result.success){
            				 Ext.Msg.show({
                  				title : '提示信息',
                  				msg :'保存成功!',
                  				buttons : Ext.Msg.OK,
                  				icon : Ext.MessageBox.INFO
                  			 });
            				 Ext.getCmp("UserAuthorityInfoWindow").close();
            				 this.reloadUserAuthorityGridPanelStore("approveGridPanel",{"type":USER_APPROVE});
            			}else{
            				 Ext.Msg.show({
                   				title : '提示信息',
                   				msg :'保存失败!',
                   				buttons : Ext.Msg.OK,
                   				icon : Ext.MessageBox.WARNING
                   			 });
            			}
            		},
        		    failure: function(from,action){
            			 Ext.Msg.hide();
            			 onActionFailureTypeShow(action);
            		},
            		scope:this
            	});
            }
        },this);
    },
    
    //个人申请单击事件
    onApplyGridPanelCellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	if(record.data.state==DIC_AUTHORITY_STATE_AUDIT_THROUGH){
    		Ext.getCmp("Change_user_change_btn").setDisabled(false);
    	}else{
    		Ext.getCmp("Change_user_change_btn").setDisabled(true);
    	}
    },
    //变身按钮
    onChange_user_change_btnClick:function(button,e,eOpts){
    	var id = Ext.getCmp("userAuthorityTab").getActiveTab().id;
    	var gridPanel = Ext.getCmp("applyGridPanel");
	    if(id==USER_APPLY){
	    	gridPanel = Ext.getCmp("applyGridPanel");
	    }else if(id==OTHER_AUTHORITY){
	    	gridPanel = Ext.getCmp("authorizeOtherGridPanel");
	    }
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请至少选择一条信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var record = selectionModel.getSelection()[0];
        
        Ext.MessageBox.confirm("提示信息", "确定要变身吗？", function (btnId) {
            if (btnId == "yes") {
            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
            	 Ext.Ajax.request({
            		url:"changeUser.do",
            		method:"POST",
            		params:record.data,
            		success:function(responst){
                	 	Ext.Msg.hide();
            			var result = JSON.parse(responst.responseText); 
            			if(result.success){
            				 Ext.Msg.show({
                  				title : '提示信息',
                  				msg :'变身成功!',
                  				buttons : Ext.Msg.OK,
                  				icon : Ext.MessageBox.INFO
                  			 });
            				 Ext.getCmp("changUserWindow").close();
            				 window.location.reload(true);
            			}else{
            				 Ext.Msg.show({
                   				title : '提示信息',
                   				msg :'变身失败!',
                   				buttons : Ext.Msg.OK,
                   				icon : Ext.MessageBox.WARNING
                   			 });
            			}
            		},
        		    failure: function(from,action){
            			 Ext.Msg.hide();
            			 onActionFailureTypeShow(action);
            		},
            		scope:this
            	});
            }
        },this);
    },
    //取消按钮
    onChange_user_cancl_btnClick:function(button,e,eOpts){
    	var id = Ext.getCmp("userAuthorityTab").getActiveTab().id;
    	var gridPanel = Ext.getCmp("applyGridPanel");
	    if(id==USER_APPLY){
	    	gridPanel = Ext.getCmp("applyGridPanel");
	    }else if(id==USER_AUTHORITY){
	    	gridPanel = Ext.getCmp("authorizeGridPanel");
	    }
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请至少选择一条信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var record = selectionModel.getSelection()[0];
        Ext.MessageBox.confirm("提示信息", "确定要取消吗？", function (btnId) {
            if (btnId == "yes") {
            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
            	 Ext.Ajax.request({
            		url:"userAuthority/delUserAuthorityState.do",
            		method:"POST",
            		params:record.data,
            		success:function(responst){
                	 	Ext.Msg.hide();
            			var result = JSON.parse(responst.responseText); 
            			if(result.success){
            				 Ext.Msg.show({
                  				title : '提示信息',
                  				msg :'取消成功!',
                  				buttons : Ext.Msg.OK,
                  				icon : Ext.MessageBox.INFO
                  			 });
            				 //刷新
            				 this.onChange_user_refresh_btnClick();
            			}else{
            				 Ext.Msg.show({
                   				title : '提示信息',
                   				msg :'取消失败!',
                   				buttons : Ext.Msg.OK,
                   				icon : Ext.MessageBox.WARNING
                   			 });
            			}
            		},
        		    failure: function(from,action){
            			 Ext.Msg.hide();
            			 onActionFailureTypeShow(action);
            		},
            		scope:this
            	});
            }
        },this);
    },
    init: function(application) {
        this.control({
            "panel[id=left]": {
        		beforerender: this.onLeftPanelRender
            },
            "treepanel[id^=treepanel_menu_]": {
            	expand: this.onMenuExpand,
            	itemclick: this.onTreeMenuPanelItemClick,
            	render: this.onTreeMenuPanelRender
            },
            "button[id=exit]":{
            	click:this.onExitClick
            },
            "#toolbar":{
            	afterrender: this.onToolbarRender
            },
            'button[id=updPasswordBtn]':{
            	click : this.onUpdPasswordBtnClick
            },
            'button[id=upd_password_save_btn]':{
            	click : this.onUpd_password_save_btnClick
            },
            'button[id=upd_password_close_btn]':{
            	click : this.onUpd_password_close_btnClick
            },
            'button[id=refreshBtn]':{
            	click : this.onRefreshBtnClick
            },
            'button[id=ChangeSubsystemBtn]':{
            	click : this.onChangeSubsystemBtnClick
            },
            'button[id=UserChangeBtn]':{
            	click : this.onUserChangeBtnClick
            },
            'button[id=Change_user_close_btn]':{
            	click : this.onChange_user_close_btnClick
            },
            "#USER_APPLY":{
            	activate:this.onUSER_APPLYActivate
            },
            "#USER_APPROVE":{
            	activate:this.onUSER_APPROVEActivate
            },
            "#USER_AUTHORITY":{
            	activate:this.onUSER_AUTHORITYActivate
            },
            "#OTHER_AUTHORITY":{
            	activate:this.onOTHER_AUTHORITYActivate
            },
            "button[id=Change_user_apply_btn]":{
            	click:this.onChange_user_apply_btnClick
            },
            "button[id=Change_user_authorize_btn]":{
            	click:this.onChange_user_authorize_btnClick
            },
            "#orgTreePanel":{
            	cellclick: this.onOrgTreePanelCellClick
            },
            "button[id=chose_user_refresh_btn]":{
            	click:this.onChose_user_refresh_btnClick
            },
            "button[id=chose_user_close_btn]":{
            	click:this.onChose_user_close_btnClick
            },
            "button[id=chose_user_save_btn]":{
            	click:this.onChose_user_save_btnClick
            },
            "button[id=User_authority_save_btn]":{
            	click:this.onUser_authority_save_btnClick
            },
            "button[id=User_authority_close_btn]":{
            	click:this.onUser_authority_close_btnClick
            },
            "button[id=Change_user_refresh_btn]":{
            	click:this.onChange_user_refresh_btnClick
            },
            "button[id=Change_user_pass_btn]":{
            	click:this.onChange_user_pass_btnClick
            },
            "button[id=User_authority_info_close_btn]":{
            	click:this.onUser_authority_info_close_btnClick
            },
            "button[id=User_authority_info_save_btn]":{
            	click:this.onUser_authority_info_save_btnClick
            },
            "#applyGridPanel":{
            	cellclick: this.onApplyGridPanelCellclick
            },
            "button[id=Change_user_change_btn]":{
            	click:this.onChange_user_change_btnClick
            },
            "button[id=Change_user_cancl_btn]":{
            	click:this.onChange_user_cancl_btnClick
            }
            
            
        });
    }

});

