/*
 * File: power/store/PowerModelTreeStore.js
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

Ext.define('Model.store.ModelStateStore', {
    extend: 'Ext.data.Store',

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
        	fields:["id","text"],
            storeId: 'ModelStateStore',
            autoLoad:true,
            proxy: {
	        	url:'dic/getParamList.do',
	            type: 'ajax',
	            extraParams:{
	        		'type':DIC_STATE
	        	},
	            reader: {
	    			type: 'json',
		   	  	 	//数据格式为json   
		            root: 'data'
	                
	            }
            }
        }, cfg)]);
    }
});