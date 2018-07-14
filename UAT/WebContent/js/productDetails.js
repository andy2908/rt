
function insDtlRadioVal(){
	var redio = $('#insDtlInitial option:selected').attr('insDtlInitialDtl');
	
	if(redio=="M"){
		$("#insDtlGenMale").prop("checked", true);
		$("#insDtlGenFemale").prop("checked", false);
		$("#insDtlGenOther").prop("checked", false);
		
		$("#insDtlGenMale").prop("disabled", false);
		$("#insDtlGenFemale").prop("disabled", true);
		$("#insDtlGenOther").prop("disabled", true);
		
	}else if(redio=="F"){
		$("#insDtlGenMale").prop("checked", false);
		$("#insDtlGenFemale").prop("checked", true);
		$("#insDtlGenOther").prop("checked", false);
		
		$("#insDtlGenMale").prop("disabled", true);
		$("#insDtlGenFemale").prop("disabled", false);
		$("#insDtlGenOther").prop("disabled", true);
		
	}else if(redio=="O"){
		$("#insDtlGenMale").prop("checked", false);
		$("#insDtlGenFemale").prop("checked", false);
		$("#insDtlGenOther").prop("checked", true);
		
		$("#insDtlGenMale").prop("disabled", true);
		$("#insDtlGenFemale").prop("disabled", true);
		$("#insDtlGenOther").prop("disabled", false);
	}else{
		$("#insDtlGenMale").prop("checked", false);
		$("#insDtlGenFemale").prop("checked", false);
		$("#insDtlGenOther").prop("checked", false);
		
		$("#insDtlGenMale").prop("disabled", false);
		$("#insDtlGenFemale").prop("disabled", false);
		$("#insDtlGenOther").prop("disabled", false);
	}
}

$("#passengers").keypress(function (event) {
    if(event.keyCode == 13) { 
    	setTimeout(function(){$('#hp').focus();},1);
    }
});
$("#hp").keypress(function (event) {
    if(event.keyCode == 13) { 
    	setTimeout(function(){$('#kg').focus();},1);
    }
});
$("#kg").keypress(function (event) {
    if(event.keyCode == 13) { 
    	setTimeout(function(){$('#wheels').focus();},1);
    }
});
$("#wheels").keypress(function (event) {
    if(event.keyCode == 13) { 
    	setTimeout(function(){$('#cc').focus();},1);
    }
});
$("#cc").keypress(function (event) {
    if(event.keyCode == 13) { 
    	setTimeout(function(){$('#fuelType').focus();},1);
    }
});
$("#fuelType").keypress(function (event) {
    if(event.keyCode == 13) { 
    	setTimeout(function(){$('#fuelKit').focus();},1);
    }
});
$("#tblBiofuelKitPack").keypress(function (event) {
    if(event.keyCode == 13) { 
    	console.log("hiiiiiiiiiiii");
    	setTimeout(function(){$('#searchLiability').focus();},1);
    }
});


$('#dateofReg').click(function(){
	var month1 = $("#month").val();
	month1--;
	var year1 = $("#year option:selected").text();
	var today = new Date();
	var yr = today.getFullYear();
	var mm = today.getMonth()
	var dd = today.getDate();
	$("#dateofReg").jqxDateTimeInput({min: new Date(year1, month1, 1), max: new Date(yr, mm, dd)}); 
	$("#prevDtlRegDate").jqxDateTimeInput({ min: new Date(year1, month1, 1), max: new Date(yr, mm, dd) });
	console.log("month1>>"+month1 +"year1>>"+year1 );
	//$('#dateofReg').datepicker('setStartDate', "01-"+month1+"-"+year1);
	
});
	
$("#month,#year,#RTOState,#RTOSCity,#vehicle,#Model,#varience,#fuelKit,#customer,#bodyType").select2({
		allowClear : true,
		maximumSelectionSize : 1,
});
$("#insBankDtlBankName,#insBankDtlBranchName,#finDtlFinType,#finDtlFinBy,#finDtlFinName,#finDtlFinState,#finDtlFinCity").select2({
	allowClear : true,
	maximumSelectionSize : 1,
});

//////Product Details Validations Starts Here/////////////////
function clearInsuredBankDtls()
{
	$('#insBankDtlBankName,#insBankDtlBranchName').select2('data', {
		allowClear : true,
		id: null,
		text: '--Select--'
	});
	
	$('#insBankDtlAccNo,#insBankDtlifscCode,#insBankDtlOtherDetails').val("");
 
}

function  emptyTFValidationForProductDetails() {
	
/*	if (!$('#productname').val()) {
		old_alert("Please Select Product Name");
		setTimeout(function(){$('#productname').select2('focus');},1);
		return false;
	}
	
	if (!$('#proposal').val()) {
		old_alert("Please Select Proposal Type");
		setTimeout(function(){$('#proposal').select2('focus');},1);
		return false;
	}*/
	if (!$('#year').val()) {
		old_alert("Please Select Manufacture Year");
		//setTimeout(function(){$('#year').select2('focus');},1);
		$('#year').select2('focus');
		return false;
	}
	if (!$('#month').val()) {
		old_alert("Please Select Manufacture Month");
		$('#month').select2('focus');
		return false;
	}
	
	if (!$('#dateofReg').val()) {
		old_alert("Please Select dateofReg!!");
		setTimeout(function(){$('#dateofReg').select2('focus');},1);
		return false;
	}
	
	if (!$('#RTOState').val()) {
		old_alert("Please Select RTO State");
		$('#RTOState').select2('focus');
		return false;
	}
	if (!$('#RTOSCity').val()) {
		old_alert("Please Select RTO City");
		setTimeout(function(){$('#RTOSCity').select2('focus');},1);
		return false;
	}
	if (!$('#vehicle').val()) {
		old_alert("Please Select Vehicle Type");
		setTimeout(function(){$('#vehicle').select2('focus');},1);
		return false;
	}
	if (!$('#Model').val()) {
		old_alert("Please Select Vehicle Model");
		setTimeout(function(){$('#Model').select2('focus');},1);
		return false;
	}
	if (!$('#varience').val()) {
		old_alert("Please Select Variance ");
		setTimeout(function(){$('#varience').select2('focus');},1);
		
		return false;
	}
	
	if (!$('#passengers').val()) {
		old_alert("Please Select Number of Passengers");
		setTimeout(function(){$('#passengers').select2('focus');},1);
	
		return false;
	}
	
	if (!$('#Zone').val()) {
		old_alert("Please Select Zone");
		setTimeout(function(){$('#Zone').select2('focus');},1);
		
		return false;
	}
	if (!$('#hp').val()) {
		old_alert("Please Select HP");
		setTimeout(function(){$('#hp').select2('focus');},1);
		return false;
	}
	if (!$('#wheels').val()) {
		old_alert("Please Select Number of Wheels");
		setTimeout(function(){$('#wheels').select2('focus');},1);
		return false;
	}
	if (!$('#cc').val()) {
		old_alert("Please Select CC");
		setTimeout(function(){$('#cc').select2('focus');},1);
		return false;
	}
	if (!$('#fuelType').val()) {
		old_alert("Please Select Fuel Type");
		setTimeout(function(){$('#fuelType').select2('focus');},1);
		return false;
	}
	/*if (!$('#fuelKit').val()) {
		old_alert("Please Select Fule Kit");
		$('#fuelKit').select2('focus');
		return false;
	}*/
	if (!$('#exshowroomprice').val()) {
		old_alert("Please Select Ex-showroom Price");
		setTimeout(function(){$('#exshowroomprice').select2('focus');},1);
		return false;
	}
	if (!$('#customer').val()) {
		old_alert("Please Select Customer Type");
		setTimeout(function(){$('#customer').select2('focus');},1);
		return false;
	}
	
	if (!$('#mobileNo').val()) {
		old_alert("Please Enter mobile Number");
		setTimeout(function(){$('#mobileNo').focus();},1);
		//$('#mobileNo').focus();
		return false;
	}
	
	if (!$('#emailId').val()) {
		old_alert("Please Select Email Id");
		$('#emailId').focus();
		return false;
	}
	
	
	var proposal=$('#proposal').val();
	if(proposal!=1){
		//old_old_alert("proposal::>>"+proposal);
		var checkedYes=$("#prevPolYes").prop("checked");
		var checkedNo=$("#prevPolNo").prop("checked");
		if(checkedYes== false && checkedNo== false){
			old_alert("Please Select Previous Policy Exist Or Not");
			setTimeout(function(){$('#prevPolNo').focus();},1);
			return false;
		}
	}
	
	return true;
	
}

function enableAllButtons()
{
	$('#showCompanyBtn').prop('disabled', false);
}

function  emptyTFValidationForPrevPolDetails() {
	
	if (!$('#prevProduct').val()) {
		old_alert("Please Select Previous Product Name");
		setTimeout(function(){$('#prevProduct').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	
	if (!$('#prevMfrYear').val()) {
		old_alert("Please Select Previous Policy Year");
		setTimeout(function(){$('#prevMfrYear').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	
	if (!$('#prevDtlRegDate').val()) {
		old_alert("Please Select Previous Policy DateofReg!!");
		setTimeout(function(){$('#prevDtlRegDate').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	
	if (!$('#prevRtoState').val()) {
		old_alert("Please Select Previous Policy RTO State");
		setTimeout(function(){$('#prevRtoState').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevRtoCity').val()) {
		old_alert("Please Select Previous Policy RTO City");
		setTimeout(function(){$('#prevRtoCity').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevVehicleType').val()) {
		old_alert("Please Select Previous Policy Vehicle Type");
		setTimeout(function(){$('#prevVehicleType').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevModel').val()) {
		old_alert("Please Select Previous Policy Model");
		setTimeout(function(){$('#prevModel').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevVariance').val()) {
		old_alert("Please Select Previous Policy Variance ");
		setTimeout(function(){$('#prevVariance').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	
	if (!$('#prevPassanger').val()) {
		old_alert("Please Select Previous Policy Variance Again");
		setTimeout(function(){$('#prevPassanger').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	
	if (!$('#prevCc').val()) {
		old_alert("Please Select Previous Policy Variance Again");
		setTimeout(function(){$('#prevCc').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	
	if (!$('#prevPolicyType').val()) {
		old_alert("Please Select Previous PolicyType ");
		setTimeout(function(){$('#prevPolicyType').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	
	if (!$('#prevInsuranceType').val()) {
		old_alert("Please Select Previous Insurance Type");
		setTimeout(function(){$('#prevInsuranceType').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	
	if (!$('#prevInsuranceCompany').val()) {
		old_alert("Please Select Previous Insurance Company");
		setTimeout(function(){$('#prevInsuranceCompany').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	
	if (!$('#prevPolicyCoverNo').val()) {
		old_alert("Please Enter Previous Covernote Policy Number");
		setTimeout(function(){$('#prevPolicyCoverNo').focus();},1);
		enableAllButtons();
		return false;
	}
	
/*	if (!$('#prevFuelKit').val()) {
		old_alert("Please Select Previous Policy Fuelkit");
		$('#prevFuelKit').select2('focus');
		return false;
	}*/
	
	if (!$('#prevInsFuelType').val()) {
		old_alert("Please Select Previous Policy Fuel Type");
		setTimeout(function(){$('#prevInsFuelType').focus();},1);
		enableAllButtons();
		return false;
	}
	
	if (!$('#prevPolicyPeriod').val()) {
		old_alert("Please Select Previous Policy Period ");
		setTimeout(function(){$('#prevPolicyPeriod').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevPolicyMonth').val()) {
		old_alert("Please Select Previous Policy Month ");
		setTimeout(function(){$('#prevPolicyMonth').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevFromDate').val()) {
		old_alert("Please Select Previous Policy From Date ");
		setTimeout(function(){$('#prevFromDate').focus();},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevToDate').val()) {
		old_alert("Please Select Previous Policy To Date ");
		setTimeout(function(){$('#prevToDate').focus();},1);
		enableAllButtons();
		return false;
	}
	/*if (!$('#prevIdv').val()) {
		old_alert("Please Select Previous Policy IDV ");
		$('#prevIdv').select2('focus');
		return false;
	}*/
	if (!$('#prevCustomerType').val()) {
		old_alert("Please Select Previous Policy Customer Type ");
		setTimeout(function(){$('#prevCustomerType').select2('focus');},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevVehId1').val()) {
		old_alert("Please Select Previous Policy Vehicle Number");
		setTimeout(function(){$('#prevVehId1').focus();},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevVehId2').val()) {
		old_alert("Please Select Previous Policy Vehicle Number");
		setTimeout(function(){$('#prevVehId2').focus();},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevVehId3').val()) {
		old_alert("Please Select Previous Policy Vehicle Number");
		setTimeout(function(){$('#prevVehId3').focus();},1);
		enableAllButtons();
		return false;
	}
	if (!$('#prevVehId4').val()) {
		old_alert("Please Select Previous Policy Vehicle Number");
		setTimeout(function(){$('#prevVehId4').focus();},1);
		enableAllButtons();
		return false;
	}
	
	return true;
	
}

function nomAddValidation(){
	if (!$('#insDtlCACountry,#insDtlCAState,#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode,#insDtlCALandmark,#insDtlCAStreet,#insDtlCAHouse').val()) {
		old_alert("Address Not Filled In Insured Details Form");
		setTimeout(function(){$('#nomDtlCountry').select2('focus');},1);
		return false;
	}
	return true;
	
}

function prevToDateValidation(){
	var ins_to_dt = $('#prevToDate').val();
	
	var toDate = ins_to_dt.split("/");
	 
    var toDay = toDate[0];
	var toMonth = toDate[1];
	var toYr = toDate[2];
	var insToDate=  toMonth + "/" + toDay + "/" + toYr;
	
	var date = new Date();
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();
	if (month < 10)
		month = "0" + month;
	if (day < 10)
		day = "0" + day;

	var currentDate = month  + "/" + day + "/" +  year ;
	
	var date1 = new Date(insToDate);
	var date2 = new Date(currentDate);
	var timeDiff = Math.abs(date2.getTime()
			- date1.getTime());
	var calcDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
	
   /* old_alert("date1==>"
    		+date1+"   date2==>>"+date2+"  calcDiff===>>"+calcDiff);
   */
   
    if (calcDiff > 21) {
    	 old_alert("Please Enter Insurance To Date Less Than 21 Days From Today");
    	 setTimeout(function(){$('#prevToDate').focus();},1);
    	 $("#prevToDate").val("");
    }
}

//////Product Details Validations Ends Here/////////////////
//////Insured Details Validations Starts Here/////////////////
function  InsuredDetailsAndNomineeDetails() {
	
	if (!$('#insDtlInitial').val()||$('#insDtlInitial').val()=="--Select--") {
		old_alert("Please Select Salutation");
		$(function () {
			var compDtlActive = true;
			$('#getProposal').click(function () {
		        if (compDtlActive) {
		        	compDtlActive = false;
		            $('.insured').collapse('show');
		            $('.insured1').attr('data-toggle', '');
		           
		        } 
		    });
		    $('#accordion').on('show.bs.collapse', function () {
		        if (compDtlActive) $('#accordion .in').collapse('hide');
		    });
		});
		 setTimeout(function(){$('#insDtlInitial').select2('focus');},1);
		return false;
	}
//	old_alert("insDtlFname')" + $('#insDtlFname').val());
//	old_alert("insDtlMaritialStatus" + $('#insDtlMaritialStatus').val());
	if (!$('#insDtlFname').val()) {
		old_alert("Please Select First Name");
		setTimeout(function(){$('#insDtlFname').focus();},1);
		return false;
	}
	if (!$('#insDtlMname').val()) {
		old_alert("Please Select Middle Name");
		setTimeout(function(){$('#insDtlMname').focus();},1);
		return false;
	}
	if (!$('#insDtlLname').val()) {
		old_alert("Please Select Last Name");
		setTimeout(function(){$('#insDtlLname').focus();},1);
		return false;
	}
	if (!$('#insDtlDob').val()) {
		old_alert("Please Select Date of Birth");
		setTimeout(function(){$('#insDtlDob').focus();},1);
		return false;
	}
	if (!$('#insDtlMaritialStatus').val()) {
		old_alert("Please Select Maritial Status");
		setTimeout(function(){$('#insDtlMaritialStatus').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlNationality').val()) {
		old_alert("Please Select Nationality");
		setTimeout(function(){$('#insDtlNationality').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlMobileNo').val()) {
		old_alert("Please Select Mobile No");
		setTimeout(function(){$('#insDtlMobileNo').focus();},1);
		return false;
	}
	if (!$('#insDtlPhoneNo').val()) {
		old_alert("Please Select Phone No");
		setTimeout(function(){$('#insDtlPhoneNo').focus();},1);
		return false;
	}
	if (!$('#insDtlEmail').val()) {
		old_alert("Please Select Email Id");
		setTimeout(function(){$('#insDtlEmail').focus();},1);
		return false;
	}
	checkEmail("insDtlEmail");
	if (!$('#insDtlPanNo').val()) {
		old_alert("Please Select PAN No");
		setTimeout(function(){$('#insDtlPanNo').focus();},1);
		return false;
	}

	if($("#companyDtl_companyId").val()== "47"){
		if (!$('#insDtlAadharEnrollNo').val()) {
			old_alert("Please Select Aadhar Enroll No");
			setTimeout(function(){$('#insDtlAadharEnrollNo').focus();},1);
			return false;
		}
	}
	if (!$('#insDtlAadharNo').val()) {
		old_alert("Please Select Aadhar No");
		setTimeout(function(){$('#insDtlAadharNo').focus();},1);
		return false;
	}
	if (!$('#insDtlCAName').val()) {
		old_alert("Please Select Address name");
		setTimeout(function(){$('#insDtlCAName').focus();},1);
		return false;
	}
	if (!$('#insDtlCACountry').val()) {
		old_alert("Please Select Country");
		setTimeout(function(){$('#insDtlCACountry').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlCAState').val()) {
		old_alert("Please Select State");
		setTimeout(function(){$('#insDtlCAState').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlCADistrict').val()) {
		old_alert("Please Select District");
		setTimeout(function(){$('#insDtlCADistrict').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlCACity').val()) {
		old_alert("Please Select City");
		setTimeout(function(){$('#insDtlCACity').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlCAPincode').val()) {
		old_alert("Please Select Pincode");
		setTimeout(function(){$('#insDtlCAPincode').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlCALandmark').val()) {
		old_alert("Please Select LandMark");
		setTimeout(function(){$('#insDtlCALandmark').focus();},1);
		return false;
	}
	if (!$('#insDtlCAStreet').val()) {
		old_alert("Please Select Street");
		setTimeout(function(){$('#insDtlCAStreet').focus();},1);
		return false;
	}
	if (!$('#insDtlCAHouse').val()) {
		old_alert("Please Select House Number");
		setTimeout(function(){$('#insDtlCAHouse').focus();},1);
		return false;
	}
	if (!$('#insDtlPACountry').val()) {
		old_alert("Please Select Country");
		setTimeout(function(){$('#insDtlPACountry').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlPAState').val()) {
		old_alert("Please Select State");
		setTimeout(function(){$('#insDtlPAState').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlPADistrict').val()) {
		old_alert("Please Select District");
		setTimeout(function(){$('#insDtlPADistrict').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlPACity').val()) {
		old_alert("Please Select City");
		setTimeout(function(){$('#insDtlPACity').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlPAPincode').val()) {
		old_alert("Please Select Pincode");
		setTimeout(function(){$('#insDtlPAPincode').select2('focus');},1);
		return false;
	}
	if (!$('#insDtlPALandmark').val()) {
		old_alert("Please Select LandMark");
		setTimeout(function(){$('#insDtlPALandmark').focus();},1);
		return false;
	}
	if (!$('#insDtlPAStreet').val()) {
		old_alert("Please Select Street");
		setTimeout(function(){$('#insDtlPAStreet').focus();},1);
		return false;
	}
	if (!$('#insDtlPAHouse').val()) {
		old_alert("Please Select House Number");
		setTimeout(function(){$('#insDtlPAHouse').focus();},1);
		return false;
	}
	if($("#companyDtl_companyId").val() == "49"){
		if (!$('#insDtlOACountry').val()) {
			old_alert("Please Select Country");
			setTimeout(function(){$('#insDtlOACountry').select2('focus');},1);
			return false;
		}
		if (!$('#insDtlOAState').val()) {
			old_alert("Please Select State");
			setTimeout(function(){$('#insDtlOAState').select2('focus');},1);
			return false;
		}
		if (!$('#insDtlOADistrict').val()) {
			old_alert("Please Select District");
			setTimeout(function(){$('#insDtlOADistrict').select2('focus');},1);
			return false;
		}
		if (!$('#insDtlOACity').val()) {
			old_alert("Please Select City");
			setTimeout(function(){$('#insDtlOACity').select2('focus');},1);
			return false;
		}
		if (!$('#insDtlOAPincode').val()) {
			old_alert("Please Select Pincode");
			setTimeout(function(){$('#insDtlOAPincode').select2('focus');},1);
			return false;
		}
		if (!$('#insDtlOALandmark').val()) {
			old_alert("Please Select LandMark");
			setTimeout(function(){$('#insDtlOALandmark').focus();},1);
			return false;
		}
		if (!$('#insDtlOAStreet').val()) {
			old_alert("Please Select Street");
			setTimeout(function(){$('#insDtlOAStreet').focus();},1);
			return false;
		}
		if (!$('#insDtlOAHouse').val()) {
			old_alert("Please Select House Number");
			setTimeout(function(){$('#insDtlOAHouse').focus();},1);
			return false;
		}
	}
	if (!$('#nomDtlInitial').val()) {
		old_alert("Please Select nominee Salutation");
		$(function () {
			var compDtlActive = true;
		            $('.nominee').collapse('show');
		            $('.nominee1').attr('data-toggle', '');
		     
		    $('#accordion').on('show.bs.collapse', function () {
		        if (compDtlActive) $('#accordion .in').collapse('hide');
		    });
		});
		setTimeout(function(){$('#nomDtlInitial').select2('focus');},1);
		return false;
	} 
	if (!$('#nomDtlFname').val()) {
		old_alert("Please Select nominee First Name");
		setTimeout(function(){$('#nomDtlFname').focus();},1);
		return false;
	}
	if (!$('#nomDtlMname').val()) {
		old_alert("Please Select nominee Middle Name");
		setTimeout(function(){$('#nomDtlMname').focus();},1);
		return false;
	}
	if (!$('#nomDtlLname').val()) {
		old_alert("Please Select nominee Last Name");
		setTimeout(function(){$('#nomDtlLname').focus();},1);
		return false;
	}
	if (!$('#nomDtlRelation').val()) {
		old_alert("Please Select nominee Relation");
		setTimeout(function(){$('#nomDtlRelation').select2('focus');},1);
		return false;
	}
	if (!$('#nomDtlAdharNo').val()) {
		old_alert("Please Select nominee AdharNo");
		setTimeout(function(){$('#nomDtlAdharNo').focus();},1);
		return false;
	}
	if (!$('#nomDtlDOB').val()) {
		old_alert("Please Select nominee DOB");
		setTimeout(function(){$('#nomDtlDOB').focus();},1);
		return false;
	}
	if (!$('#nomDtlCountry').val()) {
		old_alert("Please Select nominee Country");
		setTimeout(function(){$('#nomDtlCountry').select2('focus');},1);
		return false;
	}
	if (!$('#nomDtlState').val()) {
		old_alert("Please Select nominee State");
		setTimeout(function(){$('#nomDtlState').select2('focus');},1);
		return false;
	}
	if (!$('#nomDtlDistrict').val()) {
		old_alert("Please Select nominee District");
		setTimeout(function(){$('#nomDtlDistrict').select2('focus');},1);
		return false;
	}
	if (!$('#nomDtlCity').val()) {
		old_alert("Please Select nominee City");
		setTimeout(function(){$('#nomDtlCity').select2('focus');},1);
		return false;
	}
	if (!$('#nomDtlPincode').val()) {
		old_alert("Please Select nominee Pincode");
		setTimeout(function(){$('#nomDtlPincode').select2('focus');},1);
		return false;
	}
	if (!$('#nomDtlLandmark').val()) {
		old_alert("Please Select nominee Landmark");
		setTimeout(function(){$('#nomDtlLandmark').focus();},1);
		return false;
	}
    if (!$('#nomDtlStreet').val()) {
		old_alert("Please Select nominee Street");
		setTimeout(function(){$('#nomDtlStreet').focus();},1);
		return false;
	}
    if (!$('#nomDtlHouseNo').val()) {
    	old_alert("Please Select nominee HouseNo");
    	setTimeout(function(){$('#nomDtlHouseNo').focus();},1);
    	return false;
    }
    if (!$('#nomDtlEmail').val()) {
    	old_alert("Please Select nominee Email Id");
    	setTimeout(function(){$('#nomDtlEmail').focus();},1);
    	return false;
    }
    if (!$('#nomDtlphoneNo').val()) {
    	old_alert("Please Select nominee phone No");
    	setTimeout(function(){$('#nomDtlphoneNo').focus();},1);
    	return false;
    }
	
    if (!$('#nomDtlMobileNo').val()) {
    	old_alert("Please Select nominee Mobile No");
    	setTimeout(function(){$('#nomDtlMobileNo').focus();},1);
    	return false;
    }
	
	return true;
}

//////Insured Details Validations Ends Here/////////////////

function getInsFindFormData(pkgName,procName,tableName,strCompType,strGic,strGicBid,strProd,strDiscName,strRGrp,
		strState,strCity,strPrpsl,strSpnm,strMgnm,strProdCode,strType,strType1,kgFrom,kgTo,fuelType,strVeh,strMod,
		strVar,policyAge,strHbbId,userLevel,userId,loginType,strGap,ageTo){
	var returnResp = "";
	$.ajax({
		url : "getInsFindFormData?pkg_name="+pkgName+"&proc_name="+procName+"&table_name="+tableName+
		"&str_company_type="+strCompType+"&str_gic="+strGic+"&str_gicbid="+strGicBid+"&str_prod="+strProd+
		"&str_discnm="+strDiscName+"&str_rgrp="+strRGrp+"&str_state="+strState+"&str_city="+strCity+
		"&str_prpsl="+strPrpsl+"&str_spnm="+strSpnm+"&str_mgnm="+strMgnm+"&str_productcode="+strProdCode+
		"&str_type="+strType+"&str_type_1="+strType1+"&str_kg_from="+kgFrom+"&str_kg_to="+kgTo+"&str_fueltype="+fuelType+
		"&str_veh="+strVeh+"&str_mod="+strMod+"&str_var="+strVar+"&str_policy_age="+policyAge+"&str_hbbid="+strHbbId+
		"&str_user_level="+userLevel+"&str_user_id="+userId+"&str_login_type="+loginType+"&str_gap="+strGap+"&str_ageto="+ageTo,
		type : 'post',
		dataType : 'json',
		async : false,
		success : function(resp) {
			returnResp = resp;
		},
	});
	return returnResp;
}

function getRecordList(sqlMstId,param){
	var returnResp = "";
	$.ajax({
		url: "getRecordLst?sqlMstId="+sqlMstId+"&param="+param,
		type: 'post',
		dataType: 'json',
		async: false,
		success: function (resp) {
			returnResp = resp;
		},error:function (resp){
			alert("Error in sql id="+ sqlMstId+"::param="+param);
		}
	});
	return returnResp;
}

//--------Text Change Propert validation----------------------------------------------

function resetDivOnProductChange()
{
	$("#paymentDetailsDiv").hide();
	$("#coversDiv").hide();
	$("#getPremiumBtnDiv").hide();
	$("#otherDetailsDiv").hide();
	$("#nomineeDtlDiv").hide();
    $("#insuredDtlDiv").hide();   
    $("#companyDtlDiv").hide();
    $("#paymentOptionsDiv").hide();  
    $("#proposalDetailsDiv").hide();  
    $("#cardPay").hide(); 
    $("#onlinePay").hide(); 
    $("#relExtPay").hide(); 
    $("#cdtBal").hide();
    $("#remainingBal").hide();   
    $("#creditcardPay").hide();
    $("#VehicleFinDtlDiv").hide();  
    $("#showCompanyDiv").show();  
	$('#showCompanyBtn').prop('disabled', false);	
}
	//--------Product Details----------

	/*$("#productname").change(function() {
		   $('#year,#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience').select2('data', {
			 allowClear : true,
		     id: null,
		     text: '--Select--'
		   });
		   
		   $(",#year,#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience").empty();
		   $("#year,#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience").append('<option value="">--Select--');
		   $("#passengers,#hp,#kg,#wheels,#cc,#fuelType,#dateofReg,#Zone,#exshowroomprice").val("");

	});

	$("#proposal").change(function() {
		   $('#year,#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience,#customer').select2('data', {
			 allowClear : true,
		     id: null,
		     text: '--Select--'
		   });
		   
		   $("#year,#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience,#customer").empty();
		   $("#year,#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience,#customer").append('<option value="">--Select--');
		   $("#passengers,#hp,#kg,#wheels,#cc,#fuelType,#dateofReg,#Zone,#exshowroomprice").val("");

	});*/

	$("#year").change(function() {
	   $('#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience,#customer').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   console.log("hereeeee");
	   $("#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience,#customer").empty();
	   $("#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience,#customer").append('<option value="">--Select--');
	   $('#dateofReg').val("");
	   $("#passengers,#hp,#kg,#wheels,#cc,#fuelType,#Zone,#exshowroomprice").val("");
	   console.log("hereeeee1111111");
	   resetDivOnProductChange();
	   
	});

	$("#month").change(function() {
	   $('#RTOState,#RTOSCity,#vehicle,#Model,#varience,#customer').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#RTOState,#RTOSCity,#vehicle,#Model,#varience,#customer").empty();
	   $("#RTOState,#RTOSCity,#vehicle,#Model,#varience,#customer").append('<option value="">--Select--');
	   $('#dateofReg').val("");
	   $("#passengers,#hp,#kg,#wheels,#cc,#fuelType,#Zone,#exshowroomprice").val("");
	   resetDivOnProductChange();
	});
	 
	$("#RTOState").change(function() {
		   $('#RTOSCity,#vehicle,#Model,#varience,#customer').select2('data', {
			 allowClear : true,
		     id: null,
		     text: '--Select--'
		   });
		   
		   $("#RTOSCity,#vehicle,#Model,#varience,#customer").empty();
		   $("#RTOSCity,#vehicle,#Model,#varience").append('<option value="">--Select--');
		   $("#passengers,#hp,#kg,#wheels,#cc,#fuelType,#Zone,#exshowroomprice").val("");
		   resetDivOnProductChange();
	});

	$("#RTOSCity").change(function() {
		   $('#vehicle,#Model,#varience,#customer').select2('data', {
			 allowClear : true,
		     id: null,
		     text: '--Select--'
		   });
		   
		   $("#vehicle,#Model,#varience,#customer").empty();
		   $("#vehicle,#Model,#varience,#customer").append('<option value="">--Select--');
		   $("#passengers,#hp,#kg,#wheels,#cc,#fuelType,#Zone,#exshowroomprice").val("");
		   resetDivOnProductChange();
	});

	$("#vehicle").change(function() {
		   $('#Model,#varience,#customer').select2('data', {
			 allowClear : true,
		     id: null,
		     text: '--Select--'
		   });
		   
		   $("#Model,#varience,#customer").empty();
		   $("#Model,#varience,#customer").append('<option value="">--Select--');
		   $("#passengers,#hp,#kg,#wheels,#cc,#fuelType,#exshowroomprice").val("");
		   resetDivOnProductChange();
	});

	$("#Model").change(function() {
		   $('#varience,#customer').select2('data', {
			 allowClear : true,
		     id: null,
		     text: '--Select--'
		   });
		   
		   $("#varience,#customer").empty();
		   $("#varience,#customer").append('<option value="">--Select--');
		   $("#passengers,#hp,#kg,#wheels,#cc,#fuelType,#exshowroomprice").val("");
		   resetDivOnProductChange();
	});

	$("#varience").change(function() {
		 $('#customer').select2('data', {
			 allowClear : true,
		     id: null,
		     text: '--Select--'
		   });
		   
		   $("#customer").empty();
		   $("#customer").append('<option value="">--Select--');
		   $("#passengers,#hp,#kg,#wheels,#cc,#fuelType,#exshowroomprice").val("");
		   resetDivOnProductChange();
		 
	});
	
	$("#fuelKit").change(function() {
//		old_alert("In fuelKit Change");
		   resetDivOnProductChange();
		 
	});
	$("#customer").change(function() {
//		old_alert("In fuelKit Change");
		resetDivOnProductChange();
		
	});

//--------Pevious Policy details----------

$("#prevProduct").change(function() {
	   $('#prevRtoState,#prevRtoCity,#prevVehicleType,#prevModel,#prevVariance').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#prevRtoState,#prevRtoCity,#prevVehicleType,#prevModel,#prevVariance").empty();
	   $("#prevRtoState,#prevRtoCity,#prevVehicleType,#prevModel,#prevVariance").append('<option value="">--Select--');
	   $("#prevPassanger,#prevCc,#prevInsFuelType").val("");
	   resetDivOnProductChange();
});

$("#prevRtoState").change(function() {
	   $('#prevRtoCity,#prevVehicleType,#prevModel,#prevVariance').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#prevRtoCity,#prevVehicleType,#prevModel,#prevVariance").empty();
	   $("#prevRtoCity,#prevVehicleType,#prevModel,#prevVariance").append('<option value="">--Select--');
	   $("#prevPassanger,#prevCc,#prevInsFuelType").val("");
	   resetDivOnProductChange();
});

$("#prevRtoCity").change(function() {
	   $('#prevVehicleType,#prevModel,#prevVariance').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#prevVehicleType,#prevModel,#prevVariance").empty();
	   $("#prevVehicleType,#prevModel,#prevVariance").append('<option value="">--Select--');
	   $("#prevPassanger,#prevCc,#prevInsFuelType").val("");
	   resetDivOnProductChange();
});

$("#prevVehicleType").change(function() {
	   $('#prevModel,#prevVariance').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#prevModel,#prevVariance").empty();
	   $("#prevModel,#prevVariance").append('<option value="">--Select--');
	   $("#prevPassanger,#prevCc,#prevInsFuelType").val("");
	   resetDivOnProductChange();
});

$("#prevModel").change(function() {
	   $('#prevVariance').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#prevVariance").empty();
	   $("#prevVariance").append('<option value="">--Select--');
	   $("#prevPassanger,#prevCc,#prevInsFuelType").val("");
	   resetDivOnProductChange();
});

$("#prevVariance").change(function() {
	 
	   $("#prevPassanger,#prevCc,#prevInsFuelType").val("");
	   resetDivOnProductChange();
});


//--------Indured Details----------
$("#insDtlCACountry").change(function() {
	   $('#insDtlCAState,#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#insDtlCAState,#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode").empty();
	   $("#insDtlCAState,#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlCALandmark,#insDtlCAStreet,#insDtlCAHouse").val("");
	   $("#insDtlCALandmarkList,#insDtlCAStreetList,#insDtlCAHouseList").empty();
	   
});
$("#insDtlCAState").change(function() {
	  $('#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode').select2('data', {
			 allowClear : true,
		     id: null,
		     text: '--Select--'
		   });
		  
	   $("#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode").empty();
	   $("#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlCALandmark,#insDtlCAStreet,#insDtlCAHouse").val("");
	   $("#insDtlCALandmarkList,#insDtlCAStreetList,#insDtlCAHouseList").empty();
	   
});

$("#insDtlCADistrict").change(function() {
	  $('#insDtlCACity,#insDtlCAPincode').select2('data', {
			 allowClear : true,
		     id: null,
		     text: '--Select--'
		   });
		  
	   $("#insDtlCACity,#insDtlCAPincode").empty();
	   $("#insDtlCACity,#insDtlCAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlCALandmark,#insDtlCAStreet,#insDtlCAHouse").val("");
	   $("#insDtlCALandmarkList,#insDtlCAStreetList,#insDtlCAHouseList").empty();
	   
});

$("#insDtlCACity").change(function() {
	  $('#insDtlCAPincode').select2('data', {
			 allowClear : true,
		     id: null,
		     text: '--Select--'
		   });
		  
	   $("#insDtlCAPincode").empty();
	   $("#insDtlCAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlCALandmark,#insDtlCAStreet,#insDtlCAHouse").val("");
	   $("#insDtlCALandmarkList,#insDtlCAStreetList,#insDtlCAHouseList").empty();
	   
});

$("#insDtlCAPincode").change(function() {
	   $("#insDtlCALandmark,#insDtlCAStreet,#insDtlCAHouse").val("");
});

$("#insDtlCALandmark").change(function() {
	   $("#insDtlCAStreet,#insDtlCAHouse").val("");
});
$("#insDtlCAStreet").change(function() {
	   $("#insDtlCAHouse").val("");
});

/*PA               */

$("#insDtlPACountry").change(function() {
	   $('#insDtlPAState,#insDtlPADistrict,#insDtlPACity,#insDtlPAPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#insDtlPAState,#insDtlPADistrict,#insDtlPACity,#insDtlPAPincode").empty();
	   $("#insDtlPAState,#insDtlPADistrict,#insDtlPACity,#insDtlPAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlPALandmark,#insDtlPAStreet,#insDtlPAHouse").val("");
	   $("#insDtlPALandmarkList,#insDtlPAStreetList,#insDtlPAHouseList").empty();
	   
});
$("#insDtlPAState").change(function() {
	   $('#insDtlPADistrict,#insDtlPACity,#insDtlPAPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#insDtlPADistrict,#insDtlPACity,#insDtlPAPincode").empty();
	   $("#insDtlPADistrict,#insDtlPACity,#insDtlPAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlPALandmark,#insDtlPAStreet,#insDtlPAHouse").val("");
	   $("#insDtlPALandmarkList,#insDtlPAStreetList,#insDtlPAHouseList").empty();
	   
});

$("#insDtlPADistrict").change(function() {
	   $('#insDtlPACity,#insDtlPAPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#insDtlPACity,#insDtlPAPincode").empty();
	   $("#insDtlPACity,#insDtlPAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlPALandmark,#insDtlPAStreet,#insDtlPAHouse").val("");
	   $("#insDtlPALandmarkList,#insDtlPAStreetList,#insDtlPAHouseList").empty();
	   
});

$("#insDtlPACity").change(function() {
	   $('#insDtlPAPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#insDtlPAPincode").empty();
	   $("#insDtlPAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlPALandmark,#insDtlPAStreet,#insDtlPAHouse").val("");
	   $("#insDtlPALandmarkList,#insDtlPAStreetList,#insDtlPAHouseList").empty();
	   
});


$("#insDtlPAPincode").change(function() {
	   $("#insDtlPALandmark,#insDtlPAStreet,#insDtlPAHouse").val("");
});

$("#insDtlPALandmark").change(function() {
	   $("#insDtlPAStreet,#insDtlPAHouse").val("");
});
$("#insDtlPAStreet").change(function() {
	   $("#insDtlPAHouse").val("");
});

/*OA               */

$("#insDtlOACountry").change(function() {
	   $('#insDtlOAState,#insDtlOADistrict,#insDtlOACity,#insDtlOAPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#insDtlOAState,#insDtlOADistrict,#insDtlOACity,#insDtlOAPincode").empty();
	   $("#insDtlOAState,#insDtlOADistrict,#insDtlOACity,#insDtlOAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlOALandmark,#insDtlOAStreet,#insDtlOAHouse").val("");
	   $("#insDtlOALandmarkList,#insDtlOAStreetList,#insDtlOAHouseList").empty();
	   
});
$("#insDtlOAState").change(function() {
	   $('#insDtlOADistrict,#insDtlOACity,#insDtlOAPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#insDtlOADistrict,#insDtlOACity,#insDtlOAPincode").empty();
	   $("#insDtlOADistrict,#insDtlOACity,#insDtlOAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlOALandmark,#insDtlOAStreet,#insDtlOAHouse").val("");
	   $("#insDtlOALandmarkList,#insDtlOAStreetList,#insDtlOAHouseList").empty();
	   
});
$("#insDtlOADistrict").change(function() {
	   $('#insDtlOACity,#insDtlOAPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#insDtlOACity,#insDtlOAPincode").empty();
	   $("#insDtlOACity,#insDtlOAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlOALandmark,#insDtlOAStreet,#insDtlOAHouse").val("");
	   $("#insDtlOALandmarkList,#insDtlOAStreetList,#insDtlOAHouseList").empty();
	   
});

$("#insDtlOACity").change(function() {
	   $('#insDtlOAPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $("#insDtlOAPincode").empty();
	   $("#insDtlOAPincode").append('<option value="">--Select--');
	  
	   $("#insDtlOALandmark,#insDtlOAStreet,#insDtlOAHouse").val("");
	   $("#insDtlOALandmarkList,#insDtlOAStreetList,#insDtlOAHouseList").empty();
	   
});


$("#insDtlOAPincode").change(function() {
	   $("#insDtlOALandmark,#insDtlOAStreet,#insDtlOAHouse").val("");
});

$("#insDtlOALandmark").change(function() {
	   $("#insDtlOAStreet,#insDtlOAHouse").val("");
});
$("#insDtlOAStreet").change(function() {
	   $("#insDtlOAHouse").val("");
});

/*nomDtl*/

$("#nomDtlCountry").change(function() {
	   $('#nomDtlState,#nomDtlDistrict,#nomDtlCity,#nomDtlPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $('#nomDtlState,#nomDtlDistrict,#nomDtlCity,#nomDtlPincode').empty();
	   $('#nomDtlState,#nomDtlDistrict,#nomDtlCity,#nomDtlPincode').append('<option value="">--Select--');
	  
	   $("#nomDtlLandmark,#nomDtlStreet,#nomDtlHouseNo").val("");
	   $("#nomDtlLandmarkList,#nomDtlStreetList,#nomDtlHouseNoList").empty();
	   
});

$("#nomDtlState").change(function() {
	   $('#nomDtlDistrict,#nomDtlCity,#nomDtlPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $('#nomDtlDistrict,#nomDtlCity,#nomDtlPincode').empty();
	   $('#nomDtlDistrict,#nomDtlCity,#nomDtlPincode').append('<option value="">--Select--');
	  
	   $("#nomDtlLandmark,#nomDtlStreet,#nomDtlHouseNo").val("");
	   $("#nomDtlLandmarkList,#nomDtlStreetList,#nomDtlHouseNoList").empty();
	   
});

$("#nomDtlDistrict").change(function() {
	   $('#nomDtlCity,#nomDtlPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $('#nomDtlCity,#nomDtlPincode').empty();
	   $('#nomDtlCity,#nomDtlPincode').append('<option value="">--Select--');
	  
	   $("#nomDtlLandmark,#nomDtlStreet,#nomDtlHouseNo").val("");
	   $("#nomDtlLandmarkList,#nomDtlStreetList,#nomDtlHouseNoList").empty();
	   
});

$("#nomDtlCity").change(function() {
	   $('#nomDtlPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $('#nomDtlPincode').empty();
	   $('#nomDtlPincode').append('<option value="">--Select--');
	  
	   $("#nomDtlLandmark,#nomDtlStreet,#nomDtlHouseNo").val("");
	   $("#nomDtlLandmarkList,#nomDtlStreetList,#nomDtlHouseNoList").empty();
	   
});

$("#nomDtlPincode").change(function() {
	   $("#nomDtlLandmark,#nomDtlStreet,#nomDtlHouseNo").val("");
});

$("#nomDtlLandmark").change(function() {
	   $("#nomDtlStreet,#nomDtlHouseNo").val("");
});
$("#nomDtlStreet").change(function() {
	   $("#nomDtlHouseNo").val("");
});

//--------Inspection Details----------

$("#inspectionStateTF").change(function() {
	   $('#inspectionCityTF,#inspectionPincodeTF').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $('#inspectionCityTF,#inspectionPincodeTF').empty();
	   $('#inspectionCityTF,#inspectionPincodeTF').append('<option value="">--Select--');
	  
	   $("#inspectionLandmarkTF,#inspectionStreetTF,#inspectionHouseTF").val("");
	   $("#inspectionLandmarkList,#inspectionStreetList,#inspectionHouseList").empty();
	   
});

$("#inspectionCityTF").change(function() {
	   $('#inspectionPincodeTF').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	   
	   $('#inspectionPincodeTF').empty();
	   $('#inspectionPincodeTF').append('<option value="">--Select--');
	  
	   $("#inspectionLandmarkTF,#inspectionStreetTF,#inspectionHouseTF").val("");
	   $("#inspectionLandmarkList,#inspectionStreetList,#inspectionHouseList").empty();
	   
});

$("#inspectionPincodeTF").change(function() {
	   $("#inspectionLandmarkTF,#inspectionStreetTF,#inspectionHouseTF").val("");
});
$("#inspectionLandmarkTF").change(function() {
	   $("#inspectionStreetTF,#inspectionHouseTF").val("");
});
$("#inspectionStreetTF").change(function() {
	   $("#inspectionHouseTF").val("");
});

//--------Text Change Propert validation----------------------------------------------

function fillPrevDtlsData(){
	
	var productnameId = $("#productname").val();
	var yearId = $("#year").val();
	var RTOStateId = $("#RTOState").val();
	var RTOSCityId = $("#RTOSCity").val();
	var vehicleId = $("#vehicle").val();
	var modelId = $("#Model").val();
	var varienceId = $("#varience").val();
	var fuelKitId = $("#fuelKit").val();
	
	var reltype = $('#productname option:selected').attr('reltype');
	var reltype1 = $('#productname option:selected').attr('reltype1');
	var relproductcode = $('#productname option:selected').attr('relproductcode');
	
	getVehicleNo();

	$('#prevProduct').select2('data', {
			 allowClear : true,
		     id: productnameId,
		     text:  $("#productnameTf").val()
		   });
		   
		   prevDtlYear();
		   $('#prevMfrYear').select2('data', {
				 allowClear : true,
			     id: yearId,
			     text:  $("#year option:selected").text()
		   });
		   prevDtlRtoState();
		   $('#prevRtoState').select2('data', {
				 allowClear : true,
			     id: RTOStateId,
			     text:  $("#RTOState option:selected").text()
		   });
		   prevDtlRtoCity();
		   $('#prevRtoCity').select2('data', {
				 allowClear : true,
			     id: RTOSCityId,
			     text:  $("#RTOSCity option:selected").text()
		   });
		   
		   $("#prevProduct").attr("reltype", reltype);
		   $("#prevProduct").attr("reltype1", reltype1);
		   $("#prevProduct").attr("relproductcode", relproductcode);
		
		   prevDtlVehicleType();
		   
		   $('#prevVehicleType').select2('data', {
				 allowClear : true,
			     id: vehicleId,
			     text:  $("#vehicle option:selected").text()
		   });
		   prevDtlModel();
		   $('#prevModel').select2('data', {
				 allowClear : true,
			     id: modelId,
			     text:  $("#Model option:selected").text()
		   });
		   prevDtlVariance();
		   $('#prevVariance').select2('data', {
				 allowClear : true,
			     id: varienceId,
			     text:  $("#varience option:selected").text()
		   });
		   prevDtlFuelKit();
		   $('#prevFuelKit').select2('data', {
				 allowClear : true,
			     id: fuelKitId,
			     text:  $("#fuelKit option:selected").text()
		   });
		   
		   $('#prevDtlRegDate').val($("#dateofReg").val());
		   $("#prevPassanger").val($("#passengers").val());
		   $("#prevCc").val($("#cc").val());
		   $("#prevInsFuelType").val($("#fuelType").val());
		  
		prevDtlPolicyType();
		prevDtlInsuranceCompany();
}

function populateApplicantName() {
	$("#applicantname").empty();
	$("#applicantname").append(
			'<option value="">--Select--');
	var resp = getInsFindFormData("PKG_INS_FIND","find_hbuser","AMCP","","","","","","","","","","","","","","","","","","","","","","","","0","HB EMPLOYEE","","");
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#applicantname").append('<option value="'+resp[key].ID+'">'+ resp[key].HB_USER + '');
	});
}





function month() {
	var applicantid = $("#applicantname").val();
	var productnameid = $("#productname").val();
	var proposalid = $("#proposal").val();
	var yearId = $("#year :selected").text().trim();
	//var propsalType = $('#proposal option:selected').val();
	var data1= $('#prevProduct option:selected').text();
	var data2= $('#prevMfrYear option:selected').text();
	 	$("#prevProduct").empty().append('<option value="">Product'); 
	 	$('#prevProduct').val('').trigger("change");
	 
		$("#month").empty();
		$("#month").append('<option value="">--Select--');
	
		$.ajax({
			url : "getInsFindFormData?pkg_name="+"PKG_INS_FIND"+"&proc_name="+"FIND_MFRMONTH"+"&table_name="+"AMCP"+
			"&str_company_type="+""+"&str_gic="+""+"&str_gicbid="+""+"&str_prod="+productnameid+
			"&str_discnm="+""+"&str_rgrp="+""+"&str_state="+""+"&str_city="+""+
			"&str_prpsl="+proposalid+"&str_spnm="+""+"&str_mgnm="+""+"&str_productcode="+""+
			"&str_type="+""+"&str_type_1="+""+"&str_kg_from="+""+"&str_kg_to="+""+"&str_fueltype="+""+
			"&str_veh="+""+"&str_mod="+""+"&str_var="+""+"&str_policy_age="+""+"&str_hbbid="+""+
			"&str_user_level="+""+"&str_user_id="+"0"+"&str_login_type="+"HB EMPLOYEE"+"&str_gap="+""+"&str_ageto="+""+"&valID="+yearId,
			type : 'post',
			dataType : 'json',
			async : false,
			success : function(resp) {
				$.each(resp,function(key, value) {
					nKey = parseInt(key) + 1;
					$("#month").append(
						'<option value="'+resp[key].MID+'"> '
						+ resp[key].MONTH_MANUFACTURE
						+ '');
				});
				
			},
		});
}

function onCustomerTypeAction() {
	var proposalid = $("#proposal").val();
	
	if(proposalid==1){
		$("#isPrevPolicyDiv").hide(); 
		clearPreviousDetails();
		$("#prevPolicyDetailsDiv").hide();
		 $('.prevpanel').collapse('hide');
		 $('.prevpanelCls').attr('data-toggle', 'collapse');
	}else{
		clearPreviousDetails();
		$("#isPrevPolicyDiv").show(); 
		
	}
	
}

function getProductName() {
	var productnameid = $("#productname").val();
	var proposalid = $("#proposal").val();
	
	var resp = getInsFindFormData("PKG_INS_FIND","find_product","AMCP","","","","","","","","","","","","","","","","","","","","","","","","0","HB EMPLOYEE","","");
	$.each(resp, function(key, value) {
		
		var id=resp[key].PRODUCTID;
		
		if(productnameid==id){
			var name=resp[key].PRODUCTNAME;
			$("#productnameTf").val(name);
		}
		
	});
	
	var resp = getInsFindFormData("PKG_INS_FIND","find_proposal_type","AMCP","","","","","","","","","","","","","","","","","","","","","","","","0","HB EMPLOYEE","","");
	$.each(resp, function(key, value) {
		
		var id=resp[key].PID;
		
		if(proposalid==id){
			var name=resp[key].PROPOSAL_TYPE;
			$("#proposalTf").val(name);
		}
		
	});
	
	old_alert("product=="+$("#productnameTf").val()+" proposal=="+$("#proposalTf").val());
}

function year() {
	
	
	var applicantid = $("#applicantname").val();
	var productnameid = $("#productname").val();
	var proposalid = $("#proposal").val();
	var monthid = $("#month").val();
	console.log("productnameid="+productnameid+" proposalid"+proposalid+" monthid="+monthid);
	
	
		$("#year").empty();
		$("#year").append('<option value="">--Select--');
		var resp = getInsFindFormData("PKG_INS_FIND","find_mfryear","AMCP","","","",productnameid,"","","","",proposalid,"","","","","","","","","","","","","","","0","HB EMPLOYEE","","");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#year").append('<option value="'+resp[key].ID+'"> '
				+ resp[key].YEAR_OF_MANUFACTURE
				+ '');
		});
}

function RTOState() {

	var applicantid = $("#applicantname").val();
	var productnameid = $("#productname").val();
	var proposalid = $("#proposal").val();
	$("#RTOState").empty();
	$("#RTOState").append('<option value="">--Select--');
		var resp = getInsFindFormData("PKG_INS_FIND","find_rtostate","AMCP","","","",productnameid,"","","","",proposalid,"","","","","","","","","","","","","","","0","HB EMPLOYEE","","");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#RTOState").append(
				'<option value="'+resp[key].STATE_ID+'"> '
					+ resp[key].STATE_NAME + '');
		});
}

function RTOCity() {
	var reltype = $('#productname option:selected').attr('reltype');
	var reltype1 = $('#productname option:selected').attr('reltype1');
	var relproductcode = $('#productname option:selected').attr('relproductcode');

	var applicantid = $("#applicantname").val();
	var productnameid = $("#productname").val();
	var proposalid = $("#proposal").val();
	var RTOStateid = $("#RTOState").val();
	$("#RTOSCity").empty();
		$("#RTOSCity").append('<option value="">--Select--');
		var resp = getInsFindFormData("PKG_INS_FIND","find_rtocity","AMCP","","","",productnameid,"","",RTOStateid,"",proposalid,"","",relproductcode,reltype,reltype1,"","","","","","","","","","0","HB EMPLOYEE","","");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#RTOSCity").append(
				'<option relRtoCode="'+resp[key].RTOCODE+'" value="'+resp[key].RTOCITYID+'"> '
					+ resp[key].RTOCITYNAME + '');
	});
}

function zonePopup() {
	
	var reltype = $('#productname').val();
	var reltype1 = $('#productname').val();
	var relproductcode =  $('#productname').val();

	// old_alert("reltype==>"+reltype+" reltype1==>"+reltype1+" relproductcode==>>"+relproductcode);
	var applicantid = $("#applicantname").val();
	var productnameid = $("#productname").val();
	var proposalid = $("#proposal").val();
	var RTOStateid = $("#RTOState").val();
	var RTOSCityid = $("#RTOSCity").val();
	var zone = "";
		
		var resp = getInsFindFormData("PKG_INS_FIND","find_zone","AMCP","","","",productnameid,"","",RTOStateid,RTOSCityid,proposalid,"","",relproductcode,reltype,reltype1,"","","","","","","","","","0","HB EMPLOYEE","","");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#Zone").val(resp[key].ZONE_NAME);
			$("#zoneId").val(resp[key].ZONE_ID);
//			zoneId = resp[key].ZONE_ID;
//				old_alert("zoneid:: "+$("#zoneId").val());	
		});
		
}

function vehicleType() {
	var reltype = $('#productname').val();
	var reltype1 = $('#productname').val();
	var relproductcode = $('#productname').val();

	var applicantid = $("#applicantname").val();
	var productnameid = $("#productname").val();
	// old_alert(productnameid); 
	var proposalid = $("#proposal").val();
	var RTOStateid = $("#RTOState").val();
	var RTOSCityid = $("#RTOSCity").val();

	$("#vehicle").empty();
	$("#vehicle").append('<option value="">--Select--');
		var resp = getInsFindFormData("PKG_INS_FIND","find_vehicle","AMCP","","","",productnameid,"","",RTOStateid,RTOSCityid,proposalid,"","",relproductcode,reltype,reltype1,"","","","","","","","","","0","HB EMPLOYEE","","");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#vehicle").append(
				'<option value="'+resp[key].ID+'"> '
						+ resp[key].VEHICLE_TYPE + '');
		});
}


function variance() {
	var reltype =$('#productname').val();
	var reltype1 = $('#productname').val();
	var relproductcode = $('#productname').val();

	var applicantid = $("#applicantname").val();
	var productnameid = $("#productname").val();
	var proposalid = $("#proposal").val();
	var RTOStateid = $("#RTOState").val();
	var RTOSCityid = $("#RTOSCity").val();
	var vehicleid = $("#vehicle").val();
	var Modelid = $("#Model").val();
		$("#varience").empty();
		$("#varience").append('<option value="">--Select--');
		var resp = getInsFindFormData("PKG_INS_FIND","find_variance","AMCP","","","",productnameid,"","",RTOStateid,RTOSCityid,proposalid,"","",relproductcode,reltype,reltype1,"","","",vehicleid,Modelid,"","","","","0","HB EMPLOYEE","","");
		$.each(resp,function(key, value) {
			nKey = parseInt(key) + 1;
			$("#varience").append(
				'<option relcc="'+resp[key].CCAPACITY+'" relhp="'+resp[key].HP+'" relkg="'+resp[key].VAR_KG+'" relwheel="'+resp[key].VAR_WHEEL+'" relpass="'+resp[key].PASSENGERS+'" relfuel="'+resp[key].FUELTYPE+'" relfuelTypeId="'+resp[key].FUELTYPE_ID+'"  exshowroom="'+resp[key].EXSHOWROOMPRICE+'" value="'+resp[key].VAR_ID+'"> '
					+ resp[key].VAR_NAME+ '');
		});
}

function getValuesFromVarience() {
	var cc = $('#varience option:selected').attr('relcc');
	var hp = $('#varience option:selected').attr('relhp');
	var kg = $('#varience option:selected').attr('relkg');
	var wheel = $('#varience option:selected').attr('relwheel');
	var passenger = $('#varience option:selected').attr('relpass');
	var fuelType = $('#varience option:selected').attr('relfuel');
	var exshowroomprice = $('#varience option:selected').attr('exshowroom');
	var relfuelTypeId = $('#varience option:selected').attr('relfuelTypeId');

	$("#cc").val(cc);
	$("#hp").val(hp);
	$("#kg").val(kg);
	$("#wheels").val(wheel);
	$("#passengers").val(passenger);
	$("#fuelType").val(fuelType);
	$("#exshowroomprice").val(exshowroomprice);
	$("#fuelTypeId").val(relfuelTypeId);
	// 		old_alert("cc::"+cc+":::::hp::"+hp+":::::kg::"+kg+":::::wheel::"+wheel+":::::passenger::"+passenger+":::::fueltype::"+fuelType);
}

function fuelKit() {
	var applicantid = $("#applicantname").val();
	var productnameid = $("#productname").val();
	var proposalid = $("#proposal").val();
	var RTOStateid = $("#RTOState").val();
	var RTOSCityid = $("#RTOSCity").val();
	var vehicleid = $("#vehicle").val();
	var Modelid = $("#Model").val();
		$("#fuelKit").empty();
		$("#fuelKit").append('<option value="">--Select--');
		var resp = getInsFindFormData("PKG_INS_FIND","FIND_FUELKIT","AMCP","","","","","","","","","","","","","","","","","","","","","","","",applicantid,"HB EMPLOYEE","","");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#fuelKit").append(
					'<option value="'+resp[key].FUELKIT_ID+'"> '
						+ resp[key].FUEL_KIT + '');
		});
}

function populateCustomer() {

			$("#customer").empty();
			$("#customer").append('<option value="">--Select--');
			var resp = getRecordList("70","8");
			$.each(resp, function(key, value) {
				nKey = parseInt(key) + 1;
				$("#customer").append(
						'<option value="'+resp[key].DET_ID+'"> '
								+ resp[key].DET_NAME + '');
			});
		
			
}

function showPrevDtl(){
	var checked=$("#prevPolYes").prop("checked");
	if(checked)
	{
		$("#prevPolicyDetailsDiv").show();
		 $('.prevpanel').collapse('show');
		 $('.prevpanelCls').attr('data-toggle', '');
		 prevDtlProduct();
		 prevDtlCustomerType();
		 
		 fillPrevDtlsData();
		 $('#prevChkNmTransInRcBk').attr("disabled","disabled"); 
		 $('#prevDtNmTransInRcBk').attr("disabled","disabled"); 
		 $('#prevChkNmTransInInsPolicy').attr("disabled","disabled"); 
		 $('#prevDtNmTransInInsPolicy').attr("disabled","disabled"); 
	}else{
		$("#prevPolicyDetailsDiv").hide();
		 $('.prevpanel').collapse('hide');
		 $('.prevpanelCls').attr('data-toggle', 'collapse');
	}
	
	/*var proposalid = $("#proposal").val();
	old_alert("showPrevDtl proposalid=="+proposalid);
	//-----Code for prevDtlsPolicy
	if(proposalid==4 || proposalid==5){
		$("#prevPolicyDetailsDiv").show();
		 $('.prevpanel').collapse('show');
		 $('.prevpanelCls').attr('data-toggle', '');
		 prevDtlProduct();
		 prevDtlCustomerType();
		 
		 fillPrevDtlsData();
		 $('#prevChkNmTransInRcBk').attr("disabled","disabled"); 
		 $('#prevDtNmTransInRcBk').attr("disabled","disabled"); 
		 $('#prevChkNmTransInInsPolicy').attr("disabled","disabled"); 
		 $('#prevDtNmTransInInsPolicy').attr("disabled","disabled"); 
	}else{
		$("#prevPolicyDetailsDiv").hide();
		 $('.prevpanel').collapse('hide');
		 $('.prevpanelCls').attr('data-toggle', 'collapse');
	}*/
	
	//-----Code for prevDtlsPolicy
	
}

function model() {
	var reltype = $('#productname').val();
	var reltype1 = $('#productname').val();
	var relproductcode =$('#productname').val();

	var applicantid = $("#applicantname").val();
	var productnameid = $("#productname").val();
	var proposalid = $("#proposal").val();
	var RTOStateid = $("#RTOState").val();
	var RTOSCityid = $("#RTOSCity").val();
	var vehicleid = $("#vehicle").val();
	$("#Model").empty();
		$("#Model").append('<option value="">--Select--');
		var resp = getInsFindFormData("PKG_INS_FIND","find_model","AMCP","","","",productnameid,"","",RTOStateid,RTOSCityid,proposalid,"","",relproductcode,reltype,reltype1,"","","",vehicleid,"","","","","","0","HB EMPLOYEE","","");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#Model").append('<option value="'+resp[key].ID+'"> '
						+ resp[key].MODEL_NAME + '');
		});
}


function insDtlMaritialStatus(){
	
			$("#insDtlMaritialStatus").empty();
			$("#insDtlMaritialStatus").append('<option value="">--Select--');
			var resp = getRecordList("70","25");

			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlMaritialStatus")
					.append(
						'<option value="' + resp[key].DET_ID + '"> ' +
						resp[key].DET_NAME +
						'');
			});
		}
	

function insDtlNationality(){
	
			$("#insDtlNationality").empty();
			$("#insDtlNationality").append('<option value="">--Select--');
			var resp = getRecordList("70","26");
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlNationality")
					.append(
						'<option value="' + resp[key].DET_ID + '"> ' +
						resp[key].DET_NAME +
						'');
			});
		}
	

function insDtlCACountry(){
	        $("#insDtlCACountry").empty();
			$("#insDtlCACountry").append('<option value="">--Select--');
			var resp = getRecordList("95","");
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlCACountry")
					.append(
						'<option value="' + resp[key].COUNTRY_ID + '"> ' +
						resp[key].COUNTRY_NAME +
						'');
			});
	
}
/* CA insDtlCAState  insDtlCADistrict  insDtlCACity  insDtlCAPincode */
function insDtlCAState(){
	var insDtlCACountryId = $("#insDtlCACountry").val();
	        $("#insDtlCAState").empty();
			$("#insDtlCAState").append('<option value="">--Select--');
			var resp = getRecordList("96",insDtlCACountryId);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlCAState")
					.append(
						'<option value="' + resp[key].STATE_ID + '"> ' +
						resp[key].STATE_NAME +
						'');
			});
	}

function insDtlCACity(){
	var insDtlCACountryId = $("#insDtlCACountry").val();
	var insDtlCAStateId = $("#insDtlCAState").val();
	var insDtlCADistrictId = $("#insDtlCADistrict").val();
//	old_alert(insDtlCACountryId+"   "+insDtlCAStateId+"  "+insDtlCADistrictId);
	var total = insDtlCACountryId+"~"+insDtlCAStateId+"~"+null;
	//old_alert("total>>"+total);
			$("#insDtlCACity").empty();
			$("#insDtlCACity").append('<option value="">--Select--');
			var resp = getRecordList("98",total);

			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlCACity")
					.append(
						'<option value="' + resp[key].CITY_ID + '"> ' +
						resp[key].CITY_NAME +
						'');
			});
	}
function insDtlCAPincode(){
	var insDtlCACountryId = $("#insDtlCACountry").val();
	var insDtlCAStateId = $("#insDtlCAState").val();
	var insDtlCADistrictId = $("#insDtlCADistrict").val();
	var insDtlCACityId = $("#insDtlCACity").val();
	//old_alert(insDtlCACountryId+"   "+insDtlCAStateId+"  "+insDtlCADistrictId+"  "+insDtlCACityId);
	var total = insDtlCACountryId+"~"+insDtlCAStateId+"~"+insDtlCADistrictId+"~"+null+","+insDtlCACityId;
	//old_alert("total>>"+total);
			$("#insDtlCAPincode").empty();
			$("#insDtlCAPincode").append('<option value="">--Select--');
			var resp = getRecordList("100",total);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlCAPincode")
					.append(
						'<option  pincodeCA="' + resp[key].PINCODE + '" value="' + resp[key].AREA_ID + '"> ' +
						resp[key].AREA_NAME );
			});
		
}

function insDtlCALandmark() {
	var areaId = $("#insDtlCAPincode").val();
	// old_alert("areaId--->>"+areaId);
	 var total = "1"+"~"+areaId;
	//	old_alert("total>>"+total);
					$("#insDtlCALandmarkList").empty();
					$("#insDtlCALandmarkList").append('<option rel="" value="">--Select--');
					var resp = getRecordList("109",total);
					$.each(resp,function(key, value) {
					 nKey = parseInt(key) + 1;
					 $("#insDtlCALandmarkList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
				/* 	  nomlandmarkId=resp[key].ADD_MST_ID; */
					});
				}
					
		

function insDtlCAStreet() {
	/* console.log($("#insDtlCALandmarkList option[value='" + $('#insDtlCALandmark').val() + "']").attr('rel'));
	old_alert($("#insDtlCALandmarkList option[value='" + $('#insDtlCALandmark').val() + "']").attr('rel')); */
	var landmarkId =($("#insDtlCALandmarkList option[value='" + $('#insDtlCALandmark').val() + "']").attr('rel'));
	if(landmarkId == undefined ||  landmarkId == "")
	{
//		old_alert("landmarkId:::" + landmarkId);
		landmarkId="0";
	}
	var total = "2"+"~"+landmarkId;
	//old_alert("total>>"+total);
			$("#insDtlCAStreetList").empty();
			$("#insDtlCAStreetList").append('<option rel="" value="">--Select--');
			var resp = getRecordList("109",total);
			$.each(resp,function(key, value) {
			nKey = parseInt(key) + 1;
			$("#insDtlCAStreetList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
			var streetId=resp[key].ADD_MST_ID;
		});
			}
	

function insDtlCAHouse(){
var	streetId =($("#insDtlCAStreetList option[value='" + $('#insDtlCAStreet').val() + "']").attr('rel'));
	if(streetId == undefined ||  streetId == "")
	{
//		old_alert("landmarkId:::" + streetId);
		streetId="0";
	} 
	var total = "3"+"~"+streetId;
	//old_alert("total>>"+total);
					$("#insDtlCAHouseList").empty();
					$("#insDtlCAHouseList").append('<option rel="" value="">--Select--');
					var resp = getRecordList("109",total);
					$.each(resp,function(key, value) {
					nKey = parseInt(key) + 1;
					$("#insDtlCAHouseList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
					streetId=resp[key].ADD_MST_ID;
				});
				
				}
			

/* PA insDtlPACountry  insDtlPAState insDtlPADistrict insDtlPACity insDtlPAPincode */
function insDtlPACountry(){
	
			$("#insDtlPACountry").empty();
			$("#insDtlPACountry").append('<option value="">--Select--');
			var resp = getRecordList("95","");

			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlPACountry")
					.append(
						'<option value="' + resp[key].COUNTRY_ID + '"> ' +
						resp[key].COUNTRY_NAME +
						'');
			});
		}
	

function insDtlPAState(){
	var insDtlPACountryId = $("#insDtlPACountry").val();
	
			$("#insDtlPAState").empty();
			$("#insDtlPAState").append('<option value="">--Select--');
			var resp = getRecordList("96",insDtlPACountryId);

			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlPAState")
					.append(
						'<option value="' + resp[key].STATE_ID + '"> ' +
						resp[key].STATE_NAME +
						'');
			});
		}
	
function insDtlPADistrict(){
	var insDtlPACountryId = $("#insDtlPACountry").val();
	var insDtlPAStateId = $("#insDtlPAState").val();
	var total = insDtlPACountryId+"~"+insDtlPAStateId;
	//old_alert("total>>"+total);
			$("#insDtlPADistrict").empty();
			$("#insDtlPADistrict").append('<option value="">--Select--');
			var resp = getRecordList("97",total);

			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlPADistrict")
					.append(
						'<option value="' + resp[key].DISTRICT_ID + '"> ' +
						resp[key].DISTRICT_NAME +
						'');
			});
		}
	
function insDtlPACity(){
	var insDtlPACountryId = $("#insDtlPACountry").val();
	var insDtlPAStateId = $("#insDtlPAState").val();
	var insDtlPADistrictId = $("#insDtlPADistrict").val();
	var total = insDtlPACountryId+"~"+insDtlPAStateId+"~"+insDtlPADistrictId+"~"+null;
	//old_alert("total>>"+total);
	
			$("#insDtlPACity").empty();
			$("#insDtlPACity").append('<option value="">--Select--');
			var resp = getRecordList("98",total);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlPACity").append(
						'<option value="' + resp[key].CITY_ID + '"> ' +
						resp[key].CITY_NAME +
						'');
			});
		}
	
function insDtlPAPincode(){
	var insDtlPACountryId = $("#insDtlPACountry").val();
	var insDtlPAStateId = $("#insDtlPAState").val();
	var insDtlPADistrictId = $("#insDtlPADistrict").val();
	var insDtlPACityId = $("#insDtlPACity").val();
	var total = insDtlPACountryId+"~"+insDtlPAStateId+"~"+insDtlPADistrictId+"~"+null+"~"+insDtlPACityId;
	//old_alert("total>>"+total);
	
			$("#insDtlPAPincode").empty();
			$("#insDtlPAPincode").append('<option value="">--Select--');
			var resp = getRecordList("100",total);

			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlPAPincode")
					.append(
						'<option pincodePA="' + resp[key].PINCODE + '"  value="' + resp[key].AREA_ID + '"> ' +
						resp[key].AREA_NAME +'('+resp[key].PINCODE+')');
			});
		}
	

function insDtlPALandmark() {
	var  areaId = $("#insDtlPAPincode").val();
	// old_alert("areaId--->>"+areaId);
	 var total = "1"+"~"+areaId;
					$("#insDtlPALandmarkList").empty();
					$("#insDtlPALandmarkList").append('<option rel="" value="">--Select--');
					var resp = getRecordList("109",total);
					$.each(resp,function(key, value) {
					 nKey = parseInt(key) + 1;
					 $("#insDtlPALandmarkList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
				/* 	  nomlandmarkId=resp[key].ADD_MST_ID; */
					});
				}
					
			

function insDtlPAStreet() {
	var landmarkId =($("#insDtlPALandmarkList option[value='" + $('#insDtlPALandmark').val() + "']").attr('rel'));
	if(landmarkId == undefined ||  landmarkId == "")
	{
//		old_alert("landmarkId:::" + landmarkId);
		landmarkId="0";
	} 
	var total = "2"+"~"+landmarkId;
			$("#insDtlPAStreetList").empty();
			$("#insDtlPAStreetList").append('<option rel="" value="">--Select--');
			var resp = getRecordList("109",total);
			$.each(resp,function(key, value) {
			nKey = parseInt(key) + 1;
			$("#insDtlPAStreetList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
		var	streetId=resp[key].ADD_MST_ID;
		});
			}
	

function insDtlPAHouse(){
	var streetId =($("#insDtlPAStreetList option[value='" + $('#insDtlPAStreet').val() + "']").attr('rel'));
	if(streetId == undefined ||  streetId == "")
	{
//		old_alert("landmarkId:::" + streetId);
		streetId="0";
	} 
	var total = "3"+"~"+streetId;
//	old_alert("total>>"+total);
					$("#insDtlPAHouseList").empty();
					$("#insDtlPAHouseList").append('<option rel="" value="">--Select--');
					var resp = getRecordList("109",total);
					$.each(resp,function(key, value) {
					nKey = parseInt(key) + 1;
					$("#insDtlPAHouseList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
					streetId=resp[key].ADD_MST_ID;
				});
				
				}
			


/* insDtlOACountry insDtlOAState  insDtlOADistrict  insDtlOACity  insDtlOAPincode */

function insDtlOACountry(){
	
			$("#insDtlOACountry").empty();
			$("#insDtlOACountry").append('<option value="">--Select--');
			var resp = getRecordList("95","");
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlOACountry")
					.append(
						'<option value="' + resp[key].COUNTRY_ID + '"> ' +
						resp[key].COUNTRY_NAME +
						'');
			});
		}
	

function insDtlOAState(){
	var insDtlOACountryId = $("#insDtlOACountry").val();
	
			$("#insDtlOAState").empty();
			$("#insDtlOAState").append('<option value="">--Select--');
			var resp = getRecordList("96",insDtlOACountryId);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlOAState")
					.append(
						'<option value="' + resp[key].STATE_ID + '"> ' +
						resp[key].STATE_NAME +
						'');
			});
			
			
		}
	
function insDtlOADistrict(){
	var insDtlOACountryId = $("#insDtlOACountry").val();
	var insDtlOAStateId = $("#insDtlOAState").val();
	var total = insDtlOACountryId+"~"+insDtlOAStateId;
	//old_alert("total>>"+total);
			$("#insDtlOADistrict").empty();
			$("#insDtlOADistrict").append('<option value="">--Select--');
			var resp = getRecordList("97",total);
			 $.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlOADistrict")
					.append(
						'<option value="' + resp[key].DISTRICT_ID + '"> ' +
						resp[key].DISTRICT_NAME +
						'');
			});
		}
	
function insDtlOACity(){
	var insDtlOACountryId = $("#insDtlOACountry").val();
	var insDtlOAStateId = $("#insDtlOAState").val();
	var insDtlOADistrictId = $("#insDtlOADistrict").val();
	var total = insDtlOACountryId+"~"+insDtlOAStateId+"~"+insDtlOADistrictId+"~"+null;
	//old_alert("total>>"+total);
	
				$("#insDtlOACity").empty();
			$("#insDtlOACity").append('<option value="">--Select--');
			var resp = getRecordList("98",total);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlOACity")
					.append(
						'<option value="' + resp[key].CITY_ID + '"> ' +
						resp[key].CITY_NAME +
						'');
			});
		}
	
function insDtlOAPincode(){
	var insDtlOACountryId = $("#insDtlOACountry").val();
	var insDtlOAStateId = $("#insDtlOAState").val();
	var insDtlOADistrictId = $("#insDtlOADistrict").val();
	var insDtlOACityId = $("#insDtlOACity").val();
	var total = insDtlOACountryId+"~"+insDtlOAStateId+"~"+insDtlOADistrictId+"~"+null+","+insDtlOACityId;
	//old_alert("total>>"+total);
	
			$("#insDtlOAPincode").empty();
			$("#insDtlOAPincode").append('<option value="">--Select--');
			var resp = getRecordList("100",total);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlOAPincode")
					.append(
						'<option value="' + resp[key].AREA_ID + '"> ' +
						resp[key].AREA_NAME +
						'('+resp[key].PINCODE+')');
			});
		}
	

function insDtlOALandmark() {
	 document.getElementById('insDtlOALandmark').value='';
	var areaId = $("#insDtlOAPincode").val();
//	 old_alert("areaId--->>"+areaId);
	 var total = "1"+"~"+areaId;
	//	old_alert("total>>"+total);
					$("#insDtlOALandmarkList").empty();
					$("#insDtlOALandmarkList").append('<option rel="" value="">--Select--');
					var resp = getRecordList("109",total);
					$.each(resp,function(key, value) {
					 nKey = parseInt(key) + 1;
					 $("#insDtlOALandmarkList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
				/* 	  nomlandmarkId=resp[key].ADD_MST_ID; */
					});
				}
					
			

function insDtlOAStreet() {
	document.getElementById('insDtlOAStreet').value='';
	var landmarkId =($("#insDtlOALandmarkList option[value='" + $('#insDtlOALandmark').val() + "']").attr('rel'));
	if(landmarkId == undefined ||  landmarkId == "")
	{
//		old_alert("landmarkId:::" + landmarkId);
		landmarkId="0";
	}
	var total = "2"+"~"+landmarkId;
	//old_alert("total>>"+total);
			$("#insDtlOAStreetList").empty();
			$("#insDtlOAStreetList").append('<option rel="" value="">--Select--');
			var resp = getRecordList("109",total);
			$.each(resp,function(key, value) {
			nKey = parseInt(key) + 1;
			$("#insDtlOAStreetList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
		var	streetId=resp[key].ADD_MST_ID;
		});
			
		}
	

function insDtlOAHouse(){
	document.getElementById('insDtlOAHouse').value='';
	var streetId =($("#insDtlOAStreetList option[value='" + $('#insDtlOAStreet').val() + "']").attr('rel'));
	if(streetId == undefined ||  streetId == "")
	{
//		old_alert("landmarkId:::" + streetId);
		streetId="0";
	} 
	var total = "3"+"~"+streetId;
//	old_alert("total>>"+total);
					$("#insDtlOAHouseList").empty();
					$("#insDtlOAHouseList").append('<option rel="" value="">--Select--');
					var resp = getRecordList("109",total);
					$.each(resp,function(key, value) {
					nKey = parseInt(key) + 1;
					$("#insDtlOAHouseList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
					streetId=resp[key].ADD_MST_ID;
				});
			
}

/* insured Details method Ends Here */

/*Previous Policy Methods Starts here   */

function prevDtlProduct() {
var applicantid = $("#applicantname").val();


			$("#prevProduct").empty();
			$("#prevProduct").append('<option value="">--Select--');
			var resp = getInsFindFormData("PKG_INS_FIND","find_product","AMCP","","","","","","","","","","","","","","","","","","","","","","","",applicantid,"HB EMPLOYEE","","");
			$.each(resp,function (key, value) {
						nKey = parseInt(key) + 1;
						$("#prevProduct")
							.append(
								'<option reltype="' + resp[key].TYPE + '"reltype1="' + resp[key].TYPE1 + '"relproductcode="' + resp[key].PRODUCTCODE + '"value="' + resp[key].PRODUCTID + '"> ' +
								resp[key].PRODUCTNAME +
								'');
					});
		}


function prevDtlYear() {
	var applicantid = $("#applicantname").val();
	var prevProductNameId = $("#prevProduct").val();

	
	
				$("#prevMfrYear").empty();
				$("#prevMfrYear").append('<option value="">--Select--');
				var resp = getInsFindFormData("PKG_INS_FIND","find_mfryear","PREV_INS","","","",prevProductNameId,"","","","","","","","","","","","","","","","","","","","","","","");
				$.each(resp, function (key, value) {
					nKey = parseInt(key) + 1;
					$("#prevMfrYear").append(
						'<option value="' + resp[key].ID + '"> ' +
						resp[key].YEAR_OF_MANUFACTURE +
						'');
				});
			}
		

function prevDtlRtoState() {
	var applicantid = $("#applicantname").val();
	var prevProductNameId = $("#prevProduct").val();
	var prevMfrYearId = $("#prevMfrYear").val();

	
	
				$("#prevRtoState").empty();
				$("#prevRtoState").append('<option value="">--Select--');
				var resp = getInsFindFormData("PKG_INS_FIND","find_rtostate","PREV_INS","","","",prevProductNameId,"","","","","","","","","","","","","","","","","","","","","","","");
				$.each(resp, function (key, value) {
					nKey = parseInt(key) + 1;
					$("#prevRtoState").append(
						'<option value="' + resp[key].STATE_ID + '"> ' +
						resp[key].STATE_NAME + '');
				});
			}
		

function prevDtlRtoCity() {
	var prevType = $('#prevProduct option:selected').attr('reltype');
	var prevType1 = $('#prevProduct option:selected').attr('reltype1');
	var prevProductcode = $('#prevProduct option:selected').attr('relproductcode');

	var prevProductNameId = $("#prevProduct").val();
	var prevRtoStateId = $("#prevRtoState").val();
    
				$("#prevRtoCity").empty();
				$("#prevRtoCity").append('<option value="">--Select--');
				var resp = getInsFindFormData("PKG_INS_FIND","find_rtocity","PREV_INS","","","",prevProductNameId,"","",prevRtoStateId,"","",prevRtoStateId,"","","","",prevProductcode,prevType,prevType1,"","","","","","","","","","","","","");
				$.each(resp, function (key, value) {
					nKey = parseInt(key) + 1;
					$("#prevRtoCity").append(
						'<option relRtoCode="' + resp[key].RTOCODE + '" value="' + resp[key].RTOCITYID + '"> ' +
						resp[key].RTOCITYNAME + '');
				});
}
		

function prevDtlVehicleType() {
	var prevType = $('#prevProduct option:selected').attr('reltype');
	var prevType1 = $('#prevProduct option:selected').attr('reltype1');
	var prevProductcode = $('#prevProduct option:selected').attr('relproductcode');

	var prevProductNameId = $("#prevProduct").val();
	var prevRtoStateId = $("#prevRtoState").val();
	var prevRtoCityId = $("#prevRtoCity").val();

	
	
				$("#prevVehicleType").empty();
				$("#prevVehicleType").append('<option value="">--Select--');
				var resp = getInsFindFormData("PKG_INS_FIND","find_vehicle","PREV_INS","","","",prevProductNameId,"","","",prevRtoCityId,"","","",prevProductcode,prevType,prevType1,"","","","","","","","","","","","","");
				$.each(resp, function (key, value) {
					nKey = parseInt(key) + 1;
					$("#prevVehicleType").append(
						'<option value="' + resp[key].ID + '"> ' +
						resp[key].VEHICLE_TYPE + '');
				});

			}
		


function prevDtlModel() {
	var prevType = $('#prevProduct option:selected').attr('reltype');
	var prevType1 = $('#prevProduct option:selected').attr('reltype1');
	var prevProductcode = $('#prevProduct option:selected').attr('relproductcode');
	var prevProductNameId = $("#prevProduct").val();
	var prevRtoStateId = $("#prevRtoState").val();
	var prevRtoCityId = $("#prevRtoCity").val();
	var prevVehicleTypeId = $("#prevVehicleType").val();

				$("#prevModel").empty();
				$("#prevModel").append('<option value="">--Select--');
				var resp = getInsFindFormData("PKG_INS_FIND","find_model","PREV_INS","","","",prevProductNameId,"","","",prevRtoCityId,"","","",prevProductcode,prevType,prevType1,"","","",prevVehicleTypeId,"","","","","","","","","");
				$.each(resp, function (key, value) {
					nKey = parseInt(key) + 1;
					$("#prevModel").append(
						'<option value="' + resp[key].ID + '"> ' +
						resp[key].MODEL_NAME + '');
				});
			}
	

function prevDtlVariance() {
	var prevType = $('#prevProduct option:selected').attr('reltype');
	var prevType1 = $('#prevProduct option:selected').attr('reltype1');
	var prevProductcode = $('#prevProduct option:selected').attr('relproductcode');
	var prevProductNameId = $("#prevProduct").val();
	var prevRtoStateId = $("#prevRtoState").val();
	var prevRtoCityId = $("#prevRtoCity").val();
	var prevVehicleTypeId = $("#prevVehicleType").val();
	var prevModelId = $("#prevModel").val();
				$("#prevVariance").empty();
				$("#prevVariance").append('<option value="">--Select--');
				var resp = getInsFindFormData("PKG_INS_FIND","find_variance","PREV_INS","","","",prevProductNameId,"","","",prevRtoCityId,"","","",prevProductcode,prevType,prevType1,"","","",prevVehicleTypeId,prevModelId,"","","","","","","","");
				$.each(resp, function (key, value) {
					nKey = parseInt(key) + 1;
					$("#prevVariance")
						.append(
							'<option relcc="' + resp[key].CCAPACITY + '" relhp="' + resp[key].HP + '" relkg="' + resp[key].VAR_KG + '" relwheel="' + resp[key].VAR_WHEEL + '" relpass="' + resp[key].PASSENGERS + '" relfuel="' + resp[key].FUELTYPE + '" relfuelTypeId="' + resp[key].FUELTYPE_ID + '"  exshowroom="' + resp[key].EXSHOWROOMPRICE + '" value="' + resp[key].VAR_ID + '"> ' +
							resp[key].VAR_NAME +
							'');

				});
}
		


function getPrevDtlValuesFromVarience() {
	var cc = $('#prevVariance option:selected').attr('relcc');
	var passenger = $('#prevVariance option:selected').attr('relpass');


	$("#prevCc").val(cc);
	$("#prevPassanger").val(passenger);

}

function prevDtlPolicyType() {
	
			$("#prevPolicyType").empty();
			$("#prevPolicyType").append('<option value="">--Select--');
			var resp = getRecordList("145","");
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#prevPolicyType")
					.append(
						'<option value="' + resp[key].ID + '"> ' +
						resp[key].POLICY_TYPE +
						'');
			});
		}
	

function prevDtlInsuranceCompany() {
			$("#prevInsuranceCompany").empty();
			$("#prevInsuranceCompany").append('<option value="">--Select--');
			var resp = getInsFindFormData("PKG_INS_FIND","find_gic","CALC","","","","","","","","","","","","0","0","0","0","0","","","","","","","0","0","0","0","0");
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#prevInsuranceCompany")
					.append(
						'<option value="' + resp[key].GIC_ID + '"> ' +
						resp[key].GIC_NAME +
						'');
			});
		}

function prevDtlFuelKit() {
			$("#prevFuelKit").empty();
			$("#prevFuelKit").append('<option value="">--Select--');
			var resp = getInsFindFormData("PKG_INS_FIND","FIND_FUELKIT","MCP","","","","","","","","","","","","","","","","","","","","","","","","","","","");
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#prevFuelKit")
					.append(
						'<option value="' + resp[key].FUELKIT_ID + '"> ' +
						resp[key].FUEL_KIT +
						'');
			});
		}
	
function prevDtlCustomerType() {
	
			$("#prevCustomerType").empty();
			$("#prevCustomerType").append('<option value="">--Select--');
			var resp = getRecordList("70","8");
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#prevCustomerType")
					.append(
						'<option value="' + resp[key].DET_ID + '"> ' +
						resp[key].DET_NAME +
						'');
			});
		}
	
	/*Prevous Policy Methods Ends here   */


function forOtherDetailsData(age) {

	
					var total = age+","+age;
					console.log("forOtherDetailsData total==>>"+total);
					var resp = getRecordList("165",total);
					$.each(resp, function(key, value) {
						//	old_alert("Hello " +resp[key].DEPR);
						nKey = parseInt(key) + 1;
						$("#depr").val(resp[key].DEPR);
						console.log("forOtherDetailsData depr==>>"+resp[key].DEPR);
						 
					});
				getIDV();
}

function getIDV() {
	var exshowroomprice = $('#varience option:selected').attr('exshowroom');
	var depriciation = $("#depr").val();
	
	  console.log("depriciation" +depriciation);
	var basicIdv = exshowroomprice - (exshowroomprice * depriciation / 100);
	  console.log("basicIdv" +basicIdv);
	var depriciationValue = (exshowroomprice * depriciation / 100);

	$("#totalIDV").val(basicIdv);
	$("#basicIdv").val(basicIdv);

	//onBasicIdvAction();

}

function onBasicIdvAction(){
	var depriciation_lmt=0,basicIdvValidationAmt="",lessBasicIdvVal=0,abvBasicIdvVal=0;
	
	var resp = getInsFindFormData("PKG_INS_FIND","FIND_DEPRLIMIT","MCP","","","",$("#productname").val(),"","","","","","","","","","","","","","","","","","","","0","HB EMPLOYEE","","");
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		depriciation_lmt=resp[key].DEPR_LIMIT;
	});
	
	 var ex_showroom_price=$("#exshowroomprice").val();
	 
     //int ex_showroom_price = Integer.parseInt(tf_ex_showroom_price.getText());
     basicIdvValidationAmt = ex_showroom_price - (ex_showroom_price * depriciation_lmt / 100);
     var tf_basicIdv= $("#basicIdv").val().replace(",", "");
     var value_BIdv = tf_basicIdv;
     lessBasicIdvVal = value_BIdv - basicIdvValidationAmt;
     abvBasicIdvVal = value_BIdv + basicIdvValidationAmt;
     console.log("basicIdvValidationAmt-->"+basicIdvValidationAmt);
     console.log("value_BIdv-->"+value_BIdv);
     console.log("less-------------->>" + lessBasicIdvVal);
     console.log("abv-------------->>" + abvBasicIdvVal);
}

function getBodyType() {
	
			$("#bodyType").empty();
			$("#bodyType").append('<option value="">--Select--');
			var resp = getRecordList("70","3");
			$.each(resp, function(key, value) {
				nKey = parseInt(key) + 1;
				$("#bodyType").append(
						'<option value="'+resp[key].DET_ID+'"> '
								+ resp[key].DET_NAME + '');
			});
		}
	

//Starts methods of nomineeDeails
function nomDtlInitial() {

	$("#nomDtlInitial").empty();
	$("#nomDtlInitial").append('<option value="">--Select--');
	var resp = getRecordList("70","4");
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#nomDtlInitial").append('<option value="'+resp[key].DET_ID+'"> '+ resp[key].DET_NAME + '');
	});
}


function nomDtlRelation() {

	$("#nomDtlRelation").empty();
	$("#nomDtlRelation").append('<option value="">--Select--');
	var resp = getRecordList("70","5");
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#nomDtlRelation").append('<option value="'+resp[key].DET_ID+'"> '+ resp[key].DET_NAME + '');
	});
}

function nomDtlCountry() {

	$("#nomDtlCountry").empty();
	$("#nomDtlCountry").append('<option value="">--Select--');
	var resp = getRecordList("95","");
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#nomDtlCountry").append('<option value="'+resp[key].COUNTRY_ID+'"> '+ resp[key].COUNTRY_NAME + '');
	});
}


function nomDtlState() {
var country = $("#nomDtlCountry").val();


$("#nomDtlState").empty();
$("#nomDtlState").append('<option value="">--Select--');
var resp = getRecordList("96",country);
$.each(resp, function(key, value) {
	nKey = parseInt(key) + 1;
	$("#nomDtlState").append('<option value="'+resp[key].STATE_ID+'"> '+ resp[key].STATE_NAME + '');
});
}

function nomDtlDistrict() {
var country = $("#nomDtlCountry").val();
var proposerState = $("#nomDtlState").val();

	var total = country +","+proposerState;
	//old_alert("total>>>>>"+total);
	$("#nomDtlDistrict").empty();
	$("#nomDtlDistrict").append('<option value="">--Select--');
	var resp = getRecordList("97",total);
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#nomDtlDistrict").append('<option value="'+resp[key].DISTRICT_ID+'"> '+ resp[key].DISTRICT_NAME + '');
	});
}

function nomDtlCity() {
var country = $("#nomDtlCountry").val();
var proposerState = $("#nomDtlState").val();
var districtSelect = $("#nomDtlDistrict").val();

	var total = country +"~"+proposerState+"~"+districtSelect;
	//old_alert("total>>>>>"+total);
	$("#nomDtlCity").empty();
	$("#nomDtlCity").append('<option value="">--Select--');
	var resp = getRecordList("98",total);
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#nomDtlCity").append('<option value="'+resp[key].CITY_ID+'"> '+ resp[key].CITY_NAME + '');
	});
}


function nomDtlPincode() {
var country = $("#nomDtlCountry").val();
var proposerState = $("#nomDtlState").val();
var districtSelect = $("#nomDtlDistrict").val();
var proposerCity = $("#nomDtlCity").val();

	var total = country +"~"+proposerState+"~"+districtSelect+"~"+null+"~"+proposerCity;
	//old_alert("total>>>>>"+total);
	$("#nomDtlPincode").empty();
	$("#nomDtlPincode").append('<option value="">--Select--');
	var resp = getRecordList("100",total);
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#nomDtlPincode").append(
				'<option  nomDtlpincode="' + resp[key].PINCODE + '" value="' + resp[key].AREA_ID + '"> ' +
				resp[key].AREA_NAME );
	});
}

function nomDtlLandmark() {
var areaId = $("#nomDtlPincode").val();
//old_alert("areaId--->>"+areaId);

			var total = "1"+"~"+areaId;
			//old_alert("total>>>>>"+total);
			$("#nomDtlLandmarkList").empty();
			$("#nomDtlLandmarkList").append('<option rel="" value="">--Select--');
			var resp = getRecordList("109",total);
			$.each(resp,function(key, value) {
			 nKey = parseInt(key) + 1;
			 $("#nomDtlLandmarkList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
			});
		}
			
	

function nomDtlStreet() {
var landmarkId =($("#nomDtlLandmarkList option[value='" + $('#nomDtlLandmark').val() + "']").attr('rel'));
if(landmarkId == undefined || landmarkId ==""  )
	{
		landmarkId = "0";
	}
	var total = "2"+"~"+landmarkId;
	//old_alert("total>>>>>"+total);
	$("#nomDtlStreetList").empty();
	$("#nomDtlStreetList").append('<option rel="" value="">--Select--');
	var resp = getRecordList("109",total);
	$.each(resp,function(key, value) {
	nKey = parseInt(key) + 1;
	$("#nomDtlStreetList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
	var streetId=resp[key].ADD_MST_ID;
});
	
}


function nomDtlHouseNo(){
var streetId =($("#nomDtlStreetList option[value='" + $('#nomDtlStreet').val() + "']").attr('rel'));
if(streetId == undefined || streetId ==""  )
{
	streetId = "0";
}
var total = "3"+"~"+streetId;
//old_alert("total>>>>>"+total);

			$("#nomDtlHouseNoList").empty();
			$("#nomDtlHouseNoList").append('<option rel="" value="">--Select--');
			var resp = getRecordList("109",total);
			$.each(resp,function(key, value) {
			nKey = parseInt(key) + 1;
			$("#nomDtlHouseNoList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
			streetId=resp[key].ADD_MST_ID;
		});
		
		}
//Ends methods of nomineeDeails

function clearOtherDetails()
{
	$('#bodyType').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });

	 $('#vehRegNo1,#vehRegNo2,#vehRegNo3,#vehRegNo4,#engineNo,#chasisNo,#basicIdv,#totalIDV').val("");
	 $("#riskStartDt").attr("value", "");
}

function clearProductDetails(){
	 $('#year,#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience,#fuelType,#fuelKit,#customer').select2('data', {
		 allowClear : true,
		 id: null,
		 text: '--Select--'
	 });
	 $('#dateofReg').val("");
	 $('#passengers,#Zone,#hp,#wheels,#cc,#kg,#exshowroomprice,#mobileNo,#emailId,#fuelType').val("");

	
}

function resetProposalDetails()
{
	
	refreshTablePremium();
	refreshTableProposal();
	refreshTablePaymentDetails();
	refreshTablePaymentStatus();
	clearNomineeDetails();
	clearInsuredDetails();
	clearVehicleFinDtls();
	clearInspectionDetails();
	$("#paymentDetailsDiv").hide();
	$("#proposalDetailsDiv").hide();
	$("#VehicleFinDtlDiv").hide();  
	$("#backPapersDiv").hide();
	$("#inspectionDtlDiv").hide(); 
	$("#insuredBankDtlDiv").hide(); 
	$("#nomineeDtlDiv").hide();
    $("#insuredDtlDiv").hide();  
    $("#companyDtlDiv").hide();
	$("#getPremiumBtnDiv").hide(); 
	$("#paymentOptionsDiv").hide();  
	$("#cardPay").hide(); 
	$("#onlinePay").hide(); 
	$("#relExtPay").hide(); 
	$("#cdtBal").hide();
	$("#remainingBal").hide();  
	$("#creditcardPay").hide();  
}
function clearAllFields()
{
	//refreshTable();
	refreshTablePremium();
	refreshTableProposal();
	refreshTablePaymentDetails();
	refreshTablePaymentStatus();
	clearNomineeDetails();
	clearInsuredDetails();
	clearVehicleFinDtls();
	clearInspectionDetails();
	//clearPreviousDetails();
	clearAAI();
	clearAccidentalData();
	clearOtherDetails();
	//clearContactDetails();
	$("#otherDetailsDiv").hide();
	$("#coversDiv").hide();
	$("#paymentDetailsDiv").hide();
	$("#proposalDetailsDiv").hide();
	$("#VehicleFinDtlDiv").hide();  
	$("#backPapersDiv").hide();
	$("#inspectionDtlDiv").hide(); 
	$("#insuredBankDtlDiv").hide(); 
	$("#nomineeDtlDiv").hide();
    $("#insuredDtlDiv").hide();  
    $("#companyDtlDiv").hide();
	$("#getPremiumBtnDiv").hide(); 
	$("#paymentOptionsDiv").hide();  
	$("#cardPay").hide(); 
	$("#onlinePay").hide(); 
	$("#relExtPay").hide(); 
	$("#cdtBal").hide();
	$("#remainingBal").hide();  
	$("#creditcardPay").hide();  
}

function clearContactDetails()
{
	 $('#emailId').val(""); 
	 $('#mobileNo').val(""); 
	
}


function showCompanies() {
	clearAllFields();
		
	var stat = emptyTFValidationForProductDetails();
	if(stat){
		$('#showCompanyBtn').prop('disabled', true);
		var checked=$("#prevPolYes").prop("checked");
		
		//var proposal = $("#proposal option:selected").text();
		var proposalId = $("#proposal").val();
		console.log("proposalId:::" +proposalId);
		//old_alert("proposalId:::" +proposalId);
		
		var age ="";
		if (proposalId == 1 || (proposalId == 6 && checked==false)) {
			var date = new Date();
			console.log(date); 

			var nextDay = new Date(date);
			nextDay.setDate(date.getDate()+1);
			console.log(nextDay); 
			var day1 = nextDay.getDate();
			var month = nextDay.getMonth() + 1;
					var year = nextDay.getFullYear();
					if (month < 10)
						month = "0" + month;
					if (day1 < 10)
						day1 = "0" + day1;
					
		var next_day_currntDate =day1 + "/" +month  + "/" +  year ;
		console.log(next_day_currntDate);
					
		$('#riskStartDt').val(next_day_currntDate);
		console.log($("#riskStartDt").val());
		var dateoRegistration = $('#dateofReg').val();

		 console.log(dateoRegistration);
		 next_day_currntDate = month  + "/" + day1 + "/" +  year ;


		var day = date.getDate();
		var month = date.getMonth() + 1;
		var year = date.getFullYear();
		if (month < 10)
			month = "0" + month;
		if (day < 10)
			day = "0" + day;

		var currntDate = day + "-" + month + "-" + year;
		 var regDate = dateoRegistration.split("/");
		 
	        var regDay = regDate[0];
			var regMonth = regDate[1];
			var regYr = regDate[2];
		var dateOfReg=  regMonth + "/" + regDay + "/" + regYr;
		
		var dateReg = new Date(dateOfReg);
		var nextDayCurrntDate = new Date(next_day_currntDate);
		var timeDiff = Math.abs(dateReg.getTime()
				- nextDayCurrntDate.getTime());
		var calcDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
		var newStartDate = next_day_currntDate;
		
		if (proposalId == 1 ) {
			 $('#gap').val(calcDiff);
			
		} else if (proposalId == 6) {
			 $('#gap').val(-1);
		}
		var calcAge = calcDiff; 
		 age = calcAge / 30;
//		old_alert(age);
		 executeCoversMethod(age);
		 checkSession();
		
	}
	else if((proposalId == 6 && checked==true)){
		
		if(emptyTFValidationForPrevPolDetails()){
			var prevDate = $("#prevToDate").val().split("/");
	        var regDay = prevDate[0] ;
			var regMonth = prevDate[1];
			var regYr = prevDate[2];
			
			var prevDate = regMonth + "/" + regDay + "/" + regYr;
//			old_alert("prevDate::" +prevDate)
			var prevDatenew = new Date(prevDate);
			var prevDay = prevDatenew.getDate() + 1;
			var prevMonth = prevDatenew.getMonth()+1 ;
			var prevYear = prevDatenew.getFullYear() ;
			
			var next_day_prevInsToDate = prevMonth + "/" + prevDay + "/" + prevYear;
			
			
			var nextDayToprev = regMonth + "/" + regDay + "/" + regYr;
			var nextDayPrev = new Date(nextDayToprev);
//			old_alert("date1 " +nextDayPrev)
		
			
			var date = new Date();
			var day = date.getDate();
			var month = date.getMonth() + 1;
			var year = date.getFullYear();
			if (month < 10)
				month = "0" + month;
			if (day < 10)
				day = "0" + day;
			var currntDate = month  + "/" + day + "/" + year;
			var newInsStartDate="";
			var compareDate= new Date(next_day_prevInsToDate) < new Date(currntDate);
			if (compareDate){
//		  		old_alert("date1 is greater than date2");
		  		newInsStartDate = new Date(currntDate);
		  		
		  	}
			else{
				newInsStartDate = new Date(next_day_prevInsToDate); 
			
		        }
			
			var newInsStartDate1 = new Date(newInsStartDate);
				var nextDayToprev1 = new Date(nextDayToprev);
				var timeDiff = Math.abs(nextDayToprev1.getTime()
						- newInsStartDate1.getTime());
				var calcDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
			
			 $('#gap').val(calcDiff);
			 console.log("currntDate::" + $('#gap').val());
			 var dateoRegistration = $('#dateofReg').val();
			 var regDate = dateoRegistration.split("/");
			 
		        var regDay = regDate[0];
				var regMonth = regDate[1];
				var regYr = regDate[2];
			var dateOfReg=  regMonth + "/" + regDay + "/" + regYr;
			var dateReg = new Date(dateOfReg);
			
			var timeDiff = Math.abs(dateReg.getTime()
					- newInsStartDate1.getTime());
			var calcDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
			 
			var calcAge = calcDiff; 
			 console.log("calcAge::" +calcAge);
	        age = calcAge / 30;
	      
	        console.log("age::" +age);
//	        old_alert("age::" +age);
	        executeCoversMethod(age);
	        
	        checkSession();
		}
		
		}
		
	}
	
}

function coverDetails()
{
	saveMotorGroupResponse();
	var policyType = $("#policyTypeId").val() ;
	$("#coversDiv").show();
	
	if(policyType == "1"){
		
	$('#packageDetails td').remove();
	$('#liabilityDetails td').remove();	
	$('#additionalcover td').remove();
	
	showPackDetails();
	showLibilityDetails();
	additionalCoverage();
	}else{
		showLibilityDetails();
	}
	
//	getPlanTypesHdfc();
	getIDVHdfcResponse();
	getRelianceCoverageResponse();
//		getPremiumResponseForShriRam();
	// hideWait()
		
		
	/*	$("#companyDtlDiv").show();*/
		hitCompanyPremiumRequest();
		$("#getPremiumBtnDiv").show();
		
}



function executeCoversMethod(age){
	
	 var proposalId = $("#proposal").val();
	 getVehicleNo();
//	var proposal = $("#proposal option:selected").text();
	if (proposalId == 1 ) {
		$("#newVehicle").val("true") ; 
		$("#regNo").val("NEW") ; 
		$("#regNo1").val("NEW") ; 
		$("#regNo2").val() ; 
		$("#regNo3").val() ; 
		$("#regNo4").val() ; 
	  } else {
		  
		  $("#newVehicle").val("false");
	      var finalRegNo =$("#vehRegNo1").val() + "-" + $("#vehRegNo2").val() + "-" + $("#vehRegNo3").val() + "-" +$("#vehRegNo4").val();
	  		$("#regNo").val(finalRegNo) ; 
//	  		old_alert("reg no is::" +$("#regNo").val())
	  		$("#regNo1").val($("#vehRegNo1").val()) ; 
			$("#regNo2").val($("#vehRegNo2").val()) ; 
			$("#regNo3").val($("#vehRegNo3").val()) ; 
			$("#regNo4").val($("#vehRegNo4").val()) ; 
	  }
	
	 $("#customerType").val($('#customer option:selected').text());
	 var customerType=$("#customerType").val();
	 	if(customerType.localeCompare("INDIVIDUAL"))
		{ 
	 		 $("#custType").val("I");
	 		 $("#custTypeIdRel").val("0");
			
		}else{
			$("#custType").val("C");
	 		 $("#custTypeIdRel").val("1");
		}
	var custMobileno = $('#mobileNo').val()
	var custEmailId = $('#emailId').val()
	
	 $("#insDtlMobileNo").val(custMobileno);
	$("#insDtlEmail").val(custEmailId);
	 	
	//refreshTable();
	getEntryDate();
	getBodyType();
	$("#otherDetailsDiv").show();
	//showWait();
	forOtherDetailsData(age);

}
//////////Insured Bank Details functions starts here////////////////

function getBankDtlBankName(){
	
	$("#insBankDtlBankName").empty();
	$("#insBankDtlBankName").append('<option value="">--Select--');
	var resp = getRecordList("79",null);
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#insBankDtlBankName").append('<option value="'+resp[key].FIN_MASTER_ID+'"> '+ resp[key].FIN_NAME + '');
	});
	
}
function getBankDtlBranchName(){
	var insBankDtlBankId = $("#insBankDtlBankName").val();
	var param= "2" + "~" + "1" + "~" + insBankDtlBankId + "~" + "null" + "~" + "null" + "~" + "null" + "";
     
	$("#insBankDtlBranchName").empty();
	$("#insBankDtlBranchName").append('<option value="">--Select--');
	var resp = getRecordList("164",param);
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#insBankDtlBranchName").append('<option value="'+resp[key].FIN_MASTER_ID+'"> '+ resp[key].FINANCE_NAME + '');
	});
	
}

//////////Insured Bank Details functions ends here  ////////////////

//////////Vehicle Finance Details functions Starts here  ////////////////

function getFinanceType()
{
	$("#finDtlFinType").empty();
	$("#finDtlFinType").append('<option value="">--Select--');
	var resp = getRecordList("70","19");
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#finDtlFinType").append('<option value="'+resp[key].DET_ID +'"> '+ resp[key].DET_NAME  + '');
	});
	}

function getFinanceBy()
{
	$("#finDtlFinBy").empty();
	$("#finDtlFinBy").append('<option value="">--Select--');
	var resp = getRecordList("162",null);
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#finDtlFinBy").append('<option value="'+resp[key].ID +'"> '+ resp[key].FINANCE_TYPE + '');
	});
}

function getFinanceName()
{

	$("#finDtlFinName").empty();
	$("#finDtlFinName").append('<option value="">--Select--');
	var resp = getRecordList("163",null);
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#finDtlFinName").append('<option value="'+resp[key].FIN_MASTER_ID +'"> '+ resp[key].FINANCE_NAME  + '');
	});
}

function getFinanceState()
{
	
	$("#finDtlFinState").empty();
	$("#finDtlFinState").append('<option value="">--Select--');
	var resp = getRecordList("96",null);
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#finDtlFinState").append('<option value="'+resp[key].STATE_ID +'"> '+ resp[key].STATE_NAME  + '');
	});
}
function getFinanceCity()
{
    var param = null +"~"+$("#finDtlFinState").val()+"~"+"null"+"~"+"null";
	$("#finDtlFinCity").empty();
	$("#finDtlFinCity").append('<option value="">--Select--');
	var resp = getRecordList("98",param);
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#finDtlFinCity").append('<option value="'+resp[key].CITY_ID +'"> '+ resp[key].CITY_NAME  + '');
	});
}
//////////Vehicle Finance Details functions ends here  ////////////////



function getEntryDate(){
	var entryDate = new Date();
	var dd = entryDate.getDate();
	var mm = entryDate.getMonth()+1; //January is 0!
	var yyyy = entryDate.getFullYear();

	if(dd<10) {
	    dd = '0'+dd
	} 

	if(mm<10) {
	    mm = '0'+mm
	} 

	$("#entryDate").val(dd + '/' + mm + '/' + yyyy);
	

}


function saveMotorGroupResponse(){
	showWait();
	var productnameid = $("#productname").val();
	var proposalid = $("#proposal").val();
	var RTOSCityid = $("#RTOSCity").val();
	var manfYr= $("#year").val();
	var varianceId = $("#varience").val();
	var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
	   
	$.ajax({
		'url' : "IntegrationSaveResponse?groupId=0&sessionId=&proposalType="
				+ proposalid +
				"&policyType=0&product="+productnameid+"&rtoCity="+RTOSCityid+
				"&mfrYear=" +manfYr+
				"&variance=" + varianceId
				+"&fuelType=" +fuelTypeId
				+"&userId=0&branchId=0&userDesc=WEB",
				
		'method' : 'post',
		'dataType' : 'json',
		'async' : false,
		/*beforeSend: function () {
			//showWait();
			},
			complete: function () {
			hideWait()
			},*/
		 success : function(resp) {
			 hideWait()
			
			 $.each(resp, function(key, value) {
				// old_alert(resp[key].groupId);
				 $("#groupID").val(resp[key].groupId);
				 $("#sessionId").val(resp[key].sessionId) ;
				// old_alert(groupID);
				// old_alert(sessionId);
				});
		},
		error:function(resp) {
			 hideWait()
//			old_alert("hi");
			 
		},
	});
	
}

function  findGicCompany(companyDtl_companyId){
	 $.ajax({
			'url' : "getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=find_gic&table_name=RGCP"
					+"&str_company_type=&str_gic=&str_gicbid=&str_prod=&str_discnm=&str_rgrp=&str_state="
					+"&str_city=&str_prpsl"
					+"&str_spnm=&str_mgnm=&str_productcode=0&str_type=0"
					+"&str_type_1=0&str_kg_from=0&str_kg_to=0&str_fueltype="
					+"&str_veh=&str_mod=&str_var=&str_policy_age="
					+"&str_hbbid=&str_user_level=0&str_user_id=0&str_login_type=0"
					+"&str_gap=0&str_ageto=0",
					
			'method' : 'post',
			'dataType' : 'json',
			'async' : false,
			 success : function(resp) {
				/*  old_alert("hi success ");
				 old_alert(resp); */
				 $.each(resp, function(key, value) {
					var gicName=resp[key].GIC_NAME;
					var gicId=resp[key].GIC_ID;
						if(gicId == companyDtl_companyId)
						{
							 old_alert(gicName);
							$("#companyDtl_companyId").val(gicId);
							$("#companyName").val(gicName);
						}
					});
			},
			error:function(resp) {
//				old_alert("hi");
			},
		});   
	//old_alert("companyName:::" +companyName);
}




function refreshTablePremium() {
	$('#companyDetails td').remove();

}
function refreshTableProposal() {
	$('#proposalDetails td').remove();
}
function refreshTablePaymentDetails() {
	$('#paymentDetails td').remove();
}
function refreshTablePaymentStatus() {
	$('#paymentStatus td').remove();
}

/*function getPremiumAmount()
{
	$("#premAmount").val($("#premiumAmount").val());
}*/



function hitCompanyPremiumRequest() {
	//checkSession();
	$("#comapanyDtlRow").val("");
	$("#companyDtlDiv").hide();
	$("#showCompanyDiv").hide();
	resetProposalDetails();
	$("#coversDiv").show();
	$("#getPremiumBtnDiv").show();
	
	showWait();
	refreshTablePremium();
	$("#allCovers").val("");
	$("#allCoversValue").val("");
	$("#allCoversNumber").val("");
	var allCovers="";
	var allCoversValue="";
	var allCoversNumber="";
	
	var custMobileno = $('#mobileNo').val()
	var custEmailId = $('#emailId').val()
	//alert(custEmailId + " "+ custMobileno);
	$("#insDtlMobileNo").val(custMobileno);
	$("#insDtlEmail").val(custEmailId);
	
	var packageCoverArr = [];
	$("table#packageDetails tr").each(function() { 
		var packageCoverArrayThisRow = [];
		var tableData = $(this).find('td');
		 if (tableData.length > 0) {
			 tableData.each(function() { packageCoverArrayThisRow.push($(this).text()); });
			 packageCoverArr.push(packageCoverArrayThisRow);
		}
		// console.log("packageCoverArrayThisRow:::>>" +packageCoverArrayThisRow);
		
		 //console.log("packageCoverArrayThisRow:::>>" +packageCoverArrayThisRow);
		 var tblBiofuelKitValue="";
		if(!$("#fuelKit").val()==""){
			  tblBiofuelKitValue=$('#tblBiofuelKitPack').val();
		}
		 var status = packageCoverArrayThisRow[3];
		/* old_alert("status>>" + statusOld);
		 var statusnew = statusOld.split("\"");
		 var status = statusnew[0];*/
		 console.log("liability status================>>"+status);
		 if ((status!=("SELECT")) && (status!=("null")) && (status!=("")) && (status!=("DATE"))&& (status!=("0.00")) &&

				 (packageCoverArrayThisRow.length==12)) {
			 
			 	var statusOld1 = packageCoverArrayThisRow[3];
				// old_alert("status>>" + statusOld);
				 var statusnew = statusOld1.split(" ");
				// console.log("statusnew:::>>" +statusnew);
				 var status1 = statusnew[0];
				// console.log("status1:::>>" +status1);
			  if (allCovers=="" && allCoversValue=="") {
				  	
                 allCovers = packageCoverArrayThisRow[1];
                 allCoversValue =status1;
               //  console.log("allCoversValue:::>>" +allCoversValue);
                 allCoversNumber = packageCoverArrayThisRow[11];
                // allCoversType="Package";
             } else {
            	// console.log("allCoversValue:::>>" +allCoversValue);
            		
                 allCovers = allCovers + "," + packageCoverArrayThisRow[1];
                 allCoversValue = allCoversValue + "," + status1;
                 allCoversNumber = allCoversNumber + "," + packageCoverArrayThisRow[11];
                  //allCoversType= "," +"Package";
             }
		 }else if(((tblBiofuelKitValue!=(""))&& (status==("")))){
			  if (allCovers==("")&& allCoversValue==("")) {
                 allCovers = packageCoverArrayThisRow[1];
                 allCoversValue = tblBiofuelKitValue;
                 allCoversNumber = packageCoverArrayThisRow[11];
                // allCoversType="Package";
             } else {
                 allCovers = allCovers + "," + packageCoverArrayThisRow[1];
                 allCoversValue = allCoversValue + "," + tblBiofuelKitValue;
                 allCoversNumber = allCoversNumber + "," + packageCoverArrayThisRow[11];
                  //allCoversType= "," +"Package";
             }
        }
	});
	
	
	/*old_alert("pack allCovers==>>"+allCovers);
	old_alert("pack allCoversValue==>>"+allCoversValue);
	old_alert("pack allCoversNumber==>>"+allCoversNumber);*/
	 console.log("LIABILITY NOW");
	var liabilityCoverArr = [];
	$("table#liabilityDetails tr").each(function() { 
		var liabilityCoverArrayThisRow = [];
		var tableData = $(this).find('td');
		 if (tableData.length > 0) {
			 tableData.each(function() { liabilityCoverArrayThisRow.push($(this).text()); });
			 liabilityCoverArr.push(liabilityCoverArrayThisRow);
		}
		 console.log("liabilityCoverArrayThisRow===>>"+liabilityCoverArrayThisRow);
		 console.log("liabilityCoverArrayThisRow.length===>>"+liabilityCoverArrayThisRow.length);
		 console.log("liabilityCoverArrayThisRow[7]===>>***"+liabilityCoverArrayThisRow[7]+"***");
		 var tblBiofuelKitValue="";
		 if(!$("#fuelKit").val()==""){
			 tblBiofuelKitValue=$('#tblBiofuelKitLib').val();
		}
		 var status=liabilityCoverArrayThisRow[7];
		 
		 if ((status!=("SELECT")) && (status!=("null")) && (status!=("")) && (status!=("DATE"))&& (status!=("0.00"))&& (status!=(" "))&&

			(liabilityCoverArrayThisRow.length==12)) {
			 
			 var statusOld =  liabilityCoverArrayThisRow[7];
				// old_alert("status>>" + statusOld);
				 var statusnew = statusOld.split(" ");
				 var status1 = statusnew[0];
				 
			  if (allCovers==("")&& allCoversValue==("")) {
				  				 
				allCovers = liabilityCoverArrayThisRow[1];
                allCoversValue = status1;
                allCoversNumber = liabilityCoverArrayThisRow[11];
               //  allCoversType= "Liability";
            } else {
            	 
                allCovers = allCovers + "," + liabilityCoverArrayThisRow[1];
                allCoversValue = allCoversValue + "," + status1;
                allCoversNumber = allCoversNumber + "," + liabilityCoverArrayThisRow[11];
                 //allCoversType= "," +"Liability";
            }
		 }else if(((tblBiofuelKitValue!=(""))&& (status==("")))){
			 if (allCovers==("")&& allCoversValue==("")) {
                allCovers = liabilityCoverArrayThisRow[1];
                allCoversValue = tblBiofuelKitValue;
                allCoversNumber = liabilityCoverArrayThisRow[11];
               //  allCoversType= "Liability";
            } else {
                allCovers = allCovers + "," + liabilityCoverArrayThisRow[1];
                allCoversValue = allCoversValue + "," + tblBiofuelKitValue;
                allCoversNumber = allCoversNumber + "," + liabilityCoverArrayThisRow[11];
                 //allCoversType= "," +"Liability";
            }
        }
	});
	
	/*old_alert("lib allCovers==>>"+allCovers);
	old_alert("lib allCoversValue==>>"+allCoversValue);
	old_alert("lib allCoversNumber==>>"+allCoversNumber);
	*/
	
	// console.log("ADDITIONAL NOW");
	var additionalcoverArr = [];
	var k=1;
	$("table#additionalcover tbody tr").each(function() { 
		var additionalcoverArrayThisRow = [];
		var tableData = $(this).find('td');
		 if (tableData.length > 0) {
			 tableData.each(function() { additionalcoverArrayThisRow.push($(this).text()); });
			 additionalcoverArr.push(additionalcoverArrayThisRow);
		}
		 if ((additionalcoverArrayThisRow.length==12)) {
			// console.log("addonCheck"+k);
			 var status=document.getElementById('addonCheck'+k+'').checked;
			 console.log("addonCheck"+k+"  status=="+status);
				if (status==true) {
					if (allCovers==("")&& allCoversValue==("")) {
					//	console.log("in ifffffffff");
						allCovers = additionalcoverArrayThisRow[4];
						allCoversValue = additionalcoverArrayThisRow[11];
						allCoversNumber = additionalcoverArrayThisRow[10];
						// allCoversType= "Addon";
					} else {
						//console.log("in elseeeeeeeee");
						allCovers = allCovers + "," + additionalcoverArrayThisRow[4];
						allCoversValue = allCoversValue + "," + additionalcoverArrayThisRow[11];
						allCoversNumber = allCoversNumber + "," + additionalcoverArrayThisRow[10];
						// allCoversType= "," +"Addon";
					}
				}
				k=k+1;
		 }
	});
	console.log("allCovers==>>"+allCovers);
	console.log("allCoversValue==>>"+allCoversValue);
	console.log("allCoversNumber==>>"+allCoversNumber);
	
	$("#allCovers").val(allCovers);
	$("#allCoversValue").val(allCoversValue);
	$("#allCoversNumber").val(allCoversNumber);
	
	
    var active = true;
	
    if (active) {
//    	old_alert("compDtlActive1::" +compDtlActive1);
    	active = false;
        $('.run').collapse('show');
        $('.run1').attr('data-toggle', '');
    } /*else {
    	compDtlActive1 = true;
        $('.compDtl').collapse('hide');
        $('.compDtl1').attr('data-toggle', 'collapse');
    }*/

$('#accordion').on('show.bs.collapse', function () {
    if (active) $('#accordion .in').collapse('hide');
});
    
    
    
    
	
	 $("#compSerial").val("0");
	 $("#showCompanyDiv").hide();
	
	//---------------------------------------------
	


 		getFinanceType();
 		insDtlInitial();
		insDtlMaritialStatus();
		insDtlNationality();
		insDtlCACountry();
		insDtlPACountry();
		insDtlOACountry();
		nomDtlInitial();
		nomDtlRelation();
		nomDtlCountry();
		getBankDtlBankName();
 		inspectionState();
		inspectionAgency();
		getPremium();
		hideWait()
		
		
		
		
	
	
}

function proposalTypeUsed(Id){
	var checked=$("#prevPolYes").prop("checked");
	var preComp=$("#prevInsuranceCompany").val();
	var proposalId="";
	if(checked){
		if(preComp==Id){
			//proposalId="4";
			$("#proposal").val("4");
			$("#proposalTf").val("RENEWAL");
		}else{
			$("#proposal").val("5");
			$("#proposalTf").val("ROLLOVER");
		}
	}else{
		$("#proposal").val();
	}
	//return proposalId;
}

function destroyDataTable()
{
	if($.fn.DataTable.isDataTable("#companyDetails")){
		console.log("All premium in companyDetails destroy");
		$("#companyDetails").DataTable().destroy();
//		$("#companyDetails").DataTable().remove();
	//	$("#companyDetails").DataTable().clear();
	}
}

function getPremium()
{
//	showWait();
	 if($.fn.DataTable.isDataTable("#companyDetails")){
			console.log("All premium in companyDetails");
			 dataTable
			    .rows()
			    .remove()
			    .draw(false);
	}
	dataTable = $('#companyDetails').DataTable({
		 destroy: true,	
		 "columnDefs": [
		                { className: "hide_column", "targets":[18,19,20,21,22] ,"searchable": false}
		              ]
		
			         });
	getReliancePremiumResponse();
//	getPremiumResponseForVideocon();
//	getKotakGicPremiumRequest();
//	getBajajPremium();	
	getPremiumHdfcResponse();
//	getPremiumUniversalResponse();
//  getShriRamPremiumFromProposal();
//	getFutureGeneraliPremium();
	
	
	
	
	var compDtlActive1 = true;
	
        if (compDtlActive1) {
//        	old_alert("compDtlActive1::" +compDtlActive1);
        	compDtlActive1 = false;
            $('.compDtl').collapse('show');
            $('.compDtl1').attr('data-toggle', '');
        } /*else {
        	compDtlActive1 = true;
            $('.compDtl').collapse('hide');
            $('.compDtl1').attr('data-toggle', 'collapse');
        }*/
  
    $('#accordion').on('show.bs.collapse', function () {
        if (compDtlActive1) $('#accordion .in').collapse('hide');
    });

	
}


function selectCompanyAction(k) {
		if (!$('#engineNo').val()) {
			old_alert("Please Select engine No");
			setTimeout(function(){$('#engineNo').focus();},1);
			$("#companyCheck"+k).prop("checked", false);
			return false;
		}
		else if (!$('#chasisNo').val()) {
			old_alert("Please Select chasis No");							
			setTimeout(function(){$('#chasisNo').focus();},1);
			$("#companyCheck"+k).prop("checked", false);
			return false;
		}
		else{
		var sr=$("#compSerial").val();
		for(var i=1;i<=sr;i++){
			if(i!=k){
				 $("#companyCheck"+i).prop("checked", false);
			}
		}
		var values = new Array();
		$.each($("#companyCheck"+k+":checked").closest("td").siblings("td"),
		       function () {
		            values.push($(this).text());
		       });
	 	var comapanyDtl = values.join (",");
	    console.log(comapanyDtl);
	    $("#prevCompDataCompList").val(comapanyDtl);
	//   // old_alert("check---------:"+$("#prevCompDataCompList").val());
		var comapany = comapanyDtl.split(",");
		$("#companyDtl_companyId").val(comapany[16]);
		
		/*--------------------------------------*/ 
		var count = $('table#companyDetails tr:last').index() + 1;
		// old_alert(count);
	if(count !=0){
	var sr=$("#compSerial").val();
	 var check=false;
		for(var i=1;i<=sr;i++){
			 if(document.getElementById('companyCheck'+i+'').checked){
				 check="true";
				 
		        $('.insDtl').collapse('show');
		        $('.insDtl1').attr('data-toggle', '');
				$('#accordion').on('show.bs.collapse', function () {
					    if (insuredActive) $('#accordion .in').collapse('hide');
				});
				
			//	$("#companyDtlDiv").show();
				$("#VehicleFinDtlDiv").show();
				/*$("#backPapersDiv").show();
				$("#inspectionDtlDiv").show();
				$("#insuredBankDtlDiv").show();*/
				$("#nomineeDtlDiv").show();
				$("#insuredDtlDiv").show();
//				$("#insDtlInitial").select2("focus");
				$('#insDtlInitial').select2('focus');
				
			}
		}
		if(check==false){
			 
			 $('.insDtl').collapse('hide');
		        $('.insDtl1').attr('data-toggle', 'collapse');
				$('#accordion').on('show.bs.collapse', function () {
					    if (insuredActive) $('#accordion .in').collapse('hide');
				});
			//	$('#insDtlInitial').select2('focus');
			//	$("#companyDtlDiv").show();
				$("#VehicleFinDtlDiv").hide();
				/*$("#backPapersDiv").show();
				$("#inspectionDtlDiv").show();
				$("#insuredBankDtlDiv").show();*/
				$("#nomineeDtlDiv").hide();
				$("#insuredDtlDiv").hide();
			
		      return false;
		}
		//old_alert("you hv data in table");
	}else{
		old_alert("No data in table");
	}
	}
		
	}  

function getProposalResponse()
{
	refreshTableProposal();
	
		if(InsuredDetailsAndNomineeDetails()){
		var compId= $("#companyDtl_companyId").val() ;

	if (compId == "2") {
		getRelianceProposalResponse();
		
    } 
	 else if(compId == "34") {
		 getProposalResponseForVideocon();
		 $("#proposalDetailsDiv").show();
    }
	 else if(compId == "4") {
			getProposalHdfcResponse();
			$("#proposalDetailsDiv").show();
	 }
	 else if(compId == "48") {
		 getProposalResponseForShriRam();
		 $("#proposalDetailsDiv").show();
	 }
	 else if(compId == "47") {
		getProposalUniversalResponse(); 
		$("#proposalDetailsDiv").show();
	 }
	 else if(compId == "49") {
		 getFutureGeneraliProposal(); 
		 $("#proposalDetailsDiv").show();
	 }
	 else if(compId == "56") {	
		getKotakProposal();			
		$("#proposalDetailsDiv").show();			
	 }  
	 else if(compId == "43") {	
		 getBajajPolicyIssue();			
		 $("#proposalDetailsDiv").show();			
	 }  
	}
}

function makePayment() {
	var compId= $("#companyDtl_companyId").val();
	 if (compId == "2") {
		 makeReliancePaymentOnline();
	} 
	 else if(compId == "34") {
		 videoconPayment();
    }
	 else if(compId == "4") {
		 getPaymentHdfcResponse();
	 }
	 else if(compId== "48") {
//		ShreeRamPayment
	 }
	 else if(compId == "47") {
//		UniversalPayment
	 }
	 else if(compId == "49") {
		 getFutureGeneraliPayment();
	 }
	 else if(compId == "56") {			
         getKotakPayment();			
 }
	 else if(compId == "43") {			
		 getBajajPayment();			
	 }
	// getPaymentData();
	 $("#paymentDetailsDiv").show();
}




//////// Integration Of Reliance General Insurance Company ///////

////Reliance Coverage Starts Here/////
function getRelianceCoverageResponse() {
	showWait();
//	old_alert("In rel coverage");
	
	proposalTypeUsed("2");
	
	var insDtlPAAreaID = $("#insDtlPAPincode").val();
	var insDtlCAAreaID = $("#insDtlCAPincode").val();
	var insDtlOAAreaID = $("#insDtlOAPincode").val();
	var prevInsCompanyID = $("#prevInsuranceCompany").val();
	
	if(!$("#prevInsuranceCompany").val()){
		prevInsCompanyID="";
	}
	else{
		prevInsCompanyID = $("#prevInsuranceCompany").val();
	}
//	old_alert(prevInsCompanyID)
	var dateOfReg = $("#dateofReg").val();
//	old_alert("insDtlPAAreaID::: " +insDtlPAAreaID)
	if(insDtlPAAreaID == "--Select--"){
//		old_alert("In if");
		insDtlPAAreaID="";
	}
	else{
		insDtlPAAreaID = $("#insDtlPAPincode").val();
	}
	if(insDtlCAAreaID == "--Select--"){
		insDtlCAAreaID="";
	}
	else{
		insDtlCAAreaID = $("#insDtlCAPincode").val();
	}
	if(insDtlOAAreaID == "--Select--"){
		insDtlOAAreaID="";
	}
	else{
		insDtlOAAreaID = $("#insDtlOAPincode").val();
	}
	 $.ajax({
		'url' : "user/motorCalculatorCoverage?pkg_name=PKG_MOTOR_CALC"
           + "&proc_name=RELIANCE"
           + "&table_name=AMCP"
           + "&request_for=" + "coverage"
           + "&clienttype=" + $("#custTypeIdRel").val()
           + "&last_name=" + ""
           + "&midname=" + ""
           + "&forename=" + ""
           + "&corporatename=" + ""
           + "&occupationid=" + ""
           + "&dob=" + ""
           + "&gender=" + ""
           + "&phoneno=" + ""
           + "&mobileno=" + ""
           + "&clientaddress=" + ""
           + "&cld_ca_addresstype="+ "0"
           + "&cld_ca_address1="+ "" 
           + "&cld_ca_address2=" + ""
           + "&cld_ca_address3=" + ""
           + "&cld_ca_cityid=" + ""
           + "&cld_ca_districtid=" + ""
           + "&cld_ca_stateid=" + ""
           + "&cld_ca_pincode=" + ""
           + "&cld_ca_country=" + ""
           + "&cld_ca_nearestlandmark=" + ""
           + "&cld_pa_addresstype=" + "0"
           + "&cld_pa_address1=" + ""
           + "&cld_pa_address2=" + ""
           + "&cld_pa_address3=" + ""
           + "&cld_pa_cityid=" + ""
           + "&cld_pa_districtid=" + ""
           + "&cld_pa_stateid=" + ""
           + "&cld_pa_pincode=" + ""
           + "&cld_pa_country=" + ""
           + "&cld_pa_nearestlandmark=" + ""
           + "&cld_ra_addresstype=" + "0"
           + "&cld_ra_address1=" + ""
           + "&cld_ra_address2=" + ""
           + "&cld_ra_address3=" + ""
           + "&cld_ra_cityid=" + ""
           + "&cld_ra_districtid=" + ""
           + "&cld_ra_stateid=" + ""
           + "&cld_ra_pincode=" + ""
           + "&cld_ra_country=" + ""
           + "&cld_ra_nearestlandmark=" + ""
           + "&emailid=" + "" + "&salutation=" + ""
           + "&maritalstatus=" + ""
           + "&nationality=" + ""
           + "&p_businesstype=" + $("#proposal").val()
           + "&str_prod=" + $("#productname").val() + "&str_veh="
           + $("#vehicle").val() + "&str_mod=" + $("#Model").val() + "&str_var="
           +  $("#varience").val() + "&idv=" + "" + $("#basicIdv").val()
           + "&manufacturemonth=" + $("#month").val()
           + "&manufactureyear=" + $("#year option:selected").text()
           + "&engineno=" + "" + "&chassis=" + ""
           + "&isvehiclehypothicated=" + ""
           + "&financetypeid=" + ""
           + "&financiername=" + ""
           + "&financieraddress=" + ""
           + "&financiercity=" + ""
           + "&isregaddsameascommadd=" + ""
           + "&isperaddsameascommadd=" + ""
           + "&isregaddsameasperadd=" + ""
           + "&str_state=" + $("#RTOState").val()
           + "&str_city=" + $("#RTOSCity").val()
           + "&registration_number=" + $("#regNo").val() 
           + "&isnewvehicle=" +  $("#newVehicle").val()
           + "&isbifuelkit=" + ""
           + "&islpgcng=" + "" + "&bfk_amt=" + ""
           + "&pao_nomineename=" + "" + "&pao_nomineedob="
           + "" + "&pao_nomineerel=" + ""
           + "&previnsname=" + "" + "&prevpolicyno=" +  $("#prevPolicyCoverNo").val()
           + "&prevstartdt=" +$("#prevFromDate").val()
           + "&prevenddt=" + $("#prevToDate").val() 
           + "&ncbisapp=" + "" + "&ncbeligicrit=" + ""
           + "&ncbprevncb=" + $("#prevNcb option:selected").text()
           + "&ncbcurncb=" + $("#prevNcb option:selected").text()
           + "&voldedamt=" + "" + "&eleamt=" + ""
           + "&noneleamt=" + "" + "&unpasno=" + ""
           + "&unpasamt=" + ""
           + "&antitheftno=" + ""
           + "&antitheftname=" + ""
           + "&automemno=" + ""
           + "&automemname=" + ""
           + "&vdpolcovid=" + ""
           + "&vdamt=" + "" + "&vdno=" + ""
           + "&vdname=" + "" + "&patounpasno=" + ""
           + "&patounpasname=" + ""
           + "&patounpaspolcovid=" + ""
           + "&patounpasamt=" + ""
           + "&zone_id=" +$("#zoneId").val()
           + "&PI_CLD_RA_AreaID=" + insDtlCAAreaID
           + "&PI_CLD_PA_AreaID=" + insDtlPAAreaID
           + "&PI_CLD_CA_AreaID=" + insDtlCAAreaID
           + "&ElectricalItem=" + ""
           + "&policyType=" + "1"
           + "&prevInsId=" + prevInsCompanyID
           + "&covers="+ ""
           + "&coverVal="+ ""
           + "&coverNo="+ ""
           + "&dateOfPurchase=" + dateOfReg
           + "&isVoldedOpted=" + ""
           + "&vehicleRegDate=" + dateOfReg
           + "&isAntitheftDeviceFitted=" + ""
           + "&isautoaMem=" + ""
           + "&patounPassCover=" + ""
           + "&unpassi=" + ""
           + "&iselectricalItemFitted=" + ""
           + "&isNonElectricalItemFitted=" + ""
           + "&isTppdCover=" + ""
           + "&isBasicCDCoverage=" + ""
           + "&isBasicLiability=" + ""
           + "&isPaToCoverDriverCovered=" + ""
           + "&bfk_ischecked=" + ""
           + "&bfk_IsLPGCNG=" + ""
           + "&VDIsMandatory=" + ""
           + "&vdIsChecked=" + ""
           + "&vdPackageName=" + ""
           + "&atddIsMandatory=" + ""
           + "&atddIsChecked=" + ""
           + "&atddPackageName=" + ""
           + "&aamdIsMandatory=" + ""
           + "&aamdIsChecked=" + ""
           + "&aamdPackageName=" + ""
           + "&pauIsMandatory=" + ""
           + "&pauIsChecked=" + ""
           + "&pauPackageName=" + ""
           + "&paupolcovid=" + ""
           + "&motorGroupResponseGroupId=" + $("#groupID").val()
           + "&motorGroupResponseSessionId=" + $("#sessionId").val()
           + "&motorGroupResponseGicId=" + "2"
           + "&userId=0" 
           + "&userDesc=WEB" 
           + "&branchId=0" 
           + "&IPAddress=",
					
			'method' : 'post',
			'dataType' : 'json',
			'async' : false,
			 success : function(resp) {
				 hideWait()
			//	  old_alert("hi success ");
//				 old_alert(resp); 
				/* $.each(resp, function(key, value) {
					var gicName=resp[key].GIC_NAME;
					var gicId=resp[key].GIC_ID;
						if(gicId == $("#companyDtl_companyId").val())
						{
							 //old_alert(gicId);
						 	$("#companyDtl_companyId").val(gicId);
						 	companyName= gicName;
						}
					});*/
			},
			error:function(resp) {
				 hideWait()
				//old_alert("hi error");
				 
			},
		});   
	
}
////Reliance Coverage Ends Here/////


////Reliance Premium Starts Here/////
function getReliancePremiumResponse() {
	console.log("Reliance");
	showWait();
	var allCoversNumber = $("#allCoversNumber").val();
	var allCovers=  $("#allCovers").val();
 	var allCoversValue =  $("#allCoversValue").val();
	var comapanyDtlRow = $("#comapanyDtlRow").val();
	console.log("comapanyDtlRow:: "+ comapanyDtlRow)
	if(comapanyDtlRow !=""){
	comapanyDtlRowData = comapanyDtlRow.split(",");
	 
	if(allCoversNumber == "")
	{
		allCoversNumber = comapanyDtlRowData[25];
	}else{
		allCoversNumber = allCoversNumber + "," + comapanyDtlRowData[25] ;
	}
	if(allCovers == "")
	{
		allCovers = comapanyDtlRowData[23];
	}else{
		allCovers =allCovers + "," + comapanyDtlRowData[23];
	}
	if(allCoversValue == "")
	{
		allCoversValue = comapanyDtlRowData[24];
	}else{
		allCoversValue =allCoversValue + "," + comapanyDtlRowData[24];
	}
	
	}
	$("#companyDtl_companyId").val("2");
	var gicId = $("#companyDtl_companyId").val();
	proposalTypeUsed(gicId);
	var basicIdv = "";
	if($("#policyTypeId").val() == "2" )
	{
		 basicIdv = "0";
	}else{
		 basicIdv = $("#basicIdv").val();
	}
	
	 findGicCompany($("#companyDtl_companyId").val());
		var vehicleId = $("#vehicle").val();
		var modelId = $("#Model").val();
		var varianceId = $("#varience").val();
		var customerTypeId =$("#customer").val();
		
		//old_alert("customerType::" +customerType);
		var mfrMonthId = $("#month").val();
		var prevPolicyType = $("#prevPolicyType").val();
		var prevPolicyCoverNo = $("#prevPolicyCoverNo").val();
		var nonEleAmt = $("#nonEleTotalTextfiels").val();
		var eleAmt = $("#eleTotalTextfiels").val();
		var prevToDate = $("#prevToDate").val();
		var applicantid = $("#applicantname").val();
		var productnameid = $("#productname").val();
		
	
	//	var proposal = $("#proposal option:selected").text();
		var dateOfReg = $("#dateofReg").val();
		var manfYr= $("#year :selected").text();
		var insDtlDob= $("#insDtlDob").val();
		if(insDtlDob == "")
		{
			var insDtlDob = "";
		}else{
			insDtlDob= $("#insDtlDob").val();
		}
		var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
		var aaiName = $("#aaiName").val();
		var aaiMembershipNo = $("#aaiMembershipNo").val();
		var aaiCode = $("#aaiCode").val();
		var aaiExpiryDate = $("#aaiExpiryDate").val();
		var aaiAssociationName = $("#aaiAssociationName").val();
		var fuelKit = $("#fuelKit").val();
		var prevNoOfClaims = $("#prevNoOfClaims").val();
		var prevInsCompanyID = $("#prevInsuranceCompany").val();
		
		if(!$("#prevInsuranceCompany").val()){
			prevInsCompanyID="";
		}
		else{
			prevInsCompanyID = $("#prevInsuranceCompany").val();
		}
	//	old_alert(prevInsCompanyID);
		
		var prevPolicyCoverNo = $("#prevPolicyCoverNo").val();
		var insDtlPAAreaID = $("#insDtlPAPincode").val();
		var insDtlCAAreaID = $("#insDtlCAPincode").val();
		var insDtlOAAreaID = $("#insDtlOAPincode").val();
		//old_alert("insDtlPAAreaID::: " +insDtlPAAreaID)
		if(insDtlPAAreaID == "--Select--" ){
			//old_alert("In if");
			insDtlPAAreaID="";
		}
		else{
			insDtlPAAreaID = $("#insDtlPAPincode").val();
		}
		if(insDtlCAAreaID == "--Select--"){
			insDtlCAAreaID="";
		}
		else{
			insDtlCAAreaID = $("#insDtlCAPincode").val();
		}
		if(insDtlOAAreaID == "--Select--"){
			insDtlOAAreaID="";
		}
		else{
			insDtlOAAreaID = $("#insDtlOAPincode").val();
		}
		var prevNcbVal= $("#prevNcb option:selected").text();
		if(prevNcbVal == "Select")
		{
			prevNcbVal="";
		}else{
			prevNcbVal= $("#prevNcb option:selected").text();
		}
		var isvehiclehypothicated="";
		var financetypeid="", financiername="", financieraddress="", financiercity="";
		//old_alert($("#finDtlFinType").val());
		if($("#finDtlFinType").val()=="")
		{
			isvehiclehypothicated="false";
		}else{
			isvehiclehypothicated="true";
		}
		if(isvehiclehypothicated =="true")
		{
			financetypeid = $("#finDtlFinType").val();
			financiername = $("#finDtlFinName option:selected").text();
			financieraddress = $("#finDtlFinState option:selected").text() +" "+ $("#finDtlFinCity option:selected").text();
			financiercity = $("#finDtlFinCity option:selected").text();
			
		}else{
			financetypeid="", financiername="", financieraddress="", financiercity="";
		}
//		old_alert("entryDate:::" + entryDate);
		
		$.ajax({
			'url' :"user/motorCalculator?pkg_name=PKG_MOTOR_CALC"
            + "&proc_name=RELIANCE"
            + "&table_name=AMCP"
            + "&request_for=premium"
            + "&clienttype=" + $("#custTypeIdRel").val()
            + "&last_name=" + "" + "&midname=" + ""
            + "&forename=" + "" + "&corporatename=" + ""
            + "&occupationid=" + "" + "&dob=" + $("#entryDate").val()
            + "&gender=" + "" + "&phoneno=" + ""
            + "&mobileno=" + "" + "&clientaddress=" + ""
            + "&cld_ca_addresstype=" + "" + "&cld_ca_address1=" + ""
            + "&cld_ca_address2=" + "" + "&cld_ca_address3=" + ""
            + "&cld_ca_cityid=" + "" + "&cld_ca_districtid=" + ""
            + "&cld_ca_stateid=" + "" + "&cld_ca_pincode=" + ""
            + "&cld_ca_country=" + "" + "&cld_ca_nearestlandmark=" + ""
            + "&cld_pa_addresstype=" + "" + "&cld_pa_address1=" + ""
            + "&cld_pa_address2=" + "" + "&cld_pa_address3=" + ""
            + "&cld_pa_cityid=" + "" + "&cld_pa_districtid=" + ""
            + "&cld_pa_stateid=" + "" + "&cld_pa_pincode=" + ""
            + "&cld_pa_country=" + "" + "&cld_pa_nearestlandmark="
            + "" + "&cld_ra_addresstype=" + "" + "&cld_ra_address1="
            + "" + "&cld_ra_address2=" + "" + "&cld_ra_address3=" + ""
            + "&cld_ra_cityid=" + "" + "&cld_ra_districtid=" + ""
            + "&cld_ra_stateid=" + "" + "&cld_ra_pincode=" + ""
            + "&cld_ra_country=" + "" + "&cld_ra_nearestlandmark=" + ""
            + "&emailid=" + "" + "&salutation=" + "" + "&maritalstatus="
            + "" + "&nationality=" + "" + "&p_businesstype=" + $("#proposal").val()
            + "&str_prod=" + productnameid + "&str_veh=" + vehicleId + "&str_mod="
            + modelId + "&str_var=" + varianceId + "&idv=" + "" + basicIdv
             + "&manufacturemonth="
            + mfrMonthId + "&manufactureyear=" + manfYr.trim() + "&engineno="
            + "" + "&chassis=" + "" + "&isvehiclehypothicated=" + isvehiclehypothicated
            + "&financetypeid=" + financetypeid + "&financiername=" +financiername + "&financieraddress=" + financieraddress
            + "&financiercity=" +financiercity + "&isregaddsameascommadd=" + ""
            + "&isperaddsameascommadd=" + "" + "&isregaddsameasperadd=" + ""
            + "&str_state=" + $("#RTOState").val() + "&str_city=" + $("#RTOSCity").val() 
            + "&registration_number=" +  $("#regNo").val()  + "&isnewvehicle=" + $("#newVehicle").val()
            + "&registration_date=" + dateOfReg + 
            "&covers=" + allCovers
        	+
            "&coversValue=" +  allCoversValue
        
            + "&isbifuelkit=" + "" + "&islpgcng=" + "" + "&bfk_amt=" + ""
            + "&pao_nomineename=" + "" + "&pao_nomineedob=" + $("#entryDate").val()
            + "&pao_nomineerel=" + "" + "&previnsname=" + ""
            + "&prevpolicyno=" + prevPolicyCoverNo + "&prevstartdt=" + $("#prevFromDate").val() 
            + "&prevenddt=" + $("#prevToDate").val()  + "&ncbisapp=" + ""
            + "&ncbeligicrit=" + "" + "&ncbprevncb=" + prevNcbVal
            + "&ncbcurncb=" + "" + "&voldedamt=" + "" + "&eleamt=" + ""+eleAmt
            + "&noneleamt=" + ""+nonEleAmt + "&unpasno=" + "" + "&unpasamt=" + ""
            + "&antitheftno=" + "" + "&antitheftname=" + "" + "&automemno="
            + "" + "&automemname=" + "" + "&vdpolcovid=" + "" + "&vdamt=" + ""
            + "&vdno=" + "" + "&vdname=" + "" + "&patounpasno=" + ""
            + "&patounpasname=" + "" + "&patounpaspolcovid=" + ""
            + "&patounpasamt=" + "" + "&zone_id=" + $("#zoneId").val()
            +"&ElectricalItem=" + $("#electricalAcc").val() 
            + "&NonElectricalItem=" +$("#nonElectricalAcc").val()
            + "&PI_CLD_RA_AreaID=" + insDtlCAAreaID
            + "&PI_CLD_PA_AreaID=" + insDtlPAAreaID
            + "&PI_CLD_CA_AreaID=" + insDtlCAAreaID
            + "&policyType=" + $("#policyTypeId").val()
            + "&prevInsId=" + prevInsCompanyID
            + "&coverNo=" + allCoversNumber +
            "&dateOfPurchase=" +  $("#dateofReg").val()
            + "&isVoldedOpted=" + ""
            + "&vehicleRegDate=" +  $("#dateofReg").val()
            + "&isAntitheftDeviceFitted=" + ""
            + "&isautoaMem=" + ""
            + "&patounPassCover=" + ""
            + "&unpassi=" + ""
            + "&iselectricalItemFitted=" + ""
            + "&isNonElectricalItemFitted=" + ""
            + "&isTppdCover=" + ""
            + "&isBasicCDCoverage=" + ""
            + "&isBasicLiability=" + ""
            + "&isPaToCoverDriverCovered=" + ""
            + "&bfk_ischecked=" + ""
            + "&bfk_IsLPGCNG=" + ""
            + "&VDIsMandatory=" + ""
            + "&vdIsChecked=" + ""
            + "&vdPackageName=" + ""
            + "&atddIsMandatory=" + ""
            + "&atddIsChecked=" + ""
            + "&atddPackageName=" + ""
            + "&aamdIsMandatory=" + ""
            + "&aamdIsChecked=" + ""
            + "&aamdPackageName=" + ""
            + "&pauIsMandatory=" + ""
            + "&pauIsChecked=" + ""
            + "&pauPackageName=" + ""
            + "&paupolcovid=" + ""  + "&motorGroupResponseGroupId=" +  $("#groupID").val()
                + "&motorGroupResponseSessionId=" + $("#sessionId").val()
                + "&motorGroupResponseGicId=" +$("#companyDtl_companyId").val() 
                + "&userId=0"
                + "&userDesc=WEB"
                + "&branchId=0"
                + "&str_proposalType="+$("#proposalTf").val() 
                + "&str_productname=" +$("#productnameTf").val() ,
            
            type : 'post',
       		dataType : 'json',
      		async : true,
      		success : function(resp) {
      		//destroyDataTable();
      		 var newRow="";
			$.each(resp, function(key, value) {
				
			if(resp[key].XMLError != "Error"){
				
				nKey = parseInt(key) + 1;
				
				var sr=$("#compSerial").val() ;
				sr++;
				var gicId = resp[key].GICID  ;
				
				$("#relIdv").val(resp[key].IDV); 
				//var extraCover= $("#extraCover").val();
				
				var tax = parseFloat(resp[key].SGST_amount) + parseFloat(resp[key].CGST_amount);
			
				var breakIn=  parseFloat(resp[key].TotalOD) + parseFloat(200);
				var od="";
				 if($("#productname").val() == "1"){
					 if($("#gap").val()>=15)
					{
						 od= breakIn;
					}else{
						od = resp[key].TotalOD
					}
				 } else{
					 od = resp[key].TotalOD
				 }
				if(comapanyDtlRow == ""){
								var row = '<input type="checkbox" name="checkbox" id="companyCheck'+sr+'" onclick="selectCompanyAction('+sr+');">';
								var extr = "<div onclick='showExtraCompanyCovers("+gicId+");'  data-backdrop='static' id='cmpCovers' data-toggle='modal' data-target='#extraCompanyCovers'><u class='pointer' style='color:#283FE6;'>Extra Cover</u></div>" ;
								if(resp[key].ErrorMessages ==""){
									$("#compSerial").val(sr);
									 //newRow = $("#companyDetails").append(row2);
									dataTable.row.add([row,'',extr, resp[key].Company ,
									                    resp[key].P_COVER_FROM,
									                   '1', resp[key].P_COVER_TO ,od,
									                   '0','0',
									                   resp[key].TotalLiabilityPremium,resp[key].NetPremium , tax,
									                   resp[key].FinalPremium ,resp[key].BUSINESSTYPE  , '','',resp[key].GICID,
									                   resp[key].CGST_amount,
									                  resp[key].SGST_amount,
									                  '0',
									                  '0',
									                 '0','','','']).draw();
									$("#companyDtlDiv").show();
									
								}else{
									
									old_alert(resp[key].ErrorMessages); 
								}
								//row ++;

				}else{
				  if( resp[key].ErrorMessages ==""){
					($("#productname").val() == "1" ? 
							($("#gap").val()>=15 ?
									 $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(7).html(breakIn)
											:"<td>"+   $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(7).html(resp[key].TotalOD)  +"</td>")
									: "<td>"+   $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(7).html(resp[key].TotalOD)  +"</td>")
					
					 $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(8).html("0");
				     $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(9).html("0");
				     $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(10).html(resp[key].TotalLiabilityPremium);
				     $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(11).html(resp[key].NetPremium);
				     $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(12).html(tax);
				     $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(13).html(resp[key].FinalPremium);
				  }else{
						old_alert(resp[key].ErrorMessages); 
					}
				}
				}
				else{
					hideWait();
				}
			});
			$("#companyDtlDiv").show();
			
		hideWait()	
		},
		error: function () {
			hideWait()
//			old_alert("Fail");
		}
		});
}

////Reliance Premium Ends Here/////
////Reliance Proposal Starts Here/////

function getRelianceProposalResponse() {
	showWait();
//	if($.fn.DataTable.isDataTable('#proposalDetails')) {
////		  $('#companyDetails').dataTable();
//			proposalDetailTable.destroy();
//			
//		}
	var gicId = $("#companyDtl_companyId").val();
	proposalTypeUsed(gicId);	
	
	var basicIdv = "";
	if($("#policyTypeId").val() == "2" )
	{
		 basicIdv = $("#relIdv").val(); 
	}else{
		 basicIdv = $("#basicIdv").val();
	}
	
		var insDtlDob= $("#insDtlDob").val();
		if(insDtlDob == "")
		{
			var insDtlDob = "";
		}else{
			insDtlDob= $("#insDtlDob").val();
		}
		var prevInsCompanyID = $("#prevInsuranceCompany").val();
		
		if(!$("#prevInsuranceCompany").val()){
			prevInsCompanyID="";
		}
		else{
			prevInsCompanyID = $("#prevInsuranceCompany").val();
		}
//		old_alert(prevInsCompanyID);
		var insDtlPAAreaID = $("#insDtlPAPincode").val();
		var insDtlCAAreaID = $("#insDtlCAPincode").val();
		var insDtlOAAreaID = $("#insDtlOAPincode").val();
		if(insDtlPAAreaID == "--Select--" ){
//			old_alert("In if");
			insDtlPAAreaID="";
		}
		else{
			insDtlPAAreaID = $("#insDtlPAPincode").val();
		}
		if(insDtlCAAreaID == "--Select--"){
			insDtlCAAreaID="";
		}
		else{
			insDtlCAAreaID = $("#insDtlCAPincode").val();
		}
		if(insDtlOAAreaID == "--Select--"){
			insDtlOAAreaID="";
		}
		else{
			insDtlOAAreaID = $("#insDtlOAPincode").val();
		}
		var insDtlGender="";
		if (document.getElementById('insDtlGenMale').checked) {
			insDtlGender = document.getElementById('insDtlGenMale').value;
		}
		else if (document.getElementById('insDtlGenFemale').checked) {
			insDtlGender = document.getElementById('insDtlGenFemale').value;
		}
		else if (document.getElementById('insDtlGenOther').checked) {
			insDtlGender = document.getElementById('insDtlGenOther').value;
		}
		
		var insDtlPAName = $("#insDtlPAName").val();
		
		var finDtlFinType = $("#finDtlFinType").val();
		if(finDtlFinType == "--Select--" || finDtlFinType == "" || finDtlFinType == null)
			{
			finDtlFinType="";
			}else{
				finDtlFinType = $("#finDtlFinType").val();
			}
		var finDtlFinName = $("#finDtlFinName option:selected").text();
		if(finDtlFinName == "--Select--" || finDtlFinName == "" || finDtlFinName == null)
		{
			finDtlFinName="";
		}else{
			finDtlFinName =$("#finDtlFinName option:selected").text();
		}
		
	
		var prevNcbVal= $("#prevNcb option:selected").text();
		if(prevNcbVal == "Select")
		{
			prevNcbVal="";
		}else{
			prevNcbVal= $("#prevNcb option:selected").text();
		}
		var isvehiclehypothicated="";
		var financetypeid="", financiername="", financieraddress="", financiercity="";
		if($("#finDtlFinType").val()=="--Select--" || $("#finDtlFinType").val() == "" || $("#finDtlFinType").val() == null)
		{
			isvehiclehypothicated="false";
		}else{
			isvehiclehypothicated="true";
		}
		if(isvehiclehypothicated)
		{
			financetypeid = $("#finDtlFinType").val();
			financiername = $("#finDtlFinName option:selected").text();
			financieraddress = $("#finDtlFinState option:selected").text() +" "+ $("#finDtlFinCity option:selected").text();
			financiercity = $("#finDtlFinCity option:selected").text();
			
		}else{
			financetypeid="", financiername="", financieraddress="", financiercity="";
		}
		
		 
//		old_alert("entryDate:::" + entryDate);
	$.ajax({
		'url' :	
				"user/motorCalculator?pkg_name=PKG_MOTOR_CALC"
			    + "&proc_name=RELIANCE"
			    + "&table_name=AMCP"
			    + "&request_for=proposal"
			    + "&clienttype=" + $("#custTypeIdRel").val()
			    + "&last_name=" +  $("#insDtlLname").val()
			    + "&midname=" + $("#insDtlMname").val()
			    + "&forename=" + $("#insDtlFname").val()
			    + "&corporatename="  + "&occupationid=" 
			    + "&dob=" +  $("#insDtlDob").val() + "&gender=" + insDtlGender 
			    + "&phoneno=" + $("#insDtlPhoneNo").val()
			    + "&mobileno=" + $("#insDtlMobileNo").val() 
			    + "&clientaddress=" + ""
			    + "&cld_ca_addresstype=" + "0"
			    + "&cld_ca_address1=" + $("#insDtlCAPincode option:selected").text()
			    + "&cld_ca_address2=" + $("#insDtlCAStreet").val()
			    + "&cld_ca_address3=" + $("#insDtlCAHouse").val()
			    + "&cld_ca_cityid=" + $("#insDtlCACity").val()
			    + "&cld_ca_districtid=" + $("#insDtlCADistrict").val()
			    + "&cld_ca_pincode=" + $("#insDtlCAPincode option:selected").attr('pincodeCA')
			    + "&cld_ca_country=" +  $("#insDtlCACountry").val()
			    + "&cld_ca_nearestlandmark=" +  $("#insDtlCALandmark").val()
			    + "&cld_pa_addresstype=" + "0"
			    + "&cld_pa_address1=" + $("#insDtlPAPincode option:selected").text()
			    + "&cld_pa_address2=" +  $("#insDtlPAStreet").val()
			    + "&cld_pa_address3=" +  $("#insDtlPAHouse").val()
			    + "&cld_pa_cityid=" + $("#insDtlPACity").val()
			    + "&cld_pa_districtid=" + $("#insDtlPADistrict").val()
			 
			    + "&cld_pa_pincode=" + $("#insDtlPAPincode option:selected").attr('pincodePA')
			    + "&cld_pa_country=" +  $("#insDtlPACountry").val()
			    + "&cld_pa_nearestlandmark=" + $("#insDtlPALandmark").val()
			    + "&cld_ra_addresstype=" + "0"
			    + "&cld_ra_address1=" + $("#insDtlCAPincode option:selected").text()
			    + "&cld_ra_address2=" + $("#insDtlCAStreet").val()
			    + "&cld_ra_address3=" + $("#insDtlCAHouse").val()
			    + "&cld_ra_cityid=" + $("#insDtlCACity").val()
			    + "&cld_ra_districtid=" +  $("#insDtlCADistrict").val()
			 
			    + "&cld_ra_pincode=" + $("#insDtlCAPincode option:selected").attr('pincodeCA')
			    + "&cld_ra_country=" +$("#insDtlCACountry").val()
			    + "&cld_ra_nearestlandmark=" + $("#insDtlCALandmark").val()
			    + "&emailid=" +  $("#insDtlEmail").val()
			    + "&salutation=" + $("#insDtlInitial option:selected").text()
			    + "&maritalstatus=" + $("#insDtlMaritialStatus").val()
			    + "&nationality=" + $("#insDtlNationality").val()
			    + "&p_businesstype=" +  $("#proposal").val()
			    + "&str_prod=" + $("#productname").val() 
			    +"&str_veh=" + $("#vehicle").val() 
			    + "&str_mod=" + $("#Model").val()
			    + "&str_var=" + $("#varience").val()
			    + "&prevInsId=" + prevInsCompanyID
			    + "&idv=" + basicIdv
			    + "&manufacturemonth=" + $("#month").val()
			    + "&manufactureyear=" + $("#year option:selected").text().trim()
			    + "&engineno=" + $("#engineNo").val() 
			    + "&chassis=" +  $("#chasisNo").val() 
			    + "&covers="+ $("#allCovers").val()
			    + "&coversValue="+  $("#allCoversValue").val()
			    + "&coverNo="+ 	$("#allCoversNumber").val()
			    + "&isvehiclehypothicated=" + isvehiclehypothicated
			    + "&financetypeid=" + financetypeid
			    + "&financiername=" + financiername
			    + "&financieraddress=" + financieraddress
			    + "&financiercity=" + financiercity
			    + "&isregaddsameascommadd=" + "true"
			    + "&isperaddsameascommadd=" + "true"
			    + "&isregaddsameasperadd=" + "true"
			    + "&str_state=" +$("#RTOState").val()
			    + "&str_city=" + $("#RTOSCity").val()
			    + "&registration_number=" +  $("#regNo").val() 
			    + "&isnewvehicle=" +$("#newVehicle").val()
			    + "&registration_date=" + $("#dateofReg").val()
			    + "&isbifuelkit=" +""
			    + "&islpgcng=" +"" + "&bfk_amt=" + ""
			    + "&pao_nomineename=" + $("#nomDtlFname").val()+" "+   $("#nomDtlLname").val()
			    + "&pao_nomineedob=" +  $("#nomDtlDOB").val()
			    + "&pao_nomineerel=" +  $("#nomDtlRelation").val()
			    + "&previnsname=" + ""
			    + "&prevpolicyno=" +  $("#prevPolicyCoverNo").val()
			    + "&prevstartdt=" + $("#prevFromDate").val()
			    + "&prevenddt=" + $("#prevToDate").val()
			    + "&ncbisapp=" + ""
			    + "&ncbeligicrit=" + ""
			    + "&ncbprevncb=" + prevNcbVal
			    + "&ncbcurncb=" + prevNcbVal
			    + "&voldedamt=" + ""
			    + "&eleamt=" + ""
			    + "&noneleamt=" +"" + "&unpasno=" + ""
			    + "&unpasamt=" + "" + "&antitheftno=" + ""
			    + "&antitheftname=" + ""
			    + "&automemno=" + "" + "&automemname=" + ""
			    + "&vdpolcovid=" + "" + "&vdamt=" + ""
			    + "&vdno=" + "" + "&vdname=" + ""
			    + "&patounpasno=" + "" + "&patounpasname=" + ""
			    + "&patounpaspolcovid=" + "" + "&patounpasamt=" + ""
			    + "&zone_id=" + $("#zoneId").val()+ "&ElectricalItem=" + $("#electricalAcc").val()
			    + "&NonElectricalItem=" + $("#nonElectricalAcc").val()
			    + "&eleamt=" + ""
			    + "&noneleamt=" + ""
			    + "&PI_CLD_RA_AreaID=" + insDtlCAAreaID 
			    + "&PI_CLD_PA_AreaID=" + insDtlPAAreaID 
			    + "&PI_CLD_CA_AreaID=" + insDtlCAAreaID 
			    + "&motorGroupResponseGroupId=" + $("#groupID").val()
			    + "&motorGroupResponseSessionId=" + $("#sessionId").val()
			    + "&motorGroupResponseGicId=2"  
			    + "&userId=0"
			    + "&userDesc=WEB"
			    + "&branchId=0"
			    + "&dateOfPurchase=" + $("#dateofReg").val() 
			    + "&policyType=" + $("#policyTypeId").val() 
			    + "&vehicleRegDate=" + $("#dateofReg").val() 
			    + "&IPAddress=" + ""
			    + "&str_proposalType=" + $("#proposalTf").val()
			    + "&str_productname=" +$("#productnameTf").val() ,
			'method' : 'post',
            'dataType' : 'json',
            'async' : true,
            success : function(resp) {
            	
    		$.each(resp, function(key, value) {
    		if(resp[key].XMLError != "Error"){	
    			nKey = parseInt(key) + 1;
       				
       				$("#premiumAmount").val(resp[key].TotalPremium) ;
       				$("#quotationNo").val(resp[key].QuoteNo) ;
       				$("#productCode").val(resp[key].PRODUCTCODE) ;
       				$("#userName").val(resp[key].USERID) ;
       				$("#password").val(resp[key].password) ;
       				$("#proposalNo").val(resp[key].ProposalNo);
       				$("#productCode").val(resp[key].ProductCode);
    				
      				var row2="<tr><td>" + nKey+ "</td>" +
    					"<td>" + resp[key].ProposalNo + "</td>" +
    					"<td>" + resp[key].QuoteNo + "</td>" +
    					"<td>" + resp[key].Productname + "</td>" +
    					"<td>" + resp[key].P_COVER_FROM + "</td>" +
    					"<td>" + resp[key].CLD_FORENAME +" " + resp[key].CLD_LASTNAME + "</td>" +
    					"<td>" + resp[key].FinalPremium + "</td></tr>";
    					
      			
      				if( resp[key].ErrorMessages ==""){
      					$("#proposalDetails").append(row2);
      					$("#premAmount").val(resp[key].FinalPremium);
      					$("#proposalDetailsDiv").show();
					}else{
						old_alert(resp[key].ErrorMessages); 
					}
    		}
    		else{
    			hideWait();
    		}
    			});
    		//proposalDetailTable = ($("#proposalDetails").DataTable());
    		hideWait()
    		},
    		error: function (resp) {
    			  hideWait()
    		},
    	});
}


////Reliance Proposal Ends Here/////


////Reliance Online Payment Starts Here/////
var payment="";
function makeReliancePaymentOnline() {
	showWait();
	 var url= "http://rzonews.reliancegeneral.co.in:91/PaymentIntegration/PaymentIntegration?userId=" 
		 + $("#userName").val() + "&proposalno=" +$("#proposalNo").val() + "&PaymentType=" + "1" + "&ProposalAmount=" + $("#premAmount").val() + 
		 "&Responseurl=" + "https://hopebox.co.in/UAT/ReliancePaymentStatus";
	  payment = window.open(url, "_blank");
	  geyPaymentDataFromDB();
	/*  setTimeout(function(){
		  geyPaymentDataFromDB();
	  },5000);*/
	  hideWait()
	
}

////Reliance Online Payment Ends Here/////
////Reliance Offline Payment Starts Here/////

function getReliancePolicyDetails() {
	var insDtlPAAreaID = $("#insDtlPAPincode").val();
	var insDtlCAAreaID = $("#insDtlCAPincode").val();
	var insDtlOAAreaID = $("#insDtlOAPincode").val();
	var prevInsCompanyID = $("#prevInsuranceCompany").val();
	if(!$("#prevInsuranceCompany").val()){
//		old_alert("In if prevINS");
		prevInsCompanyID="";
	}
	else{
		prevInsCompanyID = $("#prevInsuranceCompany").val();
	}
	
	var dateOfReg = $("#dateofReg").val();
//	old_alert("insDtlPAAreaID::: " +insDtlPAAreaID)
	if(insDtlPAAreaID == "--Select--" ){
//		old_alert("In if");
		insDtlPAAreaID="";
	}
	else{
		insDtlPAAreaID = $("#insDtlPAPincode").val();
	}
	if(insDtlCAAreaID == "--Select--"){
		insDtlCAAreaID="";
	}
	else{
		insDtlCAAreaID = $("#insDtlCAPincode").val();
	}
	if(insDtlOAAreaID == "--Select--"){
		insDtlOAAreaID="";
	}
	else{
		insDtlOAAreaID = $("#insDtlOAPincode").val();
	}
	
	$.ajax({
	   'url': "user/motorCalculator?pkg_name=PKG_MOTOR_CALC"
       + "&proc_name=RELIANCE"
       + "&table_name=AMCP"
       + "&request_for=FetchAccountDetails"
       + "&clienttype=" + $("#custTypeIdRel").val()
       + "&last_name=" + "" + "&midname=" + ""
       + "&forename=" + "" + "&corporatename=" + ""
       + "&occupationid=" + "" + "&dob=" + $("#entryDate").val()
       + "&gender=" + "" + "&phoneno=" + ""
       + "&mobileno=" + "" + "&clientaddress=" + ""
       + "&cld_ca_addresstype=" + "" + "&cld_ca_address1=" + ""
       + "&cld_ca_address2=" + "" + "&cld_ca_address3=" + ""
       + "&cld_ca_cityid=" + "" + "&cld_ca_districtid=" + ""
       + "&cld_ca_stateid=" + "" + "&cld_ca_pincode=" + ""
       + "&cld_ca_country=" + "" + "&cld_ca_nearestlandmark=" + ""
       + "&cld_pa_addresstype=" + "" + "&cld_pa_address1=" + ""
       + "&cld_pa_address2=" + "" + "&cld_pa_address3=" + ""
       + "&cld_pa_cityid=" + "" + "&cld_pa_districtid=" + ""
       + "&cld_pa_stateid=" + "" + "&cld_pa_pincode=" + ""
       + "&cld_pa_country=" + "" + "&cld_pa_nearestlandmark="
       + "" + "&cld_ra_addresstype=" + "" + "&cld_ra_address1="
       + "" + "&cld_ra_address2=" + "" + "&cld_ra_address3=" + ""
       + "&cld_ra_cityid=" + "" + "&cld_ra_districtid=" + ""
       + "&cld_ra_stateid=" + "" + "&cld_ra_pincode=" + ""
       + "&cld_ra_country=" + "" + "&cld_ra_nearestlandmark=" + ""
       + "&emailid=" + "" + "&salutation=" + "" + "&maritalstatus="
       + "" + "&nationality=" + "" + "&p_businesstype=" +  $("#proposal").val()
       + "&str_prod=" + $("#productname").val() 
       + "&str_veh=" +  $("#vehicle").val()
       + "&str_mod=" + $("#Model").val() 
       + "&str_var=" + $("#varience").val() + "&idv=" + ""
       + "&manufacturemonth=" +  $("#month").val()
       + "&manufactureyear=" + $("#year option:selected").text() + "&engineno="
       + "" + "&chassis=" + "" + "&isvehiclehypothicated=" + ""
       + "&financetypeid=" + "" + "&financiername=" + "" + "&financieraddress=" + ""
       + "&financiercity=" + "" + "&isregaddsameascommadd=" + ""
       + "&isperaddsameascommadd=" + "" + "&isregaddsameasperadd=" + ""
       + "&str_state=" + $("#RTOState").val() 
       + "&str_city=" +  $("#RTOSCity").val()
       + "&registration_number=" +  $("#regNo").val()  + "&isnewvehicle=" + $("#newVehicle").val()
       + "&registration_date=" + $("#entryDate").val() + "&covers=" + ""
       + "&isbifuelkit=" + "" + "&islpgcng=" + "" + "&bfk_amt=" + ""
       + "&pao_nomineename=" + "" + "&pao_nomineedob=" + $("#entryDate").val()
       + "&pao_nomineerel=" + "" + "&previnsname=" + ""
       + "&prevpolicyno=" + $("#prevPolicyCoverNo").val()
	   + "&prevstartdt=" + $("#entryDate").val()
       + "&prevenddt=" + $("#entryDate").val() + "&ncbisapp=" + ""
       + "&ncbeligicrit=" + "" + "&ncbprevncb=" + ""
       + "&ncbcurncb=" + "" + "&voldedamt=" + "" + "&eleamt=" + ""
       + "&noneleamt=" + "" + "&unpasno=" + "" + "&unpasamt=" + ""
       + "&antitheftno=" + "" + "&antitheftname=" + "" + "&automemno="
       + "" + "&automemname=" + "" + "&vdpolcovid=" + "" + "&vdamt=" + ""
       + "&vdno=" + "" + "&vdname=" + "" + "&patounpasno=" + ""
       + "&patounpasname=" + "" + "&patounpaspolcovid=" + ""
       + "&patounpasamt=" + "" 
       + "&zone_id=" + $("#zoneId").val()
       + "&ElectricalItem=" + ""
       + "&NonElectricalItem=" + ""
       + "&eleamt=" + ""
       + "&noneleamt=" + ""
       + "&PI_CLD_RA_AreaID=" + insDtlCAAreaID
       + "&PI_CLD_PA_AreaID=" + insDtlPAAreaID
       + "&PI_CLD_CA_AreaID=" +insDtlCAAreaID
       + "&motorGroupResponseGroupId=" + $("#groupID").val()
	    + "&motorGroupResponseSessionId=" + $("#sessionId").val()
	    + "&motorGroupResponseGicId=2"  
	    + "&userId=0"
	    + "&userDesc=WEB"
	    + "&branchId=0"
	    + "&dateOfPurchase=" + $("#dateofReg").val() 
	    + "&policyType=" + $("#policyTypeId").val() 
	    + "&covers="+ $("#allCovers").val()
	    + "&coversValue="+  $("#allCoversValue").val()
	    + "&coverNo="+ 	$("#allCoversNumber").val()
	    + "&vehicleRegDate=" + $("#dateofReg").val() ,
       type : 'post',
       dataType : 'json',
       async : false,
       success : function(resp) {
   		$.each(resp, function(key, value) {
   				nKey = parseInt(key) + 1;
   				$("#userName").val(resp[key].UserID) ;
   				$("#password").val(resp[key].Password) ;
   				$("#ModeOfPayment").val(resp[key].PaymentMode);
   				$("#CDBalanceAmount").val(resp[key].CDBalanceAmount) ;
   				$("#CDTBalanceAmount").val(resp[key].CDTBalanceAmount) ;
   				/*old_alert($("#userName").val());
   				old_alert($("#password").val());*/
   			});
   		},
   	});
	
	
}

function makeReliancePaymentExternal() {
	
	 if ($("#paymentMode").val() == "CDT") {
		 $("#paymentMode").val("3");
     } else if ($("#paymentMode").val() == "CD") {
    	 $("#paymentMode").val("2");
    	     }
	 $.ajax({
     'url' :"user/motorCalculatorRelPayment?request_for=" + "paymentExternal"
             + "&username=" +$("#userName").val() 
             + "&password=" + $("#password").val() 
             + "&proposalNo=" +$("#proposalNo").val()  
             + "&paymentMode=" +  $("#paymentMode").val()
             + "&motorGroupResponseGroupId=" + $("#groupID").val()
             + "&motorGroupResponseSessionId=" + $("#sessionId").val()
             + "&motorGroupResponseGicId=" +  $("#companyDtl_companyId").val() 
             + "&userId=0"
             + "&userDesc=WEB" 
             + "&branchId=0"
             + "&quoteNo=" +  $("#quotationNo").val()
             + "&IPAddress=",
            type : 'post',
            dataType : 'json',
           	async : false,
             success : function(resp) {
             
             },
             error : function(resp) {
				
			}
    
	 });
}
////Reliance Offline Payment Ends Here/////

////Reliance Policy Generation  Starts Here/////

function getReliancePolicy() {
	var policyUrl= "http://rzonews.reliancegeneral.co.in:91/API/Service/GeneratePolicyschedule?PolicyNo="+ $("#PolicyNumber").val() +
     "&ProductCode=" + $("#productCode").val();
	window.open(policyUrl, "_blank");
}


////Reliance  Policy Generation Ends Here/////

//////// Integration Of Reliance General Insurance Company Ends Here ///////



////////Integration Of Liberty Videocon Insurance Company Starts Here ///////

////Videocon Premium Starts Here//////

function getPremiumResponseForVideocon() {
	showWait();
	
	$("#companyDtl_companyId").val("34") ;
	
	var gicId = $("#companyDtl_companyId").val();
	proposalTypeUsed(gicId);
	
	
	
	findGicCompany($("#companyDtl_companyId").val());
	var vehicleId = $("#vehicle").val();
	var modelId = $("#Model").val();
	var varianceId = $("#varience").val();
	var customerTypeId =$("#customer").val();

	var mfrMonthId = $("#month").val();
	var prevPolicyType = $("#prevPolicyType").val();
	var prevPolicyCoverNo = $("#prevPolicyCoverNo").val();
	var nonEleAmt = $("#nonEleTotalTextfiels").val();
	var eleAmt = $("#eleTotalTextfiels").val();
	var prevToDate = $("#prevToDate").val();
	var applicantid = $("#applicantname").val();
	var productnameid = $("#productname").val();
	//var proposal = $("#proposal option:selected").text();
	var dateOfReg = $("#dateofReg").val();
	var manfYr= $("#year :selected").text();
	var productName = $("#productnameTf").val();
	var insDtlDob = ""
	var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
	var aaiName = $("#aaiName").val();
	var aaiMembershipNo = $("#aaiMembershipNo").val();
	var aaiCode = $("#aaiCode").val();
	var aaiExpiryDate = $("#aaiExpiryDate").val();
	var aaiAssociationName = $("#aaiAssociationName").val();
	var fuelKit = $("#fuelKit").val();
	var prevNoOfClaims = $("#prevNoOfClaims").val();
	var prevInsuranceCompany = $("#prevInsuranceCompany  option:selected").text();

	if(prevInsuranceCompany == ""){
		prevInsuranceCompany="";
	}else{
		prevInsuranceCompany = $("#prevInsuranceCompany  option:selected").text();
	}	
	var financedBy= $("#finDtlFinType").val();
	var isHypothicated="";
	if(!$("#finDtlFinType").val()){
		isHypothicated = "false";
	}else{
		 isHypothicated= "true";
	}

	$.ajax({
		'url' :"motorCalculatorVideocon?pkg_name=PKG_MOTOR_CALC"
				+ "&proc_name=LIB_VID"
				+ "&table_name=AMCP&request_for="
				+ "premium" + "&str_ClientType=" + customerTypeId + "&str_lastName="
				+ "" + "&str_MidName="
				+ "" + "&str_ForeName="
				+ "" + "&str_CorporateName=" + ""
				+ "&str_OccupationID=" + "" + "&str_DOB=" + insDtlDob
				+ "&str_Gender=" + "" + "&str_PhoneNo=" + ""
				+ "&str_MobileNo=" + "" + "&str_ClientAddress=" + ""
				+ "&str_CLD_CA_AddressType=" + ""
				+ "&str_CLD_CA_Address1=" + ""
				+ "&str_CLD_CA_Address2=" + ""
				+ "&str_CLD_CA_Address3=" + ""
				+ "&str_CLD_CA_AreaID=" + ""
				+ "&str_CLD_CA_Pincode=" + ""
				+ "&str_CLD_CA_Country=" + ""
				+ "&str_CLD_CA_NearestLandmark=" + ""
				+ "&str_CLD_PA_AddressType=" + ""
				+ "&str_CLD_PA_Address1=" + ""
				+ "&str_CLD_PA_Address2=" + ""
				+ "&str_CLD_PA_Address3=" + ""
				+ "&str_CLD_PA_AreaID=" + ""
				+ "&str_CLD_PA_Pincode=" + ""
				+ "&str_CLD_PA_Country=" + ""
				+ "&str_CLD_PA_NearestLandmark=" + ""
				+ "&str_EmailID=" + ""
				+ "&str_Salutation=" + ""
				+ "&str_MaritalStatus=" + ""
				+ "&str_Nationality=" + ""
				+ "&str_PANno=" + ""
				+ "&str_P_BusinessType=" +  $("#proposal").val()
				+ "&str_ProductID=" + productnameid
				+ "&str_VehID=" + vehicleId
				+ "&str_ModelID=" + modelId
				+ "&str_VarID=" + varianceId
				+ "&str_EngineNo=" + ""
				+ "&str_Chassis=" + ""
				+ "&str_IsVehicleHypothicated=" + "false"
				+ "&str_FinanceTypeID=" + ""
				+ "&str_FinancierName=" + ""
				+ "&str_AgreementType=" + ""
				+ "&str_FinancierAddress=" + ""
				+ "&str_StateOfRegistrationID=" + $("#RTOState").val()
				+ "&str_Rto_City=" + $("#RTOSCity").val()
				+ "&str_ISNewVehicle=" + $("#newVehicle").val()
				+ "&str_ManfMonth=" + mfrMonthId
				+ "&str_RegNo1=" +  $("#regNo1").val() 
				+ "&str_RegNo2=" +  $("#regNo2").val() 
				+ "&str_RegNo3=" +  $("#regNo3").val() 
				+ "&str_RegNo4=" +  $("#regNo4").val()  
				+ "&str_Covers="+$("#allCovers").val()
				+ "&str_CoversValue="+ $("#allCoversValue").val()
				+ "&str_IsBiFuelKit=" + ""
				+ "&str_ISLpgCng=" + ""
				+ "&str_BFK_Amt=" + ""
				+ "&str_PAO_NomineeRepRel=" + ""
				+ "&str_PAO_NomineeRel=Brother" 
				+ "&str_PrevInsName=IFFCO TOKIO"
				+ "&str_PrevPolicyNo=" + prevPolicyCoverNo
				+ "&str_PrevPolicyType=" +prevPolicyType
				+ "&str_PrevStartDt=" + $("#prevFromDate").val()
				+ "&str_PrevEndDt=" + $("#prevToDate").val()
				+ "&str_PrevClaimNo=" + ""
				+ "&str_PrevClaimAmt=" + ""
				+ "&str_NCBIsApp=" + ""
				+ "&str_NCBEligiCrit=" + ""
				+ "&str_NCBPrevNCB=" + "0"
				+ "&str_NCBCurNCB=" + ""
				+ "&str_IsTrailerAttached=" + "No"
				+ "&str_IsInspDone=" + "true"
				+ "&str_InspDoneByWhom=" + "vikram"
				+ "&str_InspReportDate=" + $("#entryDate").val()
				+ "&str_InspDate=" + $("#entryDate").val()
				+ "&str_ZONE_ID=" + $("#zoneId").val()
				+ "&str_isFullQuote=" + "false"
				+ "&str_ManfYear=" + manfYr
				+ "&str_DeliveryDate=" + dateOfReg
				+ "&str_RegistrationDate=" + dateOfReg
				+ "&str_VehicleIDV=" + "0"
				+ "&str_NoOfPassengerForLLToPaidDriver=" + ""
				+ "&str_NoOfPassengerForLLToEmployee=" + ""
				+ "&str_NoOfPerunnamed=" + ""
				+ "&str_UnnamedPASI=" + ""
				+ "&str_lstAccessories_Description=" + ""
				+ "&str_lstAccessories_Make=" + ""
				+ "&str_lstAccessories_Model=" + ""
				+ "&str_lstAccessories_ManufactureYear=" + ""
				+ "&str_lstAccessories_SerialNo=" + ""
				+ "&str_lstAccessories_SumInsured=" + ""
				+ "&str_lstNonElecAccessories_Description=" + ""
				+ "&str_lstNonElecAccessories_Make=" + ""
				+ "&str_lstNonElecAccessories_Model=" + ""
				+ "&str_lstNonElecAccessories_ManufactureYear=" + ""
				+ "&str_lstNonElecAccessories_SerialNo=" + ""
				+ "&str_lstNonElecAccessories_SumInsured=" + ""
				+ "&str_NoOfPernamed=" + ""
				+ "&str_CustType=" +  $("#custType").val()
				+ "&str_NamedPASI=" + ""
				+ "&str_NoOfPaidDriverPassenger=" + ""
				+ "&str_PAToPaidDriverSI=" + ""
				+ "&str_FuelSI=" + ""
				+ "&str_lstTrailer_ChassisNumber=" + ""
				+ "&str_lstTrailer_EngineNumber=" + ""
				+ "&str_lstTrailer_SumInsured=" + ""
				+ "&str_FiberGlassSI=" + ""
				+ "&str_AAIMembshipNumber=" + aaiMembershipNo
				+ "&str_AAIAssociationCode=" + aaiCode
				+ "&str_AAIAssociationName=" + aaiAssociationName
				+ "&str_AAIMembshipExpiryDate=" + aaiExpiryDate
				+ "&str_IsAntiTheftDeviceCertifiedByARAI=" + "False"
				+ "&str_VoluntaryExcessAmt=" + ""
				+ "&str_NoNomineeDetails=" + ""
				+ "&str_NomineeFirstName=" + "vikram"
				+ "&str_NomineelastName=" + "rao"	
				+ "&str_NomineeRelationship=" + "brother" 
				+ "&str_OtherRelation=" + ""
				+ "&str_IsMinor=" + ""
				+ "&str_RepFirstName=" + ""
				+ "&str_RepLastName=" + ""
				+ "&str_RepOtherRelation=" + ""
				+ "&str_GAPCoverSI=" + ""
				+ "&str_KeyLossCoverSI=" + ""
				+ "&str_TPSource=" + ""
				+ "&str_PinCodeLocality=" + ""
				+ "&str_MailingPinCodeLocality=" + ""
				+ "&str_PermanentLocationSameAsMailLocation=" + ""
				+ "&str_IsEIAAvailable=" + ""
				+ "&str_EIAAccNo=" + ""
				+ "&str_IsEIAPolicy=" + ""
				+ "&str_EIAAccWith=" + ""
				+ "&str_EIAPanNo=" + ""
				+ "&str_EIAUIDNo=" + ""
				+ "&str_IsNilDepOptedInPrevPolicy=" + "false"
				+ "&str_GSTIN=" + ""
				+ "&str_buyersState=" + "MAHARASHTRA"
				+ "&ElectricalItem=" +  $("#electricalAcc").val() 
				+ "&NonElectricalItem=" + $("#nonElectricalAcc").val()
				+ "&eleamt=" + ""+ eleAmt
				+ "&noneleamt=" + ""+ nonEleAmt
				+ "&str_PolicyType=" + $("#policyTypeId").val() 
				+ "&str_ownDriverAppointeeName=" + ""
				+ "&str_CoversNo="+	$("#allCoversNumber").val()
				+ "&str_prevGICId=" + ""
				+ "&str_prevPolicyAge=" + ""
				+ "&str_fuelType=" + fuelKit
				+ "&paownerdriver=" + $("#customer  option:selected").text()
				+ "&preInsNcbAmount="
				+ "&preInsNoOfClaim=" + prevNoOfClaims
				+ "&groupId=" + $("#groupID").val()
				+ "&motorGroupResponseSessionId=" + $("#sessionId").val()
				+ "&motorGroupResponseGicId="+$("#companyDtl_companyId").val()  
				+ "&str_ProductName=" + productName
				+ "&userId=0"
				+ "&userDesc=WEB"
				+ "&branchId=0"
				+ "&IpAddress=127.0.0.0",	
		type : 'post',
		dataType : 'json',
		async : true,
		success : function(resp) {
//			destroyDataTable();
			hideWait()
		// var row=1;
			$.each(resp, function(key, value) {
				nKey = parseInt(key) + 1;
				
				var sr=$("#compSerial").val() ;
				sr++;
				$("#compSerial").val(sr);
				var row = '<input type="checkbox" name="checkbox" id="companyCheck'+sr+'" onclick="selectCompanyAction('+sr+');">';
								var row2="<tr><td>" + row + "</td>" +
										"<td></td>" +
										"<td>" + resp[key].Company + "</td>" +
										"<td>" + resp[key].POLICYSTARTDATE + "</td>" +
										"<td>" + resp[key].POLICYTENURE + "</td>" +
										"<td>" + resp[key].POLICYENDDATE + "</td>" +
										"<td>" + resp[key].Discount + "</td>" +
										"<td>"+0+"</td>" +
										"<td>"+ resp[key].TotalODPremiumValue + "</td>" +
										"<td>"+ resp[key].TotalTPPremiumValue + "</td>" +
										"<td>"+ resp[key].NetPremium + "</td>" +
										"<td>"+ resp[key].GST + "</td>" +
										"<td>"+ resp[key].TotalPremium + "</td>" +
										"<td>"+ $("#proposalTf").val() + "</td>" +
										"<td>"+ resp[key].Discount + "</td>" +
										"<td>"+ 0 + "</td>" +
										"<td>" + resp[key].GICID + "</td>" +
										"<td style='display:none;'>" + 0 + "</td>" +
										"<td style='display:none;' >" + 0 + "</td>" +
										"<td style='display:none;'>" + 0 + "</td>" +
										"<td style='display:none;'>" + 0 + "</td>" +
										"<td style='display:none;'>" + 0 + "</td>" +
												"</tr>";
								//old_alert(row2);
								if(resp[key].ErrorText == null){
								$("#companyDetails tbody").append(row2);
								//row ++;
								}else{
									old_alert(resp[key].ErrorText);
								}
							});
			$("#companyDtlDiv").show();
			if(!$.fn.DataTable.isDataTable('#companyDetails')){
				$("#companyDetails").DataTable({
						"lengthMenu": [5, 10, 20, 50],
						"pageLength": 5,});
			}
			
		},
		
		error: function () {
			hideWait()
		}
	});

}


////Videocon Premium Ends Here//////

////Videocon Proposal Starts Here//////
function getProposalResponseForVideocon() {
	showWait();
		
	var gicId = $("#companyDtl_companyId").val();
	proposalTypeUsed(gicId);
	
var vehicleId = $("#vehicle").val();
var modelId = $("#Model").val();
var varianceId = $("#varience").val();
var customerTypeId =$("#customer").val();
var customerType =$('#customer option:selected').text();
//old_alert("customerType::" +customerType);
var mfrMonthId = $("#month").val();
var prevPolicyType = $("#prevPolicyType").val();
var prevPolicyCoverNo = $("#prevPolicyCoverNo").val();
var nonEleAmt = $("#nonEleTotalTextfiels").val();
var eleAmt = $("#eleTotalTextfiels").val();
var prevToDate = $("#prevToDate").val();
var applicantid = $("#applicantname").val();
var productnameid = $("#productname").val();
var proposalid = $("#proposal").val();
//var proposal = $("#proposal option:selected").text();
var dateOfReg = $("#dateofReg").val();
var manfYr= $("#year :selected").text();
var productName = $("#productnameTf").val();
//var insDtlDob= $("#insDtlDob").val();
/* if(insDtlDob == "undefined")
{
old_alert("insDtlDob:::,,,,,," + insDtlDob); */
var insDtlDob = ""
//}
var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
var aaiName = $("#aaiName").val();
var aaiMembershipNo = $("#aaiMembershipNo").val();
var aaiCode = $("#aaiCode").val();
var aaiExpiryDate = $("#aaiExpiryDate").val();
var aaiAssociationName = $("#aaiAssociationName").val();
var fuelKit = $("#fuelKit").val();
var prevNoOfClaims = $("#prevNoOfClaims").val();
if(prevNoOfClaims == "--Select==")
{
	prevNoOfClaims = "0" ;
}

var insDtlInitial = $("#insDtlInitial option:selected").text();
var insDtlFname = $("#insDtlFname").val();
var insDtlMname = $("#insDtlMname").val();
var insDtlLname = $("#insDtlLname").val();
var insDtlMaritialStatus = $("#insDtlMaritialStatus").val();
var insDtlAadharEnrollNo = $("#insDtlAadharEnrollNo").val();
var insDtlAadharNo = $("#insDtlAadharNo").val();
var insDtlPanNo = $("#insDtlPanNo").val();
var insDtlEmail = $("#insDtlEmail").val();
var insDtlNationality = $("#insDtlNationality").val();
var insDtlCAName = $("#insDtlCAName").val();
var insDtlCACountry = $("#insDtlCACountry").val();
var insDtlCAState = $("#insDtlCAState").val();
var insDtlCADistrict = $("#insDtlCADistrict").val();
var insDtlCACity = $("#insDtlCACity").val();
var insDtlCAPincode = $("#insDtlCAPincode option:selected").attr('pincodeCA');
var insDtlCALandmark = $("#insDtlCALandmark").val();
var insDtlCAStreetList = $("#insDtlCAStreetList").val();
var insDtlCAHouseList = $("#insDtlCAHouseList").val();
var insDtlPAName = $("#insDtlCAHouseList").val();
var insDtlPACountry = $("#insDtlPACountry").val();
var insDtlPAState = $("#insDtlPAState").val();
var insDtlPADistrict = $("#insDtlPADistrict").val();
var insDtlPACity = $("#insDtlPACity").val();
var insDtlPAPincode = $("#insDtlPAPincode option:selected").attr('pincodePA');
var insDtlPALandmark = $("#insDtlPALandmark").val();
var insDtlPALandmarkList = $("#insDtlPALandmarkList").val();
var insDtlPAStreet = $("#insDtlPAStreet").val();
var insDtlPAStreetList = $("#insDtlPAStreetList").val();
var insDtlPAHouse = $("#insDtlPAHouse").val();
var insDtlPAHouseList = $("#insDtlPAHouseList").val();
var insDtlOAOfcName = $("#insDtlOAOfcName").val();
var insDtlOACountry = $("#insDtlOACountry").val();
var insDtlOAState = $("#insDtlOAState").val();
var insDtlOADistrict = $("#insDtlOADistrict").val();
var insDtlOACity = $("#insDtlOACity").val();
var insDtlOAPincode = $("#insDtlOAPincode option:selected").text();
var insDtlOAPincode = $("#insDtlOAPincode").val();
var insDtlCAPincodeText = $("#insDtlCAPincode option:selected").text();
var insDtlPAPincodeText = $("#insDtlPAPincode option:selected").text();
var insDtlOALandmark = $("#insDtlOALandmark").val();
var insDtlOAStreet = $("#insDtlOAStreet").val();
var insDtlOAStreetList = $("#insDtlOAStreetList").val();
var insDtlOAHouse = $("#insDtlOAHouse").val();
var insDtlOAHouseList = $("#insDtlOAHouseList").val();
var insDtlDob = $("#insDtlDob").val();
var insDtlPhoneNo = $("#insDtlPhoneNo").val();
var insDtlMobileNo = $("#insDtlMobileNo").val();
var insDtlCAStreet = $("#insDtlCAStreet").val();
var insDtlCAHouse = $("#insDtlCAHouse").val();
var insDtlPAStreet = $("#insDtlPAStreet").val();
var insDtlPAHouse = $("#insDtlPAHouse").val();
var engineNo = $("#engineNo").val();
var chasisNo = $("#chasisNo").val();
var nomDtlInitial = $("#nomDtlInitial").val();
var nomDtlFname = $("#nomDtlFname").val();
var nomDtlMname = $("#nomDtlMname").val();
var nomDtlLname = $("#nomDtlLname").val();
var nomDtlRelation = $("#nomDtlRelation option:selected").text();
var nomRelation = $("#nomDtlRelation").val();

var nomDtlAdharNo = $("#nomDtlAdharNo").val();
var nomDtlDOB = $("#nomDtlDOB").val();
var nomDtlCountry = $("#nomDtlCountry").val();
var nomDtlState = $("#nomDtlState").val();
var nomDtlDistrict = $("#nomDtlDistrict").val();
var nomDtlCity = $("#nomDtlCity").val();
var nomDtlPincode = $("#nomDtlPincode option:selected").attr('nomDtlpincode');
var bifuelKit = $("#fuelKit").val();
var isbifuelKit = "";
	if(!$("#fuelKit").val()==""){
		 isbifuelKit = "no";
	}else{
		 isbifuelKit = "yes";
	}

var prevInsuranceCompany = $("#prevInsuranceCompany").val();
var preInsNoOfClaim =$("#prevNoOfClaims").val();
//old_alert(preInsNoOfClaim);
if( preInsNoOfClaim == "--Select--")
{
preInsNoOfClaim = "0";
}else{
	 preInsNoOfClaim =$("#prevNoOfClaims").val();
}
var insDtlGender="";
if (document.getElementById('insDtlGenMale').checked) {
insDtlGender = document.getElementById('insDtlGenMale').value;
}
else if (document.getElementById('insDtlGenFemale').checked) {
insDtlGender = document.getElementById('insDtlGenFemale').value;
}
else if (document.getElementById('insDtlGenOther').checked) {
insDtlGender = document.getElementById('insDtlGenOther').value;
}
/*var prevInsuranceCompany = $("#prevInsuranceCompany :selected").text();
old_alert("prevInsuranceCompany:::---" +prevInsuranceCompany)
if(prevInsuranceCompany.localeCompare("Prev. Insurance Company"))
{*/
var	prevInsuranceCompany="";
/*}	*/
//   old_alert(proposal);
//old_alert("proposal customer type::: " + customerType);


//old_alert("entryDate:::" + entryDate);
var insDtlPAAreaID = $("#insDtlPAPincode").val();
var insDtlCAAreaID = $("#insDtlCAPincode").val();
var insDtlOAAreaID = $("#insDtlOAPincode").val();
//old_alert("insDtlPAAreaID::: " +insDtlPAAreaID)
if(insDtlPAAreaID == "--Select--" ){
//old_alert("In if");
insDtlPAAreaID="";
}
else{
insDtlPAAreaID = $("#insDtlPAPincode").val();
}
if(insDtlCAAreaID == "--Select--"){
insDtlCAAreaID="";
}
else{
insDtlCAAreaID = $("#insDtlCAPincode").val();
}
if(insDtlOAAreaID == "--Select--"){
insDtlOAAreaID="";
}
else{
insDtlOAAreaID = $("#insDtlOAPincode").val();
}
$.ajax({
	
	'url' : "user/motorCalculatorVideocon?pkg_name=PKG_MOTOR_CALC"
   + "&proc_name=LIB_VID" + "&table_name=AMCP" + "&request_for=proposal" + "&str_ClientType=" + customerTypeId 
   + "&str_lastName="+ insDtlLname 
   + "&str_MidName="+ insDtlMname 
   + "&str_ForeName=" + insDtlFname 
   + "&str_CorporateName=" + ""
   + "&str_OccupationID=" + "" 
   +"&str_DOB=" + insDtlDob
   + "&str_Gender=" + insDtlGender 
   + "&str_PhoneNo=" + insDtlPhoneNo
   + "&str_MobileNo=" + insDtlMobileNo + "&str_ClientAddress=" + ""
   + "&str_CLD_CA_AddressType=" + ""
   + "&str_CLD_CA_Address1=" + insDtlCAPincodeText
   + "&str_CLD_CA_Address2=" + insDtlCAStreet
   + "&str_CLD_CA_Address3=" + insDtlCAHouse
   + "&str_CLD_CA_AreaID=" + insDtlCAAreaID
   + "&str_CLD_CA_Pincode=" + insDtlCAPincode
   + "&str_CLD_CA_Country=" + insDtlCACountry
   + "&str_CLD_CA_NearestLandmark=" +insDtlCALandmark
   + "&str_CLD_PA_AddressType=" + ""
   + "&str_CLD_PA_Address1=" + insDtlPAPincodeText
   + "&str_CLD_PA_Address2=" + insDtlPAStreet
   + "&str_CLD_PA_Address3=" + insDtlPAHouse
   + "&str_CLD_PA_AreaID=" + insDtlPAAreaID
   + "&str_CLD_PA_Pincode=" + insDtlPAPincode
   + "&str_CLD_PA_Country=" + insDtlPACountry
   + "&str_CLD_PA_NearestLandmark=" +insDtlPALandmark
   + "&str_EmailID=" +insDtlEmail
   + "&str_Salutation=" + insDtlInitial
   + "&str_MaritalStatus=" + insDtlMaritialStatus
   + "&str_Nationality=" + insDtlNationality
   + "&str_PANno=" + insDtlPanNo
   + "&str_P_BusinessType=" + proposalid
   + "&str_ProductID=" + productnameid
   + "&str_VehID=" + vehicleId
   + "&str_ModelID=" + modelId
   + "&str_VarID=" + varianceId
   + "&str_EngineNo=" + engineNo
   + "&str_Chassis=" +chasisNo
   + "&str_IsVehicleHypothicated=" + "false"
   + "&str_FinanceTypeID=" + ""
   + "&str_FinancierName=" + "jshdj"
   + "&str_StateOfRegistrationID=" + $("#RTOState").val()
   + "&str_Rto_City=" + $("#RTOSCity").val()
   + "&str_ISNewVehicle=" + $("#newVehicle").val()
   + "&str_ManfMonth=" + mfrMonthId
   + "&str_RegNo1=" +  $("#regNo1").val() 
   + "&str_RegNo2=" +  $("#regNo2").val() 
   + "&str_RegNo3=" +  $("#regNo3").val() 
   + "&str_RegNo4=" +  $("#regNo4").val() 
   + "&lstcAccessoriesList=" + ""
   + "&str_IsBiFuelKit=" + isbifuelKit
   + "&str_ISLpgCng=" + "CNG"
   + "&str_BFK_Amt=" + ""
   + "&str_PAO_NomineeRepRel=" +nomRelation
   + "&str_PAO_NomineeRel=" + nomDtlRelation.trim()
   + "&str_PrevInsName=" + "IFFCO TOKIO"
   + "&str_PrevPolicyNo=" + "rtrtrrr"
   + "&str_PrevPolicyType=" + "PackagePolicy"
   + "&str_PrevStartDt=" + $("#prevFromDate").val()
   + "&str_PrevEndDt=" + $("#prevToDate").val()
   + "&str_PrevClaimNo=" + ""
   + "&str_PrevClaimAmt=" + ""
   + "&str_NCBIsApp=" + "" + "&str_NCBEligiCrit=" + ""
   + "&str_NCBPrevNCB=" + "0"
   + "&str_NCBCurNCB=" + ""
   + "&str_IsTrailerAttached=" + "No"
   + "&str_IsInspDone=" + "true"
   + "&str_InspDoneByWhom=" + "vikram"
   + "&str_InspReportDate=" + $("#entryDate").val()
   + "&str_InspDate=" + $("#entryDate").val()
   + "&str_ZONE_ID=" +$("#zoneId").val()
   + "&str_isFullQuote=" + "true"
   + "&str_ManfYear=" +manfYr.trim()
   + "&str_DeliveryDate=" + dateOfReg
   + "&str_RegistrationDate=" + dateOfReg
   + "&str_VehicleIDV=" + "0" 
   + "&str_NoOfPassengerForLLToPaidDriver=" + ""
   + "&str_NoOfPassengerForLLToEmployee=" + ""
   + "&str_NoOfPerunnamed=" + ""
   + "&str_UnnamedPASI=" + ""
   + "&str_lstAccessories_Description=" + ""
   + "&str_lstAccessories_Make=" + ""
   + "&str_lstAccessories_Model=" + ""
   + "&str_lstAccessories_ManufactureYear=" + ""
   + "&str_lstAccessories_SerialNo=" + ""
   + "&str_lstAccessories_SumInsured=" + ""
   + "&str_lstNonElecAccessories_Description=" + ""
   + "&str_lstNonElecAccessories_Make=" + ""
   + "&str_lstNonElecAccessories_Model=" + ""
   + "&str_lstNonElecAccessories_ManufactureYear=" + ""
   + "&str_lstNonElecAccessories_SerialNo=" + ""
   + "&str_lstNonElecAccessories_SumInsured=" + ""
   + "&str_NoOfPernamed=" + ""
   + "&str_CustType=" +  $("#custType").val()
   + "&str_NamedPASI=" + ""
   + "&str_NoOfPaidDriverPassenger=" + ""
   + "&str_PAToPaidDriverSI=" + ""
   + "&str_FuelSI=" + ""
   + "&str_lstTrailer_ChassisNumber=" + ""
   + "&str_lstTrailer_EngineNumber=" + ""
   + "&str_lstTrailer_SumInsured=" + ""
   + "&str_FiberGlassSI=" + ""
 
   + "&str_AAIMembshipNumber=" + aaiMembershipNo
   + "&str_AAIAssociationCode=" + aaiCode
   + "&str_AAIAssociationName=" + aaiAssociationName
   + "&str_AAIMembshipExpiryDate=" + aaiExpiryDate 
   + "&str_IsAntiTheftDeviceCertifiedByARAI=" + "False"
   + "&str_VoluntaryExcessAmt=" + ""
   + "&str_NoNomineeDetails=" + "false"
   + "&str_NomineeFirstName=" + nomDtlFname
   + "&str_NomineelastName=" + nomDtlLname
   + "&str_NomineeRelationship=" + nomDtlRelation.trim()
   + "&str_OtherRelation=" + nomDtlRelation.trim()
   + "&str_IsMinor=" + "no"
   + "&str_RepFirstName=" + ""
   + "&str_RepLastName=" + ""
   + "&str_RepOtherRelation=" + ""
   + "&str_GAPCoverSI=" + ""
   + "&str_KeyLossCoverSI=" + ""
   + "&str_TPSource=" + "TPService"
   + "&str_PinCodeLocality=" + ""
   + "&str_MailingPinCodeLocality=" + ""
   + "&str_PermanentLocationSameAsMailLocation=" + "yes"
   + "&str_IsEIAAvailable=" + ""
   + "&str_EIAAccNo=" + ""
   + "&str_IsEIAPolicy=" + ""
   + "&str_EIAAccWith=" + ""
   + "&str_EIAPanNo=" + ""
   + "&str_EIAUIDNo=" + ""
   + "&str_IsNilDepOptedInPrevPolicy=" + "false"
   + "&str_GSTIN=" + ""
   + "&str_buyersState=" + "MAHARASHTRA"
   + "&ElectricalItem=" + $("#electricalAcc").val()
   + "&NonElectricalItem=" + $("#nonElectricalAcc").val()
   + "&eleamt=" +  ""+ eleAmt
   + "&noneleamt=" +  ""+ nonEleAmt
   + "&groupId=" + $("#groupID").val()
   + "&motorGroupResponseSessionId=" + $("#sessionId").val()
   + "&motorGroupResponseGicId="+$("#companyDtl_companyId").val()  
   + "&userId=0"
   + "&userDesc=WEB"
   + "&branchId=0"
    + "&IpAddress="+""
     + "&str_PolicyType=" + $("#policyTypeId").val() 
   + "&str_ownDriverAppointeeName=" + ""
   + "&str_Covers="+$("#allCovers").val()
   + "&str_CoversValue="+ $("#allCoversValue").val()
   + "&str_CoversNo=" + $("#allCoversNumber").val()
   + "&str_prevGICId=" + prevInsuranceCompany
    + "&str_prevPolicyAge=" + ""
    + "&str_fuelType=" + fuelKit
    + "&paownerdriver=" +  $("#customer  option:selected").text()
    + "&str_ProductName=" + productName
   + "&preInsNcbAmount=" +""
   + "&preInsNoOfClaim=" + preInsNoOfClaim,
  
   'method' : 'post',
   'dataType' : 'json',
   'async' : false,
	/*success : function(data) {
			old_alert("in success::" + data);
				$("#companyDetails").DataTable({
					'data' : data,
					"columns": [
							{ "data": index},
								{ "data": "ProposalNumber"},
								{ "data": "QuotationNumber"},
								{ "data": "productName"},
								{ "data": "POLICYSTARTDATE"},
								{ "data": "FNAME"},
								{ "data": "LNAME"},
								{ "data": "TotalPremium"},
								],
			            'destroy': true,
					
				})	
			},
		error:function(data) {
			 old_alert("in failure vid:::");
			old_alert("hi");
			 
		},*/
   success : function(resp) {
	   hideWait()
   	
	$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			 $("#proposalNo").val(resp[key].ProposalNumber) ;
			 $("#quotationNo").val(resp[key].QuotationNumber) ;
	       	 $("#premiumAmount").val(resp[key].TotalPremium) ;
	       	 $("#customerId").val(resp[key].CustomerID) ;
			/*old_alert("Company" + resp[key].Company);
			old_alert("POLICYSTARTDATE" +resp[key].POLICYSTARTDATE);
			old_alert("POLICYTENURE" + resp[key].POLICYTENURE);
			old_alert("Discount" + resp[key].Discount);
			old_alert("TotalODPremiumValue" + resp[key].TotalODPremiumValue);
			old_alert("TotalTPPremiumValue" + resp[key].TotalTPPremiumValue);
			old_alert("NetPremium" + resp[key].NetPremium);
			old_alert("GICID" + resp[key].GICID);*/
							//nKey = parseInt(key) + 1;
			var row2="<tr><td>" + nKey+ "</td>" +
				"<td>" + resp[key].ProposalNumber + "</td>" +
				"<td>" + resp[key].QuotationNumber + "</td>" +
				"<td>" + resp[key].productName + "</td>" +
				"<td>" + resp[key].POLICYSTARTDATE + "</td>" +
				"<td>" + resp[key].FNAME +" " + resp[key].LNAME + "</td>" +
				"<td>" + resp[key].TotalPremium + "</td></tr>";
				//old_alert(row2);
				if(resp[key].ErrorText == null){
				$("#proposalDetails").append(row2);
				$("#premAmount").val(resp[key].TotalPremium)
				//row ++;
				}else{
					old_alert(resp[key].ErrorText);
				}
				
		});
	proposalDetailTable = ($("#proposalDetails").DataTable());
	},
	error: function (resp) {
		  hideWait()
	},
});

}
////Videocon Proposal Ends Here//////

////Videocon Payment Starts Here//////
function videoconPayment()
{
	showWait();
var url="user/videoconPayment?sqlMstId=" + "180" + "&param=" + "LVGI"
+ "&amount=" + $("#premiumAmount").val() + ""
+ "&productinfo=" + "Payment for Liberty Videocon"
+ "&SURL=" + "https://hopebox.co.in/UAT/TransactionStatus"
+ "&FURL=" + "https://hopebox.co.in/UAT/TransactionStatus"
+ "&key=" + ""
+ "&FirstName=" + $("#insDtlFname").val() + " " + $("#insDtlLname").val()
+ "&Email=" + $("#insDtlEmail").val()
+ "&Phone=" + $("#insDtlMobileNo").val()
+ "&quotationNumber=" + $("#quotationNo").val()
+ "&motorGroupResponseGroupId=" + $("#groupID").val()
+ "&motorGroupResponseSessionId=" + $("#sessionId").val()
+ "&motorGroupResponseGicId=" + $("#companyDtl_companyId").val() 
+"&userId=0"
+ "&userDesc=WEB"
+ "&branchId=0"
+ "&IpAddress="+""
+ "&customerID=" + $("#customerId").val();

window.open(url, "_blank");
geyPaymentDataFromDB();
hideWait()
}

////Videocon Payment Ends Here//////

////////Integration Of Liberty Videocon Insurance Company Ends Here ///////

function  findGicCompany(companyDtl_companyId){
$.ajax({
	'url' : "getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=find_gic&table_name=RGCP"
			+"&str_company_type=&str_gic=&str_gicbid=&str_prod=&str_discnm=&str_rgrp=&str_state="
			+"&str_city=&str_prpsl"
			+"&str_spnm=&str_mgnm=&str_productcode=0&str_type=0"
			+"&str_type_1=0&str_kg_from=0&str_kg_to=0&str_fueltype="
			+"&str_veh=&str_mod=&str_var=&str_policy_age="
			+"&str_hbbid=&str_user_level=0&str_user_id=0&str_login_type=0"
			+"&str_gap=0&str_ageto=0",
			
	'method' : 'post',
	'dataType' : 'json',
	'async' : false,
	 success : function(resp) {
		/*  old_alert("hi success ");
		 old_alert(resp); */
		 $.each(resp, function(key, value) {
			var gicName=resp[key].GIC_NAME;
			var gicId=resp[key].GIC_ID;
				if(gicId == companyDtl_companyId)
				{
					 //old_alert(gicId);
					$("#companyDtl_companyId").val(gicId);
					$("#companyName").val(gicName);
				}
			});
	},
	error:function(resp) {
//		old_alert("hi");
	},
});   
//old_alert("companyName:::" +companyName);
}


function refreshTable() {
	$('#packageDetails td').remove();
	$('#liabilityDetails td').remove();
	$('#additionalcover td').remove();
}

function getInsurancePolicy()
{
	var count = $('table#paymentStatus tr:last').index();
	 if(count !=0){
		 
		if ($("#companyDtl_companyId").val() == "2") {
		  getReliancePolicy();
		} 
		if ($("#companyDtl_companyId").val() == "48") {
			getShreeRamPolicyPdf();
		} 
		if ($("#companyDtl_companyId").val() == "43") {
			getBajajPolicyPdf();
		} 

		/* if ($("#companyDtl_companyId").val() == "34") {
		  getVideoconPolicy();
		} */
		saveUatMotorCalcIntgData();
	 }else{
		 old_alert("No data in table");
	 }
}
/*function getVideoconPolicy()
{

}*/


function getPaymentData() {
refreshTablePaymentStatus();
$.ajax({
'url' : "user/getPaymentDetails?"
+ "motorGroupResponseGroupId=" + $("#groupID").val()
+ "&motorGroupResponseSessionId=" + $("#sessionId").val()
+ "&motorGroupResponseGicId=" + $("#companyDtl_companyId").val()
+ "&userId=0"
+ "&userDesc=WEB"
+ "&branchId=0"
+ "&quotationNo=" + $("#quotationNo").val() 
+ "&proposalNo=" + $("#proposalNo").val(),
type : 'post',
dataType : 'json',
async : false,

success : function(resp) {
var compId=  $("#companyDtl_companyId").val();
console.log("compId" + compId);
if(compId =="2"){
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
	
		$("#PolicyNumber").val(resp[key].POLICY_NO) ;
		var row2="<tr><td>" + nKey+ "</td>" +
			"<td>" + resp[key].TRANSACTION_STATUS + "</td>" +
			"<td>" + resp[key].POLICY_NO + "</td>" +
			"<td>" + resp[key].PROPOSAL_NO + "</td>" +
			"<td>" + resp[key].TRANSACTION_NO + "</td></tr>";
			$("#paymentStatus").append(row2);
			console.log("#paymentDataAll").val(resp[key].TRANSACTIONSTATUS+","+ resp[key].PROPOSAL_NO +","+resp[key].POLICYNUMBER +","+resp[key].TRANSACTIONNUMBER);
			$("#paymentDataAll").val(resp[key].TRANSACTIONSTATUS+","+ resp[key].PROPOSAL_NO +","+resp[key].POLICYNUMBER +","+resp[key].TRANSACTIONNUMBER);
	});
}
if(compId =="4"){
//	old_alert("********In Hdfc Payment Response*************")
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#PolicyNumber").val(resp[key].PolicyNumber) ;
		var row2="<tr><td>" + nKey+ "</td>" +
			"<td>" + resp[key].Message + "</td>" +
			"<td>" + resp[key].ProposalNo + "</td>" +
			"<td>" + resp[key].PolicyNumber + "</td>" +
			"<td>" + resp[key].TransactionNo + "</td></tr>";
			$("#paymentStatus").append(row2);
			$("#paymentDataAll").val(resp[key].Message+","+ resp[key].ProposalNo +","+resp[key].PolicyNumber +","+resp[key].TransactionNo);
	});
}

if(compId =="56"){	
//   old_alert("********In Kotak Payment Response*************")			
   console.log("********In Kotak Payment Response*************")			
   $.each(resp, function(key, value) {		
	   	   nKey = parseInt(key) + 1;			
           $("#PolicyNumber").val(resp[key].PolicyNumber) ;			
           var row2="<tr><td>" + nKey+ "</td>" +			
                   "<td>" + resp[key].Message + "</td>" +			
                   "<td>" + resp[key].ProposalNo + "</td>" +			
                   "<td>" + resp[key].PolicyNumber + "</td>" +			
                   "<td>" + resp[key].TransactionNo + "</td></tr>";			
                   $("#paymentStatus").append(row2);			
                   $("#paymentDataAll").val(resp[key].Message+","+ resp[key].ProposalNo +","+resp[key].PolicyNumber+","+resp[key].TransactionNo);			
   });			
}

disableButtons();

},
});
}
function  disableButtons(){
	$("#showCompanyBtn").prop('disabled', true);
	$("#getPremiumBtn").prop('disabled', true);
	$("#clearInsuredDtl").prop('disabled', true);
	$("#clearNomineeDetails").prop('disabled', true);
	$("#getProposal").prop('disabled', true);
	$("#getProposal").prop('disabled', true);
	$("#previewCompId").prop('disabled', true);
	$("#b1").prop('disabled', true);
	$("#submitDtls").prop('disabled', true);
}

function getPremiumUniversalResponse(){
	showWait();

$("#companyDtl_companyId").val("47") ;

var gicId = $("#companyDtl_companyId").val();
proposalTypeUsed(gicId);

findGicCompany($("#companyDtl_companyId").val());
//old_alert("in Universal");
$.ajax({
'url' : "USGImotorcalculator?pkg_name=PKG_MOTOR_CALC"
      + "&proc_name=UNIVERSAL_SOMPO"
      + "&request_for=premium&tableName="+"AMCP"
      + "&rtoCity="+$("#RTOSCity").val()
      + "&zoneId="+ $("#zoneId").val()
      + "&policyType="+ $("#policyTypeId").val() 
      + "&varId="+ $("#varience").val()
      + "&businessType="+  $("#proposal").val()
      + "&productId="+  $("#productname").val()   
      + "&vehId="+ $("#vehicle").val() 
      + "&modelId="+ $("#Model").val()
      + "&customerType="+ $("#customer").val()
      + "&finId="+ ""				//financeDetailsMotIntgBean.getFinDtlFinById()   
      + "&prevGICId="+ ""			//$("#prevInsuranceCompany").val()
      + "&nomineeRel="+ ""		//$("#nomDtlRelation").val()
      + "&prevProduct="+ ""		//$("#prevProduct").val()
      + "&prevPolicyType="+ ""		//$("#prevPolicyType").val()
      + "&finAgreementType="+ "" //financeDetailsMotIntgBean.getFinDtlFinTypeId() 
      + "&bodyType="+ $("#bodyType").val()
      + "&presentAreaId="+	""	//	$("#insDtlCAPincode").val()
      + "&permanentAreaId="+ ""		//$("#insDtlPAPincode").val()
      + "&covers="+ $("#allCovers").val()
      + "&coverVal="+  $("#allCoversValue").val()
      + "&coverNo="+ $("#allCoversNumber").val()
      + "&CustomerName="+ ""
      + "&DOB="+ "" 
      + "&Gender="+ ""
      + "&CanBeParent="+ "0"
      + "&ContactTelephoneSTD="+ ""
      + "&MobileNo="+ ""
      + "&Emailid="+ ""
      + "&PresentAddressLine1="+ ""
      + "&PresentAddressLine2="+ ""
      + "&PermanentAddressLine1="+ ""
      + "&PermanentAddressLine2="+ ""
      + "&CustGSTNo="+ "000000000"
      + "&InstrumentNo="+ ""
      + "&InstrumentDate="+ ""
      + "&BankID="+ ""
      + "&NomineeName="+ ""
      + "&NomineeRelation="+ ""
      + "&IMDBranchName="+ ""
      + "&IMDBranchCode="+ ""
      + "&SPName="+ ""
      + "&SPCode="+ ""
      + "&ProposalDate="+ $("#dateofReg").val()
      + "&PolicyNumberChar="+ ""
      + "&VehicleLaidUpFrom="+ ""
      + "&VehicleLaidUpTo="+ ""
      + "&Fromdate="+ ""
      + "&Todate="+ ""
      + "&Fromhour="+ "00:00"
      + "&Tohour="+ "23:59"
      + "&FinancierCode="+ ""
      + "&AgreementType="+ ""
      + "&BranchName="+ ""
      + "&FinancierName="+ ""
      + "&SrNo="+ ""
      + "&ProductCode="+ ""
      + "&ClaimSettled="+ ""
      + "&ClaimPremium="+ ""
      + "&ClaimAmount="+ ""
      + "&DateofLoss="+ ""
      + "&NatureofLoss="+ ""
      + "&ClaimNo="+ ""
      + "&PolicyEffectiveTo="+ ""
      + "&PolicyEffectiveFrom="+ ""
      + "&DateOfInspection="+ ""
      + "&PolicyPremium="+ ""
      + "&PolicyNo="+ ""
      + "&PolicyYear="+ ""
      + "&OfficeCode="+ ""
      + "&PolicyStatus="+ ""
      + "&CorporateCustomerId="+ ""
      + "&InsurerName="+ ""
      + "&InsurerAddress="+ ""
      + "&PreviousPolicyType="+ ""
      + "&OfficeAddress="+ ""
      + "&NetPremium="+ ""
      + "&ServiceTax="+ ""
      + "&StampDuty2="+ ""
      + "&CGST="+ ""
      + "&SGST="+ ""
      + "&UGST="+ ""
      + "&IGST="+ ""
      + "&TotalPremium="+ ""
      + "&LoadingAmount="+ ""
      + "&LoadingRate="+ ""
      + "&SumInsured="+ ""
      + "&Rate="+ ""
      + "&Premium="+ ""
      + "&Applicable="+ ""
      + "&Description="+ ""
      + "&DiscountAmount="+ ""
      + "&DiscountRate="+ ""
      + "&DiscountSumInsured="+ ""
      + "&DiscountRate1="+ ""
      + "&DiscountPremium="+ ""
      + "&DiscountApplicable="+ ""
      + "&DiscountDescription="+ ""
      + "&RTOLocationCode="+ ""
      + "&NoOfClaimsOnPreviousPolicy="+ ""
      + "&RegistrationNumber="+ "New"
      + "&BodyTypeCode="+ $("#bodyType").val()
      + "&ModelStatus="+ ""
      + "&VehicleExShowroomPrice="+ document.getElementById('exshowroomprice').value
      + "&DateOfDeliveryOrRegistration="+ $("#dateofReg").val()
      + "&YearOfManufacture="+ $("#year :selected").text()
      + "&DateOfFirstRegistration="+ $("#dateofReg").val()
      + "&RegistrationNumberSection1="+  $("#regNo1").val() 
      + "&RegistrationNumberSection2="+  $("#regNo2").val() 
      + "&RegistrationNumberSection3="+  $("#regNo3").val() 
      + "&RegistrationNumberSection4="+  $("#regNo4").val() 
      + "&EngineNumber="+ document.getElementById('engineNo').value
      + "&ChassisNumber="+ document.getElementById('chasisNo').value
      + "&ExtensionCountryName="+ ""
      + "&AutomobileAssocnFlag="+ ""
      + "&AutomobileAssociationNumber="+ ""
      + "&VoluntaryExcess="+ ""
      + "&TPPDLimit="+ ""
      + "&AntiTheftDiscFlag="+ ""
      + "&HandicapDiscFlag="+ ""
      + "&NumberOfDrivers="+ ""
      + "&NumberOfEmployees="+ ""
      + "&TransferOfNCB="+ ""
      + "&TransferOfNCBPercent="+ ""
      + "&NCBDeclaration="+ ""
      + "&PreviousVehicleSaleDate="+ ""
      + "&BonusOnPreviousPolicy="+ ""
      + "&VehicleClass="+ ""
      + "&VehicleMake="+ ""
      + "&BodyTypeDescription="+ ""
      + "&VehiclesDrivenBy="+ ""
      + "&DriversAge="+ ""
      + "&DriversExperience="+ ""
      + "&DriversQualification="+ ""
      + "&VehicleModelCluster="+ ""
      + "&OpenCoverNoteFlag="+ ""
      + "&LegalLiability="+ ""
      + "&PaidDriver="+ ""
      + "&NCBConfirmation="+ ""
      + "&RegistrationDate="+ ""
      + "&TPLoadingRate="+ ""
      + "&ExtensionCountry="+ ""
      + "&VehicleAge="+ ""
      + "&LocationCode="+ ""
      + "&RegistrationZoneDescription="+ ""
      + "&NumberOfWorkmen="+ ""
      + "&VehicCd="+ ""
      + "&SalesTax="+ ""
      + "&ModelOfVehicle="+ ""
      + "&PopulateDetails="+ ""
      + "&VehicleIDV="+ ""
      + "&ShowroomPriceDeviation="+ ""
      + "&NewVehicle="+ ""
      + "&PaymentId="+ ""
      + "&MICRCheque="+ ""
      + "&InstrumentDatePayment="+ ""
      + "&DraweeBankName="+ ""
      + "&HOUSEBANKNAME="+ ""
      + "&AmountPaid="+ ""
      + "&PaymentType="+ ""
      + "&PaymentMode="+ ""
      + "&InstrumentNoPayment="+ ""
      + "&Status="+ ""
      + "&DepositSlipNo="+ ""
      + "&PosPolicyNo=" + ""
      + "&motorGroupResponseGroupId=" + $("#groupID").val()
      + "&motorGroupResponseSessionId=" + $("#sessionId").val()
      + "&motorGroupResponseGicId=" + "47"
      + "&userId=0"
      + "&userDesc=WEB"
      + "&branchId=0"
      + "&IpAddress=" + "192.168.0.243",
      
      	type : 'post',
		dataType : 'json',
		async : false,


success: function (resp) {
	hideWait()
	//old_alert("inside---------- Universal");
	
	$.each(resp, function (key, value) {
//		old_alert("success---------- Universal"+resp);
		var startDate=resp[key].Fromdate;
	    $("#posPolicyNoUniversal").val(resp[key].PosPolicyNo);
		
	    var sr=$("#compSerial").val() ;
		sr++;
		$("#compSerial").val(sr);
		
		var ErrorCode=resp[key].ErrorCode ;
		nKey = parseInt(key) + 1;
//		old_alert("checkBoxId in Universal-->"+"companyCheck"+sr);
		var row = '<input type="checkbox" name="checkbox" id="companyCheck'+sr+'" onclick="selectCompanyAction('+sr+');">';
		var row2="<tr><td>" + row + "</td>" +
				"<td></td>" +
				"<td>" + resp[key].Company + "</td>" +
				"<td>" + resp[key].Fromdate + "</td>" +
				"<td>" + "1" + "</td>" +
				"<td>" + resp[key].POLICYENDDATE + "</td>" +
				"<td>" + "" + "</td>" +
				"<td></td>" +
				"<td>"+ "" + "</td>" +
				"<td>"+ ""+ "</td>" +
				"<td>"+ resp[key].NetPremium + "</td>" +
				"<td>"+ resp[key].ServiceTax + "</td>" +
				"<td>"+ resp[key].TotalPremium + "</td>" +
				"<td>" + $("#proposalTf").val() + "</td>" +
				"<td>"+ "" + "</td>" +
				"<td></td>" +
				"<td>"+resp[key].GICID+"</td>" +
				"<td style='display:none;'>" + 0 + "</td>" +
				"<td style='display:none;'>" + 0 + "</td>" +
				"<td style='display:none;'>" + 0 + "</td>" +
				"<td style='display:none;'>" + 0 + "</td>" +
				"<td style='display:none;'>" + 0 + "</td>" +
				"</tr>";
		//old_alert(row2);
		if(ErrorCode==""){
			$("#companyDetails tbody").append(row2);
		}else{
			old_alert(resp[key].ErrDescription);
		}
		
	});
	/*if(!$.fn.DataTable.isDataTable('#companyDetails')){
		$("#companyDetails").DataTable({});
	}*/
},
error: function (resp) {
	hideWait()
	old_alert("error---------- Universal"+resp);
},
});
//consol.log("Universal URL----->>"+url);
}

function getProposalUniversalResponse(){
	showWait();
var gender="";
if(document.getElementById('insDtlGenMale').checked) {
	gender="male";
}else if(document.getElementById('insDtlGenFemale').checked) {
	gender="female";
}else if(document.getElementById('insDtlGenOther').checked) {
	gender="other";
}

//old_alert("in Universal Proposal");
$.ajax({
'url' : "USGImotorcalculator?pkg_name=PKG_MOTOR_CALC"
	+ "&proc_name=" + "UNIVERSAL_SOMPO"
   + "&request_for="+ "proposal"  
   + "&tableName="+ "AMCP" 
   + "&rtoCity="+ $("#RTOSCity").val()
   + "&zoneId="+ $("#zoneId").val()
   + "&policyType="+ $("#policyTypeId").val() 
   + "&varId="+ $("#varience").val()
   + "&businessType="+ $("#proposal").val()
   + "&productId="+  $("#productname").val() 
   + "&vehId="+ $("#vehicle").val()  
   + "&modelId="+ $("#Model").val() 
   + "&customerType="+ $("#customer").val() 
   + "&finId="+ "" 				//$("#finDtlFinBy").val() 
   + "&prevGICId="+ "" 			// $("#prevInsuranceCompany").val()
   + "&nomineeRel="+ $("#nomDtlRelation").val()
   + "&prevProduct="+ "" 			//$("#prevProduct").val()
   + "&prevPolicyType="+ ""  		// $("#prevPolicyType").val()
   + "&finAgreementType="+""			// $("#finDtlFinType").val()
   + "&bodyType="+ $("#bodyType").val()
   + "&presentAreaId="+ 	$("#insDtlCAPincode").val()
   + "&permanentAreaId="+ $("#insDtlPAPincode").val()
    + "&covers="+ $("#allCovers").val() 
   + "&coverVal="+  $("#allCoversValue").val()
   + "&coverNo="+ $("#allCoversNumber").val()
    + "&CustomerName="+ document.getElementById('insDtlFname').value + " " + document.getElementById('insDtlMname').value
     + "&DOB="+ $("#insDtlDob").val()
   + "&Gender="+ gender
   + "&CanBeParent="+ "0"
   + "&ContactTelephoneSTD="+ $("#insDtlPhoneNo").val()
   + "&MobileNo="+ $("#insDtlMobileNo").val()
   + "&Emailid="+ $("#insDtlEmail").val()
   + "&PresentAddressLine1="+ $("#insDtlCAPincode option:selected").text()
   + "&PresentAddressLine2="+ $("#insDtlCAStreet option:selected").text()
   + "&PermanentAddressLine1="+ $("#insDtlPAPincode option:selected").text()
   + "&PermanentAddressLine2="+ $("#insDtlPAStreet option:selected").text()
   + "&CustGSTNo="+ "00000000000"
   + "&InstrumentNo="+ ""
   + "&InstrumentDate="+ ""
   + "&BankID="+ ""
   + "&NomineeName="+ document.getElementById('nomDtlFname').value + " " + document.getElementById('nomDtlMname').value
   + "&NomineeRelation="+ $("#nomDtlRelation option:selected").text()
   + "&IMDBranchName="+ ""
   + "&IMDBranchCode="+ ""
   + "&SPName="+ ""
   + "&SPCode="+ ""
   + "&ProposalDate="+  $("#dateofReg").val()
   + "&PolicyNumberChar="+ ""
   + "&VehicleLaidUpFrom="+ ""
   + "&VehicleLaidUpTo="+ ""
   + "&Fromdate="+ $("#entryDate").val()
   + "&Todate="+ ""
   + "&Fromhour="+ "00:00"
   + "&Tohour="+ "23:59"
   + "&FinancierCode="+ ""
   + "&AgreementType="+ ""
   + "&BranchName="+ ""
   + "&FinancierName="+ ""
   + "&SrNo="+ "1"
   + "&ProductCode="+ ""
   + "&ClaimSettled="+ ""
   + "&ClaimPremium="+ ""
   + "&ClaimAmount="+ ""
   + "&DateofLoss="+ ""
   + "&NatureofLoss="+ ""
   + "&ClaimNo="+ "9999999999"
   + "&PolicyEffectiveTo="+ "02/09/2016"
   + "&PolicyEffectiveFrom="+ "03/09/2015"
   + "&DateOfInspection="+ "10/05/2016"
   + "&PolicyPremium="+ ""
   + "&PolicyNo="+ "915101005749200000"
   + "&PolicyYear="+ ""
   + "&OfficeCode="+ ""
   + "&PolicyStatus="+ "Unexpired"
   + "&CorporateCustomerId="+ ""
//   + "&InsurerName="+ URLEncoder.encode("L and T General Insurance Company Limited")
   + "&InsurerName="+ document.getElementById('insDtlFname').value +" "+ document.getElementById('insDtlMname').value +" "+ document.getElementById('insDtlLname').value
//   + "&InsurerAddress="+ "FATEHABAD"
   + "&InsurerAddress="+  $("#insDtlCAPincode option:selected").text()
   + "&PreviousPolicyType="+ "Package Policy"
   + "&OfficeAddress="+ ""
   + "&NetPremium="+ ""
   + "&ServiceTax="+ ""
   + "&StampDuty2="+ ""
   + "&CGST="+ ""
   + "&SGST="+ ""
   + "&UGST="+ ""
   + "&IGST="+ ""
   + "&TotalPremium="+ ""
   + "&LoadingAmount="+ ""
   + "&LoadingRate="+ ""
   + "&SumInsured="+ ""
   + "&Rate="+ ""
   + "&Premium="+ ""
   + "&Applicable="+ ""
   + "&Description="+ ""
   + "&DiscountAmount="+ ""
   + "&DiscountRate="+ ""
   + "&DiscountSumInsured="+ ""
   + "&DiscountRate1="+ ""
   + "&DiscountPremium="+ ""
   + "&DiscountApplicable="+ ""
   + "&DiscountDescription="+ ""
   + "&RTOLocationCode="+ ""
   + "&NoOfClaimsOnPreviousPolicy="+ ""
   + "&RegistrationNumber="+ ""
   + "&BodyTypeCode="+ ""
   + "&ModelStatus="+ ""
   + "&VehicleExShowroomPrice="+ $('#varience option:selected').attr('exshowroom')
//   + "&DateOfDeliveryOrRegistration="+  $("#dateofReg").val()
     + "&DateOfDeliveryOrRegistration="+ "19/09/2014"
   + "&YearOfManufacture="+ $("#year :selected").text()
   + "&DateOfFirstRegistration="+ $("#dateofReg").val() 
   + "&RegistrationNumberSection1="+  $("#regNo1").val() 
   + "&RegistrationNumberSection2="+  $("#regNo2").val() 
   + "&RegistrationNumberSection3="+  $("#regNo3").val() 
   + "&RegistrationNumberSection4="+  $("#regNo4").val() 
   + "&EngineNumber="+ document.getElementById('engineNo').value
   + "&ChassisNumber="+ document.getElementById('chasisNo').value
   + "&ExtensionCountryName="+ ""
   + "&AutomobileAssocnFlag="+ ""
   + "&AutomobileAssociationNumber="+ ""
   + "&VoluntaryExcess="+ ""
   + "&TPPDLimit="+ ""
   + "&AntiTheftDiscFlag="+ ""
   + "&HandicapDiscFlag="+ ""
   + "&NumberOfDrivers="+ ""
   + "&NumberOfEmployees="+ ""
   + "&TransferOfNCB="+ ""
   + "&TransferOfNCBPercent="+ ""
   + "&NCBDeclaration="+ ""
   + "&PreviousVehicleSaleDate="+ ""
   + "&BonusOnPreviousPolicy="+ ""
   + "&VehicleClass="+ ""
   + "&VehicleMake="+ ""
   + "&BodyTypeDescription="+ ""
   + "&VehiclesDrivenBy="+ ""
   + "&DriversAge="+ ""
   + "&DriversExperience="+ ""
   + "&DriversQualification="+ ""
   + "&VehicleModelCluster="+ ""
   + "&OpenCoverNoteFlag="+ ""
   + "&LegalLiability="+ ""
   + "&PaidDriver="+ ""
   + "&NCBConfirmation="+ ""
   + "&RegistrationDate="+ ""
   + "&TPLoadingRate="+ ""
   + "&ExtensionCountry="+ ""
   + "&VehicleAge="+ ""
   + "&LocationCode="+ ""
   + "&RegistrationZoneDescription="+ ""
   + "&NumberOfWorkmen="+ ""
   + "&VehicCd="+ ""
   + "&SalesTax="+ ""
   + "&ModelOfVehicle="+ ""
   + "&PopulateDetails="+ ""
   + "&VehicleIDV="+ ""
   + "&ShowroomPriceDeviation="+ ""
   + "&NewVehicle="+ ""
   + "&PaymentId="+ ""
   + "&MICRCheque="+ ""
   + "&InstrumentDatePayment="+ ""
   + "&DraweeBankName="+ ""
   + "&HOUSEBANKNAME="+ ""
   + "&AmountPaid="+ ""
   + "&PaymentType="+ ""
   + "&PaymentMode="+ ""
   + "&InstrumentNoPayment="+ ""
   + "&Status="+ ""
   + "&DepositSlipNo="+ ""
   + "&PresentPinCode="+  $('#insDtlCAPincode option:selected').attr('pincodeCA')
   + "&motorGroupResponseGroupId=" + $("#groupID").val()
   + "&motorGroupResponseSessionId=" + $("#sessionId").val()
   + "&motorGroupResponseGicId=" + "47"
   + "&userId=0"
   + "&userDesc=WEB"
   + "&branchId=0"
   + "&IpAddress=" + "192.168.0.243"
   + "&PosPolicyNo=" + $("#posPolicyNoUniversal").val(),
    type : 'post',
		dataType : 'json',
		async : false,


success: function (resp) {
	hideWait()
	//old_alert("inside---------- Universal");
	
	$.each(resp, function (key, value) {
//		old_alert("success---------- Universal proposal"+resp);
		
		var ErrorCode= resp[key].ErrorCode;
		nKey = parseInt(key) + 1;
		var row = '<input type="checkbox" name="checkbox" id="companyCheck'+nKey+'" onclick="selectCompanyAction('+nKey+');">';
		var row2="<tr><td>" + nKey+ "</td>" +
		"<td>" + resp[key].PosPolicyNo + "</td>" +
		"<td>" + posPolicyNoUniversal + "</td>" +
		"<td>" +  $("#productname :selected").text() + "</td>" +
		"<td>" + resp[key].Fromdate + "</td>" +
		"<td>" + resp[key].InsurerName + "</td>" +
		"<td>" + resp[key].TotalPremium + "</td></tr>";
//		old_alert("ErrorCode" + ErrorCode);
		if(ErrorCode==""){
			$("#proposalDetails").append(row2);
			$("#premAmount").val(resp[key].TotalPremium);
		}else{
			old_alert(resp[key].ErrDescription);
		}
		
	});
	
	
},
error: function (resp) {
	hideWait()
	console.log("error---------- Universal proposal"+resp);
},
});

}

//////////Integration for ShreeRam General Insurance Starts here/////////// 

function getPremiumResponseForShriRam() {
//old_alert("Premium Shriram");
$("$companyDtl_companyId").val("48");

var gidId= $("$companyDtl_companyId").val();
proposalTypeUsed(gidId);
	

findGicCompany(companyDtl_companyId);
var prevPolClaim="0";
var proposal= $("#proposalTf").val();
var prevNoOfClaims = $("#prevNoOfClaims").val();
if (proposal == "RENEWAL"|| proposal == "ROLLOVER") {
if(prevNoOfClaims > 0){
   prevPolClaim="1";
}
}
$.ajax({
'url' : "motorCalculatorShreeRam?pkg_name=PKG_MOTOR_CALC"
     + "&procName=SHRIRAM"
     + "&request_for="+ "premium"  
     + "&table_name=AMCP"
     + "&rtoCityId="+ $("#RTOSCity").val()
     + "&zoneId="+  $("#zoneId").val()
     + "&varID="+ $("#varience").val()
     + "&businessType="+   $("#proposal").val()
     + "&productID="+ $("#productname").val() 
     + "&vehID="+ $("#vehicle").val()  
     + "&modelID="+  $("#Model").val() 
     + "&occupationID="
     + "&prevGICID="+ ""	// $("#prevInsuranceCompany").val()
     + "&areaId="+ $("#insDtlCAPincode").val()
     + "&nomRel="+ $("#nomDtlRelation").val()
     + "&nomRelRep="
     + "&ownDriAppointeeRel="
     + "&covers="+ allCovers 
     + "&coverVal="+ allCoversValue
     + "&coverNo="+  allCoversNumber
     + "&policyType=0"
     + "&clienttype=0"
     + "&finId="+  $("#finDtlFinType").val() 
     + "&PrevProduct="+ $("#prevProduct").val()   
     + "&PrevPolicyType="+ $("#prevPolicyType").val()     
     + "&FinAgrementType="
     + "&BodyType="+ $("#bodyType").val()
     + "&PresentAreaId="+ $("#insDtlCAPincode").val()
     + "&PermantAreaId="+$("#insDtlPAPincode").val()
     + "&strFirstRegDt="+  $("#dateofReg").val()
     + "&strRTOCode="+  $("#vehRegNo1").val() +"-"+$("#vehRegNo2").val()
     + "&strInsuredState="+ $("#vehRegNo1").val()
     + "&strPrevPolExpDt="+ $("#prevToDate").val()
     + "&strPrevPolClaimYN="+ prevPolClaim
     + "&strPrevPolNCB="+ $("#prevNcb option:selected").text()
     + "&Gender=" //M
     + "&motorGroupResponseGroupId=" +  $("#groupID").val()
     + "&motorGroupResponseSessionId=" + $("#sessionId").val()
     + "&motorGroupResponseGicId=" + $("#companyDtl_companyId").val()
     + "&userId=0"
     + "&userDesc=WEB"
     + "&branchId=0"
     + "&IpAddress=",
     type : 'post',
     dataType : 'json',
     async : true,
 
     success: function (resp) {
//   	  old_alert("In success Premium Shriram"); 
   	  $.each(resp, function (key, value) {
   	
   	  });
     },
     error: function (resp) {
//		old_alert("in error Premium Shriram");
     }
     });

}


function getShriRamPremiumFromProposal() {
	//showWait();

$("#companyDtl_companyId").val("48") ;
var gicId = $("#companyDtl_companyId").val();
proposalTypeUsed(gicId);

findGicCompany("48");
//old_alert("PremiumFromPro Shriram");
var prevPolClaim="0";
var breakIn="NO";
var prevNoOfClaims = $("#prevNoOfClaims").val();
if (proposal == "RENEWAL"|| proposal == "ROLLOVER") {
if(gap>0){
  breakIn="YES";
}     
if(prevNoOfClaims > 0){
  prevPolClaim="1";
}
}

$.ajax({
'url' : "motorCalculatorShreeRam?pkg_name=PKG_MOTOR_CALC"
  + "&procName=SHRIRAM"
  + "&request_for="+ "proposal"  
  + "&table_name=AMCP"
  + "&premiumFromProposal="+ "Yes"
  + "&rtoCityId="+ $("#RTOSCity").val()
  + "&zoneId="+ $("#zoneId").val()
  + "&varID="+  $("#varience").val()
  + "&businessType="+   $("#proposal").val()
  + "&productID="+ $("#productname").val() 
  + "&vehID="+ $("#vehicle").val()  
  + "&modelID="+$("#Model").val() 
  + "&occupationID="
  + "&prevGICID="+ ""		//$("#prevInsuranceCompany").val()
  + "&areaId="+  $("#insDtlCAPincode").val()
  + "&nomRel="+ $("#nomDtlRelation").val()
  + "&nomRelRep="
  + "&ownDriAppointeeRel="
  + "&covers="+ allCovers
  + "&coverVal="+ allCoversValue
  + "&coverNo=" + allCoversNumber
  + "&policyType=0"
  + "&clienttype=0"
  + "&finId=" +   $("#finDtlFinType").val()         //financeDetailsMotIntgBean.getFinDtlFinTypeId()
  + "&PrevProduct=" + $("#prevProduct").val()   
  + "&PrevPolicyType=" + $("#prevPolicyType").val()     
  + "&FinAgrementType="
  + "&BodyType=Sedan"            //URLEncoder.encode(tf_body_type.getText())
  + "&PresentAreaId=339"               //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlAreaId())
  + "&PermantAreaId=339"               //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlPerAreaId()) 

  + "&adharEnrollNo=" + "4991186652462222222222222222"//insuredDetailsMotIntgBean.getInsDtlAadharNo()
  + "&adharNo=" +    "324444444444"                 //insuredDetailsMotIntgBean.getInsDtlAadharNo()
  + "&addonPackage=" + "ADDON_01"
  + "&address1=" +  "22"          //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlHouseNoName())
  + "&address2=" +   "MECOSABAGH ROAD"             //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlStreet())
  + "&address3=" +   "Bezonbagh"                    //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlAreaName())
  + "&appointeeNameforPAOwnerDriver=" +""
  + "&appointeeRelationforPAOwnerDriver=" + ""
  + "&breakIn=" + breakIn
  + "&chassisNo=" + "FRTYU67899"            //URLEncoder.encode(tf_chassis_no.getText())
  + "&city=" + "Nagpur"       //insuredDetailsMotIntgBean.getInsDtlCity()
  + "&coverNoteDt=" + ""
  + "&coverNoteNo=" +""
  + "&DailyExpRemYN=" + ""
  + "&DepDeductWaiverYN=" + ""
  + "&dob="+  "01/02/1990"        //insuredDetailsMotIntgBean.getInsDtlDob()
  + "&DeTariff="+ ""
  + "&isDriverAge="+ ""
  + "&ElectricalaccessSI="+ electricalAcc//complete Data
  + "&EMailID="+   "dummy@dummy.com"           //insuredDetailsMotIntgBean.getInsDtlEmail()
  + "&isEmergencyTranHotelExpRem="
  + "&EngineNo="+ "FRTYU67899"            //tf_engine_no.getText()
  + "&FaxNo="
  + "&FirstRegDt="+  $("#dateofReg").val()
  + "&Form16="
  + "&Gender="+ "male"        //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlGender())//M
  + "&GSTNo="
  + "&HypothecationAddress1="+ "22"          //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlHouseNoName())
  + "&HypothecationAddress2="+ "MECOSABAGH ROAD"           //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlStreet())
  + "&HypothecationAddress3="+ "Bezonbagh"                    //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlAreaName())
  + "&HypothecationAgreementNo="
  + "&HypothecationBankName="
  + "&HypothecationCity="
  + "&HypothecationCountry="
   + "&HypothecationPinCode="
   + "&HypothecationState="
   + "&HypothecationType="
   + "&IDVOfVehicle="+totalIDV
   + "&isInBuiltCNGKit="
   + "&InsuredName="+  "Bhavesh A Notwani"      //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlFirstName()+" "+insuredDetailsMotIntgBean.getInsDtlMiddleName()+" "+insuredDetailsMotIntgBean.getInsDtlLastName())
   + "&InsuredPrefix="+  "Mr."     //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlInitial())//Changes ServerSide
   + "&isInvReturn="+ "Y"
   + "&MobileNo="+   "9854789658"             //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlMobile())
   + "&MultiCarBenefitYN="+ ""
   + "&NoEmpCoverLL="+ ""  //Total Cover
   + "&NonElectricalaccessSI="+ $("#nonElectricalAcc").val()
   + "&PanNo="+  "FTGYH5678T"         //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlPanNo()) 
   + "&PinCode="+  "440003"                   //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlPincode()) 
//   + "&PolicyFromDt="+ URLEncoder.encode( $("#dateofReg").val()) 
//   + "&PolicyIssueDt="+ URLEncoder.encode( $("#dateofReg").val()) 
   + "&PolicyType="+ "Package policy"
   + "&PreInspection="+""
   + "&isPreInspectionReport="+ "0"
   + "&PreviousInsurer="+ ""		//$("#prevInsuranceCompany :selected").text()
   + "&PreviousNilDepreciation="+ "0"
   + "&isPreviousPolicyClaim="+ prevPolClaim 
   + "&PreviousPolicyFromDt="+ $("#prevFromDate").val()
   + "&PreviousPolicyNCBPerc="+ $("#prevNcb option:selected").text()
    + "&PreviousPolicyNo="+"" 
    + "&PreviousPolicySI="+""          
    + "&PreviousPolicyToDt="+ $("#prevToDate").val()
    + "&PreviousPolicyType="+"Package policy"
    + "&PreviousPolicyUWYear="+ ""
    + "&ProdCode="+"MOT-PRD-001"
    + "&proposalType="+  $("#proposalTf").val()
    + "&ReferenceNo="+ "" 
    + "&regNo1="+  "MH"           //URLEncoder.encode(tf_veh_no1.getText()) 
    + "&regNo2="+  "31"           //URLEncoder.encode(tf_veh_no2.getText()) 
    + "&regNo3="+ "NA" 
    + "&regNo4="+ "0000"
    + "&rtoCode="+ "MH"       //URLEncoder.encode(tf_veh_no1.getText()) 
   + "&SpecifiedPersonField=" + ""
  + "&TelephoneNo=" +  "9856895623"           //URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlPhoneNo())
  + "&VehiclePurpose=" + "0"
  + "&VehicleType=" + "0"
  + "&VoluntaryExcess=" + "0"
  + "&motorGroupResponseGroupId=" +  $("#groupID").val()
  + "&motorGroupResponseSessionId=" + $("#sessionId").val()
  + "&motorGroupResponseGicId=" + $("#companyDtl_companyId").val()
  +"&motorGroupResponseGicName=" + $("#companyName").val() 
  + "&userId=0" 
  + "&userDesc=WEB"
  + "&branchId=0" 
  + "&IpAddress=" ,
  
  type : 'post',
  dataType : 'json',
  async : true,

  success: function (resp) {
//	  destroyDataTable();
	  //hideWait()
	 
//	   old_alert("In  success PremiumFronPro Shriram"); 
	 
	$.each(resp, function (key, value) {
		
	
		var code= resp[key].errorCode;
		var desc= resp[key].errorDesc;
    	 
		if(code==1){
			old_alert(desc);
		}else if(code==0){
			nKey = parseInt(key) + 1;
			var sr=$("#compSerial").val() ;
			sr++;
			$("#compSerial").val(sr);
			
			var row = '<input type="checkbox" name="checkbox" id="companyCheck'+sr+'" onclick="selectCompanyAction('+sr+');">';
			var row2="<tr><td>" + row + "</td>" +
					"<td>" + nKey+ "</td>" +
					"<td>"+ " <img src='images/comapanyImage/shriram.jpeg'  height=50 width=75> </img>"+"</td>" +
					"<td>" + resp[key].POLICYFROMDT + "</td>" +
					"<td>" + "1" + "</td>" +
					"<td>" + resp[key].POLICYTODT+ "</td>" +
					"<td>" + "" +"</td>" +
					"<td>" + ""  + "</td>" +
					"<td>"+ resp[key].cov_Pre_OD_TOTAL + "</td>" +
					"<td>"+ resp[key].cov_Pre_TP_TOTAL + "</td>" +
					"<td>"+ resp[key].cov_Pre_TOTAL_PREMIUM + "</td>" +
					"<td>"+ resp[key].cov_Pre_IGST + "</td>" +
					"<td>"+ resp[key].cov_Pre_TOTAL_AMOUNT + "</td>" +
					"<td>" + $("#proposalTf").val() + "</td>" +
					"<td>"+ "" + "</td>" +
					"<td>  </td>" +
					"<td>"+ resp[key].GICID + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"</tr>";
			//old_alert(row2);
			$("#companyDetails tbody").append(row2);
		}
		if(!$.fn.DataTable.isDataTable('#companyDetails')){
			$("#companyDetails").DataTable({
				"lengthMenu": [5, 10, 20, 50],
		        "pageLength": 5,
			});
		}
	});
	 
  },
  error: function (resp) {
	  //hideWait()
		old_alert("in  error PremiumFronPro Shriram");
  }

  });  

}


function getProposalResponseForShriRam(){
	showWait();

$("#companyDtl_companyId").val("48") ;
var gicId=$("#companyDtl_companyId").val();
proposalTypeUsed(gicId);
findGicCompany($("#companyDtl_companyId").val());
//old_alert("Proposal Shriram");
var prevPolClaim="0";
var breakIn="NO";
var prevNoOfClaims = $("#prevNoOfClaims").val();
if (proposal == "RENEWAL"|| proposal == "ROLLOVER") {
if(gap>0){
  breakIn="YES";
}     
if(prevNoOfClaims > 0){
  prevPolClaim="1";
}
}
var gender="";
if(document.getElementById('insDtlGenMale').checked) {
gender="male";
}else if(document.getElementById('insDtlGenFemale').checked) {
gender="female";
}else if(document.getElementById('insDtlGenOther').checked) {
gender="other";
}

$.ajax({
'url' : "motorCalculatorShreeRam?pkg_name=PKG_MOTOR_CALC"
	+ "&procName=" + "SHRIRAM"
   + "&request_for="+ "proposal"  
   + "&table_name="+ "AMCP"
     + "&premiumFromProposal="+ "No"
   + "&rtoCityId="+ $("#RTOSCity").val()
   + "&zoneId="+  $("#zoneId").val()
   + "&varID="+ $("#varience").val()
   + "&businessType="+  $("#proposal").val()
   + "&productID="+  $("#productname").val() 
   + "&vehID="+ $("#vehicle").val()   
   + "&modelID="+ $("#Model").val() 
   + "&occupationID="+ ""
   + "&prevGICID="+ ""		//$("#prevInsuranceCompany option:selected").text()
   + "&areaId="+ $("#insDtlCAPincode").val()  
   + "&nomRel="+ $("#nomDtlRelation").val() 
   + "&nomRelRep="+ ""
   + "&ownDriAppointeeRel="+ ""
   + "&covers="+ allCovers
   + "&coverVal="+ allCoversValue
   + "&coverNo=" + allCoversNumber
   + "&policyType="+ "0"
   + "&clienttype="+ "0"
   + "&finId=" + $("#finDtlFinType").val() 
   + "&PrevProduct=" + $("#prevProduct").val() //URLEncoder.encode(prevInsDetailsMotIntgBean.getPreInsProductId())
   + "&PrevPolicyType=" + $("#prevPolicyType").val()  		//URLEncoder.encode(prevInsDetailsMotIntgBean.getPreInsPolicyTypeId())
   + "&FinAgrementType=" + ""
   + "&BodyType=" +  $("#bodyType").val()
   + "&PresentAreaId=" + $("#insDtlCAPincode").val()		//URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlAreaId())
   + "&PermantAreaId=" + $("#insDtlPAPincode").val()	//URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlAreaId())//Change to permant

   + "&adharEnrollNo=" +  $("#insDtlAadharEnrollNo").val()
   + "&adharNo=" + $("#insDtlAadharNo").val()
   + "&addonPackage=" + "ADDON_01"
   + "&address1=" + document.getElementById('insDtlCAHouse').value		//URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlHouseNoName())
   + "&address2=" + document.getElementById('insDtlCAStreet').value		//URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlStreet())
   + "&address3=" + $("#insDtlCAPincode option:selected").text()	//URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlAreaName())
   + "&appointeeNameforPAOwnerDriver=" + ""
   + "&appointeeRelationforPAOwnerDriver=" + ""
   + "&breakIn=" + breakIn
   + "&chassisNo=" + document.getElementById('chasisNo').value	
   + "&city=" + $("#insDtlCACity").val()			//insuredDetailsMotIntgBean.getInsDtlCity()
   + "&coverNoteDt=" +""
   + "&coverNoteNo=" + ""
   + "&DailyExpRemYN=" + ""
   + "&DepDeductWaiverYN=" + ""
   + "&dob="+ $("#insDtlDob").val()
   + "&DeTariff="+ ""
   + "&isDriverAge="+ ""
   + "&ElectricalaccessSI="+ electricalAcc //complete Data
   + "&EMailID="+ document.getElementById('insDtlEmail').value	
   + "&isEmergencyTranHotelExpRem="+ ""
   + "&EngineNo="+  document.getElementById('engineNo').value	
   + "&FaxNo="+ ""
   + "&FirstRegDt="+ $("#dateofReg").val() 
   + "&Form16="+ ""
   + "&Gender="+ 	gender	//URLEncoder.encode(insuredDetailsMotIntgBean.getInsDtlGender())//M
   + "&GSTNo="+ ""
   + "&HypothecationAddress1="+ document.getElementById('insDtlCAHouse').value	
   + "&HypothecationAddress2="+ document.getElementById('insDtlCAStreet').value	
   + "&HypothecationAddress3="+  $("#insDtlCAPincode option:selected").text()	
   + "&HypothecationAgreementNo="+ ""
   + "&HypothecationBankName="+ ""
   + "&HypothecationCity="+ ""
   + "&HypothecationCountry="+ ""
    + "&HypothecationPinCode="+ ""
    + "&HypothecationState="+ ""
    + "&HypothecationType="+ ""
    + "&IDVOfVehicle="+ document.getElementById('totalIDV').value	
    + "&isInBuiltCNGKit="+ ""
    + "&InsuredName="+ document.getElementById('insDtlFname').value +" "+ document.getElementById('insDtlMname').value +" "+ document.getElementById('insDtlLname').value
    + "&InsuredPrefix="+ $("#insDtlInitial option:selected").text()
    + "&isInvReturn="+ "Y"
    + "&MobileNo="+ document.getElementById('insDtlMobileNo').value
    + "&MultiCarBenefitYN="+ ""
    + "&NoEmpCoverLL="+ "" //Total Cover
    + "&NonElectricalaccessSI="+ $("#nonElectricalAcc").val()
    + "&PanNo="+ document.getElementById('insDtlPanNo').value 
    + "&PinCode="+ $('#insDtlCAPincode option:selected').attr('pincodeCA')
//    + "&PolicyFromDt="+ URLEncoder.encode(regDateNew) 
//    + "&PolicyIssueDt="+ URLEncoder.encode(regDateNew) 
    + "&PolicyType="+ "Package policy"
    + "&PreInspection="+ ""
    + "&isPreInspectionReport="+ "0"
    + "&PreviousInsurer="+ ""		//$("#prevInsuranceCompany option:selected").text()
    + "&PreviousNilDepreciation="+ "0"
    + "&isPreviousPolicyClaim="+ prevPolClaim
    + "&PreviousPolicyFromDt="+ $("#prevFromDate").val() 
    + "&PreviousPolicyNCBPerc="+ $("#prevNcb option:selected").text()
     + "&PreviousPolicyNo="+ ""
     + "&PreviousPolicySI="+ ""
     + "&PreviousPolicyToDt="+ $("#prevToDate").val() 
     + "&PreviousPolicyType="+ "Package policy"
     + "&PreviousPolicyUWYear="+""
     + "&ProdCode="+ "MOT-PRD-001"
     + "&proposalType="+ "new"
     + "&ReferenceNo="+ ""
     + "&regNo1="+ document.getElementById('vehRegNo1').value 
     + "&regNo2="+ document.getElementById('vehRegNo2').value 
     + "&regNo3="+ "NA"
     + "&regNo4="+ "0000"
     + "&rtoCode="+ document.getElementById('vehRegNo1').value 
    + "&SpecifiedPersonField=" + ""
   + "&TelephoneNo=" + document.getElementById('insDtlPhoneNo').value 
   + "&VehiclePurpose=" + "0"
   + "&VehicleType=" + "0"
   + "&VoluntaryExcess=" + "0"
   + "&motorGroupResponseGroupId=" +  $("#groupID").val()
   + "&motorGroupResponseSessionId=" + $("#sessionId").val()
   + "&motorGroupResponseGicId=" + $("#companyDtl_companyId").val()
   + "&userId=" + "0"
   + "&userDesc=" + "WEB"
   + "&branchId=" + "0"
   + "&IpAddress=" + "",
  			
  type : 'post',
  dataType : 'json',
  async : false,
  success: function (resp) {
	  hideWait()
//  old_alert("In  success Proposal Shriram"); 
	 
	$.each(resp, function (key, value) {
		
		var code= resp[key].errorCode;
		var desc= resp[key].errorDesc;
    	 
		if(code==1){
			old_alert(desc);
		}else if(code==0){
		
		$("#hdfcFinalProposalNo").val(resp[key].WsMessage);
		var fname=resp[key].FirstName ;
		var lname=resp[key].LastName ;
		$("#insName").val(fname +" " +lname);
		
		nKey = parseInt(key) + 1;
		var row = '<input type="checkbox" name="checkbox" id="companyCheck'+nKey+'" onclick="selectCompanyAction('+nKey+');">';
		var row2="<tr><td>" + nKey+ "</td>" +
		"<td>" + resp[key].proposalNo + "</td>" +
		"<td>" + resp[key].polSysId + "</td>" +
		"<td>" +  $("#productname :selected").text() + "</td>" +
		"<td>" + resp[key].POLICYFROMDT + "</td>" +
		"<td>" + resp[key].InsuredName + "</td>" +
		"<td>" + resp[key].cov_Pre_TOTAL_AMOUNT + "</td></tr>";
		//old_alert(row2);
		$("#proposalDetails").append(row2);
		
		$("#premAmount").val(resp[key].cov_Pre_TOTAL_AMOUNT );
		}
		
		$("#paymentCompany").val("48");
		$("#proposalNo").val(resp[key].proposalNo);	//setting value in hidden Textfield
		$("#quotationNo").val(resp[key].polSysId);	//setting value in hidden Textfield
		
	});
	 
  },
  error: function (resp) {
	  hideWait()
  }
  });  

}

function shriRamPolicy() {
	showWait();
var proNo=$("#proposalNo").val()
var polSysId=$("#quotationNo").val()
//old_alert("proNo==>>"+proNo+"polSysId==>>"+polSysId)
$("#shrProposalNo").val(proNo);
$("#shrTransactionNo").val(polSysId);
/*old_alert("proposalNo-->>"+$("#shrProposalNo").val());
old_alert("quotationNo-->>"+$("#shrTransactionNo").val());*/
}

function getPolicyApproveForShreeRam(){
/*old_alert("proposalNo1-->>"+$("#shrProposalNo").val());
old_alert("quotationNo1-->>"+$("#shrTransactionNo").val());
old_alert("shreeram PolicyApproval");*/

$.ajax({
	'url' : "motorCalculatorShreeRam?pkg_name=PKG_MOTOR_CALC"
		+ "&procName=" +"SHRIRAM"
       + "&request_for="+ "PolicyApprove"  
       + "&table_name="+ "AMCP"
         + "&premiumFromProposal="+ "No"
       
       + "&proposalNoPolicyApp="+  $("#shrProposalNo").val() 	//"10014/31/18/P/000014"
       + "&TransactionNo="+ $("#shrTransactionNo").val()
       + "&CardNo="+ $("#shrEmpCardNo").val()
       + "&CardHolderName="+  $("#shrCardHolderName").val()
       + "&CardType="+ $("#shrCardType").val()
       + "&cardValidUpto="+ $("#shrCardValidUpto").val()
       + "&BankName="+ $("#shrBankName").val()
       + "&BranchName="+ $("#shrBranchName").val()
       + "&paymentType="+ $("#shrPaymentType").val()
       + "&ChequeType="+ $("#shrChequeType").val()
       + "&ChequeClearType="+ $("#shrChequeClear").val()
       + "&Cashtype="+ $("#shrCashType").val()
       + "&TransactionDate="+ $("#shrTransactionDate").val()
       + "&motorGroupResponseGroupId=" +  $("#groupID").val()
       + "&motorGroupResponseSessionId=" + $("#sessionId").val()
       + "&motorGroupResponseGicId=" + $("#companyDtl_companyId").val()
                      + "&userId=" + "0"
       + "&userDesc=" + "WEB"
       + "&branchId=" + "0"
       + "&IpAddress=" + "", 
		
      
         type : 'post',
         dataType : 'json',
         async : false,
         success: function (resp) {
        	 hideWait()
//       	  old_alert("In success shreeram PolicyApproval"); 
       	  $.each(resp, function (key, value) {
       		 
      			var error= resp[key].errorCode;
      			var polNo="",  sysId="",status="";
      			if(error==0){
      				 polNo= resp[key].approvedPolNo;
      			     sysId= resp[key].approvePolSysId;
      			     status="Success";
    			}else {
    				 status="Fail";
    				old_alert("Policy Approve Unsuccessfully");
    			}
      			$("#shreeRamApprovePolSysIdForPdf").val(sysId);	//setting value in hidden Textfield
      			
      			
   				nKey = parseInt(key) + 1;
   				var row2="<tr><td>" + nKey+ "</td>" +
   					"<td>" + status + "</td>" +
   					"<td>" + "NA" + "</td>" +
   					"<td>" + polNo + "</td>" +
   					"<td>" + sysId + "</td></tr>";
   					$("#paymentStatus").append(row2);
      			
       	  });
         },
         error: function (resp) {
        	 hideWait()
//			old_alert("in errorshreeram PolicyApproval");
         }
         });

$("#paymentDetailsDiv").show();
}
function getShreeRamPolicyPdf(){
//old_alert("shreeram Pdf method");
showWait();
$.ajax({
'url' : "motorCalculatorShreeRam?pkg_name=PKG_MOTOR_CALC"
	+ "&procName=" + "SHRIRAM"
   + "&request_for="+ "policyScheduleURL"  
   + "&table_name="+ "AMCP"
     + "&premiumFromProposal="+ "No"
   + "&PolSysIdForPdf="+  $("#shreeRamApprovePolSysIdForPdf").val(),
   
   type : 'post',
   dataType : 'json',
   async : false,
   success: function (resp) {
	   hideWait();
// 	  old_alert("In success Pdf method"); 
 	  $.each(resp, function (key, value) {
			  
				var pdfUrl= resp[key].PolicyScheduleURL;
				var url = pdfUrl.replace("&amp;", "");
				window.open(url, "_blank");
 	  });
   },
   error: function (resp) {
	   hideWait()
//		old_alert("in errorshreeram Pdf method");
   }
   
});
}

//////////Integration for ShreeRam General Insurance ends here/////////// 


//////////Integration for HDFC ERGO General Insurance Starts here/////////// 


function getPlanTypesHdfc() {
	
	var gicId="4";
	proposalTypeUsed(gicId);
	
$.ajax({
   'url': "user/HdfcIntegrationController?procName=" + "HDFC"
+ "&tableName=" + "AMCP"
+ "&request_for=" + "coverage"
+ "&rtoCity=" +  $("#RTOSCity").val()
+ "&zoneID=" + $("#Zone").val()
+ "&policyType=" + $("#policyTypeId").val() 
+ "&varid=" +  $("#varience").val()
+ "&businessType=" +   $("#proposal").val()
+ "&vehicleClassCode=" + $("#Model").val() 
+ "&manufacturerCode=" + $("#vehicle").val() 
+ "&vehicleModelCode=" + $("#Model").val()
+ "&customerAge=" + "35"
+ "&customerType=" + $("#customer").val()
+ "&occupationType=" + "1"
+ "&financeId=" + "0"
+ "&prevGicId=" + "0"
+ "&carAreaId=" + "0"
+ "&corrsCarId=" + "0"
+ "&OwnerDriverNomineeRelationship=" + "0"
+ "&OwnerDriverAppointeeName=" + "0"
+ "&registrationDate=" +$("#dateofReg").val() 
+ "&covVal=" + ""
+ "&covNo=" + ""
+ "&claimNo=" + ""
+ "&BiFuelType=" + $("#fuelKit").val(),
type: 'post',
dataType: 'json',
async: true,

});
}



function getIDVHdfcResponse() {
//old_alert("inside---------- Hdfc Idv");

var vehicleClassCode = "";
var productId = $("#productname").val();
if (productId == "1") {
vehicleClassCode = "37";
} else {
vehicleClassCode = "45";
}

	proposalTypeUsed("4");

$.ajax({
'url': "user/HdfcIntegrationController?procName=HDFC" +
   "&tableName=" + "AMCP" +
   "&request_for=" + "idv" +
   "&rtoCity=" + $("#RTOSCity").val() +
   "&zoneID=" + $("#zoneId").val() +
   "&policyType=" + $("#policyTypeId").val()  + "" +
   "&varid=" + $("#varience").val() +
   "&businessType=" +  $("#proposal").val() +
   "&vehicleClassCode=" + vehicleClassCode +
   "&manufacturerCode=" + $("#vehicle").val() +
   "&vehicleModelCode=" + $("#Model").val() +
   "&customerAge=" + "35" +
   "&customerType=" + $("#customer").val() +
   "&occupationType=" + "0" +
   "&financeId=" + "0" +
   "&prevGicId=" + "0" +
   "&carAreaId=" + "0" +
   "&corrsCarId=" + "0" +
   "&OwnerDriverNomineeRelationship=" + "0" +
   "&OwnerDriverAppointeeName=" + "0" +
   "&registrationDate=" + $("#dateofReg").val() +
   "&prevPolicyEndDate=" + "" +
   "&covVal=" + "" +
   "&covers=" + "" +
   "&covNo=" + "" +
   "&claimNo=" + "" +
   "&BiFuelType=" + $("#fuelKit").val(),

type: 'post',
dataType: 'json',
async: false,


success: function (resp) {
//	old_alert("success----------  Hdfc Idv"+resp);
	
	$.each(resp, function (key, value) {
		
		$("#hdfcVehicleIDV").val(resp[key].idv_amount_max);
		$("#hdfcExShowroomPrize").val(resp[key].exshowroomPrice_max);
		
		nKey = parseInt(key) + 1;
		var row = '<input type="checkbox" name="checkbox" id="companyCheck'+nKey+'" onclick="selectCompanyAction('+nKey+');">';
		var row2="<tr><td>" + row + "</td>" +
				"<td>" + nKey+ "</td>" +
				"<td>" + resp[key].idv_amount_min + "</td>" +
				"<td>" + resp[key].exshowroomPrice_min + "</td>" +
				"<td>" + resp[key].idv_amount  + "</td>" +
				"<td>" + resp[key].exshowroomPrice  + "</td>" +
				"<td>" + resp[key].exshowroomPrice_max  + "</td>" +
				"<td>" + resp[key].idv_amount_max  + "</td>" +
				"</tr>";
		//$("#companyDetails").append(row2);
	});
},
error: function (resp) {
	old_alert("error----------  Hdfc Idv"+resp);
},
});

}



function getPremiumHdfcResponse() {
	console.log("HDFC");
	showWait();
	

$("#companyDtl_companyId").val("4") ;

var gicId = $("#companyDtl_companyId").val();
proposalTypeUsed(gicId);

findGicCompany($("#companyDtl_companyId").val());
//old_alert("inside---------- Hdfc Premium");

var vehicleClassCode = "";
var productId = $("#productname").val();
if (productId == "1") {
vehicleClassCode = "37";
} else {
vehicleClassCode = "45";
}

/* var preInsNoOfClaim= $("#prevNoOfClaims option:selected").text();
if(preInsNoOfClaim==1)
{
preInsNoOfClaim="0";
}else{
preInsNoOfClaim="1";
}
*/
var isPreviousClaim = $("#prevNcb option:selected").text();
var preInsNcb = "";
if (isPreviousClaim != "") {
//old_alert("--------if-------------------");
//isPreviousClaim="1";
//preInsNcb=isPreviousClaim;
isPreviousClaim = "0";
preInsNcb = "0";
} else {
//old_alert("--------else-------------------");
isPreviousClaim = "0";
preInsNcb = "0";
}


$.ajax({
   'url': "user/HdfcIntegrationController?procName=HDFC" +
       "&tableName=" + "AMCP" +
       "&request_for=" + "premium" +
       "&rtoCity=" + $("#RTOSCity").val() +
       "&zoneID=" +$("#zoneId").val() +
       "&policyType=" + $("#policyTypeId").val()  +
       "&varid=" + $("#varience").val() +
       "&businessType=" +  $("#proposal").val() +
       "&vehicleClassCode=" + vehicleClassCode +
       "&manufacturerCode=" + $("#vehicle").val() +
       "&vehicleModelCode=" + $("#Model").val() +
       "&customerAge=" + "35" +
       "&customerType=" + $("#customer").val() +
       "&occupationType=" + "1" +
       "&financeId=" + "0" +
       "&prevGicId=" + "" //$("#prevInsuranceCompany").val()
       +
       "&carAreaId=" + "0" +
       "&corrsCarId=" + "0" +
       "&OwnerDriverNomineeRelationship=" + "0" +
       "&OwnerDriverAppointeeName=" + "0" +
       "&registrationDate=" + $("#dateofReg").val() +
       "&prevPolicyEndDate=" + "" //$("#prevToDate").val()
       +
       "&manufacturingYear=" + $("#year :selected").text() +
       "&prePolicyStartDate=" + "" //$("#prevFromDate").val()
       +
       "&exshowroomPrice=" + document.getElementById('exshowroomprice').value +
       "&SumInsured=" + $("#hdfcVehicleIDV").val() +
       "&rtoLocationCode=" + $("#RTOSCity").val() +
       "&basedon_IDV_ExShowRoom=" + "1" +
       "&IsPreviousClaim=" + isPreviousClaim +
       "&previousDiscount=" + preInsNcb +
       "&electicalAcc=" + "0" +
       "&nonElecticalAcc=" + "0" +
       "&lpgCngKit=" + "0" +
       "&paidDriverSi=" + "0" +
       "&unnamedSi=" + "0" +
       "&noOfEmployees=" + "0" +
       "&noOfllDrivers=" + "0" +
       "&isPaCoverOwnerDriver=" + "0" +
       "&numIsRti=" + "0" +
       "&numIsEmrAsstCvr=" + "0" +
       "&numIsZeroDept=" + "0" +
       "&txtPlanType=" + "" +
       "&numIsEmrAsstWiderCvr=" + "0" +
       "&isNcbProtection=" + "0" +
       "&isEngineGearBoxProtection=" + "0" +
       "&isCostOfConsumable=" + "0" +
       "&isLossOfUseDownProtection=" + "0" +
       "&covers=" + $("#allCovers").val() +
       "&covVal=" +  $("#allCoversValue").val() +
       "&covNo=" + $("#allCoversNumber").val() +
       "&PanNo=" + "" +
       "&BiFuelType=" + $("#fuelKit :selected").text() +
       "&motorGroupResponseGroupId=" + $("#groupID").val() +
       "&motorGroupResponseSessionId=" + $("#sessionId").val() +
       "&motorGroupResponseGicId=" + "4" +
       "&motorGroupResponseGicName=" + $("#companyName").val() +
       "&proposalType=" + $("#proposalTf").val() +
       "&userId=" + "66" +
       "&userDesc=" + "RUP" +
       "&branchId=" + "3" +
       "&IpAddress=" + "192.168.0.243",

       type: 'post',
   dataType: 'json',
   async: true,
   success: function (resp) {
	   hideWait();
	   console.log("resp::: "+resp.length);
	  
//	   destroyDataTable();
	   var newRow="";
//		old_alert("success---------- Hdfc Premium"+resp);
		$.each(resp, function (key, value) {
			 console.log("value::: "+key.length);
			 console.log("value::: "+key);
		if(resp[key].XMLError != "Error"){
			 console.log("in if ::: "+key);
			$("#hdfcNetPremium").val(resp[key].NUM_NET_PREMIUM);
			$("#hdfcTotalTax").val(resp[key].NUM_SERVICE_TAX);
			$("#hdfcTotalPremium").val(resp[key].NUM_TOTAL_PREMIUM);
			
			var sr=$("#compSerial").val() ;
			sr++;
			
			
			
			nKey = parseInt(key) + 1;
			var extraCover= $("#extraCover").val();
//			old_alert("checkBoxId in Hdfc-->"+"companyCheck"+sr);
			var row = '<input type="checkbox" name="checkbox" id="companyCheck'+sr+'" onclick="selectCompanyAction('+sr+');">';
			var extr = "<div onclick='showExtraCompanyCovers("+gicId+");'  data-backdrop='static' id='cmpCovers' data-toggle='modal' data-target='#extraCompanyCovers'><u class='pointer' style='color:#283FE6;'>Extra Cover</u></div>" ;
			
			//old_alert(row2);
			 // newRow=$("#companyDetails").append(row2);
			if(resp[key].TXT_ERR_MSG == "")
			{
				
				$("#compSerial").val(sr);
			 dataTable.row.add([row,'',extr,resp[key].GICNAME,
			                    resp[key].POLICY_STARTDATE,
			                   '1',resp[key].POLICY_ENDDATE,resp[key].DISCOUNTLIMIT,
			                   '',resp[key].NUM_BASIC_OD_PREMIUM,
			                   resp[key].NUM_TP_RATE,resp[key].NUM_NET_PREMIUM , resp[key].NUM_SERVICE_TAX,
			                   resp[key].NUM_TOTAL_PREMIUM ,resp[key].PROPOSALTYPE  , '','','4',
				                   '0',
				                  '0',
				                  '0',
				                 '0',
				                 '0','','','']).draw();
			 
				}else{
					old_alert("Error::" + resp[key].TXT_ERR_MSG);
				}
			 }else{
				 hideWait();
				//old_alert("error---------- Hdfc Premium");
			}
		$("#companyDtlDiv").show();
		});
		
		/*if(!$.fn.DataTable.isDataTable("#companyDetails")){
			console.log("HDFC companyDetails");
			$("#companyDetails").DataTable({
				"lengthMenu": [5, 10, 20, 50],
		        "pageLength": 5,
		        order: [[2, 'asc']],
				   columnDefs: [{
				      targets: "_all",
				      orderable: false
				   }]
			});
		}*/
	  
	},
	error: function (resp) {
		  hideWait()
		//old_alert("error---------- Hdfc Premium"+resp);
	},
       
   	
   });
}


function getProposalHdfcResponse() {
	showWait();

//old_alert("inside---------- Hdfc Proposal");

	proposalTypeUsed("4");
var vehicleClassCode = "";
var productId = $("#productname").val();
if (productId == "1") {
   vehicleClassCode = "37";
} else {
   vehicleClassCode = "45";
}

var preInsNoOfClaim = $("#prevNoOfClaims option:selected").text();
if (preInsNoOfClaim == 1) {
   preInsNoOfClaim = "0";
} else {
   //preInsNoOfClaim="1";
   preInsNoOfClaim = "0";
}

var isPreviousClaim = $("#prevNcb option:selected").text();
var preInsNcb = "";
if (isPreviousClaim != "") {
   /*isPreviousClaim="1";
   preInsNcb=isPreviousClaim;*/
   isPreviousClaim = "0";
   preInsNcb = "0";
} else {
   isPreviousClaim = "0";
   preInsNcb = "0";
}

var gender = "";
if (document.getElementById('insDtlGenMale').checked) {
   gender = "Male";
} else if (document.getElementById('insDtlGenFemale').checked) {
   gender = "Female";
} else if (document.getElementById('insDtlGenOther').checked) {
   gender = "Other";
}

//old_alert( " landmark==> "+ document.getElementById('insDtlCALandmark').value + "  Street==>>  "+document.getElementById('insDtlCAStreet').value +"   House==>>  "+ $("#insDtlCAHouse option:selected").text());
//old_alert( " Relation==> "+ $("#nomDtlRelation option:selected").text());		
document.getElementById('insDtlCAHouse').value
	
$.ajax({
	'url' : "user/HdfcIntegrationController?procName=HDFC"
		+ "&tableName=" + "AMCP"
       + "&request_for=" + "proposal"
       + "&rtoCity=" + $("#RTOSCity").val()
       + "&zoneID=" + $("#zoneId").val()
       + "&policyType=" + $("#policyTypeId").val() 
       + "&varid=" + $("#varience").val()
       + "&businessType=" + $("#proposal").val()
       + "&vehicleClassCode=" + vehicleClassCode
       + "&manufacturerCode=" + $("#vehicle").val() 
       + "&vehicleModelCode=" + $("#Model").val()
       + "&customerAge=" + "35"
       + "&customerType=" + $("#customer").val()
       + "&occupationType=" + "1"
       + "&financeId=" + "0"
       + "&prevGicId=" + ""			//$("#prevInsuranceCompany").val()
       + "&carAreaId=" + $("#insDtlCAPincode").val()
       + "&corrsCarId=" + $("#insDtlCAPincode").val()
       + "&OwnerDriverNomineeRelationship=" + $("#nomDtlRelation").val()
       + "&OwnerDriverAppointeeName=" + "0"
       + "&claimNo=" + preInsNoOfClaim
       + "&registrationDate=" + $("#dateofReg").val()
       + "&manufacturingYear=" + $("#dateofReg").val()
       + "&prePolicyStartDate=" + ""			//$("#prevFromDate").val()
       + "&SumInsured=" + $("#hdfcVehicleIDV").val()
       + "&rtoLocationCode=" + "1"
       + "&vehicleRegno=" +  $("#regNo").val()  
       + "&EngineNo=" +  document.getElementById('engineNo').value
       + "&ChassisNo=" + document.getElementById('chasisNo').value
       + "&lpgCngKit=" + "0"
       + "&FirstName=" + document.getElementById('insDtlFname').value
       + "&LastName=" + document.getElementById('insDtlLname').value
       + "&dob=" + document.getElementById('insDtlDob').value
       + "&Gender=" + gender
       + "&EmailId=" + document.getElementById('insDtlEmail').value
       + "&ContactnoMobile=" + document.getElementById('insDtlMobileNo').value
       + "&CarAddress1=" +"HARKISHAN PUBLIC SCHOOL"
       + "&CarAddress2=" + "MECOSABAGH ROAD"
       + "&CarAddress3=" + "199A"
       + "&CorresAddress1=" + "HARKISHAN PUBLIC SCHOOL"
       + "&CorresAddress2=" +  "MECOSABAGH ROAD"
       + "&CorresAddress3=" +  "199A"
       + "&nomineeName=" + "" 		//document.getElementById('nomDtlFname').value + " " + document.getElementById('nomDtlMname').value + " " + document.getElementById('nomDtlLname').value
       + "&covers=" + $("#allCovers").val()
       + "&nomineeAge=" + "0"
       + "&nomineeRelation=" + ""  //$("#nomDtlRelation option:selected").text()
       + "&prePolicyNo=" + document.getElementById('prevPolicyCoverNo').value
       + "&prevPolicyEndDate=" + $("#prevToDate").val()
       + "&Data1=" + "DD"
       + "&IsAgeDisc=" + "N"
       + "&electicalAcc=" + "0"
       + "&nonElecticalAcc=" + "0"
       + "&noOfllDrivers=" + "0"
       + "&paidDriverSi=" + "0"
       + "&noOfEmployees=" + "0"
       + "&unnamedSi=" + "0"
       + "&IsPreviousClaim=" + isPreviousClaim
       + "&NCBExpiringPolicy=" + preInsNcb
       + "&NCBRenewalPolicy=" + ""
       + "&IsEmergencyCover=" + "0"
       + "&isPaCoverOwnerDriver=" + "0"
       + "&IsZeroDeptCover=" + "0"
       + "&IsZeroDeptRollOver=" + "0"
       + "&IsRTICover=" + "0"
       + "&NoofUnNamedPassenger=" + "0"
       + "&NoofNamedPassenger=" + "0"
       + "&namedSi=" + "0"
       + "&numIsEmrAsstWiderCvr=" + "0"
       + "&isNcbProtection=" + "0"
       + "&isEngineGearBoxProtection=" + "0"
       + "&isCostOfConsumable=" + "0"
       + "&isLossOfUseDownProtection=" + "0"
       + "&exshowroomPrice=" + $("#hdfcExShowroomPrize").val()
       + "&motorGroupResponseGroupId=" + $("#groupID").val()
       + "&motorGroupResponseSessionId=" + $("#sessionId").val()
       + "&motorGroupResponseGicId=" + "4"
       + "&covVal=" +  $("#allCoversValue").val()
       + "&covNo=" + $("#allCoversNumber").val()
       + "&userId=" + "0"
       + "&userDesc=" + "WEB"
       + "&branchId=" + "0"
       + "&netPrem=" + $("#hdfcNetPremium").val()
       + "&finalPrem=" + $("#hdfcTotalPremium").val() 
       + "&serviceTax=" + $("#hdfcTotalTax").val()
       + "&iPAddress=" + "192.168.0.243"
       +"&FuelType=" + document.getElementById('fuelType').value
      + "&PanNo=" +  document.getElementById('insDtlPanNo').value
      + "&BiFuelType=" + $("#fuelKit :selected").text(),

       type: 'post',
       dataType: 'json',
       async: false,


       success: function (resp) {
    	   hideWait()
		
			$.each(resp, function (key, value) {
//				old_alert("success---------- HDFC proposal"+resp);
			if(resp[key].XMLError != "Error"){
				if(resp[key].WsStatus == "0"){
					console.log("in ws if ::" +resp[key].WsStatus )
			    $("#hdfcFinalProposalNo").val(resp[key].WsMessage);;
				var fname=resp[key].FirstName ;
				var lname=resp[key].LastName ;
				$("#insName").val(fname +" " +lname);
				
				nKey = parseInt(key) + 1;
				var row = '<input type="checkbox" name="checkbox" id="companyCheck'+nKey+'" onclick="selectCompanyAction('+nKey+');">';
				var row2="<tr><td>" + nKey+ "</td>" +
				"<td>" + resp[key].WsMessage + "</td>" +
				"<td>" + ""  + "</td>" +
				"<td>" +  $("#productname :selected").text() + "</td>" +
				"<td>" + resp[key].POLICY_STARTDATE + "</td>" +
				"<td>" + $("#insName").val() + "</td>" +
				"<td>" + hdfcTotalPremium + "</td></tr>";
				//old_alert(row2);
				
				$("#proposalDetails").append(row2);
				$("#premiumAmount").val($("#hdfcTotalPremium").val() );			//code for to set proposal amt in TF
				$("#premAmount").val($("#hdfcTotalPremium").val() );
				$("#proposalDetailsDiv").show();
				}
				else{
					$("#proposalDetailsDiv").hide();
				}
				
			}
			else{
				hideWait();
			}
			});
			
		},
		error: function (resp) {
			hideWait()
		},
   });

}

function getPaymentHdfcResponse(){
showWait();
$("#companyDtl_companyId").val("4");

var url = "HdfcPayment?CustomerId="+$("#hdfcFinalProposalNo").val()
		+ "&TxnAmount=" +  $("#premiumAmount").val()		
       + "&AdditionalInfo1=" + "NB"
       + "&AdditionalInfo2=" + "MOT"
       + "&AdditionalInfo3=" + "1"
       + "&hdnPayMode=" + "CC"
       + "&UserName=" + $("#insName").val()
       + "&UserMailId=" + document.getElementById('insDtlEmail').value
       + "&ProductCd=" + "MOT"
       + "&ProducerCd=" + "LEK0001-"+$("#hdfcFinalProposalNo").val();
       + "&userId=" + "0"
       + "&userDesc=" + "WEB"
       + "&branchId=" + "0"
       + "&motorGroupResponseGroupId=" + $("#groupID").val()
       + "&motorGroupResponseSessionId=" + $("#sessionId").val()
       + "&motorGroupResponseGicId=" + "4";
       
console.log("payment Url Hdfc==>>"+url);
window.open(url, "_blank");
hideWait()
}

//////////Integration for HDFC ERGO General Insurance ENDS here/////////// 

//////////Integration for Future Generali Insurance Starts here/////////// 

function getFutureGeneraliPremium() {
showWait();

$("#companyDtl_companyId").val("49");

var gicId = $("#companyDtl_companyId").val();
proposalTypeUsed(gicId);

findGicCompany($("#companyDtl_companyId").val());

////Dummy no's are used for Engine no and chasis no as it is compulsary for premium/////////////
var corrAddress  ="R",officeAddress="P";
var isPrevNCBSelected= "N", isNewVehicle="N";
    
if(!($("#prevNcb option:selected").text() == ""))
{
isPrevNCBSelected= "Y";
}
if(proposalId == "1")
{
isNewVehicle= "Y";     
}
/*var prevInsuranceCompany = $("#prevInsuranceCompany :selected").text();
old_alert("prevInsuranceCompany:::---" +prevInsuranceCompany)
if(prevInsuranceCompany.localeCompare("Prev. Insurance Company"))
{*/
//old_alert("RegDate::" +  $("#dateofReg").val())
var insDtlPAAreaID = $("#insDtlPAPincode").val();
var insDtlCAAreaID = $("#insDtlCAPincode").val();
var insDtlOAAreaID = $("#insDtlOAPincode").val();
//old_alert("insDtlPAAreaID::: " +insDtlPAAreaID)
if(insDtlPAAreaID == "--Select--" ){
//old_alert("In if");
insDtlPAAreaID="";
}
else{
insDtlPAAreaID = $("#insDtlPAPincode").val();
}
if(insDtlCAAreaID == "--Select--"){
insDtlCAAreaID="";
}
else{
insDtlCAAreaID = $("#insDtlCAPincode").val();
}
if(insDtlOAAreaID == "--Select--"){
insDtlOAAreaID="";
}
else{
insDtlOAAreaID = $("#insDtlOAPincode").val();
}
var insDtlGender="";
if (document.getElementById('insDtlGenMale').checked) {
insDtlGender = document.getElementById('insDtlGenMale').value;
}
else if (document.getElementById('insDtlGenFemale').checked) {
insDtlGender = document.getElementById('insDtlGenFemale').value;
}
else if (document.getElementById('insDtlGenOther').checked) {
insDtlGender = document.getElementById('insDtlGenOther').value;
}
//old_alert(insDtlGender);
var prevNcbVal= $("#prevNcb option:selected").text();
prevNcbVal="";
/*if(prevNcbVal == "Select")
{
prevNcbVal="";
}else{
prevNcbVal= $("#prevNcb option:selected").text();
}*/
var	prevInsuranceCompany="";
$.ajax({
'url' :"motorCalculatorFutureGenerali?procName=" + "FUTURE"
   + "&table_name=" +"AMCP"
   + "&request_for=" +"premium"
   + "&rtoCityId="+ $("#RTOSCity").val()
   + "&zoneId="+  $("#zoneId").val()
   + "&policyType="+ $("#policyTypeId").val() 
   + "&varID="+ $("#varience").val()
   + "&businessType="+  $("#proposal").val()
   + "&productID="+  $("#productname").val()   
   + "&vehID="+ $("#vehicle").val()   
   + "&modelID="+ $("#Model").val() 
   + "&clienttype="+  $("#customer").val() 
   + "&finId="+  $("#finDtlFinType").val()  //financeDetailsMotIntgBean.getFinDtlFinById()   
   + "&prevGICID="+ prevInsuranceCompany
   + "&nomRel="+ $("#nomDtlRelation").val()
   + "&nomRelRep="+"" 
   + "&occupationID="+"" 
   + "&ownDriAppointeeRel="+"" 
   + "&prevProduct="+  $("#prevProduct").val()
   + "&prevPolicyType="+ $("#prevPolicyType").val() 
   + "&finAgreementType="+ $("#finDtlFinType").val() 
   + "&bodyType="+$("#bodyType option:selected").text()
   + "&rAreaId="+insDtlCAAreaID
   + "&pAreaId="+insDtlOAAreaID
   + "&addressType1="+corrAddress  
   + "&addressType2="+officeAddress
   + "&covers="+$("#allCovers").val()
   + "&coverVal="+  $("#allCoversValue").val()
   + "&coverNo="+ $("#allCoversNumber").val()
   + "&RTOCode="+ $("#vehRegNo1").val()  + $("#vehRegNo2").val() 
   + "&salutId="+  $("#insDtlInitial").val() 
   + "&addrType="+"" 
   + "&addrType1="+"R" 
   + "&addrType2="+"P"
   + "&policyNo="+""
   + "&clientID="+""
   + "&receiptNo="+""
   + "&referenceNo="+""
   + "&customerType="+ $("#custType").val()
   + "&firstName="+  $("#insDtlFname").val()
   + "&lastName="+ $("#insDtlLname").val()
   + "&dob="+ $("#insDtlDob").val() 
   + "&gender="+ insDtlGender 
   + "&maritalStatus="+ $("#insDtlMaritialStatus").val()
   + "&custPanNo="+ $("#insDtlPanNo").val()
   + "&custGstIn="+""
   + "&custAddharNo="+ $("#insDtlAadharNo").val()
   + "&ckycNo="+""
   + "&eiaNo="+""
   + "&resAddrLine1="+""
   + "&resAddrLine2="+ $("#insDtlCAStreet").val()
   + "&resAddrLine3="+$("#insDtlCAHouse").val()
   + "&reslandmark="+ $("#insDtlCALandmark").val()
   + "&reshomePhoneNo="+  $("#insDtlPhoneNo").val()
   + "&resofficePhoneNo="+ $("#insDtlPhoneNo").val()
   + "&resfaxNo="+""
   + "&resmobileNo="+ $("#insDtlMobileNo").val()
   + "&emailId="+  $("#insDtlEmail").val()
   + "&officeAddrLine1="+ ""
   + "&officeAddrLine2="+$("#insDtlOAStreet").val()
   + "&officeAddrLine3="+$("#insDtlOAHouse").val()
   + "&officeLandMark="+$("#insDtlOALandmark").val()
   + "&officePhoneNo="+$("#insDtlPhoneNo").val()
   + "&officeTelPhNo="+$("#insDtlPhoneNo").val()
   + "&officeFaxNo="+""
   + "&officeMObNo="+$("#insDtlMobileNo").val()
   + "&officeemailId="+ $("#insDtlEmail").val()
   + "&checkType="+""
   + "&bsbCode="+""
   + "&transactionDate="+""
   + "&receiptType="+""
   + "&amount="+""
   + "&tranRefNo="+""
   + "&tranRefNoDate="+""
   + "&typeOfVehicle="+""
   + "&vehicleClass="+""
   + "&registrationNo="+"MH01BF0028"
   + "&registrationDate="+ $("#dateofReg").val()
   + "&manufacturingYear="+ $("#year :selected").text().trim()
   + "&inbuiltKit="+""
   + "&IVDOfCNGOrLPG="+""
   + "&engineNo="+"52WVC100056"
   + "&chassiNo="+"1HGBH41JXMN1000002"
   + "&cubicCapacity="+ $("#cc").val()
   + "&seatingCapacity="+$("#passengers").val()
   + "&idv="+"0"
   + "&grossWeigh="+$("#kg").val()
   + "&carriageCapacityFlag="+""
   + "&trailerTowedBy="+""
   + "&trailerRegNo="+""
   + "&noOfTrailer="+""
   + "&trailerValLimPaxIDVDays="+""
   + "&ncb="+""
   + "&privateCommercialUsage="+""
   + "&imt23="+""
   + "&CPAReq="+"N"
   + "&CPANomName="+""
   + "&CPANomAge="+""
   + "&CPANomAgeDet="+""
   + "&CPANomPerc="+""
   + "&CPAAppointeeName="+""
   + "&CPAAppointeRel="+""
   + "&NPAReq="+"N"
   + "&NPAName="+""
   + "&NPALimit="+""
   + "&NPANomName="+""
   + "&NPANomAge="+""
   + "&NPANomAgeDet="+""
   + "&NPAAppinteeName="+""
   + "&NPAAppinteeRel="+""
   + "&NPARel="+""
   + "&AddonReq="+""
   + "&CoverCode="+""
   + "&UsedCar="+""
   + "&PurchaseDate="+$("#dateofReg").val()
   + "&InspectionRptNo="+""
   + "&InspectionDt="+""
   + "&RollOver="+""
   + "&RollOverPolicyNo="+ $("#prevPolicyCoverNo").val()
   + "&InsuredName="+ $("#insDtlFname").val() + " " + $("#insDtlLname").val() 
   + "&PreviousPolExpDt="+  $("#prevToDate").val()
   + "&ClientCode="+""
   + "&rollOverAddress1="+""
   + "&rollOverAddress2="+$("#insDtlCAStreet").val()
   + "&rollOverAddress3="+ $("#insDtlCAHouse").val()
   + "&rollOverAddress4="+ $("#insDtlCALandmark").val()
   + "&rollOverAddress5="+""
   + "&rollOverPinCode="+ ""
   + "&rollOverInspectionRptNo="+""
   + "&rollOverInspectionDt="+""
   + "&NCBDeclartion="+""
   + "&ClaimInExpiringPolicy="+ isPrevNCBSelected
   + "&NCBInExpiringPolicy="+prevNcbVal
   + "&NewVehicle="+ isNewVehicle
   + "&newVehicleInspectionRptNo="+""
   + "&newVehicleInspectionDt="+""
   + "&TCSAmount="+"" 
   + "&motorGroupResponseGroupId=" + $("#groupID").val()
   + "&motorGroupResponseSessionId=" + $("#sessionId").val()
   + "&motorGroupResponseGicId=" + "49"
   + "&userId=0" 
   + "&userDesc=WEB"
   + "&branchId=0",
   'method' : 'post',
   'dataType' : 'json',
   'async' : true,
	success : function(resp) {
		hideWait()
		$.each(resp, function (key, value) {
			nKey = parseInt(key) + 1;
			$("#PolNoFuture").val(resp[key].PolNo);
			$("#premiumAmount").val(resp[key].premAmount);
			var errorMsg = resp[key].ErrorMessage;
			var statusMsg = resp[key].statusMsg;
			
			var sr=$("#compSerial").val() ;
			sr++;
			$("#compSerial").val(sr);
			
			var row = '<input type="checkbox" name="checkbox" id="companyCheck'+sr+'" onclick="selectCompanyAction('+sr+');">';
			var row2="<tr><td>" + row + "</td>" +
					"<td></td>" +
					"<td>" + $("#companyName").val() + "</td>" +
					"<td>" + resp[key].RiskStartDate + "</td>" +
					"<td>" + resp[key].PolicyTenure + "</td>" +
					"<td>" + resp[key].PolicyEndDate + "</td>" +
					"<td>" + resp[key].discOd  + "</td>" +
					"<td>" + ""  + "</td>" +
					"<td>"+ resp[key].odValue + "</td>" +
					"<td>"+ resp[key].grossPayTP + "</td>" +
					"<td>"+ resp[key].grossPay + "</td>" +
					"<td>"+ resp[key].servTax + "</td>" +
					"<td>"+ resp[key].finalPremium + "</td>" +
					"<td>" + $("#proposalTf").val() + "</td>" +
					"<td>"+ resp[key].discOd + "</td>" +
					"<td>  </td>" +
					"<td>"+ "49" + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"</tr>";
			//old_alert(row2);
			if(statusMsg == "Successful"){
				$("#companyDetails tbody").append(row2);
			}else{
				old_alert(errorMsg);
			}
		});
		/*if(!$.fn.DataTable.isDataTable('#companyDetails')){
			$("#companyDetails").DataTable({});
		}*/
	},
	error : function(data){
		hideWait()
//		old_alert("In else of future");
	}
});
}

function getFutureGeneraliProposal()
{
	showWait();

	proposalTypeUsed("49");
var corrAddress  ="R",officeAddress="P";
var isPrevNCBSelected= "N", isNewVehicle="N";
var PreInsNcb = $("#prevNcb option:selected").text();
//old_alert("PreInsNcb::::>>>" + PreInsNcb);
if(!PreInsNcb == "")
{
isPrevNCBSelected= "Y";
}
/*var proposal = $("#proposal").val();*/
if(proposal == "1")
{
isNewVehicle= "Y";     
}
var insDtlPAAreaID = $("#insDtlPAPincode").val();
var insDtlCAAreaID = $("#insDtlCAPincode").val();
var insDtlOAAreaID = $("#insDtlOAPincode").val();
//old_alert("insDtlPAAreaID::: " +insDtlPAAreaID)
if(insDtlPAAreaID == "--Select--" ){
//old_alert("In if");
insDtlPAAreaID="";
}
else{
insDtlPAAreaID = $("#insDtlPAPincode").val();
}
if(insDtlCAAreaID == "--Select--"){
insDtlCAAreaID="";
}
else{
insDtlCAAreaID = $("#insDtlCAPincode").val();
}
if(insDtlOAAreaID == "--Select--"){
insDtlOAAreaID="";
}
else{
insDtlOAAreaID = $("#insDtlOAPincode").val();
}
var insDtlGender="";
if (document.getElementById('insDtlGenMale').checked) {
insDtlGender = document.getElementById('insDtlGenMale').value;
}
else if (document.getElementById('insDtlGenFemale').checked) {
insDtlGender = document.getElementById('insDtlGenFemale').value;
}
else if (document.getElementById('insDtlGenOther').checked) {
insDtlGender = document.getElementById('insDtlGenOther').value;
}
//old_alert(insDtlGender);
var	prevNcbVal= $("#prevNcb option:selected").text();
if(prevNcbVal == "Select")
{
prevNcbVal="";
}else{
prevNcbVal= $("#prevNcb option:selected").text();
}
var	prevInsuranceCompany="";
//old_alert("Policy No>>>>"+  $("#PolNoFuture").val());
$.ajax({
	'url' :"user/motorCalculatorFutureGenerali?procName=" + "FUTURE"
   + "&table_name=AMCP"
   + "&request_for=proposal"
   + "&rtoCityId="+ $("#RTOSCity").val()
   + "&zoneId="+ $("#zoneId").val()
   + "&policyType="+ $("#policyTypeId").val() 
   + "&varID="+  $("#varience").val()
   + "&businessType="+$("#proposal").val()
   + "&productID="+  $("#productname").val()     
   + "&vehID="+  $("#vehicle").val()      
   + "&modelID="+ $("#Model").val()    
   + "&clienttype="+  $("#customer").val()    
   + "&finId="+  $("#finDtlFinType").val()    
   + "&prevGICID="+prevInsuranceCompany
   + "&nomRel="+  $("#nomDtlRelation").val()
   + "&nomRelRep="+""
   + "&occupationID="+"" 
   + "&ownDriAppointeeRel="+"" 
   + "&prevProduct="+ $("#prevProduct").val()
   + "&prevPolicyType="+  $("#prevPolicyType").val() 
   + "&finAgreementType="+ $("#finDtlFinType").val() 
   + "&bodyType="+ $("#bodyType option:selected").text() 
   + "&rAreaId="+insDtlCAAreaID
   + "&pAreaId="+insDtlOAAreaID
   + "&addressType1="+corrAddress  
   + "&addressType2="+officeAddress
   + "&covers="+ $("#allCovers").val()
   + "&coverVal="+  $("#allCoversValue").val()
   + "&coverNo="+ $("#allCoversNumber").val()
   + "&RTOCode="+ $("#vehRegNo1").val()  + $("#vehRegNo2").val() 
   + "&salutId="+  $("#insDtlInitial").val() 
   + "&addrType="+"" 
   + "&addrType1="+"R" 
   + "&addrType2="+"P"
   + "&policyNo="+ "" //$("#PolNoFuture").val()
   + "&clientID="+""
   + "&receiptNo="+""
   + "&referenceNo="+""
   + "&customerType="+  $("#custType").val()
   + "&firstName="+  $("#insDtlFname").val()
   + "&lastName="+ $("#insDtlLname").val()
   + "&dob="+ $("#insDtlDob").val() 
   + "&gender="+  insDtlGender 
   + "&maritalStatus="+ $("#insDtlMaritialStatus :selected").text()
   + "&custPanNo="+ $("#insDtlPanNo").val()
   + "&custGstIn="+""
   + "&custAddharNo="+ $("#insDtlAadharNo").val()
   + "&ckycNo="+""
   + "&eiaNo="+""
   + "&resAddrLine1="+ $("#insDtlCAHouse").val()
   + "&resAddrLine2="+ $("#insDtlCAStreet").val()
   + "&resAddrLine3="+  $("#insDtlCAPincode option:selected").text()
   + "&reslandmark="+ $("#insDtlCALandmark").val()
   + "&reshomePhoneNo="+  $("#insDtlPhoneNo").val()
   + "&resofficePhoneNo="+  $("#insDtlPhoneNo").val()
   + "&resfaxNo="+""
   + "&resmobileNo="+ $("#insDtlMobileNo").val()
   + "&emailId="+   $("#insDtlEmail").val()
    + "&officeAddrLine1="+ $("#insDtlOAHouse").val()
   + "&officeAddrLine2="+ $("#insDtlOAStreet").val()
   + "&officeAddrLine3="+ $("#insDtlCAPincode option:selected").text()
   + "&officeLandMark="+ $("#insDtlOALandmark").val()
   + "&officePhoneNo="+ $("#insDtlPhoneNo").val()
   + "&officeTelPhNo="+ $("#insDtlPhoneNo").val()
   + "&officeFaxNo="+""
   + "&officeMObNo="+ $("#insDtlMobileNo").val()
   + "&officeemailId="+  $("#insDtlEmail").val()
   + "&checkType="+""
   + "&bsbCode="+""
   + "&amount="+	$("#premiumAmount").val()
   + "&tranRefNo="+""
   + "&typeOfVehicle="+""
   + "&vehicleClass="+""
   + "&registrationNo="+ $("#regNo").val()
   + "&registrationDate="+ $("#dateofReg").val()
   + "&manufacturingYear="+ $("#year :selected").text().trim()
   + "&inbuiltKit="+""
   + "&IVDOfCNGOrLPG="+""
   + "&engineNo="+$("#engineNo").val()
   + "&chassiNo="+ $("#chasisNo").val()
   + "&cubicCapacity="+ $("#cc").val()
   + "&seatingCapacity="+ $("#passengers").val()
   + "&idv="+"0"
   + "&grossWeigh="+ $("#kg").val()
   + "&carriageCapacityFlag="+""
   + "&trailerTowedBy="+""
   + "&trailerRegNo="+""
   + "&noOfTrailer="+""
   + "&trailerValLimPaxIDVDays="+""
   + "&ncb="+""
   + "&privateCommercialUsage="+""
   + "&imt23="+""
   + "&CPAReq="+"N"
   + "&CPANomName="+""
   + "&CPANomAge="+""
   + "&CPANomAgeDet="+""
   + "&CPANomPerc="+""
   + "&CPAAppointeeName="+""
   + "&CPAAppointeRel="+""
   + "&NPAReq="+"N"
   + "&NPAName="+""
   + "&NPALimit="+""
   + "&NPANomName="+""
   + "&NPANomAge="+""
   + "&NPANomAgeDet="+""
   + "&NPAAppinteeName="+""
   + "&NPAAppinteeRel="+""
   + "&NPARel="+""
   + "&AddonReq="+""
   + "&CoverCode="+""
   + "&UsedCar="+""
   + "&PurchaseDate="+ $("#dateofReg").val()
   + "&InspectionRptNo="+""
   + "&InspectionDt="+""
   + "&RollOver="+""
   + "&RollOverPolicyNo="+ $("#prevPolicyCoverNo").val()
   + "&InsuredName="+ $("#insDtlFname").val() + " " + $("#insDtlLname").val() 
   + "&PreviousPolExpDt="+ $("#prevToDate").val()
   + "&ClientCode="+""
   + "&rollOverAddress1="+ $("#insDtlCAHouse").val()
   + "&rollOverAddress2="+ $("#insDtlCAStreet").val()
   + "&rollOverAddress3="+ $("#insDtlCAPincode").val()
   + "&rollOverAddress4="+ $("#insDtlCALandmark").val()
   + "&rollOverAddress5="+""
   + "&rollOverPinCode="+  $("#insDtlCAPincode option:selected").attr('pincodeCA')
   + "&rollOverInspectionRptNo="+""
   + "&rollOverInspectionDt="+""
   + "&NCBDeclartion="+""
   + "&ClaimInExpiringPolicy="+isPrevNCBSelected
   + "&NCBInExpiringPolicy="+ prevNcbVal
   + "&NewVehicle="+isNewVehicle
   + "&newVehicleInspectionRptNo="+""
   + "&newVehicleInspectionDt="+""
   + "&TCSAmount="+"0.0"
   + "&motorGroupResponseGroupId=" + $("#groupID").val()
   + "&motorGroupResponseSessionId=" + $("#sessionId").val()
   + "&motorGroupResponseGicId=" + "49"
   + "&userId=0"
   + "&userDesc=WEB"
   + "&branchId=0" ,
   'method' : 'post',
   'dataType' : 'json',
   'async' : true,
	success : function(resp) {
		hideWait()
		$.each(resp, function (key, value) {
			nKey = parseInt(key) + 1;
			$("#PolNoFuture").val(resp[key].PolNo);
			$("#premiumAmount").val(resp[key].premAmount);
			$("#agentCodeFG").val(resp[key].agentCodeFG);
			$("#fuID").val(resp[key].FuID);
			var policyStatus = resp[key].PolicyStatusMsg;
			var clientStatusMsg = resp[key].ClientStatusMsg;
			var receiptStatusMsg = resp[key].ReceiptStatusMsg;
			var policyErrorMsg = resp[key].Policy_ErrorMessage;
			var clientErrorMsg = resp[key].Client_ErrorMessage;
			var receiptErrorMsg = resp[key].Receipt_ErrorMessage;

			var row2="<tr><td>" + nKey+ "</td>" +
			"<td>" + resp[key].FuID + "</td>" +
			"<td>" +  "NA" + "</td>" +
			"<td>" + $("#productname :selected").text()  + "</td>" +
			"<td>" + resp[key].startDate  + "</td>" +
			"<td>" +  resp[key].insuredName + "</td>" +
			"<td>" + resp[key].premAmount  + "</td></tr>";
			//old_alert(row2);
			/*if(policyStatus =="Successful" && clientStatusMsg == "Successful" && receiptStatusMsg =="Successful") 
			{
			old_alert(row2);
				$("#proposalDetails").append(row2);
				$("#premAmount").val(resp[key].premAmount);
			}*/
			//else
				/*if(policyStatus == "Fail" ||clientStatusMsg == "Fail" ||receiptStatusMsg =="Fail") 
	        {
	            if (policyErrorMsg == "") {
	                 old_alert(policyErrorMsg);
	            }
	            if (clientStatusMsg == "") {
	                 old_alert(clientStatusMsg);
	            }
	            if (receiptStatusMsg == "") {
	                 old_alert(receiptStatusMsg);
	            }
	        }*/
			$("#proposalDetails").append(row2);
			$("#premAmount").val(resp[key].premAmount);
	});
		
		 ($("#proposalDetails").DataTable());
},
	error : function(data){
		hideWait()
		console.log("In error of future");
	}
});
}

function getFutureGeneraliPayment()
{
showWait();
var url = "futureGPayment?" + "transactionId=" + $("#fuID").val()
+ "&paymentOption=" +"1"
+ "&responseUrl=" + "https://hopebox.co.in/FutureGPaymentStatus"
+ "&proposalNo=" + $("#fuID").val()
+ "&premiumAmount=" + $("#premiumAmount").val()
+ "&userIdentifier=" + "NA"
+ "&userIdFG=" + $("#agentCodeFG").val() 
+ "&FirstName=" +$("#nomDtlFname").val()
+ "&lastName=" + $("#nomDtlLname").val()
+ "&Email=" + $("#nomDtlEmail").val()
+ "&Phone=" + $("#insDtlPhoneNo").val()
+ "&motorGroupResponseGroupId=" +  $("#groupID").val()
+ "&motorGroupResponseSessionId=" + $("#sessionId").val()
+ "&motorGroupResponseGicId=" + $("#companyDtl_companyId").val()
+ "&userId=0"
+ "&userDesc=WEB" 
+ "&branchId=0"
+ "&IPAddress=" ;       

console.log("payment Url Future==>>"+url);
window.open(url, "_blank");
hideWait()
}


//////////Integration for Future Generali Insurance ENDS here/////////// 
//////////Integration for KOTAK Insurance Starts here/////////// 

function getKotakGicPremiumRequest() {
	showWait();
	var vehId = $("#vehicle").val();
	var custType = $("#customer").val();
//	old_alert("custType = " + custType);
// var custType = $("#customer option:selected").text();
// if (custType == " INDIVIDUAL") {
// custType = "17";
// }
	console.log("vehId="+vehId+" custType= " +custType);
//	old_alert("vehicleId=" + vehId);
//	old_alert("customer Type=" + custType);

	
	$("#companyDtl_companyId").val("56");
	var gicId=$("#companyDtl_companyId").val() ;
	proposalTypeUsed(gicId);
	
	findGicCompany($("#companyDtl_companyId").val());
	
	var dateOfReg = $("#dateofReg").val();
	var isPreviousClaim = $("#prevNcb option:selected").text();
	
	var basicIdv = $("#basicIdv").val();
	
	var isPreviousClaim = $("#prevNcb option:selected").text();
	var preInsNcb = "";
	if (isPreviousClaim != "") {
//	old_alert("--------if-------------------");
	// isPreviousClaim="1";
	// preInsNcb=isPreviousClaim;
	isPreviousClaim = "0";
	preInsNcb = "0";
	} else {
//	old_alert("--------else-------------------");
	isPreviousClaim = "0";
	preInsNcb = "0";
	}
	$.ajax({
	'url' :"KotakGicIntegrationController?request_for="+"premium"
	// + "&businessType=" + "New Business")
	
	// ///////////////////////FOR PROCEDURE//////////////////////
	+"&procName=" + "Kotak"
	+ "&tableName=" + "AMCP"
	+ "&rtoCity=" + $("#RTOSCity").val()
	+ "&zoneID=" + $("#zoneId").val()
	+ "&policyType=" +$("#policyTypeId").val() 
	+ "&varid=" +$("#varience").val()
	+ "&businessType=" + $("#proposal").val()
	+ "&customerType="  + custType
	+ "&vehicleClassCode=" + "0"
	+ "&manufacturerCode=" + $("#vehicle").val()
	// + "&occupationType=" + "1"
	+ "&financeId=" + "0"
	+ "&prevGicId=" + "0"
	+ "&carAreaId=" + ""
	+ "&corrsCarId=" +""
	+ "&OwnerDriverNomineeRelationship="+ ""
	+ "&OwnerDriverAppointeeRelationship=" + "0"
	+ "&titleId" + ""
	+ "&marStatusId" + ""
	+ "&finAgreeType" + ""
	+ "&covers=" + $("#allCovers").val()
	+ "&covVal=" +  $("#allCoversValue").val()
	+ "&covNo=" +$("#allCoversNumber").val()
	
	// //////////////FOR JSON//////////////////////////////////
	+ "&rtoCode=" + "MH27"  
	// + "&vehicleMake=" + "Maruti"
	// + "&makeCode=" + "14"
	// + "&vehicleModel=" + "Alto 800")
	+ "&VehicleModelCode=" + "246"
	// + "&VehicleVariant=" + "Lx Bs - Iv")
	// + "&VehicleVariantCode=" + "97"
	+ "&DateofRegistration="+ $("#dateofReg").val()
	+ "&idv="+ Math.round($("#basicIdv").val())
	+ "&returnToInvoice=" + "False"
	+ "&EngineProtect=" + "False"
	+ "&RoadsideAssistance=" + "True"
	+ "&DepreciationCover=" + "True"
	+ "&ConsumableCover=" + "True"
	// + "&PreviousClaim=" + "true")
	+ "&PreviousClaim=" + null
	// + "&PreviousYearNCBPercentage=" + "0")
	+ "&PreviousYearNCBPercentage=" + preInsNcb
	
	+ "&NCBPercentage=" + "0"
	+ "&near=" + "False"
	+ "&EAR=" + "False"
	+ "&cng=" + "False"
	+ "&NEARSumInsured=" + "0"
	+ "&EARSumInsured=" + "0"
	+ "&CNGSumInsured=" + "0"
	// + "&accessKey=" + "7201808BD7284BD18D68D7B3A0B90024")
	// + "&typeOfPolicyHolder=" + "Individual Owner")
	// + "&CustomerType=" + "Individual")
	// + "&CustomerType=" + $("#customer option:selected").text()
	
	+ "&PreviousPolicyTypeOrTypeofCover=" + ""
	// + "&TotalClaimCount=" + "No Claim")
	// + "&TotalClaimCount=" + $("#prevNoOfClaims").val()
	+ "&TotalClaimCount=" + "" 
	+ "&PreviousPolicyExpiryDate=" + $("#prevToDate").val()
	+ "&VoluntaryDeductibleAmount=" + "0"
	+ "&IsPACoverForUnnamedPersons=" + "False"
	+ "&NumberofPersonsUnnamed=" + "0"
	+ "&CapitalSumInsuredPerPersonUnnamed=" + "0"
	+ "&IsPACoverForNamedPersons=" + "False"
	+ "&NumberofPersonsNamed=" + "0"
	+ "&CapitalSumInsuredPerPersonNamed=" + "0"
	+ "&IsPACoverForPaidDriver=" + "False"
	+ "&NumberofPaidDrivers=" + "0"
	+ "&SumInsuredForPaidDriver=" + "0"
	+ "&WiderLegalLiabilityToPaidDriverCleanerConductorIMT28=" +"False"
	+ "&NoOfPersonWiderLegalLiability=" + "0"
	+ "&LegalLiabilityToEmployeesExcludingPaidDriverCleanerConductorIMT29=" + "False"
	+ "&NoofEmployees=" + "0"
	// + "&MarketMovement=" + "-15")
	+ "&BasicODDeviation=" + ""
	+ "&AddOnDeviation=" + ""
	+ "&InsuredProfession=" + ""
	+ "&InsuredCreditScore=" + ""
	+ "&InsureddrivingScore=" + ""
	+ "&motorGroupResponseGroupId=" + $("#groupID").val()
	+ "&motorGroupResponseSessionId=" + $("#sessionId").val()
	+ "&motorGroupResponseGicId=" + $("#companyDtl_companyId").val()
	// + "&motorGroupResponseGicId=" + "56"
	 + "&userId=0" 
	 + "&userDesc=WEB"
	 + "&branchId=0" 
	 + "&IpAddress=" ,
	
	 	'type' : 'post',
		'dataType' : 'json',
		'async' : false,
	 
		'success' : function(resp) {
//			destroyDataTable();
			hideWait()
//			old_alert("in success:==========:" + resp);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
			
			var enddt = resp[key].POLICY_ENDDATE;
//			old_alert("enddt = " + enddt);

			
			var sr=$("#compSerial").val() ;
			sr++;
			$("#compSerial").val(sr);
			
			var row = '<input type="checkbox" name="checkbox" id="companyCheck'+sr+'" onclick="selectCompanyAction('+sr+');">';
			var row2="<tr><td>" + row + "</td>" +
					"<td></td>" +
					"<td>" + $("#companyName").val() + "</td>" +
					"<td>" + resp[key].PolicyStartDate + "</td>" +
					"<td>" + "1" + "</td>" +
					"<td>" + resp[key].POLICY_ENDDATE + "</td>" +
					"<td>" + "0"  + "</td>" +
					"<td>" + ""  + "</td>" + 
					"<td>"+ resp[key].TotalPremiumOwnDamage + "</td>" +
					"<td>"+ resp[key].BasicTPPremium + "</td>" +
					"<td>"+ resp[key].NetPremium + "</td>" +
					"<td>"+ resp[key].ServiceTax + "</td>" +
					"<td>"+ resp[key].TotalPremium + "</td>" +
					"<td>" + $("#proposalTf").val() + "</td>" +
					"<td>"+ "0" + "</td>" +
					"<td>  </td>" +
					"<td>"+ "56" + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"<td style='display:none;'>" + 0 + "</td>" +
					"</tr>";
			// old_alert(row2);
			$("#companyDetails").append(row2);
		});
			$("#companyDtlDiv").show();
			if(!$.fn.DataTable.isDataTable('#companyDetails')){
				$("#companyDetails").DataTable({
					"lengthMenu": [5, 10, 20, 50],
			        "pageLength": 5,
				});
			}
	},
	error:function(data) {
		hideWait()
		 
	}
 });  
}

function getKotakProposal(){
	showWait();
	var custType = $("#customer").val();
//	old_alert("custType = " + custType);
	var veh = $("#vehicle").val();
//	old_alert("veh = " + veh);
	var modelId = $("#Model").val();
//	old_alert("modelId = " + modelId);
	var preInsNcb = "0"
	var to_dt = null;
	proposalTypeUsed("56");
	$.ajax({
		
		'url' : "user/KotakGicIntegrationController?request_for="+"Proposal"
		+"&procName=" + "Kotak"
        + "&tableName=" + "AMCP"
       + "&rtoCity=" + $("#RTOSCity").val()
       + "&zoneID=" + $("#zoneId").val()
       + "&policyType=" + $("#policyTypeId").val() 
       + "&varid=" + $("#varience").val()
       + "&businessType=" + $("#proposal").val()
       + "&vehicleClassCode=" + "0"
       + "&manufacturerCode=" + veh
       + "&VehicleModelCode=" + modelId
       + "&CustomerType=" + custType
       + "&occupationType=" + "1"
       + "&financeId=" + "0"
       + "&prevGicId=" + "0"
       + "&carAreaId=" + $('#insDtlPAPincode').val()
       + "&corrsCarId=" + $('#insDtlPAPincode').val()
       + "&OwnerDriverNomineeRelationship=" + $("#nomDtlRelation").val()
       + "&OwnerDriverAppointeeRelationship=" + "0"
        + "&titleId" + ""
        + "&marStatusId" + ""
        + "&finAgreeType" + ""
       + "&covers=" + $("#allCovers").val()
        + "&covVal=" + $("#allCoversValue").val()
       + "&covNo=" +$("#allCoversNumber").val()

      // //////////////FOR JSON//////////////////////////////////
 + "&rtoCode=" + $("#vehRegNo1").val()+$("#vehRegNo2").val()
// + "&vehicleMake=" + "Maruti"
// + "&makeCode=" + "14"
// + "&vehicleModel=" + URLEncoder.encode("Alto 800")
 + "&VehicleModelCode=" + $("#Model").val()
// + "&VehicleVariant=" + URLEncoder.encode("Lx Bs - Iv")
// + "&VehicleVariantCode=" + "97"
 + "&DateofRegistration=" + $("#dateofReg").val()
 + "&idv=" + $("#basicIdv").val()
 + "&returnToInvoice=" + "False"
 + "&EngineProtect=" + "False"
 + "&RoadsideAssistance=" + "True"
 + "&DepreciationCover=" + "True"
 + "&ConsumableCover=" + "True"
// + "&PreviousClaim=" + URLEncoder.encode("true")
       + "&PreviousClaim=" + null
// + "&PreviousYearNCBPercentage=" + URLEncoder.encode("0")
       + "&PreviousYearNCBPercentage=" + preInsNcb
       
 + "&NCBPercentage=" + "0"
 + "&near=" + "False"
 + "&EAR=" + "False"
 + "&cng=" + "False"
 + "&NEARSumInsured=" + "0"
 + "&EARSumInsured=" + "0"
 + "&CNGSumInsured=" + "0"
// + "&accessKey=" + URLEncoder.encode("7201808BD7284BD18D68D7B3A0B90024")
// + "&typeOfPolicyHolder=" + URLEncoder.encode("Individual Owner") //
// + "&CustomerType=" + URLEncoder.encode("Individual")
       + "&CustomerType=" + $('#customer option:selected').text()
// + "&PreviousPolicyTypeOrTypeofCover="
// +URLEncoder.encode("ComprehensivePolicy")
       + "&PreviousPolicyTypeOrTypeofCover=" + ""
// + "&TotalClaimCount=" + $("#prevNoOfClaims").val()
       + "&TotalClaimCount=" + ""
 + "&PreviousPolicyExpiryDate=" + to_dt
 + "&VoluntaryDeductibleAmount=" + "0"
 + "&IsPACoverForUnnamedPersons=" + "False"
 + "&NumberofPersonsUnnamed=" + "0"
 + "&CapitalSumInsuredPerPersonUnnamed=" + "0"
+ "&IsPACoverForNamedPersons=" + "False"
+ "&NumberofPersonsNamed=" + "0"
+ "&CapitalSumInsuredPerPersonNamed=" + "0"
+ "&IsPACoverForPaidDriver=" + "False"
 + "&NumberofPaidDrivers=" + "0"
 + "&SumInsuredForPaidDriver=" + "0"
 + "&WiderLegalLiabilityToPaidDriverCleanerConductorIMT28=" + "False"
 + "&NoOfPersonWiderLegalLiability=" + "0"
 + "&LegalLiabilityToEmployeesExcludingPaidDriverCleanerConductorIMT29=" + "False"
 + "&NoofEmployees=" + "0"
// + "&MarketMovement=" + URLEncoder.encode("-15")
 + "&BasicODDeviation=" + ""
 + "&AddOnDeviation=" + ""
 + "&InsuredProfession=" + ""
 + "&InsuredCreditScore=" + ""
 + "&InsureddrivingScore=" + "",
 
 	type : 'post',
	dataType : 'json',
	async : false,


success: function (resp) {
// old_alert("inside---------- Universal");

$.each(resp, function (key, value) {
	hideWait()
//	old_alert("success---------- Kotak proposal"+resp);
	
	var ErrorCode= resp[key].ErrorMessage;
	nKey = parseInt(key) + 1;
	var row = '<input type="checkbox" name="checkbox" id="companyCheck'+nKey+'" onclick="selectCompanyAction('+nKey+');">';
	var row2="<tr><td>" + nKey+ "</td>" +
	"<td>" + "N/A" + "</td>" +
	"<td>" + resp[key].QuoteNumber + "</td>" +
	"<td>" +  $("#productname :selected").text() + "</td>" +
	"<td>" + resp[key].PolicyStartDate + "</td>" +
	"<td>" + $('#nomDtlFname').val() + " " + $('#nomDtlMname').val() + " " + $('#nomDtlLname').val() +"</td>" +  
	"<td>" + resp[key].TotalPremium + "</td></tr>";
//	old_alert("ErrorCode" + ErrorCode);  
	if(ErrorCode==""){
		$("#proposalDetails").append(row2);
		$("#premAmount").val(resp[key].TotalPremium);
	}else{
		old_alert(resp[key].ErrDescription);
	}
});
},
error:function(data) {
	hideWait()
	 
}
	
	
});
}

function getKotakPayment() {
	
//	var date = new Date();  
//	var txnid = date.getSeconds() + date.getDay() + date.getMonth() + date.getYear() + date.getMilliseconds(); 
	
//	old_alert("txnid = " + txnid);
//	console.log("txnid = " + txnid);
//	old_alert("$(\"#premAmount\").val() = " + $("#premAmount").val());
	console.log("$(\"#premAmount\").val() = " + $("#premAmount").val());
	
	var url = "user/kotakPayment?sqlMstId=" + "180" + "&param=" + null
    + "&amount=" + $("#premAmount").val()
    + "&productinfo=" + "Payment"
    + "&SURL=" + "https://hopebox.co.in/UAT/KotakPaymentStatus"
    + "&FURL=" + "https://hopebox.co.in/UAT/KotakPaymentStatus"
    + "&CURL=" + "https://hopebox.co.in/UAT/KotakPaymentStatus"
     + "&key=" + "gtKFFx"
    + "&salt=" + "eCwWELxi"
    + "&FirstName=" + $("#nomDtlFname").val()
    + "&Email=" + $("#nomDtlEmail").val()
    + "&Phone=" + $("#insDtlPhoneNo").val()
//    + "&quotationNumber=" + quotationNo
    + "&customerID=" + $("#customerId").val()
//    + "&txnid=" + txnid
    + "&motorGroupResponseGroupId=" + $("#groupID").val()
    + "&motorGroupResponseSessionId=" + $("#sessionId").val()
    + "&motorGroupResponseGicId=" + $("#companyDtl_companyId").val()
    + "&userId=" + "0"
    + "&userDesc=" + "WEB"
    + "&branchId=" + "0"
    + "&IPAddress=" + "192.168.0.243";
    
    console.log("payment Url Kotak==>>"+url);
    payment = window.open(url, "_blank");
	
	geyPaymentDataFromDB();
	
}
//////////Integration for KOTAK Insurance ENDS here/////////// 
//////////Integration for Bajaj Allianz Insurance Starts here/////////// 
function getBajajPremiumForProposal() {
$("#companyDtl_companyId").val("43");
	
	var gicId = $("#companyDtl_companyId").val();
	proposalTypeUsed(gicId);
	
	findGicCompany($("#companyDtl_companyId").val());
	 
	var insDtlPAAreaID = $("#insDtlPAPincode").val();
	var insDtlCAAreaID = $("#insDtlCAPincode").val();
	var insDtlOAAreaID = $("#insDtlOAPincode").val();
	//old_alert("insDtlPAAreaID::: " +insDtlPAAreaID)
	if(insDtlPAAreaID == "--Select--" ){
		//old_alert("In if");
		insDtlPAAreaID="";
	}
	else{
		insDtlPAAreaID = $("#insDtlPAPincode").val();
	}
	if(insDtlCAAreaID == "--Select--"){
		insDtlCAAreaID="";
	}
	else{
		insDtlCAAreaID = $("#insDtlCAPincode").val();
	}
	if(insDtlOAAreaID == "--Select--"){
		insDtlOAAreaID="";
	}
	else{
		insDtlOAAreaID = $("#insDtlOAPincode").val();
	}
	var prevNcbVal= $("#prevNcb option:selected").text();
	if(prevNcbVal == "Select")
	{
		prevNcbVal="";
	}else{
		prevNcbVal= $("#prevNcb option:selected").text();
	}
	var prevInsCompanyID = "";
	
	if(!$("#prevInsuranceCompany").val()){
		prevInsCompanyID="";
	}
	else{
		prevInsCompanyID = $("#prevInsuranceCompany").val();
	}
	var prevToDate="";
	if(!$("#prevToDate").val())
	{
		prevToDate=null;
	}else{
		prevToDate=$("#prevToDate").val();
	}
	var rtoCity = $("#RTOSCity").val();
	var zoneId = $("#zoneId").val();
	var varienceId = $("#varience").val();
	var productId = $("#productname").val();
	var vehicleId = $("#vehicle").val();
	var modelId = $("#Model").val() ;
	var customerId =  $("#customer").val() ;
	var nomRelId = $("#nomDtlRelation").val() ;
	var prevProductId =  $("#prevProduct").val() ;
	var prevPolicyType =  $("#prevPolicyType").val() ;
	var finDtlFinType = $("#finDtlFinType").val()  ;
	var bodyTypeId = $("#bodyType").val()   ;
	var salutId =$("#insDtlInitial").val()   ;
	var allCovers = $("#allCovers").val() ;
	var covVal= $("#allCoversValue").val();
	var covNo=$("#allCoversNumber").val();
	var request_for = $("#proposalTf").val();
//	old_alert("request_for:::" +request_for);
	var regCode =  $("#vehRegNo1").val() + $("#vehRegNo2").val() +$("#vehRegNo3").val() + $("#vehRegNo4").val() ;
	var regDt=  $("#dateofReg").val();
	var vehIdv = $("#basicIdv").val() ;
	var mfrYear= $("#year option:selected").text().trim() ;
	var prevPolExpDt= prevToDate;
	var prevNcb= prevNcbVal;
	var prvClaimSts= "0";
	var NCBPercentage= "0";
	var prevPolNo= $("#prevPolicyCoverNo").val() ;
	var motorGroupResponseGroupId= $("#groupID").val();
	var motorGroupResponseSessionId=$("#sessionId").val();
	var motorGroupResponseGicId=$("#companyDtl_companyId").val() ;
	var motorGroupResponseGicName=$("#companyName").val() ;
	var userId= "0"	;
	var userDesc= "WEB";
	var branchId= "0";
	var IPAddress= "" ;
	var engineNo= $("#engineNo").val() ;
	var chasisNo=$("#chasisNo").val() ;
		$.ajax({
		'url' :"BajajAllianzIntegrationController",
		'data': {
			request_for : request_for,
			procName : "BAJAJ",
		    tableName :  "AMCP",
		    city  : rtoCity,
		    zoneID : zoneId,
		    policyType : $("#policyTypeId").val() ,
		    varid : varienceId,
		    businessType : $("#proposal").val(),
		    productId : productId,
		    vehId : vehicleId   ,
		    modelID : modelId,
		    customerType : customerId,
		    prevGicId : prevInsCompanyID,
		    financeId : "",
		    nomRel :  nomRelId,
		    prevProduct : prevProductId,
		    prevPolicyType : prevPolicyType,
		    finAgreementType : finDtlFinType,
		    bodyType : bodyTypeId,
		    presentAreaId : insDtlCAAreaID,
		    permanentCarId : insDtlPAAreaID,
		    PosPolicyNo : "",
		    salutationId : salutId,
		    covers :  allCovers,
		    covVal : covVal,
		    covNo : covNo,
		   regCode : regCode ,
		   regDt : regDt,
		   vehIdv : vehIdv,
		   mfrYear : mfrYear,
		prevPolExpDt : prevPolExpDt ,
		prevNcb : prevNcb,
		prvClaimSts : prvClaimSts,
		NCBPercentage : NCBPercentage,
		prevPolNo : prevPolNo ,
		motorGroupResponseGroupId : motorGroupResponseGroupId,
		motorGroupResponseSessionId : motorGroupResponseSessionId,
		motorGroupResponseGicId : motorGroupResponseGicId ,
		motorGroupResponseGicName : motorGroupResponseGicName ,
		userId : userId	,
		userDesc : userDesc,
		branchId : branchId,
		IPAddress : IPAddress,
	    engineNo: engineNo,
		chasisNo :chasisNo,
		},
		type : 'post',
    	dataType : 'json',
   		async : false,
   		success : function(resp) {
   			$.each(resp, function(key, value) {
   				if (resp[key].ErrorText == 0) {
   					$("#bajajQuouteNo").val(resp[key].transcId);
             
   				}
   			});
   		},
	});
}

function getBajajPremium() {
	showWait();

	$("#companyDtl_companyId").val("43");
	
	var gicId = $("#companyDtl_companyId").val();
	proposalTypeUsed(gicId);
	
	findGicCompany($("#companyDtl_companyId").val());
	 
	var insDtlPAAreaID = $("#insDtlPAPincode").val();
	var insDtlCAAreaID = $("#insDtlCAPincode").val();
	var insDtlOAAreaID = $("#insDtlOAPincode").val();
	//old_alert("insDtlPAAreaID::: " +insDtlPAAreaID)
	if(insDtlPAAreaID == "--Select--" ){
		//old_alert("In if");
		insDtlPAAreaID="";
	}
	else{
		insDtlPAAreaID = $("#insDtlPAPincode").val();
	}
	if(insDtlCAAreaID == "--Select--"){
		insDtlCAAreaID="";
	}
	else{
		insDtlCAAreaID = $("#insDtlCAPincode").val();
	}
	if(insDtlOAAreaID == "--Select--"){
		insDtlOAAreaID="";
	}
	else{
		insDtlOAAreaID = $("#insDtlOAPincode").val();
	}
	var prevNcbVal= $("#prevNcb option:selected").text();
	if(prevNcbVal == "Select")
	{
		prevNcbVal="";
	}else{
		prevNcbVal= $("#prevNcb option:selected").text();
	}
	var prevInsCompanyID = "";
	
	if(!$("#prevInsuranceCompany").val()){
		prevInsCompanyID="";
	}
	else{
		prevInsCompanyID = $("#prevInsuranceCompany").val();
	}
	var prevToDate="";
	if(!$("#prevToDate").val())
	{
		prevToDate=null;
	}else{
		prevToDate=$("#prevToDate").val();
	}
	var rtoCity = $("#RTOSCity").val();
	var zoneId = $("#zoneId").val();
	var varienceId = $("#varience").val();
	var productId = $("#productname").val();
	var vehicleId = $("#vehicle").val();
	var modelId = $("#Model").val() ;
	var customerId =  $("#customer").val() ;
	var nomRelId = $("#nomDtlRelation").val() ;
	var prevProductId =  $("#prevProduct").val() ;
	var prevPolicyType =  $("#prevPolicyType").val() ;
	var finDtlFinType = $("#finDtlFinType").val()  ;
	var bodyTypeId = $("#bodyType").val()   ;
	var salutId =$("#insDtlInitial").val()   ;
	var allCovers = $("#allCovers").val() ;
	var covVal= $("#allCoversValue").val();
	var covNo=$("#allCoversNumber").val();
	var request_for = $("#proposalTf").val();
//	old_alert("request_for:::" +request_for);
	var regCode =  $("#vehRegNo1").val() + $("#vehRegNo2").val() +$("#vehRegNo3").val() + $("#vehRegNo4").val() ;
	var regDt=  $("#dateofReg").val();
	var vehIdv = $("#basicIdv").val() ;
	var mfrYear= $("#year option:selected").text().trim() ;
	var prevPolExpDt= prevToDate;
	var prevNcb= prevNcbVal;
	var prvClaimSts= "0";
	var NCBPercentage= "0";
	var prevPolNo= $("#prevPolicyCoverNo").val() ;
	var motorGroupResponseGroupId= $("#groupID").val();
	var motorGroupResponseSessionId=$("#sessionId").val();
	var motorGroupResponseGicId=$("#companyDtl_companyId").val() ;
	var motorGroupResponseGicName=$("#companyName").val() ;
	var userId= "0"	;
	var userDesc= "WEB";
	var branchId= "0";
	var IPAddress= "" ;
	var engineNo= $("#engineNo").val() ;
	var chasisNo=$("#chasisNo").val() ;
		$.ajax({
		'url' :"BajajAllianzIntegrationController",
		'data': {
			request_for : request_for,
			procName : "BAJAJ",
		    tableName :  "AMCP",
		    city  : rtoCity,
		    zoneID : zoneId,
		    policyType : $("#policyTypeId").val() ,
		    varid : varienceId,
		    businessType : $("#proposal").val(),
		    productId : productId,
		    vehId : vehicleId   ,
		    modelID : modelId,
		    customerType : customerId,
		    prevGicId : prevInsCompanyID,
		    financeId : "",
		    nomRel :  nomRelId,
		    prevProduct : prevProductId,
		    prevPolicyType : prevPolicyType,
		    finAgreementType : finDtlFinType,
		    bodyType : bodyTypeId,
		    presentAreaId : insDtlCAAreaID,
		    permanentCarId : insDtlPAAreaID,
		    PosPolicyNo : "",
		    salutationId : salutId,
		    covers :  allCovers,
		    covVal : covVal,
		    covNo : covNo,
		   regCode : regCode ,
		   regDt : regDt,
		   vehIdv : vehIdv,
		   mfrYear : mfrYear,
		prevPolExpDt : prevPolExpDt ,
		prevNcb : prevNcb,
		prvClaimSts : prvClaimSts,
		NCBPercentage : NCBPercentage,
		prevPolNo : prevPolNo ,
		motorGroupResponseGroupId : motorGroupResponseGroupId,
		motorGroupResponseSessionId : motorGroupResponseSessionId,
		motorGroupResponseGicId : motorGroupResponseGicId ,
		motorGroupResponseGicName : motorGroupResponseGicName ,
		userId : userId	,
		userDesc : userDesc,
		branchId : branchId,
		IPAddress : IPAddress,
	    engineNo: engineNo,
		chasisNo :chasisNo,
		},
		type : 'post',
    	dataType : 'json',
   		async : false,
   		success : function(resp) {
//   			destroyDataTable();
   			hideWait()
			$.each(resp, function(key, value) {
			//	$("#companyDetails > tr").remove();
				nKey = parseInt(key) + 1;
				
				var sr=$("#compSerial").val() ;
				sr++;
				$("#compSerial").val(sr);
				
				var row = '<input type="checkbox" name="checkbox" id="companyCheck'+sr+'" onclick="selectCompanyAction('+sr+');">';
								var row2="<tr>" +
										"<td>" + row + "</td>" +
										"<td></td>" +
										"<td>" + resp[key].Company + "</td>" +
										"<td>" + resp[key].TERMSTARTDATE + "</td>" +
										"<td>" + 1 + "</td>" +
										"<td>" + resp[key].TERMENDDATE + "</td>" +
										"<td>"+  0 +"</td>"+
										"<td>"+ 0 +"</td>" +
										"<td>"+ 0 + "</td>" +
										"<td>"+ 0 + "</td>"+
										"<td>"+ resp[key].netprem + "</td>" +
										"<td>"+ resp[key].serviceTax  + "</td>" +
										"<td>"+ resp[key].totalnetprem  +"</td>" +
										"<td>"+ request_for + "</td>" +
										"<td>"+ 0 + "</td>" +
										"<td>"+ 0 +"</td>" +
										"<td>" + resp[key].GICID + "</td>" +
										"<td style='display:none;'>" + 0 + "</td>" +
										"<td style='display:none;'>" + 0 + "</td>" +
										"<td style='display:none;'>" + 0 + "</td>" +
										"<td style='display:none;'>" + 0 + "</td>" +
										"<td style='display:none;'>" + 0 + "</td>" +
												"</tr>";
								
								//old_alert(row2);
								if( resp[key].ErrorText =="0"){
									$("#companyDetails").append(row2);
								}else{
									old_alert(resp[key].ErrorMessages); 
								}
								//row ++;

							});
			
			$("#companyDtlDiv").show();
			if(!$.fn.DataTable.isDataTable("#companyDetails")){
				
				$("#companyDetails").DataTable({
					"lengthMenu": [5, 10, 20, 50],
			        "pageLength": 5,
				});
			}
		},
		error: function () {
			hideWait()
//			old_alert("Fail");
		}
		});

}


function getBajajPolicyIssue()
{
	getBajajPremiumForProposal();
	var insDtlPAAreaID = $("#insDtlPAPincode").val();
	var insDtlCAAreaID = $("#insDtlCAPincode").val();
	var insDtlOAAreaID = $("#insDtlOAPincode").val();
	//old_alert("insDtlPAAreaID::: " +insDtlPAAreaID)
	proposalTypeUsed("43");
	if(insDtlPAAreaID == "--Select--" ){
		//old_alert("In if");
		insDtlPAAreaID="";
	}
	else{
		insDtlPAAreaID = $("#insDtlPAPincode").val();
	}
	if(insDtlCAAreaID == "--Select--"){
		insDtlCAAreaID="";
	}
	else{
		insDtlCAAreaID = $("#insDtlCAPincode").val();
	}
	if(insDtlOAAreaID == "--Select--"){
		insDtlOAAreaID="";
	}
	else{
		insDtlOAAreaID = $("#insDtlOAPincode").val();
	}

	var prevInsCompanyID = "";
	
	if(!$("#prevInsuranceCompany").val()){
		prevInsCompanyID="";
	}
	else{
		prevInsCompanyID = $("#prevInsuranceCompany").val();
	}
	var rtoCity = $("#RTOSCity").val();
	var zoneId = $("#zoneId").val();
	var varienceId = $("#varience").val();
	var proposalId = $("#proposal").val();
	var productId = $("#productname").val();
	var vehicleId = $("#vehicle").val();
	var modelId = $("#Model").val() ;
	var customerId =  $("#customer").val() ;
	var nomRelId = $("#nomDtlRelation").val() ;
	var prevProductId =  $("#prevProduct").val() ;
	var prevPolicyType =  $("#prevPolicyType").val() ;
	var finDtlFinType = $("#finDtlFinType").val()  ;
	var bodyTypeId = $("#bodyType").val()   ;
	var salutId =$("#insDtlInitial").val()   ;
	var allCovers = $("#allCovers").val() ;
	var covVal= $("#allCoversValue").val();
	var covNo=$("#allCoversNumber").val();
	var frstnm=$("#insDtlFname").val();
	var lstnm= $("#insDtlLname").val();
	var Addr1= $("#insDtlCAHouse").val() + "," + $("#insDtlCAStreet").val();
	var Addr2= $("#insDtlCALandmark").val() + "," + $("#insDtlCADistrict").val();
	var Addr3=$("#insDtlCACity option:selected").text();
	var Addr5=  $("#insDtlCAState option:selected").text();
	var pincode=$("#insDtlCAPincode option:selected").attr('pincodeCA');
	var dob=  $("#insDtlDob").val() ;
	var email= $("#insDtlEmail").val();
	var midlName=  $("#insDtlMname").val();
	var mobNo= $("#insDtlMobileNo").val() ;
	var phone=$("#insDtlPhoneNo").val();
	var title= $("#insDtlInitial option:selected").text().trim();
	var adharNo= $("#insDtlAadharNo").val();
	var panNo=$("#insDtlPanNo").val();
	var request_for=$("#proposal option:selected").text();
	var motorGroupResponseGroupId= $("#groupID").val();
	var motorGroupResponseSessionId=$("#sessionId").val();
	var motorGroupResponseGicId=$("#companyDtl_companyId").val() ;
	var motorGroupResponseGicName=$("#companyName").val() ;
	var userId= "0"	;
	var userDesc= "WEB";
	var branchId= "0";
	var IPAddress= "" ;
	$.ajax({
		'url' :"BajajPolicyNo",
		'data' : {
			request_for : request_for,
			procName: "BAJAJ",
		    tableName:  "AMCP",
		    city:rtoCity,
		    zoneID: zoneId,
		    policyType: $("#policyTypeId").val()  ,
		    varid:varienceId,
		    businessType:proposalId,
		    productId:productId,
		    vehId: vehicleId   ,
		    modelID:modelId,
		    customerType: customerId,
		    prevGicId: prevInsCompanyID,
		    financeId: "",
		    nomRel:  nomRelId,
		    prevProduct: prevProductId,
		    prevPolicyType:prevPolicyType,
		    finAgreementType: finDtlFinType,
		    bodyType:bodyTypeId,
		    presentAreaId: insDtlCAAreaID,
		    permanentCarId: insDtlPAAreaID,
		    PosPolicyNo: "",
		    salutationId: salutId,
		    covers:  allCovers,
		    covVal: covVal,
		    covNo:covNo,
		   	frstnm: frstnm,
		    lstnm: lstnm,
		    Addr1: Addr1,
		    Addr2:Addr2,
		    Addr3:Addr3,
		    Addr5:  Addr5,
		    pincode:pincode,
		    dob:  dob,
		    email:email,
		    midlName: midlName,
		    mobNo: mobNo,
		    phone : phone,
		    title : title,
		    adharNo: adharNo,
		    panNo : panNo,
			motorGroupResponseGroupId : motorGroupResponseGroupId,
			motorGroupResponseSessionId : motorGroupResponseSessionId,
			motorGroupResponseGicId : motorGroupResponseGicId ,
			motorGroupResponseGicName : motorGroupResponseGicName ,
			userId : userId	,
			userDesc : userDesc,
			branchId : branchId,
			IPAddress : IPAddress,
		},
		    type : 'post',
	    	dataType : 'json',
	   		async : false,
	   		success: function (resp) {
		   		 old_alert("inside---------- bAJAJ");

		   		$.each(resp, function (key, value) {
		   			hideWait()
		   			old_alert("success---------- baJAJ proposal"+resp);
		   			
		   			var ErrorCode= resp[key].ErrorText;
		   			nKey = parseInt(key) + 1;
//		   			var row = '<input type="checkbox" name="checkbox" id="companyCheck'+nKey+'" onclick="selectCompanyAction('+nKey+');">';
		   			var row2="<tr><td>" + nKey+ "</td>" +
		   			"<td>" + "N/A" + "</td>" +
		   			"<td>" + "N/A" + "</td>" +
		   			"<td>" +  $("#productname :selected").text() + "</td>" +
		   			"<td>" + resp[key].TERMSTARTDATE + "</td>" +
		   			"<td>" + $('#nomDtlFname').val() + " " + $('#nomDtlMname').val() + " " + $('#nomDtlLname').val() +"</td>" +  
		   			"<td>" + resp[key].totalnetprem + "</td></tr>";
//		   			old_alert("ErrorCode" + ErrorCode);  
		   			if(ErrorCode==0){
		   				$("#proposalDetails").append(row2);
		   				$("#premAmount").val(resp[key].totalnetprem);
		   			}else{
		   				old_alert(resp[key].ErrDescription);
		   			}
		   		});
		   		},
		   		error:function(data) {
		   			hideWait()
		   			 
		   		}

			});
}


function  getBajajPayment()
{
	showWait();
	var motorGroupResponseGroupId= $("#groupID").val();
	var motorGroupResponseSessionId=$("#sessionId").val();
	var motorGroupResponseGicId=$("#companyDtl_companyId").val() ;
	var motorGroupResponseGicName=$("#companyName").val() ;
	var userId= "0"	;
	var userDesc= "WEB";
	var branchId= "0";
	var IPAddress= "" ;
	
	 var url= "BajajPayment?sqlMstId=" + "180" + "&param=" 
   + "&motorGroupResponseGroupId=" + motorGroupResponseGroupId
   + "&motorGroupResponseSessionId=" + motorGroupResponseSessionId
   + "&motorGroupResponseGicId=" + companyDtl_companyId
   + "&userId=" + userId
   + "&userDesc=" + userDesc
   + "&branchId=" + branchId
   + "&IPAddress=" + IPAddress;

	  payment = window.open(url, "_blank");
	  geyPaymentDataFromDB();
	/*  setTimeout(function(){
		  geyPaymentDataFromDB();
	  },5000);*/
	  hideWait()
}

function getBajajPolicyPdf()
{
	 $.ajax({
			'url' :"BajajGetPdf",
			type : 'post',
//		    dataType : 'json',
		   	async : false,
		   	success: function (resp) {
		   		hideWait() 	
			},
	   		error:function(data) {
			   			hideWait()
			   			 
			   		}
				});
}



//////////Integration for Bajaj Allianz Insurance Ends here/////////// 


function clearInsuredDetails()
{
	 $('#insDtlInitial,#insDtlMaritialStatus,#insDtlNationality,#insDtlCACountry,#insDtlCAState,#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode'+
			',#insDtlPACountry,#insDtlPAState,#insDtlPADistrict,#insDtlPAPincode,#insDtlOACountry,#insDtlOAState,#insDtlOADistrict,#insDtlOAPincode,#insDtlOACity,#insDtlPACity').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	 
	  $('#insDtlCAState,#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode'+
				',#insDtlPAState,#insDtlPADistrict,#insDtlPAPincode,#insDtlOAState,#insDtlOADistrict,#insDtlOAPincode,#insDtlOACity,#insDtlPACity').empty();
	   $('#insDtlCAState,#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode'+
				',#insDtlPAState,#insDtlPADistrict,#insDtlPAPincode,#insDtlOAState,#insDtlOADistrict,#insDtlOAPincode,#insDtlOACity,#insDtlPACity').append('<option value="">--Select--');
	 
	   
	 $("#insDtlCALandmark,#insDtlCAStreet,#insDtlCAHouse,#insDtlPALandmark,#insDtlPAStreet,#insDtlPAHouse,#insDtlOALandmark,#insDtlOAStreet,#insDtlOAHouse").val("");
	 $("#insDtlCALandmarkList,#insDtlCAStreetList,#insDtlCAHouseList,#insDtlPALandmarkList,#insDtlPAStreetList,#insDtlPAHouseList,#insDtlOALandmarkList,#insDtlOAStreetList,#insDtlOAHouseList").empty();
	 $('#insDtlFname,#insDtlMname,#insDtlLname,#insDtlMobileNo,#insDtlPhoneNo,#insDtlEmail,'+
			 '#insDtlPanNo,#insDtlAadharEnrollNo,#insDtlAadharNo,#insDtlCAName,#insDtlPAName,#insDtlOAOfcName').val("");
	 $('input[name="gender"').prop('checked',false);
	 if($("#insDtlChk1").prop("checked"))
	{
		 $("#insDtlChk1").prop("checked", false);
	} 
	 if($("#insDtlChk2").prop("checked"))
	{ 
		 $("#insDtlChk2").prop("checked", false);
	}
	 $("#insDtlDob").val("");
}
function clearNomineeDetails()
{
	 $('#nomDtlInitial,#nomDtlRelation,#nomDtlCountry,#nomDtlState,#nomDtlDistrict,#nomDtlCity,#nomDtlPincode').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	 
	  $('#nomDtlState,#nomDtlDistrict,#nomDtlCity,#nomDtlPincode').empty();
	  $('#nomDtlState,#nomDtlDistrict,#nomDtlCity,#nomDtlPincode').append('<option value="">--Select--');


	  
	  
	 $("#nomDtlLandmark,#nomDtlStreet,#nomDtlHouseNo").val("");
	 $("#nomDtlLandmarkList,#nomDtlStreetList,#nomDtlHouseNoList").empty();
	 $('#nomDtlFname,#nomDtlMname,#nomDtlLname,#nomDtlAdharNo,#nomDtlLandmark,#nomDtlEmail,#nomDtlphoneNo,#nomDtlMobileNo').val("");
	 if($("#nomDtlCheckBox").prop("checked"))
	{
		 $("#nomDtlCheckBox").prop("checked", false);
	} 
	 $("#nomDtlDOB").val("");
}

function copyPermanentAddress(){
	showWait();
	var insDtlCAName = $("#insDtlCAName").val();
	var insDtlCACountry = $("#insDtlCACountry").select2("val");
	var insDtlCAState = $("#insDtlCAState").select2("val");
	var insDtlCADistrict = $("#insDtlCADistrict").select2("val");
	var insDtlCACity = $("#insDtlCACity").select2("val");
	var insDtlCAPincode = $("#insDtlCAPincode").select2("val");
	var insDtlCALandmark = $("#insDtlCALandmark").val();
	var insDtlCAStreet = $("#insDtlCAStreet").val();
	var insDtlCAHouse = $("#insDtlCAHouse").val();

	var checked=$("#insDtlChk1").prop("checked");
      
      if(checked){
    	  if (!$('#insDtlCAName,#insDtlCACountry,#insDtlCAState,#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode,#insDtlCALandmark,#insDtlCAStreet,#insDtlCAHouse').val())
      		{
    			old_alert("Please select corresponding address");
    			 if($("#insDtlChk1").prop("checked"))
    			{ 
    				 $("#insDtlChk1").prop("checked", false);
    			}
    	  	}
    	 
    	  else{
    	$("#insDtlPAName").val(insDtlCAName);
    	
    	if(insDtlCACountry != "" || insDtlCACountry != "--Select--")
    	{
    		insDtlPACountry();
    		$("#insDtlPACountry").select2("val",insDtlCACountry);
    		
    	}else{
    		$("#insDtlPACountry").select2("val","");
    	}
    	if(insDtlCAState != "" || insDtlCAState != "--Select--")
    	{
    		insDtlPAState();
    		$("#insDtlPAState").select2("val",insDtlCAState);
    		
    	}else{
    		$("#insDtlPAState").select2("val","");
    	}
    	if(insDtlCADistrict != "" || insDtlCADistrict != "--Select--")
    	{
    		insDtlPADistrict();
      		$("#insDtlPADistrict").select2("val",insDtlCADistrict);
    		
    	}else{
    		$("#insDtlPADistrict").select2("val","");
    	}
    	if(insDtlCACity != "" || insDtlCACity != "--Select--")
    	{
    		insDtlPACity();
      		$("#insDtlPACity").select2("val",insDtlCACity);
    		
    	}else{
    		$("#insDtlPACity").select2("val","");
    	}
    	if(insDtlCAPincode != "" || insDtlCAPincode != "--Select--")
    	{
    		insDtlPAPincode();
      		$("#insDtlPAPincode").select2("val",insDtlCAPincode);
    		
    	}else{
    		$("#insDtlPAPincode").select2("val","");
    	}
    	if(insDtlCALandmark != "" || insDtlCALandmark != "--Select--")
    	{
    		insDtlPALandmark();
      		$("#insDtlPALandmark").val(insDtlCALandmark);
    		
    	}else{
    		$("#insDtlPALandmark").select2("val","");
    	}
    	if(insDtlCAStreet != "" || insDtlCAStreet != "--Select--")
    	{
    		insDtlPAStreet();
      		$("#insDtlPAStreet").val(insDtlCAStreet);
    		
    	}else{
    		$("#insDtlPAStreet").select2("val","");
    	}
    	if(insDtlCAHouse != "" || insDtlCAHouse != "--Select--")
    	{
    		insDtlPAHouse();
      		$("#insDtlPAHouse").val(insDtlCAHouse);
    		
    	}else{
    		$("#insDtlPAHouse").select2("val","");
    	}
      	}
      }else{
    	$("#insDtlPAName").val("");
	  	$("#insDtlPACountry").select2("val","");
//		  	insDtlPAState();
  		$("#insDtlPAState").select2("val","");
//	  		insDtlPADistrict();
  		$("#insDtlPADistrict").select2("val","");
//	  		insDtlPACity();
  		$("#insDtlPACity").select2("val","");
//	  		insDtlPAPincode();
  		$("#insDtlPAPincode").select2("val","");
//	  		insDtlPALandmark();
  		$("#insDtlPALandmark").val("");
//	  		insDtlPAStreet();
  		$("#insDtlPAStreet").val("");
//	  		insDtlPAHouse();
	  	$("#insDtlPAHouse").val("");
      }
      hideWait()
}

function copyOfficeAddress(){
	showWait();
	var insDtlPAName = $("#insDtlPAName").val();
	var insDtlPACountry = $("#insDtlPACountry").select2("val");
	var insDtlPAState = $("#insDtlPAState").select2("val");
	var insDtlPADistrict = $("#insDtlPADistrict").select2("val");
	var insDtlPACity = $("#insDtlPACity").select2("val");
	var insDtlPAPincode = $("#insDtlPAPincode").select2("val");
	var insDtlPALandmark = $("#insDtlPALandmark").val();
	var insDtlPAStreet = $("#insDtlPAStreet").val();
	var insDtlPAHouse = $("#insDtlPAHouse").val();

	var checked=$("#insDtlChk2").prop("checked");
      
      if(checked){
    	  if (!$('#insDtlPAName,#insDtlPACountry,#insDtlPAState,#insDtlPADistrict,#insDtlPACity,#insDtlPAPincode,#insDtlPALandmark,#insDtlPAStreet,#insDtlPAHouse').val())
      		{
    			old_alert("Please select Permanent Address");
    			 if($("#insDtlChk2").prop("checked"))
    			{ 
    				 $("#insDtlChk2").prop("checked", false);
    			}
    	  	}
    	  else{
//    	$("#insDtlOAOfcName").val(insDtlPAName);
    	if(insDtlPACountry != "" || insDtlPACountry != "--Select--")
    	{
    		insDtlOACountry();
      		$("#insDtlOACountry").select2("val",insDtlPACountry);
    	}else{
    	    $("#insDtlOACountry").select2("val","");
    	}
    	if(insDtlPAState != "" || insDtlPAState != "--Select--")
    	{
    		insDtlOAState();
      		$("#insDtlOAState").select2("val",insDtlPAState);
    	}else{
    		$("#insDtlOAState").select2("val","");
    	}
    	if(insDtlPADistrict != "" || insDtlPADistrict != "--Select--")
    	{
    		insDtlOADistrict();
      		$("#insDtlOADistrict").select2("val",insDtlPADistrict);
    	}else{
    		$("#insDtlOADistrict").select2("val","");
    	}
    	if(insDtlPACity != "" || insDtlPACity != "--Select--")
    	{
    		insDtlOACity();
      		$("#insDtlOACity").select2("val",insDtlPACity);
    	}else{
    		$("#insDtlOACity").select2("val","");
    	}
    	if(insDtlPAPincode != "" || insDtlPAPincode != "--Select--")
    	{
    		insDtlOAPincode();
      		$("#insDtlOAPincode").select2("val",insDtlPAPincode);
    	}else{
    		$("#insDtlOAPincode").select2("val","");
    	}
    	if(insDtlPALandmark != "" || insDtlPALandmark != "--Select--")
    	{
    		insDtlOALandmark();
      		$("#insDtlOALandmark").val(insDtlPALandmark);
    	}else{
    		$("#insDtlOALandmark").select2("val","");
    	}
    	if(insDtlPAStreet != "" || insDtlPAStreet != "--Select--")
    	{
    		insDtlOAStreet();
      		$("#insDtlOAStreet").val(insDtlPAStreet);
    	}else{
    		$("#insDtlOAStreet").select2("val","");
    	}
    	if(insDtlPAHouse != "" || insDtlPAHouse != "--Select--")
    	{
    		insDtlOAHouse();
      		$("#insDtlOAHouse").val(insDtlPAHouse);
    	}else{
    		$("#insDtlOAHouse").select2("val","");
    	}
    	
    	  }
    	  }else{
    	//$("#insDtlOAOfcName").val("");
	  	$("#insDtlOACountry").select2("val","");
//		  	insDtlOAState();
  		$("#insDtlOAState").select2("val","");
//	  		insDtlOADistrict();
  		$("#insDtlOADistrict").select2("val","");
//	  		insDtlOACity();
  		$("#insDtlOACity").select2("val","");
//	  		insDtlOAPincode();
  		$("#insDtlOAPincode").select2("val","");
//	  		insDtlOALandmark();
  		$("#insDtlOALandmark").val("");
//	  		insDtlOAStreet();
  		$("#insDtlOAStreet").val("");
//	  		insDtlOAHouse();
	  	$("#insDtlOAHouse").val("");
      }
      hideWait()
}



function copyNomineeAddress(){
	showWait();
	var insDtlCACountry = $("#insDtlCACountry").select2("val");
	var insDtlCAState = $("#insDtlCAState").select2("val");
	var insDtlCADistrict = $("#insDtlCADistrict").select2("val");
	var insDtlCACity = $("#insDtlCACity").select2("val");
	var insDtlCAPincode = $("#insDtlCAPincode").select2("val");
	var insDtlCALandmark = $("#insDtlCALandmark").val();
	var insDtlCAStreet = $("#insDtlCAStreet").val();
	var insDtlCAHouse = $("#insDtlCAHouse").val();

	var checked=$("#nomDtlCheckBox").prop("checked");

	  if(checked){
		  if (!$('#insDtlCACountry,#insDtlCAState,#insDtlCADistrict,#insDtlCACity,#insDtlCAPincode,#insDtlCALandmark,#insDtlCAStreet,#insDtlCAHouse').val())
		{
				old_alert("Please select corresponding address");
				 if($("#nomDtlCheckBox").prop("checked"))
				{ 
					 $("#nomDtlCheckBox").prop("checked", false);
				}
		  	}else{
		  		if(insDtlCACountry != "" || insDtlCACountry != "--Select--")
		    	{
		  			nomDtlCountry();
		  			$("#nomDtlCountry").select2("val",insDtlCACountry);
		    	}else{
		    		$("#nomDtlCountry").select2("val","");
		    	}
		  		if(insDtlCAState != "" || insDtlCAState != "--Select--")
		  		{
		  			nomDtlState();
		  			$("#nomDtlState").select2("val",insDtlCAState);
		  		}else{
		  			$("#nomDtlState").select2("val","");
		  		}
		  		if(insDtlCADistrict != "" || insDtlCADistrict != "--Select--")
		  		{
		  			nomDtlDistrict();
		  			$("#nomDtlDistrict").select2("val",insDtlCADistrict);
		  		}else{
		  			$("#nomDtlDistrict").select2("val","");
		  		}
		  		if(insDtlCACity != "" || insDtlCACity != "--Select--")
		  		{
		  			nomDtlCity();
		  			$("#nomDtlCity").select2("val",insDtlCACity);
		  		}else{
		  			$("#nomDtlCity").select2("val","");
		  		}
		  		if(insDtlCAPincode != "" || insDtlCAPincode != "--Select--")
		  		{
		  			nomDtlPincode();
		  			$("#nomDtlPincode").select2("val",insDtlCAPincode);
		  		}else{
		  			$("#nomDtlPincode").select2("val","");
		  		}
		  		if(insDtlCALandmark != "" || insDtlCALandmark != "--Select--")
		  		{
		  			nomDtlLandmark();
		  			$("#nomDtlLandmark").val(insDtlCALandmark);
		  		}else{
		  			$("#nomDtlLandmark").select2("val","");
		  		}
		  		if(insDtlCAStreet != "" || insDtlCAStreet != "--Select--")
		  		{
		  			nomDtlStreet();
		  			$("#nomDtlStreet").val(insDtlCAStreet);
		  		}else{
		  			$("#nomDtlStreet").select2("val","");
		  		}
		  		if(insDtlCAHouse != "" || insDtlCAHouse != "--Select--")
		  		{
		  			nomDtlHouseNo();
		  			$("#nomDtlHouseNo").val(insDtlCAHouse);
		  		}else{
		  			$("#nomDtlHouseNo").select2("val","");
		  		}
			}
		}else{
		  	$("#nomDtlCountry").select2("val","");
//		  	insDtlPAState();
			$("#nomDtlState").select2("val","");
//	  		insDtlPADistrict();
			$("#nomDtlDistrict").select2("val","");
//	  		insDtlPACity();
			$("#nomDtlCity").select2("val","");
//	  		insDtlPAPincode();
			$("#nomDtlPincode").select2("val","");
//	  		insDtlPALandmark();
			$("#nomDtlLandmark").val("");
//	  		insDtlPAStreet();
			$("#nomDtlStreet").val("");
//	  		insDtlPAHouse();
	  	$("#nomDtlHouseNo").val("");
	  }
	  hideWait()
	}
	

function getPaymentDetails(){
	refreshTablePaymentDetails();
	$("#paymentOptionsDiv").show();
//	old_alert($("#companyDtl_companyId").val());
	adjustPaymentOptions();
	$("#amountPaid").val($("#premAmount").val());
	if ($("#companyDtl_companyId").val() == "2") {
		
	if($("#payer").val()=="Agent")
	{
		$("#paymenttype").val("Agent");
			getReliancePolicyDetails();
			$("#availableCDTbalance").val($("#CDTBalanceAmount").val()) ;
			$("#amountPaid").val($("#premAmount").val()) ;
			var cdtAmount = $("#CDTBalanceAmount").val();
			var prmAmt = $("#premAmount").val();
			var remaining = cdtAmount - prmAmt ; 
			$("#balanceAfterTransaction").val(remaining) ;
			if($("#ModeOfPayment").val() == "CDT")
			{
				$("#paymentModeCDT").checked(true);
			}
			else if($("#ModeOfPayment").val() == "CD"){
				$("#paymentModeCD").checked(true);
			}
			
			var row2="<tr><td>"+"1"+"</td>" +"<td>" + $("#paymenttype").val() + "</td>" +
				"<td>" + $("#ModeOfPayment").val()  + "</td>" +
				"<td>" + $("#entryDate").val()  + "</td>" +
				"<td></td>" +
				"<td>" +   $("#premAmount").val() + "</td>" +
				"<td></td></tr>";
				
			$("#paymentDetails").append(row2);
	}
	else{
		$("#paymenttype").val("Customer");
		 $("#paymentModeOnline").attr('checked',true);
			if($('#paymentModeOnline').prop('checked'))
			 {
			 	$("#ModeOfPayment").val("Online");
			 }
			var row2="<tr><td>"+"1"+"</td>" +"<td>" + $("#paymenttype").val() + "</td>" +
			"<td>" + $("#ModeOfPayment").val()  + "</td>" +
			"<td>" + $("#entryDate").val()  + "</td>" +
			"<td></td>" +
			"<td>" +   $("#premAmount").val() + "</td>" +
			"<td></td></tr>";
			
		$("#paymentDetails").append(row2);
		}
	} else if($("#companyDtl_companyId").val() == "34") {
		$("#paymenttype").val("Customer");
		 $("#ModeOfPayment").val("Online");
		 $("#paymentModeOnline").attr('checked',true);

		 var row2="<tr><td>"+"1"+"</td>" +"<td>" + $("#paymenttype").val() + "</td>" +
		"<td>" + $("#ModeOfPayment").val()  + "</td>" +
		"<td>" + $("#entryDate").val()  + "</td>" +	"<td></td>" +
		"<td>" +   $("#premAmount").val() + "</td>" +
		"<td></td></tr>";
		
		$("#paymentDetails").append(row2);
	 }
	 else if($("#companyDtl_companyId").val() == "4") {
		 $("#paymenttype").val("Customer");
		 if($('#paymentModeCreditCard').prop('checked'))
			 {
			 	$("#ModeOfPayment").val("Credit card");
			 }
		 else if($('#paymentModeDebitCard').prop('checked'))
		 {
			 	$("#ModeOfPayment").val("Debit card");
		 }
			
			  
		 var row2="<tr><td>"+"1"+"</td>" +"<td>" + $("#paymenttype").val() + "</td>" +
			"<td>" + $("#ModeOfPayment").val()  + "</td>" +
			"<td>" + $("#entryDate").val()  + "</td>" +	"<td></td>" +
			"<td>" +   $("#premAmount").val() + "</td>" +
			"<td></td></tr>";
			
		$("#paymentDetails").append(row2);
	 }
	 else if($("#companyDtl_companyId").val() == "48") {
		 $("#paymenttype").val("Customer");
		 var row2="<tr><td>"+"1"+"</td>" +"<td>" + $("#paymenttype").val() + "</td>" +
			"<td>" + $("#ModeOfPayment").val()  + "</td>" +
			"<td>" + $("#entryDate").val()  + "</td>" +	"<td></td>" +
			"<td>" +   $("#premAmount").val() + "</td>" +
			"<td></td></tr>";
			
		$("#paymentDetails").append(row2);
	 }
	 else if($("#companyDtl_companyId").val() == "47") {
		 $("#paymenttype").val("Customer");
		 
		 var row2="<tr><td>"+"1"+"</td>" +"<td>" + $("#paymenttype").val() + "</td>" +
			"<td>" + $("#ModeOfPayment").val()  + "</td>" +
			"<td>" + $("#entryDate").val()  + "</td>" +	"<td></td>" +
			"<td>" +   $("#premAmount").val() + "</td>" +
			"<td></td></tr>";
			
		$("#paymentDetails").append(row2);
	 }
	 else if($("#companyDtl_companyId").val() == "49") {
		 $("#paymenttype").val("Customer");
		 var row2="<tr><td>"+"1"+"</td>" +"<td>" + $("#paymenttype").val() + "</td>" +
			"<td>" + $("#ModeOfPayment").val()  + "</td>" +
			"<td>" + $("#entryDate").val()  + "</td>"+
			"<td></td>" +
			"<td>" +   $("#premAmount").val() + "</td>" +
			"<td></td></tr>";
			
		$("#paymentDetails").append(row2);
	 }
	 else if($("#companyDtl_companyId").val() == "56") {			
         console.log("in getPaymentDetails");			
             $("#payModeCreditCard").attr('checked',true);				//                 $("#paymentModeCDT").checked(true)			
             $("#creditcardPay").show();			
		
//                 if($("#payModeCreditCard").prop("checked"))			
//                 {			
                     $("#ModeOfPayment").val("Credit card");			
//                 }			
             $("#paymenttype").val("Customer");			
             var row2="<tr><td>"+"1"+"</td>" +"<td>" + $("#paymenttype").val() + "</td>" +			
                    "<td>" + $("#ModeOfPayment").val()  + "</td>" +			
                    "<td>" + $("#entryDate").val()  + "</td>"+			
                    "<td></td>" +			
                "<td>" +   $("#premAmount").val() + "</td>" +			
                    "<td></td></tr>";			
                    			
            $("#paymentDetails").append(row2);			
		
     }
	 else if($("#companyDtl_companyId").val() == "43") {
			$("#paymenttype").val("Customer");
			 $("#ModeOfPayment").val("Online");
			 $("#paymentModeOnline").attr('checked',true);

			 var row2="<tr><td>"+"1"+"</td>" +"<td>" + $("#paymenttype").val() + "</td>" +
			"<td>" + $("#ModeOfPayment").val()  + "</td>" +
			"<td>" + $("#entryDate").val()  + "</td>" +	"<td></td>" +
			"<td>" +   $("#premAmount").val() + "</td>" +
			"<td></td></tr>";
			
			$("#paymentDetails").append(row2);
		 }
	
	
}



function clearPreviousDetails()
{
	
	 $('#prevInsuranceType,#prevPolicyPeriod,#prevMfrYear,#prevRtoState,#prevRtoCity,#prevVehicleType,#prevModel,#prevPolicyMonth,#prevVariance,#prevFuelKit,#prevPolicyType').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	 $('#prevMfrYear,#prevRtoState,#prevRtoCity,#prevVehicleType,#prevModel,#prevPolicyMonth,#prevVariance,#prevFuelKit,#prevPolicyType').empty();
	 $('#prevMfrYear,#prevRtoState,#prevRtoCity,#prevVehicleType,#prevModel,#prevPolicyMonth,#prevVariance,#prevFuelKit,#prevPolicyType').append('<option value="">--Select--');
	
	 $('#prevDtlRegDate,#prevPassanger,#prevCc,#prevPolicyCoverNo,#prevDtNmTransInInsPolicy,#prevInsFuelType,#prevVehId1,#prevVehId2,#prevVehId3,#prevVehId4,#prevFromDate,#prevDtNmTransInRcBk,#prevToDate,#prevIdv').val("");
	
	 $('#prevNcb').prop('selectedIndex',-1);
	 $('#prevOwnerType').prop('selectedIndex',-1);
	 $('#prevNoOfClaims').prop('selectedIndex',-1);
			 
	 if($("#prevChkNmTransInRcBk").prop("checked"))
		{
			 $("#prevChkNmTransInRcBk").prop("checked", false);
		} 
	 if($("#prevChkNmTransInInsPolicy").prop("checked"))
		{
			 $("#prevChkNmTransInInsPolicy").prop("checked", false);
		} 
}

function adjustPaymentOptions()
{
	
	if ($("#companyDtl_companyId").val() == "2") {
		 if($("#payer").val() == "Agent")
		{
			 $("#relExtPay").show();
			 $("#cdtBal").show();
			 $("#remainingBal").show(); 
			 $("#onlinePay").hide(); 
			 $("#cardPay").hide(); 
			
		}else 
			{
				$("#relExtPay").hide();
				 $("#cdtBal").hide();
				 $("#remainingBal").hide(); 
				 $("#onlinePay").show(); 
				 $("#cardPay").hide(); 
			}
	} 	
	 else if($("#companyDtl_companyId").val() == "34") {
		 $("#onlinePay").show(); 
		 $("#relExtPay").hide();
		 $("#cdtBal").hide();
		 $("#remainingBal").hide(); 
		 $("#cardPay").hide(); 
   }
	 else if($("#companyDtl_companyId").val() == "4") {
		 $("#onlinePay").hide(); 
		 $("#relExtPay").hide();
		 $("#cdtBal").hide();
		 $("#remainingBal").hide(); 
		 $("#cardPay").show(); 
		 
	 }
	 else if($("#companyDtl_companyId").val() == "48") {
//		ShreeRamPayment
	 }
	 else if($("#companyDtl_companyId").val() == "47") {
//		UniversalPayment
	 }
	 else if($("#companyDtl_companyId").val() == "49") {
		
	 }
	 else if($("#companyDtl_companyId").val() == "43") {
		 $("#onlinePay").show(); 
		 $("#relExtPay").hide();
		 $("#cdtBal").hide();
		 $("#remainingBal").hide(); 
		 $("#cardPay").hide(); 
   }
	
	
}



function inspectionState(){

	        $("#inspectionStateTF").empty();
			$("#inspectionStateTF").append('<option value="">--Select--');
			var resp = getRecordList("96",null);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#inspectionStateTF")
					.append(
						'<option value="' + resp[key].STATE_ID + '"> ' +
						resp[key].STATE_NAME +
						'');
			});
	}

function inspectionCity(){
	
	var inspectionDtlState = $("#inspectionStateTF").val();
	var total = null+"~"+inspectionDtlState+"~"+null +"~"+null;
	//old_alert("total>>"+total);
			$("#inspectionCityTF").empty();
			$("#inspectionCityTF").append('<option value="">--Select--');
			var resp = getRecordList("98",total);

			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#inspectionCityTF")
					.append(
						'<option value="' + resp[key].CITY_ID + '"> ' +
						resp[key].CITY_NAME +
						'');
			});
	}
function inspectionPincode(){
	
	var inspectionDtlState = $("#inspectionStateTF").val();
	var inspectionDtlCity = $("#inspectionCityTF").val();
	var total = null +"~"+inspectionDtlState+"~"+null+"~"+null+","+inspectionDtlCity;
	//old_alert("total>>"+total);
			$("#inspectionPincodeTF").empty();
			$("#inspectionPincodeTF").append('<option value="">--Select--');
			var resp = getRecordList("100",total);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#inspectionPincodeTF")
					.append(
						'<option  pincodeCA="' + resp[key].PINCODE + '" value="' + resp[key].AREA_ID + '"> ' +
						resp[key].AREA_NAME +
						'('+resp[key].PINCODE+')');
			});
		
}


function inspectionLandmark() {
	var areaId = $("#inspectionPincodeTF").val();
	// old_alert("areaId--->>"+areaId);
	 var total = "1"+"~"+areaId;
	//	old_alert("total>>"+total);
					$("#inspectionLandmarkList").empty();
					$("#inspectionLandmarkList").append('<option rel="" value="">--Select--');
					var resp = getRecordList("109",total);
					$.each(resp,function(key, value) {
					 nKey = parseInt(key) + 1;
					 $("#inspectionLandmarkList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
				/* 	  nomlandmarkId=resp[key].ADD_MST_ID; */
					});
}
					

function inspectionStreet() {
	var landmarkId =($("#inspectionLandmarkList option[value='" + $('#inspectionLandmarkTF').val() + "']").attr('rel'));
//	old_alert("landmarkId::" +landmarkId);
	if(landmarkId == undefined ||  landmarkId == "")
	{
//		old_alert("landmarkId:::" + landmarkId);
		landmarkId="0";
	}
	var total = "2"+"~"+landmarkId;
	//old_alert("total>>"+total);
			$("#inspectionStreetList").empty();
			$("#inspectionStreetList").append('<option rel="" value="">--Select--');
			var resp = getRecordList("109",total);
			$.each(resp,function(key, value) {
			nKey = parseInt(key) + 1;
			$("#inspectionStreetList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
			var streetId=resp[key].ADD_MST_ID;
		});
			}

function inspectionhouseNo(){
	var streetId =($("#inspectionStreetList option[value='" + $('#inspectionStreetTF').val() + "']").attr('rel'));
	if(streetId == undefined ||  streetId == "")
	{
//		old_alert("landmarkId:::" + streetId);
		streetId="0";
	} 
	var total = "3"+"~"+streetId;
	//old_alert("total>>"+total);
					$("#inspectionHouseList").empty();
					$("#inspectionHouseList").append('<option rel="" value="">--Select--');
					var resp = getRecordList("109",total);
					$.each(resp,function(key, value) {
					nKey = parseInt(key) + 1;
					$("#inspectionHouseList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
					streetId=resp[key].ADD_MST_ID;
				});
				
	}

function inspectionAgency(){
	 $("#companyDtl_companyId").val("2");
var gicId= $("#companyDtl_companyId").val();
    $("#inspectionAgencyTF").empty();
	$("#inspectionAgencyTF").append('<option value="">--Select--');
	var resp = getRecordList("273",gicId);
	$.each(resp, function (key, value) {
		nKey = parseInt(key) + 1;
		$("#inspectionAgencyTF")
			.append(
				'<option value="' + resp[key].INSP_AGENCY_ID + '"> ' +
				resp[key].INSP_AGENCY_NAME +
				'');
	});
}

function inspectionSurveyorName(){
	var gicId= $("#companyDtl_companyId").val();
	$("#inspectionSurveyorNameTF").empty();
	$("#inspectionSurveyorNameTF").append('<option value="">--Select--');
	var resp = getRecordList("274",gicId);
	$.each(resp, function (key, value) {
		nKey = parseInt(key) + 1;
		$("#inspectionSurveyorNameTF")
		.append(
				'<option value="' + resp[key].SURVEYOR_ID + '"> ' +
				resp[key].SURVEYOR_NAME +
		'');
	});
}
function inspectionSurveyorStatus(){
	$("#inspectionSurveyorStatus").empty();
	$("#inspectionSurveyorStatus").append('<option value="">--Select--');
	var resp = getRecordList("277",null);
	$.each(resp, function (key, value) {
		nKey = parseInt(key) + 1;
		$("#inspectionSurveyorStatus")
		.append(
				'<option value="' + resp[key].S_ID + '"> ' +
				resp[key].SURVEY_STATUS +
		'');
	});
}

function clearInspectionDetails()
{
	 $('#inspectionStateTF,#inspectionCityTF,#inspectionPincodeTF,#inspectionAgencyTF,#inspectionSurveyorNameTF,#inspectionSurveyorStatus').select2('data', {
		 allowClear : true,
	     id: null,
	     text: '--Select--'
	   });
	 
	  $('#inspectionStateTF,#inspectionCityTF,#inspectionPincodeTF,#inspectionAgencyTF,#inspectionSurveyorNameTF,#inspectionSurveyorStatus').empty();
	  $('#inspectionStateTF,#inspectionCityTF,#inspectionPincodeTF,#inspectionAgencyTF,#inspectionSurveyorNameTF,#inspectionSurveyorStatus').append('<option value="">--Select--');

	 $("#inspectionLandmarkTF,#inspectionStreetTF,#inspectionHouseTF,#inspectionAllotedDate,#inspectionDate,#narration").val("");
	 $("#inspectionLandmarkList,#inspectionStreetList,#inspectionHouseList").empty();
	 
	 $('#contactName,#contctnoTextfiels,#survInsNoTextfiels,#survInsContactNoTF,#inspDtlSurveyorContactNo,#inspDtlContactNo,#inspDtlContactPerName,#inspDtlSurvInspNo,#inspDtlInspAllotedDate,#inspDtlNarration').val("");
	 				
							
}
function clearVehicleFinDtls()
{
	$('#finDtlFinType,#finDtlFinBy,#finDtlFinName,#finDtlFinState,#finDtlFinCity').select2('data', {
		allowClear : true,
		id: null,
		text: '--Select--'
	});
}

$("#inspectionStateTF,#inspectionCityTF,#inspectionPincodeTF,#inspectionSurveyorNameTF,#inspectionAgencyTF,#inspectionSurveyorStatus").select2({
	allowClear : true,
	maximumSelectionSize : 1,
});

function geyPaymentDataFromDB()
{
//var status=true;
//while(status)
//	{

	$.ajax({
		'url' :"user/getPaymentStatus",
		type : 'post',
		dataType : 'json',
		async : false,
		success : function(data) {
			if(data[0].status!="null"){
			getPaymentData();
//			status=false;
			payment.close();
			}else{
//				geyPaymentDataFromDB();
				 setTimeout(function(){
					  geyPaymentDataFromDB();
				  },1000);
			}
		},
	});
//	}
}

function loadMotorLogin(){
//    old_alert("loading");

    $.ajax({
    	url: "loginMotorModel",
    	type: "post",
    	async: "false",
    	success: function(result){
   //   old_alert("success"+result);
    		$("#contentBody").html(result);
    		reLoadValidation();
			$("#showmodel").modal('show');
				$("#logModel").css({
					'width' : '60%'
				});
				$("#logModel").css({
					'height' : '230px'
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

function checkSession(){
	reLoadValidation();
	var status="";
	$.ajax({
		url : "checkUserLogin",
		type : 'post',
		dataType : 'json',
		async : false,
		success : function(data) {
//		old_alert(data[0].loginStatus);
			if(data[0].loginStatus=="yes"){
//				old_alert(data[0].loginStatus);
			//	refreshTable()
				coverDetails();
			
			}else{
				loadMotorLogin();
				
			}
		},
	});
	
		
}

function getCompanyPreview(savevar){
	$('#previewCompOD td').remove();
	$('#previewCompTP td').remove();
	
	 var check=false;
	 var count = $('table#companyDetails tr:last').index() + 1;
	 if(count !=0){
	 var sr=$("#compSerial").val();
		for(var i=1;i<=sr;i++){
			 if(document.getElementById('companyCheck'+i+'').checked){
				 check="true";
			 }
		}
		if(check==false){
			$("#previewCompany").modal("hide");
			old_alert('Please select company for Policy Calculation');
			$('html, body').animate({
			    scrollTop: ($('#companyDtlDiv').offset().top)
			},500);
		      
		}else{
			if(savevar=="save"){
				
			}else{
				$("#previewCompany").modal("show");
			}
		 
		
	 $("#saveRatingType").val("");
	 $("#saveRatingId").val("");
	 $("#saveRatingVal").val("");
	 $("#saveRateCalc").val("");
	 $("#saveAmount").val("");
	var ratingType="",ratingId="",ratingVal="",rateCalc="",amount="";
	
	var comapanyDtl=document.getElementById('prevCompDataCompList').value;
  
	var comapany = comapanyDtl.split(",");
//	old_alert("comapanyDtl--->>"+comapanyDtl+"  comapany-->>"+comapany);
	 $("#prevNetPremium").val(comapany[10]);
	 $("#prevCGst").val("0");
	 $("#prevSGst").val("0");
	 $("#prevUGst").val("0");
	 $("#prevIGst").val("0");
	 $("#prevTax").val(comapany[11] );
	 $("#prevTotalPremium").val(comapany[12]);
	 $("#prevServiceCharge").val("0");
	 $("#prevTotalPayment").val(comapany[12]);
	
	 
	 var poltyp=$("#policyTypeId").val();

	$.ajax({
	'url' : "motorIntgCalculationDetails?GROUP_ID="+$("#groupID").val()
		+ "&GIC_ID=" + comapany[16] 
		+ "&SESSION_ID=" + $("#sessionId").val()
		+ "&RESPONSE_TYPE=" + poltyp
		+ "&COV_TYPE=" + "1" 
		+ "&IP_ADDRESS=" + ""
		+ "&USERID=" +  "0"
		+ "&BRANCH_ID=" +"0"
		+ "&USERDESC=" + "WEB" ,

	      
	      	type : 'post',
			dataType : 'json',
			async : true,
			"dataSrc":"",

	success: function (resp) {
		if($.fn.DataTable.isDataTable('#previewCompOD')){
			console.log("previewCompOD table destroyed");
			 $('#previewCompOD').DataTable().destroy();
		}
		$.each(resp, function (key, value) {
			nKey = parseInt(key) + 1;
			var row2="<tr><td>" + nKey  + "</td>" +
					"<td>" + resp[key].COVERS  + "</td>"+
					"<td>" + resp[key].COV_RATE + "</td>"+
					"<td>" + resp[key].COV_VAL + "</td>" +
					"<td>" + resp[key].COV_AMOUNT + "</td>" +
					"</tr>";
			
			$("#previewCompOD").append(row2);
			
			var amt="",identity="",val="",rate="";
			
			amt=resp[key].COV_AMOUNT;
			identity=resp[key].DISC_IDENTITY;
			val=resp[key].COV_VAL;
			rate=resp[key].COV_RATE;

			if(amt>0){
               if(ratingType==""){
                   ratingType="1";
                   ratingId=identity;
                   ratingVal=""+val;
                   rateCalc=""+rate;
                   amount=""+amt;
               }else{
                   ratingType="~"+"1";
                   ratingId="~"+identity;
                   ratingVal="~"+""+val;
                   rateCalc="~"+""+rate;
                   amount="~"+""+amt;
               }
           }
		});
		
			
	},
	error: function (resp) {
		old_alert("error---------- Preview Company"+resp);
	},
	
	});
	
	
	
	$.ajax({
		'url' : "motorIntgCalculationDetails?GROUP_ID="+$("#groupID").val()
			+ "&GIC_ID=" + comapany[16] 
			+ "&SESSION_ID=" + $("#sessionId").val()
			+ "&RESPONSE_TYPE=" + poltyp
			+ "&COV_TYPE=" + "2" 
			+ "&IP_ADDRESS=" + ""
			+ "&USERID=" +  "0"
			+ "&BRANCH_ID=" +"0"
			+ "&USERDESC=" + "WEB" ,
		      
		      	type : 'post',
				dataType : 'json',
				async : true,
				"dataSrc":"",

		success: function (resp) {
			if($.fn.DataTable.isDataTable("#previewCompTP")){
				 $("#previewCompTP").DataTable().destroy();
				 console.log("previewCompTP table destroyed");
			}
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				
				var row2="<tr><td style='width: 40px'>" + nKey + "</td>" +
						"<td style='width: 150px'>" + resp[key].COVERS + "</td>"+
						"<td style='width: 60px'>" + resp[key].COV_RATE + "</td>"+
						"<td style='width: 40px'>" + resp[key].COV_VAL + "</td>" +
						"<td style='width: 80px'>" + resp[key].COV_AMOUNT + "</td>" +
						"</tr>";
				$("#previewCompTP").append(row2);
				var amt="",identity="",val="",rate="";
				
				amt=resp[key].COV_AMOUNT;
				identity=resp[key].DISC_IDENTITY;
				val=resp[key].COV_VAL;
				rate=resp[key].COV_RATE;

				if(amt>0){
	                if(ratingType==""){
	                    ratingType="1";
	                    ratingId=identity;
	                    ratingVal=""+val;
	                    rateCalc=""+rate;
	                    amount=""+amt;
	                }else{
	                    ratingType="~"+"1";
	                    ratingId="~"+identity;
	                    ratingVal="~"+""+val;
	                    rateCalc="~"+""+rate;
	                    amount="~"+""+amt;
	                }
	            }
				
				
			});
			
		},
		error: function (resp) {
			old_alert("error---------- Preview Company"+resp);
		},
		
		});
	
	/*if(!$.fn.DataTable.isDataTable("#previewCompOD")){
		 $("#previewCompOD").DataTable({
		        "lengthChange": false,
			});
	}
	if(!$.fn.DataTable.isDataTable('#previewCompTP')){
		 $('#previewCompTP').DataTable({
			//  "paging": false,
		        "lengthChange": false,
		     //   "bInfo" : false,
		   
			   order: [[0, 'asc']],
			   columnDefs: [{
			      targets: "_all",
			      orderable: false
			   }]
			});
	}*/
	
	 $("#saveRatingType").val(ratingType);
	 $("#saveRatingId").val(ratingId);
	 $("#saveRatingVal").val(ratingVal);
	 $("#saveRateCalc").val(rateCalc);
	 $("#saveAmount").val(amount);
	 
}
}
}
function saveUatMotorCalcIntgData(){
	showWait();
	var curresHouseId="",perHouseId="",offcHouseId="";
	curresHouseId=($("#insDtlCAHouseList option[value='" + $('#insDtlCAHouse').val() + "']").attr('rel'));
	perHouseId=($("#insDtlPAHouseList option[value='" + $('#insDtlPAHouse').val() + "']").attr('rel'));
	offcHouseId=($("#insDtlOAHouseList option[value='" + $('#insDtlOAHouse').val() + "']").attr('rel'));
	
	console.log(curresHouseId+"  "+perHouseId+"  "+offcHouseId);
	var addType="",addName="",areaId="",house="",houseId="",street="",streetId="",landmark="",landmarkId="";
	if(curresHouseId!="" && curresHouseId!=undefined){
		console.log("0000000000");
		addType="C";
		addName=document.getElementById('insDtlCAName').value;
		areaId=$("#insDtlCAPincode").val();
		house=document.getElementById('insDtlCAHouse').value;
		houseId=($("#insDtlCAHouseList option[value='" + $('#insDtlCAHouse').val() + "']").attr('rel'));
		street=document.getElementById('insDtlCAStreet').value;
		streetId=($("#insDtlCAStreetList option[value='" + $('#insDtlCAStreet').val() + "']").attr('rel'));
		landmark=document.getElementById('insDtlCALandmark').value;
		landmarkId=($("#insDtlCALandmarkList option[value='" + $('#insDtlCALandmark').val() + "']").attr('rel'));
	}
	if(perHouseId!="" && perHouseId!=undefined){
		console.log("1111111111");
		addType=addType+"~"+"P";
		addName=document.getElementById('insDtlPAName').value;
		areaId=$("#insDtlPAPincode").val();
		house=house+"~"+document.getElementById('insDtlPAHouse').value;
		houseId=houseId+"~"+($("#insDtlPAHouseList option[value='" + $('#insDtlPAHouse').val() + "']").attr('rel'));
		street=street+"~"+document.getElementById('insDtlPAStreet').value;
		streetId=streetId+"~"+($("#insDtlPAStreetList option[value='" + $('#insDtlPAStreet').val() + "']").attr('rel'));
		landmark=landmark+"~"+document.getElementById('insDtlPALandmark').value;
		landmarkId=landmarkId+"~"+($("#insDtlPALandmarkList option[value='" + $('#insDtlPALandmark').val() + "']").attr('rel'));
	}
	if(offcHouseId!="" && offcHouseId!=undefined){
		console.log("22222222222");
		addType=addType+"~"+"O";
		addName=document.getElementById('insDtlOAOfcName').value;
		areaId=$("#insDtlOAPincode").val();
		house=house+"~"+document.getElementById('insDtlOAHouse').value;
		houseId=houseId+"~"+($("#insDtlOAHouseList option[value='" + $('#insDtlOAHouse').val() + "']").attr('rel'));
		street=street+"~"+document.getElementById('insDtlOAStreet').value;
		streetId=streetId+"~"+($("#insDtlOAStreetList option[value='" + $('#insDtlOAStreet').val() + "']").attr('rel'));
		landmark=landmark+"~"+document.getElementById('insDtlOALandmark').value;
		landmarkId=landmarkId+"~"+($("#insDtlOALandmarkList option[value='" + $('#insDtlOALandmark').val() + "']").attr('rel'));
	}
	var isInspection="N", contactPersonName = "", inspContactNo = "", inspAddId = "", inspHouse = "",
    inspHouseId = "", inspStreet = "", inspStreetId = "", inspLandmark = "", inspLandmarkId = "";
	 var inspName=document.getElementById('inspDtlContactPerName').value;
     if(inspName != "" && inspName != undefined){
    	 console.log("inspection---if");
         isInspection="Y";
         contactPersonName = document.getElementById('inspDtlContactPerName').value;
         inspContactNo = document.getElementById('inspDtlContactNo').value;
         inspAddId = $("#inspectionPincodeTF").val();
         inspHouse = document.getElementById('inspectionHouseTF').value;
         inspHouseId = ($("#inspectionHouseList option[value='" + $('#inspectionHouseTF').val() + "']").attr('rel'));
         inspStreet = document.getElementById('inspectionStreetTF').value;
         inspStreetId = ($("#inspectionStreetList option[value='" + $('#inspectionStreetTF').val() + "']").attr('rel'));
         inspLandmark = document.getElementById('inspectionLandmarkTF').value;
         inspLandmarkId =($("#inspectionLandmarkList option[value='" + $('#inspectionLandmarkTF').val() + "']").attr('rel'));
     }else{
    	 console.log("inspection---else");
         isInspection = "N";
         contactPersonName = "";
         inspContactNo = "";
         inspAddId = "0";
         inspHouse = "";
         inspHouseId = "0";
         inspStreet = "";
         inspStreetId = "0";
         inspLandmark = "";
         inspLandmarkId = "0";
     }
     
     var isPrevious = "N", prevGicId = "", prevProdId = "", prevPolicyType = "", prevPeriod = "", prevStartDate = "",
     prevEndDate = "", prevIdv = "", prevInsType = "", prevPolicyNo = "", prevCovernoteNo = "", prevOwnerType = "",
     rcBookNameTransfer = "", rcBookNameTransferDate = "", insNameTransfer = "", insNameTransferDate = "",
     prevNcb = "", prevIsClaim = "", prevClaimNo = "", prevClaimDate = "", prevClaimAmount = "";
     var proposalid = $("#proposal").val();
     if(proposalid==4 || proposalid==5){
    	 console.log("previous---if");
    	 var noOfClaimApplicable = "N";
         var noOfClaim = $("#inspectionPincodeTF").val();
         if (noOfClaim !="0" && noOfClaim!=undefined && noOfClaim!="Select" && noOfClaim !="") {
             noOfClaimApplicable = "Y";
         }
    	 
         var month="";
         var polPrd= $("#prevPolicyPeriod").val();
         month=$("#prevPolicyMonth").val();
         if(polPrd=="One Year"){
             prevPeriod="12";
         }else if(polPrd=="Short Term"){
             month=month.replace(" Month", "");
             prevPeriod=month;
         }else{
             month=month.replace(" Year", "");
            prevPeriod= ""+ (Integer.parseInt(month))*12;
         }
         
         var rcBookName="false",rcBookNameDate="",inspolName="false",inspolNameDate="";
         if($("#prevChkNmTransInRcBk").prop("checked"))
 		{
        	 rcBookName="true";
        	 rcBookNameDate=$('#prevDtNmTransInRcBk').val();
 		} 
         if($("#prevChkNmTransInInsPolicy").prop("checked"))
  		{
        	 inspolName="true";
        	 inspolNameDate=$('#prevDtNmTransInInsPolicy').val();
  		} 
    	 
         isPrevious = "Y";
         prevGicId = $("#prevInsuranceCompany").val();
         prevProdId = $("#prevProduct").val();
         prevPolicyType = $("#prevPolicyType").val();
         //prevPeriod = URLEncoder.encode(prevInsDetailsMotIntgBean.getPreInsPeriod());
         prevStartDate = $('#prevFromDate').val();
         prevEndDate = $('#prevToDate').val();
         prevIdv = ((document.getElementById('prevIdv').value).replace(",", ""));
         prevInsType = $("#prevInsuranceType option:selected").text();
         prevPolicyNo = document.getElementById('prevPolicyCoverNo').value;
         prevCovernoteNo = document.getElementById('prevPolicyCoverNo').value;
         prevOwnerType = $("#prevOwnerType").val();
         rcBookNameTransfer = rcBookName
         rcBookNameTransferDate = rcBookNameDate
         insNameTransfer = inspolName
         insNameTransferDate = inspolNameDate
         prevNcb = $("#prevNcb").val();
         prevIsClaim = noOfClaimApplicable;
         prevClaimNo = "0" 		//URLEncoder.encode(prevInsDetailsMotIntgBean.getPreInsNoOfClaimSr());
         prevClaimDate = ""		//URLEncoder.encode(prevInsDetailsMotIntgBean.getPreInsNoOfClaimDate());
         prevClaimAmount = "0"	//URLEncoder.encode(prevInsDetailsMotIntgBean.getPreInsNoOfClaimAmt());
         
 	 }else{
 		 console.log("previous---else");
 		isPrevious = "N";
        prevGicId = "0";
        prevProdId = "0";
        prevPolicyType = "0";
        prevPeriod = "0";
        prevStartDate = "";
        prevEndDate = "";
        prevIdv = "0";
        prevInsType = "0";
        prevPolicyNo = "";
        prevCovernoteNo = "";
        prevOwnerType = "";
        rcBookNameTransfer = "";
        rcBookNameTransferDate = "";
        insNameTransfer = "";
        insNameTransferDate = "";
        prevNcb = "";
        prevIsClaim = "";
        prevClaimNo = "0";
        prevClaimDate = "";
        prevClaimAmount = "0";
 	 }
     
     var finType= $("#finDtlFinType option:selected").text(); 
     var finId= $("#finDtlFinType").val();
     var finCity= $("#finDtlFinCity").val();
     console.log("finType-->>"+finType+" finId--->>"+finId+" finCity-->>"+finCity);
     if(finType=="--Select--" || finType=="" || finType==null){
    	 finType="";
    	 finId="0";
     }
     if(finCity=="--Select--" || finCity=="" || finCity==null){
    	 finCity="0";
     }
     console.log("finType-->>"+finType+" finId--->>"+finId+" finCity-->>"+finCity);
     
     var insGen="";
     if (document.getElementById('insDtlGenMale').checked) {
		  insGen="M";
	 }
	 else if (document.getElementById('insDtlGenFemale').checked) {
		  insGen="F";
	 }
	 else if (document.getElementById('insDtlGenOther').checked) {
		 insGen="O";
	 }
     
     var comapanyDtl=document.getElementById('prevCompDataCompList').value;
     
 	 var comapany = comapanyDtl.split(",");
 	 
 	 var paymentData=document.getElementById('paymentDataAll').value;
     
 	 var paymentList = paymentData.split(",");
		
 	var date = new Date();
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();
	if (month < 10)
		month = "0" + month;
	if (day < 10)
		day = "0" + day;

	var currentDate = month  + "/" + day + "/" +  year ;
 	 
	getCompanyPreview("save");
	
     $.ajax({
 		'url' : "user/motorEntryController?appId="+"0"
 		+ "&opFlag=" + "N"
        + "&applicantId=" +  "0"
        + "&gicId=" + comapany[16]
        + "&proposalId=" + $("#proposal").val()
        + "&productId=" + $("#productname").val()
        + "&polPeriod=" + comapany[4]
        + "&startDate=" + comapany[3]
        + "&endDate=" + comapany[5]
        + "&idv=" + ((document.getElementById('basicIdv').value).replace(",", ""))
        + "&odTotal=" + comapany[8]
        + "&tpTotal=" + comapany[9]
        + "&totalPrem=" + comapany[12]
        + "&cgst=" + comapany[17]
        + "&sgst=" + comapany[18]
        + "&igst=" + comapany[19]
        + "&ugst=" + comapany[20]
        + "&totalTax=" + comapany[21]
        + "&netPrem=" + comapany[10]
        + "&serviceCharge=" + comapany[11]
        + "&totalPay=" + comapany[12]
        + "&payMode=" + "0"
        + "&rtoId=" + $("#RTOSCity").val()
        + "&zoneId=" + $("#zoneId").val()
        + "&varId=" + $("#varience").val()
        + "&mfrYear=" + $("#year").val()
        + "&mfrMonth=" + $("#month").val()
        + "&regDate=" +  $("#dateofReg").val()
        + "&fuelType=" +$('#varience option:selected').attr('relfuelTypeId')
        + "&regNo=" + ((document.getElementById('vehRegNo1').value)+" "+(document.getElementById('vehRegNo2').value)+" "+(document.getElementById('vehRegNo3').value)+" "+(document.getElementById('vehRegNo4').value))
        + "&chassisNo=" + document.getElementById('chasisNo').value
        + "&engineNo=" + document.getElementById('engineNo').value
        + "&passengers=" + document.getElementById('passengers').value
        + "&cc=" + document.getElementById('cc').value
        + "&bodyType=" + document.getElementById('bodyType').value
        + "&custType=" + document.getElementById('customer').value
        + "&insType=" + ""
        + "&policyNo=" + paymentList[2]
        + "&coverNoteNo=" + ""
        + "&salutationId=" +  $("#insDtlInitial").val()
        + "&firstName=" + document.getElementById('insDtlFname').value
        + "&middleName=" + document.getElementById('insDtlMname').value
        + "&lastName=" + document.getElementById('insDtlLname').value
        + "&dob=" + $("#insDtlDob").val()
        + "&aadharNo=" + document.getElementById('insDtlAadharNo').value
        + "&panNo=" + document.getElementById('insDtlPanNo').value
        + "&contactNo1=" + document.getElementById('insDtlPhoneNo').value
        + "&contactNo2=" + document.getElementById('insDtlMobileNo').value
        + "&emailId=" + document.getElementById('insDtlEmail').value
        + "&gender=" + insGen
        + "&bankId=" + $("#insBankDtlBankName").val()
        + "&accountNo=" + document.getElementById('insBankDtlAccNo').value
        + "&otherDet=" + document.getElementById('insBankDtlOtherDetails').value
        + "&addType=" + addType
        + "&addName=" + addName
        + "&addId=" + areaId
        + "&house=" + house
        + "&houseId=" + houseId
        + "&street=" + street
        + "&streetId=" + streetId
        + "&landmark=" + landmark
        + "&landmarkId=" + landmarkId
        + "&ratingType=" +  document.getElementById('saveRatingType').value
        + "&ratingId=" +  document.getElementById('saveRatingId').value
        + "&ratingVal=" +  document.getElementById('saveRatingVal').value
        + "&valId=" + ""
        + "&valDate=" + ""
        + "&rate=" +  document.getElementById('saveRateCalc').value
        + "&amount=" +  document.getElementById('saveAmount').value
        + "&ratingDetails=" + ""
        + "&hbUserId=" + ""
        + "&commRate=" + ""
        + "&commIncentive=" + ""
        + "&crid=" + ""
        + "&commAmount=" + ""
        + "&nomType=" + ""       //nomtyp chages ex 1,2
        + "&nomInitialId=" + $("#nomDtlInitial option:selected").text()
        + "&nomFirstName=" + document.getElementById('nomDtlFname').value
        + "&nomMiddleName=" + document.getElementById('nomDtlMname').value
        + "&nomLastName=" + document.getElementById('nomDtlLname').value
        + "&nomDob=" + $("#nomDtlDOB").val()
        + "&nomAadharNo=" + document.getElementById('nomDtlAdharNo').value
        + "&nomGender=" + ""
        + "&nomRelId=" + $("#nomDtlRelation").val()
        + "&nomContactNo=" + document.getElementById('nomDtlMobileNo').value
        + "&nomEmailId=" + document.getElementById('nomDtlEmail').value
        + "&nomAreaId=" + $("#nomDtlPincode").val()
        + "&nomHouse=" + document.getElementById('nomDtlHouseNo').value
        + "&nomHouseId=" + ($("#nomDtlHouseNoList option[value='" + $('#nomDtlHouseNo').val() + "']").attr('rel'))
        + "&nomStreet=" + document.getElementById('nomDtlStreet').value
        + "&nomStreetId=" + ($("#nomDtlStreetList option[value='" + $('#nomDtlStreet').val() + "']").attr('rel'))
        + "&nomLandmark=" + document.getElementById('nomDtlLandmark').value
        + "&nomLandmarkId=" + ($("#nomDtlLandmarkList option[value='" + $('#nomDtlLandmark').val() + "']").attr('rel')) 
        + "&isPrevIns=" + isPrevious			//Changes
        + "&prevGicId=" + prevGicId
        + "&prevProdId=" + prevProdId
        + "&prevPolicyType=" + prevPolicyType
        + "&prevPeriod=" + prevPeriod
        + "&prevStartDate=" + prevStartDate
        + "&prevEndDate=" + prevEndDate
        + "&prevIdv=" + prevIdv
        + "&prevInsType=" + prevInsType
        + "&prevPolicyNo=" + prevPolicyNo
        + "&prevCovernoteNo=" + prevCovernoteNo
        + "&prevOwnerType=" + prevOwnerType
        + "&rcBookNameTransfer=" + rcBookNameTransfer
        + "&rcBookNameTransferDate=" + rcBookNameTransferDate
        + "&insNameTransfer=" + insNameTransfer
        + "&insNameTransferDate=" + insNameTransferDate
        + "&prevNcb=" + prevNcb
        + "&prevIsClaim=" + prevIsClaim
        + "&prevClaimNo=" + prevClaimNo
        + "&prevClaimDate=" + prevClaimDate
        + "&prevClaimAmount=" + prevClaimAmount
        + "&finId=" + "0"		//finId
        + "&finType=" + ""		//finType
        + "&finCityId=" + "0"		//finCity
        + "&isInsp=" + isInspection
        + "&contactPersonName=" + contactPersonName
        + "&inspContactNo=" + inspContactNo
        + "&inspAddId=" + inspAddId
        + "&inspHouse=" + inspHouse
        + "&inspHouseId=" + inspHouseId
        + "&inspStreet=" + inspStreet
        + "&inspStreetId=" + inspStreetId
        + "&inspLandmark=" + inspLandmark
        + "&inspLandmarkId=" + inspLandmarkId
        + "&payBankId=" + ""
        + "&payChqUtrNo=" + paymentList[3]
        + "&payChqTranDate=" + currentDate
        + "&remarks=" + ""
        + "&branchId=" + "0"
        + "&userId=" + "0"
        + "&userDesc=" + "WEB",
      
       
 		      
 		      	type : 'post',
 				dataType : 'json',
 				async : true,
 				 
 			
 		success: function (resp) {
 			 hideWait()
 			//console.log("URL--->>"+url);
 			$.each(resp, function (key, value) {
 				nKey = parseInt(key) + 1;
 				
 			/*	var row2="<tr><td>" + nKey + "</td>" +
 						"<td>" + resp[key].COVERS + "</td>"+
 						"<td>" + resp[key].COV_RATE + "</td>"+
 						"<td>" + resp[key].COV_VAL + "</td>" +
 						"<td>" + resp[key].COV_AMOUNT + "</td>" +
 						"</tr>";
 				
 				$("#previewCompTP").append(row2);*/
 			});
 			/*prevTpTable = $("#previewCompTP").DataTable();*/
 			location.href = "/UAT";
 		},
 		error: function (resp) {
 			 hideWait()
 			//old_alert("error---------- UAT Save data"+resp);
 		},
 		
 		});
     

}

/*$('#dateofReg').datepicker({
    clearBtn: true,
    todayHighlight: false,
    multidate: true

}) .on('changeDate', function(){
	compareMnfAndRegDt();
});*/

