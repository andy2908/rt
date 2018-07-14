<%@page import="com.uat.hbc.common.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Hopebox Entertainment</title>
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<!-- <link href="css/jquery.mCustomScrollbar.css" rel="stylesheet"> -->
<link href="css/bootstrap.css" rel="stylesheet">
 <link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/metisMenu.min.css" rel="stylesheet">
<link href="css/sb-admin-2.css" rel="stylesheet">
<link href="css/morris.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!-- <link href="css/scrollBar.css" rel="stylesheet" type="text/css"> -->
<!-- <script src="js/jquery.mCustomScrollbar.concat.min.js"></script> -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/metisMenu.min.js"></script>
<script src="js/raphael.min.js"></script>
<script src="js/productDetails.js"></script>
<script src="js/morris.min.js"></script>
<script src="js/validation.js"></script>
<script src="js/sb-admin-2.js"></script>
<!-- <script src="js/scrollBar.js"></script> -->
<script type="text/javascript">
$(function() {  
    $("#admin-page-wrapper").niceScroll({
    	cursorwidth: "10px",
    	cursorcolor: "#ff7d33",
//     	background: "gray"
    });
    $("body").niceScroll({
    	cursorwidth: "1px",
    	cursorcolor: "#ff7d33",
//     	background: "gray"
    });
});


$(document).ajaxSend(function(){
	showWait();
});


$(document).ajaxComplete(function() {
	hideWait();
});

	$(document).ready(function() {
		$("#admin-page-wrapper").html("");
		loadMenu();
// 		$(".myContainer").scrollBox();
	});

	function loadMenu() {
		var param= $("#userId").val() + "~" +"null"+"~"+"null"+"~"+"'O'" ; 
		var resp = getRecordList("11",param);
		console.log("resp****"+resp);
		$.each(resp, function (key, value) {
			nKey = parseInt(key) + 1;
			var menuType = resp[key].MENU_TYPE;
			var menuIdToPass = "menuId"+resp[key].MENU_ID;
			console.log("menuIdToPass****"+menuIdToPass);
			if(menuType == "L"){
				$("#side-menu").append("<li id='menuName"+resp[key].MENU_ID+"'><a><i class='fa fa-edit fa-fw'></i> "+resp[key].MENU_NAME+"<span class='fa arrow'></span></a></li>");
				$("#menuName"+resp[key].MENU_ID).append("<ul class=\"nav nav-second-level\" id=\"menuId"+resp[key].MENU_ID+"\"></ul>");
				$('#side-menu').metisMenu();
				var compLink = 	document.getElementById("menuId"+resp[key].MENU_ID);
				console.log("In Link==>>"+compLink);
			}else if(menuType == "F"){
				var comp = 	document.getElementById("menuId"+resp[key].PARENT_MENU_ID);
				if(comp==null){
				console.log("In If");
					$("#side-menu").append("<li style=\"cursor: default;\"><a onclick=\"loadPage('"+resp[key].PAGE_NAME+"','"+resp[key].MENU_NAME+"')\">"+resp[key].MENU_NAME+"</a></li>");
// 					resp[key].PAGE_NAME
				}else{
				console.log("In else");
					$("#menuId"+resp[key].PARENT_MENU_ID).append("<li style=\"cursor: default;\"><a onclick=\"loadPage('"+resp[key].PAGE_NAME+"','"+resp[key].MENU_NAME+"')\">"+resp[key].MENU_NAME+"</a></li>");
				}
			}
		});
// 		/* $(".admin-sidebar").hide();
// 		$(".admin-sidebar").show(); */
	}
	
	function displayBlock(menuId) {
		console.log("In displayBlock===>>"+menuId);
// 	    $(this).find("ul").css("display", "block");
// 	    $("#menuId").show();
// 	    $("#menuId").css("display", "block");
// 	    return false;
	    $(this).find("ul").css("display", "block");
		console.log("In displayBlock 1111===>>"+menuId);
	    return false;
	}
	function populateMenu(){
		var userId = $("userId").val();
		var resp = getRecordList(sqlMstId,userId+"~null~null~O");
	}
	
	
	function loadPage(pageName,menuName) {
		$.ajax({
			url : "user/"+pageName,
			context : document.body
		}).done(function(resp) {
			$("#admin-page-wrapper").html("<div class=\"menuTitle\"><p  style=\"font-size: 20px;font-weight: bold;display:inline;\">"+menuName+"</p><button type=\"button\" class=\"btn btn-warning btn-circle\" onclick=\"closePage();\" style=\"float: right;\">"+
					"<i class=\"fa fa-times\"></i></button></div>"+resp);
// 			$("#admin-page-wrapper").html("<p class=\"menuTitle\" style=\"font-size: 20px;font-weight: bold;\">"+menuName+"</p>"+resp);
		});
	}

	function logOut() {
		$.ajax({
			url : "${pageContext.request.contextPath}/logout",
			type : "POST",
		}).done(function(resp) {
// 			alert("Session Logged Out!");
			location.href = "${pageContext.request.contextPath}";
		});
	}
	
	function showWait(){
		$("#wait").css("display","block");
	 	$("#admin-wrapper").css("opacity", 0.2);
	}
	
	function hideWait(){
		$("#wait").css("display","none");
	 	$("#admin-wrapper").css("opacity", 1);
	}
	function closePage(){
		console.log("Close Button Clicked !!!");
		$("#admin-page-wrapper").html("");
		
	}
