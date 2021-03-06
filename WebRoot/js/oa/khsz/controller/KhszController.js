/*
 * File: khsz/controller/KhszController.js
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

Ext.define('Khsz.controller.KhszController', {
    extend: 'Ext.app.Controller',
    //添加关闭按钮
    onCloseBtnClick: function(button, e, eOpts) {
        Ext.getCmp("KhszAddWindow").close();
    },
    
    //添加按钮
    onAddBtnClick: function(button, e, eOpts) {
        Ext.create("Khsz.view.KhszAddWindow",{title:'添加考勤天数',icon:'img/add.png'}).show();
    },
    
    //保存按钮
    onSaveBtnClick: function(button, e, eOpts){
    	var formPanel = Ext.getCmp("KqszForm");
	   	 if(!formPanel.isDirty()){
	   		 Ext.getCmp("KhszAddWindow").close();
	   		 return;
	   	 }
	   	 if(formPanel.isValid()){
	   		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
				if(btnId=='yes'){
					 formPanel.submit({
	    				 url:'kqsz/addKqsz.do',
			             method:'POST',
			             waitTitle : "提示",
			             waitMsg: '正在提交数据，请稍后...',
			             params:formPanel.form.getValues(),
			             success: function(form, action){
			        	    var result = JSON.parse(action.response.responseText); 
			            	if(result.success){
			            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
			            		 Ext.getCmp("KhszAddWindow").close();
								 Ext.getCmp("kqszGridPanel").store.load();
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
    
    //刷新按钮
    onRefreshBtnClick:function(button,e,eOpts){
    	var SearchForm = Ext.getCmp("searchFrom");
    	SearchForm.getForm().reset();
    	this.reloadWorkCheckGridPanelStore(null,'考勤设置');
    },
    //修改按钮点击事件
    onUpdBtnClick:function(button,e,eOpts){
    	var gridPanel = Ext.getCmp("kqszGridPanel");
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
        Ext.create("Khsz.view.KhszAddWindow",{title:'修改考勤天数',icon:'img/update.png'}).show();
    	//加载所属角色
    	Ext.getCmp("KqszForm").getForm().loadRecord(selectionModel.getSelection()[0]);

    },
    //删除按钮点击事件
    onDelBtnClick:function(button,e,eOpts){
    	var gridPanel = Ext.getCmp("kqszGridPanel");
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
            		url:"kqsz/delKqsz.do",
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
            				 Ext.getCmp("kqszGridPanel").store.load();
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
    //高级查询按钮点击事件
    onSearchBtnClick:function(button,e,eOpts){
    	var panel = Ext.getCmp("searchFrom");
        if(panel.isHidden()){
            panel.show();
        }else{
            panel.hide();
        }
    },
    //定时器,解决单击事件双击事件问题
    task:new Ext.util.DelayedTask(),
    //用户双击事件
    onGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.create("Khsz.view.KhszAddWindow",{title:'修改考勤天数',icon:'img/update.png'}).show();
    	Ext.getCmp("KqszForm").getForm().loadRecord(record);
    },
    //用户单击事件
    onGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("updBtn").enable(true);
    	Ext.getCmp("delBtn").enable(true);
    },
    //月份切换事件
    onMonthComboboxChange: function(field, newValue, oldValue, eOpts) {
    	var y=parseInt(Ext.getCmp("year").value);
		if(newValue==1){
			Ext.getCmp("kqStartTime").setValue((y-1)+"-12-26");
			Ext.getCmp("kqEndTime").setValue(y+"-01-25");
		}else{
			if(newValue-1<10){
				Ext.getCmp("kqStartTime").setValue(y+"-0"+(newValue-1)+"-26");
				if(newValue==10){
					Ext.getCmp("kqEndTime").setValue(y+"-"+newValue+"-25");
				}else{
					Ext.getCmp("kqEndTime").setValue(y+"-0"+newValue+"-25");
				}
			}else{
				Ext.getCmp("kqStartTime").setValue(y+"-"+(newValue-1)+"-26");
				Ext.getCmp("kqEndTime").setValue(y+"-"+newValue+"-25");
			
			}
		}
    },
    //查询按钮
    onReloadBtnClick:function(button,e,eOpts){
    	var SearchForm = Ext.getCmp("searchFrom");
    	this.reloadWorkCheckGridPanelStore(SearchForm.getValues(),'高级查询-考勤设置');
    },
    //重置按钮
    onResetBtnClick:function(button,e,eOpts){
    	var SearchForm = Ext.getCmp("searchFrom");
    	SearchForm.getForm().reset();
    	this.reloadWorkCheckGridPanelStore(null,'高级查询-考勤设置');
    },
    //刷新数据
    reloadWorkCheckGridPanelStore:function(data,text){
    	var gridPanel=Ext.getCmp("kqszGridPanel");
    	gridPanel.store.setProxy({
    		type: 'ajax',
            url: 'kqsz/getKqszPage.do',
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
    //导出exceel
    onExcelBtnClick:function(button,e,eOpts){
    	Ext.Msg.wait('正在处理数据，请稍候...','提示');
    	var panel = Ext.getCmp("searchFrom");
        if(panel.isHidden()){
        	window.location.href=extBasePath+"kqsz/getDownKqszList.do";
        }else{
        	var param = panel.getValues(true);
        	window.location.href=extBasePath+"kqsz/getDownKqszList.do?"+param;  
        }
        Ext.Msg.hide();
    },
    init: function(application) {
        this.control({
            "button[id=CloseBtn]": {
                click: this.onCloseBtnClick
            },
            "button[id=AddBtn]": {
                click: this.onAddBtnClick
            },
            "button[id=SaveBtn]": {
                click: this.onSaveBtnClick
            },
            "button[id=refreshBtn]": {
                click: this.onRefreshBtnClick
            },
            "button[id=updBtn]": {
                click: this.onUpdBtnClick
            },
            "button[id=delBtn]": {
                click: this.onDelBtnClick
            },
            "button[id=searchBtn]":{
            	 click: this.onSearchBtnClick
            },
            "button[id=ReloadBtn]": {
                click: this.onReloadBtnClick
            },
            "button[id=ResetBtn]": {
                click: this.onResetBtnClick
            },
            "button[id=ExcelBtn]": {
                click: this.onExcelBtnClick
            },
            "#kqszGridPanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    				this.task.delay(200,this.onGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "combobox[id=month]": {
                change: this.onMonthComboboxChange
            },
            
        });
    }

});
