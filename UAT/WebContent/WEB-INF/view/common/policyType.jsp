<%-- <%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/sb-admin-2.css" rel="stylesheet">
<style>
.l_arrowicon {
    cursor: pointer;
    display: block;
    float: left;
    font-size: 45px;
    margin-top: -25px;
    margin-left: 11px;
    font-weight: 900;
}
</style>

<script type="text/javascript">
function openPolicyType(id){
//	alert(id);
	if(id == "package"){
	$("#policyTypeId").val("1");
	}
	else if(id == "liability")
	{
		$("#policyTypeId").val("2");
	}
	proposal();
}

function proposal() {

	var mainDiv="";
	
	var resp = getInsFindFormData("PKG_INS_FIND","find_proposal_type","AMCP","","","","","","","","","","","","","","","","","","","","","","","","0","HB EMPLOYEE","","");
	var proposalType= "";
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		var key1= parseInt(key) + 1;
		proposalType=  resp[key].PROPOSAL_TYPE;
		/* mainDiv = '<div ><a href=javascript:openPolicyTypeModal(+"policyTypeIns"+)> Go Back' +"</a></div>" */
		if(proposalType == "NEW"){
		mainDiv =mainDiv + '<tr><td><img src="images/new1.png" style="width:300px;height: 100px;margin-left: 2px;" id='+resp[key].PID+' onclick="openProductDetails(this.id,\''+proposalType+'\');"></td></tr>';
		}
		else if(proposalType=="USED"){
			mainDiv = mainDiv + '<tr><td><img src="images/used1.png" style="width:300px;height: 100px;margin-left: 2px;" id='+resp[key].PID+' onclick="openProductDetails(this.id,\''+proposalType+'\');"></td></tr>';
		}
		console.log(resp);
	});
	console.log(proposalType);
	
	$("#contentBodyMotor").html(mainDiv);
	$("#showmodelMotor").modal('show');
	$("#logModelMotor").css({
		//'width' : '350px',
		'height' : 'auto',
		'margin-left' : '120px',
		'margin-top' : '100px',
		'position':'relative'
	});
	$("#logHeaderMotor").hide();
}

function openProductDetails(id,proposalType){
	//alert("4444444444444" + proposalType);
	var proposalTypeId = id;
	/* if(id==1){ */
		//alert("55555555555555");
	$("#proposal").val(proposalTypeId);
	$("#proposalTf").val(proposalType);
	//alert("proposal type:::>>>"+ $("#proposal").val());
	product();
 
}

function product() {
	//$("#productContainer").remove();
	var mainDiv="";
	var resp = getInsFindFormData("PKG_INS_FIND","find_product","AMCP","","","","","","","","","","","","","","","","","","","","","","","","0","HB EMPLOYEE","","");
	var key2=0;
	var keySize =0;
	
	for(var key = 0;key<resp.length;key++){
		console.log("dadada"+key);
		var tr = "<tr>";
		var productName = resp[key].PRODUCTNAME;
		var td1 = '<td id='+resp[key].PRODUCTID+' style=\"font-weight: bold;font-size: 12px;border: 1px solid #008B8B;background-color: #F08000;text-align: left; padding: 8px; width:300px; height:50px\" onclick="getProductName(this.id,\''+productName+'\');"><font color="white">'+resp[key].PRODUCTNAME +"</font></td>";
		key = parseInt(key) + 1; 
		var td2 = "";
		if(key<resp.length){
			productName = resp[key].PRODUCTNAME;
			td2 = '<td id='+resp[key].PRODUCTID +' style=\"font-weight: bold;font-size: 12px; border: 1px solid #008B8B;background-color: #F08000;text-align: left;padding: 8px; width:300px ; height:50px\" onclick="getProductName(this.id,\''+productName+'\');"><font color="white">'+resp[key].PRODUCTNAME+"<font></td>";
		}
		tr = tr + td1 + td2 + "</tr>";
		
		mainDiv = mainDiv + tr;
	}
	$("#contentBodyMotor").html(mainDiv);
	$("#showmodelMotor").modal('show');
	$("#logModelMotor").css({
		'width' : '630px',
		'height' : 'auto',
		'margin-left' : '10px',
		'margin-top' : '100px',
		'position':'relative'
	});
	$("#logHeaderMotor").hide();
	
	//console.log(resp);
}

function getProductName(id, productName) {
	//alert("1111111111111111111" + productName);
	console.log("Product Id:::" +id);
	//alert("Product Id:::" +id);
	var productId = id;
	console.log("proposal Id:::"+ $("#proposal").val());
	var proposalType =  $("#proposal").val();
	$("#productname").val(productId);
	$("#productnameTf").val(productName);
	
		$("#showmodel").modal('hide');
		$("#wrapperMain").show();
		$("#contentBodyMotor").hide();
		$("#showmodelMotor").modal('hide');
		year();
		 var policyType = $("#policyTypeId").val();
		 if(policyType == "1")
		 {
			//alert("in show ::>> "+policyType);
			 $(".liability").show();
			 
		 }else if(policyType == "2")
		 {
			// alert("in hide ::>> "+policyType);
			 $(".liability").hide();
			 $(".formatDiv").css({
					'width':'1170px'});
		}
			
		console.log("you are in ");
	/* }  */
}

	
</script>
</head>
<body>
<div class="container"
		style="width: 350px; height:auto;margin: 0;">
	<div class="row">
	<div ><a href = "http://localhost:8085/UAT/"> Go Back </a></div>
	<table>
	<tr>
	<td>
		<img src="images/package1.png" style="width:300px;height: 100px;margin-left: 2px;" id="package" onclick="openPolicyType(this.id);">
	</td>
	</tr>
	
	<tr>
	<td>
		<img src="images/liability1.png" style="width:300px;height: 100px;margin-left: 2px;" id="liability" onclick="openPolicyType(this.id);"> 
	</td>
	</tr>
	</table>
	</div>
	
</div>
</body>
</html> --%>