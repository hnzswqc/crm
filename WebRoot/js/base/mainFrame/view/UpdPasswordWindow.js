/*
 * File: mainFrame/view/UpdPasswordWindow.js
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

Ext.define('mainFrame.view.UpdPasswordWindow', {
    extend: 'Ext.window.Window',

    id: 'UpdPasswordWindow',
    width: 360,
    layout: {
        type: 'fit'
    },
    title: '修改密码',
    modal: true,
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
        	 items: [
                     {
                         xtype: 'container',
                         width: 150,
                         items: [
                             {
                                 xtype: 'form',
                                 region: 'center',
                                 id: 'UpdPasswordForm',
                                 bodyPadding: 10,
                                 header: false,
                                 title: '修改密码',
                                 margin:2,
                                 frame:true,
                                 trackResetOnLoad:true,
                                 items: [
             						{
             						    xtype: 'hiddenfield',
             						    anchor: '100%',
             						    fieldLabel: '登录名',
             						    name: 'userId',
             						    id:'userId',
             						    allowBlank: false,
             						    blankText: '不能为空'
             						},    
                                     {
                                         xtype: 'textfield',
                                         anchor: '100%',
                                         fieldLabel: '原密码',
                                         name: 'userPassword',
                                         allowBlank: false,
                                         inputType: 'password',
                                         blankText: '不能为空'
                                     },
                                     {
                                         xtype: 'textfield',
                                         anchor: '100%',
                                         fieldLabel: '新密码',
                                         name: 'newPassword',
                                         id:'newPassword',
                                         allowBlank: false,
                                         inputType: 'password',
                                         blankText: '不能为空',
                                         minLength: 6,
                                         minLengthText: '至少包含6个字符'
                                         
                                     },
                                     {
                                         xtype: 'textfield',
                                         anchor: '100%',
                                         fieldLabel: '确认密码',
                                         name: 'confirmPassword',
                                         inputType: 'password',
                                         allowBlank: false,
                                         blankText: '不能为空',
                                         validator: function(value) {
                                         	var newPassword = Ext.getCmp("newPassword").value;
                                         	if(value==newPassword){
                                         		return true;
                                         	}else{
                                         		return '确认密码与新密码不一致';
                                         	}
                                     	}
                                        
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
                                             icon: 'img/save.png',
                                             id:'upd_password_save_btn',
                                             text: '保存'
                                         },
                                         {
                                             xtype: 'button',
                                             margin: '0 5 0 10',
                                             icon: 'img/close.png',
                                             id:'upd_password_close_btn',
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