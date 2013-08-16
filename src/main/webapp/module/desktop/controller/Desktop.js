Ext.define('Desktop.controller.Desktop', {
	extend : 'Ext.app.Controller',

	models : ['Node'],

	refs : [{
				ref : 'navigation',
				selector : 'navigation'
			}, {
				ref : 'container',
				selector : 'fescontainer'
			}],

	init : function() {
		var me = this;
		this.control({
					'viewport' : {
						render : me.onRender
					},
					scope : me
				});
	},

	onRender : function() {
		var me = this;
		Ext.get('loading-msg').update('正在加载菜单...');
		Ext.Ajax.request({
					url : 'resource/root',// 获取面板的地址
					method : 'GET',
					callback : function(options, success, response) {
						me.createTree(Ext.JSON.decode(response.responseText));
					}
				});
	},

	createTree : function(datas) {
		var me = this;
		Ext.each(datas, function(data) {
					var tree = Ext.create("Ext.tree.Panel", {
								title : data.text,
								iconCls : data.iconCls,
								useArrows : true,
								autoScroll : true,
								rootVisible : false,
								viewConfig : {
									loadingText : "正在加载..."
								},
								store : me.createTreeStore(data.id)
							});
					tree.on('itemclick', me.onTreeItemClick, me);
					me.getNavigation().add(tree);
				});
		Ext.get('loading-msg').update('加载完成.');
		Ext.Function.defer(function() {
					Ext.get('loading-tip').remove();
				}, 1000);
	},

	onTreeItemClick : function(view, node) {
		console.log(node.data.component);
		var tab = this.getContainer();
		if (node.isLeaf()) { // 判断是否是根节点
			if (node.data.type === 'URL') { // 判断资源类型
				var panel = Ext.create('Ext.panel.Panel', {
					title : node.data.text,
					closable : true,
					iconCls : 'icon-activity',
					html : '<iframe width="100%" height="100%" frameborder="0" src="http://www.baidu.com"></iframe>'
				});
				tab.add(panel);
				tab.setActiveTab(panel);
			} else if (node.data.type === 'COMPONENT') {
				var panel = Ext.create(node.data.component, {
							title : node.data.text,
							closable : true,
							iconCls : 'icon-activity'
						});
				tab.add(panel);
				tab.setActiveTab(panel);
			}
		}
	},

	createTreeStore : function(id) {
		var me = this;
		return Ext.create("Ext.data.TreeStore", {
					defaultRootId : id, // 默认的根节点id
					model : this.getNodeModel().$className,
					proxy : {
						type : 'ajax', // 获取方式
						url : 'resource/child' // 获取树节点的地址
					},
					clearOnLoad : true,
					nodeParam : 'id'// 设置传递给后台的参数名,值是树节点的id属性
				});
	}
});