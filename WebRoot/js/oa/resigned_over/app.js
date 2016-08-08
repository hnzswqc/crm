/*
 * File: app.js
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

//@require @packageOverrides
Ext.Loader.setConfig({
    enabled: true
});
//Ext.Loader.setPath('AuditItem', extBasePath+'js/audit/auditItem');
//Ext.Loader.setPath('proof', extBasePath+'js/audit/proof');
Ext.application({
	 controllers: [
       'ResignedController'
    ],
    models: [
        'Resigned'
    ],
    stores: [
        'ResignedStore',
        'ResignedMoneyTypeStore',
        'ResignedTypeStore',
        'ResignedStateStore',
        'BankCardStateStore'
    ],
    views: [
        'MyViewport',
        'ResignedWindow',
        'ShowWindow'
    ],
    // 应用的根目录
    appFolder : extBasePath + 'js/oa/resigned_over',
    appProperty: 'resigned',
    autoCreateViewport: true,
    name: 'Resigned'
});
