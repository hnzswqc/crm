/*
 * File: yearReport/view/MyViewport.js
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

Ext.define('YearReport.view.MyViewport', {
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
                                    id: 'YearReportRefreshBtn',
                                    icon: 'img/reload.png',
                                    hidden:exists('YearReportRefreshBtn'),
                                    text: '刷新'
                                },
                                {
                                	xtype: 'tbseparator',
                                	 hidden:exists('YearReportRefreshBtn')
                                },
                                {
                                    xtype: 'button',
                                    id: 'YearReportDetailBtn',
                                    icon: 'img/detail.png',
                                    hidden:exists('YearReportDetailBtn'),
                                    text: '明细查询'
                                },
                                {
                                	xtype: 'tbseparator',
                                	  hidden:exists('YearReportDetailBtn')
                                },
                                {
                                    xtype: 'button',
                                    id: 'YearReportExportBtn',
                                    icon: 'img/excel.png',
                                    hidden:exists('YearReportExportBtn'),
                                    text: '导出'
                                },
                                {
                                	xtype: 'tbseparator',
                                	hidden:exists('YearReportExportBtn')
                                },
                                {
                                    xtype: 'button',
                                    id: 'YearReportShowSearchBtn',
                                    icon: 'img/search.png',
                                    hidden:exists('YearReportShowSearchBtn'),
                                    text: '高级查询'
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            region: 'north',
                            frame: true,
                            height: 73,
                            margin: '0 2 2 2',
                            layout: {
                                type: 'column'
                            },
                            bodyPadding: 10,
                            id:'YearReportSearchFormPanel',
                            hidden:true,
                            title: '高级查询',
                            items: [
                                {
                                    xtype: 'combobox',
                                    anchor: '100%',
                                    margin: 2,
                                    width: 137,
                                    fieldLabel: '年度',
                                    name: 'startDate',
                                    id:'startDate',
                                    valueField: 'loggingDate',
                                    displayField: 'loggingDate',
                                    store:Ext.create("YearReport.store.YearStore",{autoLoad:true}),
                                    editable: false, //启用编辑，主要是为了清空当前的选择项
                                    allowBlank: false,
                                    blankText: '不能为空',
                                    labelWidth: 50
                                },
                                {
                                    xtype: 'textfield',
                                    margin: '0 0 0 10',
                                    fieldLabel: '员工号',
                                    id:'loggingUserNumber',
                                    name:'loggingUserNumber',
                                    labelWidth: 60
                                },
                                {
                                    xtype: 'button',
                                    id: 'YearReportSearchBtn',
                                    margin: '0 0 0 10',
                                    icon: 'img/search.png',
                                    text: '查询'
                                },
                                {
                                    xtype: 'button',
                                    id: 'YearReportResetBtn',
                                    margin: '0 0 0 10',
                                    icon: 'img/reset.png',
                                    text: '重置'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            region: 'center',
                            margin: '0 2 2 2',
                            title: '本年度工资查询',
                            id:"YearReportGridPanel",
                            store: Ext.create("YearReport.store.YearReportStore",{autoLoad:true}),
                            columns: [
								{
								    xtype: 'rownumberer',
								    width: 37,
								    text: '序号'
								},
								{
								    xtype: 'gridcolumn',
								    dataIndex: 'orgName',
								    text: '部门'
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
								    text: '工资',
								    decimalPrecision: 3,
								    summaryType: 'sum'
								},
								{
								    xtype: 'numbercolumn',
								    dataIndex: 'loggingAllWeight',
								    text: '总重',
								    decimalPrecision: 3,
								    summaryType: 'sum'
								}
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    store:'YearReportStore',
                                    displayInfo: true
                                }
                            ],
                            features: [
                               {
                            	   groupHeaderTpl:  ' {name} ({rows.length} 人)',
                                   ftype: 'groupingsummary'
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