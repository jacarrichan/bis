
Ext.define('Desktop.view.Container', {
		   	alias: 'widget.fescontainer',
			extend : 'Ext.tab.Panel',
			requires : ['Ext.app.Portlet', 'Ext.app.PortalColumn', 'Ext.app.PortalPanel',
					   'Ext.app.PortalDropZone', 'Ext.ux.TabReorderer','Ext.ux.TabCloseMenu'],
			activeTab : 0,
			enableTabScroll : true,
			animScroll : true,
			border : true,
			autoScroll : true,
			region : 'center',
			split : true,
			plugins : [
				Ext.create('Ext.ux.TabReorderer'),
				Ext.create('Ext.ux.TabCloseMenu',{
		        		  	closeTabText: '关闭面板',
		        		  	closeOthersTabsText: '关闭其他',
		        		  	closeAllTabsText: '关闭所有'
		        		  })
			],
			items : [{
						iconCls : 'icon-activity',
						title : '平台首页',
						xtype : 'portalpanel',
						layout : 'column',
						items : [{
									xtype : 'portalcolumn',
									columnWidth : 0.7,
									items : [{
												title : '新闻动态',
												height : 150,
												iconCls : 'icon-news'
											}, {
												title : '最新通知',
												height : 150,
												iconCls : 'icon-notice'
											}, {
												title : '业绩报表',
												height : 150,
												iconCls : 'icon-chart'
											}, {
												title : '邮件列表',
												height : 150,
												iconCls : 'icon-email-list'
											}]
								}, {
									xtype : 'portalcolumn',
									columnWidth : 0.3,
									items : [{
												title : '功能链接',
												height : 150,
												iconCls : 'icon-link'
											}, {
												title : '待办事项',
												height : 150,
												iconCls : 'icon-note'
											}, {
												title : '邮件列表',
												height : 150,
												iconCls : 'icon-email-list'
											}, {
												title : '邮件列表',
												height : 150,
												iconCls : 'icon-email-list'
											}]
								}]
					}]
		});