<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/select2.css">
<link rel="stylesheet" href="css/jquery.dataTables.min.css">
<script	src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/select2.js"></script>
<script src="js/dataTables.checkboxes.min.js"></script>
<script src="js/dataTables.checkboxes.min.js"></script>
<script src="js/dataTables.select.min.js"></script>
<script src="js/validation.js"></script>




<!-- <script	src="js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="css/jquery.dataTables.min.css">
<script src="js/dataTables.select.min.js"></script> -->
<script type="text/javascript">
	$(document).ready(function() {
		
		
		
		$("#updatedata").hide(); 
		$("#deleteData").hide(); 
		$("#editbutton").hide();
		 $("#savedata").show();  
		    $('#example').DataTable( {
		        "scrollX": true
		    } );
		
		
		
		document.getElementById("venueNametf").focus();
		$("#venueTypeSelect").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#venueStructureSelect").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#countrySelect").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#stateSelect").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#districtSelect").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#citySelect").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#pincodeSelect").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});
		
		
		getSeatType();
		getVenueType();
		getCountry();

		$('input[type="file"]').change(function(e) {
			var fileName = e.target.files[0].name;
			alert('The file "' + fileName + '" has been selected.');
		});

		

	});

	function deleteData1() {
		if (!$('#venueTypeSelect').val()) {
			alert("Please Select Venue Type.");
			$('#venueTypeSelect').select2('focus');
			return false;
		}
		
		if (!$('#venueMap').val()) {
			alert("Please Select Venue Map");
			$('#venueMap').focus();
			return false;
		}
		var stat = validateFormArray([ '#venueNametf', '#phoneNotf',
		               				'#mobileNotf', '#emailIDtf', '#websitetf', '#venueTypeSelect', 
		               				 '#totalSeatTf', '#venueMap', '#fileLoader','#countrySelect',
		               				'#stateSelect', '#districtSelect', '#citySelect', '#pincodeSelect', '#landmarkTf',
		               				'#streetTf', '#houseNoTf' ]);
		var tb = document.getElementById("tb2");
		var ctb = tb.rows.length - 1;

		var cnt = 19;
		
		var rowNoStr = "";
		var noOfSeatStr = "";
		var seatTypeStr = "";
		var rowStructureStr = "";
		var seatStatusStr = "";
		var venueLineIdStr = "";
		var srnoStr = "";
	
		

		for (var i = 2; i <= ctb; i++) {
			var rowNo = $(tb.rows[i].cells[1].getElementsByTagName('input'))
					.val();
		
			var noOfSeat = $(tb.rows[i].cells[2].getElementsByTagName('input'))
					.val();
			
						var seatType	=$("#seatTypeID").val();
			
			
			var rowStructure = $(tb.rows[i].cells[4].getElementsByTagName('input'))
					.val();
		
			var seatStatus = "Y";
			var srno = "1";
		
			var venueLineId = $("#venueLineId").val();
			if (rowNo != "" && noOfSeat != "" && seatType != "" && rowStructure != "") {
				
				rowNoStr = rowNoStr + rowNo + "~";
				
				noOfSeatStr = noOfSeatStr + noOfSeat + "~";
				seatTypeStr = seatTypeStr + seatType + "~";
				rowStructureStr = rowStructureStr + rowStructure + "~";
				seatStatusStr=seatStatusStr + seatStatus+ "~";
				venueLineIdStr=venueLineIdStr + venueLineId+ "~";
				srnoStr=srnoStr + srno+ "~";
			}
			
		}

		console.log("rowNoStr:::" + rowNoStr);
		if (rowNoStr.split("~").length > 1) {
			var stat = true;
		} else {
			alert("Please Enter Alteast One Row in Seat Layout!");
			var stat = false;
		}
		var venueHeadID = $("#venueHeadID").val();
		if(stat && venueHeadID!=null){
			
		var venueName = $("#venueNametf").val();
		var phoneNo = $("#phoneNotf").val();
		var mobileNo1 = $("#mobileNotf").val();
		var mobileNo2 = $("#mobileNotf").val();

		var emailId = $("#emailIDtf").val();

		var website = $("#websitetf").val();

		var venueTypeId = $("#venueTypeSelect").val();

		var venueStucture = $("#venueStructureTf");
		 if (venueStucture.val() == "") {
           //If the "Please Select" option is selected display error.
           alert("Please select Venue Stucture!");
           return false;
       }else{
       	var venueStucture = $("#venueStructureTf").val();
       }

		
		var totalSeats = $("#totalSeatTf").val();

		var adrName = "";
		var areaId = $("#pincodeSelect").val();

		var house = $("#houseNoTf").val();

		var landmarkId = $("#landmarkList option[value='" + $('#landmarkTf').val() + "']").attr('rel');
	    if(landmarkId == undefined ||  landmarkId == "")
		{
	    	landmarkId="0";
		}
		
	    var streetId = $("#streetList option[value='" + $('#streetTf').val() + "']").attr('rel');
	    if(streetId == undefined ||  streetId == "")
		{
	    	streetId="0";
		}
	    
	    var houseNoId = $("#houseNoList option[value='" + $('#houseTf').val() + "']").attr('rel');
	    if(houseNoId == undefined ||  houseNoId == "")
		{
	    	houseNoId="0";
		}
	    
		var street = $("#streetTf").val();

		var landmark = $("#landmarkTf").val();

		var srno = "1";
		var venueLineId = "1";
		
		var seatStatus = "F";
		
		var formData = new FormData();
		
		var $file1 = document.getElementById('fileLoader');
		
		if ($file1.files.length > 0) {
		   for (var i = 0; i < $file1.files.length; i++) {
			   formData.append("fileLoader", $file1.files[i]);
		   }
		}
		
		
		
		formData.append("commonTrno","0");
		formData.append("venueHeadId",venueHeadID);
		formData.append("venueName",venueName);
		formData.append("phoneNo",phoneNo);
		formData.append("mobileNo1",mobileNo1);
		formData.append("mobileNo2",mobileNo2);
		formData.append("emailId",emailId);
		formData.append("website",website);
		formData.append("venueTypeId",venueTypeId);
		formData.append("venueStructure",venueStucture);
		formData.append("totalSeats",totalSeats);
		formData.append("adrName",adrName);
		formData.append("areaId",areaId);
		formData.append("house",house);
		formData.append("houseId",houseNoId);
		formData.append("street",street);
		formData.append("streetId",streetId);
		formData.append("landmark",landmark);
		formData.append("landmarkId",landmarkId);
		formData.append("srno",srnoStr);
		formData.append("venueLineId",venueLineIdStr);
		formData.append("rowNo",rowNoStr);
		formData.append("seatNo",noOfSeatStr);
		formData.append("seatStatus",seatStatusStr);
		formData.append("seatTypeId",seatTypeStr);
		formData.append("rowSeatStr",rowStructureStr);
		formData.append("opFlag","D");
		formData.append("strId",venueHeadID);

		$.ajax({
			'url' : "${pageContext.request.contextPath}/user/venueMasterController",
			'method' : 'post',
			'async' : false,
			data: formData,
			contentType: false,
			processData: false,
			success : function(resp) {
				alert("success");
				alert(resp.msg);
				
			},
			error : function(resp) {
				alert("error");
				alert(resp.msg);
			},
		});
	 
		clearData();
	}
		$("#updatedata").hide(); 
		$("#deleteData").hide();
		 $("#savedata").show();
	}
	

	function getVenueType() {
		showWait();
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 2001,
				param : null,
			},
			success : function(resp) {
				$("#venueTypeSelect").empty();
				$("#venueTypeSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#venueTypeSelect").append(
							'<option value="'+resp[key].VENUE_TYPE_ID+'"> '
									+ resp[key].VENUE_TYPE_NAME + '');
				});
				hideWait();
			},
		});
	}
	
	
	function getCountry() {
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 95,
				param : null,
			},
			success : function(resp) {
				$("#countrySelect").empty();
				$("#countrySelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#countrySelect").append(
							'<option value="'+resp[key].COUNTRY_ID+'"> '
									+ resp[key].COUNTRY_NAME + '');
				});
			},
		});
	}
	function getState() {
		var country = $("#countrySelect").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 96,
				param : country,
			},
			success : function(resp) {
				$("#stateSelect").empty();
				$("#stateSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#stateSelect").append(
							'<option value="'+resp[key].STATE_ID+'"> '
									+ resp[key].STATE_NAME + '');
				});
			},
		});
	}
	function getDistrict() {
		var country = $("#countrySelect").val();
		var state = $("#stateSelect").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 97,
				param : country + "~" + state,
			},
			success : function(resp) {
				$("#districtSelect").empty();
				$("#districtSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#districtSelect").append(
							'<option value="'+resp[key].DISTRICT_ID+'"> '
									+ resp[key].DISTRICT_NAME + '');
				});
			},
		});
	}
	function getCity() {
		var country = $("#countrySelect").val();
		var state = $("#stateSelect").val();
		var district = $("#districtSelect").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 98,
				param : country + "~" + state + "~" + district,
			},
			success : function(resp) {
				$("#citySelect").empty();
				$("#citySelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#citySelect").append(
							'<option value="'+resp[key].CITY_ID+'"> '
									+ resp[key].CITY_NAME + '');
				});
			},
		});
	}
	function getPincode() {
		var country = $("#countrySelect").val();
		var state = $("#stateSelect").val();
		var district = $("#districtSelect").val();
		var city = $("#citySelect").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 100,
				param : country + "~" + state + "~" + district + "~" + "null"
						+ "~" + city,
			},
			success : function(resp) {
				$("#pincodeSelect").empty();
				$("#pincodeSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#pincodeSelect").append(
							'<option value="'+resp[key].AREA_ID+'"> '
									+ resp[key].AREA_NAME + '   '
									+ resp[key].PINCODE + '');
				});
			},
		});
	}
	/* function getLandmark() {
		var areaId = $("#pincodeSelect").val();
		console.log("areaId==>>"+areaId);
		$
				.ajax({
					url : "${pageContext.request.contextPath}/getRecordLst",
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						sqlMstId : 109,
						param : 1 + "~" + areaId,
					},
					success : function(resp) {
						$("#landmarkList").empty();
						$("#landmarkList").append(
								'<option rel="" value="">--Select--');
						$
								.each(
										resp,
										function(key, value) {
											nKey = parseInt(key) + 1;
											$("#landmarkList")
													.append(
															'<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');

										});
					},
				});
	} */
	

	function getLandmark() {
		var areaId = $("#pincodeSelect").val();
		//alert("areaId--->>"+areaId);
		 var total = "1"+"~"+areaId;
		//	alert("total>>"+total);
						$("#landmarkList").empty();
						$("#landmarkList").append('<option rel="" value="">--Select--');
						var resp = getRecordList("109",total);
						$.each(resp,function(key, value) {
						 nKey = parseInt(key) + 1;
						 $("#landmarkList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
					/* 	  nomlandmarkId=resp[key].ADD_MST_ID; */
						});
					}
	
	function getStreet() {
		landmarkId = ($("#landmarkList option[value='" + $('#landmarkTf').val()
				+ "']").attr('rel'));
		console.log("landmarkId==>>"+landmarkId);
		$.ajax({
					url : "${pageContext.request.contextPath}/getRecordLst",
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						sqlMstId : 109,
						param : 2 + "~" + landmarkId,
					},
					success : function(resp) {
						$("#streetList").empty();
						$("#streetList").append(
								'<option rel="" value="">--Select--');
						$
								.each(
										resp,
										function(key, value) {
											nKey = parseInt(key) + 1;
											$("#streetList")
													.append(
															'<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');

										});
					},
				});
	}
	function getHouseNo() {
		streetId = ($("#streetList option[value='" + $('#streetTf').val()
				+ "']").attr('rel'));
		console.log("streetId==>>"+streetId);
		$
				.ajax({
					url : "${pageContext.request.contextPath}/getRecordLst",
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						sqlMstId : 109,
						param : 3 + "~" + streetId,
					},
					success : function(resp) {
						$("#houseNoList").empty();
						$("#houseNoList").append(
								'<option rel="" value="">--Select--');
						$
								.each(
										resp,
										function(key, value) {
											nKey = parseInt(key) + 1;
											$("#houseNoList")
													.append(
															'<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');

										});
					},
				});
	}

	function openfileDialog() {
		$("#fileLoader").click();
	}
	

	
	

	/* function reloadTabIndex() {
		var tb = document.getElementById("tb2");
		var ctb = tb.rows.length - 1;

		var cnt = 19;

		for (var i = 2; i < ctb; i++) {
			$(tb.rows[i].cells[1].getElementsByTagName('select')).attr(
					"tabIndex", cnt++);
			$(tb.rows[i].cells[2].getElementsByTagName('select')).attr(
					"tabIndex", cnt++);
			$(tb.rows[i].cells[3].getElementsByTagName('select')).attr(
					"tabIndex", cnt++);
			$(tb.rows[i].cells[4].getElementsByTagName('select')).attr(
					"tabIndex", cnt++);
			
		}
		reLoadValidation();
	} */
	
