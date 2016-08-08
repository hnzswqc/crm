/*
 * File: logging/controller/LoggingController.js
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

Ext.define('Logging.controller.LoggingController', {
    extend: 'Ext.app.Controller',

    //刷新按钮
    onLoggingRefreshBtnClick: function(button, e, eOpts) {
        Ext.getCmp("logging_gridpanel").getStore().reload();
    },
    
    //添加按钮
    onLoggingAddBtnClick: function(button, e, eOpts) {
        Ext.create("Logging.view.LoggingWindow",{title:'添加日常工作记录',icon:'img/add.png'}).show();
    },

    //关闭按钮
    onLoggingCloseBtnClick: function(button, e, eOpts) {
        Ext.getCmp("LoggingWindow").close();
    },
    
    //显示查询表单按钮
    onLoggingShowSearchBtnClick: function(button, e, eOpts) {
    	var panel = Ext.getCmp("LoggingSearchForm");
        if(panel.isHidden()){
            panel.show();
        }else{
            panel.hide();
        }
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
     					this.reloadLoggingTypeState(result.data);
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
    //根据选择的人员获取选择员工的角色信息。
    reloadLoggingTypeState:function(data){
		Ext.getCmp("loggingProductNum").setDisabled(true);
		Ext.getCmp("LoggingSearchProductNumBtn").setDisabled(true);
		Ext.getCmp("loggingPrice").setDisabled(true);
 		Ext.getCmp("loggingWeight").setDisabled(true);
 		Ext.getCmp("loggingProductNum").setDisabled(true);
 		Ext.getCmp("loggingNumber").setDisabled(true);
 		Ext.getCmp("loggingProductNum_hidden").setValue("");
 		Ext.getCmp("loggingWages").setDisabled(true);
 		Ext.getCmp("loggingWages").setValue("");
 		Ext.getCmp("loggingAllWeight").setDisabled(true);
 		Ext.getCmp("loggingAllWeight").setValue("");
    	if(null==data||null==data.userUuid||""==data.userUuid){
    		Ext.getCmp("loggingType").setDisabled(true);
    		
     		Ext.getCmp("loggingUserUuid").setValue("");//用户uuid
    		Ext.getCmp("loggingUserSex").setValue("");//用户性别
    		Ext.getCmp("loggingUserName").setValue("");//用户名称
    		return;
    	}
    	//隐藏按钮
    	Ext.getCmp("loggingType").setDisabled(false);
    	
    	var combobox = Ext.getCmp("loggingType");
        combobox.clearValue();
    	combobox.store.setProxy({
	 		url:'user/getUserRoleListByUserUuid.do',
            type: 'ajax',
            extraParams:{"userUuid":data.userUuid},
            reader: {
	    		type: 'json',
	   	  	 	//数据格式为json   
	            root: 'data'
            }
    	});
    	combobox.store.load(true); 
    	Ext.getCmp("loggingUserUuid").setValue(data.userUuid);//用户uuid
		Ext.getCmp("loggingUserSex").setValue(data.userGender);//用户性别
		Ext.getCmp("loggingUserName").setValue(data.userName);//用户名称
			
    	Ext.getCmp("loggingPrice").setFieldLabel("单价");
    	Ext.getCmp("loggingPrice").setValue("");//单价
    	Ext.getCmp("loggingWeight").setValue("");//支重
    	Ext.getCmp("loggingProductNum").setValue("");//规格
    	Ext.getCmp("loggingProductNum_hidden").setValue("");
    	Ext.getCmp("loggingNumber").setValue("");//数量
    	
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
    	var loggingType = Ext.getCmp("loggingType").value;
    	if(null==loggingType||""==loggingType){
    		Ext.Msg.show({
				title : '提示信息',
				msg : "类别为空，请先设置员工类别信息",
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING
			 });
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
    		Ext.getCmp("loggingPrice").setDisabled(true);
    		Ext.getCmp("loggingPrice").setValue("");//单价
    		
    		Ext.getCmp("loggingWeight").setDisabled(true);
        	Ext.getCmp("loggingWeight").setValue("");//支重
    		return;
    	}
    	Ext.getCmp("loggingPrice").setDisabled(false);
    	Ext.getCmp("loggingWeight").setDisabled(false);
    	Ext.getCmp("loggingNumber").setDisabled(false);
    	Ext.getCmp("loggingWeight").setValue(data.productWeight);//支重
    	Ext.getCmp("loggingProductUuid").setValue(data.productUuid);//产品uuid
    	var loggingType = Ext.getCmp("loggingType").value;
    	if(null==loggingType||""==loggingType){
    		Ext.Msg.show({
				title : '提示信息',
				msg : "类别为空，请先设置员工类别信息",
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING
			 });
    		return;
    	}
    	if(loggingType==LOGGING_TYPE_ZG){//制管
    		Ext.getCmp("loggingPrice").setFieldLabel("制管单价");
    		Ext.getCmp("loggingPrice").setValue(data.productZgPrice);
    	}else if(loggingType==LOGGING_TYPE_BZ){//包装
    		Ext.getCmp("loggingPrice").setFieldLabel("包装单价");
    		Ext.getCmp("loggingPrice").setValue(data.productBzPrice);
    	}else if(loggingType==LOGGING_TYPE_PG){//抛光
    		Ext.getCmp("loggingPrice").setFieldLabel("抛光单价");
    		Ext.getCmp("loggingPrice").setValue(data.productPgPrice);
    	}else if(loggingType==LOGGING_TYPE_TG){//推管
    		//在下边设置
    	}else if(loggingType==LOGGING_TYPE_LEADER){//班长
    		Ext.getCmp("loggingPrice").setDisabled(true);
    	}
    },
    //通过数量计算工资和总重量
    onLoggingNumberBlur: function(component, e, eOpts) {
    	if(null==component.value||""==component.value){
    		Ext.getCmp("loggingWages").setValue(0);
    		Ext.getCmp("loggingAllWeight").setValue(0);
    		return;
    	}
    	var loggingType = Ext.getCmp("loggingType").value;
    	if(null==loggingType||""==loggingType){
    		Ext.Msg.show({
				title : '提示信息',
				msg : "类别为空，请先设置员工角色信息",
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING
			 });
    		return;
    	}
    	if(loggingType==LOGGING_TYPE_LEADER){//班长
    		//这里不做任何计算，直接从后台员工包装工资总额中取平均数作为班长的工资
    	}else if(loggingType==LOGGING_TYPE_PG){//抛光
    		//工资
        	var price = Ext.getCmp("loggingPrice").value;
        	var pgNum = Ext.getCmp("loggingPgNum").value;
        	if(null==price||""==price){
        		Ext.getCmp("loggingWages").setValue(0);
        	}else{
        		if(pgNum>1){
        			Ext.getCmp("loggingWages").setValue(price*component.value*pgNum*LOGGING_PG_NUM_WEIGHT);
        		}else{
        			Ext.getCmp("loggingWages").setValue(price*component.value);
        		}
        	}
        	//重量
        	var loggingWeight = Ext.getCmp("loggingWeight").value;
        	if(null==loggingWeight||""==loggingWeight){
        		Ext.getCmp("loggingAllWeight").setValue(0);
        	}else{
        		Ext.getCmp("loggingAllWeight").setValue(loggingWeight*component.value);
        	}
        	Ext.getCmp("loggingWages").setDisabled(false);//工资
        	Ext.getCmp("loggingAllWeight").setDisabled(false);//重量
    	}else{//制管、包装
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
        	Ext.getCmp("loggingWages").setDisabled(false);//工资
        	if(loggingType!=LOGGING_TYPE_TG){
        		Ext.getCmp("loggingAllWeight").setDisabled(false);//重量
        	}else{
        		Ext.getCmp("loggingAllWeight").setDisabled(true);//重量
        	}
    	}
    },
    //通过抛光次数计算工资
    onLoggingPgNumBlur: function(component, e, eOpts) {
    	var loggingType = Ext.getCmp("loggingType").value;
    	if(loggingType==LOGGING_TYPE_PG){
    		//工资
        	var price = Ext.getCmp("loggingPrice").value;
        	var loggingNumber = Ext.getCmp("loggingNumber").value;
        	if(null==price||""==price){
        		Ext.getCmp("loggingWages").setValue(0);
        	}else{
        		if(component.value>1){
        			Ext.getCmp("loggingWages").setValue(price*component.value*LOGGING_PG_NUM_WEIGHT*loggingNumber);
        		}else{
        			Ext.getCmp("loggingWages").setValue(price*component.value*loggingNumber);
        		}
        	}
        	Ext.getCmp("loggingWages").setDisabled(false);//工资
        	Ext.getCmp("loggingAllWeight").setDisabled(false);//重量
    	}
    },
    //类别选择事件
    onLoggingTypeChange:function(field,newValue,oldValue,eOpts){
    	if(newValue==LOGGING_TYPE_LEADER){//班长。自动计算该班下今天所有包装员工的平均工资作为当前班长的今天的工资
    		Ext.getCmp("loggingAllWeight").setDisabled(true);//总重
    		Ext.getCmp("loggingAllWeight").setValue(0);
    		Ext.getCmp("loggingProductNum").setDisabled(true);//规格码
    		Ext.getCmp("loggingPgNum").setDisabled(true);//抛光数量
    		Ext.getCmp("loggingWeight").setDisabled(true);//支重
    		Ext.getCmp("loggingWages").setDisabled(false);//工资
    		Ext.getCmp("loggingPrice").setDisabled(true);//单价
    		Ext.getCmp("loggingNumber").setDisabled(true);//数量
    		Ext.getCmp("LoggingSearchProductNumBtn").setDisabled(true);//数量
    		
    		//计算班长工资方法。获取当天所有女工的包装平均工资。
    		this.getLoggingTypeLeaderWages();
    		
    	}else if(newValue==LOGGING_TYPE_TG){//如果是推管的话，男的4.5女的3.5乘以吨数即可。
    		Ext.getCmp("loggingPrice").setFieldLabel("推管单价");
    		var loggingUserSex = Ext.getCmp("loggingUserSex").value;
    		if(loggingUserSex==DIC_GENDER_FEMALE){//女
    			Ext.getCmp("loggingPrice").setValue(LOGGING_TG_WUMEN_PRICE);
    		}else{//男或未知
    			Ext.getCmp("loggingPrice").setValue(LOGGING_TG_MAN_PRICE);
    		}
    		Ext.getCmp("loggingNumber").setFieldLabel("推管重量(吨)");
    		
    		Ext.getCmp("loggingPrice").setDisabled(false);//单价
    		Ext.getCmp("loggingNumber").setFieldLabel("推管重量(吨)");
    		Ext.getCmp("loggingNumber").setDisabled(false);//推管重量
    		Ext.getCmp("loggingWages").setDisabled(false);//工资
    		Ext.getCmp("loggingAllWeight").setDisabled(true);//总重
    		Ext.getCmp("loggingAllWeight").setValue(0);
    		Ext.getCmp("loggingProductNum").setDisabled(true);//规格码
    		Ext.getCmp("loggingPgNum").setDisabled(true);//抛光数量
    		Ext.getCmp("loggingWeight").setDisabled(true);//支重
    		
    	}else if(newValue==LOGGING_TYPE_PG){
    		Ext.getCmp("loggingProductNum").setDisabled(false);
        	Ext.getCmp("LoggingSearchProductNumBtn").setDisabled(false);
    		Ext.getCmp("loggingPgNum").setDisabled(false);//抛光数量
    	}else{
    		Ext.getCmp("loggingProductNum").setDisabled(false);
        	Ext.getCmp("LoggingSearchProductNumBtn").setDisabled(false);
    		Ext.getCmp("loggingNumber").setFieldLabel("数量");
    	}
    },
    //计算当天班长工资
    getLoggingTypeLeaderWages:function(){
    	var loggingDate = Ext.getCmp("loggingDate").value;
    	var loggingUserNumber = Ext.getCmp("loggingUserNumber").value;
    	if(null==loggingUserNumber||loggingUserNumber==""){
    		Ext.Msg.show({
				title : '提示信息',
				msg : "请先输入员工号",
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING
			 });
    		return;
    	}
    	if(null==loggingDate||loggingDate==""){
    		Ext.Msg.show({
				title : '提示信息',
				msg : "请先选择记录日期",
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING
			 });
    		return;
    	}
    	Ext.Msg.wait('正在计算当日工资，请稍后...','提示');
        Ext.Ajax.request({
    		url:"logging/getLeaderWages.do",
    		method:"POST",
    		params:{"loggingDate":loggingDate,"loggingUserNumber":loggingUserNumber},
    		success:function(responst){
        	 	Ext.Msg.hide();
    			var result = JSON.parse(responst.responseText); 
    			if(result.success){
    				Ext.getCmp("loggingWages").setValue(result.data.loggingWages);
    			}else{
    				 Ext.Msg.show({
           				title : '提示信息',
           				msg :'计算失败!请手动填写',
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
    //保存按钮点击事件
    onLoggingSaveBtnClick: function(button, e, eOpts) {
   	 		var formPanel = Ext.getCmp("LoggingForm");
	     	 if(!formPanel.isDirty()){
	     		 Ext.getCmp("LoggingWindow").close();
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
	  		            		 Ext.Msg.show({title : '提示信息',msg :'操作成功!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
	  		            		 Ext.getCmp("LoggingWindow").close();
	  		            		 this.onLoggingRefreshBtnClick();
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
    //修改按钮点击事件
    onLoggingUpdBtnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("logging_gridpanel");
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
        Ext.create("Logging.view.LoggingWindow",{title:'修改日常工作记录',icon:'img/update.png'}).show();
   	    Ext.getCmp("LoggingForm").loadRecord(selectionModel.getSelection()[0]);
   	    this.setLoggingParamProperty(selectionModel.getSelection()[0].data);
    },
    //删除按钮点击事件
    onLoggingDelBtnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("logging_gridpanel");
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
            				 Ext.getCmp("logging_gridpanel").store.load();
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
    //修改的时候，根据工作性质去显示或者隐藏不必要的字段
    setLoggingParamProperty:function(data){
    	var loggingType = data.loggingType;
    	Ext.getCmp("loggingDate").setDisabled(true);//日期
    	
    	Ext.getCmp("loggingUserNumber").setDisabled(true);//员工号
    	Ext.getCmp("LoggingSearchUserNumberBtn").setDisabled(true);//选择员工号button
    	
    	Ext.getCmp("loggingType").setDisabled(true);//员工工作类别
    	
    	Ext.getCmp("loggingProductNum").setDisabled(true);
    	Ext.getCmp("LoggingSearchProductNumBtn").setDisabled(true);//选择产品button
    	
    	Ext.getCmp("loggingPrice").setDisabled(false);//单价
    	Ext.getCmp("loggingWeight").setDisabled(false);//支重
    	Ext.getCmp("loggingNumber").setDisabled(false);//数量
    	Ext.getCmp("loggingPgNum").setDisabled(false);//次数
    	Ext.getCmp("loggingWages").setDisabled(false);//工资
    	Ext.getCmp("loggingAllWeight").setDisabled(false);//总重
    	
    	
    	if(loggingType == LOGGING_TYPE_LEADER){//班长
    		Ext.getCmp("loggingPrice").setDisabled(true);//单价
        	Ext.getCmp("loggingWeight").setDisabled(true);//支重
        	Ext.getCmp("loggingNumber").setDisabled(true);//数量
        	Ext.getCmp("loggingPgNum").setDisabled(true);//次数
        	Ext.getCmp("loggingAllWeight").setDisabled(true);//总重
        	
    	}
    	if(loggingType == LOGGING_TYPE_TG){//推管
    		Ext.getCmp("loggingPrice").setFieldLabel("推管单价");
    		var loggingUserSex = Ext.getCmp("loggingUserSex").value;
    		if(loggingUserSex==DIC_GENDER_FEMALE){//女
    			Ext.getCmp("loggingPrice").setValue(LOGGING_TG_WUMEN_PRICE);
    		}else{//男或未知
    			Ext.getCmp("loggingPrice").setValue(LOGGING_TG_MAN_PRICE);
    		}
    		Ext.getCmp("loggingNumber").setFieldLabel("推管重量(吨)");
    		
        	Ext.getCmp("loggingWeight").setDisabled(true);//支重
        	Ext.getCmp("loggingPgNum").setDisabled(true);//次数
        	Ext.getCmp("loggingAllWeight").setDisabled(true);//总重
    	}
    },
    //查询按钮
    onLoggingSearchBtnClick: function(button, e, eOpts) {
    	var searchForm = Ext.getCmp("LoggingSearchForm");
    	this.reloadLoggingGridPanelStore(searchForm.getValues(),'高级查询-工作记录');
    },
    //重置按钮
    onLoggingResetBtnClick: function(button, e, eOpts) {
    	Ext.getCmp("loggingUserNumber_search").setValue("");
    	Ext.getCmp("loggingType_search").setValue("");
    	Ext.getCmp("startDate").setValue("");
    	Ext.getCmp("endDate").setValue("");
   	    this.reloadLoggingGridPanelStore(null,'工作记录');
    },
    //刷新数据
    reloadLoggingGridPanelStore:function(data,text){
    	var gridPanel=Ext.getCmp("logging_gridpanel");
    	gridPanel.store.setProxy({
	 		url:'logging/getLoggingPage.do',
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
    onLoggingGridpanelCellDblClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	   Ext.create("Logging.view.LoggingWindow",{title:'修改日常工作记录',icon:'img/update.png'}).show();
	   Ext.getCmp("LoggingForm").loadRecord(record);
	   this.setLoggingParamProperty(record.data);
    },
    //用户单击事件
    onLoggingGridpanelCellClick:function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	Ext.getCmp("LoggingUpdBtn").enable(true);
    	Ext.getCmp("LoggingDelBtn").enable(true);
    },
    //选择员工按钮
    onLoggingSearchUserNumberBtnClick:function(button, e, eOpts) {
    	Ext.create("Logging.view.LoggingUserWindow",{"title":"双击选择员工","icon":"img/search.png"}).show();
    	
    },
    //选择产品按钮
    onLoggingSearchProductNumBtnClick:function(button, e, eOpts) {
    	Ext.create("Logging.view.ProductWindow",{"title":"双击选择产品","icon":"img/search.png"}).show();
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
        //调用员工号输入框失去焦点事件
        this.reloadLoggingTypeState(data);
        Ext.getCmp("LoggingUserWindow").close();
    },
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
         //调用员工号输入框失去焦点事件
         this.reloadLoggingTypeState(data);
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
            "button[id=LoggingRefreshBtn]": {
                click: this.onLoggingRefreshBtnClick
            },
            "button[id=LoggingAddBtn]": {
                click: this.onLoggingAddBtnClick
            },
            "button[id=LoggingSaveBtn]": {
                click: this.onLoggingSaveBtnClick
            },
            "button[id=LoggingCloseBtn]": {
                click: this.onLoggingCloseBtnClick
            },
            "button[id=LoggingShowSearchBtn]": {
                click: this.onLoggingShowSearchBtnClick
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
            'textfield[id=loggingPgNum]':{
            	 blur: this.onLoggingPgNumBlur
            },
            '#loggingType':{
            	  change: this.onLoggingTypeChange
            },
            'button[id=LoggingSaveBtn]':{
            	click:this.onLoggingSaveBtnClick
            },
            'button[id=LoggingUpdBtn]':{
            	click:this.onLoggingUpdBtnClick
            },
            'button[id=LoggingDelBtn]':{
            	click:this.onLoggingDelBtnClick
            },
            "button[id=LoggingSearchBtn]":{
            	click:this.onLoggingSearchBtnClick
            },
            "button[id=LoggingResetBtn]":{
            	click:this.onLoggingResetBtnClick
            }, 
            "#logging_gridpanel":{
            	cellclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
				this.task.delay(200,this.onLoggingGridpanelCellClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		},
	    		celldblclick: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts){
	    			this.task.delay(200,this.onLoggingGridpanelCellDblClick,this,new Array(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts));
	    		}
	        },
	        "button[id=LoggingSearchUserNumberBtn]":{
            	click:this.onLoggingSearchUserNumberBtnClick
            },
            "button[id=LoggingSearchProductNumBtn]":{
            	click:this.onLoggingSearchProductNumBtnClick
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
            },
            "button[id=LoggingExportBtn]":{
            	click:this.onLoggingExportBtnClick
            }
            
            
            
            
        });
    }

});
