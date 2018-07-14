<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<link rel="stylesheet" href="css/jquery.dataTables.min.css">
 <link href="css/jqx.base.css" rel="stylesheet">
 <link href="css/jqx.energyblue.css" rel="stylesheet">
 <script src="js/jqx-all.js"></script>
 <script src="js/jquery.dataTables.min.js"></script>
<script src="js/validation.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/productDetails.js"></script>
<style type="text/css">
.venueTable {
	width: 1000%;
}

.table, .th {
	border: 1px solid black;
	text-align: center;
	color: #ff7d33;
}

@media (min-width: 769px) and (max-width: 992px) {
  .btn-responsive {
    padding:4px 9px;
    font-size:90%;
    line-height: 1.2;
    border-radius:3px;
  }
}   
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$("#editbutton").hide();
		searchData();
		
	});
	
		$('#notificationText').jqxNotification({
		    width: "auto",
		    appendContainer: "#container1",
		    position: "left",
		    opacity: 0.9,
		   
		    autoClose: true,
		    template: "info"
		});

	
	
	function edittext() {
		var table = $('#venueTable').DataTable();
		$('#venueTable tbody').on('click', 'tr', function() {
			var tableData = $(this).children("td").map(function() {
				return $(this).text();
			}).get();
			id = $.trim(tableData[0]);
			var code = $.trim(tableData[1]);
			var name = $.trim(tableData[2]);
			var nameOl = $.trim(tableData[3]);
			document.getElementById('seatCodeTf').value = code;
			document.getElementById('seatNameTf').value = name;
			document.getElementById('seatNameOlTf').value = nameOl;
			document.getElementById("seatCodeTf").focus();
			$("#savebutton").hide();
			$("#editbutton").show();
		});

	}

	function editData() {
		$("#notificationText").hide();  
		
		/* alert("id>>"+id); */
		var venuecode = $('#seatCodeTf').val();
		var venuename = $('#seatNameTf').val();
		var venuenameol = $('#seatNameOlTf').val();
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1; //January is 0!
		var yyyy = today.getFullYear();
		if (dd < 10) {
			dd = '0' + dd
		}

		if (mm < 10) {
			mm = '0' + mm
		}

		today = dd + '/' + mm + '/' + yyyy;

		$.ajax({	
					'url' : "${pageContext.request.contextPath}/user/seatTypeEntry?pio_seat_type_id="
						+ id
						+ "&pi_opflag=M&pi_seat_type_code="
						+ venuecode
						+ "&pi_seat_type_name="
						+ venuename
						+ "&pi_seat_type_name_ol="
						+ venuenameol
						+ "&pi_str_id="
						+ ""
						+ "&pi_branch_id=3&pi_entry_date="
						+ today
						+ "&pi_op_date="
						+ today
						+ "&pi_user_id=118&pi_user_desc=sha&pi_status=E",	
					
					
			'type' : 'post',
			'async' : false,
			success : function(resp) {
				/* alert(resp.msg); */
			//	$("#updatetext").show();  
			/* 	$("#notificationText").show();  */ 
				 $("#notificationContent").html(resp.msg);
				 $("#notificationText").jqxNotification("open");
			},
			error : function(resp) {
			//	alert(resp.msg);
		/* 	$("#notificationText").show();   */
			$("#notificationContent").html(resp.msg);
			$("#notificationText").jqxNotification("open");
			},
			
		});
		clearData();
		reloadation();
			}

	function topFunction() {
		document.body.scrollTop = 0;
		document.documentElement.scrollTop = 0;
	}

	function deletedata() {
		var venuecode = $('#seatCodeTf').val();
		var venuename = $('#seatNameTf').val();
		var venuenameol = $('#seatNameOlTf').val();
		$("#notificationText").hide(); 
		
		var table = $('#venueTable').DataTable();
	
					$('#venueTable tbody').on('click','td',function() {
					 //	$('#venueTable tbody').on('click','td',function() {
							 $("#selectedIndex").val($(this).index());
							 $("#row_index").val($(this).closest("tr").index());
						       if($("#selectedIndex").val()=="5"){ 
					/*  var tableData = $(this).children("td").map(function() {
							return $(this).text();}).get();
					id = $.trim(tableData[1]);   */
				id= $('#venueTable tbody tr').eq( $("#row_index").val()).find('td').eq(0).html();
				 
			var txt;
			if (window.confirm("Are you Sure to delete this record??")) 
				{
						var today = new Date();
						var dd = today.getDate();
						var mm = today.getMonth() + 1; //January is 0!
						var yyyy = today.getFullYear();
						if (dd < 10) {
							dd = '0' + dd
						}
						if (mm < 10) {
							mm = '0' + mm
						}
						today = dd + '/' + mm + '/' + yyyy;
						$.ajax({
								'url' : "${pageContext.request.contextPath}/user/seatTypeEntry?pio_seat_type_id="
									+ "0"
									+ "&pi_opflag=D&pi_seat_type_code=&pi_seat_type_name=&pi_seat_type_name_ol=&pi_str_id="
									+ id
									+ "&pi_branch_id=3&pi_entry_date="
									+ today
									+ "&pi_op_date="
									+ today
									+ "&pi_user_id=118&pi_user_desc=sha&pi_status=D",
								
							'type' : 'post',
							'async' : false,
							success : function(resp) {
										//alert(resp.msg);
								/* $("#notificationText").show(); */  
								 $("#notificationContent").html(resp.msg);
								 $("#notificationText").jqxNotification("open");
								
							},
							error : function(resp) {
								//alert(resp.msg);
								/* $("#notificationText").show();  */ 
								 $("#notificationContent").html(resp.msg);
								 $("#notificationText").jqxNotification("open");
							},
						});
						reloadation();
						clearData();
				}
				else {
						alert("cancel");
					}
						       }
						});	            
				//});
					
	}

	function searchData() {
		showWait();
		var data = getRecordList("2002", "null");
		var volTable = $("#venueTable").DataTable(
						{
							'data' : data,
							"iDisplayLength" : 5,
							 "lengthMenu": [5, 10, 15],
						     "pageLength": 5,
							'columnDefs' : [
									{
										"width" : "20%",
										"targets" : 0
									},
									{
										'targets' : 5,
										'searchable' : false,
										'orderable' : false,
										'className' : 'dt-body-center',
										'render' : function(data, type, full,meta) {return '<img src="images/edit.png" id="editImage" style="width:20px" onclick="edittext();topFunction()">';
									}
								},
								{
										'targets' : 6,
										'searchable' : false,
										'orderable' : false,
										'className' : 'dt-body-center',
										'render' : function(data, type, full,meta) {return '<img src="images/delete.png" id="deleteImage" style="width:20px" onclick="deletedata()">';
									}
									}, ],

									"columns" : [ {
										"data" : null
									}, {
										"data" : "SEAT_TYPE_ID"
									}, {
										"data" : "SEAT_TYPE_CODE"
									}, {
										"data" : "SEAT_TYPE_NAME"
									}, {
										"data" : "SEAT_TYPE_NAME_OL"
									}, {
										"data" : null
									}, {
										"data" : null
									} ],

									'destroy' : true,
									"order" : [ [ 1, 'asc' ] ]

								});

	
		
		
		volTable.on('order.dt search.dt', function() {
			volTable.column(0, {
				search : 'applied',
				order : 'applied'
			}).nodes().each(function(cell, i) {
				cell.innerHTML = i + 1;
			});
		}).draw();
		
		hideWait();

	}
	
	
	
	function clearData() {

		document.getElementById('seatCodeTf').value = "";
		document.getElementById('seatNameTf').value = "";
		document.getElementById('seatNameOlTf').value = "";
		document.getElementById("seatCodeTf").focus();
		$("#editbutton").hide();
		$("#savebutton").show();

	}
	
	
	function saveData() {
		if(validateDatePicker()){
		$("#notificationText").hide();  
	
		var venuecode = $('#seatCodeTf').val();
		var venuename = $('#seatNameTf').val();
		var venuenameol = $('#seatNameOlTf').val();
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1; //January is 0!
		var yyyy = today.getFullYear();
		if (dd < 10) {
			dd = '0' + dd
		}

		if (mm < 10) {
			mm = '0' + mm
		}

		today = dd + '/' + mm + '/' + yyyy;
		$
				.ajax({	
							'url' : "${pageContext.request.contextPath}/user/seatTypeEntry?pio_seat_type_id=0&pi_opflag=N&pi_seat_type_code="
								+ venuecode
								+ "&pi_seat_type_name="
								+ venuename
								+ "&pi_seat_type_name_ol="
								+ venuenameol
								+ "&pi_str_id="
								+ ""
								+ "&pi_branch_id=3&pi_entry_date="
								+ today
								+ "&pi_op_date="
								+ today
								+ "&pi_user_id=118&pi_user_desc=sha&pi_status=E",	
							
							
					'type' : 'post',
					'async' : false,
					success : function(resp) {
					
						 
						/* $("#notificationText").show();   */
						$("#updatetext").hide();  
						$("#notificationContent").html(resp.msg);
						  $("#notificationText").jqxNotification("open");
					},
					error : function(resp) {
						 alert("in error>>> "+resp.msg); 
						 $("#updatetext").hide();  
						 $("#notificationContent").html(resp.msg);
						  $("#notificationText").jqxNotification("open");
					},
				});
		reloadation();
		clearData();
		location.relaod();
		

	}
	}

	function validateDatePicker(){
		
		if (!$('#seatCodeTf').val()) {
			alert("Please Enter Type Code!!");
			 $('#seatCodeTf').focus();
			
			return false;
		}
		if (!$('#seatNameTf').val()) {
			alert("Please Enter Type Name!!");
			 $('#seatNameTf').focus();
			
			return false;
		}
		if (!$('#seatNameOlTf').val()) {
			alert("Please Enter Type NameOl!!");
			 $('#seatNameOlTf').focus();
			
			return false;
		}
		
		return true;
	}
	
	
	function closeForm() {
		$("#mainform").hide();
		location.relaod();
	}
	function reloadation() {
		$('#venueTable td').remove();
		searchData();

	}
	
	
	
	
	
	
