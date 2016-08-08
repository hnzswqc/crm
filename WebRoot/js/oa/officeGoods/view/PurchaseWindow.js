/*
 * File: officeGoods/view/PurchaseWindow.js
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

Ext.define('OfficeGoods.view.PurchaseWindow', {
    extend: 'Ext.window.Window',

    height: 468,
    id: 'PurchaseWindow',
    width: 623,
    layout: {
        type: 'fit'
    },
    modal:true,
    resizable: false,
    title: '选择领取的办公物品',
    maximizable: true,
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'container',
                    layout: {
                        type: 'border'
                    },
                    items: [  {
                        xtype: 'toolbar',
                        region: 'north',
                        margin: 2,
                        items: [
								{
								    xtype: 'button',
								    text: '刷新',
								    icon:'img/reload.png',
								    id:'purchaseRefreshBtn'
								},
								{
									xtype: 'tbseparator'
								},
								{
								    xtype: 'button',
								    text: '确定',
								    disabled:true,
								    icon:'img/save.png',
								    id:'purchaseSureBtn'
								},
								{
									xtype: 'tbseparator'
								},
								{
								    xtype: 'button',
								    text: '关闭',
								    icon:'img/close.png',
								    id:'purchaseCloseBtn'
								},
								{
									xtype: 'tbseparator',
									hidden:true
								},
								{
								    xtype: 'button',
								    text: '高级查询',
								    icon:'img/search.png',
								    id:'purchaseShowFormBtn'
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
                        id:'OfficeGoodsPurchaseSearchForm',
                        hidden:true,
                        items: [
                            {
                                xtype: 'textfield',
                                fieldLabel: '物品名称',
                                name:'purchaseName',
                                labelWidth: 60
                            },
                            {
                                xtype: 'combobox',
                                allowBlank: false,
                                fieldLabel: '物品类别',
                                name: 'purchaseType',
                                displayField: 'text',
                                valueField: 'id',
                                labelWidth: 60,
                                margin:'0 0 0 10',
                                editable: false, //启用编辑，主要是为了清空当前的选择项
                                store:'PurchaseTypeStore'
                            },
                            /*
                            {
                                xtype: 'textfield',
                                fieldLabel: '采购人员',
                                name:'purchasePersonName',
                                margin: '0 0 0 10',
                                labelWidth: 60
                            },
                            */
                            {
                                xtype: 'button',
                                margin: '0 0 0 10',
                                icon: 'img/search.png',
                                id:'purchaseSearchBtn',
                                text: '查询'
                            },
                            {
                                xtype: 'button',
                                margin: '0 0 0 10',
                                icon: 'img/reset.png',
                                id:'purchaseResetBtn',
                                text: '重置'
                            }
                        ]
                    },
                    {
                        xtype: 'gridpanel',
                        region: 'center',
                        margin: '0 2 2 2',
                        title: '物品采购',
                        store:Ext.create("OfficeGoods.store.PurchaseStore",{autoLoad:true}),
                        id:'PurchaseGridPanel',
                        columns: [
                            {
                                xtype: 'rownumberer',
                                width: 47,
                                text: '序号'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchaseName',
                                text: '物品名称'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchaseTypeText',
                                text: '物品类别'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchasePrice',
                                text: '物品单价'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchaseNumber',
                                text: '物品数量'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchaseOnHand',
                                text: '库存数量'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchaseModel',
                                text: '物品规格'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchaseUnit',
                                text: '物品单位'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchaseTotalPrices',
                                text: '采购总价'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchasePersonName',
                                text: '采购人员'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchaseDate',
                                text: '入库时间'
                            },
                            {
                                xtype: 'gridcolumn',
                                dataIndex: 'purchaseNote',
                                text: '物品备注'
                            }
                        ],
                        dockedItems: [
                            {
                                xtype: 'pagingtoolbar',
                                dock: 'bottom',
                                width: 360,
                                store:'PurchaseStore',
                                displayInfo: true
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