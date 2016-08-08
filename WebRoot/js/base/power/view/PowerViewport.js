/*
 * File: power/view/PowerViewport.js
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

Ext.define('Power.view.PowerViewport', {
    extend: 'Ext.container.Viewport',

    border: 0,
    layout: {
        type: 'border'
    },
    id:'PowerViewport',
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'toolbar',
                    margin: '2',
                    region: 'north',
                    //height: 40,
                    items: [
                        {
                            xtype: 'button',
                            id: 'Power_refresh_btn',
                            icon: 'img/reload.png',
                            hidden:exists('Power_refresh_btn'),
                            text: '刷新'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('User_folding_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Power_add_btn',
                            icon: 'img/add.png',
                            hidden:exists('Power_add_btn'),
                            disabled:true,
                            text: '添加'
                        },
                        {
                        	xtype: 'tbseparator',
                        	 hidden:exists('Power_add_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Power_upd_btn',
                            icon: 'img/update.png',
                            disabled:true,
                            hidden:exists('Power_upd_btn'),
                            text: '修改'
                        },
                        {
                        	xtype: 'tbseparator',
                        	 hidden:exists('Power_upd_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Power_del_btn',
                            icon: 'img/del.png',
                            disabled:true,
                            hidden:exists('Power_del_btn'),
                            text: '删除'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Power_del_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Power_operate_btn',
                            icon: 'img/setting.png',
                            disabled:true,
                            hidden:exists('Power_operate_btn'),
                            text: '操作功能'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Power_operate_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Power_show_search_btn',
                            icon: 'img/search.png',
                            hidden:exists('Power_show_search_btn'),
                            text: '高级查询'
                        }
                    ]
                },
                {
                    xtype: 'treepanel',
                    margins: '',
                    region: 'west',
                    split: true,
                    id: 'PowerModelTreePanel',
                    margin: '0 0 2 2 ',
                    width: 200,
                    collapsed: false,
                    collapsible: true,
                    title: '模块信息',
                    store: 'PowerModelTreeStore',
                    rootVisible: false
                },
                {
                    xtype: 'container',
                    region: 'center',
                    layout: {
                        type: 'border'
                    },
                    items: [
                        {
                            xtype: 'gridpanel',
                            region: 'center',
                            id: 'PowerGridPanel',
                            margin: '0 2 2 0',
                            title: '功能链接',
                            store: 'PowerStore',
                            columns: [
                                {
                                    xtype: 'rownumberer',
                                    width: 41,
                                    text: '序号'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'powerName',
                                    text: '名称'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'powerUuid',
                                    text: 'id',
                                    hidden:true,
                                    hideable: false
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'num',
                                    text: 'num',
                                    hidden:true,
                                    hideable: false
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'powerUrl',
                                    text: 'URL链接'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'powerIcon',
                                    width:60,
                                    text: '图标',
                                    renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
	                                	if(null!=data&&""!=data){
	                            			return '<img src=\''+data+"\'' width=16 height=16 />"
	                            		}else{
	                            			return data;
	                            		}
        		                  	}
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'powerLogo',
                                    width:60,
                                    text: 'LOGO',
                                    renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
	                                	if(null!=data&&""!=data){
	                            			return '<img src=\''+data+"\'' width=16 height=16 />"
	                            		}else{
	                            			return data;
	                            		}
        		                  	}
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'powerModelName',
                                    text: '所属模块'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'powerParentName',
                                    text: '父级菜单',
                                    renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                                		if(null==data){
                                			return  '根目录';
                                		}
                                		return data; 
        		                  	}
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'powerState',
                                    text: '状态',
                                    width: 41,
                                    renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                                		return record.data.powerStateText; 
        		                  	}
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'powerNote',
                                    text: '备注信息'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'powerOrderby',
                                    width: 41,
                                    text: '排序号'
                                },
                                {
                                    xtype: 'datecolumn',
                                    dataIndex: 'createTime',
                                    text: '创建时间',
                                    format: 'Y-m-d H:i:s'
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    store:'PowerStore',
                                    displayInfo: true
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            region: 'north',
                            frame: true,
                            height: 80,
                            id: 'PowerSearchForm',
                            layout: {
                                type: 'column'
                            },
                            hidden:true,
                            bodyPadding: 10,
                            margin: '0 2 2 0',
                            title: '高级查询',
                            items: [
                                {
                                    xtype: 'textfield',
                                    anchor: '100%',
                                    fieldLabel: '名称',
                                    labelWidth: 60,
                                    name: 'powerName',
                                    id:'powerName'
                                },
                                {
                                    xtype: 'combobox',
                                    margin: '0 0 0 5',
                                    width: 120,
                                    fieldLabel: '状态',
                                    labelWidth: 60,
                                    name: 'powerState',
                                    id:'powerState',
                                    displayField: 'text',
                                    valueField: 'id',
                                    editable: false, //启用编辑，主要是为了清空当前的选择项
                                    store:'PowerStateStore',
                                    listeners:{
                                      	afterrender:function(combol){
                                      		//var firstValue =combol.store.proxy.data[0].id;
                                      		//combol.setValue(firstValue);  
                                      	}
                                	}
                                },
                                {
                                    xtype: 'button',
                                    margin: '0 0 0 5',
                                    icon: 'img/search.png',
                                    id:'Power_search_btn',
                                    hidden:exists('Power_search_btn'),
                                    text: '查询'
                                },
                                {
                                    xtype: 'button',
                                    margin: '0 0 0 5',
                                    icon: 'img/reset.png',
                                    id:'Power_reset_btn',
                                    hidden:exists('Power_reset_btn'),
                                    text: '重置'
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