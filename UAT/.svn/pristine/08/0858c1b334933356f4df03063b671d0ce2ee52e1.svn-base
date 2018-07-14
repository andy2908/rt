<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/select2.js"></script>
<script src="js/productDetails.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="css/datepicker3.css">
<script src="js/validation.js"></script>

<title>Insert title here</title>
<script>
	$(document).ready(function() {
		$("#countrySelect,#stateSelect,#categorySelect,#religionSelect,#education,#insDtlMaritialStatus,#corrpincodeSelect,#initialSelect,#corrcountrySelect,#corrcitySelect,#corrdistrictSelect,#corrstateSelect,#districtSelect,#citySelect,#pincodeSelect,#seatTypeSelect,#rowNoSelect,#fromSeatSelect,#toSeatSelect").select2({
			placeholder : "--Select--",
			allowClear : true,
			maximumSelectionSize : 1,
		});

		$('input[type="file"]').change(function(e) {
			var fileName = e.target.files[0].name;
			alert('The file "' + fileName + '" has been selected.');
		});
		
		insDtlInitial();
		populateCountryCorr();
		insDtlMaritialStatus();
		populateEducation();
		populateCategory();
		populateReligion();
		
		$('#dob').datepicker({
		   placeholder:"dd/mm/yyyy",
	       autoclose: true,
	       format:'dd/mm/yyyy',
	    	todayHighlight: true
	    });
	});
	
 	function enableDisable(chkBox,txtFld) {
		document.getElementById('checkboxEducation').onchange = function() {
			 document.getElementById('browseTf').disabled = !this.checked;
			 document.getElementById('btnEcucation').disabled = !this.checked;
			 document.getElementById('btnEcucationClr').disabled = !this.checked;
		};
		document.getElementById('checkboxAadhar').onchange = function() {
			 document.getElementById('aadharTf').disabled = !this.checked;
			 document.getElementById('btnAadhar').disabled = !this.checked;
			 document.getElementById('btnAadharClr').disabled = !this.checked;
		};
		document.getElementById('checkboxPanCard').onchange = function() {
			 document.getElementById('panCardTf').disabled = !this.checked;
			 document.getElementById('btnPanCard').disabled = !this.checked;
			 document.getElementById('btnPanCardClr').disabled = !this.checked;
		};
		document.getElementById('checkboxPassport').onchange = function() {
			 document.getElementById('passportTf').disabled = !this.checked;
			 document.getElementById('btnPassport').disabled = !this.checked;
			 document.getElementById('btnPassportClr').disabled = !this.checked;
		};
		document.getElementById('checkboxDriving').onchange = function() {
			 document.getElementById('drivingLicenseTf').disabled = !this.checked;
			 document.getElementById('btnDrivingLicense').disabled = !this.checked;
			 document.getElementById('btnDrivingLicenseClr').disabled = !this.checked;
		};
 	}
	
	function  clearData() {
		document.getElementById('firstNameTf').value="";
		document.getElementById('middleNameTf').value="";
		document.getElementById('lastNameTf').value="";
		document.getElementById('fathersNameTf').value="";
		document.getElementById('mothersNameTf').value="";
		document.getElementById('aadharNoTf').value="";
		document.getElementById('aadharEnrollTf').value="";
		document.getElementById('panNoTf').value="";
		$("#categorySelect").select2("val","--Select--");
		$("#religionSelect").select2("val","--Select--");
		document.getElementById('phoneNoTf').value="";
		document.getElementById('mobNoTf').value="";
		document.getElementById('emailTf').value="";
		document.getElementById('insDtlOALandmark').value="";
		document.getElementById('insDtlOAStreet').value="";
		document.getElementById('insDtlPALandmark').value="";
		document.getElementById('insDtlPAStreet').value="";
		document.getElementById('insDtlPAHouse').value="";
		$("#initialSelect").select2("val","");
		$("#insDtlMaritialStatus").select2("val","");
		$("#countrySelect").select2("val","");
		$("#stateSelect").select2("val","");
		$("#districtSelect").select2("val","");
		$("#citySelect").select2("val","");
		$("#pincodeSelect").select2("val","");
		$("#corrcountrySelect").select2("val","");
		$("#corrstateSelect").select2("val","");
		$("#corrdistrictSelect").select2("val","");
		$("#corrcitySelect").select2("val","");
		$("#corrpincodeSelect").select2("val","");
		$("#education").select2("val","");
		$("#dob").select2("val","");
		$('input[name=gender]').prop('checked', false);
	}
	
	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	   
	}
	
	function insDtlMaritialStatus(){
		$("#insDtlMaritialStatus").empty();
		$("#insDtlMaritialStatus").append('<option value="">--Select--');
		var resp = getRecordList("70","25");
		$.each(resp, function (key, value) {
			nKey = parseInt(key) + 1;
			$("#insDtlMaritialStatus").append('<option value="' + resp[key].DET_ID + '">'+resp[key].DET_NAME+'');
		});
	}
	function populateEducation(){
		$("#education").empty();
		$("#education").append('<option value="">--Select--');
		var resp = getRecordList("70","9");
		$.each(resp, function (key, value) {
			nKey = parseInt(key) + 1;
			$("#education").append('<option value="' + resp[key].DET_ID + '">'+resp[key].DET_NAME+'');
		});
	}
	
	function insDtlInitial(){
		$("#initialSelect").empty();
		$("#initialSelect").append('<option value="">--Select--');
		var resp = getRecordList("70","4");
		$.each(resp, function (key, value) {
			nKey = parseInt(key) + 1;
			$("#initialSelect").append(
				'<option value="'+resp[key].DET_ID+'">'+resp[key].DET_NAME+''
			);
		});
}
	
	function showname (id,s) {
	      var name = document.getElementById(s); 
	      document.getElementById(id).value=name.files.item(0).name; 
	    };

