/*
 * File: org/view/OrgViewport.js
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

Ext.define('Org.view.OrgViewport', {
    extend: 'Ext.container.Viewport',

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
                            id: 'Org_refresh_btn',
                            icon: 'img/reload.png',
                            hidden:exists('Org_refresh_btn'),
                            text: '刷新'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Org_refresh_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Org_folding_btn',
                            icon: 'img/folder.gif',
                            hidden:exists('Org_folding_btn'),
                            text: '折叠'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Org_folding_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Org_expand_btn',
                            icon: 'img/folder-open.gif',
                            hidden:exists('Org_expand_btn'),
                            text: '展开'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Org_expand_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Org_add_btn',
                            icon: 'img/add.png',
                            hidden:exists('Org_add_btn'),
                            text: '添加'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Org_add_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Org_upd_btn',
                            icon: 'img/update.png',
                            hidden:exists('Org_upd_btn'),
                            disabled:true,
                            text: '修改'
                        },
                        {
                        	xtype: 'tbseparator',
                        	hidden:exists('Org_upd_btn')
                        },
                        {
                            xtype: 'button',
                            id: 'Org_del_btn',
                            icon: 'img/del.png',
                            hidden:exists('Org_del_btn'),
                            disabled:true,
                            text: '删除'
                        },
                        {
                        	xtype: 'tbseparator'
                        },
                        {
                            xtype: 'button',
                            id: 'Org_role_btn',
                            icon: 'img/subsystem.png',
                            disabled:true,
                            text: '部门职务'
                        }
                    ]
                },
                {
                	 xtype: 'treepanel',
                     margins: '',
                     region: 'west',
                     split: true,
                     id: 'OrgTreePanel',
                     margin: '0 0 2 2 ',
                     width: 200,
                     collapsed: false,
                     collapsible: true,
                     title: '组织机构',
                     store:Ext.create("Org.store.OrgTreeNodeStore",{autoLoad:true}), 
                     autoShow:true,
                     rootVisible: false
                },
                {
                    xtype: 'gridpanel',
                    region: 'center',
                    margin: '0 2 2 0',
                    title: '组织机构',
                    id:'OrgGridPanel',
                    store: Ext.create("Org.store.OrgStore",{autoLoad:true}),
                    columns: [
                        {
                            xtype: 'rownumberer',
                            width: 34,
                            text: '序号'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orgUuid',
                            hidden:true,
                            hideable:false,
                            text: 'uuid'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'child',
                            hidden:true,
                            hideable:false,
                            text: '子级数量'
                        },

                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orgName',
                            width:100,
                            text: '名称'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orgCode',
                            text: '编码'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'startNumber',
                            text: '员工起始编码'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'endNumber',
                            text: '员工结束编码'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orgIcon',
                            text: '图标',
                            renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                        		if(null!=data){
                        			return '<img src=\''+data+"\''/>"
                        		}else{
                        			return data;
                        		}
		                  	}
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orgGradeUuid',
                            text: '级别',
                            renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                        		return record.data.orgGradeText; 
		                  	}
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orgParentName',
                            text: '上级组织',
                            renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                        		if(null==data){
                        			return  '根目录';
                        		}
                        		return data; 
		                  	}
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orgParentUuid',
                            hidden:true,
                            hideable:false,
                            text: '上级组织uuid'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orgState',
                            text: '状态码',
                            renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                        		return record.data.orgStateText; 
		                  	}
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orgOrderBy',
                            text: '排序号'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'orgNote',
                            text: '备注'
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
                            store:'OrgStore',
                            displayInfo: true
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});