Ext.define('Fes.system.spring.URLMapping', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.rolelist',
			iconCls : 'icon-grid-list',
			rowLines : true,
			columnLines : true,
			viewConfig : {
				loadingText : '正在加载角色列表'
			},
			columns : [{
						xtype : 'rownumberer'
					}, {
						text : '映射',
						columns : [{
									text : '路径',
									width : 200,
									sortable : true,
									dataIndex : 'url'
								}, {
									text : '需求',
									width : 100,
									sortable : true,
									dataIndex : 'consumes'
								}, {
									text : '自定义',
									width : 100,
									sortable : true,
									dataIndex : 'custom'
								}, {
									text : '头信息',
									width : 100,
									sortable : true,
									dataIndex : 'headers'
								}, {
									text : '参数值',
									width : 100,
									sortable : true,
									dataIndex : 'params'
								}, {
									text : '请求方法',
									width : 100,
									sortable : true,
									dataIndex : 'methods'
								}, {
									text : '处理',
									width : 100,
									sortable : true,
									dataIndex : 'produces'
								}]
					}, {
						text : '方法',
						width : 200,
						sortable : true,
						dataIndex : 'methodName'
					}, {
						text : '返回值',
						width : 350,
						sortable : true,
						dataIndex : 'returnType'
					}, {
						text : '注解',
						width : 300,
						sortable : true,
						dataIndex : 'annotationName'
					},{
						text : '参数',
						width : 300,
						sortable : true,
						dataIndex : 'parameters'
					}, {
						header : '类',
						width : 100,
						sortable : true,
						dataIndex : 'className',
						width : 500
					}],
			initComponent : function() {
				var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
				        groupHeaderTpl: '{name}({rows.length})',
				        hideGroupedHeader: true,
				        groupByText:'对该列进行分组',
				        showGroupsText : '是否分组'
				    });
				this.features = [groupingFeature];
				this.createStore();
				this.callParent();
			},

			createStore : function() {
				var me = this;
				Ext.define('Fes.system.spring.URLMappingModel', {
							extend : 'Ext.data.Model',
							fields : [{
										name : 'url',
										type : 'string'
									}, {
										name : 'className',
										type : 'string'
									}, {
										name : 'methodName'
									}, {
										name : 'returnType'
									}, {
										name : 'annotationName'
									}, {
										name : 'consumes'
									}, {
										name : 'custom'
									}, {
										name : 'headers'
									}, {
										name : 'params'
									}, {
										name : 'methods'
									}, {
										name : 'produces'
									},{
										name : 'parameters'
									}]
						});
				me.store = Ext.create('Ext.data.Store', {
							model : 'Fes.system.spring.URLMappingModel',
							groupField: 'className',
							autoLoad : true,
							proxy : {
								type : 'ajax',
								url : 'spring/url-mapping',
								reader : {
									type : 'json',
									root : 'root'
								}
							}
						});
			}

		});