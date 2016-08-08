/*
 * File: dic/view/DicWindow.js
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

Ext.define('Dic.view.DicWindow', {
    extend: 'Ext.window.Window',

    id: 'DicWindow',
    width: 420,
    resizable: false,
    layout: {
        type: 'fit'
    },
    title: '添加数据字典',
    modal: true,

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
                                id: 'DicForm',
                                margin: 2,
                                bodyPadding: 10,
                                header: false,
                                title: '字典类别表单',
                                region: 'center',
                                trackResetOnLoad:true,
                                items: [
            						{
            						    xtype: 'textfield',
            						    anchor: '100%',
            						    fieldLabel: '标识Key',
            						    name: 'key',
            						    id:'DicForm_key',
            						    allowBlank: false,
            						    blankText: '不能为空，获取数据字典需要该参数'
            						},
                                    {
                                        xtype: 'textfield',
                                        anchor: '100%',
                                        fieldLabel: '字典数据值',
                                        name: 'id',
                                        id:'DicForm_id',
                                        allowBlank: false,
                                        blankText: '不能为空，获取数据字典需要该参数'
                                    },
                                    {
                                        xtype: 'hiddenfield',
                                        anchor: '100%',
                                        fieldLabel: 'uuid',
                                        name: 'uuid',
                                        id:'uuid'
                                    },
                                    {
                                        xtype: 'hiddenfield',
                                        anchor: '100%',
                                        fieldLabel: 'type',
                                        id:'type',
                                        name: 'type'
                                    },
                                    {
                                        xtype: 'textfield',
                                        anchor: '100%',
                                        fieldLabel: '字典显示值',
                                        name: 'text',
                                        allowBlank: false,
                                        blankText: '不能为空'
                                    },
                                    {
                                        xtype: 'textfield',
                                        anchor: '100%',
                                        fieldLabel: '显示样式',
                                        name: 'label',
                                        allowBlank: false,
                                        blankText: '不能为空'
                                    },
                                    {
                                        xtype: 'treepicker',
                                        anchor: '100%',
                                        fieldLabel: '上级字典',
                                        name: 'parentUuid',
                                        id:'parentUuid',
                                        allowBlank: false,
                                        blankText: '不能为空',
                                        displayField: 'text',
                                        valueField: 'id',
                                        minPickerHeight: 20, //最小高度，不设置的话有时候下拉会出问题
                                        editable: false, //启用编辑，主要是为了清空当前的选择项
                                        autoScroll:true,
                                        //enableKeyEvents: true, //激活键盘事件
                                        forceSelection : true,// 只能选择下拉框里面的内容
                                        store:new Ext.data.TreeStore({
                                            model: 'Dic.model.DicModel',
                                            autoLoad:true,
                                            proxy: {
                                              type: 'ajax',
                                              url : 'dic/getDicTreeList.do', //请求url
                                              extraParams:{},
                                              reader: {
                                                  type: 'json'
                                              }
                                            },
                                            root: {
                                              text: '根目录',
                                              id: DEFAULT_ROOT_UUID
                                            },
                                            scope:this,
                                            clearOnLoad: true,
                                            listeners: {
                                            	'beforeload':function(store, operation, eOpts){

            	                                	try {
            	  	              	        			var data;
            		  	              	        		var gridPanel = Ext.getCmp("DicTypeTreePanel");
            			  	              	            var selectionModel = gridPanel.getSelectionModel();
            			  	              	            if(selectionModel.getCount()<=0){
            			  	              	            	data=Ext.getCmp("DicGridPanel").getSelectionModel().getSelection()[0].data.type;
            			  	              	            }else{
            			  	              	            	data = selectionModel.getSelection()[0].data.id;
            			  	              	            }
            	  	              						var new_params = {
            	  	              								'type' : data
            	  	              						}
            	  	              						Ext.apply(store.proxy.extraParams, new_params);
            	  	              					} catch (e) {
            	  	              						Ext.Msg.show({
            	  	              							title : '提示',
            	  	              							msg :'获取选中节点出错 !',
            	  	              							buttons : Ext.Msg.OK,
            	  	              							icon : Ext.MessageBox.ERROR
            	  	              						});
            	  	              					} 
                                            	}
                                             }
                                          })
                                    },
                                    {
                                        xtype: 'numberfield',
                                        anchor: '100%',
                                        fieldLabel: '排序',
                                        name: 'orderBy'
                                    },
                                    {
                                        xtype: 'textareafield',
                                        anchor: '100%',
                                        height: 100,
                                        fieldLabel: '说明',
                                        name: 'note'
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
                                            id:'Dic_save_btn',
                                            hidden:exists('Dic_save_btn'),
                                            text: '保存'
                                        },
                                        {
                                            xtype: 'button',
                                            icon: 'img/close.png',
                                            id:'Dic_close_btn',
                                            hidden:exists('Dic_close_btn'),
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