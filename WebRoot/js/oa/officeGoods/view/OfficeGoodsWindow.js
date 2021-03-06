/*
 * File: officeGoods/view/OfficeGoodsWindow.js
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

Ext.define('OfficeGoods.view.OfficeGoodsWindow', {
    extend: 'Ext.window.Window',

    height: 380,
    id: 'OfficeGoodsWindow',
    width: 369,
    layout: {
        type: 'fit'
    },
    title: '办公物品领用',
    modal:true,
    resizable: false,
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
                            margin: '0 2 2 2',
                            items: [
                                '->',
                                {
                                    xtype: 'button',
                                    id: 'OfficeGoodsSaveBtn',
                                    icon: 'img/save.png',
                                    id:'ogsSaveBtn',
                                    text: '保存'
                                },
                                {
                                    xtype: 'button',
                                    id: 'OfficeGoodsCloseBtn',
                                    icon: 'img/close.png',
                                    id:'ogsCloseBtn',
                                    text: '关闭'
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            region: 'center',
                            frame: true,
                            margin: 2,
                            layout: {
                                type: 'column'
                            },
                            bodyPadding: 10,
                            header: false,
                            id:'OfficeGoodsFormPanel',
                            title: '办公物品领用表单',
                            trackResetOnLoad:true,
                            items: [
								{
								    xtype: 'hiddenfield',
								    name:"ogsUuid",
								    fieldLabel: '主键'
								},  
								{
								    xtype: 'textfield',
								    width: 257,
								    margin:'2 2 2 2',
								    name:"ogsUserNumber",
								    id:'ogsUserNumber',
								    allowBlank: false,
                                    blankText: '不能为空',
								    fieldLabel: '员工工号'
								},
								{
								    xtype: 'hiddenfield',
								    id:"loggingUserNumber_hidden",
								    fieldLabel: '隐藏员工工号'
								},
								{
								    xtype: 'button',
								    id: 'OfficeGoodsUserChoseBtn',
								    margin: '0 2 2 2',
								    icon: 'img/search.png',
								    text: '选择'
								},
                                {
                                    xtype: 'textfield',
                                    width: 316,
                                    margin:'0 2 2 2',
                                    fieldLabel: '员工姓名',
                                    fieldStyle: 'background:#f2f2f2',
                                    name:"ogsUserName",
                                    id:'ogsUserName',
                                    readOnly: true
                                },
                                {
                                    xtype: 'hiddenfield',
                                    fieldLabel: '员工uuid',
                                    fieldStyle: 'background:#f2f2f2',
                                    name:"ogsUserUuid",
                                    id:'ogsUserUuid',
                                    readOnly: true
                                },
                                {
                                    xtype: 'datefield',
                                    width: 316,
                                    margin:'0 2 2 2',
                                    name:"ogsDate",
                                    allowBlank: false,
                                    blankText: '不能为空',
                                    value:new Date(),
                                    format:'Y-m-d',
                                    id:'ogsDate',
                                    fieldLabel: '领取时间',
                                    editable: false,
                                    listeners: {
                                        'select': function () {
                                            var start = Ext.getCmp('ogsDate').getValue();
                                            Ext.getCmp('ogsBackDate').setMinValue(start);
                                            var endDate = Ext.getCmp('ogsBackDate').getValue();
                                            if (start > endDate) {
                                                Ext.getCmp('ogsBackDate').setValue(start);
                                            }
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    width: 257,
                                    margin:'0 2 2 2',
                                    name:"ogsTypeText",
                                    id:'ogsTypeText',
                                    allowBlank: false,
                                    blankText: '不能为空',
                                    fieldLabel: '领取物品',
                                    fieldStyle: 'background:#f2f2f2',
                                    readOnly: true
                                    
                                },
                                {
                                    xtype: 'hiddenfield',
                                    name:"ogsType",
                                    id:'ogsType',
                                    fieldLabel: '领取物品uuid',
                                    
                                },
                                {
								    xtype: 'button',
								    id: 'OfficeGoodsChoseBtn',
								    margin: '0 2 2 2',
								    icon: 'img/search.png',
								    text: '选择'
								},
                                {
                                    xtype: 'numberfield',
                                    width: 316,
                                    margin:'0 2 2 2',
                                    name:"ogsNumber",
                                    decimalPrecision: 3,
                                    minValue: 0,
                                    value:1,
                                    allowBlank: false,
                                    blankText: '不能为空',
                                    fieldLabel: '领取数量'
                                },
                                {
                                    xtype: 'combobox',
                                    width: 316,
                                    margin:'0 2 2 2',
                                    name:"ogsState",
                                    fieldLabel: '是否归还',
                                    displayField: 'text',
                                    valueField: 'id',
                                    allowBlank: false,
                                    editable: false, //启用编辑，主要是为了清空当前的选择项
                                    store:'OfficeGoodsTypeStore'
                                },
                                {
                                    xtype: 'datefield',
                                    width: 316,
                                    margin:'0 2 2 2',
                                    name:"ogsBackDate",
                                    id:'ogsBackDate',
                                    format:'Y-m-d',
                                    fieldLabel: '归还时间',
                                    minValue:new Date(),
                                    //editable: false,
                                    listeners: {
                                        select: function () {
                                            var start = Ext.getCmp('ogsDate').getValue();
                                            Ext.getCmp('ogsBackDate').setMinValue(start);
                                        }
                                    }
                                },
                                {
                                    xtype: 'filefield',
                                    width: 322,
                                    margin:'0 2 2 2',
                                    fieldLabel: '附件上传',
                                    buttonText: '浏览...',
                                    name:'file',
                                    buttonConfig: {
                                        xtype: 'filebutton',
                                        icon: 'img/search.png',
                                        text: '选择'
                                    }
                                },
                                {
                                    xtype: 'textareafield',
                                    height: 92,
                                    margin:'0 2 2 2',
                                    width: 322,
                                    name:"ogsNote",
                                    fieldLabel: '领用备注'
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