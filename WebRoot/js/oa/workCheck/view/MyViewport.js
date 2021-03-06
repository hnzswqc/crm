/*
 * File: workCheck/view/MyViewport.js
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

Ext.define('WorkCheck.view.MyViewport', {
    extend: 'Ext.container.Viewport',

    layout: {
        type: 'fit'
    },

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'container',
                    layout: {
                        type: 'border'
                    },
                    items: [
                        {
                            xtype: 'toolbar',
                            region: 'north',
                            margin: 2,
                            items: [
                                {
                                    xtype: 'button',
                                    icon: 'img/reload.png',
                                    id:'WorkCheckRefreshBtn',
                                    text: '刷新'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/add.png',
                                    id:'WorkCheckAddBtn',
                                    text: '添加'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/add.png',
                                    id:'BatchAddBtn',
                                    text: '批量添加'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/update.png',
                                    id:'WorkCheckUpdateBtn',
                                    disabled:true,
                                    text: '修改'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/del.png',
                                    id:'WorkCheckDelBtn',
                                    disabled:true,
                                    text: '删除'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/excel.png',
                                    id:'WorkCheckExcelBtn',
                                    text: '导出'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/search.png',
                                    id:'WorkCheckShowSearchBtn',
                                    text: '高级查询'
                                }
                            ]
                        },
                        {
                            xtype: 'treepanel',
                            region: 'west',
                            split: true,
                            margin: '0 0 2 2',
                            width: 200,
                            collapsed: true,
                            collapsible: true,
                            title: '组织机构',
                            store: 'OrgTreeStore',
                            id:'OrgTreePanel',
                            rootVisible: false,
                            viewConfig: {

                            }
                        },
                        {
                            xtype: 'container',
                            region: 'center',
                            layout: {
                                type: 'border'
                            },
                            items: [
                                {
                                    xtype: 'form',
                                    region: 'north',
                                    frame: true,
                                    height: 78,
                                    margin: '0 2 2 0',
                                    layout: {
                                        type: 'column'
                                    },
                                    bodyPadding: 10,
                                    title: '高级查询',
                                    id:'searchFormPanel',
                                    hidden:true,
                                    items: [
										{
                                            xtype: 'numberfield',
                                            fieldLabel: '考勤年度',
                                            name:'wcYear',
                                            labelWidth:80,
                                            width: 200,
                                            allowBlank: false,
                                            blankText: '不能为空',
                                            value:new Date().getFullYear(),
                                            maxLength: 4,
                                            minLength: 4
                                        },
                                        {
                                            xtype: 'combobox',
                                            width: 120,
                                            fieldLabel: '月份',
                                            name:'wcMonth',
                                            id:'searchWcMonth',
                                            labelWidth: 50,
                                            editable: false,
                                            store:Ext.create("Ext.data.Store",{
	                                           	 fields: ['id', 'text'],
	                                           	 autoLoad:true,
	                                           	 data : [
	                                           	         {"id":1, "text":"一月"},
	                                           	         {"id":2, "text":"二月"},
	                                           	         {"id":3, "text":"三月"},
	                                           	         {"id":4, "text":"四月"},
	                                           	         {"id":5, "text":"五月"},
	                                           	         {"id":6, "text":"六月"},
	                                           	         {"id":7, "text":"七月"},
	                                           	         {"id":8, "text":"八月"},
	                                           	         {"id":9, "text":"九月"},
	                                           	         {"id":10, "text":"十月"},
	                                           	         {"id":11, "text":"十一月"},
	                                           	         {"id":12, "text":"十二月"}
	                                           	     ]
                                           }),
                                           displayField: 'text',
                                           valueField: 'id'
                                        },
                                        {
                                            xtype: 'textfield',
                                            fieldLabel: '员工工号',
                                            labelWidth:80,
                                            margin:'0 0 0 10',
                                            name:'userNumber'
                                        },
                                        {
                                            xtype: 'hiddenfield',
                                            fieldLabel: '所属部门',
                                            name:'orgUuid',
                                            id:'searchOrgUuid'
                                        },
                                        {
                                            xtype: 'button',
                                            margin: '0 0 0 10',
                                            icon: 'img/search.png',
                                            id:'WorkCheckSearchBtn',
                                            text: '查询'
                                        },
                                        {
                                            xtype: 'button',
                                            margin: '0 0 0 10',
                                            icon: 'img/reset.png',
                                            id:'WorkCheckResetBtn',
                                            text: '重置'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'gridpanel',
                                    region: 'center',
                                    id: 'WorkCheckGridPanel',
                                    margin: '0 2 2 0',
                                    title: '考勤信息',
                                    store: Ext.create("WorkCheck.store.WorkCheckStore",{autoLoad:true}),
                                    columns: [
                                        {
                                            xtype: 'rownumberer',
                                            width: 54,
                                            text: '序号'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            width: 84,
                                            dataIndex: 'userName',
                                            text: '姓名'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            width: 84,
                                            dataIndex: 'userNumber',
                                            text: '员工工号'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            width: 84,
                                            dataIndex: 'orgName',
                                            text: '所属部门'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            width: 84,
                                            dataIndex: 'roleName',
                                            text: '职务'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            width: 84,
                                            dataIndex: 'wcMonth',
                                            text: '考勤月份',
                                            renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                                        		if(record.data.wcMonth<10){
                                        			return record.data.wcYear+"-0"+record.data.wcMonth;
                                        		}else{
                                        			return record.data.wcYear+"-"+record.data.wcMonth;
                                        		}
                                        		
                		                  	}
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            width: 84,
                                            dataIndex: 'wcCheckDay',
                                            text: '考勤天数'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            width: 111,
                                            dataIndex: 'wcAddDay',
                                            text: '加班天数'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            width: 111,
                                            dataIndex: 'wcAddHour',
                                            text: '加班小时'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            width: 111,
                                            dataIndex: 'joinTime',
                                            text: '入职时间'
                                        },
                                        {
                                            xtype: 'gridcolumn',
                                            width: 111,
                                            dataIndex: 'wcNote',
                                            text: '备注信息'
                                        }
                                    ],
                                    dockedItems: [
                                        {
                                            xtype: 'pagingtoolbar',
                                            dock: 'bottom',
                                            width: 360,
                                            store: 'WorkCheckStore',
                                            displayInfo: true
                                        }
                                    ]
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