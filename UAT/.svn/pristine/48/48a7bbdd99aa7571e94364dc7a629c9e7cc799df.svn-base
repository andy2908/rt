<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/sb-admin-2.css" rel="stylesheet">
<script type="text/javascript">
$(document).ready(function(){
    $("#showmodel").on('shown.bs.modal', function(){
        $(this).find('input[type="text"]').focus();
    });
});
	function loadLogin() {
		$.ajax({
			url : "${pageContext.request.contextPath}/loginModel",
			type : "post",
			async : "false",
			success : function(result) {
				//alert("success"+result);5
				$("#contentBody").html(result);
				$("#showmodel").modal('show');
				$("#logModel").css({
					'width' : '60%'
				});
				$("#logModel").css({
					'height' : '180px'
				});
				$("#logModel").css({
					'margin-left' : '160px'
				});
				$("#logModel").css({
					'margin-top' : '120px'
				});
				$("#logHeader").hide();
			}
		});
	}

	function loadAgentLogin() {
		$.ajax({
			url : "${pageContext.request.contextPath}/agentLoginModel",
			type : "post",
			async : "false",
			success : function(result) {
				//alert("success"+result);
				$("#contentBody").html(result);
				$("#showmodel").modal('show');
				$("#logModel").css({
					'width' : '60%'
				});
				$("#logModel").css({
					'height' : '180px'
				});
				$("#logModel").css({
					'margin-left' : '160px'
				});
				$("#logModel").css({
					'margin-top' : '120px'
				});
				$("#logHeader").hide();
			}
		});
	}

	function loadRegistration() {
		$.ajax({
			url : "${pageContext.request.contextPath}/registrationModel",
			type : "post",
			async : "false",
			success : function(result) {
				//alert("success"+result);
				$("#contentBody").html(result);
				$("#showmodel").modal('show');
				$("#logModel").css({
					'width' : '120%'
				});
				$("#logModel").css({
					'height' : 'auto'
				});
				$("#logModel").css({
					'margin-left' : '10px'
				});
				$("#logModel").css({
					'margin-top' : '20px'
				});
				$("#logHeader").show();
			}
		});
	}
</script>
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top navbar-fixed-top"
		role="navigation"
		style="background-color: white;border-color:transparent;">
	<div class="navbar-header" style="background-color: white;">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<div>
			<a href="#"><img src="images/logo.png" class="logo"></a>
		</div>
	</div>
	<ul class="nav navbar-top-links navbar-right collapse navbar-collapse"
		style="margin-top: 15px;">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"><i class="fa fa-user fa-fw"></i><i
				class="fa fa-caret-down"></i> </a>
			<ul class="dropdown-menu dropdown-user">
				<li onclick="loadLogin();" style="width: 110%;"><i class="fa fa-user fa-fw"></i>Customer Log
					In</li>
								<li onclick="loadRegistration();"><i class="fa fa-user fa-fw"></i>Sign
									Up</li>
				<li onclick="loadAgentLogin();"><i class="fa fa-user fa-fw"></i>POS
					Log In</li>
			</ul></li>
	</ul>
	</nav>
	<!-- log in model -->
	<div class="modal fade modalClose" id="showmodel" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content" id="logModel">
				<div class="modal-header" style="background-color: #ff9e66;" id="logHeader">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Registration Form</h4>
				</div>
				<div class="modal-body" id="contentBody"></div>
			</div>
		</div>
	</div>
</body>
</html>