/*
 * File: resigned/view/MyViewport.js
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

Ext.define('Resigned.view.MyViewport', {
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
                                    id:'ResignedRefreshBtn',
                                    text: '刷新'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/update.png',
                                    id:'ResignedUpdBtn',
                                    disabled:true,
                                    text: '修改'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                /*
                                {
                                    xtype: 'button',
                                    icon: 'img/del.png',
                                    id:'ResignedDelBtn',
                                    disabled:true,
                                    text: '删除'
                                },
                                {
                                	xtype: 'tbseparator'
                                },*/
                                {
                                    xtype: 'button',
                                    icon: 'img/confirm.png',
                                    id:'ResignedBackBtn',
                                    disabled:true,
                                    text: '恢复'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/subsystem.png',
                                    id:'ResignedStateBtn',
                                    disabled:true,
                                    text: '工资结算'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/export.png',
                                    id:'showBtn',
                                    disabled:true,
                                    text: '查看附件'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/excel.png',
                                    id:'ResignedExcelBtn',
                                    text: '导出'
                                },
                                {
                                	xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    icon: 'img/search.png',
                                    id:'ResignedShowBtn',
                                    text: '高级查询'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            region: 'center',
                            margin: '0 2 2 2 ',
                            title: '辞职员工信息',
                            id:'ResignedGridPanel',
                            store:Ext.create("Resigned.store.ResignedStore",{autoLoad:true}),
                            columns: [
                                {
                                    xtype: 'rownumberer',
                                    width: 38,
                                    text: '序号'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userName',
                                    text: '员工姓名'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userNumber',
                                    text: '员工工号'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userGenderText',
                                    text: '员工性别'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resStateText',
                                    text: '工资结算状态'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resTypeText',
                                    text: '辞职类别'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resMoneyTypeText',
                                    text: '工资结算方式'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userMobile',
                                    text: '联系方式'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resApplyDate',
                                    text: '申请辞职日期'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resApproveDate',
                                    text: '批准辞职日期'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userBankCardText',
                                    text: '工资卡号'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resHandOver',
                                    text: '工作交接情况'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resToolReturn',
                                    text: '工具归还情况'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resOfficeGoods',
                                    text: '办公物品归还情况'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resCheckWork',
                                    text: '考勤情况'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resCheckWorkNum',
                                    text: '考勤天数'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resFinancialLoan',
                                    text: '财务借款情况'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resDeductWagesItem',
                                    text: '扣款项目'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resDeductWages',
                                    text: '应扣金额'
                                },
                                /*
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resRealyWages',
                                    text: '实际领取工资'
                                },
                                */
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'fileName',
                                    text: '附件名称',
                                    renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                                		if(null!=data){
                                			if(record.data.fileType.indexOf('image')>-1){
                                				return "<a href=\"javascript:void(0);\">查看</a>"
                                			}else{
                                				return "<a href=\"javascript:void(0);\">下载</a>"
                                			}
                                		}else{
                                			return "";
                                		}
        		                  	}
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resReson',
                                    text: '辞职原因'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'resNote',
                                    text: '备注'
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    store:'ResignedStore',
                                    width: 360,
                                    displayInfo: true
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            region: 'north',
                            frame: true,
                            height: 78,
                            margin: '0 2 2 2',
                            layout: {
                                type: 'column'
                            },
                            bodyPadding: 10,
                            title: '高级查询',
                            id:'ResignedSearchFromPanel',
                            hidden:true,
                            items: [
                                /*
                                {
                                    xtype: 'combobox',
                                    width: 129,
                                    fieldLabel: '结算状态',
                                    labelWidth: 60,
                                    name:'resState',
                                    displayField: 'text',
                                    valueField: 'id',
                                    id:'resState',
                                    store:'ResignedStateStore'
                                },
                                */
                                {
                                    xtype: 'combobox',
                                    fieldLabel: '是否有工资卡',
                                    margin:'0 0 0 10',
                                    displayField: 'text',
								    valueField: 'id',
								    name:'userBankCard',
								    id:'userBankCard',
								    editable: false,//启用编辑，主要是为了清空当前的选择项
								    store:'BankCardStateStore'
                                   
                                },
                                {
                                    xtype: 'textfield',
                                    fieldLabel: '员工姓名',
                                    width:200,
                                    labelWidth:60,
                                    margin:'0 0 0 10',
                                    name:'userName'
                                },
                                /*
                                {
                                    xtype: 'datefield',
                                    margin: '0 0 2 2',
                                    name:'startDate',
                                    width:200,
                                    fieldLabel: '开始查询时间',
                                    editable: false,
                                    format:'Y-m-d'
                                },
                                {
                                    xtype: 'datefield',
                                    margin: '0 0 2 2',
                                    name:'endDate',
                                    width:200,
                                    fieldLabel: '结束查询时间',
                                    editable: false,
                                    format:'Y-m-d'
                                },
                                */
                                {
                                    xtype: 'combobox',
                                    width: 200,
                                    fieldLabel: '查询月份',
                                    labelWidth: 80,
                                    name:'monthNum',
                                    margin:'0 0 0 10',
                                    editable: false,
                                    id:'monthNum',
                                    store:Ext.create("Ext.data.Store",{
                                    	 fields: ['id', 'text'],
                                    	 autoLoad:true,
                                    	 data : [
                                    	         {"id":"", "text":"全部"},
                                    	         {"id":"1", "text":"一月"},
                                    	         {"id":"2", "text":"二月"},
                                    	         {"id":"3", "text":"三月"},
                                    	         {"id":"4", "text":"四月"},
                                    	         {"id":"5", "text":"五月"},
                                    	         {"id":"6", "text":"六月"},
                                    	         {"id":"7", "text":"七月"},
                                    	         {"id":"8", "text":"八月"},
                                    	         {"id":"9", "text":"九月"},
                                    	         {"id":"10", "text":"十月"},
                                    	         {"id":"11", "text":"十一月"},
                                    	         {"id":"12", "text":"十二月"}
                                    	     ]
                                    }),
                                    displayField: 'text',
                                    valueField: 'id'
                                   
                                },
                                {
                                    xtype: 'button',
                                    margin: '0 0 0 10',
                                    icon: 'img/search.png',
                                    id:'ResignedSearchBtn',
                                    text: '查询'
                                },
                                {
                                    xtype: 'button',
                                    margin: '0 0 0 10',
                                    icon: 'img/reset.png',
                                    id:'ResignedResetBtn',
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