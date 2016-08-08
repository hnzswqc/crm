/*
 * File: kh/view/BeatchAddWindow.js
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

Ext.define('WorkCheck.view.BeatchAddWindow', {
    extend: 'Ext.window.Window',

    height: 430,
    id: 'BeatchAddWindow',
    width: 760,
   
    title: '月度考核',
    modal:true,
    //maximizable: true,
    resizable: false,
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'container',
                    height: 34,
                    layout: {
                        align: 'middle',
                        pack: 'center',
                        type: 'hbox'
                    },
                    items: [
                        {
                            xtype: 'combobox',
                            width: 162,
                            fieldLabel: '考勤年度',
                            labelAlign: 'right',
                            id:'wcYear',
                            blankText: '不能为空',
                            allowBlank: false,
                            editable: false,
                            value:new Date().getFullYear(),
                            labelWidth: 60,
                            store:Ext.create("Ext.data.Store",{
                             	 fields: ['id', 'text'],
                             	 autoLoad:true,
                             	 listeners:{
	                     	         load : function(store, records, options ){ 
	                            	  for (i=0;i<=10;i++){
		                    			   var year = new Date().getFullYear();
		                    			   var n = year -i;
		                    			   store.insert(i,{ "id": n, "text": n});  
		                    		   }
		    			            }  
                            	}
                            }),
                            displayField: 'text',
                         	valueField: 'id'
                        },
                        {
                            xtype: 'combobox',
                            width: 162,
                            fieldLabel: '考勤月份',
                            labelAlign: 'right',
                            id:"wcMonth",
                            labelWidth: 60,
                            blankText: '不能为空',
                            allowBlank: false,
                            editable: false,
                            store:Ext.create("Ext.data.Store",{
                              	 fields: ['id', 'text'],
                              	 autoLoad:true,
                              	 data : [
                              	         {"id":1, "text":"1月"},
                              	         {"id":2, "text":"2月"},
                              	         {"id":3, "text":"3月"},
                              	         {"id":4, "text":"4月"},
                              	         {"id":5, "text":"5月"},
                              	         {"id":6, "text":"6月"},
                              	         {"id":7, "text":"7月"},
                              	         {"id":8, "text":"8月"},
                              	         {"id":9, "text":"9月"},
                              	         {"id":10, "text":"10月"},
                              	         {"id":11, "text":"11月"},
                              	         {"id":12, "text":"12月"}
                              	     ]
                          }),
                          displayField: 'text',
                          valueField: 'id',
                          listeners:{
						      	beforerender:function(combol){
                                	var m = new Date().getMonth();
                        			if(m==0){
                        				Ext.getCmp("wcYear").setValue(new Date().getFullYear()-1);
                        				m=12;
                        			}else{
                        				m=m+1                       			
                        			}
                        			combol.setValue(m);
						      	}
							}
                        }
                    ]
                },
                {
                    xtype: 'container',
                    height: 29,
                    layout: {
                        align: 'middle',
                        pack: 'end',
                        type: 'hbox'
                    },
                    items: [
                            {
                                xtype: 'datefield',
                                fieldLabel: '考勤时间',
                                id:'wcStartDate',
                                allowBlank: false,
                                format:'Y-m-d',
                                labelWidth:60,
                                width:160,
                                blankText: '不能为空'
                            },
                            {
                                xtype: 'datefield',
                                fieldLabel: '至',
                                id:'wcEndDate',
                                allowBlank: false,
                                format:'Y-m-d',
                                width:120,
                                labelWidth:20,
                                blankText: '不能为空'
                            },
                            {
                                xtype: 'numberfield',
                                fieldLabel: '满勤天数',
                                id:'wcAllDay',
                                width:120,
                                //readOnly:true,
                                //fieldStyle:'background:#f2f2f2',
                                labelWidth:60,
                            }
                    ]
                },
                {
                    xtype: 'form',
                    frame: true,
                    height: 307,
                    margin: 2,
                    autoScroll: true,
                    layout: {
                        columns: 8,
                        type: 'table'
                    },
                    bodyPadding: 10,
                    header: false,
                    title: '考勤人员表单',
                    id:"BeatchAddForm",
                    items: [
                        {
                            xtype: 'label',
                            margin: '0 0 0 35',
                            style: 'font-size:12px;font-weight:bold;',
                            text: '工号'
                        },
                        {
                            xtype: 'label',
                            margin: '0 0 0 35',
                            style: 'font-size:12px;font-weight:bold;',
                            text: '姓名'
                        },
                        {
                            xtype: 'label',
                            margin: '0 0 0 25',
                            style: 'font-size:12px;font-weight:bold;',
                            width: 86,
                            text: '所属部门'
                        },
                        {
                            xtype: 'label',
                            margin: '0 0 0 35',
                            style: 'font-size:12px;font-weight:bold;',
                            text: '职务'
                        },
                        {
                            xtype: 'label',
                            margin: '0 0 0 25',
                            style: 'font-size:12px;font-weight:bold;',
                            text: '考勤天数'
                        },
                        {
                            xtype: 'label',
                            style: 'font-size:12px;font-weight:bold;',
                            margin: '0 0 0 25',
                            text: '加班天数'
                        },
                        {
                            xtype: 'label',
                            style: 'font-size:12px;font-weight:bold;',
                            margin: '0 0 0 25',
                            text: '加班小时'
                        },
                        {
                            xtype: 'label',
                            margin: '0 0 0 10',
                            style: 'font-size:12px;font-weight:bold;',
                            text: '操作'
                        }
                    ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    items: [
                        '->',
                        {
                            xtype: 'button',
                            id: 'AddRowBtn',
                            icon: 'img/add.png',
                            text: '添加一行'
                        },
                        {
                            xtype: 'button',
                            id: 'AutoAddBtn',
                            icon: 'img/add.png',
                            text: '自动加载人员'
                        },
                        {
                            xtype: 'button',
                            id: 'SaveBtn',
                            icon: 'img/save.png',
                            text: '保存'
                        },
                        {
                            xtype: 'button',
                            id: 'CloseWindowBtn',
                            icon: 'img/close.png',
                            text: '关闭'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});