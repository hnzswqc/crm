/*
 * File: product/store/ProduceStore.js
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

Ext.define('Pg.store.ProductStore', {
    extend: 'Ext.data.Store',

    requires: [
        'Pg.model.Product'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            model: 'Pg.model.Product',
            storeId: 'ProductStore',
            autoLoad:false,
            proxy: {
                type: 'ajax',
                url: 'product/getProductPage.do',
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