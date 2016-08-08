/*
 * File: resigned/controller/ResignedController.js
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

Ext.define('Resigned.controller.ResignedController', {
    extend: 'Ext.app.Controller',

    //刷新按钮
    onResignedRefreshBtnClick: function(button, e, eOpts) {
		Ext.getCmp("ResignedGridPanel").store.load();
    },
    //修改按钮
    onResignedUpdBtnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("ResignedGridPanel");
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
   	    Ext.create("Resigned.view.ResignedWindow",{title:'修改辞职员工信息',icon:'img/update.png'}).show();
	    Ext.getCmp("ResignedFormPanel").loadRecord(selectionModel.getSelection()[0]);
	    this.setResignedMonth(selectionModel.getSelection()[0].data.userUuid);
    },
    //删除按钮
    onResignedDelBtnClick: function(button, e, eOpts) {
		var gridPanel = Ext.getCmp("ResignedGridPanel");
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
            		url:"resigned/delResigned.do",
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
            				Ext.getCmp("ResignedGridPanel").store.load();
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
    
    //恢复按钮
    onResignedBackBtnClick: function(button, e, eOpts) {
		var gridPanel = Ext.getCmp("ResignedGridPanel");
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
        Ext.MessageBox.confirm("提示信息", "确定要恢复未结算状态吗？", function (btnId) {
            if (btnId == "yes") {
            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
                Ext.Ajax.request({
            		url:"resigned/backResigned.do",
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
            				Ext.getCmp("ResignedGridPanel").store.load();
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
    //工资完结
    onResignedStateBtnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("ResignedGridPanel");
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
        if(data.resState == DIC_RESIGNED_STATE_YES){
        	Ext.Msg.show({
 				title : '提示信息',
 				msg :data.userName+'工资已经完结。',
 				buttons : Ext.Msg.OK,
 				icon : Ext.MessageBox.WARNING
 			});
        return;
        }
        Ext.MessageBox.confirm("提示信息", "确定工资已经完结吗？", function (btnId) {
            if (btnId == "yes") {
            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
                Ext.Ajax.request({
            		url:"resigned/updResigned.do",
            		method:"POST",
            		params:data,
            		success:function(responst){
                	 	Ext.Msg.hide();
            			var result = JSON.parse(responst.responseText); 
            			if(result.success){
            				 Ext.Msg.show({
                  				title : '提示信息',
                  				msg :'结算成功!',
                  				buttons : Ext.Msg.OK,
                  				icon : Ext.MessageBox.INFO
                  			 });     
            				Ext.getCmp("ResignedGridPanel").store.load();
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
    //高级查询按钮
    onResignedShowBtnClick: function(button, e, eOpts) {
    	 var panel = Ext.getCmp("ResignedSearchFromPanel");
         if(panel.isHidden()){
             panel.show();
         }else{
             panel.hide();
         }
    },
    //定时器,解决单击事件双击事件问题
    task:new Ext.util.DelayedTask(),
    //用户双击事件
    onResignedGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	 Ext.create("Resigned.view.ResignedWindow",{title:'修改辞职员工信息',icon:'img/update.png'}).show();
 	     Ext.getCmp("ResignedFormPanel").loadRecord(record);
 	     this.setResignedMonth(record.data.userUuid);
    },
    //用户单击事件
    onResignedGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("ResignedUpdBtn").enable(true);
    	Ext.getCmp("ResignedDelBtn").enable(true);
    	if(record.data.count>0){
    		Ext.getCmp("delNumberBtn").enable(true);
    	}else{
    		Ext.getCmp("delNumberBtn").setDisabled(true);
    	}
    	Ext.getCmp("showBtn").enable(true);
    	if (e.getTarget().innerHTML === '查看'||e.getTarget().innerHTML === '下载' ){
    		this.showFileHref(record.data.fileType,record.data.resUuid);
    	}
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
     				 formPanel.submit({
         				 url:'resigned/addResigned.do',
     		             method:'POST',
     		             waitTitle : "提示",
     		             waitMsg: '正在提交数据，请稍后...',
     		             success: function(form, action){
     		        	    var result = JSON.parse(action.response.responseText); 
     		            	if(result.success){
     		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
     		            		 Ext.getCmp("ResignedWindow").close();
     		            		 Ext.getCmp("ResignedGridPanel").store.load();
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
    //查询按钮
    onResignedSearchBtnClick: function(button, e, eOpts) {
    	var SearchForm = Ext.getCmp("ResignedSearchFromPanel");
    	this.reloadResignedGridPanelStore(SearchForm.getValues(),'高级查询-辞职员工信息');
    },
    //重置按钮
    onResignedResetBtnClick: function(button, e, eOpts) {
    	var SearchForm = Ext.getCmp("ResignedSearchFromPanel");
    	SearchForm.getForm().reset();
    	this.reloadResignedGridPanelStore(null,'辞职员工信息');
    },
    //刷新数据
    reloadResignedGridPanelStore:function(data,text){
    	var gridPanel=Ext.getCmp("ResignedGridPanel");
    	gridPanel.store.setProxy({
    		type: 'ajax',
            url: 'resigned/getResignedPage.do?resState='+DIC_RESIGNED_STATE_YES,
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
    //是否存在工资卡
    onUserBankCardStateComboboxChange:function(field, newValue, oldValue, eOpts) {
    	var SearchForm = Ext.getCmp("ResignedSearchFromPanel");
    	this.reloadResignedGridPanelStore(SearchForm.getValues(),'高级查询-辞职员工信息');
    },
    //月份查询
    onResStateComboboxChange:function(field, newValue, oldValue, eOpts) {
    	var SearchForm = Ext.getCmp("ResignedSearchFromPanel");
    	this.reloadResignedGridPanelStore(SearchForm.getValues(),'高级查询-辞职员工信息');
    },
  //有无工资卡
    onUserBankCardComboboxChange:function(field, newValue, oldValue, eOpts) {
    	var SearchForm = Ext.getCmp("ResignedSearchFromPanel");
    	this.reloadResignedGridPanelStore(SearchForm.getValues(),'高级查询-辞职员工信息');
    },
    //查看附件
    onShowBtnClick:function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("ResignedGridPanel");
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
        if(data.fileName==''||data.fileName==null){
        	 Ext.Msg.show({
  				title : '提示信息',
  				msg :'不存在附件信息',
  				buttons : Ext.Msg.OK,
  				icon : Ext.MessageBox.WARNING
  			});
        	return;
        }
        if(data.fileType.indexOf('image')>-1){
        	Ext.create("Resigned.view.ShowWindow",{title:'在线查看'}).show();
        	Ext.getCmp("pic").setSrc(extBasePath+"resigned/showResigned.do?resUuid="+data.resUuid+"&time="+new Date().getTime());
        }else{
        	window.location.href=extBasePath+"resigned/showResigned.do?resUuid="+data.resUuid+"&time="+new Date().getTime();
        }
    },
    //导出按钮
    onExportBtnClick:function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("ResignedGridPanel");
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
        window.location.href=extBasePath+"resigned/showResigned.do?resUuid="+data.resUuid+"&time="+new Date().getTime();
    },
    //关闭查看
    onCloseShowWindowBtnClick:function(button, e, eOpts) {
    	Ext.getCmp("ShowWindow").close();
    },
    //导出Excel按钮
    onResignedExcelBtnClick:function(button, e, eOpts) {
    	Ext.Msg.wait('正在处理数据，请稍候...','提示');
    	var panel = Ext.getCmp("ResignedSearchFromPanel");
        if(panel.isHidden()){
        	window.location.href=extBasePath+"resigned/getDownResignedList.do?resState="+DIC_RESIGNED_STATE_YES;
        }else{
        	var param = panel.getValues(true);
        	window.location.href=extBasePath+"resigned/getDownResignedList.do?resState="+DIC_RESIGNED_STATE_YES+"&"+param;  
        }
        Ext.Msg.hide();
    },
    //点击超链接查看附件
    showFileHref(fileType,resUuid){
        if(fileType.indexOf('image')>-1){
        	Ext.create("Resigned.view.ShowWindow",{title:'在线查看'}).show();
        	Ext.getCmp("pic").setSrc(extBasePath+"resigned/showResigned.do?resUuid="+resUuid+"&time="+new Date().getTime());
        }else{
        	window.location.href=extBasePath+"resigned/showResigned.do?resUuid="+resUuid+"&time="+new Date().getTime();
        }
    },
  //设置选择的月份
    setResignedMonth(userUuid){
    	//获取选中的月份呢
		var monthCheck = Ext.getCmp('month').items;
        
        Ext.Ajax.request({
    		url:"resigned/getResignedMonthList.do",
    		method:"POST",
    		params:{"userUuid":userUuid},
    		success:function(responst){
    			var result = JSON.parse(responst.responseText); 
    			if(result.success){
    				Ext.each(result.data,function(data){
    					for(var i = 0; i < monthCheck.length; i++){
			           		 if(monthCheck.get(i).inputValue==data.monthNum){
			           			monthCheck.get(i).setValue(true)
			           		 }
    					}
    				});
    			}
    		},
		    failure: function(from,action){
    			 onActionFailureTypeShow(action);
    		},
    		scope:this
    	});
    },
    onDelNumberBtnClick:function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("ResignedGridPanel");
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
        Ext.MessageBox.confirm("提示信息", "确定要删除工号吗？", function (btnId) {
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
            				 Ext.getCmp("ResignedGridPanel").store.load();
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
    init: function(application) {
        this.control({
            "button[id=ResignedRefreshBtn]": {
                click: this.onResignedRefreshBtnClick
            },
            "button[id=ResignedUpdBtn]": {
                click: this.onResignedUpdBtnClick
            },
            "button[id=ResignedDelBtn]": {
                click: this.onResignedDelBtnClick
            },
            "button[id=ResignedShowBtn]": {
                click: this.onResignedShowBtnClick
            },
            "button[id=ResignedStateBtn]": {
                click: this.onResignedStateBtnClick
            },
            "#ResignedGridPanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    				this.task.delay(200,this.onResignedGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onResignedGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "button[id=ResignedSaveBtn]":{
            	click:this.onResignedSaveBtnClick
            },
            "button[id=ResignedCloseBtn]":{
            	click:this.onResignedCloseBtnClick
            },
            "button[id=ResignedSearchBtn]":{
            	click:this.onResignedSearchBtnClick
            },
            "button[id=ResignedResetBtn]":{
            	click:this.onResignedResetBtnClick
            },
            "combobox[id=userBankCardState]": {
                change: this.onUserBankCardStateComboboxChange
            },
            "combobox[id=resState]": {
                change: this.onResStateComboboxChange
            },
            "combobox[id=userBankCard]": {
                change: this.onUserBankCardComboboxChange
            },
            "button[id=showBtn]": {
            	click: this.onShowBtnClick
            },
            'button[id=exportBtn]':{
            	click:this.onExportBtnClick
            },
            'button[id=closeShowWindowBtn]':{
            	click:this.onCloseShowWindowBtnClick
            },
            'button[id=ResignedExcelBtn]':{
            	click:this.onResignedExcelBtnClick
            },
            'button[id=ResignedBackBtn]':{
            	click:this.onResignedBackBtnClick
            },
            "button[id=delNumberBtn]":{
            	click:this.onDelNumberBtnClick
            }
            
        });
    }

});