function clearData(){
		
		clearFields("#venueNametf,#phoneNotf,#mobileNotf,#emailIDtf,#websitetf,#venueStructureTf,#totalSeatTf,#fileLoader,#landmarkTf,#streetTf,#houseNoTf,#rowNo,#seatType,#noOfSeat,#rowStructure");
		document.getElementById("venueNametf").focus();
		
		
		var tb = document.getElementById("tb2");
		var ctb = tb.rows.length - 1;
		for (var i = ctb; i > 2; i--) {
			$("#tb2 tr:eq(" + i + ")").remove();
		}

		ctb = tb.rows.length - 1;
		
		
		$(tb.rows[ctb].cells[3].getElementsByTagName('select')).empty();
		$(tb.rows[ctb].cells[3].getElementsByTagName('select')).append(
				'<option value="">--Select--');
		$(tb.rows[ctb].cells[3].getElementsByTagName('select')).val("");
		document.getElementById('venueMap').value = '';
$(tb.rows[ctb].cells[1].getElementsByTagName('input')).val("");
$(tb.rows[ctb].cells[2].getElementsByTagName('input')).val("");
$(tb.rows[ctb].cells[4].getElementsByTagName('input')).val("");
		
	
		/*   $("#seatType").select2('val',null);  */
		  $("#venueTypeSelect").select2('val',null);
		$("#countrySelect").select2('val',null);
		$("#stateSelect").select2('val',null);
		$("#districtSelect").select2('val',null);
		$("#citySelect").select2('val',null);
		$("#pincodeSelect").select2('val',null);
	   // $('#venueNametf').focus();
		$("#updatedata").hide(); 
		$("#deleteData").hide();
		 $("#savedata").show();
	}
