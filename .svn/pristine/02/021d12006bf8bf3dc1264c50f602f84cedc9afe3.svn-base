<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/web/script/json2.js"></script>


<script type="text/javascript">  
/*     function modelrow(sequence,name,feeType,children){  
        this.sequence=sequence;  
        this.name=name;  
        this.feeType=feeType;  
        this.children=children;  
    }  
    
    
    var mr1 = new Array(); 
    
    var mr2 = new Array(); 
    mr2[0] = new modelrow("1.1.1.1","购置设备费费",1,null);
    mr2[1] = new modelrow("1.1.1.2","试制设备费",1,null);
    mr2[2] = new modelrow("1.1.1.3","设备改造与租赁费",1,null);
    
    mr1[0] = new modelrow("1.1.1","设备费",1,mr2);
    
    mr1[1] = new modelrow("1.1.2","材料费",1,null);
    mr1[2] = new modelrow("1.1.3","测试化验加工费",1,null);
  
    var root = new modelrow("1.1","直接费用",1,mr1);  
             
              
                
    var jsonModel= $.parseJSON(root);
  
    alert( jsonModel); */
    
    var root = {
    "project_id":1,
    "datas":[
      {
        "modelrow_id":36,
        "designT":30000.00,
        "realT":0,
        "designA":10000.00,
        "realA":0,
        "designB":20000.00,
        "realB":0,
        "calBasis":"阿里云华东地域云服务器ECS，配置为16核64G内存、系统盘容量512G、数据盘容量2T、公网宽带30Mbps，每台包年费用5万元，需使用2年，费用总计5万元/年·台*2年*1台=10万元。"
      },
       {
        "modelrow_id":37,
        "designT":10000.00,
        "realT":0,
        "designA":5000.00,
        "realA":0,
        "designB":5000.00,
        "realB":0,
        "calBasis":"阿里云华东地域云服务器ECS，配置为16核64G内存、系统盘容量512G、数据盘容量2T、公网宽带30Mbps，每台包年费用5万元，需使用2年，费用总计5万元/年·台*2年*1台=10万元。"
      }
    ]
  }
    
    var jsonData = JSON.stringify(root)
    alert(jsonData);
    
    function test() {
		$.ajax({
			url : "<%=request.getContextPath()%>/restful/table/data/add",
			dataType: 'json', 
			type : "post",
			data : {
               jsonData:jsonData
			},
			success : function(result) {
				var r = $.parseJSON(result);
				if(r.retcode=="1"){
				   alert(r.errorMsg);
				}
			}
		});
	} 
  
     
 </script>

</html>
