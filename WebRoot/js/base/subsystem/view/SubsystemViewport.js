/*
 * File: subsystem/view/SubsystemViewport.js
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

Ext.define('Subsystem.view.SubsystemViewport', {
    extend: 'Ext.container.Viewport',

    id: 'SubsystemViewport',
    layout: {
        type: 'border'
    },

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'toolbar',
                    region: 'north',
                    //height: 40,
                    margin: 2,
                    items: [
                        {
                            xtype: 'button',
                            id: 'Sub_refresh_btn',
                            icon: 'img/reload.png',
                            hidden:exists('Sub_refresh_btn'),
                            text: '刷新'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Sub_refresh_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Sub_add_btn',
                            icon: 'img/add.png',
                            hidden:exists('Sub_add_btn'),
                            text: '添加'
                        },
                        {
                        	xtype: 'tbseparator',
                        	 hidden:exists('Sub_add_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Sub_upd_btn',
                            icon: 'img/update.png',
                            hidden:exists('Sub_upd_btn'),
                            disabled:true,
                            text: '修改'
                        },
                        {
                        	xtype: 'tbseparator',
                        	 hidden:exists('Sub_upd_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Sub_del_btn',
                            icon: 'img/del.png',
                            hidden:exists('Sub_del_btn'),
                            disabled:true,
                            text: '删除'
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    region: 'center',
                    id: 'SubSystemGridPanel',
                    margin: '0 2 2 2',
                    title: '子系统管理',
                    store: Ext.create("Subsystem.store.SubSystemStore",{autoLoad:true}),
                    columns: [
                        {
                            xtype: 'rownumberer',
                            width: 36,
                            text: '序号'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'subKey',
                            text: '标识'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'subTitle',
                            text: '标题'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'subIcon',
                            text: '图标',
                            width:50,
                            renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                        		if(null!=data&&""!=data){
                        			return "<img src='"+data+"' width='16px;' height='16px;'/>";
                        		}else{
                        			return data;
                        		}
		                  	}
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'subLogo',
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
                            dataIndex: 'subStateText',
                            text: '状态'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'subNote',
                            text: '备注'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'subOrderBy',
                            text: '排序'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'createTime',
                            text: '创建时间'
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'pagingtoolbar',
                            dock: 'bottom',
                            width: 360,
                            store:'SubSystemStore',
                            displayInfo: true
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});