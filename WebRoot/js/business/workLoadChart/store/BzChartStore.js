/*
 * File: workLoadChart/store/BzChartStore.js
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

Ext.define('WorkLoadChart.store.BzChartStore', {
    extend: 'Ext.data.Store',

    requires: [
        'WorkLoadChart.model.Chart'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            model: 'WorkLoadChart.model.Chart',
            storeId: 'BzChartStore',
            autoLoad:false,
            proxy: {
                type: 'ajax',
                url: 'workLoadChart/bz.do',
                reader: {
                    type: 'json',
                    //数据格式为json   
		            root: 'data'
                    
                    
                }
            }
        }, cfg)]);
    }
});