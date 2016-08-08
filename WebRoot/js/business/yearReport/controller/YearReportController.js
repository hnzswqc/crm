/*
 * File: yearReport/controller/YearReportController.js
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

Ext.define('YearReport.controller.YearReportController', {
    extend: 'Ext.app.Controller',

    //刷新按钮
    onYearReportRefreshBtnClick: function(button, e, eOpts) {
		Ext.getCmp("YearReportGridPanel").store.reload();	
    },
    //明细报表按钮
    onYearReportDetailBtnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("YearReportGridPanel");
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
       var data =  selectionModel.getSelection()[0].data
       var params = "";
       var panel = Ext.getCmp("YearReportSearchFormPanel");
       if(panel.isHidden()){
    	   params = {"loggingUserNumber":data.loggingUserNumber};
       }else{
    	   var startDate = Ext.getCmp("startDate").value;
    	   if(undefined!=startDate){
    		   params = {"loggingUserNumber":data.loggingUserNumber,"startDate":startDate};
    	   }
       }
       
       Ext.create("YearReport.view.MonthReportWindow",{title:data.loggingUserName+(undefined!=startDate?startDate:'本')+'年工资明细查询',icon:'img/detail.png'}).show();
       var gridPanel=Ext.getCmp("YearReportDetailGridPanel");
	   	gridPanel.store.setProxy({
		 	   url:'logging/getYearDetailReport.do',
	           type: 'ajax',
	           extraParams:params,
	           groupField: 'loggingUserName',
	           reader: {
	   			type: 'json',
		   	  	 	//数据格式为json   
		            root: 'data'
	           }
	   	});
	   	gridPanel.store.load(); 
       
    },
    //导出按钮
    onYearReportExportBtnClick: function(button, e, eOpts) {
    	Ext.Msg.wait('正在处理数据，请稍候...','提示');
    	var panel = Ext.getCmp("YearReportSearchFormPanel");
        if(panel.isHidden()){
        	window.location.href=extBasePath+"logging/getDownYearReport.do";
        	 Ext.Msg.hide();
        }else{
        	 var searchForm = Ext.getCmp("YearReportSearchFormPanel");
        	 if(!searchForm.isDirty()){
        		 Ext.Msg.hide();
        		 Ext.Msg.show({title : '提示信息',msg :'年度不能为空!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
        		 return;
         	 }
        	var startDate = Ext.getCmp("startDate").value;
        	var loggingUserNumber = Ext.getCmp("loggingUserNumber").value;
        	
        	window.location.href=extBasePath+"logging/getDownYearReport.do?startDate="+startDate+"&loggingUserNumber="+(null==loggingUserNumber?"":loggingUserNumber);
        	Ext.Msg.hide();
        }
    },
    //高级查询按钮
    onYearReportShowSearchBtnClick: function(button, e, eOpts) {
    	var panel = Ext.getCmp("YearReportSearchFormPanel");
        if(panel.isHidden()){
            panel.show();
        }else{
            panel.hide();
        }
    },
   //查询按钮
    onYearReportSearchBtnClick: function(button, e, eOpts) {
    	var searchForm = Ext.getCmp("YearReportSearchFormPanel");
	   	 if(!searchForm.isDirty()){
	   		 Ext.Msg.show({title : '提示信息',msg :'年度不能为空!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
	   		 return;
	    	 }
	   	var startDate = Ext.getCmp("startDate").value;
	   	this.reloadMonthReportGridPanelStore(searchForm.getValues(),startDate+'年工资查询');
    },
   //重置按钮
    onYearReportResetBtnClick: function(button, e, eOpts) {
    	Ext.getCmp("startDate").setValue("");
    	Ext.getCmp("loggingUserNumber").setValue("");
    	this.reloadMonthReportGridPanelStore(null,'本年度工资查询');
    },
  //重新加载数据
    reloadMonthReportGridPanelStore:function(data,title){
    	var gridPanel=Ext.getCmp("YearReportGridPanel");
    	gridPanel.store.setProxy({
	 		url:'logging/getYearDetailReport.do',
            type: 'ajax',
            extraParams:data,
            groupField: 'orgName',
            reader: {
    			type: 'json',
	   	  	 	//数据格式为json   
	            root: 'data'
            }
    	});
    	gridPanel.store.load(); 
    	gridPanel.setTitle(title);
    },
    //明细刷新按钮
    onYearReportDetailReloadBtnClick: function(button, e, eOpts) {
    	Ext.getCmp("YearReportDetailGridPanel").store.reload();
    },
    //明细导出按钮
    onYearReportDetailExportBtnClick: function(button, e, eOpts) {
    	var gridPanel = Ext.getCmp("YearReportGridPanel");
        var selectionModel = gridPanel.getSelectionModel();
        if(selectionModel.getCount()<=0){
       	 Ext.Msg.show({
     				title : '提示信息',
     				msg :'请重新打开窗口进行导出',
     				buttons : Ext.Msg.OK,
     				icon : Ext.MessageBox.WARNING
     			});
            return;
        }
        var data =  selectionModel.getSelection()[0].data
    	var panel = Ext.getCmp("YearReportSearchFormPanel");
        if(panel.isHidden()){
        	window.location.href=extBasePath+"logging/getDownYearDetailReport.do?loggingUserNumber="+data.loggingUserNumber;
        }else{
        	 var searchForm = Ext.getCmp("YearReportSearchFormPanel");
        	 if(!searchForm.isDirty()){
        		 Ext.Msg.show({title : '提示信息',msg :'年度不能为空!',buttons : Ext.Msg.OK,icon : Ext.MessageBox.INFO});
        		 return;
         	 }
        	var startDate = Ext.getCmp("startDate").value;
        	window.location.href=extBasePath+"logging/getDownYearDetailReport.do?startDate="+startDate+"&loggingUserNumber="+(null==data.loggingUserNumber?"":data.loggingUserNumber);
        	Ext.Msg.hide();
        }
    },
    //明细关闭按钮
    onYearReportDetailCloseBtnClick: function(button, e, eOpts) {
    	Ext.getCmp("MonthReportWindow").close();
    },
    init: function(application) {
        this.control({
            "button[id=YearReportRefreshBtn]": {
                click: this.onYearReportRefreshBtnClick
            },
            "button[id=YearReportDetailBtn]": {
                click: this.onYearReportDetailBtnClick
            },
            "button[id=YearReportExportBtn]": {
                click: this.onYearReportExportBtnClick
            },
            "button[id=YearReportShowSearchBtn]": {
                click: this.onYearReportShowSearchBtnClick
            },
            "button[id=YearReportSearchBtn]": {
                click: this.onYearReportSearchBtnClick
            },
            "button[id=YearReportResetBtn]": {
                click: this.onYearReportResetBtnClick
            },
            "button[id=YearReportDetailReloadBtn]": {
                click: this.onYearReportDetailReloadBtnClick
            },
            "button[id=YearReportDetailExportBtn]": {
                click: this.onYearReportDetailExportBtnClick
            },
            "button[id=YearReportDetailCloseBtn]": {
                click: this.onYearReportDetailCloseBtnClick
            }
            
            
        });
    }

});