function populateCountryCorr() {
	var resp = getRecordList("95","null");
	
	$("#corrcountrySelect").empty();
	$("#corrcountrySelect").append('<option value="">--Select--');
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#corrcountrySelect").append('<option value="'+resp[key].COUNTRY_ID+'"> '+ resp[key].COUNTRY_NAME + '');
	});
	
	$("#countrySelect").empty();
	$("#countrySelect").append('<option value="">--Select--');
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		$("#countrySelect").append('<option value="'+resp[key].COUNTRY_ID+'"> '+ resp[key].COUNTRY_NAME + '');
	});
}
function populateReligion() {
	$("#religionSelect").empty();
	$("#religionSelect").append('<option value="">--Select--');
	var resp = getRecordList("70","10");
	$.each(resp, function (key, value) {
		nKey = parseInt(key) + 1;
		$("#religionSelect").append(
			'<option value="'+resp[key].DET_ID+'"> '+resp[key].DET_NAME+''
		);
	});
}
function populateCategory() {
	$("#categorySelect").empty();
	$("#categorySelect").append('<option value="">--Select--');
	var resp = getRecordList("70","11");
	$.each(resp, function (key, value) {
		nKey = parseInt(key) + 1;
		$("#categorySelect").append(
			'<option value="'+resp[key].DET_ID+'"> '+resp[key].DET_NAME+''
		);
	});
	
}

function populateState(){
	var insDtlCACountryId = $("#countrySelect").val();
	        $("#stateSelect").empty();
			$("#stateSelect").append('<option value="">--Select--');
			var resp = getRecordList("96",insDtlCACountryId);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#stateSelect")
					.append(
						'<option value="' + resp[key].STATE_ID + '"> ' +
						resp[key].STATE_NAME +
						'');
			});
	}
function populateStateCorr(){
	var insDtlCACountryId = $("#corrcountrySelect").val();
	        $("#corrstateSelect").empty();
			$("#corrstateSelect").append('<option value="">--Select--');
			var resp = getRecordList("96",insDtlCACountryId);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#corrstateSelect")
					.append(
						'<option value="' + resp[key].STATE_ID + '"> ' +
						resp[key].STATE_NAME +
						'');
			});
	}
	
function populateDistrict() {
	var country = $("#countrySelect").val();
	var proposerState = $("#stateSelect").val();

		var total = country +"~"+proposerState;
		$("#districtSelect").empty();
		$("#districtSelect").append('<option value="">--Select--');
		var resp = getRecordList("97",total);
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#districtSelect").append('<option value="'+resp[key].DISTRICT_ID+'"> '+ resp[key].DISTRICT_NAME + '');
		});
	}
	
function populateDistrictCorr() {
	var country = $("#corrcountrySelect").val();
	var proposerState = $("#corrstateSelect").val();

		var total = country +"~"+proposerState;
		$("#corrdistrictSelect").empty();
		$("#corrdistrictSelect").append('<option value="">--Select--');
		var resp = getRecordList("97",total);
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#corrdistrictSelect").append('<option value="'+resp[key].DISTRICT_ID+'"> '+ resp[key].DISTRICT_NAME + '');
		});
	}
	
function populateCity(){
	var insDtlCACountryId = $("#countrySelect").val();
	var insDtlCAStateId = $("#stateSelect").val();
	var insDtlCADistrictId = $("#districtSelect").val();
//	alert(insDtlCACountryId+"   "+insDtlCAStateId+"  "+insDtlCADistrictId);
	var total = insDtlCACountryId+"~"+insDtlCAStateId+"~"+null;
	//alert("total>>"+total);
			$("#citySelect").empty();
			$("#citySelect").append('<option value="">--Select--');
			var resp = getRecordList("98",total);

			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#citySelect")
					.append(
						'<option value="' + resp[key].CITY_ID + '"> ' +
						resp[key].CITY_NAME +
						'');
			});
	}
function populateCityCorr(){
	var insDtlCACountryId = $("#corrcountrySelect").val();
	var insDtlCAStateId = $("#corrstateSelect").val();
	var insDtlCADistrictId = $("#corrdistrictSelect").val();
//	alert(insDtlCACountryId+"   "+insDtlCAStateId+"  "+insDtlCADistrictId);
	var total = insDtlCACountryId+"~"+insDtlCAStateId+"~"+null;
	//alert("total>>"+total);
			$("#corrcitySelect").empty();
			$("#corrcitySelect").append('<option value="">--Select--');
			var resp = getRecordList("98",total);

			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#corrcitySelect")
					.append(
						'<option value="' + resp[key].CITY_ID + '"> ' +
						resp[key].CITY_NAME +
						'');
			});
	}

