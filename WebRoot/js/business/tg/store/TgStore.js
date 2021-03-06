/*
 * File: bz/store/BzStore.js
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

Ext.define('Tg.store.TgStore', {
    extend: 'Ext.data.Store',

    requires: [
        'Tg.model.Logging'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            model: 'Tg.model.Logging',
            storeId: 'BzStore',
            groupField: 'orgName',
            proxy: {
                type: 'ajax',
                url: 'tg/getTgPage.do',
                extraParams: {"loggingType":LOGGING_TYPE_TG},
                reader: {
		        	type: 'json',
		   	  	 	//数据格式为json   
		            root: 'data',
		            //获取数据总数 
		            totalProperty:'dataSize'
                }
            }
        }, cfg)]);
    }
});