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

	function openForm()
	{
	//	$("#showmodel").modal('hide');
	//	$("#wrapperMain").show();
//		callJSP('motor');
		$("#showmodel").modal('hide');
		$("#wrapperMain").show();
		$("#contentBodyMotor").hide();
		$("#showmodelMotor").modal('hide');
	//	$("#showmodelMotor").hide();
	/* 	$("#logModelMotor").hide();
		$("#logHeaderMotor").hide(); */
		
	}
	
</script>
</head>
<body>
<div class="container" style="background-color: #F08000;width:300px;margin-top:5px;height:150px;" >
	<div class="row">
	<br>
		<label>&nbsp;&nbsp;Does Previous Policy Exists?</label>
	</div>
	<br>
	<!--  <div class="row" >
	//	 style=\"font-weight: bold;font-size: 12px;border: 1px solid #008B8B;background-color: #F08000;text-align: left; padding: 8px; width:300px; height:50px\
		&nbsp;&nbsp;<label><input id="prevPolYes" type="radio" onclick="" name="prevPolicy"style="width: 12px"  value="prevYes"><font color="#ffffff">&nbsp;&nbsp;Yes</font></label>
		<br>&nbsp;&nbsp;<label><input id="prevPolNo" type="radio"  onclick="openForm();"   name="prevPolicy"><font color="#ffffff"  value="prevno">&nbsp;&nbsp;No</font></label>
				
	</div> -->
	 <div class="row" >
	<!-- //	 style=\"font-weight: bold;font-size: 12px;border: 1px solid #008B8B;background-color: #F08000;text-align: left; padding: 8px; width:300px; height:50px\ -->
		&nbsp;&nbsp;<label><input id="prevPolYes" type="radio" onclick="openForm();" name="prevPolicy"style="width: 12px"  value="prevYes"><font color="#ffffff">&nbsp;&nbsp;Yes</font></label>
		<br>&nbsp;&nbsp;<label><input id="prevPolNo" type="radio"  onclick="openForm();"   name="prevPolicy"><font color="#ffffff"  value="prevno">&nbsp;&nbsp;No</font></label>
				
	</div>
</div>

</body>
</html>