<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String extLibPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/js/ext4";
	String ctx = request.getContextPath();
	pageContext.setAttribute("extLibPath", extLibPath);
	pageContext.setAttribute("ctx", ctx);
%>
<html>
	<head>
		<title>Business infrastructure platform</title>
		<style type="text/css">
			.x-panel-ghost {
			    z-index: 1;
			}
			.x-border-layout-ct {
			    background: #DFE8F6;
			}
			.x-portal-body {
			    padding: 0 0 0 8px;
			}
			.x-portal .x-portal-column {
			    padding: 8px 8px 0 0;
			}
			.x-portal .x-panel-dd-spacer {
			    border: 2px dashed #99bbe8;
			    background: #f6f6f6;
			    border-radius: 4px;
			    -moz-border-radius: 4px;
			    margin-bottom: 10px;
			}
			.x-portlet {
			    margin-bottom:10px;
			    padding: 1px;
			}
			.x-portlet .x-panel-body {
			    background: #fff;
			}
			.portlet-content {
			    padding: 10px;
			    font-size: 11px;
			}
		</style>
	</head>
	<body>
		<div id="loading-tip" style="border-radius:3px;position: absolute;z-index: 1;border: solid 1px #ccc;background-color: #fff;padding: 10px;">
			<div class="loading-indicator" style="color: #444;font: bold 13px tahoma, arial, helvetica;padding: 10px;height: auto;">
				<img src="${ctx}/images/loading32.gif" width="31" height="31"
					style="margin-right: 8px; float: left; vertical-align: top;" />
				Business infrastructure platformV1.0
				<br />
				<span id="loading-msg" style="font: normal 10px arial, tahoma, sans-serif;">加载样式和图片...</span>
			</div>
		</div>
		<script type="text/javascript">
			var extLibPath = "${extLibPath}";
			var ctx = "${ctx}";
			var tip = document.getElementById("loading-tip");
			tip.style.top = (document.body.scrollHeight - tip.style.top - 100) / 2;
			tip.style.left = (document.body.scrollWidth - 200) / 2;
		</script>
		<link rel="stylesheet" type="text/css" href="${extLibPath}/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="icon.css" />
		<script type="text/javascript">
			document.getElementById("loading-msg").innerHTML = "加载核心组件...";
		</script>
		<script type="text/javascript" src="${extLibPath}/ext-all-debug.js"></script>
		<script type="text/javascript" src="${extLibPath}/locale/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="app.js"></script>
	</body>
</html>
