Ext.define('Fes.system.role.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.rolelist',
	title : '角色列表',
	iconCls : 'icon-grid-list',
	rowLines : true,
	columnLines : true,
	multiSelect : true,
	simpleSelect : true,
	viewConfig : {
		loadingText : '正在加载角色列表'
	},
	columns : [{
				xtype : 'rownumberer'
			}, {
				text : '名称',
				width : 250,
				sortable : true,
				dataIndex : 'name',
				field : {
					xtype : 'textfield',
					required : true
				}
			}, {
				header : '代码',
				width : 100,
				sortable : true,
				dataIndex : 'code',
				field : {
					xtype : 'textfield'
				}
			}, {
				text : '级别',
				width : 100,
				sortable : true,
				dataIndex : 'roleLevel',
				field : {
					xtype : 'numberfield'
				}
			}, {
				text : '描述',
				flex : 1,
				sortable : true,
				dataIndex : 'description',
				field : {
					xtype : 'textarea'
				}
			}],
	// store : [Ext.create('Fes.system.role.Store')],
	initComponent : function() {
		this.createStore();
		this.rowEditor = Ext.create('Ext.grid.plugin.RowEditing', {
			listeners : {
				edit : function(editor, e) {
					e.record.save({
								success : function(role, options) {
									var data = Ext
											.decode(options.response.responseText);
									if (data.extra) {
										role.set('id', data.extra);
									}
									role.commit();
								}
							});

				}
			}
		});
		this.plugins = [this.rowEditor], this.tbar = this.createToolbar();
		this.bbar = {
			xtype : 'pagingtoolbar',
			store : this.store,
			displayInfo : true
		};
		this.callParent();
	},

	createStore : function() {
		var me = this;
		me.store = Ext.create('Fes.system.role.Store');
	},

	addRecord : function() {
		var record = new Fes.system.role.Model({
					name : '新建角色'
				});
		this.getStore().add(record);
		this.rowEditor.startEdit(record, 1);
	},

	deleteRecord : function() {
		var me = this;
		var records = this.getSelectionModel().getSelection();
		if (records.length > 0) {
			Fes.MsgBox.confirm('确定删除这' + records.length + '个角色?',
					function(btn) {
						Ext.each(records, function(record) {
									if (Ext.Array.contains(me.getStore()
													.getNewRecords(), record)) {
										me.getStore().remove(record);
									} else {
										record.destroy({
													success : function() {
														me.store.remove(record);
													}
												});
									}
								});
					});
		}

	},

	createToolbar : function() {
		var me = this;

		return Ext.create('Ext.toolbar.Toolbar', {
					items : [{
								xtype : 'textfield',
								fieldLabel : '名称',
								labelWidth : 40,
								flex : 1,
								id : 'role_name'
							}, '-', {
								xtype : 'textfield',
								fieldLabel : '代码',
								labelWidth : 40,
								flex : 1
							}, '-', {
								xtype : 'button',
								text : '查询',
								iconCls : 'icon-search',
								handler : function() {
									me.getStore().load({
										params : {
											name : Ext.getCmp('role_name').getValue()
										}
									});
								}
							}, '-', Ext.create('Ext.Button', {
										text : '添加',
										iconCls : 'icon-add',
										handler : me.addRecord,
										scope : me
									}), '-', {
								xtype : 'button',
								text : '删除',
								iconCls : 'icon-del',
								handler : me.deleteRecord,
								scope : me
							}]
				});
	}
});