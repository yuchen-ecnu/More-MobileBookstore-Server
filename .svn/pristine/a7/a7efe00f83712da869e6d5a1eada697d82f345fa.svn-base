	
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
					if(type==2){
						addHtml = "<option value=''></option>";
					}
					//显示层绑定控件
					$.each(data.obj, function(index, item) {
						/*if(value!=null&&value==item.id){
							addHtml+="<option value='"+item.id+"' selected='selected'>"+item.name+"</option>";
						}else{
							addHtml+="<option value='"+item.id+"'>"+item.name+"</option>";
						}*/
						addHtml+="<option value='"+item.id+"'>"+item.name+"</option>";
					});
					$(select).html(addHtml);
					$(select).val(value);
				}
			}
		});
	}