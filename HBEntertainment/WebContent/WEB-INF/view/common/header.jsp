<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/sb-admin-2.css" rel="stylesheet">
<script src="js/validation.js"></script>

<script type="text/javascript">
$(document).ready(function(){
    $("#showmodel").on('shown.bs.modal', function(){
        $(this).find('input[type="text"]').focus();
    });
});
	function loadRegistration(){
	    //alert("loadig");
	    $.ajax({
	    	url: "${pageContext.request.contextPath}/registerModel",
	    	type: "post",
	    	async: "false",
	    	success: function(result){
	        //alert("success"+result);
	        $("#contentBody").html(result);
	        $("#showmodel").modal('show'); 
	        $("#content").css({'width':'83.3%'}); 
	        $("#content").css({'height':'50px'});
	        $("#modalBox").show(); 
	    }});
	}
	
	function loadLogin(){
	    //alert("loadig");
	    $.ajax({
	    	url: "${pageContext.request.contextPath}/loginModel",
	    	type: "post",
	    	async: "false",
	    	success: function(result){
	        //alert("success"+result);
	        $("#contentBody").html(result);
	        $("#showmodel").modal('show'); 
	        $("#content").css({'width':'60%'}); 
	        $("#content").css({'height':'200px'});
	        $("#modalBox").hide(); 
	       
	    }});
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
		<div class=""> 
			<a href="#"><img src="images/logo.png" class="logo"></a>
		</div>
	</div>
	<ul class="nav navbar-top-links navbar-right collapse navbar-collapse"
		style="margin-top: 15px;">
		<!--                 <li class="content"> -->
		<!--                 	<a href="" class="nav">Home</a> -->
		<!--                 </li> -->
		<!--                 <li class="content"> -->
		<!--                 	<a href="#" class="nav">AboutUs</a> -->
		<!--                 </li> -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"><i class="fa fa-user fa-fw"></i><i
				class="fa fa-caret-down"></i> </a>
			<ul class="dropdown-menu dropdown-user">
				<li onclick="loadLogin();"><i
					class="fa fa-user fa-fw"></i>Log In</li>
				<li onclick="loadRegistration();"><i class="fa fa-gear fa-fw"></i>Sign Up</li>
			</ul>
		</li>
	</ul>
	</nav>
	<!-- log in model -->
	<div class="modal fade" id="showmodel" role="dialog">
		<div class="modal-dialog" >
			<!-- Modal content-->
			<div class="modal-content" id="content">
				<div class="modal-header" style="background-color: #fff;" id="modalBox">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Registration Form</h4>
				</div>
				<div class="modal-body" id="contentBody" > 
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>