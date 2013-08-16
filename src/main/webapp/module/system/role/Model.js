Ext.define('Fes.system.role.Model', { 
    extend: 'Ext.data.Model',
	fields: [
	          {name : 'id',type:'int'},
			  {name : 'name',type:'string'},
			  {name : 'code',type:'string'},
			  {name : 'description',type:'string'},
			  {name : 'roleLevel'}
	        ],
	proxy : {
		type : 'rest',
		url : 'roles',
		reader : {
			type : 'json',
			root : 'root',
			totalProperty : 'total'// 数据的总数
		},
		writer : {
			type : 'json'
		}
	}
});