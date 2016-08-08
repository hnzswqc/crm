/*
 * File: resigned/view/BalanceWindow.js
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

Ext.define('Resigned.view.BalanceWindow', {
    extend: 'Ext.window.Window',

    height: 276,
    id: 'BalanceWindow',
    width: 418,
    layout: {
        type: 'fit'
    },
    title: '工资结算',
    resizable: false,
    modal:true,
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
                            region: 'south',
                            margin: '0 2 2 2 ',
                            items: [
                                '->',
                                {
                                    xtype: 'button',
                                    id:'BalanceSaveBtn',
                                    icon:'img/confirm.png',
                                    text: '结算'
                                },
                                {
                                    xtype: 'button',
                                    id:'BalanceDelBtn',
                                    icon:'img/delete.png',
                                    text: '取消结算'
                                },
                                {
                                    xtype: 'button',
                                    id:'BalanceCloseBtn',
                                    icon:'img/close.png',
                                    text: '关闭'
                                }
                            ]
                        },
                        {
                            xtype: 'gridpanel',
                            region: 'center',
                            margin: 2,
                            header: false,
                            title: '未结算工资月份',
                            id:'BalanceGridPanel',
                            store:Ext.create("Resigned.store.ResignedMonthStore",{autoLoad:true}),
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'monthNum',
                                    text: '月份'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'monthStateText',
                                    text: '结算状态'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'clearTime',
                                    text: '结算日期'
                                }
                            ],
                            selModel: Ext.create('Ext.selection.CheckboxModel', {
                            	injectCheckbox:0,//checkbox位于哪一列，默认值为0
                            	handleMouseDown : Ext.emptyFn,
                        	    mode: 'SIMPLE'
                            })
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});