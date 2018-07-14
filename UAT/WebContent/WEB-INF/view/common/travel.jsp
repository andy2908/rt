<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Travel insurance</title>
<link rel="stylesheet" href="css/datepicker3.css">
<script src="js/bootstrap-datepicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		
		$('.datePicker').datepicker({
			autoclose : true,
			format : 'dd/mm/yyyy'
		});
		
		 $("#claim,#initialsSelect,#sumInsured,#relation,#pincodeSelect,#proposerCity,"+
					"#proposerState,#districtSelect,#countrySelect,#nationalitySelect,"+
					" #sumInsured,#noOfMembers,#occupationSelect,#citySelect,#stateSelect,"+
					 "#nomDtlInitial,#nomDtlRelation,#nomDtlCountry,#nomDtlState,#nomDtlDistrict,"+
					"#nomDtlCity, #nomDtlPincode").select2({
					placeholder : "--Select--",
					allowClear : true,
					maximumSelectionSize : 1,
				});
		
		
		
		
		$(function() {

			var active = true;

			$('#collapse-init').click(function() {
				if (active) {
					active = false;
					$('.run').collapse('show');
					$('.run1').attr('data-toggle', '');

				} else {
					active = true;
					$('.run').collapse('hide');
					$('.run1').attr('data-toggle', 'collapse');

				}
			});

			$('#collapse-init1').click(function() {
				if (active) {
					active = false;
					$('.runs').collapse('show');
					$('.runs1').attr('data-toggle', '');

				} else {
					active = true;
					$('.runs').collapse('hide');
					$('.runs1').attr('data-toggle', 'collapse');

				}
			});

			$('#collapse-init2').click(function() {
				if (active) {
					active = false;
					$('.ran').collapse('show');
					$('.ran1').attr('data-toggle', '');

				} else {
					active = true;
					$('.ran').collapse('hide');
					$('.ran1').attr('data-toggle', 'collapse');

				}
			});

			$('#accordion').on('show.bs.collapse', function() {
				if (active)
					$('#accordion .in').collapse('hide');
			});

		});
		$("#nationalitySelect").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});

		$("#initialsSelect").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#noOfMembers").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#PurposeOfVisit").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});

		$("#countrySelect").select2({
			placeholder : "Select_Country",
			allowClear : true,
			maximumSelectionSize : 4,
		});

		$("select.country").change(function() {
			var selectedCountry = $(".country option:selected").val();
			alert("You have selected the country - " + selectedCountry);
		});
		populateNationality();
		populateCountry();
		populateNoOfMembers();
		populateVisit();
		getInitial();
		AddRow();
		calculate();
		nomDtlInitial();
		

	});
	function getInitial() {
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '4',
			},
			success : function(resp) {
				$("#initialsSelect").empty();
				$("#initialsSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#initialsSelect").append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
					$("#nomInitialsSelect").append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
	}

	function populateCountry() {
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
	function populateNoOfMembers() {
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : 133,
			},
			success : function(resp) {
				$("#noOfMembers").empty();
				$("#noOfMembers").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#noOfMembers").append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
	}
	function populateVisit() {
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
				$("#PurposeOfVisit ").empty();
				$("#PurposeOfVisit").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#PurposeOfVisit").append(
							'<option value="'+resp[key].COUNTRY_ID+'"> '
									+ resp[key].COUNTRY_NAME + '');
				});
			},
		});

	}
	function getDates() {
		var fromdate = $("#fromDate").val();

		var date = fromdate.split("/")[0];
		var month = fromdate.split("/")[1];
		var year = fromdate.split("/")[2];

		var startDate = month + "/" + date + "/" + year;

		var toDate = $("#toDate").val();

		var date1 = toDate.split("/")[0];
		var month1 = toDate.split("/")[1];
		var year1 = toDate.split("/")[2];

		var endDate = month1 + "/" + date1 + "/" + year1;

		var d1 = new Date(startDate);
		var d2 = new Date(endDate);

		/* var oneDay = 24 * 60 * 60 * 1000; */
		var millisecondsPerDay = 1000 * 60 * 60 * 24;
		var millisBetween = d2.getTime() - d1.getTime();
		var days = millisBetween / millisecondsPerDay;

		// Round down.

		$('.calculated').val(Math.floor(days));

	}

	function calculate() {
		//	alert("Calculate");
		var d1 = $('.fromDate').datepicker('getDate');
		var d2 = $('.toDate').datepicker('getDate');

	}

	function populateNationality() {
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '26',
			},
			success : function(resp) {
				$("#nationalitySelect").empty();
				$("#nationalitySelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#nationalitySelect").append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
	}

	function AddRow() {
		row = '<tr id="tableRow'+k+'">'
				+ '<td>'
				+ k
				+ '</td>'
				+ '<td><input type="text" id="firstname'+k+'"required="required"></td>'
				+ '<td><input type="text" id="middleName'+k+'" required="required"></td>'
				+ '<td><input type="text" id="lastName'+k+'"required="required">'
				+ '</td><td><select id="relation'+k+'">'
				+ '<option>--Select--</option>'
				+ '</td><td><select id="gender'+k+'">'
				+ '<option>--Select--</option></td>'
				+ '<td ><input class="datePicker" type="text"id="insuredDob'+k+'"></td>'
				+

				'<td><select id="Occupation'+k+'">'
				+ '<option>--Select--</option></td>'
				+ '<td><input type="text" id="grossAnnualIncome'
				+ k
				+ '" required="required" onkeypress="return isNumber(event)"></td>'
				+ $('#insuredDetailsTable').append(row);
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 239,
				param : k,
			},
			success : function(resp) {
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$('#relation' + k + '').append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '27',
			},
			success : function(resp) {
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$('#gender' + k + '').append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '1',
			},
			success : function(resp) {
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$('#Occupation' + k + '').append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
		$("#relation" + k + "").select2({
			placeholder : "--Select--",
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#gender" + k + "").select2({
			placeholder : "--Select--",
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#Occupation" + k + "").select2({
			placeholder : "--Select--",
			allowClear : true,
			maximumSelectionSize : 1,
		});
		$("#currentSickness" + k + "").select2({
			placeholder : "--Select--",
			allowClear : true,
			maximumSelectionSize : 1,
		});

		k++;
	/*	$('.datepicker').datepicker({
			autoclose : true,
			format : 'dd/mm/yyyy'
		}); */
	}
	function getNoOfRows() {
		var members = $("#noOfMembers :selected").text();
		/* $("#addInsuredRow").click(function(){ */
		for (var j = 2; j <= members; ++j) {
			AddRow();
		}
		members = 0;
		/* }); */
	}
	
	function showSpouseData() {
		if ($("#noOfMembers :selected").text() > 1) {
			$("#spouseIncluded").show();
		} else {
			$("#spouseIncluded").hide();
			$("#spouseWorking").hide();
		}
	}
	function getProposerData() {
		if (validateDatePicker()) {
			
			var firstname = $('#firstNameTextfiels').val();
			var lastName = $('#lastnameTextfiels').val();
			var middleName = $('#middleNameTextfiels').val();
			//var occupation=	$("#occupationSelect").val();
			var dob = $('#dob1').val();
			//$('#insuredDob1').val(dob);
			$('#firstname1').val(firstname);
			$('#middleName1').val(middleName);
			$('#lastName1').val(lastName);
			$("#insuredDetails").show();
			//populateOccupation();
		   	//$("#Occupation1").select2("val",occupation);
		}
	}
	
	
	function validateDatePicker() {
		
		if (!$('#initialsSelect').val()) {
			alert("Please Select Initial!!");
			$('#initialsSelect').select2('focus');
			return false;
		}
		if (!$('#firstNameTextfiels').val()) {
			alert("Please Enter First Name!!");
			$('#firstNameTextfiels').focus();
			return false;
		}
		if (!$('#middleNameTextfiels').val()) {
			alert("Please Enter Middle Name!!");
			$('#middleNameTextfiels').focus();
			return false;
		}
		if (!$('#lastnameTextfiels').val()) {
			alert("Please Enter Last Name!!");
			$('#lastnameTextfiels').focus();
			return false;
		}
		if (!$('#dob1').val()) {
			alert("Please Select Date Of Birth!!");
			$('#dob1').focus();
			return false;
		}
		if (!$("input[name='propGender']:checked").val()) {
			alert("Please Select Gender!!");
			return false;
		}
		
		if (!$('#adharTextfiels').val()) {
			alert("Please Enter Aadhar No!!");
			$('#adharTextfiels').focus();
			return false;
		}
		var adharTextfiels=document.getElementById("adharTextfiels")
		 if (adharTextfiels.value.length < 12 || adharTextfiels.value.length > 12) {
	            alert("Aadhar No. is not valid, Please Enter 12 Digit Aadhar No.");
	            $('#adharTextfiels').val("");
	            $('#adharTextfiels').focus();
	            return false;
	       }
		if (!$('#EmailTextfiels').val()) {
			alert("Please Enter Email!!");
			$('#EmailTextfiels').focus();
			return false;
		}
		 var email = document.getElementById('EmailTextfiels');
		    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		    if (!filter.test(email.value)) {
		    alert('Please provide a valid email address');
		    $('#EmailTextfiels').focus();
		    $('#EmailTextfiels').val("");
		    return false;
		 }
		if (!$('#mbTextfiels').val()) {
			alert("Please Enter Mobile No!!");
			$('#mbTextfiels').focus();
			return false;
		}
		var mbTextfiels=document.getElementById("mbTextfiels")
		var mbFilter=/^[7-9][0-9]{9}$/;
		 if (mbTextfiels.value.length < 10 || mbTextfiels.value.length > 10) {
	            alert("Mobile No. is not valid, Please Enter 10 Digit Mobile No.");
	            $('#mbTextfiels').val("");
	            $('#mbTextfiels').focus();
	            return false;
	       }
		 if (!mbFilter.test(mbTextfiels.value)) {
			 alert('Please provide a valid Mobile no');
			    $('#mbTextfiels').focus();
			    $('#mbTextfiels').val("");
			    return false;
		 }
		
		var phoneNo=document.getElementById("phTextfiels")
		 if (phoneNo.value.length < 11 || phoneNo.value.length > 11) {
	            alert("Phone No. is not valid, Please Enter 11 Digit Phone No.");
	            $('#phTextfiels').val("");
	            $('#phTextfiels').focus();
	            return false;
	       }
	/*	if (!$('#nationalitySelect').val()) {
			alert("Please Select Nationality!!");
			$('#nationalitySelect').select2('focus');
			return false;
		}

		if (!$('#countrySelect').val()) {
			alert("Please Select Country!!");
			$('#countrySelect').select2('focus');
			return false;
		}

		if (!$('#proposerState').val()) {
			alert("Please Select State!!");
			$('#proposerState').select2('focus');
			return false;
		}
		if (!$('#districtSelect').val()) {
			alert("Please Select District!!");
			$('#districtSelect').select2('focus');
			return false;
		}
		if (!$('#proposerCity').val()) {
			alert("Please Select City!!");
			$('#proposerCity').select2('focus');
			return false;
		}
		if (!$('#pincodeSelect').val()) {
			alert("Please Select Pincode!!");
			$('#pincodeSelect').select2('focus');
			return false;
		}
		if (!$('#landMark').val()) {
			alert("Please Enter Or Select LandMark!!");
			$('#landMark').focus();
			return false;
		}
		if (!$('#street').val()) {
			alert("Please Enter Or Select Street!!");
			$('#street').focus();
			return false;
		}

		if (!$('#House').val()) {
			alert("Please Enter Or Select House!!");
			$('#House').focus();
			return false;
		}*/

		$('#dob1').empty();
		var dob = $('#dob1').val();
		var date = dob.split('/')[2];
		var d = new Date();
		var lipday = (d.getFullYear() - date);
		if (lipday < 18.000 || (!lipday == 18.000)) {
			alert('Your age should be 18 or greater than 18 yrs!!!');
			$('#dob1').val();
			$('#dob1').focus();
			
			return false;
		}
		
		return true;
	}
	
	function validateSelf() {
		var members=$("#noOfMembers :selected").text();
		for(var j=1;j<k;j++)
	
			$('#insuredDob1').empty();
		var insuredDob1 = $('#insuredDob1').val();
		var date = insuredDob1.split('/')[2];
		var d = new Date();
		var lipday = (d.getFullYear() - date);
		if (lipday < 18.000 || (!lipday == 18.000)) {
			alert('Your age should be 18 or greater than 18 years!!!');
			$('#insuredDob1').val();
			return false;
		}
	}
	
	function validateInsuredDetails(){
		if (validateSelf()) {
			if ($("#noOfMembers :selected").text() == 2
					|| $("#noOfMembers :selected").text() == 3
					|| $("#noOfMembers :selected").text() == 4) {
				$("#row2").show();

			} else {
				$("#row2").hide();
			}
		}
	}
	
	function clearProposerData(){
		document.getElementById("firstNameTextfiels").value="";
		document.getElementById("middleNameTextfiels").value="";
		document.getElementById("lastnameTextfiels").value="";
		document.getElementById("dob1").value="";
		 $('input[name=propGender]').prop('checked', false);
		 document.getElementById("adharTextfiels").value="";
		 document.getElementById("EmailTextfiels").value="";
		 document.getElementById("mbTextfiels").value="";
		 document.getElementById("phTextfiels").value="";
		 $("#initialsSelect").select2('val',null);
		 $("#nationalitySelect").select2('val',null);
		 $("#countrySelect").select2('val',null);
		 $("#proposerState").select2('val','--Select--');
		 $("#districtSelect").select2('val','--Select--');
		 $("#proposerCity").select2('val','--Select--');
		 $("#pincodeSelect").select2('val','--Select--');
		/* $("#landMark").val("");
		 $("#landMarkList").val("");
		 $("#street").val("");
		 $("#streetList").val("");
		 $("#House").val("");
		 $("#houseList").val("");*/
	}
	
	
	
	
	
	function nomineeDetailsValidation(){
		if(nomineeValidation()){
			 $("#previousPolicyDetails").show(); 
			 $("#showCompanies").show();
		}
	}
	function nomineeValidation(){
		if (!$('#nomDtlInitial').val()) {
			alert("Please Select Initial!!");
			$('#nomDtlInitial').focus();
			return false;
		}
		if (!$('#nomDtlFname').val()) {
			alert("Please Enter First Name!!");
			$('#nomDtlFname').focus();
			return false;
		}
		if (!$('#nomDtlMname').val()) {
			alert("Please Enter Middle Name!!");
			$('#nomDtlMname').focus();
			return false;
		}
		if (!$('#nomDtlLname').val()) {
			alert("Please Enter Last Name!!");
			$('#nomDtlLname').focus();
			return false;
		}
		if (!$('#nomDtlRelation').val()) {
			alert("Please Select Relation!!");
			$('#nomDtlRelation').focus();
			return false;
		}
		if (!$('#nomDtlAdharNo').val()) {
			alert("Please Enter Adhar No!!");
			$('#nomDtlAdharNo').focus();
			return false;
		}
		var adharTextfiels=document.getElementById("nomDtlAdharNo")
		 if (adharTextfiels.value.length < 12 || adharTextfiels.value.length > 12) {
	            alert("Aadhar No. is not valid, Please Enter 12 Digit Aadhar No.");
	            $('#nomDtlAdharNo').val("");
	            $('#nomDtlAdharNo').focus();
	            return false;
	       }
		if (!$('#nomDtlEmail').val()) {
			alert("Please Enter Email!!");
			$('#nomDtlEmail').focus();
			return false;
		}
		 var email = document.getElementById('nomDtlEmail');
		    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		    if (!filter.test(email.value)) {
		    alert('Please provide a valid email address');
		    $('#nomDtlEmail').focus();
		    $('#nomDtlEmail').val("");
		    return false;
		 }
		if (!$('#nomDtlMobileNo').val()) {
			alert("Please Enter Mobile No!!");
			$('#nomDtlMobileNo').focus();
			return false;
		}
		var mbTextfiels=document.getElementById("nomDtlMobileNo")
		var mbFilter=/^[7-9][0-9]{9}$/;
		 if (mbTextfiels.value.length < 10 || mbTextfiels.value.length > 10) {
	            alert("Mobile No. is not valid, Please Enter 10 Digit Mobile No.");
	            $('#nomDtlMobileNo').val("");
	            $('#nomDtlMobileNo').focus();
	            return false;
	       }
		 if (!mbFilter.test(nomDtlMobileNo.value)) {
			 alert('Please provide a valid Mobile no');
			    $('#mbTextfiels').focus();
			    $('#mbTextfiels').val("");
			    return false;
		 }
		
		var phoneNo=document.getElementById("nomDtlphoneNo")
		 if (phoneNo.value.length < 11 || phoneNo.value.length > 11) {
	            alert("Phone No. is not valid, Please Enter 11 Digit Phone No.");
	            $('#nomDtlphoneNo').val("");
	            $('#nomDtlphoneNo').focus();
	            return false;
	       }
		
		return true;
	}
	
	function saveDetails(){
		var nomId = "";
	    var nomInitialId = $('#nomDtlInitial').val();
	    if(nomInitialId=="--Select--"){
	    	nomInitialId="0";
	    }else{
	    	nomInitialId = $('#nomDtlInitial').val();
	    }
	    var nomFirstName =$('#nomDtlFname').val();
	    var nomMiddleName = $('#nomDtlMname').val();
	    var nomLastName = $('#nomDtlLname').val();
	    var nomDob = $('#nomDtlDOB').val();
	    var nomAadharNo = $('#nomDtlAdharNo').val();
		
		
	}
	function isNumber(evt) {
	     evt = (evt) ? evt : window.event;
	     var charCode = (evt.which) ? evt.which : evt.keyCode;
	     if (charCode < 48 || charCode > 57) {
	        
	         return false;
	     }
	     return true;
	 }
	
	
