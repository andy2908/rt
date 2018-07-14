<%@page import="com.uat.hbc.common.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta name="keywords" content="jQuery Calendar, Month Calendar, MonthCalendar, DateTimeInput, DateTimePicker, Date Picker" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1 maximum-scale=1 minimum-scale=1" />
<title>Hopebox</title>
<link rel="stylesheet" href="css/jqx.base.css" type="text/css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/metisMenu.min.css" rel="stylesheet">
<link href="css/sb-admin-2.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<link href="css/morris.css" rel="stylesheet">
<link href="css/Style.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/metisMenu.min.js"></script>
<script src="js/raphael.min.js"></script>
<!--  	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>  -->
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/select2.js"></script>
<link rel="stylesheet" type="text/css" href="css/select2.css">
<script src="js/jqxcore.js"></script>
<script src="js/jqxdatetimeinput.js"></script>
<script src="js/jqxcalendar.js"></script>
<script src="js/jqxtooltip.js"></script>
<script src="js/globalize.js"></script>
<script src="js/demos.js"></script>
<script src="js/validation.js"></script>
<script type="text/javascript">

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
		$("#page-wrapper").show();
		$("#wrapperMain").hide();
		
// 		showWait();
		
// 			setTimeout(function(){hideWait();},20000);

		 $(".modalClose").modal({
		        show: false,
		        backdrop: 'static'
		    });
		 
	});

	function callJSP(formName) {
		$.ajax({
			url : formName,
			type: 'POST',
			context : document.body
		}).done(function(resp) {
			$("#wrapperMain").html(resp);
			$("#page-wrapper").hide();
			$("#wrapperMain").show();

		});
	}
	
	function callMotorJSP(formName) {
		$.ajax({
			url : formName,
			type: 'POST',
			context : document.body
		}).done(function(resp) {
			$("#wrapperMain").html(resp);
			$("#page-wrapper").hide();
		//	$("#wrapperMain").show();
		});
	}

	function closeForm() {
		$("#page-wrapper").show();
		$("#wrapperMain").empty();
		$("#wrapperMain").hide();
	}
	function showWait(){
		$("#wait").css("display","block");
		/* $("#wait").show();
		document.getElementById("#wait").style = "display:none"; */
	 	$(".container").css("opacity", 0.0);
	 	$("nav").css("opacity", 0.0);
	 	console.log("In show wait");
	}
	
	function hideWait(){
		$("#wait").css("display","none");
		/* $("#wait").hide();
		document.getElementById("#wait").style = "display:block"; */
	 	$(".container").css("opacity", 1);
	 	$("nav").css("opacity", 1);
	 	console.log("In hide wait");
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
	
</script>
</head>
<body>

	<%
		User user = (User) session.getAttribute("user");
		if (user != null) {
	%>
	<script type="text/javascript">
		location.href = "${pageContext.request.contextPath}/welcome";
	</script>
	<%
		}
	%>
	<%@include file="header.jsp"%>
	
	<div id="wrapper" class="container" style="margin-top: 10px;">
		<div id="page-wrapper">
			<div class="row">
				<div class="circle col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<img class="boxes"
						onmouseover="$(this).attr('src','images/insurance-icon-26.png')"
						onmouseout="$(this).attr('src','images/insurance-icon-30.png')"
						src="images/insurance-icon-30.png" class="img-responsive"
						onclick="callJSP('motor');">
				</div>
				<div class="circle col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<img class="boxes"
						onmouseover="$(this).attr('src','images/insurance-icon-27.png')"
						onmouseout="$(this).attr('src','images/insurance-icon-31.png')"
						src="images/insurance-icon-31.png" class="img-responsive"
						onclick="callJSP('pa');">
				</div>
				<div class="circle col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<img class="boxes"
						onmouseover="$(this).attr('src','images/insurance-icon-28.png')"
						onmouseout="$(this).attr('src','images/insurance-icon-32.png')"
						src="images/insurance-icon-32.png" class="img-responsive"
						onclick="callJSP('health');">
				</div>
				<div class="circle col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<img class="boxes"
						onmouseover="$(this).attr('src','images/insurance-icon-29.png')"
						onmouseout="$(this).attr('src','images/insurance-icon-33.png')"
						src="images/insurance-icon-33.png" class="img-responsive"
						onclick="callJSP('travel');"> 
				</div> 
			</div>
		</div>
		<div id="wrapperMain"></div>
	</div>
	<div id="wait" style="display:none;width:100px;height:100px;position:fixed;top:43%;left:50%;padding:2px; z-index: 12;"><br></div>
	<div id="dialog-alert-wrapper">
	</div>
	
		<!-- <div class="modal fade modalClose"  id="showmodelMotor" role="dialog">
		<div class="modal-dialog">
			Modal content
			<div class="modal-content" id="logModelMotor">
				<div class="modal-header" style="background-color: #ff9e66;" id="logHeaderMotor">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Registration Form</h4>
				</div>
				<div id="contentBodyMotor" class="modal-body"  style="margin-top:120px"></div>
			</div>
		</div>
	</div> -->
	<%@include file="footer.jsp"%>
</body>
</html>
