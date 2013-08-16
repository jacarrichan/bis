Ext.define('Desktop.view.Navigation', {
	   	    alias: 'widget.navigation',
			extend : 'Ext.panel.Panel',
			region : 'west',
			title : '系统菜单',
			width : 250,
			iconCls : "icon-tree",
			autoScroll : false,
			layout : 'accordion',
			collapsible : true,
			layoutConfig : {
				animate : true
			},
			id : 'navigation',
			split : true,
			initComponent : function(){
				this.callParent();
			}
		});