/*
 * File: subsystem/view/SubSystemWindow.js
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

Ext.define('Subsystem.view.SubSystemWindow', {
    extend: 'Ext.window.Window',

    id: 'SubSystemWindow',
    width: 451,
    layout: {
        type: 'fit'
    },
    title: '子系统信息',
    modal: true,
    resizable: false,
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                	 xtype: 'container',
                     items: [
                         {
                             xtype: 'form',
                             region: 'center',
                             frame: true,
                             id: 'SubSystemForm',
                             margin: 2,
                             bodyPadding: 10,
                             header: false,
                             title: '子系统表单',
                             trackResetOnLoad:true,
                             items: [
         						{
         						    xtype: 'hiddenfield',
         						    anchor: '100%',
         						    fieldLabel: 'uuid',
         						    name: 'subUuid'
         						},
                                 {
                                     xtype: 'textfield',
                                     anchor: '100%',
                                     fieldLabel: '标识',
                                     name: 'subKey',
                                     id: 'subKey',
                                     allowBlank: false,
                                     blankText: '不能为空',
                                     vtype: 'alpha'
                                 },
                                 {
                                     xtype: 'textfield',
                                     anchor: '100%',
                                     fieldLabel: '标题',
                                     name: 'subTitle',
                                     allowBlank: false,
                                     blankText: '不能为空'
                                 },
                                 {
                                     xtype: 'textfield',
                                     anchor: '100%',
                                     fieldLabel: '图标',
                                     name: 'subIcon'
                                 },
                                 {
                                     xtype: 'textfield',
                                     anchor: '100%',
                                     fieldLabel: 'LOGO',
                                     name: 'subLogo'
                                 },
                                 {
                                     xtype: 'combobox',
                                     anchor: '100%',
                                     fieldLabel: '状态',
                                     name: 'subState',
                                     allowBlank: false,
                                     blankText: '不能为空',
                                     editable:false,
                                     displayField: 'text',
                                     valueField: 'id',
                                     store:'SubStateStore'
                                 },
                                 
                                 {
                                     xtype: 'numberfield',
                                     anchor: '100%',
                                     fieldLabel: '排序',
                                     allowBlank: false,
                                     blankText: '不能为空',
                                     name: 'subOrderBy'
                                 },
                                 {
                                     xtype: 'textareafield',
                                     anchor: '100%',
                                     height: 67,
                                     fieldLabel: '备注',
                                     name: 'subNote'
                                 }
                             ]
                         },
                         {
                             xtype: 'toolbar',
                             //height: 40,
                             margin: 2,
                             items: [
                                 '->',
                                 {
		                            xtype: 'button',
		                            id: 'Sub_save_btn',
		                            icon: 'img/save.png',
		                            hidden:exists('Sub_save_btn'),
		                            text: '保存'
		                        },
		                        {
		                            xtype: 'button',
		                            id: 'Sub_close_btn',
		                            margin: '0 5 0 10',
		                            icon: 'img/close.png',
		                            hidden:exists('Sub_close_btn'),
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