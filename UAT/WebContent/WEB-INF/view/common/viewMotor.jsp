<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Motor</title>
<link rel="stylesheet" href="css/datepicker3.css">
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="css/jquery.dataTables.min.css">
<script type="text/javascript">
$(document).ready(function() {
	  $("#viewMotorTable").hide();   
	    var today = new Date();
		var yr = today.getFullYear();
		var mm = today.getMonth();
		var mm1 = (today.getMonth())-2;
		var dd = today.getDate();
		
		var yr2 = today.getFullYear();
		var mm2 = today.getMonth();
		
		var dd2 = today.getDate();
		/* var year= parseInt(yr)-18;
		console.log("year::>>"+year); */


		 $("#from1").jqxDateTimeInput({ width: '220px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy",min: new Date(yr, mm1, dd), max: new Date(yr, mm, dd)});
		 $("#from1").val(null);
		 $('#from1').on('click', function () {
		     $('#from1').jqxDateTimeInput('open');
		 });
		 
	
		
		 
		 $("#to1").jqxDateTimeInput({ width: '220px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy"});
		
		 
		 $("#to1").val(null);
		 $('#to1').on('click', function () {
		     $('#to1').jqxDateTimeInput('open'); 
		 });
	

});

function setToDate(){
		var fromDate= $("#from1").val();
		 var regDate = fromDate.split("/");
		 
	        var regDay = regDate[0];
	    	var regMonth = (regDate[1])-1;
			var regYr = regDate[2];
		
			
			 var today = new Date();
	
				var yr2 = today.getFullYear();
				var mm2 = today.getMonth();
				
				var dd2 = today.getDate();
			console.log("year::>> "+regDay+"/"+regMonth+"/"+regYr);
		
		 $("#to1").jqxDateTimeInput({ width: '220px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy",min: new Date(regYr, regMonth, regDay), max: new Date(yr2, mm2, dd2)}); 
	} 




function tableShow() {
	  var b=$("#to1").val();

	  if(b!=0){
	 $("#viewMotorTable").show();
} }


	function loadMotorApplication() {
		 
		$.ajax({
			url : "${pageContext.request.contextPath}/MotorApplication",
			type : "post",
			async : "false",
			success : function(result) {
				//alert("success"+result);5
				$("#contentBody").html(result);
				$("#showmodel").modal('show');
				$(".modal-content").css({
					'width' : '1100px'
				});
				$(".modal-content").css({
					'height' : ''
				});
				$(".modal-content").css({
					'margin-left' : '-200px'
				});
				$(".modal-content").css({
					'margin-top' : '100px'
				});
				$(".modal-header").show();
			}
		});
	}

	function searchData() {
		 var b=$("#to1").val();

		  if(b!=0){
		var userid = $("#userId").val();
		var fromDate =$("#from1").val();
		var toDate = $("#to1").val();
		var appID = "0";
		var prodId = "";
		var gicId = "";
		var prpslID = "";
		var applicantID = "";
		var hbbID = "";
		var branch_id = "3";
		var policytype = "1";
		var appStatus = "";
		$
				.ajax({
					'url' : "${pageContext.request.contextPath}/user/ViewMotorApplicationFetch",
					'type' : 'post',
					'async' : false,
					'data' : {
						find : "VIEWALL",
						appID : appID,
						prodId : prodId,
						gicId : gicId,
						prpslID : prpslID,
						applicantID : applicantID,
						hbbID : hbbID,
						branch_mst_id : branch_id,
						policytype : policytype,
						userid : userid,
						frmDt : fromDate,
						toDt : toDate,
						appStatus : appStatus,
						payMode : "",
						startDt : "",
						endDt : ""
					},
					success : function(data) {
						console.log(data);
						var volTable = $("#viewMotorTable")
								.DataTable(
										{
											'data' : data,
											/* 	"iDisplayLength" : 5, */

											"columns" : [

													{
														"data" : "APP_ID",

														"render" : function(
																data, type,
																row, meta) {
															if (type === 'display') {
																data = '<a onclick="loadMotorApplication();">'
																		+ data
																		+ '</a>';
															}

															return data;
														}
													},
													{
														"data" : "APPLICATION_DATE"
													},
													{
														"data" : "APP_STATUS"
													},
													{
														"data" : "POLICY_TYPE"
													},
													{
														"data" : "APPLICANT_NAME"
													},
													{
														"data" : "COMPANY"
													},
													{
														"data" : "PRODUCT"
													},
													{
														"data" : "INS_START_DT"
													},
													{
														"data" : "INS_END_DATE"
													},
													{
														"data" : "IDV"
													},
													{
														"data" : "OD_PREM"
													},
													{
														"data" : "TP_PREM"
													},
													{
														"data" : "TOTAL_PAYMENT"
													},
													{
														"data" : "PROPOSAL_TYPE"
													},
													{
														"data" : "POLICY_PERIOD"
													}, {
														"data" : "REG_NO"
													}, {
														"data" : "ENGIN_NO"
													}, {
														"data" : "CHASIS_NO"
													}, {
														"data" : "APPLICANT_ID"
													} ],
											'destroy' : true,

										});
					},
				
				});
		 return true;
	}
		  alert("Select From Date & To Date First");
		  return false;
		  }
</script>
</head>
<body>
	<input id="userId" type="hidden" value="${userId}">

		<!-- <div class="col-md-5">
			<div class="col-md-3">
				<label for="fromDate"><font color="#ffffff">From Date</font></label>
			</div>
			<div class="col-md-3">
			<div id="from1" placeholder="dd/mm/yyyy" tabIndex="5" type="text" style="width: 220px; height: 26px;" class="numberOnly" onchange="setToDate()">
        		</div>
				<input type="text" class="datepicker" id="fromDate">
			</div>
		</div>
		<div class="col-md-4">
			<div class="col-md-3">
				<label for="toDate"><font color="#ffffff">To Date</font></label>
			</div>
			<div class="col-md-3">
			<div id="to1" ></div>
				<input type="text" class="datepicker" id="toDate"
					onchange="searchData()">
					<div class="col-md-12"><input type="submit" class="btn btn-primary"value="Show" onclick="tableShow();searchData()"style="float: right;"><br><br></div>
			
		</div>
	</div> -->



<div class="container-fluid">
	<br><br>

		<div class="row" style="background-color: #ffffff; box-shadow: 5px 5px 30px 5px #888888;">
		<br><br><br>
			<div class="col-md-4"></div>
			<div class=" col-md-6 ">
				<div class="col-md-2">
					<label for="FromDate"><font color="#ff9e66">From</font> </label>
				</div>
			<div id="from1" placeholder="dd/mm/yyyy" tabIndex="5" type="text" style="width: 220px; height: 26px;" class="numberOnly" onchange="setToDate()">
        		</div>

			</div>
			<div class="col-md-2"></div>
<br><br><br>
			<div class="col-md-4"></div>
			<div class="col-md-6">
				<div class="col-md-2">
					<label for="toDate"><font color="#ff9e66">To</font> </label>
				</div>
				
					<div id="to1" ></div>
        	
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-12"><input type="submit" class="btn btn-primary"value="Show" onclick="tableShow();searchData()"style="float: right;"><br><br></div>
		<br><br><br>


	</div>



	

	<div class="row" style="background-color: #ffffff; box-shadow: 5px 5px 30px 5px #888888;">
				<table id="viewMotorTable" class="table" border="1">
					<thead class="th">
						<tr style="background-color: #dac8b6;">
							<th>App Id</th>
							<th data-visible="false" style="display: none">App Date</th>
							<th>Gic App Status</th>
							<th>Policy Type</th>
							<th>APPLICANT NAME</th>
							<th>COMPANY</th>
							<!-- 			<th>Status</th>      -->
							<th>PRODUCT</th>

							<th>INSURANCE START DATE</th>
							<th data-visible="false" style="display: none">INS_END_DATE</th>
							<th data-visible="false" style="display: none">IDV</th>
							<th data-visible="false" style="display: none">OD_PREM</th>
							<th data-visible="false" style="display: none">TP_PREM</th>
							<th data-visible="false" style="display: none">TOTAL_PAYMENT</th>
							<th data-visible="false" style="display: none">PROPOSAL_TYPE</th>
							<th data-visible="false" style="display: none">POLICY_PERIOD</th>
							<th data-visible="false" style="display: none">REG_NO</th>
							<th data-visible="false" style="display: none">ENGIN_NO</th>
							<th data-visible="false" style="display: none">CHASIS_NO</th>
							<th data-visible="false" style="display: none">APPLICANT_ID</th>
						</tr>
					</thead>
				</table>
			</div>
			</div>
		
</body>
</html>