</script>

<style type="text/css">
/*  label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 5px;
    font-weight: 700;
    margin-left: 11px;
}
.dataTables_wrapper {
   width: 500px;
}
.dataTables_wrapper .dataTables_length {
    float: left;
    margin-left: 15px;
}
.dataTables_wrapper .dataTables_filter {

    text-align: right;
    margin-right: -15px;

} */
/* 
.selected {
    background-color: brown;
    color: #FFF;
} */

/* .dataTables_wrapper .dataTables_paginate {
    float: left;
    text-align: left;
}
.dataTables_wrapper .dataTables_filter {
    float: left;
    text-align: left;
    margin-left: 60px;
}
 */
/* .select2-container .select2-choice {
    
    border: 1px solid #b3a7a7;}
    .btn-circle {
    width: 28px;
    height: 25px;
    padding: 6px 0;
    border-radius: 15px;
    text-align: center;
    font-size: 12px;
    line-height: 1.428571429;
    background-color: #ff7d33;
} */
.modal-body {
    position: relative;
    width: 100%;
    height: auto;
    margin-bottom: 0;
    padding: 0px;
}

</style>
</head>
<body>
	<%
		User user = (User) session.getAttribute("user");
		if (user == null) {
	%>
	<script type="text/javascript">
// 		alert("Session Logged Out!");
		location.href = "${pageContext.request.contextPath}/";
	</script>
	<%
		}
	%>
	
	
	
<!-- /////////////////////Popup Menue//////////////////// -->
	<%-- <div class="row" >
					
							<div class=" col-md-12 col-lg-12 col-sm-12" >
	 <div class="modal fade modalClose" id="myModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 565px;height: 535px;margin-left: 0px;">
				<div class="modal-header">
				
					<h4 class="modal-title"  style="background-color : #337ab7;color : white;">&nbsp;&nbsp;Venue List<button type="button" class="btn btn-warning btn-circle"  data-dismiss="modal" style="float: right;"><i class="fa fa-times"></i></button></h4>
					
				</div>
				<div class="modal-body">
					<div style=" background-color: white;width: 530px; border-color: #337ab7;border-weight: 1px;border-style:solid;overfl;height: 440px;">
					<br>
							<div class="row" >
							<%@include file="popupModal.jsp" %>
							
						</div>
						
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
	</div></div>  --%>
 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog" style="width: -moz-fit-content;width:-webkit-fit-content;
    width:-ms-fit-content;">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" id="vnlist"></h4>
        </div>
        <div style=" background-color: white; border-color: #337ab7;border-weight: 1px;border-style:solid;overfl;">
        <div class="modal-body" >
         <%-- <%@include file="popupModal.jsp" %> --%>
          
        </div></div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

 

	<!-- ===============================Popup Menue close==================================== -->
	<input type="hidden" value="${ user.userId }" id="userId">
	<div id="admin-wrapper">
		<!-- Navigation -->

		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			 <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <img class="navbar-brand" src="images/favicon.ico">
            </div>
<!-- 		<div class="navbar-header"> -->
<!-- 			<!-- 			                <a class="navbar-brand" href="index.html">SB Admin v2.0</a> --> 
<!-- 			<img class="navbar-brand" src="images/favicon.ico"> -->
<!-- 		</div> -->
		<ul class="nav navbar-top-links navbar-right" style="display: inline-block;">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu">
					<li><a onclick="logOut()" style="float: right;"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul></li>
		</ul>
		<div
			style="float: right; color: #29559E; height: auto;margin-top: 15px;margin-right: 10px;">
			Hello ${ user.userName }</div>
		<div class="navbar-default admin-sidebar" role="navigation">

			<div class="sidebar-nav navbar-collapse">

				<ul class="nav" id="side-menu"></ul>
				<!-- <li><a href="#"><i class="fa fa-edit fa-fw"></i> Master
							Entry<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li style="cursor: default;"><a
								onclick="loadPage('venueType')">Venue Type Entry</a></li>
							<li style="cursor: default;"><a
								onclick="loadPage('seatType')">Seat Type Entry</a></li>
							<li style="cursor: default;"><a
								onclick="loadPage('venueMaster')">Venue Master</a></li>
							<li style="cursor: default;"><a
								onclick="loadPage('eventMaster')">Event Master</a></li>
						</ul></li> -->
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> <!-- 		 </nav> --> </nav>
		
		
		
		
		
		
<!-- 		<div id="admin-page-wrapper" class="sb-container myContainer" style="height: 550px;overflow-y: scroll;"> -->
		<div id="admin-page-wrapper"  style="height: 550px;overflow-y: scroll;">
			<!-- 			<div id="admin-page-wrapper-part1"></div> -->
			<!-- 			<div id="admin-page-wrapper-part2"></div> -->
			
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<div id="wait"
		style="display: none; width: 100px; height: 100px; position: fixed; top: 43%; left: 50%; padding: 2px; z-index: 999999;">
		<br>
	</div>
	
	<!-- Custom Scrollbar -->
	<script src="js/jquery.nicescroll.min.js"></script>
	
</body>
</html>