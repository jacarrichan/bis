Ext.define('Desktop.model.Node', { 
    extend: 'Ext.data.Model',
	fields : [{name : "id",type : "string"},
			{name : "text",type : "string"},
			{name : "iconCls",type : "string"},
			{name : "leaf",type : "boolean"},
			{name : 'type'},
			{name : 'component'}]
});