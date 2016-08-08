/*
 * File: bz/view/MyViewport.js
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

Ext.define('Tg.view.MyViewport', {
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
                                    id: 'BzReloadBtn',
                                    icon: 'img/reload.png',
                                    hidden:exists('BzReloadBtn_tg'),
                                    text: '刷新'
                                },
                                {
                                	xtype: 'tbseparator',
                                	hidden:exists('BzReloadBtn_tg')
                                },
                                {
                                    xtype: 'button',
                                    id: 'BzAddBtn',
                                    icon: 'img/add.png',
                                    hidden:exists('BzAddBtn_tg'),
                                    text: '添加'
                                },
                                {
                                	xtype: 'tbseparator',
                                	hidden:exists('BzAddBtn_tg')
                                },
                                {
                                    xtype: 'button',
                                    id: 'BzUpdBtn',
                                    icon: 'img/update.png',
                                    disabled: true,
                                    hidden:exists('BzUpdBtn_tg'),
                                    text: '修改'
                                },
                                {
                                	xtype: 'tbseparator',
                                	hidden:exists('BzUpdBtn_tg')
                                },
                                {
                                    xtype: 'button',
                                    id: 'BzDelBtn',
                                    icon: 'img/del.png',
                                    disabled: true,
                                    hidden:exists('BzDelBtn_tg'),
                                    text: '删除'
                                },
                                {
                                	xtype: 'tbseparator',
                                	hidden:exists('BzDelBtn_tg')
                                },
                                {
                                    xtype: 'button',
                                    id: 'BzExportBtn',
                                    icon: 'img/excel.png',
                                    hidden:exists('BzExportBtn_tg'),
                                    text: '导出'
                                },
                                {
                                	xtype: 'tbseparator',
                                	hidden:exists('BzExportBtn_tg')
                                },
                                {
                                    xtype: 'button',
                                    id: 'BzShowSearchFormBtn',
                                    icon: 'img/search.png',
                                    hidden:exists('BzShowSearchFormBtn_tg'),
                                    text: '高级查询'
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            region: 'north',
                            frame: true,
                            height: 76,
                            hidden: true,
                            id: 'BzSearchFormPanel',
                            margin: '0 2 2 2',
                            layout: {
                                type: 'column'
                            },
                            bodyPadding: 10,
                            title: '高级查询',
                            items: [
                                {
                                    xtype: 'hiddenfield',
                                    anchor: '100%',
                                    fieldLabel: '产品规格',
                                    name:'loggingProductNum',
                                    id:'loggingProductNum_search',
                                    width:150,
                                    labelWidth: 60
                                },
                                {
                                    xtype: 'textfield',
                                    margin: '0 0 0 10',
                                    fieldLabel: '员工号',
                                    id:'loggingUserNumber_search',
                                    name: 'loggingUserNumber',
                                    width:150,
                                    labelWidth: 60
                                },
                                {
                                    xtype: 'datefield',
                                    fieldLabel: '开始时间',
                                    name:'startDate',
                                    id:'startDate_search',
                                    format:'Y-m-d',
                                    width:160,
                                    margin: '0 0 0 10',
                                    labelWidth: 60
                                },
                                {
                                    xtype: 'datefield',
                                    fieldLabel: '结束时间',
                                    name:'endDate',
                                    id:'endDate_search',
                                    format:'Y-m-d',
                                    width:160,
                                    margin: '0 0 0 10',
                                    labelWidth: 60
                                },
                                {
                                    xtype: 'button',
                                    id: 'BzSearchBtn',
                                    margin: '0 0 0 10',
                                    icon: 'img/search.png',
                                    hidden:exists('BzSearchBtn_tg'),
                                    text: '查询'
                                },
                                {
                                    xtype: 'button',
                                    id: 'BzResetBtn',
                                    margin: '0 0 0 10',
                                    icon: 'img/reset.png',
                                    hidden:exists('BzResetBtn_tg'),
                                    text: '重置'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            region: 'center',
                            id: 'BzGridPanel',
                            margin: '0 2 2 2',
                            title: '包装工资',
                            store:Ext.create("Tg.store.TgStore",{autoLoad:true}),
                            columns: [
                                {
		                            xtype: 'rownumberer',
		                            width: 39,
		                            text: '序号'
		                        },
		                        {
		                            xtype: 'gridcolumn',
		                            dataIndex: 'loggingDate',
		                            text: '日期'
		                        },
		                        {
		                            xtype: 'gridcolumn',
		                            dataIndex: 'loggingUserName',
		                            text: '员工姓名'
		                        },
		                        {
		                            xtype: 'gridcolumn',
		                            dataIndex: 'loggingUserNumber',
		                            text: '员工号'
		                        },
		                        {
		                            xtype: 'gridcolumn',
		                            dataIndex: 'loggingUserSex',
		                            text: '性别',
		                            renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
		                        		return record.data.loggingUserSexText;
				                  	}
		                        },
		                        {
		                            xtype: 'gridcolumn',
		                            dataIndex: 'loggingType',
		                            text: '工作性质',
		                            renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
		                        		return record.data.loggingTypeText;
				                  	}
		                        },
		                        {
		                            xtype: 'numbercolumn',
		                            dataIndex: 'loggingWages',
		                            summaryType: 'sum',
		                            text: '应得工资'
		                        },
		                        {
		                            xtype: 'numbercolumn',
		                            dataIndex: 'loggingErrWages',
		                            summaryType: 'sum',
		                            text: '扣除工资'
		                        },
		                        {
		                            xtype: 'numbercolumn',
		                            dataIndex: 'loggingRealityWages',
		                            summaryType: 'sum',
		                            text: '实际工资'
		                        },
		                        {
		                            xtype: 'numbercolumn',
		                            dataIndex: 'loggingPrice',
		                            text: '单价'
		                        },
		                        {
		                            xtype: 'numbercolumn',
		                            dataIndex: 'loggingNumber',
		                            summaryType: 'sum',
		                            text: '数量'
		                        },
		                        {
		                            xtype: 'gridcolumn',
		                            dataIndex: 'note',
		                            text: '说明'
		                        }
                            ],
                            features: [
                                   {
                                	   groupHeaderTpl:  '{name} ({rows.length} 条)',
                                       ftype: 'groupingsummary'
                                   }
                               ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    displayInfo: true,
                                    store: 'BzStore'
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