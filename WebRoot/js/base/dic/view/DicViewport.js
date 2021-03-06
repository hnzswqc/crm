/*
 * File: dic/view/DicViewport.js
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

Ext.define('Dic.view.DicViewport', {
    extend: 'Ext.container.Viewport',

    id: 'DicViewport',
    layout: {
        type: 'border'
    },

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'treepanel',
                    region: 'west',
                    split: true,
                    id: 'DicTypeTreePanel',
                    margin: '0 0 2 2 ',
                    width: 200,
                    collapsible: true,
                    title: '字典类别树',
                    store: Ext.create("Dic.store.DicTypeTreeStore",{autoLoad:true}),
                    viewConfig: {
                        rootVisible: false
                    }
                },
                {
                    xtype: 'toolbar',
                    region: 'north',
                    //height: 40,
                    margin: 2,
                    items: [
                        {
                            xtype: 'button',
                            id: 'Dic_refresh_btn',
                            icon: 'img/reload.png',
                            hidden:exists('Dic_refresh_btn'),
                            text: '刷新'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Dic_refresh_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Dic_add_type_btn',
                            icon: 'img/add.png',
                            hidden:exists('Dic_add_type_btn'),
                            text: '添加类别'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Dic_add_type_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Dic_upd_type_btn',
                            icon: 'img/update.png',
                            disabled:true,
                            hidden:exists('Dic_upd_type_btn'),
                            text: '修改类别'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Dic_upd_type_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Dic_del_type_btn',
                            icon: 'img/del.png',
                            hidden:exists('Dic_del_type_btn'),
                            disabled:true,
                            text: '删除类别'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Dic_del_type_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Dic_add_dic_btn',
                            icon: 'img/add.png',
                            hidden:exists('Dic_add_dic_btn'),
                            text: '添加字典'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Dic_add_dic_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Dic_upd_dic_btn',
                            icon: 'img/update.png',
                            disabled:true,
                            hidden:exists('Dic_upd_dic_btn'),
                            text: '修改字典'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Dic_upd_dic_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Dic_del_dic_btn',
                            icon: 'img/del.png',
                            disabled:true,
                            hidden:exists('Dic_del_dic_btn'),
                            text: '删除字典'
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    region: 'center',
                    id: 'DicGridPanel',
                    margin: '0 2 2 0',
                    title: '数据字典信息',
                    store: 'DicGridPanelStore',
                    columns: [
                        {
                            xtype: 'rownumberer',
                            width: 43,
                            text: '序号'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'key',
                            text: 'key'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'id',
                            text: '数据值'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'text',
                            text: '显示值'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'label',
                            text: '显示样式值'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'parentName',
                            text: '所属父级',
                            renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                        		if(null==data){
                        			return '<span style=\'color:red\'>根目录</span>';
                        		}else{
                        			return data;
                        		}
		                  	}
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'note',
                            text: '说明'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orderBy',
                            text: '排序'
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'pagingtoolbar',
                            dock: 'bottom',
                            width: 360,
                            displayInfo: true,
                            store: 'DicGridPanelStore'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});