/* 	, ,
		, ', ,
		 '#totalSeatTf', '#countrySelect',
		'#stateSelect', '#districtSelect', '#citySelect', '#pincodeSelect', '#landmarkTf',
		'#streetTf', '#houseNoTf' */
	function saveData() {
		
		var venueStucture = $("#venueStructureTf");
		var stat = validateFormArray([ '#venueNametf','#phoneNotf','#mobileNotf','#emailIDtf',
		                               '#websitetf']);
		if(stat){
		  if (!$('#venueTypeSelect').val()) {
			alert("Please Select Venue Type.");
		 	$('#venueTypeSelect').select2('focus'); 
			  $('#venueTypeSelect').focus();
			return false;
		}else if(venueStucture.val() == "") {
	            alert("Please select Venue Stucture!");
	            $('#venueStructureTf').focus();
	            return false;
	        }else if(!$('#totalSeatTf').val()) {
				alert("Please Enter Total Seat");
				$('#totalSeatTf').focus();
				return false;
			} else if(!$('#venueMap').val()) {
				alert("Please Select Venue Map");
				$('#venueMap').focus();
				return false;
			}else if (!$('#countrySelect').val()) {
				alert("Please Select Country.");
			 	$('#countrySelect').select2('focus'); 
				  $('#countrySelect').focus();
				return false;
			}else if (!$('#stateSelect').val()) {
				alert("Please Select State.");
			 	$('#stateSelect').select2('focus'); 
				  $('#stateSelect').focus();
				return false;
			}else if (!$('#districtSelect').val()) {
				alert("Please Select District.");
			 	$('#districtSelect').select2('focus'); 
				  $('#districtSelect').focus();
				return false;
			} else if (!$('#citySelect').val()) {
				alert("Please Select City.");
			 	$('#citySelect').select2('focus'); 
				  $('#citySelect').focus();
				return false;
			} else if (!$('#pincodeSelect').val()) {
				alert("Please Select Pincode.");
			 	$('#pincodeSelect').select2('focus'); 
				  $('#pincodeSelect').focus();
				return false;
			}else if(!$('#landmarkTf').val()) {
				alert("Please Select Landmark");
				$('#landmarkTf').focus();
				return false;
			}else if(!$('#streetTf').val()) {
				alert("Please Select Street");
				$('#streetTf').focus();
				return false;
			}else if(!$('#houseNoTf').val()) {
				alert("Please Select House Number");
				$('#houseNoTf').focus();
				return false;
			}     
		}
		 /*  var stat1 = validateFormArray(['#landmarkTf','#streetTf', '#houseNoTf']); */
		  
		  
		var tb = document.getElementById("tb2");
		var ctb = tb.rows.length - 1;

		var cnt = 19;
		
		var rowNoStr = "";
		var noOfSeatStr = "";
		var seatTypeStr = "";
		var rowStructureStr = "";
		var seatStatusStr = "";
		var venueLineIdStr = "";
		var srnoStr = "";
	
		

		for (var i = 2; i <= ctb; i++) {
			var rowNo = $(tb.rows[i].cells[1].getElementsByTagName('input'))
					.val();
			
			var noOfSeat = $(tb.rows[i].cells[2].getElementsByTagName('input'))
					.val();
			
			var seatType = $(tb.rows[i].cells[3].getElementsByTagName('select'))
					.val();
	
			var rowStructure = $(tb.rows[i].cells[4].getElementsByTagName('input'))
					.val();
			var seatStatus = "Y";
			var srno = "1";
			var venueLineId = "0";

			if (rowNo != "" && noOfSeat != "" && seatType != "" && rowStructure != "") {
				rowNoStr = rowNoStr + rowNo + "~";
				noOfSeatStr = noOfSeatStr + noOfSeat + "~";
				seatTypeStr = seatTypeStr + seatType + "~";
				rowStructureStr = rowStructureStr + rowStructure + "~";
				seatStatusStr=seatStatusStr + seatStatus+ "~";
				venueLineIdStr=venueLineIdStr + venueLineId+ "~";
				srnoStr=srnoStr + srno+ "~";
				
			}
		}

		console.log("rowNoStr:::" + rowNoStr);
		if (rowNoStr.split("~").length > 1) {
			var stat = true;
		} else if(!$('#houseNoTf').val()) {
			return false;
			
		}else{
			alert("Please Enter Alteast One Row in Seat Layout!");
			var stat = false;
		}
		
		if(stat ){
			var venueStucture = $("#venueStructureTf").val();
			
		var venueName = $("#venueNametf").val();
		var phoneNo = $("#phoneNotf").val();
		var mobileNo1 = $("#mobileNotf").val();
		var mobileNo2 = $("#mobileNotf").val();

		var emailId = $("#emailIDtf").val();

		var website = $("#websitetf").val();

		var venueTypeId = $("#venueTypeSelect").val();

	/* 	var venueTypeId = $("#venueTypeSelect").val(); */

		

		/* venueStruct = ""; */
		/* if (venueStucture == "Fixed") {
			venueStruct = "F";
		} else {
			venueStruct = "C";
		} */
		var totalSeats = $("#totalSeatTf").val();

// 		var venueMap = $("fileLoader").val();
		var adrName = "";
		var areaId = $("#pincodeSelect").val();

		var house = $("#houseNoTf").val();

		var landmarkId = $("#landmarkList option[value='" + $('#landmarkTf').val() + "']").attr('rel');
	    if(landmarkId == undefined ||  landmarkId == "")
		{
	    	landmarkId="0";
		}
		
	    var streetId = $("#streetList option[value='" + $('#streetTf').val() + "']").attr('rel');
	    if(streetId == undefined ||  streetId == "")
		{
	    	streetId="0";
		}
	    
	    var houseNoId = $("#houseNoList option[value='" + $('#houseTf').val() + "']").attr('rel');
	    if(houseNoId == undefined ||  houseNoId == "")
		{
	    	houseNoId="0";
		}
	    
		var street = $("#streetTf").val();

		var landmark = $("#landmarkTf").val();

		
	
		
		
		var formData = new FormData();
		
		var $file1 = document.getElementById('fileLoader');
		
		if ($file1.files.length > 0) {
		   for (var i = 0; i < $file1.files.length; i++) {
			   formData.append("fileLoader", $file1.files[i]);
		   }
		}
		
		
		
		formData.append("commonTrno","0");
		formData.append("venueHeadId","0");
		formData.append("venueName",venueName);
		formData.append("phoneNo",phoneNo);
		formData.append("mobileNo1",mobileNo1);
		formData.append("mobileNo2",mobileNo2);
		formData.append("emailId",emailId);
		formData.append("website",website);
		formData.append("venueTypeId",venueTypeId);
		formData.append("venueStructure",venueStucture);
		formData.append("totalSeats",totalSeats);
		formData.append("adrName",adrName);
		formData.append("areaId",areaId);
		formData.append("house",house);
		formData.append("houseId",houseNoId);
		formData.append("street",street);
		formData.append("streetId",streetId);
		formData.append("landmark",landmark);
		formData.append("landmarkId",landmarkId);
		formData.append("srno",srnoStr);
		formData.append("venueLineId",venueLineIdStr);
		formData.append("rowNo",rowNoStr);
		formData.append("seatNo",noOfSeatStr);
		formData.append("seatStatus",seatStatusStr);
		formData.append("seatTypeId",seatTypeStr);
		formData.append("rowSeatStr",rowStructureStr);
		formData.append("opFlag","N");
		formData.append("strId","");
		$.ajax({
			'url' : "${pageContext.request.contextPath}/user/venueMasterController",
			'method' : 'post',
			'async' : false,
			data: formData,
			contentType: false,
			processData: false,
			success : function(resp) {
				alert("success");
				alert(resp.msg);
			},
			error : function(resp) {
				alert("error");
				alert(resp.msg);
			},
		});
	
		clearData();
	}
	}
	
	/* =========================================== */
	 /*  $(function () {
        $("#updatedata","#savedata","#deleteData").click(function () {
            var ddlFruits = $("#venueStructureTf");
            if (ddlFruits.val() == "") {
                //If the "Please Select" option is selected display error.
                alert("Please select Venue Structure!");
                return false;
            }
            return true;
        });
    });  */
	
	function updateData() {
    	if (!$('#venueTypeSelect').val()) {
			alert("Please Select Venue Type.");
			$('#venueTypeSelect').select2('focus');
			return false;
		}
		
		if (!$('#venueMap').val()) {
			alert("Please Select Venue Map");
			$('#venueMap').focus();
			return false;
		}
	
	
		var stat = validateFormArray([ '#venueNametf', '#phoneNotf',
		               				'#mobileNotf', '#emailIDtf', '#websitetf', '#venueTypeSelect','#venueMap',
		               				  '#totalSeatTf', '#fileLoader','#countrySelect',
		               				'#stateSelect', '#districtSelect', '#citySelect', '#pincodeSelect', '#landmarkTf',
		               				'#streetTf', '#houseNoTf','#venueHeadID' ]);
		var tb = document.getElementById("tb2");
		var ctb = tb.rows.length - 1;

		var cnt = 19;
		
		var rowNoStr = "";
		var noOfSeatStr = "";
		var seatTypeStr = "";
		var rowStructureStr = "";
		var seatStatusStr = "";
		var venueLineIdStr = "";
		var srnoStr = "";
	
		
		for (var i = 2; i <= ctb; i++) {
			var rowNo = $(tb.rows[i].cells[1].getElementsByTagName('input'))
					.val();
		
			var noOfSeat = $(tb.rows[i].cells[2].getElementsByTagName('input'))
					.val();
			
						var seatType	=$("#seatTypeID").val();
			
			
			var rowStructure = $(tb.rows[i].cells[4].getElementsByTagName('input'))
					.val();
		

			var seatStatus = "Y";
			var srno = "1";
			

			var venueLineId = $("#venueLineId").val();
			if (rowNo != "" && noOfSeat != "" && seatType != "" && rowStructure != "") {
				
				rowNoStr = rowNoStr + rowNo + "~";
				seatStatusStr=seatStatusStr + seatStatus+ "~";
				noOfSeatStr = noOfSeatStr + noOfSeat + "~";
				seatTypeStr = seatTypeStr + seatType + "~";
				rowStructureStr = rowStructureStr + rowStructure + "~";
				seatStatusStr=seatStatusStr + seatStatus+ "~";
				venueLineIdStr=venueLineIdStr + venueLineId+ "~";
				srnoStr=srnoStr + srno+ "~";
			
			}
			
		}

	
		if (rowNoStr.split("~").length > 1) {
			var stat = true;
		} else {
			alert("Please Enter Alteast One Row in Seat Layout!");
			var stat = false;
		}
	
	
		
		if(stat){	
		
			
			var venueTypeId = $("#venueTypeSelect").val();

			var venueStucture = $("#venueStructureTf");
			 if (venueStucture.val() == "") {
	            //If the "Please Select" option is selected display error.
	            alert("Please select Venue Stucture!");
	            return false;
	        }else{
	        	var venueStucture = $("#venueStructureTf").val();
	        }
			var venueHeadID = $("#venueHeadID").val();
		var venueName = $("#venueNametf").val();
		var phoneNo = $("#phoneNotf").val();
		var mobileNo1 = $("#mobileNotf").val();
		var mobileNo2 = $("#mobileNotf").val();

		var emailId = $("#emailIDtf").val();

		var website = $("#websitetf").val();

		var totalSeats = $("#totalSeatTf").val();

		var adrName = "";
		var areaId = $("#pincodeSelect").val();

		var house = $("#houseNoTf").val();

		var landmarkId = $("#landmarkList option[value='" + $('#landmarkTf').val() + "']").attr('rel');
	    if(landmarkId == undefined ||  landmarkId == "")
		{
	    	landmarkId="0";
		}
		
	    var streetId = $("#streetList option[value='" + $('#streetTf').val() + "']").attr('rel');
	    if(streetId == undefined ||  streetId == "")
		{
	    	streetId="0";
		}
	    
	    var houseNoId = $("#houseNoList option[value='" + $('#houseTf').val() + "']").attr('rel');
	    if(houseNoId == undefined ||  houseNoId == "")
		{
	    	houseNoId="0";
		}
	    
		var street = $("#streetTf").val();

		var landmark = $("#landmarkTf").val();

		var srno = "1";
		var venueLineId = "1";
		
		
		
		var formData = new FormData();
		
		var $file1 = document.getElementById('fileLoader');
		
		if ($file1.files.length > 0) {
		   for (var i = 0; i < $file1.files.length; i++) {
			   formData.append("fileLoader", $file1.files[i]);
		   }
		}
		
		formData.append("commonTrno","0");
		formData.append("venueHeadId",venueHeadID);
		formData.append("venueName",venueName);
		formData.append("phoneNo",phoneNo);
		formData.append("mobileNo1",mobileNo1);
		formData.append("mobileNo2",mobileNo2);
		formData.append("emailId",emailId);
		formData.append("website",website);
		formData.append("venueTypeId",venueTypeId);
		formData.append("venueStructure",venueStucture);
		formData.append("totalSeats",totalSeats);
		formData.append("adrName",adrName);
		formData.append("areaId",areaId);
		formData.append("house",house);
		formData.append("houseId",houseNoId);
		formData.append("street",street);
		formData.append("streetId",streetId);
		formData.append("landmark",landmark);
		formData.append("landmarkId",landmarkId);
		formData.append("srno",srnoStr);
		formData.append("venueLineId",venueLineIdStr);
		formData.append("rowNo",rowNoStr);
		formData.append("seatNo",noOfSeatStr);
		formData.append("seatStatus",seatStatusStr);
		formData.append("seatTypeId",seatTypeStr);
		formData.append("rowSeatStr",rowStructureStr);
		formData.append("opFlag","M");
		formData.append("strId","");

		$.ajax({
			'url' : "${pageContext.request.contextPath}/user/venueMasterController",
			'method' : 'post',
			'async' : false,
			data: formData,
			contentType: false,
			processData: false,
			success : function(resp) {
				alert("success");
				alert(resp.msg);
			},
			error : function(resp) {
				alert("error");
				alert(resp.msg);
			},
		});
		 clearData(); 
		 $("#updatedata").hide(); 
		$("#deleteData").hide();
		 $("#savedata").show(); 
	}

	 	
	}
	
	
	
	
	/* =========================================== */
	
	function closeForm(){
		
		$("#mainform").hide();
		
	}
	

	
	$(function() {
		// 			 rowNoType();
		getSeatType();
		 $('#addMore2').on('click',function() {
			
							var data = $("#tb2 tr:eq(2)").clone(true).appendTo("#tb2");
							
							data.find("input").val('');
							var tb = document.getElementById("tb2");
							var ctb = tb.rows.length - 1;

							$(tb.rows[ctb].cells[3].getElementsByTagName('select')).empty();
							$(tb.rows[ctb].cells[3].getElementsByTagName('select')).append('<option value="">--Select--');

							resp = getRecordList("2002", "null");
							$.each(resp,function(key, value) {
												nKey = parseInt(key) + 1;
												$(tb.rows[ctb].cells[3].getElementsByTagName('select')).append(
																'<option value="'+resp[key].SEAT_TYPE_ID+'"> '
																		+ resp[key].SEAT_TYPE_NAME+ '');
												}); 
		}); 

				
		$(document).on('click', '.remove', function() {
			var trIndex = $(this).closest("tr").index();
			if (trIndex > 2) {
				$(this).closest("tr").remove();
			} else {

			}
		});

	});
	
	function getSeatType() {

		$(".seatTypeSelect").empty();
		$(".seatTypeSelect").append('<option value="">--Select--');
		var resp = getRecordList("2002", "null");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$(".seatTypeSelect").append(
					'<option value="'+resp[key].SEAT_TYPE_ID+'"> '
							+ resp[key].SEAT_TYPE_NAME + '');
		});
	}

	  
	
	  
	 function checkFileUpload(element) {
			var $file = element;

			if ($file.files.length > 0) {
				for (var i = 0; i < $file.files.length; i++) {
					var size = $file.files[i].size;
					if (parseInt(size) > 5242880) {
						$("#" + element.getAttribute("id")).val(null);
						alert("File Size Greater Than 5 MB is not allowed!!\nPlease Reduce File Upload Size.");
					}
				}
			}
		}

	 function showname(id, s) {
			var name = document.getElementById(s);
			document.getElementById(id).value = name.files.item(0).name;
		}
	
	 
	
		/* function edittext() {

			$('#venueList tbody').on('click', 'tr', function() {
				var tableData = $(this).children("td").map(function() {
					return $(this).text();
				}).get();
			
			 	venueHeadId3 = $.trim(tableData[0]);
				 venueName1 = $.trim(tableData[1]);
				alert("venueName========"+venueName1);
				 totalSeat1 = $.trim(tableData[2]);
			 city = $.trim(tableData[3]); 
				 pincode1 = $.trim(tableData[4]); 
				alert("venueHeadId========"+venueHeadId3);
				
			
			}); 
			
			
			
			
			
			var resp = getRecordList("2008", venueHeadId3);
			$.each(resp, function(key, value) {
				nKey = parseInt(key) + 1;
				
			var SEAT_TYPE_ID=resp[key].SEAT_TYPE_ID;
				
				alert("SEAT_TYPE_ID"+SEAT_TYPE_ID);
				/* $("").append(
						'<option value="'+resp[key].SEAT_TYPE_ID+'"> '
								+ resp[key].SEAT_TYPE_NAME + ''); 
			});
		}
	  */
	 
		function venueList1() {
		 /*  $("#venueList123").hide();
		  $("#venueList").show(); */
		  $(".modal-body").empty();
		  $(".modal-body").append("<table id='venueList' class='table' border='1'style=' width: 100%;'><thead class='th'><tr style='background-color: #dac8b6;'><th >Venue Head Id</th><th>Venue Name</th><th>Total Seats</th><th>City</th><th>Pin code</th><th>Edit</th></tr></thead><tbody></tbody></table>");
		  $("#vnlist").empty();
			$("#vnlist").html("Venue List");
			$.ajax({
				url : "${pageContext.request.contextPath}/getRecordLst",
				type : 'post',
				dataType : 'json',
				async : false,
				data : {
					sqlMstId :2008,
					param : "null",
				},success : function(data) {
								 $("#venueList").DataTable({
										'data' : data,
										
									 'columnDefs' : [{
															'targets' : 5,
															'searchable' : false,
															'orderable' : false,
															'className' : 'dt-body-center',
															'render' : function(data, type, full,meta) {return '<img src="images/edit.png" id="editImage" style="width:20px" data-dismiss="modal" onclick="edittext();topFunction();edittextTable()">';
														}
													},],
								 
															
						   			 "columns": [
						   			          { "data": "VENUE_HEAD_ID" },
						   		          { "data": "VENUE_NAME" },
						   		          { "data": "TOTAL_SEATS"},
						   		         { "data": "TEHSIL" },
						   		         { "data": "PINCODE" },
						   		         {"data" : null} ],
						   		         
						   		      select: {
								            style: 'single'
								        },
						   		    'destroy': true,
						   		    "lengthMenu": [5, 10, 20, 50],
							        "pageLength": 5,
							        "bInfo" : false
							        
					   				});
								
								},
							error: function () {
								alert("error");
							}
					});
			 
		}
		
	 	function topFunction() {
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	
	 	
	 	
		function edittext() {
			var table = $('#venueList').DataTable();
			$('#venueList tbody').on('click', 'tr', function() {
				var tableData = $(this).children("td").map(function() {
					return $(this).text();
				}).get();
			
			 	var venueHeadId = $.trim(tableData[0]);
				var venueName = $.trim(tableData[1]);
			
			 var resp = getRecordList("2008", venueHeadId);
			 
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					
				var VENUE_NAME=resp[key].VENUE_NAME
				var MOBILE1_NO=resp[key].MOBILE1_NO
				var PHONE_NO=resp[key].PHONE_NO
				var EMAIL_ID=resp[key].EMAIL_ID
				var WEBSITE=resp[key].WEBSITE
				var TOTAL_SEATS=resp[key].TOTAL_SEATS
				var ADDRESS_ID=resp[key].ADDRESS_ID
				var AREA_ID=resp[key].AREA_ID
				var LANDMARK_ID=resp[key].LANDMARK_ID
				var LANDMARK_NAME=resp[key].LANDMARK_NAME
				var STREET_NAME=resp[key].STREET_NAME
				var PINCODE=resp[key].PINCODE
				var TEHSIL_ID=resp[key].TEHSIL_ID
				var TEHSIL=resp[key].TEHSIL
				var DISTRICT=resp[key].DISTRICT
				var DISTRICT_ID=resp[key].DISTRICT_ID
				var STATENAME=resp[key].STATENAME
				var STATE_ID=resp[key].STATE_ID	
				var COUNTRY=resp[key].COUNTRY
				var COUNTRY_ID=resp[key].COUNTRY_ID
				var HOUSE_NAME=resp[key].HOUSE_NAME	
			
				$("#venueNametf").val(VENUE_NAME);
				$("#phoneNotf").val(PHONE_NO);
				$("#mobileNotf").val(MOBILE1_NO);
				$("#emailIDtf").val(EMAIL_ID);
				$("#websitetf").val(WEBSITE);
				  
				$("#totalSeatTf").val(TOTAL_SEATS);
				$("#landmarkTf").val(LANDMARK_NAME);
				$("#streetTf").val(STREET_NAME);
				$("#houseNoTf").val(HOUSE_NAME);
				
				//getLandmark getStreet getHouseNo
				
				getCountry();
				$('#countrySelect').select2('data', {
						 allowClear : true,
					     id: COUNTRY_ID,
					     text:  COUNTRY,
					   });
				
				getState();
				$('#stateSelect').select2('data', {
					 allowClear : true,
				     id: STATE_ID,
				     text:  STATENAME,
				   });
				
				getDistrict();
				$('#districtSelect').select2('data', {
					 allowClear : true,
				     id:DISTRICT_ID,
				     text:  DISTRICT,
				   }); 
			
				getCity()
				$('#citySelect').select2('data', {
					 allowClear : true,
				     id: TEHSIL_ID,
				     text:  TEHSIL,
				   });
			
				getPincode();
				$('#pincodeSelect').select2('data', {
					 allowClear : true,
				     id: AREA_ID,
				     text:  PINCODE,
				   });
				
				});
				
				
			});

		} 
		
		
		function name5() {
			/* alert("DDDDDDDDDDDDDD"); */
				var data = $("#tb2 tr:eq(2)").clone(true).appendTo("#tb2");
		
		}
	 	
		function edittextTable() {
			var table = $('#venueList').DataTable();
			$('#venueList tbody').on('click', 'tr', function() {
				var tableData = $(this).children("td").map(function() {
					return $(this).text();
				}).get();
				
			 	 venueHeadId = $.trim(tableData[0]);
			 
				var venueName = $.trim(tableData[1]);
			
				 $("#venueHeadID").val(venueHeadId);
				
				 var resp = getRecordList("2009", venueHeadId);
				
					$.each(resp, function(key, value) {
						 
						var ROW_NO	=resp[key].ROW_NO;
				 	var VENUE_LINE_ID	=resp[key].VENUE_LINE_ID;
				 	var SEAT_NO	=resp[key].SEAT_NO;	
					var ROW_WISE_SEAT_STRUCTURE=resp[key].ROW_WISE_SEAT_STRUCTURE;
					var SEAT_TYPE_NAME =resp[key].SEAT_TYPE_NAME;		
					var SEAT_TYPE_ID =resp[key].SEAT_TYPE_ID;		
			 		 $("#seatTypeID").val(SEAT_TYPE_ID);
			 		 $("#venueLineId").val(VENUE_LINE_ID);
			 		
				 
					$("#rowNo").val(ROW_NO);
					$("#noOfSeat").val(SEAT_NO);
					$("#rowStructure").val(ROW_WISE_SEAT_STRUCTURE);
				
					getSeatType();
					$('.seatTypeSelect option:selected').text(SEAT_TYPE_ID); 
					
					getSeatType();
					$('.seatTypeSelect option:selected').text(SEAT_TYPE_NAME); 
					name5();
						
					});	
				 
					 $("#updatedata").show();   
					 $("#deleteData").show();   
				 $("#savedata").hide();    
				
			});

		} 
	 	
	 	
	 	
	 	
	
		
	 
