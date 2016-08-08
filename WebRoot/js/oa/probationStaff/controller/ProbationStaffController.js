/*
 * File: probationStaff/controller/ProbationStaffController.js
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

Ext.define('ProbationStaff.controller.ProbationStaffController', {
    extend: 'Ext.app.Controller',

    models: [
        'ProbationStaffModel',
        'OrgTreeNode'
    ],
    stores: [
        'ProbationStaffStore',
        'OrgTreeStore',
        'OrgRoleStore'
    ],
    views: [
        'MyViewport',
        'ProbationStaffWindow'
    ],
    //刷新按钮
    onProbationStaffRefreshBtnClick: function(button, e, eOpts) {
		this.reloadOrgGridPanelStore({'orgUuid':null},"试用期人员管理");
    },
    //添加按钮
    onProbationStaffAddBtnClick: function(button, e, eOpts) {
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
    	
        Ext.create("ProbationStaff.view.ProbationStaffWindow",{title:'添加试用期员工',icon:'img/add.png'}).show();
        Ext.getCmp("orgUuid").setValue(data.id);
        //加载所属角色
        this.reloadOrgRoleStore(data.id);
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
    //定时器,解决单击事件双击事件问题
    task:new Ext.util.DelayedTask(),
    //组织机构双击事件
    onOrgGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	//组织机构双击事件
    },
    //组织机构单击事件
    onOrgGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("probationStaffAddBtn").enable(true);
    	this.reloadOrgGridPanelStore({'orgUuid':record.data.id},record.data.text+"-试用期人员管理");
    },
    //刷新数据
    reloadOrgGridPanelStore:function(data,text){
    	var gridPanel=Ext.getCmp("ProbationStaffGridPanel");
    	gridPanel.store.setProxy({
	 		url:'pbf/getProbationStaffPage.do',
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
    //保存按钮
    onProbationStaffSaveBtnClick:function(button, e, eOpts){
	   	 var formPanel =  Ext.getCmp("ProbationStaffFormGridPanel");
		 if(!formPanel.isDirty()){
			 Ext.getCmp("ProbationStaffWindow").close();
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
	 		            		 Ext.getCmp("ProbationStaffWindow").close();
	 		            		 Ext.getCmp("ProbationStaffGridPanel").store.load();
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
    onProbationStaffCloseBtnClick:function(button, e, eOpts){
    	Ext.getCmp("ProbationStaffWindow").close();
    },
    //修改按钮
    onProbationStaffUpdateBtnClick:function(button, e, eOpts){
    	var gridPanel = Ext.getCmp("ProbationStaffGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
         var data = selectionModel.getSelection()[0].data;
    	 Ext.create("ProbationStaff.view.ProbationStaffWindow",{title:'修改试用期员工',icon:'img/update.png'}).show();
    	 //加载所属角色
    	 this.reloadOrgRoleStore(data.orgUuid);
    	 Ext.getCmp("orgUuid").setValue(data.orgUuid);
    	 Ext.getCmp("ProbationStaffFormGridPanel").getForm().loadRecord(selectionModel.getSelection()[0]);
    },
    //用户双击事件
    onProbationStaffGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	 Ext.create("ProbationStaff.view.ProbationStaffWindow",{title:'修改试用期员工',icon:'img/update.png'}).show();
    	 Ext.getCmp("orgUuid").setValue(record.data.orgUuid);
    	 //加载所属角色
    	 Ext.getCmp("ProbationStaffFormGridPanel").getForm().loadRecord(record);
    	 this.reloadOrgRoleStore(record.data.orgUuid);
    	 
         
    },
    //用户单击事件
    onProbationStaffGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("probationStaffUpdateBtn").enable(true);
    	Ext.getCmp("probationStaffDelBtn").enable(true);
    	Ext.getCmp("probationStaffTurnRightBtn").enable(true);
    },
    //删除
    onProbationStaffDelBtnClick:function(button, e, eOpts){
    	var gridPanel = Ext.getCmp("ProbationStaffGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条信息',
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
            				 Ext.getCmp("ProbationStaffGridPanel").store.load();
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
    //导出excel
    onProbationStaffExcelBtnClick:function(button, e, eOpts){
    	 window.location.href=extBasePath+"pbf/getDownProbationStaffList.do?time="+new Date().getTime();
    },
    //试用期员工转正
    onProbationStaffTurnRightBtnClick:function(button, e, eOpts){
    	var gridPanel = Ext.getCmp("ProbationStaffGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
         var data = selectionModel.getSelection()[0].data;
         Ext.create("ProbationStaff.view.NormalUserWindow",{title:"试用期员工转正",icon:'img/add.png'}).show();
    	 //加载所属角色
    	 this.reloadOrgRoleStore(data.orgUuid);
    	 Ext.getCmp("orgUuid").setValue(data.orgUuid);
    	 Ext.getCmp("NormalUserFormPanel").getForm().loadRecord(selectionModel.getSelection()[0]);
    	 this.getUserNumber(data.orgUuid);//自动获取员工编号
    },
    //自定预设员工编号
    getUserNumber:function(orgUuid){
    	Ext.Msg.wait('正在自动检测员工编号，请稍候...','提示');
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
     		            		 Ext.getCmp("ProbationStaffGridPanel").store.load();
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
    //用户登录名验证
    onUser_window_userIdTextfieldBlur: function(component, e, eOpts) {
    	 var formPanel =  Ext.getCmp("NormalUserFormPanel");
    	 if(!formPanel.isDirty()){
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
    init: function(application) {
        this.control({
        	"button[id=probationStaffRefreshBtn]":{
        		click: this.onProbationStaffRefreshBtnClick
        	},
            "button[id=probationStaffAddBtn]": {
                click: this.onProbationStaffAddBtnClick
            },
            "#OrgTreePanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    				this.task.delay(200,this.onOrgGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onOrgGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "#ProbationStaffGridPanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    				this.task.delay(200,this.onProbationStaffGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onProbationStaffGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "button[id=probationStaffSaveBtn]": {
                click: this.onProbationStaffSaveBtnClick
            },
            "button[id=probationStaffCloseBtn]": {
                click: this.onProbationStaffCloseBtnClick
            },
            "button[id=probationStaffUpdateBtn]": {
                click: this.onProbationStaffUpdateBtnClick
            },
            "button[id=probationStaffDelBtn]": {
                click: this.onProbationStaffDelBtnClick
            },
            "button[id=probationStaffExcelBtn]": {
                click: this.onProbationStaffExcelBtnClick
            },
            "button[id=probationStaffTurnRightBtn]": {
                click: this.onProbationStaffTurnRightBtnClick
            },
            "button[id=NormalSaveBtn]": {
                click: this.onNormalSaveBtnClick
            },
            "button[id=NormalCloseBtn]": {
                click: this.onNormalCloseBtnClick
            },
            "textfield[id=User_window_userId]": {
                blur: this.onUser_window_userIdTextfieldBlur
            },
            "textfield[id=User_window_userNumber]": {
                blur: this.onUser_window_userNumberTextfieldBlur
            },
            "textfield[id=idCard]": {
                blur: this.onIdCardTextfieldBlur
            }
            
        });
    }

});
