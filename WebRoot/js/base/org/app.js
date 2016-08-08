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
        'OrgController'
    ],
    models: [
        'OrgTreeNode',
        'OrgModel',
        'OrgRoleModel'
    ],
    stores: [
        'OrgTreeNodeStore',
        'OrgStore',
        'OrgStateStore',
        'OrgGradeStore'
    ],
    views: [
        'OrgViewport',
        'OrgWindow',
        'RoleWindow'
    ],
    // 应用的根目录
    appFolder : extBasePath + 'js/base/org',
    appProperty: 'org',
    autoCreateViewport: true,
    name: 'Org'
});
