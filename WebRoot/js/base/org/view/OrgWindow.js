/*
 * File: org/view/OrgWindow.js
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

Ext.define('Org.view.OrgWindow', {
    extend: 'Ext.window.Window',

    id: 'OrgWindow',
    width: 515,
    resizable: false,
    layout: {
        type: 'fit'
    },
    title: '添加组织机构',
    modal: true,
    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
        	 items: [
                     {
                         xtype: 'container',
                         items: [{
                             xtype: 'form',
                             frame: true,
                             id: 'addOrgForm',
                             region: 'center',
                             margin: 2,
                             bodyPadding: 10,
                             header: false,
                             title: '组织机构form表单',
                             trackResetOnLoad:true,
                             items: [
                                 {
                                     xtype: 'textfield',
                                     anchor: '100%',
                                     fieldLabel: 'uuid',
                                     name: 'orgUuid',
                                     id:'orgUuid',
                                     hidden:true
                                 },
                                 {
                                     xtype: 'textfield',
                                     anchor: '100%',
                                     fieldLabel: '名称',
                                     name: 'orgName',
                                     allowBlank: false,
                                     blankText: '不能为空'
                                 },
                                 {
                                     xtype: 'textfield',
                                     anchor: '100%',
                                     fieldLabel: '编码',
                                     name: 'orgCode',
                                     allowBlank: false,
                                     blankText: '不能为空'
                                 },
                                 {
                                     xtype: 'textfield',
                                     anchor: '100%',
                                     fieldLabel: '图标',
                                     name: 'orgIcon'
                                 },
                                 {
                                     xtype: 'combobox',
                                     anchor: '100%',
                                     fieldLabel: '级别',
                                     name: 'orgGradeUuid',
                                     id:'orgGradeUuid',
                                     allowBlank: false,
                                     blankText: '不能为空',
                                     displayField: 'text',
                                     valueField: 'id',
                                     editable: false, //启用编辑，主要是为了清空当前的选择项
                                     store:'OrgGradeStore'
                                 },
                                 {
                                     xtype: 'treepicker',
                                     anchor: '100%',
                                     fieldLabel: '上级组织',
                                     name: 'orgParentUuid',
                                     id:'orgParentUuid',
                                     allowBlank: false,
                                     blankText: '不能为空',
                                     displayField: 'text',
                                     valueField: 'id',
                                     minPickerHeight: 20, //最小高度，不设置的话有时候下拉会出问题
                                     editable: false, //启用编辑，主要是为了清空当前的选择项
                                     autoScroll:true,
                                     //enableKeyEvents: true, //激活键盘事件
                                     forceSelection : true,// 只能选择下拉框里面的内容  
                                     store: new Ext.data.TreeStore({
                                       model: 'Org.model.OrgTreeNode',
                                       autoLoad:true,
                                       proxy: {
                                         type: 'ajax',
                                         url : 'org/getOrgTreeList.do', //请求url
                                         reader: {
                                             type: 'json'
                                         }
                                       },
                                       root: {
                                         text: '根目录',
                                         id: DEFAULT_ROOT_UUID
                                       },
                                       scope:this
                                     })
                                 },
                                 {
                                     xtype: 'combobox',
                                     anchor: '100%',
                                     fieldLabel: '状态码',
                                     name: 'orgState',
                                     allowBlank: false,
                                     blankText: '不能为空',
                                     displayField: 'text',
                                     valueField: 'id',
                                     editable: false, //启用编辑，主要是为了清空当前的选择项
                                     store:'OrgStateStore'
                                 },
                                 {
                                     xtype: 'numberfield',
                                     anchor: '100%',
                                     fieldLabel: '排序号',
                                     name: 'orgOrderBy',
                                     allowBlank: false,
                                     blankText: '不能为空'
                                 },
                                 {
                                	 xtype: 'textfield',
                                     anchor: '100%',
                                     fieldLabel: '工号起始号',
                                     name:'startNumber',
                                     regex: /^\d{3}$/,
                                     allowBlank: false,
                                     blankText: '不能为空',
                                     maxLength: 3,
                                     regexText: '只能输入三位数字'
                                },
                                {
                                	 xtype: 'textfield',
                                     anchor: '100%',
                                     name:'endNumber',
                                     allowBlank: false,
                                     blankText: '不能为空',
                                     regex: /^\d{3}$/,
                                     regexText: '只能输入三位数字',
                                     maxLength: 3,
                                     fieldLabel: '工号结束号'
                                },
                                 {
                                     xtype: 'textareafield',
                                     anchor: '100%',
                                     height: 120,
                                     fieldLabel: '备注说明',
                                     name: 'orgNote'
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
         							    id: 'Org_save_btn',
         							    icon: 'img/save.png',
         							    hidden:exists('Org_save_btn'),
         							    text: '保存'
         							},
         							{
         							    xtype: 'button',
         							    id: 'Org_close_btn',
         							    icon: 'img/close.png',
         							    hidden:exists('Org_close_btn'),
         							    text: '关闭'
         							}
                             ]
                         }]
                     }
                 ]
        });

        me.callParent(arguments);
    }

});