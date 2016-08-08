/*
 * File: power/controller/PowerController.js
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

Ext.define('Power.controller.PowerController', {
    extend: 'Ext.app.Controller',

    id: 'PowerController',
    models: [
        'PowerModel'
    ],
    stores: [
        'PowerStore',
        'PowerModelTreeStore'
    ],
    views: [
        'PowerViewport'
    ],
    
    //刷新按钮
    onPower_refresh_btnClick: function(button, e, eOpts) {
		Ext.getCmp("PowerGridPanel").store.reload();
		Ext.getCmp("PowerModelTreePanel").store.reload();
		
    },

    //添加按钮
    onPower_add_btnClick: function(button, e, eOpts) {
	    var gridPanel = Ext.getCmp("PowerModelTreePanel");
        var selectionModel = gridPanel.getSelectionModel();
        var text;
        var id;
        if(selectionModel.getCount()<=0){
        	/*var jsonData=gridPanel.store.proxy.reader.jsonData
        	if(jsonData.length==0){
        		text="根目录";
        		id=DEFAULT_ROOT_UUID;
        	}else{
        		id=jsonData[0].id;
        		text=jsonData[0].text;
        	}*/
        	  Ext.Msg.show({
   				title : '提示信息',
   				msg :'请先选择一条模块信息!',
   				buttons : Ext.Msg.OK,
   				icon : Ext.MessageBox.WARNING
   			});
               return;
        }else{
        	var data = selectionModel.getSelection()[0].data;
            text=data.text;
            id=data.id;
        }
        Ext.create("Power.view.PowerWindow",{title:"添加功能链接",icon:"img/add.png"}).show();
        Ext.getCmp("powerModelName").setValue(text);
        Ext.getCmp("powerModelUuid").setValue(id);
    },
    //高级查询按钮
    onPower_show_search_btnClick: function(button, e, eOpts) {
    	 var panel = Ext.getCmp("PowerSearchForm");
         if(panel.isHidden()){
             panel.show();
         }else{
             panel.hide();
         }
    },
    //保存按钮
    onPower_window_add_btnClick: function(button, e, eOpts) {
    	 var formPanel = Ext.getCmp("PowerWindowForm");
       	 if(!formPanel.isDirty()){
       		 Ext.getCmp("PowerWindow").close();
    		 return;
       	 }
    	 if(formPanel.isValid()){
    		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
    			if(btnId=='yes'){
    				 formPanel.submit({
        				 url:'power/addPower.do',
    		             method:'POST',
    		             waitTitle : "提示",
    		             waitMsg: '正在提交数据，请稍后...',
    		             params:formPanel.form.getValues(),
    		             success: function(form, action){
    		        	    var result = JSON.parse(action.response.responseText); 
    		            	if(result.success){
    		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
    		            		 Ext.getCmp("PowerWindow").close();
    		            		 Ext.getCmp("PowerGridPanel").store.reload();
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
    onPower_window_close_btnClick: function(button, e, eOpts) {
        Ext.getCmp("PowerWindow").close();
    },
    //修改按钮
    onPower_upd_btnClick: function(button, e, eOpts) {
    	 var gridPanel = Ext.getCmp("PowerGridPanel");
         var selectionModel = gridPanel.getSelectionModel();
         if(selectionModel.getCount()<=0){
             Ext.Msg.show({
 				title : '提示信息',
 				msg :'请先选择一条功能链接信息!',
 				buttons : Ext.Msg.OK,
 				icon : Ext.MessageBox.WARNING
 			});
             return;
         }
         Ext.create("Power.view.PowerWindow",{title:"修改功能链接",icon:"img/update.png"}).show();
         Ext.getCmp("PowerWindowForm").getForm().loadRecord(selectionModel.getSelection()[0]);
    },
    
    //审计事项单击选择事件
    onPowerModelTreePanelItemClick: function(dataview, record, item, index, e, eOpts) {
    	
    	if(!record.data.leaf){
    		Ext.getCmp("Power_add_btn").setDisabled(true);
    		return;
    	}else{
    		Ext.getCmp("Power_add_btn").enable(true);
    	}
    	
    	var gridPanel = Ext.getCmp("PowerGridPanel");
    	gridPanel.store.setProxy({
	    		 type: 'ajax', 
	    		 url: 'power/getPowerList.do',
	    		 extraParams:{
		    		 'powerModelUuid':record.data.id
    		 	},
    		 	reader: {
	    			 type: 'json',
	          	  	 //数据格式为json
	                 root: 'data',   
	                 //获取数据总数 
	                 totalProperty: 'dataSize'
    		 	}
		 }); 
    	gridPanel.store.load(); 
    	gridPanel.setTitle(record.data.text+"-功能链接");
    	
    	
    },
    //删除按钮
    onPower_del_btnClick: function(button, e, eOpts) {
    	 var gridPanel = Ext.getCmp("PowerGridPanel");
         var selectionModel = gridPanel.getSelectionModel();
         if(selectionModel.getCount()<=0){
        	 Ext.Msg.show({
      				title : '提示信息',
      				msg :'请选择一条功能链接!',
      				buttons : Ext.Msg.OK,
      				icon : Ext.MessageBox.WARNING
      			});
             return;
         }
         var data = selectionModel.getSelection()[0].data;
         if(data.child>0){
         	Ext.Msg.show({
  				title : '提示信息',
  				msg :'当前功能链接存在子级功能，不允许删除',
  				buttons : Ext.Msg.OK,
  				icon : Ext.MessageBox.WARNING
  			});
         	return;
         }
         Ext.MessageBox.confirm("提示信息", "确定要删除吗？", function (btnId) {
             if (btnId == "yes") {
            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
                 Ext.Ajax.request({
             		url:"power/delPower.do",
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
             				Ext.getCmp("PowerGridPanel").store.reload();
             			}else{
             				 Ext.Msg.show({
                    				title : '提示信息',
                    				msg :'删除失败!',
                    				buttons : Ext.Msg.OK,
                    				icon : Ext.MessageBox.INFO
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
    //定时器,解决单击事件双击事件问题
    task:new Ext.util.DelayedTask(),
    //双击事件
    onPowerGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.create("Power.view.PowerWindow",{title:"修改功能链接",icon:"img/update.png"}).show();
    	Ext.getCmp("PowerWindowForm").loadRecord(record);
    },
    //单击事件
    onPowerGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("Power_upd_btn").enable(true);
    	if(record.data.num<=0){
    		Ext.getCmp("Power_del_btn").enable(true);
    		Ext.getCmp("Power_operate_btn").enable(true);
    	}else{
    		Ext.getCmp("Power_del_btn").setDisabled(true);
    		Ext.getCmp("Power_operate_btn").setDisabled(true);
    	}
    },
    //父级菜单选择事件
    onPowerParentUuidSelect:function(combobox,record,eOpts){
    	var powerUuid = Ext.getCmp("powerUuid").getValue();
    	if(null!=powerUuid){
    		if(powerUuid==record.data.id){
    			Ext.Msg.show({
    				title : '提示信息',
    				msg :'不能选择当前功能链接作为父级菜单!',
    				buttons : Ext.Msg.OK,
    				icon : Ext.MessageBox.WARNING
    			 });
    			combobox.setValue(DEFAULT_ROOT_UUID);
    		}
    	}
    },
    //所属模块
    onPowerModelUuidSelect:function(combobox,record,eOpts){
		if (record.data.id == DEFAULT_ROOT_UUID||record.data.leaf==false) {
			Ext.Msg.show({
				title : '提示信息',
				msg :'不能选择子系统或者根目录为上级模块!',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING
			 });
			return false;
		}
		Ext.getCmp("powerModelName").setValue(record.data.text);
		this.reloadPowerParentUuidStore(record.data);
    },
    
    reloadPowerParentUuidStore:function(data){
    	var treepicker = Ext.getCmp("powerParentUuid");
    	treepicker.store.setProxy({
	    		 type: 'ajax', 
	    		 url: 'power/getPowerTreeList.do',
	    		 extraParams:{
		    		 'powerModelUuid':data.id,
		    		 'powerParentUuid':DEFAULT_ROOT_UUID
    		 	},
    		 	reader: {
	    			 type: 'json',
	          	  	 //数据格式为json
	                 root: 'data',   
	                 //获取数据总数 
	                 totalProperty: 'dataSize'
    		 	},
    		 	root: {
    	             text: '根目录',
    	             id: DEFAULT_ROOT_UUID
    	           }
		 }); 
    	treepicker.store.reload(); 
    },
    //查询按钮
    onPower_search_btnClick: function(button, e, eOpts) {
    	this.reloadPowerGridPanelStore();
    },
    //重置按钮
    onPower_reset_btnClick: function(button, e, eOpts) {
    	Ext.getCmp("powerName").setValue(null);
    	Ext.getCmp("powerState").setValue(null);
    	this.reloadPowerGridPanelStore();
    },
    //过滤数据
    reloadPowerGridPanelStore:function(){
    	var gridPanel = Ext.getCmp("PowerGridPanel");
		gridPanel.store.setProxy({
	    		 type: 'ajax', 
	    		 url: 'power/getPowerList.do',
	    		 extraParams:Ext.getCmp("PowerSearchForm").getForm().getValues(),
			 	 reader: {
	    			 type: 'json',
	          	  	 //数据格式为json
	                 root: 'data',   
	                 //获取数据总数 
	                 totalProperty: 'dataSize'
			 	}
		 }); 
		gridPanel.store.load(); 
		//Ext.getCmp("PowerModelTreePanel").store.reload();
    },
    //模型菜单加载后事件
    onPowerModelTreePanelRender:function(component, eOpts){
    	component.store.addListener('load',this.beforeLoad,this);
    },
    //数据加载事件。加载完模块列表后根据第一个模块信息查找其下功能菜单。
    beforeLoad:function(store,operation,eOpts){
		var gridPanel = Ext.getCmp("PowerGridPanel");
		gridPanel.store.setProxy({
	    		 type: 'ajax', 
	    		 url: 'power/getPowerList.do',
                 extraParams:{
         		//获取第一个组织机构信息
		   	 	 	'powerModelUuid':null
			 	 },
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
    //操作权限按钮
    onPower_operate_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("PowerGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条功能链接!',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var data = selectionModel.getSelection()[0].data;
        Ext.create("Power.view.PowerOperateWindow",{title:data.powerName+'-操作功能',icon:'img/setting.png'}).show();
        this.reloadOperateGridPanel(data);
    },
    //加载功能操作功能数据
    reloadOperateGridPanel:function(data){
    	var gridPanel = Ext.getCmp("operateGridPanel");
		gridPanel.store.setProxy({
	    		 type: 'ajax', 
	    		 url: 'operate/getOperatePage.do',
                 extraParams:{
		   	 	 	'powerUuid':data.powerUuid
			 	 },
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
    init: function(application) {
        this.control({
            "button[id=Power_refresh_btn]": {
                click: this.onPower_refresh_btnClick
            },
            "button[id=Power_add_btn]": {
                click: this.onPower_add_btnClick
            },
            "button[id=Power_show_search_btn]": {
                click: this.onPower_show_search_btnClick
            },
            "button[id=Power_window_add_btn]": {
                click: this.onPower_window_add_btnClick
            },
            "button[id=Power_window_close_btn]": {
                click: this.onPower_window_close_btnClick
            },
            "button[id=Power_del_btn]":{
            	click: this.onPower_del_btnClick
            },
            "button[id=Power_search_btn]":{
            	click: this.onPower_search_btnClick
            },
            "button[id=Power_reset_btn]":{
            	click: this.onPower_reset_btnClick
            },
            "button[id=Power_upd_btn]":{
            	click: this.onPower_upd_btnClick
            },
            "button[id=Power_operate_btn]":{
            	click:this.onPower_operate_btnClick
            },
            "#PowerModelTreePanel":{
            	itemclick:this.onPowerModelTreePanelItemClick,
            	render:this.onPowerModelTreePanelRender
            	
            },
            "#PowerGridPanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    				this.task.delay(200,this.onPowerGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onPowerGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "#powerParentUuid":{
            	select:this.onPowerParentUuidSelect
            },
            '#powerModelUuid':{
            	select:this.onPowerModelUuidSelect
            }
            
        });
    }

});