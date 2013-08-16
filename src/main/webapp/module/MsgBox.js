Ext.define('Fes.MsgBox', {

			extend : 'Ext.util.Observable',

			statics : {
				confirm : function(text, fn) {
					var result;
					Ext.MessageBox.confirm('系统提示', text, function(btn) {
								if (btn == 'yes') {
									fn();
								}
							});
				}
			}
		});