function pincodePopulate(){
	
	var insDtlCACountryId = $("#countrySelect").val();
	var insDtlCAStateId = $("#stateSelect").val();
	var insDtlCADistrictId = $("#districtSelect").val();
	var insDtlCACityId = $("#citySelect").val();
	//alert(insDtlCACountryId+"   "+insDtlCAStateId+"  "+insDtlCADistrictId+"  "+insDtlCACityId);
	var total = insDtlCACountryId+"~"+insDtlCAStateId+"~"+insDtlCADistrictId+"~"+null+","+insDtlCACityId;
	//alert("total>>"+total);
			$("#pincodeSelect").empty();
			$("#pincodeSelect").append('<option value="">--Select--');
			var resp = getRecordList("100",total);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#pincodeSelect")
					.append(
						'<option  pincodeCA="' + resp[key].PINCODE + '" value="' + resp[key].AREA_ID + '"> ' +
						resp[key].AREA_NAME +
						'');
			});
		
}

function pincodePopulateCorr(){
	var insDtlCACountryId = $("#corrcountrySelect").val();
	var insDtlCAStateId = $("#corrstateSelect").val();
	var insDtlCADistrictId = $("#corrdistrictSelect").val();
	var insDtlCACityId = $("#corrcitySelect").val();
	//alert(insDtlCACountryId+"   "+insDtlCAStateId+"  "+insDtlCADistrictId+"  "+insDtlCACityId);
	var total = insDtlCACountryId+"~"+insDtlCAStateId+"~"+insDtlCADistrictId+"~"+null+","+insDtlCACityId;
	//alert("total>>"+total);
			$("#corrpincodeSelect").empty();
			$("#corrpincodeSelect").append('<option value="">--Select--');
			var resp = getRecordList("100",total);
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#corrpincodeSelect")
					.append(
						'<option  pincodeCA="' + resp[key].PINCODE + '" value="' + resp[key].AREA_ID + '"> ' +
						resp[key].AREA_NAME +
						'');
			});
		
}