</script>
</head>
<body>
	<div class="container-fluid" style="margin-top: 110px;">
		<div class="row" align="right">
			<button type="button" class="btn btn-primary btn-lg"
				onclick="closeForm();">&nbsp;Home Page</button>
		</div>
		<br> <br>
		<div class="row" style="background-color: #4cbcce;"
			style="padding: 10px">
			<div style="background-color: #d1bebe;""">&nbsp;&nbsp;Travel
				Details</div>


			<!--   <div class=row style="margin-top: 20px;">
			<div class="col-md-3">
				<div class="col-md-6">
					<label for="TravelStartDate"><font color="#ffffff"
						style="margin-left: -9px">* Travel Start Date</font></label>
				</div>
				<div class="col-md-5">
					<input class="datepicker" id="fromDate" />
				</div>
			</div>

			<div class="col-md-3">
				<div class="col-md-6">
					<label for="TravelEndDate"><font color="#ffffff">*
							Travel End Date</font></label>
				</div>
				<div class="col-md-5">
					<input class="datepicker" id="fromDate" />
				</div>
			</div>

			<div class="col-md-3">
				<div class="col-md-6">
					<label for="NoOfDays"><font color="#ffffff">No Of
					Days </font></label>
				</div>
				<div class="col-md-5">
					<input class='calculated'/>
				</div>
			</div>

			<div class="col-md-3">
				<div class="col-md-5">
					<label for="Destination"><font color="#ffffff">Destination
							 </font></label>

				</div>
				<div class="col-md-5">
					<select multiple id="countrySelect" style="width: 175px;">
						<option>    </option>
					</select>
				</div>
			</div>
            </div>-->

			<div class=row style="margin-top: 20px;">

				<!--  <div class=" col-md-3 ">
					<label for="TravelStartDate"><font color="#ffffff">Travel
							Start Date</font> </label> <input class="datepicker" id="fromDate" />
				</div>-->
				<div class="col-md-3">
					<div class="col-md-5">
						<label for="TravelStartDate"><font color="#ffffff"
							style="margin-left: -9px;">Travel Start Date</font></label>
					</div>
					<div class="col-md-5">
						<input class="datePicker" id="fromDate" type="text">
					</div>
				</div>


				<!--  <div class=" col-md-3">
					<label for="TravelEndDate"><font color="#ffffff">Travel
							End Date &nbsp;&nbsp;&nbsp; </font></label> <input class="datepicker"
						id="toDate" onchange="getDates()" />
				</div>-->

				<div class="col-md-3">
					<div class="col-md-5">
						<label for="TravelEndDate"><font color="#ffffff"
							style="margin-left: -9px;">Travel End Date</font></label>
					</div>
					<div class="col-md-5">
						<input class="datePicker" id="toDate" type="text"
							onchange="getDates()">
					</div>
				</div>


				<!--  <div class=" col-md-3  ">
					<label for="NoOfDays"><font color="#ffffff">No Of
							Days </font></label><input class='calculated'>
				</div> -->

				<div class="col-md-3">
					<div class="col-md-5">
						<label for="NoOfDays"><font color="#ffffff"
							style="margin-left: -9px;">No Of Days</font></label>
					</div>
					<div class="col-md-5">
						<input class="calculated" type="text">
					</div>
				</div>




				<!--  <div class=" col-md-3 ">
					<label for="NoOfMember"><font color="#ffffff">No Of
							Member </font></label><select style="width: 100px" id="noOfMembers" onclick="showSpouseData()"
						onchange="getNoOfRows()">
						<option>--Select--</option>
					</select>
				</div>-->

				<div class="col-md-3">
					<div class="col-md-6">
						<label for="NoOfMember"><font color="#ffffff"
							style="margin-left: -9px">No Of Member</font></label>
					</div>
					<div class="col-md-5">
						<select id="noOfMembers" onclick="showSpouseData()"
							onchange="getNoOfRows()" style="margin-left: -10px; width: 145px">
							<option>--Select--</option>
						</select>
					</div>
				</div>

			</div>

			<!--  <div class=" col-md-3  ">
					<label for="types of travel insurance"><font
						color="#ffffff">Purpose Of Visit &nbsp;&nbsp; </font></label><select
						style="width: 100px" id="PurposeOfVisit">
						<option>--Select--</option>
					</select>
				</div> -->

			<br> <br> <br>
			<div class="row" style="padding: 10px">
				<div class="col-md-3">
					<div class="col-md-5">
						<label for="typesoftravelinsurance"><font color="#ffffff"
							style="margin-left: -12px">Purpose Of Visit</font></label>
					</div>
					<div class="col-md-5">
						<select id="PurposeOfVisit"
							style="margin-left: -5px; width: 145px">
							<option>--Select--</option>
						</select>
					</div>
				</div>

				<!--  	<div class="col-md-3 ">
					<label for="VisaType"><font color="#ffffff">Visa's
							Type &nbsp;&nbsp;</font></label> <select>
						<option>--Select visa--</option>
						<option>B-1/B-2 Tourist/Visitor Visas</option>
						<option>E-1/E-2 Treaty and Investor Visas</option>
						<option>F-1 and M-1 Student Visas</option>
						<option>H-1B</option>
						<option>J-1 and Q-1 Exchange Visitor Visas</option>
						<option>K-1 Fiance(e) Visas</option>
						<option>L-1 Intracompany Transfer Visas</option>
						<option>O-1 Extraordinary Ability Worker Visas</option>
						<option>P-1 Artists and Athletes Visas</option>
						<option>R-1 Religious Worker Visas</option>
						<option>Family Sponsored Immigration Visas</option>
						<option>EB-1</option>
						<option>EB-2</option>
						<option>EB-3</option>
						<option>EB-4</option>
						<option>EB-5</option>
						<option>DV-1 Visas</option>
					</select>
				</div>-->

				<div class="col-md-3">
					<div class="col-md-5">
						<label for="VisaType"><font color="#ffffff"
							style="margin-left: -9px">Visa's Type</font></label>
					</div>
					<div class="col-md-5">
						<select style="margin-left: -1px; width: 145px">
							<option>--Select--</option>
							<option>B-1/B-2 Tourist/Visitor Visas</option>
							<option>E-1/E-2 Treaty and Investor Visas</option>
							<option>F-1 and M-1 Student Visas</option>
							<option>H-1B</option>
							<option>J-1 and Q-1 Exchange Visitor Visas</option>
							<option>K-1 Fiance(e) Visas</option>
							<option>L-1 Intracompany Transfer Visas</option>
							<option>O-1 Extraordinary Ability Worker Visas</option>
							<option>P-1 Artists and Athletes Visas</option>
							<option>R-1 Religious Worker Visas</option>
							<option>Family Sponsored Immigration Visas</option>
							<option>EB-1</option>
							<option>EB-2</option>
							<option>EB-3</option>
							<option>EB-4</option>
							<option>EB-5</option>
							<option>DV-1 Visas</option>
						</select>
					</div>
				</div>

				<!--  <div class=" col-md-3 ">
					<label for="Destination"><font color="#ffffff">Destination
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font></label><select multiple
						id="countrySelect" style="width: 200px;">
						<option>Select_Country</option>
					</select>
				</div>-->


				<div class="col-md-3">
					<div class="col-md-5">
						<label for="Destination"><font color="#ffffff"
							style="margin-left: -9px;">Destination
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></label>
					</div>
					<div class="col-md-4">
						<select multiple id="countrySelect"
							style="margin-left: -1px; width: 145px">
							<option>Select_Country</option>
						</select>
					</div>
				</div>

				<!--  <div class="col-xs-3 col-md-3 col-sm-3 col-xl-3">
					<label for="TypesOfTravelInsurance"><font color="#ffffff">Types
							Of Travel Insurance </font></label> <select>
						<option>---Select---</option>
						<option id="collapse-init">Self Travel Insurance</option>
						<option id="collapse-init1">Group / Friends Travel
							Insurance</option>
						<option id="collapse-init2">Students Travek Insurance</option>
					</select>
				</div>-->



				<div class="col-md-3">
					<div class="col-md-6">
						<label for="TypesOfTravelInsurance"><font color="#ffffff"
							style="margin-left: -9px">Types Of Travel Insurance</font></label>
					</div>
					<div class="col-md-5">
						<select style="margin-left: -1px; width: 145px">
							<option>--Select--</option>
							<option id="collapse-init">Praposal Details</option>
							<option id="collapse-init1">Group / Friends Travel
								Insurance</option>
							<option id="collapse-init2">Students Travek Insurance</option>
						</select>
					</div>
				</div>



			</div>














			<!--  <div class="col-xs-6 col-md-6 col-sm-6 col-xl-6">
					<button type="button" id="collapse-init" class="btn btn-default">
						Familly / Self Travel Insurance</button>
				</div>-->


			<br>


		</div>



		<!--  <div class="col-xs-6 col-md-6 col-sm-6 col-xl-6">
					<button type="button" id="collapse-init" class="btn btn-default">
						Familly / Self Travel Insurance</button>
				</div>-->






		<div class="row" style="background-color: #4cbcce;">
			<div class="panel-title run1 " style="background-color: #d1bebe;">
				&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
					data-parent="#accordion" href="#collapseFamillySelfTravelInsurance"
					aria-expanded="true" aria- controls="collapseOne">Praposal
					Details </a>
			</div>
			<div id="collapseFamillySelfTravelInsurance"
				class="panel-collapse collapse run" role="tabpanel"
				aria-labelledby="headingOne" class="main wrapper2"
				style="margin: auto;">
				<br>
				<div class="col-md-3">
					<div class="col-md-6">
						<label for="initial"><font color="#ffffff"
							style="margin-left: -9px">* Title</font></label>
					</div>
					<div class="col-md-5">
						<select id="initialsSelect"
							style="margin-left: -10px; width: 145px">
							<option>--Select--</option>
						</select>
					</div>
				</div>
				<div class="col-md-3">
					<div class="col-md-5">
						<label for="firstName"><font color="#ffffff"
							style="margin-left: -7px;">*First Name</font></label>
					</div>
					<div class="col-md-5">
						<input id="firstNameTextfiels" type="text">
					</div>
				</div>
				<div class="col-md-3">
					<div class="col-md-6">
						<label for="firstName"><font color="#ffffff">*Middle
								Name</font></label>
					</div>
					<div class="col-md-4">
						<input id="middleNameTextfiels" type="text" style="width: 160px">
					</div>
				</div>
				<div class="col-md-3">
					<div class="col-md-5">
						<label for="firstName"><font color="#ffffff">*Last
								Name</font></label>
					</div>
					<div class="col-md-5">
						<input id="lastnameTextfiels" type="text" style="width: 145px">
					</div>
				</div>
				<br> <br> <br>

				<div class="row" style="padding: 5px">
					<div class="col-md-3">
						<div class="col-md-6">
							<label for="dob"><font color="#ffffff">* Date Of
									Birth</font></label>
						</div>
						<div class="col-md-4">
							<input id="dob1" type="text" class="datePicker"
								style="width: 145px">
						</div>
					</div>
					<div class="col-md-3">
						<div class="col-md-3">
							<label for="Gender"><font color="#ffffff">Gender</font></label>
						</div>
						<div class="col-md-9">
							<input type="radio" name="propGender"
								style="margin-right:; width: 12px" value="male"><font
								color="#ffffff">Male</font></label> <label><input type="radio"
								name="propGender" value="female"><font color="#ffffff">Female</font></label>
							<label><input type="radio" name="propGender"
								value="other"><font color="#ffffff">Other</font></label>
						</div>
					</div>
					<div class="col-md-3">
						<div class="col-md-6">
							<label for="adhar"><font color="#ffffff">Aadhar No</font></label>
						</div>
						<div class="col-md-4">
							<input id="adharTextfiels" type="text" style="width: 160px"
								onkeypress="return isNumber(event)" maxlength="12">
						</div>
					</div>
					<div class="col-md-3">
						<div class="col-md-5">
							<label for="firstName"><font color="#ffffff">Email</font></label>
						</div>
						<div class="col-md-5">
							<input id="EmailTextfiels" type="email" style="width: 145px">
						</div>
					</div>
				</div>
				<br> <br>
				<div class="row" style="padding: 5px">
					<div class="col-md-3">
						<div class="col-md-6">
							<label for="mb"><font color="#ffffff">* Mobile</font></label>
						</div>
						<div class="col-md-5">
							<input id="mbTextfiels" type="text"
								onkeypress="return isNumber(event)" maxlength="10"
								style="width: 145px">
						</div>
					</div>
					<div class="col-md-3">
						<div class="col-md-5">
							<label for="ph"><font color="#ffffff"> Phone No</font></label>
						</div>
						<div class="col-md-5">
							<input id="phTextfiels" type="text"
								onkeypress="return isNumber(event)" maxlength="11">
						</div>
					</div>
					<div class="col-md-3">
						<div class="col-md-6">
							<label for="Nationality"><font color="#ffffff">*Nationality</font></label>
						</div>
						<div class="col-md-4">
							<select id="nationalitySelect" onchange="populateCountry()"
								style="width: 160px">
								<option>--Select--</option>
							</select>
						</div>
					</div>



				</div>
				<div class="row" style="padding: 5px"></div>
				<div class="row" style="padding: 5px">
					<div class="col-md-10"></div>
					<div class="col-md-2">
						<button type="button" class="btn btn-primary"
							onclick="getProposerData()">Next</button>
						<button type="button" class="btn btn-primary"
							onclick="clearProposerData()">Clear</button>
					</div>
				</div>


			</div>

		</div>


		<div class="row" style="background-color: #4cbcce; height: 20px;"></div>





		<!-- <div style="background-color: #d1bebe;">&nbsp;&nbsp; Self
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">

						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<div class="form-check">
							Gender : <input class="form-check-input" name="group100"
								type="radio" id="radio100"> <label
								class="form-check-label" for="radio100">Male</label> <input
								class="form-check-input" name="group100" type="radio"
								id="radio101" checked> <label class="form-check-label"
								for="radio101">Female</label>
						</div>
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Passport_No">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Nominee_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Nominee_Relationship">
					</div>
				</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-6 col-sm-6 col-xl-6">
						Are you presentlly in India :<input class="form-chek-input"
							name="abc" type="radio" id="radio101"><label
							class="form-check-label" for="radio101">yes</label> <input
							class="form-check-input" name="bcd" type="radio" id="radio102"
							checked><label class="form-check-label" for="radio102">no</label>
					</div>
					<div class="col-xs-12 col-md-6 col-sm-6 col-xl-6">
						Holds valid Indian Passport :<input class="form-chek-input"
							name="cdf" type="radio" id="radio103"><label
							class="form-check-label" for="radio103">yes</label> <input
							class="form-check-input" name="def" type="radio" id="radio104"
							checked><label class="form-check-label" for="radio104">no</label>
					</div>
				</div>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Spouse
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Father
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Mother
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Child_1
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Child_2
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Child_3
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
			</div> 
		</div>  -->

		<!--  <div class="row" style="background-color: #4cbcce; height: 20px;"></div>-->
		<div class="row" style="background-color: #4cbcce;"
			id="insuredDetails">
			<div class="panel-title runs1" style="background-color: #d1bebe;">
				&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
					data-parent="#accordion"
					href="#collapseGroupFriendsTravelInsurance" aria-expanded="true"
					aria- controls="collapseOne"> Insured Details </a>
			</div>
			<div id="collapseGroupFriendsTravelInsurance"
				class="panel-collapse collapse runs" role="tabpanel"
				aria-labelledby="headingOne" class="main 
   wrapper2"
				style="margin: auto;">
				<br>
				<div class="div2 div4">

					<table class="table" border="1" width="80%" height="120%"
						style="margin: auto;" id="insuredDetailsTable">
						<tr style="background-color: #dac8b6;" height="10%">
							<th>Sr No</th>
							<!--<th>Insured Relationship with Proposer</th>-->
							<th>First Name</th>
							<th>Middle Name</th>
							<th>Last Name</th>
							<th>Gender</th>
							<th>DOB</th>
							<!--  <th>RelationShip</th>-->
							<th>Passport NO.</th>
							<!--  <th>Current Sickness</th>-->
							<!--  <th>Name of Nominee</th>-->
							<!-- <th>Relationship With Nominee</th> -->
							<th>Occupation</th>
							<th>Gross Annual Income</th>
							<!--<th>SI-A(Death)</th>-->
							<!--<th>SI-B(Death+Permanent Total Disability(PTD))</th> -->
							<!--<th>SI-C(Death+Permanent Total Disability(PTD)+Permanent
								Partial Disability(PPD))</th> -->
							<!--<th>SI-D(Death+Permanent Total Disability(PTD)+Permanent
								Partial Disability(PPD))+Temporary Total Disability(TTD)</th> -->
							<th>Sum Insured</th>

						</tr>
					</table>
				</div>








			</div>
		</div>


		<div class="row" style="background-color: #4cbcce; height: 20px;"></div>
		<div class="row" style="background-color: #4cbcce;">
			<div class="panel-title ran1" style="background-color: #d1bebe;">
				&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
					data-parent="#accordion" href="#collapseStudentsTravelInsurance"
					aria-expanded="true" aria- controls="collapseOne"> Students
					Travel Insurance </a>
			</div>
			<div id="collapseStudentsTravelInsurance"
				class="panel-collapse collapse ran" role="tabpanel"
				aria-labelledby="headingOne" class="main 
   wrapper2"
				style="margin: auto;">
				<br>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Student_1
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<br>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Student_2
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<br>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Student_3
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<br>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Student_4
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<br>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Student_5
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp; Student_6
					Details</div>
				<div class="row" style="padding: 5px">
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="First_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Middle_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input type="text" placeholder="Last_Name">
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-xl-3"
						style="width: 300px;">
						<input id="date" type="text" class="datePicker"
							placeholder="D.O.B">
					</div>
				</div>

			</div>
		</div>

		<div class="row" style="background-color: #4cbcce; height: 20px;"></div>
		<div class="row" style="background-color: #4cbcce"
			id="nomineeDetailsTab">
			<div class="panel-title" style="background-color: #d1bebe;">
				&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
					data-parent="#accordion" href="#collapseThree" aria-expanded="true"
					aria-controls="collapseThree"> Nominee Details </a>
			</div>
			<br>
			<div id="collapseThree" class="panel-collapse collapse"
				role="tabpanel" aria-labelledby="headingOne" class="main wrapper2"
				style="margin: auto;">
				<div class="row" style="padding: 5px">

					<div class="col-md-3">
						<div class="col-md-6">
							<label for="contact"><font color="#ffffff">Title</font></label>
						</div>
						<div class="col-md-5">
							<select id="nomDtlInitial" style="width: 145px">
								<option>--Select--</option>
							</select>
						</div>
					</div>

					<div class="col-md-3">
						<div class="col-md-5">
							<label for="contact"><font color="#ffffff">First
									Name</font></label>
						</div>
						<div class="col-md-5">
							<input id="nomDtlFname" type="text" style="width: 145px">
						</div>
					</div>

					<div class="col-md-3">
						<div class="col-md-6">
							<label for="contact"><font color="#ffffff">Middle
									Name</font></label>
						</div>
						<div class="col-md-4">
							<input id="nomDtlMname" type="text" style="width: 145px">
						</div>
					</div>

					<div class="col-md-3">
						<div class="col-md-5">
							<label for="contact"><font color="#ffffff">Last
									Name</font></label>
						</div>
						<div class="col-md-5">
							<input id="nomDtlLname" type="text" style="width: 145px">
						</div>
					</div>

				</div>
				<div class="row" style="padding: 5px">
					<div class="col-md-3">
						<div class="col-md-6">
							<label for="contact"><font color="#ffffff">Relation</font></label>
						</div>
						<div class="col-md-5">
							<select id="nomDtlRelation" style="width: 145px">
								<option>--Select--</option>
							</select>
						</div>
					</div>

					<div class="col-md-3">
						<div class="col-md-5">
							<label for="contact"><font color="#ffffff">Aadhar
									No</font></label>
						</div>
						<div class="col-md-5">
							<input id="nomDtlAdharNo" type="text"
								onkeypress="return isNumber(event)" maxlength="12"
								style="width: 145px">
						</div>
					</div>


					<div class="col-md-3">
						<div class="col-md-6">
							<label for="contact"><font color="#ffffff">Date Of
									Birth</font></label>
						</div>
						<div class="col-md-4">
							<input id="nomDtlDOB" type="text" style="width: 145px"
								class="datePicker">
						</div>
					</div>
				</div>

			</div>
</body>
</html>