/*
 * File: power/view/OperateWindow.js
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

Ext.define('Power.view.OperateWindow', {
    extend: 'Ext.window.Window',

    margin: 2,
    width: 400,
    layout: {
        type: 'fit'
    },
    title: '功能权限',
    resizable: false,
    modal: true,
    id:'OperateWindow',
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
        	items: [
                    {
                        xtype: 'container',
                        items: [
                            {
                                xtype: 'form',
                                frame: true,
                                id: 'operateForm',
                                bodyPadding: 10,
                                header: false,
                                title: '功能权限表单',
                                margin:2,
                                region: 'center',
                                trackResetOnLoad:true,
                                items: [
            						{
            						    xtype: 'hiddenfield',
            						    anchor: '100%',
            						    fieldLabel: 'uuid',
            						    name: 'operateUuid'
            						},
            						{
            						    xtype: 'hiddenfield',
            						    anchor: '100%',
            						    fieldLabel: '标识',
            						    name: 'powerUuid',
            						    id:'powerUuid'
            						},
                                    {
                                        xtype: 'textfield',
                                        anchor: '100%',
                                        fieldLabel: '标识',
                                        name: 'operateKey',
                                        id:'operateKey',
                                        vtype: 'alpha',
                                        allowBlank: false,
                                        blankText: '不能为空，且唯一'
                                    },
                                    {
                                        xtype: 'textfield',
                                        anchor: '100%',
                                        fieldLabel: '名称',
                                        name: 'operateName',
                                        allowBlank: false,
                                        blankText: '不能为空'
                                    },
                                    {
                                        xtype: 'textfield',
                                        anchor: '100%',
                                        fieldLabel: '图标',
                                        name: 'operateIcon'
                                    },
                                    {
                                        xtype: 'numberfield',
                                        anchor: '100%',
                                        fieldLabel: '排序',
                                        name: 'operateOrderBy',
                                        allowBlank: false,
                                        blankText: '不能为空'
                                    },
                                    {
                                        xtype: 'textareafield',
                                        anchor: '100%',
                                        height: 101,
                                        fieldLabel: '说明',
                                        name: 'operateNote'
                                    }
                                    
                                ]
                            },
                            {
                                xtype: 'toolbar',
                               // height: 40,
                                margin: 2,
                                items: [
                                        '->',
                                        {
                                            xtype: 'button',
                                            id: 'Operate_save_btn',
                                            icon: 'img/save.png',
                                            hidden:exists('Operate_save_btn'),
                                            text: '保存'
                                        },
                                        {
                                            xtype: 'button',
                                            id: 'Operate_close_btn',
                                            margin: '0 10 0 10',
                                            icon: 'img/close.png',
                                            hidden:exists('Operate_close_btn'),
                                            text: '关闭'
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