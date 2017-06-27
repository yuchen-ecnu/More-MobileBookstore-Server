$(function() {
	var windowWidth = $(window).width() - 220;
	$("#mainRight").width(windowWidth);
    $("#hideTool").click(function(){
        $("#main-left").toggle(300);
        if ($("#hideTool").attr("src") == "../image/HideToolBar.png") {
            $("#hideTool").attr("src","../image/ShowToolBar.png");
        } else {
            $("#hideTool").attr("src","../image/HideToolBar.png");
        } 
    });
});