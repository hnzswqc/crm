/*
 * File: mainFrame/view/ChoseUserWindow.js
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

Ext.define('mainFrame.view.ChoseUserWindow', {
    extend: 'Ext.window.Window',

    height: 508,
    id: 'ChoseUserWindow',
    width: 686,
    layout: {
        type: 'fit'
    },
    title: '选择人员',
    maximizable: true,
    modal: true,

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'container',
                    width: 150,
                    layout: {
                        type: 'border'
                    },
                    items: [
                        {
                            xtype: 'gridpanel',
                            region: 'center',
                            margin: '0 2 2 0',
                            title: '组织人员',
                            id:'choseGridPanel',
                            store:'UserGridPanelStore',
                            columns: [
                                {
                                    xtype: 'rownumberer',
                                    width: 33,
                                    text: '序号'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 82,
                                    dataIndex: 'userName',
                                    text: '名称',
                                    renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                                		if(record.data.count>0||SESSION_USER_UUID==record.data.userUuid){
                                			return "<span style='color:red'>"+data+"</span>";
                                		}else{
                                			return data;
                                		}
        		                  	}
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userId',
                                    text: '帐号'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userMobile',
                                    text: '手机'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userPhone',
                                    text: '办公电话'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userMail',
                                    hidden:true,
                                    text: '邮箱'
                                },
                                
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userGender',
                                    hidden:true,
                                    text: '性别',
                                    renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                                		return record.data.userGenderText;
        		                  	}
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userState',
                                    hidden:true,
                                    text: '状态',
                                    renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                                		return record.data.userStateText;
        		                  	}
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'joinTime',
                                    hidden:true,
                                    text: '入职时间'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userUnversity',
                                    hidden:true,
                                    text: '毕业院校'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userDegrees',
                                    hidden:true,
                                    text: '学历',
                                    renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                                		return record.data.userDegreesText;
        		                  	}
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userAddress',
                                    hidden:true,
                                    text: '籍贯/住址'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userBirthday',
                                    text: '生日',
                                    hidden:true,
                                    renderer: function (data, metadata, record, rowIndex, columnIndex, store) {
                                		if(data!='1900-01-01'){
                                			return data;
                                		}
        		                  	}
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userNote',
                                    hidden:true,
                                    text: '备注'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'userOrderBy',
                                    hidden:true,
                                    text: '排序'
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    store:'UserGridPanelStore',
                                    displayInfo: true
                                }
                            ],
                            selModel: Ext.create('Ext.selection.CheckboxModel', {
                            	injectCheckbox:1,//checkbox位于哪一列，默认值为0
                            	handleMouseDown : Ext.emptyFn,
                        	    mode: 'SIMPLE',
                        	    listeners: {
                            		beforeselect: {
                                        fn: me.onCheckboxModelBeforeSelect,
                                        scope: me
                                    }
                                }
                            })
                        },
                        {
                            xtype: 'treepanel',
                            region: 'west',
                            split: true,
                            margin: '0 0 2 2',
                            width: 200,
                            collapsible: true,
                            title: '组织机构',
                            rootVisible: false,
                            id:'orgTreePanel',
                            store:Ext.create("mainFrame.store.OrgTreeStore",{autoLoad:true})
                        },
                        {
                            xtype: 'toolbar',
                            region: 'north',
                            //height: 40,
                            margin: 2,
                            items: [
                                {
                                    xtype: 'button',
                                    id: 'chose_user_refresh_btn',
                                    icon: 'img/reload.png',
                                    text: '刷新'
                                },
                                {
                                    xtype: 'button',
                                    id: 'chose_user_save_btn',
                                    icon: 'img/confirm.png',
                                    text: '确定'
                                },
                                {
                                    xtype: 'button',
                                    id: 'chose_user_close_btn',
                                    icon: 'img/close.png',
                                    text: '关闭'
                                },
                                {
                                    xtype: 'hiddenfield',
                                    id: 'user_authority_type',
                                    fieldLabel: '类别'
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    },
    onCheckboxModelBeforeSelect: function(rowmodel, record, index, eOpts) {
    	if(record.data.count>0||SESSION_USER_UUID==record.data.userUuid){
    		return false;
    	}else{
    		return true;
    	}
    }

});