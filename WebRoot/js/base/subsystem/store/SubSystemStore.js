/*
 * File: subsystem/store/SubSystemStore.js
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

Ext.define('Subsystem.store.SubSystemStore', {
    extend: 'Ext.data.Store',

    requires: [
        'Subsystem.model.SubSystemModel'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            model: 'Subsystem.model.SubSystemModel',
            storeId: 'SubSystemStore',
            autoLoad:false,
            proxy: {
                type: 'ajax',
                url: 'sub/getSubsystemList.do',
                reader: {
                    type: 'json',
                    root: 'data',
                    totalProperty: 'dataSize'
                }
            }
        }, cfg)]);
    }
});