</script>
</head>
<body>
	
	<div class="container-fluid" id="container1" id="mainform" style="padding: 5px;" > 
				 	<div id="notificationText" style="display:none;">
                        <div id="notificationContent" style="z-index: 99999; opacity: 0.9;float: inherit;text-align: center;"> </div>
                   </div>
    	<div class="panel-body" id="collapseTwo"
			style="background-color: #ffffff;box-shadow: 5px 5px 30px 5px #888888;" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne"
			class="main wrapper2" >
			<div class="row" style="padding: 5px">
		        	<div class="form-group col-sm-4 col-md-4 col-lg-4" style="padding:-1px;margin:0;">
						<font color="#ff7d33"><label class="mandatory">
								Type Code </label></font>
						<input id="seatCodeTf" type="text" autofocus="autofocus"
						onkeypress="tf1Focus(event)" tabIndex="1" class="form-control input-normal" placeholder="Enter Type Code">
					</div>
				    <div class="form-group col-sm-4 col-md-4 col-lg-4" style="padding:-1px;margin:0;">
						<font color="#ff7d33"><label class="mandatory">
								Type Name</label></font>
						<input id="seatNameTf" type="text" onkeypress="tf2Focus(event)"
							tabIndex="2" class="form-control input-normal" placeholder="Enter Type Name">
					</div>
				    <div class="form-group col-sm-4 col-md-4 col-lg-4" style="padding:-1px;margin:0;">
						<font color="#ff7d33"><label for="bankName"
							class="mandatory"> Type Name Ol</label></font>
						<input id="seatNameOlTf" type="text" onkeypress="tf3Focus(event)"
							tabIndex="3" class="form-control input-normal" placeholder="Enter Type Name Ol">
					</div>
			</div>
			
		  <!--  <div class="col-md-4">
			 <div id="container1" > 
					<div id="notificationText" style="display:none">
                        <div id="notificationContent"> </div>
                   </div>
             </div> 
          
          </div>
				
				<div class="col-md-8" style="padding: 5px;">
				<div class="col-md-2"></div>
			
				
				 
						<input type="button" id='savebutton' value="Save"  onclick="saveData()" class="btn btn-primary" tabIndex="4"/> 
						<input type="button" id="editbutton" value="Update"  onclick="editData()" class="btn btn-primary" tabIndex="5"/>
					    <input type="button" id="clearbutton" value="Clear"  onclick="clearData()"tabIndex="6" class="btn btn-primary"/> 
					    <input type="button" id="closebutton" value="Close" onclick="closeForm();"tabIndex="7" class="btn btn-primary"/>  
						
			</div>	
			</div>
			<div class="row" style="height: 35px;">
						<div class="col-md-4">
						<label for="bankName"><font color="#ffffff"> Search</font></label>	
						 <input id="searchTextField" type="text" onkeyup="searchAction()">	
						</div>
						</div>   -->
											
					<div class="modal-footer">
					
					    <div class="col-md-4 col-sm-4 col-lg-4"></div>
						<button id="savebutton" type="button" class="btn btn-primary btn-responsive"
							onclick="saveData()" tabIndex="4" style="width: 60px;">Save</button>
						<button id="editbutton" type="button" class="btn btn-primary btn-responsive"
							onclick="editData()" tabIndex="5" style="width: 60px;">Update</button>
						<button type="button" class="btn btn-primary btn-responsive" 
							style="margin-left: 4px;width: 60px;" onclick="clearData()" tabIndex="6">Clear</button>
					</div>
						
					
			<div class="table-responsive">		
			   <table id="venueTable" class="table" border="1" >
					<thead class="th">
					<tr style="background-color: #dac8b6; table-layout: auto;">
						<th data-visible="false">Sr No</th>
						<th> Type Id</th>
						<!-- data-visible="false" -->
						<th> Type Code</th>
						<th> Type Name</th>
						<th> Type Name Ol</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				    </thead>
			   </table>
		   </div>			
		 <input type="hidden" id="selectedIndex">
	<input type="hidden" id=row_index>
	 </div> 
	 </div>
</body>
</html>







