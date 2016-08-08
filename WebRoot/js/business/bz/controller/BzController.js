/*
 * File: bz/controller/BzController.js
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

Ext.define('Bz.controller.BzController', {
    extend: 'Ext.app.Controller',

    //刷新按钮
    onBzReloadBtnClick: function(button, e, eOpts) {
		Ext.getCmp("BzGridPanel").store.reload();
    },
    //添加按钮
    onBzAddBtnClick: function(button, e, eOpts) {
    	  Ext.create("Bz.view.MyWindow",{title:'添加包装工资',icon:'img/add.png'}).show();
    },
    //修改按钮
    onBzUpdBtnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("BzGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条记录',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        Ext.create("Bz.view.MyWindow",{title:'修改包装工资',icon:'img/update.png'}).show();
   	    Ext.getCmp("bzFormPanel").loadRecord(selectionModel.getSelection()[0]);
    },
    //删除按钮
    onBzDelBtnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("BzGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请选择一条记录',
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
            		url:"logging/delLogging.do",
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
            				 Ext.getCmp("BzGridPanel").store.load();
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
    onBzExportBtnClick: function(button, e, eOpts) {
    	Ext.Msg.wait('正在处理数据，请稍候...','提示');
    	var panel = Ext.getCmp("BzSearchFormPanel");
        if(panel.isHidden()){
        	window.location.href=extBasePath+"bz/getDownBzList.do";
        }else{
        	var param = panel.getValues(true);
        	window.location.href=extBasePath+"bz/getDownBzList.do?"+param;  
        }
        Ext.Msg.hide();
    	
    },
    //高级查询按钮
    onBzShowSearchFormBtnClick: function(button, e, eOpts) {
    	var panel = Ext.getCmp("BzSearchFormPanel");
        if(panel.isHidden()){
            panel.show();
        }else{
            panel.hide();
        }
    },
    //保存按钮
    onBzSaveBtnClick: function(button, e, eOpts) {
    	var formPanel = Ext.getCmp("bzFormPanel");
    	 if(!formPanel.isDirty()){
    		 Ext.getCmp("BzWindow").close();
    		 return;
    	 }
    	 if(formPanel.isValid()){
    		 Ext.MessageBox.confirm("提示信息", "确定要保存吗？", function (btnId) {
 			if(btnId=='yes'){
 				 formPanel.submit({
     				 url:'logging/addLogging.do',
 		             method:'POST',
 		             waitTitle : "提示",
 		             waitMsg: '正在提交数据，请稍后...',
 		             params:formPanel.form.getValues(),
 		             success: function(form, action){
 		        	    var result = JSON.parse(action.response.responseText); 
 		            	if(result.success){
 		            		 Ext.MessageBox.confirm("提示信息", "保存成功，是否继续添加？", function (btnId) {
 		             			if(btnId=='yes'){
 		             				Ext.getCmp("loggingProductNum").setValue("");
 		             				Ext.getCmp("loggingProductNum_hidden").setValue("");
 		             				Ext.getCmp("loggingPrice").setValue(0);
 		             				Ext.getCmp("loggingWeight").setValue(0);
 		             				Ext.getCmp("loggingProductUuid").setValue("");
 		             				Ext.getCmp("loggingNumber").setValue(0);
 		             				Ext.getCmp("loggingAllWeight").setValue(0);
 		             				Ext.getCmp("loggingWages").setValue(0);
 		             				Ext.getCmp("loggingErrNum").setValue(0);
 		             				Ext.getCmp("loggingErrRatio").setValue(0);
 		             				Ext.getCmp("loggingErrWages").setValue(0);
 		             				Ext.getCmp("loggingRealityWages").setValue(0);
 		             			}else{
 		             				Ext.getCmp("BzWindow").close();
 		             				Ext.getCmp("BzGridPanel").store.reload();
 		             			}
 		            		 },this);
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
    onBzCloseBtnClick: function(button, e, eOpts) {
    	Ext.getCmp("BzWindow").close();
    },
  //员工编号失去焦点事件，判断是否存在该编号人员员工
    onLoggingUserNumberBlur: function(component, e, eOpts) {
    	if(component.readOnly){
    		return;
    	}
    	if(null==component.value||""==component.value){
    		return;
    	}
    	var hiddenValue=Ext.getCmp("loggingUserNumber_hidden").value;
    	if(component.value==hiddenValue){
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
     					var msg='该员工已离职';
     					Ext.Msg.show({
            				title : '提示信息',
            				msg : msg,
            				buttons : Ext.Msg.OK,
            				icon : Ext.MessageBox.WARNING
            			 });
         				component.setValue("");
         				Ext.getCmp("loggingUserNumber_hidden").setValue("");
     				}else{
     					Ext.getCmp("loggingUserUuid").setValue(result.data.userUuid);//用户uuid
     					Ext.getCmp("loggingUserSex").setValue(result.data.userGender);//用户性别
     					Ext.getCmp("loggingUserName").setValue(result.data.userName);//用户名称
     					Ext.getCmp("loggingUserNumber_hidden").setValue(component.value);
     				}
     			}else{
     				var msg='没有找到该员工，请重新选择';
     				Ext.Msg.show({
        				title : '提示信息',
        				msg : msg,
        				buttons : Ext.Msg.OK,
        				icon : Ext.MessageBox.WARNING
        			 });
     				component.setValue("");
     				Ext.getCmp("loggingUserNumber_hidden").setValue("");
     				this.reloadLoggingTypeState(null);
     			}
     		},
 		    failure: function(from,action){
     			 Ext.Msg.hide();
     			 Ext.getCmp("loggingUserNumber_hidden").setValue("");
     			 onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    },
  //通过产品型号获取产品信息
    onLoggingProductNumBlur: function(component, e, eOpts) {
    	if(component.readOnly){
    		return;
    	}
    	if(null==component.value||""==component.value){
    		return;
    	}
    	var hiddenValue=Ext.getCmp("loggingProductNum_hidden").value;
    	if(component.value==hiddenValue){
    		return;
    	}
    	Ext.Msg.wait('正在查找产品信息，请稍候...','提示');
    	Ext.Ajax.request({
    		url:"product/getProductByParam.do",
     		method:"POST",
     		params:{
        	 	'productNum':component.value
         	},
     		success:function(responst){
         		Ext.Msg.hide();
     			var result = JSON.parse(responst.responseText); 
     			if(result.success){
     				//设置一些参数。
     				this.setProductParam(result.data);
     				Ext.getCmp("loggingProductNum_hidden").setValue(component.value);
     			}else{
     				var msg='没有找到产品信息，请重新选择';
     				Ext.Msg.show({
        				title : '提示信息',
        				msg : msg,
        				buttons : Ext.Msg.OK,
        				icon : Ext.MessageBox.WARNING
        			 });
     				component.setValue("");
     				Ext.getCmp("loggingProductNum_hidden").setValue("");
     				this.setProductParam(null);
     			}
     		},
 		    failure: function(from,action){
     			 Ext.Msg.hide();
     			 Ext.getCmp("loggingProductNum_hidden").setValue("");
     			 onActionFailureTypeShow(action);
     		},
     		scope:this
    	});
    },
  //设置界面属性显示隐藏属性
    setProductParam:function(data){
    	if(null==data){
    		Ext.getCmp("loggingPrice").setValue("");//单价
        	Ext.getCmp("loggingWeight").setValue("");//支重
    		return;
    	}
    	Ext.getCmp("loggingWeight").setValue(data.productWeight);//支重
    	Ext.getCmp("loggingProductUuid").setValue(data.productUuid);//产品uuid
    	//LOGGING_TYPE_BZ
		Ext.getCmp("loggingPrice").setValue(data.productBzPrice);
    },
    //通过数量计算工资和总重量
    onLoggingNumberBlur: function(component, e, eOpts) {
    	if(null==component.value||""==component.value){
    		Ext.getCmp("loggingWages").setValue(0);
    		Ext.getCmp("loggingAllWeight").setValue(0);
    		return;
    	}
		//工资
    	var price = Ext.getCmp("loggingPrice").value;
    	if(null==price||""==price){
    		Ext.getCmp("loggingWages").setValue(0);
    	}else{
    		Ext.getCmp("loggingWages").setValue(price*component.value);
    	}
    	//重量
    	var loggingWeight = Ext.getCmp("loggingWeight").value;
    	if(null==loggingWeight||""==loggingWeight){
    		Ext.getCmp("loggingAllWeight").setValue(0);
    	}else{
    		Ext.getCmp("loggingAllWeight").setValue(loggingWeight*component.value);
    	}
    	
    	Ext.getCmp("loggingRealityWages").setValue(Ext.getCmp("loggingWages").value);
    },
    //计算次品率
    onLoggingErrNumBlur: function(component, e, eOpts) {
    	if(component.value==""||null==component.value){
    		Ext.getCmp("loggingErrWages").setValue(0);
    		Ext.getCmp("loggingRealityWages").setValue(Ext.getCmp("loggingWages").value);
    		return;
    	}
    	//次品率
    	var loggingErrRatio = Ext.getCmp("loggingErrRatio").value;
    	if(null==loggingErrRatio||""==loggingErrRatio){
    		Ext.getCmp("loggingErrWages").setValue(0);
    		Ext.getCmp("loggingRealityWages").setValue(Ext.getCmp("loggingWages").value);
    	}else{
    		Ext.getCmp("loggingErrWages").setValue(loggingErrRatio*component.value);
    		Ext.getCmp("loggingRealityWages").setValue(Ext.getCmp("loggingWages").value-loggingErrRatio*component.value);
    	}
    },
    //计算次品率
    onLoggingErrRatioBlur: function(component, e, eOpts) {
    	if(component.value==""||null==component.value){
    		Ext.getCmp("loggingErrWages").setValue(0);
    		Ext.getCmp("loggingRealityWages").setValue(Ext.getCmp("loggingWages").value);
    		return;
    	}
    	//次品率
    	var loggingErrNum = Ext.getCmp("loggingErrNum").value;
    	if(null==loggingErrNum||""==loggingErrNum){
    		Ext.getCmp("loggingErrWages").setValue(0);
    		Ext.getCmp("loggingRealityWages").setValue(Ext.getCmp("loggingWages").value);
    	}else{
    		Ext.getCmp("loggingErrWages").setValue(loggingErrNum*component.value);
    		Ext.getCmp("loggingRealityWages").setValue(Ext.getCmp("loggingWages").value-loggingErrNum*component.value);
    	
    	}
    },
    //查询按钮
    onBzSearchBtnClick: function(button, e, eOpts) {
    	var searchForm = Ext.getCmp("BzSearchFormPanel");
    	this.reloadBzGridPanelStore(searchForm.getValues(),'高级查询-包装工资');
    },
    //重置按钮
    onBzResetBtnClick: function(button, e, eOpts) {
    	Ext.getCmp("loggingUserNumber_search").setValue("");
    	Ext.getCmp("loggingProductNum_search").setValue("");
    	Ext.getCmp("startDate_search").setValue("");
    	Ext.getCmp("endDate_search").setValue("");
   	    this.reloadBzGridPanelStore(null,'包装工资');
    },
    //刷新数据
    reloadBzGridPanelStore:function(data,text){
    	var gridPanel=Ext.getCmp("BzGridPanel");
    	gridPanel.store.setProxy({
	 		url:'bz/getBzPage.do',
            type: 'ajax',
            extraParams:data,
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
  //定时器,解决单击事件双击事件问题
    task:new Ext.util.DelayedTask(),
   //用户双击事件
    onBzGridPanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
       Ext.create("Bz.view.MyWindow",{title:'修改包装工资',icon:'img/update.png'}).show();
	   Ext.getCmp("bzFormPanel").loadRecord(record);
    },
    //用户单击事件
    onBzGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("BzUpdBtn").enable(true);
    	Ext.getCmp("BzDelBtn").enable(true);
    },
    
    //选择员工按钮
    onBzChoseUserBtnClick:function(button, e, eOpts) {
    	Ext.create("Bz.view.LoggingUserWindow",{"title":"双击选择员工","icon":"img/search.png"}).show();
    	
    },
    //选择产品按钮
    onBzChoseProductBtnClick:function(button, e, eOpts) {
    	Ext.create("Bz.view.ProductWindow",{"title":"双击选择产品","icon":"img/search.png"}).show();
    },
    //选择人员-刷新按钮
    onLoggingUserRefreshBtnClick:function(button, e, eOpts) {
    	this.reloadOrgGridPanelStore({'orgUuid':null},"-员工信息列表");
    },
   //选择人员-关闭按钮
    onLoggingUserCloseBtnClick:function(button, e, eOpts) {
    	Ext.getCmp("LoggingUserWindow").close();
    },
   //选择人员-保存按钮
    onLoggingUserSureBtnClick:function(button, e, eOpts) {
    	var gridPanel=Ext.getCmp("LoggingUserGridPanel");
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
        Ext.getCmp("loggingUserNumber").setValue(data.userNumber);
        Ext.getCmp("loggingUserNumber_hidden").setValue(data.userNumber);
        
        Ext.getCmp("loggingUserUuid").setValue(data.userUuid);//用户uuid
		Ext.getCmp("loggingUserSex").setValue(data.userGender);//用户性别
		Ext.getCmp("loggingUserName").setValue(data.userName);//用户名称
        Ext.getCmp("LoggingUserWindow").close();
    },
    //定时器,解决单击事件双击事件问题
    task:new Ext.util.DelayedTask(),
    //组织机构单击事件
    onOrgTreePanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	this.reloadOrgGridPanelStore({'orgUuid':record.data.id},record.data.text+"-员工信息列表");
    },
    //刷新人员数据
    reloadOrgGridPanelStore:function(data,text){
    	var gridPanel=Ext.getCmp("LoggingUserGridPanel");
    	gridPanel.store.setProxy({
	 		url:'user/getUserPage.do',
            type: 'ajax',
            extraParams:data,
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
    //人员表单单击事件
    onLoggingUserGridPanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("LoggingUserSureBtn").setDisabled(false);//保存按钮
    },
    //人员表单双击事件
    onLoggingUserGridPanelDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	 var data = record.data;
    	 Ext.getCmp("loggingUserNumber").setValue(data.userNumber);
         Ext.getCmp("loggingUserNumber_hidden").setValue(data.userNumber);
         
         Ext.getCmp("loggingUserUuid").setValue(data.userUuid);//用户uuid
 		 Ext.getCmp("loggingUserSex").setValue(data.userGender);//用户性别
 		 Ext.getCmp("loggingUserName").setValue(data.userName);//用户名称
         Ext.getCmp("LoggingUserWindow").close();
    },
    //选择产品-刷新按钮
    onLoggingProductRefreshBtnClick:function(button, e, eOpts){
    	Ext.getCmp("product_gridpanel").store.reload();
    },
    //选择产品-关闭按钮
    onLoggingProductCloseBtnClick:function(button, e, eOpts){
    	Ext.getCmp("ProductWindow").close();
    },
    //选择产品-保存按钮
    onLoggingProductSureBtnBtnClick:function(button, e, eOpts){
    	var gridPanel=Ext.getCmp("product_gridpanel");
    	var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
        	 Ext.Msg.show({
      				title : '提示信息',
      				msg :'请选择一条产品信息',
      				buttons : Ext.Msg.OK,
      				icon : Ext.MessageBox.WARNING
      			});
             return;
         }
        var data = selectionModel.getSelection()[0].data;
        Ext.getCmp("loggingProductNum").setValue(data.productNum);
        //设置一些参数。
		this.setProductParam(data);
		Ext.getCmp("ProductWindow").close();
    },
    //选择产品-显示查询表单按钮
    onLoggingProductShowSearchBtnClick:function(button, e, eOpts){
    	var panel = Ext.getCmp("LoggingProductSearchForm");
        if(panel.isHidden()){
            panel.show();
        }else{
            panel.hide();
        }
    },
    //查询按钮
    onLoggingProductSearchBtnClick: function(button, e, eOpts) {
    	var searchForm = Ext.getCmp("LoggingProductSearchForm");
    	this.reloadProductGridPanelStore(searchForm.getValues(),'高级查询-工作记录');
    },
    //重置按钮
    onLoggingProductResetBtnClick: function(button, e, eOpts) {
    	Ext.getCmp("productName").setValue("");
    	Ext.getCmp("productNum").setValue("");
   	    this.reloadProductGridPanelStore(null,'工作记录');
    },
    //刷新数据
    reloadProductGridPanelStore:function(data,text){
    	var gridPanel=Ext.getCmp("product_gridpanel");
    	gridPanel.store.setProxy({
	 		url:'product/getProductPage.do',
            type: 'ajax',
            extraParams:data,
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
    //产品表单双击事件
    onLoggingProductGridPanelDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	 var data = record.data;
         Ext.getCmp("loggingProductNum").setValue(data.productNum);
         //设置一些参数。
 		 this.setProductParam(data);
 		 Ext.getCmp("ProductWindow").close();
    },
    //导出按钮点击事件
    onLoggingExportBtnClick: function(button, e, eOpts) {
    	Ext.Msg.wait('正在处理数据，请稍候...','提示');
    	var panel = Ext.getCmp("LoggingSearchForm");
        if(panel.isHidden()){
        	window.location.href=extBasePath+"logging/getDownLoggingList.do";
        }else{
        	var param = panel.getValues(true);
        	window.location.href=extBasePath+"logging/getDownLoggingList.do?"+param;  
        }
        Ext.Msg.hide();
        
    },
    init: function(application) {
        this.control({
            "button[id=BzReloadBtn]": {
                click: this.onBzReloadBtnClick
            },
            "button[id=BzAddBtn]": {
                click: this.onBzAddBtnClick
            },
            "button[id=BzUpdBtn]": {
                click: this.onBzUpdBtnClick
            },
            "button[id=BzDelBtn]": {
                click: this.onBzDelBtnClick
            },
            "button[id=BzExportBtn]": {
                click: this.onBzExportBtnClick
            },
            "button[id=BzShowSearchFormBtn]": {
                click: this.onBzShowSearchFormBtnClick
            },
            "button[id=BzSaveBtn]": {
                click: this.onBzSaveBtnClick
            },
            "button[id=BzCloseBtn]": {
                click: this.onBzCloseBtnClick
            },
            'textfield[id=loggingUserNumber]':{
           	 	blur: this.onLoggingUserNumberBlur
            },
            'textfield[id=loggingProductNum]':{
	           	 blur: this.onLoggingProductNumBlur
	        },
	        'textfield[id=loggingNumber]':{
            	 blur: this.onLoggingNumberBlur
            },
            'textfield[id=loggingErrNum]':{
           	     blur: this.onLoggingErrNumBlur
            },
            'textfield[id=loggingErrRatio]':{
           	     blur: this.onLoggingErrRatioBlur
            },
            "button[id=BzSearchBtn]": {
                click: this.onBzSearchBtnClick
            },
            "button[id=BzResetBtn]": {
                click: this.onBzResetBtnClick
            },
            "#BzGridPanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
				this.task.delay(200,this.onBzGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onBzGridPanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
	        },
            "button[id=BzChoseUserBtn]": {
                click: this.onBzChoseUserBtnClick
            },
            "button[id=LoggingUserRefreshBtn]":{
            	click:this.onLoggingUserRefreshBtnClick
            },
            "button[id=LoggingUserSureBtn]":{
            	click:this.onLoggingUserSureBtnClick
            },
            "button[id=LoggingUserCloseBtn]":{
            	click:this.onLoggingUserCloseBtnClick
            },
            "button[id=BzChoseProductBtn]": {
                click: this.onBzChoseProductBtnClick
            },
            "#OrgTreePanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
					this.task.delay(200,this.onOrgTreePanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "#LoggingUserGridPanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
					this.task.delay(200,this.onLoggingUserGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onLoggingUserGridPanelDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            },
            "button[id=LoggingProductRefreshBtn]":{
            	click:this.onLoggingProductRefreshBtnClick
            },
            "button[id=LoggingProductSureBtn]":{
            	click:this.onLoggingProductSureBtnBtnClick
            },
            "button[id=LoggingProductCloseBtn]":{
            	click:this.onLoggingProductCloseBtnClick
            },
            "button[id=LoggingProductShowSearchBtn]":{
            	click:this.onLoggingProductShowSearchBtnClick
            },
            "button[id=LoggingProductSearchBtn]":{
            	click:this.onLoggingProductSearchBtnClick
            },
            "button[id=LoggingProductResetBtn]":{
            	click:this.onLoggingProductResetBtnClick
            },
            "#product_gridpanel":{
            /*	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
					this.task.delay(200,this.onLoggingProductGridPanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},*/
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onLoggingProductGridPanelDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
            }
            
        });
    }

});
