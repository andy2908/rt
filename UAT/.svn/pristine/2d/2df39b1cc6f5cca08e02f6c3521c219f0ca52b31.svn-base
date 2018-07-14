<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- <link rel="stylesheet" href="css/jqx.base.css" type="text/css" /> -->
<link rel="stylesheet" href="css/jquery.dataTables.min.css">
<link rel="stylesheet" href="css/datepicker3.css">
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/validation.js"></script>
<script src="js/jquery.dataTables.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		  $("#example1").hide();   
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
		 $("#example1").show();
		  }
	} 
	
	

function searchData() {
var b=$("#to1").val();

if(b!=0){
	var fromDate = document.getElementById("from1").value;
	var toDate = document.getElementById("to1").value;

	$
			.ajax({
				url : "${pageContext.request.contextPath}/user/fetchViewLedgerData?procName=VIEW_LEDGER&lType=&find=VIEW&glId=&status=&vType=&strLid=&fromDate="
						+ fromDate + "&toDate=" + toDate,
				type : 'post',
				dataType : 'json',
				async : false,

				success : function(data) {
					console.log(data);
					volTable = $("#example1").DataTable({
						/* $.extend(true, $.fn.dataTable.defaults, {
						    "lengthMenu": [[5, 10, 15, 20, 25], [5, 10, 15, 20, 25]],
						    "pageLength": 5

						});		 */
						'data' : data,
						"iDisplayLength" : 5,
						/* 'columnDefs' : [
								{
									'targets' : 4,
									'searchable' : false,
									'orderable' : false,
									'className' : 'dt-body-center',
									'render' : function(
											data, type,
											full, meta) {
										return '<img src="images/edit.png", class="img1" id="edit" style="width:100" "height:100" onclick="gettext()" >';
									}
								},
								{
									'targets' : 5,
									'searchable' : false,
									'orderable' : false,
									'className' : 'dt-body-center',
									'render' : function(
											data, type,
											full, meta) {
										return '<img src="images/delete.png", class="img2" style="width:100" "height:100" onclick="deleteData()">';
									}
								}
								/* {
									"targets" : 0,
									"visible" : false
								} 
								], */

						"columns" : [

						{
							"data" : "VD_ID"
						}, {
							"data" : "VNO"
						}, {
							"data" : "SVNO"
						}, {
							"data" : "V_DATE"
						}, {
							"data" : "L_ID"
						}, {
							"data" : "PARTICULAR"
						}, {
							"data" : "DEBIT"
						}, {
							"data" : "CREDIT"
						}, {
							"data" : "BALANCE"
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
				
					<div id="to1" >
        	
        		</div>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-12"><input type="submit" class="btn btn-primary"value="Show" onclick="tableShow();searchData()"style="float: right;"><br><br></div>
		<br><br><br>
		
		
		</div>

		<div class="row" style="background-color: #ffffff; box-shadow: 5px 5px 30px 5px #888888;;">
			
				<table id="example1" class="table" >
					<thead>
						<tr>
							<th data-visible="false" style="display: none">VD_ID</th>
							<th style="color: #ff9e66;">VNO</th>
							<th data-visible="false" style="display: none">SVNO</th>
							<th style="color: #ff9e66;">DATE</th>
							<th data-visible="false" style="display: none">L_ID</th>
							<th style="color: #ff9e66;">PARTICULAR</th>
							<th style="color: #ff9e66;">AMOUNT (CREDIT)</th>
							<th style="color: #ff9e66;">AMOUNT (DEBIT)</th>
							<th style="color: #ff9e66;">BALANCE</th>
						</tr>
					</thead>
				</table>
			
		</div>

	</div>
</body>
</html>