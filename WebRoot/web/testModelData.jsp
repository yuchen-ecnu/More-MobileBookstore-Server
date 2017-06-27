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
        "project_id": 2,
        "id": 57,
        "fid": 0,
        "sequence": "1.1",
        "name": "直接费yong",
        "feeType": 1,
        "data": {
            "id": null,
            "name": null,
            "sequence": "1.1",
            "branch_id": null,
            "calBasis": null,
            "isBranch": 0,
            "designT": null,
            "realT": null,
            "mainSetT": null,
            "childSetT": null,
            "isLockT": null,
            "isLockA": null,
            "isLockB": null,
            "designA": null,
            "realA": null,
            "mainSetA": null,
            "childSetA": null,
            "designB": null,
            "realB": null,
            "mainSetB": null,
            "childSetB": null
        },
        "subDatas": [
            {
                "id": null,
                "name": "交大",
                "sequence": "1.1",
                "branch_id": 2,
                "calBasis": null,
                "isBranch": 1,
                "designT": null,
                "realT": null,
                "mainSetT": null,
                "childSetT": null,
                "isLockT": null,
                "isLockA": null,
                "isLockB": null,
                "designA": null,
                "realA": null,
                "mainSetA": null,
                "childSetA": null,
                "designB": null,
                "realB": null,
                "mainSetB": null,
                "childSetB": null
            },
            {
                "id": null,
                "name": "华师大",
                "sequence": "1.1",
                "branch_id": 1,
                "calBasis": null,
                "isBranch": 1,
                "designT": null,
                "realT": null,
                "mainSetT": null,
                "childSetT": null,
                "isLockT": null,
                "isLockA": null,
                "isLockB": null,
                "designA": null,
                "realA": null,
                "mainSetA": null,
                "childSetA": null,
                "designB": null,
                "realB": null,
                "mainSetB": null,
                "childSetB": null
            }
        ],
        "children": [
            {
                "project_id": 2,
                "id": 58,
                "fid": 57,
                "sequence": "1.1.1",
                "name": "设备费",
                "feeType": 2,
                "data": {
                    "id": null,
                    "name": null,
                    "sequence": "1.1.1",
                    "branch_id": null,
                    "calBasis": null,
                    "isBranch": 0,
                    "designT": null,
                    "realT": null,
                    "mainSetT": null,
                    "childSetT": null,
                    "isLockT": null,
                    "isLockA": null,
                    "isLockB": null,
                    "designA": null,
                    "realA": null,
                    "mainSetA": null,
                    "childSetA": null,
                    "designB": null,
                    "realB": null,
                    "mainSetB": null,
                    "childSetB": null
                },
                "subDatas": [
                    {
                        "id": null,
                        "name": "交大",
                        "sequence": "1.1.1",
                        "branch_id": 2,
                        "calBasis": null,
                        "isBranch": 1,
                        "designT": null,
                        "realT": null,
                        "mainSetT": null,
                        "childSetT": null,
                        "isLockT": null,
                        "isLockA": null,
                        "isLockB": null,
                        "designA": null,
                        "realA": null,
                        "mainSetA": null,
                        "childSetA": null,
                        "designB": null,
                        "realB": null,
                        "mainSetB": null,
                        "childSetB": null
                    },
                    {
                        "id": null,
                        "name": "华师大",
                        "sequence": "1.1.1",
                        "branch_id": 1,
                        "calBasis": null,
                        "isBranch": 1,
                        "designT": null,
                        "realT": null,
                        "mainSetT": null,
                        "childSetT": null,
                        "isLockT": null,
                        "isLockA": null,
                        "isLockB": null,
                        "designA": null,
                        "realA": null,
                        "mainSetA": null,
                        "childSetA": null,
                        "designB": null,
                        "realB": null,
                        "mainSetB": null,
                        "childSetB": null
                    }
                ],
                "children": [
                    {
                        "project_id": 2,
                        "id": 59,
                        "fid": 58,
                        "sequence": "1.1.1.1",
                        "name": "购置设备费费",
                        "feeType": 2,
                        "data": {
                            "id": null,
                            "name": null,
                            "sequence": "1.1.1.1",
                            "branch_id": null,
                            "calBasis": null,
                            "isBranch": 0,
                            "designT": null,
                            "realT": null,
                            "mainSetT": null,
                            "childSetT": null,
                            "isLockT": null,
                            "isLockA": null,
                            "isLockB": null,
                            "designA": null,
                            "realA": null,
                            "mainSetA": null,
                            "childSetA": null,
                            "designB": null,
                            "realB": null,
                            "mainSetB": null,
                            "childSetB": null
                        },
                        "subDatas": [
                            {
                                "id": null,
                                "name": "交大",
                                "sequence": "1.1.1.1",
                                "branch_id": 2,
                                "calBasis": null,
                                "isBranch": 1,
                                "designT": null,
                                "realT": null,
                                "mainSetT": null,
                                "childSetT": null,
                                "isLockT": null,
                                "isLockA": null,
                                "isLockB": null,
                                "designA": null,
                                "realA": null,
                                "mainSetA": null,
                                "childSetA": null,
                                "designB": null,
                                "realB": null,
                                "mainSetB": null,
                                "childSetB": null
                            },
                            {
                                "id": null,
                                "name": "华师大",
                                "sequence": "1.1.1.1",
                                "branch_id": 1,
                                "calBasis": null,
                                "isBranch": 1,
                                "designT": null,
                                "realT": null,
                                "mainSetT": null,
                                "childSetT": null,
                                "isLockT": null,
                                "isLockA": null,
                                "isLockB": null,
                                "designA": null,
                                "realA": null,
                                "mainSetA": null,
                                "childSetA": null,
                                "designB": null,
                                "realB": null,
                                "mainSetB": null,
                                "childSetB": null
                            }
                        ],
                        "children": [
                            
                        ]
                    },
                    {
                        "project_id": 2,
                        "id": 60,
                        "fid": 58,
                        "sequence": "1.1.1.2",
                        "name": "试制设备费",
                        "feeType": 2,
                        "data": {
                            "id": null,
                            "name": null,
                            "sequence": "1.1.1.2",
                            "branch_id": null,
                            "calBasis": null,
                            "isBranch": 0,
                            "designT": null,
                            "realT": null,
                            "mainSetT": null,
                            "childSetT": null,
                            "isLockT": null,
                            "isLockA": null,
                            "isLockB": null,
                            "designA": null,
                            "realA": null,
                            "mainSetA": null,
                            "childSetA": null,
                            "designB": null,
                            "realB": null,
                            "mainSetB": null,
                            "childSetB": null
                        },
                        "subDatas": [
                            {
                                "id": null,
                                "name": "交大",
                                "sequence": "1.1.1.2",
                                "branch_id": 2,
                                "calBasis": null,
                                "isBranch": 1,
                                "designT": null,
                                "realT": null,
                                "mainSetT": null,
                                "childSetT": null,
                                "isLockT": null,
                                "isLockA": null,
                                "isLockB": null,
                                "designA": null,
                                "realA": null,
                                "mainSetA": null,
                                "childSetA": null,
                                "designB": null,
                                "realB": null,
                                "mainSetB": null,
                                "childSetB": null
                            },
                            {
                                "id": null,
                                "name": "华师大",
                                "sequence": "1.1.1.2",
                                "branch_id": 1,
                                "calBasis": null,
                                "isBranch": 1,
                                "designT": null,
                                "realT": null,
                                "mainSetT": null,
                                "childSetT": null,
                                "isLockT": null,
                                "isLockA": null,
                                "isLockB": null,
                                "designA": null,
                                "realA": null,
                                "mainSetA": null,
                                "childSetA": null,
                                "designB": null,
                                "realB": null,
                                "mainSetB": null,
                                "childSetB": null
                            }
                        ],
                        "children": [
                            
                        ]
                    },
                    {
                        "project_id": 2,
                        "id": 61,
                        "fid": 58,
                        "sequence": "1.1.1.3",
                        "name": "设备改造与租赁费",
                        "feeType": 2,
                        "data": {
                            "id": null,
                            "name": null,
                            "sequence": "1.1.1.3",
                            "branch_id": null,
                            "calBasis": null,
                            "isBranch": 0,
                            "designT": null,
                            "realT": null,
                            "mainSetT": null,
                            "childSetT": null,
                            "isLockT": null,
                            "isLockA": null,
                            "isLockB": null,
                            "designA": null,
                            "realA": null,
                            "mainSetA": null,
                            "childSetA": null,
                            "designB": null,
                            "realB": null,
                            "mainSetB": null,
                            "childSetB": null
                        },
                        "subDatas": [
                            {
                                "id": null,
                                "name": "交大",
                                "sequence": "1.1.1.3",
                                "branch_id": 2,
                                "calBasis": null,
                                "isBranch": 1,
                                "designT": null,
                                "realT": null,
                                "mainSetT": null,
                                "childSetT": null,
                                "isLockT": null,
                                "isLockA": null,
                                "isLockB": null,
                                "designA": null,
                                "realA": null,
                                "mainSetA": null,
                                "childSetA": null,
                                "designB": null,
                                "realB": null,
                                "mainSetB": null,
                                "childSetB": null
                            },
                            {
                                "id": null,
                                "name": "华师大",
                                "sequence": "1.1.1.3",
                                "branch_id": 1,
                                "calBasis": null,
                                "isBranch": 1,
                                "designT": null,
                                "realT": null,
                                "mainSetT": null,
                                "childSetT": null,
                                "isLockT": null,
                                "isLockA": null,
                                "isLockB": null,
                                "designA": null,
                                "realA": null,
                                "mainSetA": null,
                                "childSetA": null,
                                "designB": null,
                                "realB": null,
                                "mainSetB": null,
                                "childSetB": null
                            }
                        ],
                        "children": [
                            
                        ]
                    }
                ]
            },
            {
                "project_id": 2,
                "id": 62,
                "fid": 57,
                "sequence": "1.1.2",
                "name": "材料费",
                "feeType": 3,
                "data": {
                    "id": null,
                    "name": null,
                    "sequence": "1.1.2",
                    "branch_id": null,
                    "calBasis": null,
                    "isBranch": 0,
                    "designT": null,
                    "realT": null,
                    "mainSetT": null,
                    "childSetT": null,
                    "isLockT": null,
                    "isLockA": null,
                    "isLockB": null,
                    "designA": null,
                    "realA": null,
                    "mainSetA": null,
                    "childSetA": null,
                    "designB": null,
                    "realB": null,
                    "mainSetB": null,
                    "childSetB": null
                },
                "subDatas": [
                    {
                        "id": null,
                        "name": "交大",
                        "sequence": "1.1.2",
                        "branch_id": 2,
                        "calBasis": null,
                        "isBranch": 1,
                        "designT": null,
                        "realT": null,
                        "mainSetT": null,
                        "childSetT": null,
                        "isLockT": null,
                        "isLockA": null,
                        "isLockB": null,
                        "designA": null,
                        "realA": null,
                        "mainSetA": null,
                        "childSetA": null,
                        "designB": null,
                        "realB": null,
                        "mainSetB": null,
                        "childSetB": null
                    },
                    {
                        "id": null,
                        "name": "华师大",
                        "sequence": "1.1.2",
                        "branch_id": 1,
                        "calBasis": null,
                        "isBranch": 1,
                        "designT": null,
                        "realT": null,
                        "mainSetT": null,
                        "childSetT": null,
                        "isLockT": null,
                        "isLockA": null,
                        "isLockB": null,
                        "designA": null,
                        "realA": null,
                        "mainSetA": null,
                        "childSetA": null,
                        "designB": null,
                        "realB": null,
                        "mainSetB": null,
                        "childSetB": null
                    }
                ],
                "children": [
                    
                ]
            },
            {
                "project_id": 2,
                "id": 63,
                "fid": 57,
                "sequence": "1.1.3",
                "name": "测试化验加工费",
                "feeType": 4,
                "data": {
                    "id": null,
                    "name": null,
                    "sequence": "1.1.3",
                    "branch_id": null,
                    "calBasis": null,
                    "isBranch": 0,
                    "designT": null,
                    "realT": null,
                    "mainSetT": null,
                    "childSetT": null,
                    "isLockT": null,
                    "isLockA": null,
                    "isLockB": null,
                    "designA": null,
                    "realA": null,
                    "mainSetA": null,
                    "childSetA": null,
                    "designB": null,
                    "realB": null,
                    "mainSetB": null,
                    "childSetB": null
                },
                "subDatas": [
                    {
                        "id": null,
                        "name": "交大",
                        "sequence": "1.1.3",
                        "branch_id": 2,
                        "calBasis": null,
                        "isBranch": 1,
                        "designT": null,
                        "realT": null,
                        "mainSetT": null,
                        "childSetT": null,
                        "isLockT": null,
                        "isLockA": null,
                        "isLockB": null,
                        "designA": null,
                        "realA": null,
                        "mainSetA": null,
                        "childSetA": null,
                        "designB": null,
                        "realB": null,
                        "mainSetB": null,
                        "childSetB": null
                    },
                    {
                        "id": null,
                        "name": "华师大",
                        "sequence": "1.1.3",
                        "branch_id": 1,
                        "calBasis": null,
                        "isBranch": 1,
                        "designT": null,
                        "realT": null,
                        "mainSetT": null,
                        "childSetT": null,
                        "isLockT": null,
                        "isLockA": null,
                        "isLockB": null,
                        "designA": null,
                        "realA": null,
                        "mainSetA": null,
                        "childSetA": null,
                        "designB": null,
                        "realB": null,
                        "mainSetB": null,
                        "childSetB": null
                    }
                ],
                "children": [
                    
                ]
            }
        ]
    }

    
    var jsonData = JSON.stringify(root)
    alert(jsonData);
    
    function test() {
		$.ajax({
			url : "<%=request.getContextPath()%>/restful/table/modeldata/add",
			dataType: 'json', 
			type : "post",
			data : {
               jsonModelData:jsonData,
               institution_id:null
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
