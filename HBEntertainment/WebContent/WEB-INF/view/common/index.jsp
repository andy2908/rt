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
    <title>Hopebox Entertainment</title>
	<link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/metisMenu.min.css" rel="stylesheet">
    <link href="css/sb-admin-2.css" rel="stylesheet">
<!--     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
	<link rel="stylesheet" href="css/font-awesome.min.css">
    <link href="css/morris.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/metisMenu.min.js"></script>
    <script src="js/raphael.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/validation.js"></script>
	<script type="text/javascript" src="js/select2.js"></script>
	<script type="text/javascript">
	 $(document).ready(function() {
	    	$("#myCarousel").show();
			$("#wrapperMain").hide();
// 			showWait();
			
// 			setTimeout(function(){hideWait();},20000);
	    });
		function redirect(formName){
			$.ajax({
  			  url: formName,
  			  context: document.body
  			}).done(function(resp) {
	   			  $("#wrapperMain").html(resp);
	   			  $("#myCarousel").hide();
	   			  $("#wrapperMain").show();
  			});
		}
		function closeForm(){
    		$("#myCarousel").show();
    		$("#wrapperMain").empty();
   		 	$("#wrapperMain").hide();
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
<%
User user = (User) session.getAttribute("user");
if(user!=null){
%>
<script type="text/javascript">
	location.href = "${pageContext.request.contextPath}/welcome";
</script>
<%
}
%>
 <%@include file="header.jsp"%>

    <div class="container" >
	   
	   	<div class="row">
	    <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
		    <ol class="carousel-indicators">
		      <li data-target="#myCarousel" data-slide-to="0" class="active" style="background-color: orange;"></li>
		      <li data-target="#myCarousel" data-slide-to="1" style="background-color: orange;"></li>
		     
		    </ol>

    <!-- Wrapper for slides -->
	    <div class="carousel-inner">
		      <div class="item active">
		        <img src="images/event-01.jpg" style="width:100%;" onclick="redirect('bookNow')">
		      </div>
		     <div class="item">
		        <img class="img-responsive" onmouseover="this.src='images/event-03.jpg';" onmouseout="this.src='images/event-02.jpg';" src="images/event-02.jpg" style="width:100%;"  onclick="redirect('bookNow')">
		      </div> 
	    </div>
    <!-- Left and right controls -->
	    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
	      <span class="glyphicon glyphicon-chevron-left"></span>
	      <span class="sr-only">Previous</span>
	    </a>
	    <a class="right carousel-control" href="#myCarousel" data-slide="next">
	      <span class="glyphicon glyphicon-chevron-right"></span>
	      <span class="sr-only">Next</span>
	    </a>

	    </div>
	    
	     <div id="wrapperMain">
	    </div>
	</div>
	</div>
	<div id="wait" style="display:none;width:100px;height:100px;position:absolute;top:43%;left:50%;padding:2px; z-index: 99999;"><br></div>
	   <%@include file="footer.jsp"%>
</body>
</html>