</script>

<style type="text/css">


#tb2 {
	counter-reset: serial-number; /* Set the serial number counter to 0 */
}

#tb2 td:first-child:before {
	counter-increment: serial-number;
	/* Increment the serial number counter */
	content: counter(serial-number); /* Display the counter */
}

/*  .container-fluid {
	padding-right: 0px;
	padding-left: 0px;
	margin-right: auto;
	margin-left: auto;
}  */
</style>
</head>
<body>
<input type="hidden" id="row_index">
 <input type="hidden" id="selectedIndex">
	<input type="hidden" id=row_index>
	<input type="hidden" id=modelName>
	<input type="hidden"  id=seatTypeID>
	<input type="hidden"  id=venueHeadID>
	<input type="hidden"  id=venueLineId>
	<!-- //////venueList Popup////// -->
 

	


	<div id="mainform" class="container-fluid">
	
	
	
		<div class="panel-body" id="collapseTwo"
			style="background-color: #fff; box-shadow: 5px 5px 30px 5px #888888;"
			collapse"role="tabpanel" aria-labelledby="headingOne">

<div class="row" align="right">
				<div class=" col-sm-12 col-md-12 col-lg-12" >
				 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="venueList1()" tabIndex="23">Venue list</button> 
					
				<button type="button" id="savedata"  value="Validate"class="btn btn-primary" onclick="saveData();"
					tabIndex="24">Save</button>
				<button type="button"id="updatedata" value="Validate" class="btn btn-primary" onclick="updateData();"
					tabIndex="25">Update</button>
				<button type="button"id="deleteData" value="Validate" onclick="deleteData1()" class="btn btn-primary" 
					tabIndex="26">Delete</button>
				<button type="button" value="Validate" class="btn btn-primary" onclick="clearData()"
					tabIndex="27">Clear</button>

			
				</div>
				
			</div>

			<div class="row">

				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Venue" class="mandatory">Venue Name:</label> <input
						type="text" id="venueNametf" tabIndex="1" class="form-control"
						title="Venue Name"
						placeholder="Enter Venue Name" name="Venue Name">
				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Phone" class="mandatory">Phone No:</label> <input
						type="text" class="form-control validatePhone" id="phoneNotf"
						title="Phone No" placeholder="Enter Phone No" name="Phone"
						tabIndex="2">
				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Mobile" class="mandatory">Mobile No:</label> <input
						id="mobileNotf" type="text" class="form-control validateMobile"
						title="Mobile No" tabIndex="3" placeholder="Enter Mobile No" name="Mobile">
				</div>

			</div>


			<div class="row">

		
				
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Venue" class="mandatory">Email ID:</label> <input
						type="text" id="emailIDtf" class="form-control validateEmail"
						title="Email ID" tabIndex="4" placeholder="Enter Email ID"
						name="Email ID ">
				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Phone" class="mandatory">Website:</label> <input
						type="text" class="form-control " id="websitetf"
						title="Website" placeholder="Enter Website" name="Website"
						tabIndex="5">
				</div>
						
				<div class=" col-sm-4 col-md-4 col-lg-4">
						<label  >Venue Type:</label> <select
							id="venueTypeSelect" class="form-control " title="Venue Type"
							style="border-color: #ccc;" 
							tabIndex="6">
							<option>--Select--</option>
						</select>
						
					</div>
					
				

			</div>
			<div class="row">

				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Venue Structure" class="mandatory">Venue
						Structure:</label> <select id="venueStructureTf" tabIndex="7"
						title="Venue Structure" class="form-control">
						<option value="">--Select--</option>
						<option value="F">Fixed</option>
						<option value="C">Customizable</option>
					</select>


				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Total Seat" class="mandatory">Total Seat:</label> <input
						type="text" class="form-control " id="totalSeatTf"
						title="Total Seat" placeholder="Total Seat" name="Total Seat"
						tabIndex="8">
				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Venue Map" class="mandatory">Venue Map:</label>
					<form id="myForm">
						<input type="text" id="venueMap" title="venue Map"
							class="form-control" disabled="disabled"> <input
							type="file" id="fileLoader" style="display: none;" multiple
							onchange="showname('venueMap','fileLoader');checkFileUpload(this)" />
						<input type="button" id="btnEcucation" style="height: 30px"
							value="Browse" class="btn btn-primary"tabIndex="9"
							onclick="document.getElementById('fileLoader').click();"
							tabIndex="11" /> <input type="button" id="btnEcucationClr"
							style="height: 30px" value="Clear" class="btn btn-primary"
							onclick="document.getElementById('venueMap').value = ''">
					</form>


					<!-- <input id="fileLoader" type="file" class="form-control-file form-control btn btn-primary "style="padding: 0px;height: 34px;" title="Venue Map" tabIndex="9" id="Mobile" placeholder="Venue Map" name="Venue Map"> -->
				</div>

			</div>

		</div>
		<br>

		<div class="panel panel-default"
			style="box-shadow: 5px 5px 30px 5px #888888;">
			<div class="panel-heading" style="background-color: #dac8b6;">Address
				Details</div>
			<div class="panel-body" id="collapseTwo"
				style="background-color: #fff;" collapse"role="tabpanel"
				aria-labelledby="headingOne">


				<div class="row">

					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Country" class="mandatory">Country:</label> <select
							id="countrySelect" class="form-control " title="Country"
							type="hidden" style="border-color: #ccc;" onchange="getState()"
							tabIndex="10">
							<option>--Select--</option>
						</select>
						<datalist id=""> </datalist>
					</div>
					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="State" class="mandatory">State:</label> <select
							id="stateSelect" class="form-control " title="State"
							style="border-color: #ccc;" onchange="getDistrict()"
							tabIndex="11">
							<option>--Select--</option>
						</select>
						<datalist id=""> </datalist>
					</div>

					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="District" class="mandatory">District:</label> <select
							id="districtSelect" class="form-control" title="District"
							style="border-color: #ccc;" onchange="getCity()" tabIndex="12">
							<option>--Select--</option>
						</select>
					</div>

				</div>


				<div class="row">

					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="City" class="mandatory">City:</label> <select
							id="citySelect" class="form-control " title="City"
							style="border-color: #ccc;" onchange="getPincode()"
							tabIndex="13">
							<option>--Select--</option>
						</select>

					</div>

					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Pincode" class="mandatory">Pincode:</label> <select
							id="pincodeSelect" class="form-control " title="Pincode"
							style="border-color: #ccc;" onchange="getLandmark()"
							tabIndex="14">
							<option>--Select--</option>
						</select>
					</div>

					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Venue" class="mandatory">Landmark:</label> 
						<input id="landmarkTf" class="form-control" title="Venue Map"
						list="landmarkList"	placeholder="Landmark" onchange="getStreet()" tabIndex="15">
						<datalist id="landmarkList"> </datalist>

					</div>

				</div>

				<div class="row">

					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Street" class="mandatory">Street:</label> <input
							id="streetTf" type="text" class="form-control" title="Venue Map"
							placeholder="Enter Street" name="Email ID " list="streetList"	
							onchange="getHouseNo()" tabIndex="16">
						<datalist id="streetList"></datalist>


					</div>
					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Phone" class="mandatory">House:</label> <input
							id="houseNoTf" list="houseNoList" type="text" tabIndex="17" list="houseNoList"	
							class="form-control" title="House" placeholder="Enter House">
						<datalist id="houseNoList"></datalist>

					</div>


				</div>



			</div>

		</div>








		<div class="row">

			<div class=" col-sm-12 col-md-12 col-lg-12">
				<div class="panel panel-primary">

					<div class="bs-example">
						<div class="Container" style="overflow-x: scroll;">
							<table class="table table-hover small-text " id="tb2">
								<tr class="tr-header">
									<th><p>Sr No</p></th>
									<th><p style="margin-left: -95px;">Row No</p></th>
									<th><p style="margin-left: -95px;">No Of Seat</p></th>
									<th><p style="margin-left: -95px;">Seat Type</p></th>
									<th><p style="margin-left: -95px;">Row Structure</p></th>
									<th><a href="javascript:void(0);"
										style="font-size: 18px; margin-left: -25px;" id="addMore2"
										title="Add More Record"><span
											class="glyphicon glyphicon-plus"></span></a></th>
								</tr>

								<tr height="5%" class="tr-header">

								</tr>
								<tr id="1A" style="color: #333;">
									<td>&nbsp;</td>

									<td><input type="text"title="Row No" id="rowNo" class="rowNo1" tabIndex="19"></td>
									<td><input type="text"title="No of seats" class="noOfSeat1" id="noOfSeat" tabIndex="20"></td>
									<!-- <td><select class="seatType" id="seatTypeSelect"
										tabIndex="21" style="width: 145px;">
											<option>--Select--</option>
									</select></td> -->
									<td><select class="seatTypeSelect" id="seatType"
										title="Seat Type" style="width: 145px; height: 26px;"
											onchange="" tabIndex="21">
											<option>--Select--</option>
									</select></td>
									<td><input type="text" class="rowStructure1" id="rowStructure" title="Row Structure"tabIndex="22"></td>
									<td><a href='javascript:void(0);' class='remove'><span
											class='glyphicon glyphicon-remove'></span></a></td>

								</tr>
							</table>
						</div>
					</div>
				</div>

			</div>
		</div>

		

	</div>
</body>
</html>
