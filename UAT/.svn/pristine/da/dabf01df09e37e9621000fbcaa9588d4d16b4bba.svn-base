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
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="jQuery Calendar, Month Calendar, MonthCalendar, DateTimeInput, DateTimePicker, Date Picker" />

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1 maximum-scale=1 minimum-scale=1" />
<title>Hopebox</title>
<link rel="stylesheet" href="css/jqx.base.css" type="text/css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/metisMenu.min.css" rel="stylesheet">
<link href="css/sb-admin-2.css" rel="stylesheet">
<link href="css/morris.css" rel="stylesheet">
<link href="css/Style.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/metisMenu.min.js"></script>
<script src="js/raphael.min.js"></script>
<script src="js/morris.min.js"></script>
<script src="js/validation.js"></script>
<script src="js/sb-admin-2.js"></script>
<script src="js/productDetails.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-ui.js"></script>
<script src="js/jqxcore.js"></script>
<script src="js/jqxdatetimeinput.js"></script>
<script src="js/jqxcalendar.js"></script>
<script src="js/jqxtooltip.js"></script>
<script src="js/globalize.js"></script>
<script src="js/demos.js"></script>


<script type="text/javascript">history.forward();</script>
<script type="text/javascript">
var xhr;
	window.old_alert = alert;
	
	window.alert = function (message) {
		$("#dialog-alert").dialog('destroy').remove();
		$("#dialog-alert-wrapper").html("");
		$("#dialog-alert-wrapper").html("<div id='dialog-alert' style='display:none;'></div>");
		var str = "<h5><span class='ace-icon fa fa-check bigger-200 green' style='float:left; margin:12px 12px 20px 0;'></span>"+message+"</h5>";
		  $("#dialog-alert").dialog({
		    buttons: [{ text: "Ok", click: function() {$("#dialog-alert").dialog('destroy').remove();}}],
		    close: function (event, ui) { $("#dialog-alert").dialog('destroy').remove(); },
		    resizable: false,
		    title: "Alert!",
		    modal: true,
		    width:400,
		    height:"auto"
		  }).html(str);
	};

	window.confirm = function (message,callback) {
		$("#dialog-alert").dialog('destroy').remove();
		$("#dialog-alert-wrapper").html("");
		$("#dialog-alert-wrapper").html("<div id='dialog-alert' style='display:none;'></div>");
		var str = "<h5><span class='ace-icon fa fa-question bigger-150 green' style='float:left; margin:12px 12px 20px 0;'></span>"+message+"</h5>";
		  $("#dialog-alert").dialog({
		    buttons: { "Ok": function () { $(this).dialog("close");callback();return true; },"Cancel":function(){$(this).dialog("close");return false;} },
		    close: function (event, ui) { $(this).dialog('destroy').remove(); },
		    resizable: false,
		    title: "Confirmation!",
		    modal: true,
		    width:400,
		    height:"auto"
		  }).html(str);
	};

	
	$(document).ajaxSend(function(){
		showWait();
	});
	
	$(document).ajaxComplete(function() {
		hideWait();
	});
	
	$(document).ready(function() {
		$("#admin-page-wrapper").html("");
		loadMenu();
	});