function insDtlPALandmark() {
	document.getElementById("insDtlPALandmark").value = '';
	var areaId = $("#pincodeSelect").val();
	var total = "1"+"~"+areaId;
	$("#insDtlPALandmarkList").empty();
	$("#insDtlPALandmarkList").append('<option rel="" value="">--Select--');
	var resp = getRecordList("109",total);
	
	$.each(resp,function(key, value) {
		nKey = parseInt(key) + 1;
		$("#insDtlPALandmarkList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> '+resp[key].ADD_NAME);
	});
}

function insDtlOALandmark() {
	document.getElementById("insDtlOALandmark").value = '';
	var areaId = $("#corrpincodeSelect").val();
	var total = "1"+"~"+areaId;
	$("#insDtlOALandmarkList").empty();
	$("#insDtlOALandmarkList").append('<option rel="" value="">--Select--');
	var resp = getRecordList("109",total);
	
	$.each(resp,function(key, value) {
		nKey = parseInt(key) + 1;
		$("#insDtlOALandmarkList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> '+resp[key].ADD_NAME);
	});
}

function insDtlPAStreet(){
	document.getElementById('insDtlPAStreet').value='';
	var streetId =($("#insDtlPALandmarkList option[value='" + $('#insDtlPALandmark').val() + "']").attr('rel'));
	if(streetId == undefined ||  streetId == ""){
		streetId="0";
	} 
	var total = "2"+"~"+streetId;

	$("#insDtlPAStreetList").empty();
	$("#insDtlPAStreetList").append('<option rel="" value="">--Select--');
	
	var resp = getRecordList("109",total);
	$.each(resp,function(key, value) {
		nKey = parseInt(key) + 1;
		$("#insDtlPAStreetList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> '+resp[key].ADD_NAME);
	});
}
				
function insDtlOAStreet() {
	document.getElementById('insDtlOAStreet').value='';
	var landmarkId =($("#insDtlOALandmarkList option[value='" + $('#insDtlOALandmark').val() + "']").attr('rel'));
	if(landmarkId == undefined ||  landmarkId == ""){
		landmarkId="0";
	}
	var total = "2"+"~"+landmarkId;

	$("#insDtlOAStreetList").empty();
	$("#insDtlOAStreetList").append('<option rel="" value="">--Select--');
	var resp = getRecordList("109",total);
	$.each(resp,function(key, value) {
		nKey = parseInt(key) + 1;
		$("#insDtlOAStreetList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> '+resp[key].ADD_NAME);
	});
}

function insDtlPAHouse(){
	document.getElementById('insDtlPAHouse').value='';
	var streetId =($("#insDtlPAStreetList option[value='" + $('#insDtlPAStreet').val() + "']").attr('rel'));
	if(streetId == undefined ||  streetId == "")
	{
		streetId="0";
	} 
	var total = "3"+"~"+streetId;

	$("#insDtlPAHouseList").empty();
	$("#insDtlPAHouseList").append('<option rel="" value="">--Select--');
	var resp = getRecordList("109",total);
	$.each(resp,function(key, value) {
		nKey = parseInt(key) + 1;
		$("#insDtlPAHouseList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> '+resp[key].ADD_NAME);
	});
}

function insDtlOAHouse(){
	document.getElementById('insDtlOAHouse').value='';
	
	var streetId =($("#insDtlOAStreetList option[value='" + $('#insDtlOAStreet').val() + "']").attr('rel'));
	if(streetId == undefined ||  streetId == "")
	{
		streetId="0";
	} 
	var total = "3"+"~"+streetId;

	$("#insDtlOAHouseList").empty();
	$("#insDtlOAHouseList").append('<option rel="" value="">--Select--');
	var resp = getRecordList("109",total);
	$.each(resp,function(key, value) {
		nKey = parseInt(key) + 1;
		$("#insDtlOAHouseList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> '+resp[key].ADD_NAME);
	});
}

function setGenderData() {
	 var members=$("#initialSelect :selected").text();
	
	var mem=$("#initialSelect").val();
	
	 if (mem == 1) {
		document.getElementById('male').checked=true;
	} else {
		if (mem == 2||mem == 3||mem == 111) {
			document.getElementById('female').checked=true;
		}	
	} 
}

function copyPermanentAddress() {
	var country = $("#corrcountrySelect").val();
	var state = $("#corrstateSelect").select2("val");
	var district = $("#corrdistrictSelect").select2("val");
	var city = $("#corrcitySelect").select2("val");
	var pincode = $("#corrpincodeSelect").select2("val");
	var landmark = $("#insDtlOALandmark").val();
	var street = $("#insDtlOAStreet").val();
	var house = $("#insDtlOAHouse").val();
	var checked=$("#addressChkbox").prop("checked");
	
	if(checked){
  	  if (!$('#corrcountrySelect,#corrstateSelect,#corrdistrictSelect,#corrcitySelect,#corrpincodeSelect,#insDtlOALandmark,#insDtlOAStreet,#insDtlOAHouse').val()){
  		  alert("Please select corresponding address");
  		  if($("#addressChkbox").prop("checked")){ 
			$("#addressChkbox").prop("checked", false);
			}
  	}else{
  		$("#countrySelect").select2("val",country);
  		populateState();
  		$("#stateSelect").select2("val",state);
  		populateDistrict();
  		$("#districtSelect").select2("val",district);
  		populateCity();
  		$("#citySelect").select2("val",city);
  		pincodePopulate();
  		$("#pincodeSelect").select2("val",pincode);
  		insDtlPALandmark();
  		$("#insDtlPALandmark").val(landmark);
  		insDtlPAStreet();
  		$("#insDtlPAStreet").val(street);
  		insDtlPAHouse();
  		$("#insDtlPAHouse").val(house);
      }
	}else{
	  	$("#countrySelect").select2("val","");
  		$("#stateSelect").select2("val","");
  		$("#districtSelect").select2("val","");
  		$("#citySelect").select2("val","");
  		$("#pincodeSelect").select2("val","");
  		$("#insDtlOALandmark").val("");
  		$("#insDtlOAStreet").val("");
	  	$("#insDtlOAHouse").val("");
      }
}

function dateCheck() {
	var dob = $("#dob").val();
	var date = dob.split('/')[2];
	var d = new Date();
	var lipday = (d.getFullYear() - date);
	if (lipday < 18.000 || (!lipday == 18.000)) {
		alert('Your age should be 18 or greater than 18 yrs!!!');
		$('#dob').val();
		$('#dob').focus();
		return;
	} 
}

function  saveData() {
	var element = document.getElementsByClassName("panel-body")[0]; 
	if(validateForm(element)){
		var userid = $("#userid").val();
		var initialSelect = $("#initialSelect").val();
		var insDtlMaritialStatus = $("#insDtlMaritialStatus").val();
		var categorySelect = $("#categorySelect").val();
		var religionSelect = $("#religionSelect").val();
		var landmark = $("#insDtlOALandmark").val();
		var corrStreet = $("#insDtlOAStreet").val();
		var corrPincode = $("#corrpincodeSelect").val();
		var pincode = $("#pincodeSelect").val();

		var uploadNames = "";
		var ftpId = "";
		var docType = "";
		
		var browseFileName = $("#browseTf").val();
		if(browseFileName!=""){
			uploadNames = browseFileName+"~";
			ftpId = "7~";
			docType = "EDU~";
		}
		
		var aadharFileName = $("#aadharTf").val();
		if(aadharFileName!=""){
			uploadNames = uploadNames+aadharFileName+"~";
			ftpId = ftpId+"7~";
			docType = docType+"AADHAR~";
		}
		
		var panCardFileName = $("#panCardTf").val();
		if(panCardFileName!=""){
			uploadNames = uploadNames+panCardFileName+"~";
			ftpId = ftpId+"7~";
			docType = docType+"PAN~";
		}
		
		var passportFileName = $("#passportTf").val();
		if(passportFileName!=""){
			uploadNames = uploadNames+passportFileName+"~";
			ftpId = ftpId+"7~";
			docType = docType+"PASSPORT~";
		}
		
		var drivingLicenseFileName = $("#drivingLicenseTf").val();
		if(drivingLicenseFileName!=""){
			uploadNames = uploadNames+drivingLicenseFileName+"~";
			ftpId = ftpId+"7~";
			docType = docType+"DL~";
		}
		
		var photoFileName = $("#photoTf").val();
		if(photoFileName!=""){
			uploadNames = uploadNames+photoFileName+"~";
			ftpId = ftpId+"7~";
			docType = docType+"PHOTO~";
		}
		
		var signFileName = $("#signTf").val();
		if(signFileName!=""){
			uploadNames = uploadNames+signFileName+"~";
			ftpId = ftpId+"7~";
			docType = docType+"SIGN~";
		}

	    var houseId = $("#insDtlPAHouseList option[value='" + $('#insDtlPAHouse').val() + "']").attr('rel');
	    if(houseId == undefined ||  houseId == "")
		{
	    	houseId="0";
		}
	    var houseNo = $("#insDtlPAHouse").val();
		var houseIdCorr = $("#insDtlOAHouseList option[value='" + $('#insDtlOAHouse').val() + "']").attr('rel');
		if(houseIdCorr == undefined ||  houseIdCorr == "")
		{
			houseIdCorr="0";
		}
		var houseNoCorr = $("#insDtlOAHouse").val();
		var streetId = $("#insDtlPAStreetList option[value='" + $('#insDtlPAStreet').val() + "']").attr('rel');
		if(streetId == undefined ||  streetId == "")
		{
			streetId="0";
		}
		var street = $("#insDtlPAStreet").val();
		var streetIdCorr = $("#insDtlOAStreetList option[value='" + $('#insDtlOAStreet').val() + "']").attr('rel');
		if(streetIdCorr == undefined ||  streetIdCorr == "")
		{
			streetIdCorr="0";
		}
		var streetCorr = $("#insDtlOAStreet").val();
		var landmarkId = $("#insDtlPALandmarkList option[value='" + $('#insDtlPALandmark').val() + "']").attr('rel');
		if(landmarkId == undefined ||  landmarkId == "")
		{
			landmarkId="0";
		}
		var landmark = $("#insDtlPALandmark").val();
		var landmarkIdCorr = $("#insDtlOALandmarkList option[value='" + $('#insDtlOALandmark').val() + "']").attr('rel');
		if(landmarkIdCorr == undefined ||  landmarkIdCorr == "")
		{
			landmarkIdCorr="0";
		}
		var landmarkCorr = $("#insDtlOALandmark").val();
		
		var educationId = $("#education").val();
	    
		var firstName = $('#firstNameTf').val();
		var middleName = $('#middleNameTf').val();
		var lastName = $('#lastNameTf').val();
		var fathersName = $('#fathersNameTf').val();
		var mothersName = $('#mothersNameTf').val();
		var maritalStatus = $('#insDtlMaritialStatus').val();
		var aadharNo = $('#aadharNoTf').val();
		var aadharEnroll = $('#aadharEnrollTf').val();
		var panNo = $('#panNoTf').val();
		var phoneNo = $('#phoneNoTf').val();
		var mobNo = $('#mobNoTf').val();
		var email = $('#emailTf').val();
		alert("email==>"+email);
		var dob = $('#dob').val();
		
		var insDtlGender=""; 
		if (document.getElementById('male').checked) {
			insDtlGender = "M";
		}
		else if (document.getElementById('female').checked) {
			insDtlGender = "F";
		}
		else if (document.getElementById('other').checked) {
			insDtlGender = "O";
		}

		var formData = new FormData();
		
		formData.append("titleId",initialSelect);
		formData.append("firstName",firstName);
		formData.append("middleName",middleName);
		formData.append("lastName",lastName);
		formData.append("firstNameOl",firstName);
		formData.append("middleNameOl",middleName);
		formData.append("lastNameOl",lastName);
		formData.append("fatherName",fathersName);
		formData.append("motherName",mothersName);
		formData.append("gender",insDtlGender);
		formData.append("maritalStatus",maritalStatus);
		formData.append("birthDate",dob);
		formData.append("aadharEnroll",aadharEnroll);
		formData.append("aadharNo",aadharNo);
		formData.append("panNo",panNo);
		formData.append("categoryId",categorySelect);
		formData.append("religionId",religionSelect);
		formData.append("phoneNo",phoneNo);
		formData.append("mobNo",mobNo);
		formData.append("emailId",email);
		formData.append("adrType","P"+"~"+"C");
		formData.append("areaId",pincode +"~"+ corrPincode);
		formData.append("landmarkId",landmarkId + "~" + landmarkIdCorr);
		formData.append("landmark",landmark +"~"+ landmarkCorr);
		formData.append("streetId",streetId+ "~" + streetIdCorr);
		formData.append("street",street +"~"+ streetCorr);
		formData.append("houseId",houseId+"~"+houseIdCorr);
		formData.append("house",houseNo + "~" + houseNoCorr);
		formData.append("educationId",educationId);
		formData.append("uploadName",uploadNames);
		formData.append("docType",docType);
		formData.append("docPath",uploadNames);
		formData.append("ftpId",ftpId);
		
		formData.append("posMstID","0");
		formData.append("opflag","N");
		formData.append("userid","1");
		formData.append("branchid","1");
		formData.append("status","E");
		formData.append("strid","");
		formData.append("userDesc","REG");
		
		$.ajax({
					url : "${pageContext.request.contextPath}/saveRegistration",
					type : 'POST',
					async : false,
					data: formData,
					contentType: false,
					processData: false,
					success : function(resp) {
						alert(resp.msg);
					},
					error : function(resp) {
						alert(resp.msg);
					},
				});
// 		location.reload();
	}
}
</script>
</head>
<body>
<input type="hidden" id="userid" value="${ userid }">
	<div class="panel-title"
		style="background-color: #d1bebe; width: 700px">
		Application Form Appointment of Point Of Sale Person(POSP)
	</div>
	<div class="panel-body" id="panel" style="background-color: #4cbcce; width: 700px">
		<div class="panel"
			style="background-color: #4cbcce; border-style: groove; border-color: black">
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Applicants
							Name</font></label>
				</div>
				<div class="col-md-1">
					<select id="initialSelect" onclick="setGenderData()" class="mandatory" style="width: 70px;" tabindex="1" >
						<option>Select</option>
					</select>
				</div>
				<div class="col-md-2">
					<input id="firstNameTf" class="mandatory" required="" type="text"
						autofocus="autofocus" placeholder="First Name" tabindex="2"
						style="width: 100px;margin-left: 20px">
				</div>
				<div class="col-md-3" style="width: 160px">
					<input id="middleNameTf" class="mandatory" required="" type="text"
						placeholder="Middle Name" tabindex="3" style="width: 140px; margin-left: 10px;" >
				</div>
				<div class="col-md-3">
					<input id="lastNameTf" class="mandatory" required="" type="text"
						placeholder="Last Name" tabindex="4" style="width: 150px" >
				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Fathers
							Name</font></label>

				</div>
				<div class="col-md-3">
					<input id="fathersNameTf" class="mandatory" type="text" tabindex="5" style="width: 160px" >
				</div>
				<div class="col-md-3" style="width: 158px">
					<label for="contact"><font color="#ffffff">Mothers
							Name</font></label>
				</div>
				<div class="col-md-3">
					<input id="mothersNameTf" class="mandatory" required="" type="text" tabindex="6"style="width: 150px" >
				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Gender</font></label>

				</div>
				<div class="col-md-3">
					<label><input id="male" type="radio" tabindex="7" name="gender"
						required="required" value="male"><font color="#ffffff">Male</font></label>
					<label><input id="female" type="radio" tabindex="8" name="gender"><font
						color="#ffffff" required="required" value="female">Female</font></label> <label><input
						id="other" type="radio" tabindex="9" name="gender"><font
						color="#ffffff" required="required" value="other">Other</font></label>
				</div>
				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">Marital
							Status</font></label>
				</div>
				<div class="col-md-3">
					<select id="insDtlMaritialStatus" class="mandatory" style="width: 150px;" tabindex="10">
						<option>Select</option>
					</select>
				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Date Of
							Birth</font></label>

				</div>
				<div class="col-md-3">
							<input id="dob" class="mandatory" placeholder="dd/mm/yyyy"  required="" type="text" onchange="dateCheck();" tabindex="10" style="width: 160px" >
				</div>

                	<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Aadhar
							Enroll</font></label>

				</div>
				<div class="col-md-3">
					<input id="aadharEnrollTf" required="" type="text" tabindex="11" class="validateAadharEnrol"
						style="width: 150px; margin-left: 15px;" onkeypress="return isNumber(event)" >
				</div>
				
				
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">Aadhar No</font></label>
				</div>
				<div class="col-md-3">
					<input id="aadharNoTf" required="" class="mandatory validateAadhar" type="text" tabindex="12" style="width: 160px; margin-left: -15px;" 
						 onkeypress="return isNumber(event)" >
				</div>

				<div class="col-md-3" style="width: 160px">
					<label for="contact" style="margin-left: -15px;"><font color="#ffffff">PAN No</font></label>
				</div>
				<div class="col-md-3">
					<input id="panNoTf" class="mandatory validatePan" required="" type="text" tabindex="13" style="width: 150px; margin-left: -15px;"
						maxlength="10">
				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Category</font></label>

				</div>
				<div class="col-md-3">
					<select id="categorySelect" class="mandatory" style="width: 160px;" tabindex="14"><option>--Select--</option></select>
				</div>

				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">Religion</font></label>
				</div>
				<div class="col-md-3">
					<select id="religionSelect" class="mandatory" style="width: 150px;" tabindex="15"><option>--Select--</option></select>
				</div>
			</div>
		</div>
		<div class="panel-title" style="background-color: #d1bebe;">
			&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"F
				data-parent="#accordion" href="#collapseOne" aria-expanded="true"
				aria-controls="collapseOne"> Contact Details </a>
		</div>
		<br>
		<div id="collapseOne" class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="headingOne" class="main wrapper2"
			style="margin: auto;">
			<div class="gap" style="height: 10px; width: 475px"></div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Phone No</font></label>

				</div>
				<div class="col-md-3">
					<input id="phoneNoTf" class="mandatory validatePhone" required="" type="text" style="width: 140px"
						maxlength="314" onkeypress="return isNumber(event)" tabindex="16">
				</div>
				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">Mobile No</font></label>
				</div>
				<div class="col-md-3">
					<input id="mobNoTf" class="mandatory validateMobile" required="" type="text" maxlength="10"
						onkeypress="return isNumber(event)" tabindex="17">
				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Email
							Address</font></label>

				</div>
				<div class="col-md-3">
					<input id="emailTf" class="mandatory validateEmail" required type="text" style="width: 140px" tabindex="18" > 
				</div>

			</div>

		</div>
				<div class="panel-title" style="background-color: #d1bebe;">
			&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
				data-parent="#accordion" href="#collapseThree" aria-expanded="true"
				aria-controls="collapseOne"> Correspondence Address </a>
		</div>
		<div id="collapseThree" class="panel-collapse collapse"
			role="tabpanel" aria-labelledby="headingOne" class="main wrapper2"
			style="margin: auto;">
			<div class="gap" style="height: 10px; width: 475px"></div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Country</font></label>

				</div>
				<div class="col-md-3">
					<select id="corrcountrySelect" class="mandatory" onchange="populateStateCorr()"
						style="width: 140px;" tabindex="19"><option>--Select--</option></select>
				</div>
				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">State</font></label>
				</div>
				<div class="col-md-3">
					<select id="corrstateSelect" class="mandatory" onchange="populateDistrictCorr()"
						style="width: 140px;" tabindex="20"><option>--Select--</option></select>
				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">District</font></label>

				</div>
				<div class="col-md-3">
					<select id="corrdistrictSelect" class="mandatory" onchange="populateCityCorr()"
						style="width: 140px;" tabindex="21"><option>--Select--</option></select>
				</div>
				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">City</font></label>
				</div>
				<div class="col-md-3">
					<select id="corrcitySelect" class="mandatory" onchange="pincodePopulateCorr()"
						style="width: 140px;" tabindex="22"><option>--Select--</option></select>
				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Pincode</font></label>

				</div>
				<div class="col-md-3">
					<select id="corrpincodeSelect" class="mandatory" onchange="insDtlOALandmark()"
						style="width: 140px;" tabindex="23"><option>--Select--</option></select>
				</div>
				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">Landmark</font></label>
				</div>
				<div class="col-md-3">
<input id="insDtlOALandmark" class="mandatory" onchange="insDtlOAStreet()" list="insDtlOALandmarkList" type="text" tabIndex="24"
            			required="required" style="width: 150px;" >
					 <datalist id="insDtlOALandmarkList"></datalist>				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Street</font></label>

				</div>
				<div class="col-md-3">
					<input id="insDtlOAStreet" class="mandatory" onchange="insDtlOAHouse()"  list="insDtlOAStreetList" type="text" tabIndex="25"
            		required="required"  style="width: 150px;" >
           			 <datalist id="insDtlOAStreetList"></datalist>
				</div>
				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">House No</font></label>
				</div>
				<div class="col-md-3">
					<input id="insDtlOAHouse" class="mandatory" list="insDtlOAHouseList"  type="text" tabIndex="26"
            		required="required"  style="width: 150px;" >
            		<datalist id="insDtlOAHouseList"></datalist>
				</div>
			</div>
		</div>
	
		
		<br>
<div class="panel-title" style="background-color: #d1bebe;">
			&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
				data-parent="#accordion" href="#collapseTwo" aria-expanded="true"
				aria-controls="collapseOne" style="padding: 5px"> Permanent
				Address </a>
				
								<input type="checkbox" id="addressChkbox" onclick="copyPermanentAddress()"> Address same as above
				
		</div>
		<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="headingOne" class="main wrapper2"
			style="margin: auto;">
			<div class="gap" style="height: 10px; width: 475px"></div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Country</font></label>

				</div>
				<div class="col-md-3">
					<select id="countrySelect" onchange="populateState()"
						style="width: 140px"><option>--Select--</option></select>
				</div>
				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">State</font></label>
				</div>
				<div class="col-md-3">
					<select id="stateSelect" onchange="populateDistrict()"
						style="width: 140px"><option>--Select--</option></select>
				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">District</font></label>

				</div>
				<div class="col-md-3">
					<select id="districtSelect" onchange="populateCity()"
						style="width: 140px"><option>--Select--</option></select>
				</div>

				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">City</font></label>
				</div>
				<div class="col-md-3">
					<select id="citySelect" onchange="pincodePopulate()"
						style="width: 140px"><option>--Select--</option></select>
				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Pincode</font></label>

				</div>
				<div class="col-md-3">
					<select id="pincodeSelect" onchange="insDtlPALandmark()"
						style="width: 140px"><option>--Select--</option></select>
				</div>
				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">Landmark</font></label>
				</div>
				<div class="col-md-3">
				<input id="insDtlPALandmark" onchange="insDtlPAStreet()" list="insDtlPALandmarkList" type="text"
            		required="required"  style="width: 150px;" tabIndex="87">
					<datalist id="insDtlPALandmarkList"></datalist>
				</div>
			</div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Street</font></label>

				</div>
				<div class="col-md-3">
					<input id="insDtlPAStreet" onchange="insDtlPAHouse()"  list="insDtlPAStreetList" type="text" tabIndex="88"
            		required="required"  style="width: 150px;">
            		<datalist id="insDtlPAStreetList"></datalist>
				</div>
				<div class="col-md-3" style="width: 160px">
					<label for="contact"><font color="#ffffff">House No</font></label>
				</div>
				<div class="col-md-3">
					<input id="insDtlPAHouse"  list="insDtlPAHouseList"  type="text" tabIndex="89"
            		required="required"  style="width: 150px;">
            		<datalist id="insDtlPAHouseList"></datalist>
				</div>
			</div>
		</div>

		<br>
		<div class="panel-title" style="background-color: #d1bebe;">
			&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
				data-parent="#accordion" href="#collapseFour" aria-expanded="true"
				aria-controls="collapseOne"> Education </a>
		</div>
		<div id="collapseFour" class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="headingOne" class="main wrapper2"
			style="margin: auto;">
			<div class="gap" style="height: 10px; width: 475px"></div>
			<div class="row" style="padding: 5px">
				<div class="col-md-3" style="width: 145px">
					<label for="contact"><font color="#ffffff">Qualification</font></label>

				</div>
				<div class="col-md-3">
					<select id="education" class="mandatory" style="width: 140px"><option>--Select--</option></select>
				</div>

			</div>
		</div>
		<br>
		<div class="panel-title" style="background-color: #d1bebe;">
			&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
				data-parent="#accordion" href="#collapseFive" aria-expanded="true"
				aria-controls="collapseOne"> Upload Documents </a>
		</div>
		<div id="collapseFive" class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="headingOne" class="main wrapper2"
			style="margin: auto;">
		
<table class="table" style="margin: auto;border=1">
                 
                <tr> 
                         <td style="width: 40px">
                         <input type="checkbox" class="form-check-input" id="checkboxEducation"
                         onclick="enableDisable()">
                         </td>
					     <td style="width: 700px">
					     Education Certificate &nbsp;
					     (Minimum Qualification:Senior Secondary School Certificate or its equivalent)
					     </td>
					    <td style="width: 380px">
					    <form id="myForm">
					    <input type="text" id="browseTf" disabled="disabled">
						<input type="file" id="selectedFile" style="display: none;"  multiple onchange="showname('browseTf','selectedFile')" />
						<input type="button" id="btnEcucation" disabled="disabled" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile').click();"/>
					    <input type="button" id="btnEcucationClr" disabled="disabled" style="height: 30px" value="Clear" class="btn btn-primary" 
					    onclick="document.getElementById('browseTf').value = ''">
					    </form>
						</td>
                 </tr>
                
                <tr>
                     <td>
                     	<input type="checkbox" class="form-check-input" id="checkboxAadhar" 
                     	onclick="enableDisable()">
                     </td>
					 <td>Aadhar No</td>
					  <td style="width: 380px">
						<form id="myForm1">
					    <input type="text" id="aadharTf" disabled="disabled">
						<input type="file" id="selectedFile1" style="display: none;"  onchange="showname('aadharTf','selectedFile1')" >
						<input type="button" id="btnAadhar" disabled="disabled" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile1').click();"/>
					    <input type="button" id="btnAadharClr" disabled="disabled" style="height: 30px" value="Clear" class="btn btn-primary"  
						onclick="document.getElementById('aadharTf').value = ''">
					    </form>
					 </td>
               </tr>
               <tr>
                     <td><input type="checkbox" class="form-check-input" id="checkboxPanCard" 
                     onclick="enableDisable()">
                     </td>
					 <td>Pan Card No</td>
					  <td style="width: 380px">
					<form id="myForm2">
					    <input type="text" id="panCardTf" disabled="disabled">
						<input type="file" id="selectedFile2" style="display: none;"  onchange="showname('panCardTf','selectedFile2')" >
						<input type="button" id="btnPanCard" disabled="disabled" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile2').click();"/>
					    <input type="button" id="btnPanCardClr" disabled="disabled" style="height: 30px" value="Clear" class="btn btn-primary"
						onclick="document.getElementById('panCardTf').value = ''">
					    </form>
					 </td>
              </tr>
              <tr>
                     <td><input type="checkbox" class="form-check-input" id="checkboxPassport"
                     onclick="enableDisable()">
                     </td>
					 <td>Passport</td>
					  <td  style="width: 380px">
					 <form id="myForm3">
					    <input type="text" id="passportTf" disabled="disabled">
						<input type="file" id="selectedFile3" style="display: none;"  onchange="showname('passportTf','selectedFile3')" >
						<input type="button" id="btnPassport" disabled="disabled" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile3').click();"/>
					    <input type="button" id="btnPassportClr" disabled="disabled" style="height: 30px" value="Clear" class="btn btn-primary"  
						onclick="document.getElementById('passportTf').value = ''">
					    </form>
					 </td>
              </tr>
              <tr>
                     <td><input type="checkbox" class="form-check-input" id="checkboxDriving"
                     onclick="enableDisable()">
                     </td>
					 <td>Driving License</td>
					  <td  style="width: 380px">
					  <form id="myForm4">
					    <input type="text" id="drivingLicenseTf" disabled="disabled">
						<input type="file" id="selectedFile4" style="display: none;" onchange="showname('drivingLicenseTf','selectedFile4')" >
						<input type="button" id="btnDrivingLicense" disabled="disabled" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile4').click();"/>
					    <input type="button" id="btnDrivingLicenseClr" disabled="disabled" style="height: 30px" value="Clear" class="btn btn-primary"
						onclick="document.getElementById('drivingLicenseTf').value = ''">
					    </form>
					 </td>
              </tr>
              <tr>
                     <td></td>
					 <td>Photo</td>
					  <td  style="width: 380px">
					<form id="myForm5">
					    <input type="text" id="photoTf">
						<input type="file" id="selectedFile5" style="display: none;"  onchange="showname('photoTf','selectedFile5')" >
						<input type="button" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile5').click();"/>
					    <input type="button" style="height: 30px" value="Clear" class="btn btn-primary"  
					    onclick="document.getElementById('photoTf').value = ''">
					    </form>
					 </td>
              </tr>
              <tr>
                     <td></td>
					 <td>Sign</td>
					  <td  style="width: 380px">
					 <form id="myForm6">
					    <input type="text" id="signTf">
						<input type="file" id="selectedFile6" style="display: none;"  onchange="showname('signTf','selectedFile6')" >
						<input type="button" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile6').click();"/>
					    <input type="button" style="height: 30px" value="Clear" class="btn btn-primary" 
						onclick="document.getElementById('signTf').value = ''">
					    </form>
					 </td>
              </tr>
      </table>
		</div>
		<div class="panel" style="background-color: #4cbcce; width: 600px">
			<div class="modal-footer">
				<div class="col-md-7"></div>
				<div class="col-md-5">
					<button type="button" class="btn btn-primary" onclick="saveData()">Submit</button>
					<button type="button" class="btn btn-primary" onclick="clearData();">Clear</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>