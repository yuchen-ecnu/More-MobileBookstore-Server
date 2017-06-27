	var isInserting=new Object();	
	var isEditing=new Object();
	var columns=new Object(); 
	var source=new Object();
	var editHtml=new Object();
	function initTable(tableId,aoColumns,ajaxSource){
		isInserting[tableId]=0;
		columns[tableId]=aoColumns;
		source[tableId]=ajaxSource;
		selectItems(tableId);
	}
	
	function selectItems(tableId){
		var postParams=new Object();
		var inputs=$("#"+tableId+" #selectTemplete").find("input[type=hidden]");
		$.each(inputs, function(index, item) {
			var sourceId=$(item).attr("sourceId");
			var sourceType=$("#"+sourceId).prop("nodeName");
			var param=$(item).attr("id");
			var value="";
			if(sourceType=="INPUT"){
				value=$("#"+sourceId).val();
			}else if(sourceType=="SELECT"){
				value=$("#"+sourceId).val();
			}
			postParams[param]=value;
		});
		$.ajax({
			type: "post",
			data:postParams,
			dataType:"json", //收受数据格式
			url:source[tableId]["select"],
			cache:false,
			success: function(data){
				if(data.retcode=="0000"){
					$("#"+tableId+" #dataTbody").empty();
					//显示层绑定控件
					$.each(data.obj, function(index, item) {
						var createObj=jQuery("<tr>"+$("#"+tableId+" #selectTemplete").html()+"<input type='hidden' id='itemId' value='"+item.id+"'/></tr>");
						var beforeSelect=$("#"+tableId).attr("beforeSelect");
						fn = eval(beforeSelect);
						if(fn!=null){
					        fn.call(this,createObj,item); 
						}
						$(createObj).find("#index").html(index+1);
						$.each(columns[tableId], function(index1, item1) {
							var mDataProp=item1.mDataProp;
							var type=$(createObj).find("#"+mDataProp).prop("nodeName");
							if(type=="INPUT"){
								$(createObj).find("#"+mDataProp).attr("value",item[mDataProp]);
							}else if(type=="SELECT"){
								$(createObj).find("#"+mDataProp+" option[value='"+item[mDataProp]+"']").attr("selected","selected");
							}else{
								$(createObj).find("#"+mDataProp).html(item[mDataProp]);
							}
						});
						$("#"+tableId+" #dataTbody").append(createObj);
						var afterSelect=$("#"+tableId).attr("afterSelect");
						fn = eval(afterSelect);
						if(fn!=null){
							fn.call(this,createObj,item); 
						}
					});
				}
			}
		});
	}
	
	function addItem(table){
		if(isInserting[table.id]==1){
			return false;
		}
		var createObj=jQuery("<tr>"+$("#"+table.id+" #insertTemplete").html()+"</tr>");
		$(createObj).find("#index").html($("#"+table.id+" #dataTbody tr").length+1);
		$("#"+table.id+" #dataTbody").append(createObj);
		isInserting[table.id]=1;
		var beforeInsert=$("#"+table.id).attr("beforeInsert");
		fn = eval(beforeInsert);
		if(fn!=null){
			fn.call(this,createObj); 
		}
	}
	
	function cancleAdd(obj){
		var tableId=$(obj).parents("table").attr("id");
		$(obj).parents("tr").remove();
		isInserting[tableId]=0;
	}
	
	function submitAdd(obj){
		var postParams=new Object();
		var tableId=$(obj).parents("table").attr("id");
		$.each(columns[tableId], function(index, item) {
			var mDataProp=item.mDataProp;
			var type=$(obj).parents("tr").find("#"+mDataProp).prop("nodeName");
			var value="";
			if(type=="INPUT"){
				value=$(obj).parents("tr").find("#"+mDataProp).val();
			}else if(type=="SELECT"){
				value=$(obj).parents("tr").find("#"+mDataProp+" option:selected").val();
			}
			postParams[mDataProp]=value;
		});
		var inputs=$(obj).parents("tr").find("input[type=hidden]");
		$.each(inputs, function(index, item) {
			var sourceId=$(item).attr("sourceId");
			var sourceType=$("#"+sourceId).prop("nodeName");
			var param=$(item).attr("id");
			var value="";
			if(sourceType=="INPUT"){
				value=$("#"+sourceId).val();
			}else if(sourceType=="SELECT"){
				value=$("#"+sourceId+" option:selected").val();
			}
			postParams[param]=value;
		});
		$.ajax({
			type: "post",
			data:postParams,
			dataType:"json", //收受数据格式
			url:source[tableId]["add"],
			cache:false,
			success: function(data){
				if(data.retcode=="0000"){
					selectItems(tableId);
					isInserting[tableId]=0;
					selectItems[tableId]=0;
					var afterInsert=$("#"+tableId).attr("afterInsert");
					fn = eval(afterInsert);
					if(fn!=null){
						fn.call(this); 
					}
				}else if(data.retcode=="0001"){
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
					isInserting[tableId]=0;
					isEditing[tableId]=0;
					var afterDelete=$("#"+tableId).attr("afterDelete");
					fn = eval(afterDelete);
					if(fn!=null){
						fn.call(this); 
					}
				}
			}
		});
	}
	
	function editItem(obj){
		var tableId=$(obj).parents("table").attr("id");
		if(isEditing[tableId]==1){
			return false;
		}
		var createObj=jQuery("<tr>"+$("#"+tableId+" #editTemplete").html()+"<input type='hidden' id='itemId' value='"+$(obj).parents("tr").find("#itemId").val()+"'/></tr>");
		var dataItem;
		var itemId=value=$(obj).parents("tr").find("#itemId").val();
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
						var value="";
						if(type=="INPUT"){
							value=dataItem[mDataProp];
							$(createObj).find("#"+mDataProp).attr("value",value);
						}else if(type=="SELECT"){
							value=dataItem[mDataProp];
							$(createObj).find("#"+mDataProp+" option[value='"+value+"']").attr("selected","selected");
						}else{
							value=dataItem[mDataProp];
							$(createObj).find("#"+mDataProp).html(value);
						}
					});
					editHtml[tableId]=$(obj).parents("tr").html();
					$(obj).parents("tr").html(createObj.html());
					isEditing[tableId]=1;
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
			}
			postParams[mDataProp]=value;
		});
		$.ajax({
			type: "post",
			data:postParams,
			dataType:"json", //收受数据格式
			url:source[tableId]["edit"],
			cache:false,
			success: function(data){
				if(data.retcode=="0000"){
					selectItems(tableId);
					isInserting[tableId]=0;
					isEditing[tableId]=0;
					var afterEdit=$("#"+tableId).attr("afterEdit");
					fn = eval(afterEdit);
					if(fn!=null){
						fn.call(this); 
					}
				}else if(data.retcode=="0001"){
					alert(data.errorMsg);
				}
			}
		});
	}
	
	function initSelect(select,url,postParams,type,value,async){
		var isAsync=false;
		if(async!=null){
			isAsync=async;
		}
		$.ajax({
			type: "post",
			data:postParams,
			async: isAsync,
			dataType:"json", //收受数据格式
			url:url,
			cache:false,
			success: function(data){
				if(data.retcode=="0000"){
					var addHtml = "";
					if(type==1){
						addHtml = "<option value=''>全部</option>";
					}
					//显示层绑定控件
					$.each(data.obj, function(index, item) {
						if(value!=null&&value==item.id){
							addHtml+="<option value='"+item.id+"' selected='selected'>"+item.name+"</option>";
						}else{
							addHtml+="<option value='"+item.id+"'>"+item.name+"</option>";
						}
					});
					$(select).html(addHtml);
				}
			}
		});
	}