function loadMenu(){
	var param= $("#userId").val() + "~" +"null"+"~"+"null"+"~"+"'O'" ; 
	var resp = getRecordList("11",param);
	
	$.each(resp, function (key, value) {
		nKey = parseInt(key) + 1;
		var menuType = resp[key].MENU_TYPE;
		if(menuType == "L"){
			$("#side-menu").append("<li id='menuName"+resp[key].MENU_ID+"'><a href='#'><i class='fa fa-edit fa-fw'></i> "+resp[key].MENU_NAME+"<span class='fa arrow'></span></a></li>");
			$("#menuName"+resp[key].MENU_ID).append("<ul class=\"nav nav-second-level\" id=\"menuId"+resp[key].MENU_ID+"\"></ul>");
		}else if(menuType == "F"){
			var comp = 	document.getElementById("menuId"+resp[key].PARENT_MENU_ID);		
			if(comp==null){
				$("#side-menu").append("<li style=\"cursor: default;\"><a onclick=\"loadPage('"+resp[key].PAGE_NAME+"','"+resp[key].MENU_NAME+"')\">"+resp[key].MENU_NAME+"</a></li>");
				resp[key].PAGE_NAME
			}else{
				$("#menuId"+resp[key].PARENT_MENU_ID).append("<li style=\"cursor: default;\"><a onclick=\"loadPage('"+resp[key].PAGE_NAME+"','"+resp[key].MENU_NAME+"')\">"+resp[key].MENU_NAME+"</a></li>");
			console.log(resp[key].PAGE_NAME);
			}
		}
	});
}
	function loadPage(pageName,menuName) {
		$.ajax({
			url : "user/" + pageName,
			type : "POST",
			context : document.body
		}).done(function(resp) {
			$("#admin-page-wrapper").html("<p style=\"font-size: 20px;font-weight: bold;\">"+menuName+"</p>"+resp);
		});
	}

	function logOut() {
		$.ajax({
			url : "${pageContext.request.contextPath}/logout",
			type : "POST",
		}).done(function(resp) {
			location.href = "${pageContext.request.contextPath}";
		});
	}
	
	function startXHR(){
		xhr = $.ajax({
            url: '${pageContext.request.contextPath}/user/startLoading',
            type : "POST",
            success: function(data) {
            }
        });
	}
	
	function stopXHR(){
		xhr = $.ajax({
            url: '${pageContext.request.contextPath}/user/stopLoading',
            type : "POST",
            success: function(data) {
            }
        });
	}
	
	function showWait(){
		$("#wait").css("display","block");
	 	$(".container").css("opacity", 0.2);
	}
	
	function hideWait(){
		$("#wait").css("display","none");
	 	$(".container").css("opacity", 1);
	}
</script>
</head>
<body>
<input type="hidden" id="userId" value="${ user.userId }" style="font-size: 20px;font-weight: bold;">
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

	<div id="admin-wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0;">
		<div class="navbar-header">
			<!--<a class="navbar-brand" href="index.html">SB Admin v2.0</a> -->
			<img class="navbar-brand" src="images/favicon.ico">
		</div>
		<ul class="nav navbar-top-links navbar-right collapse navbar-collapse">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"><i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i> </a>
				<ul class="dropdown-menu dropdown-user">
					<li><a onclick="logOut()"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul></li>
		</ul>
		<div
			style="float: right; color: #29559E; height: auto; margin-top: 15px;">
			Hello ${ user.userName }</div>
		<div class="navbar-default admin-sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">

				<ul class="nav" id="side-menu" >
<!-- 				<li><a href="#"><i class="fa fa-edit fa-fw"></i> Master -->
<!-- 							Entry<span class="fa arrow"></span></a> -->
<!-- 						<ul class="nav nav-second-level"> -->
<!-- 							<li style="cursor: default;"><a onclick="loadPage('motor')">Motor -->
<!-- 									Calculator</a></li> -->
<!-- 							<li style="cursor: default;"><a -->
<!-- 								onclick="loadPage('viewMotor')">View Motor</a></li> -->
<!-- 							<li style="cursor: default;"><a -->
<!-- 								onclick="loadPage('viewledger')">View Ledger</a></li> -->
<!-- 							<li style="cursor: default;"><a -->
<!-- 								onclick="loadPage('uploadDocuments')">Upload Documents</a></li> -->
<!-- 						</ul></li>  -->
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> <!-- 		 </nav> --> </nav>
		<div id="admin-page-wrapper">
						
		</div>
		<!-- /#page-wrapper -->
	</div>
	<div id="wait" style="display:none;width:100px;height:100px;position:fixed;top:43%;left:50%;padding:2px; z-index: 999;"><br></div>
	<!-- /#wrapper -->
<div id="dialog-alert-wrapper">
	</div>
</body>
</html>