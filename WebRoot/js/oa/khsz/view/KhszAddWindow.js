/*
 * File: khsz/view/KhszAddWindow.js
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

Ext.define('Khsz.view.KhszAddWindow', {
    extend: 'Ext.window.Window',

    height: 390,
    id: 'KhszAddWindow',
    width: 347,
    resizable: false,
    layout: {
        type: 'border'
    },
    title: '满勤天数设置',
    modal: true,

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'container',
                    region: 'center',
                    margin: 2,
                    layout: {
                        type: 'border'
                    },
                    items: [
                        {
                            xtype: 'form',
                            region: 'center',
                            frame: true,
                            margin: '0 0 1 0',
                            layout: {
                                columns: 1,
                                type: 'table'
                            },
                            bodyPadding: 10,
                            header: false,
                            title: 'KqszForm',
                            id:"KqszForm",
                            trackResetOnLoad:true,
                            items: [
								{
								    xtype: 'hiddenfield',
								    name:"uuid",
								    fieldLabel: '主键'
								}, 
                                {
                                    xtype: 'numberfield',
                                    fieldLabel: '年度',
                                    name:"year",
                                    id:"year",
                                    blankText: '不能为空',
                                    allowBlank: false,
                                    value:new Date().getFullYear(),
                                    maxLength: 4,
                                    minLength: 4
                                },
                                {
                                    xtype: 'combobox',
                                    fieldLabel: '考勤月份',
                                    name:"month",
                                    id:"month",
                                    blankText: '不能为空',
                                    allowBlank: false,
                                    store:Ext.create("Ext.data.Store",{
                                      	 fields: ['id', 'text'],
                                      	 autoLoad:true,
                                      	 data : [
                                      	         {"id":"1", "text":"1月"},
                                      	         {"id":"2", "text":"2月"},
                                      	         {"id":"3", "text":"3月"},
                                      	         {"id":"4", "text":"4月"},
                                      	         {"id":"5", "text":"5月"},
                                      	         {"id":"6", "text":"6月"},
                                      	         {"id":"7", "text":"7月"},
                                      	         {"id":"8", "text":"8月"},
                                      	         {"id":"9", "text":"9月"},
                                      	         {"id":"10", "text":"10月"},
                                      	         {"id":"11", "text":"11月"},
                                      	         {"id":"12", "text":"12月"}
                                      	     ]
                                  }),
                                  displayField: 'text',
                                  valueField: 'id',
                                  listeners:{
								      	beforerender:function(combol){
		                                	var m = new Date().getMonth();
		                        			if(m==0){
		                        				Ext.getCmp("year").setValue(new Date().getFullYear()-1);
		                        				m=12;
		                        			}else{
		                        				m=m+1                       			}
		                        			combol.setValue(m+"");
								      	}
									}
                                },
                                {
                                    xtype: 'datefield',
                                    name:"kqStartTime",
                                    id:"kqStartTime",
                                    fieldLabel: '起始时间',
                                    format:'Y-m-d',
                                    //editable: false,
                                    blankText: '不能为空',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'datefield',
                                    name:"kqEndTime",
                                    id:"kqEndTime",
                                    fieldLabel: '结束时间',
                                    format:'Y-m-d',
                                    //editable: false,
                                    blankText: '不能为空',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'numberfield',
                                    name:"mqts",
                                    fieldLabel: '满勤天数',
                                    blankText: '不能为空',
                                    value:29,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textareafield',
                                    height: 142,
                                    width: 309,
                                    name:"note",
                                    fieldLabel: '备注说明'
                                }
                            ]
                        },
                        {
                            xtype: 'toolbar',
                            region: 'south',
                            margin: '1 0 0 0 ',
                            items: [
                                '->',
                                {
                                    xtype: 'button',
                                    icon: 'img/save.png',
                                    id:"SaveBtn",
                                    text: '保存'
                                },
                                {
                                    xtype: 'button',
                                    id: 'CloseBtn',
                                    icon: 'img/close.png',
                                    text: '关闭'
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});