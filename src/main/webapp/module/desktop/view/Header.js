Ext.define('Desktop.view.Header', {
	extend : 'Ext.panel.Panel',
	height : 80,
	html : 'Business infrastructure platform',
	region : 'north',
	split : true,
	bbar : [{
				iconCls : 'icon-user',
				text : '管理员'
			}, '-', {
				text : Ext.Date.format(new Date(), 'Y年m月d日')
			}, '->', {
				text : '退出',
				iconCls : 'icon-logout'
			}],
	bodyStyle : 'backgroud-color:#99bbe8;line-height : 50px;padding-left:20px;' +
			'font-size:22px;color:#000000;font-family:黑体;font-weight:bolder;'+ 
			'background: -webkit-gradient(linear, left top, left bottom, ' +
			'color-stop(0%, rgba(153,187, 232, 0.4) ),' +
			'color-stop(50%, rgba(153, 187, 232, 0.8) ),' +
			'color-stop(0%, rgba(153, 187, 232, 0.4) ) );' ,
	initComponent : function(){
		this.callParent();
	}
});