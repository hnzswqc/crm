/*
 * File: user/controller/UserController.js
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

Ext.define('User.controller.UserController', {
    extend: 'Ext.app.Controller',

    id: 'UserController',
    models: [
         'UserModel',
         'OrgTreeNode',
         'UserRoleModel'
     ],
     stores: [
         'OrgTreeStore',
         'UserGridPanelStore',
         'UserStateStore',
         'UserGenderStore',
         'UserRoleStore'
     ],
     views: [
         'UserViewport',
         'UserWindow',
         'UserChangeOrgWindow',
         'ResignedWindow'
     ],

	//刷新按钮
    onUser_refresh_btnClick: function(button, e, eOpts) {
		this.reloadOrgGridPanelStore({'orgUuid':''},"人员管理");
    },
    //添加按钮
    onUser_add_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("OrgTreePanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条组织机构信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var data = selectionModel.getSelection()[0].data;
        //Ext.create("User.view.UserWindow",{title:'添加人员信息',icon:'img/add.png'}).show();
        Ext.create("User.view.NormalUserWindow",{title:'添加员工信息',icon:'img/add.png'}).show();
        //加载所属角色
        this.reloadOrgRoleStore(data.id);
        
        Ext.getCmp("orgUuid").setValue(data.id);
        this.getUserNumber(data.id);
    },
    //刷新数据
    reloadOrgRoleStore:function(orgUuid){
    	var gridPanel=Ext.getCmp("roleUuid");
    	gridPanel.store.setProxy({
	 		url:'pbf/searchOrgRoleList.do',
            type: 'ajax',
            extraParams:{"orgUuid":orgUuid},
            actionMethods: {
        		read: 'POST'
    		},
            reader: {
    			type: 'json',
	   	  	 	//数据格式为json   
	            root: 'data'
            }
    	});
    	gridPanel.store.load(); 
    },
    //自定预设员工编号
    getUserNumber:function(orgUuid){
    	Ext.Msg.wait('正在自动获取员工编号，请稍候...','提示');
        Ext.Ajax.request({
    		url:"user/getUserNumber.do",
    		method:"POST",
    		params:{"orgUuid":orgUuid},
    		success:function(responst){
        	 	Ext.Msg.hide();
    			var result = JSON.parse(responst.responseText); 
    			if(result.success){
    				   Ext.getCmp("User_window_userNumber").setValue(result.data);
    			}else{
    				 Ext.Msg.show({
           				title : '提示信息',
           				msg :'获取失败，请手动输入',
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
    },
    //折叠按钮
    onUser_folding_btnClick: function(button, e, eOpts) {
    	Ext.getCmp("OrgTreePanel").collapseAll();
    },
    //展开按钮
    onUser_folder_open_btnClick: function(button, e, eOpts) {
    	Ext.getCmp("OrgTreePanel").expandAll();
    },
    //高级查询按钮
    onUser_show_form_btnClick: function(button, e, eOpts) {
    	 var panel = Ext.getCmp("searchForm");
         if(panel.isHidden()){
             panel.show();
         }else{
             panel.hide();
         }
    },
    //修改按钮
    onUser_upd_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("UserGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条人员信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        //Ext.create("User.view.UserWindow",{title:'修改人员信息',icon:'img/update.png'}).show();
   	    //Ext.getCmp("NormalUserFormPanel").loadRecord(selectionModel.getSelection()[0]);
         Ext.create("User.view.NormalUserWindow",{title:"修改员工基本信息",icon:'img/update.png'}).show();
    	 //加载所属角色
    	 this.reloadOrgRoleStore(selectionModel.getSelection()[0].data.orgUuid);
    	 Ext.getCmp("orgUuid").setValue(selectionModel.getSelection()[0].data.orgUuid);
    	 Ext.getCmp("NormalUserFormPanel").getForm().loadRecord(selectionModel.getSelection()[0]);

    },
    //删除按钮
    onUser_del_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("UserGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条人员信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var data = selectionModel.getSelection()[0].data;
        Ext.MessageBox.confirm("提示信息", "确定要删除吗？", function (btnId) {
            if (btnId == "yes") {
            	Ext.Msg.wait('正在处理数据，请稍候...','提示');
                Ext.Ajax.request({
            		url:"user/delUser.do",
            		method:"POST",
            		params:data,
            		success:function(responst){
                	 	Ext.Msg.hide();
            			var result = JSON.parse(responst.responseText); 
            			if(result.success){
            				 Ext.Msg.show({
                  				title : '提示信息',
                  				msg :'删除成功!',
                  				buttons : Ext.Msg.OK,
                  				icon : Ext.MessageBox.INFO
                  			 });     
            				 Ext.getCmp("UserGridPanel").store.load();
            			}else{
            				 Ext.Msg.show({
                   				title : '提示信息',
                   				msg :'删除失败!',
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
    //保存按钮
    onUser_save_btnClick: function(button, e, eOpts) {
    	 var formPanel =  Ext.getCmp("userForm");
    	 if(!formPanel.isDirty()){
    		 Ext.getCmp("UserWindow").close();
     		 return;
    	 }
    	 if(formPanel.isValid()){
    		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
     			if(btnId=='yes'){
     				 formPanel.submit({
         				 url:'user/addUser.do',
     		             method:'POST',
     		             waitTitle : "提示",
     		             waitMsg: '正在提交数据，请稍后...',
     		             success: function(form, action){
     		        	    var result = JSON.parse(action.response.responseText); 
     		            	if(result.success){
     		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
     		            		 Ext.getCmp("UserWindow").close();
     		            		 Ext.getCmp("UserGridPanel").store.load();
     		            	}else{
     		            		 Ext.Msg.show({title : '提示信息',msg :'操作失败!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
     		            	}
     		            },
     		            failure: function(form, action){
     		            	onActionFailureTypeShow(action);
     		            },
     		            scope:this
         			 });
     			}
     		 },this);
	 	}
    	
    },
    //关闭按钮
    onUser_close_btnClick: function(button, e, eOpts) {
    	 Ext.getCmp("UserWindow").close();
    },
    //定时器,解决单击事件双击事件问题
    task:new Ext.util.DelayedTask(),
    //组织机构双击事件
    onOrgGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	//组织机构双击事件
    },
    //组织机构单击事件
    onOrgGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	if(Ext.getCmp("User_add_btn").enable(true))Ext.getCmp("User_add_btn").enable(true);
      	if(Ext.getCmp("User_org_role_set_btn").enable(true))Ext.getCmp("User_org_role_set_btn").enable(true);
    	this.reloadOrgGridPanelStore({'orgUuid':record.data.id},record.data.text+"-人员管理");
    },
   //用户双击事件
    onUserGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	 
    	 Ext.create("User.view.NormalUserWindow",{title:"修改员工基本信息",icon:'img/update.png'}).show();
     	 //加载所属角色
     	 this.reloadOrgRoleStore(record.data.orgUuid);
     	 Ext.getCmp("orgUuid").setValue(record.data.orgUuid);
     	 Ext.getCmp("NormalUserFormPanel").getForm().loadRecord(record);
     	 
    },
    //用户单击事件
    onUserGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("User_upd_btn").enable(true);
    	Ext.getCmp("User_del_btn").enable(true);
    	Ext.getCmp("User_upd_org_btn").enable(true);
    	Ext.getCmp("User_role_set_btn").enable(true);
    	Ext.getCmp("User_reset_password_btn").enable(true);
    	Ext.getCmp("User_resigned_btn").enable(true);
    	if (e.getTarget().innerHTML === '查看' ){
			this.showFileHref(record.data,cellIndex);
		}else if(e.getTarget().innerHTML === '上传' ){
			this.upoadFileHref(record.data,cellIndex);
		}else if(e.getTarget().innerHTML === '删除' ){
			this.delFileHref(record.data,cellIndex);
		}
    	
    },
    //点击超链接查看附件
	showFileHref(data,cellIndex){
    	var type;
    	var fileType;
    	if(cellIndex==4){
    		type = DIC_RECORD_ID_CARD;
    		fileType = data.sfz;
    	}else if(cellIndex==5){
    		type = DIC_RECORD_JOIN_TJ_MSG;
    		fileType = data.tjzm;
    	}else if(cellIndex==6){
    		type = DIC_RECORD_JOIN_MSG;
    		fileType = data.rzbg;
    	}else if(cellIndex==7){
    		type = DIC_RECORD_JOIN_SQZZB;
    		fileType = data.sqzzb;
    	}
    	
		if(fileType.indexOf('image')>-1){
	    	Ext.create("User.view.ShowWindow",{title:'在线查看'}).show();
	    	Ext.getCmp("pic").setSrc(extBasePath+"att/showAttachment.do?foreignUUid="+data.userUuid+"&attType="+type+"&time="+new Date().getTime());
	    }else{
	    	window.location.href=extBasePath+"att/showAttachment.do?foreignUUid="+data.userUuid+"&attType="+type+"&time="+new Date().getTime();
	    }
	},
	//导出按钮
    onExportBtnClick:function(button, e, eOpts) {
        
        window.location.href=Ext.getCmp("pic").src;
    },
    //关闭查看
    onCloseShowWindowBtnClick:function(button, e, eOpts) {
    	Ext.getCmp("ShowWindow").close();
    },
    //刷新数据
    reloadOrgGridPanelStore:function(data,text){
    	var gridPanel=Ext.getCmp("UserGridPanel");
    	gridPanel.store.setProxy({
	 		url:'user/getUserPage.do',
            type: 'ajax',
            extraParams:data,
            actionMethods: {
        		read: 'POST'
    		},
            reader: {
    			type: 'json',
	   	  	 	//数据格式为json   
	            root: 'data',   
	            //获取数据总数 
	            totalProperty:'dataSize'
            }
    	});
    	gridPanel.store.load(); 
    	gridPanel.setTitle(text);
    },
    //查询按钮按钮
    onUser_search_btnClick: function(button, e, eOpts) {
    	var searchForm = Ext.getCmp("searchForm");
    	this.reloadOrgGridPanelStore(searchForm.getValues(),'高级查询-人员信息');
    },
    //重置按钮按钮
    onUser_reset_btnClick: function(button, e, eOpts) {
    	 var searchForm = Ext.getCmp("searchForm");
    	 searchForm.getForm().reset();
    	this.reloadOrgGridPanelStore(null,'人员信息');
    },
   //员工状态
    onUserStateComboboxChange:function(field, newValue, oldValue, eOpts) {
    	var searchForm = Ext.getCmp("searchForm");
    	this.reloadOrgGridPanelStore(searchForm.getValues(),'高级查询-人员信息');
    },
    //变更部门按钮
    onUser_upd_org_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("UserGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条人员信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var data = selectionModel.getSelection()[0].data;
    	Ext.create("User.view.UserChangeOrgWindow",{title:'部门变更',icon:'img/user.png'}).show();
    },
    //加载已存在部门信息
    onUserOrgTreePanelRenderer:function(component,eOpts){
    	var treePanel = Ext.getCmp("UserOrgTreePanel");
    	treePanel.store.setProxy({
      		 url:'user/getOrgTreeList.do',
             autoLoad:true,
             extraParams : Ext.getCmp("UserGridPanel").getSelectionModel().getSelection()[0].data,
             type: 'ajax',
             clearOnLoad: true,
             reader: {
	        	type: 'json'
             }
    	});
    	treePanel.store.load(); 
    },
    //部门变更折叠按钮
    onUser_change_folder_btnClick: function(button, e, eOpts) {
    	Ext.getCmp("UserOrgTreePanel").collapseAll();
    },
    //部门变更展开按钮
    onUser_change_folder_open_btnClick: function(button, e, eOpts) {
    	Ext.getCmp("UserOrgTreePanel").expandAll();
    },
    //部门变更保存按钮
    onUser_change_save_btnClick: function(button, e, eOpts) {
    	var treePanel = Ext.getCmp("UserOrgTreePanel");
    	var json=treePanel.getChecked();
    	var orgUuid="";
    	var orgName="";
    	for(var i=0;i<json.length;i++){
    		if(i>0){
    			orgUuid+=",";
    			orgName+",";
    		}
    		orgUuid+=json[i].data.id;
    		orgName+=json[i].data.text;
    	}
    	if(orgUuid==""){
    		 Ext.Msg.show({
  				title : '提示信息',
  				msg :'请先选择一个组织机构',
  				buttons : Ext.Msg.OK,
  				icon : Ext.MessageBox.WARNING
  			});
    		return;
    	}
    	var userUuid = Ext.getCmp("UserGridPanel").getSelectionModel().getSelection()[0].data.userUuid;
    	var userName = Ext.getCmp("UserGridPanel").getSelectionModel().getSelection()[0].data.userName;
    	
    	 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
             if (btnId == "yes") {
            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
                 Ext.Ajax.request({
             		url:"user/changeUserOrg.do",
             		method:"POST",
             		params:{
                	 	'orgUuid':orgUuid,
                	 	'orgName':orgName,
                	 	'userUuid':userUuid,
                	 	'userName':userName
                 	},
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
             				 Ext.getCmp("UserChangeOrgWindow").close();
             				 Ext.getCmp("UserGridPanel").store.load();
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
    //部门变更关闭按钮
    onUser_change_close_btnClick: function(button, e, eOpts) {
    	Ext.getCmp("UserChangeOrgWindow").close();
    },
    //用户登录名验证
    onUser_window_userIdTextfieldBlur: function(component, e, eOpts) {
    	 var formPanel =  Ext.getCmp("NormalUserFormPanel");
    	 if(!formPanel.isDirty()){
     		 return;
    	 }
    	 if(component.value==component.originalValue||component.value==""){
    		 return;
    	 }
    	Ext.Ajax.request({
    		url:"user/validateUserId.do",
     		method:"POST",
     		params:{
        	 	'userId':component.value
         	},
     		success:function(responst){
     			var result = JSON.parse(responst.responseText); 
     			if(result.success){
     				var msg = '该用户帐号被'+result.data.userName+'占用。';
     				if(result.data.userState==2){
     					msg+='该用户帐号被'+result.data.userName+'占用。(已离职)';
     				}
     				Ext.Msg.show({
        				title : '提示信息',
        				msg : msg,
        				buttons : Ext.Msg.OK,
        				icon : Ext.MessageBox.WARNING
        			 });
     				component.focus();
     				component.setValue("");
     			}
     		},
 		    failure: function(from,action){
     			 Ext.Msg.hide();
     			 onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    },
    //用户员工号验证
    onUser_window_userNumberTextfieldBlur: function(component, e, eOpts) {
    	var formPanel =  Ext.getCmp("NormalUserFormPanel");
	   	 if(!formPanel.isDirty()){
    		 return;
	   	 }
	   	if(component.value==component.originalValue||component.value==""){
	   		 return;
	   	 }
    	Ext.Ajax.request({
    		url:"user/validateUserNumber.do",
     		method:"POST",
     		params:{
        	 	'userNumber':component.value
         	},
     		success:function(responst){
     			var result = JSON.parse(responst.responseText); 
     			if(result.success){
     				var msg = '该用户员工号被'+result.data.userName+'占用。';
     				if(result.data.userState==2){
     					msg+='该用户员工号被'+result.data.userName+'占用。(已离职)';
     				}
     				Ext.Msg.show({
        				title : '提示信息',
        				msg : msg,
        				buttons : Ext.Msg.OK,
        				icon : Ext.MessageBox.WARNING
        			 });
     				component.focus();
     				component.setValue("");
     			}
     		},
 		    failure: function(from,action){
     			 Ext.Msg.hide();
     			 onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    },
    //分配角色按钮
    onUser_role_set_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("UserGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条人员信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var data = selectionModel.getSelection()[0].data;
    	Ext.create("User.view.UserRoleWindow",{title:'为【'+data.userName+'】分配角色',icon:'img/setting.png'}).show();
    	Ext.getCmp("org_user_role_type").setValue("USER");
    	//加载数据
    	this.loadUserOrOrgRoleGridPanelStore('user/getUserRoleList.do',{'userUuid':data.userUuid});
    },
    //加载人员/组织角色数据
    loadUserOrOrgRoleGridPanelStore:function(url,data){
    	var gridPanel = Ext.getCmp("UserRoleGridPanel");
    	gridPanel.store.setProxy({
      		 url:url,
             autoLoad:true,
             extraParams : data,
             type: 'ajax',
             clearOnLoad: true,
             reader: {
	        	type: 'json',
	        	root: 'data'
             }
    	});
    	gridPanel.store.load(function(records, operation, success) {  
    		//默认选中状态
    		Ext.each(records,function(record,i){
    			if(record.data.count>0){
    				gridPanel.getSelectionModel().select(record,true,false)
    			}
    		})
    	},this);
    },
    //关闭分配窗口
    onUser_role_close_btnClick: function(button, e, eOpts) {
    	Ext.getCmp("UserRoleWindow").close();
    },
    //保存分配信息
    onUser_role_save_btnClick: function(button, e, eOpts) {
		var gridPanel = Ext.getCmp("UserRoleGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        var roleUuids="";
        var roleNames="";
    	Ext.each(selectionModel.getSelection(),function(record,i){
    		if(i>0){
    			roleUuids+=",";
    			roleNames+=",";
    		}
    		roleUuids+=record.data.roleUuid;
    		roleNames+=record.data.roleName;
    	});
    	var type = Ext.getCmp("org_user_role_type").value;
    	var params ="";
    	var url = "user/addUserRole.do";
    	if(type=="USER"){
    		var data = Ext.getCmp("UserGridPanel").getSelectionModel().getSelection()[0].data;
        	var userUuid = data.userUuid;
        	var userName = data.userName;
        	params = {'roleUuids':roleUuids,'roleNames':roleNames,'userUuid':userUuid,'userName':userName}
        	url = "user/addUserRole.do";
    	}else{
    		var data = Ext.getCmp("OrgTreePanel").getSelectionModel().getSelection()[0].data;
        	var orgUuid = data.id;
        	var orgName = data.text;
        	params = {'roleUuids':roleUuids,'roleNames':roleNames,'orgUuid':orgUuid,'orgName':orgName}
        	url = "user/addOrgRole.do";
    	}
    	
    	Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
            if (btnId == "yes") {
           	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
                Ext.Ajax.request({
            		url:url,
            		method:"POST",
            		params:params,
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
            				 Ext.getCmp("UserRoleWindow").close();
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
    //为部门分配角色按钮
    onUser_org_role_set_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("OrgTreePanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条组织机构信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var data = selectionModel.getSelection()[0].data;
    	Ext.create("User.view.UserRoleWindow",{title:'为【'+data.text+'】分配角色',icon:'img/setting.png'}).show();
    	Ext.getCmp("org_user_role_type").setValue("ORG");
    	//加载数据
    	this.loadUserOrOrgRoleGridPanelStore('user/getOrgRoleList.do',{'orgUuid':data.id});
    },
    //删除按钮
    onUser_reset_password_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("UserGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条人员信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var data = selectionModel.getSelection()[0].data;
        Ext.MessageBox.confirm("提示信息", "确定要重置密码吗？", function (btnId) {
            if (btnId == "yes") {
            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
                Ext.Ajax.request({
            		url:"user/updPassword.do",
            		method:"POST",
            		params:data,
            		success:function(responst){
                	 	Ext.Msg.hide();
            			var result = JSON.parse(responst.responseText); 
            			if(result.success){
            				 Ext.Msg.show({
                  				title : '提示信息',
                  				msg :'重置成功!',
                  				buttons : Ext.Msg.OK,
                  				icon : Ext.MessageBox.INFO
                  			 });     
            			}else{
            				 Ext.Msg.show({
                   				title : '提示信息',
                   				msg :'重置失败!',
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
    
    //通过身份证号获取生日信息
    onIdCardTextfieldBlur: function(component, e, eOpts) {
    	if(component.isValid()){
    		var birthDay = component.value.substring(6,10)+"-"+component.value.substring(10,12)+"-"+component.value.substring(12,14);
    		var sex = (parseInt(component.value.substr(16,1)))%2==1?DIC_GENDER_MAN:DIC_GENDER_FEMALE;
    		Ext.getCmp("userBirthday").setValue(birthDay);
    		Ext.getCmp("userGender").setValue(sex);
    		Ext.getCmp("userAge").setValue(new Date().getFullYear()-component.value.substring(6,10));
    	}
    },
    //员工离职
    onUser_resigned_btnClick:function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("UserGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条人员信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
    	Ext.create("User.view.ResignedWindow",{tittle:'辞职员工登记',icon:'img/add.png'}).show();
    	Ext.getCmp("ResignedFormPanel").loadRecord(selectionModel.getSelection()[0]);
    },
    //保存离职信息
    onResignedSaveBtnClick:function(button, e, eOpts) {
    	 var formPanel =  Ext.getCmp("ResignedFormPanel");
    	 if(!formPanel.isDirty()){
    		 Ext.getCmp("ResignedWindow").close();
     		 return;
    	 }
    	 
    	 if(formPanel.isValid()){
    		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
     			if(btnId=='yes'){
     				//获取选中的月份呢
     				 var monthCheck = Ext.getCmp('month').items;
                     var month = '';
                     for(var i = 0; i < monthCheck.length; i++){
                         if(monthCheck.get(i).checked){
                        	 if(month!=''){
                        		 month +=","+ monthCheck.get(i).inputValue;
                        	 }else{
                        		 month +=monthCheck.get(i).inputValue;
                        	 }
                         }
                     };
                     
                     
     				 formPanel.submit({
         				 url:'resigned/addResigned.do?month='+month,
     		             method:'POST',
     		             waitTitle : "提示",
     		             waitMsg: '正在提交数据，请稍后...',
     		             success: function(form, action){
     		        	    var result = JSON.parse(action.response.responseText); 
     		            	if(result.success){
     		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
     		            		 Ext.getCmp("ResignedWindow").close();
     		            		 Ext.getCmp("UserGridPanel").store.load();
     		            	}else{
     		            		 Ext.Msg.show({title : '提示信息',msg :'操作失败!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
     		            	}
     		            },
     		            failure: function(form, action){
     		            	onActionFailureTypeShow(action);
     		            },
     		            scope:this
         			 });
     			}
     		 },this);
	 	}
    },
   //关闭离职窗口
    onResignedCloseBtnClick:function(button, e, eOpts) {
    	Ext.getCmp("ResignedWindow").close();
    },
  //试用期员工转正按钮
    onNormalSaveBtnClick:function(button, e, eOpts){
    	 var formPanel =  Ext.getCmp("NormalUserFormPanel");
    	 if(!formPanel.isDirty()){
    		 Ext.getCmp("NormalUerWindow").close();
     		 return;
    	 }
    	 if(formPanel.isValid()){
    		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
     			if(btnId=='yes'){
     				 formPanel.submit({
         				 url:'user/addNormalUser.do',
     		             method:'POST',
     		             waitTitle : "提示",
     		             waitMsg: '正在提交数据，请稍后...',
     		             success: function(form, action){
     		        	    var result = JSON.parse(action.response.responseText); 
     		            	if(result.success){
     		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
     		            		 Ext.getCmp("NormalUerWindow").close();
     		            		 Ext.getCmp("UserGridPanel").store.load();
     		            	}else{
     		            		 Ext.Msg.show({title : '提示信息',msg :'操作失败!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
     		            	}
     		            },
     		            failure: function(form, action){
     		            	onActionFailureTypeShow(action);
     		            },
     		            scope:this
         			 });
     			}
     		 },this);
	 	}
    },
    //试用期员工关闭按钮
    onNormalCloseBtnClick:function(button, e, eOpts){
    	Ext.getCmp("NormalUerWindow").close();
    },
    //上传附件
    delFileHref:function(data,cellIndex){
    	var type;
    	if(cellIndex==4){
    		type = DIC_RECORD_ID_CARD;
    	}else if(cellIndex==5){
    		type = DIC_RECORD_JOIN_TJ_MSG;
    	}else if(cellIndex==6){
    		type = DIC_RECORD_JOIN_MSG;
    	}else if(cellIndex==7){
    		type = DIC_RECORD_JOIN_SQZZB;
    	}
    	var param={"foreignUUid":data.userUuid,"attType":type};
    	 Ext.MessageBox.confirm("提示信息", "确定要删除吗？", function (btnId) {
             if (btnId == "yes") {
             	Ext.Msg.wait('正在处理数据，请稍候...','提示');
                 Ext.Ajax.request({
             		url:"user/delAttachment.do",
             		method:"POST",
             		params:param,
             		success:function(responst){
                 	 	Ext.Msg.hide();
             			var result = JSON.parse(responst.responseText); 
             			if(result.success){
             				 Ext.Msg.show({
                   				title : '提示信息',
                   				msg :'删除成功!',
                   				buttons : Ext.Msg.OK,
                   				icon : Ext.MessageBox.INFO
                   			 });     
             				 Ext.getCmp("UserGridPanel").store.load();
             			}else{
             				 Ext.Msg.show({
                				title : '提示信息',
                				msg :'删除失败!',
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
    //上传附件
    upoadFileHref:function(data,cellIndex){
    	var type;
    	var title;
    	if(cellIndex==4){
    		type = DIC_RECORD_ID_CARD;
    		title="身份证复印件";
    	}else if(cellIndex==5){
    		type = DIC_RECORD_JOIN_TJ_MSG;
    		title="体检报告";
    	}else if(cellIndex==6){
    		type = DIC_RECORD_JOIN_MSG;
    		title="入职应聘表";
    	}else if(cellIndex==7){
    		type = DIC_RECORD_JOIN_SQZZB;
    		title="申请转正表";
    	}
    	Ext.create("User.view.UploadWindow",{title:title,"icon":'img/update.png'}).show();
    	Ext.getCmp("foreignUuid").setValue(data.userUuid);
    	Ext.getCmp("attType").setValue(type);
    },
    //附件保存
    onUploadSaveBtnClick:function(button, e, eOpts){
		var formPanel =  Ext.getCmp("uploadFormPanel");
	   	 if(!formPanel.isDirty()){
	   		 Ext.getCmp("UploadWindow").close();
    		 return;
	   	 }
	   	 if(formPanel.isValid()){
	   		 Ext.MessageBox.confirm("提示信息", "确定要上传吗？", function (btnId) {
	    			if(btnId=='yes'){
	    				 formPanel.submit({
	        				 url:'user/addAttachment.do',
	    		             method:'POST',
	    		             waitTitle : "提示",
	    		             waitMsg: '正在提交数据，请稍后...',
	    		             success: function(form, action){
	    		        	    var result = JSON.parse(action.response.responseText); 
	    		            	if(result.success){
	    		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
	    		            		 Ext.getCmp("UploadWindow").close();
	    		            		 Ext.getCmp("UserGridPanel").store.load();
	    		            	}else{
	    		            		 Ext.Msg.show({title : '提示信息',msg :'操作失败!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
	    		            	}
	    		            },
	    		            failure: function(form, action){
	    		            	onActionFailureTypeShow(action);
	    		            },
	    		            scope:this
	        			 });
	    			}
	    		 },this);
		 	}
    	
    },
    //附件保存
    onUploadCloseBtnClick:function(button, e, eOpts){
    	Ext.getCmp("UploadWindow").close();
    },
    init: function(application) {
        this.control({
            "button[id=User_refresh_btn]": {
                click: this.onUser_refresh_btnClick
            },
            "button[id=User_add_btn]": {
                click: this.onUser_add_btnClick
            },
            "button[id=User_folding_btn]": {
                click: this.onUser_folding_btnClick
            },
            "button[id=User_folder_open_btn]": {
                click: this.onUser_folder_open_btnClick
            },
            "button[id=User_folder_open_btn]": {
                click: this.onUser_folder_open_btnClick
            },
            "button[id=User_show_form_btn]": {
                click: this.onUser_show_form_btnClick
            },
            "button[id=User_upd_btn]": {
                click: this.onUser_upd_btnClick
            },
            "button[id=User_del_btn]": {
                click: this.onUser_del_btnClick
            },
            "button[id=User_upd_org_btn]": {
                click: this.onUser_upd_org_btnClick
            },
            "button[id=User_save_btn]": {
                click: this.onUser_save_btnClick
            },
            "button[id=User_close_btn]": {
                click: this.onUser_close_btnClick
            },
            "button[id=User_search_btn]": {
                click: this.onUser_search_btnClick
            },
            "button[id=User_reset_btn]": {
                click: this.onUser_reset_btnClick
            },
            "button[id=User_change_save_btn]": {
                click: this.onUser_change_save_btnClick
            },
            "button[id=User_change_close_btn]": {
                click: this.onUser_change_close_btnClick
            },
            "button[id=User_change_folder_btn]": {
                click: this.onUser_change_folder_btnClick
            },
            "button[id=User_change_folder_open_btn]": {
                click: this.onUser_change_folder_open_btnClick
            },
            "#OrgTreePanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    				this.task.delay(200,this.onOrgGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onOrgGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "#UserGridPanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    				this.task.delay(200,this.onUserGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onUserGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "#UserChangeOrgWindow":{
            	render:this.onUserOrgTreePanelRenderer
            },
            "textfield[id=User_window_userId]": {
                blur: this.onUser_window_userIdTextfieldBlur
            },
            "button[id=User_role_set_btn]":{
            	click: this.onUser_role_set_btnClick
            },
            "button[id=User_role_close_btn]":{
            	click: this.onUser_role_close_btnClick
            },
            "button[id=User_role_save_btn]":{
            	click: this.onUser_role_save_btnClick
            },
            "button[id=User_org_role_set_btn]":{
            	click:this.onUser_org_role_set_btnClick
            },
            "button[id=User_reset_password_btn]":{
            	click:this.onUser_reset_password_btnClick
            },
            "textfield[id=User_window_userNumber]": {
                blur: this.onUser_window_userNumberTextfieldBlur
            },
            "textfield[id=idCard]": {
                blur: this.onIdCardTextfieldBlur
            },
            "button[id=User_resigned_btn]":{
            	click:this.onUser_resigned_btnClick
            },
            "button[id=ResignedSaveBtn]":{
            	click:this.onResignedSaveBtnClick
            },
            "button[id=ResignedCloseBtn]":{
            	click:this.onResignedCloseBtnClick
            },
            "combobox[id=userState]": {
                change: this.onUserStateComboboxChange
            },
            "button[id=NormalSaveBtn]": {
                click: this.onNormalSaveBtnClick
            },
            "button[id=NormalCloseBtn]": {
                click: this.onNormalCloseBtnClick
            },
            'button[id=exportBtn]':{
            	click:this.onExportBtnClick
            },
            'button[id=closeShowWindowBtn]':{
            	click:this.onCloseShowWindowBtnClick
            },
            'button[id=uploadSaveBtn]':{
            	click:this.onUploadSaveBtnClick
            },
            'button[id=uploadCloseBtn]':{
            	click:this.onUploadCloseBtnClick
            }
            
            
        });
    }

});
