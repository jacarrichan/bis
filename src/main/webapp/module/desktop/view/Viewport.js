Ext.define('Desktop.view.Viewport', {
			extend : 'Ext.container.Viewport',
			requires : ['Desktop.view.Container', 'Desktop.view.Header',
					'Desktop.view.Navigation'],

			layout : 'border',
			initComponent : function() {
				var me = this;
				Ext.apply(me, {
							items : [Ext.create('Desktop.view.Container'),
									Ext.create('Desktop.view.Header'),
									Ext.create('Desktop.view.Navigation')]
						});
				this.callParent(arguments);
			}
		});