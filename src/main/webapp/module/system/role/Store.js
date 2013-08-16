Ext.define('Fes.system.role.Store', {
			extend : 'Ext.data.Store',
			model : 'Fes.system.role.Model',
			pageSize : 20,
			autoLoad : true,
			remoteSort : true
		});