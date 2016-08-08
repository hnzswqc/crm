/*
 * File: dic/controller/DicController.js
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

Ext.define('Dic.controller.DicController', {
    extend: 'Ext.app.Controller',

    id: 'DicController',
    
    models: [
	     'DicModel'
	 ],
	 stores: [
	     'DicTypeTreeStore',
	     'DicGridPanelStore'
	 ],
	 views: [
	     'DicViewport',
	     'DicTypeWindow',
	     'DicWindow'
	 ],
    
    //刷新按钮
    onDic_refresh_btnClick: function(button, e, eOpts) {
		Ext.getCmp("DicTypeTreePanel").store.load();
		Ext.getCmp("DicGridPanel").store.load();
    },
    //添加数据字典类别
    onDic_add_type_btnClick: function(button, e, eOpts) {
        Ext.create("Dic.view.DicTypeWindow",{title:'添加数据字典类别',icon:'img/add.png'}).show();
    },
    //添加数据字典
    onDic_add_dic_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("DicTypeTreePanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条字典类别树',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        Ext.create("Dic.view.DicWindow",{title:'添加数据字典',icon:'img/add.png'}).show();
        Ext.getCmp("type").setValue(selectionModel.getSelection()[0].data.id);
    },
    //修改数据字典类别
    onDic_upd_type_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("DicTypeTreePanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条字典类别树',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var data = selectionModel.getSelection()[0].data;
        Ext.create("Dic.view.DicTypeWindow",{title:'修改字典类别',icon:'img/update.png'}).show();
        Ext.getCmp("DicTypeForm").getForm().loadRecord(selectionModel.getSelection()[0]);
   	    Ext.getCmp("id").setReadOnly(true);
   	    Ext.getCmp("id").setFieldStyle("background:#f2f2f2");
        
    },
    //修改数据字典
    onDic_upd_dic_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("DicGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条数据字典信息',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var data = selectionModel.getSelection()[0].data;
        Ext.create("Dic.view.DicWindow",{title:'修改数据字典',icon:'img/update.png'}).show();
        Ext.getCmp("DicForm").getForm().loadRecord(selectionModel.getSelection()[0]);
   	    Ext.getCmp("DicForm_key").setReadOnly(true);
	    Ext.getCmp("DicForm_key").setFieldStyle('background:#f2f2f2');
    },
    //删除数据字典类别
    onDic_del_type_btnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("DicTypeTreePanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条字典类别树',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        
    	//先判断当前类别下是否存在数据字典。
    	var count = Ext.getCmp("DicGridPanel").getStore().getCount();
    	if(count>0){
    		 Ext.Msg.show({
    				title : '提示信息',
    				msg :'请先删除该类别下的数据字典信息',
    				buttons : Ext.Msg.OK,
    				icon : Ext.MessageBox.WARNING
    			 });
    		return;
    	}
    
        var data = selectionModel.getSelection()[0].data;
		this.delDicTypeClick(data);
    },
    //删除数据字典
    onDic_del_dic_btnClick: function(button, e, eOpts) {
    	
    	var gridPanel = Ext.getCmp("DicGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条字典类别树',
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
            		url:"dic/delDic.do",
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
            				 Ext.getCmp("DicGridPanel").store.load();
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
    //保存字典类别
    onDic_type_save_btnClick: function(button, e, eOpts) {
     var formPanel =  Ext.getCmp("DicTypeForm");
   	 if(!formPanel.isDirty()){
   		 Ext.getCmp("DicTypeWindow").close();
		 return;
   	 }
   	 if(formPanel.isValid()){
   		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
    			if(btnId=='yes'){
    				 formPanel.submit({
        				 url:'dic/addDicType.do',
    		             method:'POST',
    		             waitTitle : "提示",
    		             waitMsg: '正在提交数据，请稍后...',
    		             success: function(form, action){
    		        	    var result = JSON.parse(action.response.responseText); 
    		            	if(result.success){
    		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
    		            		 Ext.getCmp("DicTypeWindow").close();
    		            		 Ext.getCmp("DicTypeTreePanel").store.load();
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
    //关闭字典类别
    onDic_type_close_btnClick: function(button, e, eOpts) {
    	Ext.getCmp("DicTypeWindow").close();
    },
    //保存数据字典
    onDic_save_btnClick: function(button, e, eOpts) {
    	var formPanel =  Ext.getCmp("DicForm");
      	 if(!formPanel.isDirty()){
      		 Ext.getCmp("DicWindow").close();
   		 return;
      	 }
      	 if(formPanel.isValid()){
      		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
       			if(btnId=='yes'){
       				 formPanel.submit({
           				 url:'dic/addDic.do',
       		             method:'POST',
       		             waitTitle : "提示",
       		             waitMsg: '正在提交数据，请稍后...',
       		             success: function(form, action){
       		        	    var result = JSON.parse(action.response.responseText); 
       		            	if(result.success){
       		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
       		            		 Ext.getCmp("DicWindow").close();
       		            		 Ext.getCmp("DicGridPanel").store.load();
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
    //关闭数据字典
    onDic_close_btnClick: function(button, e, eOpts) {
    	Ext.getCmp("DicWindow").close();
    },
    //删除数据字典类别
    delDicTypeClick:function(data){
    	 Ext.MessageBox.confirm("提示信息", "确定要删除吗？", function (btnId) {
             if (btnId == "yes") {
            	 Ext.Msg.wait('正在处理数据，请稍候...','提示');
                 Ext.Ajax.request({
             		url:"dic/delDicType.do",
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
             				 Ext.getCmp("DicTypeTreePanel").store.load();
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
    //绑定右键菜单
    onDicTypeTreePanelItemcontextmenu:function(view,record,items,index,e,eOpts){
    	 //创建一个菜单      
        var myMenu = new Ext.menu.Menu({      
            items:[
                 
                   {
		                xtype: 'menuitem',
		                icon: 'img/add.png',
		                text: '添加',
		                scope:this,
		                handler:function(){
                	   		Ext.create("Dic.view.DicTypeWindow",{title:'添加数据字典类别',icon:'img/add.png'}).show();
                  		}
		            },
                   {
		                xtype: 'menuitem',
		                icon: 'img/update.png',
		                text: '修改',
		                scope:this,
		                handler:function(){
                	   		Ext.create("Dic.view.DicTypeWindow",{title:'修改字典类别',icon:'img/update.png'}).show();
                	   		Ext.getCmp("DicTypeForm").getForm().loadRecord(record);
                	   		Ext.getCmp("id").setDisabled(true);
                   		}
                   		
		            },
		            {
		                xtype: 'menuitem',
		                icon: 'img/del.png',
		                text: '删除',
		                scope:this,
		                handler:function(){
		            		//先判断当前类别下是否存在数据字典。
			            	var count = Ext.getCmp("DicGridPanel").getStore().getCount();
			            	if(count>0){
			            		 Ext.Msg.show({
	                    				title : '提示信息',
	                    				msg :'请先删除该类别下的数据字典信息',
	                    				buttons : Ext.Msg.OK,
	                    				icon : Ext.MessageBox.WARNING
	                    			 });
			            		return;
			            	}
		            		this.delDicTypeClick(record.data);
               			}
		            }
		   ]      
       },this) ;  
    	e.preventDefault(); 
    	myMenu.showAt(e.getXY());
    },
    //定时器,解决单击事件双击事件问题
    task:new Ext.util.DelayedTask(),
    //字典类别双击事件
    onDicTypeTreePanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.create("Dic.view.DicTypeWindow",{title:'修改字典类别',icon:'img/update.png'}).show();
   		Ext.getCmp("DicTypeForm").getForm().loadRecord(record);
   		Ext.getCmp("id").setReadOnly(true);
   	    Ext.getCmp("id").setFieldStyle("background:#f2f2f2");
    },
    //字典类别单击事件
    onDicTypeTreePanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	this.reloadGridPanelStroe({'type':record.data.id},record.data.text+"-数据字典信息");
    	Ext.getCmp("Dic_upd_type_btn").enable(true);
    	Ext.getCmp("Dic_del_type_btn").enable(true);
    },
    //字典信息双击事件
    onDicGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.create("Dic.view.DicWindow",{title:'修改数据字典',icon:'img/update.png'}).show();
   		Ext.getCmp("DicForm").getForm().loadRecord(record);
   		//Ext.getCmp("DicForm_id").setDisabled(true);
   		Ext.getCmp("DicForm_key").setReadOnly(true);
	    Ext.getCmp("DicForm_key").setFieldStyle('background:#f2f2f2');
    },
    //字典信息单击事件
    onDicGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("Dic_upd_dic_btn").enable(true);
    	if(record.data.child>0){
    		Ext.getCmp("Dic_del_dic_btn").setDisabled(true);
    	}else{
    		Ext.getCmp("Dic_del_dic_btn").enable(true);
    	}
    },
    //加载gridpanel数据
    reloadGridPanelStroe:function(data,title){
    	var gridPanel = Ext.getCmp("DicGridPanel");
    	gridPanel.store.setProxy({
	    		type: 'ajax', 
	    		url: 'dic/getDicList.do',
	    		extraParams:data,
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
    //标识名验证
    onIdTextfieldBlur: function(component, e, eOpts) {
    	if(component.originalValue == component.value){
    		return;
    	}
    	Ext.Ajax.request({
    		url:"dic/validateDicType.do",
     		method:"POST",
     		params:{
        	 	'id':component.value
         	},
     		success:function(responst){
     			var result = JSON.parse(responst.responseText); 
     			if(result.success){
     				Ext.Msg.show({
        				title : '提示信息',
        				msg : '该数据字典标识已经存在，请重新输入！',
        				buttons : Ext.Msg.OK,
        				icon : Ext.MessageBox.WARNING
        			 });
     				component.focus();
     				component.setValue("");
     			}
     		},
 		    failure: function(from,action){
     			onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    },
    //数据字典信息id验证
    onDicForm_idTextfieldBlur: function(component, e, eOpts) {
    	if(component.originalValue == component.value){
    		return;
    	}
    	Ext.Ajax.request({
    		url:"dic/validateDic.do",
     		method:"POST",
     		params:{
        	 	'id':component.value,
        	 	'type':Ext.getCmp("type").value
         	},
     		success:function(responst){
     			var result = JSON.parse(responst.responseText); 
     			if(result.success){
     				Ext.Msg.show({
        				title : '提示信息',
        				msg : '该数据字典信息已经存在，请重新输入！',
        				buttons : Ext.Msg.OK,
        				icon : Ext.MessageBox.WARNING
        			 });
     				component.focus();
     				component.setValue("");
     			}
     		},
 		    failure: function(from,action){
     			onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    },
    //数据字典信息key验证
    onDicForm_keyTextfieldBlur: function(component, e, eOpts) {
    	if(component.originalValue == component.value){
    		return;
    	}
    	Ext.Ajax.request({
    		url:"dic/validateDicKey.do",
     		method:"POST",
     		params:{
        	 	'key':component.value,
        	 	'type':Ext.getCmp("type").value
         	},
     		success:function(responst){
     			var result = JSON.parse(responst.responseText); 
     			if(result.success){
     				Ext.Msg.show({
        				title : '提示信息',
        				msg : '该数据字典信息已经存在，请重新输入！',
        				buttons : Ext.Msg.OK,
        				icon : Ext.MessageBox.WARNING
        			 });
     				component.focus();
     				component.setValue("");
     			}
     		},
 		    failure: function(from,action){
     			onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    },
    //类别加载后再加载数据字典
    onDicTypeTreePanelRender:function(component, eOpts){
    	component.store.addListener('load',this.beforeLoad,this);
    },
  //数据加载事件。加载完模块列表后根据第一个模块信息查找其下功能菜单。
    beforeLoad:function(store,operation,eOpts){
    	var obj = store.proxy.reader.jsonData;
		var data;
		var text;
		if(obj.length>0){
			data = obj[0].id;
			text = obj[0].text+"-数据字典信息"
		}else{
			data = "";
			text = "数据字典信息";
		}
		this.reloadGridPanelStroe({'type':data},text);
    },
  //父级菜单选择事件
    onParentUuidSelect:function(combobox,record,eOpts){
    	var uuid = Ext.getCmp("uuid").getValue();
    	if(null!=uuid){
    		if(uuid==record.data.id){
    			Ext.Msg.show({
    				title : '提示信息',
    				msg :'不能选择当前自己作为父级菜单!',
    				buttons : Ext.Msg.OK,
    				icon : Ext.MessageBox.WARNING
    			 });
    			combobox.setValue(DEFAULT_ROOT_UUID);
    		}
    	}
    },
    init: function(application) {
        this.control({
        	"button[id=Dic_refresh_btn]": {
	            click: this.onDic_refresh_btnClick
	        },
            "button[id=Dic_add_type_btn]": {
                click: this.onDic_add_type_btnClick
            },
            "button[id=Dic_add_dic_btn]": {
                click: this.onDic_add_dic_btnClick
            },
            "button[id=Dic_upd_type_btn]": {
                click: this.onDic_upd_type_btnClick
            },
            "button[id=Dic_upd_dic_btn]": {
                click: this.onDic_upd_dic_btnClick
            },
            "button[id=Dic_del_type_btn]": {
                click: this.onDic_del_type_btnClick
            },
            "button[id=Dic_del_dic_btn]": {
                click: this.onDic_del_dic_btnClick
            },
            "button[id=Dic_type_save_btn]": {
                click: this.onDic_type_save_btnClick
            },
            "button[id=Dic_type_close_btn]": {
                click: this.onDic_type_close_btnClick
            },
            "button[id=Dic_save_btn]": {
                click: this.onDic_save_btnClick
            },
            "button[id=Dic_close_btn]": {
                click: this.onDic_close_btnClick
            },
            "#DicTypeTreePanel":{
            	//右键绑定菜单
            	//itemcontextmenu:this.onDicTypeTreePanelItemcontextmenu,
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
					this.task.delay(200,this.onDicTypeTreePanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onDicTypeTreePanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		render:this.onDicTypeTreePanelRender
            },
            "textfield[id=id]": {
                blur: this.onIdTextfieldBlur
            },
            "textfield[id=DicForm_id]": {
                blur: this.onDicForm_idTextfieldBlur
            },
            "textfield[id=DicForm_key]": {
                blur: this.onDicForm_keyTextfieldBlur
            },
            "#DicGridPanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
					this.task.delay(200,this.onDicGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onDicGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "#parentUuid":{
            	select:this.onParentUuidSelect
            }
            
        });
    }

});
