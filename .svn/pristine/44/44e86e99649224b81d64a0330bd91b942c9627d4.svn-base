<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body onload="test()">
	This is my JSP page.
	<br>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/web/script/jquery.min.js"></script>
<script type="text/javascript">
	function test() {
		$.ajax({
			url : "<%=request.getContextPath()%>/restful/template/preview",
			type : "post",
			data : {
               id:1
			},
			success : function(result) {
				var r = $.parseJSON(result);
				$.each(r.obj, function(idx, m) {
					document.write("success");
				});
			}
		});
	}
</script>
</html>
