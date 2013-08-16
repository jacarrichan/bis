Ext.define('Desktop.store.Tree', {
		extend : 'Ext.data.TreeStore',
		model : 'MVCApp.model.Model',
		proxy : {
			type : "ajax", // 获取方式
			url : "app?action=node" // 获取树节点的地址
		},
		clearOnLoad : true,
		nodeParam : "id"// 设置传递给后台的参数名,值是树节点的id属性
	});