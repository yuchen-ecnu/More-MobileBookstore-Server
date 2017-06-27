	var isInserting=new Object();	
	var isEditing=new Object();
	var columns=new Object(); 
	var source=new Object();
	var editHtml=new Object();
	/***
	 * author：gyq
	 * 用以下两个方法即可获得对应的列表或者相应行的元素
	 * 方法：
	 * getSelectList(tableId)		获取对应表格的list元素	ResultLists[tableId]=data.obj.list
	 * getSelectItem(tableId,index) 		获取对应表格的list元素的对应item（即表格对应行的数据,从零开始） 	ResultLists[tableId][index]=data.obj.list[index]
	 * findItemById(list, key, value) 详见函数
	 * 
	 * 数据：
	 * ResultLists——select后，返回回来的后台列表集合
	 * tableId——使用控件的表格的名字
	 * ResultLists[tableId]=data.obj.list
	 */
	var ResultLists=[];
	function getSelectList(tableId){//获取对应表格的list元素	ResultLists[tableId]=data.obj.list
		return ResultLists[tableId];
	}
	function getSelectItem(tableId,index){//获取对应表格的list元素的对应item（即表格对应行的数据,从零开始） 	ResultLists[tableId][index]=data.obj.list[index]
		return ResultLists[tableId][index];
	}
	
	function findItemById(list, key, value) {
		for ( var i in list) {
			if (list[i][key] == value)
				return list[i];
		}
		return null;
	}
	/***********************************************************************************************************************/
	
	function initTable(tableId,aoColumns,ajaxSource){
		if(columns[tableId]==null||columns[tableId]==""){
			columns[tableId]=aoColumns;
			source[tableId]=ajaxSource;
			//初始化排序
			initOrderPropoty(tableId);
		}
		//初始化分页框内容
		initPageDiv(tableId);
		//初始化checkbox
		initCheckbox(tableId);
		//加载表格数据
		selectItems(tableId);
	}
	
	/**************************初始化排序 开始**********************************/
	function initOrderPropoty(tableId){
		var thead=$("#"+tableId).find("thead").find("tr")[0];
		var selectTemplete=$("#"+tableId).find("#selectTemplete");
		if($(selectTemplete).find("#orderPropoty")==null||$(selectTemplete).find("#orderPropoty").length==0){
			$(selectTemplete).append("<input id='orderPropoty' type='hidden'/>");
		}
		if($(selectTemplete).find("#orderType")==null||$(selectTemplete).find("#orderType").length==0){
			$(selectTemplete).append("<input id='orderType' type='hidden'/>");
		}
		$.each(columns[tableId], function(index, item) {
			var mDataProp=item.mDataProp;
			var bSortable=item.bSortable;
			var defaultSort=item.defaultSort;
			if(bSortable==true){
				var $events = $(thead).find("#"+mDataProp).data("events");
				if( $events && $events["click"] ){
				}else{
					$(thead).find("#"+mDataProp).bind('click', function(event) {
						var orderType=$(selectTemplete).find("#orderType").val();
						if($(selectTemplete).find("#orderPropoty").val()==mDataProp){
							if(orderType==null || orderType==""){
								$(selectTemplete).find("#orderType").val("desc");
							}else{
								$(selectTemplete).find("#orderType").val("");
							}
						}else{
							$(selectTemplete).find("#orderPropoty").val(mDataProp);
							$(selectTemplete).find("#orderType").val("");
						}
						selectItems(tableId);
					});
				}
			}
			if(defaultSort==true){
				$(selectTemplete).find("#orderPropoty").val(mDataProp);
				$(selectTemplete).find("#orderType").val("");
			}
		});
		//alert($(selectTemplete).html());
	}
	/**************************初始化排序 结束**********************************/

	/**************************增删改查 开始**********************************/
	function selectItems(tableId){
		isInserting[tableId]=0;
		isEditing[tableId]=0;
		//初始化请求参数
		var postParams=new Object();
		var inputs=$("#"+tableId+" #selectTemplete").find("input[type=hidden]");
		$.each(inputs, function(index, item) {
			var param=$(item).attr("id");
			var sourceId=$(item).attr("sourceId");
			if(sourceId!=null){
				var value="";
				value=$("#"+sourceId).val();
				postParams[param]=value;
			}
			var sourceName=$(item).attr("sourceName");
			if(sourceName!=null){
				sourceObjs=$("*[name='"+sourceName+"']");
				var params=new Array();
				$.each(sourceObjs, function(index1, item1) {
					params[index1]=$(item1).val();
				});
				postParams[param]=JSON.stringify(params);
			}
			if(sourceName==null&&sourceId==null){
				postParams[param]=$(item).val();
			}
		});

		//处理分页框内容
		var pageDiv=$("div[paging-table='"+tableId+"']");
		if(pageDiv!=null&&pageDiv.length>0){
			var queryPageInput=$(pageDiv).find("#queryPage");
			if(queryPageInput==null||queryPageInput.length==0){
				$(pageDiv).append("<input id='queryPage' value='1' type='hidden'/>");
			}
			postParams['queryPage']=$(pageDiv).find("#queryPage").val();
			postParams['pageSize']=$(pageDiv).find("#pageSize").val();
		}
		
		$.ajax({
			type: "post",
			data:postParams,
			dataType:"json", //收受数据格式
			url:source[tableId]["select"],
			cache:false,
			async : false,
			success: function(data){
				if(data.retcode=="0000"){
					$("#"+tableId+" #dataTbody").empty();
					$("#"+tableId+" #headCheckbox").prop("checked",false);
					var thead=$("#"+tableId).find("thead").find("tr")[0];
					$.each(columns[tableId], function(index, item) {
						var mDataProp=item.mDataProp;
						$(thead).find("#"+mDataProp).nextAll().remove();
					});
		
					//显示层绑定控件
					var list=data.obj;
				
					if(data.obj.list!=null){
						ResultLists[tableId]=data.obj.list;//
						
						
						list=data.obj.list;
						var totalSize=data.obj.totalSize;
						var currentPage=$(pageDiv).find("#queryPage").val();
						var pageSize=postParams['pageSize']=$(pageDiv).find("#pageSize").val();
						var totalPage=Math.ceil(totalSize/pageSize);
						$(pageDiv).find("#currentPage").html(currentPage);
						$(pageDiv).find("#totalPage").html(totalPage);
						$(pageDiv).find("#totalSize").html(totalSize);
						if(currentPage==1){
							$(pageDiv).find("#pres").css("display","none");
							$(pageDiv).find("#pre").css("display","none");
						}else{
							$(pageDiv).find("#pres").css("display","inline");
							$(pageDiv).find("#pre").css("display","inline");
						}
						if(currentPage==totalPage){
							$(pageDiv).find("#nexts").css("display","none");
							$(pageDiv).find("#next").css("display","none");
						}else{
							$(pageDiv).find("#nexts").css("display","inline");
							$(pageDiv).find("#next").css("display","inline");
						}
						$(pageDiv).find("li[name='pages']").remove();
						var n=(currentPage-1)%5;
						var addLi="";
						for(var i=0;i<n;i++){
							addLi+="<li name='pages'><a onclick=\"setQueryPage('"+tableId+"',"+(currentPage-n+i)+")\">"+(currentPage-n+i)+"</a></li>";
						}
						addLi+="<li name='pages'><a onclick=\"setQueryPage('"+tableId+"',"+(currentPage)+")\" style='background-color: #e1eef3;'>"+currentPage+"</a></li>";
						var m=5-n-1;
						if(totalPage-currentPage<m){
							m=totalPage-currentPage;
						}
						for(var i=0;i<m;i++){
							addLi+="<li name='pages'><a onclick=\"setQueryPage('"+tableId+"',"+(currentPage-0+i+1)+")\">"+(currentPage-0+i+1)+"</a></li>";
						}
						$(pageDiv).find("#next").before(addLi);
					}
					$.each(list, function(index, item) {
						var createObj=jQuery($("#"+tableId+" #selectTemplete").prop("outerHTML"));
						if(index!=list.length-1){
							$(createObj).find("*[type='lastShow']").remove();
						}
						createObj.removeAttr("id");
						createObj.removeAttr("style");
						createObj.append("<input type='hidden' id='itemId' value='"+item.id+"'/>");
						var selectCheckbox=createObj.find("input[name='selectCheckbox']");
						if(selectCheckbox!=null&&selectCheckbox.length>0){
							$(selectCheckbox).bind('click', function(event) {
								event.stopPropagation();
								var status=$(this).prop("checked");
								if(status==false){
									$("#"+tableId+" #headCheckbox").prop("checked",status);
								}else{
									status=true;
									$.each($("#"+tableId+" #dataTbody input[name='selectCheckbox']"), function(index, item) {
										if($(item).prop("checked")==false){
											status=false;
										}
									});
									$("#"+tableId+" #headCheckbox").prop("checked",status);
								}
							});
						}
						var beforeSelect=$("#"+tableId).attr("beforeSelect");
						fn = eval(beforeSelect);
						if(fn!=null){
							item["totalSize"]=data.obj.length;
							item.index=index;
					        fn.call(this,createObj,item); 
						}
						$(createObj).find("#index").html(index+1);
						$.each(columns[tableId], function(index1, item1) {
							var mDataProp=item1.mDataProp;
							var type=$(createObj).find("#"+mDataProp).prop("nodeName");
							/*$(thead).find("#"+mDataProp).nextAll().remove();*/
							if(type=="INPUT"){
								
								$(createObj).find("#"+mDataProp).attr("value",item[mDataProp]);
								$(createObj).find("#"+mDataProp).attr("title",item[mDataProp]);
								var tmp=$(createObj).find("#"+mDataProp).prop("type");
								if(tmp=="checkbox")
									{
									if(item[mDataProp]==1)
										$(createObj).find("#"+mDataProp).attr("checked",true);
										else
											{
											$(createObj).find("#"+mDataProp).attr("checked",false);		
											}	
									}
							}else if(type=="SELECT"){
								$(createObj).find("#"+mDataProp+" option[value='"+item[mDataProp]+"']").attr("selected","selected");
							}else if(type=="MYSPAN"){
								//$(createObj).find("#"+mDataProp+" option[value='"+item[mDataProp]+"']").attr("selected","selected");
								var params=jQuery.parseJSON($(createObj).find("#"+mDataProp).attr("params"));
								$(createObj).find("#"+mDataProp).html(params[item[mDataProp]]);
							}
							else{
								$(createObj).find("#"+mDataProp).html(item[mDataProp]);
								$(createObj).find("#"+mDataProp).attr("title",item[mDataProp]);
							}
						});
						$("#"+tableId+" #dataTbody").append(createObj);
						var afterSelect=$("#"+tableId).attr("afterSelect");
						fn = eval(afterSelect);
						if(fn!=null){
							fn.call(this,createObj,item); 
						}
					});
					var selectTemplete=$("#"+tableId).find("#selectTemplete");
					var orderPropoty=$(selectTemplete).find("#orderPropoty").val();
//					console.log(orderPropoty);
					if(orderPropoty!=null&&orderPropoty!=""){
						//alert($(thead).find("#"+orderPropoty).parents("th").html());
						var orderType=$("#"+tableId+" #selectTemplete").find("#orderType").val();
//						console.log(orderType);
						if(orderType==null || orderType==""){
							$(thead).find("#"+orderPropoty).parents("th").append('<span class="glyphicon glyphicon-arrow-up order-btn" aria-hidden="true">');
						}else{
							$(thead).find("#"+orderPropoty).parents("th").append('<span class="glyphicon glyphicon-arrow-down order-btn" aria-hidden="true">');
						}
					}
					checkAuth();
				}else{
					alert(data.errorMsg);
				}
			}
		});
	}
	
	function addItem(table){
		if(typeof table == 'string'){
			table=$("#"+table);
		}
		var div=$(table).attr("divId");
		if(div==null||div==""){
			if(isInserting[$(table).attr('id')]==1){
				return false;
			}
			var createObj=jQuery($(table).find("#insertTemplete").prop("outerHTML"));
			createObj.removeAttr("id");
			createObj.removeAttr("style");
			$(createObj).find("#index").html($(table).find("#dataTbody tr").length+1);
			$(table).find("#dataTbody").append(createObj);
			var beforeInsert=$(table).attr("beforeInsert");
			fn = eval(beforeInsert);
			if(fn!=null){
				fn.call(this,createObj); 
			}
		}else{
			if(typeof div == 'string'){
				div=$("#"+div);
			}else{
				div=$(div);
			}
			$(div).find("*[viewType='hidden']").attr('style',"display:inline");
			$(div).find("*[viewType='disabled']").attr("disabled", true);
			/*$(div).find("input[type='text']").val("");
			$(div).find("input[type='hidden']").val("");
			$(div).find("select").val("");*/
			if($(div).find("#itemId").val()!=null&&$(div).find("#itemId").val()!=""){
				$.each(columns[$(table).attr('id')], function(index, item) {
					var mDataProp=item.mDataProp;
					var component=$(div).find("#"+mDataProp);
					var type=$(div).find("#"+mDataProp).prop("nodeName");
					if(component!=null&&component.length>0){
						if(type=="INPUT"){
							$(div).find("#"+mDataProp).val("");
						}else if(type=="SELECT"){
							$(div).find("#"+mDataProp).val($(div).find("#"+mDataProp).attr("defultValue"));
						}else{
							$(div).find("#"+mDataProp).html("");
						}
					}else{
						var type=$(div).find("*[name='"+mDataProp+"']").prop("type");
						if(type=="radio"){
							var checked=$(div).find("input[name='"+mDataProp+"'][checked='checked']").val();
							$(div).find("input[name='"+mDataProp+"'][value='"+checked+"']")[0].checked = true;
						}
					}
				});
			}
			$(div).find("#itemId").val("");
			var beforeInsert=$(table).attr("beforeInsert");
			fn = eval(beforeInsert);
			if(fn!=null){
				fn.call(this,div); 
			}
			$(div).modal({backdrop: 'static', keyboard: false});
		}
		isInserting[$(table).attr('id')]=1;
	}
	
	function cancleAdd(obj){
		var tableId=$(obj).parents("table").attr("id");
		$(obj).parents("tr").remove();
		isInserting[tableId]=0;
	}
	
	function submitAdd(obj,div){
		var postParams=new Object();
		if(div==null){
			var tableId=$(obj).parents("table").attr("id");
			var beforeSubmit=$("#"+tableId).attr("beforeSubmit");
			fn = eval(beforeSubmit);
			if(fn!=null){
				fn.call(this); 
			}
			$.each(columns[tableId], function(index, item) {
				var mDataProp=item.mDataProp;
				var type=$(obj).parents("tr").find("#"+mDataProp).prop("nodeName");
				var value="";
				if(type=="INPUT"){
					value=$(obj).parents("tr").find("#"+mDataProp).val();
				}else if(type=="SELECT"){
					value=$(obj).parents("tr").find("#"+mDataProp+" option:selected").val();
				}else if(type=="TEXTAREA"){
					value=$(obj).parents("tr").find("#"+mDataProp).html();
				}
				postParams[mDataProp]=value;
			});
			var inputs=$(obj).parents("tr").find("input[type=hidden]");
			$.each(inputs, function(index, item) {
				var param=$(item).attr("id");
				var sourceId=$(item).attr("sourceId");
				if(sourceId!=null){
					var value="";
					value=$("#"+sourceId).val();
					postParams[param]=value;
				}
				var sourceName=$(item).attr("sourceName");
				if(sourceName!=null){
					sourceObjs=$("*[name='"+sourceName+"']");
					var params=new Array();
					$.each(sourceObjs, function(index1, item1) {
						params[index1]=$(item1).val();
					});
					postParams[param]=JSON.stringify(params);
				}
				if(sourceName==null&&sourceId==null){
					postParams[param]=$(item).val();
				}
			});
		}else{
			if(typeof div == 'string'){
				div=$("#"+div);
			}else{
				div=$(div);
			}
			var tableId=div.attr("tableid");
			var beforeSubmit=$("#"+tableId).attr("beforeSubmit");
			fn = eval(beforeSubmit);
			if(fn!=null){
				fn.call(this); 
			}
			$.each(columns[tableId], function(index, item) {
				var mDataProp=item.mDataProp;
				var component=div.find("#"+mDataProp);
				var value="";
				if(component!=null&&component.length>0){
					var type=component.prop("nodeName");
					if(type=="INPUT"){
						value=div.find("#"+mDataProp).val();
					}else if(type=="SELECT"){
						value=div.find("#"+mDataProp+" option:selected").val();
					}else{
						value=div.find("#"+mDataProp).val();
					}
				}else{
					var type=div.find("*[name='"+mDataProp+"']").prop("type");
					if(type=="radio"){
						value=div.find("input[name='"+mDataProp+"']:checked").val(); 
					}
				}
				postParams[mDataProp]=value;
			});
			var inputs=div.find("input[type=hidden]");
			$.each(inputs, function(index, item) {
				var param=$(item).attr("id");
				var sourceId=$(item).attr("sourceId");
				if(sourceId!=null){
					var value="";
					value=$("#"+sourceId).val();
					postParams[param]=value;
				}
				var sourceName=$(item).attr("sourceName");
				if(sourceName!=null){
					sourceObjs=$("*[name='"+sourceName+"']");
					var params=new Array();
					$.each(sourceObjs, function(index1, item1) {
						params[index1]=$(item1).val();
					});
					postParams[param]=JSON.stringify(params);
				}
				if(sourceName==null&&sourceId==null){
					postParams[param]=$(item).val();
				}
			});
		}
		console.log(source[tableId]["add"]);
		$.ajax({
			type: "post",
			data:postParams,
			async:false,
			dataType:"json", //收受数据格式
			url:source[tableId]["add"],
			cache:false,
			success: function(data){
				if(data.retcode=="0000"){
					selectItems(tableId);
					var afterInsert=$("#"+tableId).attr("afterInsert");
					fn = eval(afterInsert);
					if(fn!=null){
						fn.call(this); 
					}
					if(div!=null){
						div.modal("hide");
					}
				}else{
					alert(data.errorMsg);
				}
			}
		});
	}
	
	function deleteItem(obj){
		if(window.confirm('你确定要删除吗？')){ 
		}else{ 
			return false; 
		} 
		var tableId=$(obj).parents("table").attr("id");
		var itemId=value=$(obj).parents("tr").find("#itemId").val();
		var postParam = {
			"itemId" : itemId
		};
		$.ajax({
			type: "post",
			data:postParam,
			dataType:"json", //收受数据格式
			url:source[tableId]["delete"],
			cache:false,
			success: function(data){
				if(data.retcode=="0000"){
					selectItems(tableId);
					var afterDelete=$("#"+tableId).attr("afterDelete");
					fn = eval(afterDelete);
					if(fn!=null){
						fn.call(this); 
					}
				}else{
					alert(data.errorMsg);
				}
			}
		});
	}
	
	function editItem(obj,isView){
		console.log(obj);
		var tableId=$(obj).parents("table").attr("id");
		var itemId=$(obj).parents("tr").find("#itemId").val();
		var div=$(obj).parents("table").attr("divId");
		if(div==null||div==""){
			if(isEditing[tableId]==1){
				return false;
			}
			var createObj=jQuery($("#"+tableId+" #editTemplete").prop("outerHTML"));
			createObj.removeAttr("id");
			createObj.removeAttr("style");
			createObj.append("<input type='hidden' id='itemId' value='"+$(obj).parents("tr").find("#itemId").val()+"'/>");
		}else{
			if(typeof div == 'string'){
				div=$("#"+div);
			}else{
				div=$(div);
			}
			if($(div).find("#itemId")==null||$(div).find("#itemId").length==0){
				$(div).append("<input type='hidden' id='itemId' value='"+itemId+"'/>");
			}else{
				$(div).find("#itemId").val(itemId);
			}
			if(isView==1){
				$(div).find("*[viewType='hidden']").attr('style',"display:none");
				$(div).find("input").attr("disabled", true);
				$(div).find("select").attr("disabled", true);
			}else{
				$(div).find("*[viewType='hidden']").attr('style',"display:inline");
				$(div).find("input").attr("disabled", false);
				$(div).find("select").attr("disabled", false);
				$(div).find("*[viewType='disabled']").attr("disabled", true);
			}
		}
		var dataItem;
		var postParams={"itemId":itemId};
		$.ajax({
			type: "post",
			data:postParams,
			dataType:"json", //收受数据格式
			url:source[tableId]["get"],
			cache:false,
			success: function(data){
				if(data.retcode=="0000"){
					dataItem=data.obj;
					if(div==null){
						var beforeEdit=$("#"+tableId).attr("beforeEdit");
						fn = eval(beforeEdit);
						if(fn!=null){
							fn.call(this,createObj,dataItem); 
						}
						var index=$(obj).parents("tr").find("#index").html();
						$(createObj).find("#index").html(index);
						$.each(columns[tableId], function(index, item) {
							var mDataProp=item.mDataProp;
							var type=$(createObj).find("#"+mDataProp).prop("nodeName");
							var value=dataItem[mDataProp];
							if(type=="INPUT"){
								$(createObj).find("#"+mDataProp).attr("value",value);
								var tmp=$(createObj).find("#"+mDataProp).prop("type");
								if(tmp=="checkbox")
								{
									if(value==1)
										$(createObj).find("#"+mDataProp).attr("checked",true);
									else
									{
										$(createObj).find("#"+mDataProp).attr("checked",false);		
									}
								}
							}else if(type=="SELECT"){
								$(createObj).find("#"+mDataProp+" option[value='"+value+"']").attr("selected","selected");
							}else{
								$(createObj).find("#"+mDataProp).html(value);
							}
						});
						editHtml[tableId]=$(obj).parents("tr").html();
						$(obj).parents("tr").html(createObj.html());
					}else{
						var beforeEdit=$("#"+tableId).attr("beforeEdit");
						fn = eval(beforeEdit);
						if(fn!=null){
							fn.call(this,div,dataItem); 
						}
						$.each(columns[tableId], function(index, item) {
							var mDataProp=item.mDataProp;
							var type=$(div).find("#"+mDataProp).prop("nodeName");
							var component=div.find("#"+mDataProp);
							var value=dataItem[mDataProp];
							if(component!=null&&component.length>0){
								if(type=="INPUT"){
									$(div).find("#"+mDataProp).val(value);
								}else if(type=="SELECT"){
									//console.log(mDataProp+","+value);
									//$(div).find("#"+mDataProp+" option[value='"+value+"']").attr("selected","selected");
									$(div).find("#"+mDataProp).val(value);
								}else{
									$(div).find("#"+mDataProp).html(value);
								}
							}else{
								var type=$(div).find("*[name='"+mDataProp+"']").prop("type");
								if(type=="radio"){
									$(div).find("input[name='"+mDataProp+"'][value='"+value+"']")[0].checked = true;
								}
							}
						});
						$(div).modal({backdrop: 'static', keyboard: false});
					}
					
					isEditing[tableId]=1;
				}else{
					alert(data.errorMsg);
				}
			}
		});
	}
	
	function cancelEdit(obj){
		var tableId=$(obj).parents("table").attr("id");
		$(obj).parents("tr").html(editHtml[tableId]);
		isEditing[tableId]=0;
	}
	
	function submitEdit(obj){
		var postParams=new Object();
		var tableId=$(obj).parents("table").attr("id");
		var itemId=value=$(obj).parents("tr").find("#itemId").val();
		postParams["itemId"]=itemId;
		$.each(columns[tableId], function(index, item) {
			var mDataProp=item.mDataProp;
			var type=$(obj).parents("tr").find("#"+mDataProp).prop("nodeName");
			var value="";
			if(type=="INPUT"){
				value=$(obj).parents("tr").find("#"+mDataProp).val();
			}else if(type=="SELECT"){
				value=$(obj).parents("tr").find("#"+mDataProp+" option:selected").val();
			}else if(type=="TEXTAREA"){
				value=$(obj).parents("tr").find("#"+mDataProp).html();
			}
			postParams[mDataProp]=value;
		});
		$.ajax({
			type: "post",
			data:postParams,
			async:false,
			dataType:"json", //收受数据格式
			url:source[tableId]["edit"],
			cache:false,
			success: function(data){
				if(data.retcode=="0000"){
					selectItems(tableId);
					isEditing[tableId]=0;
					var afterEdit=$("#"+tableId).attr("afterEdit");
					fn = eval(afterEdit);
					if(fn!=null){
						fn.call(this); 
					}
				}else{
					alert(data.errorMsg);
				}
			}
		});
		
	}
	
	function closeEditDiv(obj){
		$("#"+obj).modal("hide");
	}
	/**************************增删改查 结束**********************************/
	

	/**************************分页设置 开始**********************************/
	function initPageDiv(tableId){
		var pageDiv=$("div[paging-table='"+tableId+"']");
		if(pageDiv!=null&&pageDiv.length>0){
			pageDiv.html("<div class='table-page-left'>"+
					"当前页：<span id='currentPage'>1</span> / <span id='totalPage'>1</span>&nbsp;"+
					"总记录：<span id='totalSize'>1</span>"+
					"</div><div class='table-page-right'><nav>"+
					"<ul class='pager float-right'><li>每页记录数："+
					"<select id='pageSize' class='form-control' onchange='changePageSize(\""+tableId+"\",this)' style='width:80px;'>"+
					"<option value='5'>5</option>"+
					"<option value='10'>10</option>"+
					"<option value='20'>20</option>"+
					"<option value='30'>30</option>"+
					"<option value='40'>40</option>"+
					"<option value='50'>50</option>"+
					"</select></li>"+
					"<li id='pres'><a onclick=\"presPage('"+tableId+"')\"><<</a></li>"+
					"<li id='pre'><a onclick=\"prePage('"+tableId+"')\"><</a></li>"+
					"<li name='pages'><a>0</a></li>"+
					"<li id='next'><a onclick=\"nextPage('"+tableId+"')\">></a></li>"+
					"<li id='nexts'><a onclick=\"nextsPage('"+tableId+"')\">>></a></li></ul></nav></div>");
			$(pageDiv).find("#pageSize").val($(pageDiv).attr("page-size"));
		}
	}
	
	function presPage(tableId){
		var pageDiv=$("div[paging-table='"+tableId+"']");
		var page=$(pageDiv).find("#queryPage").val();
		page=page-5;
		if(page<1)
			page=1;
		$(pageDiv).find("#queryPage").val(page);
		selectItems(tableId);
	}
	
	function prePage(tableId){
		var pageDiv=$("div[paging-table='"+tableId+"']");
		var page=$(pageDiv).find("#queryPage").val();
		if(page>1)
			page=page-1;
		$(pageDiv).find("#queryPage").val(page);
		selectItems(tableId);
	}
	
	function nextsPage(tableId){
		var pageDiv=$("div[paging-table='"+tableId+"']");
		var page=Number($(pageDiv).find("#queryPage").val());
		page=page+5;
		if(page>Number($(pageDiv).find("#totalPage").html()))
			page=Number($(pageDiv).find("#totalPage").html());
		$(pageDiv).find("#queryPage").val(page);
		selectItems(tableId);
	}
	
	function nextPage(tableId){
		var pageDiv=$("div[paging-table='"+tableId+"']");
		var page=Number($(pageDiv).find("#queryPage").val());
		if(page<Number($(pageDiv).find("#totalPage").html()))
			page=page+1;
		$(pageDiv).find("#queryPage").val(page);
		selectItems(tableId);
	}
	
	function changePageSize(tableId,obj){
		var pageDiv=$("div[paging-table='"+tableId+"']");
		$(pageDiv).attr("page-size",$(obj).val());
		$(pageDiv).find("#queryPage").val(1);
		selectItems(tableId);
	}
	
	function setQueryPage(tableId,queryPage){
		var pageDiv=$("div[paging-table='"+tableId+"']");
		$(pageDiv).find("#queryPage").val(queryPage);
		selectItems(tableId);
	}
	/**************************分页设置 结束**********************************/
	
	

	/**************************全选设置 开始**********************************/
	function initCheckbox(tableId){
		var headCheckbox=$("#"+tableId+" #headCheckbox");
		if(headCheckbox!=null&&headCheckbox.length>0){
			$(headCheckbox).bind('click', function() {
				var status=$(this).prop("checked");
				//$("#"+tableId+" input[name='selectCheckbox']").prop("checked",status);
				$.each($("#"+tableId+" input[name='selectCheckbox']"), function(index, item) {
					item.checked=status;
					//console.log(item.checked);
				});
				var afterChange=$(this).attr("afterChange");
				fn = eval(afterChange);
				if(fn!=null){
			        fn.call(this); 
				}
			});
		}
	}
	/**************************全选设置 结束**********************************/
	