var isChrome = navigator.userAgent.toLowerCase().indexOf('chrome');
var isIE = navigator.userAgent.toLowerCase().indexOf('msie');

var vur_index=0;
var main_pages=new Array();
/**
 * 公用加载页面的方法
 * @param url 请求页面的url
 * @param workPanl 用来加载页面元素的面板id
 */
function commonLoadPage(url,workPanl,flag){
	//console.log(url);
	if($("#needLeaveCheck").length>0){
		if(!confirm("是否离开本页面？")){
		     return;
		}
	}
	lockScreen();
	/*if(index!=null){
		if(vur_index!=0){
			main_pages[vur_index]=$("#"+workPanl).html();
		}
		vur_index=index;
		if(main_pages[index]!=null && main_pages[index]!=""){
			$("#"+workPanl).html(main_pages[index]);
			unLockScreen();
			return;
		}
	}*/
	var cb = arguments;
	$.ajax({
		url :  url,
		dataType : "html",
		cache : false,
		success : function(html) {
			//unLockScreen();
			$("#"+workPanl).empty();
			if(flag!=true){
				//$("#systemLoadingDialog").nextAll().remove();
			}
			$("#"+workPanl).html(html);
			/*try{
				var j=$.parseJSON(html);
				
				if("SESSION_INVALIDATE"==j.retcode){
					$("#systemInvalidUser").dialog('open');
				}else{
					systemAlert(j.errorMsg);
				}
				
			}catch(e){
				$("#"+workPanl).html(html);
				for(var i = 2; i < cb.length; i++){
					cb[i].call();
				}
			}*/
		},
		error : function(XMLHttpRequest,textStatus,errorThrown){
			unLockScreen();
			$("#"+workPanl).empty();
			//$("#systemLoadingDialog").nextAll().remove();
			/*systemAlert("网络不是很给力，请重试");*/
		},
		complete:function(){
			unLockScreen();
		}
	});
}


/**
 * 显示数据处理中浮层，或禁用组件(按钮)
 * disableId 禁用组件ID
 */
function lockScreen(){
	$('#systemLoadingDialog').removeAttr("style");
}

/**
 * 取消显示数据处理中浮层，或启用组件(按钮)
 * enableId 启用组件ID
 */
function unLockScreen(){
	$('#systemLoadingDialog').attr('style',"display:none");//启用组件(按钮)
}

function checkAuth(){
	var urls = new Array();
	$.each($("*[authurl]"), function(index, item) {
		var url = $(item).attr("authurl");
		if (url!=null&&url!="") {
			urls[index] = url;
		}
	});
	$.ajax({
		type: "post",
		data:{"urls":JSON.stringify(urls)},
		dataType:"json", //收受数据格式
		url:"${pageContext.request.contextPath}/moduleAction_checkAuth",
		cache:false,
		success: function(data){
			if(data.retcode=="0000"){
				//console.log(data.obj);
				$.each(data.obj, function(index, item) {
					if(item.check==0){
					}else{
						$("*[authurl='"+item.url+"']").css("display","none"); 
					}
				});
			}
		}
	});
}

function CheckIntensity(pwd){ 
	  var Mcolor,Wcolor,Scolor,Color_Html; 
	  var m=0; 
	  var Modes=0; 
	  for(i=0; i<pwd.length; i++){ 
	    var charType=0; 
	    var t=pwd.charCodeAt(i); 
	    if(t>=48 && t <=57){charType=1;} 
	    else if(t>=65 && t <=90){charType=2;} 
	    else if(t>=97 && t <=122){charType=4;} 
	    else{charType=4;} 
	    Modes |= charType; 
	  } 
	  for(i=0;i<4;i++){ 
	  if(Modes & 1){m++;} 
	      Modes>>>=1; 
	  } 
	  if(pwd.length<=4){m=1;} 
	  if(pwd.length<=0){m=0;} 
	  switch(m){ 
	    case 1 : 
	      Wcolor="pwd pwd_Weak_c"; 
	      Mcolor="pwd pwd_c"; 
	      Scolor="pwd pwd_c pwd_c_r"; 
	      Color_Html="弱"; 
	    break; 
	    case 2 : 
	      Wcolor="pwd pwd_Medium_c"; 
	      Mcolor="pwd pwd_Medium_c"; 
	      Scolor="pwd pwd_c pwd_c_r"; 
	      Color_Html="中"; 
	    break; 
	    case 3 : 
	      Wcolor="pwd pwd_Strong_c"; 
	      Mcolor="pwd pwd_Strong_c"; 
	      Scolor="pwd pwd_Strong_c pwd_Strong_c_r"; 
	      Color_Html="强"; 
	    break; 
	    default : 
	      Wcolor="pwd pwd_c"; 
	      Mcolor="pwd pwd_c pwd_f"; 
	      Scolor="pwd pwd_c pwd_c_r"; 
	      Color_Html="无"; 
	    break; 
	  } 
	  document.getElementById('pwd_Weak').className=Wcolor; 
	  document.getElementById('pwd_Medium').className=Mcolor; 
	  document.getElementById('pwd_Strong').className=Scolor; 
	  document.getElementById('pwd_Medium').innerHTML=Color_Html; 
	} 