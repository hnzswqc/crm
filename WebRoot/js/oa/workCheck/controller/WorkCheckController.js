/*
 * File: workCheck/controller/WorkCheckController.js
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

Ext.define('WorkCheck.controller.WorkCheckController', {
    extend: 'Ext.app.Controller',

    //刷新按钮
    onWorkCheckRefreshBtnClick: function(button, e, eOpts) {
		this.reloadWorkCheckGridPanelStore(null,'考勤信息');
    },
    //添加按钮
    onWorkCheckAddBtnClick: function(button, e, eOpts) {
    	Ext.create("WorkCheck.view.sigleWindow",{title:'添加月度考核',icon:'img/add.png'}).show();
    },
    //修改按钮
    onWorkCheckUpdateBtnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("WorkCheckGridPanel");
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
        Ext.create("WorkCheck.view.sigleWindow",{title:'修改辞职员工信息',icon:'img/update.png'}).show();
	    Ext.getCmp("WorkCheckFormPanel").loadRecord(selectionModel.getSelection()[0]);
	    Ext.getCmp("userNumber").setReadOnly(true);
	    Ext.getCmp("userNumber").setFieldStyle("background:#f2f2f2");
	    
	    Ext.getCmp("wcYear").setReadOnly(true);
	    Ext.getCmp("wcYear").setFieldStyle("background:#f2f2f2");
	    
	    Ext.getCmp("wcMonth").setReadOnly(true);
	    Ext.getCmp("wcMonth").setFieldStyle("background:#f2f2f2");
	    
    },
    //删除按钮
    onWorkCheckDelBtnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("WorkCheckGridPanel");
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
            		url:"wc/delWorkCheck.do",
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
            				Ext.getCmp("WorkCheckGridPanel").store.load();
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
    //导出按钮
    onWorkCheckExcelBtnClick: function(button, e, eOpts) {
    	
    	var panel = Ext.getCmp("searchFormPanel");
        if(panel.isHidden()){
        	//window.location.href=extBasePath+"wc/getDownWorkCheckList.do";
        	Ext.Msg.show({
				title : '提示信息',
				msg : "请选择导出的年份和月度",
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING
			 });
        }else{
        	if(null==Ext.getCmp("searchWcMonth").value){
        		Ext.Msg.show({
    				title : '提示信息',
    				msg : "请选择导出的年份和月度",
    				buttons : Ext.Msg.OK,
    				icon : Ext.MessageBox.WARNING
    			 });
        	}else{
        		Ext.Msg.wait('正在处理数据，请稍候...','提示');
            	var param = panel.getValues(true);
            	window.location.href=extBasePath+"wc/getDownWorkCheckList.do?"+param;  
            	Ext.Msg.hide();
        	}
        }
    },
    //高级查询按钮
    onWorkCheckShowSearchBtnClick: function(button, e, eOpts) {
    	 var panel = Ext.getCmp("searchFormPanel");
         if(panel.isHidden()){
             panel.show();
            /* var wcMonth=Ext.getCmp("searchWcMonth");
             if(wcMonth.value==null||wcMonth.value==""){
            	var m = new Date().getMonth();
 	 			if(m==0){
 	 				m=12;
 	 			}
 	 			wcMonth.setValue(m);
             }*/
         }else{
             panel.hide();
         }
    },
    //刷新数据
    reloadWorkCheckGridPanelStore:function(data,text){
    	var gridPanel=Ext.getCmp("WorkCheckGridPanel");
    	gridPanel.store.setProxy({
    		type: 'ajax',
            url: 'wc/getWorkCheckPage.do',
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
    //查询按钮
    onWorkCheckSearchBtnClick: function(button, e, eOpts) {
    	var SearchForm = Ext.getCmp("searchFormPanel");
    	this.reloadWorkCheckGridPanelStore(SearchForm.getValues(),'高级查询-考勤信息');
    },
    //重置按钮
    onWorkCheckResetBtnClick: function(button, e, eOpts) {
    	var SearchForm = Ext.getCmp("searchFormPanel");
    	SearchForm.getForm().reset();
    	this.reloadWorkCheckGridPanelStore(null,'高级查询-考勤信息');
    },
    //单个添加保存考勤信息
    onWorkCheckSaveBtnClick: function(button, e, eOpts) {
    	var formPanel =  Ext.getCmp("WorkCheckFormPanel");
    	if(formPanel.isValid()){
	    	var userName=Ext.getCmp("userName").value;
	    	var wcYear=Ext.getCmp("wcYear").value;
	    	var wcMonth=Ext.getCmp("wcMonth").value;
	    	Ext.Msg.wait('正在处理数据，请稍候...','提示');
	        Ext.Ajax.request({
	    		url:"wc/addValidate.do",
	    		method:"POST",
	    		params:formPanel.getValues(),
	    		success:function(responst){
	        	 	Ext.Msg.hide();
	    			var result = JSON.parse(responst.responseText); 
	    			if(result.success){
	    				Ext.Msg.show({title : '提示信息',msg :'已经存在'+userName+wcYear+'年'+wcMonth+'月份考勤信息！',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
	    			}else{
	    				this.saveWorkCheck();
	    			}
	    		},
			    failure: function(from,action){
	    			 Ext.Msg.hide();
	    			 onActionFailureTypeShow(action);
	    		},
	    		scope:this
	    	});
    	}
    },
    //提交表单
    saveWorkCheck:function(){
    	var formPanel =  Ext.getCmp("WorkCheckFormPanel");
	   	 if(!formPanel.isDirty()){
	   		 Ext.getCmp("sigleWindow").close();
	    		 return;
	   	 }
	   	 
	   	 if(formPanel.isValid()){
	   		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
	    			if(btnId=='yes'){
	    				 formPanel.submit({
	        				 url:'wc/addWorkCheck.do',
	    		             method:'POST',
	    		             waitTitle : "提示",
	    		             waitMsg: '正在提交数据，请稍后...',
	    		             success: function(form, action){
	    		        	    var result = JSON.parse(action.response.responseText); 
	    		            	if(result.success){
	    		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
	    		            		 Ext.getCmp("WorkCheckGridPanel").store.load();
	    		            		 
	    		            		 
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
    //重置表单
    resetFormPanel:function(){
    	Ext.getCmp("userNumber").setValue("");//员工工号
		Ext.getCmp("userUuid").setValue("");//用户uuid
		Ext.getCmp("userName").setValue("");//用户名称
		Ext.getCmp("orgUuid").setValue("");//所属部门
		Ext.getCmp("orgName").setValue("");//所属部门名称
		Ext.getCmp("roleName").setValue("");//职务
		Ext.getCmp("joinTime").setValue("");//入职时间
		Ext.getCmp("wcNote").setValue("");//入职时间
		Ext.getCmp("wcCheckDay").setValue("");//员工工号
		Ext.getCmp("wcAddDay").setValue("");//员工工号
		
		
    },
    //单个添加关闭考勤信息
    onWorkCheckCloseBtnClick: function(button, e, eOpts) {
    	Ext.getCmp("sigleWindow").close();
    },
    //月份切换事件
    onWcMonthComboboxChange: function(field, newValue, oldValue, eOpts) {
    	var y=parseInt(Ext.getCmp("wcYear").value);
		if(newValue==1){
			Ext.getCmp("wcStartDate").setValue((y-1)+"-12-26");
			Ext.getCmp("wcEndDate").setValue(y+"-01-25");
		}else{
			if(newValue-1<10){
				Ext.getCmp("wcStartDate").setValue(y+"-0"+(newValue-1)+"-26");
				if(newValue==10){
					Ext.getCmp("wcEndDate").setValue(y+"-"+newValue+"-25");
				}else{
					Ext.getCmp("wcEndDate").setValue(y+"-0"+newValue+"-25");
				}
			}else{
				Ext.getCmp("wcStartDate").setValue(y+"-"+(newValue-1)+"-26");
				Ext.getCmp("wcEndDate").setValue(y+"-"+newValue+"-25");
			}
			
		}
    },
    //员工编号失去焦点事件，判断是否存在该编号人员员工
    onUserNumberBlur: function(component, e, eOpts) {
    	if(component.readOnly){
    		return;
    	}
    	if(null==component.value||""==component.value||component.value==component.originalValue){
    		return;
    	}
    	Ext.Msg.wait('正在查找人员信息，请稍候...','提示');
    	Ext.Ajax.request({
    		url:"user/validateUserNumber.do",
     		method:"POST",
     		params:{
        	 	'userNumber':component.value
         	},
     		success:function(responst){
         		Ext.Msg.hide();
     			var result = JSON.parse(responst.responseText); 
     			if(result.success){
     				if(result.data.userState==2){
     					var msg='该员工('+result.data.userName+')已离职';
     					Ext.Msg.show({
            				title : '提示信息',
            				msg : msg,
            				buttons : Ext.Msg.OK,
            				icon : Ext.MessageBox.WARNING
            			 });
     					this.resetFormPanel();
     				}else{
     					Ext.getCmp("userUuid").setValue(result.data.userUuid);//用户uuid
     					Ext.getCmp("userName").setValue(result.data.userName);//用户名称
     					Ext.getCmp("orgUuid").setValue(result.data.orgUuid);//所属部门
     					Ext.getCmp("orgName").setValue(result.data.orgName);//所属部门名称
     					Ext.getCmp("roleName").setValue(result.data.roleName);//职务
     					Ext.getCmp("joinTime").setValue(result.data.joinTime);//入职时间
     					component.originalValue = component.value;
     				}
     			}else{
     				var msg='没有找到该员工，请重新选择';
     				Ext.Msg.show({
        				title : '提示信息',
        				msg : msg,
        				buttons : Ext.Msg.OK,
        				icon : Ext.MessageBox.WARNING
        			 });
     				this.resetFormPanel();
     			}
     		},
 		    failure: function(from,action){
     			 Ext.Msg.hide();
     			this.resetFormPanel();
     			 onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    },
    //定时器,解决单击事件双击事件问题
    task:new Ext.util.DelayedTask(),
    //用户双击事件
    onWorkCheckGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
 	    Ext.create("WorkCheck.view.sigleWindow",{title:'修改辞职员工信息',icon:'img/update.png'}).show();
	    Ext.getCmp("WorkCheckFormPanel").loadRecord(record);
	    Ext.getCmp("userNumber").setReadOnly(true);
	    Ext.getCmp("userNumber").setFieldStyle("background:#f2f2f2");
	    
	    Ext.getCmp("wcYear").setReadOnly(true);
	    Ext.getCmp("wcYear").setFieldStyle("background:#f2f2f2");
	    
	    Ext.getCmp("wcMonth").setReadOnly(true);
	    Ext.getCmp("wcMonth").setFieldStyle("background:#f2f2f2");
    },
    //用户单击事件
    onWorkCheckGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("WorkCheckUpdateBtn").enable(true);
    	Ext.getCmp("WorkCheckDelBtn").enable(true);
    },
    //组织机构双击事件
    onOrgGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	//组织机构双击事件
    },
    //组织机构单击事件
    onOrgGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("searchOrgUuid").setValue(record.data.id);
    	this.reloadWorkCheckGridPanelStore({'orgUuid':record.data.id},'高级查询-考勤信息');
    },
    //月份查询
    onSearchWcMonthComboboxChange:function(field, newValue, oldValue, eOpts) {
    	var SearchForm = Ext.getCmp("searchFormPanel");
    	this.reloadWorkCheckGridPanelStore(SearchForm.getValues(),'高级查询-考勤信息');
    },
    //批量添加考勤信息
    onBatchAddBtnClick:function(button, e, eOpts){
    	Ext.create("WorkCheck.view.BeatchAddWindow",{title:'批量添加月度考核',icon:'img/add.png'}).show();
    },
    //关闭批量添加窗口
    onCloseWindowBtnClick:function(button, e, eOpts){
    	Ext.getCmp("BeatchAddWindow").close();
    	Ext.getCmp("BeatchAddWindow").destroy()
    },
    //新增一条记录
    onAddRowBtnClick:function(button, e, eOpts){
    	var i= DEFAULT_ROW_NUM;
    	var item =[
    	           {xtype: 'hiddenfield',fieldLabel: '员工uuid',name:'list['+i+'].userUuid',id:'userUuid_'+i},
    	           {xtype: 'textfield', margin: 2, name:'list['+i+'].userNumber',id:"userNumber_"+i, hideLabel: true },
    	           {xtype: 'textfield', margin: 2, name:'list['+i+'].userName',id:"userName_"+i, readOnly:true,fieldStyle:'background:#f2f2f2',hideLabel: true },
                   {xtype: 'textfield', margin: 2, name:'list['+i+'].orgName',id:"orgName_"+i, readOnly:true,fieldStyle:'background:#f2f2f2',hideLabel: true},
                   {xtype: 'hiddenfield',name:'list['+i+'].orgUuid',id:'orgUuid_'+i},
                   {xtype: 'hiddenfield',name:'list['+i+'].joinTime',id:'joinTime_'+i},
                   {xtype: 'textfield', margin: 2, name:'list['+i+'].roleName',id:"roleName_"+i,readOnly:true,fieldStyle:'background:#f2f2f2', hideLabel: true},
                   {xtype: 'textfield', margin: 2, name:'list['+i+'].wcCheckDay',id:"wcCheckDay_"+i, hideLabel: true},
                   {xtype: 'textfield', margin: 2, name:'list['+i+'].wcAddDay',id:"wcAddDay_"+i, hideLabel: true},
                   {xtype: 'textfield', margin: 2, name:'list['+i+'].wcAddHour',id:"wcAddHour_"+i, hideLabel: true},
                   {xtype: 'button', margin:'2 2 2 12', id:"DelRowBtn_"+i,icon:"img/delete.png"}
                  ];
    	Ext.getCmp('BeatchAddForm').add(item);
    	DEFAULT_ROW_NUM++;
		Ext.getCmp("userNumber_"+i).focus();
    },
    //初始化人员信息
    initUserCount(list){
    	var i= DEFAULT_ROW_NUM;
    	Ext.each(list,function(n){
    		if(n.userNumber!="000"){
	    		var item =[
	        	           {xtype: 'hiddenfield',fieldLabel: '员工uuid',name:'list['+i+'].userUuid',id:'userUuid_'+i,value:n.userUuid},
	        	           {xtype: 'textfield', margin: 2, name:'list['+i+'].userNumber',id:"userNumber_"+i, hideLabel: true,value:n.userNumber,readOnly:true,fieldStyle:'background:#f2f2f2' },
	        	           {xtype: 'textfield', margin: 2, name:'list['+i+'].userName',id:"userName_"+i, readOnly:true,fieldStyle:'background:#f2f2f2',hideLabel: true,value:n.userName },
	                       {xtype: 'textfield', margin: 2, name:'list['+i+'].orgName',id:"orgName_"+i, readOnly:true,fieldStyle:'background:#f2f2f2',hideLabel: true,value:n.orgName},
	                       {xtype: 'hiddenfield',name:'list['+i+'].orgUuid',id:'orgUuid_'+i,value:n.orgUuid},
	                       {xtype: 'hiddenfield',name:'list['+i+'].joinTime',id:'joinTime_'+i,value:n.joinTime},
	                       {xtype: 'textfield', margin: 2, name:'list['+i+'].roleName',id:"roleName_"+i,readOnly:true,fieldStyle:'background:#f2f2f2', hideLabel: true,value:n.roleName},
	                       {xtype: 'textfield', margin: 2, name:'list['+i+'].wcCheckDay',id:"wcCheckDay_"+i, hideLabel: true},
	                       {xtype: 'textfield', margin: 2, name:'list['+i+'].wcAddDay',id:"wcAddDay_"+i, hideLabel: true},
	                       {xtype: 'textfield', margin: 2, name:'list['+i+'].wcAddHour',id:"wcAddHour_"+i, hideLabel: true},
	                       {xtype: 'button', margin:'2 2 2 12', id:"DelRowBtn_"+i,icon:"img/delete.png"}
	                      ];
	        	Ext.getCmp('BeatchAddForm').add(item);
	        	DEFAULT_ROW_NUM++;
	        	i++;
    		}
    	});
    	
    	
    },
    //批量保存数据
    onSaveBtnClick:function(button, e, eOpts){
    	var formPanel = Ext.getCmp("BeatchAddForm");
	   	if(!formPanel.isDirty()){
	   		 alert(formPanel.form.getValues());
	   	}
	   	 //Ext.getCmp("wcYear_u").setValue(Ext.getCmp("wcYear").value);
	   	 //Ext.getCmp("wcMonth_u").setValue(Ext.getCmp("wcMonth").value);
	   	 //Ext.getCmp("wcStartDate_u").setValue(Ext.getCmp("wcStartDate").value);
	   	 //Ext.getCmp("wcEndDate_u").setValue(Ext.getCmp("wcEndDate").value);
	   	 //Ext.getCmp("wcAllDay_u").setValue(Ext.getCmp("wcAllDay").value);
	   	 var wcYear = Ext.getCmp("wcYear").value;
	   	 var wcMonth = Ext.getCmp("wcMonth").value;
	   	 var wcStartDate =  Ext.util.Format.date(Ext.getCmp("wcStartDate").value, 'Y-m-d');
	   	 var wcEndDate =Ext.util.Format.date(Ext.getCmp("wcEndDate").value, 'Y-m-d');
	   	 var wcAllDay = Ext.getCmp("wcAllDay").value;
	   	 var params = "wcYear="+wcYear+"&wcMonth="+wcMonth+"&wcStartDate="+wcStartDate+"&wcEndDate="+wcEndDate+"&wcAllDay="+wcAllDay;
	   	if(formPanel.isValid()){
   		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
    			if(btnId=='yes'){
    				 formPanel.submit({
        				 url:'wc/beatchAddWorkCheck.do?'+params,
    		             method:'POST',
    		             waitTitle : "提示",
    		             waitMsg: '正在提交数据，请稍后...',
    		             success: function(form, action){
    		        	    var result = JSON.parse(action.response.responseText); 
    		            	if(result.success){
    		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
    		            		 this.reloadWorkCheckGridPanelStore(null,'考勤信息');
    		            		 Ext.getCmp("BeatchAddWindow").close();
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
    //删除添加的行
    onDelRowBtnClick:function(button, e, eOpts){
    	 var i = button.id.substring(button.id.indexOf("_")+1,button.id.length);
  		// Ext.MessageBox.confirm("提示信息", "确定要删除吗？", function (btnId) {
   			//if(btnId=='yes'){
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('userUuid_'+i));
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('userNumber_'+i));
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('userName_'+i));
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('orgName_'+i));
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('orgUuid_'+i));
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('joinTime_'+i));
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('roleName_'+i));
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('wcCheckDay_'+i));
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('wcAddDay_'+i));
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('wcAddHour_'+i));
   				Ext.getCmp('BeatchAddForm').remove(Ext.getCmp('DelRowBtn_'+i));
   				//DEFAULT_ROW_NUM--;
   			//}
   		// },this);
    },
    //月份切换事件
    onWcMonthComboboxChange: function(field, newValue, oldValue, eOpts) {
    	
    	var year = Ext.getCmp("wcYear").value;
    	Ext.Ajax.request({
    		url:"kqsz/getKqszPage.do",
     		method:"POST",
     		params: {'year': year,'month':newValue},
     		success:function(responst){
     			var result = JSON.parse(responst.responseText); 
     			if(result.success){
     				Ext.getCmp("wcStartDate").setValue(result.data[0].kqStartTime);
     				Ext.getCmp("wcEndDate").setValue(result.data[0].kqEndTime);
     				Ext.getCmp("wcAllDay").setValue(result.data[0].mqts);
     				
     			}
     		},
 		    failure: function(from,action){
     			onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    	
    },
    //窗口添加前加载数量
    onAutoAddBtnClick:function(button, e, eOpts){
    	var myMask = new Ext.LoadMask(Ext.getCmp("BeatchAddWindow"),{msg:"正在加载人员信息，请稍后..."}); 
    	myMask.show(); 
    	Ext.Ajax.request({
    		url:"user/getUserCount.do",
     		method:"POST",
     		success:function(responst){
     			var result = JSON.parse(responst.responseText); 
     			if(result.success){
     				this.initUserCount(result.data);
     			}else{
     				this.onAddRowBtnClick();
     			}
     			if (myMask != undefined){ 
     				myMask.hide();
     				Ext.Msg.alert("提示信息","用户加载成功!");
     			 }
     		},
 		    failure: function(from,action){
     			msgTip.hide();
     			 onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    	
    	
    },
    //回车考勤天数进入加班时间字段。
    onWcCheckDaySpecialkey:function(field,e){
    	 var i = field.id.substring(field.id.indexOf("_")+1,field.id.length);
    	 if (e.getKey() == e.ENTER) {
    		 Ext.getCmp("wcAddDay_"+i).focus();
         }
    },
  //回车考勤天数进入加班时间字段。
    onWcAddDaySpecialkey:function(field,e){
    	 var i = field.id.substring(field.id.indexOf("_")+1,field.id.length);
    	 if (e.getKey() == e.ENTER) {
    		 Ext.getCmp("wcAddHour_"+i).focus();
         }
    },
    //回车加班时间进入下一个人的考勤天数
    onWcAddHourSpecialkey:function(field,e){
    	var i = field.id.substring(field.id.indexOf("_")+1,field.id.length);
    	i++;
    	if(undefined!=Ext.getCmp("userNumber_"+i)&&!Ext.getCmp("userNumber_"+i).readOnly){
	    	if (e.getKey() == e.ENTER) {
	   		 	Ext.getCmp("userNumber_"+i).focus();
	        }
    	}else{
    		if(undefined!=Ext.getCmp("wcCheckDay_"+i)){
	    		if (e.getKey() == e.ENTER) {
	       		 	Ext.getCmp("wcCheckDay_"+i).focus();
	            }
    		}else{
    			
    			this.onAddRowBtnClick();
    			
    		}
    	}
    },
   //用户usernumber回车验证消息。
    onUserNumberSpecialkey:function(field,e){
    	var i = field.id.substring(field.id.indexOf("_")+1,field.id.length);
    	if (e.getKey() == e.ENTER) {
   		 	this.getUserMessage(i);
   		 	Ext.getCmp("wcCheckDay_"+i).focus();
        }
    },
    //获取用户信息
    getUserMessage:function(i){
    	var component =Ext.getCmp("userNumber_"+i);
    	if(component.readOnly){
    		return;
    	}
    	if(null==component.value||""==component.value||component.value==component.originalValue){
    		return;
    	}
    	//Ext.Msg.wait('正在查找人员信息，请稍候...','提示');
    	Ext.Ajax.request({
    		url:"user/validateUserNumber.do",
     		method:"POST",
     		params:{
        	 	'userNumber':component.value
         	},
     		success:function(responst){
         		//Ext.Msg.hide();
     			var result = JSON.parse(responst.responseText); 
     			if(result.success){
     				if(result.data.userState==2){
     					var msg='该员工('+result.data.userName+')已离职';
     					Ext.Msg.show({
            				title : '提示信息',
            				msg : msg,
            				buttons : Ext.Msg.OK,
            				icon : Ext.MessageBox.WARNING
            			 });
     					Ext.getCmp("userNumber_"+i).focus();
     				}else{
     					Ext.getCmp("userUuid_"+i).setValue(result.data.userUuid);//用户uuid
     					Ext.getCmp("userName_"+i).setValue(result.data.userName);//用户名称
     					Ext.getCmp("orgUuid_"+i).setValue(result.data.orgUuid);//所属部门
     					Ext.getCmp("orgName_"+i).setValue(result.data.orgName);//所属部门名称
     					Ext.getCmp("roleName_"+i).setValue(result.data.roleName);//职务
     					Ext.getCmp("joinTime_"+i).setValue(result.data.joinTime);//入职时间
     					component.originalValue = component.value;
     				}
     			}else{
     				var msg='没有找到该员工，请重新选择';
     				Ext.Msg.show({
        				title : '提示信息',
        				msg : msg,
        				buttons : Ext.Msg.OK,
        				icon : Ext.MessageBox.WARNING
        			 });
     				component.originalValue = component.value;
     				Ext.getCmp("userNumber_"+i).focus();
     			}
     		},
 		    failure: function(from,action){
     			// Ext.Msg.hide();
     			 onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    },
    init: function(application) {
        this.control({
            "button[id=WorkCheckRefreshBtn]": {
                click: this.onWorkCheckRefreshBtnClick
            },
            "button[id=WorkCheckAddBtn]": {
                click: this.onWorkCheckAddBtnClick
            },
            "button[id=BatchAddBtn]": {
                click: this.onBatchAddBtnClick
            },
            "button[id=WorkCheckUpdateBtn]": {
                click: this.onWorkCheckUpdateBtnClick
            },
            "button[id=WorkCheckDelBtn]": {
                click: this.onWorkCheckDelBtnClick
            },
            "button[id=WorkCheckExcelBtn]": {
                click: this.onWorkCheckExcelBtnClick
            },
            "button[id=WorkCheckShowSearchBtn]": {
                click: this.onWorkCheckShowSearchBtnClick
            },
            "button[id=WorkCheckSearchBtn]": {
                click: this.onWorkCheckSearchBtnClick
            },
            "button[id=WorkCheckResetBtn]": {
                click: this.onWorkCheckResetBtnClick
            },
            "button[id=WorkCheckSaveBtn]": {
                click: this.onWorkCheckSaveBtnClick
            },
            "button[id=WorkCheckCloseBtn]": {
                click: this.onWorkCheckCloseBtnClick
            },
            "combobox[id=wcMonth]": {
                change: this.onWcMonthComboboxChange
            },
            'textfield[id=userNumber]':{
           	 	blur: this.onUserNumberBlur
            },
            "#WorkCheckGridPanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    				this.task.delay(200,this.onWorkCheckGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onWorkCheckGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "#OrgTreePanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    				this.task.delay(200,this.onOrgGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onOrgGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "combobox[id=searchWcMonth]": {
                change: this.onSearchWcMonthComboboxChange
            },
            "button[id=CloseWindowBtn]":{
            	click: this.onCloseWindowBtnClick
            },
            "button[id=AddRowBtn]":{
            	click: this.onAddRowBtnClick
            },
            "button[id=SaveBtn]":{
            	click: this.onSaveBtnClick
            },
            "button[id^=DelRowBtn]":{
            	click: this.onDelRowBtnClick
            },
            "button[id=AutoAddBtn]":{
            	click: this.onAutoAddBtnClick
            },
            "textfield[id^=wcCheckDay_]":{
            	specialkey:this.onWcCheckDaySpecialkey
            },
            "textfield[id^=wcAddDay_]":{
            	specialkey:this.onWcAddDaySpecialkey
            },
            "textfield[id^=wcAddHour_]":{
            	specialkey:this.onWcAddHourSpecialkey
            },
            "textfield[id^=userNumber_]":{
            	specialkey:this.onUserNumberSpecialkey
            },
            'textfield[id^=userNumber11b _]':{
           	 	blur: this.onUserNumber_Blur1
            }
            
        });
    }

});